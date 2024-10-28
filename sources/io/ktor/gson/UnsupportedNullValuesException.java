package io.ktor.gson;

import io.ktor.features.ContentTransformationException;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lio/ktor/gson/UnsupportedNullValuesException;", "Lio/ktor/features/ContentTransformationException;", "()V", "ktor-gson"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: GsonSupport.kt */
public final class UnsupportedNullValuesException extends ContentTransformationException {
    public UnsupportedNullValuesException() {
        super("Receiving null values is not supported");
    }
}
