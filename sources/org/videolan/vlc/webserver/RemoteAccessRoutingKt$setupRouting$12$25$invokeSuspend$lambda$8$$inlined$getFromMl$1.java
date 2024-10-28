package org.videolan.vlc.webserver;

import android.content.Context;
import android.content.SharedPreferences;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.videolan.medialibrary.interfaces.Medialibrary;
import org.videolan.medialibrary.interfaces.media.Folder;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.interfaces.media.VideoGroup;
import org.videolan.resources.util.ExtensionsKt;
import org.videolan.tools.CoroutineContextProvider;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.ArtworkProvider;

@Metadata(d1 = {"\u0000\n\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0002H\u0001\"\u0006\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0002H@¨\u0006\u0003"}, d2 = {"<anonymous>", "T", "Lkotlinx/coroutines/CoroutineScope;", "org/videolan/resources/util/ExtensionsKt$getFromMl$2"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.webserver.RemoteAccessRoutingKt$setupRouting$12$25$invokeSuspend$lambda$8$$inlined$getFromMl$1", f = "RemoteAccessRouting.kt", i = {0, 0, 0}, l = {369}, m = "invokeSuspend", n = {"$this$withContext", "ml", "scan"}, s = {"L$0", "L$1", "I$0"})
/* compiled from: Extensions.kt */
public final class RemoteAccessRoutingKt$setupRouting$12$25$invokeSuspend$lambda$8$$inlined$getFromMl$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super MediaWrapper[]>, Object> {
    final /* synthetic */ String $id$inlined;
    final /* synthetic */ Context $this_getFromMl;
    final /* synthetic */ String $type$inlined;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RemoteAccessRoutingKt$setupRouting$12$25$invokeSuspend$lambda$8$$inlined$getFromMl$1(Context context, Continuation continuation, String str, String str2) {
        super(2, continuation);
        this.$this_getFromMl = context;
        this.$type$inlined = str;
        this.$id$inlined = str2;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        RemoteAccessRoutingKt$setupRouting$12$25$invokeSuspend$lambda$8$$inlined$getFromMl$1 remoteAccessRoutingKt$setupRouting$12$25$invokeSuspend$lambda$8$$inlined$getFromMl$1 = new RemoteAccessRoutingKt$setupRouting$12$25$invokeSuspend$lambda$8$$inlined$getFromMl$1(this.$this_getFromMl, continuation, this.$type$inlined, this.$id$inlined);
        remoteAccessRoutingKt$setupRouting$12$25$invokeSuspend$lambda$8$$inlined$getFromMl$1.L$0 = obj;
        return remoteAccessRoutingKt$setupRouting$12$25$invokeSuspend$lambda$8$$inlined$getFromMl$1;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super MediaWrapper[]> continuation) {
        return ((RemoteAccessRoutingKt$setupRouting$12$25$invokeSuspend$lambda$8$$inlined$getFromMl$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object obj2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            final Medialibrary instance = Medialibrary.getInstance();
            Intrinsics.checkNotNullExpressionValue(instance, "getInstance(...)");
            if (instance.isStarted()) {
                String str = this.$type$inlined;
                switch (str.hashCode()) {
                    case -1797815808:
                        if (str.equals("video-folder")) {
                            String str2 = this.$id$inlined;
                            if (str2 == null) {
                                return null;
                            }
                            Folder folder = instance.getFolder(Folder.TYPE_FOLDER_VIDEO, Long.parseLong(str2));
                            return folder.media(Folder.TYPE_FOLDER_VIDEO, 0, false, false, false, folder.mediaCount(Folder.TYPE_FOLDER_VIDEO), 0);
                        }
                        break;
                    case -1409097913:
                        if (str.equals(ArtworkProvider.ARTIST)) {
                            String str3 = this.$id$inlined;
                            if (str3 != null) {
                                return instance.getArtist(Long.parseLong(str3)).getTracks();
                            }
                            return null;
                        }
                        break;
                    case -56977747:
                        if (str.equals("video-group")) {
                            String str4 = this.$id$inlined;
                            if (str4 == null) {
                                return null;
                            }
                            VideoGroup videoGroup = instance.getVideoGroup(Long.parseLong(str4));
                            return videoGroup.media(0, false, false, false, videoGroup.mediaCount(), 0);
                        }
                        break;
                    case 92896879:
                        if (str.equals(ArtworkProvider.ALBUM)) {
                            String str5 = this.$id$inlined;
                            if (str5 != null) {
                                return instance.getAlbum(Long.parseLong(str5)).getTracks();
                            }
                            return null;
                        }
                        break;
                    case 98240899:
                        if (str.equals("genre")) {
                            String str6 = this.$id$inlined;
                            if (str6 != null) {
                                return instance.getGenre(Long.parseLong(str6)).getTracks();
                            }
                            return null;
                        }
                        break;
                }
                return instance.getAudio(0, false, false, false);
            }
            boolean z = ((SharedPreferences) Settings.INSTANCE.getInstance(this.$this_getFromMl)).getInt(SettingsKt.KEY_MEDIALIBRARY_SCAN, 0) == 0;
            Context context = this.$this_getFromMl;
            this.L$0 = coroutineScope;
            this.L$1 = instance;
            this.L$2 = context;
            this.I$0 = z ? 1 : 0;
            this.label = 1;
            Continuation continuation = this;
            CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
            cancellableContinuationImpl.initCancellability();
            CancellableContinuation cancellableContinuation = cancellableContinuationImpl;
            final String str7 = this.$type$inlined;
            final String str8 = this.$id$inlined;
            final CancellableContinuation cancellableContinuation2 = cancellableContinuation;
            final Medialibrary medialibrary = instance;
            final AnonymousClass1 r4 = new Medialibrary.OnMedialibraryReadyListener() {
                public void onMedialibraryIdle() {
                }

                @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003H@¨\u0006\u0004"}, d2 = {"<anonymous>", "", "T", "Lkotlinx/coroutines/CoroutineScope;", "org/videolan/resources/util/ExtensionsKt$getFromMl$2$1$listener$1$onMedialibraryReady$1"}, k = 3, mv = {1, 9, 0}, xi = 48)
                @DebugMetadata(c = "org.videolan.vlc.webserver.RemoteAccessRoutingKt$setupRouting$12$25$invokeSuspend$lambda$8$$inlined$getFromMl$1$1$1", f = "RemoteAccessRouting.kt", i = {}, l = {46}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: org.videolan.vlc.webserver.RemoteAccessRoutingKt$setupRouting$12$25$invokeSuspend$lambda$8$$inlined$getFromMl$1$1$1  reason: invalid class name */
                /* compiled from: Extensions.kt */
                public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    int label;

                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                        return new AnonymousClass1(cancellableContinuation, medialibrary, this, continuation, str, str2);
                    }

                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    /* JADX WARNING: Can't fix incorrect switch cases order */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public final java.lang.Object invokeSuspend(java.lang.Object r18) {
                        /*
                            r17 = this;
                            r0 = r17
                            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                            int r2 = r0.label
                            r3 = 1
                            if (r2 == 0) goto L_0x001a
                            if (r2 != r3) goto L_0x0012
                            kotlin.ResultKt.throwOnFailure(r18)
                            goto L_0x00e8
                        L_0x0012:
                            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
                            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
                            r1.<init>(r2)
                            throw r1
                        L_0x001a:
                            kotlin.ResultKt.throwOnFailure(r18)
                            kotlinx.coroutines.CancellableContinuation r2 = r5
                            kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
                            kotlin.Result$Companion r4 = kotlin.Result.Companion
                            org.videolan.medialibrary.interfaces.Medialibrary r4 = r6
                            java.lang.String r5 = r9
                            int r6 = r5.hashCode()
                            r7 = 0
                            switch(r6) {
                                case -1797815808: goto L_0x00a6;
                                case -1409097913: goto L_0x008c;
                                case -56977747: goto L_0x0069;
                                case 92896879: goto L_0x004d;
                                case 98240899: goto L_0x0031;
                                default: goto L_0x002f;
                            }
                        L_0x002f:
                            goto L_0x00d0
                        L_0x0031:
                            java.lang.String r6 = "genre"
                            boolean r5 = r5.equals(r6)
                            if (r5 != 0) goto L_0x003b
                            goto L_0x00d0
                        L_0x003b:
                            java.lang.String r5 = r10
                            if (r5 == 0) goto L_0x00d5
                            long r5 = java.lang.Long.parseLong(r5)
                            org.videolan.medialibrary.interfaces.media.Genre r4 = r4.getGenre(r5)
                            org.videolan.medialibrary.interfaces.media.MediaWrapper[] r7 = r4.getTracks()
                            goto L_0x00d5
                        L_0x004d:
                            java.lang.String r6 = "album"
                            boolean r5 = r5.equals(r6)
                            if (r5 != 0) goto L_0x0057
                            goto L_0x00d0
                        L_0x0057:
                            java.lang.String r5 = r10
                            if (r5 == 0) goto L_0x00d5
                            long r5 = java.lang.Long.parseLong(r5)
                            org.videolan.medialibrary.interfaces.media.Album r4 = r4.getAlbum(r5)
                            org.videolan.medialibrary.interfaces.media.MediaWrapper[] r7 = r4.getTracks()
                            goto L_0x00d5
                        L_0x0069:
                            java.lang.String r6 = "video-group"
                            boolean r5 = r5.equals(r6)
                            if (r5 != 0) goto L_0x0072
                            goto L_0x00d0
                        L_0x0072:
                            java.lang.String r5 = r10
                            if (r5 == 0) goto L_0x00d5
                            long r5 = java.lang.Long.parseLong(r5)
                            org.videolan.medialibrary.interfaces.media.VideoGroup r7 = r4.getVideoGroup(r5)
                            int r12 = r7.mediaCount()
                            r13 = 0
                            r8 = 0
                            r9 = 0
                            r10 = 0
                            r11 = 0
                            org.videolan.medialibrary.interfaces.media.MediaWrapper[] r7 = r7.media(r8, r9, r10, r11, r12, r13)
                            goto L_0x00d5
                        L_0x008c:
                            java.lang.String r6 = "artist"
                            boolean r5 = r5.equals(r6)
                            if (r5 != 0) goto L_0x0095
                            goto L_0x00d0
                        L_0x0095:
                            java.lang.String r5 = r10
                            if (r5 == 0) goto L_0x00d5
                            long r5 = java.lang.Long.parseLong(r5)
                            org.videolan.medialibrary.interfaces.media.Artist r4 = r4.getArtist(r5)
                            org.videolan.medialibrary.interfaces.media.MediaWrapper[] r7 = r4.getTracks()
                            goto L_0x00d5
                        L_0x00a6:
                            java.lang.String r6 = "video-folder"
                            boolean r5 = r5.equals(r6)
                            if (r5 != 0) goto L_0x00af
                            goto L_0x00d0
                        L_0x00af:
                            java.lang.String r5 = r10
                            if (r5 == 0) goto L_0x00d5
                            int r6 = org.videolan.medialibrary.interfaces.media.Folder.TYPE_FOLDER_VIDEO
                            long r7 = java.lang.Long.parseLong(r5)
                            org.videolan.medialibrary.interfaces.media.Folder r9 = r4.getFolder(r6, r7)
                            int r10 = org.videolan.medialibrary.interfaces.media.Folder.TYPE_FOLDER_VIDEO
                            int r4 = org.videolan.medialibrary.interfaces.media.Folder.TYPE_FOLDER_VIDEO
                            int r15 = r9.mediaCount(r4)
                            r16 = 0
                            r11 = 0
                            r12 = 0
                            r13 = 0
                            r14 = 0
                            org.videolan.medialibrary.interfaces.media.MediaWrapper[] r7 = r9.media(r10, r11, r12, r13, r14, r15, r16)
                            goto L_0x00d5
                        L_0x00d0:
                            r5 = 0
                            org.videolan.medialibrary.interfaces.media.MediaWrapper[] r7 = r4.getAudio(r5, r5, r5, r5)
                        L_0x00d5:
                            java.lang.Object r4 = kotlin.Result.m1774constructorimpl(r7)
                            r2.resumeWith(r4)
                            r2 = r0
                            kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
                            r0.label = r3
                            java.lang.Object r2 = kotlinx.coroutines.YieldKt.yield(r2)
                            if (r2 != r1) goto L_0x00e8
                            return r1
                        L_0x00e8:
                            org.videolan.medialibrary.interfaces.Medialibrary r1 = r6
                            org.videolan.vlc.webserver.RemoteAccessRoutingKt$setupRouting$12$25$invokeSuspend$lambda$8$$inlined$getFromMl$1$1 r2 = r7
                            org.videolan.medialibrary.interfaces.Medialibrary$OnMedialibraryReadyListener r2 = (org.videolan.medialibrary.interfaces.Medialibrary.OnMedialibraryReadyListener) r2
                            r1.removeOnMedialibraryReadyListener(r2)
                            kotlin.Unit r1 = kotlin.Unit.INSTANCE
                            return r1
                        */
                        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.webserver.RemoteAccessRoutingKt$setupRouting$12$25$invokeSuspend$lambda$8$$inlined$getFromMl$1.AnonymousClass1.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
                    }
                }

                public void onMedialibraryReady() {
                    if (!cancellableContinuation2.isCompleted()) {
                        CoroutineScope coroutineScope = coroutineScope;
                        CoroutineStart coroutineStart = CoroutineStart.UNDISPATCHED;
                        final CancellableContinuation cancellableContinuation = cancellableContinuation2;
                        final Medialibrary medialibrary = medialibrary;
                        final String str = str7;
                        final String str2 = str8;
                        Job unused = BuildersKt__Builders_commonKt.launch$default(coroutineScope, (CoroutineContext) null, coroutineStart, new AnonymousClass1((Continuation) null), 1, (Object) null);
                    }
                }
            };
            cancellableContinuation.invokeOnCancellation(new Function1<Throwable, Unit>() {
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    invoke((Throwable) obj);
                    return Unit.INSTANCE;
                }

                public final void invoke(Throwable th) {
                    instance.removeOnMedialibraryReadyListener(r4);
                }
            });
            instance.addOnMedialibraryReadyListener(r4);
            ExtensionsKt.startMedialibrary$default(context, false, false, z, false, (CoroutineContextProvider) null, 24, (Object) null);
            obj2 = cancellableContinuationImpl.getResult();
            if (obj2 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                DebugProbesKt.probeCoroutineSuspended(continuation);
            }
            if (obj2 == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            Context context2 = (Context) this.L$2;
            Medialibrary medialibrary2 = (Medialibrary) this.L$1;
            CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            obj2 = obj;
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return obj2;
    }
}
