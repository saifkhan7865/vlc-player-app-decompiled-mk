package org.bouncycastle.pkix.util.filter;

public class TrustedInput {
    protected Object input;

    public TrustedInput(Object obj) {
        this.input = obj;
    }

    public Object getInput() {
        return this.input;
    }

    public String toString() {
        return this.input.toString();
    }
}
