package com.ykrc17.adbp.server.screenshot

import android.graphics.Bitmap
import android.graphics.Rect
import android.os.Build
import android.view.Surface
import android.view.SurfaceControl

object SurfaceControlCompat {
    fun screenshot(width: Int, height: Int): Bitmap {
        return when {
            Build.VERSION.SDK_INT >= 28 -> {
                SurfaceControl.screenshot(Rect(), width, height, if (width > height) {
                    Surface.ROTATION_90
                } else {
                    Surface.ROTATION_0
                })
            }
            else -> {
                SurfaceControl.screenshot(width, height)
            }
        }
    }
}