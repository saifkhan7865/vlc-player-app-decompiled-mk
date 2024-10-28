package org.videolan.vlc.gui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.vlc.R;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006JJ\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0012\u001a\u00020\u0013H\u0014¨\u0006\u0014"}, d2 = {"Lorg/videolan/vlc/gui/view/HeaderMediaSwitcher;", "Lorg/videolan/vlc/gui/view/AudioMediaSwitcher;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "addMediaView", "", "inflater", "Landroid/view/LayoutInflater;", "title", "", "artist", "album", "cover", "Landroid/graphics/Bitmap;", "trackInfo", "hasChapters", "", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: HeaderMediaSwitcher.kt */
public final class HeaderMediaSwitcher extends AudioMediaSwitcher {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public HeaderMediaSwitcher(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributeSet, "attrs");
    }

    /* access modifiers changed from: protected */
    public void addMediaView(LayoutInflater layoutInflater, String str, String str2, String str3, Bitmap bitmap, String str4, boolean z) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        int i = 0;
        View inflate = layoutInflater.inflate(R.layout.audio_media_switcher_item, this, false);
        View findViewById = inflate.findViewById(R.id.cover);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.ImageView");
        ImageView imageView = (ImageView) findViewById;
        View findViewById2 = inflate.findViewById(R.id.title);
        Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type android.widget.TextView");
        TextView textView = (TextView) findViewById2;
        View findViewById3 = inflate.findViewById(R.id.artist);
        Intrinsics.checkNotNull(findViewById3, "null cannot be cast to non-null type android.widget.TextView");
        TextView textView2 = (TextView) findViewById3;
        if (bitmap != null) {
            imageView.setVisibility(0);
            imageView.setImageBitmap(bitmap);
        }
        textView.setText(str);
        textView.setSelected(true);
        CharSequence charSequence = str2;
        boolean z2 = true ^ (charSequence == null || charSequence.length() == 0);
        textView2.setText(charSequence);
        textView2.setSelected(z2);
        if (!z2) {
            i = 8;
        }
        textView2.setVisibility(i);
        addView(inflate);
    }
}
