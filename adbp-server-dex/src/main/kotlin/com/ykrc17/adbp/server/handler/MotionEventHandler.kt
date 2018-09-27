package com.ykrc17.adbp.server.handler

import android.os.SystemClock
import android.view.InputDevice
import android.view.MotionEvent
import com.ykrc17.adbp.entity.ADBMotionEvent
import com.ykrc17.adbp.server.ServiceManagerWrapper
import kotlin.math.roundToInt

object MotionEventHandler : EventHandler<ADBMotionEvent>() {
    private var startTime = 0L

    override fun handle(event: ADBMotionEvent) {
        tap(event)
    }

    private fun tap(event: ADBMotionEvent) {
        if (event.action == MotionEvent.ACTION_DOWN) {
            startTime = SystemClock.uptimeMillis()
        }
        val time = SystemClock.uptimeMillis()
        val motionEvent = convertTapEvent(startTime, time, event.action, event.x, event.y)
        ServiceManagerWrapper.inputManager.injectInputEvent(motionEvent, 0)
    }

    private fun convertTapEvent(downTime: Long, eventTime: Long, action: Int, x: Float, y: Float): MotionEvent {
        val props = MotionEvent.PointerProperties()
        props.id = 0
        props.toolType = MotionEvent.TOOL_TYPE_FINGER

        val coords = MotionEvent.PointerCoords()
        coords.orientation = 0f
        coords.pressure = 1f
        coords.size = 1f
        coords.x = x
        coords.y = y

        return MotionEvent.obtain(downTime, eventTime, action, 1, arrayOf(props), arrayOf(coords), 0, 0, 1f, 1f, 0, 0, InputDevice.SOURCE_TOUCHSCREEN, 0)
    }

    private fun tapUsingShell(event: ADBMotionEvent) {
        Runtime.getRuntime().exec("input tap ${event.x.roundToInt()} ${event.y.roundToInt()}")
    }
}