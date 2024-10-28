package io.ktor.server.plugins;

import io.ktor.http.ContentType;
import io.ktor.util.internal.ExceptionUtilsJvmKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CopyableThrowable;
import org.bouncycastle.cms.CMSAttributeTableGenerator;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0000H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lio/ktor/server/plugins/UnsupportedMediaTypeException;", "Lio/ktor/server/plugins/ContentTransformationException;", "Lkotlinx/coroutines/CopyableThrowable;", "contentType", "Lio/ktor/http/ContentType;", "(Lio/ktor/http/ContentType;)V", "createCopy", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: Errors.kt */
public final class UnsupportedMediaTypeException extends ContentTransformationException implements CopyableThrowable<UnsupportedMediaTypeException> {
    private final ContentType contentType;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public UnsupportedMediaTypeException(ContentType contentType2) {
        super("Content type " + contentType2 + " is not supported");
        Intrinsics.checkNotNullParameter(contentType2, CMSAttributeTableGenerator.CONTENT_TYPE);
        this.contentType = contentType2;
    }

    public UnsupportedMediaTypeException createCopy() {
        UnsupportedMediaTypeException unsupportedMediaTypeException = new UnsupportedMediaTypeException(this.contentType);
        ExceptionUtilsJvmKt.initCauseBridge(unsupportedMediaTypeException, this);
        return unsupportedMediaTypeException;
    }
}
