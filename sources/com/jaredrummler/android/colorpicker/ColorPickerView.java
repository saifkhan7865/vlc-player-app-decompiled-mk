package com.jaredrummler.android.colorpicker;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.text.util.LocalePreferences;
import androidx.core.view.ViewCompat;

public class ColorPickerView extends View {
    private static final int ALPHA_PANEL_HEIGH_DP = 20;
    private static final int BORDER_WIDTH_PX = 1;
    private static final int CIRCLE_TRACKER_RADIUS_DP = 5;
    private static final int DEFAULT_BORDER_COLOR = -9539986;
    private static final int DEFAULT_SLIDER_COLOR = -4342339;
    private static final int HUE_PANEL_WDITH_DP = 30;
    private static final int PANEL_SPACING_DP = 10;
    private static final int SLIDER_TRACKER_OFFSET_DP = 2;
    private static final int SLIDER_TRACKER_SIZE_DP = 4;
    private int alpha;
    private Paint alphaPaint;
    private int alphaPanelHeightPx;
    private AlphaPatternDrawable alphaPatternDrawable;
    private Rect alphaRect;
    private Shader alphaShader;
    private String alphaSliderText;
    private Paint alphaTextPaint;
    private int borderColor;
    private Paint borderPaint;
    private int circleTrackerRadiusPx;
    private Rect drawingRect;
    private float hue;
    private Paint hueAlphaTrackerPaint;
    private BitmapCache hueBackgroundCache;
    private int huePanelWidthPx;
    private Rect hueRect;
    private int mRequiredPadding;
    private OnColorChangedListener onColorChangedListener;
    private int panelSpacingPx;
    private float sat;
    private Shader satShader;
    private BitmapCache satValBackgroundCache;
    private Paint satValPaint;
    private Rect satValRect;
    private Paint satValTrackerPaint;
    private boolean showAlphaPanel;
    private int sliderTrackerColor;
    private int sliderTrackerOffsetPx;
    private int sliderTrackerSizePx;
    private Point startTouchPoint;
    private float val;
    private Shader valShader;

    public interface OnColorChangedListener {
        void onColorChanged(int i);
    }

    public ColorPickerView(Context context) {
        this(context, (AttributeSet) null);
    }

