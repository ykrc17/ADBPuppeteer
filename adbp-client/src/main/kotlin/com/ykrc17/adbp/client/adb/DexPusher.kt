package com.ykrc17.adbp.client.adb

abstract class DexPusher {
    abstract fun pushServerDex(serial: String)

    fun exec(command: String): Process {
        println(command)
        return Runtime.getRuntime().exec(command)
    }

    class Jvm : DexPusher() {
        override fun pushServerDex(serial: String) {
            val command = "adb -s $serial"
            val fileName = "adbp-server.dex"
            val workDir = "/sdcard/"
            val startClass = "com.ykrc17.adbp.server.MainKt"
            exec("$command push $fileName $workDir").waitFor()
            exec("$command forward tcp:8000 tcp:8000").waitFor()
            exec("$command shell app_process -Djava.class.path=$workDir/$fileName $workDir $startClass").apply {
                // 等待socket server
                var line = inputStream.bufferedReader().readLine()
                while (!line.startsWith("waiting")) {
                    line = inputStream.bufferedReader().readLine()
                }
            }
        }

    }

    class Shell : DexPusher() {
        override fun pushServerDex(serial: String) {
            exec("sh adbp-server.sh $serial").apply {
                // 等待socket server
                while (!inputStream.bufferedReader().readLine().startsWith("waiting")) {
                }
            }
        }
    }
}