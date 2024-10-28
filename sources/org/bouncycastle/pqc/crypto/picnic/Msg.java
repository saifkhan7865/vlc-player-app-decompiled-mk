package org.bouncycastle.pqc.crypto.picnic;

import java.lang.reflect.Array;

class Msg {
    byte[][] msgs;
    int pos = 0;
    int unopened = -1;

    public Msg(PicnicEngine picnicEngine) {
        int i = picnicEngine.numMPCParties;
        int[] iArr = new int[2];
        iArr[1] = picnicEngine.andSizeBytes;
        iArr[0] = i;
        this.msgs = (byte[][]) Array.newInstance(Byte.TYPE, iArr);
    }
}
