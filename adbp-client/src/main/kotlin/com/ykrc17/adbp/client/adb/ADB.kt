package com.ykrc17.adbp.client.adb

import com.ykrc17.adbp.client.entity.DeviceEntity
import java.util.regex.Pattern

object ADB {
    fun getDevices(): ArrayList<DeviceEntity> {
        val process = Runtime.getRuntime().exec("adb devices -l")
        val pattern = Pattern.compile("(\\S*).*?model:(\\S*).*?")
        val devices = arrayListOf<DeviceEntity>()
        process.inputStream.bufferedReader().lines().forEach {
            pattern.matcher(it).apply {
                if (matches()) {
                    devices.add(DeviceEntity(group(2), group(1)))
                }
            }
        }
        process.waitFor()
        return devices
    }

    fun pushServerDex(serial: String) {
        println(">> pushing server dex")
        val startTime = System.currentTimeMillis()
        Runtime.getRuntime().exec("sh adbp-server-push.sh $serial").apply {
            inputStream.bufferedReader().forEachLine { println(it) }
            waitFor()
        }
        println(">> push success: ${System.currentTimeMillis() - startTime}ms")
        Runtime.getRuntime().exec("sh adbp-server.sh $serial").apply {
            // 等待socket server
            while (!inputStream.bufferedReader().readLine().startsWith("waiting")) {
            }
        }
    }
}