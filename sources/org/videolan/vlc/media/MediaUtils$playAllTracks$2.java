package org.videolan.vlc.media;

import android.content.Context;
import java.security.SecureRandom;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.vlc.PlaybackService;
import org.videolan.vlc.media.MediaUtils;
import org.videolan.vlc.providers.medialibrary.FoldersProvider;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.media.MediaUtils$playAllTracks$2", f = "MediaUtils.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: MediaUtils.kt */
final class MediaUtils$playAllTracks$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $context;
    final /* synthetic */ int $position;
    final /* synthetic */ FoldersProvider $provider;
    final /* synthetic */ boolean $shuffle;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MediaUtils$playAllTracks$2(Context context, FoldersProvider foldersProvider, boolean z, int i, Continuation<? super MediaUtils$playAllTracks$2> continuation) {
        super(2, continuation);
        this.$context = context;
        this.$provider = foldersProvider;
        this.$shuffle = z;
        this.$position = i;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MediaUtils$playAllTracks$2(this.$context, this.$provider, this.$shuffle, this.$position, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MediaUtils$playAllTracks$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H@"}, d2 = {"<anonymous>", "", "service", "Lorg/videolan/vlc/PlaybackService;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "org.videolan.vlc.media.MediaUtils$playAllTracks$2$1", f = "MediaUtils.kt", i = {0, 2, 2, 2, 2}, l = {236, 243, 252}, m = "invokeSuspend", n = {"service", "service", "index", "count", "pageCount"}, s = {"L$0", "L$0", "L$1", "I$0", "I$1"})
    /* renamed from: org.videolan.vlc.media.MediaUtils$playAllTracks$2$1  reason: invalid class name */
    /* compiled from: MediaUtils.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<PlaybackService, Continuation<? super Unit>, Object> {
        int I$0;
        int I$1;
        /* synthetic */ Object L$0;
        Object L$1;
        boolean Z$0;
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 r0 = new AnonymousClass1(foldersProvider, z, i, continuation);
            r0.L$0 = obj;
            return r0;
        }

        public final Object invoke(PlaybackService playbackService, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(playbackService, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARNING: Removed duplicated region for block: B:29:0x00b8  */
        /* JADX WARNING: Removed duplicated region for block: B:34:0x00eb  */
        /* JADX WARNING: Removed duplicated region for block: B:35:0x00f3  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.lang.Object invokeSuspend(java.lang.Object r12) {
            /*
                r11 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r11.label
                r2 = 3
                r3 = 2
                r4 = 1
                r5 = 0
                if (r1 == 0) goto L_0x0042
                if (r1 == r4) goto L_0x003a
                if (r1 == r3) goto L_0x002b
                if (r1 != r2) goto L_0x0023
                int r1 = r11.I$1
                int r4 = r11.I$0
                java.lang.Object r6 = r11.L$1
                kotlin.jvm.internal.Ref$IntRef r6 = (kotlin.jvm.internal.Ref.IntRef) r6
                java.lang.Object r7 = r11.L$0
                org.videolan.vlc.PlaybackService r7 = (org.videolan.vlc.PlaybackService) r7
                kotlin.ResultKt.throwOnFailure(r12)
                goto L_0x00e5
            L_0x0023:
                java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r12.<init>(r0)
                throw r12
            L_0x002b:
                int r0 = r11.I$1
                int r1 = r11.I$0
                boolean r2 = r11.Z$0
                java.lang.Object r3 = r11.L$0
                org.videolan.vlc.PlaybackService r3 = (org.videolan.vlc.PlaybackService) r3
                kotlin.ResultKt.throwOnFailure(r12)
                goto L_0x00a6
            L_0x003a:
                java.lang.Object r1 = r11.L$0
                org.videolan.vlc.PlaybackService r1 = (org.videolan.vlc.PlaybackService) r1
                kotlin.ResultKt.throwOnFailure(r12)
                goto L_0x0069
            L_0x0042:
                kotlin.ResultKt.throwOnFailure(r12)
                java.lang.Object r12 = r11.L$0
                org.videolan.vlc.PlaybackService r12 = (org.videolan.vlc.PlaybackService) r12
                kotlinx.coroutines.CoroutineDispatcher r1 = kotlinx.coroutines.Dispatchers.getIO()
                kotlin.coroutines.CoroutineContext r1 = (kotlin.coroutines.CoroutineContext) r1
                org.videolan.vlc.media.MediaUtils$playAllTracks$2$1$count$1 r6 = new org.videolan.vlc.media.MediaUtils$playAllTracks$2$1$count$1
                org.videolan.vlc.providers.medialibrary.FoldersProvider r7 = r2
                r6.<init>(r7, r5)
                kotlin.jvm.functions.Function2 r6 = (kotlin.jvm.functions.Function2) r6
                r7 = r11
                kotlin.coroutines.Continuation r7 = (kotlin.coroutines.Continuation) r7
                r11.L$0 = r12
                r11.label = r4
                java.lang.Object r1 = kotlinx.coroutines.BuildersKt.withContext(r1, r6, r7)
                if (r1 != r0) goto L_0x0066
                return r0
            L_0x0066:
                r10 = r1
                r1 = r12
                r12 = r10
            L_0x0069:
                java.lang.Number r12 = (java.lang.Number) r12
                int r12 = r12.intValue()
                if (r12 != 0) goto L_0x0074
                kotlin.Unit r12 = kotlin.Unit.INSTANCE
                return r12
            L_0x0074:
                if (r4 > r12) goto L_0x00ac
                r4 = 501(0x1f5, float:7.02E-43)
                if (r12 >= r4) goto L_0x00ac
                boolean r2 = r3
                int r4 = r4
                kotlinx.coroutines.CoroutineDispatcher r6 = kotlinx.coroutines.Dispatchers.getIO()
                kotlin.coroutines.CoroutineContext r6 = (kotlin.coroutines.CoroutineContext) r6
                org.videolan.vlc.media.MediaUtils$playAllTracks$2$1$1 r7 = new org.videolan.vlc.media.MediaUtils$playAllTracks$2$1$1
                org.videolan.vlc.providers.medialibrary.FoldersProvider r8 = r2
                r7.<init>(r8, r5)
                kotlin.jvm.functions.Function2 r7 = (kotlin.jvm.functions.Function2) r7
                r5 = r11
                kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
                r11.L$0 = r1
                r11.Z$0 = r2
                r11.I$0 = r12
                r11.I$1 = r4
                r11.label = r3
                java.lang.Object r3 = kotlinx.coroutines.BuildersKt.withContext(r6, r7, r5)
                if (r3 != r0) goto L_0x00a1
                return r0
            L_0x00a1:
                r0 = r4
                r10 = r1
                r1 = r12
                r12 = r3
                r3 = r10
            L_0x00a6:
                java.util.List r12 = (java.util.List) r12
                invokeSuspend$play(r3, r2, r1, r0, r12)
                goto L_0x00fd
            L_0x00ac:
                kotlin.jvm.internal.Ref$IntRef r4 = new kotlin.jvm.internal.Ref$IntRef
                r4.<init>()
                r7 = r1
                r6 = r4
                r4 = r12
            L_0x00b4:
                int r12 = r6.element
                if (r12 >= r4) goto L_0x00fd
                int r12 = r6.element
                int r12 = r4 - r12
                r1 = 500(0x1f4, float:7.0E-43)
                int r1 = java.lang.Math.min(r1, r12)
                kotlinx.coroutines.CoroutineDispatcher r12 = kotlinx.coroutines.Dispatchers.getIO()
                kotlin.coroutines.CoroutineContext r12 = (kotlin.coroutines.CoroutineContext) r12
                org.videolan.vlc.media.MediaUtils$playAllTracks$2$1$list$1 r8 = new org.videolan.vlc.media.MediaUtils$playAllTracks$2$1$list$1
                org.videolan.vlc.providers.medialibrary.FoldersProvider r9 = r2
                r8.<init>(r9, r1, r6, r5)
                kotlin.jvm.functions.Function2 r8 = (kotlin.jvm.functions.Function2) r8
                r9 = r11
                kotlin.coroutines.Continuation r9 = (kotlin.coroutines.Continuation) r9
                r11.L$0 = r7
                r11.L$1 = r6
                r11.I$0 = r4
                r11.I$1 = r1
                r11.label = r2
                java.lang.Object r12 = kotlinx.coroutines.BuildersKt.withContext(r12, r8, r9)
                if (r12 != r0) goto L_0x00e5
                return r0
            L_0x00e5:
                java.util.List r12 = (java.util.List) r12
                int r8 = r6.element
                if (r8 != 0) goto L_0x00f3
                boolean r8 = r3
                int r9 = r4
                invokeSuspend$play(r7, r8, r4, r9, r12)
                goto L_0x00f7
            L_0x00f3:
                r8 = 0
                org.videolan.vlc.PlaybackService.append$default((org.videolan.vlc.PlaybackService) r7, (java.util.List) r12, (int) r8, (int) r3, (java.lang.Object) r5)
            L_0x00f7:
                int r12 = r6.element
                int r12 = r12 + r1
                r6.element = r12
                goto L_0x00b4
            L_0x00fd:
                kotlin.Unit r12 = kotlin.Unit.INSTANCE
                return r12
            */
            throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.media.MediaUtils$playAllTracks$2.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        private static final void invokeSuspend$play(PlaybackService playbackService, boolean z, int i, int i2, List<? extends MediaWrapper> list) {
            if (z) {
                i2 = new SecureRandom().nextInt(Math.min(i, 500));
            }
            playbackService.load(list, i2);
            if (z && !playbackService.isShuffling()) {
                playbackService.shuffle();
            }
        }
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            Context context = this.$context;
            final FoldersProvider foldersProvider = this.$provider;
            final boolean z = this.$shuffle;
            final int i = this.$position;
            new MediaUtils.SuspendDialogCallback(context, new AnonymousClass1((Continuation<? super AnonymousClass1>) null));
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
