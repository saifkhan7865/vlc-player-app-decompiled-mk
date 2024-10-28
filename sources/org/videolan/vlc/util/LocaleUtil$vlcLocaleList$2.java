package org.videolan.vlc.util;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.videolan.vlc.util.LocaleUtil;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Lorg/videolan/vlc/util/LocaleUtil$VLCLocale;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: LocaleUtil.kt */
final class LocaleUtil$vlcLocaleList$2 extends Lambda implements Function0<List<? extends LocaleUtil.VLCLocale>> {
    public static final LocaleUtil$vlcLocaleList$2 INSTANCE = new LocaleUtil$vlcLocaleList$2();

    LocaleUtil$vlcLocaleList$2() {
        super(0);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0068, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0069, code lost:
        kotlin.io.CloseableKt.closeFinally(r2, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x006c, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<org.videolan.vlc.util.LocaleUtil.VLCLocale> invoke() {
        /*
            r6 = this;
            org.videolan.resources.AppContextProvider r0 = org.videolan.resources.AppContextProvider.INSTANCE
            android.content.res.Resources r0 = r0.getAppResources()
            int r1 = org.videolan.vlc.R.raw.vlc_locales
            java.io.InputStream r0 = r0.openRawResource(r1)
            java.lang.String r1 = "openRawResource(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            java.nio.charset.Charset r1 = kotlin.text.Charsets.UTF_8
            java.io.InputStreamReader r2 = new java.io.InputStreamReader
            r2.<init>(r0, r1)
            java.io.Reader r2 = (java.io.Reader) r2
            boolean r0 = r2 instanceof java.io.BufferedReader
            if (r0 == 0) goto L_0x0021
            java.io.BufferedReader r2 = (java.io.BufferedReader) r2
            goto L_0x0029
        L_0x0021:
            java.io.BufferedReader r0 = new java.io.BufferedReader
            r1 = 8192(0x2000, float:1.14794E-41)
            r0.<init>(r2, r1)
            r2 = r0
        L_0x0029:
            java.io.Closeable r2 = (java.io.Closeable) r2
            r0 = r2
            java.io.BufferedReader r0 = (java.io.BufferedReader) r0     // Catch:{ all -> 0x0066 }
            java.io.Reader r0 = (java.io.Reader) r0     // Catch:{ all -> 0x0066 }
            java.lang.String r0 = kotlin.io.TextStreamsKt.readText(r0)     // Catch:{ all -> 0x0066 }
            r1 = 0
            kotlin.io.CloseableKt.closeFinally(r2, r1)
            com.squareup.moshi.Moshi$Builder r1 = new com.squareup.moshi.Moshi$Builder
            r1.<init>()
            com.squareup.moshi.Moshi r1 = r1.build()
            java.lang.Class<java.util.List> r2 = java.util.List.class
            java.lang.reflect.Type r2 = (java.lang.reflect.Type) r2
            r3 = 1
            java.lang.reflect.Type[] r3 = new java.lang.reflect.Type[r3]
            r4 = 0
            java.lang.Class<org.videolan.vlc.util.LocaleUtil$VLCLocale> r5 = org.videolan.vlc.util.LocaleUtil.VLCLocale.class
            r3[r4] = r5
            java.lang.reflect.ParameterizedType r2 = com.squareup.moshi.Types.newParameterizedType(r2, r3)
            java.lang.reflect.Type r2 = (java.lang.reflect.Type) r2
            com.squareup.moshi.JsonAdapter r1 = r1.adapter((java.lang.reflect.Type) r2)
            java.lang.String r2 = "adapter(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            java.lang.Object r0 = r1.fromJson((java.lang.String) r0)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            java.util.List r0 = (java.util.List) r0
            return r0
        L_0x0066:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0068 }
        L_0x0068:
            r1 = move-exception
            kotlin.io.CloseableKt.closeFinally(r2, r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.util.LocaleUtil$vlcLocaleList$2.invoke():java.util.List");
    }
}
