package org.videolan.vlc.gui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.vlc.R;

@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B\u001f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0018\u0010/\u001a\u00020\u00142\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tH\u0002J\b\u00100\u001a\u00020\u0014H\u0002J\b\u00101\u001a\u00020\u0014H\u0002J\u001a\u00102\u001a\u00020\u00142\u0012\u00103\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00140\u0012R\u001b\u0010\u000b\u001a\u00020\f8FX\u0002¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000eR\u001c\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0012X\u000e¢\u0006\u0002\n\u0000R$\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0015\u001a\u00020\u0016@FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001b\u0010\u001c\u001a\u00020\u001d8FX\u0002¢\u0006\f\n\u0004\b \u0010\u0010\u001a\u0004\b\u001e\u0010\u001fR\u001b\u0010!\u001a\u00020\"8FX\u0002¢\u0006\f\n\u0004\b%\u0010\u0010\u001a\u0004\b#\u0010$R\u001b\u0010&\u001a\u00020\u00138BX\u0002¢\u0006\f\n\u0004\b)\u0010\u0010\u001a\u0004\b'\u0010(R\u001b\u0010*\u001a\u00020+8BX\u0002¢\u0006\f\n\u0004\b.\u0010\u0010\u001a\u0004\b,\u0010-¨\u00064"}, d2 = {"Lorg/videolan/vlc/gui/view/TitleListView;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyle", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "actionButton", "Landroid/widget/ImageButton;", "getActionButton", "()Landroid/widget/ImageButton;", "actionButton$delegate", "Lkotlin/Lazy;", "actionClickListener", "Lkotlin/Function1;", "Landroid/view/View;", "", "value", "", "displayInCards", "getDisplayInCards", "()Z", "setDisplayInCards", "(Z)V", "list", "Landroidx/recyclerview/widget/RecyclerView;", "getList", "()Landroidx/recyclerview/widget/RecyclerView;", "list$delegate", "loading", "Lorg/videolan/vlc/gui/view/EmptyLoadingStateView;", "getLoading", "()Lorg/videolan/vlc/gui/view/EmptyLoadingStateView;", "loading$delegate", "titleContent", "getTitleContent", "()Landroid/view/View;", "titleContent$delegate", "titleView", "Landroid/widget/TextView;", "getTitleView", "()Landroid/widget/TextView;", "titleView$delegate", "initAttributes", "initialize", "manageDisplay", "setOnActionClickListener", "listener", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: TitleListView.kt */
public final class TitleListView extends ConstraintLayout {
    private final Lazy actionButton$delegate = LazyKt.lazy(new TitleListView$actionButton$2(this));
    private Function1<? super View, Unit> actionClickListener;
    private boolean displayInCards = true;
    private final Lazy list$delegate = LazyKt.lazy(new TitleListView$list$2(this));
    private final Lazy loading$delegate = LazyKt.lazy(new TitleListView$loading$2(this));
    private final Lazy titleContent$delegate = LazyKt.lazy(new TitleListView$titleContent$2(this));
    private final Lazy titleView$delegate = LazyKt.lazy(new TitleListView$titleView$2(this));

    public final boolean getDisplayInCards() {
        return this.displayInCards;
    }

    public final void setDisplayInCards(boolean z) {
        boolean z2 = this.displayInCards != z;
        this.displayInCards = z;
        if (z2) {
            manageDisplay();
        }
    }

    private final TextView getTitleView() {
        Object value = this.titleView$delegate.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (TextView) value;
    }

    public final RecyclerView getList() {
        Object value = this.list$delegate.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (RecyclerView) value;
    }

    public final EmptyLoadingStateView getLoading() {
        Object value = this.loading$delegate.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (EmptyLoadingStateView) value;
    }

    public final ImageButton getActionButton() {
        Object value = this.actionButton$delegate.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (ImageButton) value;
    }

    private final View getTitleContent() {
        Object value = this.titleContent$delegate.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (View) value;
    }

    public final void setOnActionClickListener(Function1<? super View, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "listener");
        this.actionClickListener = function1;
        getTitleContent().setEnabled(this.actionClickListener != null);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public TitleListView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public TitleListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributeSet, "attrs");
        initialize();
        initAttributes(attributeSet, 0);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public TitleListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributeSet, "attrs");
        initialize();
        initAttributes(attributeSet, i);
    }

    private final void initAttributes(AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(attributeSet, R.styleable.TitleListView, 0, i);
        Intrinsics.checkNotNullExpressionValue(obtainStyledAttributes, "obtainStyledAttributes(...)");
        try {
            String string = obtainStyledAttributes.getString(R.styleable.TitleListView_title);
            getTitleView().setText(string);
            boolean z = true;
            getTitleView().setContentDescription(getContext().getString(R.string.talkback_list_section, new Object[]{string}));
            getActionButton().setContentDescription(getContext().getString(R.string.talkback_enter_screen, new Object[]{string}));
            if (!obtainStyledAttributes.getBoolean(R.styleable.TitleListView_show_button, false)) {
                KotlinExtensionsKt.setGone(getActionButton());
            }
            getActionButton().setOnClickListener(new TitleListView$$ExternalSyntheticLambda0(this));
            getTitleContent().setOnClickListener(new TitleListView$$ExternalSyntheticLambda1(this));
            View titleContent = getTitleContent();
            if (this.actionClickListener == null) {
                z = false;
            }
            titleContent.setEnabled(z);
            getList().setNestedScrollingEnabled(false);
            Unit unit = Unit.INSTANCE;
        } catch (Exception e) {
            Integer.valueOf(Log.w("", e.getMessage(), e));
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
        obtainStyledAttributes.recycle();
        manageDisplay();
    }

    /* access modifiers changed from: private */
    public static final void initAttributes$lambda$4$lambda$1(TitleListView titleListView, View view) {
        Intrinsics.checkNotNullParameter(titleListView, "this$0");
        Function1<? super View, Unit> function1 = titleListView.actionClickListener;
        if (function1 != null) {
            function1.invoke(titleListView.getActionButton());
        }
    }

    /* access modifiers changed from: private */
    public static final void initAttributes$lambda$4$lambda$3(TitleListView titleListView, View view) {
        Intrinsics.checkNotNullParameter(titleListView, "this$0");
        Function1<? super View, Unit> function1 = titleListView.actionClickListener;
        if (function1 != null) {
            function1.invoke(titleListView.getActionButton());
        }
    }

    private final void manageDisplay() {
        if (getList().getItemDecorationCount() > 0) {
            getList().removeItemDecorationAt(0);
        }
        if (this.displayInCards) {
            getList().setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
            getList().addItemDecoration(new TitleListView$manageDisplay$1(this));
            getList().setPadding(KotlinExtensionsKt.getDp(12), 0, KotlinExtensionsKt.getDp(12), 0);
        } else {
            getList().setLayoutManager(new LinearLayoutManager(getContext(), 1, false));
            getList().setPadding(0, 0, 0, 0);
        }
        RecyclerView.Adapter adapter = getList().getAdapter();
        if (adapter != null) {
            getList().setAdapter(adapter);
        }
    }

    private final void initialize() {
        LayoutInflater.from(getContext()).inflate(R.layout.title_list_view, this, true);
    }
}
