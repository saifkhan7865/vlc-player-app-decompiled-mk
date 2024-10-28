package io.ktor.server.auth;

import androidx.core.app.NotificationCompat;
import androidx.leanback.preference.LeanbackPreferenceDialogFragment;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.auth.AuthenticationFailedCause;
import io.ktor.util.AttributeKey;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 72\u00020\u0001:\u00017B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004JH\u0010\u0017\u001a\u00020(2\u0006\u0010)\u001a\u00020\u00012\u0006\u0010*\u001a\u00020\u00072(\u0010+\u001a$\b\u0001\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020(0-\u0012\u0006\u0012\u0004\u0018\u00010\u00010,ø\u0001\u0000¢\u0006\u0002\u0010.J\u0016\u0010/\u001a\u00020(2\u0006\u0010)\u001a\u00020\u00012\u0006\u0010*\u001a\u00020\u0007J\u000e\u0010\"\u001a\u00020(2\u0006\u0010\"\u001a\u00020!J(\u0010\"\u001a\u0004\u0018\u0001H0\"\n\b\u0000\u00100\u0018\u0001*\u00020!2\n\b\u0002\u00101\u001a\u0004\u0018\u000102H\b¢\u0006\u0002\u00103J\u001a\u0010\"\u001a\u00020(2\n\b\u0002\u00101\u001a\u0004\u0018\u0001022\u0006\u0010\"\u001a\u00020!J/\u0010\"\u001a\u0004\u0018\u0001H0\"\b\b\u0000\u00100*\u00020!2\b\u00101\u001a\u0004\u0018\u0001022\f\u00104\u001a\b\u0012\u0004\u0012\u0002H005¢\u0006\u0002\u00106R*\u0010\u0005\u001a\u001e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00070\u0006j\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u0007`\bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\nX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e8F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00070\u000e8F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0011R\u001e\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u0003@BX\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0017\u001a\u00020\u0018¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR6\u0010\u001b\u001a\u001e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00070\u0006j\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u0007`\b8FX\u0004¢\u0006\f\u0012\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u001fR.\u0010\"\u001a\u0004\u0018\u00010!2\b\u0010 \u001a\u0004\u0018\u00010!8F@FX\u000e¢\u0006\u0012\u0012\u0004\b#\u0010\u001d\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'\u0002\u0004\n\u0002\b\u0019¨\u00068"}, d2 = {"Lio/ktor/server/auth/AuthenticationContext;", "", "call", "Lio/ktor/server/application/ApplicationCall;", "(Lio/ktor/server/application/ApplicationCall;)V", "_errors", "Ljava/util/HashMap;", "Lio/ktor/server/auth/AuthenticationFailedCause;", "Lkotlin/collections/HashMap;", "_principal", "Lio/ktor/server/auth/CombinedPrincipal;", "get_principal$ktor_server_auth", "()Lio/ktor/server/auth/CombinedPrincipal;", "allErrors", "", "Lio/ktor/server/auth/AuthenticationFailedCause$Error;", "getAllErrors", "()Ljava/util/List;", "allFailures", "getAllFailures", "<set-?>", "getCall", "()Lio/ktor/server/application/ApplicationCall;", "challenge", "Lio/ktor/server/auth/AuthenticationProcedureChallenge;", "getChallenge", "()Lio/ktor/server/auth/AuthenticationProcedureChallenge;", "errors", "getErrors$annotations", "()V", "getErrors", "()Ljava/util/HashMap;", "value", "Lio/ktor/server/auth/Principal;", "principal", "getPrincipal$annotations", "getPrincipal", "()Lio/ktor/server/auth/Principal;", "setPrincipal", "(Lio/ktor/server/auth/Principal;)V", "", "key", "cause", "function", "Lkotlin/Function3;", "Lkotlin/coroutines/Continuation;", "(Ljava/lang/Object;Lio/ktor/server/auth/AuthenticationFailedCause;Lkotlin/jvm/functions/Function3;)V", "error", "T", "provider", "", "(Ljava/lang/String;)Lio/ktor/server/auth/Principal;", "klass", "Lkotlin/reflect/KClass;", "(Ljava/lang/String;Lkotlin/reflect/KClass;)Lio/ktor/server/auth/Principal;", "Companion", "ktor-server-auth"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: AuthenticationContext.kt */
public final class AuthenticationContext {
    /* access modifiers changed from: private */
    public static final AttributeKey<AuthenticationContext> AttributeKey = new AttributeKey<>("AuthContext");
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final HashMap<Object, AuthenticationFailedCause> _errors = new HashMap<>();
    private final CombinedPrincipal _principal = new CombinedPrincipal();
    /* access modifiers changed from: private */
    public ApplicationCall call;
    private final AuthenticationProcedureChallenge challenge = new AuthenticationProcedureChallenge();

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use allErrors, allFailures or error() function instead")
    public static /* synthetic */ void getErrors$annotations() {
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use accessor methods instead")
    public static /* synthetic */ void getPrincipal$annotations() {
    }

    public AuthenticationContext(ApplicationCall applicationCall) {
        Intrinsics.checkNotNullParameter(applicationCall, NotificationCompat.CATEGORY_CALL);
        this.call = applicationCall;
    }

    public final ApplicationCall getCall() {
        return this.call;
    }

    public final CombinedPrincipal get_principal$ktor_server_auth() {
        return this._principal;
    }

    public final Principal getPrincipal() {
        Pair pair = (Pair) CollectionsKt.firstOrNull(this._principal.getPrincipals());
        if (pair != null) {
            return (Principal) pair.getSecond();
        }
        return null;
    }

    public final void setPrincipal(Principal principal) {
        if (principal != null) {
            this._principal.add((String) null, principal);
            return;
        }
        throw new IllegalStateException("Check failed.".toString());
    }

    public final HashMap<Object, AuthenticationFailedCause> getErrors() {
        return this._errors;
    }

    public final List<AuthenticationFailedCause.Error> getAllErrors() {
        Collection<AuthenticationFailedCause> values = this._errors.values();
        Intrinsics.checkNotNullExpressionValue(values, "_errors.values");
        Collection arrayList = new ArrayList();
        for (Object next : values) {
            if (next instanceof AuthenticationFailedCause.Error) {
                arrayList.add(next);
            }
        }
        return (List) arrayList;
    }

    public final List<AuthenticationFailedCause> getAllFailures() {
        Collection<AuthenticationFailedCause> values = this._errors.values();
        Intrinsics.checkNotNullExpressionValue(values, "_errors.values");
        return CollectionsKt.toList(values);
    }

    public final void error(Object obj, AuthenticationFailedCause authenticationFailedCause) {
        Intrinsics.checkNotNullParameter(obj, LeanbackPreferenceDialogFragment.ARG_KEY);
        Intrinsics.checkNotNullParameter(authenticationFailedCause, "cause");
        this._errors.put(obj, authenticationFailedCause);
    }

    public final AuthenticationProcedureChallenge getChallenge() {
        return this.challenge;
    }

    public final void principal(Principal principal) {
        Intrinsics.checkNotNullParameter(principal, "principal");
        this._principal.add((String) null, principal);
    }

    public static /* synthetic */ void principal$default(AuthenticationContext authenticationContext, String str, Principal principal, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        authenticationContext.principal(str, principal);
    }

    public final void principal(String str, Principal principal) {
        Intrinsics.checkNotNullParameter(principal, "principal");
        this._principal.add(str, principal);
    }

    public static /* synthetic */ Principal principal$default(AuthenticationContext authenticationContext, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        Intrinsics.reifiedOperationMarker(4, "T");
        return authenticationContext.principal(str, Reflection.getOrCreateKotlinClass(Principal.class));
    }

    public final /* synthetic */ <T extends Principal> T principal(String str) {
        Intrinsics.reifiedOperationMarker(4, "T");
        return principal(str, Reflection.getOrCreateKotlinClass(Principal.class));
    }

    public final <T extends Principal> T principal(String str, KClass<T> kClass) {
        Intrinsics.checkNotNullParameter(kClass, "klass");
        return this._principal.get(str, kClass);
    }

    public final void challenge(Object obj, AuthenticationFailedCause authenticationFailedCause, Function3<? super AuthenticationProcedureChallenge, ? super ApplicationCall, ? super Continuation<? super Unit>, ? extends Object> function3) {
        Intrinsics.checkNotNullParameter(obj, LeanbackPreferenceDialogFragment.ARG_KEY);
        Intrinsics.checkNotNullParameter(authenticationFailedCause, "cause");
        Intrinsics.checkNotNullParameter(function3, "function");
        error(obj, authenticationFailedCause);
        this.challenge.getRegister$ktor_server_auth().add(TuplesKt.to(authenticationFailedCause, function3));
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0015\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0000¢\u0006\u0002\b\tR\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lio/ktor/server/auth/AuthenticationContext$Companion;", "", "()V", "AttributeKey", "Lio/ktor/util/AttributeKey;", "Lio/ktor/server/auth/AuthenticationContext;", "from", "call", "Lio/ktor/server/application/ApplicationCall;", "from$ktor_server_auth", "ktor-server-auth"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: AuthenticationContext.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final AuthenticationContext from$ktor_server_auth(ApplicationCall applicationCall) {
            Intrinsics.checkNotNullParameter(applicationCall, NotificationCompat.CATEGORY_CALL);
            AuthenticationContext authenticationContext = (AuthenticationContext) applicationCall.getAttributes().getOrNull(AuthenticationContext.AttributeKey);
            if (authenticationContext != null) {
                authenticationContext.call = applicationCall;
                return authenticationContext;
            }
            AuthenticationContext authenticationContext2 = new AuthenticationContext(applicationCall);
            applicationCall.getAttributes().put(AuthenticationContext.AttributeKey, authenticationContext2);
            return authenticationContext2;
        }
    }
}
