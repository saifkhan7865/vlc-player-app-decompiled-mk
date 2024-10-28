package io.ktor.http.cio;

import io.ktor.http.cio.internals.AsciiCharTree;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.UInt$$ExternalSyntheticBackport0;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B3\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0002\u0010\tJ\b\u0010\u0010\u001a\u00020\bH\u0002J\u0013\u0010\u0011\u001a\u00020\u00032\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001H\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\bH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000b¨\u0006\u0017"}, d2 = {"Lio/ktor/http/cio/ConnectionOptions;", "", "close", "", "keepAlive", "upgrade", "extraOptions", "", "", "(ZZZLjava/util/List;)V", "getClose", "()Z", "getExtraOptions", "()Ljava/util/List;", "getKeepAlive", "getUpgrade", "buildToString", "equals", "other", "hashCode", "", "toString", "Companion", "ktor-http-cio"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ConnectionOptions.kt */
public final class ConnectionOptions {
    /* access modifiers changed from: private */
    public static final ConnectionOptions Close;
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final ConnectionOptions KeepAlive;
    /* access modifiers changed from: private */
    public static final ConnectionOptions Upgrade;
    /* access modifiers changed from: private */
    public static final AsciiCharTree<Pair<String, ConnectionOptions>> knownTypes;
    private final boolean close;
    private final List<String> extraOptions;
    private final boolean keepAlive;
    private final boolean upgrade;

    public ConnectionOptions() {
        this(false, false, false, (List) null, 15, (DefaultConstructorMarker) null);
    }

    public ConnectionOptions(boolean z, boolean z2, boolean z3, List<String> list) {
        Intrinsics.checkNotNullParameter(list, "extraOptions");
        this.close = z;
        this.keepAlive = z2;
        this.upgrade = z3;
        this.extraOptions = list;
    }

    public final boolean getClose() {
        return this.close;
    }

    public final boolean getKeepAlive() {
        return this.keepAlive;
    }

    public final boolean getUpgrade() {
        return this.upgrade;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ConnectionOptions(boolean z, boolean z2, boolean z3, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z, (i & 2) != 0 ? false : z2, (i & 4) != 0 ? false : z3, (i & 8) != 0 ? CollectionsKt.emptyList() : list);
    }

    public final List<String> getExtraOptions() {
        return this.extraOptions;
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u00042\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011J\u0010\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0011H\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0011\u0010\t\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R \u0010\u000b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00040\r0\fX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lio/ktor/http/cio/ConnectionOptions$Companion;", "", "()V", "Close", "Lio/ktor/http/cio/ConnectionOptions;", "getClose", "()Lio/ktor/http/cio/ConnectionOptions;", "KeepAlive", "getKeepAlive", "Upgrade", "getUpgrade", "knownTypes", "Lio/ktor/http/cio/internals/AsciiCharTree;", "Lkotlin/Pair;", "", "parse", "connection", "", "parseSlow", "ktor-http-cio"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: ConnectionOptions.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ConnectionOptions getClose() {
            return ConnectionOptions.Close;
        }

        public final ConnectionOptions getKeepAlive() {
            return ConnectionOptions.KeepAlive;
        }

        public final ConnectionOptions getUpgrade() {
            return ConnectionOptions.Upgrade;
        }

