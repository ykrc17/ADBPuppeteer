package com.ykrc17.adbp.entity;

public class InputTapEvent extends Event {
    private float x;
    private float y;

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public InputTapEvent(float x, float y) {
        this.x = x;
        this.y = y;
    }
}
