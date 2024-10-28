package io.ktor.client.plugins;

import io.ktor.client.HttpClientConfig;
import io.ktor.client.plugins.HttpCallValidator;
import io.ktor.client.statement.HttpResponse;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Lio/ktor/client/plugins/HttpCallValidator$Config;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: DefaultResponseValidation.kt */
final class DefaultResponseValidationKt$addDefaultResponseValidation$1 extends Lambda implements Function1<HttpCallValidator.Config, Unit> {
    final /* synthetic */ HttpClientConfig<?> $this_addDefaultResponseValidation;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DefaultResponseValidationKt$addDefaultResponseValidation$1(HttpClientConfig<?> httpClientConfig) {
        super(1);
        this.$this_addDefaultResponseValidation = httpClientConfig;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((HttpCallValidator.Config) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(HttpCallValidator.Config config) {
        Intrinsics.checkNotNullParameter(config, "$this$HttpResponseValidator");
        config.setExpectSuccess(this.$this_addDefaultResponseValidation.getExpectSuccess());
        config.validateResponse(new AnonymousClass1((Continuation<? super AnonymousClass1>) null));
    }

    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H@"}, d2 = {"<anonymous>", "", "response", "Lio/ktor/client/statement/HttpResponse;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "io.ktor.client.plugins.DefaultResponseValidationKt$addDefaultResponseValidation$1$1", f = "DefaultResponseValidation.kt", i = {0, 0, 1, 1, 1}, l = {42, 48}, m = "invokeSuspend", n = {"response", "statusCode", "response", "exceptionResponse", "statusCode"}, s = {"L$0", "I$0", "L$0", "L$1", "I$0"})
    /* renamed from: io.ktor.client.plugins.DefaultResponseValidationKt$addDefaultResponseValidation$1$1  reason: invalid class name */
    /* compiled from: DefaultResponseValidation.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<HttpResponse, Continuation<? super Unit>, Object> {
        int I$0;
        /* synthetic */ Object L$0;
        Object L$1;
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 r0 = new AnonymousClass1(continuation);
            r0.L$0 = obj;
            return r0;
        }

