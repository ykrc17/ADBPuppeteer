package com.ykrc17.adbp.server.internal

import android.graphics.Bitmap


object SurfaceControl {
    private val clazz: Class<*> = Class.forName("android.view.SurfaceControl")

    fun screenshot(width: Int, height: Int): Bitmap {
        val method = clazz.getMethod("screenshot", Int::class.javaPrimitiveType, Int::class.javaPrimitiveType)
        method.isAccessible = true
        return method.invoke(null, width, height) as Bitmap
    }
}
