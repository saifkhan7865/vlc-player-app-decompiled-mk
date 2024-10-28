package j$.util;

import java.util.NoSuchElementException;

public final class OptionalInt {
    private static final OptionalInt EMPTY = new OptionalInt();
    private final boolean isPresent;
    private final int value;

    private OptionalInt() {
        this.isPresent = false;
        this.value = 0;
    }

    private OptionalInt(int i) {
        this.isPresent = true;
        this.value = i;
    }

    public static OptionalInt empty() {
        return EMPTY;
    }

    public static OptionalInt of(int i) {
        return new OptionalInt(i);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof OptionalInt)) {
            return false;
        }
        OptionalInt optionalInt = (OptionalInt) obj;
        boolean z = this.isPresent;
        if (!z || !optionalInt.isPresent) {
            if (z == optionalInt.isPresent) {
                return true;
            }
        } else if (this.value == optionalInt.value) {
            return true;
        }
        return false;
    }

    public int getAsInt() {
        if (this.isPresent) {
            return this.value;
        }
        throw new NoSuchElementException("No value present");
    }

    public int hashCode() {
        if (this.isPresent) {
            return this.value;
        }
        return 0;
    }

    public boolean isPresent() {
        return this.isPresent;
    }

    public String toString() {
        if (!this.isPresent) {
            return "OptionalInt.empty";
        }
        return String.format("OptionalInt[%s]", new Object[]{Integer.valueOf(this.value)});
    }
}
