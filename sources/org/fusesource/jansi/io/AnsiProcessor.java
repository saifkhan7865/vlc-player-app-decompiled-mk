package org.fusesource.jansi.io;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class AnsiProcessor {
    protected static final int ATTRIBUTE_BLINK_FAST = 6;
    protected static final int ATTRIBUTE_BLINK_OFF = 25;
    protected static final int ATTRIBUTE_BLINK_SLOW = 5;
    protected static final int ATTRIBUTE_CONCEAL_OFF = 28;
    protected static final int ATTRIBUTE_CONCEAL_ON = 8;
    protected static final int ATTRIBUTE_INTENSITY_BOLD = 1;
    protected static final int ATTRIBUTE_INTENSITY_FAINT = 2;
    protected static final int ATTRIBUTE_INTENSITY_NORMAL = 22;
    protected static final int ATTRIBUTE_ITALIC = 3;
    protected static final int ATTRIBUTE_NEGATIVE_OFF = 27;
    protected static final int ATTRIBUTE_NEGATIVE_ON = 7;
    protected static final int ATTRIBUTE_UNDERLINE = 4;
    protected static final int ATTRIBUTE_UNDERLINE_DOUBLE = 21;
    protected static final int ATTRIBUTE_UNDERLINE_OFF = 24;
    protected static final int BLACK = 0;
    protected static final int BLUE = 4;
    protected static final int CYAN = 6;
    protected static final int ERASE_LINE = 2;
    protected static final int ERASE_LINE_TO_BEGINING = 1;
    protected static final int ERASE_LINE_TO_END = 0;
    protected static final int ERASE_SCREEN = 2;
    protected static final int ERASE_SCREEN_TO_BEGINING = 1;
    protected static final int ERASE_SCREEN_TO_END = 0;
    protected static final int GREEN = 2;
    protected static final int MAGENTA = 5;
    protected static final int RED = 1;
    protected static final int WHITE = 7;
    protected static final int YELLOW = 3;
    protected final OutputStream os;

    /* access modifiers changed from: protected */
    public void processAttributeReset() throws IOException {
    }

    /* access modifiers changed from: protected */
    public void processChangeIconName(String str) {
    }

    /* access modifiers changed from: protected */
    public void processChangeWindowTitle(String str) {
    }

    /* access modifiers changed from: protected */
    public void processCharsetSelect(int i, char c) {
    }

    /* access modifiers changed from: protected */
    public void processCursorDown(int i) throws IOException {
    }

    /* access modifiers changed from: protected */
    public void processCursorLeft(int i) throws IOException {
    }

    /* access modifiers changed from: protected */
    public void processCursorTo(int i, int i2) throws IOException {
    }

    /* access modifiers changed from: protected */
    public void processCursorToColumn(int i) throws IOException {
    }

    /* access modifiers changed from: protected */
    public void processCursorUp(int i) throws IOException {
    }

    /* access modifiers changed from: protected */
    public void processCursorUpLine(int i) throws IOException {
    }

    /* access modifiers changed from: protected */
    public void processDefaultBackgroundColor() throws IOException {
    }

    /* access modifiers changed from: protected */
    public void processDefaultTextColor() throws IOException {
    }

    /* access modifiers changed from: protected */
    public void processDeleteLine(int i) throws IOException {
    }

    /* access modifiers changed from: protected */
    public void processEraseLine(int i) throws IOException {
    }

    /* access modifiers changed from: protected */
    public void processEraseScreen(int i) throws IOException {
    }

    /* access modifiers changed from: protected */
    public void processInsertLine(int i) throws IOException {
    }

    /* access modifiers changed from: protected */
    public void processRestoreCursorPosition() throws IOException {
    }

    /* access modifiers changed from: protected */
    public void processSaveCursorPosition() throws IOException {
    }

    /* access modifiers changed from: protected */
    public void processScrollDown(int i) throws IOException {
    }

    /* access modifiers changed from: protected */
    public void processScrollUp(int i) throws IOException {
    }

    /* access modifiers changed from: protected */
    public void processSetAttribute(int i) throws IOException {
    }

    /* access modifiers changed from: protected */
    public void processSetBackgroundColor(int i, boolean z) throws IOException {
    }

    /* access modifiers changed from: protected */
    public void processSetBackgroundColorExt(int i) throws IOException {
    }

    /* access modifiers changed from: protected */
    public void processSetBackgroundColorExt(int i, int i2, int i3) throws IOException {
    }

    /* access modifiers changed from: protected */
    public void processSetForegroundColor(int i, boolean z) throws IOException {
    }

    /* access modifiers changed from: protected */
    public void processSetForegroundColorExt(int i) throws IOException {
    }

    /* access modifiers changed from: protected */
    public void processSetForegroundColorExt(int i, int i2, int i3) throws IOException {
    }

    /* access modifiers changed from: protected */
    public void processUnknownExtension(ArrayList<Object> arrayList, int i) {
    }

    /* access modifiers changed from: protected */
    public void processUnknownOperatingSystemCommand(int i, String str) {
    }

    public AnsiProcessor(OutputStream outputStream) {
        this.os = outputStream;
    }

    /* access modifiers changed from: protected */
    public int getNextOptionInt(Iterator<Object> it) throws IOException {
        while (it.hasNext()) {
            Object next = it.next();
            if (next != null) {
                return ((Integer) next).intValue();
            }
        }
        throw new IllegalArgumentException();
    }

    /* access modifiers changed from: protected */
    public boolean processEscapeCommand(ArrayList<Object> arrayList, int i) throws IOException {
        if (i == 83) {
            processScrollUp(optionInt(arrayList, 0, 1));
            return true;
        } else if (i != 84) {
            if (i != 102) {
                if (i == 109) {
                    Iterator<Object> it = arrayList.iterator();
                    while (it.hasNext()) {
                        Object next = it.next();
                        if (next != null) {
                            if (next.getClass() != Integer.class) {
                                throw new IllegalArgumentException();
                            }
                        }
                    }
                    Iterator<Object> it2 = arrayList.iterator();
                    int i2 = 0;
                    while (it2.hasNext()) {
                        Object next2 = it2.next();
                        if (next2 != null) {
                            i2++;
                            int intValue = ((Integer) next2).intValue();
                            if (30 <= intValue && intValue <= 37) {
                                processSetForegroundColor(intValue - 30);
                            } else if (40 <= intValue && intValue <= 47) {
                                processSetBackgroundColor(intValue - 40);
                            } else if (90 <= intValue && intValue <= 97) {
                                processSetForegroundColor(intValue - 90, true);
                            } else if (100 > intValue || intValue > 107) {
                                if (intValue != 38) {
                                    if (intValue != 48) {
                                        if (intValue == 0) {
                                            processAttributeReset();
                                        } else if (intValue == 39) {
                                            processDefaultTextColor();
                                        } else if (intValue != 49) {
                                            processSetAttribute(intValue);
                                        } else {
                                            processDefaultBackgroundColor();
                                        }
                                    }
                                }
                                if (it2.hasNext()) {
                                    int nextOptionInt = getNextOptionInt(it2);
                                    if (nextOptionInt == 2) {
                                        int nextOptionInt2 = getNextOptionInt(it2);
                                        int nextOptionInt3 = getNextOptionInt(it2);
                                        int nextOptionInt4 = getNextOptionInt(it2);
                                        if (nextOptionInt2 < 0 || nextOptionInt2 > 255 || nextOptionInt3 < 0 || nextOptionInt3 > 255 || nextOptionInt4 < 0 || nextOptionInt4 > 255) {
                                            throw new IllegalArgumentException();
                                        } else if (intValue == 38) {
                                            processSetForegroundColorExt(nextOptionInt2, nextOptionInt3, nextOptionInt4);
                                        } else {
                                            processSetBackgroundColorExt(nextOptionInt2, nextOptionInt3, nextOptionInt4);
                                        }
                                    } else if (nextOptionInt == 5) {
                                        int nextOptionInt5 = getNextOptionInt(it2);
                                        if (nextOptionInt5 < 0 || nextOptionInt5 > 255) {
                                            throw new IllegalArgumentException();
                                        } else if (intValue == 38) {
                                            processSetForegroundColorExt(nextOptionInt5);
                                        } else {
                                            processSetBackgroundColorExt(nextOptionInt5);
                                        }
                                    } else {
                                        throw new IllegalArgumentException();
                                    }
                                }
                            } else {
                                processSetBackgroundColor(intValue - 100, true);
                            }
                        }
                    }
                    if (i2 == 0) {
                        processAttributeReset();
                    }
                    return true;
                } else if (i == 115) {
                    processSaveCursorPosition();
                    return true;
                } else if (i != 117) {
                    switch (i) {
                        case 65:
                            processCursorUp(optionInt(arrayList, 0, 1));
                            return true;
                        case 66:
                            processCursorDown(optionInt(arrayList, 0, 1));
                            return true;
                        case 67:
                            processCursorRight(optionInt(arrayList, 0, 1));
                            return true;
                        case 68:
                            processCursorLeft(optionInt(arrayList, 0, 1));
                            return true;
                        case 69:
                            processCursorDownLine(optionInt(arrayList, 0, 1));
                            return true;
                        case 70:
                            processCursorUpLine(optionInt(arrayList, 0, 1));
                            return true;
                        case 71:
                            processCursorToColumn(optionInt(arrayList, 0));
                            return true;
                        case 72:
                            break;
                        default:
                            switch (i) {
                                case 74:
                                    processEraseScreen(optionInt(arrayList, 0, 0));
                                    return true;
                                case 75:
                                    processEraseLine(optionInt(arrayList, 0, 0));
                                    return true;
                                case 76:
                                    processInsertLine(optionInt(arrayList, 0, 1));
                                    return true;
                                case 77:
                                    processDeleteLine(optionInt(arrayList, 0, 1));
                                    return true;
                                default:
                                    if (97 <= i && i <= 122) {
                                        try {
                                            processUnknownExtension(arrayList, i);
                                            return true;
                                        } catch (IllegalArgumentException unused) {
                                            return false;
                                        }
                                    } else if (65 > i || i > 90) {
                                        return false;
                                    } else {
                                        processUnknownExtension(arrayList, i);
                                        return true;
                                    }
                            }
                    }
                } else {
                    processRestoreCursorPosition();
                    return true;
                }
            }
            processCursorTo(optionInt(arrayList, 0, 1), optionInt(arrayList, 1, 1));
            return true;
        } else {
            processScrollDown(optionInt(arrayList, 0, 1));
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public boolean processOperatingSystemCommand(ArrayList<Object> arrayList) {
        int optionInt = optionInt(arrayList, 0);
        String str = (String) arrayList.get(1);
        if (optionInt == 0) {
            processChangeIconNameAndWindowTitle(str);
            return true;
        } else if (optionInt == 1) {
            processChangeIconName(str);
            return true;
        } else if (optionInt != 2) {
            try {
                processUnknownOperatingSystemCommand(optionInt, str);
                return true;
            } catch (IllegalArgumentException unused) {
                return false;
            }
        } else {
            processChangeWindowTitle(str);
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public boolean processCharsetSelect(ArrayList<Object> arrayList) {
        processCharsetSelect(optionInt(arrayList, 0), ((Character) arrayList.get(1)).charValue());
        return true;
    }

    private int optionInt(ArrayList<Object> arrayList, int i) {
        if (arrayList.size() > i) {
            Object obj = arrayList.get(i);
            if (obj == null) {
                throw new IllegalArgumentException();
            } else if (obj.getClass().equals(Integer.class)) {
                return ((Integer) obj).intValue();
            } else {
                throw new IllegalArgumentException();
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    private int optionInt(ArrayList<Object> arrayList, int i, int i2) {
        Object obj;
        if (arrayList.size() <= i || (obj = arrayList.get(i)) == null) {
            return i2;
        }
        return ((Integer) obj).intValue();
    }

    /* access modifiers changed from: protected */
    public void processSetForegroundColor(int i) throws IOException {
        processSetForegroundColor(i, false);
    }

    /* access modifiers changed from: protected */
    public void processSetBackgroundColor(int i) throws IOException {
        processSetBackgroundColor(i, false);
    }

    /* access modifiers changed from: protected */
    public void processCursorDownLine(int i) throws IOException {
        for (int i2 = 0; i2 < i; i2++) {
            this.os.write(10);
        }
    }

    /* access modifiers changed from: protected */
    public void processCursorRight(int i) throws IOException {
        for (int i2 = 0; i2 < i; i2++) {
            this.os.write(32);
        }
    }

    /* access modifiers changed from: protected */
    public void processChangeIconNameAndWindowTitle(String str) {
        processChangeIconName(str);
        processChangeWindowTitle(str);
    }
}
