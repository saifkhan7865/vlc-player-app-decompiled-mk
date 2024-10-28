package com.squareup.moshi.adapters;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import javax.annotation.CheckReturnValue;

public final class PolymorphicJsonAdapterFactory<T> implements JsonAdapter.Factory {
    final Class<T> baseType;
    final String labelKey;
    final List<String> labels;
    final List<Type> subtypes;

    PolymorphicJsonAdapterFactory(Class<T> cls, String str, List<String> list, List<Type> list2) {
        this.baseType = cls;
        this.labelKey = str;
        this.labels = list;
        this.subtypes = list2;
    }

    @CheckReturnValue
    public static <T> PolymorphicJsonAdapterFactory<T> of(Class<T> cls, String str) {
        if (cls == null) {
            throw new NullPointerException("baseType == null");
        } else if (str == null) {
            throw new NullPointerException("labelKey == null");
        } else if (cls != Object.class) {
            return new PolymorphicJsonAdapterFactory<>(cls, str, Collections.emptyList(), Collections.emptyList());
        } else {
            throw new IllegalArgumentException("The base type must not be Object. Consider using a marker interface.");
        }
    }

    public PolymorphicJsonAdapterFactory<T> withSubtype(Class<? extends T> cls, String str) {
        if (cls == null) {
            throw new NullPointerException("subtype == null");
        } else if (str == null) {
            throw new NullPointerException("label == null");
        } else if (this.labels.contains(str) || this.subtypes.contains(cls)) {
            throw new IllegalArgumentException("Subtypes and labels must be unique.");
        } else {
            ArrayList arrayList = new ArrayList(this.labels);
            arrayList.add(str);
            ArrayList arrayList2 = new ArrayList(this.subtypes);
            arrayList2.add(cls);
            return new PolymorphicJsonAdapterFactory<>(this.baseType, this.labelKey, arrayList, arrayList2);
        }
    }

    public JsonAdapter<?> create(Type type, Set<? extends Annotation> set, Moshi moshi) {
        if (Types.getRawType(type) != this.baseType || !set.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList(this.subtypes.size());
        int size = this.subtypes.size();
        for (int i = 0; i < size; i++) {
            arrayList.add(moshi.adapter(this.subtypes.get(i)));
        }
        return new PolymorphicJsonAdapter(this.labelKey, this.labels, this.subtypes, arrayList, moshi.adapter(Object.class)).nullSafe();
    }

    static final class PolymorphicJsonAdapter extends JsonAdapter<Object> {
        final List<JsonAdapter<Object>> jsonAdapters;
        final String labelKey;
        final JsonReader.Options labelKeyOptions;
        final JsonReader.Options labelOptions;
        final List<String> labels;
        final JsonAdapter<Object> objectJsonAdapter;
        final List<Type> subtypes;

        PolymorphicJsonAdapter(String str, List<String> list, List<Type> list2, List<JsonAdapter<Object>> list3, JsonAdapter<Object> jsonAdapter) {
            this.labelKey = str;
            this.labels = list;
            this.subtypes = list2;
            this.jsonAdapters = list3;
            this.objectJsonAdapter = jsonAdapter;
            this.labelKeyOptions = JsonReader.Options.of(str);
            this.labelOptions = JsonReader.Options.of((String[]) list.toArray(new String[0]));
        }

        public Object fromJson(JsonReader jsonReader) throws IOException {
            return this.jsonAdapters.get(labelIndex(jsonReader.peekJson())).fromJson(jsonReader);
        }

        private int labelIndex(JsonReader jsonReader) throws IOException {
            jsonReader.beginObject();
            while (jsonReader.hasNext()) {
                if (jsonReader.selectName(this.labelKeyOptions) == -1) {
                    jsonReader.skipName();
                    jsonReader.skipValue();
                } else {
                    int selectString = jsonReader.selectString(this.labelOptions);
                    if (selectString != -1) {
                        jsonReader.close();
                        return selectString;
                    }
                    throw new JsonDataException("Expected one of " + this.labels + " for key '" + this.labelKey + "' but found '" + jsonReader.nextString() + "'. Register a subtype for this label.");
                }
            }
            throw new JsonDataException("Missing label for " + this.labelKey);
        }

        public void toJson(JsonWriter jsonWriter, Object obj) throws IOException {
            int indexOf = this.subtypes.indexOf(obj.getClass());
            if (indexOf != -1) {
                jsonWriter.beginObject();
                jsonWriter.name(this.labelKey).value(this.labels.get(indexOf));
                int beginFlatten = jsonWriter.beginFlatten();
                this.jsonAdapters.get(indexOf).toJson(jsonWriter, obj);
                jsonWriter.endFlatten(beginFlatten);
                jsonWriter.endObject();
                return;
            }
            throw new IllegalArgumentException("Expected one of " + this.subtypes + " but found " + obj + ", a " + obj.getClass() + ". Register this subtype.");
        }

        public String toString() {
            return "PolymorphicJsonAdapter(" + this.labelKey + ")";
        }
    }
}
