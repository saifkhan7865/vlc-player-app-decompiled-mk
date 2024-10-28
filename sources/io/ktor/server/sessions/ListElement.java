package io.ktor.server.sessions;

import io.ktor.server.sessions.ListElement;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u000b\b\"\u0018\u0000*\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00002\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003R\u001e\u0010\u0004\u001a\u0004\u0018\u00018\u0000X\u000e¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001e\u0010\n\u001a\u0004\u0018\u00018\u0000X\u000e¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b\u000b\u0010\u0006\"\u0004\b\f\u0010\b¨\u0006\r"}, d2 = {"Lio/ktor/server/sessions/ListElement;", "E", "", "()V", "next", "getNext", "()Lio/ktor/server/sessions/ListElement;", "setNext", "(Lio/ktor/server/sessions/ListElement;)V", "Lio/ktor/server/sessions/ListElement;", "prev", "getPrev", "setPrev", "ktor-server-sessions"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: CacheJvm.kt */
abstract class ListElement<E extends ListElement<E>> {
    private E next;
    private E prev;

    public final E getNext() {
        return this.next;
    }

    public final void setNext(E e) {
        this.next = e;
    }

    public final E getPrev() {
        return this.prev;
    }

    public final void setPrev(E e) {
        this.prev = e;
    }
}
