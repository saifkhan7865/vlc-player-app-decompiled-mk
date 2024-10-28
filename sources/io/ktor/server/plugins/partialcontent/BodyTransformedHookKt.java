package io.ktor.server.plugins.partialcontent;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u001a=\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH@ø\u0001\u0000¢\u0006\u0002\u0010\r\u0002\u0004\n\u0002\b\u0019¨\u0006\u000e"}, d2 = {"tryProcessRange", "", "Lio/ktor/server/plugins/partialcontent/BodyTransformedHook$Context;", "content", "Lio/ktor/http/content/OutgoingContent$ReadChannelContent;", "call", "Lio/ktor/server/application/ApplicationCall;", "rangesSpecifier", "Lio/ktor/http/RangesSpecifier;", "length", "", "maxRangeCount", "", "(Lio/ktor/server/plugins/partialcontent/BodyTransformedHook$Context;Lio/ktor/http/content/OutgoingContent$ReadChannelContent;Lio/ktor/server/application/ApplicationCall;Lio/ktor/http/RangesSpecifier;JILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-server-partial-content"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: BodyTransformedHook.kt */
public final class BodyTransformedHookKt {
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v5, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v3, resolved type: io.ktor.http.RangesSpecifier} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v6, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v2, resolved type: io.ktor.http.content.OutgoingContent$ReadChannelContent} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x004b  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x006c  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0080  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object tryProcessRange(io.ktor.server.plugins.partialcontent.BodyTransformedHook.Context r5, io.ktor.http.content.OutgoingContent.ReadChannelContent r6, io.ktor.server.application.ApplicationCall r7, io.ktor.http.RangesSpecifier r8, long r9, int r11, kotlin.coroutines.Continuation<? super kotlin.Unit> r12) {
        /*
            boolean r0 = r12 instanceof io.ktor.server.plugins.partialcontent.BodyTransformedHookKt$tryProcessRange$1
            if (r0 == 0) goto L_0x0014
            r0 = r12
            io.ktor.server.plugins.partialcontent.BodyTransformedHookKt$tryProcessRange$1 r0 = (io.ktor.server.plugins.partialcontent.BodyTransformedHookKt$tryProcessRange$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r12 = r0.label
            int r12 = r12 - r2
            r0.label = r12
            goto L_0x0019
        L_0x0014:
            io.ktor.server.plugins.partialcontent.BodyTransformedHookKt$tryProcessRange$1 r0 = new io.ktor.server.plugins.partialcontent.BodyTransformedHookKt$tryProcessRange$1
            r0.<init>(r12)
        L_0x0019:
            java.lang.Object r12 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x004b
            if (r2 == r4) goto L_0x0035
            if (r2 != r3) goto L_0x002d
            kotlin.ResultKt.throwOnFailure(r12)
            goto L_0x007d
        L_0x002d:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x0035:
            int r11 = r0.I$0
            long r9 = r0.J$0
            java.lang.Object r5 = r0.L$2
            r8 = r5
            io.ktor.http.RangesSpecifier r8 = (io.ktor.http.RangesSpecifier) r8
            java.lang.Object r5 = r0.L$1
            r6 = r5
            io.ktor.http.content.OutgoingContent$ReadChannelContent r6 = (io.ktor.http.content.OutgoingContent.ReadChannelContent) r6
            java.lang.Object r5 = r0.L$0
            io.ktor.server.plugins.partialcontent.BodyTransformedHook$Context r5 = (io.ktor.server.plugins.partialcontent.BodyTransformedHook.Context) r5
            kotlin.ResultKt.throwOnFailure(r12)
            goto L_0x0061
        L_0x004b:
            kotlin.ResultKt.throwOnFailure(r12)
            r0.L$0 = r5
            r0.L$1 = r6
            r0.L$2 = r8
            r0.J$0 = r9
            r0.I$0 = r11
            r0.label = r4
            java.lang.Object r12 = io.ktor.server.plugins.partialcontent.PartialContentUtilsKt.checkIfRangeHeader(r6, r7, r0)
            if (r12 != r1) goto L_0x0061
            return r1
        L_0x0061:
            r7 = r8
            r8 = r9
            r10 = r11
            java.lang.Boolean r12 = (java.lang.Boolean) r12
            boolean r11 = r12.booleanValue()
            if (r11 == 0) goto L_0x0080
            r11 = 0
            r0.L$0 = r11
            r0.L$1 = r11
            r0.L$2 = r11
            r0.label = r3
            r11 = r0
            java.lang.Object r5 = io.ktor.server.plugins.partialcontent.PartialContentUtilsKt.processRange(r5, r6, r7, r8, r10, r11)
            if (r5 != r1) goto L_0x007d
            return r1
        L_0x007d:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        L_0x0080:
            io.ktor.server.plugins.partialcontent.PartialOutgoingContent$Bypass r7 = new io.ktor.server.plugins.partialcontent.PartialOutgoingContent$Bypass
            r7.<init>(r6)
            r5.transformBodyTo(r7)
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.plugins.partialcontent.BodyTransformedHookKt.tryProcessRange(io.ktor.server.plugins.partialcontent.BodyTransformedHook$Context, io.ktor.http.content.OutgoingContent$ReadChannelContent, io.ktor.server.application.ApplicationCall, io.ktor.http.RangesSpecifier, long, int, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