    public ColorPickerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ColorPickerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.alpha = 255;
        this.hue = 360.0f;
        this.sat = 0.0f;
        this.val = 0.0f;
        this.showAlphaPanel = false;
        this.alphaSliderText = null;
        this.sliderTrackerColor = DEFAULT_SLIDER_COLOR;
        this.borderColor = DEFAULT_BORDER_COLOR;
        this.startTouchPoint = null;
        init(context, attributeSet);
    }

    public Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("instanceState", super.onSaveInstanceState());
        bundle.putInt("alpha", this.alpha);
        bundle.putFloat("hue", this.hue);
        bundle.putFloat(LocalePreferences.FirstDayOfWeek.SATURDAY, this.sat);
        bundle.putFloat("val", this.val);
        bundle.putBoolean("show_alpha", this.showAlphaPanel);
        bundle.putString("alpha_text", this.alphaSliderText);
        return bundle;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            this.alpha = bundle.getInt("alpha");
            this.hue = bundle.getFloat("hue");
            this.sat = bundle.getFloat(LocalePreferences.FirstDayOfWeek.SATURDAY);
            this.val = bundle.getFloat("val");
            this.showAlphaPanel = bundle.getBoolean("show_alpha");
            this.alphaSliderText = bundle.getString("alpha_text");
            parcelable = bundle.getParcelable("instanceState");
        }
        super.onRestoreInstanceState(parcelable);
    }

    private void init(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.ColorPickerView);
        this.showAlphaPanel = obtainStyledAttributes.getBoolean(R.styleable.ColorPickerView_cpv_alphaChannelVisible, false);
        this.alphaSliderText = obtainStyledAttributes.getString(R.styleable.ColorPickerView_cpv_alphaChannelText);
        this.sliderTrackerColor = obtainStyledAttributes.getColor(R.styleable.ColorPickerView_cpv_sliderColor, DEFAULT_SLIDER_COLOR);
        this.borderColor = obtainStyledAttributes.getColor(R.styleable.ColorPickerView_cpv_borderColor, DEFAULT_BORDER_COLOR);
        obtainStyledAttributes.recycle();
        applyThemeColors(context);
        this.huePanelWidthPx = DrawingUtils.dpToPx(getContext(), 30.0f);
        this.alphaPanelHeightPx = DrawingUtils.dpToPx(getContext(), 20.0f);
        this.panelSpacingPx = DrawingUtils.dpToPx(getContext(), 10.0f);
        this.circleTrackerRadiusPx = DrawingUtils.dpToPx(getContext(), 5.0f);
        this.sliderTrackerSizePx = DrawingUtils.dpToPx(getContext(), 4.0f);
        this.sliderTrackerOffsetPx = DrawingUtils.dpToPx(getContext(), 2.0f);
        this.mRequiredPadding = getResources().getDimensionPixelSize(R.dimen.cpv_required_padding);
        initPaintTools();
        setFocusable(true);
        setFocusableInTouchMode(true);
    }

    private void applyThemeColors(Context context) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(new TypedValue().data, new int[]{16842808});
        if (this.borderColor == DEFAULT_BORDER_COLOR) {
            this.borderColor = obtainStyledAttributes.getColor(0, DEFAULT_BORDER_COLOR);
        }
        if (this.sliderTrackerColor == DEFAULT_SLIDER_COLOR) {
            this.sliderTrackerColor = obtainStyledAttributes.getColor(0, DEFAULT_SLIDER_COLOR);
        }
        obtainStyledAttributes.recycle();
    }

    private void initPaintTools() {
        this.satValPaint = new Paint();
        this.satValTrackerPaint = new Paint();
        this.hueAlphaTrackerPaint = new Paint();
        this.alphaPaint = new Paint();
        this.alphaTextPaint = new Paint();
        this.borderPaint = new Paint();
        this.satValTrackerPaint.setStyle(Paint.Style.STROKE);
        this.satValTrackerPaint.setStrokeWidth((float) DrawingUtils.dpToPx(getContext(), 2.0f));
        this.satValTrackerPaint.setAntiAlias(true);
        this.hueAlphaTrackerPaint.setColor(this.sliderTrackerColor);
        this.hueAlphaTrackerPaint.setStyle(Paint.Style.STROKE);
        this.hueAlphaTrackerPaint.setStrokeWidth((float) DrawingUtils.dpToPx(getContext(), 2.0f));
        this.hueAlphaTrackerPaint.setAntiAlias(true);
        this.alphaTextPaint.setColor(-14935012);
        this.alphaTextPaint.setTextSize((float) DrawingUtils.dpToPx(getContext(), 14.0f));
        this.alphaTextPaint.setAntiAlias(true);
        this.alphaTextPaint.setTextAlign(Paint.Align.CENTER);
        this.alphaTextPaint.setFakeBoldText(true);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (this.drawingRect.width() > 0 && this.drawingRect.height() > 0) {
            drawSatValPanel(canvas);
            drawHuePanel(canvas);
            drawAlphaPanel(canvas);
        }
    }

    private void drawSatValPanel(Canvas canvas) {
        Canvas canvas2 = canvas;
        Rect rect = this.satValRect;
        this.borderPaint.setColor(this.borderColor);
        canvas.drawRect((float) this.drawingRect.left, (float) this.drawingRect.top, (float) (rect.right + 1), (float) (rect.bottom + 1), this.borderPaint);
        if (this.valShader == null) {
            this.valShader = new LinearGradient((float) rect.left, (float) rect.top, (float) rect.left, (float) rect.bottom, -1, ViewCompat.MEASURED_STATE_MASK, Shader.TileMode.CLAMP);
        }
        BitmapCache bitmapCache = this.satValBackgroundCache;
        if (bitmapCache == null || bitmapCache.value != this.hue) {
            if (this.satValBackgroundCache == null) {
                this.satValBackgroundCache = new BitmapCache();
            }
            if (this.satValBackgroundCache.bitmap == null) {
                this.satValBackgroundCache.bitmap = Bitmap.createBitmap(rect.width(), rect.height(), Bitmap.Config.ARGB_8888);
            }
            if (this.satValBackgroundCache.canvas == null) {
                this.satValBackgroundCache.canvas = new Canvas(this.satValBackgroundCache.bitmap);
            }
            this.satShader = new LinearGradient((float) rect.left, (float) rect.top, (float) rect.right, (float) rect.top, -1, Color.HSVToColor(new float[]{this.hue, 1.0f, 1.0f}), Shader.TileMode.CLAMP);
            this.satValPaint.setShader(new ComposeShader(this.valShader, this.satShader, PorterDuff.Mode.MULTIPLY));
            this.satValBackgroundCache.canvas.drawRect(0.0f, 0.0f, (float) this.satValBackgroundCache.bitmap.getWidth(), (float) this.satValBackgroundCache.bitmap.getHeight(), this.satValPaint);
            this.satValBackgroundCache.value = this.hue;
        }
        canvas2.drawBitmap(this.satValBackgroundCache.bitmap, (Rect) null, rect, (Paint) null);
        Point satValToPoint = satValToPoint(this.sat, this.val);
        this.satValTrackerPaint.setColor(ViewCompat.MEASURED_STATE_MASK);
        canvas2.drawCircle((float) satValToPoint.x, (float) satValToPoint.y, (float) (this.circleTrackerRadiusPx - DrawingUtils.dpToPx(getContext(), 1.0f)), this.satValTrackerPaint);
        this.satValTrackerPaint.setColor(-2236963);
        canvas2.drawCircle((float) satValToPoint.x, (float) satValToPoint.y, (float) this.circleTrackerRadiusPx, this.satValTrackerPaint);
    }

    private void drawHuePanel(Canvas canvas) {
        Canvas canvas2 = canvas;
        Rect rect = this.hueRect;
        this.borderPaint.setColor(this.borderColor);
        canvas.drawRect((float) (rect.left - 1), (float) (rect.top - 1), (float) (rect.right + 1), (float) (rect.bottom + 1), this.borderPaint);
        if (this.hueBackgroundCache == null) {
            BitmapCache bitmapCache = new BitmapCache();
            this.hueBackgroundCache = bitmapCache;
            bitmapCache.bitmap = Bitmap.createBitmap(rect.width(), rect.height(), Bitmap.Config.ARGB_8888);
            this.hueBackgroundCache.canvas = new Canvas(this.hueBackgroundCache.bitmap);
            int height = (int) (((float) rect.height()) + 0.5f);
            int[] iArr = new int[height];
            float f = 360.0f;
            for (int i = 0; i < height; i++) {
                iArr[i] = Color.HSVToColor(new float[]{f, 1.0f, 1.0f});
                f -= 360.0f / ((float) height);
            }
            Paint paint = new Paint();
            paint.setStrokeWidth(0.0f);
            for (int i2 = 0; i2 < height; i2++) {
                paint.setColor(iArr[i2]);
                float f2 = (float) i2;
                this.hueBackgroundCache.canvas.drawLine(0.0f, f2, (float) this.hueBackgroundCache.bitmap.getWidth(), f2, paint);
            }
        }
        canvas2.drawBitmap(this.hueBackgroundCache.bitmap, (Rect) null, rect, (Paint) null);
        Point hueToPoint = hueToPoint(this.hue);
        RectF rectF = new RectF();
        rectF.left = (float) (rect.left - this.sliderTrackerOffsetPx);
        rectF.right = (float) (rect.right + this.sliderTrackerOffsetPx);
        rectF.top = (float) (hueToPoint.y - (this.sliderTrackerSizePx / 2));
        rectF.bottom = (float) (hueToPoint.y + (this.sliderTrackerSizePx / 2));
        canvas2.drawRoundRect(rectF, 2.0f, 2.0f, this.hueAlphaTrackerPaint);
    }

    private void drawAlphaPanel(Canvas canvas) {
        Rect rect;
        Canvas canvas2 = canvas;
        if (this.showAlphaPanel && (rect = this.alphaRect) != null && this.alphaPatternDrawable != null) {
            this.borderPaint.setColor(this.borderColor);
            canvas.drawRect((float) (rect.left - 1), (float) (rect.top - 1), (float) (rect.right + 1), (float) (rect.bottom + 1), this.borderPaint);
            this.alphaPatternDrawable.draw(canvas2);
            float[] fArr = {this.hue, this.sat, this.val};
            LinearGradient linearGradient = new LinearGradient((float) rect.left, (float) rect.top, (float) rect.right, (float) rect.top, Color.HSVToColor(fArr), Color.HSVToColor(0, fArr), Shader.TileMode.CLAMP);
            this.alphaShader = linearGradient;
            this.alphaPaint.setShader(linearGradient);
            canvas2.drawRect(rect, this.alphaPaint);
            String str = this.alphaSliderText;
            if (str != null && !str.equals("")) {
                canvas2.drawText(this.alphaSliderText, (float) rect.centerX(), (float) (rect.centerY() + DrawingUtils.dpToPx(getContext(), 4.0f)), this.alphaTextPaint);
            }
            Point alphaToPoint = alphaToPoint(this.alpha);
            RectF rectF = new RectF();
            rectF.left = (float) (alphaToPoint.x - (this.sliderTrackerSizePx / 2));
            rectF.right = (float) (alphaToPoint.x + (this.sliderTrackerSizePx / 2));
            rectF.top = (float) (rect.top - this.sliderTrackerOffsetPx);
            rectF.bottom = (float) (rect.bottom + this.sliderTrackerOffsetPx);
            canvas2.drawRoundRect(rectF, 2.0f, 2.0f, this.hueAlphaTrackerPaint);
        }
    }

    private Point hueToPoint(float f) {
        Rect rect = this.hueRect;
        float height = (float) rect.height();
        Point point = new Point();
        point.y = (int) ((height - ((f * height) / 360.0f)) + ((float) rect.top));
        point.x = rect.left;
        return point;
    }

    private Point satValToPoint(float f, float f2) {
        Rect rect = this.satValRect;
        Point point = new Point();
        point.x = (int) ((f * ((float) rect.width())) + ((float) rect.left));
        point.y = (int) (((1.0f - f2) * ((float) rect.height())) + ((float) rect.top));
        return point;
    }

    private Point alphaToPoint(int i) {
        Rect rect = this.alphaRect;
        float width = (float) rect.width();
        Point point = new Point();
        point.x = (int) ((width - ((((float) i) * width) / 255.0f)) + ((float) rect.left));
        point.y = rect.top;
        return point;
    }

    private float[] pointToSatVal(float f, float f2) {
        float f3;
        Rect rect = this.satValRect;
        float width = (float) rect.width();
        float height = (float) rect.height();
        float f4 = 0.0f;
        if (f < ((float) rect.left)) {
            f3 = 0.0f;
        } else if (f > ((float) rect.right)) {
            f3 = width;
        } else {
            f3 = f - ((float) rect.left);
        }
        if (f2 >= ((float) rect.top)) {
            if (f2 > ((float) rect.bottom)) {
                f4 = height;
            } else {
                f4 = f2 - ((float) rect.top);
            }
        }
        return new float[]{(1.0f / width) * f3, 1.0f - ((1.0f / height) * f4)};
    }

    private float pointToHue(float f) {
        float f2;
        Rect rect = this.hueRect;
        float height = (float) rect.height();
        if (f < ((float) rect.top)) {
            f2 = 0.0f;
        } else if (f > ((float) rect.bottom)) {
            f2 = height;
        } else {
            f2 = f - ((float) rect.top);
        }
        return 360.0f - ((f2 * 360.0f) / height);
    }

    private int pointToAlpha(int i) {
        int i2;
        Rect rect = this.alphaRect;
        int width = rect.width();
        if (i < rect.left) {
            i2 = 0;
        } else if (i > rect.right) {
            i2 = width;
        } else {
            i2 = i - rect.left;
        }
        return 255 - ((i2 * 255) / width);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z;
        int action = motionEvent.getAction();
        if (action == 0) {
            this.startTouchPoint = new Point((int) motionEvent.getX(), (int) motionEvent.getY());
            z = moveTrackersIfNeeded(motionEvent);
        } else if (action != 1) {
            if (action == 2) {
                z = moveTrackersIfNeeded(motionEvent);
            }
            return super.onTouchEvent(motionEvent);
        } else {
            this.startTouchPoint = null;
            z = moveTrackersIfNeeded(motionEvent);
        }
        if (z) {
            OnColorChangedListener onColorChangedListener2 = this.onColorChangedListener;
            if (onColorChangedListener2 != null) {
                onColorChangedListener2.onColorChanged(Color.HSVToColor(this.alpha, new float[]{this.hue, this.sat, this.val}));
            }
            invalidate();
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }

    private boolean moveTrackersIfNeeded(MotionEvent motionEvent) {
        Point point = this.startTouchPoint;
        if (point == null) {
            return false;
        }
        int i = point.x;
        int i2 = this.startTouchPoint.y;
        if (this.hueRect.contains(i, i2)) {
            this.hue = pointToHue(motionEvent.getY());
        } else if (this.satValRect.contains(i, i2)) {
            float[] pointToSatVal = pointToSatVal(motionEvent.getX(), motionEvent.getY());
            this.sat = pointToSatVal[0];
            this.val = pointToSatVal[1];
        } else {
            Rect rect = this.alphaRect;
            if (rect == null || !rect.contains(i, i2)) {
                return false;
            }
            this.alpha = pointToAlpha((int) motionEvent.getX());
        }
        return true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0058, code lost:
        if (r0 != false) goto L_0x005a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0087, code lost:
        if (r1 > r6) goto L_0x0089;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onMeasure(int r6, int r7) {
        /*
            r5 = this;
            int r0 = android.view.View.MeasureSpec.getMode(r6)
            int r1 = android.view.View.MeasureSpec.getMode(r7)
            int r6 = android.view.View.MeasureSpec.getSize(r6)
            int r2 = r5.getPaddingLeft()
            int r6 = r6 - r2
            int r2 = r5.getPaddingRight()
            int r6 = r6 - r2
            int r7 = android.view.View.MeasureSpec.getSize(r7)
            int r2 = r5.getPaddingBottom()
            int r7 = r7 - r2
            int r2 = r5.getPaddingTop()
            int r7 = r7 - r2
            r2 = 1073741824(0x40000000, float:2.0)
            if (r0 == r2) goto L_0x005c
            if (r1 != r2) goto L_0x002b
            goto L_0x005c
        L_0x002b:
            int r0 = r5.panelSpacingPx
            int r1 = r7 + r0
            int r2 = r5.huePanelWidthPx
            int r1 = r1 + r2
            int r3 = r6 - r0
            int r3 = r3 - r2
            boolean r2 = r5.showAlphaPanel
            if (r2 == 0) goto L_0x0040
            int r2 = r5.alphaPanelHeightPx
            int r4 = r0 + r2
            int r1 = r1 - r4
            int r0 = r0 + r2
            int r3 = r3 + r0
        L_0x0040:
            r0 = 1
            r2 = 0
            if (r1 > r6) goto L_0x0046
            r4 = 1
            goto L_0x0047
        L_0x0046:
            r4 = 0
        L_0x0047:
            if (r3 > r7) goto L_0x004a
            goto L_0x004b
        L_0x004a:
            r0 = 0
        L_0x004b:
            if (r4 == 0) goto L_0x0050
            if (r0 == 0) goto L_0x0050
            goto L_0x005a
        L_0x0050:
            if (r0 != 0) goto L_0x0056
            if (r4 == 0) goto L_0x0056
        L_0x0054:
            r6 = r1
            goto L_0x0089
        L_0x0056:
            if (r4 != 0) goto L_0x0089
            if (r0 == 0) goto L_0x0089
        L_0x005a:
            r7 = r3
            goto L_0x0089
        L_0x005c:
            if (r0 != r2) goto L_0x0074
            if (r1 == r2) goto L_0x0074
            int r0 = r5.panelSpacingPx
            int r1 = r6 - r0
            int r2 = r5.huePanelWidthPx
            int r1 = r1 - r2
            boolean r2 = r5.showAlphaPanel
            if (r2 == 0) goto L_0x006f
            int r2 = r5.alphaPanelHeightPx
            int r0 = r0 + r2
            int r1 = r1 + r0
        L_0x006f:
            if (r1 <= r7) goto L_0x0072
            goto L_0x0089
        L_0x0072:
            r7 = r1
            goto L_0x0089
        L_0x0074:
            if (r1 != r2) goto L_0x0089
            if (r0 == r2) goto L_0x0089
            int r0 = r5.panelSpacingPx
            int r1 = r7 + r0
            int r2 = r5.huePanelWidthPx
            int r1 = r1 + r2
            boolean r2 = r5.showAlphaPanel
            if (r2 == 0) goto L_0x0087
            int r2 = r5.alphaPanelHeightPx
            int r0 = r0 + r2
            int r1 = r1 - r0
        L_0x0087:
            if (r1 <= r6) goto L_0x0054
        L_0x0089:
            int r0 = r5.getPaddingLeft()
            int r6 = r6 + r0
            int r0 = r5.getPaddingRight()
            int r6 = r6 + r0
            int r0 = r5.getPaddingTop()
            int r7 = r7 + r0
            int r0 = r5.getPaddingBottom()
            int r7 = r7 + r0
            r5.setMeasuredDimension(r6, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jaredrummler.android.colorpicker.ColorPickerView.onMeasure(int, int):void");
    }

    private int getPreferredWidth() {
        return DrawingUtils.dpToPx(getContext(), 200.0f) + this.huePanelWidthPx + this.panelSpacingPx;
    }

    private int getPreferredHeight() {
        int dpToPx = DrawingUtils.dpToPx(getContext(), 200.0f);
        return this.showAlphaPanel ? dpToPx + this.panelSpacingPx + this.alphaPanelHeightPx : dpToPx;
    }

    public int getPaddingTop() {
        return Math.max(super.getPaddingTop(), this.mRequiredPadding);
    }

    public int getPaddingBottom() {
        return Math.max(super.getPaddingBottom(), this.mRequiredPadding);
    }

    public int getPaddingLeft() {
        return Math.max(super.getPaddingLeft(), this.mRequiredPadding);
    }

    public int getPaddingRight() {
        return Math.max(super.getPaddingRight(), this.mRequiredPadding);
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        Rect rect = new Rect();
        this.drawingRect = rect;
        rect.left = getPaddingLeft();
        this.drawingRect.right = i - getPaddingRight();
        this.drawingRect.top = getPaddingTop();
        this.drawingRect.bottom = i2 - getPaddingBottom();
        this.valShader = null;
        this.satShader = null;
        this.alphaShader = null;
        this.satValBackgroundCache = null;
        this.hueBackgroundCache = null;
        setUpSatValRect();
        setUpHueRect();
        setUpAlphaRect();
    }

    private void setUpSatValRect() {
        Rect rect = this.drawingRect;
        int i = rect.left + 1;
        int i2 = rect.top + 1;
        int i3 = rect.bottom - 1;
        int i4 = this.panelSpacingPx;
        int i5 = ((rect.right - 1) - i4) - this.huePanelWidthPx;
        if (this.showAlphaPanel) {
            i3 -= this.alphaPanelHeightPx + i4;
        }
        this.satValRect = new Rect(i, i2, i5, i3);
    }

    private void setUpHueRect() {
        Rect rect = this.drawingRect;
        this.hueRect = new Rect((rect.right - this.huePanelWidthPx) + 1, rect.top + 1, rect.right - 1, (rect.bottom - 1) - (this.showAlphaPanel ? this.panelSpacingPx + this.alphaPanelHeightPx : 0));
    }

    private void setUpAlphaRect() {
        if (this.showAlphaPanel) {
            Rect rect = this.drawingRect;
            this.alphaRect = new Rect(rect.left + 1, (rect.bottom - this.alphaPanelHeightPx) + 1, rect.right - 1, rect.bottom - 1);
            AlphaPatternDrawable alphaPatternDrawable2 = new AlphaPatternDrawable(DrawingUtils.dpToPx(getContext(), 4.0f));
            this.alphaPatternDrawable = alphaPatternDrawable2;
            alphaPatternDrawable2.setBounds(Math.round((float) this.alphaRect.left), Math.round((float) this.alphaRect.top), Math.round((float) this.alphaRect.right), Math.round((float) this.alphaRect.bottom));
        }
    }

    public void setOnColorChangedListener(OnColorChangedListener onColorChangedListener2) {
        this.onColorChangedListener = onColorChangedListener2;
    }

    public int getColor() {
        return Color.HSVToColor(this.alpha, new float[]{this.hue, this.sat, this.val});
    }

    public void setColor(int i) {
        setColor(i, false);
    }

    public void setColor(int i, boolean z) {
        OnColorChangedListener onColorChangedListener2;
        int alpha2 = Color.alpha(i);
        float[] fArr = new float[3];
        Color.RGBToHSV(Color.red(i), Color.green(i), Color.blue(i), fArr);
        this.alpha = alpha2;
        float f = fArr[0];
        this.hue = f;
        float f2 = fArr[1];
        this.sat = f2;
        float f3 = fArr[2];
        this.val = f3;
        if (z && (onColorChangedListener2 = this.onColorChangedListener) != null) {
            onColorChangedListener2.onColorChanged(Color.HSVToColor(alpha2, new float[]{f, f2, f3}));
        }
        invalidate();
    }

    public void setAlphaSliderVisible(boolean z) {
        if (this.showAlphaPanel != z) {
            this.showAlphaPanel = z;
            this.valShader = null;
            this.satShader = null;
            this.alphaShader = null;
            this.hueBackgroundCache = null;
            this.satValBackgroundCache = null;
            requestLayout();
        }
    }

    public int getSliderTrackerColor() {
        return this.sliderTrackerColor;
    }

    public void setSliderTrackerColor(int i) {
        this.sliderTrackerColor = i;
        this.hueAlphaTrackerPaint.setColor(i);
        invalidate();
    }

    public int getBorderColor() {
        return this.borderColor;
    }

    public void setBorderColor(int i) {
        this.borderColor = i;
        invalidate();
    }

    public void setAlphaSliderText(int i) {
        setAlphaSliderText(getContext().getString(i));
    }

    public String getAlphaSliderText() {
        return this.alphaSliderText;
    }

    public void setAlphaSliderText(String str) {
        this.alphaSliderText = str;
        invalidate();
    }

    private class BitmapCache {
        public Bitmap bitmap;
        public Canvas canvas;
        public float value;

        private BitmapCache() {
        }
    }
}
