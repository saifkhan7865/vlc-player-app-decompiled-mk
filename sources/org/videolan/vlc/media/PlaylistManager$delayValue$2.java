package org.videolan.vlc.media;

import androidx.lifecycle.MutableLiveData;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Landroidx/lifecycle/MutableLiveData;", "Lorg/videolan/vlc/media/DelayValues;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: PlaylistManager.kt */
final class PlaylistManager$delayValue$2 extends Lambda implements Function0<MutableLiveData<DelayValues>> {
    public static final PlaylistManager$delayValue$2 INSTANCE = new PlaylistManager$delayValue$2();

    PlaylistManager$delayValue$2() {
        super(0);
    }

    public final MutableLiveData<DelayValues> invoke() {
        MutableLiveData<DelayValues> mutableLiveData = new MutableLiveData<>();
        mutableLiveData.setValue(new DelayValues(0, 0, 3, (DefaultConstructorMarker) null));
        return mutableLiveData;
    }
}
