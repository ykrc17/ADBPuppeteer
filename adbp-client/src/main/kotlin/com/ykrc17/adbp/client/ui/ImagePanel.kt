package com.ykrc17.adbp.client.ui

import com.ykrc17.adbp.client.SocketClient
import com.ykrc17.adbp.client.adb.ImageFetcher
import com.ykrc17.adbp.client.input.KeyEventDispatcher
import com.ykrc17.adbp.entity.InputTapEvent
import java.awt.Dimension
import java.awt.EventQueue
import java.awt.Graphics
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import javax.swing.JPanel


class ImagePanel : JPanel() {

    init {
        EventQueue.invokeLater {
            addMouseListener(mouseListener)
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

    private val mouseListener = object : MouseListener {
        override fun mouseClicked(e: MouseEvent) {
            println(e.point)
            val x = e.x / DRAW_SCALE / SCREEN_SHOT_SCALE
            val y = e.y / DRAW_SCALE / SCREEN_SHOT_SCALE
            SocketClient.newSocket(InputTapEvent(x, y)) {}
//        Runtime.getRuntime().exec("adb shell input tap $x $y")
        }

        override fun mouseReleased(e: MouseEvent) {
        }

        override fun mouseEntered(e: MouseEvent) {
        }

        override fun mouseExited(e: MouseEvent) {
        }

        override fun mousePressed(e: MouseEvent) {
        }
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