package org.videolan.vlc.webserver;

import android.content.Context;
import android.content.SharedPreferences;
import kotlin.Metadata;
import kotlin.Result;
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
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.YieldKt;
import org.videolan.medialibrary.interfaces.Medialibrary;
import org.videolan.medialibrary.interfaces.media.Folder;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.interfaces.media.VideoGroup;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.resources.util.ExtensionsKt;
import org.videolan.tools.CoroutineContextProvider;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.providers.medialibrary.VideoGroupsProviderKt;

@Metadata(d1 = {"\u0000\n\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0002H\u0001\"\u0006\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0002H@¨\u0006\u0003"}, d2 = {"<anonymous>", "T", "Lkotlinx/coroutines/CoroutineScope;", "org/videolan/resources/util/ExtensionsKt$getFromMl$2"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.webserver.RemoteAccessRoutingKt$setupRouting$12$2$invokeSuspend$$inlined$getFromMl$1", f = "RemoteAccessRouting.kt", i = {0, 0, 0}, l = {353}, m = "invokeSuspend", n = {"$this$withContext", "ml", "scan"}, s = {"L$0", "L$1", "I$0"})
/* compiled from: Extensions.kt */
public final class RemoteAccessRoutingKt$setupRouting$12$2$invokeSuspend$$inlined$getFromMl$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super MediaLibraryItem[]>, Object> {
    final /* synthetic */ long $folderId$inlined;
    final /* synthetic */ long $groupId$inlined;
    final /* synthetic */ Ref.ObjectRef $groupTitle$inlined;
    final /* synthetic */ int $grouping$inlined;
    final /* synthetic */ Context $this_getFromMl;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RemoteAccessRoutingKt$setupRouting$12$2$invokeSuspend$$inlined$getFromMl$1(Context context, Continuation continuation, long j, long j2, int i, Ref.ObjectRef objectRef) {
        super(2, continuation);
        this.$this_getFromMl = context;
        this.$groupId$inlined = j;
        this.$folderId$inlined = j2;
        this.$grouping$inlined = i;
        this.$groupTitle$inlined = objectRef;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        RemoteAccessRoutingKt$setupRouting$12$2$invokeSuspend$$inlined$getFromMl$1 remoteAccessRoutingKt$setupRouting$12$2$invokeSuspend$$inlined$getFromMl$1 = new RemoteAccessRoutingKt$setupRouting$12$2$invokeSuspend$$inlined$getFromMl$1(this.$this_getFromMl, continuation, this.$groupId$inlined, this.$folderId$inlined, this.$grouping$inlined, this.$groupTitle$inlined);
        remoteAccessRoutingKt$setupRouting$12$2$invokeSuspend$$inlined$getFromMl$1.L$0 = obj;
        return remoteAccessRoutingKt$setupRouting$12$2$invokeSuspend$$inlined$getFromMl$1;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super MediaLibraryItem[]> continuation) {
        return ((RemoteAccessRoutingKt$setupRouting$12$2$invokeSuspend$$inlined$getFromMl$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
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
                long j = this.$groupId$inlined;
                MediaWrapper[] mediaWrapperArr = null;
                if (j != 0) {
                    VideoGroup videoGroup = instance.getVideoGroup(j);
                    if (videoGroup != null) {
                        Intrinsics.checkNotNull(videoGroup);
                        Ref.ObjectRef objectRef = this.$groupTitle$inlined;
                        T title = videoGroup.getTitle();
                        Intrinsics.checkNotNullExpressionValue(title, "getTitle(...)");
                        objectRef.element = title;
                        mediaWrapperArr = videoGroup.media(0, false, false, false, videoGroup.mediaCount(), 0);
                    }
                    return (MediaLibraryItem[]) mediaWrapperArr;
                } else if (this.$folderId$inlined != 0) {
                    Folder folder = instance.getFolder(Folder.TYPE_FOLDER_VIDEO, this.$folderId$inlined);
                    if (folder != null) {
                        Intrinsics.checkNotNull(folder);
                        Ref.ObjectRef objectRef2 = this.$groupTitle$inlined;
                        T title2 = folder.getTitle();
                        Intrinsics.checkNotNullExpressionValue(title2, "getTitle(...)");
                        objectRef2.element = title2;
                        mediaWrapperArr = folder.media(Folder.TYPE_FOLDER_VIDEO, 0, false, false, false, folder.mediaCount(Folder.TYPE_FOLDER_VIDEO), 0);
                    }
                    return (MediaLibraryItem[]) mediaWrapperArr;
                } else {
                    int i2 = this.$grouping$inlined;
                    if (i2 == 0) {
                        return (MediaLibraryItem[]) instance.getVideos(0, false, false, false);
                    }
                    if (i2 != 1) {
                        VideoGroup[] videoGroups = instance.getVideoGroups(0, false, false, false, 100000, 0);
                        Intrinsics.checkNotNullExpressionValue(videoGroups, "getVideoGroups(...)");
                        return VideoGroupsProviderKt.sanitizeGroups(videoGroups);
                    }
                    return (MediaLibraryItem[]) instance.getFolders(Folder.TYPE_FOLDER_VIDEO, 0, false, false, false, 100000, 0);
                }
            } else {
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
                final long j2 = this.$groupId$inlined;
                final long j3 = this.$folderId$inlined;
                int i3 = this.$grouping$inlined;
                Ref.ObjectRef objectRef3 = this.$groupTitle$inlined;
                final CancellableContinuation cancellableContinuation2 = cancellableContinuation;
                int i4 = i3;
                final Medialibrary medialibrary = instance;
                final AnonymousClass1 r0 = r4;
                final int i5 = i4;
                CancellableContinuationImpl cancellableContinuationImpl2 = cancellableContinuationImpl;
                final Ref.ObjectRef objectRef4 = objectRef3;
                AnonymousClass1 r4 = new Medialibrary.OnMedialibraryReadyListener() {
                    public void onMedialibraryIdle() {
                    }

                    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003H@¨\u0006\u0004"}, d2 = {"<anonymous>", "", "T", "Lkotlinx/coroutines/CoroutineScope;", "org/videolan/resources/util/ExtensionsKt$getFromMl$2$1$listener$1$onMedialibraryReady$1"}, k = 3, mv = {1, 9, 0}, xi = 48)
                    @DebugMetadata(c = "org.videolan.vlc.webserver.RemoteAccessRoutingKt$setupRouting$12$2$invokeSuspend$$inlined$getFromMl$1$1$1", f = "RemoteAccessRouting.kt", i = {}, l = {46}, m = "invokeSuspend", n = {}, s = {})
                    /* renamed from: org.videolan.vlc.webserver.RemoteAccessRoutingKt$setupRouting$12$2$invokeSuspend$$inlined$getFromMl$1$1$1  reason: invalid class name */
                    /* compiled from: Extensions.kt */
                    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        int label;

                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                            return new AnonymousClass1(cancellableContinuation, medialibrary, this, continuation, j, j2, i, objectRef);
                        }

                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                        }

                        public final Object invokeSuspend(Object obj) {
                            MediaLibraryItem[] mediaLibraryItemArr;
                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            int i = this.label;
                            if (i == 0) {
                                ResultKt.throwOnFailure(obj);
                                Continuation continuation = cancellableContinuation;
                                Result.Companion companion = Result.Companion;
                                Medialibrary medialibrary = medialibrary;
                                long j = j;
                                MediaWrapper[] mediaWrapperArr = null;
                                if (j != 0) {
                                    VideoGroup videoGroup = medialibrary.getVideoGroup(j);
                                    if (videoGroup != null) {
                                        Intrinsics.checkNotNull(videoGroup);
                                        Ref.ObjectRef objectRef = objectRef;
                                        T title = videoGroup.getTitle();
                                        Intrinsics.checkNotNullExpressionValue(title, "getTitle(...)");
                                        objectRef.element = title;
                                        mediaWrapperArr = videoGroup.media(0, false, false, false, videoGroup.mediaCount(), 0);
                                    }
                                    mediaLibraryItemArr = (MediaLibraryItem[]) mediaWrapperArr;
                                } else if (j2 != 0) {
                                    Folder folder = medialibrary.getFolder(Folder.TYPE_FOLDER_VIDEO, j2);
                                    if (folder != null) {
                                        Intrinsics.checkNotNull(folder);
                                        Ref.ObjectRef objectRef2 = objectRef;
                                        T title2 = folder.getTitle();
                                        Intrinsics.checkNotNullExpressionValue(title2, "getTitle(...)");
                                        objectRef2.element = title2;
                                        mediaWrapperArr = folder.media(Folder.TYPE_FOLDER_VIDEO, 0, false, false, false, folder.mediaCount(Folder.TYPE_FOLDER_VIDEO), 0);
                                    }
                                    mediaLibraryItemArr = (MediaLibraryItem[]) mediaWrapperArr;
                                } else {
                                    int i2 = i;
                                    if (i2 == 0) {
                                        mediaLibraryItemArr = (MediaLibraryItem[]) medialibrary.getVideos(0, false, false, false);
                                    } else if (i2 != 1) {
                                        VideoGroup[] videoGroups = medialibrary.getVideoGroups(0, false, false, false, 100000, 0);
                                        Intrinsics.checkNotNullExpressionValue(videoGroups, "getVideoGroups(...)");
                                        mediaLibraryItemArr = VideoGroupsProviderKt.sanitizeGroups(videoGroups);
                                    } else {
                                        mediaLibraryItemArr = (MediaLibraryItem[]) medialibrary.getFolders(Folder.TYPE_FOLDER_VIDEO, 0, false, false, false, 100000, 0);
                                    }
                                }
                                continuation.resumeWith(Result.m1774constructorimpl(mediaLibraryItemArr));
                                this.label = 1;
                                if (YieldKt.yield(this) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                            } else if (i == 1) {
                                ResultKt.throwOnFailure(obj);
                            } else {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            medialibrary.removeOnMedialibraryReadyListener(this);
                            return Unit.INSTANCE;
                        }
                    }

                    public void onMedialibraryReady() {
                        if (!cancellableContinuation2.isCompleted()) {
                            CoroutineScope coroutineScope = coroutineScope;
                            CoroutineStart coroutineStart = CoroutineStart.UNDISPATCHED;
                            final CancellableContinuation cancellableContinuation = cancellableContinuation2;
                            final Medialibrary medialibrary = medialibrary;
                            final long j = j2;
                            final long j2 = j3;
                            final int i = i5;
                            final Ref.ObjectRef objectRef = objectRef4;
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
                        instance.removeOnMedialibraryReadyListener(r0);
                    }
                });
                instance.addOnMedialibraryReadyListener(r0);
                ExtensionsKt.startMedialibrary$default(context, false, false, z, false, (CoroutineContextProvider) null, 24, (Object) null);
                obj2 = cancellableContinuationImpl2.getResult();
                if (obj2 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    DebugProbesKt.probeCoroutineSuspended(continuation);
                }
                if (obj2 == coroutine_suspended) {
                    return coroutine_suspended;
                }
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
