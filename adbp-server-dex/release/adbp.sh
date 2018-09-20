#!/usr/bin/env bash
cd `dirname "$0"`

FILE_NAME=adbp.dex
WORK_DIR=/sdcard/
START_CLASS=com.ykrc17.adbp.server.MainKt

adb forward tcp:8000 tcp:8000

echo app_process -Djava.class.path=$WORK_DIR/$FILE_NAME $WORK_DIR $START_CLASS
adb push $FILE_NAME $WORK_DIR
adb shell app_process -Djava.class.path=$WORK_DIR/$FILE_NAME $WORK_DIR $START_CLASS