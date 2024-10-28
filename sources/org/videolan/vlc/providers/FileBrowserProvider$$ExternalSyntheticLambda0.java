package org.videolan.vlc.providers;

import androidx.lifecycle.Observer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class FileBrowserProvider$$ExternalSyntheticLambda0 implements Observer {
    public final /* synthetic */ FileBrowserProvider f$0;

    public /* synthetic */ FileBrowserProvider$$ExternalSyntheticLambda0(FileBrowserProvider fileBrowserProvider) {
        this.f$0 = fileBrowserProvider;
    }

    public final void onChanged(Object obj) {
        FileBrowserProvider.browseRootImpl$lambda$0(this.f$0, ((Boolean) obj).booleanValue());
    }
}
