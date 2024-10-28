package org.videolan.vlc.gui;

import android.graphics.Bitmap;
import android.net.Uri;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.medialibrary.interfaces.media.Album;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.vlc.databinding.HeaderMediaListActivityBinding;
import org.videolan.vlc.gui.helpers.AudioUtil;
import org.videolan.vlc.gui.helpers.UiTools;
import org.videolan.vlc.util.KextensionsKt;
import org.videolan.vlc.util.ThumbnailsProvider;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "Landroid/graphics/Bitmap;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.HeaderMediaListActivity$onCreate$10$cover$1", f = "HeaderMediaListActivity.kt", i = {}, l = {212}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: HeaderMediaListActivity.kt */
final class HeaderMediaListActivity$onCreate$10$cover$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Bitmap>, Object> {
    final /* synthetic */ HeaderMediaListActivity $context;
    final /* synthetic */ MediaLibraryItem $playlist;
    final /* synthetic */ Ref.BooleanRef $showBackground;
    int label;
    final /* synthetic */ HeaderMediaListActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    HeaderMediaListActivity$onCreate$10$cover$1(HeaderMediaListActivity headerMediaListActivity, HeaderMediaListActivity headerMediaListActivity2, MediaLibraryItem mediaLibraryItem, Ref.BooleanRef booleanRef, Continuation<? super HeaderMediaListActivity$onCreate$10$cover$1> continuation) {
        super(2, continuation);
        this.this$0 = headerMediaListActivity;
        this.$context = headerMediaListActivity2;
        this.$playlist = mediaLibraryItem;
        this.$showBackground = booleanRef;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new HeaderMediaListActivity$onCreate$10$cover$1(this.this$0, this.$context, this.$playlist, this.$showBackground, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Bitmap> continuation) {
        return ((HeaderMediaListActivity$onCreate$10$cover$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        int i;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            HeaderMediaListActivityBinding access$getBinding$p = this.this$0.binding;
            HeaderMediaListActivityBinding headerMediaListActivityBinding = null;
            if (access$getBinding$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                access$getBinding$p = null;
            }
            if (access$getBinding$p.backgroundView.getWidth() > 0) {
                HeaderMediaListActivityBinding access$getBinding$p2 = this.this$0.binding;
                if (access$getBinding$p2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    headerMediaListActivityBinding = access$getBinding$p2;
                }
                i = headerMediaListActivityBinding.backgroundView.getWidth();
            } else {
                i = KextensionsKt.getScreenWidth(this.$context);
            }
            int i3 = i;
            CharSequence artworkMrl = this.$playlist.getArtworkMrl();
            if (artworkMrl != null && artworkMrl.length() != 0) {
                AudioUtil audioUtil = AudioUtil.INSTANCE;
                String decode = Uri.decode(this.$playlist.getArtworkMrl());
                Intrinsics.checkNotNullExpressionValue(decode, "decode(...)");
                return audioUtil.fetchCoverBitmap(decode, i3);
            } else if (this.$playlist instanceof Album) {
                this.$showBackground.element = false;
                return UiTools.INSTANCE.getDefaultAlbumDrawableBig(this.this$0).getBitmap();
            } else {
                MediaWrapper[] tracks = this.$playlist.getTracks();
                Intrinsics.checkNotNullExpressionValue(tracks, "getTracks(...)");
                this.label = 1;
                obj = ThumbnailsProvider.getPlaylistOrGenreImage$default(ThumbnailsProvider.INSTANCE, "playlist:" + this.$playlist.getId() + '_' + i3, ArraysKt.toList((T[]) (Object[]) tracks), i3, (Bitmap) null, this, 8, (Object) null);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        } else if (i2 == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return (Bitmap) obj;
    }
}
