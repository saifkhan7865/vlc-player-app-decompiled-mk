package com.squareup.moshi;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import okio.BufferedSource;

final class JsonValueWriter extends JsonWriter {
    @Nullable
    private String deferredName;
    Object[] stack = new Object[32];

    JsonValueWriter() {
        pushScope(6);
    }

    public Object root() {
        int i = this.stackSize;
        if (i <= 1 && (i != 1 || this.scopes[i - 1] == 7)) {
            return this.stack[0];
        }
        throw new IllegalStateException("Incomplete document");
    }

    public JsonWriter beginArray() throws IOException {
        if (this.promoteValueToName) {
            throw new IllegalStateException("Array cannot be used as a map key in JSON at path " + getPath());
        } else if (this.stackSize == this.flattenStackSize && this.scopes[this.stackSize - 1] == 1) {
            this.flattenStackSize ^= -1;
            return this;
        } else {
            checkStack();
            ArrayList arrayList = new ArrayList();
            add(arrayList);
            this.stack[this.stackSize] = arrayList;
            this.pathIndices[this.stackSize] = 0;
            pushScope(1);
            return this;
        }
    }

    public JsonWriter endArray() throws IOException {
        if (peekScope() != 1) {
            throw new IllegalStateException("Nesting problem.");
        } else if (this.stackSize == (this.flattenStackSize ^ -1)) {
            this.flattenStackSize ^= -1;
            return this;
        } else {
            this.stackSize--;
            this.stack[this.stackSize] = null;
            int[] iArr = this.pathIndices;
            int i = this.stackSize - 1;
            iArr[i] = iArr[i] + 1;
            return this;
        }
    }

    public JsonWriter beginObject() throws IOException {
        if (this.promoteValueToName) {
            throw new IllegalStateException("Object cannot be used as a map key in JSON at path " + getPath());
        } else if (this.stackSize == this.flattenStackSize && this.scopes[this.stackSize - 1] == 3) {
            this.flattenStackSize ^= -1;
            return this;
        } else {
            checkStack();
            LinkedHashTreeMap linkedHashTreeMap = new LinkedHashTreeMap();
            add(linkedHashTreeMap);
            this.stack[this.stackSize] = linkedHashTreeMap;
            pushScope(3);
            return this;
        }
    }

    public JsonWriter endObject() throws IOException {
        if (peekScope() != 3) {
            throw new IllegalStateException("Nesting problem.");
        } else if (this.deferredName != null) {
            throw new IllegalStateException("Dangling name: " + this.deferredName);
        } else if (this.stackSize == (this.flattenStackSize ^ -1)) {
            this.flattenStackSize ^= -1;
            return this;
        } else {
            this.promoteValueToName = false;
            this.stackSize--;
            this.stack[this.stackSize] = null;
            this.pathNames[this.stackSize] = null;
            int[] iArr = this.pathIndices;
            int i = this.stackSize - 1;
            iArr[i] = iArr[i] + 1;
            return this;
        }
    }

    public JsonWriter name(String str) throws IOException {
        if (str == null) {
            throw new NullPointerException("name == null");
        } else if (this.stackSize == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        } else if (peekScope() == 3 && this.deferredName == null) {
            this.deferredName = str;
            this.pathNames[this.stackSize - 1] = str;
            this.promoteValueToName = false;
            return this;
        } else {
            throw new IllegalStateException("Nesting problem.");
        }
    }

    public JsonWriter value(@Nullable String str) throws IOException {
        if (this.promoteValueToName) {
            return name(str);
        }
        add(str);
        int[] iArr = this.pathIndices;
        int i = this.stackSize - 1;
        iArr[i] = iArr[i] + 1;
        return this;
    }

    public JsonWriter nullValue() throws IOException {
        if (!this.promoteValueToName) {
            add((Object) null);
            int[] iArr = this.pathIndices;
            int i = this.stackSize - 1;
            iArr[i] = iArr[i] + 1;
            return this;
        }
        throw new IllegalStateException("null cannot be used as a map key in JSON at path " + getPath());
    }

    public JsonWriter value(boolean z) throws IOException {
        if (!this.promoteValueToName) {
            add(Boolean.valueOf(z));
            int[] iArr = this.pathIndices;
            int i = this.stackSize - 1;
            iArr[i] = iArr[i] + 1;
            return this;
        }
        throw new IllegalStateException("Boolean cannot be used as a map key in JSON at path " + getPath());
    }

