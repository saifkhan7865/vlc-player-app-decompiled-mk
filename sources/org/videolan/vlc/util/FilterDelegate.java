package org.videolan.vlc.util;

import androidx.lifecycle.MutableLiveData;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;
import org.videolan.medialibrary.media.MediaLibraryItem;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0004\b\u0016\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B\u001b\u0012\u0014\u0010\u0004\u001a\u0010\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u00028\u00000\u00060\u0005¢\u0006\u0002\u0010\u0007J\u0018\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH@¢\u0006\u0002\u0010\u000fJ \u0010\u0010\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00112\b\u0010\r\u001a\u0004\u0018\u00010\u000eH@¢\u0006\u0002\u0010\u000fJ\u0010\u0010\u0012\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0006H\u0004J\u0018\u0010\u0013\u001a\u00020\f2\u000e\u0010\u0014\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0011H\u0002R\"\u0010\u0004\u001a\u0010\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u00028\u00000\u00060\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0016\u0010\n\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lorg/videolan/vlc/util/FilterDelegate;", "T", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "", "dataset", "Landroidx/lifecycle/MutableLiveData;", "", "(Landroidx/lifecycle/MutableLiveData;)V", "getDataset", "()Landroidx/lifecycle/MutableLiveData;", "sourceSet", "filter", "", "charSequence", "", "(Ljava/lang/CharSequence;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "filteringJob", "", "initSource", "publish", "list", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: FilterDelegate.kt */
public class FilterDelegate<T extends MediaLibraryItem> {
    private final MutableLiveData<? extends List<T>> dataset;
    private List<? extends T> sourceSet;

    /* access modifiers changed from: protected */
    public Object filteringJob(CharSequence charSequence, Continuation<? super List<T>> continuation) {
        return filteringJob$suspendImpl(this, charSequence, continuation);
    }

    public FilterDelegate(MutableLiveData<? extends List<? extends T>> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "dataset");
        this.dataset = mutableLiveData;
    }

    /* access modifiers changed from: protected */
    public final MutableLiveData<? extends List<T>> getDataset() {
        return this.dataset;
    }

    /* access modifiers changed from: protected */
    public final List<T> initSource() {
        if (this.sourceSet == null) {
            this.sourceSet = (List) this.dataset.getValue();
        }
        return this.sourceSet;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object filter(java.lang.CharSequence r5, kotlin.coroutines.Continuation<? super kotlin.Unit> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof org.videolan.vlc.util.FilterDelegate$filter$1
            if (r0 == 0) goto L_0x0014
            r0 = r6
            org.videolan.vlc.util.FilterDelegate$filter$1 r0 = (org.videolan.vlc.util.FilterDelegate$filter$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L_0x0019
        L_0x0014:
            org.videolan.vlc.util.FilterDelegate$filter$1 r0 = new org.videolan.vlc.util.FilterDelegate$filter$1
            r0.<init>(r4, r6)
        L_0x0019:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0036
            if (r2 != r3) goto L_0x002e
            java.lang.Object r5 = r0.L$0
            org.videolan.vlc.util.FilterDelegate r5 = (org.videolan.vlc.util.FilterDelegate) r5
            kotlin.ResultKt.throwOnFailure(r6)
            goto L_0x0045
        L_0x002e:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x0036:
            kotlin.ResultKt.throwOnFailure(r6)
            r0.L$0 = r4
            r0.label = r3
            java.lang.Object r6 = r4.filteringJob(r5, r0)
            if (r6 != r1) goto L_0x0044
            return r1
        L_0x0044:
            r5 = r4
        L_0x0045:
            java.util.List r6 = (java.util.List) r6
            r5.publish(r6)
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.util.FilterDelegate.filter(java.lang.CharSequence, kotlin.coroutines.Continuation):java.lang.Object");
    }

    static /* synthetic */ <T extends MediaLibraryItem> Object filteringJob$suspendImpl(FilterDelegate<T> filterDelegate, CharSequence charSequence, Continuation<? super List<T>> continuation) {
        List<T> initSource;
        if (charSequence == null || (initSource = filterDelegate.initSource()) == null) {
            return null;
        }
        return BuildersKt.withContext(Dispatchers.getDefault(), new FilterDelegate$filteringJob$2$1(charSequence, initSource, (Continuation<? super FilterDelegate$filteringJob$2$1>) null), continuation);
    }

    private final void publish(List<T> list) {
        List<? extends T> list2 = this.sourceSet;
        if (list2 == null) {
            return;
        }
        if (list != null) {
            this.dataset.setValue(list);
            return;
        }
        this.dataset.setValue(list2);
        this.sourceSet = null;
    }
}
