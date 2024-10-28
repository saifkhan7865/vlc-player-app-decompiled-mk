package org.videolan.vlc.gui.view;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.collection.SparseArrayCompat;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewTreeLifecycleOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.channels.ActorKt;
import kotlinx.coroutines.channels.SendChannel;
import org.videolan.resources.util.HeaderProvider;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.vlc.R;
import org.videolan.vlc.util.KextensionsKt;
import org.videolan.vlc.util.LifecycleAwareScheduler;
import org.videolan.vlc.util.SchedulerCallback;

@Metadata(d1 = {"\u0000À\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u00012\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00040\u0003j\u0002`\u00050\u00022\u00020\u00062\u00020\u0007:\u0002jkB\u0017\b\u0016\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fB\u001f\b\u0016\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0002\u0010\u000fJ \u0010F\u001a\u00020\u00122\u0006\u0010G\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010\"\u001a\u0004\u0018\u00010#J \u0010H\u001a\u00020\u000e2\u0006\u0010I\u001a\u00020\u000e2\u0006\u0010J\u001a\u00020\u000e2\u0006\u0010K\u001a\u00020\u000eH\u0002J\b\u0010L\u001a\u00020\u0012H\u0002J\u0010\u0010M\u001a\u00020\u00122\u0006\u0010\b\u001a\u00020\tH\u0002J \u0010N\u001a\u00020\u00182\u0006\u0010O\u001a\u00020P2\u0006\u0010Q\u001a\u00020'2\u0006\u0010R\u001a\u00020'H\u0002J\u001a\u0010S\u001a\u00020\u00122\u0010\u0010T\u001a\f\u0012\u0004\u0012\u00020\u00040\u0003j\u0002`\u0005H\u0016J\b\u0010U\u001a\u00020\u0012H\u0014J\u001a\u0010V\u001a\u00020\u00122\b\u0010W\u001a\u0004\u0018\u00010\u00162\u0006\u0010X\u001a\u00020\u000eH\u0016J(\u0010Y\u001a\u00020\u00122\u0006\u0010Z\u001a\u00020\u000e2\u0006\u0010[\u001a\u00020\u000e2\u0006\u0010\\\u001a\u00020\u000e2\u0006\u0010]\u001a\u00020\u000eH\u0014J\u0018\u0010^\u001a\u00020\u00122\u0006\u0010_\u001a\u00020\u00042\u0006\u0010`\u001a\u00020aH\u0016J\u0010\u0010b\u001a\u00020\u00182\u0006\u0010c\u001a\u00020dH\u0016J\u0010\u0010e\u001a\u00020\u00122\u0006\u0010f\u001a\u00020'H\u0002J\u0016\u0010g\u001a\u00020\u00122\u0006\u00107\u001a\u0002082\u0006\u00105\u001a\u000206J\u0010\u0010h\u001a\u00020\u00122\u0006\u0010f\u001a\u00020'H\u0002J\b\u0010A\u001a\u00020\u0012H\u0002J\b\u0010i\u001a\u00020\u0012H\u0002R\u001a\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\u0016X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX.¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX.¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u000eX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u000eX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0018X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\"\u001a\u0004\u0018\u00010#X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020%X.¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020'X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020)X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010*\u001a\u00020\u000e8BX\u0004¢\u0006\u0006\u001a\u0004\b+\u0010,R\u000e\u0010-\u001a\u00020'X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\u000eX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u000200X.¢\u0006\u0002\n\u0000R\u0014\u00101\u001a\u0002028VX\u0004¢\u0006\u0006\u001a\u0004\b3\u00104R\u0010\u00105\u001a\u0004\u0018\u000106X\u000e¢\u0006\u0002\n\u0000R\u000e\u00107\u001a\u000208X.¢\u0006\u0002\n\u0000R\u001a\u00109\u001a\u00020:X.¢\u0006\u000e\n\u0000\u001a\u0004\b;\u0010<\"\u0004\b=\u0010>R\u0012\u0010?\u001a\u00060@R\u00020\u0000X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010A\u001a\u00020\u0018X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010B\u001a\u00020\u000eX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010C\u001a\u00020\u000eX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010D\u001a\u00020\u0018X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010E\u001a\u00020\u0018X\u000e¢\u0006\u0002\n\u0000¨\u0006l"}, d2 = {"Lorg/videolan/vlc/gui/view/FastScroller;", "Landroid/widget/LinearLayout;", "Landroidx/lifecycle/Observer;", "Landroidx/collection/SparseArrayCompat;", "", "Lorg/videolan/resources/util/HeadersIndex;", "Lorg/videolan/vlc/util/SchedulerCallback;", "Lcom/google/android/material/appbar/AppBarLayout$OnOffsetChangedListener;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "actor", "Lkotlinx/coroutines/channels/SendChannel;", "", "getActor$annotations", "()V", "appbarLayout", "Lcom/google/android/material/appbar/AppBarLayout;", "appbarLayoutExpanded", "", "bubble", "Landroid/widget/TextView;", "coordinatorLayout", "Landroidx/coordinatorlayout/widget/CoordinatorLayout;", "currentAnimator", "Landroid/animation/AnimatorSet;", "currentHeight", "currentPosition", "fastScrolling", "floatingActionButton", "Lcom/google/android/material/floatingactionbutton/FloatingActionButton;", "handle", "Landroid/widget/ImageView;", "hiddenTranslationX", "", "isAnimating", "Ljava/util/concurrent/atomic/AtomicBoolean;", "itemCount", "getItemCount", "()I", "lastPosition", "lastVerticalOffset", "layoutManager", "Landroidx/recyclerview/widget/LinearLayoutManager;", "lifecycle", "Landroidx/lifecycle/Lifecycle;", "getLifecycle", "()Landroidx/lifecycle/Lifecycle;", "provider", "Lorg/videolan/resources/util/HeaderProvider;", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "scheduler", "Lorg/videolan/vlc/util/LifecycleAwareScheduler;", "getScheduler", "()Lorg/videolan/vlc/util/LifecycleAwareScheduler;", "setScheduler", "(Lorg/videolan/vlc/util/LifecycleAwareScheduler;)V", "scrollListener", "Lorg/videolan/vlc/gui/view/FastScroller$ScrollListener;", "showBubble", "timesScrollingDown", "timesScrollingUp", "tryCollapseAppbarOnNextScroll", "tryExpandAppbarOnNextScroll", "attachToCoordinator", "appBarLayout", "getValueInRange", "min", "max", "value", "hideBubble", "initialize", "isViewContains", "view", "Landroid/view/View;", "rx", "ry", "onChanged", "t", "onDetachedFromWindow", "onOffsetChanged", "appBar", "verticalOffset", "onSizeChanged", "w", "h", "oldw", "oldh", "onTaskTriggered", "id", "data", "Landroid/os/Bundle;", "onTouchEvent", "event", "Landroid/view/MotionEvent;", "setPosition", "y", "setRecyclerView", "setRecyclerViewPosition", "updatePositions", "ScrollListener", "SeparatedAdapter", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: FastScroller.kt */
public final class FastScroller extends LinearLayout implements Observer<SparseArrayCompat<String>>, SchedulerCallback, AppBarLayout.OnOffsetChangedListener {
    private final SendChannel<Unit> actor = ActorKt.actor$default(KextensionsKt.getScope(this), (CoroutineContext) null, -1, (CoroutineStart) null, (Function1) null, new FastScroller$actor$1(this, (Continuation<? super FastScroller$actor$1>) null), 13, (Object) null);
    /* access modifiers changed from: private */
    public AppBarLayout appbarLayout;
    private boolean appbarLayoutExpanded = true;
    /* access modifiers changed from: private */
    public TextView bubble;
    private CoordinatorLayout coordinatorLayout;
    /* access modifiers changed from: private */
    public AnimatorSet currentAnimator;
    private int currentHeight;
    private int currentPosition;
    /* access modifiers changed from: private */
    public boolean fastScrolling;
    /* access modifiers changed from: private */
    public FloatingActionButton floatingActionButton;
    private ImageView handle;
    private final float hiddenTranslationX = ((float) KotlinExtensionsKt.getDp(38));
    /* access modifiers changed from: private */
    public final AtomicBoolean isAnimating = new AtomicBoolean(false);
    private float lastPosition;
    /* access modifiers changed from: private */
    public int lastVerticalOffset;
    /* access modifiers changed from: private */
    public LinearLayoutManager layoutManager;
    /* access modifiers changed from: private */
    public HeaderProvider provider;
    private RecyclerView recyclerView;
    public LifecycleAwareScheduler scheduler;
    private final ScrollListener scrollListener = new ScrollListener();
    private boolean showBubble;
    private int timesScrollingDown;
    private int timesScrollingUp;
    /* access modifiers changed from: private */
    public boolean tryCollapseAppbarOnNextScroll;
    /* access modifiers changed from: private */
    public boolean tryExpandAppbarOnNextScroll;

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0004"}, d2 = {"Lorg/videolan/vlc/gui/view/FastScroller$SeparatedAdapter;", "", "hasSections", "", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: FastScroller.kt */
    public interface SeparatedAdapter {
        boolean hasSections();
    }

    private static /* synthetic */ void getActor$annotations() {
    }

    public void onTaskCancelled(String str) {
        SchedulerCallback.DefaultImpls.onTaskCancelled(this, str);
    }

    private final int getItemCount() {
        RecyclerView recyclerView2 = this.recyclerView;
        if (recyclerView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
            recyclerView2 = null;
        }
        RecyclerView.Adapter adapter = recyclerView2.getAdapter();
        if (adapter != null) {
            return adapter.getItemCount();
        }
        return 0;
    }

    public final LifecycleAwareScheduler getScheduler() {
        LifecycleAwareScheduler lifecycleAwareScheduler = this.scheduler;
        if (lifecycleAwareScheduler != null) {
            return lifecycleAwareScheduler;
        }
        Intrinsics.throwUninitializedPropertyAccessException("scheduler");
        return null;
    }

    public final void setScheduler(LifecycleAwareScheduler lifecycleAwareScheduler) {
        Intrinsics.checkNotNullParameter(lifecycleAwareScheduler, "<set-?>");
        this.scheduler = lifecycleAwareScheduler;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FastScroller(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributeSet, "attrs");
        initialize(context);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FastScroller(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributeSet, "attrs");
        initialize(context);
    }

    private final void initialize(Context context) {
        setScheduler(new LifecycleAwareScheduler(this));
        setOrientation(0);
        setClipChildren(false);
        LayoutInflater.from(context).inflate(R.layout.fastscroller, this);
        View findViewById = findViewById(R.id.fastscroller_handle);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.handle = (ImageView) findViewById;
        View findViewById2 = findViewById(R.id.fastscroller_bubble);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        this.bubble = (TextView) findViewById2;
        setTranslationX(this.hiddenTranslationX);
        setPadding(KotlinExtensionsKt.getDp(24), 0, 0, 0);
    }

    public final void attachToCoordinator(AppBarLayout appBarLayout, CoordinatorLayout coordinatorLayout2, FloatingActionButton floatingActionButton2) {
        Intrinsics.checkNotNullParameter(appBarLayout, "appBarLayout");
        Intrinsics.checkNotNullParameter(coordinatorLayout2, "coordinatorLayout");
        this.coordinatorLayout = coordinatorLayout2;
        this.appbarLayout = appBarLayout;
        this.floatingActionButton = floatingActionButton2;
        appBarLayout.addOnOffsetChangedListener((AppBarLayout.OnOffsetChangedListener) this);
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.currentHeight = i2;
        if (this.recyclerView != null) {
            updatePositions();
        }
    }

    private final void showBubble() {
        if (getItemCount() >= 25) {
            AnimatorSet animatorSet = new AnimatorSet();
            TextView textView = this.bubble;
            TextView textView2 = null;
            if (textView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bubble");
                textView = null;
            }
            TextView textView3 = this.bubble;
            if (textView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bubble");
                textView3 = null;
            }
            textView.setPivotX((float) textView3.getWidth());
            TextView textView4 = this.bubble;
            if (textView4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bubble");
                textView4 = null;
            }
            TextView textView5 = this.bubble;
            if (textView5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bubble");
                textView5 = null;
            }
            textView4.setPivotY((float) textView5.getHeight());
            ScrollListener scrollListener2 = this.scrollListener;
            RecyclerView recyclerView2 = this.recyclerView;
            if (recyclerView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
                recyclerView2 = null;
            }
            scrollListener2.onScrolled(recyclerView2, 0, 0);
            TextView textView6 = this.bubble;
            if (textView6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bubble");
                textView6 = null;
            }
            textView6.setVisibility(0);
            TextView textView7 = this.bubble;
            if (textView7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bubble");
                textView7 = null;
            }
            ObjectAnimator duration = ObjectAnimator.ofFloat(textView7, LinearLayout.SCALE_X, new float[]{0.0f, 1.0f}).setDuration(100);
            Intrinsics.checkNotNullExpressionValue(duration, "setDuration(...)");
            TextView textView8 = this.bubble;
            if (textView8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bubble");
                textView8 = null;
            }
            ObjectAnimator duration2 = ObjectAnimator.ofFloat(textView8, LinearLayout.SCALE_Y, new float[]{0.0f, 1.0f}).setDuration(100);
            Intrinsics.checkNotNullExpressionValue(duration2, "setDuration(...)");
            TextView textView9 = this.bubble;
            if (textView9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bubble");
            } else {
                textView2 = textView9;
            }
            ObjectAnimator duration3 = ObjectAnimator.ofFloat(textView2, LinearLayout.ALPHA, new float[]{0.0f, 1.0f}).setDuration(100);
            Intrinsics.checkNotNullExpressionValue(duration3, "setDuration(...)");
            animatorSet.playTogether(new Animator[]{duration, duration2, duration3});
            animatorSet.start();
        }
    }

    private final void hideBubble() {
        this.currentAnimator = new AnimatorSet();
        TextView textView = this.bubble;
        TextView textView2 = null;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bubble");
            textView = null;
        }
        TextView textView3 = this.bubble;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bubble");
            textView3 = null;
        }
        textView.setPivotX((float) textView3.getWidth());
        TextView textView4 = this.bubble;
        if (textView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bubble");
            textView4 = null;
        }
        TextView textView5 = this.bubble;
        if (textView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bubble");
            textView5 = null;
        }
        textView4.setPivotY((float) textView5.getHeight());
        TextView textView6 = this.bubble;
        if (textView6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bubble");
            textView6 = null;
        }
        ObjectAnimator duration = ObjectAnimator.ofFloat(textView6, LinearLayout.SCALE_X, new float[]{1.0f, 0.0f}).setDuration(100);
        Intrinsics.checkNotNullExpressionValue(duration, "setDuration(...)");
        TextView textView7 = this.bubble;
        if (textView7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bubble");
            textView7 = null;
        }
        ObjectAnimator duration2 = ObjectAnimator.ofFloat(textView7, LinearLayout.SCALE_Y, new float[]{1.0f, 0.0f}).setDuration(100);
        Intrinsics.checkNotNullExpressionValue(duration2, "setDuration(...)");
        TextView textView8 = this.bubble;
        if (textView8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bubble");
        } else {
            textView2 = textView8;
        }
        ObjectAnimator duration3 = ObjectAnimator.ofFloat(textView2, LinearLayout.ALPHA, new float[]{1.0f, 0.0f}).setDuration(100);
        Intrinsics.checkNotNullExpressionValue(duration3, "setDuration(...)");
        AnimatorSet animatorSet = this.currentAnimator;
        if (animatorSet != null) {
            animatorSet.playTogether(new Animator[]{duration, duration2, duration3});
        }
        AnimatorSet animatorSet2 = this.currentAnimator;
        if (animatorSet2 != null) {
            animatorSet2.addListener(new FastScroller$hideBubble$1(this));
        }
        AnimatorSet animatorSet3 = this.currentAnimator;
        if (animatorSet3 != null) {
            animatorSet3.start();
        }
    }

    private final void setPosition(float f) {
        float f2 = f / ((float) this.currentHeight);
        ImageView imageView = this.handle;
        TextView textView = null;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("handle");
            imageView = null;
        }
        int height = imageView.getHeight();
        ImageView imageView2 = this.handle;
        if (imageView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("handle");
            imageView2 = null;
        }
        int i = this.currentHeight;
        imageView2.setY((float) getValueInRange(0, i - height, (int) (((float) (i - height)) * f2)));
        TextView textView2 = this.bubble;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bubble");
            textView2 = null;
        }
        int height2 = textView2.getHeight();
        TextView textView3 = this.bubble;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bubble");
        } else {
            textView = textView3;
        }
        int i2 = this.currentHeight;
        textView.setY((float) getValueInRange(0, i2 - height2, ((int) (((float) (i2 - height2)) * f2)) - height));
    }

    public final void setRecyclerView(RecyclerView recyclerView2, HeaderProvider headerProvider) {
        LiveData<SparseArrayCompat<String>> liveHeaders;
        Intrinsics.checkNotNullParameter(recyclerView2, "recyclerView");
        Intrinsics.checkNotNullParameter(headerProvider, "provider");
        this.recyclerView = recyclerView2;
        RecyclerView.LayoutManager layoutManager2 = recyclerView2.getLayoutManager();
        Intrinsics.checkNotNull(layoutManager2, "null cannot be cast to non-null type androidx.recyclerview.widget.LinearLayoutManager");
        this.layoutManager = (LinearLayoutManager) layoutManager2;
        RecyclerView recyclerView3 = this.recyclerView;
        if (recyclerView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
            recyclerView3 = null;
        }
        recyclerView3.removeOnScrollListener(this.scrollListener);
        LifecycleAwareScheduler.startAction$default(getScheduler(), "hide_handle", (Bundle) null, 2, (Object) null);
        HeaderProvider headerProvider2 = this.provider;
        if (!(headerProvider2 == null || (liveHeaders = headerProvider2.getLiveHeaders()) == null)) {
            liveHeaders.removeObserver(this);
        }
        this.provider = headerProvider;
        headerProvider.getLiveHeaders().observe(this, this);
        recyclerView2.addOnScrollListener(this.scrollListener);
        RecyclerView.Adapter adapter = recyclerView2.getAdapter();
        Intrinsics.checkNotNull(adapter, "null cannot be cast to non-null type org.videolan.vlc.gui.view.FastScroller.SeparatedAdapter");
        this.showBubble = ((SeparatedAdapter) adapter).hasSections();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        LiveData<SparseArrayCompat<String>> liveHeaders;
        LinearLayoutManager linearLayoutManager = this.layoutManager;
        if (linearLayoutManager != null) {
            if (linearLayoutManager == null) {
                Intrinsics.throwUninitializedPropertyAccessException("layoutManager");
                linearLayoutManager = null;
            }
            RecyclerView recyclerView2 = this.recyclerView;
            if (recyclerView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
                recyclerView2 = null;
            }
            linearLayoutManager.onDetachedFromWindow(recyclerView2);
        }
        AppBarLayout appBarLayout = this.appbarLayout;
        if (appBarLayout != null) {
            if (appBarLayout == null) {
                Intrinsics.throwUninitializedPropertyAccessException("appbarLayout");
                appBarLayout = null;
            }
            appBarLayout.removeOnOffsetChangedListener((AppBarLayout.OnOffsetChangedListener) this);
        }
        HeaderProvider headerProvider = this.provider;
        if (!(headerProvider == null || (liveHeaders = headerProvider.getLiveHeaders()) == null)) {
            liveHeaders.removeObserver(this);
        }
        this.provider = null;
        SendChannel.DefaultImpls.close$default(this.actor, (Throwable) null, 1, (Object) null);
        super.onDetachedFromWindow();
    }

    /* JADX WARNING: type inference failed for: r3v0 */
    /* JADX WARNING: type inference failed for: r3v1, types: [android.widget.TextView] */
    /* JADX WARNING: type inference failed for: r3v3 */
    /* JADX WARNING: type inference failed for: r3v4, types: [androidx.recyclerview.widget.RecyclerView] */
    /* JADX WARNING: type inference failed for: r3v5 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouchEvent(android.view.MotionEvent r20) {
        /*
            r19 = this;
            r0 = r19
            java.lang.String r1 = "event"
            r2 = r20
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r1)
            int r1 = r20.getAction()
            r3 = 0
            if (r1 != 0) goto L_0x002f
            android.widget.ImageView r1 = r0.handle
            if (r1 != 0) goto L_0x001a
            java.lang.String r1 = "handle"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            r1 = r3
        L_0x001a:
            android.view.View r1 = (android.view.View) r1
            float r4 = r20.getRawX()
            float r5 = r20.getRawY()
            boolean r1 = r0.isViewContains(r1, r4, r5)
            if (r1 != 0) goto L_0x002f
            boolean r1 = super.onTouchEvent(r20)
            return r1
        L_0x002f:
            int r1 = r20.getAction()
            r4 = 1
            if (r1 == 0) goto L_0x008d
            int r1 = r20.getAction()
            r5 = 2
            if (r1 != r5) goto L_0x003e
            goto L_0x008d
        L_0x003e:
            int r1 = r20.getAction()
            if (r1 != r4) goto L_0x0088
            r1 = 0
            r0.fastScrolling = r1
            org.videolan.vlc.util.LifecycleAwareScheduler r5 = r19.getScheduler()
            r10 = 4
            r11 = 0
            java.lang.String r6 = "hide_handle"
            r7 = 1000(0x3e8, double:4.94E-321)
            r9 = 0
            org.videolan.vlc.util.LifecycleAwareScheduler.scheduleAction$default(r5, r6, r7, r9, r10, r11)
            org.videolan.vlc.util.LifecycleAwareScheduler r12 = r19.getScheduler()
            r17 = 4
            r18 = 0
            java.lang.String r13 = "hide_scroller"
            r14 = 3000(0xbb8, double:1.482E-320)
            r16 = 0
            org.videolan.vlc.util.LifecycleAwareScheduler.scheduleAction$default(r12, r13, r14, r16, r17, r18)
            float r1 = r20.getY()
            int r2 = r0.currentHeight
            float r2 = (float) r2
            float r1 = r1 / r2
            r2 = 1065185444(0x3f7d70a4, float:0.99)
            int r1 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            if (r1 <= 0) goto L_0x0087
            androidx.recyclerview.widget.RecyclerView r1 = r0.recyclerView
            if (r1 != 0) goto L_0x007f
            java.lang.String r1 = "recyclerView"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            goto L_0x0080
        L_0x007f:
            r3 = r1
        L_0x0080:
            int r1 = r19.getItemCount()
            r3.smoothScrollToPosition(r1)
        L_0x0087:
            return r4
        L_0x0088:
            boolean r1 = super.onTouchEvent(r20)
            return r1
        L_0x008d:
            r0.fastScrolling = r4
            r1 = -1
            r0.currentPosition = r1
            android.animation.AnimatorSet r1 = r0.currentAnimator
            if (r1 == 0) goto L_0x009b
            if (r1 == 0) goto L_0x009b
            r1.cancel()
        L_0x009b:
            org.videolan.vlc.util.LifecycleAwareScheduler r1 = r19.getScheduler()
            java.lang.String r5 = "hide_scroller"
            r1.cancelAction(r5)
            org.videolan.vlc.util.LifecycleAwareScheduler r1 = r19.getScheduler()
            java.lang.String r5 = "hide_handle"
            r1.cancelAction(r5)
            boolean r1 = r0.showBubble
            if (r1 == 0) goto L_0x00c7
            android.widget.TextView r1 = r0.bubble
            if (r1 != 0) goto L_0x00bb
            java.lang.String r1 = "bubble"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            goto L_0x00bc
        L_0x00bb:
            r3 = r1
        L_0x00bc:
            int r1 = r3.getVisibility()
            r3 = 8
            if (r1 != r3) goto L_0x00c7
            r19.showBubble()
        L_0x00c7:
            float r1 = r20.getY()
            r0.setRecyclerViewPosition(r1)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.view.FastScroller.onTouchEvent(android.view.MotionEvent):boolean");
    }

    private final boolean isViewContains(View view, float f, float f2) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        int dp = iArr[0] - KotlinExtensionsKt.getDp(32);
        int dp2 = iArr[1] + KotlinExtensionsKt.getDp(8);
        int width = view.getWidth() + KotlinExtensionsKt.getDp(40);
        int height = view.getHeight() + KotlinExtensionsKt.getDp(8);
        if (f < ((float) dp) || f > ((float) (dp + width)) || f2 < ((float) dp2) || f2 > ((float) (dp2 + height))) {
            return false;
        }
        return true;
    }

    private final void setRecyclerViewPosition(float f) {
        int valueInRange;
        if (this.recyclerView != null && (valueInRange = getValueInRange(0, getItemCount(), MathKt.roundToInt((f / ((float) this.currentHeight)) * ((float) getItemCount())))) != this.currentPosition) {
            if (!this.isAnimating.get()) {
                float f2 = this.lastPosition;
                if (f2 < f) {
                    this.timesScrollingUp = 0;
                    this.timesScrollingDown++;
                } else if (f2 > f) {
                    this.timesScrollingUp++;
                    this.timesScrollingDown = 0;
                }
            }
            this.currentPosition = valueInRange;
            RecyclerView recyclerView2 = this.recyclerView;
            if (recyclerView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
                recyclerView2 = null;
            }
            recyclerView2.scrollToPosition(valueInRange);
            if (!this.isAnimating.get() && this.appbarLayoutExpanded && this.timesScrollingDown > 3) {
                this.tryCollapseAppbarOnNextScroll = true;
                this.appbarLayoutExpanded = false;
                this.isAnimating.set(true);
                this.timesScrollingUp = 0;
                this.timesScrollingDown = 0;
            } else if (!this.isAnimating.get() && !this.appbarLayoutExpanded && this.timesScrollingUp > 3) {
                this.tryExpandAppbarOnNextScroll = true;
                this.appbarLayoutExpanded = true;
                this.isAnimating.set(true);
                this.timesScrollingUp = 0;
                this.timesScrollingDown = 0;
            }
            this.lastPosition = f;
        }
    }

    private final int getValueInRange(int i, int i2, int i3) {
        return Math.min(Math.max(i, i3), i2);
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016¨\u0006\n"}, d2 = {"Lorg/videolan/vlc/gui/view/FastScroller$ScrollListener;", "Landroidx/recyclerview/widget/RecyclerView$OnScrollListener;", "(Lorg/videolan/vlc/gui/view/FastScroller;)V", "onScrolled", "", "rv", "Landroidx/recyclerview/widget/RecyclerView;", "dx", "", "dy", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: FastScroller.kt */
    private final class ScrollListener extends RecyclerView.OnScrollListener {
        public ScrollListener() {
        }

        public void onScrolled(RecyclerView recyclerView, int i, int i2) {
            Intrinsics.checkNotNullParameter(recyclerView, "rv");
            FastScroller.this.updatePositions();
            AppBarLayout access$getAppbarLayout$p = FastScroller.this.appbarLayout;
            AppBarLayout appBarLayout = null;
            if (access$getAppbarLayout$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("appbarLayout");
                access$getAppbarLayout$p = null;
            }
            int totalScrollRange = access$getAppbarLayout$p.getTotalScrollRange();
            FastScroller fastScroller = FastScroller.this;
            if (fastScroller.tryCollapseAppbarOnNextScroll && fastScroller.lastVerticalOffset != (-totalScrollRange)) {
                if (!fastScroller.isAnimating.get()) {
                    AppBarLayout access$getAppbarLayout$p2 = fastScroller.appbarLayout;
                    if (access$getAppbarLayout$p2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("appbarLayout");
                        access$getAppbarLayout$p2 = null;
                    }
                    access$getAppbarLayout$p2.setExpanded(false);
                    FloatingActionButton access$getFloatingActionButton$p = fastScroller.floatingActionButton;
                    if (access$getFloatingActionButton$p != null) {
                        access$getFloatingActionButton$p.hide();
                    }
                    fastScroller.isAnimating.set(true);
                }
                fastScroller.tryCollapseAppbarOnNextScroll = false;
            }
            if (fastScroller.tryExpandAppbarOnNextScroll && fastScroller.lastVerticalOffset == (-totalScrollRange)) {
                if (!fastScroller.isAnimating.get()) {
                    AppBarLayout access$getAppbarLayout$p3 = fastScroller.appbarLayout;
                    if (access$getAppbarLayout$p3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("appbarLayout");
                    } else {
                        appBarLayout = access$getAppbarLayout$p3;
                    }
                    appBarLayout.setExpanded(true);
                    FloatingActionButton access$getFloatingActionButton$p2 = fastScroller.floatingActionButton;
                    if (access$getFloatingActionButton$p2 != null) {
                        access$getFloatingActionButton$p2.show();
                    }
                    fastScroller.isAnimating.set(true);
                }
                fastScroller.tryExpandAppbarOnNextScroll = false;
            }
        }
    }

    /* access modifiers changed from: private */
    public final void updatePositions() {
        RecyclerView recyclerView2 = this.recyclerView;
        if (recyclerView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
            recyclerView2 = null;
        }
        int computeVerticalScrollOffset = recyclerView2.computeVerticalScrollOffset();
        RecyclerView recyclerView3 = this.recyclerView;
        if (recyclerView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
            recyclerView3 = null;
        }
        int computeVerticalScrollRange = recyclerView3.computeVerticalScrollRange();
        RecyclerView recyclerView4 = this.recyclerView;
        if (recyclerView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
            recyclerView4 = null;
        }
        int computeVerticalScrollExtent = computeVerticalScrollRange - recyclerView4.computeVerticalScrollExtent();
        setPosition(((float) this.currentHeight) * (computeVerticalScrollExtent == 0 ? 0.0f : ((float) computeVerticalScrollOffset) / ((float) computeVerticalScrollExtent)));
        LifecycleAwareScheduler.startAction$default(getScheduler(), "show_scroller", (Bundle) null, 2, (Object) null);
        this.actor.m1139trySendJP2dKIU(Unit.INSTANCE);
    }

    public void onChanged(SparseArrayCompat<String> sparseArrayCompat) {
        Intrinsics.checkNotNullParameter(sparseArrayCompat, "t");
        this.actor.m1139trySendJP2dKIU(Unit.INSTANCE);
    }

    public void onTaskTriggered(String str, Bundle bundle) {
        Intrinsics.checkNotNullParameter(str, "id");
        Intrinsics.checkNotNullParameter(bundle, "data");
        int hashCode = str.hashCode();
        if (hashCode != -2066509161) {
            if (hashCode != -301170747) {
                if (hashCode == 1338038076 && str.equals("show_scroller") && getItemCount() >= 25) {
                    setTranslationX((float) KotlinExtensionsKt.getDp(0));
                    getScheduler().cancelAction("hide_scroller");
                    LifecycleAwareScheduler.scheduleAction$default(getScheduler(), "hide_scroller", 3000, (Bundle) null, 4, (Object) null);
                }
            } else if (str.equals("hide_handle")) {
                hideBubble();
            }
        } else if (str.equals("hide_scroller")) {
            animate().translationX(this.hiddenTranslationX);
        }
    }

    public Lifecycle getLifecycle() {
        LifecycleOwner lifecycleOwner = ViewTreeLifecycleOwner.get(this);
        Intrinsics.checkNotNull(lifecycleOwner);
        return lifecycleOwner.getLifecycle();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:32:0x007d, code lost:
        if (r1.getTop() == 0) goto L_0x007f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onOffsetChanged(com.google.android.material.appbar.AppBarLayout r6, int r7) {
        /*
            r5 = this;
            android.view.ViewGroup$LayoutParams r6 = r5.getLayoutParams()
            androidx.coordinatorlayout.widget.CoordinatorLayout r0 = r5.coordinatorLayout
            r1 = 0
            if (r0 != 0) goto L_0x000f
            java.lang.String r0 = "coordinatorLayout"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
            r0 = r1
        L_0x000f:
            int r0 = r0.getHeight()
            com.google.android.material.appbar.AppBarLayout r2 = r5.appbarLayout
            java.lang.String r3 = "appbarLayout"
            if (r2 != 0) goto L_0x001d
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
            r2 = r1
        L_0x001d:
            int r2 = r2.getHeight()
            com.google.android.material.appbar.AppBarLayout r4 = r5.appbarLayout
            if (r4 != 0) goto L_0x0029
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
            r4 = r1
        L_0x0029:
            int r4 = r4.getTop()
            int r2 = r2 + r4
            int r0 = r0 - r2
            r6.height = r0
            r5.invalidate()
            com.google.android.material.appbar.AppBarLayout r6 = r5.appbarLayout
            if (r6 != 0) goto L_0x003c
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
            r6 = r1
        L_0x003c:
            int r6 = r6.getTop()
            com.google.android.material.appbar.AppBarLayout r0 = r5.appbarLayout
            if (r0 != 0) goto L_0x0048
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
            r0 = r1
        L_0x0048:
            int r0 = r0.getHeight()
            int r0 = -r0
            r2 = 0
            if (r6 <= r0) goto L_0x0052
            r6 = 1
            goto L_0x0053
        L_0x0052:
            r6 = 0
        L_0x0053:
            r5.appbarLayoutExpanded = r6
            com.google.android.material.appbar.AppBarLayout r6 = r5.appbarLayout
            if (r6 != 0) goto L_0x005d
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
            r6 = r1
        L_0x005d:
            int r6 = r6.getHeight()
            com.google.android.material.appbar.AppBarLayout r0 = r5.appbarLayout
            if (r0 != 0) goto L_0x0069
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
            r0 = r1
        L_0x0069:
            int r0 = r0.getTop()
            int r0 = -r0
            if (r6 == r0) goto L_0x007f
            com.google.android.material.appbar.AppBarLayout r6 = r5.appbarLayout
            if (r6 != 0) goto L_0x0078
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
            goto L_0x0079
        L_0x0078:
            r1 = r6
        L_0x0079:
            int r6 = r1.getTop()
            if (r6 != 0) goto L_0x0084
        L_0x007f:
            java.util.concurrent.atomic.AtomicBoolean r6 = r5.isAnimating
            r6.set(r2)
        L_0x0084:
            r5.lastVerticalOffset = r7
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.view.FastScroller.onOffsetChanged(com.google.android.material.appbar.AppBarLayout, int):void");
    }
}
