package com.ykrc17.adbp.server

import android.graphics.Bitmap
import android.view.SurfaceControl
import java.util.concurrent.LinkedTransferQueue

object ScreenshotThread : Thread() {
    val queue = LinkedTransferQueue<Bitmap>()

    override fun run() {
        super.run()
        while (true) {
//            val time = System.currentTimeMillis()
            val bitmap = SurfaceControl.screenshot(360, 640)
//            println("screenshot: " + (System.currentTimeMillis() - time))
            queue.transfer(bitmap)
        }
    }
}