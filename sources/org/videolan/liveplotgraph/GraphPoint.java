package org.videolan.liveplotgraph;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001R\u001a\u0010\u0006\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\b\"\u0004\b\r\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\b¨\u0006\u001a"}, d2 = {"Lorg/videolan/liveplotgraph/GraphPoint;", "", "x", "", "y", "(FF)V", "dx", "getDx", "()F", "setDx", "(F)V", "dy", "getDy", "setDy", "getX", "getY", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "live-plot-graph_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PlotView.kt */
public final class GraphPoint {
    private float dx;
    private float dy;
    private final float x;
    private final float y;

    public static /* synthetic */ GraphPoint copy$default(GraphPoint graphPoint, float f, float f2, int i, Object obj) {
        if ((i & 1) != 0) {
            f = graphPoint.x;
        }
        if ((i & 2) != 0) {
            f2 = graphPoint.y;
        }
        return graphPoint.copy(f, f2);
    }

    public final float component1() {
        return this.x;
    }

    public final float component2() {
        return this.y;
    }

    public final GraphPoint copy(float f, float f2) {
        return new GraphPoint(f, f2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GraphPoint)) {
            return false;
        }
        GraphPoint graphPoint = (GraphPoint) obj;
        return Float.compare(this.x, graphPoint.x) == 0 && Float.compare(this.y, graphPoint.y) == 0;
    }

    public int hashCode() {
        return (Float.floatToIntBits(this.x) * 31) + Float.floatToIntBits(this.y);
    }

    public String toString() {
        return "GraphPoint(x=" + this.x + ", y=" + this.y + ')';
    }

    public GraphPoint(float f, float f2) {
        this.x = f;
        this.y = f2;
    }

    public final float getX() {
        return this.x;
    }

    public final float getY() {
        return this.y;
    }

    public final float getDx() {
        return this.dx;
    }

    public final void setDx(float f) {
        this.dx = f;
    }

    public final float getDy() {
        return this.dy;
    }

    public final void setDy(float f) {
        this.dy = f;
    }
}
