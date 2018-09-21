package com.ykrc17.adbc

import android.content.Context
import android.content.IClipboard
import android.os.ServiceManager

object ServiceManagerWrapper {
    fun getClipboardManager(): IClipboard {
        return IClipboard.Stub.asInterface(ServiceManager.getService(Context.CLIPBOARD_SERVICE));
    }
}