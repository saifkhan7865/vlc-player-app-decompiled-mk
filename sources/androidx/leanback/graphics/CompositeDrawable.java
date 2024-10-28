package androidx.leanback.graphics;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Property;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.leanback.graphics.BoundsRule;
import java.util.ArrayList;

public class CompositeDrawable extends Drawable implements Drawable.Callback {
    boolean mMutated;
    CompositeState mState;

    public int getOpacity() {
        return 0;
    }

    static class CompositeState extends Drawable.ConstantState {
        final ArrayList<ChildDrawable> mChildren;

        public int getChangingConfigurations() {
            return 0;
        }

        CompositeState() {
            this.mChildren = new ArrayList<>();
        }

        CompositeState(CompositeState compositeState, CompositeDrawable compositeDrawable, Resources resources) {
            int size = compositeState.mChildren.size();
            this.mChildren = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                this.mChildren.add(new ChildDrawable(compositeState.mChildren.get(i), compositeDrawable, resources));
            }
        }

        public Drawable newDrawable() {
            return new CompositeDrawable(this);
        }
    }

    public CompositeDrawable() {
        this.mMutated = false;
        this.mState = new CompositeState();
    }

    CompositeDrawable(CompositeState compositeState) {
        this.mMutated = false;
        this.mState = compositeState;
    }

    public Drawable.ConstantState getConstantState() {
        return this.mState;
    }

    public Drawable mutate() {
        if (!this.mMutated && super.mutate() == this) {
            CompositeState compositeState = new CompositeState(this.mState, this, (Resources) null);
            this.mState = compositeState;
            ArrayList<ChildDrawable> arrayList = compositeState.mChildren;
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                Drawable drawable = arrayList.get(i).mDrawable;
                if (drawable != null) {
                    drawable.mutate();
                }
            }
            this.mMutated = true;
        }
        return this;
    }

    public void addChildDrawable(Drawable drawable) {
        this.mState.mChildren.add(new ChildDrawable(drawable, this));
    }

    public void setChildDrawableAt(int i, Drawable drawable) {
        this.mState.mChildren.set(i, new ChildDrawable(drawable, this));
    }

    public Drawable getDrawable(int i) {
        return this.mState.mChildren.get(i).mDrawable;
    }

    public ChildDrawable getChildAt(int i) {
        return this.mState.mChildren.get(i);
    }

    public void removeChild(int i) {
        this.mState.mChildren.remove(i);
    }

    public void removeDrawable(Drawable drawable) {
        ArrayList<ChildDrawable> arrayList = this.mState.mChildren;
        for (int i = 0; i < arrayList.size(); i++) {
            if (drawable == arrayList.get(i).mDrawable) {
                arrayList.get(i).mDrawable.setCallback((Drawable.Callback) null);
                arrayList.remove(i);
                return;
            }
        }
    }

    public int getChildCount() {
        return this.mState.mChildren.size();
    }

    public void draw(Canvas canvas) {
        ArrayList<ChildDrawable> arrayList = this.mState.mChildren;
        for (int i = 0; i < arrayList.size(); i++) {
            arrayList.get(i).mDrawable.draw(canvas);
        }
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        updateBounds(rect);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        ArrayList<ChildDrawable> arrayList = this.mState.mChildren;
        for (int i = 0; i < arrayList.size(); i++) {
            arrayList.get(i).mDrawable.setColorFilter(colorFilter);
        }
    }

    public void setAlpha(int i) {
        ArrayList<ChildDrawable> arrayList = this.mState.mChildren;
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            arrayList.get(i2).mDrawable.setAlpha(i);
        }
    }

    public int getAlpha() {
        Drawable firstNonNullDrawable = getFirstNonNullDrawable();
        if (firstNonNullDrawable != null) {
            return DrawableCompat.getAlpha(firstNonNullDrawable);
        }
        return 255;
    }

    /* access modifiers changed from: package-private */
    public final Drawable getFirstNonNullDrawable() {
        ArrayList<ChildDrawable> arrayList = this.mState.mChildren;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            Drawable drawable = arrayList.get(i).mDrawable;
            if (drawable != null) {
                return drawable;
            }
        }
        return null;
    }

    public void invalidateDrawable(Drawable drawable) {
        invalidateSelf();
    }

    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        scheduleSelf(runnable, j);
    }

    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        unscheduleSelf(runnable);
    }

    /* access modifiers changed from: package-private */
    public void updateBounds(Rect rect) {
        ArrayList<ChildDrawable> arrayList = this.mState.mChildren;
        for (int i = 0; i < arrayList.size(); i++) {
            arrayList.get(i).updateBounds(rect);
        }
    }

    public static final class ChildDrawable {
        public static final Property<ChildDrawable, Integer> BOTTOM_ABSOLUTE = new Property<ChildDrawable, Integer>(Integer.class, "absoluteBottom") {
            public void set(ChildDrawable childDrawable, Integer num) {
                if (childDrawable.getBoundsRule().bottom == null) {
                    childDrawable.getBoundsRule().bottom = BoundsRule.ValueRule.absoluteValue(num.intValue());
                } else {
                    childDrawable.getBoundsRule().bottom.setAbsoluteValue(num.intValue());
                }
                childDrawable.recomputeBounds();
            }

            public Integer get(ChildDrawable childDrawable) {
                if (childDrawable.getBoundsRule().bottom == null) {
                    return Integer.valueOf(childDrawable.mParent.getBounds().bottom);
                }
                return Integer.valueOf(childDrawable.getBoundsRule().bottom.getAbsoluteValue());
            }
        };
        public static final Property<ChildDrawable, Float> BOTTOM_FRACTION = new Property<ChildDrawable, Float>(Float.class, "fractionBottom") {
            public void set(ChildDrawable childDrawable, Float f) {
                if (childDrawable.getBoundsRule().bottom == null) {
                    childDrawable.getBoundsRule().bottom = BoundsRule.ValueRule.inheritFromParent(f.floatValue());
                } else {
                    childDrawable.getBoundsRule().bottom.setFraction(f.floatValue());
                }
                childDrawable.recomputeBounds();
            }

            public Float get(ChildDrawable childDrawable) {
                if (childDrawable.getBoundsRule().bottom == null) {
                    return Float.valueOf(1.0f);
                }
                return Float.valueOf(childDrawable.getBoundsRule().bottom.getFraction());
            }
        };
        public static final Property<ChildDrawable, Integer> LEFT_ABSOLUTE = new Property<ChildDrawable, Integer>(Integer.class, "absoluteLeft") {
            public void set(ChildDrawable childDrawable, Integer num) {
                if (childDrawable.getBoundsRule().left == null) {
                    childDrawable.getBoundsRule().left = BoundsRule.ValueRule.absoluteValue(num.intValue());
                } else {
                    childDrawable.getBoundsRule().left.setAbsoluteValue(num.intValue());
                }
                childDrawable.recomputeBounds();
            }

            public Integer get(ChildDrawable childDrawable) {
                if (childDrawable.getBoundsRule().left == null) {
                    return Integer.valueOf(childDrawable.mParent.getBounds().left);
                }
                return Integer.valueOf(childDrawable.getBoundsRule().left.getAbsoluteValue());
            }
        };
        public static final Property<ChildDrawable, Float> LEFT_FRACTION = new Property<ChildDrawable, Float>(Float.class, "fractionLeft") {
            public void set(ChildDrawable childDrawable, Float f) {
                if (childDrawable.getBoundsRule().left == null) {
                    childDrawable.getBoundsRule().left = BoundsRule.ValueRule.inheritFromParent(f.floatValue());
                } else {
                    childDrawable.getBoundsRule().left.setFraction(f.floatValue());
                }
                childDrawable.recomputeBounds();
            }

            public Float get(ChildDrawable childDrawable) {
                if (childDrawable.getBoundsRule().left == null) {
                    return Float.valueOf(0.0f);
                }
                return Float.valueOf(childDrawable.getBoundsRule().left.getFraction());
            }
        };
        public static final Property<ChildDrawable, Integer> RIGHT_ABSOLUTE = new Property<ChildDrawable, Integer>(Integer.class, "absoluteRight") {
            public void set(ChildDrawable childDrawable, Integer num) {
                if (childDrawable.getBoundsRule().right == null) {
                    childDrawable.getBoundsRule().right = BoundsRule.ValueRule.absoluteValue(num.intValue());
                } else {
                    childDrawable.getBoundsRule().right.setAbsoluteValue(num.intValue());
                }
                childDrawable.recomputeBounds();
            }

            public Integer get(ChildDrawable childDrawable) {
                if (childDrawable.getBoundsRule().right == null) {
                    return Integer.valueOf(childDrawable.mParent.getBounds().right);
                }
                return Integer.valueOf(childDrawable.getBoundsRule().right.getAbsoluteValue());
            }
        };
        public static final Property<ChildDrawable, Float> RIGHT_FRACTION = new Property<ChildDrawable, Float>(Float.class, "fractionRight") {
            public void set(ChildDrawable childDrawable, Float f) {
                if (childDrawable.getBoundsRule().right == null) {
                    childDrawable.getBoundsRule().right = BoundsRule.ValueRule.inheritFromParent(f.floatValue());
                } else {
                    childDrawable.getBoundsRule().right.setFraction(f.floatValue());
                }
                childDrawable.recomputeBounds();
            }

            public Float get(ChildDrawable childDrawable) {
                if (childDrawable.getBoundsRule().right == null) {
                    return Float.valueOf(1.0f);
                }
                return Float.valueOf(childDrawable.getBoundsRule().right.getFraction());
            }
        };
        public static final Property<ChildDrawable, Integer> TOP_ABSOLUTE = new Property<ChildDrawable, Integer>(Integer.class, "absoluteTop") {
            public void set(ChildDrawable childDrawable, Integer num) {
                if (childDrawable.getBoundsRule().top == null) {
                    childDrawable.getBoundsRule().top = BoundsRule.ValueRule.absoluteValue(num.intValue());
                } else {
                    childDrawable.getBoundsRule().top.setAbsoluteValue(num.intValue());
                }
                childDrawable.recomputeBounds();
            }

            public Integer get(ChildDrawable childDrawable) {
                if (childDrawable.getBoundsRule().top == null) {
                    return Integer.valueOf(childDrawable.mParent.getBounds().top);
                }
                return Integer.valueOf(childDrawable.getBoundsRule().top.getAbsoluteValue());
            }
        };
        public static final Property<ChildDrawable, Float> TOP_FRACTION = new Property<ChildDrawable, Float>(Float.class, "fractionTop") {
            public void set(ChildDrawable childDrawable, Float f) {
                if (childDrawable.getBoundsRule().top == null) {
                    childDrawable.getBoundsRule().top = BoundsRule.ValueRule.inheritFromParent(f.floatValue());
                } else {
                    childDrawable.getBoundsRule().top.setFraction(f.floatValue());
                }
                childDrawable.recomputeBounds();
            }

            public Float get(ChildDrawable childDrawable) {
                if (childDrawable.getBoundsRule().top == null) {
                    return Float.valueOf(0.0f);
                }
                return Float.valueOf(childDrawable.getBoundsRule().top.getFraction());
            }
        };
        private final Rect adjustedBounds = new Rect();
        private final BoundsRule mBoundsRule;
        final Drawable mDrawable;
        final CompositeDrawable mParent;

        public ChildDrawable(Drawable drawable, CompositeDrawable compositeDrawable) {
            this.mDrawable = drawable;
            this.mParent = compositeDrawable;
            this.mBoundsRule = new BoundsRule();
            drawable.setCallback(compositeDrawable);
        }

        ChildDrawable(ChildDrawable childDrawable, CompositeDrawable compositeDrawable, Resources resources) {
            Drawable drawable;
            Drawable drawable2 = childDrawable.mDrawable;
            if (drawable2 != null) {
                Drawable.ConstantState constantState = drawable2.getConstantState();
                if (resources != null) {
                    drawable = constantState.newDrawable(resources);
                } else {
                    drawable = constantState.newDrawable();
                }
                drawable.setCallback(compositeDrawable);
                DrawableCompat.setLayoutDirection(drawable, DrawableCompat.getLayoutDirection(drawable2));
                drawable.setBounds(drawable2.getBounds());
                drawable.setLevel(drawable2.getLevel());
            } else {
                drawable = null;
            }
            BoundsRule boundsRule = childDrawable.mBoundsRule;
            if (boundsRule != null) {
                this.mBoundsRule = new BoundsRule(boundsRule);
            } else {
                this.mBoundsRule = new BoundsRule();
            }
            this.mDrawable = drawable;
            this.mParent = compositeDrawable;
        }

        public BoundsRule getBoundsRule() {
            return this.mBoundsRule;
        }

        public Drawable getDrawable() {
            return this.mDrawable;
        }

        /* access modifiers changed from: package-private */
        public void updateBounds(Rect rect) {
            this.mBoundsRule.calculateBounds(rect, this.adjustedBounds);
            this.mDrawable.setBounds(this.adjustedBounds);
        }

        public void recomputeBounds() {
            updateBounds(this.mParent.getBounds());
        }
    }
}
