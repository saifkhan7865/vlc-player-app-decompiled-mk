package j$.util;

import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;

public abstract class OptionalConversions {
    public static Optional convert(Optional optional) {
        if (optional == null) {
            return null;
        }
        return optional.isPresent() ? Optional.of(optional.get()) : Optional.empty();
    }

    public static OptionalDouble convert(OptionalDouble optionalDouble) {
        if (optionalDouble == null) {
            return null;
        }
        return optionalDouble.isPresent() ? OptionalDouble.of(optionalDouble.getAsDouble()) : OptionalDouble.empty();
    }

    public static OptionalInt convert(OptionalInt optionalInt) {
        if (optionalInt == null) {
            return null;
        }
        return optionalInt.isPresent() ? OptionalInt.of(optionalInt.getAsInt()) : OptionalInt.empty();
    }

    public static OptionalLong convert(OptionalLong optionalLong) {
        if (optionalLong == null) {
            return null;
        }
        return optionalLong.isPresent() ? OptionalLong.of(optionalLong.getAsLong()) : OptionalLong.empty();
    }

    public static Optional convert(Optional optional) {
        if (optional == null) {
            return null;
        }
        return optional.isPresent() ? Optional.of(optional.get()) : Optional.empty();
    }

    public static OptionalDouble convert(OptionalDouble optionalDouble) {
        if (optionalDouble == null) {
            return null;
        }
        return optionalDouble.isPresent() ? OptionalDouble.of(optionalDouble.getAsDouble()) : OptionalDouble.empty();
    }

    public static OptionalInt convert(OptionalInt optionalInt) {
        if (optionalInt == null) {
            return null;
        }
        return optionalInt.isPresent() ? OptionalInt.of(optionalInt.getAsInt()) : OptionalInt.empty();
    }

    public static OptionalLong convert(OptionalLong optionalLong) {
        if (optionalLong == null) {
            return null;
        }
        return optionalLong.isPresent() ? OptionalLong.of(optionalLong.getAsLong()) : OptionalLong.empty();
    }
}
