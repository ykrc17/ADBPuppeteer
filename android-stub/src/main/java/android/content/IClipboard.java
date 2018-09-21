package android.content;

import android.os.IBinder;

public interface IClipboard {

    void setPrimaryClip(ClipData clip, String OpPackageName);

    ClipData getPrimaryClip(String OpPackageName);

    abstract class Stub implements IClipboard {
        public static IClipboard asInterface(IBinder binder) {
            throw new RuntimeException("Stub!");
        }
    }
}
