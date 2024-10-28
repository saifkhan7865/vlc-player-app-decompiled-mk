package io.ktor.server.plugins.callloging;

import io.ktor.http.ContentDisposition;
import io.ktor.http.HttpHeaders;
import io.ktor.http.HttpStatusCode;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.request.ApplicationRequest;
import io.ktor.server.request.ApplicationRequestPropertiesKt;
import io.ktor.util.KtorDsl;
import io.netty.handler.codec.rtsp.RtspHeaders;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;
import org.slf4j.Logger;
import org.slf4j.event.Level;

@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0003\u001a\u00020.2\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004J\u0010\u0010/\u001a\u00020\u00122\u0006\u00100\u001a\u000201H\u0002J\u0018\u0010/\u001a\u00020\u00122\u0006\u00102\u001a\u00020\u00012\u0006\u00103\u001a\u000204H\u0002J\u0010\u00105\u001a\u00020\u00122\u0006\u00106\u001a\u00020\rH\u0002J\u0006\u00107\u001a\u00020.J\u0006\u00108\u001a\u00020.J\u001a\u00109\u001a\u00020.2\u0012\u0010:\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\fJ\u001a\u0010;\u001a\u00020.2\u0012\u0010<\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00120\fJ$\u0010=\u001a\u00020.2\u0006\u0010>\u001a\u00020\u00122\u0014\u0010?\u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0006\u0012\u0004\u0018\u00010\u00120\fJ\u0011\u0010@\u001a\u00020\u0012*\u00020AH\u0000¢\u0006\u0002\bBR \u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR&\u0010\n\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\f0\u000bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R&\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00120\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\u000eX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001c\u001a\u00020\u000eX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0019\"\u0004\b\u001e\u0010\u001bR\u001a\u0010\u001f\u001a\u00020 X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001c\u0010%\u001a\u0004\u0018\u00010&X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u001a\u0010+\u001a\b\u0012\u0004\u0012\u00020,0\u000bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\u0010¨\u0006C"}, d2 = {"Lio/ktor/server/plugins/callloging/CallLoggingConfig;", "", "()V", "clock", "Lkotlin/Function0;", "", "getClock$ktor_server_call_logging", "()Lkotlin/jvm/functions/Function0;", "setClock$ktor_server_call_logging", "(Lkotlin/jvm/functions/Function0;)V", "filters", "", "Lkotlin/Function1;", "Lio/ktor/server/application/ApplicationCall;", "", "getFilters$ktor_server_call_logging", "()Ljava/util/List;", "formatCall", "", "getFormatCall$ktor_server_call_logging", "()Lkotlin/jvm/functions/Function1;", "setFormatCall$ktor_server_call_logging", "(Lkotlin/jvm/functions/Function1;)V", "ignoreStaticContent", "getIgnoreStaticContent$ktor_server_call_logging", "()Z", "setIgnoreStaticContent$ktor_server_call_logging", "(Z)V", "isColorsEnabled", "isColorsEnabled$ktor_server_call_logging", "setColorsEnabled$ktor_server_call_logging", "level", "Lorg/slf4j/event/Level;", "getLevel", "()Lorg/slf4j/event/Level;", "setLevel", "(Lorg/slf4j/event/Level;)V", "logger", "Lorg/slf4j/Logger;", "getLogger", "()Lorg/slf4j/Logger;", "setLogger", "(Lorg/slf4j/Logger;)V", "mdcEntries", "Lio/ktor/server/plugins/callloging/MDCEntry;", "getMdcEntries$ktor_server_call_logging", "", "colored", "status", "Lio/ktor/http/HttpStatusCode;", "value", "color", "Lorg/fusesource/jansi/Ansi$Color;", "defaultFormat", "call", "disableDefaultColors", "disableForStaticContent", "filter", "predicate", "format", "formatter", "mdc", "name", "provider", "toLogStringWithColors", "Lio/ktor/server/request/ApplicationRequest;", "toLogStringWithColors$ktor_server_call_logging", "ktor-server-call-logging"}, k = 1, mv = {1, 8, 0}, xi = 48)
@KtorDsl
/* compiled from: CallLoggingConfig.kt */
public final class CallLoggingConfig {
    private Function0<Long> clock = CallLoggingConfig$clock$1.INSTANCE;
    private final List<Function1<ApplicationCall, Boolean>> filters = new ArrayList();
    private Function1<? super ApplicationCall, String> formatCall = new CallLoggingConfig$formatCall$1(this);
    private boolean ignoreStaticContent;
    private boolean isColorsEnabled = true;
    private Level level = Level.INFO;
    private Logger logger;
    private final List<MDCEntry> mdcEntries = new ArrayList();

    public final Function0<Long> getClock$ktor_server_call_logging() {
        return this.clock;
    }

    public final void setClock$ktor_server_call_logging(Function0<Long> function0) {
        Intrinsics.checkNotNullParameter(function0, "<set-?>");
        this.clock = function0;
    }

    public final List<Function1<ApplicationCall, Boolean>> getFilters$ktor_server_call_logging() {
        return this.filters;
    }

    public final List<MDCEntry> getMdcEntries$ktor_server_call_logging() {
        return this.mdcEntries;
    }

    public final Function1<ApplicationCall, String> getFormatCall$ktor_server_call_logging() {
        return this.formatCall;
    }

    public final void setFormatCall$ktor_server_call_logging(Function1<? super ApplicationCall, String> function1) {
        Intrinsics.checkNotNullParameter(function1, "<set-?>");
        this.formatCall = function1;
    }

