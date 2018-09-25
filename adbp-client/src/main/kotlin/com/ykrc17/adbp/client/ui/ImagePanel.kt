package com.ykrc17.adbp.client.ui

import android.view.MotionEvent
import com.ykrc17.adbp.client.SocketClient
import com.ykrc17.adbp.client.adb.ImageFetcher
import com.ykrc17.adbp.client.input.KeyEventDispatcher
import com.ykrc17.adbp.entity.ClientMotionEvent
import java.awt.Dimension
import java.awt.EventQueue
import java.awt.Graphics
import java.awt.event.*
import javax.swing.JPanel


class ImagePanel : JPanel() {

    init {
        EventQueue.invokeLater {
            addMouseListener(mouseListener)
            addMouseMotionListener(mouseListener)
            addKeyListener(keyListener)
        }
//        try {
        val image = ImageFetcher.image
        preferredSize = Dimension(Math.round(image.width * DRAW_SCALE), Math.round(image.height * DRAW_SCALE))
//        } catch (ex: Exception) {
//            error(ex)
//        }
        ImageFetcher.startFetch(this)
    }

    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        val image = ImageFetcher.image
        g.drawImage(image, 0, 0, Math.round(image.width * DRAW_SCALE), Math.round(image.height * DRAW_SCALE), this) // see javadoc for more info on the parameters
    }

    private val mouseListener = object : MouseListener, MouseMotionListener {

        override fun mousePressed(e: MouseEvent) {
            sendMotionEvent(e, MotionEvent.ACTION_DOWN)
        }

        override fun mouseReleased(e: MouseEvent) {
            sendMotionEvent(e, MotionEvent.ACTION_UP)
        }

        override fun mouseDragged(e: MouseEvent) {
            sendMotionEvent(e, MotionEvent.ACTION_MOVE)
        }

        override fun mouseClicked(e: MouseEvent) {
        }

        override fun mouseEntered(e: MouseEvent) {
        }

        override fun mouseExited(e: MouseEvent) {
        }

        override fun mouseMoved(e: MouseEvent) {
        }
    }

    private fun sendMotionEvent(e: MouseEvent, action: Int) {
        println("action: $action, ${e.point}")
        val x = e.x / DRAW_SCALE / SCREEN_SHOT_SCALE
        val y = e.y / DRAW_SCALE / SCREEN_SHOT_SCALE
        SocketClient.newSocket(ClientMotionEvent(x, y, action)) {}
    }

    val keyListener = object : KeyListener {
        override fun keyTyped(e: KeyEvent) {
        }

        override fun keyPressed(e: KeyEvent) {
            println("keyPressed: ${e.keyCode}")
            KeyEventDispatcher.down(e)
        }

        override fun keyReleased(e: KeyEvent) {
        }
    }

    companion object {
        const val DRAW_SCALE = 1f
        const val SCREEN_SHOT_SCALE = 1f / 3
    }
}