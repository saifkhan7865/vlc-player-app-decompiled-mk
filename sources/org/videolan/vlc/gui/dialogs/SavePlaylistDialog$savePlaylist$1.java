package org.videolan.vlc.gui.dialogs;

import java.util.LinkedList;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.medialibrary.interfaces.Medialibrary;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.interfaces.media.Playlist;
import org.videolan.tools.Settings;
import org.videolan.vlc.databinding.DialogPlaylistBinding;
import org.videolan.vlc.util.BrowserutilsKt;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.dialogs.SavePlaylistDialog$savePlaylist$1", f = "SavePlaylistDialog.kt", i = {}, l = {284}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: SavePlaylistDialog.kt */
final class SavePlaylistDialog$savePlaylist$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Playlist $playlist;
    final /* synthetic */ MediaWrapper[] $tracks;
    int label;
    final /* synthetic */ SavePlaylistDialog this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SavePlaylistDialog$savePlaylist$1(MediaWrapper[] mediaWrapperArr, SavePlaylistDialog savePlaylistDialog, Playlist playlist, Continuation<? super SavePlaylistDialog$savePlaylist$1> continuation) {
        super(2, continuation);
        this.$tracks = mediaWrapperArr;
        this.this$0 = savePlaylistDialog;
        this.$playlist = playlist;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SavePlaylistDialog$savePlaylist$1(this.$tracks, this.this$0, this.$playlist, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SavePlaylistDialog$savePlaylist$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        MediaWrapper mediaWrapper;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            if (this.$tracks.length == 0) {
                return Unit.INSTANCE;
            }
            LinkedList linkedList = new LinkedList();
            MediaWrapper[] mediaWrapperArr = this.$tracks;
            int length = mediaWrapperArr.length;
            int i2 = 0;
            while (true) {
                Medialibrary medialibrary = null;
                if (i2 >= length) {
                    break;
                }
                MediaWrapper mediaWrapper2 = mediaWrapperArr[i2];
                long id = mediaWrapper2.getId();
                if (id == 0) {
                    Medialibrary access$getMedialibrary$p = this.this$0.medialibrary;
                    if (access$getMedialibrary$p == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("medialibrary");
                        access$getMedialibrary$p = null;
                    }
                    MediaWrapper media = access$getMedialibrary$p.getMedia(mediaWrapper2.getUri());
                    if (media != null) {
                        linkedList.add(Boxing.boxLong(media.getId()));
                        media.setTitle(mediaWrapper2.getTitle());
                    } else {
                        if (BrowserutilsKt.isSchemeStreaming(mediaWrapper2.getLocation())) {
                            Medialibrary access$getMedialibrary$p2 = this.this$0.medialibrary;
                            if (access$getMedialibrary$p2 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("medialibrary");
                            } else {
                                medialibrary = access$getMedialibrary$p2;
                            }
                            mediaWrapper = medialibrary.addStream(mediaWrapper2.getLocation(), mediaWrapper2.getTitle());
                        } else {
                            Medialibrary access$getMedialibrary$p3 = this.this$0.medialibrary;
                            if (access$getMedialibrary$p3 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("medialibrary");
                            } else {
                                medialibrary = access$getMedialibrary$p3;
                            }
                            mediaWrapper = medialibrary.addMedia(mediaWrapper2.getLocation(), -1);
                        }
                        if (mediaWrapper != null) {
                            linkedList.add(Boxing.boxLong(mediaWrapper.getId()));
                        }
                    }
                } else {
                    linkedList.add(Boxing.boxLong(id));
                }
                i2++;
            }
            DialogPlaylistBinding access$getBinding$p = this.this$0.binding;
            if (access$getBinding$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                access$getBinding$p = null;
            }
            if (access$getBinding$p.replaceSwitch.isChecked()) {
                String title = this.$playlist.getTitle();
                this.$playlist.delete();
                Medialibrary access$getMedialibrary$p4 = this.this$0.medialibrary;
                if (access$getMedialibrary$p4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("medialibrary");
                    access$getMedialibrary$p4 = null;
                }
                access$getMedialibrary$p4.createPlaylist(title, Settings.INSTANCE.getIncludeMissing(), false).append((List<Long>) linkedList);
            } else {
                this.$playlist.append((List<Long>) linkedList);
            }
            final SavePlaylistDialog savePlaylistDialog = this.this$0;
            final MediaWrapper[] mediaWrapperArr2 = this.$tracks;
            this.label = 1;
            if (BuildersKt.withContext(this.this$0.coroutineContextProvider.getMain(), new AnonymousClass1((Continuation<? super AnonymousClass1>) null), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "org.videolan.vlc.gui.dialogs.SavePlaylistDialog$savePlaylist$1$1", f = "SavePlaylistDialog.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: org.videolan.vlc.gui.dialogs.SavePlaylistDialog$savePlaylist$1$1  reason: invalid class name */
    /* compiled from: SavePlaylistDialog.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(savePlaylistDialog, mediaWrapperArr2, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (savePlaylistDialog.playlistIterator == null) {
                    savePlaylistDialog.dismiss();
                } else {
                    savePlaylistDialog.processNextItem(mediaWrapperArr2);
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
