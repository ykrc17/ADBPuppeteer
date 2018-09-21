package com.ykrc17.adbp.entity;

public class InputTapEvent extends Event {
    public int x;
    public int y;

    public InputTapEvent(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
