package io.ktor.network.selector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\t\b\u0001\u0018\u0000 \u000b2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u000bB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\f"}, d2 = {"Lio/ktor/network/selector/SelectInterest;", "", "flag", "", "(Ljava/lang/String;II)V", "getFlag", "()I", "READ", "WRITE", "ACCEPT", "CONNECT", "Companion", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: SelectorManager.kt */
public enum SelectInterest {
    READ(1),
    WRITE(4),
    ACCEPT(16),
    CONNECT(8);
    
    /* access modifiers changed from: private */
    public static final SelectInterest[] AllInterests = null;
    public static final Companion Companion = null;
    /* access modifiers changed from: private */
    public static final int[] flags = null;
    /* access modifiers changed from: private */
    public static final int size = 0;
    private final int flag;

    private SelectInterest(int i) {
        this.flag = i;
    }

    public final int getFlag() {
        return this.flag;
    }

    static {
        int i;
        Companion = new Companion((DefaultConstructorMarker) null);
        AllInterests = values();
        SelectInterest[] values = values();
        Collection arrayList = new ArrayList(values.length);
        for (SelectInterest selectInterest : values) {
            arrayList.add(Integer.valueOf(selectInterest.flag));
        }
        flags = CollectionsKt.toIntArray((List) arrayList);
        size = values().length;
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0019\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lio/ktor/network/selector/SelectInterest$Companion;", "", "()V", "AllInterests", "", "Lio/ktor/network/selector/SelectInterest;", "getAllInterests", "()[Lio/ktor/network/selector/SelectInterest;", "[Lio/ktor/network/selector/SelectInterest;", "flags", "", "getFlags", "()[I", "size", "", "getSize", "()I", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: SelectorManager.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final SelectInterest[] getAllInterests() {
            return SelectInterest.AllInterests;
        }

        public final int[] getFlags() {
            return SelectInterest.flags;
        }

        public final int getSize() {
            return SelectInterest.size;
        }
    }
}
