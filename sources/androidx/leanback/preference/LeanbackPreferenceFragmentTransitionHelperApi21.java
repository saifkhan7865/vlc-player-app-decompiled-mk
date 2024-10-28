package androidx.leanback.preference;

import android.app.Fragment;
import androidx.core.view.GravityCompat;
import androidx.leanback.transition.FadeAndShortSlide;

public class LeanbackPreferenceFragmentTransitionHelperApi21 {
    public static void addTransitions(Fragment fragment) {
        FadeAndShortSlide fadeAndShortSlide = new FadeAndShortSlide(GravityCompat.START);
        FadeAndShortSlide fadeAndShortSlide2 = new FadeAndShortSlide(GravityCompat.END);
        fragment.setEnterTransition(fadeAndShortSlide2);
        fragment.setExitTransition(fadeAndShortSlide);
        fragment.setReenterTransition(fadeAndShortSlide);
        fragment.setReturnTransition(fadeAndShortSlide2);
    }

    private LeanbackPreferenceFragmentTransitionHelperApi21() {
    }
}
