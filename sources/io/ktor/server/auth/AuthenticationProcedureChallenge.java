package io.ktor.server.auth;

import io.ktor.server.application.ApplicationCall;
import io.ktor.server.auth.AuthenticationFailedCause;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function3;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\fB\u0007¢\u0006\u0004\b\u0001\u0010\u0002J\r\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0002J\u000f\u0010\u0006\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\u0006\u0010\u0007R?\u0010\u000f\u001a*\u0012&\u0012$\b\u0001\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u000b\u0012\u0006\u0012\u0004\u0018\u00010\f0\t0\b8@X\u0004ø\u0001\u0000¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR$\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00108F@BX\u000e¢\u0006\f\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R?\u0010\u0018\u001a*\u0012&\u0012$\b\u0001\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u000b\u0012\u0006\u0012\u0004\u0018\u00010\f0\t0\b8@X\u0004ø\u0001\u0000¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u000eRQ\u0010\u001c\u001a6\u00122\u00120\u0012\u0004\u0012\u00020\u001b\u0012&\u0012$\b\u0001\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u000b\u0012\u0006\u0012\u0004\u0018\u00010\f0\t0\u001a0\u00198\u0000X\u0004ø\u0001\u0000¢\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u000e\u0002\u0004\n\u0002\b\u0019¨\u0006\u001f"}, d2 = {"Lio/ktor/server/auth/AuthenticationProcedureChallenge;", "<init>", "()V", "", "complete", "", "toString", "()Ljava/lang/String;", "", "Lkotlin/Function3;", "Lio/ktor/server/application/ApplicationCall;", "Lkotlin/coroutines/Continuation;", "", "getChallenges$ktor_server_auth", "()Ljava/util/List;", "challenges", "", "value", "getCompleted", "()Z", "setCompleted", "(Z)V", "completed", "getErrorChallenges$ktor_server_auth", "errorChallenges", "", "Lkotlin/Pair;", "Lio/ktor/server/auth/AuthenticationFailedCause;", "register", "Ljava/util/List;", "getRegister$ktor_server_auth", "ktor-server-auth"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: AuthenticationProcedureChallenge.kt */
public final class AuthenticationProcedureChallenge {
    private volatile /* synthetic */ int _completed = 0;
    private final List<Pair<AuthenticationFailedCause, Function3<AuthenticationProcedureChallenge, ApplicationCall, Continuation<? super Unit>, Object>>> register = new ArrayList();

    public final List<Pair<AuthenticationFailedCause, Function3<AuthenticationProcedureChallenge, ApplicationCall, Continuation<? super Unit>, Object>>> getRegister$ktor_server_auth() {
        return this.register;
    }

    public final List<Function3<AuthenticationProcedureChallenge, ApplicationCall, Continuation<? super Unit>, Object>> getChallenges$ktor_server_auth() {
        Collection arrayList = new ArrayList();
        for (Object next : this.register) {
            if (!(((Pair) next).getFirst() instanceof AuthenticationFailedCause.Error)) {
                arrayList.add(next);
            }
        }
        Iterable<Pair> sortedWith = CollectionsKt.sortedWith((List) arrayList, new AuthenticationProcedureChallenge$special$$inlined$sortedBy$1());
        Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(sortedWith, 10));
        for (Pair second : sortedWith) {
            arrayList2.add((Function3) second.getSecond());
        }
        return (List) arrayList2;
    }

    public final List<Function3<AuthenticationProcedureChallenge, ApplicationCall, Continuation<? super Unit>, Object>> getErrorChallenges$ktor_server_auth() {
        Collection arrayList = new ArrayList();
        for (Object next : this.register) {
            if (((Pair) next).getFirst() instanceof AuthenticationFailedCause.Error) {
                arrayList.add(next);
            }
        }
        Iterable<Pair> iterable = (List) arrayList;
        Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (Pair second : iterable) {
            arrayList2.add((Function3) second.getSecond());
        }
        return (List) arrayList2;
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [boolean, int] */
    public final boolean getCompleted() {
        return this._completed;
    }

    private final void setCompleted(boolean z) {
        this._completed = z ? 1 : 0;
    }

    public final void complete() {
        setCompleted(true);
    }

    public String toString() {
        return "AuthenticationProcedureChallenge";
    }
}
