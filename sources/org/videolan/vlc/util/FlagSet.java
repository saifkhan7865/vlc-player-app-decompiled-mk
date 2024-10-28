package org.videolan.vlc.util;

import java.lang.Enum;
import java.util.EnumSet;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.vlc.util.Flag;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\u0018\u0000*\u0012\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u0002*\u00020\u00032\u00020\u0004B\u0013\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\u0002\u0010\u0007J\u0013\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00028\u0000¢\u0006\u0002\u0010\u000eJ\u001f\u0010\u000f\u001a\u00020\u00102\u0012\u0010\u0011\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u0012\"\u00028\u0000¢\u0006\u0002\u0010\u0013J\u0013\u0010\u0014\u001a\u00020\f2\u0006\u0010\r\u001a\u00028\u0000¢\u0006\u0002\u0010\u000eJ\u0006\u0010\u0015\u001a\u00020\u0016J\u0006\u0010\u0017\u001a\u00020\fJ\u0013\u0010\u0018\u001a\u00020\f2\u0006\u0010\r\u001a\u00028\u0000¢\u0006\u0002\u0010\u000eJ\u001f\u0010\u0019\u001a\u00020\u00102\u0012\u0010\u0011\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u0012\"\u00028\u0000¢\u0006\u0002\u0010\u0013J\u000e\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u0016J\b\u0010\u001c\u001a\u00020\u001dH\u0016R2\u0010\b\u001a&\u0012\f\u0012\n \n*\u0004\u0018\u00018\u00008\u0000 \n*\u0012\u0012\f\u0012\n \n*\u0004\u0018\u00018\u00008\u0000\u0018\u00010\t0\tX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lorg/videolan/vlc/util/FlagSet;", "T", "", "Lorg/videolan/vlc/util/Flag;", "", "enumClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)V", "enabledActions", "Ljava/util/EnumSet;", "kotlin.jvm.PlatformType", "add", "", "action", "(Ljava/lang/Enum;)Z", "addAll", "", "actions", "", "([Ljava/lang/Enum;)V", "contains", "getCapabilities", "", "isNotEmpty", "remove", "removeAll", "setCapabilities", "capabilities", "toString", "", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: FlagSet.kt */
public final class FlagSet<T extends Enum<T> & Flag> {
    private final EnumSet<T> enabledActions;
    private final Class<T> enumClass;

    public FlagSet(Class<T> cls) {
        Intrinsics.checkNotNullParameter(cls, "enumClass");
        this.enumClass = cls;
        this.enabledActions = EnumSet.noneOf(cls);
    }

    public String toString() {
        String enumSet = this.enabledActions.toString();
        Intrinsics.checkNotNullExpressionValue(enumSet, "toString(...)");
        return enumSet;
    }

    public final boolean add(T t) {
        Intrinsics.checkNotNullParameter(t, "action");
        return this.enabledActions.add(t);
    }

    public final boolean remove(T t) {
        Intrinsics.checkNotNullParameter(t, "action");
        return this.enabledActions.remove(t);
    }

    public final boolean contains(T t) {
        Intrinsics.checkNotNullParameter(t, "action");
        return this.enabledActions.contains(t);
    }

    public final boolean isNotEmpty() {
        EnumSet<T> enumSet = this.enabledActions;
        Intrinsics.checkNotNullExpressionValue(enumSet, "enabledActions");
        return !enumSet.isEmpty();
    }

    public final long getCapabilities() {
        EnumSet<T> enumSet = this.enabledActions;
        Intrinsics.checkNotNullExpressionValue(enumSet, "enabledActions");
        long j = 0;
        for (T t : enumSet) {
            j |= ((Flag) t).toLong();
        }
        return j;
    }

    public final void setCapabilities(long j) {
        if (j != 0) {
            Object[] enumConstants = this.enumClass.getEnumConstants();
            Intrinsics.checkNotNullExpressionValue(enumConstants, "getEnumConstants(...)");
            long j2 = j;
            for (Enum enumR : (Enum[]) enumConstants) {
                long j3 = ((Flag) enumR).toLong();
                if ((j & j3) != 0) {
                    this.enabledActions.add(enumR);
                    j2 ^= j3;
                    if (j2 == 0) {
                        return;
                    }
                }
            }
        }
    }

    public final void addAll(T... tArr) {
        Intrinsics.checkNotNullParameter(tArr, "actions");
        for (T add : tArr) {
            add(add);
        }
    }

    public final void removeAll(T... tArr) {
        Intrinsics.checkNotNullParameter(tArr, "actions");
        for (T remove : tArr) {
            remove(remove);
        }
    }
}
