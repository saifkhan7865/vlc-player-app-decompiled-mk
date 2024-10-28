package org.fusesource.jansi.io;

import io.ktor.util.date.GMTDateParser;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import org.fusesource.jansi.AnsiColors;

public class ColorsAnsiProcessor extends AnsiProcessor {
    private final AnsiColors colors;

    /* access modifiers changed from: protected */
    public boolean processCharsetSelect(ArrayList<Object> arrayList) {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean processOperatingSystemCommand(ArrayList<Object> arrayList) {
        return false;
    }

    public ColorsAnsiProcessor(OutputStream outputStream, AnsiColors ansiColors) {
        super(outputStream);
        this.colors = ansiColors;
    }

    /* access modifiers changed from: protected */
    public boolean processEscapeCommand(ArrayList<Object> arrayList, int i) throws IOException {
        if (i != 109) {
            return false;
        }
        if (this.colors != AnsiColors.Colors256 && this.colors != AnsiColors.Colors16) {
            return false;
        }
        Iterator<Object> it = arrayList.iterator();
        boolean z = false;
        while (true) {
            int i2 = 48;
            if (it.hasNext()) {
                Object next = it.next();
                if (next == null || next.getClass() == Integer.class) {
                    Integer num = (Integer) next;
                    z |= num.intValue() == 38 || num.intValue() == 48;
                } else {
                    throw new IllegalArgumentException();
                }
            } else if (!z) {
                return false;
            } else {
                StringBuilder sb = new StringBuilder(32);
                sb.append("\u001b[");
                Iterator<Object> it2 = arrayList.iterator();
                boolean z2 = true;
                while (it2.hasNext()) {
                    Object next2 = it2.next();
                    if (next2 != null) {
                        int intValue = ((Integer) next2).intValue();
                        if (intValue == 38 || intValue == i2) {
                            int nextOptionInt = getNextOptionInt(it2);
                            if (nextOptionInt == 2) {
                                int nextOptionInt2 = getNextOptionInt(it2);
                                int nextOptionInt3 = getNextOptionInt(it2);
                                int nextOptionInt4 = getNextOptionInt(it2);
                                if (this.colors == AnsiColors.Colors256) {
                                    int roundRgbColor = Colors.roundRgbColor(nextOptionInt2, nextOptionInt3, nextOptionInt4, 256);
                                    if (!z2) {
                                        sb.append(';');
                                    }
                                    sb.append(intValue);
                                    sb.append(";5;");
                                    sb.append(roundRgbColor);
                                } else {
                                    int roundRgbColor2 = Colors.roundRgbColor(nextOptionInt2, nextOptionInt3, nextOptionInt4, 16);
                                    if (!z2) {
                                        sb.append(';');
                                    }
                                    sb.append(intValue == 38 ? roundRgbColor2 >= 8 ? roundRgbColor2 + 82 : roundRgbColor2 + 30 : roundRgbColor2 >= 8 ? roundRgbColor2 + 92 : roundRgbColor2 + 40);
                                }
                            } else if (nextOptionInt == 5) {
                                int nextOptionInt5 = getNextOptionInt(it2);
                                if (this.colors == AnsiColors.Colors256) {
                                    if (!z2) {
                                        sb.append(';');
                                    }
                                    sb.append(intValue);
                                    sb.append(";5;");
                                    sb.append(nextOptionInt5);
                                } else {
                                    int roundColor = Colors.roundColor(nextOptionInt5, 16);
                                    if (!z2) {
                                        sb.append(';');
                                    }
                                    sb.append(intValue == 38 ? roundColor >= 8 ? roundColor + 82 : roundColor + 30 : roundColor >= 8 ? roundColor + 92 : roundColor + 40);
                                }
                            } else {
                                throw new IllegalArgumentException();
                            }
                        } else {
                            if (!z2) {
                                sb.append(';');
                            }
                            sb.append(intValue);
                        }
                        z2 = false;
                    }
                    i2 = 48;
                }
                sb.append(GMTDateParser.MINUTES);
                this.os.write(sb.toString().getBytes());
                return true;
            }
        }
    }
}
