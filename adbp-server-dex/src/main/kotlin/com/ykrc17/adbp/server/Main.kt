package com.ykrc17.adbp.server

import android.graphics.Bitmap
import android.util.Log
import com.ykrc17.adbp.entity.ADBEvent
import com.ykrc17.adbp.entity.ClipBoardPullEvent
import com.ykrc17.adbp.entity.ScreenEvent
import com.ykrc17.adbp.server.handler.Clipboard
import com.ykrc17.adbp.server.net.ControlConnection
import com.ykrc17.adbp.server.screenshot.ScreenshotThread
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.net.ServerSocket
import java.net.Socket
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicLong

const val PACKAGE_NAME = "com.android.shell"
const val TAG = "ADBP"
const val TIMEOUT_THRESHOLD = 5000

val threadPool = Executors.newCachedThreadPool()
val lastEventTime = AtomicLong()

fun main(args: Array<String>) {
    val oldUEH = Thread.getDefaultUncaughtExceptionHandler()
    Thread.setDefaultUncaughtExceptionHandler { t, e ->
        e.printStackTrace()
        oldUEH.uncaughtException(t, e)
    }

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
        if (event is ADBEvent) {
            when (event) {
                is ScreenEvent -> handleScreenEvent(socket)
                is ClipBoardPullEvent -> handleClipboardPullEvent(socket)
                else -> ControlConnection(socket).start(event, sin)
            }
        } else {
            socket.close()
        }
    }
}

object TimeOutTask : TimerTask() {

    override fun run() {
        if ((System.currentTimeMillis() - lastEventTime.get()) > TIMEOUT_THRESHOLD) {
            logd("no active client, kill server")
            System.exit(0)
        }
    }
}

fun handleScreenEvent(socket: Socket) {
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

fun handleClipboardPullEvent(socket: Socket) {
    threadPool.execute {
        Clipboard.get()?.also { text ->
            ObjectOutputStream(socket.getOutputStream()).apply {
                writeObject(ClipBoardPullEvent.Callback(text))
                flush()
                close()
            }
        }
        socket.close()
    }
}

fun logd(msg: String) {
    Log.d(TAG, msg)
}