package androidx.car.app.media;

import androidx.car.app.annotations.RequiresCarApi;

@RequiresCarApi(5)
public interface CarAudioCallback {
    void onStopRecording();
}
