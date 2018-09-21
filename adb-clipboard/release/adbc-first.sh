#!/usr/bin/env bash
cd `dirname "$0"`

FILE_NAME=adb-clipboard.dex
WORK_DIR=/sdcard/
START_CLASS=com.ykrc17.adbc.ADBClipboardKt

adb push $FILE_NAME $WORK_DIR
adb shell app_process -Djava.class.path=$WORK_DIR/$FILE_NAME $WORK_DIR $START_CLASS $@