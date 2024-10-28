package io.ktor.http.cio.internals;

import io.ktor.utils.io.ByteWriteChannel;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.http.cio.internals.CharsKt", f = "Chars.kt", i = {0, 0, 0, 0, 1, 1, 1, 1}, l = {108, 116}, m = "writeIntHex", n = {"$this$writeIntHex", "table", "current", "digits", "$this$writeIntHex", "table", "current", "digits"}, s = {"L$0", "L$1", "I$0", "I$1", "L$0", "L$1", "I$0", "I$1"})
/* compiled from: Chars.kt */
final class CharsKt$writeIntHex$1 extends ContinuationImpl {
    int I$0;
    int I$1;
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;

    CharsKt$writeIntHex$1(Continuation<? super CharsKt$writeIntHex$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return CharsKt.writeIntHex((ByteWriteChannel) null, 0, this);
    }
}
