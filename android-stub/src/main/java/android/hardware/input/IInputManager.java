package android.hardware.input;

import android.os.IBinder;
import android.view.InputEvent;

public interface IInputManager {

    boolean injectInputEvent(InputEvent event, int mode);

    abstract class Stub implements IInputManager {
        public static IInputManager asInterface(IBinder binder) {
            throw new RuntimeException("Stub!");
        }
    }
}
