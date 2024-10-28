package io.ktor.server.engine;

import io.ktor.http.Parameters;
import io.ktor.server.application.Application;
import io.ktor.server.application.ApplicationCall;
import io.ktor.util.Attributes;
import io.ktor.util.AttributesJvmKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0013\u001a\u00020\u0014H\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\f8VX\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0012\u0010\u000f\u001a\u00020\u0010X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0012\u0010\u0013\u001a\u00020\u0014X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u0019"}, d2 = {"Lio/ktor/server/engine/BaseApplicationCall;", "Lio/ktor/server/application/ApplicationCall;", "application", "Lio/ktor/server/application/Application;", "(Lio/ktor/server/application/Application;)V", "getApplication", "()Lio/ktor/server/application/Application;", "attributes", "Lio/ktor/util/Attributes;", "getAttributes", "()Lio/ktor/util/Attributes;", "parameters", "Lio/ktor/http/Parameters;", "getParameters", "()Lio/ktor/http/Parameters;", "request", "Lio/ktor/server/engine/BaseApplicationRequest;", "getRequest", "()Lio/ktor/server/engine/BaseApplicationRequest;", "response", "Lio/ktor/server/engine/BaseApplicationResponse;", "getResponse", "()Lio/ktor/server/engine/BaseApplicationResponse;", "putResponseAttribute", "", "ktor-server-host-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: BaseApplicationCall.kt */
public abstract class BaseApplicationCall implements ApplicationCall {
    private final Application application;
    private final Attributes attributes = AttributesJvmKt.Attributes$default(false, 1, (Object) null);

    public abstract BaseApplicationRequest getRequest();

    public abstract BaseApplicationResponse getResponse();

    public BaseApplicationCall(Application application2) {
        Intrinsics.checkNotNullParameter(application2, "application");
        this.application = application2;
    }

    public final Application getApplication() {
        return this.application;
    }

    public final Attributes getAttributes() {
        return this.attributes;
    }

    public Parameters getParameters() {
        return getRequest().getQueryParameters();
    }

    public static /* synthetic */ void putResponseAttribute$default(BaseApplicationCall baseApplicationCall, BaseApplicationResponse baseApplicationResponse, int i, Object obj) {
        if (obj == null) {
            if ((i & 1) != 0) {
                baseApplicationResponse = baseApplicationCall.getResponse();
            }
            baseApplicationCall.putResponseAttribute(baseApplicationResponse);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: putResponseAttribute");
    }

    /* access modifiers changed from: protected */
    public final void putResponseAttribute(BaseApplicationResponse baseApplicationResponse) {
        Intrinsics.checkNotNullParameter(baseApplicationResponse, "response");
        this.attributes.put(BaseApplicationResponse.Companion.getEngineResponseAttributeKey(), baseApplicationResponse);
    }
}
