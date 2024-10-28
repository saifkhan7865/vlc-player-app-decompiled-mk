package io.netty.util.concurrent;

import io.netty.util.internal.InternalThreadLocalMap;
import io.netty.util.internal.PlatformDependent;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;

public class FastThreadLocal<V> {
    private final int index = InternalThreadLocalMap.nextVariableIndex();

    /* access modifiers changed from: protected */
    public V initialValue() throws Exception {
        return null;
    }

    /* access modifiers changed from: protected */
    public void onRemoval(V v) throws Exception {
    }

    public static void removeAll() {
        InternalThreadLocalMap ifSet = InternalThreadLocalMap.getIfSet();
        if (ifSet != null) {
            try {
                Object indexedVariable = ifSet.indexedVariable(InternalThreadLocalMap.VARIABLES_TO_REMOVE_INDEX);
                if (!(indexedVariable == null || indexedVariable == InternalThreadLocalMap.UNSET)) {
                    for (FastThreadLocal remove : (FastThreadLocal[]) ((Set) indexedVariable).toArray(new FastThreadLocal[0])) {
                        remove.remove(ifSet);
                    }
                }
            } finally {
                InternalThreadLocalMap.remove();
            }
        }
    }

    public static int size() {
        InternalThreadLocalMap ifSet = InternalThreadLocalMap.getIfSet();
        if (ifSet == null) {
            return 0;
        }
        return ifSet.size();
    }

    public static void destroy() {
        InternalThreadLocalMap.destroy();
    }

    private static void addToVariablesToRemove(InternalThreadLocalMap internalThreadLocalMap, FastThreadLocal<?> fastThreadLocal) {
        Set set;
        Object indexedVariable = internalThreadLocalMap.indexedVariable(InternalThreadLocalMap.VARIABLES_TO_REMOVE_INDEX);
        if (indexedVariable == InternalThreadLocalMap.UNSET || indexedVariable == null) {
            set = Collections.newSetFromMap(new IdentityHashMap());
            internalThreadLocalMap.setIndexedVariable(InternalThreadLocalMap.VARIABLES_TO_REMOVE_INDEX, set);
        } else {
            set = (Set) indexedVariable;
        }
        set.add(fastThreadLocal);
    }

    private static void removeFromVariablesToRemove(InternalThreadLocalMap internalThreadLocalMap, FastThreadLocal<?> fastThreadLocal) {
        Object indexedVariable = internalThreadLocalMap.indexedVariable(InternalThreadLocalMap.VARIABLES_TO_REMOVE_INDEX);
        if (indexedVariable != InternalThreadLocalMap.UNSET && indexedVariable != null) {
            ((Set) indexedVariable).remove(fastThreadLocal);
        }
    }

    public final V get() {
        InternalThreadLocalMap internalThreadLocalMap = InternalThreadLocalMap.get();
        V indexedVariable = internalThreadLocalMap.indexedVariable(this.index);
        if (indexedVariable != InternalThreadLocalMap.UNSET) {
            return indexedVariable;
        }
        return initialize(internalThreadLocalMap);
    }

    public final V getIfExists() {
        V indexedVariable;
        InternalThreadLocalMap ifSet = InternalThreadLocalMap.getIfSet();
        if (ifSet == null || (indexedVariable = ifSet.indexedVariable(this.index)) == InternalThreadLocalMap.UNSET) {
            return null;
        }
        return indexedVariable;
    }

    public final V get(InternalThreadLocalMap internalThreadLocalMap) {
        V indexedVariable = internalThreadLocalMap.indexedVariable(this.index);
        if (indexedVariable != InternalThreadLocalMap.UNSET) {
            return indexedVariable;
        }
        return initialize(internalThreadLocalMap);
    }

    private V initialize(InternalThreadLocalMap internalThreadLocalMap) {
        V v;
        try {
            v = initialValue();
            try {
                if (v != InternalThreadLocalMap.UNSET) {
                    internalThreadLocalMap.setIndexedVariable(this.index, v);
                    addToVariablesToRemove(internalThreadLocalMap, this);
                    return v;
                }
                throw new IllegalArgumentException("InternalThreadLocalMap.UNSET can not be initial value.");
            } catch (Exception e) {
                e = e;
                PlatformDependent.throwException(e);
                internalThreadLocalMap.setIndexedVariable(this.index, v);
                addToVariablesToRemove(internalThreadLocalMap, this);
                return v;
            }
        } catch (Exception e2) {
            e = e2;
            v = null;
            PlatformDependent.throwException(e);
            internalThreadLocalMap.setIndexedVariable(this.index, v);
            addToVariablesToRemove(internalThreadLocalMap, this);
            return v;
        }
    }

    public final void set(V v) {
        if (v != InternalThreadLocalMap.UNSET) {
            setKnownNotUnset(InternalThreadLocalMap.get(), v);
        } else {
            remove();
        }
    }

    public final void set(InternalThreadLocalMap internalThreadLocalMap, V v) {
        if (v != InternalThreadLocalMap.UNSET) {
            setKnownNotUnset(internalThreadLocalMap, v);
        } else {
            remove(internalThreadLocalMap);
        }
    }

    private void setKnownNotUnset(InternalThreadLocalMap internalThreadLocalMap, V v) {
        if (internalThreadLocalMap.setIndexedVariable(this.index, v)) {
            addToVariablesToRemove(internalThreadLocalMap, this);
        }
    }

    public final boolean isSet() {
        return isSet(InternalThreadLocalMap.getIfSet());
    }

    public final boolean isSet(InternalThreadLocalMap internalThreadLocalMap) {
        return internalThreadLocalMap != null && internalThreadLocalMap.isIndexedVariableSet(this.index);
    }

    public final void remove() {
        remove(InternalThreadLocalMap.getIfSet());
    }

    public final void remove(InternalThreadLocalMap internalThreadLocalMap) {
        Object removeIndexedVariable;
        if (internalThreadLocalMap != null && (removeIndexedVariable = internalThreadLocalMap.removeIndexedVariable(this.index)) != InternalThreadLocalMap.UNSET) {
            removeFromVariablesToRemove(internalThreadLocalMap, this);
            try {
                onRemoval(removeIndexedVariable);
            } catch (Exception e) {
                PlatformDependent.throwException(e);
            }
        }
    }
}
