package androidx.car.app.hardware.info;

import androidx.car.app.annotations.RequiresCarApi;
import androidx.car.app.hardware.common.CarValue;
import j$.util.Objects;

@RequiresCarApi(3)
public final class Model {
    private final CarValue<String> mManufacturer;
    private final CarValue<String> mName;
    private final CarValue<Integer> mYear;

    public CarValue<String> getName() {
        return (CarValue) Objects.requireNonNull(this.mName);
    }

    public CarValue<Integer> getYear() {
        return (CarValue) Objects.requireNonNull(this.mYear);
    }

    public CarValue<String> getManufacturer() {
        return (CarValue) Objects.requireNonNull(this.mManufacturer);
    }

    public String toString() {
        return "[ name: " + this.mName + ", year: " + this.mYear + ", manufacturer: " + this.mManufacturer + "]";
    }

    public int hashCode() {
        return Objects.hash(this.mName, this.mYear, this.mManufacturer);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Model)) {
            return false;
        }
        Model model = (Model) obj;
        if (!Objects.equals(this.mName, model.mName) || !Objects.equals(this.mYear, model.mYear) || !Objects.equals(this.mManufacturer, model.mManufacturer)) {
            return false;
        }
        return true;
    }

    Model(Builder builder) {
        this.mName = (CarValue) Objects.requireNonNull(builder.mName);
        this.mManufacturer = (CarValue) Objects.requireNonNull(builder.mManufacturer);
        this.mYear = (CarValue) Objects.requireNonNull(builder.mYear);
    }

    private Model() {
        this.mName = CarValue.UNKNOWN_STRING;
        this.mManufacturer = CarValue.UNKNOWN_STRING;
        this.mYear = CarValue.UNKNOWN_INTEGER;
    }

    public static final class Builder {
        CarValue<String> mManufacturer = CarValue.UNKNOWN_STRING;
        CarValue<String> mName = CarValue.UNKNOWN_STRING;
        CarValue<Integer> mYear = CarValue.UNKNOWN_INTEGER;

        /* JADX WARNING: type inference failed for: r1v0, types: [androidx.car.app.hardware.common.CarValue<java.lang.String>, java.lang.Object] */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public androidx.car.app.hardware.info.Model.Builder setName(androidx.car.app.hardware.common.CarValue<java.lang.String> r1) {
            /*
                r0 = this;
                java.lang.Object r1 = j$.util.Objects.requireNonNull(r1)
                androidx.car.app.hardware.common.CarValue r1 = (androidx.car.app.hardware.common.CarValue) r1
                r0.mName = r1
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.car.app.hardware.info.Model.Builder.setName(androidx.car.app.hardware.common.CarValue):androidx.car.app.hardware.info.Model$Builder");
        }

        /* JADX WARNING: type inference failed for: r1v0, types: [java.lang.Object, androidx.car.app.hardware.common.CarValue<java.lang.Integer>] */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public androidx.car.app.hardware.info.Model.Builder setYear(androidx.car.app.hardware.common.CarValue<java.lang.Integer> r1) {
            /*
                r0 = this;
                java.lang.Object r1 = j$.util.Objects.requireNonNull(r1)
                androidx.car.app.hardware.common.CarValue r1 = (androidx.car.app.hardware.common.CarValue) r1
                r0.mYear = r1
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.car.app.hardware.info.Model.Builder.setYear(androidx.car.app.hardware.common.CarValue):androidx.car.app.hardware.info.Model$Builder");
        }

        /* JADX WARNING: type inference failed for: r1v0, types: [androidx.car.app.hardware.common.CarValue<java.lang.String>, java.lang.Object] */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public androidx.car.app.hardware.info.Model.Builder setManufacturer(androidx.car.app.hardware.common.CarValue<java.lang.String> r1) {
            /*
                r0 = this;
                java.lang.Object r1 = j$.util.Objects.requireNonNull(r1)
                androidx.car.app.hardware.common.CarValue r1 = (androidx.car.app.hardware.common.CarValue) r1
                r0.mManufacturer = r1
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.car.app.hardware.info.Model.Builder.setManufacturer(androidx.car.app.hardware.common.CarValue):androidx.car.app.hardware.info.Model$Builder");
        }

        public Model build() {
            return new Model(this);
        }
    }
}
