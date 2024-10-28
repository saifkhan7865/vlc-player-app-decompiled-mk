package org.fusesource.jansi;

import io.ktor.util.date.GMTDateParser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

public class Ansi implements Appendable {
    public static final String DISABLE = (Ansi.class.getName() + ".disable");
    private static final char FIRST_ESC_CHAR = '\u001b';
    private static final char SECOND_ESC_CHAR = '[';
    private static Callable<Boolean> detector = new Callable<Boolean>() {
        public Boolean call() throws Exception {
            return Boolean.valueOf(!Boolean.getBoolean(Ansi.DISABLE));
        }
    };
    private static final InheritableThreadLocal<Boolean> holder = new InheritableThreadLocal<Boolean>() {
        /* access modifiers changed from: protected */
        public Boolean initialValue() {
            return Boolean.valueOf(Ansi.isDetected());
        }
    };
    private final ArrayList<Integer> attributeOptions;
    private final StringBuilder builder;

    public interface Consumer {
        void apply(Ansi ansi);
    }

    public enum Color {
        BLACK(0, "BLACK"),
        RED(1, "RED"),
        GREEN(2, "GREEN"),
        YELLOW(3, "YELLOW"),
        BLUE(4, "BLUE"),
        MAGENTA(5, "MAGENTA"),
        CYAN(6, "CYAN"),
        WHITE(7, "WHITE"),
        DEFAULT(9, "DEFAULT");
        
        private final String name;
        private final int value;

        private Color(int i, String str) {
            this.value = i;
            this.name = str;
        }

        public String toString() {
            return this.name;
        }

        public int value() {
            return this.value;
        }

        public int fg() {
            return this.value + 30;
        }

        public int bg() {
            return this.value + 40;
        }

        public int fgBright() {
            return this.value + 90;
        }

        public int bgBright() {
            return this.value + 100;
        }
    }

    public enum Attribute {
        RESET(0, "RESET"),
        INTENSITY_BOLD(1, "INTENSITY_BOLD"),
        INTENSITY_FAINT(2, "INTENSITY_FAINT"),
        ITALIC(3, "ITALIC_ON"),
        UNDERLINE(4, "UNDERLINE_ON"),
        BLINK_SLOW(5, "BLINK_SLOW"),
        BLINK_FAST(6, "BLINK_FAST"),
        NEGATIVE_ON(7, "NEGATIVE_ON"),
        CONCEAL_ON(8, "CONCEAL_ON"),
        STRIKETHROUGH_ON(9, "STRIKETHROUGH_ON"),
        UNDERLINE_DOUBLE(21, "UNDERLINE_DOUBLE"),
        INTENSITY_BOLD_OFF(22, "INTENSITY_BOLD_OFF"),
        ITALIC_OFF(23, "ITALIC_OFF"),
        UNDERLINE_OFF(24, "UNDERLINE_OFF"),
        BLINK_OFF(25, "BLINK_OFF"),
        NEGATIVE_OFF(27, "NEGATIVE_OFF"),
        CONCEAL_OFF(28, "CONCEAL_OFF"),
        STRIKETHROUGH_OFF(29, "STRIKETHROUGH_OFF");
        
        private final String name;
        private final int value;

        private Attribute(int i, String str) {
            this.value = i;
            this.name = str;
        }

        public String toString() {
            return this.name;
        }

        public int value() {
            return this.value;
        }
    }

    public enum Erase {
        FORWARD(0, "FORWARD"),
        BACKWARD(1, "BACKWARD"),
        ALL(2, "ALL");
        
        private final String name;
        private final int value;

        private Erase(int i, String str) {
            this.value = i;
            this.name = str;
        }

        public String toString() {
            return this.name;
        }

        public int value() {
            return this.value;
        }
    }

    public static void setDetector(Callable<Boolean> callable) {
        if (callable != null) {
            detector = callable;
            return;
        }
        throw new IllegalArgumentException();
    }

    public static boolean isDetected() {
        try {
            return detector.call().booleanValue();
        } catch (Exception unused) {
            return true;
        }
    }

    public static void setEnabled(boolean z) {
        holder.set(Boolean.valueOf(z));
    }

    public static boolean isEnabled() {
        return ((Boolean) holder.get()).booleanValue();
    }

    public static Ansi ansi() {
        if (isEnabled()) {
            return new Ansi();
        }
        return new NoAnsi();
    }

    public static Ansi ansi(StringBuilder sb) {
        if (isEnabled()) {
            return new Ansi(sb);
        }
        return new NoAnsi(sb);
    }

    public static Ansi ansi(int i) {
        if (isEnabled()) {
            return new Ansi(i);
        }
        return new NoAnsi(i);
    }

    private static class NoAnsi extends Ansi {
        public Ansi a(Attribute attribute) {
            return this;
        }

