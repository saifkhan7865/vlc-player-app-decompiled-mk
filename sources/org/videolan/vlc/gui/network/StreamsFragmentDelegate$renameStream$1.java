package org.videolan.vlc.gui.network;

import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.vlc.viewmodels.StreamsModel;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "media", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "name", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: StreamsFragmentDelegate.kt */
final class StreamsFragmentDelegate$renameStream$1 extends Lambda implements Function2<MediaLibraryItem, String, Unit> {
    final /* synthetic */ StreamsFragmentDelegate this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    StreamsFragmentDelegate$renameStream$1(StreamsFragmentDelegate streamsFragmentDelegate) {
        super(2);
        this.this$0 = streamsFragmentDelegate;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((MediaLibraryItem) obj, (String) obj2);
        return Unit.INSTANCE;
    }

    public final void invoke(MediaLibraryItem mediaLibraryItem, String str) {
        Intrinsics.checkNotNullParameter(mediaLibraryItem, "media");
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        StreamsModel access$getViewModel$p = this.this$0.viewModel;
        if (access$getViewModel$p == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            access$getViewModel$p = null;
        }
        access$getViewModel$p.rename((MediaWrapper) mediaLibraryItem, str);
    }
}
