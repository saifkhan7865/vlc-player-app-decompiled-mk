package androidx.car.app.media;

import android.os.ParcelFileDescriptor;
import androidx.car.app.annotations.RequiresCarApi;
import androidx.core.animation.AnimatorKt$$ExternalSyntheticApiModelOutline0;
import j$.util.Objects;
import java.io.IOException;
import java.io.InputStream;

@RequiresCarApi(5)
public final class OpenMicrophoneResponse {
    private final CarAudioCallbackDelegate mCarAudioCallbackDelegate;
    private final ParcelFileDescriptor mCarMicrophoneDescriptor;

    OpenMicrophoneResponse(Builder builder) {
        this.mCarAudioCallbackDelegate = builder.mCarAudioCallbackDelegate;
        this.mCarMicrophoneDescriptor = builder.mCarMicrophoneDescriptor;
    }

    private OpenMicrophoneResponse() {
        this.mCarMicrophoneDescriptor = null;
        this.mCarAudioCallbackDelegate = null;
    }

    public CarAudioCallbackDelegate getCarAudioCallback() {
        return (CarAudioCallbackDelegate) Objects.requireNonNull(this.mCarAudioCallbackDelegate);
    }

    public InputStream getCarMicrophoneInputStream() {
        ParcelFileDescriptor parcelFileDescriptor = this.mCarMicrophoneDescriptor;
        if (parcelFileDescriptor == null) {
            try {
                ParcelFileDescriptor[] m = AnimatorKt$$ExternalSyntheticApiModelOutline0.m();
                m[1].close();
                parcelFileDescriptor = m[0];
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
        return new ParcelFileDescriptor.AutoCloseInputStream(parcelFileDescriptor);
    }

    public static final class Builder {
        final CarAudioCallbackDelegate mCarAudioCallbackDelegate;
        ParcelFileDescriptor mCarMicrophoneDescriptor;

        public Builder(CarAudioCallback carAudioCallback) {
            this.mCarAudioCallbackDelegate = CarAudioCallbackDelegate.create((CarAudioCallback) Objects.requireNonNull(carAudioCallback));
        }

        public Builder setCarMicrophoneDescriptor(ParcelFileDescriptor parcelFileDescriptor) {
            this.mCarMicrophoneDescriptor = (ParcelFileDescriptor) Objects.requireNonNull(parcelFileDescriptor);
            return this;
        }

        public OpenMicrophoneResponse build() {
            return new OpenMicrophoneResponse(this);
        }
    }
}
