package org.videolan.vlc.gui.network;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.vlc.viewmodels.StreamsModel;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: StreamsFragmentDelegate.kt */
final class StreamsFragmentDelegate$onCtxAction$2 extends Lambda implements Function0<Unit> {
    final /* synthetic */ StreamsFragmentDelegate this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    StreamsFragmentDelegate$onCtxAction$2(StreamsFragmentDelegate streamsFragmentDelegate) {
        super(0);
        this.this$0 = streamsFragmentDelegate;
    }

    public final void invoke() {
        StreamsModel access$getViewModel$p = this.this$0.viewModel;
        StreamsModel streamsModel = null;
        if (access$getViewModel$p == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            access$getViewModel$p = null;
        }
        access$getViewModel$p.setDeletingMedia((MediaWrapper) null);
        StreamsModel access$getViewModel$p2 = this.this$0.viewModel;
        if (access$getViewModel$p2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            streamsModel = access$getViewModel$p2;
        }
        streamsModel.refresh();
    }
}