    public JsonWriter value(@Nullable Boolean bool) throws IOException {
        if (!this.promoteValueToName) {
            add(bool);
            int[] iArr = this.pathIndices;
            int i = this.stackSize - 1;
            iArr[i] = iArr[i] + 1;
            return this;
        }
        throw new IllegalStateException("Boolean cannot be used as a map key in JSON at path " + getPath());
    }

    public JsonWriter value(double d) throws IOException {
        if (!this.lenient && (Double.isNaN(d) || d == Double.NEGATIVE_INFINITY || d == Double.POSITIVE_INFINITY)) {
            throw new IllegalArgumentException("Numeric values must be finite, but was " + d);
        } else if (this.promoteValueToName) {
            return name(Double.toString(d));
        } else {
            add(Double.valueOf(d));
            int[] iArr = this.pathIndices;
            int i = this.stackSize - 1;
            iArr[i] = iArr[i] + 1;
            return this;
        }
    }

    public JsonWriter value(long j) throws IOException {
        if (this.promoteValueToName) {
            return name(Long.toString(j));
        }
        add(Long.valueOf(j));
        int[] iArr = this.pathIndices;
        int i = this.stackSize - 1;
        iArr[i] = iArr[i] + 1;
        return this;
    }

    public JsonWriter value(@Nullable Number number) throws IOException {
        BigDecimal bigDecimal;
        if ((number instanceof Byte) || (number instanceof Short) || (number instanceof Integer) || (number instanceof Long)) {
            return value(number.longValue());
        }
        if ((number instanceof Float) || (number instanceof Double)) {
            return value(number.doubleValue());
        }
        if (number == null) {
            return nullValue();
        }
        if (number instanceof BigDecimal) {
            bigDecimal = (BigDecimal) number;
        } else {
            bigDecimal = new BigDecimal(number.toString());
        }
        if (this.promoteValueToName) {
            return name(bigDecimal.toString());
        }
        add(bigDecimal);
        int[] iArr = this.pathIndices;
        int i = this.stackSize - 1;
        iArr[i] = iArr[i] + 1;
        return this;
    }

    /* JADX INFO: finally extract failed */
    public JsonWriter value(BufferedSource bufferedSource) throws IOException {
        if (!this.promoteValueToName) {
            Object readJsonValue = JsonReader.of(bufferedSource).readJsonValue();
            boolean z = this.serializeNulls;
            this.serializeNulls = true;
            try {
                add(readJsonValue);
                this.serializeNulls = z;
                int[] iArr = this.pathIndices;
                int i = this.stackSize - 1;
                iArr[i] = iArr[i] + 1;
                return this;
            } catch (Throwable th) {
                this.serializeNulls = z;
                throw th;
            }
        } else {
            throw new IllegalStateException("BufferedSource cannot be used as a map key in JSON at path " + getPath());
        }
    }

    public void close() throws IOException {
        int i = this.stackSize;
        if (i > 1 || (i == 1 && this.scopes[i - 1] != 7)) {
            throw new IOException("Incomplete document");
        }
        this.stackSize = 0;
    }

    public void flush() throws IOException {
        if (this.stackSize == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
    }

    private JsonValueWriter add(@Nullable Object obj) {
        Object put;
        int peekScope = peekScope();
        if (this.stackSize == 1) {
            if (peekScope == 6) {
                this.scopes[this.stackSize - 1] = 7;
                this.stack[this.stackSize - 1] = obj;
            } else {
                throw new IllegalStateException("JSON must have only one top-level value.");
            }
        } else if (peekScope != 3 || this.deferredName == null) {
            if (peekScope == 1) {
                ((List) this.stack[this.stackSize - 1]).add(obj);
            } else {
                throw new IllegalStateException("Nesting problem.");
            }
        } else if ((obj != null || this.serializeNulls) && (put = ((Map) this.stack[this.stackSize - 1]).put(this.deferredName, obj)) != null) {
            throw new IllegalArgumentException("Map key '" + this.deferredName + "' has multiple values at path " + getPath() + ": " + put + " and " + obj);
        } else {
            this.deferredName = null;
        }
        return this;
    }
}
