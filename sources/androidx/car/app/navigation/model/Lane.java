package androidx.car.app.navigation.model;

import androidx.car.app.utils.CollectionUtils;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Lane {
    private final List<LaneDirection> mDirections;

    public List<LaneDirection> getDirections() {
        return CollectionUtils.emptyIfNull(this.mDirections);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[direction count: ");
        List<LaneDirection> list = this.mDirections;
        sb.append(list != null ? list.size() : 0);
        sb.append("]");
        return sb.toString();
    }

    public int hashCode() {
        return Objects.hashCode(this.mDirections);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Lane)) {
            return false;
        }
        return Objects.equals(this.mDirections, ((Lane) obj).mDirections);
    }

    Lane(List<LaneDirection> list) {
        this.mDirections = CollectionUtils.unmodifiableCopy(list);
    }

    private Lane() {
        this.mDirections = Collections.emptyList();
    }

    public static final class Builder {
        private final List<LaneDirection> mDirections = new ArrayList();

        public Builder addDirection(LaneDirection laneDirection) {
            this.mDirections.add((LaneDirection) Objects.requireNonNull(laneDirection));
            return this;
        }

        public Lane build() {
            return new Lane(this.mDirections);
        }
    }
}