        public final Object invoke(HttpResponse httpResponse, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(httpResponse, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARNING: Removed duplicated region for block: B:36:0x00dc A[ADDED_TO_REGION] */
        /* JADX WARNING: Removed duplicated region for block: B:40:0x00eb A[ADDED_TO_REGION] */
        /* JADX WARNING: Removed duplicated region for block: B:45:0x00fc  */
        /* JADX WARNING: Removed duplicated region for block: B:46:0x0104  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.lang.Object invokeSuspend(java.lang.Object r11) {
            /*
                r10 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r10.label
                r2 = 300(0x12c, float:4.2E-43)
                r3 = 2
                r4 = 1
                if (r1 == 0) goto L_0x0032
                if (r1 == r4) goto L_0x0027
                if (r1 != r3) goto L_0x001f
                int r0 = r10.I$0
                java.lang.Object r1 = r10.L$1
                io.ktor.client.statement.HttpResponse r1 = (io.ktor.client.statement.HttpResponse) r1
                java.lang.Object r3 = r10.L$0
                io.ktor.client.statement.HttpResponse r3 = (io.ktor.client.statement.HttpResponse) r3
                kotlin.ResultKt.throwOnFailure(r11)     // Catch:{ MalformedInputException -> 0x00d6 }
                goto L_0x00d0
            L_0x001f:
                java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r11.<init>(r0)
                throw r11
            L_0x0027:
                int r1 = r10.I$0
                java.lang.Object r5 = r10.L$0
                io.ktor.client.statement.HttpResponse r5 = (io.ktor.client.statement.HttpResponse) r5
                kotlin.ResultKt.throwOnFailure(r11)
                goto L_0x00a6
            L_0x0032:
                kotlin.ResultKt.throwOnFailure(r11)
                java.lang.Object r11 = r10.L$0
                io.ktor.client.statement.HttpResponse r11 = (io.ktor.client.statement.HttpResponse) r11
                io.ktor.client.call.HttpClientCall r1 = r11.getCall()
                io.ktor.util.Attributes r1 = r1.getAttributes()
                io.ktor.util.AttributeKey r5 = io.ktor.client.plugins.HttpCallValidatorKt.getExpectSuccessAttributeKey()
                java.lang.Object r1 = r1.get(r5)
                java.lang.Boolean r1 = (java.lang.Boolean) r1
                boolean r1 = r1.booleanValue()
                if (r1 != 0) goto L_0x0075
                org.slf4j.Logger r0 = io.ktor.client.plugins.DefaultResponseValidationKt.LOGGER
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                java.lang.String r2 = "Skipping default response validation for "
                r1.<init>(r2)
                io.ktor.client.call.HttpClientCall r11 = r11.getCall()
                io.ktor.client.request.HttpRequest r11 = r11.getRequest()
                io.ktor.http.Url r11 = r11.getUrl()
                r1.append(r11)
                java.lang.String r11 = r1.toString()
                r0.trace(r11)
                kotlin.Unit r11 = kotlin.Unit.INSTANCE
                return r11
            L_0x0075:
                io.ktor.http.HttpStatusCode r1 = r11.getStatus()
                int r1 = r1.getValue()
                io.ktor.client.call.HttpClientCall r5 = r11.getCall()
                if (r1 < r2) goto L_0x0133
                io.ktor.util.Attributes r6 = r5.getAttributes()
                io.ktor.util.AttributeKey r7 = io.ktor.client.plugins.DefaultResponseValidationKt.ValidateMark
                boolean r6 = r6.contains(r7)
                if (r6 == 0) goto L_0x0093
                goto L_0x0133
            L_0x0093:
                r6 = r10
                kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
                r10.L$0 = r11
                r10.I$0 = r1
                r10.label = r4
                java.lang.Object r5 = io.ktor.client.call.SavedCallKt.save(r5, r6)
                if (r5 != r0) goto L_0x00a3
                return r0
            L_0x00a3:
                r9 = r5
                r5 = r11
                r11 = r9
            L_0x00a6:
                io.ktor.client.call.HttpClientCall r11 = (io.ktor.client.call.HttpClientCall) r11
                io.ktor.util.Attributes r6 = r11.getAttributes()
                io.ktor.util.AttributeKey r7 = io.ktor.client.plugins.DefaultResponseValidationKt.ValidateMark
                kotlin.Unit r8 = kotlin.Unit.INSTANCE
                r6.put(r7, r8)
                io.ktor.client.statement.HttpResponse r11 = r11.getResponse()
                r6 = r10
                kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6     // Catch:{ MalformedInputException -> 0x00d3 }
                r10.L$0 = r5     // Catch:{ MalformedInputException -> 0x00d3 }
                r10.L$1 = r11     // Catch:{ MalformedInputException -> 0x00d3 }
                r10.I$0 = r1     // Catch:{ MalformedInputException -> 0x00d3 }
                r10.label = r3     // Catch:{ MalformedInputException -> 0x00d3 }
                r3 = 0
                java.lang.Object r3 = io.ktor.client.statement.HttpResponseKt.bodyAsText$default(r11, r3, r6, r4, r3)     // Catch:{ MalformedInputException -> 0x00d3 }
                if (r3 != r0) goto L_0x00cc
                return r0
            L_0x00cc:
                r0 = r1
                r1 = r11
                r11 = r3
                r3 = r5
            L_0x00d0:
                java.lang.String r11 = (java.lang.String) r11     // Catch:{ MalformedInputException -> 0x00d6 }
                goto L_0x00d8
            L_0x00d3:
                r0 = r1
                r3 = r5
                r1 = r11
            L_0x00d6:
                java.lang.String r11 = "<body failed decoding>"
            L_0x00d8:
                r4 = 400(0x190, float:5.6E-43)
                if (r2 > r0) goto L_0x00e7
                if (r0 < r4) goto L_0x00df
                goto L_0x00e7
            L_0x00df:
                io.ktor.client.plugins.RedirectResponseException r0 = new io.ktor.client.plugins.RedirectResponseException
                r0.<init>(r1, r11)
                io.ktor.client.plugins.ResponseException r0 = (io.ktor.client.plugins.ResponseException) r0
                goto L_0x0109
            L_0x00e7:
                r2 = 500(0x1f4, float:7.0E-43)
                if (r4 > r0) goto L_0x00f6
                if (r0 < r2) goto L_0x00ee
                goto L_0x00f6
            L_0x00ee:
                io.ktor.client.plugins.ClientRequestException r0 = new io.ktor.client.plugins.ClientRequestException
                r0.<init>(r1, r11)
                io.ktor.client.plugins.ResponseException r0 = (io.ktor.client.plugins.ResponseException) r0
                goto L_0x0109
            L_0x00f6:
                if (r2 > r0) goto L_0x0104
                r2 = 600(0x258, float:8.41E-43)
                if (r0 >= r2) goto L_0x0104
                io.ktor.client.plugins.ServerResponseException r0 = new io.ktor.client.plugins.ServerResponseException
                r0.<init>(r1, r11)
                io.ktor.client.plugins.ResponseException r0 = (io.ktor.client.plugins.ResponseException) r0
                goto L_0x0109
            L_0x0104:
                io.ktor.client.plugins.ResponseException r0 = new io.ktor.client.plugins.ResponseException
                r0.<init>(r1, r11)
            L_0x0109:
                org.slf4j.Logger r11 = io.ktor.client.plugins.DefaultResponseValidationKt.LOGGER
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                java.lang.String r2 = "Default response validation for "
                r1.<init>(r2)
                io.ktor.client.call.HttpClientCall r2 = r3.getCall()
                io.ktor.client.request.HttpRequest r2 = r2.getRequest()
                io.ktor.http.Url r2 = r2.getUrl()
                r1.append(r2)
                java.lang.String r2 = " failed with "
                r1.append(r2)
                r1.append(r0)
                java.lang.String r1 = r1.toString()
                r11.trace(r1)
                throw r0
            L_0x0133:
                kotlin.Unit r11 = kotlin.Unit.INSTANCE
                return r11
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.DefaultResponseValidationKt$addDefaultResponseValidation$1.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }
}
