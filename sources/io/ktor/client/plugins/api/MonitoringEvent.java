package io.ktor.client.plugins.api;

import androidx.core.app.NotificationCompat;
import io.ktor.client.HttpClient;
import io.ktor.events.EventDefinition;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\u000e\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00010\u00042\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u00070\u00060\u0005B\r\u0012\u0006\u0010\b\u001a\u00028\u0001¢\u0006\u0002\u0010\tJ$\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\r2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00070\u0006H\u0016R\u0010\u0010\b\u001a\u00028\u0001X\u0004¢\u0006\u0004\n\u0002\u0010\n¨\u0006\u000f"}, d2 = {"Lio/ktor/client/plugins/api/MonitoringEvent;", "Param", "", "Event", "Lio/ktor/events/EventDefinition;", "Lio/ktor/client/plugins/api/ClientHook;", "Lkotlin/Function1;", "", "event", "(Lio/ktor/events/EventDefinition;)V", "Lio/ktor/events/EventDefinition;", "install", "client", "Lio/ktor/client/HttpClient;", "handler", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: CommonHooks.kt */
public final class MonitoringEvent<Param, Event extends EventDefinition<Param>> implements ClientHook<Function1<? super Param, ? extends Unit>> {
    private final Event event;

    public MonitoringEvent(Event event2) {
        Intrinsics.checkNotNullParameter(event2, NotificationCompat.CATEGORY_EVENT);
        this.event = event2;
    }

    public void install(HttpClient httpClient, Function1<? super Param, Unit> function1) {
        Intrinsics.checkNotNullParameter(httpClient, "client");
        Intrinsics.checkNotNullParameter(function1, "handler");
        httpClient.getMonitor().subscribe(this.event, new MonitoringEvent$install$1(function1));
    }
}
