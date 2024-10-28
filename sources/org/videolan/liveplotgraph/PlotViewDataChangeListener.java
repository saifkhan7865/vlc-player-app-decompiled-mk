package org.videolan.liveplotgraph;

import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\"\u0010\u0002\u001a\u00020\u00032\u0018\u0010\u0004\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00060\u0005H&¨\u0006\t"}, d2 = {"Lorg/videolan/liveplotgraph/PlotViewDataChangeListener;", "", "onDataChanged", "", "data", "", "Lkotlin/Pair;", "Lorg/videolan/liveplotgraph/LineGraph;", "", "live-plot-graph_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PlotView.kt */
public interface PlotViewDataChangeListener {
    void onDataChanged(List<Pair<LineGraph, String>> list);
}
