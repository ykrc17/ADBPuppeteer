package com.ykrc17.adbp.server

import android.graphics.Bitmap
import com.ykrc17.adbp.entity.ADBKeyEvent
import com.ykrc17.adbp.entity.ADBMotionEvent
import com.ykrc17.adbp.entity.ScreenEvent
import com.ykrc17.adbp.server.handler.KeyEventHandler
import com.ykrc17.adbp.server.handler.MotionEventHandler
import java.io.ObjectInputStream
import java.net.ServerSocket
import java.net.Socket
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicLong

val threadPool = Executors.newCachedThreadPool()
val lastEventTime = AtomicLong()
const val TIMEOUT_THRESHOLD = 10000

fun main(args: Array<String>) {

    val serverSocket = ServerSocket(8000)
    println("waiting client...")
    lastEventTime.set(System.currentTimeMillis())
    val timer = Timer()
    timer.schedule(TimeOutTask, 0, 1000)
    while (true) {
        val socket = serverSocket.accept()
        lastEventTime.set(System.currentTimeMillis())
        val sin = ObjectInputStream(socket.getInputStream())
        val event = sin.readObject()
        when (event) {
            is ScreenEvent -> handleBitmapEvent(event, socket)
            is ADBMotionEvent -> MotionEventHandler.dispatch(event, socket)
            is ADBKeyEvent -> KeyEventHandler.dispatch(event, socket)
            else -> socket.close()
        }
    }
}

object TimeOutTask : TimerTask() {
    override fun run() {
        if ((System.currentTimeMillis() - lastEventTime.get()) > TIMEOUT_THRESHOLD) {
            println("no active client, kill server")
            System.exit(0)
        }
    }
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