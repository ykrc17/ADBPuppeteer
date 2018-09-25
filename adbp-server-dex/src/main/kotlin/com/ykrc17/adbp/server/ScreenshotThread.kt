package com.ykrc17.adbp.server

import android.graphics.Bitmap
import android.hardware.display.DisplayManagerGlobal
import android.util.DisplayMetrics
import android.view.Display
import android.view.SurfaceControl
import java.util.concurrent.LinkedTransferQueue

object ScreenshotThread : Thread() {
    val queue = LinkedTransferQueue<Bitmap>()
    val width: Int
    val height: Int

    init {
        val display = DisplayManagerGlobal.getInstance().getRealDisplay(Display.DEFAULT_DISPLAY)
        val metrics = DisplayMetrics()
        display.getRealMetrics(metrics)
        width = (metrics.widthPixels / metrics.density).toInt()
        height = (metrics.heightPixels / metrics.density).toInt()
        println("width: $width, height: $height")
    }

    override fun run() {
        super.run()
        while (true) {
//            val time = System.currentTimeMillis()
            val bitmap = SurfaceControl.screenshot(width, height)
//            println("screenshot: " + (System.currentTimeMillis() - time))
            queue.transfer(bitmap)
        }
    }
}