package androidx.car.app.media;

import android.content.Context;
import android.util.Log;
import androidx.car.app.AppManager;
import androidx.car.app.CarContext;
import androidx.car.app.annotations.RequiresCarApi;
import androidx.car.app.media.OpenMicrophoneRequest;
import androidx.car.app.media.OpenMicrophoneResponse;
import androidx.car.app.utils.CommonUtils;
import androidx.car.app.utils.LogTags;
import j$.util.Objects;

@RequiresCarApi(5)
public abstract class CarAudioRecord {
    public static final int AUDIO_CONTENT_BUFFER_SIZE = 512;
    public static final String AUDIO_CONTENT_MIME = "audio/l16";
    public static final int AUDIO_CONTENT_SAMPLING_RATE = 16000;
    private static final int RECORDSTATE_RECORDING = 1;
    private static final int RECORDSTATE_REMOTE_CLOSED = 2;
    private static final int RECORDSTATE_STOPPED = 0;
    private final CarContext mCarContext;
    private OpenMicrophoneResponse mOpenMicrophoneResponse;
    private int mRecordingState = 0;
    private final Object mRecordingStateLock = new Object();

    static /* synthetic */ void lambda$startRecording$1() {
    }

    /* access modifiers changed from: protected */
    public abstract int readInternal(byte[] bArr, int i, int i2);

    /* access modifiers changed from: protected */
    public abstract void startRecordingInternal(OpenMicrophoneResponse openMicrophoneResponse);

    /* access modifiers changed from: protected */
    public abstract void stopRecordingInternal();

    public static CarAudioRecord create(CarContext carContext) {
        String str;
        if (CommonUtils.isAutomotiveOS((Context) Objects.requireNonNull(carContext))) {
            str = "androidx.car.app.media.AutomotiveCarAudioRecord";
        } else {
            str = "androidx.car.app.media.ProjectedCarAudioRecord";
        }
        return createCarAudioRecord(carContext, str);
    }

    private static CarAudioRecord createCarAudioRecord(CarContext carContext, String str) {
        try {
            return (CarAudioRecord) Class.forName(str).getConstructor(new Class[]{CarContext.class}).newInstance(new Object[]{carContext});
        } catch (ReflectiveOperationException unused) {
            throw new IllegalStateException("CarAudioRecord not configured. Did you forget to add a dependency on app-automotive or app-projected artifacts?");
        }
    }

    protected CarAudioRecord(CarContext carContext) {
        this.mCarContext = carContext;
    }

    public void startRecording() {
        synchronized (this.mRecordingStateLock) {
            if (this.mRecordingState == 0) {
                OpenMicrophoneResponse openMicrophone = ((AppManager) this.mCarContext.getCarService(AppManager.class)).openMicrophone(new OpenMicrophoneRequest.Builder(new CarAudioRecord$$ExternalSyntheticLambda0(this)).build());
                this.mOpenMicrophoneResponse = openMicrophone;
                if (openMicrophone == null) {
                    Log.e(LogTags.TAG, "Did not get microphone input from host");
                    this.mOpenMicrophoneResponse = new OpenMicrophoneResponse.Builder(new CarAudioRecord$$ExternalSyntheticLambda1()).build();
                }
                startRecordingInternal(this.mOpenMicrophoneResponse);
                this.mRecordingState = 1;
            } else {
                throw new IllegalStateException("Cannot start recording if it has started and not been stopped");
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$startRecording$0$androidx-car-app-media-CarAudioRecord  reason: not valid java name */
    public /* synthetic */ void m35lambda$startRecording$0$androidxcarappmediaCarAudioRecord() {
        synchronized (this.mRecordingStateLock) {
            this.mRecordingState = 2;
        }
    }

    public void stopRecording() {
        synchronized (this.mRecordingStateLock) {
            OpenMicrophoneResponse openMicrophoneResponse = this.mOpenMicrophoneResponse;
            if (openMicrophoneResponse != null) {
                if (this.mRecordingState != 2) {
                    openMicrophoneResponse.getCarAudioCallback().onStopRecording();
                }
                this.mOpenMicrophoneResponse = null;
            }
            stopRecordingInternal();
            this.mRecordingState = 0;
        }
    }

    public int read(byte[] bArr, int i, int i2) {
        synchronized (this.mRecordingStateLock) {
            int i3 = this.mRecordingState;
            if (i3 == 0) {
                throw new IllegalStateException("Called read before calling startRecording or after calling stopRecording");
            } else if (i3 != 2) {
                return readInternal(bArr, i, i2);
            } else {
                return -1;
            }
        }
    }
}
