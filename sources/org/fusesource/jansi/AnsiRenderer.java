package org.fusesource.jansi;

import java.io.IOException;
import java.util.Locale;
import org.fusesource.jansi.Ansi;

public class AnsiRenderer {
    public static final String BEGIN_TOKEN = "@|";
    private static final int BEGIN_TOKEN_LEN = 2;
    public static final String CODE_LIST_SEPARATOR = ",";
    public static final String CODE_TEXT_SEPARATOR = " ";
    public static final String END_TOKEN = "|@";
    private static final int END_TOKEN_LEN = 2;

    public static String render(String str) throws IllegalArgumentException {
        try {
            return render(str, (Appendable) new StringBuilder()).toString();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static Appendable render(String str, Appendable appendable) throws IOException {
        int i = 0;
        while (true) {
            int indexOf = str.indexOf(BEGIN_TOKEN, i);
            if (indexOf != -1) {
                appendable.append(str.substring(i, indexOf));
                int indexOf2 = str.indexOf(END_TOKEN, indexOf);
                if (indexOf2 == -1) {
                    appendable.append(str);
                    return appendable;
                }
                String[] split = str.substring(indexOf + 2, indexOf2).split(CODE_TEXT_SEPARATOR, 2);
                if (split.length == 1) {
                    appendable.append(str);
                    return appendable;
                }
                appendable.append(render(split[1], split[0].split(CODE_LIST_SEPARATOR)));
                i = indexOf2 + 2;
            } else if (i == 0) {
                appendable.append(str);
                return appendable;
            } else {
                appendable.append(str.substring(i));
                return appendable;
            }
        }
    }

    public static String render(String str, String... strArr) {
        return render(Ansi.ansi(), strArr).a(str).reset().toString();
    }

    public static String renderCodes(String... strArr) {
        return render(Ansi.ansi(), strArr).toString();
    }

    public static String renderCodes(String str) {
        return renderCodes(str.split("\\s"));
    }

    private static Ansi render(Ansi ansi, String... strArr) {
        for (String upperCase : strArr) {
            Code valueOf = Code.valueOf(upperCase.toUpperCase(Locale.ENGLISH));
            if (valueOf.isColor()) {
                if (valueOf.isBackground()) {
                    ansi.bg(valueOf.getColor());
                } else {
                    ansi.fg(valueOf.getColor());
                }
            } else if (valueOf.isAttribute()) {
                ansi.a(valueOf.getAttribute());
            }
        }
        return ansi;
    }

    public static boolean test(String str) {
        return str != null && str.contains(BEGIN_TOKEN);
    }

    public enum Code {
        BLACK(Ansi.Color.BLACK),
        RED(Ansi.Color.RED),
        GREEN(Ansi.Color.GREEN),
        YELLOW(Ansi.Color.YELLOW),
        BLUE(Ansi.Color.BLUE),
        MAGENTA(Ansi.Color.MAGENTA),
        CYAN(Ansi.Color.CYAN),
        WHITE(Ansi.Color.WHITE),
        DEFAULT(Ansi.Color.DEFAULT),
        FG_BLACK(Ansi.Color.BLACK, false),
        FG_RED(Ansi.Color.RED, false),
        FG_GREEN(Ansi.Color.GREEN, false),
        FG_YELLOW(Ansi.Color.YELLOW, false),
        FG_BLUE(Ansi.Color.BLUE, false),
        FG_MAGENTA(Ansi.Color.MAGENTA, false),
        FG_CYAN(Ansi.Color.CYAN, false),
        FG_WHITE(Ansi.Color.WHITE, false),
        FG_DEFAULT(Ansi.Color.DEFAULT, false),
        BG_BLACK(Ansi.Color.BLACK, true),
        BG_RED(Ansi.Color.RED, true),
        BG_GREEN(Ansi.Color.GREEN, true),
        BG_YELLOW(Ansi.Color.YELLOW, true),
        BG_BLUE(Ansi.Color.BLUE, true),
        BG_MAGENTA(Ansi.Color.MAGENTA, true),
        BG_CYAN(Ansi.Color.CYAN, true),
        BG_WHITE(Ansi.Color.WHITE, true),
        BG_DEFAULT(Ansi.Color.DEFAULT, true),
        RESET(Ansi.Attribute.RESET),
        INTENSITY_BOLD(Ansi.Attribute.INTENSITY_BOLD),
        INTENSITY_FAINT(Ansi.Attribute.INTENSITY_FAINT),
        ITALIC(Ansi.Attribute.ITALIC),
        UNDERLINE(Ansi.Attribute.UNDERLINE),
        BLINK_SLOW(Ansi.Attribute.BLINK_SLOW),
        BLINK_FAST(Ansi.Attribute.BLINK_FAST),
        BLINK_OFF(Ansi.Attribute.BLINK_OFF),
        NEGATIVE_ON(Ansi.Attribute.NEGATIVE_ON),
        NEGATIVE_OFF(Ansi.Attribute.NEGATIVE_OFF),
        CONCEAL_ON(Ansi.Attribute.CONCEAL_ON),
        CONCEAL_OFF(Ansi.Attribute.CONCEAL_OFF),
        UNDERLINE_DOUBLE(Ansi.Attribute.UNDERLINE_DOUBLE),
        UNDERLINE_OFF(Ansi.Attribute.UNDERLINE_OFF),
        BOLD(Ansi.Attribute.INTENSITY_BOLD),
        FAINT(Ansi.Attribute.INTENSITY_FAINT);
        
        private final boolean background;
        private final Enum<?> n;

        private Code(Enum<?> enumR, boolean z) {
            this.n = enumR;
            this.background = z;
        }

        private Code(Enum<?> enumR) {
            this(r2, r3, enumR, false);
        }

        public boolean isColor() {
            return this.n instanceof Ansi.Color;
        }

        public Ansi.Color getColor() {
            return (Ansi.Color) this.n;
        }

        public boolean isAttribute() {
            return this.n instanceof Ansi.Attribute;
        }

        public Ansi.Attribute getAttribute() {
            return (Ansi.Attribute) this.n;
        }

        public boolean isBackground() {
            return this.background;
        }
    }

    private AnsiRenderer() {
    }
}
