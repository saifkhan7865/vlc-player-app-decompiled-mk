package org.videolan.vlc.providers;

import android.content.Context;
import android.hardware.usb.UsbDevice;
import android.net.Uri;
import androidx.lifecycle.Observer;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.videolan.libvlc.util.MediaBrowser;
import org.videolan.medialibrary.MLServiceLocator;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.tools.livedata.LiveDataset;
import org.videolan.vlc.ExternalMonitor;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.helpers.hf.StoragePermissionsDelegate;
import videolan.org.commontools.LiveEvent;

@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u00012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002BI\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\r\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\r¢\u0006\u0002\u0010\u0012J\u0012\u0010\u0016\u001a\u00020\u00172\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0014J\u001c\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00192\u0006\u0010\n\u001a\u00020\u000bH@¢\u0006\u0002\u0010\u001bJ\u000e\u0010\u001c\u001a\u00020\u0017H@¢\u0006\u0002\u0010\u001dJ\u0016\u0010\u001e\u001a\u00020\u00172\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0016J\b\u0010 \u001a\u00020\u0017H\u0016J*\u0010!\u001a\u0004\u0018\u00010\u00172\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\rH@¢\u0006\u0002\u0010%R\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0010X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\r0\u0002X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0010X\u000e¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lorg/videolan/vlc/providers/FileBrowserProvider;", "Lorg/videolan/vlc/providers/BrowserProvider;", "Landroidx/lifecycle/Observer;", "", "Landroid/hardware/usb/UsbDevice;", "context", "Landroid/content/Context;", "dataset", "Lorg/videolan/tools/livedata/LiveDataset;", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "url", "", "filePicker", "", "showDummyCategory", "sort", "", "desc", "(Landroid/content/Context;Lorg/videolan/tools/livedata/LiveDataset;Ljava/lang/String;ZZIZ)V", "otgPosition", "storageObserver", "storagePosition", "browse", "", "browseByUrl", "", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "browseRootImpl", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onChanged", "list", "release", "requestBrowsing", "eventListener", "Lorg/videolan/libvlc/util/MediaBrowser$EventListener;", "interact", "(Ljava/lang/String;Lorg/videolan/libvlc/util/MediaBrowser$EventListener;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: FileBrowserProvider.kt */
public class FileBrowserProvider extends BrowserProvider implements Observer<List<UsbDevice>> {
    private final boolean filePicker;
    private int otgPosition;
    private final boolean showDummyCategory;
    private Observer<Boolean> storageObserver;
    private int storagePosition;

    public Object browseRootImpl(Continuation<? super Unit> continuation) {
        return browseRootImpl$suspendImpl(this, continuation);
    }

