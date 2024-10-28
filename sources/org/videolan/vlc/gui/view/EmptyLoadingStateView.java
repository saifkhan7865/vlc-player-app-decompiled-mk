package org.videolan.vlc.gui.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.transition.TransitionManager;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.BaseActivity;
import org.videolan.vlc.gui.SecondaryActivity;
import org.videolan.vlc.gui.helpers.BitmapUtilKt;
import org.videolan.vlc.gui.helpers.hf.StoragePermissionsDelegate;

@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B\u001f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010?\u001a\u000200H\u0002J\u0018\u0010@\u001a\u0002002\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tH\u0003J\b\u0010A\u001a\u000200H\u0002J\u0014\u0010B\u001a\u0002002\f\u0010C\u001a\b\u0012\u0004\u0012\u0002000/R\u000e\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u000e@BX\u000e¢\u0006\b\n\u0000\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0013X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0018\u001a\u00020\u0019X.¢\u0006\u0002\n\u0000R$\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\r\u001a\u00020\u001a@FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u000e\u0010 \u001a\u00020!X.¢\u0006\u0002\n\u0000R\u001c\u0010\"\u001a\u0004\u0018\u00010\u001aX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u001d\"\u0004\b$\u0010\u001fR\u000e\u0010%\u001a\u00020&X.¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020(X.¢\u0006\u0002\n\u0000R$\u0010)\u001a\u00020\u001a2\u0006\u0010\r\u001a\u00020\u001a@FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010\u001d\"\u0004\b+\u0010\u001fR\u000e\u0010,\u001a\u00020!X.¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020&X.¢\u0006\u0002\n\u0000R\u0016\u0010.\u001a\n\u0012\u0004\u0012\u000200\u0018\u00010/X\u000e¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u00020!X.¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u00020!X.¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u00020&X.¢\u0006\u0002\n\u0000R\u001a\u00105\u001a\u00020\u000eX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u00107\"\u0004\b8\u0010\u0011R$\u0010:\u001a\u0002092\u0006\u0010\r\u001a\u000209@FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u0010<\"\u0004\b=\u0010>¨\u0006D"}, d2 = {"Lorg/videolan/vlc/gui/view/EmptyLoadingStateView;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyle", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "compactConstraintSet", "Landroidx/constraintlayout/widget/ConstraintSet;", "value", "", "compactMode", "setCompactMode", "(Z)V", "container", "Landroidx/constraintlayout/widget/ConstraintLayout;", "getContainer", "()Landroidx/constraintlayout/widget/ConstraintLayout;", "setContainer", "(Landroidx/constraintlayout/widget/ConstraintLayout;)V", "emptyImageView", "Landroid/widget/ImageView;", "", "emptyText", "getEmptyText", "()Ljava/lang/String;", "setEmptyText", "(Ljava/lang/String;)V", "emptyTextView", "Landroid/widget/TextView;", "filterQuery", "getFilterQuery", "setFilterQuery", "grantPermissionButton", "Landroid/widget/Button;", "loadingFlipper", "Landroid/widget/ViewFlipper;", "loadingText", "getLoadingText", "setLoadingText", "loadingTitle", "noMediaButton", "noMediaClickListener", "Lkotlin/Function0;", "", "normalConstraintSet", "permissionTextView", "permissionTitle", "pickFileButton", "showNoMedia", "getShowNoMedia", "()Z", "setShowNoMedia", "Lorg/videolan/vlc/gui/view/EmptyLoadingState;", "state", "getState", "()Lorg/videolan/vlc/gui/view/EmptyLoadingState;", "setState", "(Lorg/videolan/vlc/gui/view/EmptyLoadingState;)V", "applyCompactMode", "initAttributes", "initialize", "setOnNoMediaClickListener", "l", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: EmptyLoadingStateView.kt */
public final class EmptyLoadingStateView extends FrameLayout {
    private final ConstraintSet compactConstraintSet = new ConstraintSet();
    private boolean compactMode;
    public ConstraintLayout container;
    private ImageView emptyImageView;
    private String emptyText;
    private TextView emptyTextView;
    private String filterQuery;
    private Button grantPermissionButton;
    private ViewFlipper loadingFlipper;
    private String loadingText;
    private TextView loadingTitle;
    private Button noMediaButton;
    private Function0<Unit> noMediaClickListener;
    private final ConstraintSet normalConstraintSet = new ConstraintSet();
    private TextView permissionTextView;
    private TextView permissionTitle;
    private Button pickFileButton;
    private boolean showNoMedia = true;
    private EmptyLoadingState state = EmptyLoadingState.LOADING;

    public final String getFilterQuery() {
        return this.filterQuery;
    }

    public final void setFilterQuery(String str) {
        this.filterQuery = str;
    }

    public final ConstraintLayout getContainer() {
        ConstraintLayout constraintLayout = this.container;
        if (constraintLayout != null) {
            return constraintLayout;
        }
        Intrinsics.throwUninitializedPropertyAccessException("container");
        return null;
    }

    public final void setContainer(ConstraintLayout constraintLayout) {
        Intrinsics.checkNotNullParameter(constraintLayout, "<set-?>");
        this.container = constraintLayout;
    }

    public final boolean getShowNoMedia() {
        return this.showNoMedia;
    }

    public final void setShowNoMedia(boolean z) {
        this.showNoMedia = z;
    }

    private final void setCompactMode(boolean z) {
        this.compactMode = z;
        applyCompactMode();
    }

    public final EmptyLoadingState getState() {
        return this.state;
    }

    public final void setState(EmptyLoadingState emptyLoadingState) {
        int i;
        EmptyLoadingState emptyLoadingState2 = emptyLoadingState;
        Intrinsics.checkNotNullParameter(emptyLoadingState2, "value");
        ViewFlipper viewFlipper = this.loadingFlipper;
        Button button = null;
        if (viewFlipper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("loadingFlipper");
            viewFlipper = null;
        }
        int i2 = 8;
        viewFlipper.setVisibility(emptyLoadingState2 == EmptyLoadingState.LOADING ? 0 : 8);
        TextView textView = this.loadingTitle;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("loadingTitle");
            textView = null;
        }
        textView.setVisibility(emptyLoadingState2 == EmptyLoadingState.LOADING ? 0 : 8);
        TextView textView2 = this.emptyTextView;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("emptyTextView");
            textView2 = null;
        }
        textView2.setVisibility(ArraysKt.contains((T[]) new EmptyLoadingState[]{EmptyLoadingState.EMPTY, EmptyLoadingState.EMPTY_SEARCH, EmptyLoadingState.EMPTY_FAVORITES}, emptyLoadingState2) ? 0 : 8);
        ImageView imageView = this.emptyImageView;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("emptyImageView");
            imageView = null;
        }
        imageView.setVisibility(ArraysKt.contains((T[]) new EmptyLoadingState[]{EmptyLoadingState.EMPTY, EmptyLoadingState.MISSING_PERMISSION, EmptyLoadingState.EMPTY_SEARCH, EmptyLoadingState.EMPTY_FAVORITES}, emptyLoadingState2) ? 0 : 8);
        ImageView imageView2 = this.emptyImageView;
        if (imageView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("emptyImageView");
            imageView2 = null;
        }
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        if (emptyLoadingState2 == EmptyLoadingState.EMPTY_FAVORITES) {
            i = R.drawable.ic_fav_empty;
        } else {
            i = ArraysKt.contains((T[]) new EmptyLoadingState[]{EmptyLoadingState.EMPTY, EmptyLoadingState.EMPTY_SEARCH, EmptyLoadingState.EMPTY_FAVORITES}, emptyLoadingState2) ? R.drawable.ic_empty : R.drawable.ic_empty_warning;
        }
        imageView2.setImageBitmap(BitmapUtilKt.getBitmapFromDrawable$default(context, i, 0, 0, 6, (Object) null));
        TextView textView3 = this.permissionTitle;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("permissionTitle");
            textView3 = null;
        }
        textView3.setVisibility(emptyLoadingState2 == EmptyLoadingState.MISSING_PERMISSION ? 0 : 8);
        TextView textView4 = this.permissionTextView;
        if (textView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("permissionTextView");
            textView4 = null;
        }
        textView4.setVisibility(emptyLoadingState2 == EmptyLoadingState.MISSING_PERMISSION ? 0 : 8);
        Button button2 = this.grantPermissionButton;
        if (button2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("grantPermissionButton");
            button2 = null;
        }
        button2.setVisibility(emptyLoadingState2 == EmptyLoadingState.MISSING_PERMISSION ? 0 : 8);
        Button button3 = this.pickFileButton;
        if (button3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("pickFileButton");
            button3 = null;
        }
        button3.setVisibility((emptyLoadingState2 != EmptyLoadingState.MISSING_PERMISSION || Build.VERSION.SDK_INT < 26) ? 8 : 0);
        Button button4 = this.noMediaButton;
        if (button4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("noMediaButton");
        } else {
            button = button4;
        }
        if (this.showNoMedia && emptyLoadingState2 == EmptyLoadingState.EMPTY) {
            i2 = 0;
        } else if (emptyLoadingState2 == EmptyLoadingState.EMPTY_FAVORITES) {
            i2 = 4;
        }
        button.setVisibility(i2);
        this.state = emptyLoadingState2;
    }

    public final String getEmptyText() {
        return this.emptyText;
    }

    public final void setEmptyText(String str) {
        Intrinsics.checkNotNullParameter(str, "value");
        TextView textView = this.emptyTextView;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("emptyTextView");
            textView = null;
        }
        textView.setText(str);
        this.emptyText = this.emptyText;
    }

    public final String getLoadingText() {
        return this.loadingText;
    }

    public final void setLoadingText(String str) {
        Intrinsics.checkNotNullParameter(str, "value");
        TextView textView = this.loadingTitle;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("loadingTitle");
            textView = null;
        }
        textView.setText(str);
        this.loadingText = this.emptyText;
    }

    public final void setOnNoMediaClickListener(Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "l");
        this.noMediaClickListener = function0;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public EmptyLoadingStateView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        String string = getContext().getString(R.string.nomedia);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        this.emptyText = string;
        String string2 = getContext().getString(R.string.loading);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        this.loadingText = string2;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public EmptyLoadingStateView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributeSet, "attrs");
        String string = getContext().getString(R.string.nomedia);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        this.emptyText = string;
        String string2 = getContext().getString(R.string.loading);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        this.loadingText = string2;
        initialize();
        initAttributes(attributeSet, 0);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public EmptyLoadingStateView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributeSet, "attrs");
        String string = getContext().getString(R.string.nomedia);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        this.emptyText = string;
        String string2 = getContext().getString(R.string.loading);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        this.loadingText = string2;
        initialize();
        initAttributes(attributeSet, i);
    }

    private final void initAttributes(AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(attributeSet, R.styleable.EmptyLoadingStateView, 0, i);
        Intrinsics.checkNotNullExpressionValue(obtainStyledAttributes, "obtainStyledAttributes(...)");
        TextView textView = null;
        try {
            TextView textView2 = this.emptyTextView;
            if (textView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("emptyTextView");
                textView2 = null;
            }
            textView2.setText(obtainStyledAttributes.getString(R.styleable.EmptyLoadingStateView_empty_text));
            this.showNoMedia = obtainStyledAttributes.getBoolean(R.styleable.EmptyLoadingStateView_show_no_media, true);
            setCompactMode(obtainStyledAttributes.getBoolean(R.styleable.EmptyLoadingStateView_compact_mode, true));
            Unit unit = Unit.INSTANCE;
        } catch (Exception e) {
            Integer.valueOf(Log.w("", e.getMessage(), e));
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
        obtainStyledAttributes.recycle();
        setState(EmptyLoadingState.LOADING);
        Button button = this.noMediaButton;
        if (button == null) {
            Intrinsics.throwUninitializedPropertyAccessException("noMediaButton");
            button = null;
        }
        button.setOnClickListener(new EmptyLoadingStateView$$ExternalSyntheticLambda0(this));
        Button button2 = this.grantPermissionButton;
        if (button2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("grantPermissionButton");
            button2 = null;
        }
        button2.setOnClickListener(new EmptyLoadingStateView$$ExternalSyntheticLambda1(this));
        Button button3 = this.pickFileButton;
        if (button3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("pickFileButton");
            button3 = null;
        }
        button3.setOnClickListener(new EmptyLoadingStateView$$ExternalSyntheticLambda2(this));
        TextView textView3 = this.permissionTextView;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("permissionTextView");
        } else {
            textView = textView3;
        }
        textView.setText(getContext().getString(R.string.permission_expanation_no_allow) + "\n\n" + getContext().getString(R.string.permission_expanation_allow));
    }

    /* access modifiers changed from: private */
    public static final void initAttributes$lambda$1(EmptyLoadingStateView emptyLoadingStateView, View view) {
        Intrinsics.checkNotNullParameter(emptyLoadingStateView, "this$0");
        Intent intent = new Intent(emptyLoadingStateView.getContext().getApplicationContext(), SecondaryActivity.class);
        intent.putExtra(SecondaryActivity.KEY_FRAGMENT, SecondaryActivity.STORAGE_BROWSER);
        Context context = emptyLoadingStateView.getContext();
        Intrinsics.checkNotNull(context, "null cannot be cast to non-null type android.app.Activity");
        ((Activity) context).startActivityForResult(intent, 1);
        Function0<Unit> function0 = emptyLoadingStateView.noMediaClickListener;
        if (function0 != null) {
            function0.invoke();
        }
    }

    /* access modifiers changed from: private */
    public static final void initAttributes$lambda$2(EmptyLoadingStateView emptyLoadingStateView, View view) {
        Intrinsics.checkNotNullParameter(emptyLoadingStateView, "this$0");
        Context context = emptyLoadingStateView.getContext();
        FragmentActivity fragmentActivity = context instanceof FragmentActivity ? (FragmentActivity) context : null;
        if (fragmentActivity != null) {
            StoragePermissionsDelegate.Companion.askStoragePermission(fragmentActivity, false, (Runnable) null);
        }
    }

    /* access modifiers changed from: private */
    public static final void initAttributes$lambda$3(EmptyLoadingStateView emptyLoadingStateView, View view) {
        Intrinsics.checkNotNullParameter(emptyLoadingStateView, "this$0");
        if (Build.VERSION.SDK_INT >= 26) {
            Context context = emptyLoadingStateView.getContext();
            Intrinsics.checkNotNull(context, "null cannot be cast to non-null type org.videolan.vlc.gui.BaseActivity");
            Uri parse = Uri.parse("");
            Intrinsics.checkNotNullExpressionValue(parse, "parse(...)");
            ((BaseActivity) context).openFile(parse);
        }
    }

    private final void applyCompactMode() {
        if (this.container != null) {
            TransitionManager.beginDelayedTransition(getContainer());
            (this.compactMode ? this.compactConstraintSet : this.normalConstraintSet).applyTo(getContainer());
            TextView textView = this.emptyTextView;
            if (textView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("emptyTextView");
                textView = null;
            }
            textView.setGravity(this.compactMode ? GravityCompat.START : 17);
        }
    }

    private final void initialize() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_empty_loading, this, true);
        View findViewById = findViewById(R.id.emptyTextView);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.emptyTextView = (TextView) findViewById;
        View findViewById2 = findViewById(R.id.permissionTextView);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        this.permissionTextView = (TextView) findViewById2;
        View findViewById3 = findViewById(R.id.loadingFlipper);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        this.loadingFlipper = (ViewFlipper) findViewById3;
        View findViewById4 = findViewById(R.id.grantPermissionButton);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(...)");
        this.grantPermissionButton = (Button) findViewById4;
        View findViewById5 = findViewById(R.id.pickFile);
        Intrinsics.checkNotNullExpressionValue(findViewById5, "findViewById(...)");
        this.pickFileButton = (Button) findViewById5;
        View findViewById6 = findViewById(R.id.loadingTitle);
        Intrinsics.checkNotNullExpressionValue(findViewById6, "findViewById(...)");
        this.loadingTitle = (TextView) findViewById6;
        View findViewById7 = findViewById(R.id.emptyImageView);
        Intrinsics.checkNotNullExpressionValue(findViewById7, "findViewById(...)");
        this.emptyImageView = (ImageView) findViewById7;
        View findViewById8 = findViewById(R.id.permissionTitle);
        Intrinsics.checkNotNullExpressionValue(findViewById8, "findViewById(...)");
        this.permissionTitle = (TextView) findViewById8;
        View findViewById9 = findViewById(R.id.noMediaButton);
        Intrinsics.checkNotNullExpressionValue(findViewById9, "findViewById(...)");
        this.noMediaButton = (Button) findViewById9;
    }
}
