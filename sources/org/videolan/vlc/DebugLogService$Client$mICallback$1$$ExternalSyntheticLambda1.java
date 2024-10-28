package org.videolan.vlc;

import java.util.List;
import org.videolan.vlc.DebugLogService;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DebugLogService$Client$mICallback$1$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ DebugLogService.Client f$0;
    public final /* synthetic */ List f$1;

    public /* synthetic */ DebugLogService$Client$mICallback$1$$ExternalSyntheticLambda1(DebugLogService.Client client, List list) {
        this.f$0 = client;
        this.f$1 = list;
    }

    public final void run() {
        DebugLogService$Client$mICallback$1.onStarted$lambda$1(this.f$0, this.f$1);
    }
}
