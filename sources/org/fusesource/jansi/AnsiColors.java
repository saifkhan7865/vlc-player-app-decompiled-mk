package org.fusesource.jansi;

public enum AnsiColors {
    Colors16("16 colors"),
    Colors256("256 colors"),
    TrueColor("24-bit colors");
    
    private final String description;

    private AnsiColors(String str) {
        this.description = str;
    }

    /* access modifiers changed from: package-private */
    public String getDescription() {
        return this.description;
    }
}
