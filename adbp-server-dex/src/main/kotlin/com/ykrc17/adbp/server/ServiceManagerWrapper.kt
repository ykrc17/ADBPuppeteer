package com.ykrc17.adbp.server

import android.content.Context
import android.content.IClipboard
import android.hardware.input.IInputManager
import android.os.ServiceManager

object ServiceManagerWrapper {
    val inputManager by lazy {
        IInputManager.Stub.asInterface(ServiceManager.getService(Context.INPUT_SERVICE))
    }

    val clipboardManager by lazy {
        IClipboard.Stub.asInterface(ServiceManager.getService(Context.CLIPBOARD_SERVICE));
    }
}