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
    var ssScaleX = 1f
    var ssScaleY = 1f

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
        ssScaleX = image.width / 1080f
        ssScaleY = image.height / 1920f
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
            val x = Math.round(e.x / DRAW_SCALE / ssScaleX)
            val y = Math.round(e.y / DRAW_SCALE / ssScaleY)
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
    }
}