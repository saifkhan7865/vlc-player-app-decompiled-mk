package org.videolan.vlc.gui.helpers;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u001a\u000e\u0010\u0000\u001a\u00020\u0001H@¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"restartMediaPlayer", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "vlc-android_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: PreferenceUtils.kt */
public final class PreferenceUtilsKt {
    public static final Object restartMediaPlayer(Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getMain(), new PreferenceUtilsKt$restartMediaPlayer$2((Continuation<? super PreferenceUtilsKt$restartMediaPlayer$2>) null), continuation);
        return withContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }
}
