package org.videolan.vlc;

import android.os.Bundle;
import androidx.lifecycle.LifecycleOwnerKt;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import org.videolan.medialibrary.interfaces.media.Album;
import org.videolan.medialibrary.interfaces.media.Artist;
import org.videolan.medialibrary.interfaces.media.Genre;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.interfaces.media.Playlist;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.medialibrary.media.SearchAggregate;
import org.videolan.tools.Settings;
import org.videolan.vlc.gui.helpers.MediaComparators;
import org.videolan.vlc.util.KextensionsKt;
import org.videolan.vlc.util.VoiceSearchParams;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.MediaSessionCallback$onPlayFromSearch$1", f = "MediaSessionCallback.kt", i = {0}, l = {323}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"})
/* compiled from: MediaSessionCallback.kt */
final class MediaSessionCallback$onPlayFromSearch$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Bundle $extras;
    final /* synthetic */ String $query;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ MediaSessionCallback this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MediaSessionCallback$onPlayFromSearch$1(MediaSessionCallback mediaSessionCallback, String str, Bundle bundle, Continuation<? super MediaSessionCallback$onPlayFromSearch$1> continuation) {
        super(2, continuation);
        this.this$0 = mediaSessionCallback;
        this.$query = str;
        this.$extras = bundle;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        MediaSessionCallback$onPlayFromSearch$1 mediaSessionCallback$onPlayFromSearch$1 = new MediaSessionCallback$onPlayFromSearch$1(this.this$0, this.$query, this.$extras, continuation);
        mediaSessionCallback$onPlayFromSearch$1.L$0 = obj;
        return mediaSessionCallback$onPlayFromSearch$1;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MediaSessionCallback$onPlayFromSearch$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        Object obj2;
        MediaLibraryItem[] mediaLibraryItemArr;
        String str;
        SearchAggregate search;
        Object obj3;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
            if (!CoroutineScopeKt.isActive(coroutineScope2)) {
                return Unit.INSTANCE;
            }
            this.L$0 = coroutineScope2;
            this.label = 1;
            if (KextensionsKt.awaitMedialibraryStarted(this.this$0.playbackService, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            coroutineScope = coroutineScope2;
        } else if (i == 1) {
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        String str2 = this.$query;
        if (str2 == null) {
            str2 = "";
        }
        final VoiceSearchParams voiceSearchParams = new VoiceSearchParams(str2, this.$extras);
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        if (voiceSearchParams.isAny()) {
            obj2 = this.this$0.playbackService.getMedialibrary$vlc_android_release().getAudio();
        } else {
            obj2 = voiceSearchParams.isSongFocus() ? this.this$0.playbackService.getMedialibrary$vlc_android_release().searchMedia(voiceSearchParams.getSong()) : null;
        }
        objectRef.element = obj2;
        MediaWrapper[] mediaWrapperArr = (MediaWrapper[]) objectRef.element;
        if (mediaWrapperArr != null) {
            ArraysKt.sortWith(mediaWrapperArr, MediaComparators.INSTANCE.getANDROID_AUTO());
        }
        if (voiceSearchParams.isAlbumFocus()) {
            mediaLibraryItemArr = (MediaLibraryItem[]) this.this$0.playbackService.getMedialibrary$vlc_android_release().searchAlbum(voiceSearchParams.getAlbum());
        } else if (voiceSearchParams.isGenreFocus()) {
            mediaLibraryItemArr = (MediaLibraryItem[]) this.this$0.playbackService.getMedialibrary$vlc_android_release().searchGenre(voiceSearchParams.getGenre());
        } else if (voiceSearchParams.isArtistFocus()) {
            mediaLibraryItemArr = (MediaLibraryItem[]) this.this$0.playbackService.getMedialibrary$vlc_android_release().searchArtist(voiceSearchParams.getArtist());
        } else {
            mediaLibraryItemArr = voiceSearchParams.isPlaylistFocus() ? (MediaLibraryItem[]) this.this$0.playbackService.getMedialibrary$vlc_android_release().searchPlaylist(voiceSearchParams.getPlaylist(), Playlist.Type.All, Settings.INSTANCE.getIncludeMissing(), false) : null;
        }
        if (!CoroutineScopeKt.isActive(coroutineScope)) {
            return Unit.INSTANCE;
        }
        Object[] objArr = (Object[]) objectRef.element;
        if ((objArr == null || objArr.length == 0) && ((mediaLibraryItemArr == null || mediaLibraryItemArr.length == 0) && (str = this.$query) != null && str.length() > 0 && (search = this.this$0.playbackService.getMedialibrary$vlc_android_release().search(this.$query, Settings.INSTANCE.getIncludeMissing(), false)) != null)) {
            Album[] albums = search.getAlbums();
            if (albums == null || albums.length == 0) {
                Artist[] artists = search.getArtists();
                if (artists == null || artists.length == 0) {
                    Playlist[] playlists = search.getPlaylists();
                    if (playlists == null || playlists.length == 0) {
                        Genre[] genres = search.getGenres();
                        if (genres == null || genres.length == 0) {
                            obj3 = null;
                        } else {
                            Genre[] genres2 = search.getGenres();
                            Intrinsics.checkNotNull(genres2);
                            Collection arrayList = new ArrayList();
                            for (Genre tracks : genres2) {
                                MediaWrapper[] tracks2 = tracks.getTracks();
                                Intrinsics.checkNotNullExpressionValue(tracks2, "getTracks(...)");
                                CollectionsKt.addAll(arrayList, ArraysKt.toList((T[]) (Object[]) tracks2));
                            }
                            obj3 = (MediaWrapper[]) ((List) arrayList).toArray(new MediaWrapper[0]);
                        }
                    } else {
                        Playlist[] playlists2 = search.getPlaylists();
                        Intrinsics.checkNotNull(playlists2);
                        Collection arrayList2 = new ArrayList();
                        for (Playlist tracks3 : playlists2) {
                            MediaWrapper[] tracks4 = tracks3.getTracks();
                            Intrinsics.checkNotNullExpressionValue(tracks4, "getTracks(...)");
                            CollectionsKt.addAll(arrayList2, ArraysKt.toList((T[]) (Object[]) tracks4));
                        }
                        obj3 = (MediaWrapper[]) ((List) arrayList2).toArray(new MediaWrapper[0]);
                    }
                } else {
                    Artist[] artists2 = search.getArtists();
                    Intrinsics.checkNotNull(artists2);
                    Collection arrayList3 = new ArrayList();
                    for (Artist tracks5 : artists2) {
                        MediaWrapper[] tracks6 = tracks5.getTracks();
                        Intrinsics.checkNotNullExpressionValue(tracks6, "getTracks(...)");
                        CollectionsKt.addAll(arrayList3, ArraysKt.toList((T[]) (Object[]) tracks6));
                    }
                    obj3 = (MediaWrapper[]) ((List) arrayList3).toArray(new MediaWrapper[0]);
                }
            } else {
                Album[] albums2 = search.getAlbums();
                Intrinsics.checkNotNull(albums2);
                Collection arrayList4 = new ArrayList();
                for (Album tracks7 : albums2) {
                    MediaWrapper[] tracks8 = tracks7.getTracks();
                    Intrinsics.checkNotNullExpressionValue(tracks8, "getTracks(...)");
                    CollectionsKt.addAll(arrayList4, ArraysKt.toList((T[]) (Object[]) tracks8));
                }
                obj3 = (MediaWrapper[]) ((List) arrayList4).toArray(new MediaWrapper[0]);
            }
            objectRef.element = obj3;
        }
        if (!CoroutineScopeKt.isActive(coroutineScope)) {
            return Unit.INSTANCE;
        }
        Object[] objArr2 = (Object[]) objectRef.element;
        if (!((objArr2 != null && objArr2.length != 0) || mediaLibraryItemArr == null || mediaLibraryItemArr.length == 0)) {
            Collection arrayList5 = new ArrayList();
            for (MediaLibraryItem tracks9 : mediaLibraryItemArr) {
                MediaWrapper[] tracks10 = tracks9.getTracks();
                Intrinsics.checkNotNullExpressionValue(tracks10, "getTracks(...)");
                CollectionsKt.addAll(arrayList5, ArraysKt.toList((T[]) (Object[]) tracks10));
            }
            objectRef.element = ((List) arrayList5).toArray(new MediaWrapper[0]);
        }
        final MediaSessionCallback mediaSessionCallback = this.this$0;
        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this.this$0.playbackService), Dispatchers.getMain(), (CoroutineStart) null, new AnonymousClass3((Continuation<? super AnonymousClass3>) null), 2, (Object) null);
        return Unit.INSTANCE;
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "org.videolan.vlc.MediaSessionCallback$onPlayFromSearch$1$3", f = "MediaSessionCallback.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: org.videolan.vlc.MediaSessionCallback$onPlayFromSearch$1$3  reason: invalid class name */
    /* compiled from: MediaSessionCallback.kt */
    static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass3(objectRef, mediaSessionCallback, voiceSearchParams, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            int i;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                Object[] objArr = (Object[]) objectRef.element;
                if (objArr != null && objArr.length != 0) {
                    MediaSessionCallback mediaSessionCallback = mediaSessionCallback;
                    MediaWrapper[] mediaWrapperArr = (MediaWrapper[]) objectRef.element;
                    List list = mediaWrapperArr != null ? ArraysKt.toList((T[]) mediaWrapperArr) : null;
                    if (voiceSearchParams.isAny()) {
                        SecureRandom secureRandom = new SecureRandom();
                        T t = objectRef.element;
                        Intrinsics.checkNotNull(t);
                        i = secureRandom.nextInt(Math.min(((Object[]) t).length, 500));
                    } else {
                        i = 0;
                    }
                    MediaSessionCallback.loadMedia$default(mediaSessionCallback, list, i, false, 4, (Object) null);
                    if (voiceSearchParams.isAny() == (!mediaSessionCallback.playbackService.isShuffling())) {
                        mediaSessionCallback.playbackService.shuffle();
                    }
                } else if (mediaSessionCallback.playbackService.hasMedia()) {
                    mediaSessionCallback.playbackService.play();
                } else {
                    mediaSessionCallback.playbackService.displayPlaybackError(R.string.search_no_result);
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
