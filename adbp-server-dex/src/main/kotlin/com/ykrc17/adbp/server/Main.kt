package com.ykrc17.adbp.server

import android.graphics.Bitmap
import com.ykrc17.adbp.entity.InputKeyEvent
import com.ykrc17.adbp.entity.InputTapEvent
import com.ykrc17.adbp.entity.ScreenEvent
import com.ykrc17.adbp.server.handler.KeyEventHandler
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
            is InputTapEvent -> handleTapEvent(event, socket)
            is InputKeyEvent -> KeyEventHandler.handle(event, socket)
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

fun handleTapEvent(event: InputTapEvent, socket: Socket) {
    threadPool.execute {
        Runtime.getRuntime().exec("input tap ${event.x} ${event.y}")
        socket.close()
    }
}