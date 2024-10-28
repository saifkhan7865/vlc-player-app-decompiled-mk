package androidx.window.embedding;

import androidx.tvprovider.media.tv.TvContractCompat;
import androidx.window.core.Logger;
import androidx.window.core.SpecificationComputer;
import androidx.window.core.VerificationMode;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u0000 \u00132\u00020\u0001:\u0004\u0012\u0013\u0014\u0015B\u001b\b\u0007\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0001H\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0016"}, d2 = {"Landroidx/window/embedding/SplitAttributes;", "", "splitType", "Landroidx/window/embedding/SplitAttributes$SplitType;", "layoutDirection", "Landroidx/window/embedding/SplitAttributes$LayoutDirection;", "(Landroidx/window/embedding/SplitAttributes$SplitType;Landroidx/window/embedding/SplitAttributes$LayoutDirection;)V", "getLayoutDirection", "()Landroidx/window/embedding/SplitAttributes$LayoutDirection;", "getSplitType", "()Landroidx/window/embedding/SplitAttributes$SplitType;", "equals", "", "other", "hashCode", "", "toString", "", "Builder", "Companion", "LayoutDirection", "SplitType", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: SplitAttributes.kt */
public final class SplitAttributes {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final String TAG = "SplitAttributes";
    private final LayoutDirection layoutDirection;
    private final SplitType splitType;

    public SplitAttributes() {
        this((SplitType) null, (LayoutDirection) null, 3, (DefaultConstructorMarker) null);
    }

    public SplitAttributes(SplitType splitType2, LayoutDirection layoutDirection2) {
        Intrinsics.checkNotNullParameter(splitType2, "splitType");
        Intrinsics.checkNotNullParameter(layoutDirection2, "layoutDirection");
        this.splitType = splitType2;
        this.layoutDirection = layoutDirection2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ SplitAttributes(SplitType splitType2, LayoutDirection layoutDirection2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? SplitType.SPLIT_TYPE_EQUAL : splitType2, (i & 2) != 0 ? LayoutDirection.LOCALE : layoutDirection2);
    }

    public final SplitType getSplitType() {
        return this.splitType;
    }

    public final LayoutDirection getLayoutDirection() {
        return this.layoutDirection;
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u0017\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0001H\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u0003H\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0012"}, d2 = {"Landroidx/window/embedding/SplitAttributes$SplitType;", "", "description", "", "value", "", "(Ljava/lang/String;F)V", "getDescription$window_release", "()Ljava/lang/String;", "getValue$window_release", "()F", "equals", "", "other", "hashCode", "", "toString", "Companion", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: SplitAttributes.kt */
    public static final class SplitType {
        public static final Companion Companion;
        public static final SplitType SPLIT_TYPE_EQUAL;
        public static final SplitType SPLIT_TYPE_EXPAND = new SplitType("expandContainers", 0.0f);
        public static final SplitType SPLIT_TYPE_HINGE = new SplitType("hinge", -1.0f);
        private final String description;
        private final float value;

        @JvmStatic
        public static final SplitType ratio(float f) {
            return Companion.ratio(f);
        }

        public SplitType(String str, float f) {
            Intrinsics.checkNotNullParameter(str, TvContractCompat.Channels.COLUMN_DESCRIPTION);
            this.description = str;
            this.value = f;
        }

        public final String getDescription$window_release() {
            return this.description;
        }

        public final float getValue$window_release() {
            return this.value;
        }

        public String toString() {
            return this.description;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof SplitType)) {
                return false;
            }
            SplitType splitType = (SplitType) obj;
            if (this.value != splitType.value || !Intrinsics.areEqual((Object) this.description, (Object) splitType.description)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return this.description.hashCode() + (Float.floatToIntBits(this.value) * 31);
        }

        @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0017\u0010\u0007\u001a\u00020\u00042\b\b\u0001\u0010\b\u001a\u00020\tH\u0001¢\u0006\u0002\b\nJ\u0012\u0010\u000b\u001a\u00020\u00042\b\b\u0001\u0010\u000b\u001a\u00020\tH\u0007R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Landroidx/window/embedding/SplitAttributes$SplitType$Companion;", "", "()V", "SPLIT_TYPE_EQUAL", "Landroidx/window/embedding/SplitAttributes$SplitType;", "SPLIT_TYPE_EXPAND", "SPLIT_TYPE_HINGE", "buildSplitTypeFromValue", "value", "", "buildSplitTypeFromValue$window_release", "ratio", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* compiled from: SplitAttributes.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            @JvmStatic
            public final SplitType ratio(float f) {
                SpecificationComputer.Companion companion = SpecificationComputer.Companion;
                Float valueOf = Float.valueOf(f);
                String access$getTAG$cp = SplitAttributes.TAG;
                Intrinsics.checkNotNullExpressionValue(access$getTAG$cp, "TAG");
                Object compute = SpecificationComputer.Companion.startSpecification$default(companion, valueOf, access$getTAG$cp, VerificationMode.STRICT, (Logger) null, 4, (Object) null).require("Ratio must be in range (0.0, 1.0). Use SplitType.expandContainers() instead of 0 or 1.", new SplitAttributes$SplitType$Companion$ratio$checkedRatio$1(f)).compute();
                Intrinsics.checkNotNull(compute);
                float floatValue = ((Number) compute).floatValue();
                return new SplitType("ratio:" + floatValue, floatValue);
            }

            public final SplitType buildSplitTypeFromValue$window_release(float f) {
                if (f == SplitType.SPLIT_TYPE_EXPAND.getValue$window_release()) {
                    return SplitType.SPLIT_TYPE_EXPAND;
                }
                return ratio(f);
            }
        }

