package com.ykrc17.adbc

import android.content.ClipData
import android.content.IClipboard

const val PACKAGE_NAME = "com.android.shell"

fun main(args: Array<String>) {
    val clipboard = ServiceManagerWrapper.getClipboardManager()
    if (args.isEmpty()) {
        readClipboard(clipboard)
    } else {
        writeClipboard(clipboard, args[0])
    }
}

fun writeClipboard(clipboard: IClipboard, text: String) {
    val clip = ClipData.newPlainText("haha", text)
    clipboard.setPrimaryClip(clip, PACKAGE_NAME)
}

fun readClipboard(clipboard: IClipboard) {
    val clip = clipboard.getPrimaryClip(PACKAGE_NAME)
    for (i in 0 until clip.itemCount) {
        println(clip.getItemAt(i).text)
    }
}
