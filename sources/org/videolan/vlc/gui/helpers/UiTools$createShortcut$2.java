package org.videolan.vlc.gui.helpers;

import androidx.fragment.app.FragmentActivity;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.medialibrary.media.MediaLibraryItem;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.helpers.UiTools$createShortcut$2", f = "UiTools.kt", i = {}, l = {564, 567}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: UiTools.kt */
final class UiTools$createShortcut$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
    final /* synthetic */ FragmentActivity $context;
    final /* synthetic */ MediaLibraryItem $mediaLibraryItem;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    UiTools$createShortcut$2(MediaLibraryItem mediaLibraryItem, FragmentActivity fragmentActivity, Continuation<? super UiTools$createShortcut$2> continuation) {
        super(2, continuation);
        this.$mediaLibraryItem = mediaLibraryItem;
        this.$context = fragmentActivity;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new UiTools$createShortcut$2(this.$mediaLibraryItem, this.$context, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
        return ((UiTools$createShortcut$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x00b0  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00b5  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00c0  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00eb  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00ee  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r14) {
        /*
            r13 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r13.label
            r2 = 2
            r3 = 1
            r4 = 48
            if (r1 == 0) goto L_0x0021
            if (r1 == r3) goto L_0x001c
            if (r1 != r2) goto L_0x0014
            kotlin.ResultKt.throwOnFailure(r14)
            goto L_0x005d
        L_0x0014:
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r14.<init>(r0)
            throw r14
        L_0x001c:
            kotlin.ResultKt.throwOnFailure(r14)
            goto L_0x00a7
        L_0x0021:
            kotlin.ResultKt.throwOnFailure(r14)
            org.videolan.medialibrary.media.MediaLibraryItem r14 = r13.$mediaLibraryItem
            boolean r1 = r14 instanceof org.videolan.medialibrary.interfaces.media.Genre
            if (r1 != 0) goto L_0x0060
            boolean r14 = r14 instanceof org.videolan.medialibrary.interfaces.media.Playlist
            if (r14 == 0) goto L_0x002f
            goto L_0x0060
        L_0x002f:
            org.videolan.tools.BitmapCache r14 = org.videolan.tools.BitmapCache.INSTANCE
            org.videolan.vlc.util.ThumbnailsProvider r1 = org.videolan.vlc.util.ThumbnailsProvider.INSTANCE
            org.videolan.medialibrary.media.MediaLibraryItem r3 = r13.$mediaLibraryItem
            boolean r5 = r3 instanceof org.videolan.medialibrary.interfaces.media.MediaWrapper
            int r6 = org.videolan.tools.KotlinExtensionsKt.getDp(r4)
            java.lang.String r6 = java.lang.String.valueOf(r6)
            java.lang.String r1 = r1.getMediaCacheKey(r5, r3, r6)
            android.graphics.Bitmap r14 = r14.getBitmapFromMemCache((java.lang.String) r1)
            if (r14 != 0) goto L_0x00a9
            org.videolan.vlc.util.ThumbnailsProvider r14 = org.videolan.vlc.util.ThumbnailsProvider.INSTANCE
            org.videolan.medialibrary.media.MediaLibraryItem r1 = r13.$mediaLibraryItem
            int r3 = org.videolan.tools.KotlinExtensionsKt.getDp(r4)
            r5 = r13
            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
            r13.label = r2
            java.lang.Object r14 = r14.obtainBitmap(r1, r3, r5)
            if (r14 != r0) goto L_0x005d
            return r0
        L_0x005d:
            android.graphics.Bitmap r14 = (android.graphics.Bitmap) r14
            goto L_0x00a9
        L_0x0060:
            org.videolan.vlc.util.ThumbnailsProvider r5 = org.videolan.vlc.util.ThumbnailsProvider.INSTANCE
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            java.lang.String r1 = "playlist:"
            r14.<init>(r1)
            org.videolan.medialibrary.media.MediaLibraryItem r1 = r13.$mediaLibraryItem
            long r1 = r1.getId()
            r14.append(r1)
            r1 = 95
            r14.append(r1)
            int r1 = org.videolan.tools.KotlinExtensionsKt.getDp(r4)
            r14.append(r1)
            java.lang.String r6 = r14.toString()
            org.videolan.medialibrary.media.MediaLibraryItem r14 = r13.$mediaLibraryItem
            org.videolan.medialibrary.interfaces.media.MediaWrapper[] r14 = r14.getTracks()
            java.lang.String r1 = "getTracks(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r14, r1)
            java.lang.Object[] r14 = (java.lang.Object[]) r14
            java.util.List r7 = kotlin.collections.ArraysKt.toList((T[]) r14)
            int r8 = org.videolan.tools.KotlinExtensionsKt.getDp(r4)
            r10 = r13
            kotlin.coroutines.Continuation r10 = (kotlin.coroutines.Continuation) r10
            r13.label = r3
            r9 = 0
            r11 = 8
            r12 = 0
            java.lang.Object r14 = org.videolan.vlc.util.ThumbnailsProvider.getPlaylistOrGenreImage$default(r5, r6, r7, r8, r9, r10, r11, r12)
            if (r14 != r0) goto L_0x00a7
            return r0
        L_0x00a7:
            android.graphics.Bitmap r14 = (android.graphics.Bitmap) r14
        L_0x00a9:
            int r0 = org.videolan.tools.KotlinExtensionsKt.getDp(r4)
            r1 = 0
            if (r14 == 0) goto L_0x00b5
            int r2 = r14.getHeight()
            goto L_0x00b6
        L_0x00b5:
            r2 = 0
        L_0x00b6:
            int r0 = java.lang.Math.min(r0, r2)
            android.graphics.Bitmap r14 = org.videolan.vlc.gui.helpers.BitmapUtilKt.centerCrop(r14, r0, r0)
            if (r14 != 0) goto L_0x00dc
            org.videolan.vlc.gui.helpers.BitmapUtil r14 = org.videolan.vlc.gui.helpers.BitmapUtil.INSTANCE
            androidx.fragment.app.FragmentActivity r0 = r13.$context
            android.content.Context r0 = (android.content.Context) r0
            int r2 = org.videolan.vlc.R.drawable.ic_icon
            int r3 = org.videolan.tools.KotlinExtensionsKt.getDp(r4)
            java.lang.Integer r3 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r3)
            int r4 = org.videolan.tools.KotlinExtensionsKt.getDp(r4)
            java.lang.Integer r4 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r4)
            android.graphics.Bitmap r14 = r14.vectorToBitmap(r0, r2, r3, r4)
        L_0x00dc:
            androidx.core.graphics.drawable.IconCompat r14 = androidx.core.graphics.drawable.IconCompat.createWithAdaptiveBitmap(r14)
            java.lang.String r0 = "createWithAdaptiveBitmap(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r14, r0)
            org.videolan.medialibrary.media.MediaLibraryItem r0 = r13.$mediaLibraryItem
            boolean r2 = r0 instanceof org.videolan.medialibrary.interfaces.media.Album
            if (r2 == 0) goto L_0x00ee
            java.lang.String r0 = "album"
            goto L_0x0105
        L_0x00ee:
            boolean r2 = r0 instanceof org.videolan.medialibrary.interfaces.media.Artist
            if (r2 == 0) goto L_0x00f5
            java.lang.String r0 = "artist"
            goto L_0x0105
        L_0x00f5:
            boolean r2 = r0 instanceof org.videolan.medialibrary.interfaces.media.Genre
            if (r2 == 0) goto L_0x00fc
            java.lang.String r0 = "genre"
            goto L_0x0105
        L_0x00fc:
            boolean r0 = r0 instanceof org.videolan.medialibrary.interfaces.media.Playlist
            if (r0 == 0) goto L_0x0103
            java.lang.String r0 = "playlist"
            goto L_0x0105
        L_0x0103:
            java.lang.String r0 = "media"
        L_0x0105:
            androidx.core.content.pm.ShortcutInfoCompat$Builder r2 = new androidx.core.content.pm.ShortcutInfoCompat$Builder
            androidx.fragment.app.FragmentActivity r3 = r13.$context
            android.content.Context r3 = (android.content.Context) r3
            org.videolan.medialibrary.media.MediaLibraryItem r4 = r13.$mediaLibraryItem
            long r4 = r4.getId()
            java.lang.String r4 = java.lang.String.valueOf(r4)
            r2.<init>((android.content.Context) r3, (java.lang.String) r4)
            org.videolan.medialibrary.media.MediaLibraryItem r3 = r13.$mediaLibraryItem
            java.lang.String r3 = r3.getTitle()
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3
            androidx.core.content.pm.ShortcutInfoCompat$Builder r2 = r2.setShortLabel(r3)
            android.content.Intent r3 = new android.content.Intent
            androidx.fragment.app.FragmentActivity r4 = r13.$context
            android.content.Context r4 = (android.content.Context) r4
            java.lang.Class<org.videolan.vlc.StartActivity> r5 = org.videolan.vlc.StartActivity.class
            r3.<init>(r4, r5)
            org.videolan.medialibrary.media.MediaLibraryItem r4 = r13.$mediaLibraryItem
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = "vlc.mediashortcut:"
            r5.<init>(r6)
            r5.append(r0)
            r0 = 58
            r5.append(r0)
            long r6 = r4.getId()
            r5.append(r6)
            java.lang.String r0 = r5.toString()
            r3.setAction(r0)
            androidx.core.content.pm.ShortcutInfoCompat$Builder r0 = r2.setIntent(r3)
            androidx.core.content.pm.ShortcutInfoCompat$Builder r14 = r0.setIcon(r14)
            androidx.core.content.pm.ShortcutInfoCompat r14 = r14.build()
            java.lang.String r0 = "build(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r14, r0)
            androidx.fragment.app.FragmentActivity r0 = r13.$context
            android.content.Context r0 = (android.content.Context) r0
            android.content.Intent r0 = androidx.core.content.pm.ShortcutManagerCompat.createShortcutResultIntent(r0, r14)
            java.lang.String r2 = "createShortcutResultIntent(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r2)
            androidx.fragment.app.FragmentActivity r2 = r13.$context
            android.content.Context r2 = (android.content.Context) r2
            r3 = 67108864(0x4000000, float:1.5046328E-36)
            android.app.PendingIntent r0 = android.app.PendingIntent.getBroadcast(r2, r1, r0, r3)
            androidx.fragment.app.FragmentActivity r1 = r13.$context
            android.content.Context r1 = (android.content.Context) r1
            android.content.IntentSender r0 = r0.getIntentSender()
            boolean r14 = androidx.core.content.pm.ShortcutManagerCompat.requestPinShortcut(r1, r14, r0)
            java.lang.Boolean r14 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r14)
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.helpers.UiTools$createShortcut$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
