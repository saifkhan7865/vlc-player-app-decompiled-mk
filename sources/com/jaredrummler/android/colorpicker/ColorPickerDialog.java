package com.jaredrummler.android.colorpicker;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.core.graphics.ColorUtils;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import com.jaredrummler.android.colorpicker.ColorPaletteAdapter;
import com.jaredrummler.android.colorpicker.ColorPickerView;
import java.util.Arrays;
import java.util.Locale;

public class ColorPickerDialog extends DialogFragment implements ColorPickerView.OnColorChangedListener, TextWatcher {
    static final int ALPHA_THRESHOLD = 165;
    private static final String ARG_ALLOW_CUSTOM = "allowCustom";
    private static final String ARG_ALLOW_PRESETS = "allowPresets";
    private static final String ARG_ALPHA = "alpha";
    private static final String ARG_COLOR = "color";
    private static final String ARG_COLOR_SHAPE = "colorShape";
    private static final String ARG_CUSTOM_BUTTON_TEXT = "customButtonText";
    private static final String ARG_DIALOG_TITLE = "dialogTitle";
    private static final String ARG_ID = "id";
    private static final String ARG_PRESETS = "presets";
    private static final String ARG_PRESETS_BUTTON_TEXT = "presetsButtonText";
    private static final String ARG_SELECTED_BUTTON_TEXT = "selectedButtonText";
    private static final String ARG_SHOW_COLOR_SHADES = "showColorShades";
    private static final String ARG_TYPE = "dialogType";
    public static final int[] MATERIAL_COLORS = {-769226, -1499549, -54125, -6543440, -10011977, -12627531, -14575885, -16537100, -16728876, -16738680, -11751600, -7617718, -3285959, -5317, -16121, -26624, -8825528, -10453621, -6381922};
    private static final String TAG = "ColorPickerDialog";
    public static final int TYPE_CUSTOM = 0;
    public static final int TYPE_PRESETS = 1;
    ColorPaletteAdapter adapter;
    int color;
    ColorPickerView colorPicker;
    ColorPickerDialogListener colorPickerDialogListener;
    int colorShape;
    /* access modifiers changed from: private */
    public int customButtonStringRes;
    int dialogId;
    int dialogType;
    private boolean fromEditText;
    EditText hexEditText;
    ColorPanelView newColorPanel;
    private final View.OnTouchListener onPickerTouchListener = new View.OnTouchListener() {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (view == ColorPickerDialog.this.hexEditText || !ColorPickerDialog.this.hexEditText.hasFocus()) {
                return false;
            }
            ColorPickerDialog.this.hexEditText.clearFocus();
            ((InputMethodManager) ColorPickerDialog.this.getActivity().getSystemService("input_method")).hideSoftInputFromWindow(ColorPickerDialog.this.hexEditText.getWindowToken(), 0);
            ColorPickerDialog.this.hexEditText.clearFocus();
            return true;
        }
    };
    int[] presets;
    /* access modifiers changed from: private */
    public int presetsButtonStringRes;
    FrameLayout rootView;
    LinearLayout shadesLayout;
    boolean showAlphaSlider;
    boolean showColorShades;
    TextView transparencyPercText;
    SeekBar transparencySeekBar;

    public @interface DialogType {
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Dialog onCreateDialog(Bundle bundle) {
        int i;
        this.dialogId = getArguments().getInt(ARG_ID);
        this.showAlphaSlider = getArguments().getBoolean("alpha");
        this.showColorShades = getArguments().getBoolean(ARG_SHOW_COLOR_SHADES);
        this.colorShape = getArguments().getInt(ARG_COLOR_SHAPE);
        if (bundle == null) {
            this.color = getArguments().getInt("color");
            this.dialogType = getArguments().getInt(ARG_TYPE);
        } else {
            this.color = bundle.getInt("color");
            this.dialogType = bundle.getInt(ARG_TYPE);
        }
        FrameLayout frameLayout = new FrameLayout(requireActivity());
        this.rootView = frameLayout;
        int i2 = this.dialogType;
        if (i2 == 0) {
            frameLayout.addView(createPickerView());
        } else if (i2 == 1) {
            frameLayout.addView(createPresetsView());
        }
        int i3 = getArguments().getInt(ARG_SELECTED_BUTTON_TEXT);
        if (i3 == 0) {
            i3 = R.string.cpv_select;
        }
        AlertDialog.Builder positiveButton = new AlertDialog.Builder(requireActivity()).setView((View) this.rootView).setPositiveButton(i3, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                ColorPickerDialog colorPickerDialog = ColorPickerDialog.this;
                colorPickerDialog.onColorSelected(colorPickerDialog.color);
            }
        });
        int i4 = getArguments().getInt(ARG_DIALOG_TITLE);
        if (i4 != 0) {
            positiveButton.setTitle(i4);
        }
        this.presetsButtonStringRes = getArguments().getInt(ARG_PRESETS_BUTTON_TEXT);
        this.customButtonStringRes = getArguments().getInt(ARG_CUSTOM_BUTTON_TEXT);
        if (this.dialogType == 0 && getArguments().getBoolean(ARG_ALLOW_PRESETS)) {
            i = this.presetsButtonStringRes;
            if (i == 0) {
                i = R.string.cpv_presets;
            }
        } else if (this.dialogType != 1 || !getArguments().getBoolean(ARG_ALLOW_CUSTOM)) {
            i = 0;
        } else {
            i = this.customButtonStringRes;
            if (i == 0) {
                i = R.string.cpv_custom;
            }
        }
        if (i != 0) {
            positiveButton.setNeutralButton(i, (DialogInterface.OnClickListener) null);
        }
        return positiveButton.create();
    }

    public void onStart() {
        super.onStart();
        AlertDialog alertDialog = (AlertDialog) getDialog();
        alertDialog.getWindow().clearFlags(131080);
        alertDialog.getWindow().setSoftInputMode(4);
        Button button = alertDialog.getButton(-3);
        if (button != null) {
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ColorPickerDialog.this.rootView.removeAllViews();
                    int i = ColorPickerDialog.this.dialogType;
                    if (i == 0) {
                        ColorPickerDialog.this.dialogType = 1;
                        ((Button) view).setText(ColorPickerDialog.this.customButtonStringRes != 0 ? ColorPickerDialog.this.customButtonStringRes : R.string.cpv_custom);
                        ColorPickerDialog.this.rootView.addView(ColorPickerDialog.this.createPresetsView());
                    } else if (i == 1) {
                        ColorPickerDialog.this.dialogType = 0;
                        ((Button) view).setText(ColorPickerDialog.this.presetsButtonStringRes != 0 ? ColorPickerDialog.this.presetsButtonStringRes : R.string.cpv_presets);
                        ColorPickerDialog.this.rootView.addView(ColorPickerDialog.this.createPickerView());
                    }
                }
            });
        }
    }

    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        onDialogDismissed();
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putInt("color", this.color);
        bundle.putInt(ARG_TYPE, this.dialogType);
        super.onSaveInstanceState(bundle);
    }

    public void setColorPickerDialogListener(ColorPickerDialogListener colorPickerDialogListener2) {
        this.colorPickerDialogListener = colorPickerDialogListener2;
    }

    /* access modifiers changed from: package-private */
    public View createPickerView() {
        View inflate = View.inflate(getActivity(), R.layout.cpv_dialog_color_picker, (ViewGroup) null);
        this.colorPicker = (ColorPickerView) inflate.findViewById(R.id.cpv_color_picker_view);
        ColorPanelView colorPanelView = (ColorPanelView) inflate.findViewById(R.id.cpv_color_panel_old);
        this.newColorPanel = (ColorPanelView) inflate.findViewById(R.id.cpv_color_panel_new);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.cpv_arrow_right);
        this.hexEditText = (EditText) inflate.findViewById(R.id.cpv_hex);
        try {
            TypedArray obtainStyledAttributes = getActivity().obtainStyledAttributes(new TypedValue().data, new int[]{16842806});
            int color2 = obtainStyledAttributes.getColor(0, ViewCompat.MEASURED_STATE_MASK);
            obtainStyledAttributes.recycle();
            imageView.setColorFilter(color2);
        } catch (Exception unused) {
        }
        this.colorPicker.setAlphaSliderVisible(this.showAlphaSlider);
        colorPanelView.setColor(getArguments().getInt("color"));
        this.colorPicker.setColor(this.color, true);
        this.newColorPanel.setColor(this.color);
        setHex(this.color);
        if (!this.showAlphaSlider) {
            this.hexEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)});
        }
        this.newColorPanel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (ColorPickerDialog.this.newColorPanel.getColor() == ColorPickerDialog.this.color) {
                    ColorPickerDialog colorPickerDialog = ColorPickerDialog.this;
                    colorPickerDialog.onColorSelected(colorPickerDialog.color);
                    ColorPickerDialog.this.dismiss();
                }
            }
        });
        inflate.setOnTouchListener(this.onPickerTouchListener);
        this.colorPicker.setOnColorChangedListener(this);
        this.hexEditText.addTextChangedListener(this);
        this.hexEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    ((InputMethodManager) ColorPickerDialog.this.getActivity().getSystemService("input_method")).showSoftInput(ColorPickerDialog.this.hexEditText, 1);
                }
            }
        });
        return inflate;
    }

    public void onColorChanged(int i) {
        this.color = i;
        ColorPanelView colorPanelView = this.newColorPanel;
        if (colorPanelView != null) {
            colorPanelView.setColor(i);
        }
        if (!this.fromEditText && this.hexEditText != null) {
            setHex(i);
            if (this.hexEditText.hasFocus()) {
                ((InputMethodManager) getActivity().getSystemService("input_method")).hideSoftInputFromWindow(this.hexEditText.getWindowToken(), 0);
                this.hexEditText.clearFocus();
            }
        }
        this.fromEditText = false;
    }

    public void afterTextChanged(Editable editable) {
        int parseColorString;
        if (this.hexEditText.isFocused() && (parseColorString = parseColorString(editable.toString())) != this.colorPicker.getColor()) {
            this.fromEditText = true;
            this.colorPicker.setColor(parseColorString, true);
        }
    }

    private void setHex(int i) {
        if (this.showAlphaSlider) {
            this.hexEditText.setText(String.format("%08X", new Object[]{Integer.valueOf(i)}));
            return;
        }
        this.hexEditText.setText(String.format("%06X", new Object[]{Integer.valueOf(i & 16777215)}));
    }

    private int parseColorString(String str) throws NumberFormatException {
        int i;
        int i2;
        if (str.startsWith("#")) {
            str = str.substring(1);
        }
        int i3 = 255;
        int i4 = 0;
        if (str.length() == 0) {
            i = 0;
        } else if (str.length() <= 2) {
            i = Integer.parseInt(str, 16);
        } else {
            if (str.length() == 3) {
                i4 = Integer.parseInt(str.substring(0, 1), 16);
                i2 = Integer.parseInt(str.substring(1, 2), 16);
                i = Integer.parseInt(str.substring(2, 3), 16);
            } else if (str.length() == 4) {
                i2 = Integer.parseInt(str.substring(0, 2), 16);
                i = Integer.parseInt(str.substring(2, 4), 16);
            } else if (str.length() == 5) {
                i4 = Integer.parseInt(str.substring(0, 1), 16);
                i2 = Integer.parseInt(str.substring(1, 3), 16);
                i = Integer.parseInt(str.substring(3, 5), 16);
            } else if (str.length() == 6) {
                i4 = Integer.parseInt(str.substring(0, 2), 16);
                i2 = Integer.parseInt(str.substring(2, 4), 16);
                i = Integer.parseInt(str.substring(4, 6), 16);
            } else if (str.length() == 7) {
                int parseInt = Integer.parseInt(str.substring(0, 1), 16);
                i4 = Integer.parseInt(str.substring(1, 3), 16);
                int parseInt2 = Integer.parseInt(str.substring(3, 5), 16);
                i = Integer.parseInt(str.substring(5, 7), 16);
                i3 = parseInt;
                i2 = parseInt2;
            } else if (str.length() == 8) {
                i3 = Integer.parseInt(str.substring(0, 2), 16);
                i4 = Integer.parseInt(str.substring(2, 4), 16);
                i2 = Integer.parseInt(str.substring(4, 6), 16);
                i = Integer.parseInt(str.substring(6, 8), 16);
            } else {
                i3 = -1;
                i = -1;
                i2 = -1;
                i4 = -1;
            }
            return Color.argb(i3, i4, i2, i);
        }
        i2 = 0;
        return Color.argb(i3, i4, i2, i);
    }

    /* access modifiers changed from: package-private */
    public View createPresetsView() {
        View inflate = View.inflate(getActivity(), R.layout.cpv_dialog_presets, (ViewGroup) null);
        this.shadesLayout = (LinearLayout) inflate.findViewById(R.id.shades_layout);
        this.transparencySeekBar = (SeekBar) inflate.findViewById(R.id.transparency_seekbar);
        this.transparencyPercText = (TextView) inflate.findViewById(R.id.transparency_text);
        GridView gridView = (GridView) inflate.findViewById(R.id.gridView);
        loadPresets();
        if (this.showColorShades) {
            createColorShades(this.color);
        } else {
            this.shadesLayout.setVisibility(8);
            inflate.findViewById(R.id.shades_divider).setVisibility(8);
        }
        ColorPaletteAdapter colorPaletteAdapter = new ColorPaletteAdapter(new ColorPaletteAdapter.OnColorSelectedListener() {
            public void onColorSelected(int i) {
                if (ColorPickerDialog.this.color == i) {
                    ColorPickerDialog colorPickerDialog = ColorPickerDialog.this;
                    colorPickerDialog.onColorSelected(colorPickerDialog.color);
                    ColorPickerDialog.this.dismiss();
                    return;
                }
                ColorPickerDialog.this.color = i;
                if (ColorPickerDialog.this.showColorShades) {
                    ColorPickerDialog colorPickerDialog2 = ColorPickerDialog.this;
                    colorPickerDialog2.createColorShades(colorPickerDialog2.color);
                }
            }
        }, this.presets, getSelectedItemPosition(), this.colorShape);
        this.adapter = colorPaletteAdapter;
        gridView.setAdapter(colorPaletteAdapter);
        if (this.showAlphaSlider) {
            setupTransparency();
        } else {
            inflate.findViewById(R.id.transparency_layout).setVisibility(8);
            inflate.findViewById(R.id.transparency_title).setVisibility(8);
        }
        return inflate;
    }

    private void loadPresets() {
        int alpha = Color.alpha(this.color);
        int[] intArray = getArguments().getIntArray(ARG_PRESETS);
        this.presets = intArray;
        if (intArray == null) {
            this.presets = MATERIAL_COLORS;
        }
        int[] iArr = this.presets;
        boolean z = iArr == MATERIAL_COLORS;
        this.presets = Arrays.copyOf(iArr, iArr.length);
        if (alpha != 255) {
            int i = 0;
            while (true) {
                int[] iArr2 = this.presets;
                if (i >= iArr2.length) {
                    break;
                }
                int i2 = iArr2[i];
                this.presets[i] = Color.argb(alpha, Color.red(i2), Color.green(i2), Color.blue(i2));
                i++;
            }
        }
        this.presets = unshiftIfNotExists(this.presets, this.color);
        int i3 = getArguments().getInt("color");
        if (i3 != this.color) {
            this.presets = unshiftIfNotExists(this.presets, i3);
        }
        if (z) {
            int[] iArr3 = this.presets;
            if (iArr3.length == 19) {
                this.presets = pushIfNotExists(iArr3, Color.argb(alpha, 0, 0, 0));
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void createColorShades(int i) {
        int i2;
        int[] colorShades = getColorShades(i);
        if (this.shadesLayout.getChildCount() != 0) {
            for (int i3 = 0; i3 < this.shadesLayout.getChildCount(); i3++) {
                FrameLayout frameLayout = (FrameLayout) this.shadesLayout.getChildAt(i3);
                ColorPanelView colorPanelView = (ColorPanelView) frameLayout.findViewById(R.id.cpv_color_panel_view);
                colorPanelView.setColor(colorShades[i3]);
                colorPanelView.setTag(false);
                ((ImageView) frameLayout.findViewById(R.id.cpv_color_image_view)).setImageDrawable((Drawable) null);
            }
            return;
        }
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.cpv_item_horizontal_padding);
        for (final int i4 : colorShades) {
            if (this.colorShape == 0) {
                i2 = R.layout.cpv_color_item_square;
            } else {
                i2 = R.layout.cpv_color_item_circle;
            }
            View inflate = View.inflate(getActivity(), i2, (ViewGroup) null);
            final ColorPanelView colorPanelView2 = (ColorPanelView) inflate.findViewById(R.id.cpv_color_panel_view);
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) colorPanelView2.getLayoutParams();
            marginLayoutParams.rightMargin = dimensionPixelSize;
            marginLayoutParams.leftMargin = dimensionPixelSize;
            colorPanelView2.setLayoutParams(marginLayoutParams);
            colorPanelView2.setColor(i4);
            this.shadesLayout.addView(inflate);
            colorPanelView2.post(new Runnable() {
                public void run() {
                    colorPanelView2.setColor(i4);
                }
            });
            colorPanelView2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!(view.getTag() instanceof Boolean) || !((Boolean) view.getTag()).booleanValue()) {
                        ColorPickerDialog.this.color = colorPanelView2.getColor();
                        ColorPickerDialog.this.adapter.selectNone();
                        for (int i = 0; i < ColorPickerDialog.this.shadesLayout.getChildCount(); i++) {
                            FrameLayout frameLayout = (FrameLayout) ColorPickerDialog.this.shadesLayout.getChildAt(i);
                            ColorPanelView colorPanelView = (ColorPanelView) frameLayout.findViewById(R.id.cpv_color_panel_view);
                            ImageView imageView = (ImageView) frameLayout.findViewById(R.id.cpv_color_image_view);
                            imageView.setImageResource(colorPanelView == view ? R.drawable.cpv_preset_checked : 0);
                            if ((colorPanelView != view || ColorUtils.calculateLuminance(colorPanelView.getColor()) < 0.65d) && Color.alpha(colorPanelView.getColor()) > ColorPickerDialog.ALPHA_THRESHOLD) {
                                imageView.setColorFilter((ColorFilter) null);
                            } else {
                                imageView.setColorFilter(ViewCompat.MEASURED_STATE_MASK, PorterDuff.Mode.SRC_IN);
                            }
                            colorPanelView.setTag(Boolean.valueOf(colorPanelView == view));
                        }
                        return;
                    }
                    ColorPickerDialog colorPickerDialog = ColorPickerDialog.this;
                    colorPickerDialog.onColorSelected(colorPickerDialog.color);
                    ColorPickerDialog.this.dismiss();
                }
            });
            colorPanelView2.setOnLongClickListener(new View.OnLongClickListener() {
                public boolean onLongClick(View view) {
                    colorPanelView2.showHint();
                    return true;
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void onColorSelected(int i) {
        if (this.colorPickerDialogListener != null) {
            Log.w(TAG, "Using deprecated listener which may be remove in future releases");
            this.colorPickerDialogListener.onColorSelected(this.dialogId, i);
            return;
        }
        FragmentActivity activity = getActivity();
        if (activity instanceof ColorPickerDialogListener) {
            ((ColorPickerDialogListener) activity).onColorSelected(this.dialogId, i);
            return;
        }
        throw new IllegalStateException("The activity must implement ColorPickerDialogListener");
    }

    private void onDialogDismissed() {
        if (this.colorPickerDialogListener != null) {
            Log.w(TAG, "Using deprecated listener which may be remove in future releases");
            this.colorPickerDialogListener.onDialogDismissed(this.dialogId);
            return;
        }
        FragmentActivity activity = getActivity();
        if (activity instanceof ColorPickerDialogListener) {
            ((ColorPickerDialogListener) activity).onDialogDismissed(this.dialogId);
        }
    }

    private int shadeColor(int i, double d) {
        long parseLong = Long.parseLong(String.format("#%06X", new Object[]{Integer.valueOf(16777215 & i)}).substring(1), 16);
        double d2 = d < 0.0d ? 0.0d : 255.0d;
        if (d < 0.0d) {
            d *= -1.0d;
        }
        long j = parseLong >> 16;
        long j2 = (parseLong >> 8) & 255;
        long j3 = parseLong & 255;
        int alpha = Color.alpha(i);
        double d3 = (double) j;
        Double.isNaN(d3);
        int round = (int) (Math.round((d2 - d3) * d) + j);
        double d4 = (double) j2;
        Double.isNaN(d4);
        double d5 = (double) j3;
        Double.isNaN(d5);
        return Color.argb(alpha, round, (int) (Math.round((d2 - d4) * d) + j2), (int) (Math.round((d2 - d5) * d) + j3));
    }

    private int[] getColorShades(int i) {
        return new int[]{shadeColor(i, 0.9d), shadeColor(i, 0.7d), shadeColor(i, 0.5d), shadeColor(i, 0.333d), shadeColor(i, 0.166d), shadeColor(i, -0.125d), shadeColor(i, -0.25d), shadeColor(i, -0.375d), shadeColor(i, -0.5d), shadeColor(i, -0.675d), shadeColor(i, -0.7d), shadeColor(i, -0.775d)};
    }

    private void setupTransparency() {
        int alpha = 255 - Color.alpha(this.color);
        this.transparencySeekBar.setMax(255);
        this.transparencySeekBar.setProgress(alpha);
        double d = (double) alpha;
        Double.isNaN(d);
        this.transparencyPercText.setText(String.format(Locale.ENGLISH, "%d%%", new Object[]{Integer.valueOf((int) ((d * 100.0d) / 255.0d))}));
        this.transparencySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                double d = (double) i;
                Double.isNaN(d);
                int i2 = (int) ((d * 100.0d) / 255.0d);
                ColorPickerDialog.this.transparencyPercText.setText(String.format(Locale.ENGLISH, "%d%%", new Object[]{Integer.valueOf(i2)}));
                int i3 = 255 - i;
                for (int i4 = 0; i4 < ColorPickerDialog.this.adapter.colors.length; i4++) {
                    int i5 = ColorPickerDialog.this.adapter.colors[i4];
                    ColorPickerDialog.this.adapter.colors[i4] = Color.argb(i3, Color.red(i5), Color.green(i5), Color.blue(i5));
                }
                ColorPickerDialog.this.adapter.notifyDataSetChanged();
                for (int i6 = 0; i6 < ColorPickerDialog.this.shadesLayout.getChildCount(); i6++) {
                    FrameLayout frameLayout = (FrameLayout) ColorPickerDialog.this.shadesLayout.getChildAt(i6);
                    ColorPanelView colorPanelView = (ColorPanelView) frameLayout.findViewById(R.id.cpv_color_panel_view);
                    ImageView imageView = (ImageView) frameLayout.findViewById(R.id.cpv_color_image_view);
                    if (frameLayout.getTag() == null) {
                        frameLayout.setTag(Integer.valueOf(colorPanelView.getBorderColor()));
                    }
                    int color = colorPanelView.getColor();
                    int argb = Color.argb(i3, Color.red(color), Color.green(color), Color.blue(color));
                    if (i3 <= ColorPickerDialog.ALPHA_THRESHOLD) {
                        colorPanelView.setBorderColor(argb | ViewCompat.MEASURED_STATE_MASK);
                    } else {
                        colorPanelView.setBorderColor(((Integer) frameLayout.getTag()).intValue());
                    }
                    if (colorPanelView.getTag() != null && ((Boolean) colorPanelView.getTag()).booleanValue()) {
                        if (i3 <= ColorPickerDialog.ALPHA_THRESHOLD) {
                            imageView.setColorFilter(ViewCompat.MEASURED_STATE_MASK, PorterDuff.Mode.SRC_IN);
                        } else if (ColorUtils.calculateLuminance(argb) >= 0.65d) {
                            imageView.setColorFilter(ViewCompat.MEASURED_STATE_MASK, PorterDuff.Mode.SRC_IN);
                        } else {
                            imageView.setColorFilter(-1, PorterDuff.Mode.SRC_IN);
                        }
                    }
                    colorPanelView.setColor(argb);
                }
                int red = Color.red(ColorPickerDialog.this.color);
                int green = Color.green(ColorPickerDialog.this.color);
                int blue = Color.blue(ColorPickerDialog.this.color);
                ColorPickerDialog.this.color = Color.argb(i3, red, green, blue);
            }
        });
    }

    private int[] unshiftIfNotExists(int[] iArr, int i) {
        for (int i2 : iArr) {
            if (i2 == i) {
                return iArr;
            }
        }
        int length = iArr.length;
        int[] iArr2 = new int[(length + 1)];
        iArr2[0] = i;
        System.arraycopy(iArr, 0, iArr2, 1, length);
        return iArr2;
    }

    private int[] pushIfNotExists(int[] iArr, int i) {
        for (int i2 : iArr) {
            if (i2 == i) {
                return iArr;
            }
        }
        int length = iArr.length;
        int[] iArr2 = new int[(length + 1)];
        iArr2[length] = i;
        System.arraycopy(iArr, 0, iArr2, 0, length);
        return iArr2;
    }

    private int getSelectedItemPosition() {
        int i = 0;
        while (true) {
            int[] iArr = this.presets;
            if (i >= iArr.length) {
                return -1;
            }
            if (iArr[i] == this.color) {
                return i;
            }
            i++;
        }
    }

    public static final class Builder {
        boolean allowCustom = true;
        boolean allowPresets = true;
        int color = ViewCompat.MEASURED_STATE_MASK;
        ColorPickerDialogListener colorPickerDialogListener;
        int colorShape = 1;
        int customButtonText = R.string.cpv_custom;
        int dialogId = 0;
        int dialogTitle = R.string.cpv_default_title;
        int dialogType = 1;
        int[] presets = ColorPickerDialog.MATERIAL_COLORS;
        int presetsButtonText = R.string.cpv_presets;
        int selectedButtonText = R.string.cpv_select;
        boolean showAlphaSlider = false;
        boolean showColorShades = true;

        Builder() {
        }

        public Builder setDialogTitle(int i) {
            this.dialogTitle = i;
            return this;
        }

        public Builder setSelectedButtonText(int i) {
            this.selectedButtonText = i;
            return this;
        }

        public Builder setPresetsButtonText(int i) {
            this.presetsButtonText = i;
            return this;
        }

        public Builder setCustomButtonText(int i) {
            this.customButtonText = i;
            return this;
        }

        public Builder setDialogType(int i) {
            this.dialogType = i;
            return this;
        }

        public Builder setPresets(int[] iArr) {
            this.presets = iArr;
            return this;
        }

        public Builder setColor(int i) {
            this.color = i;
            return this;
        }

        public Builder setDialogId(int i) {
            this.dialogId = i;
            return this;
        }

        public Builder setShowAlphaSlider(boolean z) {
            this.showAlphaSlider = z;
            return this;
        }

        public Builder setAllowPresets(boolean z) {
            this.allowPresets = z;
            return this;
        }

        public Builder setAllowCustom(boolean z) {
            this.allowCustom = z;
            return this;
        }

        public Builder setShowColorShades(boolean z) {
            this.showColorShades = z;
            return this;
        }

        public Builder setColorShape(int i) {
            this.colorShape = i;
            return this;
        }

        public ColorPickerDialog create() {
            ColorPickerDialog colorPickerDialog = new ColorPickerDialog();
            Bundle bundle = new Bundle();
            bundle.putInt(ColorPickerDialog.ARG_ID, this.dialogId);
            bundle.putInt(ColorPickerDialog.ARG_TYPE, this.dialogType);
            bundle.putInt("color", this.color);
            bundle.putIntArray(ColorPickerDialog.ARG_PRESETS, this.presets);
            bundle.putBoolean("alpha", this.showAlphaSlider);
            bundle.putBoolean(ColorPickerDialog.ARG_ALLOW_CUSTOM, this.allowCustom);
            bundle.putBoolean(ColorPickerDialog.ARG_ALLOW_PRESETS, this.allowPresets);
            bundle.putInt(ColorPickerDialog.ARG_DIALOG_TITLE, this.dialogTitle);
            bundle.putBoolean(ColorPickerDialog.ARG_SHOW_COLOR_SHADES, this.showColorShades);
            bundle.putInt(ColorPickerDialog.ARG_COLOR_SHAPE, this.colorShape);
            bundle.putInt(ColorPickerDialog.ARG_PRESETS_BUTTON_TEXT, this.presetsButtonText);
            bundle.putInt(ColorPickerDialog.ARG_CUSTOM_BUTTON_TEXT, this.customButtonText);
            bundle.putInt(ColorPickerDialog.ARG_SELECTED_BUTTON_TEXT, this.selectedButtonText);
            colorPickerDialog.setArguments(bundle);
            return colorPickerDialog;
        }

        public void show(FragmentActivity fragmentActivity) {
            create().show(fragmentActivity.getSupportFragmentManager(), "color-picker-dialog");
        }
    }
}
