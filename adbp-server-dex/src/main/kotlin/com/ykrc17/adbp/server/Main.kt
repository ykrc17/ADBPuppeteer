package com.ykrc17.adbp.server

import android.graphics.Bitmap
import com.ykrc17.adbp.entity.InputKeyEvent
import com.ykrc17.adbp.entity.ClientMotionEvent
import com.ykrc17.adbp.entity.ScreenEvent
import com.ykrc17.adbp.server.handler.KeyEventHandler
import com.ykrc17.adbp.server.handler.MotionEventHandler
import java.io.ObjectInputStream
import java.net.ServerSocket
import java.net.Socket
import java.util.concurrent.Executors

val threadPool = Executors.newCachedThreadPool()

fun main(args: Array<String>) {

    val serverSocket = ServerSocket(8000)
    println("waiting client...")
    while (true) {
        val socket = serverSocket.accept()
        val sin = ObjectInputStream(socket.getInputStream())
        val event = sin.readObject()
        when (event) {
            is ScreenEvent -> handleBitmapEvent(event, socket)
            is ClientMotionEvent -> MotionEventHandler.dispatch(event, socket)
            is InputKeyEvent -> KeyEventHandler.dispatch(event, socket)
            else -> socket.close()
        }
    }
    println("closed")
}


fun handleBitmapEvent(event: ScreenEvent, socket: Socket) {
    if (!ScreenshotThread.isAlive) {
        ScreenshotThread.start()
    }
    threadPool.execute {
        val sout = socket.getOutputStream()
//    val time = System.currentTimeMillis()
        ScreenshotThread.queue.take().compress(Bitmap.CompressFormat.JPEG, 90, sout)
//    println("transfer: " + (System.currentTimeMillis() - time))
        socket.close()
    }
}