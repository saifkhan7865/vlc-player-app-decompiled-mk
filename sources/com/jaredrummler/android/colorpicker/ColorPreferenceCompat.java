package com.jaredrummler.android.colorpicker;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;

public class ColorPreferenceCompat extends Preference implements ColorPickerDialogListener {
    private static final int SIZE_LARGE = 1;
    private static final int SIZE_NORMAL = 0;
    private boolean allowCustom;
    private boolean allowPresets;
    private int color = ViewCompat.MEASURED_STATE_MASK;
    private int colorShape;
    private int dialogTitle;
    private int dialogType;
    private OnShowDialogListener onShowDialogListener;
    private int[] presets;
    private int previewSize;
    private boolean showAlphaSlider;
    private boolean showColorShades;
    private boolean showDialog;

    public interface OnShowDialogListener {
        void onShowColorPickerDialog(String str, int i);
    }

    public void onDialogDismissed(int i) {
    }

    public ColorPreferenceCompat(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet);
    }

    public ColorPreferenceCompat(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(attributeSet);
    }

    private void init(AttributeSet attributeSet) {
        setPersistent(true);
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.ColorPreference);
        this.showDialog = obtainStyledAttributes.getBoolean(R.styleable.ColorPreference_cpv_showDialog, true);
        this.dialogType = obtainStyledAttributes.getInt(R.styleable.ColorPreference_cpv_dialogType, 1);
        this.colorShape = obtainStyledAttributes.getInt(R.styleable.ColorPreference_cpv_colorShape, 1);
        this.allowPresets = obtainStyledAttributes.getBoolean(R.styleable.ColorPreference_cpv_allowPresets, true);
        this.allowCustom = obtainStyledAttributes.getBoolean(R.styleable.ColorPreference_cpv_allowCustom, true);
        this.showAlphaSlider = obtainStyledAttributes.getBoolean(R.styleable.ColorPreference_cpv_showAlphaSlider, false);
        this.showColorShades = obtainStyledAttributes.getBoolean(R.styleable.ColorPreference_cpv_showColorShades, true);
        this.previewSize = obtainStyledAttributes.getInt(R.styleable.ColorPreference_cpv_previewSize, 0);
        int resourceId = obtainStyledAttributes.getResourceId(R.styleable.ColorPreference_cpv_colorPresets, 0);
        this.dialogTitle = obtainStyledAttributes.getResourceId(R.styleable.ColorPreference_cpv_dialogTitle, R.string.cpv_default_title);
        if (resourceId != 0) {
            this.presets = getContext().getResources().getIntArray(resourceId);
        } else {
            this.presets = ColorPickerDialog.MATERIAL_COLORS;
        }
        if (this.colorShape == 1) {
            setWidgetLayoutResource(this.previewSize == 1 ? R.layout.cpv_preference_circle_large : R.layout.cpv_preference_circle);
        } else {
            setWidgetLayoutResource(this.previewSize == 1 ? R.layout.cpv_preference_square_large : R.layout.cpv_preference_square);
        }
        obtainStyledAttributes.recycle();
    }

    /* access modifiers changed from: protected */
    public void onClick() {
        super.onClick();
        OnShowDialogListener onShowDialogListener2 = this.onShowDialogListener;
        if (onShowDialogListener2 != null) {
            onShowDialogListener2.onShowColorPickerDialog((String) getTitle(), this.color);
        } else if (this.showDialog) {
            ColorPickerDialog create = ColorPickerDialog.newBuilder().setDialogType(this.dialogType).setDialogTitle(this.dialogTitle).setColorShape(this.colorShape).setPresets(this.presets).setAllowPresets(this.allowPresets).setAllowCustom(this.allowCustom).setShowAlphaSlider(this.showAlphaSlider).setShowColorShades(this.showColorShades).setColor(this.color).create();
            create.setColorPickerDialogListener(this);
            getActivity().getSupportFragmentManager().beginTransaction().add((Fragment) create, getFragmentTag()).commitAllowingStateLoss();
        }
    }

    public FragmentActivity getActivity() {
        Context context = getContext();
        if (context instanceof FragmentActivity) {
            return (FragmentActivity) context;
        }
        if (context instanceof ContextWrapper) {
            Context baseContext = ((ContextWrapper) context).getBaseContext();
            if (baseContext instanceof FragmentActivity) {
                return (FragmentActivity) baseContext;
            }
        }
        throw new IllegalStateException("Error getting activity from context");
    }

    public void onAttached() {
        ColorPickerDialog colorPickerDialog;
        super.onAttached();
        if (this.showDialog && (colorPickerDialog = (ColorPickerDialog) getActivity().getSupportFragmentManager().findFragmentByTag(getFragmentTag())) != null) {
            colorPickerDialog.setColorPickerDialogListener(this);
        }
    }

    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        ColorPanelView colorPanelView = (ColorPanelView) preferenceViewHolder.itemView.findViewById(R.id.cpv_preference_preview_color_panel);
        if (colorPanelView != null) {
            colorPanelView.setColor(this.color);
        }
    }

    /* access modifiers changed from: protected */
    public void onSetInitialValue(Object obj) {
        super.onSetInitialValue(obj);
        if (obj instanceof Integer) {
            int intValue = ((Integer) obj).intValue();
            this.color = intValue;
            persistInt(intValue);
            return;
        }
        this.color = getPersistedInt(ViewCompat.MEASURED_STATE_MASK);
    }

    /* access modifiers changed from: protected */
    public Object onGetDefaultValue(TypedArray typedArray, int i) {
        return Integer.valueOf(typedArray.getInteger(i, ViewCompat.MEASURED_STATE_MASK));
    }

    public void onColorSelected(int i, int i2) {
        saveValue(i2);
    }

    public void saveValue(int i) {
        this.color = i;
        persistInt(i);
        notifyChanged();
        callChangeListener(Integer.valueOf(i));
    }

    public int[] getPresets() {
        return this.presets;
    }

    public void setPresets(int[] iArr) {
        this.presets = iArr;
    }

    public void setOnShowDialogListener(OnShowDialogListener onShowDialogListener2) {
        this.onShowDialogListener = onShowDialogListener2;
    }

    public String getFragmentTag() {
        return "color_" + getKey();
    }
}
