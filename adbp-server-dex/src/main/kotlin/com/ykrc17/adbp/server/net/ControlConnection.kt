package com.ykrc17.adbp.server.net

import android.util.Log
import com.ykrc17.adbp.entity.ADBEvent
import com.ykrc17.adbp.entity.ADBKeyEvent
import com.ykrc17.adbp.entity.ADBMotionEvent
import com.ykrc17.adbp.server.TAG
import com.ykrc17.adbp.server.handler.KeyEventHandler
import com.ykrc17.adbp.server.handler.MotionEventHandler
import com.ykrc17.adbp.server.logd
import com.ykrc17.adbp.server.threadPool
import java.io.ObjectInputStream
import java.net.Socket

class ControlConnection(val socket: Socket) {

    fun start() {
        threadPool.execute {
            innerStart(ObjectInputStream(socket.getInputStream()))
        }
    }

    fun start(event: ADBEvent, ois: ObjectInputStream) {
        threadPool.execute {
            handleEvent(event)
            innerStart(ois)
        }
    }

    private fun innerStart(ois: ObjectInputStream) {
        try {
            while (true) {
                if (!socket.isClosed) {
                    val event = ois.readObject()
                    if (event is ADBEvent) {
                        handleEvent(event)
                        continue
                    }
                }
                break
            }
        } catch (e: Exception) {
            Log.e(TAG, "", e)
        } finally {
            logd("ControlConnection finished, kill server")
            socket.close()
            System.exit(0)
        }
    }

    private fun handleEvent(event: ADBEvent) {
        when (event) {
            is ADBKeyEvent -> KeyEventHandler.handle(event)
            is ADBMotionEvent -> MotionEventHandler.handle(event)
        }
    }
}
