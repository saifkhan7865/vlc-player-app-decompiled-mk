package androidx.leanback.widget;

import android.app.Activity;
import android.os.Handler;
import android.text.TextUtils;
import androidx.core.app.ActivityCompat;
import androidx.core.view.ViewCompat;
import androidx.leanback.transition.TransitionHelper;
import androidx.leanback.transition.TransitionListener;
import androidx.leanback.widget.FullWidthDetailsOverviewRowPresenter;
import java.lang.ref.WeakReference;

public class FullWidthDetailsOverviewSharedElementHelper extends FullWidthDetailsOverviewRowPresenter.Listener {
    static final boolean DEBUG = false;
    private static final long DEFAULT_TIMEOUT = 5000;
    static final String TAG = "DetailsTransitionHelper";
    Activity mActivityToRunTransition;
    private boolean mAutoStartSharedElementTransition = true;
    String mSharedElementName;
    private boolean mStartedPostpone;
    FullWidthDetailsOverviewRowPresenter.ViewHolder mViewHolder;

    static class TransitionTimeOutRunnable implements Runnable {
        WeakReference<FullWidthDetailsOverviewSharedElementHelper> mHelperRef;

        TransitionTimeOutRunnable(FullWidthDetailsOverviewSharedElementHelper fullWidthDetailsOverviewSharedElementHelper) {
            this.mHelperRef = new WeakReference<>(fullWidthDetailsOverviewSharedElementHelper);
        }

        public void run() {
            FullWidthDetailsOverviewSharedElementHelper fullWidthDetailsOverviewSharedElementHelper = (FullWidthDetailsOverviewSharedElementHelper) this.mHelperRef.get();
            if (fullWidthDetailsOverviewSharedElementHelper != null) {
                fullWidthDetailsOverviewSharedElementHelper.startPostponedEnterTransition();
            }
        }
    }

    public void setSharedElementEnterTransition(Activity activity, String str) {
        setSharedElementEnterTransition(activity, str, 5000);
    }

    public void setSharedElementEnterTransition(Activity activity, String str, long j) {
        if ((activity == null && !TextUtils.isEmpty(str)) || (activity != null && TextUtils.isEmpty(str))) {
            throw new IllegalArgumentException();
        } else if (activity != this.mActivityToRunTransition || !TextUtils.equals(str, this.mSharedElementName)) {
            this.mActivityToRunTransition = activity;
            this.mSharedElementName = str;
            setAutoStartSharedElementTransition(TransitionHelper.getSharedElementEnterTransition(activity.getWindow()) != null);
            ActivityCompat.postponeEnterTransition(this.mActivityToRunTransition);
            if (j > 0) {
                new Handler().postDelayed(new TransitionTimeOutRunnable(this), j);
            }
        }
    }

    public void setAutoStartSharedElementTransition(boolean z) {
        this.mAutoStartSharedElementTransition = z;
    }

    public boolean getAutoStartSharedElementTransition() {
        return this.mAutoStartSharedElementTransition;
    }

    public void onBindLogo(FullWidthDetailsOverviewRowPresenter.ViewHolder viewHolder) {
        this.mViewHolder = viewHolder;
        if (this.mAutoStartSharedElementTransition) {
            if (viewHolder != null) {
                ViewCompat.setTransitionName(viewHolder.getLogoViewHolder().view, (String) null);
            }
            this.mViewHolder.getDetailsDescriptionFrame().postOnAnimation(new Runnable() {
                public void run() {
                    ViewCompat.setTransitionName(FullWidthDetailsOverviewSharedElementHelper.this.mViewHolder.getLogoViewHolder().view, FullWidthDetailsOverviewSharedElementHelper.this.mSharedElementName);
                    Object sharedElementEnterTransition = TransitionHelper.getSharedElementEnterTransition(FullWidthDetailsOverviewSharedElementHelper.this.mActivityToRunTransition.getWindow());
                    if (sharedElementEnterTransition != null) {
                        TransitionHelper.addTransitionListener(sharedElementEnterTransition, new TransitionListener() {
                            public void onTransitionEnd(Object obj) {
                                if (FullWidthDetailsOverviewSharedElementHelper.this.mViewHolder.getActionsRow().isFocused()) {
                                    FullWidthDetailsOverviewSharedElementHelper.this.mViewHolder.getActionsRow().requestFocus();
                                }
                                TransitionHelper.removeTransitionListener(obj, this);
                            }
                        });
                    }
                    FullWidthDetailsOverviewSharedElementHelper.this.startPostponedEnterTransitionInternal();
                }
            });
        }
    }

    public void startPostponedEnterTransition() {
        new Handler().post(new Runnable() {
            public void run() {
                FullWidthDetailsOverviewSharedElementHelper.this.startPostponedEnterTransitionInternal();
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void startPostponedEnterTransitionInternal() {
        if (!this.mStartedPostpone && this.mViewHolder != null) {
            ActivityCompat.startPostponedEnterTransition(this.mActivityToRunTransition);
            this.mStartedPostpone = true;
        }
    }
}
