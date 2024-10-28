package androidx.paging.internal;

import androidx.paging.LoadStates;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a$\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\bø\u0001\u0000\u0002\u0007\n\u0005\b20\u0001¨\u0006\u0006"}, d2 = {"appendMediatorStatesIfNotNull", "", "mediatorStates", "Landroidx/paging/LoadStates;", "log", "Lkotlin/Function0;", "paging-common"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: LogUtil.kt */
public final class LogUtilKt {
    public static final String appendMediatorStatesIfNotNull(LoadStates loadStates, Function0<String> function0) {
        Intrinsics.checkNotNullParameter(function0, "log");
        String invoke = function0.invoke();
        if (loadStates != null) {
            invoke = invoke + "|   mediatorLoadStates: " + loadStates + 10;
        }
        return StringsKt.trimMargin$default(invoke + "|)", (String) null, 1, (Object) null);
    }
}
