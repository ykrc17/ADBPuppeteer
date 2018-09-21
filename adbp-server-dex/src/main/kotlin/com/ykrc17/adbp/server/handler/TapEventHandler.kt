package com.ykrc17.adbp.server.handler

import android.os.SystemClock
import android.view.InputDevice
import android.view.MotionEvent
import com.ykrc17.adbp.entity.InputTapEvent
import com.ykrc17.adbp.server.ServiceManagerWrapper
import java.io.OutputStream
import kotlin.math.roundToInt

object TapEventHandler : EventHandler<InputTapEvent>() {
    override fun handle(event: InputTapEvent, out: OutputStream) {
        tap(event)
    }

    private fun tap(event: InputTapEvent) {
        val time = SystemClock.uptimeMillis()
        val downEvent = convertTapEvent(time, time, MotionEvent.ACTION_DOWN, event.x, event.y)
        ServiceManagerWrapper.inputManager.injectInputEvent(downEvent, 0)
        val upEvent = convertTapEvent(time, time, MotionEvent.ACTION_UP, event.x, event.y)
        ServiceManagerWrapper.inputManager.injectInputEvent(upEvent, 0)
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

    private fun tapUsingShell(event: InputTapEvent) {
        Runtime.getRuntime().exec("input tap ${event.x.roundToInt()} ${event.y.roundToInt()}")
    }
}