package androidx.car.app.model.constraints;

import androidx.car.app.model.CarIcon;
import androidx.car.app.model.Row;
import j$.util.Objects;

public final class RowConstraints {
    public static final RowConstraints ROW_CONSTRAINTS_CONSERVATIVE = new Builder().setMaxActionsExclusive(0).setImageAllowed(false).setMaxTextLinesPerRow(1).setOnClickListenerAllowed(true).setToggleAllowed(false).build();
    public static final RowConstraints ROW_CONSTRAINTS_FULL_LIST;
    public static final RowConstraints ROW_CONSTRAINTS_PANE = new Builder().setMaxActionsExclusive(2).setImageAllowed(true).setMaxTextLinesPerRow(2).setToggleAllowed(false).setOnClickListenerAllowed(false).build();
    public static final RowConstraints ROW_CONSTRAINTS_SIMPLE;
    public static final RowConstraints UNCONSTRAINED = new Builder().build();
    private final CarIconConstraints mCarIconConstraints;
    private final boolean mIsImageAllowed;
    private final boolean mIsOnClickListenerAllowed;
    private final boolean mIsToggleAllowed;
    private final int mMaxActionsExclusive;
    private final int mMaxTextLinesPerRow;

    static {
        RowConstraints build = new Builder().setMaxActionsExclusive(0).setImageAllowed(true).setMaxTextLinesPerRow(2).setToggleAllowed(false).setOnClickListenerAllowed(true).build();
        ROW_CONSTRAINTS_SIMPLE = build;
        ROW_CONSTRAINTS_FULL_LIST = new Builder(build).setToggleAllowed(true).build();
    }

    public boolean isOnClickListenerAllowed() {
        return this.mIsOnClickListenerAllowed;
    }

    public int getMaxTextLinesPerRow() {
        return this.mMaxTextLinesPerRow;
    }

    public int getMaxActionsExclusive() {
        return this.mMaxActionsExclusive;
    }

    public boolean isToggleAllowed() {
        return this.mIsToggleAllowed;
    }

    public boolean isImageAllowed() {
        return this.mIsImageAllowed;
    }

    public CarIconConstraints getCarIconConstraints() {
        return this.mCarIconConstraints;
    }

    public void validateOrThrow(Row row) {
        if (!this.mIsOnClickListenerAllowed && row.getOnClickDelegate() != null) {
            throw new IllegalArgumentException("A click listener is not allowed on the row");
        } else if (this.mIsToggleAllowed || row.getToggle() == null) {
            CarIcon image = row.getImage();
            if (image != null) {
                if (this.mIsImageAllowed) {
                    this.mCarIconConstraints.validateOrThrow(image);
                } else {
                    throw new IllegalArgumentException("An image is not allowed on the row");
                }
            }
            if (row.getTexts().size() > this.mMaxTextLinesPerRow) {
                throw new IllegalArgumentException("The number of lines of texts for the row exceeded the supported max of " + this.mMaxTextLinesPerRow);
            }
        } else {
            throw new IllegalArgumentException("A toggle is not allowed on the row");
        }
    }

    RowConstraints(Builder builder) {
        this.mIsOnClickListenerAllowed = builder.mIsOnClickListenerAllowed;
        this.mMaxTextLinesPerRow = builder.mMaxTextLines;
        this.mMaxActionsExclusive = builder.mMaxActionsExclusive;
        this.mIsToggleAllowed = builder.mIsToggleAllowed;
        this.mIsImageAllowed = builder.mIsImageAllowed;
        this.mCarIconConstraints = builder.mCarIconConstraints;
    }

    public static final class Builder {
        CarIconConstraints mCarIconConstraints = CarIconConstraints.UNCONSTRAINED;
        boolean mIsImageAllowed = true;
        boolean mIsOnClickListenerAllowed = true;
        boolean mIsToggleAllowed = true;
        int mMaxActionsExclusive = Integer.MAX_VALUE;
        int mMaxTextLines = Integer.MAX_VALUE;

        public Builder setOnClickListenerAllowed(boolean z) {
            this.mIsOnClickListenerAllowed = z;
            return this;
        }

        public Builder setMaxTextLinesPerRow(int i) {
            this.mMaxTextLines = i;
            return this;
        }

        public Builder setMaxActionsExclusive(int i) {
            this.mMaxActionsExclusive = i;
            return this;
        }

        public Builder setImageAllowed(boolean z) {
            this.mIsImageAllowed = z;
            return this;
        }

        public Builder setToggleAllowed(boolean z) {
            this.mIsToggleAllowed = z;
            return this;
        }

        public Builder setCarIconConstraints(CarIconConstraints carIconConstraints) {
            this.mCarIconConstraints = carIconConstraints;
            return this;
        }

        public RowConstraints build() {
            return new RowConstraints(this);
        }

        public Builder() {
        }

        public Builder(RowConstraints rowConstraints) {
            Objects.requireNonNull(rowConstraints);
            this.mIsOnClickListenerAllowed = rowConstraints.isOnClickListenerAllowed();
            this.mMaxTextLines = rowConstraints.getMaxTextLinesPerRow();
            this.mMaxActionsExclusive = rowConstraints.getMaxActionsExclusive();
            this.mIsToggleAllowed = rowConstraints.isToggleAllowed();
            this.mIsImageAllowed = rowConstraints.isImageAllowed();
            this.mCarIconConstraints = rowConstraints.getCarIconConstraints();
        }
    }
}
