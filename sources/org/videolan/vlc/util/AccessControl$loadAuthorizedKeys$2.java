package org.videolan.vlc.util;

import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001*\u00020\u0004H@"}, d2 = {"<anonymous>", "", "", "Lorg/videolan/vlc/util/CertInfo;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.util.AccessControl$loadAuthorizedKeys$2", f = "AccessControl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: AccessControl.kt */
final class AccessControl$loadAuthorizedKeys$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Map<String, CertInfo>>, Object> {
    int label;

    AccessControl$loadAuthorizedKeys$2(Continuation<? super AccessControl$loadAuthorizedKeys$2> continuation) {
        super(2, continuation);
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AccessControl$loadAuthorizedKeys$2(continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Map<String, CertInfo>> continuation) {
        return ((AccessControl$loadAuthorizedKeys$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x00aa, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x00ab, code lost:
        kotlin.io.CloseableKt.closeFinally(r2, r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x00ae, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r13) {
        /*
            r12 = this;
            kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r0 = r12.label
            if (r0 != 0) goto L_0x00af
            kotlin.ResultKt.throwOnFailure(r13)
            java.util.LinkedHashMap r13 = new java.util.LinkedHashMap
            r13.<init>()
            java.util.Map r13 = (java.util.Map) r13
            org.videolan.resources.AppContextProvider r0 = org.videolan.resources.AppContextProvider.INSTANCE
            android.content.res.Resources r0 = r0.getAppResources()
            int r1 = org.videolan.vlc.R.raw.authorized_keys
            java.io.InputStream r0 = r0.openRawResource(r1)
            java.lang.String r1 = "openRawResource(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            java.nio.charset.Charset r1 = kotlin.text.Charsets.UTF_8
            java.io.InputStreamReader r2 = new java.io.InputStreamReader
            r2.<init>(r0, r1)
            java.io.Reader r2 = (java.io.Reader) r2
            boolean r0 = r2 instanceof java.io.BufferedReader
            if (r0 == 0) goto L_0x0032
            java.io.BufferedReader r2 = (java.io.BufferedReader) r2
            goto L_0x003a
        L_0x0032:
            java.io.BufferedReader r0 = new java.io.BufferedReader
            r1 = 8192(0x2000, float:1.14794E-41)
            r0.<init>(r2, r1)
            r2 = r0
        L_0x003a:
            java.io.Closeable r2 = (java.io.Closeable) r2
            r0 = r2
            java.io.BufferedReader r0 = (java.io.BufferedReader) r0     // Catch:{ all -> 0x00a8 }
            java.io.Reader r0 = (java.io.Reader) r0     // Catch:{ all -> 0x00a8 }
            java.lang.String r0 = kotlin.io.TextStreamsKt.readText(r0)     // Catch:{ all -> 0x00a8 }
            r1 = 0
            kotlin.io.CloseableKt.closeFinally(r2, r1)
            org.json.JSONArray r1 = new org.json.JSONArray
            r1.<init>(r0)
            int r0 = r1.length()
            r2 = 0
            r3 = 0
        L_0x0054:
            if (r3 >= r0) goto L_0x00a7
            java.lang.Object r4 = r1.get(r3)
            java.lang.String r5 = "null cannot be cast to non-null type org.json.JSONObject"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4, r5)
            org.json.JSONObject r4 = (org.json.JSONObject) r4
            java.lang.String r6 = "keys"
            org.json.JSONArray r6 = r4.getJSONArray(r6)
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            int r8 = r6.length()
            r9 = 0
        L_0x0071:
            if (r9 >= r8) goto L_0x0088
            java.lang.Object r10 = r6.get(r9)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r10, r5)
            org.json.JSONObject r10 = (org.json.JSONObject) r10
            java.lang.String r11 = "keyId"
            java.lang.String r10 = r10.getString(r11)
            r7.add(r10)
            int r9 = r9 + 1
            goto L_0x0071
        L_0x0088:
            java.lang.String r5 = "name"
            java.lang.String r5 = r4.getString(r5)
            java.lang.String r6 = "package"
            java.lang.String r4 = r4.getString(r6)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4)
            org.videolan.vlc.util.CertInfo r6 = new org.videolan.vlc.util.CertInfo
            kotlin.jvm.internal.Intrinsics.checkNotNull(r5)
            java.util.List r7 = (java.util.List) r7
            r6.<init>(r5, r7)
            r13.put(r4, r6)
            int r3 = r3 + 1
            goto L_0x0054
        L_0x00a7:
            return r13
        L_0x00a8:
            r13 = move-exception
            throw r13     // Catch:{ all -> 0x00aa }
        L_0x00aa:
            r0 = move-exception
            kotlin.io.CloseableKt.closeFinally(r2, r13)
            throw r0
        L_0x00af:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r0)
            goto L_0x00b8
        L_0x00b7:
            throw r13
        L_0x00b8:
            goto L_0x00b7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.util.AccessControl$loadAuthorizedKeys$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
