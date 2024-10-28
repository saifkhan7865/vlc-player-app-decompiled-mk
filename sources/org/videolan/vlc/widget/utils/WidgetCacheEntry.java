package org.videolan.vlc.widget.utils;

import androidx.palette.graphics.Palette;
import kotlin.Metadata;
import kotlin.UInt$$ExternalSyntheticBackport0;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.vlc.mediadb.models.Widget;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b+\n\u0002\u0010\u0002\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001BS\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\r¢\u0006\u0002\u0010\u000fJ\t\u0010,\u001a\u00020\u0003HÆ\u0003J\u000b\u0010-\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010.\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010/\u001a\u0004\u0018\u00010\tHÆ\u0003J\u0010\u00100\u001a\u0004\u0018\u00010\u000bHÆ\u0003¢\u0006\u0002\u0010\u001dJ\u0010\u00101\u001a\u0004\u0018\u00010\rHÆ\u0003¢\u0006\u0002\u0010&J\t\u00102\u001a\u00020\rHÆ\u0003J^\u00103\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\b\b\u0002\u0010\u000e\u001a\u00020\rHÆ\u0001¢\u0006\u0002\u00104J\u0013\u00105\u001a\u00020\r2\b\u00106\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00107\u001a\u00020\u000bHÖ\u0001J\u0006\u00108\u001a\u000209J\t\u0010:\u001a\u00020\u0007HÖ\u0001R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u000e\u001a\u00020\rX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001e\u0010\n\u001a\u0004\u0018\u00010\u000bX\u000e¢\u0006\u0010\n\u0002\u0010 \u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001c\u0010\b\u001a\u0004\u0018\u00010\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001e\u0010\f\u001a\u0004\u0018\u00010\rX\u000e¢\u0006\u0010\n\u0002\u0010)\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b*\u0010+¨\u0006;"}, d2 = {"Lorg/videolan/vlc/widget/utils/WidgetCacheEntry;", "", "widget", "Lorg/videolan/vlc/mediadb/models/Widget;", "currentMedia", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "currentCover", "", "palette", "Landroidx/palette/graphics/Palette;", "foregroundColor", "", "playing", "", "currentCoverInvalidated", "(Lorg/videolan/vlc/mediadb/models/Widget;Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;Ljava/lang/String;Landroidx/palette/graphics/Palette;Ljava/lang/Integer;Ljava/lang/Boolean;Z)V", "getCurrentCover", "()Ljava/lang/String;", "setCurrentCover", "(Ljava/lang/String;)V", "getCurrentCoverInvalidated", "()Z", "setCurrentCoverInvalidated", "(Z)V", "getCurrentMedia", "()Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "setCurrentMedia", "(Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;)V", "getForegroundColor", "()Ljava/lang/Integer;", "setForegroundColor", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getPalette", "()Landroidx/palette/graphics/Palette;", "setPalette", "(Landroidx/palette/graphics/Palette;)V", "getPlaying", "()Ljava/lang/Boolean;", "setPlaying", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "getWidget", "()Lorg/videolan/vlc/mediadb/models/Widget;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "(Lorg/videolan/vlc/mediadb/models/Widget;Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;Ljava/lang/String;Landroidx/palette/graphics/Palette;Ljava/lang/Integer;Ljava/lang/Boolean;Z)Lorg/videolan/vlc/widget/utils/WidgetCacheEntry;", "equals", "other", "hashCode", "reset", "", "toString", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: WidgetCache.kt */
public final class WidgetCacheEntry {
    private String currentCover;
    private boolean currentCoverInvalidated;
    private MediaWrapper currentMedia;
    private Integer foregroundColor;
    private Palette palette;
    private Boolean playing;
    private final Widget widget;

