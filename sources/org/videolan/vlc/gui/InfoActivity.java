package org.videolan.vlc.gui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.ActionBar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.snackbar.Snackbar;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.Job;
import org.videolan.medialibrary.interfaces.Medialibrary;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.resources.Constants;
import org.videolan.tools.AppUtils$$ExternalSyntheticApiModelOutline0;
import org.videolan.vlc.ArtworkProvider;
import org.videolan.vlc.R;
import org.videolan.vlc.databinding.InfoActivityBinding;
import org.videolan.vlc.gui.browser.PathAdapterListener;
import org.videolan.vlc.gui.helpers.FloatingActionButtonBehavior;
import org.videolan.vlc.gui.helpers.MedialibraryUtils;
import org.videolan.vlc.gui.video.MediaInfoAdapter;
import org.videolan.vlc.media.MediaUtils;
import org.videolan.vlc.util.KextensionsKt;
import org.videolan.vlc.viewmodels.browser.IPathOperationDelegate;
import org.videolan.vlc.viewmodels.browser.PathOperationDelegate;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004B\u0005¢\u0006\u0002\u0010\u0005J\u0019\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0001J\u0010\u0010\u001d\u001a\u00020\u00112\u0006\u0010\u001e\u001a\u00020\u001aH\u0016J\t\u0010\u001f\u001a\u00020\u0011H\u0001J\b\u0010 \u001a\u00020\u0000H\u0016J\u000b\u0010!\u001a\u0004\u0018\u00010\"H\u0001J\b\u0010#\u001a\u00020\u0000H\u0016J\u000b\u0010$\u001a\u0004\u0018\u00010\u0015H\u0001J\b\u0010%\u001a\u00020&H\u0016J\u0011\u0010'\u001a\u00020\u001a2\u0006\u0010\u0019\u001a\u00020\u001aH\u0001J\b\u0010(\u001a\u00020\u0011H\u0002J\u0010\u0010)\u001a\u00020\u00112\u0006\u0010*\u001a\u00020+H\u0016J\u0012\u0010,\u001a\u00020\u00112\b\u0010-\u001a\u0004\u0018\u00010.H\u0014J\u0018\u0010/\u001a\u00020\u00112\u0006\u00100\u001a\u00020+2\u0006\u00101\u001a\u000202H\u0014J\u0012\u00103\u001a\u00020\u00112\b\u0010-\u001a\u0004\u0018\u00010.H\u0014J\u0010\u00104\u001a\u00020\u00112\u0006\u00105\u001a\u00020.H\u0014J\u0011\u00106\u001a\u00020\u001a2\u0006\u0010\u0019\u001a\u00020\u001aH\u0001J\u0011\u00107\u001a\u00020\u001a2\u0006\u00108\u001a\u00020\u001aH\u0001J\u0013\u00109\u001a\u00020\u00112\b\u0010:\u001a\u0004\u0018\u00010\"H\u0001J\u0013\u0010;\u001a\u00020\u00112\b\u0010<\u001a\u0004\u0018\u00010\u0015H\u0001J\b\u0010=\u001a\u00020&H\u0016J\b\u0010>\u001a\u00020?H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X.¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u00020\tX.¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR \u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u000fX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\u0015X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X.¢\u0006\u0002\n\u0000¨\u0006@"}, d2 = {"Lorg/videolan/vlc/gui/InfoActivity;", "Lorg/videolan/vlc/gui/AudioPlayerContainerActivity;", "Landroid/view/View$OnClickListener;", "Lorg/videolan/vlc/gui/browser/PathAdapterListener;", "Lorg/videolan/vlc/viewmodels/browser/IPathOperationDelegate;", "()V", "adapter", "Lorg/videolan/vlc/gui/video/MediaInfoAdapter;", "binding", "Lorg/videolan/vlc/databinding/InfoActivityBinding;", "getBinding$vlc_android_release", "()Lorg/videolan/vlc/databinding/InfoActivityBinding;", "setBinding$vlc_android_release", "(Lorg/videolan/vlc/databinding/InfoActivityBinding;)V", "insetListener", "Lkotlin/Function1;", "Landroidx/core/graphics/Insets;", "", "getInsetListener", "()Lkotlin/jvm/functions/Function1;", "item", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "model", "Lorg/videolan/vlc/gui/InfoModel;", "appendPathToUri", "path", "", "uri", "Landroid/net/Uri$Builder;", "backTo", "tag", "consumeSource", "currentContext", "getAndRemoveDestination", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "getPathOperationDelegate", "getSource", "isTransparent", "", "makePathSafe", "noCoverFallback", "onClick", "v", "Landroid/view/View;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onPlayerStateChanged", "bottomSheet", "newState", "", "onPostCreate", "onSaveInstanceState", "outState", "replaceStoragePath", "retrieveSafePath", "encoded", "setDestination", "media", "setSource", "currentItem", "showRoot", "updateMeta", "Lkotlinx/coroutines/Job;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: InfoActivity.kt */
public final class InfoActivity extends AudioPlayerContainerActivity implements View.OnClickListener, PathAdapterListener, IPathOperationDelegate {
    private final /* synthetic */ PathOperationDelegate $$delegate_0 = new PathOperationDelegate();
    /* access modifiers changed from: private */
    public MediaInfoAdapter adapter;
    public InfoActivityBinding binding;
    private final Function1<Insets, Unit> insetListener = new InfoActivity$insetListener$1(this);
    /* access modifiers changed from: private */
    public MediaLibraryItem item;
    private InfoModel model;

    public void appendPathToUri(String str, Uri.Builder builder) {
        Intrinsics.checkNotNullParameter(str, ArtworkProvider.PATH);
        Intrinsics.checkNotNullParameter(builder, Constants.KEY_URI);
        this.$$delegate_0.appendPathToUri(str, builder);
    }

    public void backTo(String str) {
        Intrinsics.checkNotNullParameter(str, "tag");
    }

    public void consumeSource() {
        this.$$delegate_0.consumeSource();
    }

    public InfoActivity currentContext() {
        return this;
    }

    public MediaWrapper getAndRemoveDestination() {
        return this.$$delegate_0.getAndRemoveDestination();
    }

    public InfoActivity getPathOperationDelegate() {
        return this;
    }

    public MediaLibraryItem getSource() {
        return this.$$delegate_0.getSource();
    }

    public boolean isTransparent() {
        return true;
    }

    public String makePathSafe(String str) {
        Intrinsics.checkNotNullParameter(str, ArtworkProvider.PATH);
        String makePathSafe = this.$$delegate_0.makePathSafe(str);
        Intrinsics.checkNotNullExpressionValue(makePathSafe, "makePathSafe(...)");
        return makePathSafe;
    }

    public String replaceStoragePath(String str) {
        Intrinsics.checkNotNullParameter(str, ArtworkProvider.PATH);
        return this.$$delegate_0.replaceStoragePath(str);
    }

    public String retrieveSafePath(String str) {
        Intrinsics.checkNotNullParameter(str, "encoded");
        return this.$$delegate_0.retrieveSafePath(str);
    }

    public void setDestination(MediaWrapper mediaWrapper) {
        this.$$delegate_0.setDestination(mediaWrapper);
    }

    public void setSource(MediaLibraryItem mediaLibraryItem) {
        this.$$delegate_0.setSource(mediaLibraryItem);
    }

    public boolean showRoot() {
        return false;
    }

    public final InfoActivityBinding getBinding$vlc_android_release() {
        InfoActivityBinding infoActivityBinding = this.binding;
        if (infoActivityBinding != null) {
            return infoActivityBinding;
        }
        Intrinsics.throwUninitializedPropertyAccessException("binding");
        return null;
    }

    public final void setBinding$vlc_android_release(InfoActivityBinding infoActivityBinding) {
        Intrinsics.checkNotNullParameter(infoActivityBinding, "<set-?>");
        this.binding = infoActivityBinding;
    }

    public Function1<Insets, Unit> getInsetListener() {
        return this.insetListener;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        MediaLibraryItem mediaLibraryItem;
        MediaWrapper media;
        Parcelable parcelable;
        Parcelable parcelable2;
        super.onCreate(bundle);
        Activity activity = this;
        ViewDataBinding contentView = DataBindingUtil.setContentView(activity, R.layout.info_activity);
        Intrinsics.checkNotNullExpressionValue(contentView, "setContentView(...)");
        setBinding$vlc_android_release((InfoActivityBinding) contentView);
        initAudioPlayerContainerActivity();
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        InfoModel infoModel = null;
        if (bundle != null) {
            if (Build.VERSION.SDK_INT >= 33) {
                parcelable2 = (Parcelable) AppUtils$$ExternalSyntheticApiModelOutline0.m(bundle, "ML_ITEM", Parcelable.class);
            } else {
                parcelable2 = bundle.getParcelable("ML_ITEM");
                if (!(parcelable2 instanceof Parcelable)) {
                    parcelable2 = null;
                }
            }
            mediaLibraryItem = (MediaLibraryItem) parcelable2;
        } else {
            Intent intent = getIntent();
            Intrinsics.checkNotNullExpressionValue(intent, "getIntent(...)");
            if (Build.VERSION.SDK_INT >= 33) {
                parcelable = (Parcelable) AppUtils$$ExternalSyntheticApiModelOutline0.m(intent, "ML_ITEM", Parcelable.class);
            } else {
                parcelable = intent.getParcelableExtra("ML_ITEM");
                if (!(parcelable instanceof Parcelable)) {
                    parcelable = null;
                }
            }
            Intrinsics.checkNotNull(parcelable, "null cannot be cast to non-null type org.videolan.medialibrary.media.MediaLibraryItem");
            mediaLibraryItem = (MediaLibraryItem) parcelable;
        }
        if (mediaLibraryItem == null) {
            finish();
            return;
        }
        this.item = mediaLibraryItem;
        if (mediaLibraryItem.getId() == 0 && (media = Medialibrary.getInstance().getMedia(((MediaWrapper) mediaLibraryItem).getUri())) != null) {
            this.item = media;
        }
        getBinding$vlc_android_release().setItem(mediaLibraryItem);
        int i = bundle != null ? bundle.getInt("FAB") : -1;
        this.model = (InfoModel) new ViewModelProvider(this).get(InfoModel.class);
        getBinding$vlc_android_release().fab.setOnClickListener(this);
        if (mediaLibraryItem instanceof MediaWrapper) {
            this.adapter = new MediaInfoAdapter();
            getBinding$vlc_android_release().list.setLayoutManager(new LinearLayoutManager(getBinding$vlc_android_release().getRoot().getContext()));
            RecyclerView recyclerView = getBinding$vlc_android_release().list;
            MediaInfoAdapter mediaInfoAdapter = this.adapter;
            if (mediaInfoAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                mediaInfoAdapter = null;
            }
            recyclerView.setAdapter(mediaInfoAdapter);
            getBinding$vlc_android_release().list.setNestedScrollingEnabled(false);
            InfoModel infoModel2 = this.model;
            if (infoModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("model");
                infoModel2 = null;
            }
            if (infoModel2.getSizeText().getValue() == null) {
                InfoModel infoModel3 = this.model;
                if (infoModel3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("model");
                    infoModel3 = null;
                }
                infoModel3.checkFile((MediaWrapper) mediaLibraryItem);
            }
            InfoModel infoModel4 = this.model;
            if (infoModel4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("model");
                infoModel4 = null;
            }
            if (infoModel4.getMediaTracks().getValue() == null) {
                InfoModel infoModel5 = this.model;
                if (infoModel5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("model");
                    infoModel5 = null;
                }
                infoModel5.parseTracks(this, (MediaWrapper) mediaLibraryItem);
            }
        }
        InfoModel infoModel6 = this.model;
        if (infoModel6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("model");
            infoModel6 = null;
        }
        LifecycleOwner lifecycleOwner = this;
        infoModel6.getHasSubs().observe(lifecycleOwner, new InfoActivityKt$sam$androidx_lifecycle_Observer$0(new InfoActivity$onCreate$1(this)));
        InfoModel infoModel7 = this.model;
        if (infoModel7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("model");
            infoModel7 = null;
        }
        infoModel7.getMediaTracks().observe(lifecycleOwner, new InfoActivityKt$sam$androidx_lifecycle_Observer$0(new InfoActivity$onCreate$2(this)));
        InfoModel infoModel8 = this.model;
        if (infoModel8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("model");
            infoModel8 = null;
        }
        infoModel8.getSizeText().observe(lifecycleOwner, new InfoActivityKt$sam$androidx_lifecycle_Observer$0(new InfoActivity$onCreate$3(this)));
        InfoModel infoModel9 = this.model;
        if (infoModel9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("model");
            infoModel9 = null;
        }
        infoModel9.getCover().observe(lifecycleOwner, new InfoActivityKt$sam$androidx_lifecycle_Observer$0(new InfoActivity$onCreate$4(this, i)));
        InfoModel infoModel10 = this.model;
        if (infoModel10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("model");
            infoModel10 = null;
        }
        if (infoModel10.getCover().getValue() == null) {
            InfoModel infoModel11 = this.model;
            if (infoModel11 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("model");
            } else {
                infoModel = infoModel11;
            }
            infoModel.getCover(mediaLibraryItem, KextensionsKt.getScreenWidth(activity));
        }
        updateMeta();
        getBinding$vlc_android_release().directoryNotScannedButton.setOnClickListener(new InfoActivity$$ExternalSyntheticLambda0(mediaLibraryItem, this));
    }

    /* access modifiers changed from: private */
    public static final void onCreate$lambda$0(MediaLibraryItem mediaLibraryItem, InfoActivity infoActivity, View view) {
        Intrinsics.checkNotNullParameter(infoActivity, "this$0");
        Intrinsics.checkNotNull(mediaLibraryItem, "null cannot be cast to non-null type org.videolan.medialibrary.interfaces.media.MediaWrapper");
        MediaWrapper mediaWrapper = (MediaWrapper) mediaLibraryItem;
        String uri = mediaWrapper.getUri().toString();
        Intrinsics.checkNotNullExpressionValue(uri, "toString(...)");
        String uri2 = mediaWrapper.getUri().toString();
        Intrinsics.checkNotNullExpressionValue(uri2, "toString(...)");
        String substring = uri.substring(0, StringsKt.lastIndexOf$default((CharSequence) uri2, "/", 0, false, 6, (Object) null));
        Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
        MedialibraryUtils.INSTANCE.addDir(substring, infoActivity.getApplicationContext());
        Snackbar.make(infoActivity.getBinding$vlc_android_release().getRoot(), (CharSequence) infoActivity.getString(R.string.scanned_directory_added, new Object[]{Uri.parse(substring).getLastPathSegment()}), 0).show();
        infoActivity.getBinding$vlc_android_release().setScanned(true);
    }

    private final Job updateMeta() {
        return LifecycleOwnerKt.getLifecycleScope(this).launchWhenStarted(new InfoActivity$updateMeta$1(this, (Continuation<? super InfoActivity$updateMeta$1>) null));
    }

    /* access modifiers changed from: protected */
    public void onPostCreate(Bundle bundle) {
        NestedScrollView nestedScrollView = getBinding$vlc_android_release().container;
        Intrinsics.checkNotNullExpressionValue(nestedScrollView, "container");
        setFragmentContainer(nestedScrollView);
        super.onPostCreate(bundle);
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        Intrinsics.checkNotNullParameter(bundle, "outState");
        super.onSaveInstanceState(bundle);
        MediaLibraryItem mediaLibraryItem = this.item;
        if (mediaLibraryItem == null) {
            Intrinsics.throwUninitializedPropertyAccessException("item");
            mediaLibraryItem = null;
        }
        bundle.putParcelable("ML_ITEM", mediaLibraryItem);
        bundle.putInt("FAB", getBinding$vlc_android_release().fab.getVisibility());
    }

    /* access modifiers changed from: private */
    public final void noCoverFallback() {
        getBinding$vlc_android_release().appbar.setExpanded(false);
        ViewCompat.setNestedScrollingEnabled(getBinding$vlc_android_release().list, false);
        ViewGroup.LayoutParams layoutParams = getBinding$vlc_android_release().fab.getLayoutParams();
        Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams");
        CoordinatorLayout.LayoutParams layoutParams2 = (CoordinatorLayout.LayoutParams) layoutParams;
        layoutParams2.setAnchorId(getBinding$vlc_android_release().container.getId());
        layoutParams2.anchorGravity = 8388693;
        layoutParams2.bottomMargin = getResources().getDimensionPixelSize(R.dimen.default_margin);
        layoutParams2.setBehavior(new FloatingActionButtonBehavior(this, (AttributeSet) null));
        getBinding$vlc_android_release().fab.setLayoutParams(layoutParams2);
        getBinding$vlc_android_release().fab.show();
    }

    public void onClick(View view) {
        Intrinsics.checkNotNullParameter(view, "v");
        MediaUtils mediaUtils = MediaUtils.INSTANCE;
        Context context = this;
        MediaLibraryItem mediaLibraryItem = this.item;
        if (mediaLibraryItem == null) {
            Intrinsics.throwUninitializedPropertyAccessException("item");
            mediaLibraryItem = null;
        }
        MediaUtils.playTracks$default(mediaUtils, context, mediaLibraryItem, 0, false, 8, (Object) null);
        finish();
    }

    /* access modifiers changed from: protected */
    public void onPlayerStateChanged(View view, int i) {
        Intrinsics.checkNotNullParameter(view, "bottomSheet");
        int visibility = getBinding$vlc_android_release().fab.getVisibility();
        if (visibility == 0 && i != 4 && i != 5) {
            getBinding$vlc_android_release().fab.hide();
        } else if (visibility != 4) {
        } else {
            if (i == 4 || i == 5) {
                getBinding$vlc_android_release().fab.show();
            }
        }
    }
}
