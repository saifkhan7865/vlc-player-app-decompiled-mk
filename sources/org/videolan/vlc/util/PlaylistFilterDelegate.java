package org.videolan.vlc.util;

import androidx.lifecycle.MutableLiveData;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010!\n\u0000\n\u0002\u0010\r\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001b\u0012\u0014\u0010\u0003\u001a\u0010\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u00020\u00020\u00050\u0004¢\u0006\u0002\u0010\u0006J \u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH@¢\u0006\u0002\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/videolan/vlc/util/PlaylistFilterDelegate;", "Lorg/videolan/vlc/util/FilterDelegate;", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "dataset", "Landroidx/lifecycle/MutableLiveData;", "", "(Landroidx/lifecycle/MutableLiveData;)V", "filteringJob", "", "charSequence", "", "(Ljava/lang/CharSequence;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: FilterDelegate.kt */
public final class PlaylistFilterDelegate extends FilterDelegate<MediaWrapper> {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PlaylistFilterDelegate(MutableLiveData<? extends List<? extends MediaWrapper>> mutableLiveData) {
        super(mutableLiveData);
        Intrinsics.checkNotNullParameter(mutableLiveData, "dataset");
    }

    /* access modifiers changed from: protected */
    public Object filteringJob(CharSequence charSequence, Continuation<? super List<MediaWrapper>> continuation) {
        List initSource;
        if (charSequence == null || (initSource = initSource()) == null) {
            return null;
        }
        return BuildersKt.withContext(Dispatchers.getDefault(), new PlaylistFilterDelegate$filteringJob$2$1(charSequence, initSource, (Continuation<? super PlaylistFilterDelegate$filteringJob$2$1>) null), continuation);
    }
}
