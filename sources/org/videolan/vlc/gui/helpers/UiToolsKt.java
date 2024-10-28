package org.videolan.vlc.gui.helpers;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.resources.Constants;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.BaseActivity;
import org.videolan.vlc.util.LifecycleAwareScheduler;

@Metadata(d1 = {"\u0000T\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005\u001a,\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH@¢\u0006\u0002\u0010\u000f\u001a\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000e\u001a\u001f\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0013\u001a\u0004\u0018\u00010\u0016H\u0007¢\u0006\u0002\u0010\u0017\u001a\u001f\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0013\u001a\u0004\u0018\u00010\u0016H\u0007¢\u0006\u0002\u0010\u0017\u001a\u001f\u0010\u0019\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0013\u001a\u0004\u0018\u00010\u0016H\u0007¢\u0006\u0002\u0010\u0017\u001a\u0018\u0010\u001a\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0016H\u0007\u001a\n\u0010\u001e\u001a\u00020\u0007*\u00020\u001f\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"MARQUEE_ACTION", "", "enableMarqueeEffect", "Lorg/videolan/vlc/util/LifecycleAwareScheduler;", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "fillActionMode", "", "context", "Landroid/content/Context;", "mode", "Landroidx/appcompat/view/ActionMode;", "multiSelectHelper", "Lorg/videolan/tools/MultiSelectHelper;", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "(Landroid/content/Context;Landroidx/appcompat/view/ActionMode;Lorg/videolan/tools/MultiSelectHelper;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTvIconRes", "", "mediaLibraryItem", "isSelected", "v", "Landroid/view/View;", "", "(Landroid/view/View;Ljava/lang/Boolean;)V", "selectedElevation", "selectedPadding", "setEllipsizeModeByPref", "t", "Landroid/widget/TextView;", "activated", "applyTheme", "Lorg/videolan/vlc/gui/BaseActivity;", "vlc-android_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: UiTools.kt */
public final class UiToolsKt {
    public static final String MARQUEE_ACTION = "marquee_action";

    @BindingAdapter({"ellipsizeMode"})
    public static final void setEllipsizeModeByPref(TextView textView, boolean z) {
        Intrinsics.checkNotNullParameter(textView, "t");
        if (z) {
            int listTitleEllipsize = Settings.INSTANCE.getListTitleEllipsize();
            if (listTitleEllipsize == 1) {
                textView.setEllipsize(TextUtils.TruncateAt.START);
            } else if (listTitleEllipsize == 2) {
                textView.setEllipsize(TextUtils.TruncateAt.END);
            } else if (listTitleEllipsize == 3) {
                textView.setEllipsize(TextUtils.TruncateAt.MIDDLE);
            } else if (listTitleEllipsize == 4) {
                textView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
                textView.setMarqueeRepeatLimit(1);
            }
        }
    }

    public static final LifecycleAwareScheduler enableMarqueeEffect(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        LinearLayoutManager linearLayoutManager = layoutManager instanceof LinearLayoutManager ? (LinearLayoutManager) layoutManager : null;
        if (linearLayoutManager == null) {
            return null;
        }
        LifecycleAwareScheduler lifecycleAwareScheduler = new LifecycleAwareScheduler(new UiToolsKt$enableMarqueeEffect$1$scheduler$1(linearLayoutManager, recyclerView));
        LifecycleAwareScheduler.scheduleAction$default(lifecycleAwareScheduler, MARQUEE_ACTION, 1500, (Bundle) null, 4, (Object) null);
        recyclerView.addOnScrollListener(new UiToolsKt$enableMarqueeEffect$1$1(lifecycleAwareScheduler));
        return lifecycleAwareScheduler;
    }

    @BindingAdapter({"selected"})
    public static final void isSelected(View view, Boolean bool) {
        Intrinsics.checkNotNullParameter(view, "v");
        Intrinsics.checkNotNull(bool);
        view.setSelected(bool.booleanValue());
    }

    @BindingAdapter({"selectedPadding"})
    public static final void selectedPadding(View view, Boolean bool) {
        Intrinsics.checkNotNullParameter(view, "v");
        int dp = KotlinExtensionsKt.getDp(Intrinsics.areEqual((Object) bool, (Object) true) ? 16 : 0);
        view.setPadding(dp, dp, dp, dp);
    }

    @BindingAdapter({"selectedElevation"})
    public static final void selectedElevation(View view, Boolean bool) {
        Intrinsics.checkNotNullParameter(view, "v");
        if (Build.VERSION.SDK_INT >= 21) {
            int dp = KotlinExtensionsKt.getDp(Intrinsics.areEqual((Object) bool, (Object) true) ? 0 : 4);
            if (view instanceof CardView) {
                ((CardView) view).setCardElevation((float) dp);
            } else {
                view.setElevation((float) dp);
            }
        }
    }

    public static final void applyTheme(BaseActivity baseActivity) {
        Intrinsics.checkNotNullParameter(baseActivity, "<this>");
        Integer forcedTheme = baseActivity.forcedTheme();
        if (forcedTheme != null) {
            baseActivity.setTheme(forcedTheme.intValue());
        } else if (Settings.INSTANCE.getShowTvUi()) {
            AppCompatDelegate.setDefaultNightMode(1);
            baseActivity.setTheme(R.style.Theme_VLC_Black);
        } else {
            String string = baseActivity.getSettings().getString(SettingsKt.KEY_APP_THEME, Constants.GROUP_VIDEOS_NONE);
            Intrinsics.checkNotNull(string);
            Integer valueOf = Integer.valueOf(string);
            Intrinsics.checkNotNullExpressionValue(valueOf, "valueOf(...)");
            AppCompatDelegate.setDefaultNightMode(valueOf.intValue());
        }
    }

    public static final int getTvIconRes(MediaLibraryItem mediaLibraryItem) {
        Intrinsics.checkNotNullParameter(mediaLibraryItem, "mediaLibraryItem");
        int itemType = mediaLibraryItem.getItemType();
        if (itemType == 2) {
            return R.drawable.ic_album_big;
        }
        if (itemType == 4) {
            return R.drawable.ic_artist_big;
        }
        if (itemType == 8) {
            return R.drawable.ic_genre_big;
        }
        if (itemType == 32) {
            MediaWrapper mediaWrapper = (MediaWrapper) mediaLibraryItem;
            int type = mediaWrapper.getType();
            if (type == 0) {
                return R.drawable.ic_video_big;
            }
            if (type == 1) {
                return R.drawable.ic_song_big;
            }
            if (type != 3) {
                return R.drawable.ic_unknown_big;
            }
            return Intrinsics.areEqual((Object) mediaWrapper.getUri().getScheme(), (Object) "file") ? R.drawable.ic_folder_big : R.drawable.ic_network_big;
        } else if (itemType != 64) {
            return R.drawable.ic_unknown_big;
        } else {
            long id = mediaLibraryItem.getId();
            if (id == 0) {
                return R.drawable.ic_video_big;
            }
            if (id == 35) {
                return R.drawable.ic_permission_big;
            }
            if (id == 4) {
                return R.drawable.ic_folder_big;
            }
            if (id == 3) {
                return R.drawable.ic_network_big;
            }
            if (id == 7) {
                return R.drawable.ic_network_add_big;
            }
            if (id == 6) {
                return R.drawable.ic_stream_big;
            }
            if (id == 8) {
                return R.drawable.ic_playlist_big;
            }
            if (id == 30 || id == 26) {
                return R.drawable.ic_browser_movie_big;
            }
            if (id == 31) {
                return R.drawable.ic_browser_tvshow_big;
            }
            if (id == 10) {
                return R.drawable.ic_settings_big;
            }
            if (id == 11) {
                return R.drawable.ic_default_cone;
            }
            if (id == 18) {
                return R.drawable.ic_remote_access_big;
            }
            if (id == 16) {
                return R.drawable.ic_donate_big;
            }
            if (id == 21) {
                return R.drawable.ic_artist_big;
            }
            if (id == 22) {
                return R.drawable.ic_album_big;
            }
            if (id == 23) {
                return R.drawable.ic_genre_big;
            }
            if (id == 24 || id == 20) {
                return R.drawable.ic_song_big;
            }
            return R.drawable.ic_unknown_big;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0048  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x008b  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object fillActionMode(android.content.Context r14, androidx.appcompat.view.ActionMode r15, org.videolan.tools.MultiSelectHelper<org.videolan.medialibrary.media.MediaLibraryItem> r16, kotlin.coroutines.Continuation<? super kotlin.Unit> r17) {
        /*
            r0 = r17
            boolean r1 = r0 instanceof org.videolan.vlc.gui.helpers.UiToolsKt$fillActionMode$1
            if (r1 == 0) goto L_0x0016
            r1 = r0
            org.videolan.vlc.gui.helpers.UiToolsKt$fillActionMode$1 r1 = (org.videolan.vlc.gui.helpers.UiToolsKt$fillActionMode$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0016
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x001b
        L_0x0016:
            org.videolan.vlc.gui.helpers.UiToolsKt$fillActionMode$1 r1 = new org.videolan.vlc.gui.helpers.UiToolsKt$fillActionMode$1
            r1.<init>(r0)
        L_0x001b:
            java.lang.Object r0 = r1.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            r4 = 1
            if (r3 == 0) goto L_0x0048
            if (r3 != r4) goto L_0x0040
            java.lang.Object r2 = r1.L$4
            kotlin.jvm.internal.Ref$BooleanRef r2 = (kotlin.jvm.internal.Ref.BooleanRef) r2
            java.lang.Object r3 = r1.L$3
            kotlin.jvm.internal.Ref$LongRef r3 = (kotlin.jvm.internal.Ref.LongRef) r3
            java.lang.Object r5 = r1.L$2
            kotlin.jvm.internal.Ref$IntRef r5 = (kotlin.jvm.internal.Ref.IntRef) r5
            java.lang.Object r6 = r1.L$1
            androidx.appcompat.view.ActionMode r6 = (androidx.appcompat.view.ActionMode) r6
            java.lang.Object r1 = r1.L$0
            android.content.Context r1 = (android.content.Context) r1
            kotlin.ResultKt.throwOnFailure(r0)
            goto L_0x0087
        L_0x0040:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0048:
            kotlin.ResultKt.throwOnFailure(r0)
            kotlin.jvm.internal.Ref$IntRef r0 = new kotlin.jvm.internal.Ref$IntRef
            r0.<init>()
            kotlin.jvm.internal.Ref$LongRef r3 = new kotlin.jvm.internal.Ref$LongRef
            r3.<init>()
            kotlin.jvm.internal.Ref$BooleanRef r11 = new kotlin.jvm.internal.Ref$BooleanRef
            r11.<init>()
            kotlinx.coroutines.CoroutineDispatcher r5 = kotlinx.coroutines.Dispatchers.getIO()
            r12 = r5
            kotlin.coroutines.CoroutineContext r12 = (kotlin.coroutines.CoroutineContext) r12
            org.videolan.vlc.gui.helpers.UiToolsKt$fillActionMode$2 r13 = new org.videolan.vlc.gui.helpers.UiToolsKt$fillActionMode$2
            r10 = 0
            r5 = r13
            r6 = r16
            r7 = r11
            r8 = r0
            r9 = r3
            r5.<init>(r6, r7, r8, r9, r10)
            kotlin.jvm.functions.Function2 r13 = (kotlin.jvm.functions.Function2) r13
            r5 = r14
            r1.L$0 = r5
            r6 = r15
            r1.L$1 = r6
            r1.L$2 = r0
            r1.L$3 = r3
            r1.L$4 = r11
            r1.label = r4
            java.lang.Object r1 = kotlinx.coroutines.BuildersKt.withContext(r12, r13, r1)
            if (r1 != r2) goto L_0x0084
            return r2
        L_0x0084:
            r1 = r5
            r2 = r11
            r5 = r0
        L_0x0087:
            boolean r0 = r2.element
            if (r0 == 0) goto L_0x00ac
            int r0 = org.videolan.vlc.R.string.selection_count
            int r2 = r5.element
            java.lang.Integer r2 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r2)
            java.lang.Object[] r4 = new java.lang.Object[r4]
            r5 = 0
            r4[r5] = r2
            java.lang.String r0 = r1.getString(r0, r4)
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            r6.setTitle((java.lang.CharSequence) r0)
            long r0 = r3.element
            java.lang.String r0 = org.videolan.medialibrary.Tools.millisToString(r0)
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            r6.setSubtitle((java.lang.CharSequence) r0)
        L_0x00ac:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.helpers.UiToolsKt.fillActionMode(android.content.Context, androidx.appcompat.view.ActionMode, org.videolan.tools.MultiSelectHelper, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
