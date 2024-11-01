package org.bouncycastle.pqc.jcajce.spec;

import java.security.spec.AlgorithmParameterSpec;
import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.util.Strings;

public class SPHINCSPlusParameterSpec implements AlgorithmParameterSpec {
    public static final SPHINCSPlusParameterSpec haraka_128f;
    public static final SPHINCSPlusParameterSpec haraka_128f_simple;
    public static final SPHINCSPlusParameterSpec haraka_128s;
    public static final SPHINCSPlusParameterSpec haraka_128s_simple;
    public static final SPHINCSPlusParameterSpec haraka_192f;
    public static final SPHINCSPlusParameterSpec haraka_192f_simple;
    public static final SPHINCSPlusParameterSpec haraka_192s;
    public static final SPHINCSPlusParameterSpec haraka_192s_simple;
    public static final SPHINCSPlusParameterSpec haraka_256f;
    public static final SPHINCSPlusParameterSpec haraka_256f_simple;
    public static final SPHINCSPlusParameterSpec haraka_256s;
    public static final SPHINCSPlusParameterSpec haraka_256s_simple;
    private static Map parameters;
    public static final SPHINCSPlusParameterSpec sha2_128f;
    public static final SPHINCSPlusParameterSpec sha2_128f_robust;
    public static final SPHINCSPlusParameterSpec sha2_128s;
    public static final SPHINCSPlusParameterSpec sha2_128s_robust;
    public static final SPHINCSPlusParameterSpec sha2_192f;
    public static final SPHINCSPlusParameterSpec sha2_192f_robust;
    public static final SPHINCSPlusParameterSpec sha2_192s;
    public static final SPHINCSPlusParameterSpec sha2_192s_robust;
    public static final SPHINCSPlusParameterSpec sha2_256f;
    public static final SPHINCSPlusParameterSpec sha2_256f_robust;
    public static final SPHINCSPlusParameterSpec sha2_256s;
    public static final SPHINCSPlusParameterSpec sha2_256s_robust;
    public static final SPHINCSPlusParameterSpec shake_128f;
    public static final SPHINCSPlusParameterSpec shake_128f_robust;
    public static final SPHINCSPlusParameterSpec shake_128s;
    public static final SPHINCSPlusParameterSpec shake_128s_robust;
    public static final SPHINCSPlusParameterSpec shake_192f;
    public static final SPHINCSPlusParameterSpec shake_192f_robust;
    public static final SPHINCSPlusParameterSpec shake_192s;
    public static final SPHINCSPlusParameterSpec shake_192s_robust;
    public static final SPHINCSPlusParameterSpec shake_256f;
    public static final SPHINCSPlusParameterSpec shake_256f_robust;
    public static final SPHINCSPlusParameterSpec shake_256s;
    public static final SPHINCSPlusParameterSpec shake_256s_robust;
    private final String name;

