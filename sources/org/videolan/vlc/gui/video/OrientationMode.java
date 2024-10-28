package org.videolan.vlc.gui.video;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.videolan.vlc.R;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\r\b\u0002\u0018\u0000 \u000f2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u000fB\u0019\b\u0002\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007j\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000e¨\u0006\u0010"}, d2 = {"Lorg/videolan/vlc/gui/video/OrientationMode;", "", "title", "", "value", "(Ljava/lang/String;III)V", "getTitle", "()I", "getValue", "SENSOR", "PORTRAIT", "PORTRAIT_REVERSE", "LANDSCAPE", "LANDSCAPE_REVERSE", "LANDSCAPE_SENSOR", "Companion", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideoPlayerOrientationDelegate.kt */
public enum OrientationMode {
    SENSOR(R.string.screen_orientation_sensor, -1),
    PORTRAIT(R.string.screen_orientation_portrait, 1),
    PORTRAIT_REVERSE(R.string.screen_orientation_portrait_reverse, 9),
    LANDSCAPE(R.string.screen_orientation_landscape, 0),
    LANDSCAPE_REVERSE(R.string.screen_orientation_landscape_reverse, 8),
    LANDSCAPE_SENSOR(R.string.screen_orientation_landscape_sensor, 6);
    
    public static final Companion Companion = null;
    private final int title;
    private final int value;

    public static EnumEntries<OrientationMode> getEntries() {
        return $ENTRIES;
    }

    private OrientationMode(int i, int i2) {
        this.title = i;
        this.value = i2;
    }

    public final int getTitle() {
        return this.title;
    }

    public final int getValue() {
        return this.value;
    }

    static {
        OrientationMode[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries((E[]) (Enum[]) $values);
        Companion = new Companion((DefaultConstructorMarker) null);
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lorg/videolan/vlc/gui/video/OrientationMode$Companion;", "", "()V", "findByValue", "Lorg/videolan/vlc/gui/video/OrientationMode;", "value", "", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: VideoPlayerOrientationDelegate.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final OrientationMode findByValue(int i) {
            OrientationMode orientationMode;
            OrientationMode[] values = OrientationMode.values();
            int length = values.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    orientationMode = null;
                    break;
                }
                orientationMode = values[i2];
                if (orientationMode.getValue() == i) {
                    break;
                }
                i2++;
            }
            return orientationMode == null ? OrientationMode.PORTRAIT : orientationMode;
        }
    }
}
