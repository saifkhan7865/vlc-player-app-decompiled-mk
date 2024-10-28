package io.ktor.server.plugins.callloging;

import io.ktor.http.ContentDisposition;
import io.ktor.server.application.ApplicationCall;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0014\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0005¢\u0006\u0002\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001f\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lio/ktor/server/plugins/callloging/MDCEntry;", "", "name", "", "provider", "Lkotlin/Function1;", "Lio/ktor/server/application/ApplicationCall;", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;)V", "getName", "()Ljava/lang/String;", "getProvider", "()Lkotlin/jvm/functions/Function1;", "ktor-server-call-logging"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: MDCEntryUtils.kt */
public final class MDCEntry {
    private final String name;
    private final Function1<ApplicationCall, String> provider;

    public MDCEntry(String str, Function1<? super ApplicationCall, String> function1) {
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(function1, "provider");
        this.name = str;
        this.provider = function1;
    }

    public final String getName() {
        return this.name;
    }

    public final Function1<ApplicationCall, String> getProvider() {
        return this.provider;
    }
}
