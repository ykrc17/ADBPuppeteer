package com.ykrc17.adbp.server

import android.graphics.Bitmap
import android.view.SurfaceControl
import java.io.OutputStream
import java.net.ServerSocket


fun main(args: Array<String>) {
    val serverSocket = ServerSocket(8000)
    println("waiting client...")
    while (true) {
        val socket = serverSocket.accept()
        val sin = socket.getInputStream()
        val sout = socket.getOutputStream()
        screenshot(sout)
        socket.close()
    }
    println("closed")
//    screenshot(File("/sdcard/temp.jpg").outputStream())
}

fun screenshot(out: OutputStream) {
    var time = System.currentTimeMillis()
//    val bitmap = SurfaceControl.screenshot(1080, 1920)
    val bitmap = SurfaceControl.screenshot(360, 640)
//    println(System.currentTimeMillis() - time)
//
//    time = System.currentTimeMillis()
    bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out)
    println(System.currentTimeMillis() - time)
}