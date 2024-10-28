package org.videolan.vlc.viewmodels;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.channels.SendChannel;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.tools.CoroutineContextProvider;
import org.videolan.tools.livedata.LiveDataset;
import org.videolan.vlc.util.FilterDelegate;

@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010!\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u000e\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0016\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00028\u0000H@¢\u0006\u0002\u00100J\u001c\u0010-\u001a\u00020.2\f\u00101\u001a\b\u0012\u0004\u0012\u00028\u000002H@¢\u0006\u0002\u00103J\u0012\u0010\u0018\u001a\u00020.2\b\u00104\u001a\u0004\u0018\u00010\fH\u0016J\u0006\u00105\u001a\u00020\u001fJ\b\u00106\u001a\u00020.H\u0014J\b\u00107\u001a\u00020.H\u0016J\u0013\u00108\u001a\u00020.2\u0006\u00109\u001a\u00028\u0000¢\u0006\u0002\u0010:J\u0015\u0010;\u001a\u00020.2\u0006\u0010/\u001a\u00028\u0000H\u0014¢\u0006\u0002\u0010:J\b\u0010<\u001a\u00020.H\u0016J\u001c\u0010=\u001a\u00020.2\f\u00101\u001a\b\u0012\u0004\u0012\u00028\u000002H@¢\u0006\u0002\u00103J\u000e\u0010>\u001a\u00020.H@¢\u0006\u0002\u0010?R3\u0010\t\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\r0\u000b0\n8FX\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u0015¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R!\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028\u00000\u00198BX\u0002¢\u0006\f\n\u0004\b\u001c\u0010\u0011\u001a\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001eX\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R'\u0010\"\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\r0\n8FX\u0002¢\u0006\f\n\u0004\b$\u0010\u0011\u001a\u0004\b#\u0010\u000fR'\u0010%\u001a\b\u0012\u0004\u0012\u00020'0&8DX\u0002¢\u0006\u0012\n\u0004\b,\u0010\u0011\u0012\u0004\b(\u0010)\u001a\u0004\b*\u0010+¨\u0006@"}, d2 = {"Lorg/videolan/vlc/viewmodels/BaseModel;", "T", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "Lorg/videolan/vlc/viewmodels/SortableModel;", "context", "Landroid/content/Context;", "coroutineContextProvider", "Lorg/videolan/tools/CoroutineContextProvider;", "(Landroid/content/Context;Lorg/videolan/tools/CoroutineContextProvider;)V", "categories", "Landroidx/lifecycle/LiveData;", "", "", "", "getCategories", "()Landroidx/lifecycle/LiveData;", "categories$delegate", "Lkotlin/Lazy;", "getCoroutineContextProvider", "()Lorg/videolan/tools/CoroutineContextProvider;", "dataset", "Lorg/videolan/tools/livedata/LiveDataset;", "getDataset", "()Lorg/videolan/tools/livedata/LiveDataset;", "filter", "Lorg/videolan/vlc/util/FilterDelegate;", "getFilter", "()Lorg/videolan/vlc/util/FilterDelegate;", "filter$delegate", "loading", "Landroidx/lifecycle/MutableLiveData;", "", "getLoading", "()Landroidx/lifecycle/MutableLiveData;", "sections", "getSections", "sections$delegate", "updateActor", "Lkotlinx/coroutines/channels/SendChannel;", "Lorg/videolan/vlc/viewmodels/Update;", "getUpdateActor$annotations", "()V", "getUpdateActor", "()Lkotlinx/coroutines/channels/SendChannel;", "updateActor$delegate", "addMedia", "", "media", "(Lorg/videolan/medialibrary/media/MediaLibraryItem;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "mediaList", "", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "query", "isEmpty", "onCleared", "refresh", "remove", "mw", "(Lorg/videolan/medialibrary/media/MediaLibraryItem;)V", "removeMedia", "restore", "updateItems", "updateList", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: BaseModel.kt */
public abstract class BaseModel<T extends MediaLibraryItem> extends SortableModel {
    private final Lazy categories$delegate;
    private final CoroutineContextProvider coroutineContextProvider;
    private final LiveDataset<T> dataset = new LiveDataset<>();
    private final Lazy filter$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, new BaseModel$filter$2(this));
    private final MutableLiveData<Boolean> loading;
    private final Lazy sections$delegate;
    private final Lazy updateActor$delegate;

    protected static /* synthetic */ void getUpdateActor$annotations() {
    }

    public Object addMedia(List<? extends T> list, Continuation<? super Unit> continuation) {
        return this.dataset.add(list);
    }

    /* access modifiers changed from: protected */
    public Object addMedia(T t, Continuation<? super Unit> continuation) {
        return this.dataset.add(t);
    }

    /* access modifiers changed from: protected */
    public Object updateItems(List<? extends T> list, Continuation<? super Unit> continuation) {
        return updateItems$suspendImpl(this, list, continuation);
    }

    /* access modifiers changed from: protected */
    public Object updateList(Continuation<? super Unit> continuation) {
        return Unit.INSTANCE;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BaseModel(Context context, CoroutineContextProvider coroutineContextProvider2) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(coroutineContextProvider2, "coroutineContextProvider");
        this.coroutineContextProvider = coroutineContextProvider2;
        MutableLiveData<Boolean> mutableLiveData = new MutableLiveData<>();
        mutableLiveData.setValue(false);
        this.loading = mutableLiveData;
        this.categories$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, new BaseModel$categories$2(this));
        this.sections$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, new BaseModel$sections$2(this));
        this.updateActor$delegate = LazyKt.lazy(new BaseModel$updateActor$2(this));
    }

    public final CoroutineContextProvider getCoroutineContextProvider() {
        return this.coroutineContextProvider;
    }

    /* access modifiers changed from: private */
    public final FilterDelegate<T> getFilter() {
        return (FilterDelegate) this.filter$delegate.getValue();
    }

    public final LiveDataset<T> getDataset() {
        return this.dataset;
    }

    public MutableLiveData<Boolean> getLoading() {
        return this.loading;
    }

    public final LiveData<Map<String, List<MediaLibraryItem>>> getCategories() {
        return (LiveData) this.categories$delegate.getValue();
    }

    public final LiveData<List<MediaLibraryItem>> getSections() {
        return (LiveData) this.sections$delegate.getValue();
    }

    /* access modifiers changed from: protected */
    public final SendChannel<Update> getUpdateActor() {
        return (SendChannel) this.updateActor$delegate.getValue();
    }

    public final boolean isEmpty() {
        return this.dataset.isEmpty();
    }

    public void refresh() {
        getUpdateActor().m1139trySendJP2dKIU(Refresh.INSTANCE);
    }

    public final void remove(T t) {
        Intrinsics.checkNotNullParameter(t, "mw");
        getUpdateActor().m1139trySendJP2dKIU(new Remove(t));
    }

    public void filter(String str) {
        if (!getUpdateActor().isClosedForSend()) {
            setFilterQuery(str);
            getUpdateActor().m1139trySendJP2dKIU(new Filter(str));
        }
    }

    public void restore() {
        if (getFilterQuery() != null) {
            filter((String) null);
        }
    }

    /* access modifiers changed from: protected */
    public void removeMedia(T t) {
        Intrinsics.checkNotNullParameter(t, "media");
        this.dataset.remove(t);
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ <T extends org.videolan.medialibrary.media.MediaLibraryItem> java.lang.Object updateItems$suspendImpl(org.videolan.vlc.viewmodels.BaseModel<T> r7, java.util.List<? extends T> r8, kotlin.coroutines.Continuation<? super kotlin.Unit> r9) {
        /*
            boolean r0 = r9 instanceof org.videolan.vlc.viewmodels.BaseModel$updateItems$1
            if (r0 == 0) goto L_0x0014
            r0 = r9
            org.videolan.vlc.viewmodels.BaseModel$updateItems$1 r0 = (org.videolan.vlc.viewmodels.BaseModel$updateItems$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L_0x0019
        L_0x0014:
            org.videolan.vlc.viewmodels.BaseModel$updateItems$1 r0 = new org.videolan.vlc.viewmodels.BaseModel$updateItems$1
            r0.<init>(r7, r9)
        L_0x0019:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0036
            if (r2 != r3) goto L_0x002e
            java.lang.Object r7 = r0.L$0
            org.videolan.tools.livedata.LiveDataset r7 = (org.videolan.tools.livedata.LiveDataset) r7
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x0059
        L_0x002e:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x0036:
            kotlin.ResultKt.throwOnFailure(r9)
            org.videolan.tools.livedata.LiveDataset<T> r9 = r7.dataset
            org.videolan.tools.CoroutineContextProvider r2 = r7.coroutineContextProvider
            kotlinx.coroutines.CoroutineDispatcher r2 = r2.getDefault()
            kotlin.coroutines.CoroutineContext r2 = (kotlin.coroutines.CoroutineContext) r2
            org.videolan.vlc.viewmodels.BaseModel$updateItems$2 r4 = new org.videolan.vlc.viewmodels.BaseModel$updateItems$2
            r5 = 0
            r4.<init>(r7, r8, r5)
            kotlin.jvm.functions.Function2 r4 = (kotlin.jvm.functions.Function2) r4
            r0.L$0 = r9
            r0.label = r3
            java.lang.Object r7 = kotlinx.coroutines.BuildersKt.withContext(r2, r4, r0)
            if (r7 != r1) goto L_0x0056
            return r1
        L_0x0056:
            r6 = r9
            r9 = r7
            r7 = r6
        L_0x0059:
            java.util.List r9 = (java.util.List) r9
            r7.setValue(r9)
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.viewmodels.BaseModel.updateItems$suspendImpl(org.videolan.vlc.viewmodels.BaseModel, java.util.List, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: protected */
    public void onCleared() {
        SendChannel.DefaultImpls.close$default(getUpdateActor(), (Throwable) null, 1, (Object) null);
        super.onCleared();
    }
}
