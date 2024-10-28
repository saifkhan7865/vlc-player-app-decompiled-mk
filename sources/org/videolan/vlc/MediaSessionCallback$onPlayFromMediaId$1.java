package org.videolan.vlc;

import android.os.Bundle;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.MediaSessionCallback$onPlayFromMediaId$1", f = "MediaSessionCallback.kt", i = {0, 1, 1, 2, 2, 3, 3, 4, 4, 4, 5, 5, 6, 6, 7, 8, 9, 10}, l = {399, 401, 403, 405, 407, 409, 411, 414, 416, 418, 420}, m = "invokeSuspend", n = {"$this$launch", "$this$launch", "position", "$this$launch", "position", "$this$launch", "position", "$this$launch", "position", "pageOffset", "$this$launch", "position", "$this$launch", "position", "$this$launch", "$this$launch", "$this$launch", "$this$launch"}, s = {"L$0", "L$0", "I$0", "L$0", "I$0", "L$0", "I$0", "L$0", "I$0", "I$1", "L$0", "I$0", "L$0", "I$0", "L$0", "L$0", "L$0", "L$0"})
/* compiled from: MediaSessionCallback.kt */
final class MediaSessionCallback$onPlayFromMediaId$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Bundle $extras;
    final /* synthetic */ String $mediaId;
    int I$0;
    int I$1;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ MediaSessionCallback this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MediaSessionCallback$onPlayFromMediaId$1(MediaSessionCallback mediaSessionCallback, Bundle bundle, String str, Continuation<? super MediaSessionCallback$onPlayFromMediaId$1> continuation) {
        super(2, continuation);
        this.this$0 = mediaSessionCallback;
        this.$extras = bundle;
        this.$mediaId = str;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        MediaSessionCallback$onPlayFromMediaId$1 mediaSessionCallback$onPlayFromMediaId$1 = new MediaSessionCallback$onPlayFromMediaId$1(this.this$0, this.$extras, this.$mediaId, continuation);
        mediaSessionCallback$onPlayFromMediaId$1.L$0 = obj;
        return mediaSessionCallback$onPlayFromMediaId$1;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MediaSessionCallback$onPlayFromMediaId$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:101:0x01c6, code lost:
        r13 = (java.util.List) r13;
        r0 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x01cb, code lost:
        if (r0 == null) goto L_0x04da;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x01d1, code lost:
        if (r0.isEmpty() == false) goto L_0x01d5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x01d9, code lost:
        if (kotlinx.coroutines.CoroutineScopeKt.isActive(r1) == false) goto L_0x04da;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x01db, code lost:
        org.videolan.vlc.MediaSessionCallback.loadMedia$default(r12.this$0, r13.subList(0, kotlin.ranges.RangesKt.coerceAtMost(r13.size(), 100)), r6, false, 4, (java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x021e, code lost:
        r13 = (org.videolan.medialibrary.interfaces.media.MediaWrapper[]) r13;
        kotlin.jvm.internal.Intrinsics.checkNotNull(r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:117:0x0224, code lost:
        if (r13.length != 0) goto L_0x0227;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x0226, code lost:
        r3 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x0229, code lost:
        if ((!r3) == false) goto L_0x04da;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x022f, code lost:
        if (kotlinx.coroutines.CoroutineScopeKt.isActive(r1) == false) goto L_0x04da;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:123:0x0231, code lost:
        org.videolan.vlc.MediaSessionCallback.loadMedia$default(r12.this$0, kotlin.collections.ArraysKt.toList((T[]) r13), r7, false, 4, (java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:135:0x027e, code lost:
        r13 = (org.videolan.medialibrary.interfaces.media.MediaWrapper[]) r13;
        kotlin.jvm.internal.Intrinsics.checkNotNull(r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:136:0x0284, code lost:
        if (r13.length != 0) goto L_0x0287;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:137:0x0286, code lost:
        r3 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:139:0x0289, code lost:
        if ((!r3) == false) goto L_0x02cd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:141:0x028f, code lost:
        if (kotlinx.coroutines.CoroutineScopeKt.isActive(r0) == false) goto L_0x02cd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:142:0x0291, code lost:
        kotlin.collections.ArraysKt.sortWith(r13, org.videolan.vlc.gui.helpers.MediaComparators.INSTANCE.getANDROID_AUTO());
        org.videolan.vlc.MediaSessionCallback.loadMedia$default(r12.this$0, kotlin.collections.ArraysKt.toList((T[]) r13), new java.security.SecureRandom().nextInt(java.lang.Math.min(r13.length, 500)), false, 4, (java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:143:0x02c0, code lost:
        if (r12.this$0.playbackService.isShuffling() != false) goto L_0x04da;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:144:0x02c2, code lost:
        r12.this$0.playbackService.shuffle();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:145:0x02cd, code lost:
        r12.this$0.playbackService.displayPlaybackError(org.videolan.vlc.R.string.search_no_result);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:153:0x0307, code lost:
        r13 = (org.videolan.medialibrary.interfaces.media.MediaWrapper[]) r13;
        kotlin.jvm.internal.Intrinsics.checkNotNull(r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:154:0x030d, code lost:
        if (r13.length != 0) goto L_0x0310;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:155:0x030f, code lost:
        r3 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:157:0x0311, code lost:
        if ((!r3) == false) goto L_0x04da;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:159:0x0317, code lost:
        if (kotlinx.coroutines.CoroutineScopeKt.isActive(r2) == false) goto L_0x04da;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:160:0x0319, code lost:
        kotlin.collections.ArraysKt.sortWith(r13, org.videolan.vlc.gui.helpers.MediaComparators.INSTANCE.getANDROID_AUTO());
        org.videolan.vlc.MediaSessionCallback.loadMedia$default(r12.this$0, kotlin.collections.ArraysKt.toList((T[]) r13), r0 + r1, false, 4, (java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:161:0x0332, code lost:
        r3 = android.content.ContentUris.parseId(r5);
        r5 = java.lang.String.valueOf(org.videolan.tools.KotlinExtensionsKt.retrieveParent(r5));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:162:0x0342, code lost:
        switch(r5.hashCode()) {
            case -1979024705: goto L_0x044b;
            case -1979024700: goto L_0x040a;
            case -1979024694: goto L_0x03c9;
            case -664866953: goto L_0x0388;
            case 830303327: goto L_0x0347;
            default: goto L_0x0345;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:165:0x034d, code lost:
        if (r5.equals(org.videolan.vlc.media.MediaSessionBrowser.ID_MEDIA) == false) goto L_0x048d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:166:0x034f, code lost:
        r12.L$0 = r13;
        r12.label = 11;
        r0 = kotlinx.coroutines.BuildersKt.withContext(kotlinx.coroutines.Dispatchers.getIO(), new org.videolan.vlc.MediaSessionCallback$onPlayFromMediaId$1$invokeSuspend$$inlined$getFromMl$11(r2, (kotlin.coroutines.Continuation) null, r3), r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:167:0x0369, code lost:
        if (r0 != r1) goto L_0x036c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:168:0x036b, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:169:0x036c, code lost:
        r11 = r0;
        r0 = r13;
        r13 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:170:0x036f, code lost:
        r13 = (org.videolan.medialibrary.interfaces.media.MediaWrapper[]) r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:171:0x0375, code lost:
        if (kotlinx.coroutines.CoroutineScopeKt.isActive(r0) == false) goto L_0x04da;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:172:0x0377, code lost:
        if (r13 == null) goto L_0x04da;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:173:0x0379, code lost:
        org.videolan.vlc.MediaSessionCallback.loadMedia$default(r12.this$0, kotlin.collections.ArraysKt.toList((T[]) r13), 0, false, 6, (java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:175:0x038e, code lost:
        if (r5.equals(org.videolan.vlc.media.MediaSessionBrowser.ID_PLAYLIST) == false) goto L_0x048d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:176:0x0390, code lost:
        r12.L$0 = r13;
        r12.label = 10;
        r0 = kotlinx.coroutines.BuildersKt.withContext(kotlinx.coroutines.Dispatchers.getIO(), new org.videolan.vlc.MediaSessionCallback$onPlayFromMediaId$1$invokeSuspend$$inlined$getFromMl$10(r2, (kotlin.coroutines.Continuation) null, r3), r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:177:0x03aa, code lost:
        if (r0 != r1) goto L_0x03ad;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:178:0x03ac, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:179:0x03ad, code lost:
        r11 = r0;
        r0 = r13;
        r13 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:180:0x03b0, code lost:
        r13 = (org.videolan.medialibrary.interfaces.media.MediaWrapper[]) r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:181:0x03b6, code lost:
        if (kotlinx.coroutines.CoroutineScopeKt.isActive(r0) == false) goto L_0x04da;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:182:0x03b8, code lost:
        if (r13 == null) goto L_0x04da;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:183:0x03ba, code lost:
        org.videolan.vlc.MediaSessionCallback.loadMedia$default(r12.this$0, kotlin.collections.ArraysKt.toList((T[]) r13), 0, true, 2, (java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:185:0x03cf, code lost:
        if (r5.equals(org.videolan.vlc.media.MediaSessionBrowser.ID_ARTIST) == false) goto L_0x048d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:186:0x03d1, code lost:
        r12.L$0 = r13;
        r12.label = 8;
        r0 = kotlinx.coroutines.BuildersKt.withContext(kotlinx.coroutines.Dispatchers.getIO(), new org.videolan.vlc.MediaSessionCallback$onPlayFromMediaId$1$invokeSuspend$$inlined$getFromMl$8(r2, (kotlin.coroutines.Continuation) null, r3), r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:187:0x03eb, code lost:
        if (r0 != r1) goto L_0x03ee;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:188:0x03ed, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:189:0x03ee, code lost:
        r11 = r0;
        r0 = r13;
        r13 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:190:0x03f1, code lost:
        r13 = (org.videolan.medialibrary.interfaces.media.MediaWrapper[]) r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:191:0x03f7, code lost:
        if (kotlinx.coroutines.CoroutineScopeKt.isActive(r0) == false) goto L_0x04da;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:192:0x03f9, code lost:
        if (r13 == null) goto L_0x04da;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:193:0x03fb, code lost:
        org.videolan.vlc.MediaSessionCallback.loadMedia$default(r12.this$0, kotlin.collections.ArraysKt.toList((T[]) r13), 0, true, 2, (java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:195:0x0410, code lost:
        if (r5.equals(org.videolan.vlc.media.MediaSessionBrowser.ID_ALBUM) == false) goto L_0x048d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:196:0x0412, code lost:
        r12.L$0 = r13;
        r12.I$0 = r6;
        r12.label = 7;
        r0 = kotlinx.coroutines.BuildersKt.withContext(kotlinx.coroutines.Dispatchers.getIO(), new org.videolan.vlc.MediaSessionCallback$onPlayFromMediaId$1$invokeSuspend$$inlined$getFromMl$7(r2, (kotlin.coroutines.Continuation) null, r3), r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:197:0x042d, code lost:
        if (r0 != r1) goto L_0x0430;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:198:0x042f, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:199:0x0430, code lost:
        r1 = r13;
        r13 = r0;
        r2 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:200:0x0433, code lost:
        r13 = (org.videolan.medialibrary.interfaces.media.MediaWrapper[]) r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:201:0x0439, code lost:
        if (kotlinx.coroutines.CoroutineScopeKt.isActive(r1) == false) goto L_0x04da;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:202:0x043b, code lost:
        if (r13 == null) goto L_0x04da;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:203:0x043d, code lost:
        org.videolan.vlc.MediaSessionCallback.loadMedia$default(r12.this$0, kotlin.collections.ArraysKt.toList((T[]) r13), r2, false, 4, (java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:205:0x0451, code lost:
        if (r5.equals(org.videolan.vlc.media.MediaSessionBrowser.ID_GENRE) == false) goto L_0x048d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:206:0x0453, code lost:
        r12.L$0 = r13;
        r12.label = 9;
        r0 = kotlinx.coroutines.BuildersKt.withContext(kotlinx.coroutines.Dispatchers.getIO(), new org.videolan.vlc.MediaSessionCallback$onPlayFromMediaId$1$invokeSuspend$$inlined$getFromMl$9(r2, (kotlin.coroutines.Continuation) null, r3), r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:207:0x046d, code lost:
        if (r0 != r1) goto L_0x0470;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:208:0x046f, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:209:0x0470, code lost:
        r11 = r0;
        r0 = r13;
        r13 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:210:0x0473, code lost:
        r13 = (java.util.List) r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:211:0x0479, code lost:
        if (kotlinx.coroutines.CoroutineScopeKt.isActive(r0) == false) goto L_0x04da;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:212:0x047b, code lost:
        if (r13 == null) goto L_0x04da;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:213:0x047d, code lost:
        org.videolan.vlc.MediaSessionCallback.loadMedia$default(r12.this$0, kotlin.collections.CollectionsKt.toList(r13), 0, true, 2, (java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:215:0x04a0, code lost:
        throw new java.lang.IllegalStateException("Failed to load: " + r12.$mediaId);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:222:0x04dc, code lost:
        return kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0120, code lost:
        r13 = (org.videolan.medialibrary.interfaces.media.MediaWrapper[]) r13;
        kotlin.jvm.internal.Intrinsics.checkNotNull(r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x0126, code lost:
        if (r13.length != 0) goto L_0x0129;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x0128, code lost:
        r3 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x012b, code lost:
        if ((!r3) == false) goto L_0x04da;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x0131, code lost:
        if (kotlinx.coroutines.CoroutineScopeKt.isActive(r1) == false) goto L_0x04da;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x0133, code lost:
        kotlin.collections.ArraysKt.sortWith(r13, org.videolan.vlc.gui.helpers.MediaComparators.INSTANCE.getANDROID_AUTO());
        org.videolan.vlc.MediaSessionCallback.loadMedia$default(r12.this$0, kotlin.collections.ArraysKt.toList((T[]) r13), r7, false, 4, (java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x017f, code lost:
        r6 = (java.util.List) r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x018a, code lost:
        if ((!r6.isEmpty()) == false) goto L_0x04da;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x0190, code lost:
        if (kotlinx.coroutines.CoroutineScopeKt.isActive(r1) == false) goto L_0x04da;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x0192, code lost:
        org.videolan.vlc.MediaSessionCallback.loadMedia$default(r12.this$0, r6, r7, false, 4, (java.lang.Object) null);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r13) {
        /*
            r12 = this;
            java.lang.String r0 = "Failed to load: "
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r12.label
            r3 = 0
            r4 = 1
            switch(r2) {
                case 0: goto L_0x008b;
                case 1: goto L_0x0082;
                case 2: goto L_0x0076;
                case 3: goto L_0x006a;
                case 4: goto L_0x005e;
                case 5: goto L_0x0051;
                case 6: goto L_0x0045;
                case 7: goto L_0x0039;
                case 8: goto L_0x0030;
                case 9: goto L_0x0027;
                case 10: goto L_0x001e;
                case 11: goto L_0x0015;
                default: goto L_0x000d;
            }
        L_0x000d:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r0)
            throw r13
        L_0x0015:
            java.lang.Object r0 = r12.L$0
            kotlinx.coroutines.CoroutineScope r0 = (kotlinx.coroutines.CoroutineScope) r0
            kotlin.ResultKt.throwOnFailure(r13)     // Catch:{ Exception -> 0x04a1 }
            goto L_0x036f
        L_0x001e:
            java.lang.Object r0 = r12.L$0
            kotlinx.coroutines.CoroutineScope r0 = (kotlinx.coroutines.CoroutineScope) r0
            kotlin.ResultKt.throwOnFailure(r13)     // Catch:{ Exception -> 0x04a1 }
            goto L_0x03b0
        L_0x0027:
            java.lang.Object r0 = r12.L$0
            kotlinx.coroutines.CoroutineScope r0 = (kotlinx.coroutines.CoroutineScope) r0
            kotlin.ResultKt.throwOnFailure(r13)     // Catch:{ Exception -> 0x04a1 }
            goto L_0x0473
        L_0x0030:
            java.lang.Object r0 = r12.L$0
            kotlinx.coroutines.CoroutineScope r0 = (kotlinx.coroutines.CoroutineScope) r0
            kotlin.ResultKt.throwOnFailure(r13)     // Catch:{ Exception -> 0x04a1 }
            goto L_0x03f1
        L_0x0039:
            int r0 = r12.I$0
            java.lang.Object r1 = r12.L$0
            kotlinx.coroutines.CoroutineScope r1 = (kotlinx.coroutines.CoroutineScope) r1
            kotlin.ResultKt.throwOnFailure(r13)     // Catch:{ Exception -> 0x04a1 }
            r2 = r0
            goto L_0x0433
        L_0x0045:
            int r0 = r12.I$0
            java.lang.Object r1 = r12.L$0
            kotlinx.coroutines.CoroutineScope r1 = (kotlinx.coroutines.CoroutineScope) r1
            kotlin.ResultKt.throwOnFailure(r13)     // Catch:{ Exception -> 0x04a1 }
            r7 = r0
            goto L_0x017f
        L_0x0051:
            int r0 = r12.I$1
            int r1 = r12.I$0
            java.lang.Object r2 = r12.L$0
            kotlinx.coroutines.CoroutineScope r2 = (kotlinx.coroutines.CoroutineScope) r2
            kotlin.ResultKt.throwOnFailure(r13)     // Catch:{ Exception -> 0x04a1 }
            goto L_0x0307
        L_0x005e:
            int r0 = r12.I$0
            java.lang.Object r1 = r12.L$0
            kotlinx.coroutines.CoroutineScope r1 = (kotlinx.coroutines.CoroutineScope) r1
            kotlin.ResultKt.throwOnFailure(r13)     // Catch:{ Exception -> 0x04a1 }
            r7 = r0
            goto L_0x0120
        L_0x006a:
            int r0 = r12.I$0
            java.lang.Object r1 = r12.L$0
            kotlinx.coroutines.CoroutineScope r1 = (kotlinx.coroutines.CoroutineScope) r1
            kotlin.ResultKt.throwOnFailure(r13)     // Catch:{ Exception -> 0x04a1 }
            r6 = r0
            goto L_0x01c6
        L_0x0076:
            int r0 = r12.I$0
            java.lang.Object r1 = r12.L$0
            kotlinx.coroutines.CoroutineScope r1 = (kotlinx.coroutines.CoroutineScope) r1
            kotlin.ResultKt.throwOnFailure(r13)     // Catch:{ Exception -> 0x04a1 }
            r7 = r0
            goto L_0x021e
        L_0x0082:
            java.lang.Object r0 = r12.L$0
            kotlinx.coroutines.CoroutineScope r0 = (kotlinx.coroutines.CoroutineScope) r0
            kotlin.ResultKt.throwOnFailure(r13)     // Catch:{ Exception -> 0x04a1 }
            goto L_0x027e
        L_0x008b:
            kotlin.ResultKt.throwOnFailure(r13)
            java.lang.Object r13 = r12.L$0
            kotlinx.coroutines.CoroutineScope r13 = (kotlinx.coroutines.CoroutineScope) r13
            org.videolan.vlc.MediaSessionCallback r2 = r12.this$0
            org.videolan.vlc.PlaybackService r2 = r2.playbackService
            android.content.Context r2 = r2.getApplicationContext()
            android.os.Bundle r5 = r12.$extras     // Catch:{ Exception -> 0x04a1 }
            if (r5 == 0) goto L_0x00a8
            java.lang.String r6 = org.videolan.resources.Constants.EXTRA_RELATIVE_MEDIA_ID     // Catch:{ Exception -> 0x04a1 }
            java.lang.String r5 = r5.getString(r6)     // Catch:{ Exception -> 0x04a1 }
            if (r5 != 0) goto L_0x00aa
        L_0x00a8:
            java.lang.String r5 = r12.$mediaId     // Catch:{ Exception -> 0x04a1 }
        L_0x00aa:
            android.net.Uri r5 = android.net.Uri.parse(r5)     // Catch:{ Exception -> 0x04a1 }
            java.lang.String r6 = "i"
            java.lang.String r6 = r5.getQueryParameter(r6)     // Catch:{ Exception -> 0x04a1 }
            if (r6 == 0) goto L_0x00bb
            int r6 = java.lang.Integer.parseInt(r6)     // Catch:{ Exception -> 0x04a1 }
            goto L_0x00bc
        L_0x00bb:
            r6 = 0
        L_0x00bc:
            java.lang.String r7 = "p"
            java.lang.String r7 = r5.getQueryParameter(r7)     // Catch:{ Exception -> 0x04a1 }
            if (r7 == 0) goto L_0x00cb
            int r7 = java.lang.Integer.parseInt(r7)     // Catch:{ Exception -> 0x04a1 }
            int r7 = r7 * 800
            goto L_0x00cc
        L_0x00cb:
            r7 = 0
        L_0x00cc:
            android.net.Uri r8 = org.videolan.tools.KotlinExtensionsKt.removeQuery(r5)     // Catch:{ Exception -> 0x04a1 }
            java.lang.String r8 = java.lang.String.valueOf(r8)     // Catch:{ Exception -> 0x04a1 }
            int r9 = r8.hashCode()     // Catch:{ Exception -> 0x04a1 }
            r10 = 0
            switch(r9) {
                case -1979024692: goto L_0x02da;
                case -1523728688: goto L_0x0256;
                case -1503610914: goto L_0x023f;
                case -1122674686: goto L_0x01f3;
                case 10476169: goto L_0x019c;
                case 141293709: goto L_0x014a;
                case 155640421: goto L_0x00f5;
                case 1198773592: goto L_0x00de;
                default: goto L_0x00dc;
            }     // Catch:{ Exception -> 0x04a1 }
        L_0x00dc:
            goto L_0x0332
        L_0x00de:
            java.lang.String r3 = "//org.videolan.vlc/r/error/media"
            boolean r3 = r8.equals(r3)     // Catch:{ Exception -> 0x04a1 }
            if (r3 != 0) goto L_0x00e8
            goto L_0x0332
        L_0x00e8:
            org.videolan.vlc.MediaSessionCallback r13 = r12.this$0     // Catch:{ Exception -> 0x04a1 }
            org.videolan.vlc.PlaybackService r13 = r13.playbackService     // Catch:{ Exception -> 0x04a1 }
            int r0 = org.videolan.vlc.R.string.search_no_result     // Catch:{ Exception -> 0x04a1 }
            r13.displayPlaybackError(r0)     // Catch:{ Exception -> 0x04a1 }
            goto L_0x04da
        L_0x00f5:
            java.lang.String r7 = "//org.videolan.vlc/r/stream"
            boolean r7 = r8.equals(r7)     // Catch:{ Exception -> 0x04a1 }
            if (r7 != 0) goto L_0x00ff
            goto L_0x0332
        L_0x00ff:
            kotlinx.coroutines.CoroutineDispatcher r0 = kotlinx.coroutines.Dispatchers.getIO()     // Catch:{ Exception -> 0x04a1 }
            kotlin.coroutines.CoroutineContext r0 = (kotlin.coroutines.CoroutineContext) r0     // Catch:{ Exception -> 0x04a1 }
            org.videolan.vlc.MediaSessionCallback$onPlayFromMediaId$1$invokeSuspend$$inlined$getFromMl$4 r5 = new org.videolan.vlc.MediaSessionCallback$onPlayFromMediaId$1$invokeSuspend$$inlined$getFromMl$4     // Catch:{ Exception -> 0x04a1 }
            r5.<init>(r2, r10)     // Catch:{ Exception -> 0x04a1 }
            kotlin.jvm.functions.Function2 r5 = (kotlin.jvm.functions.Function2) r5     // Catch:{ Exception -> 0x04a1 }
            r2 = r12
            kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2     // Catch:{ Exception -> 0x04a1 }
            r12.L$0 = r13     // Catch:{ Exception -> 0x04a1 }
            r12.I$0 = r6     // Catch:{ Exception -> 0x04a1 }
            r7 = 4
            r12.label = r7     // Catch:{ Exception -> 0x04a1 }
            java.lang.Object r0 = kotlinx.coroutines.BuildersKt.withContext(r0, r5, r2)     // Catch:{ Exception -> 0x04a1 }
            if (r0 != r1) goto L_0x011d
            return r1
        L_0x011d:
            r1 = r13
            r13 = r0
            r7 = r6
        L_0x0120:
            org.videolan.medialibrary.interfaces.media.MediaWrapper[] r13 = (org.videolan.medialibrary.interfaces.media.MediaWrapper[]) r13     // Catch:{ Exception -> 0x04a1 }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r13)     // Catch:{ Exception -> 0x04a1 }
            int r0 = r13.length     // Catch:{ Exception -> 0x04a1 }
            if (r0 != 0) goto L_0x0129
            r3 = 1
        L_0x0129:
            r0 = r3 ^ 1
            if (r0 == 0) goto L_0x04da
            boolean r0 = kotlinx.coroutines.CoroutineScopeKt.isActive(r1)     // Catch:{ Exception -> 0x04a1 }
            if (r0 == 0) goto L_0x04da
            org.videolan.vlc.gui.helpers.MediaComparators r0 = org.videolan.vlc.gui.helpers.MediaComparators.INSTANCE     // Catch:{ Exception -> 0x04a1 }
            java.util.Comparator r0 = r0.getANDROID_AUTO()     // Catch:{ Exception -> 0x04a1 }
            kotlin.collections.ArraysKt.sortWith(r13, r0)     // Catch:{ Exception -> 0x04a1 }
            org.videolan.vlc.MediaSessionCallback r5 = r12.this$0     // Catch:{ Exception -> 0x04a1 }
            java.util.List r6 = kotlin.collections.ArraysKt.toList((T[]) r13)     // Catch:{ Exception -> 0x04a1 }
            r9 = 4
            r10 = 0
            r8 = 0
            org.videolan.vlc.MediaSessionCallback.loadMedia$default(r5, r6, r7, r8, r9, r10)     // Catch:{ Exception -> 0x04a1 }
            goto L_0x04da
        L_0x014a:
            java.lang.String r3 = "//org.videolan.vlc/r/search"
            boolean r3 = r8.equals(r3)     // Catch:{ Exception -> 0x04a1 }
            if (r3 != 0) goto L_0x0154
            goto L_0x0332
        L_0x0154:
            java.lang.String r0 = "query"
            java.lang.String r0 = r5.getQueryParameter(r0)     // Catch:{ Exception -> 0x04a1 }
            if (r0 != 0) goto L_0x015e
            java.lang.String r0 = ""
        L_0x015e:
            kotlinx.coroutines.CoroutineDispatcher r3 = kotlinx.coroutines.Dispatchers.getIO()     // Catch:{ Exception -> 0x04a1 }
            kotlin.coroutines.CoroutineContext r3 = (kotlin.coroutines.CoroutineContext) r3     // Catch:{ Exception -> 0x04a1 }
            org.videolan.vlc.MediaSessionCallback$onPlayFromMediaId$1$invokeSuspend$$inlined$getFromMl$6 r5 = new org.videolan.vlc.MediaSessionCallback$onPlayFromMediaId$1$invokeSuspend$$inlined$getFromMl$6     // Catch:{ Exception -> 0x04a1 }
            r5.<init>(r2, r10, r0)     // Catch:{ Exception -> 0x04a1 }
            kotlin.jvm.functions.Function2 r5 = (kotlin.jvm.functions.Function2) r5     // Catch:{ Exception -> 0x04a1 }
            r0 = r12
            kotlin.coroutines.Continuation r0 = (kotlin.coroutines.Continuation) r0     // Catch:{ Exception -> 0x04a1 }
            r12.L$0 = r13     // Catch:{ Exception -> 0x04a1 }
            r12.I$0 = r6     // Catch:{ Exception -> 0x04a1 }
            r2 = 6
            r12.label = r2     // Catch:{ Exception -> 0x04a1 }
            java.lang.Object r0 = kotlinx.coroutines.BuildersKt.withContext(r3, r5, r0)     // Catch:{ Exception -> 0x04a1 }
            if (r0 != r1) goto L_0x017c
            return r1
        L_0x017c:
            r1 = r13
            r13 = r0
            r7 = r6
        L_0x017f:
            r6 = r13
            java.util.List r6 = (java.util.List) r6     // Catch:{ Exception -> 0x04a1 }
            r13 = r6
            java.util.Collection r13 = (java.util.Collection) r13     // Catch:{ Exception -> 0x04a1 }
            boolean r13 = r13.isEmpty()     // Catch:{ Exception -> 0x04a1 }
            r13 = r13 ^ r4
            if (r13 == 0) goto L_0x04da
            boolean r13 = kotlinx.coroutines.CoroutineScopeKt.isActive(r1)     // Catch:{ Exception -> 0x04a1 }
            if (r13 == 0) goto L_0x04da
            org.videolan.vlc.MediaSessionCallback r5 = r12.this$0     // Catch:{ Exception -> 0x04a1 }
            r9 = 4
            r10 = 0
            r8 = 0
            org.videolan.vlc.MediaSessionCallback.loadMedia$default(r5, r6, r7, r8, r9, r10)     // Catch:{ Exception -> 0x04a1 }
            goto L_0x04da
        L_0x019c:
            java.lang.String r4 = "//org.videolan.vlc/r/home/history"
            boolean r4 = r8.equals(r4)     // Catch:{ Exception -> 0x04a1 }
            if (r4 != 0) goto L_0x01a6
            goto L_0x0332
        L_0x01a6:
            kotlinx.coroutines.CoroutineDispatcher r0 = kotlinx.coroutines.Dispatchers.getIO()     // Catch:{ Exception -> 0x04a1 }
            kotlin.coroutines.CoroutineContext r0 = (kotlin.coroutines.CoroutineContext) r0     // Catch:{ Exception -> 0x04a1 }
            org.videolan.vlc.MediaSessionCallback$onPlayFromMediaId$1$invokeSuspend$$inlined$getFromMl$3 r4 = new org.videolan.vlc.MediaSessionCallback$onPlayFromMediaId$1$invokeSuspend$$inlined$getFromMl$3     // Catch:{ Exception -> 0x04a1 }
            r4.<init>(r2, r10)     // Catch:{ Exception -> 0x04a1 }
            kotlin.jvm.functions.Function2 r4 = (kotlin.jvm.functions.Function2) r4     // Catch:{ Exception -> 0x04a1 }
            r2 = r12
            kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2     // Catch:{ Exception -> 0x04a1 }
            r12.L$0 = r13     // Catch:{ Exception -> 0x04a1 }
            r12.I$0 = r6     // Catch:{ Exception -> 0x04a1 }
            r5 = 3
            r12.label = r5     // Catch:{ Exception -> 0x04a1 }
            java.lang.Object r0 = kotlinx.coroutines.BuildersKt.withContext(r0, r4, r2)     // Catch:{ Exception -> 0x04a1 }
            if (r0 != r1) goto L_0x01c4
            return r1
        L_0x01c4:
            r1 = r13
            r13 = r0
        L_0x01c6:
            java.util.List r13 = (java.util.List) r13     // Catch:{ Exception -> 0x04a1 }
            r0 = r13
            java.util.Collection r0 = (java.util.Collection) r0     // Catch:{ Exception -> 0x04a1 }
            if (r0 == 0) goto L_0x04da
            boolean r0 = r0.isEmpty()     // Catch:{ Exception -> 0x04a1 }
            if (r0 == 0) goto L_0x01d5
            goto L_0x04da
        L_0x01d5:
            boolean r0 = kotlinx.coroutines.CoroutineScopeKt.isActive(r1)     // Catch:{ Exception -> 0x04a1 }
            if (r0 == 0) goto L_0x04da
            int r0 = r13.size()     // Catch:{ Exception -> 0x04a1 }
            r1 = 100
            int r0 = kotlin.ranges.RangesKt.coerceAtMost((int) r0, (int) r1)     // Catch:{ Exception -> 0x04a1 }
            java.util.List r5 = r13.subList(r3, r0)     // Catch:{ Exception -> 0x04a1 }
            org.videolan.vlc.MediaSessionCallback r4 = r12.this$0     // Catch:{ Exception -> 0x04a1 }
            r8 = 4
            r9 = 0
            r7 = 0
            org.videolan.vlc.MediaSessionCallback.loadMedia$default(r4, r5, r6, r7, r8, r9)     // Catch:{ Exception -> 0x04a1 }
            goto L_0x04da
        L_0x01f3:
            java.lang.String r7 = "//org.videolan.vlc/r/home/last_added"
            boolean r7 = r8.equals(r7)     // Catch:{ Exception -> 0x04a1 }
            if (r7 != 0) goto L_0x01fd
            goto L_0x0332
        L_0x01fd:
            kotlinx.coroutines.CoroutineDispatcher r0 = kotlinx.coroutines.Dispatchers.getIO()     // Catch:{ Exception -> 0x04a1 }
            kotlin.coroutines.CoroutineContext r0 = (kotlin.coroutines.CoroutineContext) r0     // Catch:{ Exception -> 0x04a1 }
            org.videolan.vlc.MediaSessionCallback$onPlayFromMediaId$1$invokeSuspend$$inlined$getFromMl$2 r5 = new org.videolan.vlc.MediaSessionCallback$onPlayFromMediaId$1$invokeSuspend$$inlined$getFromMl$2     // Catch:{ Exception -> 0x04a1 }
            r5.<init>(r2, r10)     // Catch:{ Exception -> 0x04a1 }
            kotlin.jvm.functions.Function2 r5 = (kotlin.jvm.functions.Function2) r5     // Catch:{ Exception -> 0x04a1 }
            r2 = r12
            kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2     // Catch:{ Exception -> 0x04a1 }
            r12.L$0 = r13     // Catch:{ Exception -> 0x04a1 }
            r12.I$0 = r6     // Catch:{ Exception -> 0x04a1 }
            r7 = 2
            r12.label = r7     // Catch:{ Exception -> 0x04a1 }
            java.lang.Object r0 = kotlinx.coroutines.BuildersKt.withContext(r0, r5, r2)     // Catch:{ Exception -> 0x04a1 }
            if (r0 != r1) goto L_0x021b
            return r1
        L_0x021b:
            r1 = r13
            r13 = r0
            r7 = r6
        L_0x021e:
            org.videolan.medialibrary.interfaces.media.MediaWrapper[] r13 = (org.videolan.medialibrary.interfaces.media.MediaWrapper[]) r13     // Catch:{ Exception -> 0x04a1 }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r13)     // Catch:{ Exception -> 0x04a1 }
            int r0 = r13.length     // Catch:{ Exception -> 0x04a1 }
            if (r0 != 0) goto L_0x0227
            r3 = 1
        L_0x0227:
            r0 = r3 ^ 1
            if (r0 == 0) goto L_0x04da
            boolean r0 = kotlinx.coroutines.CoroutineScopeKt.isActive(r1)     // Catch:{ Exception -> 0x04a1 }
            if (r0 == 0) goto L_0x04da
            org.videolan.vlc.MediaSessionCallback r5 = r12.this$0     // Catch:{ Exception -> 0x04a1 }
            java.util.List r6 = kotlin.collections.ArraysKt.toList((T[]) r13)     // Catch:{ Exception -> 0x04a1 }
            r9 = 4
            r10 = 0
            r8 = 0
            org.videolan.vlc.MediaSessionCallback.loadMedia$default(r5, r6, r7, r8, r9, r10)     // Catch:{ Exception -> 0x04a1 }
            goto L_0x04da
        L_0x023f:
            java.lang.String r3 = "//org.videolan.vlc/r/error/playlist"
            boolean r3 = r8.equals(r3)     // Catch:{ Exception -> 0x04a1 }
            if (r3 != 0) goto L_0x0249
            goto L_0x0332
        L_0x0249:
            org.videolan.vlc.MediaSessionCallback r13 = r12.this$0     // Catch:{ Exception -> 0x04a1 }
            org.videolan.vlc.PlaybackService r13 = r13.playbackService     // Catch:{ Exception -> 0x04a1 }
            int r0 = org.videolan.vlc.R.string.noplaylist     // Catch:{ Exception -> 0x04a1 }
            r13.displayPlaybackError(r0)     // Catch:{ Exception -> 0x04a1 }
            goto L_0x04da
        L_0x0256:
            java.lang.String r7 = "//org.videolan.vlc/r/home/shuffle_all"
            boolean r7 = r8.equals(r7)     // Catch:{ Exception -> 0x04a1 }
            if (r7 != 0) goto L_0x0260
            goto L_0x0332
        L_0x0260:
            kotlinx.coroutines.CoroutineDispatcher r0 = kotlinx.coroutines.Dispatchers.getIO()     // Catch:{ Exception -> 0x04a1 }
            kotlin.coroutines.CoroutineContext r0 = (kotlin.coroutines.CoroutineContext) r0     // Catch:{ Exception -> 0x04a1 }
            org.videolan.vlc.MediaSessionCallback$onPlayFromMediaId$1$invokeSuspend$$inlined$getFromMl$1 r5 = new org.videolan.vlc.MediaSessionCallback$onPlayFromMediaId$1$invokeSuspend$$inlined$getFromMl$1     // Catch:{ Exception -> 0x04a1 }
            r5.<init>(r2, r10)     // Catch:{ Exception -> 0x04a1 }
            kotlin.jvm.functions.Function2 r5 = (kotlin.jvm.functions.Function2) r5     // Catch:{ Exception -> 0x04a1 }
            r2 = r12
            kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2     // Catch:{ Exception -> 0x04a1 }
            r12.L$0 = r13     // Catch:{ Exception -> 0x04a1 }
            r12.label = r4     // Catch:{ Exception -> 0x04a1 }
            java.lang.Object r0 = kotlinx.coroutines.BuildersKt.withContext(r0, r5, r2)     // Catch:{ Exception -> 0x04a1 }
            if (r0 != r1) goto L_0x027b
            return r1
        L_0x027b:
            r11 = r0
            r0 = r13
            r13 = r11
        L_0x027e:
            org.videolan.medialibrary.interfaces.media.MediaWrapper[] r13 = (org.videolan.medialibrary.interfaces.media.MediaWrapper[]) r13     // Catch:{ Exception -> 0x04a1 }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r13)     // Catch:{ Exception -> 0x04a1 }
            int r1 = r13.length     // Catch:{ Exception -> 0x04a1 }
            if (r1 != 0) goto L_0x0287
            r3 = 1
        L_0x0287:
            r1 = r3 ^ 1
            if (r1 == 0) goto L_0x02cd
            boolean r0 = kotlinx.coroutines.CoroutineScopeKt.isActive(r0)     // Catch:{ Exception -> 0x04a1 }
            if (r0 == 0) goto L_0x02cd
            org.videolan.vlc.gui.helpers.MediaComparators r0 = org.videolan.vlc.gui.helpers.MediaComparators.INSTANCE     // Catch:{ Exception -> 0x04a1 }
            java.util.Comparator r0 = r0.getANDROID_AUTO()     // Catch:{ Exception -> 0x04a1 }
            kotlin.collections.ArraysKt.sortWith(r13, r0)     // Catch:{ Exception -> 0x04a1 }
            org.videolan.vlc.MediaSessionCallback r1 = r12.this$0     // Catch:{ Exception -> 0x04a1 }
            java.util.List r2 = kotlin.collections.ArraysKt.toList((T[]) r13)     // Catch:{ Exception -> 0x04a1 }
            java.security.SecureRandom r0 = new java.security.SecureRandom     // Catch:{ Exception -> 0x04a1 }
            r0.<init>()     // Catch:{ Exception -> 0x04a1 }
            int r13 = r13.length     // Catch:{ Exception -> 0x04a1 }
            r3 = 500(0x1f4, float:7.0E-43)
            int r13 = java.lang.Math.min(r13, r3)     // Catch:{ Exception -> 0x04a1 }
            int r3 = r0.nextInt(r13)     // Catch:{ Exception -> 0x04a1 }
            r5 = 4
            r6 = 0
            r4 = 0
            org.videolan.vlc.MediaSessionCallback.loadMedia$default(r1, r2, r3, r4, r5, r6)     // Catch:{ Exception -> 0x04a1 }
            org.videolan.vlc.MediaSessionCallback r13 = r12.this$0     // Catch:{ Exception -> 0x04a1 }
            org.videolan.vlc.PlaybackService r13 = r13.playbackService     // Catch:{ Exception -> 0x04a1 }
            boolean r13 = r13.isShuffling()     // Catch:{ Exception -> 0x04a1 }
            if (r13 != 0) goto L_0x04da
            org.videolan.vlc.MediaSessionCallback r13 = r12.this$0     // Catch:{ Exception -> 0x04a1 }
            org.videolan.vlc.PlaybackService r13 = r13.playbackService     // Catch:{ Exception -> 0x04a1 }
            r13.shuffle()     // Catch:{ Exception -> 0x04a1 }
            goto L_0x04da
        L_0x02cd:
            org.videolan.vlc.MediaSessionCallback r13 = r12.this$0     // Catch:{ Exception -> 0x04a1 }
            org.videolan.vlc.PlaybackService r13 = r13.playbackService     // Catch:{ Exception -> 0x04a1 }
            int r0 = org.videolan.vlc.R.string.search_no_result     // Catch:{ Exception -> 0x04a1 }
            r13.displayPlaybackError(r0)     // Catch:{ Exception -> 0x04a1 }
            goto L_0x04da
        L_0x02da:
            java.lang.String r9 = "//org.videolan.vlc/r/l/t"
            boolean r8 = r8.equals(r9)     // Catch:{ Exception -> 0x04a1 }
            if (r8 != 0) goto L_0x02e3
            goto L_0x0332
        L_0x02e3:
            kotlinx.coroutines.CoroutineDispatcher r0 = kotlinx.coroutines.Dispatchers.getIO()     // Catch:{ Exception -> 0x04a1 }
            kotlin.coroutines.CoroutineContext r0 = (kotlin.coroutines.CoroutineContext) r0     // Catch:{ Exception -> 0x04a1 }
            org.videolan.vlc.MediaSessionCallback$onPlayFromMediaId$1$invokeSuspend$$inlined$getFromMl$5 r5 = new org.videolan.vlc.MediaSessionCallback$onPlayFromMediaId$1$invokeSuspend$$inlined$getFromMl$5     // Catch:{ Exception -> 0x04a1 }
            r5.<init>(r2, r10)     // Catch:{ Exception -> 0x04a1 }
            kotlin.jvm.functions.Function2 r5 = (kotlin.jvm.functions.Function2) r5     // Catch:{ Exception -> 0x04a1 }
            r2 = r12
            kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2     // Catch:{ Exception -> 0x04a1 }
            r12.L$0 = r13     // Catch:{ Exception -> 0x04a1 }
            r12.I$0 = r6     // Catch:{ Exception -> 0x04a1 }
            r12.I$1 = r7     // Catch:{ Exception -> 0x04a1 }
            r8 = 5
            r12.label = r8     // Catch:{ Exception -> 0x04a1 }
            java.lang.Object r0 = kotlinx.coroutines.BuildersKt.withContext(r0, r5, r2)     // Catch:{ Exception -> 0x04a1 }
            if (r0 != r1) goto L_0x0303
            return r1
        L_0x0303:
            r2 = r13
            r13 = r0
            r1 = r6
            r0 = r7
        L_0x0307:
            org.videolan.medialibrary.interfaces.media.MediaWrapper[] r13 = (org.videolan.medialibrary.interfaces.media.MediaWrapper[]) r13     // Catch:{ Exception -> 0x04a1 }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r13)     // Catch:{ Exception -> 0x04a1 }
            int r5 = r13.length     // Catch:{ Exception -> 0x04a1 }
            if (r5 != 0) goto L_0x0310
            r3 = 1
        L_0x0310:
            r3 = r3 ^ r4
            if (r3 == 0) goto L_0x04da
            boolean r2 = kotlinx.coroutines.CoroutineScopeKt.isActive(r2)     // Catch:{ Exception -> 0x04a1 }
            if (r2 == 0) goto L_0x04da
            org.videolan.vlc.gui.helpers.MediaComparators r2 = org.videolan.vlc.gui.helpers.MediaComparators.INSTANCE     // Catch:{ Exception -> 0x04a1 }
            java.util.Comparator r2 = r2.getANDROID_AUTO()     // Catch:{ Exception -> 0x04a1 }
            kotlin.collections.ArraysKt.sortWith(r13, r2)     // Catch:{ Exception -> 0x04a1 }
            org.videolan.vlc.MediaSessionCallback r3 = r12.this$0     // Catch:{ Exception -> 0x04a1 }
            java.util.List r4 = kotlin.collections.ArraysKt.toList((T[]) r13)     // Catch:{ Exception -> 0x04a1 }
            int r5 = r0 + r1
            r7 = 4
            r8 = 0
            r6 = 0
            org.videolan.vlc.MediaSessionCallback.loadMedia$default(r3, r4, r5, r6, r7, r8)     // Catch:{ Exception -> 0x04a1 }
            goto L_0x04da
        L_0x0332:
            long r3 = android.content.ContentUris.parseId(r5)     // Catch:{ Exception -> 0x04a1 }
            android.net.Uri r5 = org.videolan.tools.KotlinExtensionsKt.retrieveParent(r5)     // Catch:{ Exception -> 0x04a1 }
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ Exception -> 0x04a1 }
            int r7 = r5.hashCode()     // Catch:{ Exception -> 0x04a1 }
            switch(r7) {
                case -1979024705: goto L_0x044b;
                case -1979024700: goto L_0x040a;
                case -1979024694: goto L_0x03c9;
                case -664866953: goto L_0x0388;
                case 830303327: goto L_0x0347;
                default: goto L_0x0345;
            }     // Catch:{ Exception -> 0x04a1 }
        L_0x0345:
            goto L_0x048d
        L_0x0347:
            java.lang.String r6 = "//org.videolan.vlc/r/media"
            boolean r5 = r5.equals(r6)     // Catch:{ Exception -> 0x04a1 }
            if (r5 == 0) goto L_0x048d
            kotlinx.coroutines.CoroutineDispatcher r0 = kotlinx.coroutines.Dispatchers.getIO()     // Catch:{ Exception -> 0x04a1 }
            kotlin.coroutines.CoroutineContext r0 = (kotlin.coroutines.CoroutineContext) r0     // Catch:{ Exception -> 0x04a1 }
            org.videolan.vlc.MediaSessionCallback$onPlayFromMediaId$1$invokeSuspend$$inlined$getFromMl$11 r5 = new org.videolan.vlc.MediaSessionCallback$onPlayFromMediaId$1$invokeSuspend$$inlined$getFromMl$11     // Catch:{ Exception -> 0x04a1 }
            r5.<init>(r2, r10, r3)     // Catch:{ Exception -> 0x04a1 }
            kotlin.jvm.functions.Function2 r5 = (kotlin.jvm.functions.Function2) r5     // Catch:{ Exception -> 0x04a1 }
            r2 = r12
            kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2     // Catch:{ Exception -> 0x04a1 }
            r12.L$0 = r13     // Catch:{ Exception -> 0x04a1 }
            r3 = 11
            r12.label = r3     // Catch:{ Exception -> 0x04a1 }
            java.lang.Object r0 = kotlinx.coroutines.BuildersKt.withContext(r0, r5, r2)     // Catch:{ Exception -> 0x04a1 }
            if (r0 != r1) goto L_0x036c
            return r1
        L_0x036c:
            r11 = r0
            r0 = r13
            r13 = r11
        L_0x036f:
            org.videolan.medialibrary.interfaces.media.MediaWrapper[] r13 = (org.videolan.medialibrary.interfaces.media.MediaWrapper[]) r13     // Catch:{ Exception -> 0x04a1 }
            boolean r0 = kotlinx.coroutines.CoroutineScopeKt.isActive(r0)     // Catch:{ Exception -> 0x04a1 }
            if (r0 == 0) goto L_0x04da
            if (r13 == 0) goto L_0x04da
            org.videolan.vlc.MediaSessionCallback r1 = r12.this$0     // Catch:{ Exception -> 0x04a1 }
            java.util.List r2 = kotlin.collections.ArraysKt.toList((T[]) r13)     // Catch:{ Exception -> 0x04a1 }
            r5 = 6
            r6 = 0
            r3 = 0
            r4 = 0
            org.videolan.vlc.MediaSessionCallback.loadMedia$default(r1, r2, r3, r4, r5, r6)     // Catch:{ Exception -> 0x04a1 }
            goto L_0x04da
        L_0x0388:
            java.lang.String r6 = "//org.videolan.vlc/r/playlist"
            boolean r5 = r5.equals(r6)     // Catch:{ Exception -> 0x04a1 }
            if (r5 == 0) goto L_0x048d
            kotlinx.coroutines.CoroutineDispatcher r0 = kotlinx.coroutines.Dispatchers.getIO()     // Catch:{ Exception -> 0x04a1 }
            kotlin.coroutines.CoroutineContext r0 = (kotlin.coroutines.CoroutineContext) r0     // Catch:{ Exception -> 0x04a1 }
            org.videolan.vlc.MediaSessionCallback$onPlayFromMediaId$1$invokeSuspend$$inlined$getFromMl$10 r5 = new org.videolan.vlc.MediaSessionCallback$onPlayFromMediaId$1$invokeSuspend$$inlined$getFromMl$10     // Catch:{ Exception -> 0x04a1 }
            r5.<init>(r2, r10, r3)     // Catch:{ Exception -> 0x04a1 }
            kotlin.jvm.functions.Function2 r5 = (kotlin.jvm.functions.Function2) r5     // Catch:{ Exception -> 0x04a1 }
            r2 = r12
            kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2     // Catch:{ Exception -> 0x04a1 }
            r12.L$0 = r13     // Catch:{ Exception -> 0x04a1 }
            r3 = 10
            r12.label = r3     // Catch:{ Exception -> 0x04a1 }
            java.lang.Object r0 = kotlinx.coroutines.BuildersKt.withContext(r0, r5, r2)     // Catch:{ Exception -> 0x04a1 }
            if (r0 != r1) goto L_0x03ad
            return r1
        L_0x03ad:
            r11 = r0
            r0 = r13
            r13 = r11
        L_0x03b0:
            org.videolan.medialibrary.interfaces.media.MediaWrapper[] r13 = (org.videolan.medialibrary.interfaces.media.MediaWrapper[]) r13     // Catch:{ Exception -> 0x04a1 }
            boolean r0 = kotlinx.coroutines.CoroutineScopeKt.isActive(r0)     // Catch:{ Exception -> 0x04a1 }
            if (r0 == 0) goto L_0x04da
            if (r13 == 0) goto L_0x04da
            org.videolan.vlc.MediaSessionCallback r1 = r12.this$0     // Catch:{ Exception -> 0x04a1 }
            java.util.List r2 = kotlin.collections.ArraysKt.toList((T[]) r13)     // Catch:{ Exception -> 0x04a1 }
            r5 = 2
            r6 = 0
            r3 = 0
            r4 = 1
            org.videolan.vlc.MediaSessionCallback.loadMedia$default(r1, r2, r3, r4, r5, r6)     // Catch:{ Exception -> 0x04a1 }
            goto L_0x04da
        L_0x03c9:
            java.lang.String r6 = "//org.videolan.vlc/r/l/r"
            boolean r5 = r5.equals(r6)     // Catch:{ Exception -> 0x04a1 }
            if (r5 == 0) goto L_0x048d
            kotlinx.coroutines.CoroutineDispatcher r0 = kotlinx.coroutines.Dispatchers.getIO()     // Catch:{ Exception -> 0x04a1 }
            kotlin.coroutines.CoroutineContext r0 = (kotlin.coroutines.CoroutineContext) r0     // Catch:{ Exception -> 0x04a1 }
            org.videolan.vlc.MediaSessionCallback$onPlayFromMediaId$1$invokeSuspend$$inlined$getFromMl$8 r5 = new org.videolan.vlc.MediaSessionCallback$onPlayFromMediaId$1$invokeSuspend$$inlined$getFromMl$8     // Catch:{ Exception -> 0x04a1 }
            r5.<init>(r2, r10, r3)     // Catch:{ Exception -> 0x04a1 }
            kotlin.jvm.functions.Function2 r5 = (kotlin.jvm.functions.Function2) r5     // Catch:{ Exception -> 0x04a1 }
            r2 = r12
            kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2     // Catch:{ Exception -> 0x04a1 }
            r12.L$0 = r13     // Catch:{ Exception -> 0x04a1 }
            r3 = 8
            r12.label = r3     // Catch:{ Exception -> 0x04a1 }
            java.lang.Object r0 = kotlinx.coroutines.BuildersKt.withContext(r0, r5, r2)     // Catch:{ Exception -> 0x04a1 }
            if (r0 != r1) goto L_0x03ee
            return r1
        L_0x03ee:
            r11 = r0
            r0 = r13
            r13 = r11
        L_0x03f1:
            org.videolan.medialibrary.interfaces.media.MediaWrapper[] r13 = (org.videolan.medialibrary.interfaces.media.MediaWrapper[]) r13     // Catch:{ Exception -> 0x04a1 }
            boolean r0 = kotlinx.coroutines.CoroutineScopeKt.isActive(r0)     // Catch:{ Exception -> 0x04a1 }
            if (r0 == 0) goto L_0x04da
            if (r13 == 0) goto L_0x04da
            org.videolan.vlc.MediaSessionCallback r1 = r12.this$0     // Catch:{ Exception -> 0x04a1 }
            java.util.List r2 = kotlin.collections.ArraysKt.toList((T[]) r13)     // Catch:{ Exception -> 0x04a1 }
            r5 = 2
            r6 = 0
            r3 = 0
            r4 = 1
            org.videolan.vlc.MediaSessionCallback.loadMedia$default(r1, r2, r3, r4, r5, r6)     // Catch:{ Exception -> 0x04a1 }
            goto L_0x04da
        L_0x040a:
            java.lang.String r7 = "//org.videolan.vlc/r/l/l"
            boolean r5 = r5.equals(r7)     // Catch:{ Exception -> 0x04a1 }
            if (r5 == 0) goto L_0x048d
            kotlinx.coroutines.CoroutineDispatcher r0 = kotlinx.coroutines.Dispatchers.getIO()     // Catch:{ Exception -> 0x04a1 }
            kotlin.coroutines.CoroutineContext r0 = (kotlin.coroutines.CoroutineContext) r0     // Catch:{ Exception -> 0x04a1 }
            org.videolan.vlc.MediaSessionCallback$onPlayFromMediaId$1$invokeSuspend$$inlined$getFromMl$7 r5 = new org.videolan.vlc.MediaSessionCallback$onPlayFromMediaId$1$invokeSuspend$$inlined$getFromMl$7     // Catch:{ Exception -> 0x04a1 }
            r5.<init>(r2, r10, r3)     // Catch:{ Exception -> 0x04a1 }
            kotlin.jvm.functions.Function2 r5 = (kotlin.jvm.functions.Function2) r5     // Catch:{ Exception -> 0x04a1 }
            r2 = r12
            kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2     // Catch:{ Exception -> 0x04a1 }
            r12.L$0 = r13     // Catch:{ Exception -> 0x04a1 }
            r12.I$0 = r6     // Catch:{ Exception -> 0x04a1 }
            r3 = 7
            r12.label = r3     // Catch:{ Exception -> 0x04a1 }
            java.lang.Object r0 = kotlinx.coroutines.BuildersKt.withContext(r0, r5, r2)     // Catch:{ Exception -> 0x04a1 }
            if (r0 != r1) goto L_0x0430
            return r1
        L_0x0430:
            r1 = r13
            r13 = r0
            r2 = r6
        L_0x0433:
            org.videolan.medialibrary.interfaces.media.MediaWrapper[] r13 = (org.videolan.medialibrary.interfaces.media.MediaWrapper[]) r13     // Catch:{ Exception -> 0x04a1 }
            boolean r0 = kotlinx.coroutines.CoroutineScopeKt.isActive(r1)     // Catch:{ Exception -> 0x04a1 }
            if (r0 == 0) goto L_0x04da
            if (r13 == 0) goto L_0x04da
            org.videolan.vlc.MediaSessionCallback r0 = r12.this$0     // Catch:{ Exception -> 0x04a1 }
            java.util.List r1 = kotlin.collections.ArraysKt.toList((T[]) r13)     // Catch:{ Exception -> 0x04a1 }
            r4 = 4
            r5 = 0
            r3 = 0
            org.videolan.vlc.MediaSessionCallback.loadMedia$default(r0, r1, r2, r3, r4, r5)     // Catch:{ Exception -> 0x04a1 }
            goto L_0x04da
        L_0x044b:
            java.lang.String r6 = "//org.videolan.vlc/r/l/g"
            boolean r5 = r5.equals(r6)     // Catch:{ Exception -> 0x04a1 }
            if (r5 == 0) goto L_0x048d
            kotlinx.coroutines.CoroutineDispatcher r0 = kotlinx.coroutines.Dispatchers.getIO()     // Catch:{ Exception -> 0x04a1 }
            kotlin.coroutines.CoroutineContext r0 = (kotlin.coroutines.CoroutineContext) r0     // Catch:{ Exception -> 0x04a1 }
            org.videolan.vlc.MediaSessionCallback$onPlayFromMediaId$1$invokeSuspend$$inlined$getFromMl$9 r5 = new org.videolan.vlc.MediaSessionCallback$onPlayFromMediaId$1$invokeSuspend$$inlined$getFromMl$9     // Catch:{ Exception -> 0x04a1 }
            r5.<init>(r2, r10, r3)     // Catch:{ Exception -> 0x04a1 }
            kotlin.jvm.functions.Function2 r5 = (kotlin.jvm.functions.Function2) r5     // Catch:{ Exception -> 0x04a1 }
            r2 = r12
            kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2     // Catch:{ Exception -> 0x04a1 }
            r12.L$0 = r13     // Catch:{ Exception -> 0x04a1 }
            r3 = 9
            r12.label = r3     // Catch:{ Exception -> 0x04a1 }
            java.lang.Object r0 = kotlinx.coroutines.BuildersKt.withContext(r0, r5, r2)     // Catch:{ Exception -> 0x04a1 }
            if (r0 != r1) goto L_0x0470
            return r1
        L_0x0470:
            r11 = r0
            r0 = r13
            r13 = r11
        L_0x0473:
            java.util.List r13 = (java.util.List) r13     // Catch:{ Exception -> 0x04a1 }
            boolean r0 = kotlinx.coroutines.CoroutineScopeKt.isActive(r0)     // Catch:{ Exception -> 0x04a1 }
            if (r0 == 0) goto L_0x04da
            if (r13 == 0) goto L_0x04da
            org.videolan.vlc.MediaSessionCallback r1 = r12.this$0     // Catch:{ Exception -> 0x04a1 }
            java.lang.Iterable r13 = (java.lang.Iterable) r13     // Catch:{ Exception -> 0x04a1 }
            java.util.List r2 = kotlin.collections.CollectionsKt.toList(r13)     // Catch:{ Exception -> 0x04a1 }
            r5 = 2
            r6 = 0
            r3 = 0
            r4 = 1
            org.videolan.vlc.MediaSessionCallback.loadMedia$default(r1, r2, r3, r4, r5, r6)     // Catch:{ Exception -> 0x04a1 }
            goto L_0x04da
        L_0x048d:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException     // Catch:{ Exception -> 0x04a1 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x04a1 }
            r1.<init>(r0)     // Catch:{ Exception -> 0x04a1 }
            java.lang.String r0 = r12.$mediaId     // Catch:{ Exception -> 0x04a1 }
            r1.append(r0)     // Catch:{ Exception -> 0x04a1 }
            java.lang.String r0 = r1.toString()     // Catch:{ Exception -> 0x04a1 }
            r13.<init>(r0)     // Catch:{ Exception -> 0x04a1 }
            throw r13     // Catch:{ Exception -> 0x04a1 }
        L_0x04a1:
            r13 = move-exception
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Could not play media: "
            r0.<init>(r1)
            java.lang.String r1 = r12.$mediaId
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.Throwable r13 = (java.lang.Throwable) r13
            java.lang.String r1 = "VLC/MediaSessionCallback"
            android.util.Log.e(r1, r0, r13)
            org.videolan.vlc.MediaSessionCallback r13 = r12.this$0
            org.videolan.vlc.PlaybackService r13 = r13.playbackService
            boolean r13 = r13.hasMedia()
            if (r13 == 0) goto L_0x04cf
            org.videolan.vlc.MediaSessionCallback r13 = r12.this$0
            org.videolan.vlc.PlaybackService r13 = r13.playbackService
            r13.play()
            goto L_0x04da
        L_0x04cf:
            org.videolan.vlc.MediaSessionCallback r13 = r12.this$0
            org.videolan.vlc.PlaybackService r13 = r13.playbackService
            int r0 = org.videolan.vlc.R.string.search_no_result
            r13.displayPlaybackError(r0)
        L_0x04da:
            kotlin.Unit r13 = kotlin.Unit.INSTANCE
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.MediaSessionCallback$onPlayFromMediaId$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
