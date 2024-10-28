package org.videolan.vlc.util;

import androidx.appcompat.app.AppCompatActivity;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.util.KextensionsKt", f = "Kextensions.kt", i = {0, 0, 0, 0}, l = {120}, m = "share", n = {"$this$share", "media", "intentShareFile", "fileWithinMyDir"}, s = {"L$0", "L$1", "L$2", "L$3"})
/* compiled from: Kextensions.kt */
final class KextensionsKt$share$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;

    KextensionsKt$share$1(Continuation<? super KextensionsKt$share$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return KextensionsKt.share((AppCompatActivity) null, (MediaWrapper) null, (Continuation<? super Unit>) this);
    }
}
