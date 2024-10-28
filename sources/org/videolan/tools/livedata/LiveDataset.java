package org.videolan.tools.livedata;

import androidx.lifecycle.MutableLiveData;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\n\u0018\u0000*\u0004\b\u0000\u0010\u00012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00030\u0002B\u0005¢\u0006\u0002\u0010\u0004J\u0013\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00028\u0000¢\u0006\u0002\u0010\u000eJ+\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00028\u00002\u0016\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00028\u00000\u0010j\b\u0012\u0004\u0012\u00028\u0000`\u0011¢\u0006\u0002\u0010\u0012J\u001b\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\b2\u0006\u0010\r\u001a\u00028\u0000¢\u0006\u0002\u0010\u0014J\u0014\u0010\u000b\u001a\u00020\f2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u0016J\u0006\u0010\u0017\u001a\u00020\fJ\u0013\u0010\u0018\u001a\u00028\u00002\u0006\u0010\u0013\u001a\u00020\b¢\u0006\u0002\u0010\u0019J\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00028\u00000\u0016J\u000e\u0010\u001b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0016J\u0006\u0010\u001c\u001a\u00020\u001dJ\u001b\u0010\u001e\u001a\u00020\f2\u0006\u0010\r\u001a\u00028\u00002\u0006\u0010\u001f\u001a\u00020\b¢\u0006\u0002\u0010 J\u0016\u0010\u001e\u001a\u00020\f2\u0006\u0010!\u001a\u00020\b2\u0006\u0010\"\u001a\u00020\bJ\u0013\u0010#\u001a\u00020\f2\u0006\u0010\r\u001a\u00028\u0000¢\u0006\u0002\u0010\u000eJ\u000e\u0010#\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\bJ\u0013\u0010$\u001a\u00020\f2\u0006\u0010\r\u001a\u00028\u0000¢\u0006\u0002\u0010\u000eJ\u0018\u0010%\u001a\u00020\f2\u000e\u0010&\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0003H\u0016R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003X\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006'"}, d2 = {"Lorg/videolan/tools/livedata/LiveDataset;", "T", "Landroidx/lifecycle/MutableLiveData;", "", "()V", "emptyList", "internalList", "size", "", "getSize", "()I", "add", "", "item", "(Ljava/lang/Object;)V", "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "(Ljava/lang/Object;Ljava/util/Comparator;)V", "position", "(ILjava/lang/Object;)V", "items", "", "clear", "get", "(I)Ljava/lang/Object;", "getList", "getValue", "isEmpty", "", "move", "newIndex", "(Ljava/lang/Object;I)V", "from", "to", "remove", "replace", "setValue", "value", "tools_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: LiveDataset.kt */
public final class LiveDataset<T> extends MutableLiveData<List<T>> {
    private final List<T> emptyList;
    private List<T> internalList;
    private final int size;

    public LiveDataset() {
        List<T> arrayList = new ArrayList<>();
        this.emptyList = arrayList;
        this.internalList = arrayList;
        this.size = arrayList.size();
    }

    public final boolean isEmpty() {
        return this.internalList.isEmpty();
    }

    public void setValue(List<T> list) {
        this.internalList = list == null ? this.emptyList : list;
        super.setValue(list);
    }

    public List<T> getValue() {
        return this.internalList;
    }

    public final T get(int i) {
        return this.internalList.get(i);
    }

    public final List<T> getList() {
        return CollectionsKt.toList(this.internalList);
    }

    public final int getSize() {
        return this.size;
    }

    public final void clear() {
        List<T> list = this.internalList;
        list.clear();
        setValue(list);
    }

    public final void add(T t) {
        List<T> list = this.internalList;
        list.add(t);
        setValue(list);
    }

    public final void add(T t, Comparator<T> comparator) {
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Iterator<T> it = this.internalList.iterator();
        int i = 0;
        while (it.hasNext() && comparator.compare(t, it.next()) > 0) {
            i++;
        }
        List<T> list = this.internalList;
        list.add(i, t);
        setValue(list);
    }

    public final void add(int i, T t) {
        List<T> list = this.internalList;
        list.add(i, t);
        setValue(list);
    }

    public final void add(List<? extends T> list) {
        Intrinsics.checkNotNullParameter(list, "items");
        List<T> list2 = this.internalList;
        Collection arrayList = new ArrayList();
        for (Object next : list) {
            if (!list2.contains(next)) {
                arrayList.add(next);
            }
        }
        list2.addAll((List) arrayList);
        setValue(list2);
    }

    public final void remove(T t) {
        List<T> list = this.internalList;
        list.remove(t);
        setValue(list);
    }

    public final void replace(T t) {
        List<T> list = this.internalList;
        int indexOf = list.indexOf(t);
        list.remove(indexOf);
        list.add(indexOf, t);
        setValue(list);
    }

    public final void remove(int i) {
        List<T> list = this.internalList;
        list.remove(i);
        setValue(list);
    }

    public final void move(T t, int i) {
        int indexOf = this.internalList.indexOf(t);
        if (indexOf > 0) {
            move(indexOf, i);
        }
    }

    public final void move(int i, int i2) {
        List<T> list = this.internalList;
        T t = list.get(i);
        list.remove(i);
        if (i > i2) {
            list.add(i2, t);
        } else {
            list.add(i2 - 1, t);
        }
        setValue(list);
    }
}
