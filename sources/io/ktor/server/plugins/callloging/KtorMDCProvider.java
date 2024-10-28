package io.ktor.server.plugins.callloging;

import io.ktor.server.application.ApplicationCall;
import io.ktor.server.logging.MDCProvider;
import io.ktor.util.AttributeKey;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.slf4j.MDCContext;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0000\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J7\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u001c\u0010\n\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\f\u0012\u0006\u0012\u0004\u0018\u00010\r0\u000bH@ø\u0001\u0000¢\u0006\u0002\u0010\u000eR\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u0010"}, d2 = {"Lio/ktor/server/plugins/callloging/KtorMDCProvider;", "Lio/ktor/server/logging/MDCProvider;", "entries", "", "Lio/ktor/server/plugins/callloging/MDCEntry;", "(Ljava/util/List;)V", "withMDCBlock", "", "call", "Lio/ktor/server/application/ApplicationCall;", "block", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "", "(Lio/ktor/server/application/ApplicationCall;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "ktor-server-call-logging"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: MDCProvider.kt */
public final class KtorMDCProvider implements MDCProvider {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final AttributeKey<KtorMDCProvider> key = new AttributeKey<>("KtorMDCProvider");
    private final List<MDCEntry> entries;

    public KtorMDCProvider(List<MDCEntry> list) {
        Intrinsics.checkNotNullParameter(list, "entries");
        this.entries = list;
    }

    public Object withMDCBlock(ApplicationCall applicationCall, Function1<? super Continuation<? super Unit>, ? extends Object> function1, Continuation<? super Unit> continuation) {
        List<MDCEntry> list = this.entries;
        Object withContext = BuildersKt.withContext(new MDCContext(MDCEntryUtilsKt.setup(list, applicationCall)), new KtorMDCProvider$withMDCBlock$$inlined$withMDC$1(function1, list, (Continuation) null), continuation);
        if (withContext == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            return withContext;
        }
        return Unit.INSTANCE;
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lio/ktor/server/plugins/callloging/KtorMDCProvider$Companion;", "", "()V", "key", "Lio/ktor/util/AttributeKey;", "Lio/ktor/server/plugins/callloging/KtorMDCProvider;", "getKey", "()Lio/ktor/util/AttributeKey;", "ktor-server-call-logging"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: MDCProvider.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final AttributeKey<KtorMDCProvider> getKey() {
            return KtorMDCProvider.key;
        }
    }
}
