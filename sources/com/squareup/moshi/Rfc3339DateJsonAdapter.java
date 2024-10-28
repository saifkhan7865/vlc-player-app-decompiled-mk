package com.squareup.moshi;

import java.io.IOException;
import java.util.Date;

public final class Rfc3339DateJsonAdapter extends JsonAdapter<Date> {
    com.squareup.moshi.adapters.Rfc3339DateJsonAdapter delegate = new com.squareup.moshi.adapters.Rfc3339DateJsonAdapter();

    public Date fromJson(JsonReader jsonReader) throws IOException {
        return this.delegate.fromJson(jsonReader);
    }

    public void toJson(JsonWriter jsonWriter, Date date) throws IOException {
        this.delegate.toJson(jsonWriter, date);
    }
}
