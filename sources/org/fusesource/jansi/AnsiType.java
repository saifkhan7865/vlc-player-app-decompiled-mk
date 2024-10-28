package org.fusesource.jansi;

public enum AnsiType {
    Native("Supports ansi sequences natively"),
    Unsupported("Ansi sequences are stripped out"),
    VirtualTerminal("Supported through windows virtual terminal"),
    Emulation("Emulated through using windows API console commands"),
    Redirected("The stream is redirected to a file or a pipe");
    
    private final String description;

    private AnsiType(String str) {
        this.description = str;
    }

    /* access modifiers changed from: package-private */
    public String getDescription() {
        return this.description;
    }
}
