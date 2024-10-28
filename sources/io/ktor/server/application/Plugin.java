package io.ktor.server.application;

import io.ktor.util.AttributeKey;
import io.ktor.util.pipeline.Pipeline;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000*\u0014\b\u0000\u0010\u0001 \u0000*\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u00020\u00030\u0002*\n\b\u0001\u0010\u0004 \u0001*\u00020\u0005*\b\b\u0002\u0010\u0006*\u00020\u00052\u00020\u0005J.\u0010\u000b\u001a\u00028\u00022\u0006\u0010\f\u001a\u00028\u00002\u0017\u0010\r\u001a\u0013\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00020\u000f0\u000e¢\u0006\u0002\b\u0010H&¢\u0006\u0002\u0010\u0011R\u0018\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00020\bX¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\u0012"}, d2 = {"Lio/ktor/server/application/Plugin;", "TPipeline", "Lio/ktor/util/pipeline/Pipeline;", "Lio/ktor/server/application/ApplicationCall;", "TConfiguration", "", "TPlugin", "key", "Lio/ktor/util/AttributeKey;", "getKey", "()Lio/ktor/util/AttributeKey;", "install", "pipeline", "configure", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "(Lio/ktor/util/pipeline/Pipeline;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ApplicationPlugin.kt */
public interface Plugin<TPipeline extends Pipeline<?, ApplicationCall>, TConfiguration, TPlugin> {
    AttributeKey<TPlugin> getKey();

    TPlugin install(TPipeline tpipeline, Function1<? super TConfiguration, Unit> function1);
}
