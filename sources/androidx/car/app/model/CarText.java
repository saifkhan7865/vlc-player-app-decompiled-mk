package androidx.car.app.model;

import android.text.SpannableString;
import android.text.Spanned;
import androidx.car.app.annotations.RequiresCarApi;
import androidx.car.app.utils.CollectionUtils;
import androidx.car.app.utils.StringUtils;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class CarText {
    private final List<SpanWrapper> mSpans;
    private final List<List<SpanWrapper>> mSpansForVariants;
    private final String mText;
    private final List<String> mTextVariants;

    public static boolean isNullOrEmpty(CarText carText) {
        return carText == null || carText.isEmpty();
    }

    public static CarText create(CharSequence charSequence) {
        return new CarText((CharSequence) Objects.requireNonNull(charSequence));
    }

    public boolean isEmpty() {
        return this.mText.isEmpty();
    }

    public String toString() {
        return this.mText;
    }

    public CharSequence toCharSequence() {
        return getCharSequence(this.mText, this.mSpans);
    }

    public List<CharSequence> getVariants() {
        if (this.mTextVariants.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.mTextVariants.size(); i++) {
            arrayList.add(getCharSequence(this.mTextVariants.get(i), this.mSpansForVariants.get(i)));
        }
        return Collections.unmodifiableList(arrayList);
    }

    public List<SpanWrapper> getSpans() {
        return this.mSpans;
    }

    public List<List<SpanWrapper>> getSpansForVariants() {
        return this.mSpansForVariants;
    }

    public static String toShortString(CarText carText) {
        if (carText == null) {
            return null;
        }
        return StringUtils.shortenString(carText.toString());
    }

    private CarText() {
        this.mText = "";
        this.mSpans = Collections.emptyList();
        this.mTextVariants = Collections.emptyList();
        this.mSpansForVariants = Collections.emptyList();
    }

    CarText(CharSequence charSequence) {
        this.mText = charSequence.toString();
        this.mSpans = getSpans(charSequence);
        this.mTextVariants = Collections.emptyList();
        this.mSpansForVariants = Collections.emptyList();
    }

    CarText(Builder builder) {
        this.mText = builder.mText.toString();
        this.mSpans = getSpans(builder.mText);
        List<CharSequence> list = builder.mTextVariants;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            CharSequence charSequence = list.get(i);
            arrayList.add(charSequence.toString());
            arrayList2.add(getSpans(charSequence));
        }
        this.mTextVariants = CollectionUtils.unmodifiableCopy(arrayList);
        this.mSpansForVariants = CollectionUtils.unmodifiableCopy(arrayList2);
    }

    private static List<SpanWrapper> getSpans(CharSequence charSequence) {
        ArrayList arrayList = new ArrayList();
        if (charSequence instanceof Spanned) {
            Spanned spanned = (Spanned) charSequence;
            for (Object obj : spanned.getSpans(0, charSequence.length(), Object.class)) {
                if (obj instanceof CarSpan) {
                    arrayList.add(new SpanWrapper(spanned, (CarSpan) obj));
                }
            }
        }
        return CollectionUtils.unmodifiableCopy(arrayList);
    }

    private static CharSequence getCharSequence(String str, List<SpanWrapper> list) {
        SpannableString spannableString = new SpannableString(str);
        for (T t : CollectionUtils.emptyIfNull(list)) {
            spannableString.setSpan(t.getCarSpan(), t.getStart(), t.getEnd(), t.getFlags());
        }
        return spannableString;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CarText)) {
            return false;
        }
        CarText carText = (CarText) obj;
        if (!Objects.equals(this.mText, carText.mText) || !Objects.equals(this.mSpans, carText.mSpans) || !Objects.equals(this.mTextVariants, carText.mTextVariants) || !Objects.equals(this.mSpansForVariants, carText.mSpansForVariants)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Objects.hash(this.mText, this.mSpans, this.mTextVariants, this.mSpansForVariants);
    }

    public static class SpanWrapper {
        private final CarSpan mCarSpan;
        private final int mEnd;
        private final int mFlags;
        private final int mStart;

        SpanWrapper(Spanned spanned, CarSpan carSpan) {
            this.mStart = spanned.getSpanStart(carSpan);
            this.mEnd = spanned.getSpanEnd(carSpan);
            this.mFlags = spanned.getSpanFlags(carSpan);
            this.mCarSpan = carSpan;
        }

        SpanWrapper() {
            this.mStart = 0;
            this.mEnd = 0;
            this.mFlags = 0;
            this.mCarSpan = new CarSpan();
        }

        public int getStart() {
            return this.mStart;
        }

        public int getEnd() {
            return this.mEnd;
        }

        public int getFlags() {
            return this.mFlags;
        }

        public CarSpan getCarSpan() {
            return this.mCarSpan;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SpanWrapper)) {
                return false;
            }
            SpanWrapper spanWrapper = (SpanWrapper) obj;
            if (this.mStart == spanWrapper.mStart && this.mEnd == spanWrapper.mEnd && this.mFlags == spanWrapper.mFlags && Objects.equals(this.mCarSpan, spanWrapper.mCarSpan)) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(this.mStart), Integer.valueOf(this.mEnd), Integer.valueOf(this.mFlags), this.mCarSpan);
        }

        public String toString() {
            return "[" + this.mCarSpan + ": " + this.mStart + ", " + this.mEnd + ", flags: " + this.mFlags + "]";
        }
    }

    public static final class Builder {
        CharSequence mText;
        List<CharSequence> mTextVariants = new ArrayList();

        public Builder(CharSequence charSequence) {
            this.mText = (CharSequence) Objects.requireNonNull(charSequence);
        }

        @RequiresCarApi(2)
        public Builder addVariant(CharSequence charSequence) {
            this.mTextVariants.add((CharSequence) Objects.requireNonNull(charSequence));
            return this;
        }

        public CarText build() {
            return new CarText(this);
        }
    }
}
