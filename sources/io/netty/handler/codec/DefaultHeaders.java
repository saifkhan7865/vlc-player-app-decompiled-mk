package io.netty.handler.codec;

import io.ktor.http.ContentDisposition;
import io.netty.handler.codec.Headers;
import io.netty.util.HashingStrategy;
import io.netty.util.internal.MathUtil;
import io.netty.util.internal.ObjectUtil;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public class DefaultHeaders<K, V, T extends Headers<K, V, T>> implements Headers<K, V, T> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final int HASH_CODE_SEED = -1028477387;
    /* access modifiers changed from: private */
    public final HeaderEntry<K, V>[] entries;
    private final byte hashMask;
    /* access modifiers changed from: private */
    public final HashingStrategy<K> hashingStrategy;
    /* access modifiers changed from: protected */
    public final HeaderEntry<K, V> head;
    private final NameValidator<K> nameValidator;
    int size;
    private final ValueConverter<V> valueConverter;
    private final ValueValidator<V> valueValidator;

    public interface NameValidator<K> {
        public static final NameValidator NOT_NULL = new NameValidator() {
            public void validateName(Object obj) {
                ObjectUtil.checkNotNull(obj, ContentDisposition.Parameters.Name);
            }
        };

        void validateName(K k);
    }

    public interface ValueValidator<V> {
        public static final ValueValidator<?> NO_VALIDATION = new ValueValidator<Object>() {
            public void validate(Object obj) {
            }
        };

        void validate(V v);
    }

    private T thisT() {
        return this;
    }

    public DefaultHeaders(ValueConverter<V> valueConverter2) {
        this(HashingStrategy.JAVA_HASHER, valueConverter2);
    }

    public DefaultHeaders(ValueConverter<V> valueConverter2, NameValidator<K> nameValidator2) {
        this(HashingStrategy.JAVA_HASHER, valueConverter2, nameValidator2);
    }

    public DefaultHeaders(HashingStrategy<K> hashingStrategy2, ValueConverter<V> valueConverter2) {
        this(hashingStrategy2, valueConverter2, NameValidator.NOT_NULL);
    }

    public DefaultHeaders(HashingStrategy<K> hashingStrategy2, ValueConverter<V> valueConverter2, NameValidator<K> nameValidator2) {
        this(hashingStrategy2, valueConverter2, nameValidator2, 16);
    }

    public DefaultHeaders(HashingStrategy<K> hashingStrategy2, ValueConverter<V> valueConverter2, NameValidator<K> nameValidator2, int i) {
        this(hashingStrategy2, valueConverter2, nameValidator2, i, ValueValidator.NO_VALIDATION);
    }

    public DefaultHeaders(HashingStrategy<K> hashingStrategy2, ValueConverter<V> valueConverter2, NameValidator<K> nameValidator2, int i, ValueValidator<V> valueValidator2) {
        this.valueConverter = (ValueConverter) ObjectUtil.checkNotNull(valueConverter2, "valueConverter");
        this.nameValidator = (NameValidator) ObjectUtil.checkNotNull(nameValidator2, "nameValidator");
        this.hashingStrategy = (HashingStrategy) ObjectUtil.checkNotNull(hashingStrategy2, "nameHashingStrategy");
        this.valueValidator = (ValueValidator) ObjectUtil.checkNotNull(valueValidator2, "valueValidator");
        HeaderEntry<K, V>[] headerEntryArr = new HeaderEntry[MathUtil.findNextPositivePowerOfTwo(Math.max(2, Math.min(i, 128)))];
        this.entries = headerEntryArr;
        this.hashMask = (byte) (headerEntryArr.length - 1);
        this.head = new HeaderEntry<>();
    }

    public V get(K k) {
        ObjectUtil.checkNotNull(k, ContentDisposition.Parameters.Name);
        int hashCode = this.hashingStrategy.hashCode(k);
        V v = null;
        for (HeaderEntry<K, V> headerEntry = this.entries[index(hashCode)]; headerEntry != null; headerEntry = headerEntry.next) {
            if (headerEntry.hash == hashCode && this.hashingStrategy.equals(k, headerEntry.key)) {
                v = headerEntry.value;
            }
        }
        return v;
    }

    public V get(K k, V v) {
        V v2 = get(k);
        return v2 == null ? v : v2;
    }

    public V getAndRemove(K k) {
        int hashCode = this.hashingStrategy.hashCode(k);
        return remove0(hashCode, index(hashCode), ObjectUtil.checkNotNull(k, ContentDisposition.Parameters.Name));
    }

    public V getAndRemove(K k, V v) {
        V andRemove = getAndRemove(k);
        return andRemove == null ? v : andRemove;
    }

    public List<V> getAll(K k) {
        ObjectUtil.checkNotNull(k, ContentDisposition.Parameters.Name);
        LinkedList linkedList = new LinkedList();
        int hashCode = this.hashingStrategy.hashCode(k);
        for (HeaderEntry<K, V> headerEntry = this.entries[index(hashCode)]; headerEntry != null; headerEntry = headerEntry.next) {
            if (headerEntry.hash == hashCode && this.hashingStrategy.equals(k, headerEntry.key)) {
                linkedList.addFirst(headerEntry.getValue());
            }
        }
        return linkedList;
    }

    public Iterator<V> valueIterator(K k) {
        return new ValueIterator(k);
    }

    public List<V> getAllAndRemove(K k) {
        List<V> all = getAll(k);
        remove(k);
        return all;
    }

    public boolean contains(K k) {
        return get(k) != null;
    }

    public boolean containsObject(K k, Object obj) {
        return contains(k, fromObject(k, obj));
    }

    public boolean containsBoolean(K k, boolean z) {
        return contains(k, fromBoolean(k, z));
    }

    public boolean containsByte(K k, byte b) {
        return contains(k, fromByte(k, b));
    }

    public boolean containsChar(K k, char c) {
        return contains(k, fromChar(k, c));
    }

    public boolean containsShort(K k, short s) {
        return contains(k, fromShort(k, s));
    }

    public boolean containsInt(K k, int i) {
        return contains(k, fromInt(k, i));
    }

    public boolean containsLong(K k, long j) {
        return contains(k, fromLong(k, j));
    }

    public boolean containsFloat(K k, float f) {
        return contains(k, fromFloat(k, f));
    }

    public boolean containsDouble(K k, double d) {
        return contains(k, fromDouble(k, d));
    }

    public boolean containsTimeMillis(K k, long j) {
        return contains(k, fromTimeMillis(k, j));
    }

    public boolean contains(K k, V v) {
        return contains(k, v, HashingStrategy.JAVA_HASHER);
    }

    public final boolean contains(K k, V v, HashingStrategy<? super V> hashingStrategy2) {
        ObjectUtil.checkNotNull(k, ContentDisposition.Parameters.Name);
        int hashCode = this.hashingStrategy.hashCode(k);
        for (HeaderEntry<K, V> headerEntry = this.entries[index(hashCode)]; headerEntry != null; headerEntry = headerEntry.next) {
            if (headerEntry.hash == hashCode && this.hashingStrategy.equals(k, headerEntry.key) && hashingStrategy2.equals(v, headerEntry.value)) {
                return true;
            }
        }
        return false;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        HeaderEntry<K, V> headerEntry = this.head;
        return headerEntry == headerEntry.after;
    }

    public Set<K> names() {
        if (isEmpty()) {
            return Collections.emptySet();
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet(size());
        for (HeaderEntry<K, V> headerEntry = this.head.after; headerEntry != this.head; headerEntry = headerEntry.after) {
            linkedHashSet.add(headerEntry.getKey());
        }
        return linkedHashSet;
    }

    public T add(K k, V v) {
        validateName(this.nameValidator, true, k);
        validateValue(this.valueValidator, k, v);
        ObjectUtil.checkNotNull(v, "value");
        int hashCode = this.hashingStrategy.hashCode(k);
        add0(hashCode, index(hashCode), k, v);
        return thisT();
    }

    public T add(K k, Iterable<? extends V> iterable) {
        validateName(this.nameValidator, true, k);
        int hashCode = this.hashingStrategy.hashCode(k);
        int index = index(hashCode);
        for (Object next : iterable) {
            validateValue(this.valueValidator, k, next);
            add0(hashCode, index, k, next);
        }
        return thisT();
    }

    public T add(K k, V... vArr) {
        validateName(this.nameValidator, true, k);
        int hashCode = this.hashingStrategy.hashCode(k);
        int index = index(hashCode);
        for (V v : vArr) {
            validateValue(this.valueValidator, k, v);
            add0(hashCode, index, k, v);
        }
        return thisT();
    }

    public T addObject(K k, Object obj) {
        return add(k, fromObject(k, obj));
    }

    public T addObject(K k, Iterable<?> iterable) {
        for (Object addObject : iterable) {
            addObject(k, (Object) addObject);
        }
        return thisT();
    }

    public T addObject(K k, Object... objArr) {
        for (Object addObject : objArr) {
            addObject(k, addObject);
        }
        return thisT();
    }

    public T addInt(K k, int i) {
        return add(k, fromInt(k, i));
    }

    public T addLong(K k, long j) {
        return add(k, fromLong(k, j));
    }

    public T addDouble(K k, double d) {
        return add(k, fromDouble(k, d));
    }

    public T addTimeMillis(K k, long j) {
        return add(k, fromTimeMillis(k, j));
    }

    public T addChar(K k, char c) {
        return add(k, fromChar(k, c));
    }

    public T addBoolean(K k, boolean z) {
        return add(k, fromBoolean(k, z));
    }

    public T addFloat(K k, float f) {
        return add(k, fromFloat(k, f));
    }

    public T addByte(K k, byte b) {
        return add(k, fromByte(k, b));
    }

    public T addShort(K k, short s) {
        return add(k, fromShort(k, s));
    }

    public T add(Headers<? extends K, ? extends V, ?> headers) {
        if (headers != this) {
            addImpl(headers);
            return thisT();
        }
        throw new IllegalArgumentException("can't add to itself.");
    }

    /* access modifiers changed from: protected */
    public void addImpl(Headers<? extends K, ? extends V, ?> headers) {
        if (headers instanceof DefaultHeaders) {
            DefaultHeaders defaultHeaders = (DefaultHeaders) headers;
            HeaderEntry<K, V> headerEntry = defaultHeaders.head.after;
            if (defaultHeaders.hashingStrategy == this.hashingStrategy && defaultHeaders.nameValidator == this.nameValidator) {
                while (headerEntry != defaultHeaders.head) {
                    add0(headerEntry.hash, index(headerEntry.hash), headerEntry.key, headerEntry.value);
                    headerEntry = headerEntry.after;
                }
                return;
            }
            while (headerEntry != defaultHeaders.head) {
                add(headerEntry.key, headerEntry.value);
                headerEntry = headerEntry.after;
            }
            return;
        }
        for (Map.Entry next : headers) {
            add(next.getKey(), next.getValue());
        }
    }

    public T set(K k, V v) {
        validateName(this.nameValidator, false, k);
        validateValue(this.valueValidator, k, v);
        ObjectUtil.checkNotNull(v, "value");
        int hashCode = this.hashingStrategy.hashCode(k);
        int index = index(hashCode);
        remove0(hashCode, index, k);
        add0(hashCode, index, k, v);
        return thisT();
    }

    public T set(K k, Iterable<? extends V> iterable) {
        Object next;
        validateName(this.nameValidator, false, k);
        ObjectUtil.checkNotNull(iterable, "values");
        int hashCode = this.hashingStrategy.hashCode(k);
        int index = index(hashCode);
        remove0(hashCode, index, k);
        Iterator<? extends V> it = iterable.iterator();
        while (it.hasNext() && (next = it.next()) != null) {
            validateValue(this.valueValidator, k, next);
            add0(hashCode, index, k, next);
        }
        return thisT();
    }

    public T set(K k, V... vArr) {
        validateName(this.nameValidator, false, k);
        ObjectUtil.checkNotNull(vArr, "values");
        int hashCode = this.hashingStrategy.hashCode(k);
        int index = index(hashCode);
        remove0(hashCode, index, k);
        for (V v : vArr) {
            if (v == null) {
                break;
            }
            validateValue(this.valueValidator, k, v);
            add0(hashCode, index, k, v);
        }
        return thisT();
    }

    public T setObject(K k, Object obj) {
        return set(k, ObjectUtil.checkNotNull(fromObject(k, obj), "convertedValue"));
    }

    public T setObject(K k, Iterable<?> iterable) {
        Object next;
        validateName(this.nameValidator, false, k);
        int hashCode = this.hashingStrategy.hashCode(k);
        int index = index(hashCode);
        remove0(hashCode, index, k);
        Iterator<?> it = iterable.iterator();
        while (it.hasNext() && (next = it.next()) != null) {
            Object fromObject = fromObject(k, next);
            validateValue(this.valueValidator, k, fromObject);
            add0(hashCode, index, k, fromObject);
        }
        return thisT();
    }

    public T setObject(K k, Object... objArr) {
        validateName(this.nameValidator, false, k);
        int hashCode = this.hashingStrategy.hashCode(k);
        int index = index(hashCode);
        remove0(hashCode, index, k);
        for (Object obj : objArr) {
            if (obj == null) {
                break;
            }
            Object fromObject = fromObject(k, obj);
            validateValue(this.valueValidator, k, fromObject);
            add0(hashCode, index, k, fromObject);
        }
        return thisT();
    }

    public T setInt(K k, int i) {
        return set(k, fromInt(k, i));
    }

    public T setLong(K k, long j) {
        return set(k, fromLong(k, j));
    }

    public T setDouble(K k, double d) {
        return set(k, fromDouble(k, d));
    }

    public T setTimeMillis(K k, long j) {
        return set(k, fromTimeMillis(k, j));
    }

    public T setFloat(K k, float f) {
        return set(k, fromFloat(k, f));
    }

    public T setChar(K k, char c) {
        return set(k, fromChar(k, c));
    }

    public T setBoolean(K k, boolean z) {
        return set(k, fromBoolean(k, z));
    }

    public T setByte(K k, byte b) {
        return set(k, fromByte(k, b));
    }

    public T setShort(K k, short s) {
        return set(k, fromShort(k, s));
    }

    public T set(Headers<? extends K, ? extends V, ?> headers) {
        if (headers != this) {
            clear();
            addImpl(headers);
        }
        return thisT();
    }

    public T setAll(Headers<? extends K, ? extends V, ?> headers) {
        if (headers != this) {
            for (Object remove : headers.names()) {
                remove(remove);
            }
            addImpl(headers);
        }
        return thisT();
    }

    public boolean remove(K k) {
        return getAndRemove(k) != null;
    }

    public T clear() {
        Arrays.fill(this.entries, (Object) null);
        HeaderEntry<K, V> headerEntry = this.head;
        headerEntry.after = headerEntry;
        headerEntry.before = headerEntry;
        this.size = 0;
        return thisT();
    }

    public Iterator<Map.Entry<K, V>> iterator() {
        return new HeaderIterator();
    }

    public Boolean getBoolean(K k) {
        Object obj = get(k);
        if (obj == null) {
            return null;
        }
        try {
            return Boolean.valueOf(toBoolean(k, obj));
        } catch (RuntimeException unused) {
            return null;
        }
    }

    public boolean getBoolean(K k, boolean z) {
        Boolean bool = getBoolean(k);
        return bool != null ? bool.booleanValue() : z;
    }

    public Byte getByte(K k) {
        Object obj = get(k);
        if (obj == null) {
            return null;
        }
        try {
            return Byte.valueOf(toByte(k, obj));
        } catch (RuntimeException unused) {
            return null;
        }
    }

    public byte getByte(K k, byte b) {
        Byte b2 = getByte(k);
        return b2 != null ? b2.byteValue() : b;
    }

    public Character getChar(K k) {
        Object obj = get(k);
        if (obj == null) {
            return null;
        }
        try {
            return Character.valueOf(toChar(k, obj));
        } catch (RuntimeException unused) {
            return null;
        }
    }

    public char getChar(K k, char c) {
        Character ch = getChar(k);
        return ch != null ? ch.charValue() : c;
    }

    public Short getShort(K k) {
        Object obj = get(k);
        if (obj == null) {
            return null;
        }
        try {
            return Short.valueOf(toShort(k, obj));
        } catch (RuntimeException unused) {
            return null;
        }
    }

    public short getShort(K k, short s) {
        Short sh = getShort(k);
        return sh != null ? sh.shortValue() : s;
    }

    public Integer getInt(K k) {
        Object obj = get(k);
        if (obj == null) {
            return null;
        }
        try {
            return Integer.valueOf(toInt(k, obj));
        } catch (RuntimeException unused) {
            return null;
        }
    }

    public int getInt(K k, int i) {
        Integer num = getInt(k);
        return num != null ? num.intValue() : i;
    }

    public Long getLong(K k) {
        Object obj = get(k);
        if (obj == null) {
            return null;
        }
        try {
            return Long.valueOf(toLong(k, obj));
        } catch (RuntimeException unused) {
            return null;
        }
    }

    public long getLong(K k, long j) {
        Long l = getLong(k);
        return l != null ? l.longValue() : j;
    }

    public Float getFloat(K k) {
        Object obj = get(k);
        if (obj == null) {
            return null;
        }
        try {
            return Float.valueOf(toFloat(k, obj));
        } catch (RuntimeException unused) {
            return null;
        }
    }

    public float getFloat(K k, float f) {
        Float f2 = getFloat(k);
        return f2 != null ? f2.floatValue() : f;
    }

    public Double getDouble(K k) {
        Object obj = get(k);
        if (obj == null) {
            return null;
        }
        try {
            return Double.valueOf(toDouble(k, obj));
        } catch (RuntimeException unused) {
            return null;
        }
    }

    public double getDouble(K k, double d) {
        Double d2 = getDouble(k);
        return d2 != null ? d2.doubleValue() : d;
    }

    public Long getTimeMillis(K k) {
        Object obj = get(k);
        if (obj == null) {
            return null;
        }
        try {
            return Long.valueOf(toTimeMillis(k, obj));
        } catch (RuntimeException unused) {
            return null;
        }
    }

    public long getTimeMillis(K k, long j) {
        Long timeMillis = getTimeMillis(k);
        return timeMillis != null ? timeMillis.longValue() : j;
    }

    public Boolean getBooleanAndRemove(K k) {
        Object andRemove = getAndRemove(k);
        if (andRemove == null) {
            return null;
        }
        try {
            return Boolean.valueOf(toBoolean(k, andRemove));
        } catch (RuntimeException unused) {
            return null;
        }
    }

    public boolean getBooleanAndRemove(K k, boolean z) {
        Boolean booleanAndRemove = getBooleanAndRemove(k);
        return booleanAndRemove != null ? booleanAndRemove.booleanValue() : z;
    }

    public Byte getByteAndRemove(K k) {
        Object andRemove = getAndRemove(k);
        if (andRemove == null) {
            return null;
        }
        try {
            return Byte.valueOf(toByte(k, andRemove));
        } catch (RuntimeException unused) {
            return null;
        }
    }

    public byte getByteAndRemove(K k, byte b) {
        Byte byteAndRemove = getByteAndRemove(k);
        return byteAndRemove != null ? byteAndRemove.byteValue() : b;
    }

    public Character getCharAndRemove(K k) {
        Object andRemove = getAndRemove(k);
        if (andRemove == null) {
            return null;
        }
        try {
            return Character.valueOf(toChar(k, andRemove));
        } catch (RuntimeException unused) {
            return null;
        }
    }

    public char getCharAndRemove(K k, char c) {
        Character charAndRemove = getCharAndRemove(k);
        return charAndRemove != null ? charAndRemove.charValue() : c;
    }

    public Short getShortAndRemove(K k) {
        Object andRemove = getAndRemove(k);
        if (andRemove == null) {
            return null;
        }
        try {
            return Short.valueOf(toShort(k, andRemove));
        } catch (RuntimeException unused) {
            return null;
        }
    }

    public short getShortAndRemove(K k, short s) {
        Short shortAndRemove = getShortAndRemove(k);
        return shortAndRemove != null ? shortAndRemove.shortValue() : s;
    }

    public Integer getIntAndRemove(K k) {
        Object andRemove = getAndRemove(k);
        if (andRemove == null) {
            return null;
        }
        try {
            return Integer.valueOf(toInt(k, andRemove));
        } catch (RuntimeException unused) {
            return null;
        }
    }

    public int getIntAndRemove(K k, int i) {
        Integer intAndRemove = getIntAndRemove(k);
        return intAndRemove != null ? intAndRemove.intValue() : i;
    }

    public Long getLongAndRemove(K k) {
        Object andRemove = getAndRemove(k);
        if (andRemove == null) {
            return null;
        }
        try {
            return Long.valueOf(toLong(k, andRemove));
        } catch (RuntimeException unused) {
            return null;
        }
    }

    public long getLongAndRemove(K k, long j) {
        Long longAndRemove = getLongAndRemove(k);
        return longAndRemove != null ? longAndRemove.longValue() : j;
    }

    public Float getFloatAndRemove(K k) {
        Object andRemove = getAndRemove(k);
        if (andRemove == null) {
            return null;
        }
        try {
            return Float.valueOf(toFloat(k, andRemove));
        } catch (RuntimeException unused) {
            return null;
        }
    }

    public float getFloatAndRemove(K k, float f) {
        Float floatAndRemove = getFloatAndRemove(k);
        return floatAndRemove != null ? floatAndRemove.floatValue() : f;
    }

    public Double getDoubleAndRemove(K k) {
        Object andRemove = getAndRemove(k);
        if (andRemove == null) {
            return null;
        }
        try {
            return Double.valueOf(toDouble(k, andRemove));
        } catch (RuntimeException unused) {
            return null;
        }
    }

    public double getDoubleAndRemove(K k, double d) {
        Double doubleAndRemove = getDoubleAndRemove(k);
        return doubleAndRemove != null ? doubleAndRemove.doubleValue() : d;
    }

    public Long getTimeMillisAndRemove(K k) {
        Object andRemove = getAndRemove(k);
        if (andRemove == null) {
            return null;
        }
        try {
            return Long.valueOf(toTimeMillis(k, andRemove));
        } catch (RuntimeException unused) {
            return null;
        }
    }

    public long getTimeMillisAndRemove(K k, long j) {
        Long timeMillisAndRemove = getTimeMillisAndRemove(k);
        return timeMillisAndRemove != null ? timeMillisAndRemove.longValue() : j;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Headers)) {
            return false;
        }
        return equals((Headers) obj, HashingStrategy.JAVA_HASHER);
    }

    public int hashCode() {
        return hashCode(HashingStrategy.JAVA_HASHER);
    }

    public final boolean equals(Headers<K, V, ?> headers, HashingStrategy<V> hashingStrategy2) {
        if (headers.size() != size()) {
            return false;
        }
        if (this == headers) {
            return true;
        }
        for (Object next : names()) {
            List<V> all = headers.getAll(next);
            List all2 = getAll(next);
            if (all.size() != all2.size()) {
                return false;
            }
            int i = 0;
            while (true) {
                if (i < all.size()) {
                    if (!hashingStrategy2.equals(all.get(i), all2.get(i))) {
                        return false;
                    }
                    i++;
                }
            }
        }
        return true;
    }

    public final int hashCode(HashingStrategy<V> hashingStrategy2) {
        int i = HASH_CODE_SEED;
        for (Object next : names()) {
            i = (i * 31) + this.hashingStrategy.hashCode(next);
            List all = getAll(next);
            for (int i2 = 0; i2 < all.size(); i2++) {
                i = (i * 31) + hashingStrategy2.hashCode(all.get(i2));
            }
        }
        return i;
    }

    public String toString() {
        return HeadersUtils.toString(getClass(), iterator(), size());
    }

    /* access modifiers changed from: protected */
    public void validateName(NameValidator<K> nameValidator2, boolean z, K k) {
        nameValidator2.validateName(k);
    }

    /* access modifiers changed from: protected */
    public void validateValue(ValueValidator<V> valueValidator2, K k, V v) {
        try {
            valueValidator2.validate(v);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Validation failed for header '" + k + "'", e);
        }
    }

    /* access modifiers changed from: protected */
    public HeaderEntry<K, V> newHeaderEntry(int i, K k, V v, HeaderEntry<K, V> headerEntry) {
        return new HeaderEntry(i, k, v, headerEntry, this.head);
    }

    /* access modifiers changed from: protected */
    public ValueConverter<V> valueConverter() {
        return this.valueConverter;
    }

    /* access modifiers changed from: protected */
    public NameValidator<K> nameValidator() {
        return this.nameValidator;
    }

    /* access modifiers changed from: protected */
    public ValueValidator<V> valueValidator() {
        return this.valueValidator;
    }

    /* access modifiers changed from: private */
    public int index(int i) {
        return i & this.hashMask;
    }

    private void add0(int i, int i2, K k, V v) {
        HeaderEntry<K, V>[] headerEntryArr = this.entries;
        headerEntryArr[i2] = newHeaderEntry(i, k, v, headerEntryArr[i2]);
        this.size++;
    }

    private V remove0(int i, int i2, K k) {
        HeaderEntry<K, V> headerEntry = this.entries[i2];
        V v = null;
        if (headerEntry == null) {
            return null;
        }
        for (HeaderEntry<K, V> headerEntry2 = headerEntry.next; headerEntry2 != null; headerEntry2 = headerEntry.next) {
            if (headerEntry2.hash != i || !this.hashingStrategy.equals(k, headerEntry2.key)) {
                headerEntry = headerEntry2;
            } else {
                v = headerEntry2.value;
                headerEntry.next = headerEntry2.next;
                headerEntry2.remove();
                this.size--;
            }
        }
        HeaderEntry<K, V> headerEntry3 = this.entries[i2];
        if (headerEntry3.hash == i && this.hashingStrategy.equals(k, headerEntry3.key)) {
            if (v == null) {
                v = headerEntry3.value;
            }
            this.entries[i2] = headerEntry3.next;
            headerEntry3.remove();
            this.size--;
        }
        return v;
    }

    /* access modifiers changed from: package-private */
    public HeaderEntry<K, V> remove0(HeaderEntry<K, V> headerEntry, HeaderEntry<K, V> headerEntry2) {
        int index = index(headerEntry.hash);
        HeaderEntry<K, V>[] headerEntryArr = this.entries;
        HeaderEntry<K, V> headerEntry3 = headerEntryArr[index];
        if (headerEntry3 == headerEntry) {
            headerEntryArr[index] = headerEntry.next;
            headerEntry2 = this.entries[index];
        } else if (headerEntry2 == null) {
            HeaderEntry<K, V> headerEntry4 = headerEntry3.next;
            while (headerEntry4 != null && headerEntry4 != headerEntry) {
                headerEntry3 = headerEntry4;
                headerEntry4 = headerEntry4.next;
            }
            headerEntry3.next = headerEntry.next;
            headerEntry2 = headerEntry3;
        } else {
            headerEntry2.next = headerEntry.next;
        }
        headerEntry.remove();
        this.size--;
        return headerEntry2;
    }

    private V fromObject(K k, Object obj) {
        try {
            return this.valueConverter.convertObject(ObjectUtil.checkNotNull(obj, "value"));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Failed to convert object value for header '" + k + '\'', e);
        }
    }

    private V fromBoolean(K k, boolean z) {
        try {
            return this.valueConverter.convertBoolean(z);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Failed to convert boolean value for header '" + k + '\'', e);
        }
    }

    private V fromByte(K k, byte b) {
        try {
            return this.valueConverter.convertByte(b);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Failed to convert byte value for header '" + k + '\'', e);
        }
    }

    private V fromChar(K k, char c) {
        try {
            return this.valueConverter.convertChar(c);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Failed to convert char value for header '" + k + '\'', e);
        }
    }

    private V fromShort(K k, short s) {
        try {
            return this.valueConverter.convertShort(s);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Failed to convert short value for header '" + k + '\'', e);
        }
    }

    private V fromInt(K k, int i) {
        try {
            return this.valueConverter.convertInt(i);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Failed to convert int value for header '" + k + '\'', e);
        }
    }

    private V fromLong(K k, long j) {
        try {
            return this.valueConverter.convertLong(j);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Failed to convert long value for header '" + k + '\'', e);
        }
    }

    private V fromFloat(K k, float f) {
        try {
            return this.valueConverter.convertFloat(f);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Failed to convert float value for header '" + k + '\'', e);
        }
    }

    private V fromDouble(K k, double d) {
        try {
            return this.valueConverter.convertDouble(d);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Failed to convert double value for header '" + k + '\'', e);
        }
    }

    private V fromTimeMillis(K k, long j) {
        try {
            return this.valueConverter.convertTimeMillis(j);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Failed to convert millsecond value for header '" + k + '\'', e);
        }
    }

    private boolean toBoolean(K k, V v) {
        try {
            return this.valueConverter.convertToBoolean(v);
        } catch (IllegalArgumentException unused) {
            throw new IllegalArgumentException("Failed to convert header value to boolean for header '" + k + '\'');
        }
    }

    private byte toByte(K k, V v) {
        try {
            return this.valueConverter.convertToByte(v);
        } catch (IllegalArgumentException unused) {
            throw new IllegalArgumentException("Failed to convert header value to byte for header '" + k + '\'');
        }
    }

    private char toChar(K k, V v) {
        try {
            return this.valueConverter.convertToChar(v);
        } catch (IllegalArgumentException unused) {
            throw new IllegalArgumentException("Failed to convert header value to char for header '" + k + '\'');
        }
    }

    private short toShort(K k, V v) {
        try {
            return this.valueConverter.convertToShort(v);
        } catch (IllegalArgumentException unused) {
            throw new IllegalArgumentException("Failed to convert header value to short for header '" + k + '\'');
        }
    }

    private int toInt(K k, V v) {
        try {
            return this.valueConverter.convertToInt(v);
        } catch (IllegalArgumentException unused) {
            throw new IllegalArgumentException("Failed to convert header value to int for header '" + k + '\'');
        }
    }

    private long toLong(K k, V v) {
        try {
            return this.valueConverter.convertToLong(v);
        } catch (IllegalArgumentException unused) {
            throw new IllegalArgumentException("Failed to convert header value to long for header '" + k + '\'');
        }
    }

    private float toFloat(K k, V v) {
        try {
            return this.valueConverter.convertToFloat(v);
        } catch (IllegalArgumentException unused) {
            throw new IllegalArgumentException("Failed to convert header value to float for header '" + k + '\'');
        }
    }

    private double toDouble(K k, V v) {
        try {
            return this.valueConverter.convertToDouble(v);
        } catch (IllegalArgumentException unused) {
            throw new IllegalArgumentException("Failed to convert header value to double for header '" + k + '\'');
        }
    }

    private long toTimeMillis(K k, V v) {
        try {
            return this.valueConverter.convertToTimeMillis(v);
        } catch (IllegalArgumentException unused) {
            throw new IllegalArgumentException("Failed to convert header value to millsecond for header '" + k + '\'');
        }
    }

    public DefaultHeaders<K, V, T> copy() {
        DefaultHeaders<K, V, T> defaultHeaders = new DefaultHeaders<>(this.hashingStrategy, this.valueConverter, this.nameValidator, this.entries.length);
        defaultHeaders.addImpl(this);
        return defaultHeaders;
    }

    private final class HeaderIterator implements Iterator<Map.Entry<K, V>> {
        private HeaderEntry<K, V> current;

        private HeaderIterator() {
            this.current = DefaultHeaders.this.head;
        }

        public boolean hasNext() {
            return this.current.after != DefaultHeaders.this.head;
        }

        public Map.Entry<K, V> next() {
            HeaderEntry<K, V> headerEntry = this.current.after;
            this.current = headerEntry;
            if (headerEntry != DefaultHeaders.this.head) {
                return this.current;
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            throw new UnsupportedOperationException("read only");
        }
    }

    private final class ValueIterator implements Iterator<V> {
        private final int hash;
        private final K name;
        private HeaderEntry<K, V> next;
        private HeaderEntry<K, V> previous;
        private HeaderEntry<K, V> removalPrevious;

        ValueIterator(K k) {
            this.name = ObjectUtil.checkNotNull(k, ContentDisposition.Parameters.Name);
            int hashCode = DefaultHeaders.this.hashingStrategy.hashCode(k);
            this.hash = hashCode;
            calculateNext(DefaultHeaders.this.entries[DefaultHeaders.this.index(hashCode)]);
        }

        public boolean hasNext() {
            return this.next != null;
        }

        public V next() {
            if (hasNext()) {
                HeaderEntry<K, V> headerEntry = this.previous;
                if (headerEntry != null) {
                    this.removalPrevious = headerEntry;
                }
                HeaderEntry<K, V> headerEntry2 = this.next;
                this.previous = headerEntry2;
                calculateNext(headerEntry2.next);
                return this.previous.value;
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            HeaderEntry<K, V> headerEntry = this.previous;
            if (headerEntry != null) {
                this.removalPrevious = DefaultHeaders.this.remove0(headerEntry, this.removalPrevious);
                this.previous = null;
                return;
            }
            throw new IllegalStateException();
        }

        private void calculateNext(HeaderEntry<K, V> headerEntry) {
            while (headerEntry != null) {
                if (headerEntry.hash != this.hash || !DefaultHeaders.this.hashingStrategy.equals(this.name, headerEntry.key)) {
                    headerEntry = headerEntry.next;
                } else {
                    this.next = headerEntry;
                    return;
                }
            }
            this.next = null;
        }
    }

    protected static class HeaderEntry<K, V> implements Map.Entry<K, V> {
        protected HeaderEntry<K, V> after;
        protected HeaderEntry<K, V> before;
        protected final int hash;
        protected final K key;
        protected HeaderEntry<K, V> next;
        protected V value;

        protected HeaderEntry(int i, K k) {
            this.hash = i;
            this.key = k;
        }

        HeaderEntry(int i, K k, V v, HeaderEntry<K, V> headerEntry, HeaderEntry<K, V> headerEntry2) {
            this.hash = i;
            this.key = k;
            this.value = v;
            this.next = headerEntry;
            this.after = headerEntry2;
            this.before = headerEntry2.before;
            pointNeighborsToThis();
        }

        HeaderEntry() {
            this.hash = -1;
            this.key = null;
            this.after = this;
            this.before = this;
        }

        /* access modifiers changed from: protected */
        public final void pointNeighborsToThis() {
            this.before.after = this;
            this.after.before = this;
        }

        public final HeaderEntry<K, V> before() {
            return this.before;
        }

        public final HeaderEntry<K, V> after() {
            return this.after;
        }

        /* access modifiers changed from: protected */
        public void remove() {
            HeaderEntry<K, V> headerEntry = this.before;
            headerEntry.after = this.after;
            this.after.before = headerEntry;
        }

        public final K getKey() {
            return this.key;
        }

        public final V getValue() {
            return this.value;
        }

        public final V setValue(V v) {
            ObjectUtil.checkNotNull(v, "value");
            V v2 = this.value;
            this.value = v;
            return v2;
        }

        public final String toString() {
            return this.key.toString() + '=' + this.value.toString();
        }

        /* JADX WARNING: Removed duplicated region for block: B:15:0x003e A[ORIG_RETURN, RETURN, SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean equals(java.lang.Object r4) {
            /*
                r3 = this;
                boolean r0 = r4 instanceof java.util.Map.Entry
                r1 = 0
                if (r0 != 0) goto L_0x0006
                return r1
            L_0x0006:
                java.util.Map$Entry r4 = (java.util.Map.Entry) r4
                java.lang.Object r0 = r3.getKey()
                if (r0 != 0) goto L_0x0015
                java.lang.Object r0 = r4.getKey()
                if (r0 != 0) goto L_0x003f
                goto L_0x0023
            L_0x0015:
                java.lang.Object r0 = r3.getKey()
                java.lang.Object r2 = r4.getKey()
                boolean r0 = r0.equals(r2)
                if (r0 == 0) goto L_0x003f
            L_0x0023:
                java.lang.Object r0 = r3.getValue()
                if (r0 != 0) goto L_0x0030
                java.lang.Object r4 = r4.getValue()
                if (r4 != 0) goto L_0x003f
                goto L_0x003e
            L_0x0030:
                java.lang.Object r0 = r3.getValue()
                java.lang.Object r4 = r4.getValue()
                boolean r4 = r0.equals(r4)
                if (r4 == 0) goto L_0x003f
            L_0x003e:
                r1 = 1
            L_0x003f:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.codec.DefaultHeaders.HeaderEntry.equals(java.lang.Object):boolean");
        }

        public int hashCode() {
            K k = this.key;
            int i = 0;
            int hashCode = k == null ? 0 : k.hashCode();
            V v = this.value;
            if (v != null) {
                i = v.hashCode();
            }
            return hashCode ^ i;
        }
    }
}
