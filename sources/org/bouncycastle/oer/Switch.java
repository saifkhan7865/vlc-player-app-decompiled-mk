package org.bouncycastle.oer;

import org.bouncycastle.asn1.ASN1Encodable;

public interface Switch {
    ASN1Encodable[] keys();

    Element result(SwitchIndexer switchIndexer);
}
