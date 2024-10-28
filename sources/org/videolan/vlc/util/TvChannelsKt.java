package org.videolan.vlc.util;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import androidx.lifecycle.LifecycleOwner;
import androidx.tvprovider.media.tv.TvContractCompat;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;
import org.videolan.medialibrary.EventTools;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.tools.AppScope;
import videolan.org.commontools.TvChannelUtilsKt;

@Metadata(d1 = {"\u0000D\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005H@¢\u0006\u0002\u0010\t\u001a\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u0007H@¢\u0006\u0002\u0010\f\u001a\u000e\u0010\r\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u0007\u001a\u001e\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0010H@¢\u0006\u0002\u0010\u0011\u001a\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0006\u001a\u00020\u0007H\u0007\u001a\u001e\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u0010H@¢\u0006\u0002\u0010\u0011\u001a&\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0010H@¢\u0006\u0002\u0010\u0019\u001a\u001e\u0010\u001a\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u0005H@¢\u0006\u0002\u0010\t\u001a\u0012\u0010\u001c\u001a\u00020\u001d*\u00020\u0010H@¢\u0006\u0002\u0010\u001e\u001a\n\u0010\u001f\u001a\u00020\u0013*\u00020\u0007\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003XT¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"MAX_RECOMMENDATIONS", "", "TAG", "", "checkWatchNextId", "", "context", "Landroid/content/Context;", "id", "(Landroid/content/Context;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "cleanupWatchNextList", "", "(Landroid/content/Context;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteAllWatchNext", "insertWatchNext", "mw", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "(Landroid/content/Context;Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setChannel", "Lkotlinx/coroutines/Job;", "setResumeProgram", "media", "updateNextProgramAfterThumbnailGeneration", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "(Landroidx/lifecycle/LifecycleOwner;Landroid/content/Context;Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updatePrograms", "channelId", "artUri", "Landroid/net/Uri;", "(Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "launchChannelUpdate", "vlc-android_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: TvChannels.kt */
public final class TvChannelsKt {
    private static final int MAX_RECOMMENDATIONS = 3;
    private static final String TAG = "VLC/TvChannels";

    public static final Job setChannel(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, (CoroutineContext) null, CoroutineStart.UNDISPATCHED, new TvChannelsKt$setChannel$1(context, (Continuation<? super TvChannelsKt$setChannel$1>) null), 1, (Object) null);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x00cf  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0115 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0116  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0126  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x013b  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x019f  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x01e4  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x025f  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x026c  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x0280  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x02af  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object updatePrograms(android.content.Context r35, long r36, kotlin.coroutines.Continuation<? super kotlin.Unit> r38) {
        /*
            r0 = r35
            r1 = r36
            r3 = r38
            boolean r4 = r3 instanceof org.videolan.vlc.util.TvChannelsKt$updatePrograms$1
            if (r4 == 0) goto L_0x001a
            r4 = r3
            org.videolan.vlc.util.TvChannelsKt$updatePrograms$1 r4 = (org.videolan.vlc.util.TvChannelsKt$updatePrograms$1) r4
            int r5 = r4.label
            r6 = -2147483648(0xffffffff80000000, float:-0.0)
            r5 = r5 & r6
            if (r5 == 0) goto L_0x001a
            int r3 = r4.label
            int r3 = r3 - r6
            r4.label = r3
            goto L_0x001f
        L_0x001a:
            org.videolan.vlc.util.TvChannelsKt$updatePrograms$1 r4 = new org.videolan.vlc.util.TvChannelsKt$updatePrograms$1
            r4.<init>(r3)
        L_0x001f:
            java.lang.Object r3 = r4.result
            java.lang.Object r5 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r6 = r4.label
            r7 = 5
            r8 = 4
            r9 = 2
            r10 = 3
            r11 = 1
            if (r6 == 0) goto L_0x00cf
            if (r6 == r11) goto L_0x00c0
            if (r6 == r9) goto L_0x00b2
            if (r6 == r10) goto L_0x0091
            if (r6 == r8) goto L_0x004f
            if (r6 != r7) goto L_0x0047
            java.lang.Object r0 = r4.L$1
            java.util.Iterator r0 = (java.util.Iterator) r0
            java.lang.Object r1 = r4.L$0
            android.content.Context r1 = (android.content.Context) r1
            kotlin.ResultKt.throwOnFailure(r3)
            r2 = 5
            r7 = 0
            goto L_0x027a
        L_0x0047:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x004f:
            long r0 = r4.J$2
            long r13 = r4.J$1
            int r2 = r4.I$2
            int r6 = r4.I$1
            int r9 = r4.I$0
            long r7 = r4.J$0
            java.lang.Object r15 = r4.L$6
            java.lang.String r15 = (java.lang.String) r15
            java.lang.Object r10 = r4.L$5
            java.lang.String r10 = (java.lang.String) r10
            java.lang.Object r11 = r4.L$4
            org.videolan.medialibrary.interfaces.media.MediaWrapper r11 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r11
            java.lang.Object r12 = r4.L$3
            org.videolan.medialibrary.interfaces.media.MediaWrapper[] r12 = (org.videolan.medialibrary.interfaces.media.MediaWrapper[]) r12
            r35 = r0
            java.lang.Object r0 = r4.L$2
            android.content.ComponentName r0 = (android.content.ComponentName) r0
            java.lang.Object r1 = r4.L$1
            java.util.List r1 = (java.util.List) r1
            r37 = r0
            java.lang.Object r0 = r4.L$0
            android.content.Context r0 = (android.content.Context) r0
            kotlin.ResultKt.throwOnFailure(r3)
            r19 = r35
            r21 = r10
            r17 = r13
            r22 = r15
            r13 = r7
            r7 = r6
            r6 = r4
            r4 = r3
            r3 = r2
            r2 = r1
            r1 = r0
            r0 = r37
            goto L_0x01f4
        L_0x0091:
            int r0 = r4.I$2
            int r1 = r4.I$1
            int r2 = r4.I$0
            long r6 = r4.J$0
            java.lang.Object r8 = r4.L$4
            org.videolan.medialibrary.interfaces.media.MediaWrapper r8 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r8
            java.lang.Object r9 = r4.L$3
            org.videolan.medialibrary.interfaces.media.MediaWrapper[] r9 = (org.videolan.medialibrary.interfaces.media.MediaWrapper[]) r9
            java.lang.Object r10 = r4.L$2
            android.content.ComponentName r10 = (android.content.ComponentName) r10
            java.lang.Object r11 = r4.L$1
            java.util.List r11 = (java.util.List) r11
            java.lang.Object r12 = r4.L$0
            android.content.Context r12 = (android.content.Context) r12
            kotlin.ResultKt.throwOnFailure(r3)
            goto L_0x0195
        L_0x00b2:
            long r0 = r4.J$0
            java.lang.Object r2 = r4.L$1
            org.videolan.medialibrary.interfaces.media.MediaWrapper[] r2 = (org.videolan.medialibrary.interfaces.media.MediaWrapper[]) r2
            java.lang.Object r6 = r4.L$0
            android.content.Context r6 = (android.content.Context) r6
            kotlin.ResultKt.throwOnFailure(r3)
            goto L_0x011d
        L_0x00c0:
            long r0 = r4.J$0
            java.lang.Object r2 = r4.L$0
            android.content.Context r2 = (android.content.Context) r2
            kotlin.ResultKt.throwOnFailure(r3)
            r33 = r0
            r0 = r2
            r1 = r33
            goto L_0x00f7
        L_0x00cf:
            kotlin.ResultKt.throwOnFailure(r3)
            r6 = -1
            int r3 = (r1 > r6 ? 1 : (r1 == r6 ? 0 : -1))
            if (r3 != 0) goto L_0x00db
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x00db:
            kotlinx.coroutines.CoroutineDispatcher r3 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r3 = (kotlin.coroutines.CoroutineContext) r3
            org.videolan.vlc.util.TvChannelsKt$updatePrograms$$inlined$getFromMl$1 r6 = new org.videolan.vlc.util.TvChannelsKt$updatePrograms$$inlined$getFromMl$1
            r7 = 0
            r6.<init>(r0, r7)
            kotlin.jvm.functions.Function2 r6 = (kotlin.jvm.functions.Function2) r6
            r4.L$0 = r0
            r4.J$0 = r1
            r7 = 1
            r4.label = r7
            java.lang.Object r3 = kotlinx.coroutines.BuildersKt.withContext(r3, r6, r4)
            if (r3 != r5) goto L_0x00f7
            return r5
        L_0x00f7:
            org.videolan.medialibrary.interfaces.media.MediaWrapper[] r3 = (org.videolan.medialibrary.interfaces.media.MediaWrapper[]) r3
            kotlinx.coroutines.CoroutineDispatcher r6 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r6 = (kotlin.coroutines.CoroutineContext) r6
            org.videolan.vlc.util.TvChannelsKt$updatePrograms$programs$1 r7 = new org.videolan.vlc.util.TvChannelsKt$updatePrograms$programs$1
            r8 = 0
            r7.<init>(r0, r1, r8)
            kotlin.jvm.functions.Function2 r7 = (kotlin.jvm.functions.Function2) r7
            r4.L$0 = r0
            r4.L$1 = r3
            r4.J$0 = r1
            r4.label = r9
            java.lang.Object r6 = kotlinx.coroutines.BuildersKt.withContext(r6, r7, r4)
            if (r6 != r5) goto L_0x0116
            return r5
        L_0x0116:
            r33 = r6
            r6 = r0
            r0 = r1
            r2 = r3
            r3 = r33
        L_0x011d:
            java.util.List r3 = (java.util.List) r3
            if (r2 == 0) goto L_0x02af
            int r7 = r2.length
            if (r7 != 0) goto L_0x0126
            goto L_0x02af
        L_0x0126:
            android.content.ComponentName r7 = new android.content.ComponentName
            java.lang.Class<org.videolan.vlc.PreviewVideoInputService> r8 = org.videolan.vlc.PreviewVideoInputService.class
            r7.<init>(r6, r8)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
            int r8 = r2.length
            r9 = 0
            r9 = r2
            r11 = r3
            r12 = r6
            r10 = r7
            r6 = r0
            r1 = r8
            r0 = 0
        L_0x0139:
            if (r0 >= r1) goto L_0x0275
            r8 = r9[r0]
            if (r8 == 0) goto L_0x0270
            long r2 = r8.getId()
            int r2 = videolan.org.commontools.TvChannelUtilsKt.indexOfId(r11, r2)
            r3 = -1
            if (r2 == r3) goto L_0x014f
            r11.remove(r2)
            goto L_0x0270
        L_0x014f:
            boolean r2 = r8.isThumbnailGenerated()
            if (r2 == 0) goto L_0x0166
            java.lang.String r2 = r8.getArtworkMrl()
            if (r2 != 0) goto L_0x015d
            goto L_0x0270
        L_0x015d:
            r2 = r0
            r13 = r6
            r6 = r1
            r1 = r11
            r0 = r12
            r11 = r8
            r12 = r9
            r9 = r2
            goto L_0x01aa
        L_0x0166:
            kotlinx.coroutines.CoroutineDispatcher r2 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r2 = (kotlin.coroutines.CoroutineContext) r2
            org.videolan.vlc.util.TvChannelsKt$updatePrograms$2 r3 = new org.videolan.vlc.util.TvChannelsKt$updatePrograms$2
            r13 = 0
            r3.<init>(r8, r13)
            kotlin.jvm.functions.Function2 r3 = (kotlin.jvm.functions.Function2) r3
            r4.L$0 = r12
            r4.L$1 = r11
            r4.L$2 = r10
            r4.L$3 = r9
            r4.L$4 = r8
            r4.L$5 = r13
            r4.L$6 = r13
            r4.J$0 = r6
            r4.I$0 = r0
            r4.I$1 = r1
            r4.I$2 = r0
            r13 = 3
            r4.label = r13
            java.lang.Object r3 = kotlinx.coroutines.BuildersKt.withContext(r2, r3, r4)
            if (r3 != r5) goto L_0x0194
            return r5
        L_0x0194:
            r2 = r0
        L_0x0195:
            if (r3 == 0) goto L_0x026c
            java.lang.String r3 = r8.getArtworkMrl()
            if (r3 != 0) goto L_0x019f
            goto L_0x026c
        L_0x019f:
            r13 = r6
            r6 = r1
            r1 = r11
            r11 = r8
            r33 = r2
            r2 = r0
            r0 = r12
            r12 = r9
            r9 = r33
        L_0x01aa:
            long r7 = r11.getId()
            java.lang.String r3 = r11.getTitle()
            java.lang.String r15 = "getTitle(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r15)
            java.lang.String r15 = r11.getDescription()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r11)
            r4.L$0 = r0
            r4.L$1 = r1
            r4.L$2 = r10
            r4.L$3 = r12
            r4.L$4 = r11
            r4.L$5 = r3
            r4.L$6 = r15
            r4.J$0 = r13
            r4.I$0 = r9
            r4.I$1 = r6
            r4.I$2 = r2
            r4.J$1 = r13
            r4.J$2 = r7
            r35 = r0
            r0 = 4
            r4.label = r0
            java.lang.Object r0 = artUri(r11, r4)
            if (r0 != r5) goto L_0x01e4
            return r5
        L_0x01e4:
            r21 = r3
            r19 = r7
            r17 = r13
            r22 = r15
            r3 = r2
            r7 = r6
            r2 = r1
            r6 = r4
            r1 = r35
            r4 = r0
            r0 = r10
        L_0x01f4:
            r23 = r4
            android.net.Uri r23 = (android.net.Uri) r23
            r35 = r6
            r36 = r7
            long r6 = r11.getLength()
            int r4 = (int) r6
            r24 = r4
            long r6 = r11.getTime()
            int r4 = (int) r6
            r25 = r4
            int r26 = r11.getWidth()
            int r27 = r11.getHeight()
            android.net.Uri r4 = r11.getUri()
            java.lang.String r4 = r4.toString()
            r29 = r4
            java.lang.String r6 = "toString(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r6)
            videolan.org.commontools.ProgramDesc r4 = new videolan.org.commontools.ProgramDesc
            r16 = r4
            java.lang.String r28 = "org.videolan.vlc"
            r30 = 0
            r31 = 2048(0x800, float:2.87E-42)
            r32 = 0
            r16.<init>(r17, r19, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32)
            androidx.tvprovider.media.tv.PreviewProgram r4 = videolan.org.commontools.TvChannelUtilsKt.buildProgram(r0, r4)
            kotlinx.coroutines.GlobalScope r6 = kotlinx.coroutines.GlobalScope.INSTANCE
            r16 = r6
            kotlinx.coroutines.CoroutineScope r16 = (kotlinx.coroutines.CoroutineScope) r16
            kotlinx.coroutines.CoroutineDispatcher r6 = kotlinx.coroutines.Dispatchers.getIO()
            r17 = r6
            kotlin.coroutines.CoroutineContext r17 = (kotlin.coroutines.CoroutineContext) r17
            org.videolan.vlc.util.TvChannelsKt$updatePrograms$3 r6 = new org.videolan.vlc.util.TvChannelsKt$updatePrograms$3
            r7 = 0
            r6.<init>(r1, r4, r7)
            r19 = r6
            kotlin.jvm.functions.Function2 r19 = (kotlin.jvm.functions.Function2) r19
            r20 = 2
            r21 = 0
            r18 = 0
            kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r16, r17, r18, r19, r20, r21)
            int r4 = r2.size()
            int r3 = r3 - r4
            r8 = 3
            r4 = r35
            if (r3 >= r8) goto L_0x0269
            r10 = r0
            r11 = r2
            r0 = r9
            r9 = r12
            r6 = r13
            r2 = 1
            r12 = r1
            r1 = r36
            goto L_0x0272
        L_0x0269:
            r12 = r1
            r11 = r2
            goto L_0x0275
        L_0x026c:
            r8 = 3
            r0 = r2
        L_0x026e:
            r2 = 1
            goto L_0x0272
        L_0x0270:
            r8 = 3
            goto L_0x026e
        L_0x0272:
            int r0 = r0 + r2
            goto L_0x0139
        L_0x0275:
            java.util.Iterator r0 = r11.iterator()
            r1 = r12
        L_0x027a:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x02ac
            java.lang.Object r2 = r0.next()
            videolan.org.commontools.TvPreviewProgram r2 = (videolan.org.commontools.TvPreviewProgram) r2
            kotlinx.coroutines.CoroutineDispatcher r3 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r3 = (kotlin.coroutines.CoroutineContext) r3
            org.videolan.vlc.util.TvChannelsKt$updatePrograms$4 r6 = new org.videolan.vlc.util.TvChannelsKt$updatePrograms$4
            r7 = 0
            r6.<init>(r1, r2, r7)
            kotlin.jvm.functions.Function2 r6 = (kotlin.jvm.functions.Function2) r6
            r4.L$0 = r1
            r4.L$1 = r0
            r4.L$2 = r7
            r4.L$3 = r7
            r4.L$4 = r7
            r4.L$5 = r7
            r4.L$6 = r7
            r2 = 5
            r4.label = r2
            java.lang.Object r3 = kotlinx.coroutines.BuildersKt.withContext(r3, r6, r4)
            if (r3 != r5) goto L_0x027a
            return r5
        L_0x02ac:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x02af:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.util.TvChannelsKt.updatePrograms(android.content.Context, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static final Job launchChannelUpdate(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        return BuildersKt__Builders_commonKt.launch$default(AppScope.INSTANCE, (CoroutineContext) null, (CoroutineStart) null, new TvChannelsKt$launchChannelUpdate$1(context, (Continuation<? super TvChannelsKt$launchChannelUpdate$1>) null), 3, (Object) null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0052  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0028  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object insertWatchNext(android.content.Context r23, org.videolan.medialibrary.interfaces.media.MediaWrapper r24, kotlin.coroutines.Continuation<? super kotlin.Unit> r25) {
        /*
            r0 = r24
            r1 = r25
            boolean r2 = r1 instanceof org.videolan.vlc.util.TvChannelsKt$insertWatchNext$1
            if (r2 == 0) goto L_0x0018
            r2 = r1
            org.videolan.vlc.util.TvChannelsKt$insertWatchNext$1 r2 = (org.videolan.vlc.util.TvChannelsKt$insertWatchNext$1) r2
            int r3 = r2.label
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r3 & r4
            if (r3 == 0) goto L_0x0018
            int r1 = r2.label
            int r1 = r1 - r4
            r2.label = r1
            goto L_0x001d
        L_0x0018:
            org.videolan.vlc.util.TvChannelsKt$insertWatchNext$1 r2 = new org.videolan.vlc.util.TvChannelsKt$insertWatchNext$1
            r2.<init>(r1)
        L_0x001d:
            java.lang.Object r1 = r2.result
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r2.label
            r5 = 1
            if (r4 == 0) goto L_0x0052
            if (r4 != r5) goto L_0x004a
            long r3 = r2.J$1
            long r5 = r2.J$0
            java.lang.Object r0 = r2.L$3
            java.lang.String r0 = (java.lang.String) r0
            java.lang.Object r7 = r2.L$2
            java.lang.String r7 = (java.lang.String) r7
            java.lang.Object r8 = r2.L$1
            org.videolan.medialibrary.interfaces.media.MediaWrapper r8 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r8
            java.lang.Object r2 = r2.L$0
            android.content.Context r2 = (android.content.Context) r2
            kotlin.ResultKt.throwOnFailure(r1)
            r9 = r0
            r0 = r8
            r8 = r7
            r20 = r3
            r4 = r5
            r6 = r20
            goto L_0x008a
        L_0x004a:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0052:
            kotlin.ResultKt.throwOnFailure(r1)
            long r6 = r24.getId()
            java.lang.String r1 = r24.getTitle()
            java.lang.String r4 = "getTitle(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r4)
            java.lang.String r4 = r24.getDescription()
            r8 = r23
            r2.L$0 = r8
            r2.L$1 = r0
            r2.L$2 = r1
            r2.L$3 = r4
            r9 = 0
            r2.J$0 = r9
            r2.J$1 = r6
            r2.label = r5
            java.lang.Object r2 = artUri(r0, r2)
            if (r2 != r3) goto L_0x007f
            return r3
        L_0x007f:
            r20 = r8
            r8 = r1
            r1 = r2
            r2 = r20
            r21 = r9
            r9 = r4
            r4 = r21
        L_0x008a:
            r10 = r1
            android.net.Uri r10 = (android.net.Uri) r10
            long r11 = r0.getLength()
            int r11 = (int) r11
            long r12 = r0.getTime()
            int r12 = (int) r12
            int r13 = r0.getWidth()
            int r14 = r0.getHeight()
            android.net.Uri r0 = r0.getUri()
            java.lang.String r0 = r0.toString()
            r16 = r0
            java.lang.String r1 = "toString(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            videolan.org.commontools.ProgramDesc r0 = new videolan.org.commontools.ProgramDesc
            r3 = r0
            java.lang.String r15 = "org.videolan.vlc"
            r17 = 0
            r18 = 2048(0x800, float:2.87E-42)
            r19 = 0
            r3.<init>(r4, r6, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19)
            android.content.ComponentName r1 = new android.content.ComponentName
            java.lang.Class<org.videolan.vlc.PreviewVideoInputService> r3 = org.videolan.vlc.PreviewVideoInputService.class
            r1.<init>(r2, r3)
            androidx.tvprovider.media.tv.WatchNextProgram r0 = videolan.org.commontools.TvChannelUtilsKt.buildWatchNextProgram(r1, r0)
            android.content.ContentResolver r1 = r2.getContentResolver()
            android.net.Uri r2 = androidx.tvprovider.media.tv.TvContractCompat.WatchNextPrograms.CONTENT_URI
            android.content.ContentValues r0 = r0.toContentValues()
            android.net.Uri r0 = r1.insert(r2, r0)
            if (r0 == 0) goto L_0x00df
            android.net.Uri r1 = android.net.Uri.EMPTY
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r0, (java.lang.Object) r1)
            if (r0 == 0) goto L_0x00e6
        L_0x00df:
            java.lang.String r0 = "VLC/TvChannels"
            java.lang.String r1 = "Insert watch next program failed"
            android.util.Log.e(r0, r1)
        L_0x00e6:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.util.TvChannelsKt.insertWatchNext(android.content.Context, org.videolan.medialibrary.interfaces.media.MediaWrapper, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static final Object updateNextProgramAfterThumbnailGeneration(LifecycleOwner lifecycleOwner, Context context, MediaWrapper mediaWrapper, Continuation<? super Unit> continuation) {
        EventTools.getInstance().lastThumb.observe(lifecycleOwner, new TvChannelsKt$sam$androidx_lifecycle_Observer$0(new TvChannelsKt$updateNextProgramAfterThumbnailGeneration$2(lifecycleOwner, context, mediaWrapper)));
        return Unit.INSTANCE;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v22, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v7, resolved type: kotlin.jvm.internal.Ref$ObjectRef} */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00fb, code lost:
        r11.element = true;
        r7 = r4.element.getLong(0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x010a, code lost:
        if (r5.getInt(2) == 0) goto L_0x01d0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0114, code lost:
        if (r1.getTime() == 0) goto L_0x01d0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x011c, code lost:
        if (r1.getTime() == 0) goto L_0x0138;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x011e, code lost:
        r5 = (double) r1.getTime();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0127, code lost:
        r14 = (double) r1.getLength();
        java.lang.Double.isNaN(r5);
        java.lang.Double.isNaN(r14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0136, code lost:
        if ((r5 / r14) >= 0.95d) goto L_0x01d0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0140, code lost:
        if (r1.getLength() >= 20000) goto L_0x0144;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0144, code lost:
        r13 = androidx.tvprovider.media.tv.WatchNextProgram.fromCursor(r4.element);
        r5 = r1.getId();
        r9 = r1.getTitle();
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, "getTitle(...)");
        r12 = r1.getDescription();
        kotlin.jvm.internal.Intrinsics.checkNotNull(r1);
        r2.L$0 = r0;
        r2.L$1 = r4;
        r2.L$2 = r11;
        r2.L$3 = r1;
        r2.L$4 = r13;
        r2.L$5 = r12;
        r2.L$6 = r9;
        r2.J$0 = r7;
        r2.J$1 = r5;
        r16 = r11;
        r2.J$2 = 0;
        r2.label = 2;
        r10 = artUri(r1, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x017f, code lost:
        if (r10 != r3) goto L_0x0182;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0181, code lost:
        return r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0182, code lost:
        r14 = r0;
        r0 = r1;
        r20 = r5;
        r22 = r9;
        r1 = r10;
        r23 = r12;
        r11 = r16;
        r18 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x01d0, code lost:
        r16 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x01d6, code lost:
        if (videolan.org.commontools.TvChannelUtilsKt.deleteWatchNext(r0, r7) >= 1) goto L_0x01eb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x01d8, code lost:
        android.util.Log.e(TAG, "Delete program failed");
        r0 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x01e1, code lost:
        r1 = r4.element;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x01e5, code lost:
        if (r1 == null) goto L_0x01ea;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x01e7, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x01ea, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x01eb, code lost:
        r11 = r16;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x024c, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x024d, code lost:
        r2 = r4;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x008c  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00d9 A[Catch:{ all -> 0x024c }, LOOP:0: B:32:0x00d9->B:92:0x00d9, LOOP_START] */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x0218 A[SYNTHETIC, Splitter:B:73:0x0218] */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x0246  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002a  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x0254  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object setResumeProgram(android.content.Context r34, org.videolan.medialibrary.interfaces.media.MediaWrapper r35, kotlin.coroutines.Continuation<? super kotlin.Unit> r36) {
        /*
            r0 = r34
            r1 = r36
            boolean r2 = r1 instanceof org.videolan.vlc.util.TvChannelsKt$setResumeProgram$1
            if (r2 == 0) goto L_0x0018
            r2 = r1
            org.videolan.vlc.util.TvChannelsKt$setResumeProgram$1 r2 = (org.videolan.vlc.util.TvChannelsKt$setResumeProgram$1) r2
            int r3 = r2.label
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r3 & r4
            if (r3 == 0) goto L_0x0018
            int r1 = r2.label
            int r1 = r1 - r4
            r2.label = r1
            goto L_0x001d
        L_0x0018:
            org.videolan.vlc.util.TvChannelsKt$setResumeProgram$1 r2 = new org.videolan.vlc.util.TvChannelsKt$setResumeProgram$1
            r2.<init>(r1)
        L_0x001d:
            java.lang.Object r1 = r2.result
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r2.label
            r9 = 3
            r10 = 2
            r13 = 1
            if (r4 == 0) goto L_0x008c
            if (r4 == r13) goto L_0x007a
            if (r4 == r10) goto L_0x0045
            if (r4 != r9) goto L_0x003d
            java.lang.Object r0 = r2.L$0
            r2 = r0
            kotlin.jvm.internal.Ref$ObjectRef r2 = (kotlin.jvm.internal.Ref.ObjectRef) r2
            kotlin.ResultKt.throwOnFailure(r1)     // Catch:{ all -> 0x003a }
            goto L_0x023f
        L_0x003a:
            r0 = move-exception
            goto L_0x024e
        L_0x003d:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0045:
            long r9 = r2.J$2
            long r5 = r2.J$1
            long r7 = r2.J$0
            java.lang.Object r0 = r2.L$6
            java.lang.String r0 = (java.lang.String) r0
            java.lang.Object r4 = r2.L$5
            java.lang.String r4 = (java.lang.String) r4
            java.lang.Object r13 = r2.L$4
            androidx.tvprovider.media.tv.WatchNextProgram r13 = (androidx.tvprovider.media.tv.WatchNextProgram) r13
            java.lang.Object r15 = r2.L$3
            org.videolan.medialibrary.interfaces.media.MediaWrapper r15 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r15
            java.lang.Object r11 = r2.L$2
            kotlin.jvm.internal.Ref$BooleanRef r11 = (kotlin.jvm.internal.Ref.BooleanRef) r11
            java.lang.Object r12 = r2.L$1
            kotlin.jvm.internal.Ref$ObjectRef r12 = (kotlin.jvm.internal.Ref.ObjectRef) r12
            java.lang.Object r14 = r2.L$0
            android.content.Context r14 = (android.content.Context) r14
            kotlin.ResultKt.throwOnFailure(r1)     // Catch:{ all -> 0x0076 }
            r22 = r0
            r23 = r4
            r20 = r5
            r18 = r9
            r4 = r12
            r0 = r15
            goto L_0x018f
        L_0x0076:
            r0 = move-exception
            r2 = r12
            goto L_0x024e
        L_0x007a:
            java.lang.Object r0 = r2.L$2
            kotlin.jvm.internal.Ref$BooleanRef r0 = (kotlin.jvm.internal.Ref.BooleanRef) r0
            java.lang.Object r4 = r2.L$1
            kotlin.jvm.internal.Ref$ObjectRef r4 = (kotlin.jvm.internal.Ref.ObjectRef) r4
            java.lang.Object r5 = r2.L$0
            android.content.Context r5 = (android.content.Context) r5
            kotlin.ResultKt.throwOnFailure(r1)
            r11 = r0
            r0 = r5
            goto L_0x00bb
        L_0x008c:
            kotlin.ResultKt.throwOnFailure(r1)
            kotlin.jvm.internal.Ref$ObjectRef r1 = new kotlin.jvm.internal.Ref$ObjectRef
            r1.<init>()
            kotlin.jvm.internal.Ref$BooleanRef r4 = new kotlin.jvm.internal.Ref$BooleanRef
            r4.<init>()
            kotlinx.coroutines.CoroutineDispatcher r5 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r5 = (kotlin.coroutines.CoroutineContext) r5
            org.videolan.vlc.util.TvChannelsKt$setResumeProgram$$inlined$getFromMl$1 r6 = new org.videolan.vlc.util.TvChannelsKt$setResumeProgram$$inlined$getFromMl$1
            r7 = r35
            r8 = 0
            r6.<init>(r0, r8, r7)
            kotlin.jvm.functions.Function2 r6 = (kotlin.jvm.functions.Function2) r6
            r2.L$0 = r0
            r2.L$1 = r1
            r2.L$2 = r4
            r2.label = r13
            java.lang.Object r5 = kotlinx.coroutines.BuildersKt.withContext(r5, r6, r2)
            if (r5 != r3) goto L_0x00b8
            return r3
        L_0x00b8:
            r11 = r4
            r4 = r1
            r1 = r5
        L_0x00bb:
            org.videolan.medialibrary.interfaces.media.MediaWrapper r1 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r1
            android.content.ContentResolver r17 = r0.getContentResolver()     // Catch:{ all -> 0x024c }
            android.net.Uri r18 = androidx.tvprovider.media.tv.TvContractCompat.WatchNextPrograms.CONTENT_URI     // Catch:{ all -> 0x024c }
            java.lang.String[] r19 = videolan.org.commontools.TvChannelUtilsKt.getWATCH_NEXT_MAP_PROJECTION()     // Catch:{ all -> 0x024c }
            r21 = 0
            r22 = 0
            r20 = 0
            android.database.Cursor r5 = r17.query(r18, r19, r20, r21, r22)     // Catch:{ all -> 0x024c }
            r4.element = r5     // Catch:{ all -> 0x024c }
            T r5 = r4.element     // Catch:{ all -> 0x024c }
            android.database.Cursor r5 = (android.database.Cursor) r5     // Catch:{ all -> 0x024c }
            if (r5 == 0) goto L_0x01ee
        L_0x00d9:
            boolean r6 = r5.moveToNext()     // Catch:{ all -> 0x024c }
            if (r6 == 0) goto L_0x01ee
            boolean r6 = r5.isNull(r13)     // Catch:{ all -> 0x024c }
            if (r6 != 0) goto L_0x00d9
            long r6 = r1.getId()     // Catch:{ all -> 0x024c }
            java.lang.String r6 = java.lang.String.valueOf(r6)     // Catch:{ all -> 0x024c }
            T r7 = r4.element     // Catch:{ all -> 0x024c }
            android.database.Cursor r7 = (android.database.Cursor) r7     // Catch:{ all -> 0x024c }
            java.lang.String r7 = r7.getString(r13)     // Catch:{ all -> 0x024c }
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r6, (java.lang.Object) r7)     // Catch:{ all -> 0x024c }
            if (r6 == 0) goto L_0x00d9
            r11.element = r13     // Catch:{ all -> 0x024c }
            T r6 = r4.element     // Catch:{ all -> 0x024c }
            android.database.Cursor r6 = (android.database.Cursor) r6     // Catch:{ all -> 0x024c }
            r7 = 0
            long r7 = r6.getLong(r7)     // Catch:{ all -> 0x024c }
            int r5 = r5.getInt(r10)     // Catch:{ all -> 0x024c }
            if (r5 == 0) goto L_0x01d0
            long r5 = r1.getTime()     // Catch:{ all -> 0x024c }
            r14 = 0
            int r9 = (r5 > r14 ? 1 : (r5 == r14 ? 0 : -1))
            if (r9 == 0) goto L_0x01d0
            long r5 = r1.getTime()     // Catch:{ all -> 0x024c }
            int r9 = (r5 > r14 ? 1 : (r5 == r14 ? 0 : -1))
            if (r9 == 0) goto L_0x0138
            long r5 = r1.getTime()     // Catch:{ all -> 0x024c }
            double r5 = (double) r5     // Catch:{ all -> 0x024c }
            long r14 = r1.getLength()     // Catch:{ all -> 0x024c }
            double r14 = (double) r14
            java.lang.Double.isNaN(r5)
            java.lang.Double.isNaN(r14)
            double r5 = r5 / r14
            r14 = 4606732058837280358(0x3fee666666666666, double:0.95)
            int r9 = (r5 > r14 ? 1 : (r5 == r14 ? 0 : -1))
            if (r9 >= 0) goto L_0x01d0
        L_0x0138:
            long r5 = r1.getLength()     // Catch:{ all -> 0x024c }
            r14 = 20000(0x4e20, double:9.8813E-320)
            int r9 = (r5 > r14 ? 1 : (r5 == r14 ? 0 : -1))
            if (r9 >= 0) goto L_0x0144
            goto L_0x01d0
        L_0x0144:
            T r5 = r4.element     // Catch:{ all -> 0x024c }
            android.database.Cursor r5 = (android.database.Cursor) r5     // Catch:{ all -> 0x024c }
            androidx.tvprovider.media.tv.WatchNextProgram r13 = androidx.tvprovider.media.tv.WatchNextProgram.fromCursor(r5)     // Catch:{ all -> 0x024c }
            long r5 = r1.getId()     // Catch:{ all -> 0x024c }
            java.lang.String r9 = r1.getTitle()     // Catch:{ all -> 0x024c }
            java.lang.String r12 = "getTitle(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r12)     // Catch:{ all -> 0x024c }
            java.lang.String r12 = r1.getDescription()     // Catch:{ all -> 0x024c }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)     // Catch:{ all -> 0x024c }
            r2.L$0 = r0     // Catch:{ all -> 0x024c }
            r2.L$1 = r4     // Catch:{ all -> 0x024c }
            r2.L$2 = r11     // Catch:{ all -> 0x024c }
            r2.L$3 = r1     // Catch:{ all -> 0x024c }
            r2.L$4 = r13     // Catch:{ all -> 0x024c }
            r2.L$5 = r12     // Catch:{ all -> 0x024c }
            r2.L$6 = r9     // Catch:{ all -> 0x024c }
            r2.J$0 = r7     // Catch:{ all -> 0x024c }
            r2.J$1 = r5     // Catch:{ all -> 0x024c }
            r16 = r11
            r10 = 0
            r2.J$2 = r10     // Catch:{ all -> 0x024c }
            r10 = 2
            r2.label = r10     // Catch:{ all -> 0x024c }
            java.lang.Object r10 = artUri(r1, r2)     // Catch:{ all -> 0x024c }
            if (r10 != r3) goto L_0x0182
            return r3
        L_0x0182:
            r14 = r0
            r0 = r1
            r20 = r5
            r22 = r9
            r1 = r10
            r23 = r12
            r11 = r16
            r18 = 0
        L_0x018f:
            r24 = r1
            android.net.Uri r24 = (android.net.Uri) r24     // Catch:{ all -> 0x024c }
            long r5 = r0.getLength()     // Catch:{ all -> 0x024c }
            int r1 = (int) r5     // Catch:{ all -> 0x024c }
            long r5 = r0.getTime()     // Catch:{ all -> 0x024c }
            int r6 = (int) r5     // Catch:{ all -> 0x024c }
            int r27 = r0.getWidth()     // Catch:{ all -> 0x024c }
            int r28 = r0.getHeight()     // Catch:{ all -> 0x024c }
            java.lang.String r29 = "org.videolan.vlc"
            android.net.Uri r5 = r0.getUri()     // Catch:{ all -> 0x024c }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x024c }
            java.lang.String r9 = "toString(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r9)     // Catch:{ all -> 0x024c }
            videolan.org.commontools.ProgramDesc r9 = new videolan.org.commontools.ProgramDesc     // Catch:{ all -> 0x024c }
            r31 = 0
            r32 = 2048(0x800, float:2.87E-42)
            r33 = 0
            r17 = r9
            r25 = r1
            r26 = r6
            r30 = r5
            r17.<init>(r18, r20, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33)     // Catch:{ all -> 0x024c }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r13)     // Catch:{ all -> 0x024c }
            videolan.org.commontools.TvChannelUtilsKt.updateWatchNext(r14, r13, r9, r7)     // Catch:{ all -> 0x024c }
            r1 = r0
            r0 = r14
            goto L_0x01f0
        L_0x01d0:
            r16 = r11
            int r5 = videolan.org.commontools.TvChannelUtilsKt.deleteWatchNext(r0, r7)     // Catch:{ all -> 0x024c }
            if (r5 >= r13) goto L_0x01eb
            java.lang.String r0 = "VLC/TvChannels"
            java.lang.String r1 = "Delete program failed"
            android.util.Log.e(r0, r1)     // Catch:{ all -> 0x024c }
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x024c }
            T r1 = r4.element
            android.database.Cursor r1 = (android.database.Cursor) r1
            if (r1 == 0) goto L_0x01ea
            r1.close()
        L_0x01ea:
            return r0
        L_0x01eb:
            r11 = r16
            goto L_0x01f0
        L_0x01ee:
            r16 = r11
        L_0x01f0:
            boolean r5 = r11.element     // Catch:{ all -> 0x024c }
            if (r5 != 0) goto L_0x0240
            long r5 = r1.getTime()     // Catch:{ all -> 0x024c }
            r7 = 0
            int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r9 == 0) goto L_0x0240
            long r5 = r1.getTime()     // Catch:{ all -> 0x024c }
            double r5 = (double) r5     // Catch:{ all -> 0x024c }
            long r7 = r1.getLength()     // Catch:{ all -> 0x024c }
            double r7 = (double) r7
            java.lang.Double.isNaN(r5)
            java.lang.Double.isNaN(r7)
            double r5 = r5 / r7
            r7 = 4606732058837280358(0x3fee666666666666, double:0.95)
            int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r9 >= 0) goto L_0x0240
            long r5 = r1.getLength()     // Catch:{ all -> 0x024c }
            r7 = 20000(0x4e20, double:9.8813E-320)
            int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r9 <= 0) goto L_0x0240
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)     // Catch:{ all -> 0x024c }
            r2.L$0 = r4     // Catch:{ all -> 0x024c }
            r5 = 0
            r2.L$1 = r5     // Catch:{ all -> 0x024c }
            r2.L$2 = r5     // Catch:{ all -> 0x024c }
            r2.L$3 = r5     // Catch:{ all -> 0x024c }
            r2.L$4 = r5     // Catch:{ all -> 0x024c }
            r2.L$5 = r5     // Catch:{ all -> 0x024c }
            r2.L$6 = r5     // Catch:{ all -> 0x024c }
            r5 = 3
            r2.label = r5     // Catch:{ all -> 0x024c }
            java.lang.Object r0 = insertWatchNext(r0, r1, r2)     // Catch:{ all -> 0x024c }
            if (r0 != r3) goto L_0x023e
            return r3
        L_0x023e:
            r2 = r4
        L_0x023f:
            r4 = r2
        L_0x0240:
            T r0 = r4.element
            android.database.Cursor r0 = (android.database.Cursor) r0
            if (r0 == 0) goto L_0x0249
            r0.close()
        L_0x0249:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x024c:
            r0 = move-exception
            r2 = r4
        L_0x024e:
            T r1 = r2.element
            android.database.Cursor r1 = (android.database.Cursor) r1
            if (r1 == 0) goto L_0x0257
            r1.close()
        L_0x0257:
            goto L_0x0259
        L_0x0258:
            throw r0
        L_0x0259:
            goto L_0x0258
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.util.TvChannelsKt.setResumeProgram(android.content.Context, org.videolan.medialibrary.interfaces.media.MediaWrapper, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00a7, code lost:
        r0 = r9;
        r16 = r3;
        r3 = r1;
        r1 = r8;
        r8 = r16;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00ea, code lost:
        if (r8.moveToNext() == false) goto L_0x0237;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00ec, code lost:
        r9 = androidx.tvprovider.media.tv.WatchNextProgram.fromCursor(r8);
        r10 = ((android.database.Cursor) r1.element).getLong(0);
        r9 = r9.toContentValues();
        r12 = r9.getAsString("internal_provider_id");
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r12, "getAsString(...)");
        r12 = java.lang.Long.parseLong(r12);
        r9 = r9.getAsString(androidx.tvprovider.media.tv.TvContractCompat.PreviewProgramColumns.COLUMN_CONTENT_ID);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0112, code lost:
        if (r9 == null) goto L_0x0165;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x011a, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r9, (java.lang.Object) "") == false) goto L_0x0165;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0120, code lost:
        if (videolan.org.commontools.TvChannelUtilsKt.deleteWatchNext(r0, r10) >= 1) goto L_0x0127;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0122, code lost:
        android.util.Log.e(TAG, "Delete program failed");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0127, code lost:
        r3.L$0 = r0;
        r3.L$1 = r1;
        r3.L$2 = r8;
        r3.L$3 = r7;
        r3.label = 1;
        r9 = kotlinx.coroutines.BuildersKt.withContext(kotlinx.coroutines.Dispatchers.getIO(), new org.videolan.vlc.util.TvChannelsKt$cleanupWatchNextList$lambda$7$$inlined$getFromMl$1(r0, r7, r12), r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0142, code lost:
        if (r9 != r2) goto L_0x0145;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0144, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0145, code lost:
        r16 = r9;
        r9 = r0;
        r0 = r16;
        r17 = r8;
        r8 = r1;
        r1 = r3;
        r3 = r17;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0150, code lost:
        r0 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r0;
        kotlin.jvm.internal.Intrinsics.checkNotNull(r0);
        r1.L$0 = r9;
        r1.L$1 = r8;
        r1.L$2 = r3;
        r1.label = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0162, code lost:
        if (insertWatchNext(r9, r0, r1) != r2) goto L_0x00a7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0164, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0165, code lost:
        if (r9 == null) goto L_0x0235;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0167, code lost:
        r3.L$0 = r0;
        r3.L$1 = r1;
        r3.L$2 = r8;
        r3.L$3 = r9;
        r3.J$0 = r10;
        r3.J$1 = r12;
        r3.label = 3;
        r7 = kotlinx.coroutines.BuildersKt.withContext(kotlinx.coroutines.Dispatchers.getIO(), new org.videolan.vlc.util.TvChannelsKt$cleanupWatchNextList$lambda$7$$inlined$getFromMl$2(r0, r7, r9), r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x0187, code lost:
        if (r7 != r2) goto L_0x018a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0189, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x018a, code lost:
        r14 = r0;
        r0 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x018e, code lost:
        if (((org.videolan.medialibrary.interfaces.media.MediaWrapper) r0) != null) goto L_0x019d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0194, code lost:
        if (videolan.org.commontools.TvChannelUtilsKt.deleteWatchNext(r14, r10) >= 1) goto L_0x0199;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0196, code lost:
        android.util.Log.e(TAG, "Delete program failed");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0199, code lost:
        r0 = r14;
        r7 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x019d, code lost:
        r3.L$0 = r14;
        r3.L$1 = r1;
        r3.L$2 = r8;
        r3.L$3 = r9;
        r3.J$0 = r10;
        r3.label = 4;
        r0 = kotlinx.coroutines.BuildersKt.withContext(kotlinx.coroutines.Dispatchers.getIO(), new org.videolan.vlc.util.TvChannelsKt$cleanupWatchNextList$lambda$7$$inlined$getFromMl$3(r14, (kotlin.coroutines.Continuation) null, r12), r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x01bc, code lost:
        if (r0 != r2) goto L_0x01bf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x01be, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x01bf, code lost:
        r7 = r3;
        r3 = r9;
        r16 = r10;
        r10 = r8;
        r8 = r16;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x01c6, code lost:
        r0 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x01c8, code lost:
        if (r0 == null) goto L_0x01de;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x01d6, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r0.getUri().toString(), (java.lang.Object) r3) != false) goto L_0x01d9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x01d9, code lost:
        r3 = r7;
        r8 = r10;
        r0 = r14;
        r9 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x01e2, code lost:
        if (videolan.org.commontools.TvChannelUtilsKt.deleteWatchNext(r14, r8) >= 1) goto L_0x01ec;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x01e4, code lost:
        android.util.Log.e(TAG, "checkWatchNextId: Delete program failed");
        r3 = r7;
        r8 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x01ec, code lost:
        r9 = null;
        r7.L$0 = r14;
        r7.L$1 = r1;
        r7.L$2 = r10;
        r7.L$3 = null;
        r7.label = 5;
        r0 = kotlinx.coroutines.BuildersKt.withContext(kotlinx.coroutines.Dispatchers.getIO(), new org.videolan.vlc.util.TvChannelsKt$cleanupWatchNextList$lambda$7$$inlined$getFromMl$4(r14, (kotlin.coroutines.Continuation) null, r3), r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x0209, code lost:
        if (r0 != r2) goto L_0x020c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x020b, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x020c, code lost:
        r8 = r1;
        r1 = r7;
        r3 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x020f, code lost:
        r0 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r0;
        kotlin.jvm.internal.Intrinsics.checkNotNull(r0);
        r1.L$0 = r14;
        r1.L$1 = r8;
        r1.L$2 = r3;
        r1.label = 6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0221, code lost:
        if (insertWatchNext(r14, r0, r1) != r2) goto L_0x0224;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x0223, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x0224, code lost:
        r0 = r14;
        r16 = r3;
        r3 = r1;
        r1 = r8;
        r8 = r16;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x022b, code lost:
        ((android.database.Cursor) r1.element).close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x0232, code lost:
        r7 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x0235, code lost:
        r9 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x0237, code lost:
        ((android.database.Cursor) r1.element).close();
     */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x0034  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0047  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x005a  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0074  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0098  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00af  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00c0  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object cleanupWatchNextList(android.content.Context r19, kotlin.coroutines.Continuation<? super kotlin.Unit> r20) {
        /*
            r0 = r20
            boolean r1 = r0 instanceof org.videolan.vlc.util.TvChannelsKt$cleanupWatchNextList$1
            if (r1 == 0) goto L_0x0016
            r1 = r0
            org.videolan.vlc.util.TvChannelsKt$cleanupWatchNextList$1 r1 = (org.videolan.vlc.util.TvChannelsKt$cleanupWatchNextList$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0016
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x001b
        L_0x0016:
            org.videolan.vlc.util.TvChannelsKt$cleanupWatchNextList$1 r1 = new org.videolan.vlc.util.TvChannelsKt$cleanupWatchNextList$1
            r1.<init>(r0)
        L_0x001b:
            java.lang.Object r0 = r1.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            java.lang.String r4 = "Delete program failed"
            r5 = 1
            java.lang.String r6 = "VLC/TvChannels"
            r7 = 0
            switch(r3) {
                case 0: goto L_0x00c0;
                case 1: goto L_0x00af;
                case 2: goto L_0x0098;
                case 3: goto L_0x0074;
                case 4: goto L_0x005a;
                case 5: goto L_0x0047;
                case 6: goto L_0x0034;
                default: goto L_0x002c;
            }
        L_0x002c:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0034:
            java.lang.Object r3 = r1.L$2
            android.database.Cursor r3 = (android.database.Cursor) r3
            java.lang.Object r8 = r1.L$1
            kotlin.jvm.internal.Ref$ObjectRef r8 = (kotlin.jvm.internal.Ref.ObjectRef) r8
            java.lang.Object r9 = r1.L$0
            android.content.Context r9 = (android.content.Context) r9
            kotlin.ResultKt.throwOnFailure(r0)     // Catch:{ Exception -> 0x023f }
            r14 = r9
            r9 = r7
            goto L_0x0224
        L_0x0047:
            java.lang.Object r3 = r1.L$2
            android.database.Cursor r3 = (android.database.Cursor) r3
            java.lang.Object r8 = r1.L$1
            kotlin.jvm.internal.Ref$ObjectRef r8 = (kotlin.jvm.internal.Ref.ObjectRef) r8
            java.lang.Object r9 = r1.L$0
            android.content.Context r9 = (android.content.Context) r9
            kotlin.ResultKt.throwOnFailure(r0)     // Catch:{ Exception -> 0x023f }
            r14 = r9
            r9 = r7
            goto L_0x020f
        L_0x005a:
            long r8 = r1.J$0
            java.lang.Object r3 = r1.L$3
            java.lang.String r3 = (java.lang.String) r3
            java.lang.Object r10 = r1.L$2
            android.database.Cursor r10 = (android.database.Cursor) r10
            java.lang.Object r11 = r1.L$1
            kotlin.jvm.internal.Ref$ObjectRef r11 = (kotlin.jvm.internal.Ref.ObjectRef) r11
            java.lang.Object r12 = r1.L$0
            android.content.Context r12 = (android.content.Context) r12
            kotlin.ResultKt.throwOnFailure(r0)     // Catch:{ Exception -> 0x023f }
            r7 = r1
            r1 = r11
            r14 = r12
            goto L_0x01c6
        L_0x0074:
            long r8 = r1.J$1
            long r10 = r1.J$0
            java.lang.Object r3 = r1.L$3
            java.lang.String r3 = (java.lang.String) r3
            java.lang.Object r12 = r1.L$2
            android.database.Cursor r12 = (android.database.Cursor) r12
            java.lang.Object r13 = r1.L$1
            kotlin.jvm.internal.Ref$ObjectRef r13 = (kotlin.jvm.internal.Ref.ObjectRef) r13
            java.lang.Object r14 = r1.L$0
            android.content.Context r14 = (android.content.Context) r14
            kotlin.ResultKt.throwOnFailure(r0)     // Catch:{ Exception -> 0x023f }
            r16 = r3
            r3 = r1
            r1 = r13
            r17 = r8
            r9 = r16
            r8 = r12
            r12 = r17
            goto L_0x018c
        L_0x0098:
            java.lang.Object r3 = r1.L$2
            android.database.Cursor r3 = (android.database.Cursor) r3
            java.lang.Object r8 = r1.L$1
            kotlin.jvm.internal.Ref$ObjectRef r8 = (kotlin.jvm.internal.Ref.ObjectRef) r8
            java.lang.Object r9 = r1.L$0
            android.content.Context r9 = (android.content.Context) r9
            kotlin.ResultKt.throwOnFailure(r0)     // Catch:{ Exception -> 0x023f }
        L_0x00a7:
            r0 = r9
            r16 = r3
            r3 = r1
            r1 = r8
            r8 = r16
            goto L_0x00e6
        L_0x00af:
            java.lang.Object r3 = r1.L$2
            android.database.Cursor r3 = (android.database.Cursor) r3
            java.lang.Object r8 = r1.L$1
            kotlin.jvm.internal.Ref$ObjectRef r8 = (kotlin.jvm.internal.Ref.ObjectRef) r8
            java.lang.Object r9 = r1.L$0
            android.content.Context r9 = (android.content.Context) r9
            kotlin.ResultKt.throwOnFailure(r0)     // Catch:{ Exception -> 0x023f }
            goto L_0x0150
        L_0x00c0:
            kotlin.ResultKt.throwOnFailure(r0)
            kotlin.jvm.internal.Ref$ObjectRef r0 = new kotlin.jvm.internal.Ref$ObjectRef
            r0.<init>()
            android.content.ContentResolver r8 = r19.getContentResolver()     // Catch:{ Exception -> 0x023f }
            android.net.Uri r9 = androidx.tvprovider.media.tv.TvContractCompat.WatchNextPrograms.CONTENT_URI     // Catch:{ Exception -> 0x023f }
            java.lang.String[] r10 = videolan.org.commontools.TvChannelUtilsKt.getWATCH_NEXT_MAP_PROJECTION()     // Catch:{ Exception -> 0x023f }
            r12 = 0
            r13 = 0
            r11 = 0
            android.database.Cursor r3 = r8.query(r9, r10, r11, r12, r13)     // Catch:{ Exception -> 0x023f }
            r0.element = r3     // Catch:{ Exception -> 0x023f }
            T r3 = r0.element     // Catch:{ Exception -> 0x023f }
            android.database.Cursor r3 = (android.database.Cursor) r3     // Catch:{ Exception -> 0x023f }
            if (r3 == 0) goto L_0x0251
            r8 = r3
            r3 = r1
            r1 = r0
            r0 = r19
        L_0x00e6:
            boolean r9 = r8.moveToNext()     // Catch:{ Exception -> 0x023f }
            if (r9 == 0) goto L_0x0237
            androidx.tvprovider.media.tv.WatchNextProgram r9 = androidx.tvprovider.media.tv.WatchNextProgram.fromCursor(r8)     // Catch:{ Exception -> 0x023f }
            T r10 = r1.element     // Catch:{ Exception -> 0x023f }
            android.database.Cursor r10 = (android.database.Cursor) r10     // Catch:{ Exception -> 0x023f }
            r11 = 0
            long r10 = r10.getLong(r11)     // Catch:{ Exception -> 0x023f }
            android.content.ContentValues r9 = r9.toContentValues()     // Catch:{ Exception -> 0x023f }
            java.lang.String r12 = "internal_provider_id"
            java.lang.String r12 = r9.getAsString(r12)     // Catch:{ Exception -> 0x023f }
            java.lang.String r13 = "getAsString(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r12, r13)     // Catch:{ Exception -> 0x023f }
            long r12 = java.lang.Long.parseLong(r12)     // Catch:{ Exception -> 0x023f }
            java.lang.String r14 = "content_id"
            java.lang.String r9 = r9.getAsString(r14)     // Catch:{ Exception -> 0x023f }
            if (r9 == 0) goto L_0x0165
            java.lang.String r14 = ""
            boolean r14 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r9, (java.lang.Object) r14)     // Catch:{ Exception -> 0x023f }
            if (r14 == 0) goto L_0x0165
            int r9 = videolan.org.commontools.TvChannelUtilsKt.deleteWatchNext(r0, r10)     // Catch:{ Exception -> 0x023f }
            if (r9 >= r5) goto L_0x0127
            android.util.Log.e(r6, r4)     // Catch:{ Exception -> 0x023f }
            goto L_0x0235
        L_0x0127:
            kotlinx.coroutines.CoroutineDispatcher r9 = kotlinx.coroutines.Dispatchers.getIO()     // Catch:{ Exception -> 0x023f }
            kotlin.coroutines.CoroutineContext r9 = (kotlin.coroutines.CoroutineContext) r9     // Catch:{ Exception -> 0x023f }
            org.videolan.vlc.util.TvChannelsKt$cleanupWatchNextList$lambda$7$$inlined$getFromMl$1 r10 = new org.videolan.vlc.util.TvChannelsKt$cleanupWatchNextList$lambda$7$$inlined$getFromMl$1     // Catch:{ Exception -> 0x023f }
            r10.<init>(r0, r7, r12)     // Catch:{ Exception -> 0x023f }
            kotlin.jvm.functions.Function2 r10 = (kotlin.jvm.functions.Function2) r10     // Catch:{ Exception -> 0x023f }
            r3.L$0 = r0     // Catch:{ Exception -> 0x023f }
            r3.L$1 = r1     // Catch:{ Exception -> 0x023f }
            r3.L$2 = r8     // Catch:{ Exception -> 0x023f }
            r3.L$3 = r7     // Catch:{ Exception -> 0x023f }
            r3.label = r5     // Catch:{ Exception -> 0x023f }
            java.lang.Object r9 = kotlinx.coroutines.BuildersKt.withContext(r9, r10, r3)     // Catch:{ Exception -> 0x023f }
            if (r9 != r2) goto L_0x0145
            return r2
        L_0x0145:
            r16 = r9
            r9 = r0
            r0 = r16
            r17 = r8
            r8 = r1
            r1 = r3
            r3 = r17
        L_0x0150:
            org.videolan.medialibrary.interfaces.media.MediaWrapper r0 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r0     // Catch:{ Exception -> 0x023f }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)     // Catch:{ Exception -> 0x023f }
            r1.L$0 = r9     // Catch:{ Exception -> 0x023f }
            r1.L$1 = r8     // Catch:{ Exception -> 0x023f }
            r1.L$2 = r3     // Catch:{ Exception -> 0x023f }
            r10 = 2
            r1.label = r10     // Catch:{ Exception -> 0x023f }
            java.lang.Object r0 = insertWatchNext(r9, r0, r1)     // Catch:{ Exception -> 0x023f }
            if (r0 != r2) goto L_0x00a7
            return r2
        L_0x0165:
            if (r9 == 0) goto L_0x0235
            kotlinx.coroutines.CoroutineDispatcher r14 = kotlinx.coroutines.Dispatchers.getIO()     // Catch:{ Exception -> 0x023f }
            kotlin.coroutines.CoroutineContext r14 = (kotlin.coroutines.CoroutineContext) r14     // Catch:{ Exception -> 0x023f }
            org.videolan.vlc.util.TvChannelsKt$cleanupWatchNextList$lambda$7$$inlined$getFromMl$2 r15 = new org.videolan.vlc.util.TvChannelsKt$cleanupWatchNextList$lambda$7$$inlined$getFromMl$2     // Catch:{ Exception -> 0x023f }
            r15.<init>(r0, r7, r9)     // Catch:{ Exception -> 0x023f }
            kotlin.jvm.functions.Function2 r15 = (kotlin.jvm.functions.Function2) r15     // Catch:{ Exception -> 0x023f }
            r3.L$0 = r0     // Catch:{ Exception -> 0x023f }
            r3.L$1 = r1     // Catch:{ Exception -> 0x023f }
            r3.L$2 = r8     // Catch:{ Exception -> 0x023f }
            r3.L$3 = r9     // Catch:{ Exception -> 0x023f }
            r3.J$0 = r10     // Catch:{ Exception -> 0x023f }
            r3.J$1 = r12     // Catch:{ Exception -> 0x023f }
            r7 = 3
            r3.label = r7     // Catch:{ Exception -> 0x023f }
            java.lang.Object r7 = kotlinx.coroutines.BuildersKt.withContext(r14, r15, r3)     // Catch:{ Exception -> 0x023f }
            if (r7 != r2) goto L_0x018a
            return r2
        L_0x018a:
            r14 = r0
            r0 = r7
        L_0x018c:
            org.videolan.medialibrary.interfaces.media.MediaWrapper r0 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r0     // Catch:{ Exception -> 0x023f }
            if (r0 != 0) goto L_0x019d
            int r0 = videolan.org.commontools.TvChannelUtilsKt.deleteWatchNext(r14, r10)     // Catch:{ Exception -> 0x023f }
            if (r0 >= r5) goto L_0x0199
            android.util.Log.e(r6, r4)     // Catch:{ Exception -> 0x023f }
        L_0x0199:
            r0 = r14
            r7 = 0
            goto L_0x00e6
        L_0x019d:
            kotlinx.coroutines.CoroutineDispatcher r0 = kotlinx.coroutines.Dispatchers.getIO()     // Catch:{ Exception -> 0x023f }
            kotlin.coroutines.CoroutineContext r0 = (kotlin.coroutines.CoroutineContext) r0     // Catch:{ Exception -> 0x023f }
            org.videolan.vlc.util.TvChannelsKt$cleanupWatchNextList$lambda$7$$inlined$getFromMl$3 r7 = new org.videolan.vlc.util.TvChannelsKt$cleanupWatchNextList$lambda$7$$inlined$getFromMl$3     // Catch:{ Exception -> 0x023f }
            r15 = 0
            r7.<init>(r14, r15, r12)     // Catch:{ Exception -> 0x023f }
            kotlin.jvm.functions.Function2 r7 = (kotlin.jvm.functions.Function2) r7     // Catch:{ Exception -> 0x023f }
            r3.L$0 = r14     // Catch:{ Exception -> 0x023f }
            r3.L$1 = r1     // Catch:{ Exception -> 0x023f }
            r3.L$2 = r8     // Catch:{ Exception -> 0x023f }
            r3.L$3 = r9     // Catch:{ Exception -> 0x023f }
            r3.J$0 = r10     // Catch:{ Exception -> 0x023f }
            r12 = 4
            r3.label = r12     // Catch:{ Exception -> 0x023f }
            java.lang.Object r0 = kotlinx.coroutines.BuildersKt.withContext(r0, r7, r3)     // Catch:{ Exception -> 0x023f }
            if (r0 != r2) goto L_0x01bf
            return r2
        L_0x01bf:
            r7 = r3
            r3 = r9
            r16 = r10
            r10 = r8
            r8 = r16
        L_0x01c6:
            org.videolan.medialibrary.interfaces.media.MediaWrapper r0 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r0     // Catch:{ Exception -> 0x023f }
            if (r0 == 0) goto L_0x01de
            android.net.Uri r0 = r0.getUri()     // Catch:{ Exception -> 0x023f }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x023f }
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r0, (java.lang.Object) r3)     // Catch:{ Exception -> 0x023f }
            if (r0 != 0) goto L_0x01d9
            goto L_0x01de
        L_0x01d9:
            r3 = r7
            r8 = r10
            r0 = r14
            r9 = 0
            goto L_0x022b
        L_0x01de:
            int r0 = videolan.org.commontools.TvChannelUtilsKt.deleteWatchNext(r14, r8)     // Catch:{ Exception -> 0x023f }
            if (r0 >= r5) goto L_0x01ec
            java.lang.String r0 = "checkWatchNextId: Delete program failed"
            android.util.Log.e(r6, r0)     // Catch:{ Exception -> 0x023f }
            r3 = r7
            r8 = r10
            goto L_0x0199
        L_0x01ec:
            kotlinx.coroutines.CoroutineDispatcher r0 = kotlinx.coroutines.Dispatchers.getIO()     // Catch:{ Exception -> 0x023f }
            kotlin.coroutines.CoroutineContext r0 = (kotlin.coroutines.CoroutineContext) r0     // Catch:{ Exception -> 0x023f }
            org.videolan.vlc.util.TvChannelsKt$cleanupWatchNextList$lambda$7$$inlined$getFromMl$4 r8 = new org.videolan.vlc.util.TvChannelsKt$cleanupWatchNextList$lambda$7$$inlined$getFromMl$4     // Catch:{ Exception -> 0x023f }
            r9 = 0
            r8.<init>(r14, r9, r3)     // Catch:{ Exception -> 0x023f }
            kotlin.jvm.functions.Function2 r8 = (kotlin.jvm.functions.Function2) r8     // Catch:{ Exception -> 0x023f }
            r7.L$0 = r14     // Catch:{ Exception -> 0x023f }
            r7.L$1 = r1     // Catch:{ Exception -> 0x023f }
            r7.L$2 = r10     // Catch:{ Exception -> 0x023f }
            r7.L$3 = r9     // Catch:{ Exception -> 0x023f }
            r3 = 5
            r7.label = r3     // Catch:{ Exception -> 0x023f }
            java.lang.Object r0 = kotlinx.coroutines.BuildersKt.withContext(r0, r8, r7)     // Catch:{ Exception -> 0x023f }
            if (r0 != r2) goto L_0x020c
            return r2
        L_0x020c:
            r8 = r1
            r1 = r7
            r3 = r10
        L_0x020f:
            org.videolan.medialibrary.interfaces.media.MediaWrapper r0 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r0     // Catch:{ Exception -> 0x023f }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)     // Catch:{ Exception -> 0x023f }
            r1.L$0 = r14     // Catch:{ Exception -> 0x023f }
            r1.L$1 = r8     // Catch:{ Exception -> 0x023f }
            r1.L$2 = r3     // Catch:{ Exception -> 0x023f }
            r7 = 6
            r1.label = r7     // Catch:{ Exception -> 0x023f }
            java.lang.Object r0 = insertWatchNext(r14, r0, r1)     // Catch:{ Exception -> 0x023f }
            if (r0 != r2) goto L_0x0224
            return r2
        L_0x0224:
            r0 = r14
            r16 = r3
            r3 = r1
            r1 = r8
            r8 = r16
        L_0x022b:
            T r7 = r1.element     // Catch:{ Exception -> 0x023f }
            android.database.Cursor r7 = (android.database.Cursor) r7     // Catch:{ Exception -> 0x023f }
            r7.close()     // Catch:{ Exception -> 0x023f }
        L_0x0232:
            r7 = r9
            goto L_0x00e6
        L_0x0235:
            r9 = r7
            goto L_0x0232
        L_0x0237:
            T r0 = r1.element     // Catch:{ Exception -> 0x023f }
            android.database.Cursor r0 = (android.database.Cursor) r0     // Catch:{ Exception -> 0x023f }
            r0.close()     // Catch:{ Exception -> 0x023f }
            goto L_0x0251
        L_0x023f:
            r0 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "cleanupWatchNextList: "
            r1.<init>(r2)
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            android.util.Log.e(r6, r0)
        L_0x0251:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.util.TvChannelsKt.cleanupWatchNextList(android.content.Context, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static final void deleteAllWatchNext(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            Cursor query = context.getContentResolver().query(TvContractCompat.WatchNextPrograms.CONTENT_URI, TvChannelUtilsKt.getWATCH_NEXT_MAP_PROJECTION(), (String) null, (String[]) null, (String) null);
            if (query != null) {
                while (query.moveToNext()) {
                    TvChannelUtilsKt.deleteWatchNext(context, query.getLong(0));
                }
                query.close();
            }
        } catch (Exception e) {
            Log.e(TAG, "deleteAllWatchNext: " + e);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00d8, code lost:
        r10 = r10.getAsString(androidx.tvprovider.media.tv.TvContractCompat.PreviewProgramColumns.COLUMN_CONTENT_ID);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00de, code lost:
        if (r10 == null) goto L_0x00f9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00e6, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r10, (java.lang.Object) "") == false) goto L_0x00f9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00ec, code lost:
        if (videolan.org.commontools.TvChannelUtilsKt.deleteWatchNext(r0, r11) >= r8) goto L_0x00f1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00ee, code lost:
        android.util.Log.e(TAG, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00f1, code lost:
        r9.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00f8, code lost:
        return kotlin.coroutines.jvm.internal.Boxing.boxLong(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00f9, code lost:
        r17 = r6;
        r1.L$0 = r0;
        r1.L$1 = r9;
        r1.L$2 = r5;
        r1.L$3 = r10;
        r1.J$0 = r3;
        r1.J$1 = r11;
        r1.J$2 = r13;
        r1.label = 1;
        r6 = kotlinx.coroutines.BuildersKt.withContext(kotlinx.coroutines.Dispatchers.getIO(), new org.videolan.vlc.util.TvChannelsKt$checkWatchNextId$lambda$12$$inlined$getFromMl$1(r0, (kotlin.coroutines.Continuation) null, r10), r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x011e, code lost:
        if (r6 != r2) goto L_0x0121;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0120, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0121, code lost:
        r14 = r13;
        r12 = r11;
        r22 = r9;
        r9 = r0;
        r0 = r6;
        r6 = r22;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:?, code lost:
        r9.close();
     */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0093  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00b8 A[Catch:{ Exception -> 0x01c3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x012d A[Catch:{ Exception -> 0x01c3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x016c A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x016d  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0179 A[Catch:{ Exception -> 0x0066 }] */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x0191 A[Catch:{ Exception -> 0x0066 }] */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x01b3 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x01b4  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object checkWatchNextId(android.content.Context r24, long r25, kotlin.coroutines.Continuation<? super java.lang.Long> r27) {
        /*
            r0 = r27
            boolean r1 = r0 instanceof org.videolan.vlc.util.TvChannelsKt$checkWatchNextId$1
            if (r1 == 0) goto L_0x0016
            r1 = r0
            org.videolan.vlc.util.TvChannelsKt$checkWatchNextId$1 r1 = (org.videolan.vlc.util.TvChannelsKt$checkWatchNextId$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0016
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x001b
        L_0x0016:
            org.videolan.vlc.util.TvChannelsKt$checkWatchNextId$1 r1 = new org.videolan.vlc.util.TvChannelsKt$checkWatchNextId$1
            r1.<init>(r0)
        L_0x001b:
            java.lang.Object r0 = r1.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            r4 = 3
            r5 = 2
            java.lang.String r6 = "checkWatchNextId: Delete program failed"
            java.lang.String r7 = "VLC/TvChannels"
            r8 = 1
            if (r3 == 0) goto L_0x0093
            if (r3 == r8) goto L_0x006a
            if (r3 == r5) goto L_0x0049
            if (r3 != r4) goto L_0x0041
            long r2 = r1.J$0
            java.lang.Object r1 = r1.L$0
            android.database.Cursor r1 = (android.database.Cursor) r1
            kotlin.ResultKt.throwOnFailure(r0)     // Catch:{ Exception -> 0x003d }
            goto L_0x01b5
        L_0x003d:
            r0 = move-exception
            r3 = r2
            goto L_0x01d2
        L_0x0041:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0049:
            long r10 = r1.J$1
            long r12 = r1.J$0
            java.lang.Object r3 = r1.L$2
            java.lang.String r3 = (java.lang.String) r3
            java.lang.Object r5 = r1.L$1
            android.database.Cursor r5 = (android.database.Cursor) r5
            java.lang.Object r14 = r1.L$0
            android.content.Context r14 = (android.content.Context) r14
            kotlin.ResultKt.throwOnFailure(r0)     // Catch:{ Exception -> 0x0066 }
            r8 = r6
            r22 = r3
            r3 = r1
            r1 = r5
            r4 = r10
            r10 = r22
            goto L_0x0175
        L_0x0066:
            r0 = move-exception
            r3 = r12
            goto L_0x01d2
        L_0x006a:
            long r10 = r1.J$2
            long r12 = r1.J$1
            long r14 = r1.J$0
            java.lang.Object r3 = r1.L$3
            java.lang.String r3 = (java.lang.String) r3
            java.lang.Object r4 = r1.L$2
            android.database.Cursor r4 = (android.database.Cursor) r4
            java.lang.Object r5 = r1.L$1
            android.database.Cursor r5 = (android.database.Cursor) r5
            java.lang.Object r9 = r1.L$0
            android.content.Context r9 = (android.content.Context) r9
            kotlin.ResultKt.throwOnFailure(r0)     // Catch:{ Exception -> 0x008f }
            r17 = r6
            r6 = r5
            r5 = r4
            r22 = r10
            r10 = r3
            r3 = r14
            r14 = r22
            goto L_0x0129
        L_0x008f:
            r0 = move-exception
            r3 = r14
            goto L_0x01d2
        L_0x0093:
            kotlin.ResultKt.throwOnFailure(r0)
            android.content.ContentResolver r16 = r24.getContentResolver()     // Catch:{ Exception -> 0x01cf }
            android.net.Uri r17 = androidx.tvprovider.media.tv.TvContractCompat.WatchNextPrograms.CONTENT_URI     // Catch:{ Exception -> 0x01cf }
            java.lang.String[] r18 = videolan.org.commontools.TvChannelUtilsKt.getWATCH_NEXT_MAP_PROJECTION()     // Catch:{ Exception -> 0x01cf }
            r20 = 0
            r21 = 0
            r19 = 0
            android.database.Cursor r0 = r16.query(r17, r18, r19, r20, r21)     // Catch:{ Exception -> 0x01cf }
            r3 = r25
            if (r0 == 0) goto L_0x01e3
            r5 = r0
            r9 = r5
            r0 = r24
        L_0x00b2:
            boolean r10 = r5.moveToNext()     // Catch:{ Exception -> 0x01c3 }
            if (r10 == 0) goto L_0x01cb
            androidx.tvprovider.media.tv.WatchNextProgram r10 = androidx.tvprovider.media.tv.WatchNextProgram.fromCursor(r5)     // Catch:{ Exception -> 0x01c3 }
            android.content.ContentValues r10 = r10.toContentValues()     // Catch:{ Exception -> 0x01c3 }
            r11 = 0
            long r11 = r9.getLong(r11)     // Catch:{ Exception -> 0x01c3 }
            java.lang.String r13 = "internal_provider_id"
            java.lang.String r13 = r10.getAsString(r13)     // Catch:{ Exception -> 0x01c3 }
            java.lang.String r14 = "getAsString(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r13, r14)     // Catch:{ Exception -> 0x01c3 }
            long r13 = java.lang.Long.parseLong(r13)     // Catch:{ Exception -> 0x01c3 }
            int r15 = (r13 > r3 ? 1 : (r13 == r3 ? 0 : -1))
            if (r15 != 0) goto L_0x01c5
            java.lang.String r15 = "content_id"
            java.lang.String r10 = r10.getAsString(r15)     // Catch:{ Exception -> 0x01c3 }
            if (r10 == 0) goto L_0x00f9
            java.lang.String r15 = ""
            boolean r15 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r10, (java.lang.Object) r15)     // Catch:{ Exception -> 0x01c3 }
            if (r15 == 0) goto L_0x00f9
            int r0 = videolan.org.commontools.TvChannelUtilsKt.deleteWatchNext(r0, r11)     // Catch:{ Exception -> 0x01c3 }
            if (r0 >= r8) goto L_0x00f1
            android.util.Log.e(r7, r6)     // Catch:{ Exception -> 0x01c3 }
        L_0x00f1:
            r9.close()     // Catch:{ Exception -> 0x01c3 }
            java.lang.Long r0 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r3)     // Catch:{ Exception -> 0x01c3 }
            return r0
        L_0x00f9:
            kotlinx.coroutines.CoroutineDispatcher r15 = kotlinx.coroutines.Dispatchers.getIO()     // Catch:{ Exception -> 0x01c3 }
            kotlin.coroutines.CoroutineContext r15 = (kotlin.coroutines.CoroutineContext) r15     // Catch:{ Exception -> 0x01c3 }
            org.videolan.vlc.util.TvChannelsKt$checkWatchNextId$lambda$12$$inlined$getFromMl$1 r8 = new org.videolan.vlc.util.TvChannelsKt$checkWatchNextId$lambda$12$$inlined$getFromMl$1     // Catch:{ Exception -> 0x01c3 }
            r17 = r6
            r6 = 0
            r8.<init>(r0, r6, r10)     // Catch:{ Exception -> 0x01c3 }
            kotlin.jvm.functions.Function2 r8 = (kotlin.jvm.functions.Function2) r8     // Catch:{ Exception -> 0x01c3 }
            r1.L$0 = r0     // Catch:{ Exception -> 0x01c3 }
            r1.L$1 = r9     // Catch:{ Exception -> 0x01c3 }
            r1.L$2 = r5     // Catch:{ Exception -> 0x01c3 }
            r1.L$3 = r10     // Catch:{ Exception -> 0x01c3 }
            r1.J$0 = r3     // Catch:{ Exception -> 0x01c3 }
            r1.J$1 = r11     // Catch:{ Exception -> 0x01c3 }
            r1.J$2 = r13     // Catch:{ Exception -> 0x01c3 }
            r6 = 1
            r1.label = r6     // Catch:{ Exception -> 0x01c3 }
            java.lang.Object r6 = kotlinx.coroutines.BuildersKt.withContext(r15, r8, r1)     // Catch:{ Exception -> 0x01c3 }
            if (r6 != r2) goto L_0x0121
            return r2
        L_0x0121:
            r14 = r13
            r12 = r11
            r22 = r9
            r9 = r0
            r0 = r6
            r6 = r22
        L_0x0129:
            org.videolan.medialibrary.interfaces.media.MediaWrapper r0 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r0     // Catch:{ Exception -> 0x01c3 }
            if (r0 != 0) goto L_0x0147
            int r0 = videolan.org.commontools.TvChannelUtilsKt.deleteWatchNext(r9, r12)     // Catch:{ Exception -> 0x01c3 }
            r8 = 1
            if (r0 >= r8) goto L_0x0141
            r8 = r17
            android.util.Log.e(r7, r8)     // Catch:{ Exception -> 0x01c3 }
            r6.close()     // Catch:{ Exception -> 0x01c3 }
            java.lang.Long r0 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r3)     // Catch:{ Exception -> 0x01c3 }
            return r0
        L_0x0141:
            r0 = r9
            r9 = r6
            r6 = r17
            goto L_0x00b2
        L_0x0147:
            r8 = r17
            kotlinx.coroutines.CoroutineDispatcher r0 = kotlinx.coroutines.Dispatchers.getIO()     // Catch:{ Exception -> 0x01c3 }
            kotlin.coroutines.CoroutineContext r0 = (kotlin.coroutines.CoroutineContext) r0     // Catch:{ Exception -> 0x01c3 }
            org.videolan.vlc.util.TvChannelsKt$checkWatchNextId$lambda$12$$inlined$getFromMl$2 r5 = new org.videolan.vlc.util.TvChannelsKt$checkWatchNextId$lambda$12$$inlined$getFromMl$2     // Catch:{ Exception -> 0x01c3 }
            r11 = 0
            r5.<init>(r9, r11, r14)     // Catch:{ Exception -> 0x01c3 }
            kotlin.jvm.functions.Function2 r5 = (kotlin.jvm.functions.Function2) r5     // Catch:{ Exception -> 0x01c3 }
            r1.L$0 = r9     // Catch:{ Exception -> 0x01c3 }
            r1.L$1 = r6     // Catch:{ Exception -> 0x01c3 }
            r1.L$2 = r10     // Catch:{ Exception -> 0x01c3 }
            r1.L$3 = r11     // Catch:{ Exception -> 0x01c3 }
            r1.J$0 = r3     // Catch:{ Exception -> 0x01c3 }
            r1.J$1 = r12     // Catch:{ Exception -> 0x01c3 }
            r11 = 2
            r1.label = r11     // Catch:{ Exception -> 0x01c3 }
            java.lang.Object r0 = kotlinx.coroutines.BuildersKt.withContext(r0, r5, r1)     // Catch:{ Exception -> 0x01c3 }
            if (r0 != r2) goto L_0x016d
            return r2
        L_0x016d:
            r14 = r9
            r22 = r3
            r3 = r1
            r1 = r6
            r4 = r12
            r12 = r22
        L_0x0175:
            org.videolan.medialibrary.interfaces.media.MediaWrapper r0 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r0     // Catch:{ Exception -> 0x0066 }
            if (r0 == 0) goto L_0x018a
            android.net.Uri r0 = r0.getUri()     // Catch:{ Exception -> 0x0066 }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x0066 }
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r0, (java.lang.Object) r10)     // Catch:{ Exception -> 0x0066 }
            if (r0 != 0) goto L_0x0188
            goto L_0x018a
        L_0x0188:
            r2 = r12
            goto L_0x01bb
        L_0x018a:
            int r0 = videolan.org.commontools.TvChannelUtilsKt.deleteWatchNext(r14, r4)     // Catch:{ Exception -> 0x0066 }
            r6 = 1
            if (r0 >= r6) goto L_0x0194
            android.util.Log.e(r7, r8)     // Catch:{ Exception -> 0x0066 }
        L_0x0194:
            kotlinx.coroutines.CoroutineDispatcher r0 = kotlinx.coroutines.Dispatchers.getIO()     // Catch:{ Exception -> 0x0066 }
            kotlin.coroutines.CoroutineContext r0 = (kotlin.coroutines.CoroutineContext) r0     // Catch:{ Exception -> 0x0066 }
            org.videolan.vlc.util.TvChannelsKt$checkWatchNextId$lambda$12$$inlined$getFromMl$3 r4 = new org.videolan.vlc.util.TvChannelsKt$checkWatchNextId$lambda$12$$inlined$getFromMl$3     // Catch:{ Exception -> 0x0066 }
            r15 = 0
            r4.<init>(r14, r15, r10)     // Catch:{ Exception -> 0x0066 }
            kotlin.jvm.functions.Function2 r4 = (kotlin.jvm.functions.Function2) r4     // Catch:{ Exception -> 0x0066 }
            r3.L$0 = r1     // Catch:{ Exception -> 0x0066 }
            r3.L$1 = r15     // Catch:{ Exception -> 0x0066 }
            r3.L$2 = r15     // Catch:{ Exception -> 0x0066 }
            r3.J$0 = r12     // Catch:{ Exception -> 0x0066 }
            r10 = 3
            r3.label = r10     // Catch:{ Exception -> 0x0066 }
            java.lang.Object r0 = kotlinx.coroutines.BuildersKt.withContext(r0, r4, r3)     // Catch:{ Exception -> 0x0066 }
            if (r0 != r2) goto L_0x01b4
            return r2
        L_0x01b4:
            r2 = r12
        L_0x01b5:
            org.videolan.medialibrary.interfaces.media.MediaWrapper r0 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r0     // Catch:{ Exception -> 0x003d }
            long r12 = r0.getId()     // Catch:{ Exception -> 0x003d }
        L_0x01bb:
            r1.close()     // Catch:{ Exception -> 0x003d }
            java.lang.Long r0 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r12)     // Catch:{ Exception -> 0x003d }
            return r0
        L_0x01c3:
            r0 = move-exception
            goto L_0x01d2
        L_0x01c5:
            r8 = r6
            r6 = 1
            r6 = r8
            r8 = 1
            goto L_0x00b2
        L_0x01cb:
            r9.close()     // Catch:{ Exception -> 0x01c3 }
            goto L_0x01e3
        L_0x01cf:
            r0 = move-exception
            r3 = r25
        L_0x01d2:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "checkWatchNextList: "
            r1.<init>(r2)
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            android.util.Log.e(r7, r0)
        L_0x01e3:
            java.lang.Long r0 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r3)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.util.TvChannelsKt.checkWatchNextId(android.content.Context, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0072 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0073 A[SYNTHETIC, Splitter:B:20:0x0073] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object artUri(org.videolan.medialibrary.interfaces.media.MediaWrapper r5, kotlin.coroutines.Continuation<? super android.net.Uri> r6) {
        /*
            boolean r0 = r6 instanceof org.videolan.vlc.util.TvChannelsKt$artUri$1
            if (r0 == 0) goto L_0x0014
            r0 = r6
            org.videolan.vlc.util.TvChannelsKt$artUri$1 r0 = (org.videolan.vlc.util.TvChannelsKt$artUri$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L_0x0019
        L_0x0014:
            org.videolan.vlc.util.TvChannelsKt$artUri$1 r0 = new org.videolan.vlc.util.TvChannelsKt$artUri$1
            r0.<init>(r6)
        L_0x0019:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0036
            if (r2 != r3) goto L_0x002e
            java.lang.Object r5 = r0.L$0
            org.videolan.medialibrary.interfaces.media.MediaWrapper r5 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r5
            kotlin.ResultKt.throwOnFailure(r6)
            goto L_0x0058
        L_0x002e:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x0036:
            kotlin.ResultKt.throwOnFailure(r6)
            boolean r6 = r5.isThumbnailGenerated()
            if (r6 != 0) goto L_0x0058
            kotlinx.coroutines.CoroutineDispatcher r6 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r6 = (kotlin.coroutines.CoroutineContext) r6
            org.videolan.vlc.util.TvChannelsKt$artUri$2 r2 = new org.videolan.vlc.util.TvChannelsKt$artUri$2
            r4 = 0
            r2.<init>(r5, r4)
            kotlin.jvm.functions.Function2 r2 = (kotlin.jvm.functions.Function2) r2
            r0.L$0 = r5
            r0.label = r3
            java.lang.Object r6 = kotlinx.coroutines.BuildersKt.withContext(r6, r2, r0)
            if (r6 != r1) goto L_0x0058
            return r1
        L_0x0058:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r0 = "android.resource://org.videolan.vlc/"
            r6.<init>(r0)
            int r0 = org.videolan.vlc.R.drawable.tv_channel_default
            r6.append(r0)
            java.lang.String r6 = r6.toString()
            android.net.Uri r6 = android.net.Uri.parse(r6)
            java.lang.String r5 = r5.getArtworkMrl()
            if (r5 != 0) goto L_0x0073
            return r6
        L_0x0073:
            android.net.Uri r6 = org.videolan.vlc.FileProviderKt.getFileUri(r5)     // Catch:{ IllegalArgumentException -> 0x0077 }
        L_0x0077:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.util.TvChannelsKt.artUri(org.videolan.medialibrary.interfaces.media.MediaWrapper, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
