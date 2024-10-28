package kotlin.reflect.jvm.internal.impl.descriptors;

import io.ktor.http.ContentDisposition;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ModuleCapability.kt */
public final class ModuleCapability<T> {
    private final String name;

    public ModuleCapability(String str) {
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        this.name = str;
    }

    public String toString() {
        return this.name;
    }
}
