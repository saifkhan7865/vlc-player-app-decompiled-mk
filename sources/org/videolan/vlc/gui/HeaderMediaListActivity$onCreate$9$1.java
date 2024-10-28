package org.videolan.vlc.gui;

import android.content.Intent;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.videolan.medialibrary.interfaces.media.Album;
import org.videolan.medialibrary.interfaces.media.Artist;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.vlc.viewmodels.mobile.PlaylistViewModel;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.HeaderMediaListActivity$onCreate$9$1", f = "HeaderMediaListActivity.kt", i = {}, l = {186}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: HeaderMediaListActivity.kt */
final class HeaderMediaListActivity$onCreate$9$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ HeaderMediaListActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    HeaderMediaListActivity$onCreate$9$1(HeaderMediaListActivity headerMediaListActivity, Continuation<? super HeaderMediaListActivity$onCreate$9$1> continuation) {
        super(2, continuation);
        this.this$0 = headerMediaListActivity;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new HeaderMediaListActivity$onCreate$9$1(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((HeaderMediaListActivity$onCreate$9$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "org.videolan.vlc.gui.HeaderMediaListActivity$onCreate$9$1$1", f = "HeaderMediaListActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: org.videolan.vlc.gui.HeaderMediaListActivity$onCreate$9$1$1  reason: invalid class name */
    /* compiled from: HeaderMediaListActivity.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(headerMediaListActivity, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                PlaylistViewModel access$getViewModel$p = headerMediaListActivity.viewModel;
                if (access$getViewModel$p == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    access$getViewModel$p = null;
                }
                MediaLibraryItem playlist = access$getViewModel$p.getPlaylist();
                Intrinsics.checkNotNull(playlist, "null cannot be cast to non-null type org.videolan.medialibrary.interfaces.media.Album");
                Artist retrieveAlbumArtist = ((Album) playlist).retrieveAlbumArtist();
                Intent intent = new Intent(headerMediaListActivity, SecondaryActivity.class);
                intent.putExtra(SecondaryActivity.KEY_FRAGMENT, SecondaryActivity.ALBUMS_SONGS);
                intent.putExtra("ML_ITEM", retrieveAlbumArtist);
                intent.putExtra(HeaderMediaListActivity.ARTIST_FROM_ALBUM, true);
                intent.setFlags(intent.getFlags() | 1073741824);
                headerMediaListActivity.startActivity(intent);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final HeaderMediaListActivity headerMediaListActivity = this.this$0;
            this.label = 1;
            if (BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass1((Continuation<? super AnonymousClass1>) null), this) == coroutine_suspended) {
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
