package androidx.leanback.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Transformation;
import android.widget.FrameLayout;
import androidx.leanback.R;
import java.util.ArrayList;

public class BaseCardView extends FrameLayout {
    public static final int CARD_REGION_VISIBLE_ACTIVATED = 1;
    public static final int CARD_REGION_VISIBLE_ALWAYS = 0;
    public static final int CARD_REGION_VISIBLE_SELECTED = 2;
    public static final int CARD_TYPE_INFO_OVER = 1;
    public static final int CARD_TYPE_INFO_UNDER = 2;
    public static final int CARD_TYPE_INFO_UNDER_WITH_EXTRA = 3;
    private static final int CARD_TYPE_INVALID = 4;
    public static final int CARD_TYPE_MAIN_ONLY = 0;
    private static final boolean DEBUG = false;
    private static final int[] LB_PRESSED_STATE_SET = {16842919};
    private static final String TAG = "BaseCardView";
    private final int mActivatedAnimDuration;
    private Animation mAnim;
    private final Runnable mAnimationTrigger;
    private int mCardType;
    private boolean mDelaySelectedAnim;
    ArrayList<View> mExtraViewList;
    private int mExtraVisibility;
    float mInfoAlpha;
    float mInfoOffset;
    ArrayList<View> mInfoViewList;
    float mInfoVisFraction;
    private int mInfoVisibility;
    private ArrayList<View> mMainViewList;
    private int mMeasuredHeight;
    private int mMeasuredWidth;
    private final int mSelectedAnimDuration;
    private int mSelectedAnimationDelay;

    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public BaseCardView(Context context) {
        this(context, (AttributeSet) null);
    }

