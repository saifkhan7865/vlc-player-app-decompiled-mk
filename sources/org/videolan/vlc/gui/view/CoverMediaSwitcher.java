package org.videolan.vlc.gui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.tools.Settings;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.helpers.UiToolsKt;
import org.videolan.vlc.util.TextUtils;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006JJ\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0012\u001a\u00020\u0013H\u0014¨\u0006\u0014"}, d2 = {"Lorg/videolan/vlc/gui/view/CoverMediaSwitcher;", "Lorg/videolan/vlc/gui/view/AudioMediaSwitcher;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "addMediaView", "", "inflater", "Landroid/view/LayoutInflater;", "title", "", "artist", "album", "cover", "Landroid/graphics/Bitmap;", "trackInfo", "hasChapters", "", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: CoverMediaSwitcher.kt */
public final class CoverMediaSwitcher extends AudioMediaSwitcher {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CoverMediaSwitcher(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributeSet, "attrs");
    }

    /* access modifiers changed from: protected */
    public void addMediaView(LayoutInflater layoutInflater, String str, String str2, String str3, Bitmap bitmap, String str4, boolean z) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        View inflate = layoutInflater.inflate(R.layout.cover_media_switcher_item, this, false);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.cover);
        TextView textView = (TextView) inflate.findViewById(R.id.song_title);
        TextView textView2 = (TextView) inflate.findViewById(R.id.song_subtitle);
        TextView textView3 = (TextView) inflate.findViewById(R.id.song_track_info);
        ImageView imageView2 = (ImageView) inflate.findViewById(R.id.previous_chapter);
        ImageView imageView3 = (ImageView) inflate.findViewById(R.id.next_chapter);
        if (z) {
            if (imageView2 != null) {
                KotlinExtensionsKt.setVisible(imageView2);
            }
            if (imageView3 != null) {
                KotlinExtensionsKt.setVisible(imageView3);
            }
        } else {
            if (imageView2 != null) {
                KotlinExtensionsKt.setGone(imageView2);
            }
            if (imageView3 != null) {
                KotlinExtensionsKt.setGone(imageView3);
            }
        }
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        } else {
            imageView.setImageDrawable(ContextCompat.getDrawable(inflate.getContext(), R.drawable.ic_no_thumbnail_song));
        }
        if (textView3 != null) {
            textView3.setVisibility(Settings.INSTANCE.getShowAudioTrackInfo() ? 0 : 8);
        }
        textView.setOnClickListener(new CoverMediaSwitcher$$ExternalSyntheticLambda0(this));
        textView2.setOnClickListener(new CoverMediaSwitcher$$ExternalSyntheticLambda1(this));
        if (imageView2 != null) {
            imageView2.setOnClickListener(new CoverMediaSwitcher$$ExternalSyntheticLambda2(this));
        }
        if (imageView3 != null) {
            imageView3.setOnClickListener(new CoverMediaSwitcher$$ExternalSyntheticLambda3(this));
        }
        textView.setText(str);
        textView2.setText(TextUtils.INSTANCE.separatedStringArgs(str2, str3));
        if (textView3 != null) {
            textView3.setText(str4);
        }
        Intrinsics.checkNotNull(textView);
        UiToolsKt.setEllipsizeModeByPref(textView, true);
        if (Settings.INSTANCE.getListTitleEllipsize() == 4) {
            textView.setSelected(true);
        }
        Intrinsics.checkNotNull(textView2);
        UiToolsKt.setEllipsizeModeByPref(textView2, true);
        if (Settings.INSTANCE.getListTitleEllipsize() == 4) {
            textView2.setSelected(true);
        }
        if (textView3 != null) {
            UiToolsKt.setEllipsizeModeByPref(textView3, true);
            if (Settings.INSTANCE.getListTitleEllipsize() == 4) {
                textView3.setSelected(true);
            }
        }
        addView(inflate);
    }

    /* access modifiers changed from: private */
    public static final void addMediaView$lambda$0(CoverMediaSwitcher coverMediaSwitcher, View view) {
        Intrinsics.checkNotNullParameter(coverMediaSwitcher, "this$0");
        coverMediaSwitcher.onTextClicked();
    }

    /* access modifiers changed from: private */
    public static final void addMediaView$lambda$1(CoverMediaSwitcher coverMediaSwitcher, View view) {
        Intrinsics.checkNotNullParameter(coverMediaSwitcher, "this$0");
        coverMediaSwitcher.onTextClicked();
    }

    /* access modifiers changed from: private */
    public static final void addMediaView$lambda$2(CoverMediaSwitcher coverMediaSwitcher, View view) {
        Intrinsics.checkNotNullParameter(coverMediaSwitcher, "this$0");
        coverMediaSwitcher.onChapterSwitching(false);
    }

    /* access modifiers changed from: private */
    public static final void addMediaView$lambda$3(CoverMediaSwitcher coverMediaSwitcher, View view) {
        Intrinsics.checkNotNullParameter(coverMediaSwitcher, "this$0");
        coverMediaSwitcher.onChapterSwitching(true);
    }
}