        public Ansi bg(int i) {
            return this;
        }

        public Ansi bg(Color color) {
            return this;
        }

        public Ansi bgBright(Color color) {
            return this;
        }

        public Ansi bgRgb(int i, int i2, int i3) {
            return this;
        }

        public Ansi cursor(int i, int i2) {
            return this;
        }

        public Ansi cursorDown(int i) {
            return this;
        }

        public Ansi cursorDownLine() {
            return this;
        }

        public Ansi cursorDownLine(int i) {
            return this;
        }

        public Ansi cursorLeft(int i) {
            return this;
        }

        public Ansi cursorRight(int i) {
            return this;
        }

        public Ansi cursorToColumn(int i) {
            return this;
        }

        public Ansi cursorUp(int i) {
            return this;
        }

        public Ansi cursorUpLine() {
            return this;
        }

        public Ansi cursorUpLine(int i) {
            return this;
        }

        public Ansi eraseLine() {
            return this;
        }

        public Ansi eraseLine(Erase erase) {
            return this;
        }

        public Ansi eraseScreen() {
            return this;
        }

        public Ansi eraseScreen(Erase erase) {
            return this;
        }

        public Ansi fg(int i) {
            return this;
        }

        public Ansi fg(Color color) {
            return this;
        }

        public Ansi fgBright(Color color) {
            return this;
        }

        public Ansi fgRgb(int i, int i2, int i3) {
            return this;
        }

        public Ansi reset() {
            return this;
        }

        @Deprecated
        public Ansi restorCursorPosition() {
            return this;
        }

        public Ansi restoreCursorPosition() {
            return this;
        }

        public Ansi saveCursorPosition() {
            return this;
        }

        public Ansi scrollDown(int i) {
            return this;
        }

        public Ansi scrollUp(int i) {
            return this;
        }

        public /* bridge */ /* synthetic */ Appendable append(char c) throws IOException {
            return Ansi.super.append(c);
        }

        public /* bridge */ /* synthetic */ Appendable append(CharSequence charSequence) throws IOException {
            return Ansi.super.append(charSequence);
        }

        public /* bridge */ /* synthetic */ Appendable append(CharSequence charSequence, int i, int i2) throws IOException {
            return Ansi.super.append(charSequence, i, i2);
        }

        public NoAnsi() {
        }

        public NoAnsi(int i) {
            super(i);
        }

        public NoAnsi(StringBuilder sb) {
            super(sb);
        }
    }

    public Ansi() {
        this(new StringBuilder(80));
    }

    public Ansi(Ansi ansi) {
        this(new StringBuilder(ansi.builder));
        this.attributeOptions.addAll(ansi.attributeOptions);
    }

    public Ansi(int i) {
        this(new StringBuilder(i));
    }

    public Ansi(StringBuilder sb) {
        this.attributeOptions = new ArrayList<>(5);
        this.builder = sb;
    }

    public Ansi fg(Color color) {
        this.attributeOptions.add(Integer.valueOf(color.fg()));
        return this;
    }

    public Ansi fg(int i) {
        this.attributeOptions.add(38);
        this.attributeOptions.add(5);
        this.attributeOptions.add(Integer.valueOf(i & 255));
        return this;
    }

    public Ansi fgRgb(int i) {
        return fgRgb(i >> 16, i >> 8, i);
    }

    public Ansi fgRgb(int i, int i2, int i3) {
        this.attributeOptions.add(38);
        this.attributeOptions.add(2);
        this.attributeOptions.add(Integer.valueOf(i & 255));
        this.attributeOptions.add(Integer.valueOf(i2 & 255));
        this.attributeOptions.add(Integer.valueOf(i3 & 255));
        return this;
    }

    public Ansi fgBlack() {
        return fg(Color.BLACK);
    }

    public Ansi fgBlue() {
        return fg(Color.BLUE);
    }

    public Ansi fgCyan() {
        return fg(Color.CYAN);
    }

    public Ansi fgDefault() {
        return fg(Color.DEFAULT);
    }

    public Ansi fgGreen() {
        return fg(Color.GREEN);
    }

    public Ansi fgMagenta() {
        return fg(Color.MAGENTA);
    }

    public Ansi fgRed() {
        return fg(Color.RED);
    }

    public Ansi fgYellow() {
        return fg(Color.YELLOW);
    }

    public Ansi bg(Color color) {
        this.attributeOptions.add(Integer.valueOf(color.bg()));
        return this;
    }

    public Ansi bg(int i) {
        this.attributeOptions.add(48);
        this.attributeOptions.add(5);
        this.attributeOptions.add(Integer.valueOf(i & 255));
        return this;
    }

    public Ansi bgRgb(int i) {
        return bgRgb(i >> 16, i >> 8, i);
    }

