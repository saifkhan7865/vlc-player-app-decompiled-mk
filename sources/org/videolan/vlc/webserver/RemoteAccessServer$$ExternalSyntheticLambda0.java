package org.videolan.vlc.webserver;

import androidx.lifecycle.Observer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class RemoteAccessServer$$ExternalSyntheticLambda0 implements Observer {
    public final /* synthetic */ RemoteAccessServer f$0;

    public /* synthetic */ RemoteAccessServer$$ExternalSyntheticLambda0(RemoteAccessServer remoteAccessServer) {
        this.f$0 = remoteAccessServer;
    }

    public final void onChanged(Object obj) {
        RemoteAccessServer.miniPlayerObserver$lambda$0(this.f$0, ((Boolean) obj).booleanValue());
    }
}
