package com.ykrc17.adbp.server

import android.graphics.Bitmap
import java.net.ServerSocket


fun main(args: Array<String>) {
    ScreenshotThread.start()

    val serverSocket = ServerSocket(8000)
    println("waiting client...")
    while (true) {
        val socket = serverSocket.accept()
        val sin = socket.getInputStream()
        val sout = socket.getOutputStream()
        val time = System.currentTimeMillis()
        ScreenshotThread.queue.take().compress(Bitmap.CompressFormat.JPEG, 90, sout)
        println("transfer: " + (System.currentTimeMillis() - time))
        socket.close()
    }
    println("closed")
}