    public Ansi bgRgb(int i, int i2, int i3) {
        this.attributeOptions.add(48);
        this.attributeOptions.add(2);
        this.attributeOptions.add(Integer.valueOf(i & 255));
        this.attributeOptions.add(Integer.valueOf(i2 & 255));
        this.attributeOptions.add(Integer.valueOf(i3 & 255));
        return this;
    }

    public Ansi bgCyan() {
        return bg(Color.CYAN);
    }

    public Ansi bgDefault() {
        return bg(Color.DEFAULT);
    }

    public Ansi bgGreen() {
        return bg(Color.GREEN);
    }

    public Ansi bgMagenta() {
        return bg(Color.MAGENTA);
    }

    public Ansi bgRed() {
        return bg(Color.RED);
    }

    public Ansi bgYellow() {
        return bg(Color.YELLOW);
    }

    public Ansi fgBright(Color color) {
        this.attributeOptions.add(Integer.valueOf(color.fgBright()));
        return this;
    }

    public Ansi fgBrightBlack() {
        return fgBright(Color.BLACK);
    }

    public Ansi fgBrightBlue() {
        return fgBright(Color.BLUE);
    }

    public Ansi fgBrightCyan() {
        return fgBright(Color.CYAN);
    }

    public Ansi fgBrightDefault() {
        return fgBright(Color.DEFAULT);
    }

    public Ansi fgBrightGreen() {
        return fgBright(Color.GREEN);
    }

    public Ansi fgBrightMagenta() {
        return fgBright(Color.MAGENTA);
    }

    public Ansi fgBrightRed() {
        return fgBright(Color.RED);
    }

    public Ansi fgBrightYellow() {
        return fgBright(Color.YELLOW);
    }

    public Ansi bgBright(Color color) {
        this.attributeOptions.add(Integer.valueOf(color.bgBright()));
        return this;
    }

    public Ansi bgBrightCyan() {
        return bgBright(Color.CYAN);
    }

    public Ansi bgBrightDefault() {
        return bgBright(Color.DEFAULT);
    }

    public Ansi bgBrightGreen() {
        return bgBright(Color.GREEN);
    }

    public Ansi bgBrightMagenta() {
        return bgBright(Color.MAGENTA);
    }

    public Ansi bgBrightRed() {
        return bgBright(Color.RED);
    }

    public Ansi bgBrightYellow() {
        return bgBright(Color.YELLOW);
    }

    public Ansi a(Attribute attribute) {
        this.attributeOptions.add(Integer.valueOf(attribute.value()));
        return this;
    }

    public Ansi cursor(int i, int i2) {
        return appendEscapeSequence('H', Integer.valueOf(Math.max(1, i)), Integer.valueOf(Math.max(1, i2)));
    }

    public Ansi cursorToColumn(int i) {
        return appendEscapeSequence('G', Math.max(1, i));
    }

    public Ansi cursorUp(int i) {
        if (i > 0) {
            return appendEscapeSequence('A', i);
        }
        return i < 0 ? cursorDown(-i) : this;
    }

    public Ansi cursorDown(int i) {
        if (i > 0) {
            return appendEscapeSequence('B', i);
        }
        return i < 0 ? cursorUp(-i) : this;
    }

    public Ansi cursorRight(int i) {
        if (i > 0) {
            return appendEscapeSequence('C', i);
        }
        return i < 0 ? cursorLeft(-i) : this;
    }

    public Ansi cursorLeft(int i) {
        if (i > 0) {
            return appendEscapeSequence('D', i);
        }
        return i < 0 ? cursorRight(-i) : this;
    }

    public Ansi cursorMove(int i, int i2) {
        return cursorRight(i).cursorDown(i2);
    }

    public Ansi cursorDownLine() {
        return appendEscapeSequence('E');
    }

    public Ansi cursorDownLine(int i) {
        return i < 0 ? cursorUpLine(-i) : appendEscapeSequence('E', i);
    }

    public Ansi cursorUpLine() {
        return appendEscapeSequence('F');
    }

    public Ansi cursorUpLine(int i) {
        return i < 0 ? cursorDownLine(-i) : appendEscapeSequence('F', i);
    }

    public Ansi eraseScreen() {
        return appendEscapeSequence('J', Erase.ALL.value());
    }

    public Ansi eraseScreen(Erase erase) {
        return appendEscapeSequence('J', erase.value());
    }

    public Ansi eraseLine() {
        return appendEscapeSequence('K');
    }

    public Ansi eraseLine(Erase erase) {
        return appendEscapeSequence('K', erase.value());
    }

    public Ansi scrollUp(int i) {
        if (i > 0) {
            return appendEscapeSequence('S', i);
        }
        return i < 0 ? scrollDown(-i) : this;
    }

    public Ansi scrollDown(int i) {
        if (i > 0) {
            return appendEscapeSequence('T', i);
        }
        return i < 0 ? scrollUp(-i) : this;
    }

