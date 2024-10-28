package io.ktor.server.http.content;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0010\u0018\u00002\u00020\u0001BI\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\u001a\b\u0002\u0010\b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00070\n0\t¢\u0006\u0002\u0010\u000bR\u001a\u0010\u0004\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\r\"\u0004\b\u0011\u0010\u000fR\u001a\u0010\u0005\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\r\"\u0004\b\u0013\u0010\u000fR&\u0010\b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00070\n0\tX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0006\u001a\u00020\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019¨\u0006\u001a"}, d2 = {"Lio/ktor/server/http/content/SPAConfig;", "", "defaultPage", "", "applicationRoute", "filesPath", "useResources", "", "ignoredFiles", "", "Lkotlin/Function1;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/util/List;)V", "getApplicationRoute", "()Ljava/lang/String;", "setApplicationRoute", "(Ljava/lang/String;)V", "getDefaultPage", "setDefaultPage", "getFilesPath", "setFilesPath", "getIgnoredFiles$ktor_server_core", "()Ljava/util/List;", "getUseResources", "()Z", "setUseResources", "(Z)V", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: SinglePageApplication.kt */
public final class SPAConfig {
    private String applicationRoute;
    private String defaultPage;
    private String filesPath;
    private final List<Function1<String, Boolean>> ignoredFiles;
    private boolean useResources;

    public SPAConfig() {
        this((String) null, (String) null, (String) null, false, (List) null, 31, (DefaultConstructorMarker) null);
    }

    public SPAConfig(String str, String str2, String str3, boolean z, List<Function1<String, Boolean>> list) {
        Intrinsics.checkNotNullParameter(str, "defaultPage");
        Intrinsics.checkNotNullParameter(str2, "applicationRoute");
        Intrinsics.checkNotNullParameter(str3, "filesPath");
        Intrinsics.checkNotNullParameter(list, "ignoredFiles");
        this.defaultPage = str;
        this.applicationRoute = str2;
        this.filesPath = str3;
        this.useResources = z;
        this.ignoredFiles = list;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ SPAConfig(java.lang.String r4, java.lang.String r5, java.lang.String r6, boolean r7, java.util.List r8, int r9, kotlin.jvm.internal.DefaultConstructorMarker r10) {
        /*
            r3 = this;
            r10 = r9 & 1
            if (r10 == 0) goto L_0x0006
            java.lang.String r4 = "index.html"
        L_0x0006:
            r10 = r9 & 2
            if (r10 == 0) goto L_0x000c
            java.lang.String r5 = "/"
        L_0x000c:
            r10 = r5
            r5 = r9 & 4
            if (r5 == 0) goto L_0x0013
            java.lang.String r6 = ""
        L_0x0013:
            r0 = r6
            r5 = r9 & 8
            if (r5 == 0) goto L_0x001b
            r7 = 0
            r1 = 0
            goto L_0x001c
        L_0x001b:
            r1 = r7
        L_0x001c:
            r5 = r9 & 16
            if (r5 == 0) goto L_0x0028
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            r8 = r5
            java.util.List r8 = (java.util.List) r8
        L_0x0028:
            r2 = r8
            r5 = r3
            r6 = r4
            r7 = r10
            r8 = r0
            r9 = r1
            r10 = r2
            r5.<init>(r6, r7, r8, r9, r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.http.content.SPAConfig.<init>(java.lang.String, java.lang.String, java.lang.String, boolean, java.util.List, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final String getDefaultPage() {
        return this.defaultPage;
    }

    public final void setDefaultPage(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.defaultPage = str;
    }

    public final String getApplicationRoute() {
        return this.applicationRoute;
    }

    public final void setApplicationRoute(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.applicationRoute = str;
    }

    public final String getFilesPath() {
        return this.filesPath;
    }

    public final void setFilesPath(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.filesPath = str;
    }

    public final boolean getUseResources() {
        return this.useResources;
    }

    public final void setUseResources(boolean z) {
        this.useResources = z;
    }

    public final List<Function1<String, Boolean>> getIgnoredFiles$ktor_server_core() {
        return this.ignoredFiles;
    }
}
