package org.videolan.vlc.gui.video;

import android.view.ViewParent;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.BindingAdapter;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.medialibrary.Tools;

@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0007\u001a \u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0007\u001a\u0018\u0010\u000b\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\f2\u0006\u0010\b\u001a\u00020\tH\u0007¨\u0006\r"}, d2 = {"setConstraintPercent", "", "view", "Landroidx/constraintlayout/widget/Guideline;", "percent", "", "setPlaybackTime", "Landroid/widget/TextView;", "length", "", "time", "setProgressMax", "Landroid/widget/SeekBar;", "vlc-android_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideoPlayerActivity.kt */
public final class VideoPlayerActivityKt {
    @BindingAdapter({"length", "time"})
    public static final void setPlaybackTime(TextView textView, long j, long j2) {
        CharSequence charSequence;
        Intrinsics.checkNotNullParameter(textView, "view");
        if (!VideoPlayerActivity.Companion.getSDisplayRemainingTime$vlc_android_release() || j <= 0) {
            charSequence = Tools.millisToString(j);
        } else {
            charSequence = "- " + Tools.millisToString(j - j2);
        }
        textView.setText(charSequence);
    }

    @BindingAdapter({"constraintPercent"})
    public static final void setConstraintPercent(Guideline guideline, float f) {
        Intrinsics.checkNotNullParameter(guideline, "view");
        ViewParent parent = guideline.getParent();
        Intrinsics.checkNotNull(parent, "null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout");
        ConstraintLayout constraintLayout = (ConstraintLayout) parent;
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);
        constraintSet.setGuidelinePercent(guideline.getId(), f);
        constraintSet.applyTo(constraintLayout);
    }

    @BindingAdapter({"mediamax"})
    public static final void setProgressMax(SeekBar seekBar, long j) {
        Intrinsics.checkNotNullParameter(seekBar, "view");
        seekBar.setMax(j == 0 ? 1000 : (int) j);
    }
}
