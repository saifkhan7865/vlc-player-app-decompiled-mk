package androidx.leanback.widget;

import androidx.leanback.R;
import androidx.leanback.widget.Parallax;
import androidx.leanback.widget.RecyclerViewParallax;

public class DetailsParallax extends RecyclerViewParallax {
    final Parallax.IntProperty mFrameBottom = ((RecyclerViewParallax.ChildPositionProperty) addProperty("overviewRowBottom")).adapterPosition(0).viewId(R.id.details_frame).fraction(1.0f);
    final Parallax.IntProperty mFrameTop = ((RecyclerViewParallax.ChildPositionProperty) addProperty("overviewRowTop")).adapterPosition(0).viewId(R.id.details_frame);

    public Parallax.IntProperty getOverviewRowTop() {
        return this.mFrameTop;
    }

    public Parallax.IntProperty getOverviewRowBottom() {
        return this.mFrameBottom;
    }
}
