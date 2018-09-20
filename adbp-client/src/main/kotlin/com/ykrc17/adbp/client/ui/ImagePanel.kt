package com.ykrc17.adbp.client.ui

import com.ykrc17.adbp.client.adb.ImageFetcher
import java.awt.Dimension
import java.awt.Graphics
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import javax.swing.JPanel


class ImagePanel : JPanel(), MouseListener {

    init {
        addMouseListener(this)
//        try {
        val image = ImageFetcher.image
        preferredSize = Dimension(Math.round(image.width * SCALE), Math.round(image.height * SCALE))
//        } catch (ex: Exception) {
//            error(ex)
//        }
        ImageFetcher.startFetch(this)
    }

    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        val image = ImageFetcher.image
        g.drawImage(image, 0, 0, Math.round(image.width * SCALE), Math.round(image.height * SCALE), this) // see javadoc for more info on the parameters
    }

    override fun mouseClicked(e: MouseEvent) {
        println(e.point)
        Runtime.getRuntime().exec("adb shell input tap ${Math.round(e.x / SCALE)} ${Math.round(e.y / SCALE)}")
    }

    override fun mouseReleased(e: MouseEvent) {
    }

    override fun mouseEntered(e: MouseEvent) {
    }

    override fun mouseExited(e: MouseEvent) {
    }

    override fun mousePressed(e: MouseEvent) {
    }

    companion object {
        const val SCALE = 0.5f
    }
}