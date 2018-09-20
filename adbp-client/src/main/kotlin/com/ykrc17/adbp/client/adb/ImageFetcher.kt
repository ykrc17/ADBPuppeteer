package com.ykrc17.adbp.client.adb

import java.awt.Component
import java.awt.EventQueue
import java.awt.image.BufferedImage
import java.util.*
import java.util.concurrent.TimeUnit
import javax.imageio.ImageIO

object ImageFetcher {
    private val fpsList = LinkedList<Long>()

    private var imageInner: BufferedImage? = null

    var image: BufferedImage
        @Synchronized get() {
            if (imageInner == null) {
                imageInner = fetchImage()
            }
            return imageInner!!
        }
        private set(value) {
            imageInner = value
        }

    fun startFetch(component: Component) {
        Thread(Runnable {
            while (true) {
                image = fetchImage()
                EventQueue.invokeLater {
                    component.repaint()
                }
                fpsList.add(System.currentTimeMillis())
                while (TimeUnit.SECONDS.convert(System.currentTimeMillis() - fpsList.peek(), TimeUnit.MILLISECONDS) > 5) {
                    fpsList.pollFirst()
                }
                println(fpsList.size / 5f)
            }
        }).start()
    }

    private fun fetchImage(): BufferedImage {
        val process = Runtime.getRuntime().exec("adb exec-out screencap -p")
        return ImageIO.read(process.inputStream)
    }
}