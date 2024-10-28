package org.videolan.vlc.media;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.ExecutorCoroutineDispatcher;
import kotlinx.coroutines.ThreadPoolDispatcherKt;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: PlayerController.kt */
final class PlayerController$playerContext$2 extends Lambda implements Function0<ExecutorCoroutineDispatcher> {
    public static final PlayerController$playerContext$2 INSTANCE = new PlayerController$playerContext$2();

    PlayerController$playerContext$2() {
        super(0);
    }

    public final ExecutorCoroutineDispatcher invoke() {
        return ThreadPoolDispatcherKt.newSingleThreadContext("vlc-player");
    }
}
