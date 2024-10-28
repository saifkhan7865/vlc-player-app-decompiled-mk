package org.videolan.vlc.viewmodels.mobile;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import androidx.lifecycle.ViewModelProvider;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import org.videolan.medialibrary.interfaces.media.Album;
import org.videolan.medialibrary.interfaces.media.Playlist;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.vlc.ArtworkProvider;
import org.videolan.vlc.providers.medialibrary.MedialibraryProvider;
import org.videolan.vlc.providers.medialibrary.TracksProvider;
import org.videolan.vlc.viewmodels.MedialibraryViewModel;

@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001&B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u001a\u001a\u00020\u001bH\u0016J\u000e\u0010\u001c\u001a\u00020\u001bH@¢\u0006\u0002\u0010\u001dJ\u001e\u0010\u001e\u001a\u00020\u001b2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"H@¢\u0006\u0002\u0010#J\u0010\u0010$\u001a\u0004\u0018\u00010%H@¢\u0006\u0002\u0010\u001dR\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u00058F¢\u0006\u0006\u001a\u0004\b\b\u0010\tR \u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u000bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR$\u0010\u0010\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00050\u00120\u0011X\u0004¢\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0016\u001a\u00020\u0017¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019¨\u0006'"}, d2 = {"Lorg/videolan/vlc/viewmodels/mobile/PlaylistViewModel;", "Lorg/videolan/vlc/viewmodels/MedialibraryViewModel;", "context", "Landroid/content/Context;", "initialPlaylist", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "(Landroid/content/Context;Lorg/videolan/medialibrary/media/MediaLibraryItem;)V", "playlist", "getPlaylist", "()Lorg/videolan/medialibrary/media/MediaLibraryItem;", "playlistLiveData", "Landroidx/lifecycle/MutableLiveData;", "getPlaylistLiveData", "()Landroidx/lifecycle/MutableLiveData;", "setPlaylistLiveData", "(Landroidx/lifecycle/MutableLiveData;)V", "providers", "", "Lorg/videolan/vlc/providers/medialibrary/MedialibraryProvider;", "getProviders", "()[Lorg/videolan/vlc/providers/medialibrary/MedialibraryProvider;", "[Lorg/videolan/vlc/providers/medialibrary/MedialibraryProvider;", "tracksProvider", "Lorg/videolan/vlc/providers/medialibrary/TracksProvider;", "getTracksProvider", "()Lorg/videolan/vlc/providers/medialibrary/TracksProvider;", "refresh", "", "refreshPlaylistItem", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "rename", "media", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "name", "", "(Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toggleFavorite", "", "Factory", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PlaylistViewModel.kt */
public final class PlaylistViewModel extends MedialibraryViewModel {
    /* access modifiers changed from: private */
    public final MediaLibraryItem initialPlaylist;
    private MutableLiveData<MediaLibraryItem> playlistLiveData = new MutableLiveData<>();
    private final MedialibraryProvider<? extends MediaLibraryItem>[] providers;
    private final TracksProvider tracksProvider;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PlaylistViewModel(Context context, MediaLibraryItem mediaLibraryItem) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(mediaLibraryItem, "initialPlaylist");
        this.initialPlaylist = mediaLibraryItem;
        TracksProvider tracksProvider2 = new TracksProvider(mediaLibraryItem, context, this);
        this.tracksProvider = tracksProvider2;
        this.providers = new MedialibraryProvider[]{tracksProvider2};
        if (mediaLibraryItem instanceof Playlist) {
            watchPlaylists();
            watchMedia();
        } else if (mediaLibraryItem instanceof Album) {
            watchAlbums();
            watchMedia();
        } else {
            watchMedia();
        }
        ViewModel viewModel = this;
        registerCallBacks(ViewModelKt.getViewModelScope(viewModel), new Function0<Unit>(this) {
            final /* synthetic */ PlaylistViewModel this$0;

            {
                this.this$0 = r1;
            }

            public final void invoke() {
                this.this$0.refresh();
            }
        });
        Job unused = BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(viewModel), (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass2(this, (Continuation<? super AnonymousClass2>) null), 3, (Object) null);
    }

    public final TracksProvider getTracksProvider() {
        return this.tracksProvider;
    }

    public MedialibraryProvider<? extends MediaLibraryItem>[] getProviders() {
        return this.providers;
    }

    public final MutableLiveData<MediaLibraryItem> getPlaylistLiveData() {
        return this.playlistLiveData;
    }

    public final void setPlaylistLiveData(MutableLiveData<MediaLibraryItem> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.playlistLiveData = mutableLiveData;
    }

    public final MediaLibraryItem getPlaylist() {
        return this.playlistLiveData.getValue();
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "org.videolan.vlc.viewmodels.mobile.PlaylistViewModel$2", f = "PlaylistViewModel.kt", i = {}, l = {64}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: org.videolan.vlc.viewmodels.mobile.PlaylistViewModel$2  reason: invalid class name */
    /* compiled from: PlaylistViewModel.kt */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        final /* synthetic */ PlaylistViewModel this$0;

        {
            this.this$0 = r1;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.this$0, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (this.this$0.refreshPlaylistItem(this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i == 1) {
                ResultKt.throwOnFailure(obj);
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }

    public void refresh() {
        Job unused = BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), (CoroutineContext) null, (CoroutineStart) null, new PlaylistViewModel$refresh$1(this, (Continuation<? super PlaylistViewModel$refresh$1>) null), 3, (Object) null);
    }

    /* access modifiers changed from: private */
    public final Object refreshPlaylistItem(Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new PlaylistViewModel$refreshPlaylistItem$2(this, (Continuation<? super PlaylistViewModel$refreshPlaylistItem$2>) null), continuation);
        return withContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J%\u0010\u000b\u001a\u0002H\f\"\b\b\u0000\u0010\f*\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\f0\u000fH\u0016¢\u0006\u0002\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0011"}, d2 = {"Lorg/videolan/vlc/viewmodels/mobile/PlaylistViewModel$Factory;", "Landroidx/lifecycle/ViewModelProvider$NewInstanceFactory;", "context", "Landroid/content/Context;", "playlist", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "(Landroid/content/Context;Lorg/videolan/medialibrary/media/MediaLibraryItem;)V", "getContext", "()Landroid/content/Context;", "getPlaylist", "()Lorg/videolan/medialibrary/media/MediaLibraryItem;", "create", "T", "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: PlaylistViewModel.kt */
    public static final class Factory extends ViewModelProvider.NewInstanceFactory {
        private final Context context;
        private final MediaLibraryItem playlist;

        public Factory(Context context2, MediaLibraryItem mediaLibraryItem) {
            Intrinsics.checkNotNullParameter(context2, "context");
            Intrinsics.checkNotNullParameter(mediaLibraryItem, ArtworkProvider.PLAYLIST);
            this.context = context2;
            this.playlist = mediaLibraryItem;
        }

        public final Context getContext() {
            return this.context;
        }

        public final MediaLibraryItem getPlaylist() {
            return this.playlist;
        }

        public <T extends ViewModel> T create(Class<T> cls) {
            Intrinsics.checkNotNullParameter(cls, "modelClass");
            Context applicationContext = this.context.getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
            return (ViewModel) new PlaylistViewModel(applicationContext, this.playlist);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object rename(org.videolan.medialibrary.interfaces.media.MediaWrapper r6, java.lang.String r7, kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            r5 = this;
            boolean r0 = r8 instanceof org.videolan.vlc.viewmodels.mobile.PlaylistViewModel$rename$1
            if (r0 == 0) goto L_0x0014
            r0 = r8
            org.videolan.vlc.viewmodels.mobile.PlaylistViewModel$rename$1 r0 = (org.videolan.vlc.viewmodels.mobile.PlaylistViewModel$rename$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            org.videolan.vlc.viewmodels.mobile.PlaylistViewModel$rename$1 r0 = new org.videolan.vlc.viewmodels.mobile.PlaylistViewModel$rename$1
            r0.<init>(r5, r8)
        L_0x0019:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0036
            if (r2 != r3) goto L_0x002e
            java.lang.Object r6 = r0.L$0
            org.videolan.vlc.viewmodels.mobile.PlaylistViewModel r6 = (org.videolan.vlc.viewmodels.mobile.PlaylistViewModel) r6
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x0053
        L_0x002e:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0036:
            kotlin.ResultKt.throwOnFailure(r8)
            kotlinx.coroutines.CoroutineDispatcher r8 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r8 = (kotlin.coroutines.CoroutineContext) r8
            org.videolan.vlc.viewmodels.mobile.PlaylistViewModel$rename$2 r2 = new org.videolan.vlc.viewmodels.mobile.PlaylistViewModel$rename$2
            r4 = 0
            r2.<init>(r6, r7, r4)
            kotlin.jvm.functions.Function2 r2 = (kotlin.jvm.functions.Function2) r2
            r0.L$0 = r5
            r0.label = r3
            java.lang.Object r6 = kotlinx.coroutines.BuildersKt.withContext(r8, r2, r0)
            if (r6 != r1) goto L_0x0052
            return r1
        L_0x0052:
            r6 = r5
        L_0x0053:
            r6.refresh()
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.viewmodels.mobile.PlaylistViewModel.rename(org.videolan.medialibrary.interfaces.media.MediaWrapper, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final Object toggleFavorite(Continuation<? super Boolean> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new PlaylistViewModel$toggleFavorite$2(this, (Continuation<? super PlaylistViewModel$toggleFavorite$2>) null), continuation);
    }
}
