package com.ykrc17.adbp.entity;

public class InputKeyEvent extends Event {
    private int keyCode;
    private int metaState;

    public int getKeyCode() {
        return keyCode;
    }

    public int getMetaState() {
        return metaState;
    }

    public InputKeyEvent(int keyCode, int metaState) {
        this.keyCode = keyCode;
        this.metaState = metaState;
    }
}
