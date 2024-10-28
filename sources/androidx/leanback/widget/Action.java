package androidx.leanback.widget;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import java.util.ArrayList;
import org.fusesource.jansi.AnsiRenderer;

public class Action {
    public static final long NO_ID = -1;
    private Drawable mIcon;
    private long mId;
    private ArrayList<Integer> mKeyCodes;
    private CharSequence mLabel1;
    private CharSequence mLabel2;

    public Action(long j) {
        this(j, "");
    }

    public Action(long j, CharSequence charSequence) {
        this(j, charSequence, (CharSequence) null);
    }

    public Action(long j, CharSequence charSequence, CharSequence charSequence2) {
        this(j, charSequence, charSequence2, (Drawable) null);
    }

    public Action(long j, CharSequence charSequence, CharSequence charSequence2, Drawable drawable) {
        this.mId = -1;
        this.mKeyCodes = new ArrayList<>();
        setId(j);
        setLabel1(charSequence);
        setLabel2(charSequence2);
        setIcon(drawable);
    }

    public final void setId(long j) {
        this.mId = j;
    }

    public final long getId() {
        return this.mId;
    }

    public final void setLabel1(CharSequence charSequence) {
        this.mLabel1 = charSequence;
    }

    public final CharSequence getLabel1() {
        return this.mLabel1;
    }

    public final void setLabel2(CharSequence charSequence) {
        this.mLabel2 = charSequence;
    }

    public final CharSequence getLabel2() {
        return this.mLabel2;
    }

    public final void setIcon(Drawable drawable) {
        this.mIcon = drawable;
    }

    public final Drawable getIcon() {
        return this.mIcon;
    }

    public final void addKeyCode(int i) {
        this.mKeyCodes.add(Integer.valueOf(i));
    }

    public final void removeKeyCode(int i) {
        this.mKeyCodes.remove(i);
    }

    public final boolean respondsToKeyCode(int i) {
        return this.mKeyCodes.contains(Integer.valueOf(i));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(this.mLabel1)) {
            sb.append(this.mLabel1);
        }
        if (!TextUtils.isEmpty(this.mLabel2)) {
            if (!TextUtils.isEmpty(this.mLabel1)) {
                sb.append(AnsiRenderer.CODE_TEXT_SEPARATOR);
            }
            sb.append(this.mLabel2);
        }
        if (this.mIcon != null && sb.length() == 0) {
            sb.append("(action icon)");
        }
        return sb.toString();
    }
}
