package org.videolan.vlc.viewmodels;

import android.content.Context;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.viewmodel.CreationExtras;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.tools.CoroutineContextProvider;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0011B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\b\u001a\u00020\tH\u0016J\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u0002J\u000e\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u0002J\u000e\u0010\u000f\u001a\u00020\u000bH@¢\u0006\u0002\u0010\u0010¨\u0006\u0012"}, d2 = {"Lorg/videolan/vlc/viewmodels/HistoryModel;", "Lorg/videolan/vlc/viewmodels/MedialibraryModel;", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "context", "Landroid/content/Context;", "coroutineContextProvider", "Lorg/videolan/tools/CoroutineContextProvider;", "(Landroid/content/Context;Lorg/videolan/tools/CoroutineContextProvider;)V", "canSortByName", "", "clearHistory", "", "moveUp", "media", "removeFromHistory", "updateList", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Factory", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: HistoryModel.kt */
public final class HistoryModel extends MedialibraryModel<MediaWrapper> {
    public boolean canSortByName() {
        return false;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public HistoryModel(Context context, CoroutineContextProvider coroutineContextProvider) {
        super(context, coroutineContextProvider);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(coroutineContextProvider, "coroutineContextProvider");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ HistoryModel(Context context, CoroutineContextProvider coroutineContextProvider, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? new CoroutineContextProvider() : coroutineContextProvider);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object updateList(kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            r7 = this;
            boolean r0 = r8 instanceof org.videolan.vlc.viewmodels.HistoryModel$updateList$1
            if (r0 == 0) goto L_0x0014
            r0 = r8
            org.videolan.vlc.viewmodels.HistoryModel$updateList$1 r0 = (org.videolan.vlc.viewmodels.HistoryModel$updateList$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            org.videolan.vlc.viewmodels.HistoryModel$updateList$1 r0 = new org.videolan.vlc.viewmodels.HistoryModel$updateList$1
            r0.<init>(r7, r8)
        L_0x0019:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0036
            if (r2 != r3) goto L_0x002e
            java.lang.Object r0 = r0.L$0
            org.videolan.tools.livedata.LiveDataset r0 = (org.videolan.tools.livedata.LiveDataset) r0
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x0074
        L_0x002e:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L_0x0036:
            kotlin.ResultKt.throwOnFailure(r8)
            org.videolan.tools.Settings r8 = org.videolan.tools.Settings.INSTANCE
            android.content.Context r2 = r7.getContext()
            java.lang.Object r8 = r8.getInstance(r2)
            android.content.SharedPreferences r8 = (android.content.SharedPreferences) r8
            java.lang.String r2 = "playback_history"
            boolean r8 = r8.getBoolean(r2, r3)
            if (r8 != 0) goto L_0x0050
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        L_0x0050:
            org.videolan.tools.livedata.LiveDataset r8 = r7.getDataset()
            org.videolan.tools.CoroutineContextProvider r2 = r7.getCoroutineContextProvider()
            kotlinx.coroutines.CoroutineDispatcher r2 = r2.getDefault()
            kotlin.coroutines.CoroutineContext r2 = (kotlin.coroutines.CoroutineContext) r2
            org.videolan.vlc.viewmodels.HistoryModel$updateList$2 r4 = new org.videolan.vlc.viewmodels.HistoryModel$updateList$2
            r5 = 0
            r4.<init>(r7, r5)
            kotlin.jvm.functions.Function2 r4 = (kotlin.jvm.functions.Function2) r4
            r0.L$0 = r8
            r0.label = r3
            java.lang.Object r0 = kotlinx.coroutines.BuildersKt.withContext(r2, r4, r0)
            if (r0 != r1) goto L_0x0071
            return r1
        L_0x0071:
            r6 = r0
            r0 = r8
            r8 = r6
        L_0x0074:
            java.util.List r8 = (java.util.List) r8
            r0.setValue(r8)
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.viewmodels.HistoryModel.updateList(kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void moveUp(MediaWrapper mediaWrapper) {
        Intrinsics.checkNotNullParameter(mediaWrapper, "media");
        getDataset().move(mediaWrapper, 0);
    }

    public final void clearHistory() {
        getDataset().clear();
    }

    public final void removeFromHistory(MediaWrapper mediaWrapper) {
        Intrinsics.checkNotNullParameter(mediaWrapper, "media");
        Job unused = BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), (CoroutineContext) null, (CoroutineStart) null, new HistoryModel$removeFromHistory$1(this, mediaWrapper, (Continuation<? super HistoryModel$removeFromHistory$1>) null), 3, (Object) null);
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J%\u0010\u0005\u001a\u0002H\u0006\"\b\b\u0000\u0010\u0006*\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00060\tH\u0016¢\u0006\u0002\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lorg/videolan/vlc/viewmodels/HistoryModel$Factory;", "Landroidx/lifecycle/ViewModelProvider$Factory;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "create", "T", "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: HistoryModel.kt */
    public static final class Factory implements ViewModelProvider.Factory {
        private final Context context;

        public /* synthetic */ ViewModel create(Class cls, CreationExtras creationExtras) {
            return ViewModelProvider.Factory.CC.$default$create(this, cls, creationExtras);
        }

        public Factory(Context context2) {
            Intrinsics.checkNotNullParameter(context2, "context");
            this.context = context2;
        }

        public <T extends ViewModel> T create(Class<T> cls) {
            Intrinsics.checkNotNullParameter(cls, "modelClass");
            Context applicationContext = this.context.getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
            return (ViewModel) new HistoryModel(applicationContext, (CoroutineContextProvider) null, 2, (DefaultConstructorMarker) null);
        }
    }
}