        static {
            Companion companion = new Companion((DefaultConstructorMarker) null);
            Companion = companion;
            SPLIT_TYPE_EQUAL = companion.ratio(0.5f);
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u0000 \n2\u00020\u0001:\u0001\nB\u0017\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\t\u001a\u00020\u0003H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u000b"}, d2 = {"Landroidx/window/embedding/SplitAttributes$LayoutDirection;", "", "description", "", "value", "", "(Ljava/lang/String;I)V", "getValue$window_release", "()I", "toString", "Companion", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: SplitAttributes.kt */
    public static final class LayoutDirection {
        public static final LayoutDirection BOTTOM_TO_TOP = new LayoutDirection("BOTTOM_TO_TOP", 4);
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        public static final LayoutDirection LEFT_TO_RIGHT = new LayoutDirection("LEFT_TO_RIGHT", 1);
        public static final LayoutDirection LOCALE = new LayoutDirection("LOCALE", 0);
        public static final LayoutDirection RIGHT_TO_LEFT = new LayoutDirection("RIGHT_TO_LEFT", 2);
        public static final LayoutDirection TOP_TO_BOTTOM = new LayoutDirection("TOP_TO_BOTTOM", 3);
        private final String description;
        private final int value;

        @JvmStatic
        public static final LayoutDirection getLayoutDirectionFromValue$window_release(int i) {
            return Companion.getLayoutDirectionFromValue$window_release(i);
        }

        private LayoutDirection(String str, int i) {
            this.description = str;
            this.value = i;
        }

        public final int getValue$window_release() {
            return this.value;
        }

        public String toString() {
            return this.description;
        }

        @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0017\u0010\t\u001a\u00020\u00042\b\b\u0001\u0010\n\u001a\u00020\u000bH\u0001¢\u0006\u0002\b\fR\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Landroidx/window/embedding/SplitAttributes$LayoutDirection$Companion;", "", "()V", "BOTTOM_TO_TOP", "Landroidx/window/embedding/SplitAttributes$LayoutDirection;", "LEFT_TO_RIGHT", "LOCALE", "RIGHT_TO_LEFT", "TOP_TO_BOTTOM", "getLayoutDirectionFromValue", "value", "", "getLayoutDirectionFromValue$window_release", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* compiled from: SplitAttributes.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            @JvmStatic
            public final LayoutDirection getLayoutDirectionFromValue$window_release(int i) {
                if (i == LayoutDirection.LEFT_TO_RIGHT.getValue$window_release()) {
                    return LayoutDirection.LEFT_TO_RIGHT;
                }
                if (i == LayoutDirection.RIGHT_TO_LEFT.getValue$window_release()) {
                    return LayoutDirection.RIGHT_TO_LEFT;
                }
                if (i == LayoutDirection.LOCALE.getValue$window_release()) {
                    return LayoutDirection.LOCALE;
                }
                if (i == LayoutDirection.TOP_TO_BOTTOM.getValue$window_release()) {
                    return LayoutDirection.TOP_TO_BOTTOM;
                }
                if (i == LayoutDirection.BOTTOM_TO_TOP.getValue$window_release()) {
                    return LayoutDirection.BOTTOM_TO_TOP;
                }
                throw new IllegalArgumentException("Undefined value:" + i);
            }
        }
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Landroidx/window/embedding/SplitAttributes$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: SplitAttributes.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public int hashCode() {
        return (this.splitType.hashCode() * 31) + this.layoutDirection.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SplitAttributes)) {
            return false;
        }
        SplitAttributes splitAttributes = (SplitAttributes) obj;
        if (!Intrinsics.areEqual((Object) this.splitType, (Object) splitAttributes.splitType) || !Intrinsics.areEqual((Object) this.layoutDirection, (Object) splitAttributes.layoutDirection)) {
            return false;
        }
        return true;
    }

    public String toString() {
        return "SplitAttributes:{splitType=" + this.splitType + ", layoutDir=" + this.layoutDirection + " }";
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\t\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0004J\u000e\u0010\n\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Landroidx/window/embedding/SplitAttributes$Builder;", "", "()V", "layoutDirection", "Landroidx/window/embedding/SplitAttributes$LayoutDirection;", "splitType", "Landroidx/window/embedding/SplitAttributes$SplitType;", "build", "Landroidx/window/embedding/SplitAttributes;", "setLayoutDirection", "setSplitType", "type", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: SplitAttributes.kt */
    public static final class Builder {
        private LayoutDirection layoutDirection = LayoutDirection.LOCALE;
        private SplitType splitType = SplitType.SPLIT_TYPE_EQUAL;

        public final Builder setSplitType(SplitType splitType2) {
            Intrinsics.checkNotNullParameter(splitType2, "type");
            Builder builder = this;
            this.splitType = splitType2;
            return this;
        }

        public final Builder setLayoutDirection(LayoutDirection layoutDirection2) {
            Intrinsics.checkNotNullParameter(layoutDirection2, "layoutDirection");
            Builder builder = this;
            this.layoutDirection = layoutDirection2;
            return this;
        }

        public final SplitAttributes build() {
            return new SplitAttributes(this.splitType, this.layoutDirection);
        }
    }
}
