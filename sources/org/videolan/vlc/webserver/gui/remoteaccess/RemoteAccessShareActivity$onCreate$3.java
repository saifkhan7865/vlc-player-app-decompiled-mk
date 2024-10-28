package org.videolan.vlc.webserver.gui.remoteaccess;

import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.vlc.webserver.RemoteAccessServer;
import org.videolan.vlc.webserver.gui.remoteaccess.adapters.ConnnectionAdapter;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004 \u0005*\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "it", "", "Lorg/videolan/vlc/webserver/RemoteAccessServer$RemoteAccessConnection;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: RemoteAccessShareActivity.kt */
final class RemoteAccessShareActivity$onCreate$3 extends Lambda implements Function1<List<? extends RemoteAccessServer.RemoteAccessConnection>, Unit> {
    final /* synthetic */ RemoteAccessShareActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RemoteAccessShareActivity$onCreate$3(RemoteAccessShareActivity remoteAccessShareActivity) {
        super(1);
        this.this$0 = remoteAccessShareActivity;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((List<RemoteAccessServer.RemoteAccessConnection>) (List) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(List<RemoteAccessServer.RemoteAccessConnection> list) {
        ConnnectionAdapter access$getConnectionAdapter$p = this.this$0.connectionAdapter;
        ConnnectionAdapter connnectionAdapter = null;
        if (access$getConnectionAdapter$p == null) {
            Intrinsics.throwUninitializedPropertyAccessException("connectionAdapter");
            access$getConnectionAdapter$p = null;
        }
        Intrinsics.checkNotNull(list);
        access$getConnectionAdapter$p.setConnections(list);
        ConnnectionAdapter access$getConnectionAdapter$p2 = this.this$0.connectionAdapter;
        if (access$getConnectionAdapter$p2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("connectionAdapter");
        } else {
            connnectionAdapter = access$getConnectionAdapter$p2;
        }
        connnectionAdapter.notifyDataSetChanged();
    }
}
