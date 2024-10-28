package org.videolan.vlc.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.widget.RemoteViews;
import androidx.core.content.ContextCompat;
import androidx.palette.graphics.Palette;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.videolan.medialibrary.MLServiceLocator;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.resources.Constants;
import org.videolan.tools.AppScope;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.tools.WorkersKt;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.helpers.AudioUtil;
import org.videolan.vlc.gui.helpers.BitmapUtil;
import org.videolan.vlc.gui.helpers.BitmapUtilKt;
import org.videolan.vlc.repository.WidgetRepository;
import org.videolan.vlc.widget.utils.WidgetCacheEntry;
import org.videolan.vlc.widget.utils.WidgetType;
import org.videolan.vlc.widget.utils.WidgetUtilsKt;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\b\u0018\u0000 >2\u00020\u0001:\u0002>?B\u0005¢\u0006\u0002\u0010\u0002J*\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\fH\u0002J \u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J0\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\b\u0010\u001e\u001a\u00020\u001fH\u0002J\u0018\u0010 \u001a\u00020\f2\u0006\u0010!\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0018H\u0003J\u0010\u0010\"\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0010H\u0002JT\u0010#\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\f2\u0006\u0010$\u001a\u00020%2\b\b\u0002\u0010&\u001a\u00020\u00062\n\b\u0002\u0010'\u001a\u0004\u0018\u00010\u00162\n\b\u0002\u0010(\u001a\u0004\u0018\u00010)2\b\b\u0002\u0010*\u001a\u00020\u0006H@¢\u0006\u0002\u0010+J \u0010,\u001a\u00020\u000e2\u0006\u0010-\u001a\u00020\f2\u0006\u0010.\u001a\u00020\t2\u0006\u0010/\u001a\u000200H\u0002J.\u00101\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u00102\u001a\u0004\u0018\u0001032\u0006\u0010\u0014\u001a\u00020\f2\b\u00104\u001a\u0004\u0018\u000105H\u0016J\u001c\u00106\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u00107\u001a\u0004\u0018\u000108H\u0016J\u0018\u00109\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010$\u001a\u00020%H\u0016J \u0010:\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u00102\u001a\u0002032\u0006\u00107\u001a\u000208H\u0016J4\u0010;\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010<\u001a\u0004\u0018\u0001002\b\u0010=\u001a\u0004\u0018\u000100H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XD¢\u0006\u0002\n\u0000R\u001e\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\nX\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\f0\bj\b\u0012\u0004\u0012\u00020\f`\nX\u0004¢\u0006\u0002\n\u0000¨\u0006@"}, d2 = {"Lorg/videolan/vlc/widget/MiniPlayerAppWidgetProvider;", "Landroid/appwidget/AppWidgetProvider;", "()V", "_widgetRepository", "Lorg/videolan/vlc/repository/WidgetRepository;", "enableLogs", "", "logTypeFilter", "Ljava/util/ArrayList;", "Lorg/videolan/vlc/widget/MiniPlayerAppWidgetProvider$WidgetLogType;", "Lkotlin/collections/ArrayList;", "logWidgetIdFilter", "", "applyUpdate", "", "context", "Landroid/content/Context;", "views", "Landroid/widget/RemoteViews;", "partial", "appWidgetId", "cutBitmapCover", "Landroid/graphics/Bitmap;", "widgetType", "Lorg/videolan/vlc/widget/utils/WidgetType;", "cover", "widgetCacheEntry", "Lorg/videolan/vlc/widget/utils/WidgetCacheEntry;", "displayCover", "playing", "getFakeMedia", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "getPlayPauseImage", "isPlaying", "getWidgetRepository", "layoutWidget", "intent", "Landroid/content/Intent;", "forPreview", "previewBitmap", "previewPalette", "Landroidx/palette/graphics/Palette;", "previewPlaying", "(Landroid/content/Context;ILandroid/content/Intent;ZLandroid/graphics/Bitmap;Landroidx/palette/graphics/Palette;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "log", "widgetId", "logType", "text", "", "onAppWidgetOptionsChanged", "appWidgetManager", "Landroid/appwidget/AppWidgetManager;", "newOptions", "Landroid/os/Bundle;", "onDeleted", "appWidgetIds", "", "onReceive", "onUpdate", "setupTexts", "title", "artist", "Companion", "WidgetLogType", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MiniPlayerAppWidgetProvider.kt */
public final class MiniPlayerAppWidgetProvider extends AppWidgetProvider {
    /* access modifiers changed from: private */
    public static final String ACTION_WIDGET_DISABLED;
    /* access modifiers changed from: private */
    public static final String ACTION_WIDGET_ENABLED;
    /* access modifiers changed from: private */
    public static final String ACTION_WIDGET_INIT;
    /* access modifiers changed from: private */
    public static final String ACTION_WIDGET_PREFIX;
    /* access modifiers changed from: private */
    public static final String ACTION_WIDGET_UPDATE;
    /* access modifiers changed from: private */
    public static final String ACTION_WIDGET_UPDATE_POSITION;
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String TAG = "VLC/VLCAppWidgetProvider";
    private WidgetRepository _widgetRepository;
    private final boolean enableLogs = true;
    private final ArrayList<WidgetLogType> logTypeFilter = CollectionsKt.arrayListOf(WidgetLogType.BITMAP_GENERATION, WidgetLogType.INFO);
    private final ArrayList<Integer> logWidgetIdFilter = new ArrayList<>();

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: MiniPlayerAppWidgetProvider.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(11:0|1|2|3|4|5|6|7|8|9|11) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
        static {
            /*
                org.videolan.vlc.widget.utils.WidgetType[] r0 = org.videolan.vlc.widget.utils.WidgetType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                org.videolan.vlc.widget.utils.WidgetType r1 = org.videolan.vlc.widget.utils.WidgetType.MICRO     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                org.videolan.vlc.widget.utils.WidgetType r1 = org.videolan.vlc.widget.utils.WidgetType.PILL     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                org.videolan.vlc.widget.utils.WidgetType r1 = org.videolan.vlc.widget.utils.WidgetType.MINI     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                org.videolan.vlc.widget.utils.WidgetType r1 = org.videolan.vlc.widget.utils.WidgetType.MACRO     // Catch:{ NoSuchFieldError -> 0x002b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
            L_0x002b:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.widget.MiniPlayerAppWidgetProvider.WhenMappings.<clinit>():void");
        }
    }

    private final void log(int i, WidgetLogType widgetLogType, String str) {
    }

    /* access modifiers changed from: private */
    public final WidgetRepository getWidgetRepository(Context context) {
        WidgetRepository widgetRepository = this._widgetRepository;
        if (widgetRepository == null) {
            WidgetRepository widgetRepository2 = (WidgetRepository) WidgetRepository.Companion.getInstance(context);
            this._widgetRepository = widgetRepository2;
            if (widgetRepository2 != null) {
                return widgetRepository2;
            }
            Intrinsics.throwUninitializedPropertyAccessException("_widgetRepository");
        } else if (widgetRepository != null) {
            return widgetRepository;
        } else {
            Intrinsics.throwUninitializedPropertyAccessException("_widgetRepository");
        }
        return null;
    }

    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] iArr) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(appWidgetManager, "appWidgetManager");
        Intrinsics.checkNotNullParameter(iArr, "appWidgetIds");
        super.onUpdate(context, appWidgetManager, iArr);
        String str = ACTION_WIDGET_INIT;
        onReceive(context, new Intent(str));
        context.sendBroadcast(new Intent(str).setPackage("org.videolan.vlc"));
    }

    public void onDeleted(Context context, int[] iArr) {
        super.onDeleted(context, iArr);
        Job unused = BuildersKt__Builders_commonKt.launch$default(AppScope.INSTANCE, (CoroutineContext) null, (CoroutineStart) null, new MiniPlayerAppWidgetProvider$onDeleted$1(iArr, this, context, (Continuation<? super MiniPlayerAppWidgetProvider$onDeleted$1>) null), 3, (Object) null);
    }

    public void onReceive(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        super.onReceive(context, intent);
        WidgetLogType widgetLogType = WidgetLogType.INFO;
        log(-1, widgetLogType, "onReceive: " + intent.getAction());
        String action = intent.getAction();
        boolean areEqual = Intrinsics.areEqual((Object) ACTION_WIDGET_INIT, (Object) action) ^ true;
        Job unused = BuildersKt__Builders_commonKt.launch$default(AppScope.INSTANCE, (CoroutineContext) null, (CoroutineStart) null, new MiniPlayerAppWidgetProvider$onReceive$1(intent, getWidgetRepository(context), this, context, areEqual, (Continuation<? super MiniPlayerAppWidgetProvider$onReceive$1>) null), 3, (Object) null);
        if (action == null || !StringsKt.startsWith$default(action, VLCAppWidgetProvider.Companion.getACTION_WIDGET_PREFIX(), false, 2, (Object) null)) {
            super.onReceive(context, intent);
        }
    }

    public static /* synthetic */ Object layoutWidget$default(MiniPlayerAppWidgetProvider miniPlayerAppWidgetProvider, Context context, int i, Intent intent, boolean z, Bitmap bitmap, Palette palette, boolean z2, Continuation continuation, int i2, Object obj) {
        return miniPlayerAppWidgetProvider.layoutWidget(context, i, intent, (i2 & 8) != 0 ? false : z, (i2 & 16) != 0 ? null : bitmap, (i2 & 32) != 0 ? null : palette, (i2 & 64) != 0 ? false : z2, continuation);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v101, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v126, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v127, resolved type: boolean} */
    /* JADX WARNING: type inference failed for: r0v35, types: [androidx.palette.graphics.Palette, java.lang.Integer] */
    /* JADX WARNING: type inference failed for: r0v36 */
    /* JADX WARNING: type inference failed for: r0v43 */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0176, code lost:
        if (r7 == false) goto L_0x017d;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:101:0x0675  */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x0686  */
    /* JADX WARNING: Removed duplicated region for block: B:109:0x0693  */
    /* JADX WARNING: Removed duplicated region for block: B:115:0x06a9  */
    /* JADX WARNING: Removed duplicated region for block: B:124:0x06cc  */
    /* JADX WARNING: Removed duplicated region for block: B:128:0x06d9  */
    /* JADX WARNING: Removed duplicated region for block: B:138:0x070b  */
    /* JADX WARNING: Removed duplicated region for block: B:139:0x070d  */
    /* JADX WARNING: Removed duplicated region for block: B:145:0x071e  */
    /* JADX WARNING: Removed duplicated region for block: B:146:0x0720  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x009d  */
    /* JADX WARNING: Removed duplicated region for block: B:150:0x0744  */
    /* JADX WARNING: Removed duplicated region for block: B:151:0x0747  */
    /* JADX WARNING: Removed duplicated region for block: B:157:0x082f  */
    /* JADX WARNING: Removed duplicated region for block: B:159:0x085d  */
    /* JADX WARNING: Removed duplicated region for block: B:196:0x093e  */
    /* JADX WARNING: Removed duplicated region for block: B:197:0x0940  */
    /* JADX WARNING: Removed duplicated region for block: B:208:0x096d  */
    /* JADX WARNING: Removed duplicated region for block: B:209:0x096f  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x010d A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:213:0x097e  */
    /* JADX WARNING: Removed duplicated region for block: B:218:0x09dd  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x010f  */
    /* JADX WARNING: Removed duplicated region for block: B:221:0x0a04  */
    /* JADX WARNING: Removed duplicated region for block: B:222:0x0a06  */
    /* JADX WARNING: Removed duplicated region for block: B:225:0x0a0f  */
    /* JADX WARNING: Removed duplicated region for block: B:226:0x0a11  */
    /* JADX WARNING: Removed duplicated region for block: B:229:0x0a19  */
    /* JADX WARNING: Removed duplicated region for block: B:230:0x0a1b  */
    /* JADX WARNING: Removed duplicated region for block: B:233:0x0a23  */
    /* JADX WARNING: Removed duplicated region for block: B:234:0x0a26  */
    /* JADX WARNING: Removed duplicated region for block: B:239:0x0a32  */
    /* JADX WARNING: Removed duplicated region for block: B:240:0x0a35  */
    /* JADX WARNING: Removed duplicated region for block: B:245:0x0a41  */
    /* JADX WARNING: Removed duplicated region for block: B:246:0x0a44  */
    /* JADX WARNING: Removed duplicated region for block: B:251:0x0a50  */
    /* JADX WARNING: Removed duplicated region for block: B:252:0x0a53  */
    /* JADX WARNING: Removed duplicated region for block: B:258:0x0a61 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:266:0x0afc  */
    /* JADX WARNING: Removed duplicated region for block: B:269:0x0b15  */
    /* JADX WARNING: Removed duplicated region for block: B:270:0x0b18  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x018f  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x01ad  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x01e1  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x02b7  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x02e9  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x0321  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x038d  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x048f  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object layoutWidget(android.content.Context r34, int r35, android.content.Intent r36, boolean r37, android.graphics.Bitmap r38, androidx.palette.graphics.Palette r39, boolean r40, kotlin.coroutines.Continuation<? super android.widget.RemoteViews> r41) {
        /*
            r33 = this;
            r0 = r33
            r1 = r35
            r2 = r41
            boolean r3 = r2 instanceof org.videolan.vlc.widget.MiniPlayerAppWidgetProvider$layoutWidget$1
            if (r3 == 0) goto L_0x001a
            r3 = r2
            org.videolan.vlc.widget.MiniPlayerAppWidgetProvider$layoutWidget$1 r3 = (org.videolan.vlc.widget.MiniPlayerAppWidgetProvider$layoutWidget$1) r3
            int r4 = r3.label
            r5 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r4 & r5
            if (r4 == 0) goto L_0x001a
            int r2 = r3.label
            int r2 = r2 - r5
            r3.label = r2
            goto L_0x001f
        L_0x001a:
            org.videolan.vlc.widget.MiniPlayerAppWidgetProvider$layoutWidget$1 r3 = new org.videolan.vlc.widget.MiniPlayerAppWidgetProvider$layoutWidget$1
            r3.<init>(r0, r2)
        L_0x001f:
            java.lang.Object r2 = r3.result
            java.lang.Object r4 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r5 = r3.label
            java.lang.String r6 = ".widget"
            r7 = 2
            r9 = 1
            if (r5 == 0) goto L_0x009d
            if (r5 == r9) goto L_0x007c
            if (r5 != r7) goto L_0x0074
            int r1 = r3.I$5
            int r4 = r3.I$4
            int r5 = r3.I$3
            int r11 = r3.I$2
            int r12 = r3.I$1
            boolean r13 = r3.Z$1
            boolean r14 = r3.Z$0
            int r15 = r3.I$0
            java.lang.Object r10 = r3.L$7
            kotlin.Pair r10 = (kotlin.Pair) r10
            java.lang.Object r7 = r3.L$6
            org.videolan.vlc.widget.utils.WidgetType r7 = (org.videolan.vlc.widget.utils.WidgetType) r7
            java.lang.Object r8 = r3.L$5
            org.videolan.vlc.PlaybackService r8 = (org.videolan.vlc.PlaybackService) r8
            java.lang.Object r9 = r3.L$4
            androidx.palette.graphics.Palette r9 = (androidx.palette.graphics.Palette) r9
            r34 = r1
            java.lang.Object r1 = r3.L$3
            org.videolan.vlc.widget.utils.WidgetCacheEntry r1 = (org.videolan.vlc.widget.utils.WidgetCacheEntry) r1
            r35 = r1
            java.lang.Object r1 = r3.L$2
            android.graphics.Bitmap r1 = (android.graphics.Bitmap) r1
            r36 = r1
            java.lang.Object r1 = r3.L$1
            android.content.Context r1 = (android.content.Context) r1
            java.lang.Object r3 = r3.L$0
            org.videolan.vlc.widget.MiniPlayerAppWidgetProvider r3 = (org.videolan.vlc.widget.MiniPlayerAppWidgetProvider) r3
            kotlin.ResultKt.throwOnFailure(r2)
            r19 = r34
            r0 = r11
            r2 = r14
            r14 = r35
            r11 = r36
            goto L_0x02a4
        L_0x0074:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L_0x007c:
            int r1 = r3.I$1
            boolean r5 = r3.Z$1
            boolean r7 = r3.Z$0
            int r8 = r3.I$0
            java.lang.Object r9 = r3.L$4
            org.videolan.vlc.repository.WidgetRepository r9 = (org.videolan.vlc.repository.WidgetRepository) r9
            java.lang.Object r10 = r3.L$3
            androidx.palette.graphics.Palette r10 = (androidx.palette.graphics.Palette) r10
            java.lang.Object r11 = r3.L$2
            android.graphics.Bitmap r11 = (android.graphics.Bitmap) r11
            java.lang.Object r12 = r3.L$1
            android.content.Context r12 = (android.content.Context) r12
            java.lang.Object r13 = r3.L$0
            org.videolan.vlc.widget.MiniPlayerAppWidgetProvider r13 = (org.videolan.vlc.widget.MiniPlayerAppWidgetProvider) r13
            kotlin.ResultKt.throwOnFailure(r2)
            goto L_0x0109
        L_0x009d:
            kotlin.ResultKt.throwOnFailure(r2)
            java.lang.String r2 = ACTION_WIDGET_INIT
            java.lang.String r5 = r36.getAction()
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) r5)
            r5 = 1
            r2 = r2 ^ r5
            org.videolan.vlc.widget.MiniPlayerAppWidgetProvider$WidgetLogType r5 = org.videolan.vlc.widget.MiniPlayerAppWidgetProvider.WidgetLogType.INFO
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r8 = "layoutWidget widget id "
            r7.<init>(r8)
            r7.append(r1)
            java.lang.String r8 = " / partial: "
            r7.append(r8)
            r7.append(r2)
            java.lang.String r8 = " / action = "
            r7.append(r8)
            java.lang.String r8 = r36.getAction()
            r7.append(r8)
            java.lang.String r7 = r7.toString()
            r0.log(r1, r5, r7)
            org.videolan.vlc.repository.WidgetRepository r9 = r33.getWidgetRepository(r34)
            r3.L$0 = r0
            r5 = r34
            r3.L$1 = r5
            r7 = r38
            r3.L$2 = r7
            r8 = r39
            r3.L$3 = r8
            r3.L$4 = r9
            r3.I$0 = r1
            r10 = r37
            r3.Z$0 = r10
            r11 = r40
            r3.Z$1 = r11
            r3.I$1 = r2
            r12 = 1
            r3.label = r12
            java.lang.Object r12 = r9.getWidget(r1, r3)
            if (r12 != r4) goto L_0x00fd
            return r4
        L_0x00fd:
            r13 = r0
            r31 = r8
            r8 = r1
            r1 = r2
            r2 = r12
            r12 = r5
            r5 = r11
            r11 = r7
            r7 = r10
            r10 = r31
        L_0x0109:
            org.videolan.vlc.mediadb.models.Widget r2 = (org.videolan.vlc.mediadb.models.Widget) r2
            if (r2 != 0) goto L_0x010f
            r14 = 0
            return r14
        L_0x010f:
            if (r7 == 0) goto L_0x012d
            org.videolan.vlc.widget.utils.WidgetCacheEntry r14 = new org.videolan.vlc.widget.utils.WidgetCacheEntry
            org.videolan.medialibrary.interfaces.media.MediaWrapper r19 = r13.getFakeMedia()
            r25 = 124(0x7c, float:1.74E-43)
            r26 = 0
            r20 = 0
            r21 = 0
            r22 = 0
            r23 = 0
            r24 = 0
            r17 = r14
            r18 = r2
            r17.<init>(r18, r19, r20, r21, r22, r23, r24, r25, r26)
            goto L_0x013c
        L_0x012d:
            org.videolan.vlc.widget.utils.WidgetCache r14 = org.videolan.vlc.widget.utils.WidgetCache.INSTANCE
            org.videolan.vlc.widget.utils.WidgetCacheEntry r14 = r14.getEntry(r2)
            if (r14 != 0) goto L_0x013c
            org.videolan.vlc.widget.utils.WidgetCache r14 = org.videolan.vlc.widget.utils.WidgetCache.INSTANCE
            org.videolan.vlc.widget.utils.WidgetCacheEntry r2 = r14.addEntry(r2)
            r14 = r2
        L_0x013c:
            if (r1 != 0) goto L_0x0143
            if (r7 != 0) goto L_0x0143
            r14.reset()
        L_0x0143:
            if (r7 == 0) goto L_0x0146
            goto L_0x014b
        L_0x0146:
            androidx.palette.graphics.Palette r2 = r14.getPalette()
            r10 = r2
        L_0x014b:
            org.videolan.vlc.mediadb.models.Widget r2 = r14.getWidget()
            int r2 = org.videolan.vlc.widget.utils.WidgetUtilsKt.getForegroundColor(r2, r12, r10)
            org.videolan.vlc.mediadb.models.Widget r15 = r14.getWidget()
            int r15 = org.videolan.vlc.widget.utils.WidgetUtilsKt.getBackgroundColor(r15, r12, r10)
            org.videolan.vlc.PlaybackService$Companion r17 = org.videolan.vlc.PlaybackService.Companion
            kotlinx.coroutines.flow.MutableStateFlow r17 = r17.getServiceFlow()
            java.lang.Object r17 = r17.getValue()
            r0 = r17
            org.videolan.vlc.PlaybackService r0 = (org.videolan.vlc.PlaybackService) r0
            r17 = r4
            if (r0 == 0) goto L_0x0179
            boolean r4 = r0.isPlaying()
            r18 = r9
            r9 = 1
            if (r4 != r9) goto L_0x017b
            if (r7 == 0) goto L_0x017d
            goto L_0x017b
        L_0x0179:
            r18 = r9
        L_0x017b:
            if (r5 == 0) goto L_0x017f
        L_0x017d:
            r4 = 1
            goto L_0x0180
        L_0x017f:
            r4 = 0
        L_0x0180:
            if (r1 == 0) goto L_0x01ad
            java.lang.Integer r9 = r14.getForegroundColor()
            if (r9 != 0) goto L_0x0189
            goto L_0x01ad
        L_0x0189:
            int r9 = r9.intValue()
            if (r9 != r2) goto L_0x01ad
            org.videolan.vlc.mediadb.models.Widget r9 = r14.getWidget()
            int r9 = r9.getTheme()
            r34 = r15
            r15 = 1
            if (r9 != r15) goto L_0x01ab
            java.lang.Boolean r9 = r14.getPlaying()
            java.lang.Boolean r15 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r4)
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r9, (java.lang.Object) r15)
            if (r9 != 0) goto L_0x01ab
            goto L_0x01af
        L_0x01ab:
            r9 = 0
            goto L_0x01b0
        L_0x01ad:
            r34 = r15
        L_0x01af:
            r9 = 1
        L_0x01b0:
            java.lang.Integer r15 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r2)
            r14.setForegroundColor(r15)
            org.videolan.vlc.widget.utils.WidgetUtils r15 = org.videolan.vlc.widget.utils.WidgetUtils.INSTANCE
            r19 = r9
            org.videolan.vlc.mediadb.models.Widget r9 = r14.getWidget()
            org.videolan.vlc.widget.utils.WidgetType r9 = r15.getWidgetType(r9)
            org.videolan.vlc.widget.utils.WidgetSizeUtil r15 = org.videolan.vlc.widget.utils.WidgetSizeUtil.INSTANCE
            kotlin.Pair r15 = r15.getWidgetsSize(r12, r8)
            java.lang.Object r20 = r15.getFirst()
            java.lang.Number r20 = (java.lang.Number) r20
            int r20 = r20.intValue()
            if (r20 == 0) goto L_0x02b7
            java.lang.Object r20 = r15.getSecond()
            java.lang.Number r20 = (java.lang.Number) r20
            int r20 = r20.intValue()
            if (r20 == 0) goto L_0x02b7
            org.videolan.vlc.mediadb.models.Widget r20 = r14.getWidget()
            r35 = r4
            int r4 = r20.getWidth()
            java.lang.Object r20 = r15.getFirst()
            java.lang.Number r20 = (java.lang.Number) r20
            r36 = r2
            int r2 = r20.intValue()
            if (r4 != r2) goto L_0x0216
            org.videolan.vlc.mediadb.models.Widget r2 = r14.getWidget()
            int r2 = r2.getHeight()
            java.lang.Object r4 = r15.getSecond()
            java.lang.Number r4 = (java.lang.Number) r4
            int r4 = r4.intValue()
            if (r2 == r4) goto L_0x020e
            goto L_0x0216
        L_0x020e:
            r4 = r36
            r36 = r0
            r37 = r1
            goto L_0x02be
        L_0x0216:
            org.videolan.vlc.mediadb.models.Widget r2 = r14.getWidget()
            java.lang.Object r4 = r15.getFirst()
            java.lang.Number r4 = (java.lang.Number) r4
            int r4 = r4.intValue()
            r2.setWidth(r4)
            org.videolan.vlc.mediadb.models.Widget r2 = r14.getWidget()
            java.lang.Object r4 = r15.getSecond()
            java.lang.Number r4 = (java.lang.Number) r4
            int r4 = r4.intValue()
            r2.setHeight(r4)
            r2 = 1
            r14.setCurrentCoverInvalidated(r2)
            org.videolan.vlc.widget.MiniPlayerAppWidgetProvider$WidgetLogType r2 = org.videolan.vlc.widget.MiniPlayerAppWidgetProvider.WidgetLogType.INFO
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r37 = r1
            java.lang.String r1 = "Updating widget entry to: "
            r4.<init>(r1)
            r4.append(r14)
            r4.append(r6)
            java.lang.String r1 = r4.toString()
            r13.log(r8, r2, r1)
            org.videolan.vlc.mediadb.models.Widget r1 = r14.getWidget()
            r3.L$0 = r13
            r3.L$1 = r12
            r3.L$2 = r11
            r3.L$3 = r14
            r3.L$4 = r10
            r3.L$5 = r0
            r3.L$6 = r9
            r3.L$7 = r15
            r3.I$0 = r8
            r3.Z$0 = r7
            r3.Z$1 = r5
            r2 = r37
            r3.I$1 = r2
            r4 = r36
            r3.I$2 = r4
            r36 = r0
            r0 = r34
            r3.I$3 = r0
            r0 = r35
            r3.I$4 = r0
            r0 = r19
            r3.I$5 = r0
            r0 = 2
            r3.label = r0
            r0 = r18
            r2 = 1
            java.lang.Object r0 = r0.updateWidget(r1, r2, r3)
            r1 = r17
            if (r0 != r1) goto L_0x0293
            return r1
        L_0x0293:
            r0 = r4
            r2 = r7
            r7 = r9
            r9 = r10
            r1 = r12
            r3 = r13
            r10 = r15
            r4 = r35
            r12 = r37
            r13 = r5
            r15 = r8
            r5 = r34
            r8 = r36
        L_0x02a4:
            r31 = r2
            r2 = r0
            r0 = r8
            r8 = r5
            r5 = r13
            r13 = r3
            r3 = r15
            r15 = r9
            r9 = r7
            r7 = r31
            r32 = r14
            r14 = r1
            r1 = r12
        L_0x02b4:
            r12 = r32
            goto L_0x02d1
        L_0x02b7:
            r36 = r0
            r37 = r1
            r35 = r4
            r4 = r2
        L_0x02be:
            r0 = r36
            r1 = r37
            r2 = r4
            r3 = r8
            r8 = r34
            r4 = r35
            r31 = r15
            r15 = r10
            r10 = r31
            r32 = r14
            r14 = r12
            goto L_0x02b4
        L_0x02d1:
            java.lang.Object r17 = r10.getFirst()
            java.lang.Number r17 = (java.lang.Number) r17
            int r17 = r17.intValue()
            if (r17 != 0) goto L_0x0321
            java.lang.Object r17 = r10.getSecond()
            java.lang.Number r17 = (java.lang.Number) r17
            int r17 = r17.intValue()
            if (r17 != 0) goto L_0x0321
            org.videolan.vlc.widget.MiniPlayerAppWidgetProvider$WidgetLogType r10 = org.videolan.vlc.widget.MiniPlayerAppWidgetProvider.WidgetLogType.INFO
            r17 = r1
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r18 = r11
            java.lang.String r11 = "Size is 0. Getting size from db: "
            r1.<init>(r11)
            r1.append(r12)
            r1.append(r6)
            java.lang.String r1 = r1.toString()
            r13.log(r3, r10, r1)
            kotlin.Pair r10 = new kotlin.Pair
            org.videolan.vlc.mediadb.models.Widget r1 = r12.getWidget()
            int r1 = r1.getWidth()
            java.lang.Integer r1 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r1)
            org.videolan.vlc.mediadb.models.Widget r6 = r12.getWidget()
            int r6 = r6.getHeight()
            java.lang.Integer r6 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r6)
            r10.<init>(r1, r6)
            goto L_0x0325
        L_0x0321:
            r17 = r1
            r18 = r11
        L_0x0325:
            org.videolan.vlc.widget.MiniPlayerAppWidgetProvider$WidgetLogType r1 = org.videolan.vlc.widget.MiniPlayerAppWidgetProvider.WidgetLogType.INFO
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r11 = "New widget size by provider: "
            r6.<init>(r11)
            java.lang.Object r11 = r10.getFirst()
            java.lang.Number r11 = (java.lang.Number) r11
            int r11 = r11.intValue()
            r6.append(r11)
            java.lang.String r11 = " / "
            r6.append(r11)
            java.lang.Object r20 = r10.getSecond()
            java.lang.Number r20 = (java.lang.Number) r20
            r21 = r11
            int r11 = r20.intValue()
            r6.append(r11)
            java.lang.String r11 = " // ratio = "
            r6.append(r11)
            java.lang.Object r11 = r10.getFirst()
            java.lang.Number r11 = (java.lang.Number) r11
            int r11 = r11.intValue()
            float r11 = (float) r11
            java.lang.Object r10 = r10.getSecond()
            java.lang.Number r10 = (java.lang.Number) r10
            float r10 = r10.floatValue()
            float r11 = r11 / r10
            r6.append(r11)
            java.lang.String r6 = r6.toString()
            r13.log(r3, r1, r6)
            org.videolan.vlc.widget.utils.WidgetUtils r1 = org.videolan.vlc.widget.utils.WidgetUtils.INSTANCE
            org.videolan.vlc.mediadb.models.Widget r6 = r12.getWidget()
            org.videolan.vlc.widget.utils.WidgetType r1 = r1.getWidgetType(r6)
            android.widget.RemoteViews r6 = new android.widget.RemoteViews
            java.lang.String r10 = r14.getPackageName()
            int r11 = r1.getLayout()
            r6.<init>(r10, r11)
            if (r9 == r1) goto L_0x039a
            org.videolan.vlc.widget.utils.WidgetCache r9 = org.videolan.vlc.widget.utils.WidgetCache.INSTANCE
            org.videolan.vlc.mediadb.models.Widget r10 = r12.getWidget()
            r9.clear(r10)
            r9 = 0
            r13.applyUpdate(r14, r6, r9, r3)
        L_0x039a:
            android.content.Context r9 = r14.getApplicationContext()
            android.content.Intent r10 = new android.content.Intent
            java.lang.String r11 = org.videolan.resources.Constants.ACTION_REMOTE_BACKWARD
            r27 = r4
            java.lang.Class<org.videolan.vlc.PlaybackService> r4 = org.videolan.vlc.PlaybackService.class
            r20 = r5
            r5 = 0
            r10.<init>(r11, r5, r9, r4)
            android.content.Intent r4 = new android.content.Intent
            java.lang.String r11 = org.videolan.resources.Constants.ACTION_REMOTE_PLAYPAUSE
            r22 = r0
            java.lang.Class<org.videolan.vlc.PlaybackService> r0 = org.videolan.vlc.PlaybackService.class
            r4.<init>(r11, r5, r9, r0)
            android.content.Intent r0 = new android.content.Intent
            java.lang.String r11 = org.videolan.resources.Constants.ACTION_REMOTE_STOP
            r23 = r8
            java.lang.Class<org.videolan.vlc.PlaybackService> r8 = org.videolan.vlc.PlaybackService.class
            r0.<init>(r11, r5, r9, r8)
            android.content.Intent r8 = new android.content.Intent
            java.lang.String r11 = org.videolan.resources.Constants.ACTION_REMOTE_FORWARD
            r24 = r13
            java.lang.Class<org.videolan.vlc.PlaybackService> r13 = org.videolan.vlc.PlaybackService.class
            r8.<init>(r11, r5, r9, r13)
            android.content.Intent r11 = new android.content.Intent
            java.lang.String r13 = org.videolan.resources.Constants.ACTION_REMOTE_SEEK_FORWARD
            r28 = r2
            java.lang.Class<org.videolan.vlc.PlaybackService> r2 = org.videolan.vlc.PlaybackService.class
            r11.<init>(r13, r5, r9, r2)
            java.lang.String r2 = org.videolan.resources.Constants.EXTRA_SEEK_DELAY
            org.videolan.vlc.mediadb.models.Widget r13 = r12.getWidget()
            int r13 = r13.getForwardDelay()
            r29 = r6
            long r5 = (long) r13
            r11.putExtra(r2, r5)
            android.content.Intent r2 = new android.content.Intent
            java.lang.String r5 = org.videolan.resources.Constants.ACTION_REMOTE_SEEK_BACKWARD
            java.lang.Class<org.videolan.vlc.PlaybackService> r6 = org.videolan.vlc.PlaybackService.class
            r13 = 0
            r2.<init>(r5, r13, r9, r6)
            java.lang.String r5 = org.videolan.resources.Constants.EXTRA_SEEK_DELAY
            org.videolan.vlc.mediadb.models.Widget r6 = r12.getWidget()
            int r6 = r6.getRewindDelay()
            r25 = r7
            long r6 = (long) r6
            r2.putExtra(r5, r6)
            android.content.Intent r5 = new android.content.Intent
            java.lang.Class<org.videolan.vlc.StartActivity> r6 = org.videolan.vlc.StartActivity.class
            r5.<init>(r9, r6)
            android.content.Intent r6 = new android.content.Intent
            java.lang.String r7 = "android.appwidget.action.APPWIDGET_CONFIGURE"
            r6.<init>(r7)
            android.content.ComponentName r7 = new android.content.ComponentName
            java.lang.Class<org.videolan.vlc.widget.MiniPlayerConfigureActivity> r9 = org.videolan.vlc.widget.MiniPlayerConfigureActivity.class
            r7.<init>(r14, r9)
            r6.setComponent(r7)
            java.lang.String r7 = "appWidgetId"
            r6.putExtra(r7, r3)
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r9 = "vlc://mini_widget/"
            r7.<init>(r9)
            r7.append(r3)
            java.lang.String r7 = r7.toString()
            android.net.Uri r7 = android.net.Uri.parse(r7)
            r6.setData(r7)
            android.app.PendingIntent r7 = org.videolan.vlc.util.KextensionsKt.getPendingIntent(r14, r10)
            android.app.PendingIntent r4 = org.videolan.vlc.util.KextensionsKt.getPendingIntent(r14, r4)
            android.app.PendingIntent r0 = org.videolan.vlc.util.KextensionsKt.getPendingIntent(r14, r0)
            android.app.PendingIntent r8 = org.videolan.vlc.util.KextensionsKt.getPendingIntent(r14, r8)
            r9 = 201326592(0xc000000, float:9.8607613E-32)
            r10 = 0
            android.app.PendingIntent r5 = android.app.PendingIntent.getActivity(r14, r10, r5, r9)
            android.app.PendingIntent r6 = android.app.PendingIntent.getActivity(r14, r10, r6, r9)
            android.app.PendingIntent r9 = org.videolan.vlc.util.KextensionsKt.getPendingIntent(r14, r11)
            android.app.PendingIntent r2 = org.videolan.vlc.util.KextensionsKt.getPendingIntent(r14, r2)
            int r10 = org.videolan.vlc.R.id.backward
            r13 = r29
            r13.setOnClickPendingIntent(r10, r7)
            int r7 = org.videolan.vlc.R.id.play_pause
            r13.setOnClickPendingIntent(r7, r4)
            int r4 = org.videolan.vlc.R.id.stop
            r13.setOnClickPendingIntent(r4, r0)
            int r0 = org.videolan.vlc.R.id.forward
            r13.setOnClickPendingIntent(r0, r8)
            int r0 = org.videolan.vlc.R.id.cover
            r13.setOnClickPendingIntent(r0, r5)
            int r0 = org.videolan.vlc.R.id.app_icon
            r13.setOnClickPendingIntent(r0, r5)
            int r0 = org.videolan.vlc.R.id.widget_container
            r13.setOnClickPendingIntent(r0, r5)
            int r0 = org.videolan.vlc.R.id.widget_configure
            r13.setOnClickPendingIntent(r0, r6)
            int r0 = org.videolan.vlc.R.id.seek_rewind
            r13.setOnClickPendingIntent(r0, r2)
            int r0 = org.videolan.vlc.R.id.seek_forward
            r13.setOnClickPendingIntent(r0, r9)
            r0 = 48
            if (r19 == 0) goto L_0x0675
            org.videolan.vlc.widget.utils.WidgetType r4 = org.videolan.vlc.widget.utils.WidgetType.MACRO
            if (r1 != r4) goto L_0x04c6
            int r4 = org.videolan.vlc.R.id.cover_background
            int r5 = org.videolan.vlc.R.drawable.widget_rectangle_background
            org.videolan.vlc.mediadb.models.Widget r6 = r12.getWidget()
            int r6 = org.videolan.vlc.widget.utils.WidgetUtilsKt.getBackgroundColor(r6, r14, r15)
            org.videolan.vlc.mediadb.models.Widget r7 = r12.getWidget()
            int r7 = r7.getWidth()
            int r7 = org.videolan.tools.KotlinExtensionsKt.getDp(r7)
            java.lang.Integer r7 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r7)
            org.videolan.vlc.mediadb.models.Widget r8 = r12.getWidget()
            int r8 = r8.getWidth()
            int r8 = org.videolan.tools.KotlinExtensionsKt.getDp(r8)
            java.lang.Integer r8 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r8)
            android.graphics.Bitmap r5 = org.videolan.vlc.gui.helpers.BitmapUtilKt.getColoredBitmapFromColor(r14, r5, r6, r7, r8)
            r13.setImageViewBitmap(r4, r5)
        L_0x04c6:
            org.videolan.vlc.widget.MiniPlayerAppWidgetProvider$WidgetLogType r4 = org.videolan.vlc.widget.MiniPlayerAppWidgetProvider.WidgetLogType.BITMAP_GENERATION
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = "Bugfix Color changed!!! for widget "
            r5.<init>(r6)
            r5.append(r3)
            java.lang.String r6 = " // forPreview "
            r5.append(r6)
            r7 = r25
            r5.append(r7)
            java.lang.String r6 = " // foreground color: "
            r5.append(r6)
            r6 = 16777215(0xffffff, float:2.3509886E-38)
            r6 = r28 & r6
            java.lang.Integer r6 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r6)
            r8 = 1
            java.lang.Object[] r9 = new java.lang.Object[r8]
            r8 = 0
            r9[r8] = r6
            java.lang.String r6 = "#%06X"
            java.lang.String r6 = java.lang.String.format(r6, r9)
            r5.append(r6)
            java.lang.String r6 = " /// palette "
            r5.append(r6)
            androidx.palette.graphics.Palette r6 = r12.getPalette()
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            r6 = r24
            r6.log(r3, r4, r5)
            java.util.Locale r4 = java.util.Locale.getDefault()
            int r4 = android.text.TextUtils.getLayoutDirectionFromLocale(r4)
            r5 = 1
            if (r4 != r5) goto L_0x055f
            int r4 = org.videolan.vlc.R.id.forward
            int r5 = org.videolan.vlc.R.drawable.ic_widget_previous_normal
            r8 = 12
            r9 = 0
            r10 = 0
            r11 = 0
            r34 = r14
            r35 = r5
            r36 = r28
            r37 = r10
            r38 = r11
            r39 = r8
            r40 = r9
            android.graphics.Bitmap r5 = org.videolan.vlc.gui.helpers.BitmapUtilKt.getColoredBitmapFromColor$default(r34, r35, r36, r37, r38, r39, r40)
            r13.setImageViewBitmap(r4, r5)
            int r4 = org.videolan.vlc.R.id.backward
            int r5 = org.videolan.vlc.R.drawable.ic_widget_next_normal
            r35 = r5
            android.graphics.Bitmap r5 = org.videolan.vlc.gui.helpers.BitmapUtilKt.getColoredBitmapFromColor$default(r34, r35, r36, r37, r38, r39, r40)
            r13.setImageViewBitmap(r4, r5)
            int r4 = org.videolan.vlc.R.id.seek_rewind
            int r5 = org.videolan.vlc.R.drawable.ic_widget_forward_10
            r35 = r5
            android.graphics.Bitmap r5 = org.videolan.vlc.gui.helpers.BitmapUtilKt.getColoredBitmapFromColor$default(r34, r35, r36, r37, r38, r39, r40)
            r13.setImageViewBitmap(r4, r5)
            int r4 = org.videolan.vlc.R.id.seek_forward
            int r5 = org.videolan.vlc.R.drawable.ic_widget_rewind_10
            r35 = r5
            android.graphics.Bitmap r5 = org.videolan.vlc.gui.helpers.BitmapUtilKt.getColoredBitmapFromColor$default(r34, r35, r36, r37, r38, r39, r40)
            r13.setImageViewBitmap(r4, r5)
            goto L_0x05a4
        L_0x055f:
            int r4 = org.videolan.vlc.R.id.forward
            int r5 = org.videolan.vlc.R.drawable.ic_widget_next_normal
            r8 = 12
            r9 = 0
            r10 = 0
            r11 = 0
            r34 = r14
            r35 = r5
            r36 = r28
            r37 = r10
            r38 = r11
            r39 = r8
            r40 = r9
            android.graphics.Bitmap r5 = org.videolan.vlc.gui.helpers.BitmapUtilKt.getColoredBitmapFromColor$default(r34, r35, r36, r37, r38, r39, r40)
            r13.setImageViewBitmap(r4, r5)
            int r4 = org.videolan.vlc.R.id.backward
            int r5 = org.videolan.vlc.R.drawable.ic_widget_previous_normal
            r35 = r5
            android.graphics.Bitmap r5 = org.videolan.vlc.gui.helpers.BitmapUtilKt.getColoredBitmapFromColor$default(r34, r35, r36, r37, r38, r39, r40)
            r13.setImageViewBitmap(r4, r5)
            int r4 = org.videolan.vlc.R.id.seek_rewind
            int r5 = org.videolan.vlc.R.drawable.ic_widget_rewind_10
            r35 = r5
            android.graphics.Bitmap r5 = org.videolan.vlc.gui.helpers.BitmapUtilKt.getColoredBitmapFromColor$default(r34, r35, r36, r37, r38, r39, r40)
            r13.setImageViewBitmap(r4, r5)
            int r4 = org.videolan.vlc.R.id.seek_forward
            int r5 = org.videolan.vlc.R.drawable.ic_widget_forward_10
            r35 = r5
            android.graphics.Bitmap r5 = org.videolan.vlc.gui.helpers.BitmapUtilKt.getColoredBitmapFromColor$default(r34, r35, r36, r37, r38, r39, r40)
            r13.setImageViewBitmap(r4, r5)
        L_0x05a4:
            int r4 = org.videolan.vlc.R.id.play_pause_background
            int r5 = org.videolan.vlc.R.drawable.widget_rectangle_background
            org.videolan.vlc.mediadb.models.Widget r8 = r12.getWidget()
            int r8 = org.videolan.vlc.widget.utils.WidgetUtilsKt.getBackgroundSecondaryColor(r8, r14, r15)
            r9 = 52
            int r10 = org.videolan.tools.KotlinExtensionsKt.getDp(r9)
            java.lang.Integer r10 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r10)
            int r9 = org.videolan.tools.KotlinExtensionsKt.getDp(r9)
            java.lang.Integer r9 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r9)
            android.graphics.Bitmap r5 = org.videolan.vlc.gui.helpers.BitmapUtilKt.getColoredBitmapFromColor(r14, r5, r8, r10, r9)
            r13.setImageViewBitmap(r4, r5)
            int r4 = org.videolan.vlc.R.id.widget_configure
            org.videolan.vlc.mediadb.models.Widget r5 = r12.getWidget()
            boolean r5 = r5.getShowConfigure()
            if (r5 == 0) goto L_0x05f0
            int r5 = org.videolan.vlc.R.drawable.ic_widget_configure
            r8 = 24
            int r9 = org.videolan.tools.KotlinExtensionsKt.getDp(r8)
            java.lang.Integer r9 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r9)
            int r8 = org.videolan.tools.KotlinExtensionsKt.getDp(r8)
            java.lang.Integer r8 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r8)
            r11 = r28
            android.graphics.Bitmap r5 = org.videolan.vlc.gui.helpers.BitmapUtilKt.getColoredBitmapFromColor(r14, r5, r11, r9, r8)
            goto L_0x05f3
        L_0x05f0:
            r11 = r28
            r5 = 0
        L_0x05f3:
            r13.setImageViewBitmap(r4, r5)
            org.videolan.vlc.widget.utils.WidgetType r4 = org.videolan.vlc.widget.utils.WidgetType.PILL
            if (r1 != r4) goto L_0x061d
            int r4 = org.videolan.vlc.R.id.cover_background
            int r5 = org.videolan.vlc.R.drawable.widget_circle
            org.videolan.vlc.mediadb.models.Widget r8 = r12.getWidget()
            int r8 = org.videolan.vlc.widget.utils.WidgetUtilsKt.getSeparatorColor(r8, r14)
            int r9 = org.videolan.tools.KotlinExtensionsKt.getDp(r0)
            java.lang.Integer r9 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r9)
            int r10 = org.videolan.tools.KotlinExtensionsKt.getDp(r0)
            java.lang.Integer r10 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r10)
            android.graphics.Bitmap r5 = org.videolan.vlc.gui.helpers.BitmapUtilKt.getColoredBitmapFromColor(r14, r5, r8, r9, r10)
            r13.setImageViewBitmap(r4, r5)
        L_0x061d:
            boolean r4 = org.videolan.vlc.widget.utils.WidgetUtilsKt.isLight(r23)
            if (r4 == 0) goto L_0x0626
            int r4 = org.videolan.vlc.R.drawable.widget_touch_background
            goto L_0x0628
        L_0x0626:
            int r4 = org.videolan.vlc.R.drawable.widget_touch_background_dark
        L_0x0628:
            int r5 = org.videolan.vlc.R.id.play_pause
            java.lang.Integer r5 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r5)
            int r8 = org.videolan.vlc.R.id.forward
            java.lang.Integer r8 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r8)
            int r9 = org.videolan.vlc.R.id.backward
            java.lang.Integer r9 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r9)
            int r10 = org.videolan.vlc.R.id.seek_rewind
            java.lang.Integer r10 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r10)
            int r24 = org.videolan.vlc.R.id.seek_forward
            java.lang.Integer r24 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r24)
            int r25 = org.videolan.vlc.R.id.widget_configure
            java.lang.Integer r25 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r25)
            r0 = 6
            java.lang.Integer[] r2 = new java.lang.Integer[r0]
            r29 = 0
            r2[r29] = r5
            r5 = 1
            r2[r5] = r8
            r5 = 2
            r2[r5] = r9
            r5 = 3
            r2[r5] = r10
            r5 = 4
            r2[r5] = r24
            r8 = 5
            r2[r8] = r25
            r8 = 0
        L_0x0663:
            if (r8 >= r0) goto L_0x067c
            r9 = r2[r8]
            java.lang.Number r9 = (java.lang.Number) r9
            int r9 = r9.intValue()
            java.lang.String r10 = "setBackgroundResource"
            r13.setInt(r9, r10, r4)
            int r8 = r8 + 1
            goto L_0x0663
        L_0x0675:
            r6 = r24
            r7 = r25
            r11 = r28
            r5 = 4
        L_0x067c:
            org.videolan.tools.Settings r0 = org.videolan.tools.Settings.INSTANCE
            java.lang.Object r0 = r0.getInstance(r14)
            android.content.SharedPreferences r0 = (android.content.SharedPreferences) r0
            if (r7 != 0) goto L_0x0691
            if (r22 == 0) goto L_0x068d
            org.videolan.medialibrary.interfaces.media.MediaWrapper r2 = r22.getCurrentMediaWrapper()
            goto L_0x068e
        L_0x068d:
            r2 = 0
        L_0x068e:
            r12.setCurrentMedia(r2)
        L_0x0691:
            if (r7 == 0) goto L_0x06a9
            if (r20 != 0) goto L_0x069c
            int r2 = org.videolan.vlc.R.string.widget_default_text
            java.lang.String r2 = r14.getString(r2)
            goto L_0x06ca
        L_0x069c:
            org.videolan.medialibrary.interfaces.media.MediaWrapper r2 = r12.getCurrentMedia()
            if (r2 == 0) goto L_0x06a7
            java.lang.String r2 = r2.getTitle()
            goto L_0x06ca
        L_0x06a7:
            r2 = 0
            goto L_0x06ca
        L_0x06a9:
            if (r27 == 0) goto L_0x06be
            if (r22 == 0) goto L_0x06b3
            java.lang.String r2 = r22.getTitle()
            if (r2 != 0) goto L_0x06ca
        L_0x06b3:
            org.videolan.medialibrary.interfaces.media.MediaWrapper r2 = r12.getCurrentMedia()
            if (r2 == 0) goto L_0x06a7
            java.lang.String r2 = r2.getTitle()
            goto L_0x06ca
        L_0x06be:
            int r2 = org.videolan.vlc.R.string.widget_default_text
            java.lang.String r2 = r14.getString(r2)
            java.lang.String r4 = "key_current_audio_resume_title"
            java.lang.String r2 = r0.getString(r4, r2)
        L_0x06ca:
            if (r7 == 0) goto L_0x06d9
            org.videolan.medialibrary.interfaces.media.MediaWrapper r4 = r12.getCurrentMedia()
            if (r4 == 0) goto L_0x06d7
            java.lang.String r4 = r4.getArtist()
            goto L_0x06f6
        L_0x06d7:
            r4 = 0
            goto L_0x06f6
        L_0x06d9:
            if (r27 == 0) goto L_0x06ee
            if (r22 == 0) goto L_0x06e3
            java.lang.String r4 = r22.getArtist()
            if (r4 != 0) goto L_0x06f6
        L_0x06e3:
            org.videolan.medialibrary.interfaces.media.MediaWrapper r4 = r12.getCurrentMedia()
            if (r4 == 0) goto L_0x06d7
            java.lang.String r4 = r4.getArtist()
            goto L_0x06f6
        L_0x06ee:
            java.lang.String r4 = "key_current_audio_resume_artist"
            java.lang.String r8 = ""
            java.lang.String r4 = r0.getString(r4, r8)
        L_0x06f6:
            r34 = r6
            r35 = r14
            r36 = r13
            r37 = r1
            r38 = r2
            r39 = r4
            r34.setupTexts(r35, r36, r37, r38, r39)
            java.lang.Boolean r2 = r12.getPlaying()
            if (r27 == 0) goto L_0x070d
            r4 = 1
            goto L_0x070e
        L_0x070d:
            r4 = 0
        L_0x070e:
            java.lang.Boolean r4 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r4)
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) r4)
            if (r2 == 0) goto L_0x071a
            if (r19 == 0) goto L_0x0740
        L_0x071a:
            int r2 = org.videolan.vlc.R.id.play_pause
            if (r27 == 0) goto L_0x0720
            r4 = 1
            goto L_0x0721
        L_0x0720:
            r4 = 0
        L_0x0721:
            int r4 = r6.getPlayPauseImage(r4, r1)
            r8 = 12
            r9 = 0
            r10 = 0
            r19 = 0
            r34 = r14
            r35 = r4
            r36 = r11
            r37 = r10
            r38 = r19
            r39 = r8
            r40 = r9
            android.graphics.Bitmap r4 = org.videolan.vlc.gui.helpers.BitmapUtilKt.getColoredBitmapFromColor$default(r34, r35, r36, r37, r38, r39, r40)
            r13.setImageViewBitmap(r2, r4)
        L_0x0740:
            int r2 = org.videolan.vlc.R.id.play_pause
            if (r27 != 0) goto L_0x0747
            int r4 = org.videolan.vlc.R.string.resume_playback_short_title
            goto L_0x0749
        L_0x0747:
            int r4 = org.videolan.vlc.R.string.pause
        L_0x0749:
            java.lang.String r4 = r14.getString(r4)
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4
            r13.setContentDescription(r2, r4)
            int r2 = org.videolan.vlc.R.id.player_container_background
            java.lang.String r4 = "setColorFilter"
            r8 = r23
            r13.setInt(r2, r4, r8)
            int r2 = org.videolan.vlc.R.id.player_container_background
            org.videolan.vlc.mediadb.models.Widget r4 = r12.getWidget()
            int r4 = r4.getOpacity()
            float r4 = (float) r4
            r8 = 255(0xff, float:3.57E-43)
            float r8 = (float) r8
            float r4 = r4 * r8
            r9 = 100
            float r9 = (float) r9
            float r4 = r4 / r9
            int r4 = (int) r4
            java.lang.String r10 = "setImageAlpha"
            r13.setInt(r2, r10, r4)
            int r2 = org.videolan.vlc.R.id.player_container_background
            r4 = 1
            int r19 = org.videolan.tools.KotlinExtensionsKt.getDp(r4)
            int r23 = org.videolan.tools.KotlinExtensionsKt.getDp(r4)
            int r24 = org.videolan.tools.KotlinExtensionsKt.getDp(r4)
            int r25 = org.videolan.tools.KotlinExtensionsKt.getDp(r4)
            r34 = r13
            r35 = r2
            r36 = r19
            r37 = r23
            r38 = r24
            r39 = r25
            r34.setViewPadding(r35, r36, r37, r38, r39)
            int r2 = org.videolan.vlc.R.id.play_pause_background
            org.videolan.vlc.mediadb.models.Widget r4 = r12.getWidget()
            int r4 = r4.getOpacity()
            float r4 = (float) r4
            float r4 = r4 * r8
            float r4 = r4 / r9
            int r4 = (int) r4
            r13.setInt(r2, r10, r4)
            org.videolan.vlc.mediadb.models.Widget r2 = r12.getWidget()
            int r2 = r2.getHeight()
            int r2 = org.videolan.tools.KotlinExtensionsKt.getDp(r2)
            r4 = 48
            int r19 = org.videolan.tools.KotlinExtensionsKt.getDp(r4)
            int r2 = r2 - r19
            r4 = 2
            int r2 = r2 / r4
            org.videolan.vlc.widget.MiniPlayerAppWidgetProvider$WidgetLogType r4 = org.videolan.vlc.widget.MiniPlayerAppWidgetProvider.WidgetLogType.INFO
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r16 = r11
            java.lang.String r11 = "coverPadding: "
            r5.<init>(r11)
            r5.append(r2)
            java.lang.String r11 = ": "
            r5.append(r11)
            org.videolan.vlc.mediadb.models.Widget r11 = r12.getWidget()
            int r11 = r11.getHeight()
            r5.append(r11)
            java.lang.String r11 = " /// "
            r5.append(r11)
            r11 = 48
            int r11 = org.videolan.tools.KotlinExtensionsKt.getDp(r11)
            r5.append(r11)
            java.lang.String r5 = r5.toString()
            r6.log(r3, r4, r5)
            int r4 = org.videolan.vlc.R.id.cover_parent
            r5 = 0
            r11 = 0
            r35 = r4
            r36 = r2
            r37 = r5
            r38 = r2
            r39 = r11
            r34.setViewPadding(r35, r36, r37, r38, r39)
            int r2 = org.videolan.vlc.R.id.separator
            org.videolan.vlc.mediadb.models.Widget r4 = r12.getWidget()
            int r4 = org.videolan.vlc.widget.utils.WidgetUtilsKt.getSeparatorColor(r4, r14)
            java.lang.String r5 = "setBackgroundColor"
            r13.setInt(r2, r5, r4)
            int r2 = org.videolan.vlc.R.id.separator
            org.videolan.vlc.mediadb.models.Widget r4 = r12.getWidget()
            int r4 = r4.getOpacity()
            float r4 = (float) r4
            float r4 = r4 * r8
            float r4 = r4 / r9
            int r4 = (int) r4
            r13.setInt(r2, r10, r4)
            if (r7 == 0) goto L_0x082d
            if (r20 == 0) goto L_0x082d
            java.lang.String r2 = "fake"
            r12.setCurrentCover(r2)
        L_0x082d:
            if (r7 == 0) goto L_0x085d
            r0 = 1
            r34 = r6
            r35 = r14
            r36 = r13
            r37 = r0
            r38 = r1
            r39 = r12
            r34.displayCover(r35, r36, r37, r38, r39)
            int r0 = org.videolan.vlc.R.id.cover
            kotlin.jvm.internal.Intrinsics.checkNotNull(r18)
            r11 = r18
            android.graphics.Bitmap r2 = r6.cutBitmapCover(r1, r11, r12)
            r13.setImageViewBitmap(r0, r2)
        L_0x084d:
            r40 = r6
            r6 = r12
            r29 = r13
            r30 = r15
            r4 = r16
            r5 = r21
            r0 = 0
            r16 = r14
            goto L_0x0934
        L_0x085d:
            org.videolan.medialibrary.interfaces.media.MediaWrapper r2 = r12.getCurrentMedia()
            if (r2 == 0) goto L_0x0868
            java.lang.String r2 = r2.getArtworkMrl()
            goto L_0x0869
        L_0x0868:
            r2 = 0
        L_0x0869:
            java.lang.String r4 = r12.getCurrentCover()
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) r4)
            if (r2 == 0) goto L_0x0879
            boolean r2 = r12.getCurrentCoverInvalidated()
            if (r2 == 0) goto L_0x084d
        L_0x0879:
            r2 = 0
            r12.setCurrentCoverInvalidated(r2)
            java.lang.String r2 = "key_current_audio_resume_thumb"
            if (r22 == 0) goto L_0x0893
            boolean r4 = r22.isVideoPlaying()
            if (r4 != 0) goto L_0x0893
            org.videolan.medialibrary.interfaces.media.MediaWrapper r4 = r12.getCurrentMedia()
            if (r4 == 0) goto L_0x0893
            java.lang.String r4 = r4.getArtworkMrl()
            if (r4 != 0) goto L_0x0899
        L_0x0893:
            r4 = 0
            java.lang.String r0 = r0.getString(r2, r4)
            r4 = r0
        L_0x0899:
            r12.setCurrentCover(r4)
            java.lang.String r0 = r12.getCurrentCover()
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            if (r0 == 0) goto L_0x0910
            int r0 = r0.length()
            if (r0 != 0) goto L_0x08ba
            r40 = r6
            r6 = r12
            r29 = r13
            r30 = r15
            r4 = r16
            r5 = r21
            r0 = 0
            r16 = r14
            goto L_0x091e
        L_0x08ba:
            org.videolan.vlc.widget.MiniPlayerAppWidgetProvider$WidgetLogType r0 = org.videolan.vlc.widget.MiniPlayerAppWidgetProvider.WidgetLogType.INFO
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r4 = "Bugfix Refresh - Update cover: "
            r2.<init>(r4)
            org.videolan.medialibrary.interfaces.media.MediaWrapper r4 = r12.getCurrentMedia()
            if (r4 == 0) goto L_0x08ce
            java.lang.String r4 = r4.getArtworkMrl()
            goto L_0x08cf
        L_0x08ce:
            r4 = 0
        L_0x08cf:
            r2.append(r4)
            java.lang.String r4 = " for "
            r2.append(r4)
            org.videolan.vlc.mediadb.models.Widget r4 = r12.getWidget()
            int r4 = r4.getWidgetId()
            r2.append(r4)
            java.lang.String r2 = r2.toString()
            r6.log(r3, r0, r2)
            if (r17 == 0) goto L_0x08ed
            r0 = 1
            goto L_0x08ee
        L_0x08ed:
            r0 = 0
        L_0x08ee:
            org.videolan.vlc.widget.MiniPlayerAppWidgetProvider$$ExternalSyntheticLambda4 r2 = new org.videolan.vlc.widget.MiniPlayerAppWidgetProvider$$ExternalSyntheticLambda4
            r8 = r2
            r9 = r6
            r10 = r3
            r4 = r16
            r5 = r21
            r11 = r12
            r24 = r6
            r6 = r12
            r12 = r14
            r29 = r13
            r40 = r24
            r13 = r1
            r16 = r14
            r14 = r29
            r30 = r15
            r15 = r0
            r8.<init>(r9, r10, r11, r12, r13, r14, r15)
            org.videolan.tools.WorkersKt.runIO(r2)
            r0 = 0
            goto L_0x0934
        L_0x0910:
            r40 = r6
            r6 = r12
            r29 = r13
            r30 = r15
            r4 = r16
            r5 = r21
            r16 = r14
            r0 = 0
        L_0x091e:
            r6.setPalette(r0)
            r6.setForegroundColor(r0)
            r2 = 0
            r34 = r40
            r35 = r16
            r36 = r29
            r37 = r2
            r38 = r1
            r39 = r6
            r34.displayCover(r35, r36, r37, r38, r39)
        L_0x0934:
            if (r27 != 0) goto L_0x0950
            java.lang.String r2 = r6.getCurrentCover()
            if (r2 != 0) goto L_0x0950
            if (r27 == 0) goto L_0x0940
            r2 = 1
            goto L_0x0941
        L_0x0940:
            r2 = 0
        L_0x0941:
            r34 = r40
            r35 = r16
            r36 = r29
            r37 = r2
            r38 = r1
            r39 = r6
            r34.displayCover(r35, r36, r37, r38, r39)
        L_0x0950:
            if (r22 == 0) goto L_0x096f
            org.videolan.vlc.media.PlaylistManager r2 = r22.getPlaylistManager()
            if (r2 == 0) goto L_0x096f
            org.videolan.vlc.media.PlayerController r2 = r2.getPlayer()
            if (r2 == 0) goto L_0x096f
            androidx.lifecycle.MutableLiveData r2 = r2.getProgress()
            if (r2 == 0) goto L_0x096f
            java.lang.Object r2 = r2.getValue()
            org.videolan.vlc.media.Progress r2 = (org.videolan.vlc.media.Progress) r2
            if (r2 != 0) goto L_0x096d
            goto L_0x096f
        L_0x096d:
            r8 = r2
            goto L_0x097c
        L_0x096f:
            if (r7 == 0) goto L_0x097b
            org.videolan.vlc.media.Progress r8 = new org.videolan.vlc.media.Progress
            r9 = 3333(0xd05, double:1.6467E-320)
            r11 = 10000(0x2710, double:4.9407E-320)
            r8.<init>(r9, r11)
            goto L_0x097c
        L_0x097b:
            r8 = r0
        L_0x097c:
            if (r8 == 0) goto L_0x09dd
            long r9 = r8.getTime()
            float r0 = (float) r9
            long r9 = r8.getLength()
            float r2 = (float) r9
            float r0 = r0 / r2
            org.videolan.vlc.widget.MiniPlayerAppWidgetProvider$WidgetLogType r2 = org.videolan.vlc.widget.MiniPlayerAppWidgetProvider.WidgetLogType.BITMAP_GENERATION
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            java.lang.String r10 = "Refresh - progress updated to "
            r9.<init>(r10)
            r9.append(r0)
            java.lang.String r10 = " // "
            r9.append(r10)
            long r10 = r8.getLength()
            r9.append(r10)
            r9.append(r5)
            long r10 = r8.getTime()
            r9.append(r10)
            r5 = 32
            r9.append(r5)
            java.lang.String r5 = r9.toString()
            r13 = r40
            r13.log(r3, r2, r5)
            if (r17 == 0) goto L_0x09be
            r25 = 1
            goto L_0x09c0
        L_0x09be:
            r25 = 0
        L_0x09c0:
            org.videolan.vlc.widget.MiniPlayerAppWidgetProvider$$ExternalSyntheticLambda5 r2 = new org.videolan.vlc.widget.MiniPlayerAppWidgetProvider$$ExternalSyntheticLambda5
            r17 = r2
            r18 = r1
            r19 = r6
            r20 = r16
            r21 = r0
            r22 = r29
            r23 = r7
            r24 = r13
            r26 = r3
            r17.<init>(r18, r19, r20, r21, r22, r23, r24, r25, r26)
            org.videolan.tools.WorkersKt.runIO(r2)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            goto L_0x09df
        L_0x09dd:
            r13 = r40
        L_0x09df:
            org.videolan.vlc.widget.utils.WidgetUtils r0 = org.videolan.vlc.widget.utils.WidgetUtils.INSTANCE
            org.videolan.vlc.mediadb.models.Widget r2 = r6.getWidget()
            boolean r0 = r0.shouldShowSeek(r2, r1)
            org.videolan.vlc.widget.MiniPlayerAppWidgetProvider$WidgetLogType r1 = org.videolan.vlc.widget.MiniPlayerAppWidgetProvider.WidgetLogType.INFO
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r5 = "hasEnoughSpaceForSeek: "
            r2.<init>(r5)
            r2.append(r0)
            java.lang.String r2 = r2.toString()
            r13.log(r3, r1, r2)
            int r1 = org.videolan.vlc.R.id.progress_round
            r9 = 8
            r5 = r29
            if (r27 == 0) goto L_0x0a06
            r2 = 0
            goto L_0x0a08
        L_0x0a06:
            r2 = 8
        L_0x0a08:
            r5.setViewVisibility(r1, r2)
            int r1 = org.videolan.vlc.R.id.forward
            if (r27 == 0) goto L_0x0a11
            r2 = 0
            goto L_0x0a12
        L_0x0a11:
            r2 = 4
        L_0x0a12:
            r5.setViewVisibility(r1, r2)
            int r1 = org.videolan.vlc.R.id.backward
            if (r27 == 0) goto L_0x0a1b
            r2 = 0
            goto L_0x0a1c
        L_0x0a1b:
            r2 = 4
        L_0x0a1c:
            r5.setViewVisibility(r1, r2)
            int r1 = org.videolan.vlc.R.id.seek_forward
            if (r0 != 0) goto L_0x0a26
            r2 = 8
            goto L_0x0a2b
        L_0x0a26:
            if (r27 == 0) goto L_0x0a2a
            r2 = 0
            goto L_0x0a2b
        L_0x0a2a:
            r2 = 4
        L_0x0a2b:
            r5.setViewVisibility(r1, r2)
            int r1 = org.videolan.vlc.R.id.seek_forward_text
            if (r0 != 0) goto L_0x0a35
            r2 = 8
            goto L_0x0a3a
        L_0x0a35:
            if (r27 == 0) goto L_0x0a39
            r2 = 0
            goto L_0x0a3a
        L_0x0a39:
            r2 = 4
        L_0x0a3a:
            r5.setViewVisibility(r1, r2)
            int r1 = org.videolan.vlc.R.id.seek_rewind
            if (r0 != 0) goto L_0x0a44
            r2 = 8
            goto L_0x0a49
        L_0x0a44:
            if (r27 == 0) goto L_0x0a48
            r2 = 0
            goto L_0x0a49
        L_0x0a48:
            r2 = 4
        L_0x0a49:
            r5.setViewVisibility(r1, r2)
            int r1 = org.videolan.vlc.R.id.seek_rewind_text
            if (r0 != 0) goto L_0x0a53
            r2 = 8
            goto L_0x0a58
        L_0x0a53:
            if (r27 == 0) goto L_0x0a57
            r2 = 0
            goto L_0x0a58
        L_0x0a57:
            r2 = 4
        L_0x0a58:
            r5.setViewVisibility(r1, r2)
            int r1 = org.videolan.vlc.R.id.widget_left_space
            if (r0 != 0) goto L_0x0a61
        L_0x0a5f:
            r2 = 0
            goto L_0x0a65
        L_0x0a61:
            if (r27 == 0) goto L_0x0a5f
            r2 = 8
        L_0x0a65:
            r5.setViewVisibility(r1, r2)
            int r1 = org.videolan.vlc.R.id.widget_right_space
            if (r0 != 0) goto L_0x0a6e
        L_0x0a6c:
            r9 = 0
            goto L_0x0a70
        L_0x0a6e:
            if (r27 == 0) goto L_0x0a6c
        L_0x0a70:
            r5.setViewVisibility(r1, r9)
            int r0 = org.videolan.vlc.R.id.seek_rewind
            int r1 = org.videolan.vlc.R.string.seek_backward_content_description
            org.videolan.vlc.mediadb.models.Widget r2 = r6.getWidget()
            int r2 = r2.getRewindDelay()
            java.lang.String r2 = java.lang.String.valueOf(r2)
            r7 = 1
            java.lang.Object[] r8 = new java.lang.Object[r7]
            r9 = 0
            r8[r9] = r2
            r12 = r16
            java.lang.String r1 = r12.getString(r1, r8)
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            r5.setContentDescription(r0, r1)
            int r0 = org.videolan.vlc.R.id.seek_forward
            int r1 = org.videolan.vlc.R.string.seek_forward_content_description
            org.videolan.vlc.mediadb.models.Widget r2 = r6.getWidget()
            int r2 = r2.getForwardDelay()
            java.lang.String r2 = java.lang.String.valueOf(r2)
            java.lang.Object[] r8 = new java.lang.Object[r7]
            r8[r9] = r2
            java.lang.String r1 = r12.getString(r1, r8)
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            r5.setContentDescription(r0, r1)
            int r0 = org.videolan.vlc.R.id.songName
            r5.setTextColor(r0, r4)
            int r0 = org.videolan.vlc.R.id.artist
            org.videolan.vlc.mediadb.models.Widget r1 = r6.getWidget()
            r10 = r30
            int r1 = org.videolan.vlc.widget.utils.WidgetUtilsKt.getArtistColor(r1, r12, r10)
            r5.setTextColor(r0, r1)
            int r0 = org.videolan.vlc.R.id.seek_forward_text
            r5.setTextColor(r0, r4)
            int r0 = org.videolan.vlc.R.id.seek_rewind_text
            r5.setTextColor(r0, r4)
            int r0 = org.videolan.vlc.R.id.app_name
            r5.setTextColor(r0, r4)
            int r0 = org.videolan.vlc.R.id.seek_forward_text
            org.videolan.vlc.mediadb.models.Widget r1 = r6.getWidget()
            int r1 = r1.getForwardDelay()
            java.lang.String r1 = java.lang.String.valueOf(r1)
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            r5.setTextViewText(r0, r1)
            int r0 = org.videolan.vlc.R.id.seek_rewind_text
            org.videolan.vlc.mediadb.models.Widget r1 = r6.getWidget()
            int r1 = r1.getRewindDelay()
            java.lang.String r1 = java.lang.String.valueOf(r1)
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            r5.setTextViewText(r0, r1)
            if (r27 == 0) goto L_0x0afd
            r9 = 1
        L_0x0afd:
            java.lang.Boolean r0 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r9)
            r6.setPlaying(r0)
            org.videolan.vlc.widget.MiniPlayerAppWidgetProvider$WidgetLogType r0 = org.videolan.vlc.widget.MiniPlayerAppWidgetProvider.WidgetLogType.INFO
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Layout is "
            r1.<init>(r2)
            int r2 = r5.getLayoutId()
            int r4 = org.videolan.vlc.R.layout.widget_pill
            if (r2 != r4) goto L_0x0b18
            java.lang.String r2 = "pill"
            goto L_0x0b28
        L_0x0b18:
            int r4 = org.videolan.vlc.R.layout.widget_micro
            if (r2 != r4) goto L_0x0b1f
            java.lang.String r2 = "micro"
            goto L_0x0b28
        L_0x0b1f:
            int r4 = org.videolan.vlc.R.layout.widget_macro
            if (r2 != r4) goto L_0x0b26
            java.lang.String r2 = "macro"
            goto L_0x0b28
        L_0x0b26:
            java.lang.String r2 = "mini"
        L_0x0b28:
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r13.log(r3, r0, r1)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.widget.MiniPlayerAppWidgetProvider.layoutWidget(android.content.Context, int, android.content.Intent, boolean, android.graphics.Bitmap, androidx.palette.graphics.Palette, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    public static final void layoutWidget$lambda$6(MiniPlayerAppWidgetProvider miniPlayerAppWidgetProvider, int i, WidgetCacheEntry widgetCacheEntry, Context context, WidgetType widgetType, RemoteViews remoteViews, boolean z) {
        Intrinsics.checkNotNullParameter(miniPlayerAppWidgetProvider, "this$0");
        Intrinsics.checkNotNullParameter(widgetCacheEntry, "$widgetCacheEntry");
        Intrinsics.checkNotNullParameter(context, "$context");
        WidgetType widgetType2 = widgetType;
        Intrinsics.checkNotNullParameter(widgetType, "$widgetType");
        RemoteViews remoteViews2 = remoteViews;
        Intrinsics.checkNotNullParameter(remoteViews2, "$views");
        miniPlayerAppWidgetProvider.log(i, WidgetLogType.BITMAP_GENERATION, "Generating cover");
        Bitmap readCoverBitmap = AudioUtil.INSTANCE.readCoverBitmap(Uri.decode(widgetCacheEntry.getCurrentCover()), DilithiumEngine.DilithiumPolyT1PackedBytes);
        Object systemService = ContextCompat.getSystemService(context, WindowManager.class);
        Intrinsics.checkNotNull(systemService);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) systemService).getDefaultDisplay().getMetrics(displayMetrics);
        WorkersKt.runOnMainThread(new MiniPlayerAppWidgetProvider$$ExternalSyntheticLambda0(readCoverBitmap, displayMetrics, miniPlayerAppWidgetProvider, widgetType2, widgetCacheEntry, remoteViews2, context, z, i));
    }

    /* access modifiers changed from: private */
    public static final void layoutWidget$lambda$6$lambda$5(Bitmap bitmap, DisplayMetrics displayMetrics, MiniPlayerAppWidgetProvider miniPlayerAppWidgetProvider, WidgetType widgetType, WidgetCacheEntry widgetCacheEntry, RemoteViews remoteViews, Context context, boolean z, int i) {
        Intrinsics.checkNotNullParameter(displayMetrics, "$dm");
        Intrinsics.checkNotNullParameter(miniPlayerAppWidgetProvider, "this$0");
        Intrinsics.checkNotNullParameter(widgetType, "$widgetType");
        Intrinsics.checkNotNullParameter(widgetCacheEntry, "$widgetCacheEntry");
        Intrinsics.checkNotNullParameter(remoteViews, "$views");
        Intrinsics.checkNotNullParameter(context, "$context");
        if (bitmap == null) {
            widgetCacheEntry.setPalette((Palette) null);
            widgetCacheEntry.setForegroundColor((Integer) null);
            miniPlayerAppWidgetProvider.displayCover(context, remoteViews, false, widgetType, widgetCacheEntry);
        } else if (MiniPlayerAppWidgetProviderKt.byteSize(bitmap) < displayMetrics.widthPixels * displayMetrics.heightPixels * 6) {
            remoteViews.setImageViewBitmap(R.id.cover, miniPlayerAppWidgetProvider.cutBitmapCover(widgetType, bitmap, widgetCacheEntry));
            if (widgetCacheEntry.getWidget().getTheme() == 1) {
                widgetCacheEntry.setPalette(Palette.from(bitmap).generate());
            }
            miniPlayerAppWidgetProvider.displayCover(context, remoteViews, true, widgetType, widgetCacheEntry);
        }
        miniPlayerAppWidgetProvider.applyUpdate(context, remoteViews, z, i);
    }

    /* access modifiers changed from: private */
    public static final void layoutWidget$lambda$12$lambda$11(WidgetType widgetType, WidgetCacheEntry widgetCacheEntry, Context context, float f, RemoteViews remoteViews, boolean z, MiniPlayerAppWidgetProvider miniPlayerAppWidgetProvider, boolean z2, int i) {
        WidgetCacheEntry widgetCacheEntry2 = widgetCacheEntry;
        Context context2 = context;
        WidgetType widgetType2 = widgetType;
        Intrinsics.checkNotNullParameter(widgetType, "$widgetType");
        Intrinsics.checkNotNullParameter(widgetCacheEntry, "$widgetCacheEntry");
        Intrinsics.checkNotNullParameter(context, "$context");
        RemoteViews remoteViews2 = remoteViews;
        Intrinsics.checkNotNullParameter(remoteViews, "$views");
        Intrinsics.checkNotNullParameter(miniPlayerAppWidgetProvider, "this$0");
        int i2 = WhenMappings.$EnumSwitchMapping$0[widgetType.ordinal()];
        if (i2 == 1) {
            WidgetCacheEntry widgetCacheEntry3 = widgetCacheEntry;
            Context context3 = context;
            WorkersKt.runOnMainThread(new MiniPlayerAppWidgetProvider$$ExternalSyntheticLambda1(remoteViews, WidgetUtilsKt.generateCircularProgressbar$default(widgetCacheEntry3, context3, (float) KotlinExtensionsKt.getDp(128), f, 0.0f, 8, (Object) null), z, miniPlayerAppWidgetProvider, context, z2, i));
        } else if (i2 == 2) {
            float f2 = f;
            Bitmap generatePillProgressbar = WidgetUtilsKt.generatePillProgressbar(widgetCacheEntry, context, f);
            if (generatePillProgressbar != null) {
                WorkersKt.runOnMainThread(new MiniPlayerAppWidgetProvider$$ExternalSyntheticLambda2(remoteViews, generatePillProgressbar, z, miniPlayerAppWidgetProvider, context, z2, i));
            }
        } else if (i2 == 3 || i2 == 4) {
            float f3 = f;
            WorkersKt.runOnMainThread(new MiniPlayerAppWidgetProvider$$ExternalSyntheticLambda3(remoteViews, WidgetUtilsKt.generateCircularProgressbar(widgetCacheEntry, context, (float) KotlinExtensionsKt.getDp(32), f, (float) KotlinExtensionsKt.getDp(3)), z, miniPlayerAppWidgetProvider, context, z2, i));
        }
    }

    /* access modifiers changed from: private */
    public static final void layoutWidget$lambda$12$lambda$11$lambda$7(RemoteViews remoteViews, Bitmap bitmap, boolean z, MiniPlayerAppWidgetProvider miniPlayerAppWidgetProvider, Context context, boolean z2, int i) {
        Intrinsics.checkNotNullParameter(remoteViews, "$views");
        Intrinsics.checkNotNullParameter(bitmap, "$bitmap");
        Intrinsics.checkNotNullParameter(miniPlayerAppWidgetProvider, "this$0");
        Intrinsics.checkNotNullParameter(context, "$context");
        remoteViews.setImageViewBitmap(R.id.progress_round, bitmap);
        if (!z) {
            miniPlayerAppWidgetProvider.applyUpdate(context, remoteViews, z2, i);
        }
    }

    /* access modifiers changed from: private */
    public static final void layoutWidget$lambda$12$lambda$11$lambda$9$lambda$8(RemoteViews remoteViews, Bitmap bitmap, boolean z, MiniPlayerAppWidgetProvider miniPlayerAppWidgetProvider, Context context, boolean z2, int i) {
        Intrinsics.checkNotNullParameter(remoteViews, "$views");
        Intrinsics.checkNotNullParameter(bitmap, "$bitmap");
        Intrinsics.checkNotNullParameter(miniPlayerAppWidgetProvider, "this$0");
        Intrinsics.checkNotNullParameter(context, "$context");
        remoteViews.setImageViewBitmap(R.id.progress_round, bitmap);
        if (!z) {
            miniPlayerAppWidgetProvider.applyUpdate(context, remoteViews, z2, i);
        }
    }

    /* access modifiers changed from: private */
    public static final void layoutWidget$lambda$12$lambda$11$lambda$10(RemoteViews remoteViews, Bitmap bitmap, boolean z, MiniPlayerAppWidgetProvider miniPlayerAppWidgetProvider, Context context, boolean z2, int i) {
        Intrinsics.checkNotNullParameter(remoteViews, "$views");
        Intrinsics.checkNotNullParameter(bitmap, "$bitmap");
        Intrinsics.checkNotNullParameter(miniPlayerAppWidgetProvider, "this$0");
        Intrinsics.checkNotNullParameter(context, "$context");
        remoteViews.setImageViewBitmap(R.id.progress_round, bitmap);
        if (!z) {
            miniPlayerAppWidgetProvider.applyUpdate(context, remoteViews, z2, i);
        }
    }

    private final Bitmap cutBitmapCover(WidgetType widgetType, Bitmap bitmap, WidgetCacheEntry widgetCacheEntry) {
        int i = WhenMappings.$EnumSwitchMapping$0[widgetType.ordinal()];
        if (i == 1) {
            return BitmapUtil.INSTANCE.roundBitmap(bitmap);
        }
        if (i == 2) {
            return BitmapUtil.INSTANCE.roundBitmap(bitmap);
        }
        if (i == 3) {
            return BitmapUtil.roundedRectangleBitmap$default(BitmapUtil.INSTANCE, bitmap, KotlinExtensionsKt.getDp(widgetCacheEntry.getWidget().getHeight()), 0, 0.0f, false, false, false, false, 92, (Object) null);
        } else if (i == 4) {
            return BitmapUtil.roundedRectangleBitmap$default(BitmapUtil.INSTANCE, bitmap, KotlinExtensionsKt.getDp(widgetCacheEntry.getWidget().getWidth()), 0, 0.0f, false, false, false, false, 252, (Object) null);
        } else {
            throw new NoWhenBranchMatchedException();
        }
    }

    private final MediaWrapper getFakeMedia() {
        MediaWrapper abstractMediaWrapper = MLServiceLocator.getAbstractMediaWrapper(-1, "fakemedia://", -1, -1.0f, 1337000, 1, "Track name", "", "Artist name", "", "", "", 0, 0, "fakemedia://", 0, 0, 0, 0, 0, 0, true, false, 0, true, 1683711438317L);
        Intrinsics.checkNotNullExpressionValue(abstractMediaWrapper, "getAbstractMediaWrapper(...)");
        return abstractMediaWrapper;
    }

    private final void setupTexts(Context context, RemoteViews remoteViews, WidgetType widgetType, String str, String str2) {
        WidgetLogType widgetLogType = WidgetLogType.INFO;
        log(-1, widgetLogType, "setupTexts: " + str + " /// " + str2);
        remoteViews.setTextViewText(R.id.songName, str);
        int i = R.id.artist;
        CharSequence charSequence = str2;
        if (charSequence != null && !StringsKt.isBlank(charSequence)) {
            StringBuilder sb = new StringBuilder();
            sb.append(widgetType == WidgetType.MACRO ? "" : " · ");
            sb.append(str2);
            str2 = sb.toString();
        }
        remoteViews.setTextViewText(i, str2);
        if (Intrinsics.areEqual((Object) str, (Object) context.getString(R.string.widget_default_text))) {
            remoteViews.setViewVisibility(R.id.app_name, 0);
            remoteViews.setViewVisibility(R.id.songName, 8);
            remoteViews.setViewVisibility(R.id.artist, 8);
            return;
        }
        remoteViews.setViewVisibility(R.id.app_name, 8);
        remoteViews.setViewVisibility(R.id.songName, 0);
        remoteViews.setViewVisibility(R.id.artist, 0);
    }

    private final void displayCover(Context context, RemoteViews remoteViews, boolean z, WidgetType widgetType, WidgetCacheEntry widgetCacheEntry) {
        int i;
        if (widgetType != WidgetType.MINI || widgetCacheEntry.getWidget().getShowCover()) {
            int foregroundColor = WidgetUtilsKt.getForegroundColor(widgetCacheEntry.getWidget(), context, widgetCacheEntry.getPalette());
            int widgetId = widgetCacheEntry.getWidget().getWidgetId();
            WidgetLogType widgetLogType = WidgetLogType.INFO;
            log(widgetId, widgetLogType, "Bugfix displayCover: widgetType " + widgetType + " /// playing " + z + " /// foregroundColor " + foregroundColor + " -> " + String.format("#%06X", new Object[]{Integer.valueOf(16777215 & foregroundColor)}));
            if (!z) {
                int i2 = WhenMappings.$EnumSwitchMapping$0[widgetType.ordinal()];
                if (i2 == 2) {
                    i = KotlinExtensionsKt.getDp(24);
                } else if (i2 != 4) {
                    i = KotlinExtensionsKt.getDp(48);
                } else {
                    i = KotlinExtensionsKt.getDp(128);
                }
                remoteViews.setImageViewBitmap(R.id.app_icon, BitmapUtilKt.getColoredBitmapFromColor(context, R.drawable.ic_widget_icon, foregroundColor, Integer.valueOf(i), Integer.valueOf(i)));
                remoteViews.setViewVisibility(R.id.cover, 4);
                remoteViews.setViewVisibility(R.id.app_icon, 0);
                remoteViews.setViewVisibility(R.id.separator, 0);
                remoteViews.setViewVisibility(R.id.cover_parent, 0);
                widgetCacheEntry.setCurrentCover((String) null);
                return;
            }
            remoteViews.setViewVisibility(R.id.app_icon, 4);
            remoteViews.setViewVisibility(R.id.cover, 0);
            remoteViews.setViewVisibility(R.id.separator, 0);
            remoteViews.setViewVisibility(R.id.cover_parent, 0);
            return;
        }
        remoteViews.setViewVisibility(R.id.app_icon, 8);
        remoteViews.setViewVisibility(R.id.cover, 8);
        remoteViews.setViewVisibility(R.id.cover_background, 8);
        remoteViews.setViewVisibility(R.id.cover_parent, 8);
        remoteViews.setViewVisibility(R.id.separator, 8);
    }

    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int i, Bundle bundle) {
        if (context != null) {
            Intrinsics.checkNotNull(appWidgetManager);
            Bundle appWidgetOptions = appWidgetManager.getAppWidgetOptions(i);
            int i2 = appWidgetOptions.getInt("appWidgetMinWidth");
            int i3 = appWidgetOptions.getInt("appWidgetMinHeight");
            WidgetLogType widgetLogType = WidgetLogType.INFO;
            log(i, widgetLogType, "New widget size: " + i2 + " / " + i3);
            if (i != 0) {
                Intent intent = new Intent(ACTION_WIDGET_INIT);
                intent.putExtra("ID", i);
                Unit unit = Unit.INSTANCE;
                onReceive(context, intent);
            }
            super.onAppWidgetOptionsChanged(context, appWidgetManager, i, bundle);
        }
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000e\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0011\u0010\t\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0011\u0010\u000b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006R\u0011\u0010\r\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0006R\u0011\u0010\u000f\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0006R\u000e\u0010\u0011\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lorg/videolan/vlc/widget/MiniPlayerAppWidgetProvider$Companion;", "", "()V", "ACTION_WIDGET_DISABLED", "", "getACTION_WIDGET_DISABLED", "()Ljava/lang/String;", "ACTION_WIDGET_ENABLED", "getACTION_WIDGET_ENABLED", "ACTION_WIDGET_INIT", "getACTION_WIDGET_INIT", "ACTION_WIDGET_PREFIX", "getACTION_WIDGET_PREFIX", "ACTION_WIDGET_UPDATE", "getACTION_WIDGET_UPDATE", "ACTION_WIDGET_UPDATE_POSITION", "getACTION_WIDGET_UPDATE_POSITION", "TAG", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: MiniPlayerAppWidgetProvider.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getACTION_WIDGET_PREFIX() {
            return MiniPlayerAppWidgetProvider.ACTION_WIDGET_PREFIX;
        }

        public final String getACTION_WIDGET_INIT() {
            return MiniPlayerAppWidgetProvider.ACTION_WIDGET_INIT;
        }

        public final String getACTION_WIDGET_UPDATE() {
            return MiniPlayerAppWidgetProvider.ACTION_WIDGET_UPDATE;
        }

        public final String getACTION_WIDGET_UPDATE_POSITION() {
            return MiniPlayerAppWidgetProvider.ACTION_WIDGET_UPDATE_POSITION;
        }

        public final String getACTION_WIDGET_ENABLED() {
            return MiniPlayerAppWidgetProvider.ACTION_WIDGET_ENABLED;
        }

        public final String getACTION_WIDGET_DISABLED() {
            return MiniPlayerAppWidgetProvider.ACTION_WIDGET_DISABLED;
        }
    }

    static {
        String buildPkgString = Constants.buildPkgString("widget.mini.");
        ACTION_WIDGET_PREFIX = buildPkgString;
        ACTION_WIDGET_INIT = buildPkgString + "INIT";
        ACTION_WIDGET_UPDATE = buildPkgString + "UPDATE";
        ACTION_WIDGET_UPDATE_POSITION = buildPkgString + "UPDATE_POSITION";
        ACTION_WIDGET_ENABLED = buildPkgString + "ENABLED";
        ACTION_WIDGET_DISABLED = buildPkgString + "DISABLED";
    }

    private final int getPlayPauseImage(boolean z, WidgetType widgetType) {
        if (z) {
            return (widgetType == WidgetType.MINI || widgetType == WidgetType.MACRO) ? R.drawable.ic_widget_pause_inner : R.drawable.ic_widget_pause;
        }
        return R.drawable.ic_widget_play;
    }

    /* access modifiers changed from: private */
    public final void applyUpdate(Context context, RemoteViews remoteViews, boolean z, int i) {
        WidgetLogType widgetLogType = WidgetLogType.INFO;
        log(i, widgetLogType, "Apply update. Widget id: " + i + " Partial: " + z);
        AppWidgetManager instance = AppWidgetManager.getInstance(context);
        if (z) {
            try {
                instance.partiallyUpdateAppWidget(i, remoteViews);
            } catch (Exception e) {
                Log.e("VLC/VLCAppWidgetProvider", "Unable to update widget " + i, e);
            }
        } else {
            instance.updateAppWidget(i, remoteViews);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lorg/videolan/vlc/widget/MiniPlayerAppWidgetProvider$WidgetLogType;", "", "(Ljava/lang/String;I)V", "INFO", "BITMAP_GENERATION", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: MiniPlayerAppWidgetProvider.kt */
    public enum WidgetLogType {
        INFO,
        BITMAP_GENERATION;

        public static EnumEntries<WidgetLogType> getEntries() {
            return $ENTRIES;
        }

        static {
            WidgetLogType[] $values;
            $ENTRIES = EnumEntriesKt.enumEntries((E[]) (Enum[]) $values);
        }
    }
}
