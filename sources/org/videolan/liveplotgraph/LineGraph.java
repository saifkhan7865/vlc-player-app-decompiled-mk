package org.videolan.liveplotgraph;

import android.graphics.Paint;
import java.util.HashMap;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\b\u0018\u00002\u00020\u0001BC\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012$\b\u0002\u0010\u0007\u001a\u001e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bj\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n`\u000b¢\u0006\u0002\u0010\fJ\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J%\u0010\u001d\u001a\u001e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bj\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n`\u000bHÆ\u0003JM\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032$\b\u0002\u0010\u0007\u001a\u001e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bj\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n`\u000bHÆ\u0001J\u0013\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\u0001H\u0002J\t\u0010\"\u001a\u00020\u0003HÖ\u0001J\t\u0010#\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR-\u0010\u0007\u001a\u001e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bj\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n`\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\u001b\u0010\u0012\u001a\u00020\u00138FX\u0002¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019¨\u0006$"}, d2 = {"Lorg/videolan/liveplotgraph/LineGraph;", "", "index", "", "title", "", "color", "data", "Ljava/util/HashMap;", "", "", "Lkotlin/collections/HashMap;", "(ILjava/lang/String;ILjava/util/HashMap;)V", "getColor", "()I", "getData", "()Ljava/util/HashMap;", "getIndex", "paint", "Landroid/graphics/Paint;", "getPaint", "()Landroid/graphics/Paint;", "paint$delegate", "Lkotlin/Lazy;", "getTitle", "()Ljava/lang/String;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "live-plot-graph_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: LineGraph.kt */
public final class LineGraph {
    private final int color;
    private final HashMap<Long, Float> data;
    private final int index;
    private final Lazy paint$delegate;
    private final String title;

    public static /* synthetic */ LineGraph copy$default(LineGraph lineGraph, int i, String str, int i2, HashMap<Long, Float> hashMap, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = lineGraph.index;
        }
        if ((i3 & 2) != 0) {
            str = lineGraph.title;
        }
        if ((i3 & 4) != 0) {
            i2 = lineGraph.color;
        }
        if ((i3 & 8) != 0) {
            hashMap = lineGraph.data;
        }
        return lineGraph.copy(i, str, i2, hashMap);
    }

    public final int component1() {
        return this.index;
    }

    public final String component2() {
        return this.title;
    }

    public final int component3() {
        return this.color;
    }

    public final HashMap<Long, Float> component4() {
        return this.data;
    }

    public final LineGraph copy(int i, String str, int i2, HashMap<Long, Float> hashMap) {
        Intrinsics.checkNotNullParameter(str, "title");
        Intrinsics.checkNotNullParameter(hashMap, "data");
        return new LineGraph(i, str, i2, hashMap);
    }

    public int hashCode() {
        return (((((this.index * 31) + this.title.hashCode()) * 31) + this.color) * 31) + this.data.hashCode();
    }

    public String toString() {
        return "LineGraph(index=" + this.index + ", title=" + this.title + ", color=" + this.color + ", data=" + this.data + ')';
    }

    public LineGraph(int i, String str, int i2, HashMap<Long, Float> hashMap) {
        Intrinsics.checkNotNullParameter(str, "title");
        Intrinsics.checkNotNullParameter(hashMap, "data");
        this.index = i;
        this.title = str;
        this.color = i2;
        this.data = hashMap;
        this.paint$delegate = LazyKt.lazy(new LineGraph$paint$2(this));
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ LineGraph(int i, String str, int i2, HashMap hashMap, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, str, i2, (i3 & 8) != 0 ? new HashMap() : hashMap);
    }

    public final int getColor() {
        return this.color;
    }

    public final HashMap<Long, Float> getData() {
        return this.data;
    }

    public final int getIndex() {
        return this.index;
    }

    public final String getTitle() {
        return this.title;
    }

    public final Paint getPaint() {
        return (Paint) this.paint$delegate.getValue();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof LineGraph) || ((LineGraph) obj).index != this.index) {
            return super.equals(obj);
        }
        return true;
    }
}
