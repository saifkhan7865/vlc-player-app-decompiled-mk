package io.ktor.server.auth;

import io.ktor.server.application.ApplicationCall;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001:\u0001\u0016B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0019\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H¦@ø\u0001\u0000¢\u0006\u0002\u0010\u0015R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR'\u0010\t\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000bj\u0002`\u000e0\n¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010\u0002\u0004\n\u0002\b\u0019¨\u0006\u0017"}, d2 = {"Lio/ktor/server/auth/AuthenticationProvider;", "", "config", "Lio/ktor/server/auth/AuthenticationProvider$Config;", "(Lio/ktor/server/auth/AuthenticationProvider$Config;)V", "name", "", "getName", "()Ljava/lang/String;", "skipWhen", "", "Lkotlin/Function1;", "Lio/ktor/server/application/ApplicationCall;", "", "Lio/ktor/server/auth/ApplicationCallPredicate;", "getSkipWhen", "()Ljava/util/List;", "onAuthenticate", "", "context", "Lio/ktor/server/auth/AuthenticationContext;", "(Lio/ktor/server/auth/AuthenticationContext;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Config", "ktor-server-auth"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: AuthenticationProvider.kt */
public abstract class AuthenticationProvider {
    private final String name;
    private final List<Function1<ApplicationCall, Boolean>> skipWhen;

    public abstract Object onAuthenticate(AuthenticationContext authenticationContext, Continuation<? super Unit> continuation);

    public AuthenticationProvider(Config config) {
        Intrinsics.checkNotNullParameter(config, "config");
        this.name = config.getName();
        List<Function1<ApplicationCall, Boolean>> filterPredicates$ktor_server_auth = config.getFilterPredicates$ktor_server_auth();
        this.skipWhen = filterPredicates$ktor_server_auth == null ? CollectionsKt.emptyList() : filterPredicates$ktor_server_auth;
    }

    public final String getName() {
        return this.name;
    }

    public final List<Function1<ApplicationCall, Boolean>> getSkipWhen() {
        return this.skipWhen;
    }

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\u0011\b\u0004\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u001a\u0010\u0011\u001a\u00020\u00122\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007R2\u0010\u0005\u001a\u001a\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007j\u0002`\n\u0018\u00010\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0014"}, d2 = {"Lio/ktor/server/auth/AuthenticationProvider$Config;", "", "name", "", "(Ljava/lang/String;)V", "filterPredicates", "", "Lkotlin/Function1;", "Lio/ktor/server/application/ApplicationCall;", "", "Lio/ktor/server/auth/ApplicationCallPredicate;", "getFilterPredicates$ktor_server_auth", "()Ljava/util/List;", "setFilterPredicates$ktor_server_auth", "(Ljava/util/List;)V", "getName", "()Ljava/lang/String;", "skipWhen", "", "predicate", "ktor-server-auth"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: AuthenticationProvider.kt */
    public static class Config {
        private List<Function1<ApplicationCall, Boolean>> filterPredicates;
        private final String name;

        protected Config(String str) {
            this.name = str;
        }

        public final String getName() {
            return this.name;
        }

        public final List<Function1<ApplicationCall, Boolean>> getFilterPredicates$ktor_server_auth() {
            return this.filterPredicates;
        }

        public final void setFilterPredicates$ktor_server_auth(List<Function1<ApplicationCall, Boolean>> list) {
            this.filterPredicates = list;
        }

        public final void skipWhen(Function1<? super ApplicationCall, Boolean> function1) {
            Intrinsics.checkNotNullParameter(function1, "predicate");
            List<Function1<ApplicationCall, Boolean>> list = this.filterPredicates;
            if (list == null) {
                list = new ArrayList<>();
            }
            list.add(function1);
            this.filterPredicates = list;
        }
    }
}
