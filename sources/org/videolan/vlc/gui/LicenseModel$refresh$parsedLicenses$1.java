package org.videolan.vlc.gui;

import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H@"}, d2 = {"<anonymous>", "", "Lorg/videolan/vlc/gui/LibraryWithLicense;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.LicenseModel$refresh$parsedLicenses$1", f = "LibrariesActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: LibrariesActivity.kt */
final class LicenseModel$refresh$parsedLicenses$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<LibraryWithLicense>>, Object> {
    int label;

    LicenseModel$refresh$parsedLicenses$1(Continuation<? super LicenseModel$refresh$parsedLicenses$1> continuation) {
        super(2, continuation);
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new LicenseModel$refresh$parsedLicenses$1(continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super List<LibraryWithLicense>> continuation) {
        return ((LicenseModel$refresh$parsedLicenses$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x00c1, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x00c2, code lost:
        kotlin.io.CloseableKt.closeFinally(r1, r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00c5, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r13) {
        /*
            r12 = this;
            kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r0 = r12.label
            if (r0 != 0) goto L_0x00c6
            kotlin.ResultKt.throwOnFailure(r13)
            org.videolan.resources.AppContextProvider r13 = org.videolan.resources.AppContextProvider.INSTANCE
            android.content.res.Resources r13 = r13.getAppResources()
            int r0 = org.videolan.vlc.R.raw.libraries
            java.io.InputStream r13 = r13.openRawResource(r0)
            java.lang.String r0 = "openRawResource(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r13, r0)
            java.nio.charset.Charset r0 = kotlin.text.Charsets.UTF_8
            java.io.InputStreamReader r1 = new java.io.InputStreamReader
            r1.<init>(r13, r0)
            java.io.Reader r1 = (java.io.Reader) r1
            boolean r13 = r1 instanceof java.io.BufferedReader
            if (r13 == 0) goto L_0x002b
            java.io.BufferedReader r1 = (java.io.BufferedReader) r1
            goto L_0x0033
        L_0x002b:
            java.io.BufferedReader r13 = new java.io.BufferedReader
            r0 = 8192(0x2000, float:1.14794E-41)
            r13.<init>(r1, r0)
            r1 = r13
        L_0x0033:
            java.io.Closeable r1 = (java.io.Closeable) r1
            r13 = r1
            java.io.BufferedReader r13 = (java.io.BufferedReader) r13     // Catch:{ all -> 0x00bf }
            java.io.Reader r13 = (java.io.Reader) r13     // Catch:{ all -> 0x00bf }
            java.lang.String r13 = kotlin.io.TextStreamsKt.readText(r13)     // Catch:{ all -> 0x00bf }
            r0 = 0
            kotlin.io.CloseableKt.closeFinally(r1, r0)
            com.squareup.moshi.Moshi$Builder r0 = new com.squareup.moshi.Moshi$Builder
            r0.<init>()
            com.squareup.moshi.Moshi r0 = r0.build()
            java.lang.Class<org.videolan.vlc.gui.Licenses> r1 = org.videolan.vlc.gui.Licenses.class
            com.squareup.moshi.JsonAdapter r0 = r0.adapter(r1)
            java.lang.String r1 = "adapter(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            java.lang.Object r13 = r0.fromJson((java.lang.String) r13)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r13)
            org.videolan.vlc.gui.Licenses r13 = (org.videolan.vlc.gui.Licenses) r13
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.List r0 = (java.util.List) r0
            java.util.List r1 = r13.getLibraries()
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            java.util.Iterator r1 = r1.iterator()
        L_0x0070:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x00be
            java.lang.Object r2 = r1.next()
            org.videolan.vlc.gui.Library r2 = (org.videolan.vlc.gui.Library) r2
            java.util.List r3 = r13.getLicenses()
            java.lang.Iterable r3 = (java.lang.Iterable) r3
            java.util.Iterator r3 = r3.iterator()
        L_0x0086:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x0070
            java.lang.Object r4 = r3.next()
            org.videolan.vlc.gui.License r4 = (org.videolan.vlc.gui.License) r4
            java.lang.String r5 = r4.getId()
            java.lang.String r6 = r2.getLicense()
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r6)
            if (r5 == 0) goto L_0x0086
            org.videolan.vlc.gui.LibraryWithLicense r5 = new org.videolan.vlc.gui.LibraryWithLicense
            java.lang.String r7 = r2.getTitle()
            java.lang.String r8 = r2.getCopyright()
            java.lang.String r9 = r4.getName()
            java.lang.String r10 = r4.getDescription()
            java.lang.String r11 = r4.getLink()
            r6 = r5
            r6.<init>(r7, r8, r9, r10, r11)
            r0.add(r5)
            goto L_0x0086
        L_0x00be:
            return r0
        L_0x00bf:
            r13 = move-exception
            throw r13     // Catch:{ all -> 0x00c1 }
        L_0x00c1:
            r0 = move-exception
            kotlin.io.CloseableKt.closeFinally(r1, r13)
            throw r0
        L_0x00c6:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r0)
            goto L_0x00cf
        L_0x00ce:
            throw r13
        L_0x00cf:
            goto L_0x00ce
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.LicenseModel$refresh$parsedLicenses$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
