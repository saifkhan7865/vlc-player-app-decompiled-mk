package io.ktor.server.plugins.partialcontent;

import io.ktor.http.DateUtilsKt;
import io.ktor.http.HeaderValue;
import io.ktor.http.HttpHeaderValueParserKt;
import io.ktor.http.HttpMethod;
import io.ktor.http.HttpStatusCode;
import io.ktor.http.RangesSpecifier;
import io.ktor.http.content.EntityTagVersion;
import io.ktor.http.content.LastModifiedVersion;
import io.ktor.http.content.OutgoingContent;
import io.ktor.http.content.Version;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.http.content.HttpStatusCodeContent;
import io.ktor.server.http.content.SuppressionAttributeKt;
import io.ktor.server.plugins.partialcontent.BodyTransformedHook;
import io.ktor.server.plugins.partialcontent.PartialOutgoingContent;
import io.ktor.server.request.ApplicationRequestPropertiesKt;
import io.ktor.server.response.ApplicationResponsePropertiesKt;
import io.ktor.util.CryptoKt;
import io.ktor.util.date.DateKt;
import io.ktor.util.date.GMTDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.ranges.LongRange;
import kotlin.text.StringsKt;
import org.fusesource.jansi.AnsiRenderer;
import org.slf4j.Logger;

