package org.videolan.vlc.widget.utils;

import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.vlc.mediadb.models.Widget;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nJ\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u0007J\u0016\u0010\u000e\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u0007¨\u0006\u000f"}, d2 = {"Lorg/videolan/vlc/widget/utils/WidgetUtils;", "", "()V", "getMinimalWidgetSize", "Lkotlin/Pair;", "", "type", "Lorg/videolan/vlc/widget/utils/WidgetType;", "getWidgetType", "widget", "Lorg/videolan/vlc/mediadb/models/Widget;", "hasEnoughSpaceForSeek", "", "widgetType", "shouldShowSeek", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: WidgetUtils.kt */
public final class WidgetUtils {
    public static final WidgetUtils INSTANCE = new WidgetUtils();

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: WidgetUtils.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(9:0|1|2|3|4|5|6|7|9) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        static {
            /*
                org.videolan.vlc.widget.utils.WidgetType[] r0 = org.videolan.vlc.widget.utils.WidgetType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                org.videolan.vlc.widget.utils.WidgetType r1 = org.videolan.vlc.widget.utils.WidgetType.MACRO     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                org.videolan.vlc.widget.utils.WidgetType r1 = org.videolan.vlc.widget.utils.WidgetType.MINI     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                org.videolan.vlc.widget.utils.WidgetType r1 = org.videolan.vlc.widget.utils.WidgetType.MICRO     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.widget.utils.WidgetUtils.WhenMappings.<clinit>():void");
        }
    }

    private WidgetUtils() {
    }

    public final WidgetType getWidgetType(Widget widget) {
        Intrinsics.checkNotNullParameter(widget, "widget");
        if (widget.getType() == 1) {
            return WidgetType.PILL;
        }
        if (widget.getType() == 2) {
            return WidgetType.MINI;
        }
        if (widget.getType() == 3) {
            return WidgetType.MICRO;
        }
        if (widget.getType() == 4) {
            return WidgetType.MACRO;
        }
        if (widget.getWidth() > 220 && widget.getHeight() > 220) {
            return WidgetType.MACRO;
        }
        if (widget.getWidth() > 220 && widget.getHeight() > 72) {
            return WidgetType.MINI;
        }
        if (widget.getWidth() <= 128 || widget.getHeight() <= 148) {
            return WidgetType.PILL;
        }
        return WidgetType.MICRO;
    }

    public final Pair<Integer, Integer> getMinimalWidgetSize(WidgetType widgetType) {
        Intrinsics.checkNotNullParameter(widgetType, "type");
        int i = WhenMappings.$EnumSwitchMapping$0[widgetType.ordinal()];
        if (i == 1) {
            return new Pair<>(220, 200);
        }
        if (i == 2) {
            return new Pair<>(220, 72);
        }
        if (i != 3) {
            return new Pair<>(0, 0);
        }
        return new Pair<>(128, 148);
    }

    /* JADX WARNING: Removed duplicated region for block: B:8:0x0046 A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean hasEnoughSpaceForSeek(org.videolan.vlc.mediadb.models.Widget r5, org.videolan.vlc.widget.utils.WidgetType r6) {
        /*
            r4 = this;
            java.lang.String r0 = "widget"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            java.lang.String r0 = "widgetType"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            int[] r0 = org.videolan.vlc.widget.utils.WidgetUtils.WhenMappings.$EnumSwitchMapping$0
            int r6 = r6.ordinal()
            r6 = r0[r6]
            r0 = 48
            r1 = 0
            r2 = 1
            if (r6 == r2) goto L_0x0036
            r3 = 2
            if (r6 == r3) goto L_0x001c
            goto L_0x0047
        L_0x001c:
            int r6 = r5.getWidth()
            int r6 = org.videolan.tools.KotlinExtensionsKt.getDp(r6)
            int r5 = r5.getHeight()
            int r5 = org.videolan.tools.KotlinExtensionsKt.getDp(r5)
            int r0 = org.videolan.tools.KotlinExtensionsKt.getDp(r0)
            int r0 = r0 * 5
            int r5 = r5 + r0
            if (r6 <= r5) goto L_0x0047
            goto L_0x0046
        L_0x0036:
            int r5 = r5.getWidth()
            int r5 = org.videolan.tools.KotlinExtensionsKt.getDp(r5)
            int r6 = org.videolan.tools.KotlinExtensionsKt.getDp(r0)
            int r6 = r6 * 5
            if (r5 <= r6) goto L_0x0047
        L_0x0046:
            r1 = 1
        L_0x0047:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.widget.utils.WidgetUtils.hasEnoughSpaceForSeek(org.videolan.vlc.mediadb.models.Widget, org.videolan.vlc.widget.utils.WidgetType):boolean");
    }

    public final boolean shouldShowSeek(Widget widget, WidgetType widgetType) {
        Intrinsics.checkNotNullParameter(widget, "widget");
        Intrinsics.checkNotNullParameter(widgetType, "widgetType");
        return widget.getShowSeek() && hasEnoughSpaceForSeek(widget, widgetType);
    }
}
