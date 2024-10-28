package io.ktor.events;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;

@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a=\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00052\u0006\u0010\u0006\u001a\u0002H\u00022\u0010\b\u0002\u0010\u0007\u001a\n\u0018\u00010\bj\u0004\u0018\u0001`\t¢\u0006\u0002\u0010\n*(\u0010\u000b\u001a\u0004\b\u0000\u0010\u0002\"\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\f2\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\f¨\u0006\r"}, d2 = {"raiseCatching", "", "T", "Lio/ktor/events/Events;", "definition", "Lio/ktor/events/EventDefinition;", "value", "logger", "Lorg/slf4j/Logger;", "Lio/ktor/util/logging/Logger;", "(Lio/ktor/events/Events;Lio/ktor/events/EventDefinition;Ljava/lang/Object;Lorg/slf4j/Logger;)V", "EventHandler", "Lkotlin/Function1;", "ktor-events"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: Events.kt */
public final class EventsKt {
    public static /* synthetic */ void raiseCatching$default(Events events, EventDefinition eventDefinition, Object obj, Logger logger, int i, Object obj2) {
        if ((i & 4) != 0) {
            logger = null;
        }
        raiseCatching(events, eventDefinition, obj, logger);
    }

    public static final <T> void raiseCatching(Events events, EventDefinition<T> eventDefinition, T t, Logger logger) {
        Intrinsics.checkNotNullParameter(events, "<this>");
        Intrinsics.checkNotNullParameter(eventDefinition, "definition");
        try {
            events.raise(eventDefinition, t);
        } catch (Throwable th) {
            if (logger != null) {
                logger.error("Some handlers have thrown an exception", th);
            }
        }
    }
}
