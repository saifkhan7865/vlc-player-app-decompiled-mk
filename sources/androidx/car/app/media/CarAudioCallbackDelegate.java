package androidx.car.app.media;

import android.os.RemoteException;
import androidx.car.app.annotations.RequiresCarApi;
import androidx.car.app.media.ICarAudioCallback;
import j$.util.Objects;

@RequiresCarApi(5)
public class CarAudioCallbackDelegate {
    private final ICarAudioCallback mCallback;

    public void onStopRecording() {
        try {
            ((ICarAudioCallback) Objects.requireNonNull(this.mCallback)).onStopRecording();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    static CarAudioCallbackDelegate create(CarAudioCallback carAudioCallback) {
        return new CarAudioCallbackDelegate(carAudioCallback);
    }

    private CarAudioCallbackDelegate(CarAudioCallback carAudioCallback) {
        this.mCallback = new CarAudioCallbackStub(carAudioCallback);
    }

    private CarAudioCallbackDelegate() {
        this.mCallback = null;
    }

    private static class CarAudioCallbackStub extends ICarAudioCallback.Stub {
        private final CarAudioCallback mCarAudioCallback;

        CarAudioCallbackStub(CarAudioCallback carAudioCallback) {
            this.mCarAudioCallback = carAudioCallback;
        }

        CarAudioCallbackStub() {
            this.mCarAudioCallback = null;
        }

        public void onStopRecording() {
            ((CarAudioCallback) Objects.requireNonNull(this.mCarAudioCallback)).onStopRecording();
        }
    }
}
