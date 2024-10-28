package org.videolan.libvlc.util;

import android.app.Activity;
import android.app.Presentation;
import android.content.Context;
import android.content.DialogInterface;
import android.media.MediaRouter;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.SurfaceView;
import android.view.WindowManager;
import android.widget.FrameLayout;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import org.videolan.R;
import org.videolan.libvlc.RendererItem;

public class DisplayManager {
    private static final String TAG = "VLC/DisplayManager";
    /* access modifiers changed from: private */
    public Activity mActivity;
    private DisplayType mDisplayType;
    private MediaRouter mMediaRouter;
    private MediaRouter.SimpleCallback mMediaRouterCallback;
    private DialogInterface.OnDismissListener mOnDismissListener = new DialogInterface.OnDismissListener() {
        public void onDismiss(DialogInterface dialogInterface) {
            if (dialogInterface == DisplayManager.this.mPresentation) {
                SecondaryDisplay unused = DisplayManager.this.mPresentation = null;
                int unused2 = DisplayManager.this.mPresentationId = -1;
            }
        }
    };
    /* access modifiers changed from: private */
    public SecondaryDisplay mPresentation;
    /* access modifiers changed from: private */
    public int mPresentationId = -1;
    /* access modifiers changed from: private */
    public RendererItem mRendererItem;
    private Observer<RendererItem> mRendererObs = new Observer<RendererItem>() {
        public void onChanged(RendererItem rendererItem) {
            if (DisplayManager.this.mRendererItem != rendererItem) {
                RendererItem unused = DisplayManager.this.mRendererItem = rendererItem;
                DisplayManager.this.updateDisplayType();
            }
        }
    };
    private LiveData<RendererItem> mSelectedRenderer;
    private boolean mTextureView;

    public enum DisplayType {
        PRIMARY,
        PRESENTATION,
        RENDERER
    }

    public DisplayManager(Activity activity, LiveData<RendererItem> liveData, boolean z, boolean z2, boolean z3) {
        this.mActivity = activity;
        this.mSelectedRenderer = liveData;
        this.mMediaRouter = (MediaRouter) ContextCompat.getSystemService(activity.getApplicationContext(), MediaRouter.class);
        this.mTextureView = z;
        this.mPresentation = (z2 || z3 || liveData == null || liveData.getValue() != null) ? null : createPresentation();
        LiveData<RendererItem> liveData2 = this.mSelectedRenderer;
        if (liveData2 != null) {
            this.mRendererItem = liveData2.getValue();
            this.mSelectedRenderer.observeForever(this.mRendererObs);
        }
        this.mDisplayType = z3 ? DisplayType.PRIMARY : getCurrentType();
    }

    public boolean isPrimary() {
        return this.mDisplayType == DisplayType.PRIMARY;
    }

    public boolean isSecondary() {
        return this.mDisplayType == DisplayType.PRESENTATION;
    }

    public boolean isOnRenderer() {
        return this.mDisplayType == DisplayType.RENDERER;
    }

    public void release() {
        SecondaryDisplay secondaryDisplay = this.mPresentation;
        if (secondaryDisplay != null) {
            secondaryDisplay.dismiss();
            this.mPresentation = null;
        }
        LiveData<RendererItem> liveData = this.mSelectedRenderer;
        if (liveData != null) {
            liveData.removeObserver(this.mRendererObs);
        }
    }

