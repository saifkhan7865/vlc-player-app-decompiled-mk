package org.videolan.vlc;

import org.videolan.vlc.DebugLogService;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DebugLogService$Client$mICallback$1$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ DebugLogService.Client f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ String f$2;

    public /* synthetic */ DebugLogService$Client$mICallback$1$$ExternalSyntheticLambda2(DebugLogService.Client client, boolean z, String str) {
        this.f$0 = client;
        this.f$1 = z;
        this.f$2 = str;
    }

    public final void run() {
        DebugLogService$Client$mICallback$1.onSaved$lambda$3(this.f$0, this.f$1, this.f$2);
    }
}
