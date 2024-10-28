package androidx.car.app.media;

import androidx.car.app.annotations.RequiresCarApi;
import j$.util.Objects;

@RequiresCarApi(5)
public final class OpenMicrophoneRequest {
    private final CarAudioCallbackDelegate mCarAudioCallbackDelegate;

    OpenMicrophoneRequest(Builder builder) {
        this.mCarAudioCallbackDelegate = builder.mCarAudioCallbackDelegate;
    }

    private OpenMicrophoneRequest() {
        this.mCarAudioCallbackDelegate = null;
    }

    public CarAudioCallbackDelegate getCarAudioCallbackDelegate() {
        return (CarAudioCallbackDelegate) Objects.requireNonNull(this.mCarAudioCallbackDelegate);
    }

    public static final class Builder {
        final CarAudioCallbackDelegate mCarAudioCallbackDelegate;

        public Builder(CarAudioCallback carAudioCallback) {
            this.mCarAudioCallbackDelegate = CarAudioCallbackDelegate.create((CarAudioCallback) Objects.requireNonNull(carAudioCallback));
        }

        public OpenMicrophoneRequest build() {
            return new OpenMicrophoneRequest(this);
        }
    }
}