        public final ConnectionOptions parse(CharSequence charSequence) {
            if (charSequence == null) {
                return null;
            }
            List search$default = AsciiCharTree.search$default(ConnectionOptions.knownTypes, charSequence, 0, 0, true, ConnectionOptions$Companion$parse$known$1.INSTANCE, 6, (Object) null);
            if (search$default.size() == 1) {
                return (ConnectionOptions) ((Pair) search$default.get(0)).getSecond();
            }
            return parseSlow(charSequence);
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v14, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v7, resolved type: io.ktor.http.cio.ConnectionOptions} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private final io.ktor.http.cio.ConnectionOptions parseSlow(java.lang.CharSequence r13) {
            /*
                r12 = this;
                int r6 = r13.length()
                r7 = 0
                r0 = 0
                r8 = r0
                r9 = r8
                r0 = 0
                r1 = 0
            L_0x000a:
                if (r0 >= r6) goto L_0x00b4
            L_0x000c:
                char r2 = r13.charAt(r0)
                r3 = 44
                r4 = 32
                if (r2 == r4) goto L_0x001b
                if (r2 == r3) goto L_0x001b
                r10 = r0
                r11 = r10
                goto L_0x0021
            L_0x001b:
                int r0 = r0 + 1
                if (r0 < r6) goto L_0x000c
                r10 = r0
                r11 = r1
            L_0x0021:
                if (r10 >= r6) goto L_0x002f
                char r0 = r13.charAt(r10)
                if (r0 == r4) goto L_0x002f
                if (r0 != r3) goto L_0x002c
                goto L_0x002f
            L_0x002c:
                int r10 = r10 + 1
                goto L_0x0021
            L_0x002f:
                io.ktor.http.cio.internals.AsciiCharTree r0 = io.ktor.http.cio.ConnectionOptions.knownTypes
                io.ktor.http.cio.ConnectionOptions$Companion$parseSlow$detected$1 r1 = io.ktor.http.cio.ConnectionOptions$Companion$parseSlow$detected$1.INSTANCE
                r5 = r1
                kotlin.jvm.functions.Function2 r5 = (kotlin.jvm.functions.Function2) r5
                r4 = 1
                r1 = r13
                r2 = r11
                r3 = r10
                java.util.List r0 = r0.search(r1, r2, r3, r4, r5)
                java.lang.Object r0 = kotlin.collections.CollectionsKt.singleOrNull(r0)
                kotlin.Pair r0 = (kotlin.Pair) r0
                if (r0 != 0) goto L_0x005c
                if (r9 != 0) goto L_0x0050
                java.util.ArrayList r0 = new java.util.ArrayList
                r0.<init>()
                r9 = r0
            L_0x0050:
                java.lang.CharSequence r0 = r13.subSequence(r11, r10)
                java.lang.String r0 = r0.toString()
                r9.add(r0)
                goto L_0x0065
            L_0x005c:
                if (r8 != 0) goto L_0x0068
                java.lang.Object r0 = r0.getSecond()
                r8 = r0
                io.ktor.http.cio.ConnectionOptions r8 = (io.ktor.http.cio.ConnectionOptions) r8
            L_0x0065:
                r0 = r10
                r1 = r11
                goto L_0x000a
            L_0x0068:
                io.ktor.http.cio.ConnectionOptions r1 = new io.ktor.http.cio.ConnectionOptions
                boolean r2 = r8.getClose()
                r3 = 1
                if (r2 != 0) goto L_0x0080
                java.lang.Object r2 = r0.getSecond()
                io.ktor.http.cio.ConnectionOptions r2 = (io.ktor.http.cio.ConnectionOptions) r2
                boolean r2 = r2.getClose()
                if (r2 == 0) goto L_0x007e
                goto L_0x0080
            L_0x007e:
                r2 = 0
                goto L_0x0081
            L_0x0080:
                r2 = 1
            L_0x0081:
                boolean r4 = r8.getKeepAlive()
                if (r4 != 0) goto L_0x0096
                java.lang.Object r4 = r0.getSecond()
                io.ktor.http.cio.ConnectionOptions r4 = (io.ktor.http.cio.ConnectionOptions) r4
                boolean r4 = r4.getKeepAlive()
                if (r4 == 0) goto L_0x0094
                goto L_0x0096
            L_0x0094:
                r4 = 0
                goto L_0x0097
            L_0x0096:
                r4 = 1
            L_0x0097:
                boolean r5 = r8.getUpgrade()
                if (r5 != 0) goto L_0x00ab
                java.lang.Object r0 = r0.getSecond()
                io.ktor.http.cio.ConnectionOptions r0 = (io.ktor.http.cio.ConnectionOptions) r0
                boolean r0 = r0.getUpgrade()
                if (r0 == 0) goto L_0x00aa
                goto L_0x00ab
            L_0x00aa:
                r3 = 0
            L_0x00ab:
                java.util.List r0 = kotlin.collections.CollectionsKt.emptyList()
                r1.<init>(r2, r4, r3, r0)
                r8 = r1
                goto L_0x0065
            L_0x00b4:
                if (r8 != 0) goto L_0x00ba
                io.ktor.http.cio.ConnectionOptions r8 = r12.getKeepAlive()
            L_0x00ba:
                if (r9 != 0) goto L_0x00bd
                goto L_0x00d1
            L_0x00bd:
                io.ktor.http.cio.ConnectionOptions r0 = new io.ktor.http.cio.ConnectionOptions
                boolean r1 = r8.getClose()
                boolean r2 = r8.getKeepAlive()
                boolean r3 = r8.getUpgrade()
                java.util.List r9 = (java.util.List) r9
                r0.<init>(r1, r2, r3, r9)
                r8 = r0
            L_0x00d1:
                return r8
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.cio.ConnectionOptions.Companion.parseSlow(java.lang.CharSequence):io.ktor.http.cio.ConnectionOptions");
        }
    }

    static {
        ConnectionOptions connectionOptions = new ConnectionOptions(true, false, false, (List) null, 14, (DefaultConstructorMarker) null);
        Close = connectionOptions;
        ConnectionOptions connectionOptions2 = new ConnectionOptions(false, true, false, (List) null, 13, (DefaultConstructorMarker) null);
        KeepAlive = connectionOptions2;
        ConnectionOptions connectionOptions3 = new ConnectionOptions(false, false, true, (List) null, 11, (DefaultConstructorMarker) null);
        Upgrade = connectionOptions3;
        knownTypes = AsciiCharTree.Companion.build(CollectionsKt.listOf(TuplesKt.to("close", connectionOptions), TuplesKt.to("keep-alive", connectionOptions2), TuplesKt.to("upgrade", connectionOptions3)), ConnectionOptions$Companion$knownTypes$1.INSTANCE, ConnectionOptions$Companion$knownTypes$2.INSTANCE);
    }

    public String toString() {
        if (!this.extraOptions.isEmpty()) {
            return buildToString();
        }
        boolean z = this.close;
        if (z && !this.keepAlive && !this.upgrade) {
            return "close";
        }
        if (!z && this.keepAlive && !this.upgrade) {
            return "keep-alive";
        }
        if (z || !this.keepAlive || !this.upgrade) {
            return buildToString();
        }
        return "keep-alive, Upgrade";
    }

    private final String buildToString() {
        StringBuilder sb = new StringBuilder();
        ArrayList arrayList = new ArrayList(this.extraOptions.size() + 3);
        if (this.close) {
            arrayList.add("close");
        }
        if (this.keepAlive) {
            arrayList.add("keep-alive");
        }
        if (this.upgrade) {
            arrayList.add("Upgrade");
        }
        if (!this.extraOptions.isEmpty()) {
            arrayList.addAll(this.extraOptions);
        }
        CollectionsKt.joinTo$default(arrayList, sb, (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 126, (Object) null);
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ConnectionOptions connectionOptions = (ConnectionOptions) obj;
        return this.close == connectionOptions.close && this.keepAlive == connectionOptions.keepAlive && this.upgrade == connectionOptions.upgrade && Intrinsics.areEqual((Object) this.extraOptions, (Object) connectionOptions.extraOptions);
    }

    public int hashCode() {
        return (((((UInt$$ExternalSyntheticBackport0.m(this.close) * 31) + UInt$$ExternalSyntheticBackport0.m(this.keepAlive)) * 31) + UInt$$ExternalSyntheticBackport0.m(this.upgrade)) * 31) + this.extraOptions.hashCode();
    }
}
