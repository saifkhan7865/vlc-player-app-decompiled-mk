package io.ktor.server.routing;

import io.ktor.http.Parameters;
import io.ktor.server.routing.RouteSelectorEvaluation;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\u001aF\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00042\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00042\u0006\u0010\n\u001a\u00020\u000bH\u0000¨\u0006\f"}, d2 = {"evaluatePathSegmentParameter", "Lio/ktor/server/routing/RouteSelectorEvaluation;", "segments", "", "", "segmentIndex", "", "name", "prefix", "suffix", "isOptional", "", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: RouteSelector.kt */
public final class RouteSelectorKt {
    public static /* synthetic */ RouteSelectorEvaluation evaluatePathSegmentParameter$default(List list, int i, String str, String str2, String str3, boolean z, int i2, Object obj) {
        return evaluatePathSegmentParameter(list, i, str, (i2 & 8) != 0 ? null : str2, (i2 & 16) != 0 ? null : str3, z);
    }

    private static final RouteSelectorEvaluation evaluatePathSegmentParameter$failedEvaluation(boolean z, String str) {
        if (!z) {
            return RouteSelectorEvaluation.Companion.getFailedPath();
        }
        if (str == null) {
            return RouteSelectorEvaluation.Companion.getMissing();
        }
        if (str.length() == 0) {
            return new RouteSelectorEvaluation.Success(0.2d, (Parameters) null, 1, 2, (DefaultConstructorMarker) null);
        }
        return RouteSelectorEvaluation.Companion.getMissing();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x005f, code lost:
        r7 = r7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final io.ktor.server.routing.RouteSelectorEvaluation evaluatePathSegmentParameter(java.util.List<java.lang.String> r3, int r4, java.lang.String r5, java.lang.String r6, java.lang.String r7, boolean r8) {
        /*
            java.lang.String r0 = "segments"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            java.lang.String r0 = "name"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            int r0 = r3.size()
            r1 = 0
            if (r4 < r0) goto L_0x0016
            io.ktor.server.routing.RouteSelectorEvaluation r3 = evaluatePathSegmentParameter$failedEvaluation(r8, r1)
            return r3
        L_0x0016:
            java.lang.Object r3 = r3.get(r4)
            java.lang.String r3 = (java.lang.String) r3
            r4 = r3
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4
            int r4 = r4.length()
            if (r4 != 0) goto L_0x002a
            io.ktor.server.routing.RouteSelectorEvaluation r3 = evaluatePathSegmentParameter$failedEvaluation(r8, r3)
            return r3
        L_0x002a:
            r4 = 2
            r0 = 0
            if (r6 != 0) goto L_0x0030
            r2 = r3
            goto L_0x003e
        L_0x0030:
            boolean r2 = kotlin.text.StringsKt.startsWith$default(r3, r6, r0, r4, r1)
            if (r2 == 0) goto L_0x0081
            int r2 = r6.length()
            java.lang.String r2 = kotlin.text.StringsKt.drop((java.lang.String) r3, (int) r2)
        L_0x003e:
            if (r7 != 0) goto L_0x0041
            goto L_0x004f
        L_0x0041:
            boolean r4 = kotlin.text.StringsKt.endsWith$default(r2, r7, r0, r4, r1)
            if (r4 == 0) goto L_0x007c
            int r3 = r7.length()
            java.lang.String r2 = kotlin.text.StringsKt.dropLast((java.lang.String) r2, (int) r3)
        L_0x004f:
            io.ktor.http.Parameters r3 = io.ktor.http.ParametersKt.parametersOf((java.lang.String) r5, (java.lang.String) r2)
            io.ktor.server.routing.RouteSelectorEvaluation$Success r4 = new io.ktor.server.routing.RouteSelectorEvaluation$Success
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6
            if (r6 == 0) goto L_0x005f
            int r5 = r6.length()
            if (r5 != 0) goto L_0x006a
        L_0x005f:
            java.lang.CharSequence r7 = (java.lang.CharSequence) r7
            if (r7 == 0) goto L_0x0070
            int r5 = r7.length()
            if (r5 != 0) goto L_0x006a
            goto L_0x0070
        L_0x006a:
            r5 = 4606281698874543309(0x3feccccccccccccd, double:0.9)
            goto L_0x0075
        L_0x0070:
            r5 = 4605380978949069210(0x3fe999999999999a, double:0.8)
        L_0x0075:
            r7 = 1
            r4.<init>(r5, r3, r7)
            io.ktor.server.routing.RouteSelectorEvaluation r4 = (io.ktor.server.routing.RouteSelectorEvaluation) r4
            return r4
        L_0x007c:
            io.ktor.server.routing.RouteSelectorEvaluation r3 = evaluatePathSegmentParameter$failedEvaluation(r8, r3)
            return r3
        L_0x0081:
            io.ktor.server.routing.RouteSelectorEvaluation r3 = evaluatePathSegmentParameter$failedEvaluation(r8, r3)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.routing.RouteSelectorKt.evaluatePathSegmentParameter(java.util.List, int, java.lang.String, java.lang.String, java.lang.String, boolean):io.ktor.server.routing.RouteSelectorEvaluation");
    }
}
