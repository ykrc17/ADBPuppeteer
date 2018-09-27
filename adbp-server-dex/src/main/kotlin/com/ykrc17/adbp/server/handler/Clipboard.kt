package com.ykrc17.adbp.server.handler

import android.content.ClipData
import com.ykrc17.adbp.server.PACKAGE_NAME
import com.ykrc17.adbp.server.ServiceManagerWrapper

object Clipboard {
    fun set(text: String) {
        val clip = ClipData.newPlainText("haha", text)
        ServiceManagerWrapper.clipboardManager.setPrimaryClip(clip, PACKAGE_NAME)
    }

    fun get(): String? {
        val clip = ServiceManagerWrapper.clipboardManager.getPrimaryClip(PACKAGE_NAME)
        if (clip.itemCount > 0) {
            return clip.getItemAt(0).text.toString()
        }
        return null
    }
}