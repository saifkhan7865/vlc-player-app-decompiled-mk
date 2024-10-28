package org.videolan.vlc.gui.audio;

import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager.widget.PagerAdapter;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B#\u0012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\u0002\u0010\u0007J\b\u0010\n\u001a\u00020\u000bH\u0016J\u0012\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000bH\u0016J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u000e\u001a\u00020\u000bH\u0016J\u0018\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0010H\u0016R\u0018\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003X\u0004¢\u0006\u0004\n\u0002\u0010\bR\u0016\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003X\u0004¢\u0006\u0004\n\u0002\u0010\t¨\u0006\u0017"}, d2 = {"Lorg/videolan/vlc/gui/audio/AudioPagerAdapter;", "Landroidx/viewpager/widget/PagerAdapter;", "lists", "", "Landroid/view/View;", "titles", "", "([Landroid/view/View;[Ljava/lang/String;)V", "[Landroid/view/View;", "[Ljava/lang/String;", "getCount", "", "getPageTitle", "", "position", "instantiateItem", "", "container", "Landroid/view/ViewGroup;", "isViewFromObject", "", "view", "object", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: AudioPagerAdapter.kt */
public final class AudioPagerAdapter extends PagerAdapter {
    private final View[] lists;
    private final String[] titles;

    public boolean isViewFromObject(View view, Object obj) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(obj, "object");
        return view == obj;
    }

    public AudioPagerAdapter(View[] viewArr, String[] strArr) {
        Intrinsics.checkNotNullParameter(strArr, "titles");
        this.lists = viewArr;
        this.titles = strArr;
    }

    public int getCount() {
        View[] viewArr = this.lists;
        if (viewArr != null) {
            return viewArr.length;
        }
        return 0;
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        Intrinsics.checkNotNullParameter(viewGroup, "container");
        View[] viewArr = this.lists;
        Intrinsics.checkNotNull(viewArr);
        return viewArr[i];
    }

    public CharSequence getPageTitle(int i) {
        if (i >= 0) {
            String[] strArr = this.titles;
            if (i < strArr.length) {
                return strArr[i];
            }
        }
        return "";
    }
}
