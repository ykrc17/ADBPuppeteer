package com.ykrc17.adbp.entity;

public class TapEvent extends Event {
    public int x;
    public int y;

    public TapEvent(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
