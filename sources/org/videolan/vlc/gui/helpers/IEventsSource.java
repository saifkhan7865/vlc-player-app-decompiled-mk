package org.videolan.vlc.gui.helpers;

import kotlin.Metadata;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.flow.Flow;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002R\u0018\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0018\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\bX¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lorg/videolan/vlc/gui/helpers/IEventsSource;", "E", "", "events", "Lkotlinx/coroutines/flow/Flow;", "getEvents", "()Lkotlinx/coroutines/flow/Flow;", "eventsChannel", "Lkotlinx/coroutines/channels/Channel;", "getEventsChannel", "()Lkotlinx/coroutines/channels/Channel;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: EventsSource.kt */
public interface IEventsSource<E> {
    Flow<E> getEvents();

    Channel<E> getEventsChannel();
}
