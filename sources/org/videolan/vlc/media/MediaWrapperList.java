package org.videolan.vlc.media;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u0011\u0018\u0000 .2\u00020\u0001:\u0002./B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0005J\u000e\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\nJ\u0006\u0010\u0016\u001a\u00020\u0012J\u0012\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u0010H\u0002J\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0019\u001a\u00020\u0010J\u0016\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0005J\u0010\u0010\u001c\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\u0010H\u0002J\u0014\u0010\u001d\u001a\u00020\u00122\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00050\u001fJ\u0016\u0010 \u001a\u00020\u00122\u0006\u0010!\u001a\u00020\u00102\u0006\u0010\"\u001a\u00020\u0010J\u000e\u0010#\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u0010J\u000e\u0010#\u001a\u00020\u00122\u0006\u0010$\u001a\u00020\u0018J\u000e\u0010%\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\nJ\u0014\u0010&\u001a\u00020\u00122\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00050\u001fJ(\u0010'\u001a\u00020\u00122\u0006\u0010(\u001a\u00020\u00102\u0006\u0010)\u001a\u00020\u00102\u0006\u0010*\u001a\u00020\u00102\u0006\u0010+\u001a\u00020\u0018H\u0002J\u0006\u0010,\u001a\u00020\u0010J\b\u0010-\u001a\u00020\u0018H\u0016R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\tX\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\f\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b\f\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u000e¢\u0006\u0002\n\u0000¨\u00060"}, d2 = {"Lorg/videolan/vlc/media/MediaWrapperList;", "", "()V", "copy", "", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "getCopy", "()Ljava/util/List;", "eventListenerList", "Ljava/util/ArrayList;", "Lorg/videolan/vlc/media/MediaWrapperList$EventListener;", "internalList", "isAudioList", "", "()Z", "videoCount", "", "add", "", "media", "addEventListener", "listener", "clear", "getMRL", "", "position", "getMedia", "insert", "isValid", "map", "list", "", "move", "startPosition", "endPosition", "remove", "location", "removeEventListener", "replaceWith", "signalEventListeners", "event", "arg1", "arg2", "mrl", "size", "toString", "Companion", "EventListener", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaWrapperList.kt */
public final class MediaWrapperList {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final int EVENT_ADDED = 0;
    private static final int EVENT_MOVED = 2;
    private static final int EVENT_REMOVED = 1;
    private static final String TAG = "VLC/MediaWrapperList";
    private final ArrayList<EventListener> eventListenerList = new ArrayList<>();
    private final ArrayList<MediaWrapper> internalList = new ArrayList<>();
    private int videoCount;

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J \u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0018\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\f"}, d2 = {"Lorg/videolan/vlc/media/MediaWrapperList$EventListener;", "", "onItemAdded", "", "index", "", "mrl", "", "onItemMoved", "indexBefore", "indexAfter", "onItemRemoved", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: MediaWrapperList.kt */
    public interface EventListener {
        void onItemAdded(int i, String str);

        void onItemMoved(int i, int i2, String str);

        void onItemRemoved(int i, String str);
    }

    public final synchronized List<MediaWrapper> getCopy() {
        return new ArrayList<>(this.internalList);
    }

    public final synchronized boolean isAudioList() {
        return this.videoCount == 0;
    }

    public final synchronized void add(MediaWrapper mediaWrapper) {
        Intrinsics.checkNotNullParameter(mediaWrapper, "media");
        this.internalList.add(mediaWrapper);
        String location = mediaWrapper.getLocation();
        Intrinsics.checkNotNullExpressionValue(location, "getLocation(...)");
        signalEventListeners(0, this.internalList.size() - 1, -1, location);
        if (mediaWrapper.getType() == 0) {
            this.videoCount++;
        }
    }

    public final synchronized void addEventListener(EventListener eventListener) {
        Intrinsics.checkNotNullParameter(eventListener, "listener");
        if (!this.eventListenerList.contains(eventListener)) {
            this.eventListenerList.add(eventListener);
        }
    }

    public final synchronized void removeEventListener(EventListener eventListener) {
        Intrinsics.checkNotNullParameter(eventListener, "listener");
        this.eventListenerList.remove(eventListener);
    }

    private final synchronized void signalEventListeners(int i, int i2, int i3, String str) {
        Iterator<EventListener> it = this.eventListenerList.iterator();
        while (it.hasNext()) {
            EventListener next = it.next();
            if (i == 0) {
                next.onItemAdded(i2, str);
            } else if (i == 1) {
                next.onItemRemoved(i2, str);
            } else if (i == 2) {
                next.onItemMoved(i2, i3, str);
            }
        }
    }

    public final synchronized void clear() {
        int size = this.internalList.size();
        for (int i = 0; i < size; i++) {
            String location = this.internalList.get(i).getLocation();
            Intrinsics.checkNotNullExpressionValue(location, "getLocation(...)");
            signalEventListeners(1, i, -1, location);
        }
        this.internalList.clear();
        this.videoCount = 0;
    }

    private final synchronized boolean isValid(int i) {
        boolean z;
        if (i >= 0) {
            if (i < this.internalList.size()) {
                z = true;
            }
        }
        z = false;
        return z;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0032, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void insert(int r4, org.videolan.medialibrary.interfaces.media.MediaWrapper r5) {
        /*
            r3 = this;
            monitor-enter(r3)
            java.lang.String r0 = "media"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)     // Catch:{ all -> 0x0033 }
            if (r4 >= 0) goto L_0x000a
            monitor-exit(r3)
            return
        L_0x000a:
            java.util.ArrayList<org.videolan.medialibrary.interfaces.media.MediaWrapper> r0 = r3.internalList     // Catch:{ all -> 0x0033 }
            int r1 = r0.size()     // Catch:{ all -> 0x0033 }
            int r1 = kotlin.ranges.RangesKt.coerceAtMost((int) r4, (int) r1)     // Catch:{ all -> 0x0033 }
            r0.add(r1, r5)     // Catch:{ all -> 0x0033 }
            java.lang.String r0 = r5.getLocation()     // Catch:{ all -> 0x0033 }
            java.lang.String r1 = "getLocation(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)     // Catch:{ all -> 0x0033 }
            r1 = 0
            r2 = -1
            r3.signalEventListeners(r1, r4, r2, r0)     // Catch:{ all -> 0x0033 }
            int r4 = r5.getType()     // Catch:{ all -> 0x0033 }
            if (r4 != 0) goto L_0x0031
            int r4 = r3.videoCount     // Catch:{ all -> 0x0033 }
            int r4 = r4 + 1
            r3.videoCount = r4     // Catch:{ all -> 0x0033 }
        L_0x0031:
            monitor-exit(r3)
            return
        L_0x0033:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.media.MediaWrapperList.insert(int, org.videolan.medialibrary.interfaces.media.MediaWrapper):void");
    }

    public final synchronized void move(int i, int i2) {
        if (!isValid(i) || i2 < 0 || i2 > this.internalList.size()) {
            throw new IndexOutOfBoundsException("Indexes out of range");
        }
        MediaWrapper mediaWrapper = this.internalList.get(i);
        Intrinsics.checkNotNullExpressionValue(mediaWrapper, "get(...)");
        MediaWrapper mediaWrapper2 = mediaWrapper;
        this.internalList.remove(i);
        if (i >= i2) {
            this.internalList.add(i2, mediaWrapper2);
        } else {
            this.internalList.add(i2 - 1, mediaWrapper2);
        }
        String location = mediaWrapper2.getLocation();
        Intrinsics.checkNotNullExpressionValue(location, "getLocation(...)");
        signalEventListeners(2, i, i2, location);
    }

    public final synchronized void remove(int i) {
        if (isValid(i)) {
            if (this.internalList.get(i).getType() == 0) {
                this.videoCount--;
            }
            String location = this.internalList.get(i).getLocation();
            this.internalList.remove(i);
            Intrinsics.checkNotNull(location);
            signalEventListeners(1, i, -1, location);
        }
    }

    public final synchronized void remove(String str) {
        Intrinsics.checkNotNullParameter(str, "location");
        int i = 0;
        while (i < this.internalList.size()) {
            String location = this.internalList.get(i).getLocation();
            if (Intrinsics.areEqual((Object) location, (Object) str)) {
                if (this.internalList.get(i).getType() == 0) {
                    this.videoCount--;
                }
                this.internalList.remove(i);
                Intrinsics.checkNotNull(location);
                signalEventListeners(1, i, -1, location);
                i--;
            }
            i++;
        }
    }

    public final synchronized int size() {
        return this.internalList.size();
    }

    public final synchronized MediaWrapper getMedia(int i) {
        return isValid(i) ? this.internalList.get(i) : null;
    }

    public final synchronized void replaceWith(List<? extends MediaWrapper> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        this.internalList.clear();
        this.internalList.addAll(list);
    }

    public final synchronized void map(List<? extends MediaWrapper> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        this.internalList.addAll(list);
    }

    private final synchronized String getMRL(int i) {
        return !isValid(i) ? null : this.internalList.get(i).getLocation();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("LibVLC Media List: {");
        int size = size();
        for (int i = 0; i < size; i++) {
            sb.append(String.valueOf(i));
            sb.append(": ");
            sb.append(getMRL(i));
            sb.append(", ");
        }
        sb.append("}");
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
        return sb2;
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bXT¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lorg/videolan/vlc/media/MediaWrapperList$Companion;", "", "()V", "EVENT_ADDED", "", "EVENT_MOVED", "EVENT_REMOVED", "TAG", "", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: MediaWrapperList.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
