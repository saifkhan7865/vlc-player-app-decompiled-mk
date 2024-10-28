package io.ktor.server.routing;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import org.videolan.vlc.ArtworkProvider;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \n2\u00020\u0001:\u0001\nB\u0015\b\u0002\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\b\u0010\b\u001a\u00020\tH\u0016R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000b"}, d2 = {"Lio/ktor/server/routing/RoutingPath;", "", "parts", "", "Lio/ktor/server/routing/RoutingPathSegment;", "(Ljava/util/List;)V", "getParts", "()Ljava/util/List;", "toString", "", "Companion", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: RoutingPath.kt */
public final class RoutingPath {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final RoutingPath root = new RoutingPath(CollectionsKt.emptyList());
    private final List<RoutingPathSegment> parts;

    public /* synthetic */ RoutingPath(List list, DefaultConstructorMarker defaultConstructorMarker) {
        this(list);
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\n"}, d2 = {"Lio/ktor/server/routing/RoutingPath$Companion;", "", "()V", "root", "Lio/ktor/server/routing/RoutingPath;", "getRoot", "()Lio/ktor/server/routing/RoutingPath;", "parse", "path", "", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: RoutingPath.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final RoutingPath getRoot() {
            return RoutingPath.root;
        }

        public final RoutingPath parse(String str) {
            Intrinsics.checkNotNullParameter(str, ArtworkProvider.PATH);
            if (Intrinsics.areEqual((Object) str, (Object) "/")) {
                return getRoot();
            }
            return new RoutingPath(SequencesKt.toList(SequencesKt.map(SequencesKt.filter(StringsKt.splitToSequence$default((CharSequence) str, new String[]{"/"}, false, 0, 6, (Object) null), RoutingPath$Companion$parse$segments$1.INSTANCE), RoutingPath$Companion$parse$segments$2.INSTANCE)), (DefaultConstructorMarker) null);
        }
    }

    private RoutingPath(List<RoutingPathSegment> list) {
        this.parts = list;
    }

    public final List<RoutingPathSegment> getParts() {
        return this.parts;
    }

    public String toString() {
        return CollectionsKt.joinToString$default(this.parts, "/", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, RoutingPath$toString$1.INSTANCE, 30, (Object) null);
    }
}