    static {
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec = new SPHINCSPlusParameterSpec("sha2-128f-robust");
        sha2_128f_robust = sPHINCSPlusParameterSpec;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec2 = new SPHINCSPlusParameterSpec("sha2-128s-robust");
        sha2_128s_robust = sPHINCSPlusParameterSpec2;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec3 = new SPHINCSPlusParameterSpec("sha2-192f-robust");
        sha2_192f_robust = sPHINCSPlusParameterSpec3;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec4 = new SPHINCSPlusParameterSpec("sha2-192s-robust");
        sha2_192s_robust = sPHINCSPlusParameterSpec4;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec5 = new SPHINCSPlusParameterSpec("sha2-256f-robust");
        sha2_256f_robust = sPHINCSPlusParameterSpec5;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec6 = new SPHINCSPlusParameterSpec("sha2-256s-robust");
        sha2_256s_robust = sPHINCSPlusParameterSpec6;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec7 = new SPHINCSPlusParameterSpec("sha2-128s");
        sha2_128f = sPHINCSPlusParameterSpec7;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec8 = new SPHINCSPlusParameterSpec("sha2-128f");
        sha2_128s = sPHINCSPlusParameterSpec8;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec9 = new SPHINCSPlusParameterSpec("sha2-192f");
        sha2_192f = sPHINCSPlusParameterSpec9;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec10 = new SPHINCSPlusParameterSpec("sha2-192s");
        sha2_192s = sPHINCSPlusParameterSpec10;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec11 = new SPHINCSPlusParameterSpec("sha2-256f");
        sha2_256f = sPHINCSPlusParameterSpec11;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec12 = new SPHINCSPlusParameterSpec("sha2-256s");
        sha2_256s = sPHINCSPlusParameterSpec12;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec13 = new SPHINCSPlusParameterSpec("shake-128f-robust");
        shake_128f_robust = sPHINCSPlusParameterSpec13;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec14 = sPHINCSPlusParameterSpec13;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec15 = new SPHINCSPlusParameterSpec("shake-128s-robust");
        shake_128s_robust = sPHINCSPlusParameterSpec15;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec16 = sPHINCSPlusParameterSpec15;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec17 = new SPHINCSPlusParameterSpec("shake-192f-robust");
        shake_192f_robust = sPHINCSPlusParameterSpec17;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec18 = sPHINCSPlusParameterSpec17;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec19 = new SPHINCSPlusParameterSpec("shake-192s-robust");
        shake_192s_robust = sPHINCSPlusParameterSpec19;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec20 = sPHINCSPlusParameterSpec19;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec21 = new SPHINCSPlusParameterSpec("shake-256f-robust");
        shake_256f_robust = sPHINCSPlusParameterSpec21;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec22 = sPHINCSPlusParameterSpec21;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec23 = new SPHINCSPlusParameterSpec("shake-256s-robust");
        shake_256s_robust = sPHINCSPlusParameterSpec23;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec24 = sPHINCSPlusParameterSpec23;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec25 = new SPHINCSPlusParameterSpec("shake-128f");
        shake_128f = sPHINCSPlusParameterSpec25;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec26 = sPHINCSPlusParameterSpec25;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec27 = new SPHINCSPlusParameterSpec("shake-128s");
        shake_128s = sPHINCSPlusParameterSpec27;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec28 = sPHINCSPlusParameterSpec27;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec29 = new SPHINCSPlusParameterSpec("shake-192f");
        shake_192f = sPHINCSPlusParameterSpec29;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec30 = sPHINCSPlusParameterSpec29;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec31 = new SPHINCSPlusParameterSpec("shake-192s");
        shake_192s = sPHINCSPlusParameterSpec31;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec32 = sPHINCSPlusParameterSpec31;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec33 = new SPHINCSPlusParameterSpec("shake-256f");
        shake_256f = sPHINCSPlusParameterSpec33;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec34 = sPHINCSPlusParameterSpec33;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec35 = new SPHINCSPlusParameterSpec("shake-256s");
        shake_256s = sPHINCSPlusParameterSpec35;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec36 = sPHINCSPlusParameterSpec35;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec37 = new SPHINCSPlusParameterSpec("haraka-128f-robust");
        haraka_128f = sPHINCSPlusParameterSpec37;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec38 = sPHINCSPlusParameterSpec37;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec39 = new SPHINCSPlusParameterSpec("haraka-128s-robust");
        haraka_128s = sPHINCSPlusParameterSpec39;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec40 = sPHINCSPlusParameterSpec39;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec41 = new SPHINCSPlusParameterSpec("haraka-256f-robust");
        haraka_256f = sPHINCSPlusParameterSpec41;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec42 = sPHINCSPlusParameterSpec41;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec43 = new SPHINCSPlusParameterSpec("haraka-256s-robust");
        haraka_256s = sPHINCSPlusParameterSpec43;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec44 = sPHINCSPlusParameterSpec43;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec45 = new SPHINCSPlusParameterSpec("haraka-192f-robust");
        haraka_192f = sPHINCSPlusParameterSpec45;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec46 = sPHINCSPlusParameterSpec45;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec47 = new SPHINCSPlusParameterSpec("haraka-192s-robust");
        haraka_192s = sPHINCSPlusParameterSpec47;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec48 = sPHINCSPlusParameterSpec47;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec49 = new SPHINCSPlusParameterSpec("haraka-128f-simple");
        haraka_128f_simple = sPHINCSPlusParameterSpec49;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec50 = sPHINCSPlusParameterSpec49;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec51 = new SPHINCSPlusParameterSpec("haraka-128s-simple");
        haraka_128s_simple = sPHINCSPlusParameterSpec51;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec52 = sPHINCSPlusParameterSpec51;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec53 = new SPHINCSPlusParameterSpec("haraka-192f-simple");
        haraka_192f_simple = sPHINCSPlusParameterSpec53;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec54 = sPHINCSPlusParameterSpec53;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec55 = new SPHINCSPlusParameterSpec("haraka-192s-simple");
        haraka_192s_simple = sPHINCSPlusParameterSpec55;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec56 = sPHINCSPlusParameterSpec55;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec57 = new SPHINCSPlusParameterSpec("haraka-256f-simple");
        haraka_256f_simple = sPHINCSPlusParameterSpec57;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec58 = sPHINCSPlusParameterSpec57;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec59 = new SPHINCSPlusParameterSpec("haraka-256s-simple");
        haraka_256s_simple = sPHINCSPlusParameterSpec59;
        HashMap hashMap = new HashMap();
        parameters = hashMap;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec60 = sPHINCSPlusParameterSpec59;
        hashMap.put(sPHINCSPlusParameterSpec.getName(), sPHINCSPlusParameterSpec);
        parameters.put(sPHINCSPlusParameterSpec2.getName(), sPHINCSPlusParameterSpec2);
        parameters.put(sPHINCSPlusParameterSpec3.getName(), sPHINCSPlusParameterSpec3);
        parameters.put(sPHINCSPlusParameterSpec4.getName(), sPHINCSPlusParameterSpec4);
        parameters.put(sPHINCSPlusParameterSpec5.getName(), sPHINCSPlusParameterSpec5);
        parameters.put(sPHINCSPlusParameterSpec6.getName(), sPHINCSPlusParameterSpec6);
        parameters.put(sPHINCSPlusParameterSpec7.getName(), sPHINCSPlusParameterSpec7);
        parameters.put(sPHINCSPlusParameterSpec8.getName(), sPHINCSPlusParameterSpec8);
        parameters.put(sPHINCSPlusParameterSpec9.getName(), sPHINCSPlusParameterSpec9);
        parameters.put(sPHINCSPlusParameterSpec10.getName(), sPHINCSPlusParameterSpec10);
        parameters.put(sPHINCSPlusParameterSpec11.getName(), sPHINCSPlusParameterSpec11);
        parameters.put(sPHINCSPlusParameterSpec12.getName(), sPHINCSPlusParameterSpec12);
        parameters.put("sha2-128f", sPHINCSPlusParameterSpec7);
        parameters.put("sha2-128s", sPHINCSPlusParameterSpec8);
        parameters.put("sha2-192f", sPHINCSPlusParameterSpec9);
        parameters.put("sha2-192s", sPHINCSPlusParameterSpec10);
        parameters.put("sha2-256f", sPHINCSPlusParameterSpec11);
        parameters.put("sha2-256s", sPHINCSPlusParameterSpec12);
        parameters.put(sPHINCSPlusParameterSpec14.getName(), sPHINCSPlusParameterSpec14);
        parameters.put(sPHINCSPlusParameterSpec16.getName(), sPHINCSPlusParameterSpec16);
        parameters.put(sPHINCSPlusParameterSpec18.getName(), sPHINCSPlusParameterSpec18);
        parameters.put(sPHINCSPlusParameterSpec20.getName(), sPHINCSPlusParameterSpec20);
        parameters.put(sPHINCSPlusParameterSpec22.getName(), sPHINCSPlusParameterSpec22);
        parameters.put(sPHINCSPlusParameterSpec24.getName(), sPHINCSPlusParameterSpec24);
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec61 = sPHINCSPlusParameterSpec26;
        parameters.put(sPHINCSPlusParameterSpec26.getName(), sPHINCSPlusParameterSpec61);
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec62 = sPHINCSPlusParameterSpec28;
        parameters.put(sPHINCSPlusParameterSpec28.getName(), sPHINCSPlusParameterSpec62);
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec63 = sPHINCSPlusParameterSpec30;
        parameters.put(sPHINCSPlusParameterSpec30.getName(), sPHINCSPlusParameterSpec63);
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec64 = sPHINCSPlusParameterSpec32;
        parameters.put(sPHINCSPlusParameterSpec32.getName(), sPHINCSPlusParameterSpec64);
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec65 = sPHINCSPlusParameterSpec34;
        parameters.put(sPHINCSPlusParameterSpec34.getName(), sPHINCSPlusParameterSpec65);
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec66 = sPHINCSPlusParameterSpec36;
        parameters.put(sPHINCSPlusParameterSpec36.getName(), sPHINCSPlusParameterSpec66);
        parameters.put("shake-128f", sPHINCSPlusParameterSpec61);
        parameters.put("shake-128s", sPHINCSPlusParameterSpec62);
        parameters.put("shake-192f", sPHINCSPlusParameterSpec63);
        parameters.put("shake-192s", sPHINCSPlusParameterSpec64);
        parameters.put("shake-256f", sPHINCSPlusParameterSpec65);
        parameters.put("shake-256s", sPHINCSPlusParameterSpec66);
        parameters.put(sPHINCSPlusParameterSpec38.getName(), sPHINCSPlusParameterSpec38);
        parameters.put(sPHINCSPlusParameterSpec40.getName(), sPHINCSPlusParameterSpec40);
        parameters.put(sPHINCSPlusParameterSpec46.getName(), sPHINCSPlusParameterSpec46);
        parameters.put(sPHINCSPlusParameterSpec48.getName(), sPHINCSPlusParameterSpec48);
        parameters.put(sPHINCSPlusParameterSpec42.getName(), sPHINCSPlusParameterSpec42);
        parameters.put(sPHINCSPlusParameterSpec44.getName(), sPHINCSPlusParameterSpec44);
        parameters.put(sPHINCSPlusParameterSpec50.getName(), sPHINCSPlusParameterSpec50);
        parameters.put(sPHINCSPlusParameterSpec52.getName(), sPHINCSPlusParameterSpec52);
        parameters.put(sPHINCSPlusParameterSpec54.getName(), sPHINCSPlusParameterSpec54);
        parameters.put(sPHINCSPlusParameterSpec56.getName(), sPHINCSPlusParameterSpec56);
        parameters.put(sPHINCSPlusParameterSpec58.getName(), sPHINCSPlusParameterSpec58);
        parameters.put(sPHINCSPlusParameterSpec60.getName(), sPHINCSPlusParameterSpec60);
    }

    private SPHINCSPlusParameterSpec(String str) {
        this.name = str;
    }

    public static SPHINCSPlusParameterSpec fromName(String str) {
        return (SPHINCSPlusParameterSpec) parameters.get(Strings.toLowerCase(str));
    }

    public String getName() {
        return this.name;
    }
}
