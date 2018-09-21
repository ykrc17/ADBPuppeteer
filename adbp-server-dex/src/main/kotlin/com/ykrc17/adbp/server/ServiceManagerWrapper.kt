package com.ykrc17.adbp.server

import android.content.Context
import android.hardware.input.IInputManager
import android.os.ServiceManager

object ServiceManagerWrapper {
    val inputManager by lazy {
        IInputManager.Stub.asInterface(ServiceManager.getService(Context.INPUT_SERVICE))
    }
}