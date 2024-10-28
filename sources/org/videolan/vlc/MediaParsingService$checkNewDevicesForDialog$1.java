package org.videolan.vlc;

import android.content.Context;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.MediaParsingService", f = "MediaParsingService.kt", i = {0, 0, 1, 1, 1, 1, 1}, l = {301, 306}, m = "checkNewDevicesForDialog", n = {"this", "addExternal", "this", "knownDevices", "device", "uuid", "addExternal"}, s = {"L$0", "Z$0", "L$0", "L$1", "L$3", "L$4", "Z$0"})
/* compiled from: MediaParsingService.kt */
final class MediaParsingService$checkNewDevicesForDialog$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    boolean Z$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ MediaParsingService this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MediaParsingService$checkNewDevicesForDialog$1(MediaParsingService mediaParsingService, Continuation<? super MediaParsingService$checkNewDevicesForDialog$1> continuation) {
        super(continuation);
        this.this$0 = mediaParsingService;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.checkNewDevicesForDialog((Context) null, false, false, this);
    }
}