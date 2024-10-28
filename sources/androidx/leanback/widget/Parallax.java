package androidx.leanback.widget;

import android.util.Property;
import androidx.leanback.widget.ParallaxEffect;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Parallax<PropertyT extends Property> {
    private final List<ParallaxEffect> mEffects = new ArrayList(4);
    private float[] mFloatValues = new float[4];
    final List<PropertyT> mProperties;
    final List<PropertyT> mPropertiesReadOnly;
    private int[] mValues = new int[4];

    public abstract PropertyT createProperty(String str, int i);

    public abstract float getMaxValue();

    public Parallax() {
        ArrayList arrayList = new ArrayList();
        this.mProperties = arrayList;
        this.mPropertiesReadOnly = Collections.unmodifiableList(arrayList);
    }

    public static class PropertyMarkerValue<PropertyT> {
        private final PropertyT mProperty;

        public PropertyMarkerValue(PropertyT propertyt) {
            this.mProperty = propertyt;
        }

        public PropertyT getProperty() {
            return this.mProperty;
        }
    }

    public static class IntProperty extends Property<Parallax, Integer> {
        public static final int UNKNOWN_AFTER = Integer.MAX_VALUE;
        public static final int UNKNOWN_BEFORE = Integer.MIN_VALUE;
        private final int mIndex;

        public IntProperty(String str, int i) {
            super(Integer.class, str);
            this.mIndex = i;
        }

        public final Integer get(Parallax parallax) {
            return Integer.valueOf(parallax.getIntPropertyValue(this.mIndex));
        }

        public final void set(Parallax parallax, Integer num) {
            parallax.setIntPropertyValue(this.mIndex, num.intValue());
        }

        public final int getIndex() {
            return this.mIndex;
        }

        public final int getValue(Parallax parallax) {
            return parallax.getIntPropertyValue(this.mIndex);
        }

        public final void setValue(Parallax parallax, int i) {
            parallax.setIntPropertyValue(this.mIndex, i);
        }

        public final PropertyMarkerValue atAbsolute(int i) {
            return new IntPropertyMarkerValue(this, i, 0.0f);
        }

        public final PropertyMarkerValue atMax() {
            return new IntPropertyMarkerValue(this, 0, 1.0f);
        }

        public final PropertyMarkerValue atMin() {
            return new IntPropertyMarkerValue(this, 0);
        }

        public final PropertyMarkerValue atFraction(float f) {
            return new IntPropertyMarkerValue(this, 0, f);
        }

        public final PropertyMarkerValue at(int i, float f) {
            return new IntPropertyMarkerValue(this, i, f);
        }
    }

    static class IntPropertyMarkerValue extends PropertyMarkerValue<IntProperty> {
        private final float mFactionOfMax;
        private final int mValue;

        IntPropertyMarkerValue(IntProperty intProperty, int i) {
            this(intProperty, i, 0.0f);
        }

        IntPropertyMarkerValue(IntProperty intProperty, int i, float f) {
            super(intProperty);
            this.mValue = i;
            this.mFactionOfMax = f;
        }

        /* access modifiers changed from: package-private */
        public final int getMarkerValue(Parallax parallax) {
            if (this.mFactionOfMax == 0.0f) {
                return this.mValue;
            }
            return Math.round(parallax.getMaxValue() * this.mFactionOfMax) + this.mValue;
        }
    }

    public static class FloatProperty extends Property<Parallax, Float> {
        public static final float UNKNOWN_AFTER = Float.MAX_VALUE;
        public static final float UNKNOWN_BEFORE = -3.4028235E38f;
        private final int mIndex;

        public FloatProperty(String str, int i) {
            super(Float.class, str);
            this.mIndex = i;
        }

        public final Float get(Parallax parallax) {
            return Float.valueOf(parallax.getFloatPropertyValue(this.mIndex));
        }

        public final void set(Parallax parallax, Float f) {
            parallax.setFloatPropertyValue(this.mIndex, f.floatValue());
        }

        public final int getIndex() {
            return this.mIndex;
        }

        public final float getValue(Parallax parallax) {
            return parallax.getFloatPropertyValue(this.mIndex);
        }

        public final void setValue(Parallax parallax, float f) {
            parallax.setFloatPropertyValue(this.mIndex, f);
        }

        public final PropertyMarkerValue atAbsolute(float f) {
            return new FloatPropertyMarkerValue(this, f, 0.0f);
        }

        public final PropertyMarkerValue atMax() {
            return new FloatPropertyMarkerValue(this, 0.0f, 1.0f);
        }

        public final PropertyMarkerValue atMin() {
            return new FloatPropertyMarkerValue(this, 0.0f);
        }

        public final PropertyMarkerValue atFraction(float f) {
            return new FloatPropertyMarkerValue(this, 0.0f, f);
        }

        public final PropertyMarkerValue at(float f, float f2) {
            return new FloatPropertyMarkerValue(this, f, f2);
        }
    }

    static class FloatPropertyMarkerValue extends PropertyMarkerValue<FloatProperty> {
        private final float mFactionOfMax;
        private final float mValue;

        FloatPropertyMarkerValue(FloatProperty floatProperty, float f) {
            this(floatProperty, f, 0.0f);
        }

        FloatPropertyMarkerValue(FloatProperty floatProperty, float f, float f2) {
            super(floatProperty);
            this.mValue = f;
            this.mFactionOfMax = f2;
        }

        /* access modifiers changed from: package-private */
        public final float getMarkerValue(Parallax parallax) {
            if (this.mFactionOfMax == 0.0f) {
                return this.mValue;
            }
            return (parallax.getMaxValue() * this.mFactionOfMax) + this.mValue;
        }
    }

    /* access modifiers changed from: package-private */
    public final int getIntPropertyValue(int i) {
        return this.mValues[i];
    }

    /* access modifiers changed from: package-private */
    public final void setIntPropertyValue(int i, int i2) {
        if (i < this.mProperties.size()) {
            this.mValues[i] = i2;
            return;
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public final PropertyT addProperty(String str) {
        int size = this.mProperties.size();
        PropertyT createProperty = createProperty(str, size);
        int i = 0;
        if (createProperty instanceof IntProperty) {
            int length = this.mValues.length;
            if (length == size) {
                int[] iArr = new int[(length * 2)];
                while (i < length) {
                    iArr[i] = this.mValues[i];
                    i++;
                }
                this.mValues = iArr;
            }
            this.mValues[size] = Integer.MAX_VALUE;
        } else if (createProperty instanceof FloatProperty) {
            int length2 = this.mFloatValues.length;
            if (length2 == size) {
                float[] fArr = new float[(length2 * 2)];
                while (i < length2) {
                    fArr[i] = this.mFloatValues[i];
                    i++;
                }
                this.mFloatValues = fArr;
            }
            this.mFloatValues[size] = Float.MAX_VALUE;
        } else {
            throw new IllegalArgumentException("Invalid Property type");
        }
        this.mProperties.add(createProperty);
        return createProperty;
    }

    /* access modifiers changed from: package-private */
    public void verifyIntProperties() throws IllegalStateException {
        if (this.mProperties.size() >= 2) {
            int intPropertyValue = getIntPropertyValue(0);
            int i = 1;
            while (i < this.mProperties.size()) {
                int intPropertyValue2 = getIntPropertyValue(i);
                if (intPropertyValue2 < intPropertyValue) {
                    Integer valueOf = Integer.valueOf(i);
                    String name = ((Property) this.mProperties.get(i)).getName();
                    int i2 = i - 1;
                    throw new IllegalStateException(String.format("Parallax Property[%d]\"%s\" is smaller than Property[%d]\"%s\"", new Object[]{valueOf, name, Integer.valueOf(i2), ((Property) this.mProperties.get(i2)).getName()}));
                } else if (intPropertyValue == Integer.MIN_VALUE && intPropertyValue2 == Integer.MAX_VALUE) {
                    int i3 = i - 1;
                    throw new IllegalStateException(String.format("Parallax Property[%d]\"%s\" is UNKNOWN_BEFORE and Property[%d]\"%s\" is UNKNOWN_AFTER", new Object[]{Integer.valueOf(i3), ((Property) this.mProperties.get(i3)).getName(), Integer.valueOf(i), ((Property) this.mProperties.get(i)).getName()}));
                } else {
                    i++;
                    intPropertyValue = intPropertyValue2;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void verifyFloatProperties() throws IllegalStateException {
        if (this.mProperties.size() >= 2) {
            float floatPropertyValue = getFloatPropertyValue(0);
            int i = 1;
            while (i < this.mProperties.size()) {
                float floatPropertyValue2 = getFloatPropertyValue(i);
                if (floatPropertyValue2 < floatPropertyValue) {
                    Integer valueOf = Integer.valueOf(i);
                    String name = ((Property) this.mProperties.get(i)).getName();
                    int i2 = i - 1;
                    throw new IllegalStateException(String.format("Parallax Property[%d]\"%s\" is smaller than Property[%d]\"%s\"", new Object[]{valueOf, name, Integer.valueOf(i2), ((Property) this.mProperties.get(i2)).getName()}));
                } else if (floatPropertyValue == -3.4028235E38f && floatPropertyValue2 == Float.MAX_VALUE) {
                    int i3 = i - 1;
                    throw new IllegalStateException(String.format("Parallax Property[%d]\"%s\" is UNKNOWN_BEFORE and Property[%d]\"%s\" is UNKNOWN_AFTER", new Object[]{Integer.valueOf(i3), ((Property) this.mProperties.get(i3)).getName(), Integer.valueOf(i), ((Property) this.mProperties.get(i)).getName()}));
                } else {
                    i++;
                    floatPropertyValue = floatPropertyValue2;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final float getFloatPropertyValue(int i) {
        return this.mFloatValues[i];
    }

    /* access modifiers changed from: package-private */
    public final void setFloatPropertyValue(int i, float f) {
        if (i < this.mProperties.size()) {
            this.mFloatValues[i] = f;
            return;
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public final List<PropertyT> getProperties() {
        return this.mPropertiesReadOnly;
    }

    public void updateValues() {
        for (int i = 0; i < this.mEffects.size(); i++) {
            this.mEffects.get(i).performMapping(this);
        }
    }

    public List<ParallaxEffect> getEffects() {
        return this.mEffects;
    }

    public void removeEffect(ParallaxEffect parallaxEffect) {
        this.mEffects.remove(parallaxEffect);
    }

    public void removeAllEffects() {
        this.mEffects.clear();
    }

    public ParallaxEffect addEffect(PropertyMarkerValue... propertyMarkerValueArr) {
        ParallaxEffect parallaxEffect;
        if (propertyMarkerValueArr[0].getProperty() instanceof IntProperty) {
            parallaxEffect = new ParallaxEffect.IntEffect();
        } else {
            parallaxEffect = new ParallaxEffect.FloatEffect();
        }
        parallaxEffect.setPropertyRanges(propertyMarkerValueArr);
        this.mEffects.add(parallaxEffect);
        return parallaxEffect;
    }
}
