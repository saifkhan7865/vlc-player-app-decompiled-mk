package org.videolan.vlc.webserver;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
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
import kotlin.text.StringsKt;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.videolan.medialibrary.MLServiceLocator;
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
@DebugMetadata(c = "org.videolan.vlc.webserver.RemoteAccessRoutingKt$setupRouting$12$24$invokeSuspend$lambda$1$$inlined$getFromMl$1", f = "RemoteAccessRouting.kt", i = {0, 0, 0}, l = {353}, m = "invokeSuspend", n = {"$this$withContext", "ml", "scan"}, s = {"L$0", "L$1", "I$0"})
/* compiled from: Extensions.kt */
public final class RemoteAccessRoutingKt$setupRouting$12$24$invokeSuspend$lambda$1$$inlined$getFromMl$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super MediaWrapper[]>, Object> {
    final /* synthetic */ String $id$inlined;
    final /* synthetic */ String $path$inlined;
    final /* synthetic */ Context $this_getFromMl;
    final /* synthetic */ String $type$inlined;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RemoteAccessRoutingKt$setupRouting$12$24$invokeSuspend$lambda$1$$inlined$getFromMl$1(Context context, Continuation continuation, String str, String str2, String str3) {
        super(2, continuation);
        this.$this_getFromMl = context;
        this.$path$inlined = str;
        this.$type$inlined = str2;
        this.$id$inlined = str3;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        RemoteAccessRoutingKt$setupRouting$12$24$invokeSuspend$lambda$1$$inlined$getFromMl$1 remoteAccessRoutingKt$setupRouting$12$24$invokeSuspend$lambda$1$$inlined$getFromMl$1 = new RemoteAccessRoutingKt$setupRouting$12$24$invokeSuspend$lambda$1$$inlined$getFromMl$1(this.$this_getFromMl, continuation, this.$path$inlined, this.$type$inlined, this.$id$inlined);
        remoteAccessRoutingKt$setupRouting$12$24$invokeSuspend$lambda$1$$inlined$getFromMl$1.L$0 = obj;
        return remoteAccessRoutingKt$setupRouting$12$24$invokeSuspend$lambda$1$$inlined$getFromMl$1;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super MediaWrapper[]> continuation) {
        return ((RemoteAccessRoutingKt$setupRouting$12$24$invokeSuspend$lambda$1$$inlined$getFromMl$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
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
                String str = this.$path$inlined;
                if (str == null || !(!StringsKt.isBlank(str))) {
                    String str2 = this.$type$inlined;
                    switch (str2.hashCode()) {
                        case -1797815808:
                            if (str2.equals("video-folder")) {
                                Folder folder = instance.getFolder(Folder.TYPE_FOLDER_VIDEO, Long.parseLong(this.$id$inlined));
                                return folder.media(Folder.TYPE_FOLDER_VIDEO, 0, false, false, false, folder.mediaCount(Folder.TYPE_FOLDER_VIDEO), 0);
                            }
                            break;
                        case -1409097913:
                            if (str2.equals(ArtworkProvider.ARTIST)) {
                                return instance.getArtist(Long.parseLong(this.$id$inlined)).getTracks();
                            }
                            break;
                        case -56977747:
                            if (str2.equals("video-group")) {
                                VideoGroup videoGroup = instance.getVideoGroup(Long.parseLong(this.$id$inlined));
                                return videoGroup.media(0, false, false, false, videoGroup.mediaCount(), 0);
                            }
                            break;
                        case 92896879:
                            if (str2.equals(ArtworkProvider.ALBUM)) {
                                return instance.getAlbum(Long.parseLong(this.$id$inlined)).getTracks();
                            }
                            break;
                        case 98240899:
                            if (str2.equals("genre")) {
                                return instance.getGenre(Long.parseLong(this.$id$inlined)).getTracks();
                            }
                            break;
                        case 1879474642:
                            if (str2.equals(ArtworkProvider.PLAYLIST)) {
                                return instance.getPlaylist(Long.parseLong(this.$id$inlined), false, false).getTracks();
                            }
                            break;
                    }
                    return new MediaWrapper[]{instance.getMedia(Long.parseLong(this.$id$inlined))};
                }
                return new MediaWrapper[]{MLServiceLocator.getAbstractMediaWrapper(Uri.parse(this.$path$inlined))};
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
            final String str3 = this.$path$inlined;
            final String str4 = this.$type$inlined;
            final String str5 = this.$id$inlined;
            final CancellableContinuation cancellableContinuation2 = cancellableContinuation;
            final Medialibrary medialibrary = instance;
            final AnonymousClass1 r4 = new Medialibrary.OnMedialibraryReadyListener() {
                public void onMedialibraryIdle() {
                }

                @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003H@¨\u0006\u0004"}, d2 = {"<anonymous>", "", "T", "Lkotlinx/coroutines/CoroutineScope;", "org/videolan/resources/util/ExtensionsKt$getFromMl$2$1$listener$1$onMedialibraryReady$1"}, k = 3, mv = {1, 9, 0}, xi = 48)
                @DebugMetadata(c = "org.videolan.vlc.webserver.RemoteAccessRoutingKt$setupRouting$12$24$invokeSuspend$lambda$1$$inlined$getFromMl$1$1$1", f = "RemoteAccessRouting.kt", i = {}, l = {46}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: org.videolan.vlc.webserver.RemoteAccessRoutingKt$setupRouting$12$24$invokeSuspend$lambda$1$$inlined$getFromMl$1$1$1  reason: invalid class name */
                /* compiled from: Extensions.kt */
                public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    int label;

                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                        return new AnonymousClass1(cancellableContinuation, medialibrary, this, continuation, str, str2, str3);
                    }

                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    /* JADX WARNING: Can't fix incorrect switch cases order */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public final java.lang.Object invokeSuspend(java.lang.Object r15) {
                        /*
                            r14 = this;
                            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                            int r1 = r14.label
                            r2 = 1
                            if (r1 == 0) goto L_0x0018
                            if (r1 != r2) goto L_0x0010
                            kotlin.ResultKt.throwOnFailure(r15)
                            goto L_0x011c
                        L_0x0010:
                            java.lang.IllegalStateException r15 = new java.lang.IllegalStateException
                            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                            r15.<init>(r0)
                            throw r15
                        L_0x0018:
                            kotlin.ResultKt.throwOnFailure(r15)
                            kotlinx.coroutines.CancellableContinuation r15 = r5
                            kotlin.coroutines.Continuation r15 = (kotlin.coroutines.Continuation) r15
                            kotlin.Result$Companion r1 = kotlin.Result.Companion
                            org.videolan.medialibrary.interfaces.Medialibrary r1 = r6
                            java.lang.String r3 = r9
                            r4 = 0
                            if (r3 == 0) goto L_0x0041
                            java.lang.CharSequence r3 = (java.lang.CharSequence) r3
                            boolean r3 = kotlin.text.StringsKt.isBlank(r3)
                            r3 = r3 ^ r2
                            if (r3 != r2) goto L_0x0041
                            org.videolan.medialibrary.interfaces.media.MediaWrapper[] r1 = new org.videolan.medialibrary.interfaces.media.MediaWrapper[r2]
                            java.lang.String r3 = r9
                            android.net.Uri r3 = android.net.Uri.parse(r3)
                            org.videolan.medialibrary.interfaces.media.MediaWrapper r3 = org.videolan.medialibrary.MLServiceLocator.getAbstractMediaWrapper((android.net.Uri) r3)
                            r1[r4] = r3
                            goto L_0x0109
                        L_0x0041:
                            java.lang.String r3 = r10
                            int r5 = r3.hashCode()
                            switch(r5) {
                                case -1797815808: goto L_0x00d3;
                                case -1409097913: goto L_0x00bb;
                                case -56977747: goto L_0x009a;
                                case 92896879: goto L_0x0080;
                                case 98240899: goto L_0x0066;
                                case 1879474642: goto L_0x004c;
                                default: goto L_0x004a;
                            }
                        L_0x004a:
                            goto L_0x00fa
                        L_0x004c:
                            java.lang.String r5 = "playlist"
                            boolean r3 = r3.equals(r5)
                            if (r3 != 0) goto L_0x0056
                            goto L_0x00fa
                        L_0x0056:
                            java.lang.String r3 = r11
                            long r5 = java.lang.Long.parseLong(r3)
                            org.videolan.medialibrary.interfaces.media.Playlist r1 = r1.getPlaylist(r5, r4, r4)
                            org.videolan.medialibrary.interfaces.media.MediaWrapper[] r1 = r1.getTracks()
                            goto L_0x0109
                        L_0x0066:
                            java.lang.String r5 = "genre"
                            boolean r3 = r3.equals(r5)
                            if (r3 != 0) goto L_0x0070
                            goto L_0x00fa
                        L_0x0070:
                            java.lang.String r3 = r11
                            long r3 = java.lang.Long.parseLong(r3)
                            org.videolan.medialibrary.interfaces.media.Genre r1 = r1.getGenre(r3)
                            org.videolan.medialibrary.interfaces.media.MediaWrapper[] r1 = r1.getTracks()
                            goto L_0x0109
                        L_0x0080:
                            java.lang.String r5 = "album"
                            boolean r3 = r3.equals(r5)
                            if (r3 != 0) goto L_0x008a
                            goto L_0x00fa
                        L_0x008a:
                            java.lang.String r3 = r11
                            long r3 = java.lang.Long.parseLong(r3)
                            org.videolan.medialibrary.interfaces.media.Album r1 = r1.getAlbum(r3)
                            org.videolan.medialibrary.interfaces.media.MediaWrapper[] r1 = r1.getTracks()
                            goto L_0x0109
                        L_0x009a:
                            java.lang.String r5 = "video-group"
                            boolean r3 = r3.equals(r5)
                            if (r3 != 0) goto L_0x00a3
                            goto L_0x00fa
                        L_0x00a3:
                            java.lang.String r3 = r11
                            long r3 = java.lang.Long.parseLong(r3)
                            org.videolan.medialibrary.interfaces.media.VideoGroup r5 = r1.getVideoGroup(r3)
                            int r10 = r5.mediaCount()
                            r11 = 0
                            r6 = 0
                            r7 = 0
                            r8 = 0
                            r9 = 0
                            org.videolan.medialibrary.interfaces.media.MediaWrapper[] r1 = r5.media(r6, r7, r8, r9, r10, r11)
                            goto L_0x0109
                        L_0x00bb:
                            java.lang.String r5 = "artist"
                            boolean r3 = r3.equals(r5)
                            if (r3 != 0) goto L_0x00c4
                            goto L_0x00fa
                        L_0x00c4:
                            java.lang.String r3 = r11
                            long r3 = java.lang.Long.parseLong(r3)
                            org.videolan.medialibrary.interfaces.media.Artist r1 = r1.getArtist(r3)
                            org.videolan.medialibrary.interfaces.media.MediaWrapper[] r1 = r1.getTracks()
                            goto L_0x0109
                        L_0x00d3:
                            java.lang.String r5 = "video-folder"
                            boolean r3 = r3.equals(r5)
                            if (r3 != 0) goto L_0x00dc
                            goto L_0x00fa
                        L_0x00dc:
                            int r3 = org.videolan.medialibrary.interfaces.media.Folder.TYPE_FOLDER_VIDEO
                            java.lang.String r4 = r11
                            long r4 = java.lang.Long.parseLong(r4)
                            org.videolan.medialibrary.interfaces.media.Folder r6 = r1.getFolder(r3, r4)
                            int r7 = org.videolan.medialibrary.interfaces.media.Folder.TYPE_FOLDER_VIDEO
                            int r1 = org.videolan.medialibrary.interfaces.media.Folder.TYPE_FOLDER_VIDEO
                            int r12 = r6.mediaCount(r1)
                            r13 = 0
                            r8 = 0
                            r9 = 0
                            r10 = 0
                            r11 = 0
                            org.videolan.medialibrary.interfaces.media.MediaWrapper[] r1 = r6.media(r7, r8, r9, r10, r11, r12, r13)
                            goto L_0x0109
                        L_0x00fa:
                            org.videolan.medialibrary.interfaces.media.MediaWrapper[] r3 = new org.videolan.medialibrary.interfaces.media.MediaWrapper[r2]
                            java.lang.String r5 = r11
                            long r5 = java.lang.Long.parseLong(r5)
                            org.videolan.medialibrary.interfaces.media.MediaWrapper r1 = r1.getMedia((long) r5)
                            r3[r4] = r1
                            r1 = r3
                        L_0x0109:
                            java.lang.Object r1 = kotlin.Result.m1774constructorimpl(r1)
                            r15.resumeWith(r1)
                            r15 = r14
                            kotlin.coroutines.Continuation r15 = (kotlin.coroutines.Continuation) r15
                            r14.label = r2
                            java.lang.Object r15 = kotlinx.coroutines.YieldKt.yield(r15)
                            if (r15 != r0) goto L_0x011c
                            return r0
                        L_0x011c:
                            org.videolan.medialibrary.interfaces.Medialibrary r15 = r6
                            org.videolan.vlc.webserver.RemoteAccessRoutingKt$setupRouting$12$24$invokeSuspend$lambda$1$$inlined$getFromMl$1$1 r0 = r7
                            org.videolan.medialibrary.interfaces.Medialibrary$OnMedialibraryReadyListener r0 = (org.videolan.medialibrary.interfaces.Medialibrary.OnMedialibraryReadyListener) r0
                            r15.removeOnMedialibraryReadyListener(r0)
                            kotlin.Unit r15 = kotlin.Unit.INSTANCE
                            return r15
                        */
                        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.webserver.RemoteAccessRoutingKt$setupRouting$12$24$invokeSuspend$lambda$1$$inlined$getFromMl$1.AnonymousClass1.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
                    }
                }

                public void onMedialibraryReady() {
                    if (!cancellableContinuation2.isCompleted()) {
                        CoroutineScope coroutineScope = coroutineScope;
                        CoroutineStart coroutineStart = CoroutineStart.UNDISPATCHED;
                        final CancellableContinuation cancellableContinuation = cancellableContinuation2;
                        final Medialibrary medialibrary = medialibrary;
                        final String str = str3;
                        final String str2 = str4;
                        final String str3 = str5;
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
