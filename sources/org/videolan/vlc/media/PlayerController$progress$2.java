package org.videolan.vlc.media;

import androidx.lifecycle.MutableLiveData;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Landroidx/lifecycle/MutableLiveData;", "Lorg/videolan/vlc/media/Progress;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: PlayerController.kt */
final class PlayerController$progress$2 extends Lambda implements Function0<MutableLiveData<Progress>> {
    public static final PlayerController$progress$2 INSTANCE = new PlayerController$progress$2();

    PlayerController$progress$2() {
        super(0);
    }

    public final MutableLiveData<Progress> invoke() {
        MutableLiveData<Progress> mutableLiveData = new MutableLiveData<>();
        mutableLiveData.setValue(new Progress(0, 0, 3, (DefaultConstructorMarker) null));
        return mutableLiveData;
    }
}