@Metadata(d1 = {"\u0000f\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\u001a\u001e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0000\u001a!\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH@ø\u0001\u0000¢\u0006\u0002\u0010\f\u001a\u001e\u0010\r\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u000e2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0000\u001a\u0016\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u00052\u0006\u0010\u0011\u001a\u00020\u0012H\u0000\u001a\u0012\u0010\u0013\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0014\u001a\u00020\u0012H\u0000\u001a\u0012\u0010\u0015\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00160\u0005H\u0000\u001a\f\u0010\u0017\u001a\u00020\u0001*\u00020\u000bH\u0000\u001a\f\u0010\u0018\u001a\u00020\u0001*\u00020\u000bH\u0000\u001a\u0018\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005*\b\u0012\u0004\u0012\u00020\u00100\u0005H\u0000\u001a3\u0010\u001a\u001a\u00020\u001b*\u00020\u001c2\u0006\u0010\b\u001a\u00020\t2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00160\u00052\u0006\u0010\u001e\u001a\u00020\u001fH@ø\u0001\u0000¢\u0006\u0002\u0010 \u001a5\u0010!\u001a\u00020\u001b*\u00020\u001c2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\"\u001a\u00020#2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010$\u001a\u00020%H@ø\u0001\u0000¢\u0006\u0002\u0010&\u001a$\u0010'\u001a\u00020\u001b*\u00020\u001c2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010(\u001a\u00020\u00162\u0006\u0010\u001e\u001a\u00020\u001fH\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006)"}, d2 = {"checkEntityTags", "", "actual", "Lio/ktor/http/content/EntityTagVersion;", "ifRange", "", "Lio/ktor/http/content/Version;", "checkIfRangeHeader", "content", "Lio/ktor/http/content/OutgoingContent$ReadChannelContent;", "call", "Lio/ktor/server/application/ApplicationCall;", "(Lio/ktor/http/content/OutgoingContent$ReadChannelContent;Lio/ktor/server/application/ApplicationCall;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "checkLastModified", "Lio/ktor/http/content/LastModifiedVersion;", "parseIfRangeHeader", "Lio/ktor/http/HeaderValue;", "header", "", "parseVersion", "value", "isAscending", "Lkotlin/ranges/LongRange;", "isGet", "isGetOrHead", "parseVersions", "processMultiRange", "", "Lio/ktor/server/plugins/partialcontent/BodyTransformedHook$Context;", "ranges", "length", "", "(Lio/ktor/server/plugins/partialcontent/BodyTransformedHook$Context;Lio/ktor/http/content/OutgoingContent$ReadChannelContent;Ljava/util/List;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "processRange", "rangesSpecifier", "Lio/ktor/http/RangesSpecifier;", "maxRangeCount", "", "(Lio/ktor/server/plugins/partialcontent/BodyTransformedHook$Context;Lio/ktor/http/content/OutgoingContent$ReadChannelContent;Lio/ktor/http/RangesSpecifier;JILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "processSingleRange", "range", "ktor-server-partial-content"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: PartialContentUtils.kt */
public final class PartialContentUtilsKt {
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x0120  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x0114 A[EDGE_INSN: B:76:0x0114->B:51:0x0114 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object checkIfRangeHeader(io.ktor.http.content.OutgoingContent.ReadChannelContent r8, io.ktor.server.application.ApplicationCall r9, kotlin.coroutines.Continuation<? super java.lang.Boolean> r10) {
        /*
            boolean r0 = r10 instanceof io.ktor.server.plugins.partialcontent.PartialContentUtilsKt$checkIfRangeHeader$1
            if (r0 == 0) goto L_0x0014
            r0 = r10
            io.ktor.server.plugins.partialcontent.PartialContentUtilsKt$checkIfRangeHeader$1 r0 = (io.ktor.server.plugins.partialcontent.PartialContentUtilsKt$checkIfRangeHeader$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L_0x0019
        L_0x0014:
            io.ktor.server.plugins.partialcontent.PartialContentUtilsKt$checkIfRangeHeader$1 r0 = new io.ktor.server.plugins.partialcontent.PartialContentUtilsKt$checkIfRangeHeader$1
            r0.<init>(r10)
        L_0x0019:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L_0x0038
            if (r2 != r4) goto L_0x0030
            java.lang.Object r8 = r0.L$0
            java.util.List r8 = (java.util.List) r8
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x00da
        L_0x0030:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x0038:
            kotlin.ResultKt.throwOnFailure(r10)
            io.ktor.server.application.Application r10 = r9.getApplication()
            io.ktor.util.pipeline.Pipeline r10 = (io.ktor.util.pipeline.Pipeline) r10
            io.ktor.server.application.RouteScopedPlugin r2 = io.ktor.server.plugins.conditionalheaders.ConditionalHeadersKt.getConditionalHeaders()
            io.ktor.server.application.Plugin r2 = (io.ktor.server.application.Plugin) r2
            java.lang.Object r10 = io.ktor.server.application.ApplicationPluginKt.pluginOrNull(r10, r2)
            io.ktor.server.application.PluginInstance r10 = (io.ktor.server.application.PluginInstance) r10
            io.ktor.server.request.ApplicationRequest r2 = r9.getRequest()     // Catch:{ all -> 0x014f }
            io.ktor.http.Headers r2 = r2.getHeaders()     // Catch:{ all -> 0x014f }
            io.ktor.http.HttpHeaders r5 = io.ktor.http.HttpHeaders.INSTANCE     // Catch:{ all -> 0x014f }
            java.lang.String r5 = r5.getIfRange()     // Catch:{ all -> 0x014f }
            java.util.List r2 = r2.getAll(r5)     // Catch:{ all -> 0x014f }
            if (r2 == 0) goto L_0x014a
            java.lang.Iterable r2 = (java.lang.Iterable) r2     // Catch:{ all -> 0x014f }
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch:{ all -> 0x014f }
            r6 = 10
            int r6 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r2, r6)     // Catch:{ all -> 0x014f }
            r5.<init>(r6)     // Catch:{ all -> 0x014f }
            java.util.Collection r5 = (java.util.Collection) r5     // Catch:{ all -> 0x014f }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ all -> 0x014f }
        L_0x0074:
            boolean r6 = r2.hasNext()     // Catch:{ all -> 0x014f }
            if (r6 == 0) goto L_0x0088
            java.lang.Object r6 = r2.next()     // Catch:{ all -> 0x014f }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ all -> 0x014f }
            java.util.List r6 = parseIfRangeHeader(r6)     // Catch:{ all -> 0x014f }
            r5.add(r6)     // Catch:{ all -> 0x014f }
            goto L_0x0074
        L_0x0088:
            java.util.List r5 = (java.util.List) r5     // Catch:{ all -> 0x014f }
            r2 = r5
            java.util.Collection r2 = (java.util.Collection) r2     // Catch:{ all -> 0x014f }
            boolean r2 = r2.isEmpty()     // Catch:{ all -> 0x014f }
            r2 = r2 ^ r4
            r6 = 0
            if (r2 == 0) goto L_0x0096
            goto L_0x0097
        L_0x0096:
            r5 = r6
        L_0x0097:
            if (r5 == 0) goto L_0x014a
            java.lang.Iterable r5 = (java.lang.Iterable) r5     // Catch:{ all -> 0x014f }
            java.util.Iterator r2 = r5.iterator()     // Catch:{ all -> 0x014f }
            boolean r5 = r2.hasNext()     // Catch:{ all -> 0x014f }
            if (r5 == 0) goto L_0x0142
            java.lang.Object r5 = r2.next()     // Catch:{ all -> 0x014f }
        L_0x00a9:
            boolean r7 = r2.hasNext()     // Catch:{ all -> 0x014f }
            if (r7 == 0) goto L_0x00c0
            java.lang.Object r7 = r2.next()     // Catch:{ all -> 0x014f }
            java.util.List r7 = (java.util.List) r7     // Catch:{ all -> 0x014f }
            java.util.List r5 = (java.util.List) r5     // Catch:{ all -> 0x014f }
            java.util.Collection r5 = (java.util.Collection) r5     // Catch:{ all -> 0x014f }
            java.lang.Iterable r7 = (java.lang.Iterable) r7     // Catch:{ all -> 0x014f }
            java.util.List r5 = kotlin.collections.CollectionsKt.plus(r5, r7)     // Catch:{ all -> 0x014f }
            goto L_0x00a9
        L_0x00c0:
            java.util.List r5 = (java.util.List) r5     // Catch:{ all -> 0x014f }
            if (r5 == 0) goto L_0x014a
            java.util.List r2 = parseVersions(r5)     // Catch:{ all -> 0x014f }
            if (r2 == 0) goto L_0x014a
            if (r10 == 0) goto L_0x00de
            io.ktor.http.content.OutgoingContent r8 = (io.ktor.http.content.OutgoingContent) r8
            r0.L$0 = r2
            r0.label = r4
            java.lang.Object r10 = io.ktor.server.plugins.conditionalheaders.ConditionalHeadersKt.versionsFor(r9, r8, r0)
            if (r10 != r1) goto L_0x00d9
            return r1
        L_0x00d9:
            r8 = r2
        L_0x00da:
            java.util.List r10 = (java.util.List) r10
            r2 = r8
            goto L_0x0105
        L_0x00de:
            io.ktor.http.Headers r8 = r8.getHeaders()
            java.util.List r8 = io.ktor.server.plugins.conditionalheaders.ConditionalHeadersKt.parseVersions(r8)
            r10 = r8
            java.util.Collection r10 = (java.util.Collection) r10
            boolean r10 = r10.isEmpty()
            r10 = r10 ^ r4
            if (r10 == 0) goto L_0x00f1
            r6 = r8
        L_0x00f1:
            if (r6 != 0) goto L_0x0104
            io.ktor.server.response.ApplicationResponse r8 = r9.getResponse()
            io.ktor.server.response.ResponseHeaders r8 = r8.getHeaders()
            io.ktor.http.Headers r8 = r8.allValues()
            java.util.List r10 = io.ktor.server.plugins.conditionalheaders.ConditionalHeadersKt.parseVersions(r8)
            goto L_0x0105
        L_0x0104:
            r10 = r6
        L_0x0105:
            java.lang.Iterable r10 = (java.lang.Iterable) r10
            boolean r8 = r10 instanceof java.util.Collection
            if (r8 == 0) goto L_0x0116
            r8 = r10
            java.util.Collection r8 = (java.util.Collection) r8
            boolean r8 = r8.isEmpty()
            if (r8 == 0) goto L_0x0116
        L_0x0114:
            r3 = 1
            goto L_0x013d
        L_0x0116:
            java.util.Iterator r8 = r10.iterator()
        L_0x011a:
            boolean r9 = r8.hasNext()
            if (r9 == 0) goto L_0x0114
            java.lang.Object r9 = r8.next()
            io.ktor.http.content.Version r9 = (io.ktor.http.content.Version) r9
            boolean r10 = r9 instanceof io.ktor.http.content.LastModifiedVersion
            if (r10 == 0) goto L_0x0131
            io.ktor.http.content.LastModifiedVersion r9 = (io.ktor.http.content.LastModifiedVersion) r9
            boolean r9 = checkLastModified(r9, r2)
            goto L_0x013b
        L_0x0131:
            boolean r10 = r9 instanceof io.ktor.http.content.EntityTagVersion
            if (r10 == 0) goto L_0x011a
            io.ktor.http.content.EntityTagVersion r9 = (io.ktor.http.content.EntityTagVersion) r9
            boolean r9 = checkEntityTags(r9, r2)
        L_0x013b:
            if (r9 != 0) goto L_0x011a
        L_0x013d:
            java.lang.Boolean r8 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)
            return r8
        L_0x0142:
            java.lang.UnsupportedOperationException r8 = new java.lang.UnsupportedOperationException     // Catch:{ all -> 0x014f }
            java.lang.String r9 = "Empty collection can't be reduced."
            r8.<init>(r9)     // Catch:{ all -> 0x014f }
            throw r8     // Catch:{ all -> 0x014f }
        L_0x014a:
            java.lang.Boolean r8 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r4)     // Catch:{ all -> 0x014f }
            return r8
        L_0x014f:
            java.lang.Boolean r8 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.plugins.partialcontent.PartialContentUtilsKt.checkIfRangeHeader(io.ktor.http.content.OutgoingContent$ReadChannelContent, io.ktor.server.application.ApplicationCall, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static final boolean checkLastModified(LastModifiedVersion lastModifiedVersion, List<? extends Version> list) {
        Intrinsics.checkNotNullParameter(lastModifiedVersion, "actual");
        Intrinsics.checkNotNullParameter(list, "ifRange");
        GMTDate truncateToSeconds = DateKt.truncateToSeconds(lastModifiedVersion.getLastModified());
        Iterable<Version> iterable = list;
        if ((iterable instanceof Collection) && ((Collection) iterable).isEmpty()) {
            return true;
        }
        for (Version version : iterable) {
            if ((version instanceof LastModifiedVersion) && truncateToSeconds.compareTo(((LastModifiedVersion) version).getLastModified()) > 0) {
                return false;
            }
        }
        return true;
    }

    public static final boolean checkEntityTags(EntityTagVersion entityTagVersion, List<? extends Version> list) {
        Intrinsics.checkNotNullParameter(entityTagVersion, "actual");
        Intrinsics.checkNotNullParameter(list, "ifRange");
        Iterable<Version> iterable = list;
        if ((iterable instanceof Collection) && ((Collection) iterable).isEmpty()) {
            return true;
        }
        for (Version version : iterable) {
            if ((version instanceof EntityTagVersion) && !Intrinsics.areEqual((Object) entityTagVersion.getEtag(), (Object) ((EntityTagVersion) version).getEtag())) {
                return false;
            }
        }
        return true;
    }

    public static final Object processRange(BodyTransformedHook.Context context, OutgoingContent.ReadChannelContent readChannelContent, RangesSpecifier rangesSpecifier, long j, int i, Continuation<? super Unit> continuation) {
        if (j >= 0) {
            List<LongRange> merge = rangesSpecifier.merge(j, i);
            if (merge.isEmpty()) {
                Logger logger = PartialContentKt.getLOGGER();
                logger.trace("Responding 416 RequestedRangeNotSatisfiable for " + ApplicationRequestPropertiesKt.getUri(context.getCall().getRequest()) + ": range is empty");
                ApplicationResponsePropertiesKt.contentRange$default(context.getCall().getResponse(), (LongRange) null, Boxing.boxLong(j), (String) null, 4, (Object) null);
                HttpStatusCode requestedRangeNotSatisfiable = HttpStatusCode.Companion.getRequestedRangeNotSatisfiable();
                context.transformBodyTo(new HttpStatusCodeContent(requestedRangeNotSatisfiable.description("Couldn't satisfy range request " + rangesSpecifier + ": it should comply with the restriction [0; " + j + ')')));
                return Unit.INSTANCE;
            }
            if (merge.size() != 1 && !isAscending(merge)) {
                LongRange mergeToSingle = rangesSpecifier.mergeToSingle(j);
                Intrinsics.checkNotNull(mergeToSingle);
                processSingleRange(context, readChannelContent, mergeToSingle, j);
            } else if (merge.size() == 1) {
                processSingleRange(context, readChannelContent, (LongRange) CollectionsKt.single(merge), j);
            } else {
                Object processMultiRange = processMultiRange(context, readChannelContent, merge, j, continuation);
                return processMultiRange == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? processMultiRange : Unit.INSTANCE;
            }
            return Unit.INSTANCE;
        }
        throw new IllegalArgumentException("Failed requirement.".toString());
    }

    public static final void processSingleRange(BodyTransformedHook.Context context, OutgoingContent.ReadChannelContent readChannelContent, LongRange longRange, long j) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(readChannelContent, "content");
        Intrinsics.checkNotNullParameter(longRange, "range");
        Logger logger = PartialContentKt.getLOGGER();
        logger.trace("Responding 206 PartialContent for " + ApplicationRequestPropertiesKt.getUri(context.getCall().getRequest()) + ": single range " + longRange);
        context.transformBodyTo(new PartialOutgoingContent.Single(isGet(context.getCall()), readChannelContent, longRange, j));
    }

    public static final Object processMultiRange(BodyTransformedHook.Context context, OutgoingContent.ReadChannelContent readChannelContent, List<LongRange> list, long j, Continuation<? super Unit> continuation) {
        context.getCall().getAttributes().put(SuppressionAttributeKt.getSuppressionAttribute(), Boxing.boxBoolean(true));
        PartialContentKt.getLOGGER().trace("Responding 206 PartialContent for " + ApplicationRequestPropertiesKt.getUri(context.getCall().getRequest()) + ": multiple range " + CollectionsKt.joinToString$default(list, AnsiRenderer.CODE_LIST_SEPARATOR, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null));
        context.transformBodyTo(new PartialOutgoingContent.Multiple(continuation.getContext(), isGet(context.getCall()), readChannelContent, list, j, "ktor-boundary-" + CryptoKt.hex(Random.Default.nextBytes(16))));
        return Unit.INSTANCE;
    }

    public static final boolean isGet(ApplicationCall applicationCall) {
        Intrinsics.checkNotNullParameter(applicationCall, "<this>");
        return Intrinsics.areEqual((Object) applicationCall.getRequest().getLocal().getMethod(), (Object) HttpMethod.Companion.getGet());
    }

    public static final boolean isGetOrHead(ApplicationCall applicationCall) {
        Intrinsics.checkNotNullParameter(applicationCall, "<this>");
        return isGet(applicationCall) || Intrinsics.areEqual((Object) applicationCall.getRequest().getLocal().getMethod(), (Object) HttpMethod.Companion.getHead());
    }

    public static final boolean isAscending(List<LongRange> list) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        Pair pair = TuplesKt.to(true, 0L);
        for (LongRange longRange : list) {
            pair = TuplesKt.to(Boolean.valueOf(((Boolean) pair.getFirst()).booleanValue() && ((Number) pair.getSecond()).longValue() <= longRange.getStart().longValue()), longRange.getStart());
        }
        return ((Boolean) pair.getFirst()).booleanValue();
    }

    public static final List<HeaderValue> parseIfRangeHeader(String str) {
        Intrinsics.checkNotNullParameter(str, "header");
        if (StringsKt.endsWith$default(str, " GMT", false, 2, (Object) null)) {
            return CollectionsKt.listOf(new HeaderValue(str, (List) null, 2, (DefaultConstructorMarker) null));
        }
        return HttpHeaderValueParserKt.parseHeaderValue(str);
    }

    public static final List<Version> parseVersions(List<HeaderValue> list) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        Collection arrayList = new ArrayList();
        for (HeaderValue headerValue : list) {
            if (headerValue.getQuality() != 1.0d) {
                throw new IllegalStateException("If-Range doesn't support quality".toString());
            } else if (headerValue.getParams().isEmpty()) {
                Version parseVersion = parseVersion(headerValue.getValue());
                if (parseVersion != null) {
                    arrayList.add(parseVersion);
                }
            } else {
                throw new IllegalStateException("If-Range doesn't support parameters".toString());
            }
        }
        return (List) arrayList;
    }

    public static final Version parseVersion(String str) {
        Intrinsics.checkNotNullParameter(str, "value");
        if (StringsKt.isBlank(str)) {
            return null;
        }
        if (!(!StringsKt.startsWith$default(str, "W/", false, 2, (Object) null))) {
            throw new IllegalStateException("Check failed.".toString());
        } else if (StringsKt.startsWith$default(str, "\"", false, 2, (Object) null)) {
            return EntityTagVersion.Companion.parseSingle(str);
        } else {
            return new LastModifiedVersion(DateUtilsKt.fromHttpToGmtDate(str));
        }
    }
}
