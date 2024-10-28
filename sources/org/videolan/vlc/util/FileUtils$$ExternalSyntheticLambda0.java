package org.videolan.vlc.util;

import java.io.File;
import org.videolan.vlc.util.FileUtils;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class FileUtils$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ File f$0;
    public final /* synthetic */ FileUtils.Callback f$1;

    public /* synthetic */ FileUtils$$ExternalSyntheticLambda0(File file, FileUtils.Callback callback) {
        this.f$0 = file;
        this.f$1 = callback;
    }

    public final void run() {
        FileUtils.asyncRecursiveDelete$lambda$1(this.f$0, this.f$1);
    }
}
