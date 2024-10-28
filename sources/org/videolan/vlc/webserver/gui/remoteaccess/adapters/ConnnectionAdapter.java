package org.videolan.vlc.webserver.gui.remoteaccess.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.vlc.gui.helpers.SelectorViewHolder;
import org.videolan.vlc.webserver.RemoteAccessServer;
import org.videolan.vlc.webserver.databinding.RemoteAccessConnectionItemBinding;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001\u0017B\u001b\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\u0010\bJ\b\u0010\r\u001a\u00020\u000eH\u0016J\u001c\u0010\u000f\u001a\u00020\u00102\n\u0010\u0011\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u000eH\u0016J\u001c\u0010\u0013\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u000eH\u0016R \u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lorg/videolan/vlc/webserver/gui/remoteaccess/adapters/ConnnectionAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lorg/videolan/vlc/webserver/gui/remoteaccess/adapters/ConnnectionAdapter$ViewHolder;", "layoutInflater", "Landroid/view/LayoutInflater;", "connections", "", "Lorg/videolan/vlc/webserver/RemoteAccessServer$RemoteAccessConnection;", "(Landroid/view/LayoutInflater;Ljava/util/List;)V", "getConnections", "()Ljava/util/List;", "setConnections", "(Ljava/util/List;)V", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "ViewHolder", "webserver_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ConnnectionAdapter.kt */
public final class ConnnectionAdapter extends RecyclerView.Adapter<ViewHolder> {
    private List<RemoteAccessServer.RemoteAccessConnection> connections;
    private final LayoutInflater layoutInflater;

    public ConnnectionAdapter(LayoutInflater layoutInflater2, List<RemoteAccessServer.RemoteAccessConnection> list) {
        Intrinsics.checkNotNullParameter(layoutInflater2, "layoutInflater");
        Intrinsics.checkNotNullParameter(list, "connections");
        this.layoutInflater = layoutInflater2;
        this.connections = list;
    }

    public final List<RemoteAccessServer.RemoteAccessConnection> getConnections() {
        return this.connections;
    }

    public final void setConnections(List<RemoteAccessServer.RemoteAccessConnection> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.connections = list;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        RemoteAccessConnectionItemBinding inflate = RemoteAccessConnectionItemBinding.inflate(this.layoutInflater, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        return new ViewHolder(this, inflate);
    }

    public int getItemCount() {
        return this.connections.size();
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Intrinsics.checkNotNullParameter(viewHolder, "holder");
        ((RemoteAccessConnectionItemBinding) viewHolder.getBinding()).connectionTitle.setText(this.connections.get(i).getIp());
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lorg/videolan/vlc/webserver/gui/remoteaccess/adapters/ConnnectionAdapter$ViewHolder;", "Lorg/videolan/vlc/gui/helpers/SelectorViewHolder;", "Lorg/videolan/vlc/webserver/databinding/RemoteAccessConnectionItemBinding;", "binding", "(Lorg/videolan/vlc/webserver/gui/remoteaccess/adapters/ConnnectionAdapter;Lorg/videolan/vlc/webserver/databinding/RemoteAccessConnectionItemBinding;)V", "webserver_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: ConnnectionAdapter.kt */
    public final class ViewHolder extends SelectorViewHolder<RemoteAccessConnectionItemBinding> {
        final /* synthetic */ ConnnectionAdapter this$0;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ViewHolder(ConnnectionAdapter connnectionAdapter, RemoteAccessConnectionItemBinding remoteAccessConnectionItemBinding) {
            super(remoteAccessConnectionItemBinding);
            Intrinsics.checkNotNullParameter(remoteAccessConnectionItemBinding, "binding");
            this.this$0 = connnectionAdapter;
        }
    }
}
