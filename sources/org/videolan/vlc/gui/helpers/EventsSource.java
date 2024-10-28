package org.videolan.vlc.gui.helpers;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0005¢\u0006\u0002\u0010\u0003R\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\tX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/videolan/vlc/gui/helpers/EventsSource;", "E", "Lorg/videolan/vlc/gui/helpers/IEventsSource;", "()V", "events", "Lkotlinx/coroutines/flow/Flow;", "getEvents", "()Lkotlinx/coroutines/flow/Flow;", "eventsChannel", "Lkotlinx/coroutines/channels/Channel;", "getEventsChannel", "()Lkotlinx/coroutines/channels/Channel;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: EventsSource.kt */
public final class EventsSource<E> implements IEventsSource<E> {
    private final Flow<E> events = FlowKt.consumeAsFlow(getEventsChannel());
    private final Channel<E> eventsChannel = ChannelKt.Channel$default(-1, (BufferOverflow) null, (Function1) null, 6, (Object) null);

    public Channel<E> getEventsChannel() {
        return this.eventsChannel;
    }

    public Flow<E> getEvents() {
        return this.events;
    }
}
