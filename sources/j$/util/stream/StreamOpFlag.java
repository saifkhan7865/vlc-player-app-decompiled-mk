package j$.util.stream;

import j$.util.Map;
import j$.util.Spliterator;
import java.util.EnumMap;
import java.util.Map;

enum StreamOpFlag {
    DISTINCT(0, r2.setAndClear(r4)),
    SORTED(1, set(r1).set(r3).setAndClear(r4)),
    ORDERED(2, r7.clear(r10)),
    SIZED(3, set(r1).set(r3).clear(r4)),
    SHORT_CIRCUIT(12, set(r4).set(r9));
    
    private static final int FLAG_MASK = 0;
    private static final int FLAG_MASK_IS = 0;
    private static final int FLAG_MASK_NOT = 0;
    static final int INITIAL_OPS_VALUE = 0;
    static final int IS_DISTINCT = 0;
    static final int IS_ORDERED = 0;
    static final int IS_SHORT_CIRCUIT = 0;
    static final int IS_SIZED = 0;
    static final int IS_SORTED = 0;
    static final int NOT_DISTINCT = 0;
    static final int NOT_ORDERED = 0;
    static final int NOT_SIZED = 0;
    static final int NOT_SORTED = 0;
    static final int OP_MASK = 0;
    static final int SPLITERATOR_CHARACTERISTICS_MASK = 0;
    static final int STREAM_MASK = 0;
    static final int TERMINAL_OP_MASK = 0;
    static final int UPSTREAM_TERMINAL_OP_MASK = 0;
    private final int bitPosition;
    private final int clear;
    private final Map maskTable;
    private final int preserve;
    private final int set;

    private static class MaskBuilder {
        final Map map;

        MaskBuilder(Map map2) {
            this.map = map2;
        }

        /* access modifiers changed from: package-private */
        public Map build() {
            for (Type putIfAbsent : Type.values()) {
                Map.EL.putIfAbsent(this.map, putIfAbsent, 0);
            }
            return this.map;
        }

        /* access modifiers changed from: package-private */
        public MaskBuilder clear(Type type) {
            return mask(type, 2);
        }

        /* access modifiers changed from: package-private */
        public MaskBuilder mask(Type type, Integer num) {
            this.map.put(type, num);
            return this;
        }

        /* access modifiers changed from: package-private */
        public MaskBuilder set(Type type) {
            return mask(type, 1);
        }

        /* access modifiers changed from: package-private */
        public MaskBuilder setAndClear(Type type) {
            return mask(type, 3);
        }
    }

    enum Type {
        SPLITERATOR,
        STREAM,
        OP,
        TERMINAL_OP,
        UPSTREAM_TERMINAL_OP
    }

    static {
        StreamOpFlag streamOpFlag;
        Type type;
        Type type2;
        Type type3;
        StreamOpFlag streamOpFlag2;
        StreamOpFlag streamOpFlag3;
        Type type4;
        Type type5;
        StreamOpFlag streamOpFlag4;
        StreamOpFlag streamOpFlag5;
        SPLITERATOR_CHARACTERISTICS_MASK = createMask(type);
        int createMask = createMask(type2);
        STREAM_MASK = createMask;
        OP_MASK = createMask(type3);
        TERMINAL_OP_MASK = createMask(type4);
        UPSTREAM_TERMINAL_OP_MASK = createMask(type5);
        FLAG_MASK = createFlagMask();
        FLAG_MASK_IS = createMask;
        int i = createMask << 1;
        FLAG_MASK_NOT = i;
        INITIAL_OPS_VALUE = createMask | i;
        IS_DISTINCT = streamOpFlag.set;
        NOT_DISTINCT = streamOpFlag.clear;
        IS_SORTED = streamOpFlag2.set;
        NOT_SORTED = streamOpFlag2.clear;
        IS_ORDERED = streamOpFlag3.set;
        NOT_ORDERED = streamOpFlag3.clear;
        IS_SIZED = streamOpFlag4.set;
        NOT_SIZED = streamOpFlag4.clear;
        IS_SHORT_CIRCUIT = streamOpFlag5.set;
    }

    private StreamOpFlag(int i, MaskBuilder maskBuilder) {
        this.maskTable = maskBuilder.build();
        int i2 = i * 2;
        this.bitPosition = i2;
        this.set = 1 << i2;
        this.clear = 2 << i2;
        this.preserve = 3 << i2;
    }

    static int combineOpFlags(int i, int i2) {
        return i | (i2 & getMask(i));
    }

    private static int createFlagMask() {
        int i = 0;
        for (StreamOpFlag streamOpFlag : values()) {
            i |= streamOpFlag.preserve;
        }
        return i;
    }

    private static int createMask(Type type) {
        int i = 0;
        for (StreamOpFlag streamOpFlag : values()) {
            i |= ((Integer) streamOpFlag.maskTable.get(type)).intValue() << streamOpFlag.bitPosition;
        }
        return i;
    }

    static int fromCharacteristics(int i) {
        return i & SPLITERATOR_CHARACTERISTICS_MASK;
    }

    static int fromCharacteristics(Spliterator spliterator) {
        int characteristics = spliterator.characteristics();
        return ((characteristics & 4) == 0 || spliterator.getComparator() == null) ? SPLITERATOR_CHARACTERISTICS_MASK & characteristics : SPLITERATOR_CHARACTERISTICS_MASK & characteristics & -5;
    }

    private static int getMask(int i) {
        if (i == 0) {
            return FLAG_MASK;
        }
        return (((i & FLAG_MASK_NOT) >> 1) | (((FLAG_MASK_IS & i) << 1) | i)) ^ -1;
    }

    private static MaskBuilder set(Type type) {
        return new MaskBuilder(new EnumMap(Type.class)).set(type);
    }

    static int toCharacteristics(int i) {
        return i & SPLITERATOR_CHARACTERISTICS_MASK;
    }

    static int toStreamFlags(int i) {
        return i & ((i ^ -1) >> 1) & FLAG_MASK_IS;
    }

    /* access modifiers changed from: package-private */
    public boolean isKnown(int i) {
        return (i & this.preserve) == this.set;
    }

    /* access modifiers changed from: package-private */
    public boolean isPreserved(int i) {
        int i2 = this.preserve;
        return (i & i2) == i2;
    }
}
