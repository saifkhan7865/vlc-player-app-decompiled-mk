package io.ktor.server.sessions;

import io.ktor.server.sessions.ListElement;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0002\u0018\u0000*\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0013\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00028\u0000¢\u0006\u0002\u0010\u000bJ\u0006\u0010\f\u001a\u00020\tJ\u000b\u0010\u0005\u001a\u00028\u0000¢\u0006\u0002\u0010\rJ\u0006\u0010\u000e\u001a\u00020\u000fJ\u0013\u0010\u0010\u001a\u00020\t2\u0006\u0010\n\u001a\u00028\u0000¢\u0006\u0002\u0010\u000bJ\u0013\u0010\u0011\u001a\u00020\t2\u0006\u0010\n\u001a\u00028\u0000¢\u0006\u0002\u0010\u000bJ\u000b\u0010\u0012\u001a\u00028\u0000¢\u0006\u0002\u0010\rR\u0012\u0010\u0005\u001a\u0004\u0018\u00018\u0000X\u000e¢\u0006\u0004\n\u0002\u0010\u0006R\u0012\u0010\u0007\u001a\u0004\u0018\u00018\u0000X\u000e¢\u0006\u0004\n\u0002\u0010\u0006¨\u0006\u0013"}, d2 = {"Lio/ktor/server/sessions/PullableLinkedList;", "E", "Lio/ktor/server/sessions/ListElement;", "", "()V", "head", "Lio/ktor/server/sessions/ListElement;", "tail", "add", "", "element", "(Lio/ktor/server/sessions/ListElement;)V", "clear", "()Lio/ktor/server/sessions/ListElement;", "isEmpty", "", "pull", "remove", "take", "ktor-server-sessions"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: CacheJvm.kt */
final class PullableLinkedList<E extends ListElement<E>> {
    private E head;
    private E tail;

    public final boolean isEmpty() {
        return this.head == null;
    }

    public final E take() {
        E head2 = head();
        remove(head2);
        return head2;
    }

    public final E head() {
        E e = this.head;
        if (e != null) {
            return e;
        }
        throw new NoSuchElementException();
    }

    public final void add(E e) {
        Intrinsics.checkNotNullParameter(e, "element");
        if (e.getNext() != null) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        } else if (e.getPrev() == null) {
            E e2 = this.head;
            if (e2 != null) {
                e.setNext(e2);
                e2.setPrev(e);
            }
            this.head = e;
            if (this.tail == null) {
                this.tail = e;
            }
        } else {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
    }

    public final void remove(E e) {
        Intrinsics.checkNotNullParameter(e, "element");
        if (Intrinsics.areEqual((Object) e, (Object) this.head)) {
            this.head = null;
        }
        if (Intrinsics.areEqual((Object) e, (Object) this.tail)) {
            this.tail = null;
        }
        ListElement prev = e.getPrev();
        if (prev != null) {
            prev.setNext(e.getNext());
        }
        e.setNext(null);
        e.setPrev(null);
    }

    public final void clear() {
        this.head = null;
        this.tail = null;
    }

    public final void pull(E e) {
        Intrinsics.checkNotNullParameter(e, "element");
        if (e != this.head) {
            remove(e);
            add(e);
        }
    }
}
