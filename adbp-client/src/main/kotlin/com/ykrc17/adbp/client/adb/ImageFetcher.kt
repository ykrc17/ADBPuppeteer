package com.ykrc17.adbp.client.adb

import com.ykrc17.adbp.client.SocketClient
import com.ykrc17.adbp.entity.ScreenEvent
import java.awt.Component
import java.awt.EventQueue
import java.awt.image.BufferedImage
import java.util.*
import javax.imageio.ImageIO

object ImageFetcher {
    const val FPS_ON = false
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
                val time = System.currentTimeMillis()
                image = fetchImage()
                // 最大30帧
                val sleepTime = Math.max(0, 33 - (System.currentTimeMillis() - time))

                EventQueue.invokeLater {
                    component.repaint()

                    // fps
                    if (FPS_ON) {
                        fpsList.add(System.currentTimeMillis())
                        while (System.currentTimeMillis() - fpsList.peek() > 1000) {
                            fpsList.pollFirst()
                        }
                        println(fpsList.size)
                    }
                }

                Thread.sleep(sleepTime)
            }
        }).start()
    }

    private fun fetchImage(): BufferedImage {
        SocketClient.call(ScreenEvent()) {
            image = ImageIO.read(it)
        }
        return image
    }
}
