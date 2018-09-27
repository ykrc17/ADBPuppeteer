package com.ykrc17.adbp.entity;

public class ClipBoardPullEvent extends ADBEvent {
    public static class Callback extends ClipBoardPushEvent {

        public Callback(String text) {
            super(text);
        }
    }
}
