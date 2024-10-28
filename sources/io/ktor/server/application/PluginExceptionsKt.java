package io.ktor.server.application;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0000\u001a\u001a\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0000¨\u0006\u0006"}, d2 = {"noBinaryDataException", "Lio/ktor/server/application/InvalidBodyException;", "expectedTypeName", "", "subject", "", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: PluginExceptions.kt */
public final class PluginExceptionsKt {
    public static final InvalidBodyException noBinaryDataException(String str, Object obj) {
        String str2;
        Intrinsics.checkNotNullParameter(str, "expectedTypeName");
        StringBuilder sb = new StringBuilder("Expected ");
        sb.append(str);
        sb.append(" type but ");
        if (obj == null || (str2 = Reflection.getOrCreateKotlinClass(obj.getClass()).getSimpleName()) == null) {
            str2 = AbstractJsonLexerKt.NULL;
        }
        sb.append(str2);
        sb.append(" found");
        return new InvalidBodyException(sb.toString());
    }
}
