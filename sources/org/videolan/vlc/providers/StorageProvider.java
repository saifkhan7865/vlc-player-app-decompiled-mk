package org.videolan.vlc.providers;

import android.content.Context;
import android.content.res.Resources;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.videolan.libvlc.interfaces.IMedia;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.medialibrary.media.Storage;
import org.videolan.tools.livedata.LiveDataset;
import org.videolan.vlc.R;

@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tJ\u000e\u0010\r\u001a\u00020\u000eH@¢\u0006\u0002\u0010\u000fJ\u0016\u0010\u0010\u001a\u00020\u000e2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00060\u0012H\u0016J\u0018\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u0016H@¢\u0006\u0002\u0010\u0017J\u0018\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001aH\u0016J\u0010\u0010\u001c\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\u001eH\u0016R\u0012\u0010\n\u001a\u00060\u000bj\u0002`\fX\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lorg/videolan/vlc/providers/StorageProvider;", "Lorg/videolan/vlc/providers/FileBrowserProvider;", "context", "Landroid/content/Context;", "dataset", "Lorg/videolan/tools/livedata/LiveDataset;", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "url", "", "(Landroid/content/Context;Lorg/videolan/tools/livedata/LiveDataset;Ljava/lang/String;)V", "sb", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "browseRootImpl", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "computeHeaders", "value", "", "findMedia", "Lorg/videolan/medialibrary/media/Storage;", "media", "Lorg/videolan/libvlc/interfaces/IMedia;", "(Lorg/videolan/libvlc/interfaces/IMedia;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getDescription", "folderCount", "", "mediaFileCount", "getFlags", "interact", "", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: StorageProvider.kt */
public final class StorageProvider extends FileBrowserProvider {
    private final StringBuilder sb = new StringBuilder();

    public void computeHeaders(List<? extends MediaLibraryItem> list) {
        Intrinsics.checkNotNullParameter(list, "value");
    }

    public int getFlags(boolean z) {
        return z ? 2 : 0;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public StorageProvider(Context context, LiveDataset<MediaLibraryItem> liveDataset, String str) {
        super(context, liveDataset, str, false, false, 10, false, 16, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(liveDataset, "dataset");
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0045  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x007a A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x007b  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x008f  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00e6  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object browseRootImpl(kotlin.coroutines.Continuation<? super kotlin.Unit> r11) {
        /*
            r10 = this;
            boolean r0 = r11 instanceof org.videolan.vlc.providers.StorageProvider$browseRootImpl$1
            if (r0 == 0) goto L_0x0014
            r0 = r11
            org.videolan.vlc.providers.StorageProvider$browseRootImpl$1 r0 = (org.videolan.vlc.providers.StorageProvider$browseRootImpl$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L_0x0019
        L_0x0014:
            org.videolan.vlc.providers.StorageProvider$browseRootImpl$1 r0 = new org.videolan.vlc.providers.StorageProvider$browseRootImpl$1
            r0.<init>(r10, r11)
        L_0x0019:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            r4 = 2
            if (r2 == 0) goto L_0x0045
            if (r2 == r3) goto L_0x003d
            if (r2 != r4) goto L_0x0035
            java.lang.Object r1 = r0.L$1
            java.util.List r1 = (java.util.List) r1
            java.lang.Object r0 = r0.L$0
            org.videolan.vlc.providers.StorageProvider r0 = (org.videolan.vlc.providers.StorageProvider) r0
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x007e
        L_0x0035:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r0)
            throw r11
        L_0x003d:
            java.lang.Object r2 = r0.L$0
            org.videolan.vlc.providers.StorageProvider r2 = (org.videolan.vlc.providers.StorageProvider) r2
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x0060
        L_0x0045:
            kotlin.ResultKt.throwOnFailure(r11)
            org.videolan.vlc.repository.DirectoryRepository$Companion r11 = org.videolan.vlc.repository.DirectoryRepository.Companion
            android.content.Context r2 = r10.getContext()
            java.lang.Object r11 = r11.getInstance(r2)
            org.videolan.vlc.repository.DirectoryRepository r11 = (org.videolan.vlc.repository.DirectoryRepository) r11
            r0.L$0 = r10
            r0.label = r3
            java.lang.Object r11 = r11.getMediaDirectories(r0)
            if (r11 != r1) goto L_0x005f
            return r1
        L_0x005f:
            r2 = r10
        L_0x0060:
            java.util.List r11 = (java.util.List) r11
            org.videolan.vlc.repository.DirectoryRepository$Companion r3 = org.videolan.vlc.repository.DirectoryRepository.Companion
            android.content.Context r5 = r2.getContext()
            java.lang.Object r3 = r3.getInstance(r5)
            org.videolan.vlc.repository.DirectoryRepository r3 = (org.videolan.vlc.repository.DirectoryRepository) r3
            r0.L$0 = r2
            r0.L$1 = r11
            r0.label = r4
            java.lang.Object r0 = r3.getCustomDirectories(r0)
            if (r0 != r1) goto L_0x007b
            return r1
        L_0x007b:
            r1 = r11
            r11 = r0
            r0 = r2
        L_0x007e:
            java.util.List r11 = (java.util.List) r11
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.util.Iterator r3 = r1.iterator()
        L_0x0089:
            boolean r5 = r3.hasNext()
            if (r5 == 0) goto L_0x00dc
            java.lang.Object r5 = r3.next()
            java.lang.String r5 = (java.lang.String) r5
            java.io.File r6 = new java.io.File
            r6.<init>(r5)
            boolean r7 = r6.exists()
            if (r7 == 0) goto L_0x0089
            boolean r6 = r6.canRead()
            if (r6 != 0) goto L_0x00a7
            goto L_0x0089
        L_0x00a7:
            r6 = r5
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6
            int r6 = r6.length()
            if (r6 != 0) goto L_0x00b1
            goto L_0x0089
        L_0x00b1:
            org.videolan.medialibrary.media.Storage r6 = new org.videolan.medialibrary.media.Storage
            java.io.File r7 = new java.io.File
            r7.<init>(r5)
            android.net.Uri r7 = android.net.Uri.fromFile(r7)
            r6.<init>((android.net.Uri) r7)
            org.videolan.resources.AndroidDevices r7 = org.videolan.resources.AndroidDevices.INSTANCE
            java.lang.String r7 = r7.getEXTERNAL_PUBLIC_DIRECTORY()
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r5)
            if (r5 == 0) goto L_0x00d8
            android.content.Context r5 = r0.getContext()
            int r7 = org.videolan.vlc.R.string.internal_memory
            java.lang.String r5 = r5.getString(r7)
            r6.setName(r5)
        L_0x00d8:
            r2.add(r6)
            goto L_0x0089
        L_0x00dc:
            java.util.Iterator r11 = r11.iterator()
        L_0x00e0:
            boolean r3 = r11.hasNext()
            if (r3 == 0) goto L_0x0124
            java.lang.Object r3 = r11.next()
            org.videolan.vlc.mediadb.models.CustomDirectory r3 = (org.videolan.vlc.mediadb.models.CustomDirectory) r3
            java.util.Iterator r5 = r1.iterator()
        L_0x00f0:
            boolean r6 = r5.hasNext()
            if (r6 == 0) goto L_0x0113
            java.lang.Object r6 = r5.next()
            java.lang.String r6 = (java.lang.String) r6
            r7 = r6
            java.lang.CharSequence r7 = (java.lang.CharSequence) r7
            int r7 = r7.length()
            if (r7 != 0) goto L_0x0106
            goto L_0x00f0
        L_0x0106:
            java.lang.String r7 = r3.getPath()
            r8 = 0
            r9 = 0
            boolean r6 = kotlin.text.StringsKt.startsWith$default(r7, r6, r8, r4, r9)
            if (r6 == 0) goto L_0x00f0
            goto L_0x00e0
        L_0x0113:
            org.videolan.medialibrary.media.Storage r5 = new org.videolan.medialibrary.media.Storage
            java.lang.String r3 = r3.getPath()
            android.net.Uri r3 = android.net.Uri.parse(r3)
            r5.<init>((android.net.Uri) r3)
            r2.add(r5)
            goto L_0x00e0
        L_0x0124:
            org.videolan.tools.livedata.LiveDataset r11 = r0.getDataset()
            java.util.List r2 = (java.util.List) r2
            r11.setValue(r2)
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.providers.StorageProvider.browseRootImpl(kotlin.coroutines.Continuation):java.lang.Object");
    }

    public String getDescription(int i, int i2) {
        Resources resources = getContext().getResources();
        StringsKt.clear(this.sb);
        if (i > 0) {
            this.sb.append(resources.getQuantityString(R.plurals.subfolders_quantity, i, new Object[]{Integer.valueOf(i)}));
        } else {
            this.sb.append(resources.getString(R.string.nosubfolder));
        }
        String sb2 = this.sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
        return sb2;
    }

    /* access modifiers changed from: protected */
    public Object findMedia(IMedia iMedia, Continuation<? super Storage> continuation) {
        if (!StorageProviderKt.isStorage(iMedia)) {
            iMedia = null;
        }
        if (iMedia != null) {
            return new Storage(iMedia.getUri());
        }
        return null;
    }
}
