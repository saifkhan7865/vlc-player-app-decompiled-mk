package org.videolan.tools;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lkotlinx/coroutines/MainCoroutineDispatcher;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: CoroutineContextProvider.kt */
final class CoroutineContextProvider$Main$2 extends Lambda implements Function0<MainCoroutineDispatcher> {
    public static final CoroutineContextProvider$Main$2 INSTANCE = new CoroutineContextProvider$Main$2();

    CoroutineContextProvider$Main$2() {
        super(0);
    }

    public final MainCoroutineDispatcher invoke() {
        return Dispatchers.getMain();
    }
}
