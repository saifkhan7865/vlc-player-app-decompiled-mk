package org.videolan.vlc;

import org.videolan.vlc.DebugLogService;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DebugLogService$Client$mICallback$1$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ DebugLogService.Client f$0;
    public final /* synthetic */ String f$1;

    public /* synthetic */ DebugLogService$Client$mICallback$1$$ExternalSyntheticLambda0(DebugLogService.Client client, String str) {
        this.f$0 = client;
        this.f$1 = str;
    }

    public final void run() {
        DebugLogService$Client$mICallback$1.onLog$lambda$2(this.f$0, this.f$1);
    }
}
