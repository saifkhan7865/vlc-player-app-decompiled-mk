package androidx.leanback.app;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.view.GravityCompat;
import androidx.leanback.R;
import androidx.leanback.widget.PagingIndicator;
import java.util.ArrayList;

@Deprecated
public abstract class OnboardingFragment extends Fragment {
    private static final boolean DEBUG = false;
    private static final long DESCRIPTION_START_DELAY_MS = 33;
    private static final long HEADER_ANIMATION_DURATION_MS = 417;
    private static final long HEADER_APPEAR_DELAY_MS = 500;
    private static final TimeInterpolator HEADER_APPEAR_INTERPOLATOR = new DecelerateInterpolator();
    private static final TimeInterpolator HEADER_DISAPPEAR_INTERPOLATOR = new AccelerateInterpolator();
    private static final String KEY_CURRENT_PAGE_INDEX = "leanback.onboarding.current_page_index";
    private static final String KEY_ENTER_ANIMATION_FINISHED = "leanback.onboarding.enter_animation_finished";
    private static final String KEY_LOGO_ANIMATION_FINISHED = "leanback.onboarding.logo_animation_finished";
    private static final long LOGO_SPLASH_PAUSE_DURATION_MS = 1333;
    private static final int SLIDE_DISTANCE = 60;
    private static final String TAG = "OnboardingF";
    private static int sSlideDistance;
    private AnimatorSet mAnimator;
    private int mArrowBackgroundColor = 0;
    private boolean mArrowBackgroundColorSet;
    private int mArrowColor = 0;
    private boolean mArrowColorSet;
    int mCurrentPageIndex;
    TextView mDescriptionView;
    private int mDescriptionViewTextColor = 0;
    private boolean mDescriptionViewTextColorSet;
    private int mDotBackgroundColor = 0;
    private boolean mDotBackgroundColorSet;
    boolean mEnterAnimationFinished;
    private int mIconResourceId;
    boolean mIsLtr;
    boolean mLogoAnimationFinished;
    private int mLogoResourceId;
    private ImageView mLogoView;
    private ImageView mMainIconView;
    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        public void onClick(View view) {
            if (OnboardingFragment.this.mLogoAnimationFinished) {
                if (OnboardingFragment.this.mCurrentPageIndex == OnboardingFragment.this.getPageCount() - 1) {
                    OnboardingFragment.this.onFinishFragment();
                } else {
                    OnboardingFragment.this.moveToNextPage();
                }
            }
        }
    };
    private final View.OnKeyListener mOnKeyListener = new View.OnKeyListener() {
        public boolean onKey(View view, int i, KeyEvent keyEvent) {
            if (!OnboardingFragment.this.mLogoAnimationFinished) {
                return i != 4;
            }
            if (keyEvent.getAction() == 0) {
                return false;
            }
            if (i != 4) {
                if (i == 21) {
                    if (OnboardingFragment.this.mIsLtr) {
                        OnboardingFragment.this.moveToPreviousPage();
                    } else {
                        OnboardingFragment.this.moveToNextPage();
                    }
                    return true;
                } else if (i != 22) {
                    return false;
                } else {
                    if (OnboardingFragment.this.mIsLtr) {
                        OnboardingFragment.this.moveToNextPage();
                    } else {
                        OnboardingFragment.this.moveToPreviousPage();
                    }
                    return true;
                }
            } else if (OnboardingFragment.this.mCurrentPageIndex == 0) {
                return false;
            } else {
                OnboardingFragment.this.moveToPreviousPage();
                return true;
            }
        }
    };
    PagingIndicator mPageIndicator;
    View mStartButton;
    private CharSequence mStartButtonText;
    private boolean mStartButtonTextSet;
    private ContextThemeWrapper mThemeWrapper;
    TextView mTitleView;
    private int mTitleViewTextColor = 0;
    private boolean mTitleViewTextColorSet;

    /* access modifiers changed from: protected */
    public abstract int getPageCount();

    /* access modifiers changed from: protected */
    public abstract CharSequence getPageDescription(int i);

    /* access modifiers changed from: protected */
    public abstract CharSequence getPageTitle(int i);

    /* access modifiers changed from: protected */
    public abstract View onCreateBackgroundView(LayoutInflater layoutInflater, ViewGroup viewGroup);

    /* access modifiers changed from: protected */
    public abstract View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup);

    /* access modifiers changed from: protected */
    public Animator onCreateEnterAnimation() {
        return null;
    }

    /* access modifiers changed from: protected */
    public abstract View onCreateForegroundView(LayoutInflater layoutInflater, ViewGroup viewGroup);

    /* access modifiers changed from: protected */
    public Animator onCreateLogoAnimation() {
        return null;
    }

    /* access modifiers changed from: protected */
    public void onFinishFragment() {
    }

    /* access modifiers changed from: protected */
    public void onPageChanged(int i, int i2) {
    }

    public int onProvideTheme() {
        return -1;
    }

    /* access modifiers changed from: protected */
    public void moveToPreviousPage() {
        int i;
        if (this.mLogoAnimationFinished && (i = this.mCurrentPageIndex) > 0) {
            this.mCurrentPageIndex = i - 1;
            onPageChangedInternal(i);
        }
    }

    /* access modifiers changed from: protected */
    public void moveToNextPage() {
        if (this.mLogoAnimationFinished && this.mCurrentPageIndex < getPageCount() - 1) {
            int i = this.mCurrentPageIndex;
            this.mCurrentPageIndex = i + 1;
            onPageChangedInternal(i);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        resolveTheme();
        boolean z = false;
        ViewGroup viewGroup2 = (ViewGroup) getThemeInflater(layoutInflater).inflate(R.layout.lb_onboarding_fragment, viewGroup, false);
        if (getResources().getConfiguration().getLayoutDirection() == 0) {
            z = true;
        }
        this.mIsLtr = z;
        PagingIndicator pagingIndicator = (PagingIndicator) viewGroup2.findViewById(R.id.page_indicator);
        this.mPageIndicator = pagingIndicator;
        pagingIndicator.setOnClickListener(this.mOnClickListener);
        this.mPageIndicator.setOnKeyListener(this.mOnKeyListener);
        View findViewById = viewGroup2.findViewById(R.id.button_start);
        this.mStartButton = findViewById;
        findViewById.setOnClickListener(this.mOnClickListener);
        this.mStartButton.setOnKeyListener(this.mOnKeyListener);
        this.mMainIconView = (ImageView) viewGroup2.findViewById(R.id.main_icon);
        this.mLogoView = (ImageView) viewGroup2.findViewById(R.id.logo);
        this.mTitleView = (TextView) viewGroup2.findViewById(R.id.title);
        this.mDescriptionView = (TextView) viewGroup2.findViewById(R.id.description);
        if (this.mTitleViewTextColorSet) {
            this.mTitleView.setTextColor(this.mTitleViewTextColor);
        }
        if (this.mDescriptionViewTextColorSet) {
            this.mDescriptionView.setTextColor(this.mDescriptionViewTextColor);
        }
        if (this.mDotBackgroundColorSet) {
            this.mPageIndicator.setDotBackgroundColor(this.mDotBackgroundColor);
        }
        if (this.mArrowColorSet) {
            this.mPageIndicator.setArrowColor(this.mArrowColor);
        }
        if (this.mArrowBackgroundColorSet) {
            this.mPageIndicator.setDotBackgroundColor(this.mArrowBackgroundColor);
        }
        if (this.mStartButtonTextSet) {
            ((Button) this.mStartButton).setText(this.mStartButtonText);
        }
        Context context = FragmentUtil.getContext(this);
        if (sSlideDistance == 0) {
            sSlideDistance = (int) (context.getResources().getDisplayMetrics().scaledDensity * 60.0f);
        }
        viewGroup2.requestFocus();
        return viewGroup2;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (bundle == null) {
            this.mCurrentPageIndex = 0;
            this.mLogoAnimationFinished = false;
            this.mEnterAnimationFinished = false;
            this.mPageIndicator.onPageSelected(0, false);
            view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                public boolean onPreDraw() {
                    OnboardingFragment.this.getView().getViewTreeObserver().removeOnPreDrawListener(this);
                    if (!OnboardingFragment.this.startLogoAnimation()) {
                        OnboardingFragment.this.mLogoAnimationFinished = true;
                        OnboardingFragment.this.onLogoAnimationFinished();
                    }
                    return true;
                }
            });
            return;
        }
        this.mCurrentPageIndex = bundle.getInt(KEY_CURRENT_PAGE_INDEX);
        this.mLogoAnimationFinished = bundle.getBoolean(KEY_LOGO_ANIMATION_FINISHED);
        this.mEnterAnimationFinished = bundle.getBoolean(KEY_ENTER_ANIMATION_FINISHED);
        if (this.mLogoAnimationFinished) {
            onLogoAnimationFinished();
        } else if (!startLogoAnimation()) {
            this.mLogoAnimationFinished = true;
            onLogoAnimationFinished();
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt(KEY_CURRENT_PAGE_INDEX, this.mCurrentPageIndex);
        bundle.putBoolean(KEY_LOGO_ANIMATION_FINISHED, this.mLogoAnimationFinished);
        bundle.putBoolean(KEY_ENTER_ANIMATION_FINISHED, this.mEnterAnimationFinished);
    }

    public void setTitleViewTextColor(int i) {
        this.mTitleViewTextColor = i;
        this.mTitleViewTextColorSet = true;
        TextView textView = this.mTitleView;
        if (textView != null) {
            textView.setTextColor(i);
        }
    }

    public final int getTitleViewTextColor() {
        return this.mTitleViewTextColor;
    }

    public void setDescriptionViewTextColor(int i) {
        this.mDescriptionViewTextColor = i;
        this.mDescriptionViewTextColorSet = true;
        TextView textView = this.mDescriptionView;
        if (textView != null) {
            textView.setTextColor(i);
        }
    }

    public final int getDescriptionViewTextColor() {
        return this.mDescriptionViewTextColor;
    }

    public void setDotBackgroundColor(int i) {
        this.mDotBackgroundColor = i;
        this.mDotBackgroundColorSet = true;
        PagingIndicator pagingIndicator = this.mPageIndicator;
        if (pagingIndicator != null) {
            pagingIndicator.setDotBackgroundColor(i);
        }
    }

    public final int getDotBackgroundColor() {
        return this.mDotBackgroundColor;
    }

    public void setArrowColor(int i) {
        this.mArrowColor = i;
        this.mArrowColorSet = true;
        PagingIndicator pagingIndicator = this.mPageIndicator;
        if (pagingIndicator != null) {
            pagingIndicator.setArrowColor(i);
        }
    }

    public final int getArrowColor() {
        return this.mArrowColor;
    }

    public void setArrowBackgroundColor(int i) {
        this.mArrowBackgroundColor = i;
        this.mArrowBackgroundColorSet = true;
        PagingIndicator pagingIndicator = this.mPageIndicator;
        if (pagingIndicator != null) {
            pagingIndicator.setArrowBackgroundColor(i);
        }
    }

    public final int getArrowBackgroundColor() {
        return this.mArrowBackgroundColor;
    }

    public final CharSequence getStartButtonText() {
        return this.mStartButtonText;
    }

    public void setStartButtonText(CharSequence charSequence) {
        this.mStartButtonText = charSequence;
        this.mStartButtonTextSet = true;
        View view = this.mStartButton;
        if (view != null) {
            ((Button) view).setText(charSequence);
        }
    }

    private void resolveTheme() {
        Context context = FragmentUtil.getContext(this);
        int onProvideTheme = onProvideTheme();
        if (onProvideTheme == -1) {
            int i = R.attr.onboardingTheme;
            TypedValue typedValue = new TypedValue();
            if (context.getTheme().resolveAttribute(i, typedValue, true)) {
                this.mThemeWrapper = new ContextThemeWrapper(context, typedValue.resourceId);
                return;
            }
            return;
        }
        this.mThemeWrapper = new ContextThemeWrapper(context, onProvideTheme);
    }

    private LayoutInflater getThemeInflater(LayoutInflater layoutInflater) {
        ContextThemeWrapper contextThemeWrapper = this.mThemeWrapper;
        return contextThemeWrapper == null ? layoutInflater : layoutInflater.cloneInContext(contextThemeWrapper);
    }

    public final void setLogoResourceId(int i) {
        this.mLogoResourceId = i;
    }

    public final int getLogoResourceId() {
        return this.mLogoResourceId;
    }

    /* access modifiers changed from: package-private */
    public boolean startLogoAnimation() {
        AnimatorSet animatorSet;
        final Context context = FragmentUtil.getContext(this);
        if (context == null) {
            return false;
        }
        if (this.mLogoResourceId != 0) {
            this.mLogoView.setVisibility(0);
            this.mLogoView.setImageResource(this.mLogoResourceId);
            Animator loadAnimator = AnimatorInflater.loadAnimator(context, R.animator.lb_onboarding_logo_enter);
            Animator loadAnimator2 = AnimatorInflater.loadAnimator(context, R.animator.lb_onboarding_logo_exit);
            loadAnimator2.setStartDelay(LOGO_SPLASH_PAUSE_DURATION_MS);
            AnimatorSet animatorSet2 = new AnimatorSet();
            animatorSet2.playSequentially(new Animator[]{loadAnimator, loadAnimator2});
            animatorSet2.setTarget(this.mLogoView);
            animatorSet = animatorSet2;
        } else {
            animatorSet = onCreateLogoAnimation();
        }
        if (animatorSet == null) {
            return false;
        }
        animatorSet.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                if (context != null) {
                    OnboardingFragment.this.mLogoAnimationFinished = true;
                    OnboardingFragment.this.onLogoAnimationFinished();
                }
            }
        });
        animatorSet.start();
        return true;
    }

    /* access modifiers changed from: package-private */
    public void hideLogoView() {
        this.mLogoView.setVisibility(8);
        int i = this.mIconResourceId;
        if (i != 0) {
            this.mMainIconView.setImageResource(i);
            this.mMainIconView.setVisibility(0);
        }
        View view = getView();
        LayoutInflater themeInflater = getThemeInflater(LayoutInflater.from(FragmentUtil.getContext(this)));
        ViewGroup viewGroup = (ViewGroup) view.findViewById(R.id.background_container);
        View onCreateBackgroundView = onCreateBackgroundView(themeInflater, viewGroup);
        if (onCreateBackgroundView != null) {
            viewGroup.setVisibility(0);
            viewGroup.addView(onCreateBackgroundView);
        }
        ViewGroup viewGroup2 = (ViewGroup) view.findViewById(R.id.content_container);
        View onCreateContentView = onCreateContentView(themeInflater, viewGroup2);
        if (onCreateContentView != null) {
            viewGroup2.setVisibility(0);
            viewGroup2.addView(onCreateContentView);
        }
        ViewGroup viewGroup3 = (ViewGroup) view.findViewById(R.id.foreground_container);
        View onCreateForegroundView = onCreateForegroundView(themeInflater, viewGroup3);
        if (onCreateForegroundView != null) {
            viewGroup3.setVisibility(0);
            viewGroup3.addView(onCreateForegroundView);
        }
        view.findViewById(R.id.page_container).setVisibility(0);
        view.findViewById(R.id.content_container).setVisibility(0);
        if (getPageCount() > 1) {
            this.mPageIndicator.setPageCount(getPageCount());
            this.mPageIndicator.onPageSelected(this.mCurrentPageIndex, false);
        }
        if (this.mCurrentPageIndex == getPageCount() - 1) {
            this.mStartButton.setVisibility(0);
        } else {
            this.mPageIndicator.setVisibility(0);
        }
        this.mTitleView.setText(getPageTitle(this.mCurrentPageIndex));
        this.mDescriptionView.setText(getPageDescription(this.mCurrentPageIndex));
    }

    /* access modifiers changed from: protected */
    public void onLogoAnimationFinished() {
        startEnterAnimation(false);
    }

    /* access modifiers changed from: protected */
    public final void startEnterAnimation(boolean z) {
        Context context = FragmentUtil.getContext(this);
        if (context != null) {
            hideLogoView();
            if (!this.mEnterAnimationFinished || z) {
                ArrayList arrayList = new ArrayList();
                Animator loadAnimator = AnimatorInflater.loadAnimator(context, R.animator.lb_onboarding_page_indicator_enter);
                loadAnimator.setTarget(getPageCount() <= 1 ? this.mStartButton : this.mPageIndicator);
                arrayList.add(loadAnimator);
                Animator onCreateTitleAnimator = onCreateTitleAnimator();
                if (onCreateTitleAnimator != null) {
                    onCreateTitleAnimator.setTarget(this.mTitleView);
                    arrayList.add(onCreateTitleAnimator);
                }
                Animator onCreateDescriptionAnimator = onCreateDescriptionAnimator();
                if (onCreateDescriptionAnimator != null) {
                    onCreateDescriptionAnimator.setTarget(this.mDescriptionView);
                    arrayList.add(onCreateDescriptionAnimator);
                }
                Animator onCreateEnterAnimation = onCreateEnterAnimation();
                if (onCreateEnterAnimation != null) {
                    arrayList.add(onCreateEnterAnimation);
                }
                if (!arrayList.isEmpty()) {
                    AnimatorSet animatorSet = new AnimatorSet();
                    this.mAnimator = animatorSet;
                    animatorSet.playTogether(arrayList);
                    this.mAnimator.start();
                    this.mAnimator.addListener(new AnimatorListenerAdapter() {
                        public void onAnimationEnd(Animator animator) {
                            OnboardingFragment.this.mEnterAnimationFinished = true;
                        }
                    });
                    getView().requestFocus();
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public Animator onCreateDescriptionAnimator() {
        return AnimatorInflater.loadAnimator(FragmentUtil.getContext(this), R.animator.lb_onboarding_description_enter);
    }

    /* access modifiers changed from: protected */
    public Animator onCreateTitleAnimator() {
        return AnimatorInflater.loadAnimator(FragmentUtil.getContext(this), R.animator.lb_onboarding_title_enter);
    }

    /* access modifiers changed from: protected */
    public final boolean isLogoAnimationFinished() {
        return this.mLogoAnimationFinished;
    }

    /* access modifiers changed from: protected */
    public final int getCurrentPageIndex() {
        return this.mCurrentPageIndex;
    }

    private void onPageChangedInternal(int i) {
        Animator animator;
        AnimatorSet animatorSet = this.mAnimator;
        if (animatorSet != null) {
            animatorSet.end();
        }
        this.mPageIndicator.onPageSelected(this.mCurrentPageIndex, true);
        ArrayList arrayList = new ArrayList();
        if (i < getCurrentPageIndex()) {
            arrayList.add(createAnimator(this.mTitleView, false, GravityCompat.START, 0));
            animator = createAnimator(this.mDescriptionView, false, GravityCompat.START, 33);
            arrayList.add(animator);
            arrayList.add(createAnimator(this.mTitleView, true, GravityCompat.END, HEADER_APPEAR_DELAY_MS));
            arrayList.add(createAnimator(this.mDescriptionView, true, GravityCompat.END, 533));
        } else {
            arrayList.add(createAnimator(this.mTitleView, false, GravityCompat.END, 0));
            animator = createAnimator(this.mDescriptionView, false, GravityCompat.END, 33);
            arrayList.add(animator);
            arrayList.add(createAnimator(this.mTitleView, true, GravityCompat.START, HEADER_APPEAR_DELAY_MS));
            arrayList.add(createAnimator(this.mDescriptionView, true, GravityCompat.START, 533));
        }
        final int currentPageIndex = getCurrentPageIndex();
        animator.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                OnboardingFragment.this.mTitleView.setText(OnboardingFragment.this.getPageTitle(currentPageIndex));
                OnboardingFragment.this.mDescriptionView.setText(OnboardingFragment.this.getPageDescription(currentPageIndex));
            }
        });
        Context context = FragmentUtil.getContext(this);
        if (getCurrentPageIndex() == getPageCount() - 1) {
            this.mStartButton.setVisibility(0);
            Animator loadAnimator = AnimatorInflater.loadAnimator(context, R.animator.lb_onboarding_page_indicator_fade_out);
            loadAnimator.setTarget(this.mPageIndicator);
            loadAnimator.addListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animator) {
                    OnboardingFragment.this.mPageIndicator.setVisibility(8);
                }
            });
            arrayList.add(loadAnimator);
            Animator loadAnimator2 = AnimatorInflater.loadAnimator(context, R.animator.lb_onboarding_start_button_fade_in);
            loadAnimator2.setTarget(this.mStartButton);
            arrayList.add(loadAnimator2);
        } else if (i == getPageCount() - 1) {
            this.mPageIndicator.setVisibility(0);
            Animator loadAnimator3 = AnimatorInflater.loadAnimator(context, R.animator.lb_onboarding_page_indicator_fade_in);
            loadAnimator3.setTarget(this.mPageIndicator);
            arrayList.add(loadAnimator3);
            Animator loadAnimator4 = AnimatorInflater.loadAnimator(context, R.animator.lb_onboarding_start_button_fade_out);
            loadAnimator4.setTarget(this.mStartButton);
            loadAnimator4.addListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animator) {
                    OnboardingFragment.this.mStartButton.setVisibility(8);
                }
            });
            arrayList.add(loadAnimator4);
        }
        AnimatorSet animatorSet2 = new AnimatorSet();
        this.mAnimator = animatorSet2;
        animatorSet2.playTogether(arrayList);
        this.mAnimator.start();
        onPageChanged(this.mCurrentPageIndex, i);
    }

    private Animator createAnimator(View view, boolean z, int i, long j) {
        ObjectAnimator objectAnimator;
        ObjectAnimator objectAnimator2;
        boolean z2 = getView().getLayoutDirection() == 0;
        boolean z3 = (z2 && i == 8388613) || (!z2 && i == 8388611) || i == 5;
        if (z) {
            objectAnimator2 = ObjectAnimator.ofFloat(view, View.ALPHA, new float[]{0.0f, 1.0f});
            objectAnimator = ObjectAnimator.ofFloat(view, View.TRANSLATION_X, new float[]{(float) (z3 ? sSlideDistance : -sSlideDistance), 0.0f});
            TimeInterpolator timeInterpolator = HEADER_APPEAR_INTERPOLATOR;
            objectAnimator2.setInterpolator(timeInterpolator);
            objectAnimator.setInterpolator(timeInterpolator);
        } else {
            objectAnimator2 = ObjectAnimator.ofFloat(view, View.ALPHA, new float[]{1.0f, 0.0f});
            objectAnimator = ObjectAnimator.ofFloat(view, View.TRANSLATION_X, new float[]{0.0f, (float) (z3 ? sSlideDistance : -sSlideDistance)});
            TimeInterpolator timeInterpolator2 = HEADER_DISAPPEAR_INTERPOLATOR;
            objectAnimator2.setInterpolator(timeInterpolator2);
            objectAnimator.setInterpolator(timeInterpolator2);
        }
        objectAnimator2.setDuration(HEADER_ANIMATION_DURATION_MS);
        objectAnimator2.setTarget(view);
        objectAnimator.setDuration(HEADER_ANIMATION_DURATION_MS);
        objectAnimator.setTarget(view);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{objectAnimator2, objectAnimator});
        if (j > 0) {
            animatorSet.setStartDelay(j);
        }
        return animatorSet;
    }

    public final void setIconResouceId(int i) {
        this.mIconResourceId = i;
        ImageView imageView = this.mMainIconView;
        if (imageView != null) {
            imageView.setImageResource(i);
            this.mMainIconView.setVisibility(0);
        }
    }

    public final int getIconResourceId() {
        return this.mIconResourceId;
    }
}
