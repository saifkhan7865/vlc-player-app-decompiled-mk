package io.ktor.server.http.content;

import androidx.core.app.NotificationCompat;
import io.ktor.http.ContentType;
import io.ktor.http.HttpStatusCode;
import io.ktor.http.content.ByteArrayContent;
import io.ktor.http.content.OutgoingContent;
import io.ktor.http.content.TextContent;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.response.ApplicationResponseFunctionsKt;
import io.ktor.utils.io.ByteReadChannel;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\u001a\u0018\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005¨\u0006\u0006"}, d2 = {"transformDefaultContent", "Lio/ktor/http/content/OutgoingContent;", "call", "Lio/ktor/server/application/ApplicationCall;", "value", "", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: DefaultTransform.kt */
public final class DefaultTransformKt {
    public static final OutgoingContent transformDefaultContent(ApplicationCall applicationCall, Object obj) {
        Intrinsics.checkNotNullParameter(applicationCall, NotificationCompat.CATEGORY_CALL);
        Intrinsics.checkNotNullParameter(obj, "value");
        if (obj instanceof OutgoingContent) {
            return (OutgoingContent) obj;
        }
        if (obj instanceof String) {
            return new TextContent((String) obj, ApplicationResponseFunctionsKt.defaultTextContentType(applicationCall, (ContentType) null), (HttpStatusCode) null);
        } else if (obj instanceof byte[]) {
            return new ByteArrayContent((byte[]) obj, (ContentType) null, (HttpStatusCode) null, 6, (DefaultConstructorMarker) null);
        } else {
            if (obj instanceof HttpStatusCode) {
                return new HttpStatusCodeContent((HttpStatusCode) obj);
            }
            if (obj instanceof ByteReadChannel) {
                return new DefaultTransformKt$transformDefaultContent$1(obj);
            }
            return DefaultTransformJvmKt.platformTransformDefaultContent(applicationCall, obj);
        }
    }
}
