package io.netty.handler.codec.http;

import io.netty.handler.codec.CharSequenceValueConverter;
import io.netty.util.AsciiString;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public final class ReadOnlyHttpHeaders extends HttpHeaders {
    /* access modifiers changed from: private */
    public final CharSequence[] nameValuePairs;

    public ReadOnlyHttpHeaders(boolean z, CharSequence... charSequenceArr) {
        if ((charSequenceArr.length & 1) == 0) {
            if (z) {
                validateHeaders(charSequenceArr);
            }
            this.nameValuePairs = charSequenceArr;
            return;
        }
        throw newInvalidArraySizeException();
    }

    private static IllegalArgumentException newInvalidArraySizeException() {
        return new IllegalArgumentException("nameValuePairs must be arrays of [name, value] pairs");
    }

    private static void validateHeaders(CharSequence... charSequenceArr) {
        for (int i = 0; i < charSequenceArr.length; i += 2) {
            DefaultHttpHeaders.HttpNameValidator.validateName(charSequenceArr[i]);
        }
    }

    private CharSequence get0(CharSequence charSequence) {
        int hashCode = AsciiString.hashCode(charSequence);
        int i = 0;
        while (true) {
            CharSequence[] charSequenceArr = this.nameValuePairs;
            if (i >= charSequenceArr.length) {
                return null;
            }
            CharSequence charSequence2 = charSequenceArr[i];
            if (AsciiString.hashCode(charSequence2) == hashCode && AsciiString.contentEqualsIgnoreCase(charSequence2, charSequence)) {
                return this.nameValuePairs[i + 1];
            }
            i += 2;
        }
    }

    public String get(String str) {
        CharSequence r1 = get0(str);
        if (r1 == null) {
            return null;
        }
        return r1.toString();
    }

    public Integer getInt(CharSequence charSequence) {
        CharSequence r2 = get0(charSequence);
        if (r2 == null) {
            return null;
        }
        return Integer.valueOf(CharSequenceValueConverter.INSTANCE.convertToInt(r2));
    }

    public int getInt(CharSequence charSequence, int i) {
        CharSequence r1 = get0(charSequence);
        return r1 == null ? i : CharSequenceValueConverter.INSTANCE.convertToInt(r1);
    }

    public Short getShort(CharSequence charSequence) {
        CharSequence r2 = get0(charSequence);
        if (r2 == null) {
            return null;
        }
        return Short.valueOf(CharSequenceValueConverter.INSTANCE.convertToShort(r2));
    }

    public short getShort(CharSequence charSequence, short s) {
        CharSequence r1 = get0(charSequence);
        return r1 == null ? s : CharSequenceValueConverter.INSTANCE.convertToShort(r1);
    }

    public Long getTimeMillis(CharSequence charSequence) {
        CharSequence r3 = get0(charSequence);
        if (r3 == null) {
            return null;
        }
        return Long.valueOf(CharSequenceValueConverter.INSTANCE.convertToTimeMillis(r3));
    }

    public long getTimeMillis(CharSequence charSequence, long j) {
        CharSequence r1 = get0(charSequence);
        return r1 == null ? j : CharSequenceValueConverter.INSTANCE.convertToTimeMillis(r1);
    }

    public List<String> getAll(String str) {
        if (isEmpty()) {
            return Collections.emptyList();
        }
        int hashCode = AsciiString.hashCode(str);
        ArrayList arrayList = new ArrayList(4);
        int i = 0;
        while (true) {
            CharSequence[] charSequenceArr = this.nameValuePairs;
            if (i >= charSequenceArr.length) {
                return arrayList;
            }
            CharSequence charSequence = charSequenceArr[i];
            if (AsciiString.hashCode(charSequence) == hashCode && AsciiString.contentEqualsIgnoreCase(charSequence, str)) {
                arrayList.add(this.nameValuePairs[i + 1].toString());
            }
            i += 2;
        }
    }

    public List<Map.Entry<String, String>> entries() {
        if (isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(size());
        for (int i = 0; i < this.nameValuePairs.length; i += 2) {
            arrayList.add(new AbstractMap.SimpleImmutableEntry(this.nameValuePairs[i].toString(), this.nameValuePairs[i + 1].toString()));
        }
        return arrayList;
    }

    public boolean contains(String str) {
        return get0(str) != null;
    }

    public boolean contains(String str, String str2, boolean z) {
        return containsValue(str, str2, z);
    }

    public boolean containsValue(CharSequence charSequence, CharSequence charSequence2, boolean z) {
        if (!z) {
            int i = 0;
            while (true) {
                CharSequence[] charSequenceArr = this.nameValuePairs;
                if (i >= charSequenceArr.length) {
                    break;
                } else if (AsciiString.contentEqualsIgnoreCase(charSequenceArr[i], charSequence) && AsciiString.contentEquals(this.nameValuePairs[i + 1], charSequence2)) {
                    return true;
                } else {
                    i += 2;
                }
            }
        } else {
            int i2 = 0;
            while (true) {
                CharSequence[] charSequenceArr2 = this.nameValuePairs;
                if (i2 >= charSequenceArr2.length) {
                    break;
                } else if (AsciiString.contentEqualsIgnoreCase(charSequenceArr2[i2], charSequence) && AsciiString.contentEqualsIgnoreCase(this.nameValuePairs[i2 + 1], charSequence2)) {
                    return true;
                } else {
                    i2 += 2;
                }
            }
        }
        return false;
    }

    public Iterator<String> valueStringIterator(CharSequence charSequence) {
        return new ReadOnlyStringValueIterator(charSequence);
    }

    public Iterator<CharSequence> valueCharSequenceIterator(CharSequence charSequence) {
        return new ReadOnlyValueIterator(charSequence);
    }

    public Iterator<Map.Entry<String, String>> iterator() {
        return new ReadOnlyStringIterator();
    }

    public Iterator<Map.Entry<CharSequence, CharSequence>> iteratorCharSequence() {
        return new ReadOnlyIterator();
    }

    public boolean isEmpty() {
        return this.nameValuePairs.length == 0;
    }

    public int size() {
        return this.nameValuePairs.length >>> 1;
    }

    public Set<String> names() {
        if (isEmpty()) {
            return Collections.emptySet();
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet(size());
        int i = 0;
        while (true) {
            CharSequence[] charSequenceArr = this.nameValuePairs;
            if (i >= charSequenceArr.length) {
                return linkedHashSet;
            }
            linkedHashSet.add(charSequenceArr[i].toString());
            i += 2;
        }
    }

    public HttpHeaders add(String str, Object obj) {
        throw new UnsupportedOperationException("read only");
    }

    public HttpHeaders add(String str, Iterable<?> iterable) {
        throw new UnsupportedOperationException("read only");
    }

    public HttpHeaders addInt(CharSequence charSequence, int i) {
        throw new UnsupportedOperationException("read only");
    }

    public HttpHeaders addShort(CharSequence charSequence, short s) {
        throw new UnsupportedOperationException("read only");
    }

    public HttpHeaders set(String str, Object obj) {
        throw new UnsupportedOperationException("read only");
    }

    public HttpHeaders set(String str, Iterable<?> iterable) {
        throw new UnsupportedOperationException("read only");
    }

    public HttpHeaders setInt(CharSequence charSequence, int i) {
        throw new UnsupportedOperationException("read only");
    }

    public HttpHeaders setShort(CharSequence charSequence, short s) {
        throw new UnsupportedOperationException("read only");
    }

    public HttpHeaders remove(String str) {
        throw new UnsupportedOperationException("read only");
    }

    public HttpHeaders clear() {
        throw new UnsupportedOperationException("read only");
    }

    private final class ReadOnlyIterator implements Map.Entry<CharSequence, CharSequence>, Iterator<Map.Entry<CharSequence, CharSequence>> {
        private CharSequence key;
        private int nextNameIndex;
        private CharSequence value;

        private ReadOnlyIterator() {
        }

        public boolean hasNext() {
            return this.nextNameIndex != ReadOnlyHttpHeaders.this.nameValuePairs.length;
        }

        public Map.Entry<CharSequence, CharSequence> next() {
            if (hasNext()) {
                this.key = ReadOnlyHttpHeaders.this.nameValuePairs[this.nextNameIndex];
                CharSequence[] access$200 = ReadOnlyHttpHeaders.this.nameValuePairs;
                int i = this.nextNameIndex;
                this.value = access$200[i + 1];
                this.nextNameIndex = i + 2;
                return this;
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            throw new UnsupportedOperationException("read only");
        }

        public CharSequence getKey() {
            return this.key;
        }

        public CharSequence getValue() {
            return this.value;
        }

        public CharSequence setValue(CharSequence charSequence) {
            throw new UnsupportedOperationException("read only");
        }

        public String toString() {
            return this.key.toString() + '=' + this.value.toString();
        }
    }

    private final class ReadOnlyStringIterator implements Map.Entry<String, String>, Iterator<Map.Entry<String, String>> {
        private String key;
        private int nextNameIndex;
        private String value;

        private ReadOnlyStringIterator() {
        }

        public boolean hasNext() {
            return this.nextNameIndex != ReadOnlyHttpHeaders.this.nameValuePairs.length;
        }

        public Map.Entry<String, String> next() {
            if (hasNext()) {
                this.key = ReadOnlyHttpHeaders.this.nameValuePairs[this.nextNameIndex].toString();
                this.value = ReadOnlyHttpHeaders.this.nameValuePairs[this.nextNameIndex + 1].toString();
                this.nextNameIndex += 2;
                return this;
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            throw new UnsupportedOperationException("read only");
        }

        public String getKey() {
            return this.key;
        }

        public String getValue() {
            return this.value;
        }

        public String setValue(String str) {
            throw new UnsupportedOperationException("read only");
        }

        public String toString() {
            return this.key + '=' + this.value;
        }
    }

    private final class ReadOnlyStringValueIterator implements Iterator<String> {
        private final CharSequence name;
        private final int nameHash;
        private int nextNameIndex = findNextValue();

        ReadOnlyStringValueIterator(CharSequence charSequence) {
            this.name = charSequence;
            this.nameHash = AsciiString.hashCode(charSequence);
        }

        public boolean hasNext() {
            return this.nextNameIndex != -1;
        }

        public String next() {
            if (hasNext()) {
                String charSequence = ReadOnlyHttpHeaders.this.nameValuePairs[this.nextNameIndex + 1].toString();
                this.nextNameIndex = findNextValue();
                return charSequence;
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            throw new UnsupportedOperationException("read only");
        }

        private int findNextValue() {
            for (int i = this.nextNameIndex; i < ReadOnlyHttpHeaders.this.nameValuePairs.length; i += 2) {
                CharSequence charSequence = ReadOnlyHttpHeaders.this.nameValuePairs[i];
                if (this.nameHash == AsciiString.hashCode(charSequence) && AsciiString.contentEqualsIgnoreCase(this.name, charSequence)) {
                    return i;
                }
            }
            return -1;
        }
    }

    private final class ReadOnlyValueIterator implements Iterator<CharSequence> {
        private final CharSequence name;
        private final int nameHash;
        private int nextNameIndex = findNextValue();

        ReadOnlyValueIterator(CharSequence charSequence) {
            this.name = charSequence;
            this.nameHash = AsciiString.hashCode(charSequence);
        }

        public boolean hasNext() {
            return this.nextNameIndex != -1;
        }

        public CharSequence next() {
            if (hasNext()) {
                CharSequence charSequence = ReadOnlyHttpHeaders.this.nameValuePairs[this.nextNameIndex + 1];
                this.nextNameIndex = findNextValue();
                return charSequence;
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            throw new UnsupportedOperationException("read only");
        }

        private int findNextValue() {
            for (int i = this.nextNameIndex; i < ReadOnlyHttpHeaders.this.nameValuePairs.length; i += 2) {
                CharSequence charSequence = ReadOnlyHttpHeaders.this.nameValuePairs[i];
                if (this.nameHash == AsciiString.hashCode(charSequence) && AsciiString.contentEqualsIgnoreCase(this.name, charSequence)) {
                    return i;
                }
            }
            return -1;
        }
    }
}
