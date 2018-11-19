package android.view;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Rect;

public class SurfaceControl {
    /**
     * Like {@link SurfaceControl#screenshot(int, int, int, int, boolean)} but
     * includes all Surfaces in the screenshot.
     *
     * @param width  The desired width of the returned bitmap; the raw
     *               screen will be scaled down to this size.
     * @param height The desired height of the returned bitmap; the raw
     *               screen will be scaled down to this size.
     * @return Returns a Bitmap containing the screen contents, or null
     * if an error occurs. Make sure to call Bitmap.recycle() as soon as
     * possible, once its content is not needed anymore.
     */
    public static Bitmap screenshot(int width, int height) {
        throw new RuntimeException("Stub!");
    }

    /**
     * Like {@link SurfaceControl#screenshot(Rect, int, int, int, int, boolean, int)} but
     * includes all Surfaces in the screenshot. This will also update the orientation so it
     * sends the correct coordinates to SF based on the rotation value.
     *
     * @param sourceCrop The portion of the screen to capture into the Bitmap;
     *                   caller may pass in 'new Rect()' if no cropping is desired.
     * @param width      The desired width of the returned bitmap; the raw
     *                   screen will be scaled down to this size.
     * @param height     The desired height of the returned bitmap; the raw
     *                   screen will be scaled down to this size.
     * @param rotation   Apply a custom clockwise rotation to the screenshot, i.e.
     *                   Surface.ROTATION_0,90,180,270. Surfaceflinger will always take
     *                   screenshots in its native portrait orientation by default, so this is
     *                   useful for returning screenshots that are independent of device
     *                   orientation.
     * @return Returns a Bitmap containing the screen contents, or null
     * if an error occurs. Make sure to call Bitmap.recycle() as soon as
     * possible, once its content is not needed anymore.
     */
    @TargetApi(28)
    public static Bitmap screenshot(Rect sourceCrop, int width, int height, int rotation) {
        throw new RuntimeException("Stub!");
    }
}
