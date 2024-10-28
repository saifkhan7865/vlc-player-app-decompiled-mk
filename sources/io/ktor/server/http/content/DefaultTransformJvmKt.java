package io.ktor.server.http.content;

import androidx.core.app.NotificationCompat;
import io.ktor.http.ContentType;
import io.ktor.http.content.OutgoingContent;
import io.ktor.http.content.URIFileContent;
import io.ktor.server.application.ApplicationCall;
import java.io.File;
import java.io.InputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\u001a\u001a\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0000¨\u0006\u0006"}, d2 = {"platformTransformDefaultContent", "Lio/ktor/http/content/OutgoingContent;", "call", "Lio/ktor/server/application/ApplicationCall;", "value", "", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: DefaultTransformJvm.kt */
public final class DefaultTransformJvmKt {
    public static final OutgoingContent platformTransformDefaultContent(ApplicationCall applicationCall, Object obj) {
        Intrinsics.checkNotNullParameter(applicationCall, NotificationCompat.CATEGORY_CALL);
        Intrinsics.checkNotNullParameter(obj, "value");
        LocalFileContent localFileContent = null;
        if (obj instanceof URIFileContent) {
            URIFileContent uRIFileContent = (URIFileContent) obj;
            if (Intrinsics.areEqual((Object) uRIFileContent.getUri().getScheme(), (Object) "file")) {
                localFileContent = new LocalFileContent(new File(uRIFileContent.getUri()), (ContentType) null, 2, (DefaultConstructorMarker) null);
            }
            return localFileContent;
        } else if (obj instanceof InputStream) {
            return new DefaultTransformJvmKt$platformTransformDefaultContent$1(obj);
        } else {
            return null;
        }
    }
}
