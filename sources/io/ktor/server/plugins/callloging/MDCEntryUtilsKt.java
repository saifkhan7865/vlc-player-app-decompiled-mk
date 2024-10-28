package io.ktor.server.plugins.callloging;

import androidx.core.app.NotificationCompat;
import io.ktor.server.application.ApplicationCall;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.slf4j.MDCContext;
import org.slf4j.MDC;

@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\u001aG\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u001e\b\u0004\u0010\u0007\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\t\u0012\u0006\u0012\u0004\u0018\u00010\n0\bHHø\u0001\u0000¢\u0006\u0002\u0010\u000b\u001a\u0012\u0010\f\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0000\u001a&\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f0\u000e*\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u0010"}, d2 = {"withMDC", "", "mdcEntries", "", "Lio/ktor/server/plugins/callloging/MDCEntry;", "call", "Lio/ktor/server/application/ApplicationCall;", "block", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "", "(Ljava/util/List;Lio/ktor/server/application/ApplicationCall;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "cleanup", "setup", "", "", "ktor-server-call-logging"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: MDCEntryUtils.kt */
public final class MDCEntryUtilsKt {
    public static final Object withMDC(List<MDCEntry> list, ApplicationCall applicationCall, Function1<? super Continuation<? super Unit>, ? extends Object> function1, Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(new MDCContext(setup(list, applicationCall)), new MDCEntryUtilsKt$withMDC$2(function1, list, (Continuation<? super MDCEntryUtilsKt$withMDC$2>) null), continuation);
        return withContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    private static final Object withMDC$$forInline(List<MDCEntry> list, ApplicationCall applicationCall, Function1<? super Continuation<? super Unit>, ? extends Object> function1, Continuation<? super Unit> continuation) {
        InlineMarker.mark(0);
        BuildersKt.withContext(new MDCContext(setup(list, applicationCall)), new MDCEntryUtilsKt$withMDC$2(function1, list, (Continuation<? super MDCEntryUtilsKt$withMDC$2>) null), continuation);
        InlineMarker.mark(1);
        return Unit.INSTANCE;
    }

    public static final Map<String, String> setup(List<MDCEntry> list, ApplicationCall applicationCall) {
        Object obj;
        Intrinsics.checkNotNullParameter(list, "<this>");
        Intrinsics.checkNotNullParameter(applicationCall, NotificationCompat.CATEGORY_CALL);
        HashMap hashMap = new HashMap();
        for (MDCEntry mDCEntry : list) {
            try {
                Result.Companion companion = Result.Companion;
                obj = Result.m1774constructorimpl(mDCEntry.getProvider().invoke(applicationCall));
            } catch (Throwable th) {
                Result.Companion companion2 = Result.Companion;
                obj = Result.m1774constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m1780isFailureimpl(obj)) {
                obj = null;
            }
            String str = (String) obj;
            if (str != null) {
                hashMap.put(mDCEntry.getName(), str);
            }
        }
        return hashMap;
    }

    public static final void cleanup(List<MDCEntry> list) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        for (MDCEntry name : list) {
            MDC.remove(name.getName());
        }
    }
}
