package com.ykrc17.adbp.entity;

public class ClipBoardPushEvent extends ADBEvent {
    private String text;

    public String getText() {
        return text;
    }

    public ClipBoardPushEvent(String text) {
        this.text = text;
    }
}
