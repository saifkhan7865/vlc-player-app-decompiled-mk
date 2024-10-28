package org.videolan.vlc;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.MediaParsingService", f = "MediaParsingService.kt", i = {0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 3}, l = {347, 355, 360, 367}, m = "updateStorages", n = {"this", "this", "knownDevices", "missingDevices", "device", "uuid", "this", "knownDevices", "missingDevices", "device", "this"}, s = {"L$0", "L$0", "L$1", "L$2", "L$4", "L$5", "L$0", "L$1", "L$2", "L$4", "L$0"})
/* compiled from: MediaParsingService.kt */
final class MediaParsingService$updateStorages$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ MediaParsingService this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MediaParsingService$updateStorages$1(MediaParsingService mediaParsingService, Continuation<? super MediaParsingService$updateStorages$1> continuation) {
        super(continuation);
        this.this$0 = mediaParsingService;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.updateStorages(this);
    }
}
