package org.videolan.vlc.gui.dialogs;

import android.text.Editable;
import android.widget.EditText;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.videolan.medialibrary.interfaces.Medialibrary;
import org.videolan.medialibrary.interfaces.media.Playlist;
import org.videolan.tools.Settings;
import org.videolan.vlc.R;
import org.videolan.vlc.databinding.DialogPlaylistBinding;
import org.videolan.vlc.gui.SimpleAdapter;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.dialogs.SavePlaylistDialog$addNewPlaylist$1", f = "SavePlaylistDialog.kt", i = {}, l = {243}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: SavePlaylistDialog.kt */
final class SavePlaylistDialog$addNewPlaylist$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $name;
    int label;
    final /* synthetic */ SavePlaylistDialog this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SavePlaylistDialog$addNewPlaylist$1(SavePlaylistDialog savePlaylistDialog, String str, Continuation<? super SavePlaylistDialog$addNewPlaylist$1> continuation) {
        super(2, continuation);
        this.this$0 = savePlaylistDialog;
        this.$name = str;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SavePlaylistDialog$addNewPlaylist$1(this.this$0, this.$name, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SavePlaylistDialog$addNewPlaylist$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "Lorg/videolan/medialibrary/interfaces/media/Playlist;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "org.videolan.vlc.gui.dialogs.SavePlaylistDialog$addNewPlaylist$1$1", f = "SavePlaylistDialog.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: org.videolan.vlc.gui.dialogs.SavePlaylistDialog$addNewPlaylist$1$1  reason: invalid class name */
    /* compiled from: SavePlaylistDialog.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Playlist>, Object> {
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(savePlaylistDialog, str, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Playlist> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                Medialibrary access$getMedialibrary$p = savePlaylistDialog.medialibrary;
                if (access$getMedialibrary$p == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("medialibrary");
                    access$getMedialibrary$p = null;
                }
                return SavePlaylistDialogKt.getPlaylistByName(access$getMedialibrary$p, str);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final Object invokeSuspend(Object obj) {
        Editable text;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        DialogPlaylistBinding dialogPlaylistBinding = null;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final SavePlaylistDialog savePlaylistDialog = this.this$0;
            final String str = this.$name;
            this.label = 1;
            obj = BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass1((Continuation<? super AnonymousClass1>) null), this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        Playlist playlist = (Playlist) obj;
        if (playlist != null) {
            SavePlaylistDialog savePlaylistDialog2 = this.this$0;
            DialogPlaylistBinding access$getBinding$p = savePlaylistDialog2.binding;
            if (access$getBinding$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                dialogPlaylistBinding = access$getBinding$p;
            }
            dialogPlaylistBinding.dialogPlaylistName.setError(savePlaylistDialog2.getString(R.string.playlist_existing, playlist.getTitle()));
            savePlaylistDialog2.alreadyAdding.set(false);
            return Unit.INSTANCE;
        }
        Medialibrary access$getMedialibrary$p = this.this$0.medialibrary;
        if (access$getMedialibrary$p == null) {
            Intrinsics.throwUninitializedPropertyAccessException("medialibrary");
            access$getMedialibrary$p = null;
        }
        access$getMedialibrary$p.createPlaylist(this.$name, Settings.INSTANCE.getIncludeMissing(), false);
        DialogPlaylistBinding access$getBinding$p2 = this.this$0.binding;
        if (access$getBinding$p2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            access$getBinding$p2 = null;
        }
        EditText editText = access$getBinding$p2.dialogPlaylistName.getEditText();
        if (!(editText == null || (text = editText.getText()) == null)) {
            text.clear();
        }
        SimpleAdapter access$getAdapter$p = this.this$0.adapter;
        if (access$getAdapter$p == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            access$getAdapter$p = null;
        }
        Medialibrary access$getMedialibrary$p2 = this.this$0.medialibrary;
        if (access$getMedialibrary$p2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("medialibrary");
            access$getMedialibrary$p2 = null;
        }
        Playlist[] playlists = access$getMedialibrary$p2.getPlaylists(Playlist.Type.All, false);
        SavePlaylistDialog savePlaylistDialog3 = this.this$0;
        Intrinsics.checkNotNull(playlists);
        for (Playlist playlist2 : playlists) {
            playlist2.setDescription(savePlaylistDialog3.getResources().getQuantityString(R.plurals.media_quantity, playlist2.getTracksCount(), new Object[]{Boxing.boxInt(playlist2.getTracksCount())}));
        }
        Intrinsics.checkNotNullExpressionValue(playlists, "apply(...)");
        access$getAdapter$p.submitList(CollectionsKt.listOf(Arrays.copyOf(playlists, playlists.length)));
        this.this$0.alreadyAdding.set(false);
        DialogPlaylistBinding access$getBinding$p3 = this.this$0.binding;
        if (access$getBinding$p3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            access$getBinding$p3 = null;
        }
        access$getBinding$p3.dialogPlaylistName.setError((CharSequence) null);
        return Unit.INSTANCE;
    }
}
