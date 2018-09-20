package com.ykrc17.adbp.server

import android.graphics.Bitmap
import com.ykrc17.adbp.server.internal.SurfaceControl
import java.io.File


fun main(args: Array<String>) {
    var time = System.currentTimeMillis()
//    val bitmap = SurfaceControl.screenshot(1080, 1920)
    val bitmap = SurfaceControl.screenshot(360, 640)
    println(System.currentTimeMillis() - time)

    time = System.currentTimeMillis()
    bitmap.compress(Bitmap.CompressFormat.JPEG, 90, File("/sdcard/temp.jpg").outputStream())
    println(System.currentTimeMillis() - time)
}