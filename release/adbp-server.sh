#!/usr/bin/env bash
cd `dirname "$0"`

# 参数serial number
serial_param=""
if [ -n "$1" ]; then
    serial_param="-s $1"
fi

FILE_NAME=adbp-server.dex
WORK_DIR=/sdcard/
START_CLASS=com.ykrc17.adbp.server.MainKt

adb $serial_param push $FILE_NAME $WORK_DIR
adb $serial_param forward tcp:8000 tcp:8000
adb $serial_param shell app_process -Djava.class.path=$WORK_DIR/$FILE_NAME $WORK_DIR $START_CLASS