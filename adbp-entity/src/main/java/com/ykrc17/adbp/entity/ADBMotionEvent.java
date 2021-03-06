package com.ykrc17.adbp.entity;

public class ADBMotionEvent extends ADBEvent {
    private float x;
    private float y;
    private int action;

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getAction() {
        return action;
    }

    public ADBMotionEvent(float x, float y, int action) {
        this.x = x;
        this.y = y;
        this.action = action;
    }
}
