package org.videolan.vlc.gui;

import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H@"}, d2 = {"<anonymous>", "", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.AuthorsActivity$loadAuthors$1$authors$1", f = "AuthorsActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: AuthorsActivity.kt */
final class AuthorsActivity$loadAuthors$1$authors$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends String>>, Object> {
    int label;

    AuthorsActivity$loadAuthors$1$authors$1(Continuation<? super AuthorsActivity$loadAuthors$1$authors$1> continuation) {
        super(2, continuation);
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AuthorsActivity$loadAuthors$1$authors$1(continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super List<String>> continuation) {
        return ((AuthorsActivity$loadAuthors$1$authors$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0070, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0071, code lost:
        kotlin.io.CloseableKt.closeFinally(r1, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0074, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r6) {
        /*
            r5 = this;
            kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r0 = r5.label
            if (r0 != 0) goto L_0x0075
            kotlin.ResultKt.throwOnFailure(r6)
            org.videolan.resources.AppContextProvider r6 = org.videolan.resources.AppContextProvider.INSTANCE
            android.content.res.Resources r6 = r6.getAppResources()
            int r0 = org.videolan.vlc.R.raw.authors
            java.io.InputStream r6 = r6.openRawResource(r0)
            java.lang.String r0 = "openRawResource(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r0)
            java.nio.charset.Charset r0 = kotlin.text.Charsets.UTF_8
            java.io.InputStreamReader r1 = new java.io.InputStreamReader
            r1.<init>(r6, r0)
            java.io.Reader r1 = (java.io.Reader) r1
            boolean r6 = r1 instanceof java.io.BufferedReader
            if (r6 == 0) goto L_0x002b
            java.io.BufferedReader r1 = (java.io.BufferedReader) r1
            goto L_0x0033
        L_0x002b:
            java.io.BufferedReader r6 = new java.io.BufferedReader
            r0 = 8192(0x2000, float:1.14794E-41)
            r6.<init>(r1, r0)
            r1 = r6
        L_0x0033:
            java.io.Closeable r1 = (java.io.Closeable) r1
            r6 = r1
            java.io.BufferedReader r6 = (java.io.BufferedReader) r6     // Catch:{ all -> 0x006e }
            java.io.Reader r6 = (java.io.Reader) r6     // Catch:{ all -> 0x006e }
            java.lang.String r6 = kotlin.io.TextStreamsKt.readText(r6)     // Catch:{ all -> 0x006e }
            r0 = 0
            kotlin.io.CloseableKt.closeFinally(r1, r0)
            com.squareup.moshi.Moshi$Builder r0 = new com.squareup.moshi.Moshi$Builder
            r0.<init>()
            com.squareup.moshi.Moshi r0 = r0.build()
            java.lang.Class<java.util.List> r1 = java.util.List.class
            java.lang.reflect.Type r1 = (java.lang.reflect.Type) r1
            r2 = 1
            java.lang.reflect.Type[] r2 = new java.lang.reflect.Type[r2]
            r3 = 0
            java.lang.Class<java.lang.String> r4 = java.lang.String.class
            r2[r3] = r4
            java.lang.reflect.ParameterizedType r1 = com.squareup.moshi.Types.newParameterizedType(r1, r2)
            java.lang.reflect.Type r1 = (java.lang.reflect.Type) r1
            com.squareup.moshi.JsonAdapter r0 = r0.adapter((java.lang.reflect.Type) r1)
            java.lang.String r1 = "adapter(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            java.lang.Object r6 = r0.fromJson((java.lang.String) r6)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r6)
            return r6
        L_0x006e:
            r6 = move-exception
            throw r6     // Catch:{ all -> 0x0070 }
        L_0x0070:
            r0 = move-exception
            kotlin.io.CloseableKt.closeFinally(r1, r6)
            throw r0
        L_0x0075:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.AuthorsActivity$loadAuthors$1$authors$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
