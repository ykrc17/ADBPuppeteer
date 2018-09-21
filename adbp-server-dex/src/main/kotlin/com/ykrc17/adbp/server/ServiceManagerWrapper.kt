package com.ykrc17.adbp.server

import android.content.Context
import android.hardware.input.IInputManager
import android.os.ServiceManager

object ServiceManagerWrapper {
    fun getInputManager(): IInputManager {
        return IInputManager.Stub.asInterface(ServiceManager.getService(Context.INPUT_SERVICE))
    }
}