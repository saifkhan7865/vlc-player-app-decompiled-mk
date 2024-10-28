package org.videolan.vlc.gui.helpers;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.helpers.UiTools$fillAboutView$8$1", f = "UiTools.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: UiTools.kt */
final class UiTools$fillAboutView$8$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Ref.ObjectRef<String> $licenseText;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    UiTools$fillAboutView$8$1(Ref.ObjectRef<String> objectRef, Continuation<? super UiTools$fillAboutView$8$1> continuation) {
        super(2, continuation);
        this.$licenseText = objectRef;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new UiTools$fillAboutView$8$1(this.$licenseText, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((UiTools$fillAboutView$8$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x004b, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x004c, code lost:
        kotlin.io.CloseableKt.closeFinally(r2, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x004f, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r4) {
        /*
            r3 = this;
            kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r0 = r3.label
            if (r0 != 0) goto L_0x0050
            kotlin.ResultKt.throwOnFailure(r4)
            kotlin.jvm.internal.Ref$ObjectRef<java.lang.String> r4 = r3.$licenseText
            org.videolan.resources.AppContextProvider r0 = org.videolan.resources.AppContextProvider.INSTANCE
            android.content.res.Resources r0 = r0.getAppResources()
            int r1 = org.videolan.vlc.R.raw.vlc_license
            java.io.InputStream r0 = r0.openRawResource(r1)
            java.lang.String r1 = "openRawResource(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            java.nio.charset.Charset r1 = kotlin.text.Charsets.UTF_8
            java.io.InputStreamReader r2 = new java.io.InputStreamReader
            r2.<init>(r0, r1)
            java.io.Reader r2 = (java.io.Reader) r2
            boolean r0 = r2 instanceof java.io.BufferedReader
            if (r0 == 0) goto L_0x002d
            java.io.BufferedReader r2 = (java.io.BufferedReader) r2
            goto L_0x0035
        L_0x002d:
            java.io.BufferedReader r0 = new java.io.BufferedReader
            r1 = 8192(0x2000, float:1.14794E-41)
            r0.<init>(r2, r1)
            r2 = r0
        L_0x0035:
            java.io.Closeable r2 = (java.io.Closeable) r2
            r0 = r2
            java.io.BufferedReader r0 = (java.io.BufferedReader) r0     // Catch:{ all -> 0x0049 }
            java.io.Reader r0 = (java.io.Reader) r0     // Catch:{ all -> 0x0049 }
            java.lang.String r0 = kotlin.io.TextStreamsKt.readText(r0)     // Catch:{ all -> 0x0049 }
            r1 = 0
            kotlin.io.CloseableKt.closeFinally(r2, r1)
            r4.element = r0
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
            return r4
        L_0x0049:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x004b }
        L_0x004b:
            r0 = move-exception
            kotlin.io.CloseableKt.closeFinally(r2, r4)
            throw r0
        L_0x0050:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r0)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.helpers.UiTools$fillAboutView$8$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
