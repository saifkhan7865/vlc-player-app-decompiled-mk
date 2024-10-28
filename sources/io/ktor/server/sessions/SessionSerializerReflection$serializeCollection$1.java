package io.ktor.server.sessions;

import io.ktor.http.CodecsKt;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "T", "", "it", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: SessionSerializerReflection.kt */
final class SessionSerializerReflection$serializeCollection$1 extends Lambda implements Function1<Object, CharSequence> {
    final /* synthetic */ SessionSerializerReflection<T> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SessionSerializerReflection$serializeCollection$1(SessionSerializerReflection<T> sessionSerializerReflection) {
        super(1);
        this.this$0 = sessionSerializerReflection;
    }

    public final CharSequence invoke(Object obj) {
        return CodecsKt.encodeURLQueryComponent$default(this.this$0.serializeValue(obj), false, false, (Charset) null, 7, (Object) null);
    }
}
