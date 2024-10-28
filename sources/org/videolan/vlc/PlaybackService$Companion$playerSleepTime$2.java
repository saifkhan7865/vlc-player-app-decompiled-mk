package org.videolan.vlc;

import androidx.lifecycle.MutableLiveData;
import java.util.Calendar;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Landroidx/lifecycle/MutableLiveData;", "Ljava/util/Calendar;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: PlaybackService.kt */
final class PlaybackService$Companion$playerSleepTime$2 extends Lambda implements Function0<MutableLiveData<Calendar>> {
    public static final PlaybackService$Companion$playerSleepTime$2 INSTANCE = new PlaybackService$Companion$playerSleepTime$2();

    PlaybackService$Companion$playerSleepTime$2() {
        super(0);
    }

    public final MutableLiveData<Calendar> invoke() {
        MutableLiveData<Calendar> mutableLiveData = new MutableLiveData<>();
        mutableLiveData.setValue(null);
        return mutableLiveData;
    }
}