    public BaseCardView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.baseCardViewStyle);
    }

    /* JADX INFO: finally extract failed */
    public BaseCardView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mAnimationTrigger = new Runnable() {
            public void run() {
                BaseCardView.this.animateInfoOffset(true);
            }
        };
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.lbBaseCardView, i, 0);
        try {
            this.mCardType = obtainStyledAttributes.getInteger(R.styleable.lbBaseCardView_cardType, 0);
            Drawable drawable = obtainStyledAttributes.getDrawable(R.styleable.lbBaseCardView_cardForeground);
            if (drawable != null) {
                setForeground(drawable);
            }
            Drawable drawable2 = obtainStyledAttributes.getDrawable(R.styleable.lbBaseCardView_cardBackground);
            if (drawable2 != null) {
                setBackground(drawable2);
            }
            this.mInfoVisibility = obtainStyledAttributes.getInteger(R.styleable.lbBaseCardView_infoVisibility, 1);
            int integer = obtainStyledAttributes.getInteger(R.styleable.lbBaseCardView_extraVisibility, 2);
            this.mExtraVisibility = integer;
            int i2 = this.mInfoVisibility;
            if (integer < i2) {
                this.mExtraVisibility = i2;
            }
            this.mSelectedAnimationDelay = obtainStyledAttributes.getInteger(R.styleable.lbBaseCardView_selectedAnimationDelay, getResources().getInteger(R.integer.lb_card_selected_animation_delay));
            this.mSelectedAnimDuration = obtainStyledAttributes.getInteger(R.styleable.lbBaseCardView_selectedAnimationDuration, getResources().getInteger(R.integer.lb_card_selected_animation_duration));
            this.mActivatedAnimDuration = obtainStyledAttributes.getInteger(R.styleable.lbBaseCardView_activatedAnimationDuration, getResources().getInteger(R.integer.lb_card_activated_animation_duration));
            obtainStyledAttributes.recycle();
            this.mDelaySelectedAnim = true;
            this.mMainViewList = new ArrayList<>();
            this.mInfoViewList = new ArrayList<>();
            this.mExtraViewList = new ArrayList<>();
            this.mInfoOffset = 0.0f;
            this.mInfoVisFraction = getFinalInfoVisFraction();
            this.mInfoAlpha = getFinalInfoAlpha();
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    public void setSelectedAnimationDelayed(boolean z) {
        this.mDelaySelectedAnim = z;
    }

    public boolean isSelectedAnimationDelayed() {
        return this.mDelaySelectedAnim;
    }

    public void setCardType(int i) {
        if (this.mCardType != i) {
            if (i < 0 || i >= 4) {
                Log.e(TAG, "Invalid card type specified: " + i + ". Defaulting to type CARD_TYPE_MAIN_ONLY.");
                this.mCardType = 0;
            } else {
                this.mCardType = i;
            }
            requestLayout();
        }
    }

    public int getCardType() {
        return this.mCardType;
    }

    public void setInfoVisibility(int i) {
        if (this.mInfoVisibility != i) {
            cancelAnimations();
            this.mInfoVisibility = i;
            this.mInfoVisFraction = getFinalInfoVisFraction();
            requestLayout();
            float finalInfoAlpha = getFinalInfoAlpha();
            if (finalInfoAlpha != this.mInfoAlpha) {
                this.mInfoAlpha = finalInfoAlpha;
                for (int i2 = 0; i2 < this.mInfoViewList.size(); i2++) {
                    this.mInfoViewList.get(i2).setAlpha(this.mInfoAlpha);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final float getFinalInfoVisFraction() {
        return (this.mCardType == 2 && this.mInfoVisibility == 2 && !isSelected()) ? 0.0f : 1.0f;
    }

    /* access modifiers changed from: package-private */
    public final float getFinalInfoAlpha() {
        return (this.mCardType == 1 && this.mInfoVisibility == 2 && !isSelected()) ? 0.0f : 1.0f;
    }

    public int getInfoVisibility() {
        return this.mInfoVisibility;
    }

    @Deprecated
    public void setExtraVisibility(int i) {
        if (this.mExtraVisibility != i) {
            this.mExtraVisibility = i;
        }
    }

    @Deprecated
    public int getExtraVisibility() {
        return this.mExtraVisibility;
    }

    public void setActivated(boolean z) {
        if (z != isActivated()) {
            super.setActivated(z);
            applyActiveState(isActivated());
        }
    }

    public void setSelected(boolean z) {
        if (z != isSelected()) {
            super.setSelected(z);
            applySelectedState(isSelected());
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00d4  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00dd  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00df  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onMeasure(int r14, int r15) {
        /*
            r13 = this;
            r0 = 0
            r13.mMeasuredWidth = r0
            r13.mMeasuredHeight = r0
            r13.findChildrenViews()
            int r1 = android.view.View.MeasureSpec.makeMeasureSpec(r0, r0)
            r2 = 0
            r3 = 0
            r4 = 0
        L_0x000f:
            java.util.ArrayList<android.view.View> r5 = r13.mMainViewList
            int r5 = r5.size()
            r6 = 8
            if (r2 >= r5) goto L_0x0046
            java.util.ArrayList<android.view.View> r5 = r13.mMainViewList
            java.lang.Object r5 = r5.get(r2)
            android.view.View r5 = (android.view.View) r5
            int r7 = r5.getVisibility()
            if (r7 == r6) goto L_0x0043
            r13.measureChild(r5, r1, r1)
            int r6 = r13.mMeasuredWidth
            int r7 = r5.getMeasuredWidth()
            int r6 = java.lang.Math.max(r6, r7)
            r13.mMeasuredWidth = r6
            int r6 = r5.getMeasuredHeight()
            int r3 = r3 + r6
            int r5 = r5.getMeasuredState()
            int r4 = android.view.View.combineMeasuredStates(r4, r5)
        L_0x0043:
            int r2 = r2 + 1
            goto L_0x000f
        L_0x0046:
            int r2 = r13.mMeasuredWidth
            r5 = 2
            int r2 = r2 / r5
            float r2 = (float) r2
            r13.setPivotX(r2)
            int r2 = r3 / 2
            float r2 = (float) r2
            r13.setPivotY(r2)
            int r2 = r13.mMeasuredWidth
            r7 = 1073741824(0x40000000, float:2.0)
            int r2 = android.view.View.MeasureSpec.makeMeasureSpec(r2, r7)
            boolean r7 = r13.hasInfoRegion()
            r8 = 1
            if (r7 == 0) goto L_0x00c3
            r7 = 0
            r9 = 0
        L_0x0065:
            java.util.ArrayList<android.view.View> r10 = r13.mInfoViewList
            int r10 = r10.size()
            if (r7 >= r10) goto L_0x0092
            java.util.ArrayList<android.view.View> r10 = r13.mInfoViewList
            java.lang.Object r10 = r10.get(r7)
            android.view.View r10 = (android.view.View) r10
            int r11 = r10.getVisibility()
            if (r11 == r6) goto L_0x008f
            r13.measureChild(r10, r2, r1)
            int r11 = r13.mCardType
            if (r11 == r8) goto L_0x0087
            int r11 = r10.getMeasuredHeight()
            int r9 = r9 + r11
        L_0x0087:
            int r10 = r10.getMeasuredState()
            int r4 = android.view.View.combineMeasuredStates(r4, r10)
        L_0x008f:
            int r7 = r7 + 1
            goto L_0x0065
        L_0x0092:
            boolean r7 = r13.hasExtraRegion()
            if (r7 == 0) goto L_0x00c4
            r7 = 0
            r10 = 0
        L_0x009a:
            java.util.ArrayList<android.view.View> r11 = r13.mExtraViewList
            int r11 = r11.size()
            if (r7 >= r11) goto L_0x00c5
            java.util.ArrayList<android.view.View> r11 = r13.mExtraViewList
            java.lang.Object r11 = r11.get(r7)
            android.view.View r11 = (android.view.View) r11
            int r12 = r11.getVisibility()
            if (r12 == r6) goto L_0x00c0
            r13.measureChild(r11, r2, r1)
            int r12 = r11.getMeasuredHeight()
            int r10 = r10 + r12
            int r11 = r11.getMeasuredState()
            int r4 = android.view.View.combineMeasuredStates(r4, r11)
        L_0x00c0:
            int r7 = r7 + 1
            goto L_0x009a
        L_0x00c3:
            r9 = 0
        L_0x00c4:
            r10 = 0
        L_0x00c5:
            boolean r1 = r13.hasInfoRegion()
            if (r1 == 0) goto L_0x00d0
            int r1 = r13.mInfoVisibility
            if (r1 != r5) goto L_0x00d0
            r0 = 1
        L_0x00d0:
            float r1 = (float) r3
            float r2 = (float) r9
            if (r0 == 0) goto L_0x00d8
            float r3 = r13.mInfoVisFraction
            float r2 = r2 * r3
        L_0x00d8:
            float r1 = r1 + r2
            float r2 = (float) r10
            float r1 = r1 + r2
            if (r0 == 0) goto L_0x00df
            r0 = 0
            goto L_0x00e1
        L_0x00df:
            float r0 = r13.mInfoOffset
        L_0x00e1:
            float r1 = r1 - r0
            int r0 = (int) r1
            r13.mMeasuredHeight = r0
            int r0 = r13.mMeasuredWidth
            int r1 = r13.getPaddingLeft()
            int r0 = r0 + r1
            int r1 = r13.getPaddingRight()
            int r0 = r0 + r1
            int r14 = android.view.View.resolveSizeAndState(r0, r14, r4)
            int r0 = r13.mMeasuredHeight
            int r1 = r13.getPaddingTop()
            int r0 = r0 + r1
            int r1 = r13.getPaddingBottom()
            int r0 = r0 + r1
            int r1 = r4 << 16
            int r15 = android.view.View.resolveSizeAndState(r0, r15, r1)
            r13.setMeasuredDimension(r14, r15)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.leanback.widget.BaseCardView.onMeasure(int, int):void");
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        float paddingTop = (float) getPaddingTop();
        for (int i5 = 0; i5 < this.mMainViewList.size(); i5++) {
            View view = this.mMainViewList.get(i5);
            if (view.getVisibility() != 8) {
                view.layout(getPaddingLeft(), (int) paddingTop, this.mMeasuredWidth + getPaddingLeft(), (int) (((float) view.getMeasuredHeight()) + paddingTop));
                paddingTop += (float) view.getMeasuredHeight();
            }
        }
        if (hasInfoRegion()) {
            float f = 0.0f;
            for (int i6 = 0; i6 < this.mInfoViewList.size(); i6++) {
                f += (float) this.mInfoViewList.get(i6).getMeasuredHeight();
            }
            int i7 = this.mCardType;
            if (i7 == 1) {
                paddingTop -= f;
                if (paddingTop < 0.0f) {
                    paddingTop = 0.0f;
                }
            } else if (i7 != 2) {
                paddingTop -= this.mInfoOffset;
            } else if (this.mInfoVisibility == 2) {
                f *= this.mInfoVisFraction;
            }
            for (int i8 = 0; i8 < this.mInfoViewList.size(); i8++) {
                View view2 = this.mInfoViewList.get(i8);
                if (view2.getVisibility() != 8) {
                    int measuredHeight = view2.getMeasuredHeight();
                    if (((float) measuredHeight) > f) {
                        measuredHeight = (int) f;
                    }
                    float f2 = (float) measuredHeight;
                    paddingTop += f2;
                    view2.layout(getPaddingLeft(), (int) paddingTop, this.mMeasuredWidth + getPaddingLeft(), (int) paddingTop);
                    f -= f2;
                    if (f <= 0.0f) {
                        break;
                    }
                }
            }
            if (hasExtraRegion()) {
                for (int i9 = 0; i9 < this.mExtraViewList.size(); i9++) {
                    View view3 = this.mExtraViewList.get(i9);
                    if (view3.getVisibility() != 8) {
                        view3.layout(getPaddingLeft(), (int) paddingTop, this.mMeasuredWidth + getPaddingLeft(), (int) (((float) view3.getMeasuredHeight()) + paddingTop));
                        paddingTop += (float) view3.getMeasuredHeight();
                    }
                }
            }
        }
        onSizeChanged(0, 0, i3 - i, i4 - i2);
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this.mAnimationTrigger);
        cancelAnimations();
    }

    private boolean hasInfoRegion() {
        return this.mCardType != 0;
    }

    private boolean hasExtraRegion() {
        return this.mCardType == 3;
    }

    private boolean isRegionVisible(int i) {
        if (i == 0) {
            return true;
        }
        if (i == 1) {
            return isActivated();
        }
        if (i != 2) {
            return false;
        }
        return isSelected();
    }

    private boolean isCurrentRegionVisible(int i) {
        if (i == 0) {
            return true;
        }
        if (i == 1) {
            return isActivated();
        }
        if (i != 2) {
            return false;
        }
        if (this.mCardType != 2) {
            return isSelected();
        }
        if (this.mInfoVisFraction > 0.0f) {
            return true;
        }
        return false;
    }

    private void findChildrenViews() {
        this.mMainViewList.clear();
        this.mInfoViewList.clear();
        this.mExtraViewList.clear();
        int childCount = getChildCount();
        boolean z = hasInfoRegion() && isCurrentRegionVisible(this.mInfoVisibility);
        boolean z2 = hasExtraRegion() && this.mInfoOffset > 0.0f;
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt != null) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                int i2 = 8;
                if (layoutParams.viewType == 1) {
                    childAt.setAlpha(this.mInfoAlpha);
                    this.mInfoViewList.add(childAt);
                    if (z) {
                        i2 = 0;
                    }
                    childAt.setVisibility(i2);
                } else if (layoutParams.viewType == 2) {
                    this.mExtraViewList.add(childAt);
                    if (z2) {
                        i2 = 0;
                    }
                    childAt.setVisibility(i2);
                } else {
                    this.mMainViewList.add(childAt);
                    childAt.setVisibility(0);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public int[] onCreateDrawableState(int i) {
        boolean z = false;
        boolean z2 = false;
        for (int i2 : super.onCreateDrawableState(i)) {
            if (i2 == 16842919) {
                z = true;
            }
            if (i2 == 16842910) {
                z2 = true;
            }
        }
        if (z && z2) {
            return View.PRESSED_ENABLED_STATE_SET;
        }
        if (z) {
            return LB_PRESSED_STATE_SET;
        }
        if (z2) {
            return View.ENABLED_STATE_SET;
        }
        return View.EMPTY_STATE_SET;
    }

    private void applyActiveState(boolean z) {
        int i;
        if (hasInfoRegion() && (i = this.mInfoVisibility) == 1) {
            setInfoViewVisibility(isRegionVisible(i));
        }
    }

    private void setInfoViewVisibility(boolean z) {
        int i = this.mCardType;
        if (i == 3) {
            if (z) {
                for (int i2 = 0; i2 < this.mInfoViewList.size(); i2++) {
                    this.mInfoViewList.get(i2).setVisibility(0);
                }
                return;
            }
            for (int i3 = 0; i3 < this.mInfoViewList.size(); i3++) {
                this.mInfoViewList.get(i3).setVisibility(8);
            }
            for (int i4 = 0; i4 < this.mExtraViewList.size(); i4++) {
                this.mExtraViewList.get(i4).setVisibility(8);
            }
            this.mInfoOffset = 0.0f;
        } else if (i == 2) {
            if (this.mInfoVisibility == 2) {
                animateInfoHeight(z);
                return;
            }
            for (int i5 = 0; i5 < this.mInfoViewList.size(); i5++) {
                this.mInfoViewList.get(i5).setVisibility(z ? 0 : 8);
            }
        } else if (i == 1) {
            animateInfoAlpha(z);
        }
    }

    private void applySelectedState(boolean z) {
        removeCallbacks(this.mAnimationTrigger);
        if (this.mCardType == 3) {
            if (!z) {
                animateInfoOffset(false);
            } else if (!this.mDelaySelectedAnim) {
                post(this.mAnimationTrigger);
                this.mDelaySelectedAnim = true;
            } else {
                postDelayed(this.mAnimationTrigger, (long) this.mSelectedAnimationDelay);
            }
        } else if (this.mInfoVisibility == 2) {
            setInfoViewVisibility(z);
        }
    }

    /* access modifiers changed from: package-private */
    public void cancelAnimations() {
        Animation animation = this.mAnim;
        if (animation != null) {
            animation.cancel();
            this.mAnim = null;
            clearAnimation();
        }
    }

    /* access modifiers changed from: package-private */
    public void animateInfoOffset(boolean z) {
        cancelAnimations();
        int i = 0;
        if (z) {
            int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(this.mMeasuredWidth, 1073741824);
            int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(0, 0);
            int i2 = 0;
            for (int i3 = 0; i3 < this.mExtraViewList.size(); i3++) {
                View view = this.mExtraViewList.get(i3);
                view.setVisibility(0);
                view.measure(makeMeasureSpec, makeMeasureSpec2);
                i2 = Math.max(i2, view.getMeasuredHeight());
            }
            i = i2;
        }
        InfoOffsetAnimation infoOffsetAnimation = new InfoOffsetAnimation(this.mInfoOffset, z ? (float) i : 0.0f);
        this.mAnim = infoOffsetAnimation;
        infoOffsetAnimation.setDuration((long) this.mSelectedAnimDuration);
        this.mAnim.setInterpolator(new AccelerateDecelerateInterpolator());
        this.mAnim.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                if (BaseCardView.this.mInfoOffset == 0.0f) {
                    for (int i = 0; i < BaseCardView.this.mExtraViewList.size(); i++) {
                        BaseCardView.this.mExtraViewList.get(i).setVisibility(8);
                    }
                }
            }
        });
        startAnimation(this.mAnim);
    }

    private void animateInfoHeight(boolean z) {
        cancelAnimations();
        if (z) {
            for (int i = 0; i < this.mInfoViewList.size(); i++) {
                this.mInfoViewList.get(i).setVisibility(0);
            }
        }
        float f = z ? 1.0f : 0.0f;
        if (this.mInfoVisFraction != f) {
            InfoHeightAnimation infoHeightAnimation = new InfoHeightAnimation(this.mInfoVisFraction, f);
            this.mAnim = infoHeightAnimation;
            infoHeightAnimation.setDuration((long) this.mSelectedAnimDuration);
            this.mAnim.setInterpolator(new AccelerateDecelerateInterpolator());
            this.mAnim.setAnimationListener(new Animation.AnimationListener() {
                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationStart(Animation animation) {
                }

                public void onAnimationEnd(Animation animation) {
                    if (BaseCardView.this.mInfoVisFraction == 0.0f) {
                        for (int i = 0; i < BaseCardView.this.mInfoViewList.size(); i++) {
                            BaseCardView.this.mInfoViewList.get(i).setVisibility(8);
                        }
                    }
                }
            });
            startAnimation(this.mAnim);
        }
    }

    private void animateInfoAlpha(boolean z) {
        cancelAnimations();
        if (z) {
            for (int i = 0; i < this.mInfoViewList.size(); i++) {
                this.mInfoViewList.get(i).setVisibility(0);
            }
        }
        float f = 1.0f;
        if ((z ? 1.0f : 0.0f) != this.mInfoAlpha) {
            float f2 = this.mInfoAlpha;
            if (!z) {
                f = 0.0f;
            }
            InfoAlphaAnimation infoAlphaAnimation = new InfoAlphaAnimation(f2, f);
            this.mAnim = infoAlphaAnimation;
            infoAlphaAnimation.setDuration((long) this.mActivatedAnimDuration);
            this.mAnim.setInterpolator(new DecelerateInterpolator());
            this.mAnim.setAnimationListener(new Animation.AnimationListener() {
                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationStart(Animation animation) {
                }

                public void onAnimationEnd(Animation animation) {
                    if (((double) BaseCardView.this.mInfoAlpha) == 0.0d) {
                        for (int i = 0; i < BaseCardView.this.mInfoViewList.size(); i++) {
                            BaseCardView.this.mInfoViewList.get(i).setVisibility(8);
                        }
                    }
                }
            });
            startAnimation(this.mAnim);
        }
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    /* access modifiers changed from: protected */
    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    /* access modifiers changed from: protected */
    public LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof LayoutParams) {
            return new LayoutParams((LayoutParams) layoutParams);
        }
        return new LayoutParams(layoutParams);
    }

    /* access modifiers changed from: protected */
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    public static class LayoutParams extends FrameLayout.LayoutParams {
        public static final int VIEW_TYPE_EXTRA = 2;
        public static final int VIEW_TYPE_INFO = 1;
        public static final int VIEW_TYPE_MAIN = 0;
        @ViewDebug.ExportedProperty(category = "layout", mapping = {@ViewDebug.IntToString(from = 0, to = "MAIN"), @ViewDebug.IntToString(from = 1, to = "INFO"), @ViewDebug.IntToString(from = 2, to = "EXTRA")})
        public int viewType = 0;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.lbBaseCardView_Layout);
            this.viewType = obtainStyledAttributes.getInt(R.styleable.lbBaseCardView_Layout_layout_viewType, 0);
            obtainStyledAttributes.recycle();
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(LayoutParams layoutParams) {
            super(layoutParams);
            this.viewType = layoutParams.viewType;
        }
    }

    class AnimationBase extends Animation {
        AnimationBase() {
        }

        /* access modifiers changed from: package-private */
        public final void mockStart() {
            getTransformation(0, (Transformation) null);
        }

        /* access modifiers changed from: package-private */
        public final void mockEnd() {
            applyTransformation(1.0f, (Transformation) null);
            BaseCardView.this.cancelAnimations();
        }
    }

    final class InfoOffsetAnimation extends AnimationBase {
        private float mDelta;
        private float mStartValue;

        public InfoOffsetAnimation(float f, float f2) {
            super();
            this.mStartValue = f;
            this.mDelta = f2 - f;
        }

        /* access modifiers changed from: protected */
        public void applyTransformation(float f, Transformation transformation) {
            BaseCardView.this.mInfoOffset = this.mStartValue + (f * this.mDelta);
            BaseCardView.this.requestLayout();
        }
    }

    final class InfoHeightAnimation extends AnimationBase {
        private float mDelta;
        private float mStartValue;

        public InfoHeightAnimation(float f, float f2) {
            super();
            this.mStartValue = f;
            this.mDelta = f2 - f;
        }

        /* access modifiers changed from: protected */
        public void applyTransformation(float f, Transformation transformation) {
            BaseCardView.this.mInfoVisFraction = this.mStartValue + (f * this.mDelta);
            BaseCardView.this.requestLayout();
        }
    }

    final class InfoAlphaAnimation extends AnimationBase {
        private float mDelta;
        private float mStartValue;

        public InfoAlphaAnimation(float f, float f2) {
            super();
            this.mStartValue = f;
            this.mDelta = f2 - f;
        }

        /* access modifiers changed from: protected */
        public void applyTransformation(float f, Transformation transformation) {
            BaseCardView.this.mInfoAlpha = this.mStartValue + (f * this.mDelta);
            for (int i = 0; i < BaseCardView.this.mInfoViewList.size(); i++) {
                BaseCardView.this.mInfoViewList.get(i).setAlpha(BaseCardView.this.mInfoAlpha);
            }
        }
    }

    public String toString() {
        return super.toString();
    }
}
