package org.videolan.liveplotgraph;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;
import io.ktor.util.date.GMTDateParser;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.videolan.tools.KotlinExtensionsKt;

@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010&\n\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B\u001f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\"\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\t2\u0012\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00170#J\u000e\u0010$\u001a\u00020 2\u0006\u0010%\u001a\u00020\u000eJ\u000e\u0010&\u001a\u00020 2\u0006\u0010'\u001a\u00020\u0013J\u0006\u0010(\u001a\u00020 J*\u0010)\u001a\u00020 2\b\u0010*\u001a\u0004\u0018\u00010+2\u0006\u0010,\u001a\u00020\u00172\u0006\u0010-\u001a\u00020\u00152\u0006\u0010.\u001a\u00020\u0015H\u0002J*\u0010/\u001a\u00020 2\u0006\u0010,\u001a\u00020\u00172\u0006\u0010-\u001a\u00020\u00152\u0006\u0010.\u001a\u00020\u00152\b\u0010*\u001a\u0004\u0018\u00010+H\u0002JP\u00100\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00170#2\u0012\u00101\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u0017022\u0006\u0010,\u001a\u00020\u00172\u0006\u0010-\u001a\u00020\u00152\u0006\u0010.\u001a\u00020\u00152\u0006\u00103\u001a\u00020\t2\u0006\u00104\u001a\u00020\tH\u0002J\u0010\u00105\u001a\u00020\u00172\u0006\u00106\u001a\u00020\u0017H\u0002J\u0018\u00107\u001a\u00020 2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tH\u0002J\u0010\u00108\u001a\u00020 2\u0006\u0010*\u001a\u00020+H\u0014J\u000e\u00109\u001a\u00020 2\u0006\u0010'\u001a\u00020\u0013R\u000e\u0010\u000b\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R!\u0010\f\u001a\u0012\u0012\u0004\u0012\u00020\u000e0\rj\b\u0012\u0004\u0012\u00020\u000e`\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001e\u0010\u0012\u001a\u0012\u0012\u0004\u0012\u00020\u00130\rj\b\u0012\u0004\u0012\u00020\u0013`\u000fX\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0014\u001a\u0012\u0012\u0004\u0012\u00020\u00150\rj\b\u0012\u0004\u0012\u00020\u0015`\u000fX\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0016\u001a\u0012\u0012\u0004\u0012\u00020\u00170\rj\b\u0012\u0004\u0012\u00020\u0017`\u000fX\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0018\u001a\u0012\u0012\u0004\u0012\u00020\u00150\rj\b\u0012\u0004\u0012\u00020\u0015`\u000fX\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0019\u001a\u00020\u001a8BX\u0002¢\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001b\u0010\u001c¨\u0006:"}, d2 = {"Lorg/videolan/liveplotgraph/PlotView;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyle", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "color", "data", "Ljava/util/ArrayList;", "Lorg/videolan/liveplotgraph/LineGraph;", "Lkotlin/collections/ArrayList;", "getData", "()Ljava/util/ArrayList;", "listeners", "Lorg/videolan/liveplotgraph/PlotViewDataChangeListener;", "maxsX", "", "maxsY", "", "minsX", "textPaint", "Landroid/graphics/Paint;", "getTextPaint", "()Landroid/graphics/Paint;", "textPaint$delegate", "Lkotlin/Lazy;", "addData", "", "index", "value", "Lkotlin/Pair;", "addLine", "lineGraph", "addListener", "listener", "clear", "drawGrid", "canvas", "Landroid/graphics/Canvas;", "maxY", "minX", "maxX", "drawLines", "getCoordinates", "point", "", "measuredWidth", "measuredHeight", "getRoundedByUnit", "number", "initAttributes", "onDraw", "removeListener", "live-plot-graph_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PlotView.kt */
public final class PlotView extends FrameLayout {
    /* access modifiers changed from: private */
    public int color = 16777215;
    private final ArrayList<LineGraph> data = new ArrayList<>();
    private ArrayList<PlotViewDataChangeListener> listeners = new ArrayList<>();
    private final ArrayList<Long> maxsX = new ArrayList<>();
    private final ArrayList<Float> maxsY = new ArrayList<>();
    private final ArrayList<Long> minsX = new ArrayList<>();
    private final Lazy textPaint$delegate = LazyKt.lazy(new PlotView$textPaint$2(this));

    private final Paint getTextPaint() {
        return (Paint) this.textPaint$delegate.getValue();
    }

    public final ArrayList<LineGraph> getData() {
        return this.data;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PlotView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        setWillNotDraw(false);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PlotView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributeSet, "attrs");
        initAttributes(attributeSet, 0);
        setWillNotDraw(false);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PlotView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributeSet, "attrs");
        initAttributes(attributeSet, i);
        setWillNotDraw(false);
    }

    private final void initAttributes(AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(attributeSet, R.styleable.LPGPlotView, 0, i);
        Intrinsics.checkNotNullExpressionValue(obtainStyledAttributes, "obtainStyledAttributes(...)");
        try {
            this.color = obtainStyledAttributes.getInt(R.styleable.LPGPlotView_lpg_color, 16777215);
            Unit unit = Unit.INSTANCE;
        } catch (Exception e) {
            Integer.valueOf(Log.w("", e.getMessage(), e));
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
        obtainStyledAttributes.recycle();
    }

    public final void addData(int i, Pair<Long, Float> pair) {
        Intrinsics.checkNotNullParameter(pair, "value");
        for (LineGraph lineGraph : this.data) {
            if (lineGraph.getIndex() == i) {
                lineGraph.getData().put(pair.getFirst(), pair.getSecond());
                if (lineGraph.getData().size() > 30) {
                    lineGraph.getData().remove(MapsKt.toSortedMap(lineGraph.getData()).firstKey());
                }
                invalidate();
                ArrayList arrayList = new ArrayList(this.data.size());
                for (LineGraph lineGraph2 : this.data) {
                    StringBuilder sb = new StringBuilder();
                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                    Set<Long> keySet = lineGraph2.getData().keySet();
                    Intrinsics.checkNotNullExpressionValue(keySet, "<get-keys>(...)");
                    String format = String.format("%.0f", Arrays.copyOf(new Object[]{lineGraph2.getData().get(CollectionsKt.maxOrNull(keySet))}, 1));
                    Intrinsics.checkNotNullExpressionValue(format, "format(...)");
                    sb.append(format);
                    sb.append(" kb/s");
                    arrayList.add(new Pair(lineGraph2, sb.toString()));
                }
                for (PlotViewDataChangeListener onDataChanged : this.listeners) {
                    onDataChanged.onDataChanged(arrayList);
                }
            }
        }
    }

    public final void addListener(PlotViewDataChangeListener plotViewDataChangeListener) {
        Intrinsics.checkNotNullParameter(plotViewDataChangeListener, "listener");
        this.listeners.add(plotViewDataChangeListener);
    }

    public final void removeListener(PlotViewDataChangeListener plotViewDataChangeListener) {
        Intrinsics.checkNotNullParameter(plotViewDataChangeListener, "listener");
        this.listeners.remove(plotViewDataChangeListener);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        Object obj;
        Object obj2;
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        super.onDraw(canvas);
        this.maxsY.clear();
        this.maxsX.clear();
        this.minsX.clear();
        Iterator it = this.data.iterator();
        while (true) {
            float f = 0.0f;
            Object obj3 = null;
            if (!it.hasNext()) {
                break;
            }
            ArrayList<Float> arrayList = this.maxsY;
            Iterator it2 = ((LineGraph) it.next()).getData().entrySet().iterator();
            if (it2.hasNext()) {
                obj3 = it2.next();
                if (it2.hasNext()) {
                    float floatValue = ((Number) ((Map.Entry) obj3).getValue()).floatValue();
                    do {
                        Object next = it2.next();
                        float floatValue2 = ((Number) ((Map.Entry) next).getValue()).floatValue();
                        if (Float.compare(floatValue, floatValue2) < 0) {
                            obj3 = next;
                            floatValue = floatValue2;
                        }
                    } while (it2.hasNext());
                }
            }
            Map.Entry entry = (Map.Entry) obj3;
            if (entry != null) {
                f = ((Number) entry.getValue()).floatValue();
            }
            arrayList.add(Float.valueOf(f));
        }
        Float maxOrNull = CollectionsKt.maxOrNull(this.maxsY);
        float floatValue3 = maxOrNull != null ? maxOrNull.floatValue() : 0.0f;
        Iterator it3 = this.data.iterator();
        while (true) {
            long j = 0;
            if (!it3.hasNext()) {
                break;
            }
            ArrayList<Long> arrayList2 = this.maxsX;
            Iterator it4 = ((LineGraph) it3.next()).getData().entrySet().iterator();
            if (!it4.hasNext()) {
                obj2 = null;
            } else {
                obj2 = it4.next();
                if (it4.hasNext()) {
                    long longValue = ((Number) ((Map.Entry) obj2).getKey()).longValue();
                    do {
                        Object next2 = it4.next();
                        long longValue2 = ((Number) ((Map.Entry) next2).getKey()).longValue();
                        if (longValue < longValue2) {
                            obj2 = next2;
                            longValue = longValue2;
                        }
                    } while (it4.hasNext());
                }
            }
            Map.Entry entry2 = (Map.Entry) obj2;
            if (entry2 != null) {
                j = ((Number) entry2.getKey()).longValue();
            }
            arrayList2.add(Long.valueOf(j));
        }
        Long l = (Long) CollectionsKt.maxOrNull(this.maxsX);
        long longValue3 = l != null ? l.longValue() : 0;
        for (LineGraph data2 : this.data) {
            ArrayList<Long> arrayList3 = this.minsX;
            Iterator it5 = data2.getData().entrySet().iterator();
            if (!it5.hasNext()) {
                obj = null;
            } else {
                obj = it5.next();
                if (it5.hasNext()) {
                    long longValue4 = ((Number) ((Map.Entry) obj).getKey()).longValue();
                    do {
                        Object next3 = it5.next();
                        long longValue5 = ((Number) ((Map.Entry) next3).getKey()).longValue();
                        if (longValue4 > longValue5) {
                            obj = next3;
                            longValue4 = longValue5;
                        }
                    } while (it5.hasNext());
                }
            }
            Map.Entry entry3 = (Map.Entry) obj;
            arrayList3.add(Long.valueOf(entry3 != null ? ((Number) entry3.getKey()).longValue() : 0));
        }
        Long l2 = (Long) CollectionsKt.minOrNull(this.minsX);
        long longValue6 = l2 != null ? l2.longValue() : 0;
        drawLines(floatValue3, longValue6, longValue3, canvas);
        drawGrid(canvas, floatValue3, longValue6, longValue3);
    }

    private final void drawGrid(Canvas canvas, float f, long j, long j2) {
        Canvas canvas2 = canvas;
        if (canvas2 != null && f > 0.0f) {
            StringBuilder sb = new StringBuilder();
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String format = String.format("%.0f", Arrays.copyOf(new Object[]{Float.valueOf(f)}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(...)");
            sb.append(format);
            sb.append(" kb/s");
            canvas2.drawText(sb.toString(), 10.0f, (float) KotlinExtensionsKt.getDp(10), getTextPaint());
            float f2 = (float) 2;
            float roundedByUnit = getRoundedByUnit(f / f2);
            float measuredHeight = ((float) getMeasuredHeight()) * ((f - roundedByUnit) / f);
            canvas.drawLine(0.0f, measuredHeight, (float) getMeasuredWidth(), measuredHeight, getTextPaint());
            StringBuilder sb2 = new StringBuilder();
            StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
            String format2 = String.format("%.0f", Arrays.copyOf(new Object[]{Float.valueOf(roundedByUnit)}, 1));
            Intrinsics.checkNotNullExpressionValue(format2, "format(...)");
            sb2.append(format2);
            sb2.append(" kb/s");
            canvas2.drawText(sb2.toString(), 10.0f, measuredHeight - ((float) KotlinExtensionsKt.getDp(2)), getTextPaint());
            long j3 = (long) 1000;
            for (long j4 = j2 - j3; j4 > j; j4 -= j3) {
                double measuredWidth = (double) getMeasuredWidth();
                double d = (double) (j4 - j);
                double d2 = (double) (j2 - j);
                Double.isNaN(d);
                Double.isNaN(d2);
                Double.isNaN(measuredWidth);
                float f3 = (float) (measuredWidth * (d / d2));
                canvas.drawLine(f3, 0.0f, f3, ((float) getMeasuredHeight()) - ((float) KotlinExtensionsKt.getDp(12)), getTextPaint());
                StringBuilder sb3 = new StringBuilder();
                StringCompanionObject stringCompanionObject3 = StringCompanionObject.INSTANCE;
                String format3 = String.format("%.0f", Arrays.copyOf(new Object[]{Float.valueOf(getRoundedByUnit((float) (j4 - j2)) / ((float) 1000))}, 1));
                Intrinsics.checkNotNullExpressionValue(format3, "format(...)");
                sb3.append(format3);
                sb3.append(GMTDateParser.SECONDS);
                String sb4 = sb3.toString();
                canvas2.drawText(sb4, f3 - (getTextPaint().measureText(sb4) / f2), (float) getMeasuredHeight(), getTextPaint());
            }
        }
    }

    private final float getRoundedByUnit(float f) {
        double d = (double) f;
        double log10 = (double) ((int) Math.log10(d));
        double pow = Math.pow(10.0d, log10);
        Double.isNaN(d);
        return (float) (Math.rint(d / pow) * Math.pow(10.0d, log10));
    }

    private final void drawLines(float f, long j, long j2, Canvas canvas) {
        for (LineGraph lineGraph : this.data) {
            Iterator it = MapsKt.toSortedMap(lineGraph.getData()).entrySet().iterator();
            Pair<Float, Float> pair = null;
            while (true) {
                Pair<Float, Float> pair2 = pair;
                if (it.hasNext()) {
                    Map.Entry entry = (Map.Entry) it.next();
                    if (pair2 == null) {
                        pair = getCoordinates(entry, f, j, j2, getMeasuredWidth(), getMeasuredHeight());
                    } else {
                        pair = getCoordinates(entry, f, j, j2, getMeasuredWidth(), getMeasuredHeight());
                        if (canvas != null) {
                            canvas.drawLine(pair2.getFirst().floatValue(), pair2.getSecond().floatValue(), pair.getFirst().floatValue(), pair.getSecond().floatValue(), lineGraph.getPaint());
                        }
                    }
                }
            }
        }
    }

    private final Pair<Float, Float> getCoordinates(Map.Entry<Long, Float> entry, float f, long j, long j2, int i, int i2) {
        double d = (double) i;
        double longValue = (double) (entry.getKey().longValue() - j);
        double d2 = (double) (j2 - j);
        Double.isNaN(longValue);
        Double.isNaN(d2);
        Double.isNaN(d);
        return new Pair<>(Float.valueOf((float) (d * (longValue / d2))), Float.valueOf(((float) i2) * ((f - entry.getValue().floatValue()) / f)));
    }

    public final void clear() {
        for (LineGraph data2 : this.data) {
            data2.getData().clear();
        }
    }

    public final void addLine(LineGraph lineGraph) {
        Intrinsics.checkNotNullParameter(lineGraph, "lineGraph");
        if (!this.data.contains(lineGraph)) {
            this.data.add(lineGraph);
        }
    }
}
