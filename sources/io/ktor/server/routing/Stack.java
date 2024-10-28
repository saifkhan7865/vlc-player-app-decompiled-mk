package io.ktor.server.routing;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0006\u0010\u0007\u001a\u00020\bJ\u000b\u0010\t\u001a\u00028\u0000¢\u0006\u0002\u0010\nJ\u000b\u0010\u000b\u001a\u00028\u0000¢\u0006\u0002\u0010\nJ\u0013\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00028\u0000¢\u0006\u0002\u0010\u000fR\u001e\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00028\u00000\u0005j\b\u0012\u0004\u0012\u00028\u0000`\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lio/ktor/server/routing/Stack;", "E", "", "()V", "tower", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "empty", "", "peek", "()Ljava/lang/Object;", "pop", "push", "", "element", "(Ljava/lang/Object;)V", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: RoutingResolveTrace.kt */
final class Stack<E> {
    private final ArrayList<E> tower = new ArrayList<>();

    public final boolean empty() {
        return this.tower.isEmpty();
    }

    public final void push(E e) {
        this.tower.add(e);
    }

    public final E pop() {
        if (!this.tower.isEmpty()) {
            ArrayList<E> arrayList = this.tower;
            return arrayList.remove(CollectionsKt.getLastIndex(arrayList));
        }
        throw new NoSuchElementException("Unable to pop an element from empty stack");
    }

    public final E peek() {
        if (!this.tower.isEmpty()) {
            return CollectionsKt.last(this.tower);
        }
        throw new NoSuchElementException("Unable to peek an element into empty stack");
    }
}