    /* access modifiers changed from: protected */
    public Object requestBrowsing(String str, MediaBrowser.EventListener eventListener, boolean z, Continuation<? super Unit> continuation) {
        return BuildersKt.withContext(getCoroutineContextProvider().getIO(), new FileBrowserProvider$requestBrowsing$2(this, eventListener, str, z, (Continuation<? super FileBrowserProvider$requestBrowsing$2>) null), continuation);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ FileBrowserProvider(Context context, LiveDataset liveDataset, String str, boolean z, boolean z2, int i, boolean z3, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, liveDataset, str, (i2 & 8) != 0 ? false : z, (i2 & 16) != 0 ? true : z2, i, z3);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FileBrowserProvider(Context context, LiveDataset<MediaLibraryItem> liveDataset, String str, boolean z, boolean z2, int i, boolean z3) {
        super(context, liveDataset, str, i, z3);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(liveDataset, "dataset");
        this.filePicker = z;
        this.showDummyCategory = z2;
        this.storagePosition = -1;
        this.otgPosition = -1;
        fetch();
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0045  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00b3  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0123  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x012e  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x014a  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ java.lang.Object browseRootImpl$suspendImpl(org.videolan.vlc.providers.FileBrowserProvider r10, kotlin.coroutines.Continuation<? super kotlin.Unit> r11) {
        /*
            boolean r0 = r11 instanceof org.videolan.vlc.providers.FileBrowserProvider$browseRootImpl$1
            if (r0 == 0) goto L_0x0014
            r0 = r11
            org.videolan.vlc.providers.FileBrowserProvider$browseRootImpl$1 r0 = (org.videolan.vlc.providers.FileBrowserProvider$browseRootImpl$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L_0x0019
        L_0x0014:
            org.videolan.vlc.providers.FileBrowserProvider$browseRootImpl$1 r0 = new org.videolan.vlc.providers.FileBrowserProvider$browseRootImpl$1
            r0.<init>(r10, r11)
        L_0x0019:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L_0x0045
            if (r2 != r4) goto L_0x003d
            int r10 = r0.I$0
            java.lang.Object r1 = r0.L$2
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Object r2 = r0.L$1
            java.lang.String r2 = (java.lang.String) r2
            java.lang.Object r0 = r0.L$0
            org.videolan.vlc.providers.FileBrowserProvider r0 = (org.videolan.vlc.providers.FileBrowserProvider) r0
            kotlin.ResultKt.throwOnFailure(r11)
            r9 = r11
            r11 = r10
            r10 = r0
            r0 = r9
            goto L_0x008f
        L_0x003d:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L_0x0045:
            kotlin.ResultKt.throwOnFailure(r11)
            androidx.lifecycle.MutableLiveData r11 = r10.getLoading()
            java.lang.Boolean r2 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r4)
            r11.postValue(r2)
            android.content.Context r11 = r10.getContext()
            int r2 = org.videolan.vlc.R.string.internal_memory
            java.lang.String r2 = r11.getString(r2)
            java.lang.String r11 = "getString(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r11)
            android.content.Context r5 = r10.getContext()
            int r6 = org.videolan.vlc.R.string.browser_storages
            java.lang.String r5 = r5.getString(r6)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r11)
            org.videolan.vlc.repository.DirectoryRepository$Companion r11 = org.videolan.vlc.repository.DirectoryRepository.Companion
            android.content.Context r6 = r10.getContext()
            java.lang.Object r11 = r11.getInstance(r6)
            org.videolan.vlc.repository.DirectoryRepository r11 = (org.videolan.vlc.repository.DirectoryRepository) r11
            r0.L$0 = r10
            r0.L$1 = r2
            r0.L$2 = r5
            r0.I$0 = r3
            r0.label = r4
            java.lang.Object r11 = r11.getMediaDirectories(r0)
            if (r11 != r1) goto L_0x008c
            return r1
        L_0x008c:
            r0 = r11
            r1 = r5
            r11 = 0
        L_0x008f:
            java.util.List r0 = (java.util.List) r0
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            java.util.List r5 = (java.util.List) r5
            boolean r6 = r10.filePicker
            if (r6 != 0) goto L_0x00a8
            boolean r6 = r10.showDummyCategory
            if (r6 == 0) goto L_0x00a8
            org.videolan.medialibrary.media.DummyItem r6 = new org.videolan.medialibrary.media.DummyItem
            r6.<init>((java.lang.String) r1)
            r5.add(r6)
        L_0x00a8:
            java.util.Iterator r0 = r0.iterator()
        L_0x00ac:
            boolean r1 = r0.hasNext()
            r6 = 3
            if (r1 == 0) goto L_0x010c
            java.lang.Object r1 = r0.next()
            java.lang.String r1 = (java.lang.String) r1
            java.io.File r7 = new java.io.File
            r7.<init>(r1)
            boolean r8 = r7.exists()
            if (r8 == 0) goto L_0x00ac
            boolean r7 = r7.canRead()
            if (r7 != 0) goto L_0x00cb
            goto L_0x00ac
        L_0x00cb:
            android.net.Uri r11 = org.videolan.libvlc.util.AndroidUtil.PathToUri(r1)
            org.videolan.medialibrary.interfaces.media.MediaWrapper r11 = org.videolan.medialibrary.MLServiceLocator.getAbstractMediaWrapper((android.net.Uri) r11)
            r11.setType(r6)
            org.videolan.resources.AndroidDevices r6 = org.videolan.resources.AndroidDevices.INSTANCE
            java.lang.String r6 = r6.getEXTERNAL_PUBLIC_DIRECTORY()
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r6, (java.lang.Object) r1)
            if (r1 == 0) goto L_0x00ec
            r11.setDisplayTitle(r2)
            int r1 = r5.size()
            r10.storagePosition = r1
            goto L_0x0104
        L_0x00ec:
            org.videolan.vlc.util.FileUtils r1 = org.videolan.vlc.util.FileUtils.INSTANCE
            java.lang.String r6 = r11.getTitle()
            java.lang.String r7 = "getTitle(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r7)
            java.lang.String r1 = r1.getStorageTag(r6)
            if (r1 == 0) goto L_0x0100
            r11.setDisplayTitle(r1)
        L_0x0100:
            r1 = 4
            r11.addStateFlags(r1)
        L_0x0104:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r11)
            r5.add(r11)
            r11 = 1
            goto L_0x00ac
        L_0x010c:
            boolean r0 = org.videolan.libvlc.util.AndroidUtil.isMarshMallowOrLater
            if (r0 == 0) goto L_0x012c
            if (r11 != 0) goto L_0x012c
            org.videolan.vlc.providers.FileBrowserProvider$$ExternalSyntheticLambda0 r0 = new org.videolan.vlc.providers.FileBrowserProvider$$ExternalSyntheticLambda0
            r0.<init>(r10)
            r10.storageObserver = r0
            org.videolan.vlc.gui.helpers.hf.StoragePermissionsDelegate$Companion r0 = org.videolan.vlc.gui.helpers.hf.StoragePermissionsDelegate.Companion
            videolan.org.commontools.LiveEvent r0 = r0.getStorageAccessGranted()
            androidx.lifecycle.Observer<java.lang.Boolean> r1 = r10.storageObserver
            if (r1 != 0) goto L_0x0129
            java.lang.String r1 = "storageObserver"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            r1 = 0
        L_0x0129:
            r0.observeForever(r1)
        L_0x012c:
            if (r11 != 0) goto L_0x014a
            androidx.lifecycle.MutableLiveData r11 = r10.getLoading()
            java.lang.Boolean r0 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)
            r11.postValue(r0)
            org.videolan.tools.livedata.LiveDataset r10 = r10.getDataset()
            java.util.ArrayList r11 = new java.util.ArrayList
            r11.<init>()
            java.util.List r11 = (java.util.List) r11
            r10.setValue(r11)
            kotlin.Unit r10 = kotlin.Unit.INSTANCE
            return r10
        L_0x014a:
            boolean r11 = org.videolan.libvlc.util.AndroidUtil.isLolliPopOrLater
            if (r11 == 0) goto L_0x0180
            org.videolan.vlc.ExternalMonitor r11 = org.videolan.vlc.ExternalMonitor.INSTANCE
            org.videolan.tools.livedata.LiveDataset r11 = r11.getDevices()
            boolean r11 = r11.isEmpty()
            if (r11 != 0) goto L_0x0180
            java.lang.String r11 = "otg://"
            android.net.Uri r11 = android.net.Uri.parse(r11)
            org.videolan.medialibrary.interfaces.media.MediaWrapper r11 = org.videolan.medialibrary.MLServiceLocator.getAbstractMediaWrapper((android.net.Uri) r11)
            android.content.Context r0 = r10.getContext()
            int r1 = org.videolan.vlc.R.string.otg_device_title
            java.lang.String r0 = r0.getString(r1)
            r11.setTitle(r0)
            r11.setType(r6)
            int r0 = r5.size()
            r10.otgPosition = r0
            kotlin.jvm.internal.Intrinsics.checkNotNull(r11)
            r5.add(r11)
        L_0x0180:
            org.videolan.tools.livedata.LiveDataset r11 = r10.getDataset()
            r11.setValue(r5)
            org.videolan.vlc.ExternalMonitor r11 = org.videolan.vlc.ExternalMonitor.INSTANCE
            org.videolan.tools.livedata.LiveDataset r11 = r11.getDevices()
            r0 = r10
            androidx.lifecycle.Observer r0 = (androidx.lifecycle.Observer) r0
            r11.observeForever(r0)
            androidx.lifecycle.MutableLiveData r11 = r10.getLoading()
            java.lang.Boolean r0 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)
            r11.postValue(r0)
            androidx.collection.SparseArrayCompat r10 = r10.getHeaders()
            r10.clear()
            kotlin.Unit r10 = kotlin.Unit.INSTANCE
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.providers.FileBrowserProvider.browseRootImpl$suspendImpl(org.videolan.vlc.providers.FileBrowserProvider, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    public static final void browseRootImpl$lambda$0(FileBrowserProvider fileBrowserProvider, boolean z) {
        Intrinsics.checkNotNullParameter(fileBrowserProvider, "this$0");
        if (z) {
            Job unused = BuildersKt__Builders_commonKt.launch$default(fileBrowserProvider, (CoroutineContext) null, (CoroutineStart) null, new FileBrowserProvider$browseRootImpl$2$1(fileBrowserProvider, (Continuation<? super FileBrowserProvider$browseRootImpl$2$1>) null), 3, (Object) null);
        }
    }

    /* access modifiers changed from: protected */
    public void browse(String str) {
        if (Intrinsics.areEqual((Object) str, (Object) "otg://") || (str != null && StringsKt.startsWith$default(str, "content:", false, 2, (Object) null))) {
            Job unused = BuildersKt__Builders_commonKt.launch$default(this, (CoroutineContext) null, (CoroutineStart) null, new FileBrowserProvider$browse$1(this, str, (Continuation<? super FileBrowserProvider$browse$1>) null), 3, (Object) null);
        } else if (Intrinsics.areEqual((Object) str, (Object) "root")) {
            Job unused2 = BuildersKt__Builders_commonKt.launch$default(this, (CoroutineContext) null, (CoroutineStart) null, new FileBrowserProvider$browse$2(this, (Continuation<? super FileBrowserProvider$browse$2>) null), 3, (Object) null);
        } else {
            super.browse(str);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0032  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0071 A[LOOP:0: B:20:0x006b->B:22:0x0071, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object browseByUrl(java.lang.String r10, kotlin.coroutines.Continuation<? super java.util.List<? extends org.videolan.medialibrary.interfaces.media.MediaWrapper>> r11) {
        /*
            r9 = this;
            boolean r0 = r11 instanceof org.videolan.vlc.providers.FileBrowserProvider$browseByUrl$1
            if (r0 == 0) goto L_0x0014
            r0 = r11
            org.videolan.vlc.providers.FileBrowserProvider$browseByUrl$1 r0 = (org.videolan.vlc.providers.FileBrowserProvider$browseByUrl$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L_0x0019
        L_0x0014:
            org.videolan.vlc.providers.FileBrowserProvider$browseByUrl$1 r0 = new org.videolan.vlc.providers.FileBrowserProvider$browseByUrl$1
            r0.<init>(r9, r11)
        L_0x0019:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0032
            if (r2 != r3) goto L_0x002a
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x0052
        L_0x002a:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L_0x0032:
            kotlin.ResultKt.throwOnFailure(r11)
            java.lang.String r11 = "otg://"
            boolean r11 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r10, (java.lang.Object) r11)
            r2 = 0
            if (r11 != 0) goto L_0x0085
            r11 = 0
            r4 = 2
            java.lang.String r5 = "content:"
            boolean r11 = kotlin.text.StringsKt.startsWith$default(r10, r5, r11, r4, r2)
            if (r11 == 0) goto L_0x0049
            goto L_0x0085
        L_0x0049:
            r0.label = r3
            java.lang.Object r11 = super.browseUrl(r10, r0)
            if (r11 != r1) goto L_0x0052
            return r1
        L_0x0052:
            java.lang.Iterable r11 = (java.lang.Iterable) r11
            java.util.List r10 = kotlin.collections.CollectionsKt.toList(r11)
            java.lang.Iterable r10 = (java.lang.Iterable) r10
            java.util.ArrayList r11 = new java.util.ArrayList
            r0 = 10
            int r0 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r10, r0)
            r11.<init>(r0)
            java.util.Collection r11 = (java.util.Collection) r11
            java.util.Iterator r10 = r10.iterator()
        L_0x006b:
            boolean r0 = r10.hasNext()
            if (r0 == 0) goto L_0x0082
            java.lang.Object r0 = r10.next()
            org.videolan.medialibrary.media.MediaLibraryItem r0 = (org.videolan.medialibrary.media.MediaLibraryItem) r0
            java.lang.String r1 = "null cannot be cast to non-null type org.videolan.medialibrary.interfaces.media.MediaWrapper"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0, r1)
            org.videolan.medialibrary.interfaces.media.MediaWrapper r0 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r0
            r11.add(r0)
            goto L_0x006b
        L_0x0082:
            java.util.List r11 = (java.util.List) r11
            goto L_0x00a2
        L_0x0085:
            java.util.ArrayList r11 = new java.util.ArrayList
            r11.<init>()
            r3 = r9
            kotlinx.coroutines.CoroutineScope r3 = (kotlinx.coroutines.CoroutineScope) r3
            org.videolan.vlc.providers.FileBrowserProvider$browseByUrl$2 r0 = new org.videolan.vlc.providers.FileBrowserProvider$browseByUrl$2
            r0.<init>(r9, r11, r10, r2)
            r6 = r0
            kotlin.jvm.functions.Function2 r6 = (kotlin.jvm.functions.Function2) r6
            r7 = 3
            r8 = 0
            r4 = 0
            r5 = 0
            kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r3, r4, r5, r6, r7, r8)
            java.lang.Iterable r11 = (java.lang.Iterable) r11
            java.util.List r11 = kotlin.collections.CollectionsKt.toList(r11)
        L_0x00a2:
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.providers.FileBrowserProvider.browseByUrl(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public void release() {
        if (getUrl() == null) {
            ExternalMonitor.INSTANCE.getDevices().removeObserver(this);
            if (this.storageObserver != null) {
                LiveEvent<Boolean> storageAccessGranted = StoragePermissionsDelegate.Companion.getStorageAccessGranted();
                Observer<Boolean> observer = this.storageObserver;
                if (observer == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("storageObserver");
                    observer = null;
                }
                storageAccessGranted.removeObserver(observer);
            }
        }
        super.release();
    }

    public void onChanged(List<UsbDevice> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        if (list.isEmpty()) {
            if (this.otgPosition != -1) {
                getDataset().remove(this.otgPosition);
                this.otgPosition = -1;
            }
        } else if (this.otgPosition == -1) {
            MediaWrapper abstractMediaWrapper = MLServiceLocator.getAbstractMediaWrapper(Uri.parse("otg://"));
            abstractMediaWrapper.setTitle(getContext().getString(R.string.otg_device_title));
            abstractMediaWrapper.setType(3);
            this.otgPosition = this.storagePosition + 1;
            LiveDataset<MediaLibraryItem> dataset = getDataset();
            int i = this.otgPosition;
            Intrinsics.checkNotNull(abstractMediaWrapper);
            dataset.add(i, abstractMediaWrapper);
        }
    }
}
