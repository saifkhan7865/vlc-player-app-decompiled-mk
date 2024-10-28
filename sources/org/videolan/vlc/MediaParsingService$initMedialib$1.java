package org.videolan.vlc;

import android.content.Context;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.MediaParsingService", f = "MediaParsingService.kt", i = {0, 0, 0, 0}, l = {281}, m = "initMedialib", n = {"this", "parse", "shouldInit", "upgrade"}, s = {"L$0", "Z$0", "Z$1", "Z$2"})
/* compiled from: MediaParsingService.kt */
final class MediaParsingService$initMedialib$1 extends ContinuationImpl {
    Object L$0;
    boolean Z$0;
    boolean Z$1;
    boolean Z$2;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ MediaParsingService this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MediaParsingService$initMedialib$1(MediaParsingService mediaParsingService, Continuation<? super MediaParsingService$initMedialib$1> continuation) {
        super(continuation);
        this.this$0 = mediaParsingService;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.initMedialib(false, (Context) null, false, false, false, this);
    }
}
