package org.videolan.vlc.gui;

import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.libvlc.interfaces.IMedia;
import org.videolan.vlc.gui.video.MediaInfoAdapter;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004 \u0005*\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "it", "", "Lorg/videolan/libvlc/interfaces/IMedia$Track;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: InfoActivity.kt */
final class InfoActivity$onCreate$2 extends Lambda implements Function1<List<? extends IMedia.Track>, Unit> {
    final /* synthetic */ InfoActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    InfoActivity$onCreate$2(InfoActivity infoActivity) {
        super(1);
        this.this$0 = infoActivity;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((List<? extends IMedia.Track>) (List) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(List<? extends IMedia.Track> list) {
        MediaInfoAdapter access$getAdapter$p = this.this$0.adapter;
        if (access$getAdapter$p == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            access$getAdapter$p = null;
        }
        Intrinsics.checkNotNull(list);
        access$getAdapter$p.setTracks(list);
    }
}