    public static /* synthetic */ WidgetCacheEntry copy$default(WidgetCacheEntry widgetCacheEntry, Widget widget2, MediaWrapper mediaWrapper, String str, Palette palette2, Integer num, Boolean bool, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            widget2 = widgetCacheEntry.widget;
        }
        if ((i & 2) != 0) {
            mediaWrapper = widgetCacheEntry.currentMedia;
        }
        MediaWrapper mediaWrapper2 = mediaWrapper;
        if ((i & 4) != 0) {
            str = widgetCacheEntry.currentCover;
        }
        String str2 = str;
        if ((i & 8) != 0) {
            palette2 = widgetCacheEntry.palette;
        }
        Palette palette3 = palette2;
        if ((i & 16) != 0) {
            num = widgetCacheEntry.foregroundColor;
        }
        Integer num2 = num;
        if ((i & 32) != 0) {
            bool = widgetCacheEntry.playing;
        }
        Boolean bool2 = bool;
        if ((i & 64) != 0) {
            z = widgetCacheEntry.currentCoverInvalidated;
        }
        return widgetCacheEntry.copy(widget2, mediaWrapper2, str2, palette3, num2, bool2, z);
    }

    public final Widget component1() {
        return this.widget;
    }

    public final MediaWrapper component2() {
        return this.currentMedia;
    }

    public final String component3() {
        return this.currentCover;
    }

    public final Palette component4() {
        return this.palette;
    }

    public final Integer component5() {
        return this.foregroundColor;
    }

    public final Boolean component6() {
        return this.playing;
    }

    public final boolean component7() {
        return this.currentCoverInvalidated;
    }

    public final WidgetCacheEntry copy(Widget widget2, MediaWrapper mediaWrapper, String str, Palette palette2, Integer num, Boolean bool, boolean z) {
        Intrinsics.checkNotNullParameter(widget2, "widget");
        return new WidgetCacheEntry(widget2, mediaWrapper, str, palette2, num, bool, z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof WidgetCacheEntry)) {
            return false;
        }
        WidgetCacheEntry widgetCacheEntry = (WidgetCacheEntry) obj;
        return Intrinsics.areEqual((Object) this.widget, (Object) widgetCacheEntry.widget) && Intrinsics.areEqual((Object) this.currentMedia, (Object) widgetCacheEntry.currentMedia) && Intrinsics.areEqual((Object) this.currentCover, (Object) widgetCacheEntry.currentCover) && Intrinsics.areEqual((Object) this.palette, (Object) widgetCacheEntry.palette) && Intrinsics.areEqual((Object) this.foregroundColor, (Object) widgetCacheEntry.foregroundColor) && Intrinsics.areEqual((Object) this.playing, (Object) widgetCacheEntry.playing) && this.currentCoverInvalidated == widgetCacheEntry.currentCoverInvalidated;
    }

    public int hashCode() {
        int hashCode = this.widget.hashCode() * 31;
        MediaWrapper mediaWrapper = this.currentMedia;
        int i = 0;
        int hashCode2 = (hashCode + (mediaWrapper == null ? 0 : mediaWrapper.hashCode())) * 31;
        String str = this.currentCover;
        int hashCode3 = (hashCode2 + (str == null ? 0 : str.hashCode())) * 31;
        Palette palette2 = this.palette;
        int hashCode4 = (hashCode3 + (palette2 == null ? 0 : palette2.hashCode())) * 31;
        Integer num = this.foregroundColor;
        int hashCode5 = (hashCode4 + (num == null ? 0 : num.hashCode())) * 31;
        Boolean bool = this.playing;
        if (bool != null) {
            i = bool.hashCode();
        }
        return ((hashCode5 + i) * 31) + UInt$$ExternalSyntheticBackport0.m(this.currentCoverInvalidated);
    }

    public String toString() {
        return "WidgetCacheEntry(widget=" + this.widget + ", currentMedia=" + this.currentMedia + ", currentCover=" + this.currentCover + ", palette=" + this.palette + ", foregroundColor=" + this.foregroundColor + ", playing=" + this.playing + ", currentCoverInvalidated=" + this.currentCoverInvalidated + ')';
    }

    public WidgetCacheEntry(Widget widget2, MediaWrapper mediaWrapper, String str, Palette palette2, Integer num, Boolean bool, boolean z) {
        Intrinsics.checkNotNullParameter(widget2, "widget");
        this.widget = widget2;
        this.currentMedia = mediaWrapper;
        this.currentCover = str;
        this.palette = palette2;
        this.foregroundColor = num;
        this.playing = bool;
        this.currentCoverInvalidated = z;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ WidgetCacheEntry(org.videolan.vlc.mediadb.models.Widget r7, org.videolan.medialibrary.interfaces.media.MediaWrapper r8, java.lang.String r9, androidx.palette.graphics.Palette r10, java.lang.Integer r11, java.lang.Boolean r12, boolean r13, int r14, kotlin.jvm.internal.DefaultConstructorMarker r15) {
        /*
            r6 = this;
            r0 = r14 & 2
            r1 = 0
            if (r0 == 0) goto L_0x0007
            r0 = r1
            goto L_0x0008
        L_0x0007:
            r0 = r8
        L_0x0008:
            r2 = r14 & 4
            if (r2 == 0) goto L_0x000e
            r2 = r1
            goto L_0x000f
        L_0x000e:
            r2 = r9
        L_0x000f:
            r3 = r14 & 8
            if (r3 == 0) goto L_0x0015
            r3 = r1
            goto L_0x0016
        L_0x0015:
            r3 = r10
        L_0x0016:
            r4 = r14 & 16
            if (r4 == 0) goto L_0x001c
            r4 = r1
            goto L_0x001d
        L_0x001c:
            r4 = r11
        L_0x001d:
            r5 = r14 & 32
            if (r5 == 0) goto L_0x0022
            goto L_0x0023
        L_0x0022:
            r1 = r12
        L_0x0023:
            r5 = r14 & 64
            if (r5 == 0) goto L_0x0029
            r5 = 0
            goto L_0x002a
        L_0x0029:
            r5 = r13
        L_0x002a:
            r8 = r6
            r9 = r7
            r10 = r0
            r11 = r2
            r12 = r3
            r13 = r4
            r14 = r1
            r15 = r5
            r8.<init>(r9, r10, r11, r12, r13, r14, r15)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.widget.utils.WidgetCacheEntry.<init>(org.videolan.vlc.mediadb.models.Widget, org.videolan.medialibrary.interfaces.media.MediaWrapper, java.lang.String, androidx.palette.graphics.Palette, java.lang.Integer, java.lang.Boolean, boolean, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final String getCurrentCover() {
        return this.currentCover;
    }

    public final boolean getCurrentCoverInvalidated() {
        return this.currentCoverInvalidated;
    }

    public final MediaWrapper getCurrentMedia() {
        return this.currentMedia;
    }

    public final Integer getForegroundColor() {
        return this.foregroundColor;
    }

    public final Palette getPalette() {
        return this.palette;
    }

    public final Boolean getPlaying() {
        return this.playing;
    }

    public final Widget getWidget() {
        return this.widget;
    }

    public final void setCurrentCover(String str) {
        this.currentCover = str;
    }

    public final void setCurrentCoverInvalidated(boolean z) {
        this.currentCoverInvalidated = z;
    }

    public final void setCurrentMedia(MediaWrapper mediaWrapper) {
        this.currentMedia = mediaWrapper;
    }

    public final void setForegroundColor(Integer num) {
        this.foregroundColor = num;
    }

    public final void setPalette(Palette palette2) {
        this.palette = palette2;
    }

    public final void setPlaying(Boolean bool) {
        this.playing = bool;
    }

    public final void reset() {
        this.currentCover = null;
        this.currentMedia = null;
    }
}