    public Ansi saveCursorPosition() {
        return appendEscapeSequence(GMTDateParser.SECONDS);
    }

    @Deprecated
    public Ansi restorCursorPosition() {
        return appendEscapeSequence(AbstractJsonLexerKt.UNICODE_ESC);
    }

    public Ansi restoreCursorPosition() {
        return appendEscapeSequence(AbstractJsonLexerKt.UNICODE_ESC);
    }

    public Ansi reset() {
        return a(Attribute.RESET);
    }

    public Ansi bold() {
        return a(Attribute.INTENSITY_BOLD);
    }

    public Ansi boldOff() {
        return a(Attribute.INTENSITY_BOLD_OFF);
    }

    public Ansi a(String str) {
        flushAttributes();
        this.builder.append(str);
        return this;
    }

    public Ansi a(boolean z) {
        flushAttributes();
        this.builder.append(z);
        return this;
    }

    public Ansi a(char c) {
        flushAttributes();
        this.builder.append(c);
        return this;
    }

    public Ansi a(char[] cArr, int i, int i2) {
        flushAttributes();
        this.builder.append(cArr, i, i2);
        return this;
    }

    public Ansi a(char[] cArr) {
        flushAttributes();
        this.builder.append(cArr);
        return this;
    }

    public Ansi a(CharSequence charSequence, int i, int i2) {
        flushAttributes();
        this.builder.append(charSequence, i, i2);
        return this;
    }

    public Ansi a(CharSequence charSequence) {
        flushAttributes();
        this.builder.append(charSequence);
        return this;
    }

    public Ansi a(double d) {
        flushAttributes();
        this.builder.append(d);
        return this;
    }

    public Ansi a(float f) {
        flushAttributes();
        this.builder.append(f);
        return this;
    }

    public Ansi a(int i) {
        flushAttributes();
        this.builder.append(i);
        return this;
    }

    public Ansi a(long j) {
        flushAttributes();
        this.builder.append(j);
        return this;
    }

    public Ansi a(Object obj) {
        flushAttributes();
        this.builder.append(obj);
        return this;
    }

    public Ansi a(StringBuffer stringBuffer) {
        flushAttributes();
        this.builder.append(stringBuffer);
        return this;
    }

    public Ansi newline() {
        flushAttributes();
        this.builder.append(System.getProperty("line.separator"));
        return this;
    }

    public Ansi format(String str, Object... objArr) {
        flushAttributes();
        this.builder.append(String.format(str, objArr));
        return this;
    }

    public Ansi apply(Consumer consumer) {
        consumer.apply(this);
        return this;
    }

    public Ansi render(String str) {
        a(AnsiRenderer.render(str));
        return this;
    }

    public Ansi render(String str, Object... objArr) {
        a(String.format(AnsiRenderer.render(str), objArr));
        return this;
    }

    public String toString() {
        flushAttributes();
        return this.builder.toString();
    }

    private Ansi appendEscapeSequence(char c) {
        flushAttributes();
        this.builder.append(FIRST_ESC_CHAR);
        this.builder.append('[');
        this.builder.append(c);
        return this;
    }

    private Ansi appendEscapeSequence(char c, int i) {
        flushAttributes();
        this.builder.append(FIRST_ESC_CHAR);
        this.builder.append('[');
        this.builder.append(i);
        this.builder.append(c);
        return this;
    }

    private Ansi appendEscapeSequence(char c, Object... objArr) {
        flushAttributes();
        return _appendEscapeSequence(c, objArr);
    }

    private void flushAttributes() {
        if (!this.attributeOptions.isEmpty()) {
            if (this.attributeOptions.size() == 1 && this.attributeOptions.get(0).intValue() == 0) {
                this.builder.append(FIRST_ESC_CHAR);
                this.builder.append('[');
                this.builder.append(GMTDateParser.MINUTES);
            } else {
                _appendEscapeSequence(GMTDateParser.MINUTES, this.attributeOptions.toArray());
            }
            this.attributeOptions.clear();
        }
    }

    private Ansi _appendEscapeSequence(char c, Object... objArr) {
        this.builder.append(FIRST_ESC_CHAR);
        this.builder.append('[');
        int length = objArr.length;
        for (int i = 0; i < length; i++) {
            if (i != 0) {
                this.builder.append(';');
            }
            Object obj = objArr[i];
            if (obj != null) {
                this.builder.append(obj);
            }
        }
        this.builder.append(c);
        return this;
    }

    public Ansi append(CharSequence charSequence) {
        this.builder.append(charSequence);
        return this;
    }

    public Ansi append(CharSequence charSequence, int i, int i2) {
        this.builder.append(charSequence, i, i2);
        return this;
    }

    public Ansi append(char c) {
        this.builder.append(c);
        return this;
    }
}
