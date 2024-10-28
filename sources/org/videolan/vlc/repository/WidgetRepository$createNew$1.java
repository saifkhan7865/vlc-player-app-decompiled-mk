package org.videolan.vlc.repository;

import android.content.Context;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.repository.WidgetRepository", f = "WidgetRepository.kt", i = {0}, l = {71}, m = "createNew", n = {"widget"}, s = {"L$0"})
/* compiled from: WidgetRepository.kt */
final class WidgetRepository$createNew$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ WidgetRepository this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    WidgetRepository$createNew$1(WidgetRepository widgetRepository, Continuation<? super WidgetRepository$createNew$1> continuation) {
        super(continuation);
        this.this$0 = widgetRepository;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.createNew((Context) null, 0, this);
    }
}