    public final boolean isColorsEnabled$ktor_server_call_logging() {
        return this.isColorsEnabled;
    }

    public final void setColorsEnabled$ktor_server_call_logging(boolean z) {
        this.isColorsEnabled = z;
    }

    public final boolean getIgnoreStaticContent$ktor_server_call_logging() {
        return this.ignoreStaticContent;
    }

    public final void setIgnoreStaticContent$ktor_server_call_logging(boolean z) {
        this.ignoreStaticContent = z;
    }

    public final Level getLevel() {
        return this.level;
    }

    public final void setLevel(Level level2) {
        Intrinsics.checkNotNullParameter(level2, "<set-?>");
        this.level = level2;
    }

    public final Logger getLogger() {
        return this.logger;
    }

    public final void setLogger(Logger logger2) {
        this.logger = logger2;
    }

    public final void filter(Function1<? super ApplicationCall, Boolean> function1) {
        Intrinsics.checkNotNullParameter(function1, "predicate");
        this.filters.add(function1);
    }

    public final void mdc(String str, Function1<? super ApplicationCall, String> function1) {
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(function1, "provider");
        this.mdcEntries.add(new MDCEntry(str, function1));
    }

    public final void format(Function1<? super ApplicationCall, String> function1) {
        Intrinsics.checkNotNullParameter(function1, "formatter");
        this.formatCall = function1;
    }

    public final void clock(Function0<Long> function0) {
        Intrinsics.checkNotNullParameter(function0, RtspHeaders.Values.CLOCK);
        this.clock = function0;
    }

    public final void disableDefaultColors() {
        this.isColorsEnabled = false;
    }

    public final void disableForStaticContent() {
        this.ignoreStaticContent = true;
    }

    /* access modifiers changed from: private */
    public final String defaultFormat(ApplicationCall applicationCall) {
        HttpStatusCode status = applicationCall.getResponse().status();
        String str = status != null ? status : "Unhandled";
        if (Intrinsics.areEqual((Object) str, (Object) HttpStatusCode.Companion.getFound())) {
            return colored((HttpStatusCode) str) + ": " + toLogStringWithColors$ktor_server_call_logging(applicationCall.getRequest()) + " -> " + applicationCall.getResponse().getHeaders().get(HttpHeaders.INSTANCE.getLocation());
        } else if (Intrinsics.areEqual((Object) str, (Object) "Unhandled")) {
            return colored(str, Ansi.Color.RED) + ": " + toLogStringWithColors$ktor_server_call_logging(applicationCall.getRequest());
        } else {
            return colored((HttpStatusCode) str) + ": " + toLogStringWithColors$ktor_server_call_logging(applicationCall.getRequest());
        }
    }

    public final String toLogStringWithColors$ktor_server_call_logging(ApplicationRequest applicationRequest) {
        Intrinsics.checkNotNullParameter(applicationRequest, "<this>");
        return colored(ApplicationRequestPropertiesKt.getHttpMethod(applicationRequest).getValue(), Ansi.Color.CYAN) + " - " + ApplicationRequestPropertiesKt.path(applicationRequest) + " in " + CallLoggingKt.processingTimeMillis(applicationRequest.getCall(), this.clock) + "ms";
    }

    private final String colored(HttpStatusCode httpStatusCode) {
        try {
            if (this.isColorsEnabled && !AnsiConsole.isInstalled()) {
                AnsiConsole.systemInstall();
            }
        } catch (Throwable unused) {
            this.isColorsEnabled = false;
        }
        if (Intrinsics.areEqual((Object) httpStatusCode, (Object) HttpStatusCode.Companion.getFound()) || Intrinsics.areEqual((Object) httpStatusCode, (Object) HttpStatusCode.Companion.getOK()) || Intrinsics.areEqual((Object) httpStatusCode, (Object) HttpStatusCode.Companion.getAccepted()) || Intrinsics.areEqual((Object) httpStatusCode, (Object) HttpStatusCode.Companion.getCreated())) {
            return colored(httpStatusCode, Ansi.Color.GREEN);
        }
        if (!Intrinsics.areEqual((Object) httpStatusCode, (Object) HttpStatusCode.Companion.getContinue()) && !Intrinsics.areEqual((Object) httpStatusCode, (Object) HttpStatusCode.Companion.getProcessing()) && !Intrinsics.areEqual((Object) httpStatusCode, (Object) HttpStatusCode.Companion.getPartialContent()) && !Intrinsics.areEqual((Object) httpStatusCode, (Object) HttpStatusCode.Companion.getNotModified()) && !Intrinsics.areEqual((Object) httpStatusCode, (Object) HttpStatusCode.Companion.getUseProxy()) && !Intrinsics.areEqual((Object) httpStatusCode, (Object) HttpStatusCode.Companion.getUpgradeRequired()) && !Intrinsics.areEqual((Object) httpStatusCode, (Object) HttpStatusCode.Companion.getNoContent())) {
            return colored(httpStatusCode, Ansi.Color.RED);
        }
        return colored(httpStatusCode, Ansi.Color.YELLOW);
    }

    private final String colored(Object obj, Ansi.Color color) {
        if (!this.isColorsEnabled) {
            return obj.toString();
        }
        String ansi = Ansi.ansi().fg(color).a(obj).reset().toString();
        Intrinsics.checkNotNullExpressionValue(ansi, "{\n            Ansi.ansi(…et().toString()\n        }");
        return ansi;
    }
}
