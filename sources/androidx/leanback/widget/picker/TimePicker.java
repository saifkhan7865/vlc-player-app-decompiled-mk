package androidx.leanback.widget.picker;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import androidx.leanback.R;
import androidx.leanback.widget.picker.PickerUtility;
import io.ktor.util.date.GMTDateParser;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class TimePicker extends Picker {
    private static final int AM_INDEX = 0;
    private static final int HOURS_IN_HALF_DAY = 12;
    private static final int PM_INDEX = 1;
    static final String TAG = "TimePicker";
    PickerColumn mAmPmColumn;
    int mColAmPmIndex;
    int mColHourIndex;
    int mColMinuteIndex;
    private final PickerUtility.TimeConstant mConstant;
    private int mCurrentAmPmIndex;
    private int mCurrentHour;
    private int mCurrentMinute;
    PickerColumn mHourColumn;
    private boolean mIs24hFormat;
    PickerColumn mMinuteColumn;
    private String mTimePickerFormat;

    public TimePicker(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TimePicker(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        PickerUtility.TimeConstant timeConstantInstance = PickerUtility.getTimeConstantInstance(Locale.getDefault(), context.getResources());
        this.mConstant = timeConstantInstance;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.lbTimePicker);
        this.mIs24hFormat = obtainStyledAttributes.getBoolean(R.styleable.lbTimePicker_is24HourFormat, DateFormat.is24HourFormat(context));
        boolean z = obtainStyledAttributes.getBoolean(R.styleable.lbTimePicker_useCurrentTime, true);
        updateColumns();
        updateColumnsRange();
        if (z) {
            Calendar calendarForLocale = PickerUtility.getCalendarForLocale((Calendar) null, timeConstantInstance.locale);
            setHour(calendarForLocale.get(11));
            setMinute(calendarForLocale.get(12));
            setAmPmValue();
        }
    }

    private static boolean updateMin(PickerColumn pickerColumn, int i) {
        if (i == pickerColumn.getMinValue()) {
            return false;
        }
        pickerColumn.setMinValue(i);
        return true;
    }

    private static boolean updateMax(PickerColumn pickerColumn, int i) {
        if (i == pickerColumn.getMaxValue()) {
            return false;
        }
        pickerColumn.setMaxValue(i);
        return true;
    }

    /* access modifiers changed from: package-private */
    public String getBestHourMinutePattern() {
        String str;
        if (PickerUtility.SUPPORTS_BEST_DATE_TIME_PATTERN) {
            str = DateFormat.getBestDateTimePattern(this.mConstant.locale, this.mIs24hFormat ? "Hma" : "hma");
        } else {
            java.text.DateFormat timeInstance = SimpleDateFormat.getTimeInstance(3, this.mConstant.locale);
            if (timeInstance instanceof SimpleDateFormat) {
                str = ((SimpleDateFormat) timeInstance).toPattern().replace("s", "");
                if (this.mIs24hFormat) {
                    str = str.replace(GMTDateParser.HOURS, 'H').replace("a", "");
                }
            } else if (this.mIs24hFormat) {
                str = "H:mma";
            } else {
                str = "h:mma";
            }
        }
        if (TextUtils.isEmpty(str)) {
            return "h:mma";
        }
        return str;
    }

    /* access modifiers changed from: package-private */
    public List<CharSequence> extractSeparators() {
        String bestHourMinutePattern = getBestHourMinutePattern();
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        char[] cArr = {'H', GMTDateParser.HOURS, 'K', 'k', GMTDateParser.MINUTES, GMTDateParser.MONTH, 'a'};
        boolean z = false;
        char c = 0;
        for (int i = 0; i < bestHourMinutePattern.length(); i++) {
            char charAt = bestHourMinutePattern.charAt(i);
            if (charAt != ' ') {
                if (charAt != '\'') {
                    if (z) {
                        sb.append(charAt);
                    } else if (!isAnyOf(charAt, cArr)) {
                        sb.append(charAt);
                    } else if (charAt != c) {
                        arrayList.add(sb.toString());
                        sb.setLength(0);
                    }
                    c = charAt;
                } else if (!z) {
                    sb.setLength(0);
                    z = true;
                } else {
                    z = false;
                }
            }
        }
        arrayList.add(sb.toString());
        return arrayList;
    }

    private static boolean isAnyOf(char c, char[] cArr) {
        for (char c2 : cArr) {
            if (c == c2) {
                return true;
            }
        }
        return false;
    }

    private String extractTimeFields() {
        String bestHourMinutePattern = getBestHourMinutePattern();
        boolean z = false;
        boolean z2 = TextUtils.getLayoutDirectionFromLocale(this.mConstant.locale) == 1;
        if (bestHourMinutePattern.indexOf(97) < 0 || bestHourMinutePattern.indexOf("a") > bestHourMinutePattern.indexOf("m")) {
            z = true;
        }
        String str = z2 ? "mh" : "hm";
        if (is24Hour()) {
            return str;
        }
        return z ? str.concat("a") : "a".concat(str);
    }

    private void updateColumns() {
        String bestHourMinutePattern = getBestHourMinutePattern();
        if (!TextUtils.equals(bestHourMinutePattern, this.mTimePickerFormat)) {
            this.mTimePickerFormat = bestHourMinutePattern;
            String extractTimeFields = extractTimeFields();
            List<CharSequence> extractSeparators = extractSeparators();
            if (extractSeparators.size() == extractTimeFields.length() + 1) {
                setSeparators(extractSeparators);
                String upperCase = extractTimeFields.toUpperCase();
                this.mAmPmColumn = null;
                this.mMinuteColumn = null;
                this.mHourColumn = null;
                this.mColAmPmIndex = -1;
                this.mColMinuteIndex = -1;
                this.mColHourIndex = -1;
                ArrayList arrayList = new ArrayList(3);
                for (int i = 0; i < upperCase.length(); i++) {
                    char charAt = upperCase.charAt(i);
                    if (charAt == 'A') {
                        PickerColumn pickerColumn = new PickerColumn();
                        this.mAmPmColumn = pickerColumn;
                        arrayList.add(pickerColumn);
                        this.mAmPmColumn.setStaticLabels(this.mConstant.ampm);
                        this.mColAmPmIndex = i;
                        updateMin(this.mAmPmColumn, 0);
                        updateMax(this.mAmPmColumn, 1);
                    } else if (charAt == 'H') {
                        PickerColumn pickerColumn2 = new PickerColumn();
                        this.mHourColumn = pickerColumn2;
                        arrayList.add(pickerColumn2);
                        this.mHourColumn.setStaticLabels(this.mConstant.hours24);
                        this.mColHourIndex = i;
                    } else if (charAt == 'M') {
                        PickerColumn pickerColumn3 = new PickerColumn();
                        this.mMinuteColumn = pickerColumn3;
                        arrayList.add(pickerColumn3);
                        this.mMinuteColumn.setStaticLabels(this.mConstant.minutes);
                        this.mColMinuteIndex = i;
                    } else {
                        throw new IllegalArgumentException("Invalid time picker format.");
                    }
                }
                setColumns(arrayList);
                return;
            }
            throw new IllegalStateException("Separators size: " + extractSeparators.size() + " must equal the size of timeFieldsPattern: " + extractTimeFields.length() + " + 1");
        }
    }

    private void updateColumnsRange() {
        updateMin(this.mHourColumn, this.mIs24hFormat ^ true ? 1 : 0);
        updateMax(this.mHourColumn, this.mIs24hFormat ? 23 : 12);
        updateMin(this.mMinuteColumn, 0);
        updateMax(this.mMinuteColumn, 59);
        PickerColumn pickerColumn = this.mAmPmColumn;
        if (pickerColumn != null) {
            updateMin(pickerColumn, 0);
            updateMax(this.mAmPmColumn, 1);
        }
    }

    private void setAmPmValue() {
        if (!is24Hour()) {
            setColumnValue(this.mColAmPmIndex, this.mCurrentAmPmIndex, false);
        }
    }

    public void setHour(int i) {
        if (i < 0 || i > 23) {
            throw new IllegalArgumentException("hour: " + i + " is not in [0-23] range in");
        }
        this.mCurrentHour = i;
        if (!is24Hour()) {
            int i2 = this.mCurrentHour;
            if (i2 >= 12) {
                this.mCurrentAmPmIndex = 1;
                if (i2 > 12) {
                    this.mCurrentHour = i2 - 12;
                }
            } else {
                this.mCurrentAmPmIndex = 0;
                if (i2 == 0) {
                    this.mCurrentHour = 12;
                }
            }
            setAmPmValue();
        }
        setColumnValue(this.mColHourIndex, this.mCurrentHour, false);
    }

    public int getHour() {
        if (this.mIs24hFormat) {
            return this.mCurrentHour;
        }
        if (this.mCurrentAmPmIndex == 0) {
            return this.mCurrentHour % 12;
        }
        return (this.mCurrentHour % 12) + 12;
    }

    public void setMinute(int i) {
        if (i < 0 || i > 59) {
            throw new IllegalArgumentException("minute: " + i + " is not in [0-59] range.");
        }
        this.mCurrentMinute = i;
        setColumnValue(this.mColMinuteIndex, i, false);
    }

    public int getMinute() {
        return this.mCurrentMinute;
    }

    public void setIs24Hour(boolean z) {
        if (this.mIs24hFormat != z) {
            int hour = getHour();
            int minute = getMinute();
            this.mIs24hFormat = z;
            updateColumns();
            updateColumnsRange();
            setHour(hour);
            setMinute(minute);
            setAmPmValue();
        }
    }

    public boolean is24Hour() {
        return this.mIs24hFormat;
    }

    public boolean isPm() {
        return this.mCurrentAmPmIndex == 1;
    }

    public void onColumnValueChanged(int i, int i2) {
        if (i == this.mColHourIndex) {
            this.mCurrentHour = i2;
        } else if (i == this.mColMinuteIndex) {
            this.mCurrentMinute = i2;
        } else if (i == this.mColAmPmIndex) {
            this.mCurrentAmPmIndex = i2;
        } else {
            throw new IllegalArgumentException("Invalid column index.");
        }
    }
}
