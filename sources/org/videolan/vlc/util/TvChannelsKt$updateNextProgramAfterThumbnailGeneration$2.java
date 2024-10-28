package org.videolan.vlc.util;

import android.content.Context;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import org.videolan.medialibrary.EventTools;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "media", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: TvChannels.kt */
final class TvChannelsKt$updateNextProgramAfterThumbnailGeneration$2 extends Lambda implements Function1<MediaWrapper, Unit> {
    final /* synthetic */ Context $context;
    final /* synthetic */ LifecycleOwner $lifecycleOwner;
    final /* synthetic */ MediaWrapper $mw;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    TvChannelsKt$updateNextProgramAfterThumbnailGeneration$2(LifecycleOwner lifecycleOwner, Context context, MediaWrapper mediaWrapper) {
        super(1);
        this.$lifecycleOwner = lifecycleOwner;
        this.$context = context;
        this.$mw = mediaWrapper;
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "org.videolan.vlc.util.TvChannelsKt$updateNextProgramAfterThumbnailGeneration$2$1", f = "TvChannels.kt", i = {0, 0, 0, 0}, l = {126}, m = "invokeSuspend", n = {"cursor", "it", "existingProgram", "watchNextProgramId"}, s = {"L$0", "L$4", "L$5", "J$0"})
    /* renamed from: org.videolan.vlc.util.TvChannelsKt$updateNextProgramAfterThumbnailGeneration$2$1  reason: invalid class name */
    /* compiled from: TvChannels.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        long J$0;
        long J$1;
        long J$2;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(context, mediaWrapper2, mediaWrapper, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARNING: Removed duplicated region for block: B:17:0x0085 A[Catch:{ all -> 0x0177 }] */
        /* JADX WARNING: Removed duplicated region for block: B:39:0x0171  */
        /* JADX WARNING: Removed duplicated region for block: B:45:0x017e  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.lang.Object invokeSuspend(java.lang.Object r32) {
            /*
                r31 = this;
                r1 = r31
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r2 = r1.label
                r3 = 1
                if (r2 == 0) goto L_0x0052
                if (r2 != r3) goto L_0x004a
                long r4 = r1.J$2
                long r6 = r1.J$1
                long r8 = r1.J$0
                java.lang.Object r2 = r1.L$7
                java.lang.String r2 = (java.lang.String) r2
                java.lang.Object r10 = r1.L$6
                java.lang.String r10 = (java.lang.String) r10
                java.lang.Object r11 = r1.L$5
                androidx.tvprovider.media.tv.WatchNextProgram r11 = (androidx.tvprovider.media.tv.WatchNextProgram) r11
                java.lang.Object r12 = r1.L$4
                android.database.Cursor r12 = (android.database.Cursor) r12
                java.lang.Object r13 = r1.L$3
                android.content.Context r13 = (android.content.Context) r13
                java.lang.Object r14 = r1.L$2
                org.videolan.medialibrary.interfaces.media.MediaWrapper r14 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r14
                java.lang.Object r15 = r1.L$1
                org.videolan.medialibrary.interfaces.media.MediaWrapper r15 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r15
                java.lang.Object r3 = r1.L$0
                kotlin.jvm.internal.Ref$ObjectRef r3 = (kotlin.jvm.internal.Ref.ObjectRef) r3
                kotlin.ResultKt.throwOnFailure(r32)     // Catch:{ all -> 0x0177 }
                r23 = r12
                r24 = r14
                r25 = r15
                r14 = r8
                r12 = r10
                r9 = r6
                r7 = r4
                r4 = 1
                r5 = r32
            L_0x0043:
                r30 = r11
                r11 = r2
                r2 = r30
                goto L_0x0106
            L_0x004a:
                java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
                java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
                r0.<init>(r2)
                throw r0
            L_0x0052:
                kotlin.ResultKt.throwOnFailure(r32)
                kotlin.jvm.internal.Ref$ObjectRef r3 = new kotlin.jvm.internal.Ref$ObjectRef
                r3.<init>()
                android.content.Context r2 = r3     // Catch:{ all -> 0x0177 }
                android.content.ContentResolver r4 = r2.getContentResolver()     // Catch:{ all -> 0x0177 }
                android.net.Uri r5 = androidx.tvprovider.media.tv.TvContractCompat.WatchNextPrograms.CONTENT_URI     // Catch:{ all -> 0x0177 }
                java.lang.String[] r6 = videolan.org.commontools.TvChannelUtilsKt.getWATCH_NEXT_MAP_PROJECTION()     // Catch:{ all -> 0x0177 }
                r8 = 0
                r9 = 0
                r7 = 0
                android.database.Cursor r2 = r4.query(r5, r6, r7, r8, r9)     // Catch:{ all -> 0x0177 }
                r3.element = r2     // Catch:{ all -> 0x0177 }
                T r2 = r3.element     // Catch:{ all -> 0x0177 }
                android.database.Cursor r2 = (android.database.Cursor) r2     // Catch:{ all -> 0x0177 }
                if (r2 == 0) goto L_0x016b
                org.videolan.medialibrary.interfaces.media.MediaWrapper r4 = r4     // Catch:{ all -> 0x0177 }
                org.videolan.medialibrary.interfaces.media.MediaWrapper r5 = r8     // Catch:{ all -> 0x0177 }
                android.content.Context r6 = r3     // Catch:{ all -> 0x0177 }
                r12 = r2
                r15 = r4
                r14 = r5
                r13 = r6
            L_0x007f:
                boolean r2 = r12.moveToNext()     // Catch:{ all -> 0x0177 }
                if (r2 == 0) goto L_0x016b
                androidx.tvprovider.media.tv.WatchNextProgram r2 = androidx.tvprovider.media.tv.WatchNextProgram.fromCursor(r12)     // Catch:{ all -> 0x0177 }
                T r4 = r3.element     // Catch:{ all -> 0x0177 }
                android.database.Cursor r4 = (android.database.Cursor) r4     // Catch:{ all -> 0x0177 }
                androidx.tvprovider.media.tv.WatchNextProgram r11 = androidx.tvprovider.media.tv.WatchNextProgram.fromCursor(r4)     // Catch:{ all -> 0x0177 }
                T r4 = r3.element     // Catch:{ all -> 0x0177 }
                android.database.Cursor r4 = (android.database.Cursor) r4     // Catch:{ all -> 0x0177 }
                r5 = 0
                long r8 = r4.getLong(r5)     // Catch:{ all -> 0x0177 }
                android.content.ContentValues r2 = r2.toContentValues()     // Catch:{ all -> 0x0177 }
                java.lang.String r4 = "content_id"
                java.lang.String r2 = r2.getAsString(r4)     // Catch:{ all -> 0x0177 }
                kotlin.jvm.internal.Intrinsics.checkNotNull(r2)     // Catch:{ all -> 0x0177 }
                android.net.Uri r2 = android.net.Uri.parse(r2)     // Catch:{ all -> 0x0177 }
                android.net.Uri r4 = r15.getUri()     // Catch:{ all -> 0x0177 }
                boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) r4)     // Catch:{ all -> 0x0177 }
                if (r2 == 0) goto L_0x0166
                android.net.Uri r2 = r15.getUri()     // Catch:{ all -> 0x0177 }
                android.net.Uri r4 = r14.getUri()     // Catch:{ all -> 0x0177 }
                boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) r4)     // Catch:{ all -> 0x0177 }
                if (r2 == 0) goto L_0x0166
                long r6 = r14.getId()     // Catch:{ all -> 0x0177 }
                java.lang.String r2 = r14.getTitle()     // Catch:{ all -> 0x0177 }
                java.lang.String r4 = "getTitle(...)"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r4)     // Catch:{ all -> 0x0177 }
                java.lang.String r10 = r14.getDescription()     // Catch:{ all -> 0x0177 }
                kotlin.jvm.internal.Intrinsics.checkNotNull(r14)     // Catch:{ all -> 0x0177 }
                r1.L$0 = r3     // Catch:{ all -> 0x0177 }
                r1.L$1 = r15     // Catch:{ all -> 0x0177 }
                r1.L$2 = r14     // Catch:{ all -> 0x0177 }
                r1.L$3 = r13     // Catch:{ all -> 0x0177 }
                r1.L$4 = r12     // Catch:{ all -> 0x0177 }
                r1.L$5 = r11     // Catch:{ all -> 0x0177 }
                r1.L$6 = r10     // Catch:{ all -> 0x0177 }
                r1.L$7 = r2     // Catch:{ all -> 0x0177 }
                r1.J$0 = r8     // Catch:{ all -> 0x0177 }
                r1.J$1 = r6     // Catch:{ all -> 0x0177 }
                r4 = 0
                r1.J$2 = r4     // Catch:{ all -> 0x0177 }
                r4 = 1
                r1.label = r4     // Catch:{ all -> 0x0177 }
                java.lang.Object r5 = org.videolan.vlc.util.TvChannelsKt.artUri(r14, r1)     // Catch:{ all -> 0x0177 }
                if (r5 != r0) goto L_0x00f9
                return r0
            L_0x00f9:
                r23 = r12
                r24 = r14
                r25 = r15
                r14 = r8
                r12 = r10
                r9 = r6
                r7 = 0
                goto L_0x0043
            L_0x0106:
                android.net.Uri r5 = (android.net.Uri) r5     // Catch:{ all -> 0x0162 }
                r16 = r5
                long r4 = r24.getLength()     // Catch:{ all -> 0x0162 }
                int r5 = (int) r4     // Catch:{ all -> 0x0162 }
                r4 = r0
                long r0 = r24.getTime()     // Catch:{ all -> 0x0162 }
                int r1 = (int) r0     // Catch:{ all -> 0x0162 }
                int r0 = r24.getWidth()     // Catch:{ all -> 0x0162 }
                int r17 = r24.getHeight()     // Catch:{ all -> 0x0162 }
                java.lang.String r18 = "org.videolan.vlc"
                android.net.Uri r6 = r24.getUri()     // Catch:{ all -> 0x0162 }
                java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x0162 }
                r26 = r3
                java.lang.String r3 = "toString(...)"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r3)     // Catch:{ all -> 0x015e }
                videolan.org.commontools.ProgramDesc r3 = new videolan.org.commontools.ProgramDesc     // Catch:{ all -> 0x015e }
                r20 = 0
                r21 = 2048(0x800, float:2.87E-42)
                r22 = 0
                r19 = r6
                r6 = r3
                r27 = r4
                r4 = r13
                r13 = r16
                r28 = r14
                r14 = r5
                r15 = r1
                r16 = r0
                r6.<init>(r7, r9, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22)     // Catch:{ all -> 0x015e }
                kotlin.jvm.internal.Intrinsics.checkNotNull(r2)     // Catch:{ all -> 0x015e }
                r8 = r28
                videolan.org.commontools.TvChannelUtilsKt.updateWatchNext(r4, r2, r3, r8)     // Catch:{ all -> 0x015e }
                r1 = r31
                r13 = r4
                r12 = r23
                r14 = r24
                r15 = r25
                r3 = r26
            L_0x015a:
                r0 = r27
                goto L_0x007f
            L_0x015e:
                r0 = move-exception
                r3 = r26
                goto L_0x0178
            L_0x0162:
                r0 = move-exception
                r26 = r3
                goto L_0x0178
            L_0x0166:
                r27 = r0
                r1 = r31
                goto L_0x015a
            L_0x016b:
                T r0 = r3.element
                android.database.Cursor r0 = (android.database.Cursor) r0
                if (r0 == 0) goto L_0x0174
                r0.close()
            L_0x0174:
                kotlin.Unit r0 = kotlin.Unit.INSTANCE
                return r0
            L_0x0177:
                r0 = move-exception
            L_0x0178:
                T r1 = r3.element
                android.database.Cursor r1 = (android.database.Cursor) r1
                if (r1 == 0) goto L_0x0181
                r1.close()
            L_0x0181:
                goto L_0x0183
            L_0x0182:
                throw r0
            L_0x0183:
                goto L_0x0182
            */
            throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.util.TvChannelsKt$updateNextProgramAfterThumbnailGeneration$2.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((MediaWrapper) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(final MediaWrapper mediaWrapper) {
        final Context context = this.$context;
        final MediaWrapper mediaWrapper2 = this.$mw;
        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this.$lifecycleOwner), Dispatchers.getIO(), (CoroutineStart) null, new AnonymousClass1((Continuation<? super AnonymousClass1>) null), 2, (Object) null);
        EventTools.getInstance().lastThumb.removeObservers(this.$lifecycleOwner);
    }
}
