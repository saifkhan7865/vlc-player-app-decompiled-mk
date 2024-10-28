package io.ktor.server.sessions;

import androidx.leanback.preference.LeanbackPreferenceDialogFragment;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\b\b\u0001\u0010\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\n¢\u0006\u0004\b\u0007\u0010\b"}, d2 = {"<anonymous>", "", "T", "", "key", "", "<anonymous parameter 1>", "invoke", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: SessionSerializerReflection.kt */
final class SessionSerializerReflection$findConstructor$filtered$1 extends Lambda implements Function2<String, String, Boolean> {
    public static final SessionSerializerReflection$findConstructor$filtered$1 INSTANCE = new SessionSerializerReflection$findConstructor$filtered$1();

    SessionSerializerReflection$findConstructor$filtered$1() {
        super(2);
    }

    public final Boolean invoke(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, LeanbackPreferenceDialogFragment.ARG_KEY);
        Intrinsics.checkNotNullParameter(str2, "<anonymous parameter 1>");
        return Boolean.valueOf(!Intrinsics.areEqual((Object) str, (Object) "$type"));
    }
}
