package androidx.car.app.model;

import androidx.car.app.annotations.RequiresCarApi;
import androidx.car.app.utils.CollectionUtils;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Pane {
    private final List<Action> mActionList;
    private final CarIcon mImage;
    private final boolean mIsLoading;
    private final List<Row> mRows;

    public boolean isLoading() {
        return this.mIsLoading;
    }

    public List<Action> getActions() {
        return CollectionUtils.emptyIfNull(this.mActionList);
    }

    public List<Row> getRows() {
        return CollectionUtils.emptyIfNull(this.mRows);
    }

    @RequiresCarApi(4)
    public CarIcon getImage() {
        return this.mImage;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[ rows: ");
        List<Row> list = this.mRows;
        sb.append(list != null ? list.toString() : null);
        sb.append(", action list: ");
        sb.append(this.mActionList);
        sb.append("]");
        return sb.toString();
    }

    public int hashCode() {
        return Objects.hash(this.mRows, this.mActionList, Boolean.valueOf(this.mIsLoading), this.mImage);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Pane)) {
            return false;
        }
        Pane pane = (Pane) obj;
        if (this.mIsLoading != pane.mIsLoading || !Objects.equals(this.mActionList, pane.mActionList) || !Objects.equals(this.mRows, pane.mRows) || !Objects.equals(this.mImage, pane.mImage)) {
            return false;
        }
        return true;
    }

    Pane(Builder builder) {
        this.mRows = CollectionUtils.unmodifiableCopy(builder.mRows);
        this.mActionList = CollectionUtils.unmodifiableCopy(builder.mActionList);
        this.mImage = builder.mImage;
        this.mIsLoading = builder.mIsLoading;
    }

    private Pane() {
        this.mRows = Collections.emptyList();
        this.mActionList = Collections.emptyList();
        this.mIsLoading = false;
        this.mImage = null;
    }

    public static final class Builder {
        List<Action> mActionList = new ArrayList();
        CarIcon mImage;
        boolean mIsLoading;
        final List<Row> mRows = new ArrayList();

        public Builder setLoading(boolean z) {
            this.mIsLoading = z;
            return this;
        }

        public Builder addRow(Row row) {
            this.mRows.add((Row) Objects.requireNonNull(row));
            return this;
        }

        public Builder addAction(Action action) {
            Objects.requireNonNull(action);
            this.mActionList.add(action);
            return this;
        }

        @RequiresCarApi(4)
        public Builder setImage(CarIcon carIcon) {
            this.mImage = (CarIcon) Objects.requireNonNull(carIcon);
            return this;
        }

        public Pane build() {
            if ((size() > 0) != this.mIsLoading) {
                return new Pane(this);
            }
            throw new IllegalStateException("The pane is set to loading but is not empty, or vice versa");
        }

        private int size() {
            return this.mRows.size();
        }
    }
}