    /* access modifiers changed from: private */
    public void updateDisplayType() {
        if (this.mDisplayType != getCurrentType()) {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    DisplayManager.this.mActivity.recreate();
                }
            }, 100);
        }
    }

    private DisplayType getCurrentType() {
        if (this.mPresentationId != -1) {
            return DisplayType.PRESENTATION;
        }
        if (this.mRendererItem != null) {
            return DisplayType.RENDERER;
        }
        return DisplayType.PRIMARY;
    }

    public SecondaryDisplay getPresentation() {
        return this.mPresentation;
    }

    public DisplayType getDisplayType() {
        return this.mDisplayType;
    }

    private SecondaryDisplay createPresentation() {
        MediaRouter mediaRouter = this.mMediaRouter;
        if (mediaRouter == null) {
            return null;
        }
        MediaRouter.RouteInfo selectedRoute = mediaRouter.getSelectedRoute(2);
        Display presentationDisplay = selectedRoute != null ? selectedRoute.getPresentationDisplay() : null;
        if (presentationDisplay != null) {
            SecondaryDisplay secondaryDisplay = new SecondaryDisplay(this.mActivity, presentationDisplay);
            secondaryDisplay.setOnDismissListener(this.mOnDismissListener);
            try {
                secondaryDisplay.show();
                this.mPresentationId = presentationDisplay.getDisplayId();
                return secondaryDisplay;
            } catch (WindowManager.InvalidDisplayException unused) {
                this.mPresentationId = -1;
            }
        }
        return null;
    }

    public boolean setMediaRouterCallback() {
        if (this.mMediaRouter == null || this.mMediaRouterCallback != null) {
            return false;
        }
        AnonymousClass4 r0 = new MediaRouter.SimpleCallback() {
            public void onRoutePresentationDisplayChanged(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo) {
                int displayId = routeInfo.getPresentationDisplay() != null ? routeInfo.getPresentationDisplay().getDisplayId() : -1;
                if (displayId != DisplayManager.this.mPresentationId) {
                    int unused = DisplayManager.this.mPresentationId = displayId;
                    if (displayId == -1) {
                        DisplayManager.this.removePresentation();
                    } else {
                        DisplayManager.this.updateDisplayType();
                    }
                }
            }
        };
        this.mMediaRouterCallback = r0;
        this.mMediaRouter.addCallback(2, r0);
        return true;
    }

    public void removeMediaRouterCallback() {
        MediaRouter mediaRouter = this.mMediaRouter;
        if (mediaRouter != null) {
            mediaRouter.removeCallback(this.mMediaRouterCallback);
        }
        this.mMediaRouterCallback = null;
    }

    /* access modifiers changed from: private */
    public void removePresentation() {
        if (this.mMediaRouter != null) {
            SecondaryDisplay secondaryDisplay = this.mPresentation;
            if (secondaryDisplay != null) {
                secondaryDisplay.dismiss();
                this.mPresentation = null;
            }
            updateDisplayType();
        }
    }

    public class SecondaryDisplay extends Presentation {
        public static final String TAG = "VLC/SecondaryDisplay";
        private SurfaceView mSubtitlesSurfaceView;
        private FrameLayout mSurfaceFrame;
        private SurfaceView mSurfaceView;

        public SecondaryDisplay(Context context, Display display) {
            super(context, display);
        }

        public SecondaryDisplay(Context context, Display display, int i) {
            super(context, display, i);
        }

        /* access modifiers changed from: protected */
        public void onCreate(Bundle bundle) {
            super.onCreate(bundle);
            setContentView(R.layout.player_remote);
            FrameLayout frameLayout = (FrameLayout) findViewById(R.id.remote_player_surface_frame);
            this.mSurfaceFrame = frameLayout;
            this.mSurfaceView = (SurfaceView) frameLayout.findViewById(R.id.remote_player_surface);
            SurfaceView surfaceView = (SurfaceView) this.mSurfaceFrame.findViewById(R.id.remote_subtitles_surface);
            this.mSubtitlesSurfaceView = surfaceView;
            surfaceView.setZOrderMediaOverlay(true);
            this.mSubtitlesSurfaceView.getHolder().setFormat(-3);
        }

        public FrameLayout getSurfaceFrame() {
            return this.mSurfaceFrame;
        }

        public SurfaceView getSurfaceView() {
            return this.mSurfaceView;
        }

        public SurfaceView getSubtitlesSurfaceView() {
            return this.mSubtitlesSurfaceView;
        }
    }
}
