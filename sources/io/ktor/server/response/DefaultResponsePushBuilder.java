package io.ktor.server.response;

import io.ktor.http.HeadersBuilder;
import io.ktor.http.HttpMethod;
import io.ktor.http.URLBuilder;
import io.ktor.http.content.Version;
import io.ktor.util.InternalAPI;
import io.netty.handler.codec.rtsp.RtspHeaders;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@UseHttp2Push
@InternalAPI
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u000f\b\u0016\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tB3\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\f\u0012\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e¢\u0006\u0002\u0010\u0010R\u0014\u0010\u0004\u001a\u00020\fX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001a\u0010\n\u001a\u00020\u000bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R*\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u000f0\u0019j\b\u0012\u0004\u0012\u00020\u000f`\u001aX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001e¨\u0006\u001f"}, d2 = {"Lio/ktor/server/response/DefaultResponsePushBuilder;", "Lio/ktor/server/response/ResponsePushBuilder;", "url", "Lio/ktor/http/URLBuilder;", "headers", "Lio/ktor/http/Headers;", "(Lio/ktor/http/URLBuilder;Lio/ktor/http/Headers;)V", "call", "Lio/ktor/server/application/ApplicationCall;", "(Lio/ktor/server/application/ApplicationCall;)V", "method", "Lio/ktor/http/HttpMethod;", "Lio/ktor/http/HeadersBuilder;", "versions", "", "Lio/ktor/http/content/Version;", "(Lio/ktor/http/HttpMethod;Lio/ktor/http/URLBuilder;Lio/ktor/http/HeadersBuilder;Ljava/util/List;)V", "getHeaders", "()Lio/ktor/http/HeadersBuilder;", "getMethod", "()Lio/ktor/http/HttpMethod;", "setMethod", "(Lio/ktor/http/HttpMethod;)V", "getUrl", "()Lio/ktor/http/URLBuilder;", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "getVersions", "()Ljava/util/ArrayList;", "setVersions", "(Ljava/util/ArrayList;)V", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: DefaultResponsePushBuilder.kt */
public final class DefaultResponsePushBuilder implements ResponsePushBuilder {
    private final HeadersBuilder headers;
    private HttpMethod method;
    private final URLBuilder url;
    private ArrayList<Version> versions;

    public DefaultResponsePushBuilder() {
        this((HttpMethod) null, (URLBuilder) null, (HeadersBuilder) null, (List) null, 15, (DefaultConstructorMarker) null);
    }

    public DefaultResponsePushBuilder(HttpMethod httpMethod, URLBuilder uRLBuilder, HeadersBuilder headersBuilder, List<? extends Version> list) {
        Intrinsics.checkNotNullParameter(httpMethod, "method");
        Intrinsics.checkNotNullParameter(uRLBuilder, RtspHeaders.Values.URL);
        Intrinsics.checkNotNullParameter(headersBuilder, "headers");
        Intrinsics.checkNotNullParameter(list, "versions");
        this.method = httpMethod;
        this.url = uRLBuilder;
        this.headers = headersBuilder;
        this.versions = list.isEmpty() ? new ArrayList<>() : new ArrayList<>(list);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ DefaultResponsePushBuilder(io.ktor.http.HttpMethod r15, io.ktor.http.URLBuilder r16, io.ktor.http.HeadersBuilder r17, java.util.List r18, int r19, kotlin.jvm.internal.DefaultConstructorMarker r20) {
        /*
            r14 = this;
            r0 = r19 & 1
            if (r0 == 0) goto L_0x000b
            io.ktor.http.HttpMethod$Companion r0 = io.ktor.http.HttpMethod.Companion
            io.ktor.http.HttpMethod r0 = r0.getGet()
            goto L_0x000c
        L_0x000b:
            r0 = r15
        L_0x000c:
            r1 = r19 & 2
            if (r1 == 0) goto L_0x0023
            io.ktor.http.URLBuilder r1 = new io.ktor.http.URLBuilder
            r12 = 511(0x1ff, float:7.16E-43)
            r13 = 0
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            r2 = r1
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13)
            goto L_0x0025
        L_0x0023:
            r1 = r16
        L_0x0025:
            r2 = r19 & 4
            if (r2 == 0) goto L_0x0032
            io.ktor.http.HeadersBuilder r2 = new io.ktor.http.HeadersBuilder
            r3 = 0
            r4 = 0
            r5 = 1
            r2.<init>(r3, r5, r4)
            goto L_0x0034
        L_0x0032:
            r2 = r17
        L_0x0034:
            r3 = r19 & 8
            if (r3 == 0) goto L_0x003e
            java.util.List r3 = kotlin.collections.CollectionsKt.emptyList()
            r4 = r14
            goto L_0x0041
        L_0x003e:
            r4 = r14
            r3 = r18
        L_0x0041:
            r14.<init>(r0, r1, r2, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.response.DefaultResponsePushBuilder.<init>(io.ktor.http.HttpMethod, io.ktor.http.URLBuilder, io.ktor.http.HeadersBuilder, java.util.List, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public HttpMethod getMethod() {
        return this.method;
    }

    public void setMethod(HttpMethod httpMethod) {
        Intrinsics.checkNotNullParameter(httpMethod, "<set-?>");
        this.method = httpMethod;
    }

    public URLBuilder getUrl() {
        return this.url;
    }

    public HeadersBuilder getHeaders() {
        return this.headers;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public DefaultResponsePushBuilder(io.ktor.http.URLBuilder r9, io.ktor.http.Headers r10) {
        /*
            r8 = this;
            java.lang.String r0 = "url"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
            java.lang.String r0 = "headers"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
            io.ktor.http.HeadersBuilder r4 = new io.ktor.http.HeadersBuilder
            r0 = 1
            r1 = 0
            r2 = 0
            r4.<init>(r2, r0, r1)
            io.ktor.util.StringValues r10 = (io.ktor.util.StringValues) r10
            r4.appendAll(r10)
            kotlin.Unit r10 = kotlin.Unit.INSTANCE
            r6 = 9
            r7 = 0
            r2 = 0
            r5 = 0
            r1 = r8
            r3 = r9
            r1.<init>(r2, r3, r4, r5, r6, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.response.DefaultResponsePushBuilder.<init>(io.ktor.http.URLBuilder, io.ktor.http.Headers):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public DefaultResponsePushBuilder(io.ktor.server.application.ApplicationCall r9) {
        /*
            r8 = this;
            java.lang.String r0 = "call"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
            io.ktor.http.URLBuilder$Companion r0 = io.ktor.http.URLBuilder.Companion
            io.ktor.http.URLBuilder r3 = io.ktor.server.util.URLBuilderKt.createFromCall(r0, r9)
            io.ktor.http.HeadersBuilder r4 = new io.ktor.http.HeadersBuilder
            r0 = 1
            r1 = 0
            r2 = 0
            r4.<init>(r2, r0, r1)
            io.ktor.server.request.ApplicationRequest r0 = r9.getRequest()
            io.ktor.http.Headers r0 = r0.getHeaders()
            io.ktor.util.StringValues r0 = (io.ktor.util.StringValues) r0
            r4.appendAll(r0)
            io.ktor.http.HttpHeaders r0 = io.ktor.http.HttpHeaders.INSTANCE
            java.lang.String r0 = r0.getReferrer()
            io.ktor.http.URLBuilder$Companion r1 = io.ktor.http.URLBuilder.Companion
            io.ktor.http.URLBuilder r9 = io.ktor.server.util.URLBuilderKt.createFromCall(r1, r9)
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            java.lang.String r9 = r9.buildString()
            r4.set(r0, r9)
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            r6 = 9
            r7 = 0
            r2 = 0
            r5 = 0
            r1 = r8
            r1.<init>(r2, r3, r4, r5, r6, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.response.DefaultResponsePushBuilder.<init>(io.ktor.server.application.ApplicationCall):void");
    }

    public ArrayList<Version> getVersions() {
        return this.versions;
    }

    public void setVersions(ArrayList<Version> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.versions = arrayList;
    }
}
