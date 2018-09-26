package com.ykrc17.adbp.entity;

public class ADBKeyEvent extends Event {
    private int keyCode;
    private int metaState;

    public int getKeyCode() {
        return keyCode;
    }

    public int getMetaState() {
        return metaState;
    }

    public ADBKeyEvent(int keyCode, int metaState) {
        this.keyCode = keyCode;
        this.metaState = metaState;
    }
}
