package org.videolan.television.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.TextView;
import androidx.core.os.BundleKt;
import androidx.fragment.app.FragmentTransaction;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.television.R;
import org.videolan.television.ui.browser.BaseTvActivity;
import org.videolan.tools.AppUtils$$ExternalSyntheticApiModelOutline0;
import org.videolan.vlc.gui.SecondaryActivity;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0007\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\bH\u0014J\u000e\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\fR\u000e\u0010\u0003\u001a\u00020\u0004X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X.¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lorg/videolan/television/ui/MediaScrapingTvActivity;", "Lorg/videolan/television/ui/browser/BaseTvActivity;", "()V", "emptyView", "Landroid/widget/TextView;", "fragment", "Lorg/videolan/television/ui/MediaScrapingTvFragment;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onSearchRequested", "", "refresh", "updateEmptyView", "empty", "Companion", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaScrapingTvActivity.kt */
public final class MediaScrapingTvActivity extends BaseTvActivity {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String MEDIA = "MEDIA";
    private static final String TAG = "VLC/MediaScrapingTvActivity";
    private TextView emptyView;
    private MediaScrapingTvFragment fragment;

    public void onCreate(Bundle bundle) {
        Parcelable parcelable;
        super.onCreate(bundle);
        setContentView(R.layout.tv_next);
        MediaScrapingTvFragment mediaScrapingTvFragment = new MediaScrapingTvFragment();
        Pair[] pairArr = new Pair[1];
        Intent intent = getIntent();
        Intrinsics.checkNotNullExpressionValue(intent, "getIntent(...)");
        MediaScrapingTvFragment mediaScrapingTvFragment2 = null;
        if (Build.VERSION.SDK_INT >= 33) {
            parcelable = (Parcelable) AppUtils$$ExternalSyntheticApiModelOutline0.m(intent, MEDIA, MediaWrapper.class);
        } else {
            Parcelable parcelableExtra = intent.getParcelableExtra(MEDIA);
            if (!(parcelableExtra instanceof MediaWrapper)) {
                parcelableExtra = null;
            }
            parcelable = (MediaWrapper) parcelableExtra;
        }
        pairArr[0] = TuplesKt.to(MEDIA, parcelable);
        mediaScrapingTvFragment.setArguments(BundleKt.bundleOf(pairArr));
        this.fragment = mediaScrapingTvFragment;
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        int i = R.id.fragment_placeholder;
        MediaScrapingTvFragment mediaScrapingTvFragment3 = this.fragment;
        if (mediaScrapingTvFragment3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(SecondaryActivity.KEY_FRAGMENT);
        } else {
            mediaScrapingTvFragment2 = mediaScrapingTvFragment3;
        }
        beginTransaction.replace(i, mediaScrapingTvFragment2).commit();
        View findViewById = findViewById(R.id.empty);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.emptyView = (TextView) findViewById;
    }

    /* access modifiers changed from: protected */
    public void refresh() {
        MediaScrapingTvFragment mediaScrapingTvFragment = this.fragment;
        if (mediaScrapingTvFragment == null) {
            Intrinsics.throwUninitializedPropertyAccessException(SecondaryActivity.KEY_FRAGMENT);
            mediaScrapingTvFragment = null;
        }
        mediaScrapingTvFragment.refresh();
    }

    public final void updateEmptyView(boolean z) {
        TextView textView = this.emptyView;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("emptyView");
            textView = null;
        }
        textView.setVisibility(z ? 0 : 8);
    }

    public boolean onSearchRequested() {
        MediaScrapingTvFragment mediaScrapingTvFragment = this.fragment;
        if (mediaScrapingTvFragment == null) {
            Intrinsics.throwUninitializedPropertyAccessException(SecondaryActivity.KEY_FRAGMENT);
            mediaScrapingTvFragment = null;
        }
        mediaScrapingTvFragment.startRecognition();
        return true;
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lorg/videolan/television/ui/MediaScrapingTvActivity$Companion;", "", "()V", "MEDIA", "", "TAG", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: MediaScrapingTvActivity.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
