package org.bouncycastle.pqc.crypto.sphincsplus;

import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusEngine;
import org.bouncycastle.util.Integers;
import org.bouncycastle.util.Pack;

public class SPHINCSPlusParameters {
    private static final Map<Integer, SPHINCSPlusParameters> ID_TO_PARAMS = new HashMap();
    @Deprecated
    public static final SPHINCSPlusParameters haraka_128f;
    public static final SPHINCSPlusParameters haraka_128f_simple;
    @Deprecated
    public static final SPHINCSPlusParameters haraka_128s;
    public static final SPHINCSPlusParameters haraka_128s_simple;
    @Deprecated
    public static final SPHINCSPlusParameters haraka_192f;
    public static final SPHINCSPlusParameters haraka_192f_simple;
    @Deprecated
    public static final SPHINCSPlusParameters haraka_192s;
    public static final SPHINCSPlusParameters haraka_192s_simple;
    @Deprecated
    public static final SPHINCSPlusParameters haraka_256f;
    public static final SPHINCSPlusParameters haraka_256f_simple;
    @Deprecated
    public static final SPHINCSPlusParameters haraka_256s;
    public static final SPHINCSPlusParameters haraka_256s_simple;
    public static final SPHINCSPlusParameters sha2_128f;
    public static final SPHINCSPlusParameters sha2_128f_robust;
    public static final SPHINCSPlusParameters sha2_128s;
    public static final SPHINCSPlusParameters sha2_128s_robust;
    public static final SPHINCSPlusParameters sha2_192f;
    public static final SPHINCSPlusParameters sha2_192f_robust;
    public static final SPHINCSPlusParameters sha2_192s;
    public static final SPHINCSPlusParameters sha2_192s_robust;
    public static final SPHINCSPlusParameters sha2_256f;
    public static final SPHINCSPlusParameters sha2_256f_robust;
    public static final SPHINCSPlusParameters sha2_256s;
    public static final SPHINCSPlusParameters sha2_256s_robust;
    public static final SPHINCSPlusParameters shake_128f;
    public static final SPHINCSPlusParameters shake_128f_robust;
    public static final SPHINCSPlusParameters shake_128s;
    public static final SPHINCSPlusParameters shake_128s_robust;
    public static final SPHINCSPlusParameters shake_192f;
    public static final SPHINCSPlusParameters shake_192f_robust;
    public static final SPHINCSPlusParameters shake_192s;
    public static final SPHINCSPlusParameters shake_192s_robust;
    public static final SPHINCSPlusParameters shake_256f;
    public static final SPHINCSPlusParameters shake_256f_robust;
    public static final SPHINCSPlusParameters shake_256s;
    public static final SPHINCSPlusParameters shake_256s_robust;
    private final SPHINCSPlusEngineProvider engineProvider;
    private final Integer id;
    private final String name;

    private static class HarakaSEngineProvider implements SPHINCSPlusEngineProvider {
        private final int a;
        private final int d;
        private final int h;
        private final int k;
        private final int n;
        private final boolean robust;
        private final int w;

        public HarakaSEngineProvider(boolean z, int i, int i2, int i3, int i4, int i5, int i6) {
            this.robust = z;
            this.n = i;
            this.w = i2;
            this.d = i3;
            this.a = i4;
            this.k = i5;
            this.h = i6;
        }

        public SPHINCSPlusEngine get() {
            return new SPHINCSPlusEngine.HarakaSEngine(this.robust, this.n, this.w, this.d, this.a, this.k, this.h);
        }

        public int getN() {
            return this.n;
        }
    }

    private static class Sha2EngineProvider implements SPHINCSPlusEngineProvider {
        private final int a;
        private final int d;
        private final int h;
        private final int k;
        private final int n;
        private final boolean robust;
        private final int w;

        public Sha2EngineProvider(boolean z, int i, int i2, int i3, int i4, int i5, int i6) {
            this.robust = z;
            this.n = i;
            this.w = i2;
            this.d = i3;
            this.a = i4;
            this.k = i5;
            this.h = i6;
        }

        public SPHINCSPlusEngine get() {
            return new SPHINCSPlusEngine.Sha2Engine(this.robust, this.n, this.w, this.d, this.a, this.k, this.h);
        }

        public int getN() {
            return this.n;
        }
    }

    private static class Shake256EngineProvider implements SPHINCSPlusEngineProvider {
        private final int a;
        private final int d;
        private final int h;
        private final int k;
        private final int n;
        private final boolean robust;
        private final int w;

        public Shake256EngineProvider(boolean z, int i, int i2, int i3, int i4, int i5, int i6) {
            this.robust = z;
            this.n = i;
            this.w = i2;
            this.d = i3;
            this.a = i4;
            this.k = i5;
            this.h = i6;
        }

        public SPHINCSPlusEngine get() {
            return new SPHINCSPlusEngine.Shake256Engine(this.robust, this.n, this.w, this.d, this.a, this.k, this.h);
        }

        public int getN() {
            return this.n;
        }
    }

    static {
        SPHINCSPlusParameters sPHINCSPlusParameters = new SPHINCSPlusParameters(Integers.valueOf(65793), "sha2-128f-robust", new Sha2EngineProvider(true, 16, 16, 22, 6, 33, 66));
        sha2_128f_robust = sPHINCSPlusParameters;
        SPHINCSPlusParameters sPHINCSPlusParameters2 = new SPHINCSPlusParameters(Integers.valueOf(65794), "sha2-128s-robust", new Sha2EngineProvider(true, 16, 16, 7, 12, 14, 63));
        sha2_128s_robust = sPHINCSPlusParameters2;
        SPHINCSPlusParameters sPHINCSPlusParameters3 = new SPHINCSPlusParameters(Integers.valueOf(65795), "sha2-192f-robust", new Sha2EngineProvider(true, 24, 16, 22, 8, 33, 66));
        sha2_192f_robust = sPHINCSPlusParameters3;
        SPHINCSPlusParameters sPHINCSPlusParameters4 = new SPHINCSPlusParameters(Integers.valueOf(65796), "sha2-192s-robust", new Sha2EngineProvider(true, 24, 16, 7, 14, 17, 63));
        sha2_192s_robust = sPHINCSPlusParameters4;
        SPHINCSPlusParameters sPHINCSPlusParameters5 = new SPHINCSPlusParameters(Integers.valueOf(65797), "sha2-256f-robust", new Sha2EngineProvider(true, 32, 16, 17, 9, 35, 68));
        sha2_256f_robust = sPHINCSPlusParameters5;
        SPHINCSPlusParameters sPHINCSPlusParameters6 = new SPHINCSPlusParameters(Integers.valueOf(65798), "sha2-256s-robust", new Sha2EngineProvider(true, 32, 16, 8, 14, 22, 64));
        sha2_256s_robust = sPHINCSPlusParameters6;
        Integer valueOf = Integers.valueOf(66049);
        SPHINCSPlusParameters sPHINCSPlusParameters7 = sPHINCSPlusParameters6;
        Sha2EngineProvider sha2EngineProvider = r8;
        Sha2EngineProvider sha2EngineProvider2 = new Sha2EngineProvider(false, 16, 16, 22, 6, 33, 66);
        SPHINCSPlusParameters sPHINCSPlusParameters8 = new SPHINCSPlusParameters(valueOf, "sha2-128f", sha2EngineProvider);
        sha2_128f = sPHINCSPlusParameters8;
        Integer valueOf2 = Integers.valueOf(66050);
        SPHINCSPlusParameters sPHINCSPlusParameters9 = sPHINCSPlusParameters8;
        Sha2EngineProvider sha2EngineProvider3 = r8;
        Sha2EngineProvider sha2EngineProvider4 = new Sha2EngineProvider(false, 16, 16, 7, 12, 14, 63);
        SPHINCSPlusParameters sPHINCSPlusParameters10 = new SPHINCSPlusParameters(valueOf2, "sha2-128s", sha2EngineProvider3);
        sha2_128s = sPHINCSPlusParameters10;
        Integer valueOf3 = Integers.valueOf(66051);
        SPHINCSPlusParameters sPHINCSPlusParameters11 = sPHINCSPlusParameters10;
        Sha2EngineProvider sha2EngineProvider5 = r8;
        Sha2EngineProvider sha2EngineProvider6 = new Sha2EngineProvider(false, 24, 16, 22, 8, 33, 66);
        SPHINCSPlusParameters sPHINCSPlusParameters12 = new SPHINCSPlusParameters(valueOf3, "sha2-192f", sha2EngineProvider5);
        sha2_192f = sPHINCSPlusParameters12;
        Integer valueOf4 = Integers.valueOf(66052);
        SPHINCSPlusParameters sPHINCSPlusParameters13 = sPHINCSPlusParameters12;
        Sha2EngineProvider sha2EngineProvider7 = r8;
        Sha2EngineProvider sha2EngineProvider8 = new Sha2EngineProvider(false, 24, 16, 7, 14, 17, 63);
        SPHINCSPlusParameters sPHINCSPlusParameters14 = new SPHINCSPlusParameters(valueOf4, "sha2-192s", sha2EngineProvider7);
        sha2_192s = sPHINCSPlusParameters14;
        Integer valueOf5 = Integers.valueOf(66053);
        SPHINCSPlusParameters sPHINCSPlusParameters15 = sPHINCSPlusParameters14;
        Sha2EngineProvider sha2EngineProvider9 = r8;
        Sha2EngineProvider sha2EngineProvider10 = new Sha2EngineProvider(false, 32, 16, 17, 9, 35, 68);
        SPHINCSPlusParameters sPHINCSPlusParameters16 = new SPHINCSPlusParameters(valueOf5, "sha2-256f", sha2EngineProvider9);
        sha2_256f = sPHINCSPlusParameters16;
        Integer valueOf6 = Integers.valueOf(66054);
        SPHINCSPlusParameters sPHINCSPlusParameters17 = sPHINCSPlusParameters16;
        Sha2EngineProvider sha2EngineProvider11 = r8;
        Sha2EngineProvider sha2EngineProvider12 = new Sha2EngineProvider(false, 32, 16, 8, 14, 22, 64);
        SPHINCSPlusParameters sPHINCSPlusParameters18 = new SPHINCSPlusParameters(valueOf6, "sha2-256s", sha2EngineProvider11);
        sha2_256s = sPHINCSPlusParameters18;
        Integer valueOf7 = Integers.valueOf(131329);
        SPHINCSPlusParameters sPHINCSPlusParameters19 = sPHINCSPlusParameters18;
        Shake256EngineProvider shake256EngineProvider = r8;
        Shake256EngineProvider shake256EngineProvider2 = new Shake256EngineProvider(true, 16, 16, 22, 6, 33, 66);
        SPHINCSPlusParameters sPHINCSPlusParameters20 = new SPHINCSPlusParameters(valueOf7, "shake-128f-robust", shake256EngineProvider);
        shake_128f_robust = sPHINCSPlusParameters20;
        Integer valueOf8 = Integers.valueOf(131330);
        SPHINCSPlusParameters sPHINCSPlusParameters21 = sPHINCSPlusParameters20;
        Shake256EngineProvider shake256EngineProvider3 = r8;
        Shake256EngineProvider shake256EngineProvider4 = new Shake256EngineProvider(true, 16, 16, 7, 12, 14, 63);
        SPHINCSPlusParameters sPHINCSPlusParameters22 = new SPHINCSPlusParameters(valueOf8, "shake-128s-robust", shake256EngineProvider3);
        shake_128s_robust = sPHINCSPlusParameters22;
        Integer valueOf9 = Integers.valueOf(131331);
        SPHINCSPlusParameters sPHINCSPlusParameters23 = sPHINCSPlusParameters22;
        Shake256EngineProvider shake256EngineProvider5 = r8;
        Shake256EngineProvider shake256EngineProvider6 = new Shake256EngineProvider(true, 24, 16, 22, 8, 33, 66);
        SPHINCSPlusParameters sPHINCSPlusParameters24 = new SPHINCSPlusParameters(valueOf9, "shake-192f-robust", shake256EngineProvider5);
        shake_192f_robust = sPHINCSPlusParameters24;
        Integer valueOf10 = Integers.valueOf(131332);
        SPHINCSPlusParameters sPHINCSPlusParameters25 = sPHINCSPlusParameters24;
        Shake256EngineProvider shake256EngineProvider7 = r8;
        Shake256EngineProvider shake256EngineProvider8 = new Shake256EngineProvider(true, 24, 16, 7, 14, 17, 63);
        SPHINCSPlusParameters sPHINCSPlusParameters26 = new SPHINCSPlusParameters(valueOf10, "shake-192s-robust", shake256EngineProvider7);
        shake_192s_robust = sPHINCSPlusParameters26;
        Integer valueOf11 = Integers.valueOf(131333);
        SPHINCSPlusParameters sPHINCSPlusParameters27 = sPHINCSPlusParameters26;
        Shake256EngineProvider shake256EngineProvider9 = r8;
        Shake256EngineProvider shake256EngineProvider10 = new Shake256EngineProvider(true, 32, 16, 17, 9, 35, 68);
        SPHINCSPlusParameters sPHINCSPlusParameters28 = new SPHINCSPlusParameters(valueOf11, "shake-256f-robust", shake256EngineProvider9);
        shake_256f_robust = sPHINCSPlusParameters28;
        Integer valueOf12 = Integers.valueOf(131334);
        SPHINCSPlusParameters sPHINCSPlusParameters29 = sPHINCSPlusParameters28;
        Shake256EngineProvider shake256EngineProvider11 = r8;
        Shake256EngineProvider shake256EngineProvider12 = new Shake256EngineProvider(true, 32, 16, 8, 14, 22, 64);
        SPHINCSPlusParameters sPHINCSPlusParameters30 = new SPHINCSPlusParameters(valueOf12, "shake-256s-robust", shake256EngineProvider11);
        shake_256s_robust = sPHINCSPlusParameters30;
        Integer valueOf13 = Integers.valueOf(131585);
        SPHINCSPlusParameters sPHINCSPlusParameters31 = sPHINCSPlusParameters30;
        Shake256EngineProvider shake256EngineProvider13 = r8;
        Shake256EngineProvider shake256EngineProvider14 = new Shake256EngineProvider(false, 16, 16, 22, 6, 33, 66);
        SPHINCSPlusParameters sPHINCSPlusParameters32 = new SPHINCSPlusParameters(valueOf13, "shake-128f", shake256EngineProvider13);
        shake_128f = sPHINCSPlusParameters32;
        Integer valueOf14 = Integers.valueOf(131586);
        SPHINCSPlusParameters sPHINCSPlusParameters33 = sPHINCSPlusParameters32;
        Shake256EngineProvider shake256EngineProvider15 = r8;
        Shake256EngineProvider shake256EngineProvider16 = new Shake256EngineProvider(false, 16, 16, 7, 12, 14, 63);
        SPHINCSPlusParameters sPHINCSPlusParameters34 = new SPHINCSPlusParameters(valueOf14, "shake-128s", shake256EngineProvider15);
        shake_128s = sPHINCSPlusParameters34;
        Integer valueOf15 = Integers.valueOf(131587);
        SPHINCSPlusParameters sPHINCSPlusParameters35 = sPHINCSPlusParameters34;
        Shake256EngineProvider shake256EngineProvider17 = r8;
        Shake256EngineProvider shake256EngineProvider18 = new Shake256EngineProvider(false, 24, 16, 22, 8, 33, 66);
        SPHINCSPlusParameters sPHINCSPlusParameters36 = new SPHINCSPlusParameters(valueOf15, "shake-192f", shake256EngineProvider17);
        shake_192f = sPHINCSPlusParameters36;
        Integer valueOf16 = Integers.valueOf(131588);
        SPHINCSPlusParameters sPHINCSPlusParameters37 = sPHINCSPlusParameters36;
        Shake256EngineProvider shake256EngineProvider19 = r8;
        Shake256EngineProvider shake256EngineProvider20 = new Shake256EngineProvider(false, 24, 16, 7, 14, 17, 63);
        SPHINCSPlusParameters sPHINCSPlusParameters38 = new SPHINCSPlusParameters(valueOf16, "shake-192s", shake256EngineProvider19);
        shake_192s = sPHINCSPlusParameters38;
        Integer valueOf17 = Integers.valueOf(131589);
        SPHINCSPlusParameters sPHINCSPlusParameters39 = sPHINCSPlusParameters38;
        Shake256EngineProvider shake256EngineProvider21 = r8;
        Shake256EngineProvider shake256EngineProvider22 = new Shake256EngineProvider(false, 32, 16, 17, 9, 35, 68);
        SPHINCSPlusParameters sPHINCSPlusParameters40 = new SPHINCSPlusParameters(valueOf17, "shake-256f", shake256EngineProvider21);
        shake_256f = sPHINCSPlusParameters40;
        Integer valueOf18 = Integers.valueOf(131590);
        SPHINCSPlusParameters sPHINCSPlusParameters41 = sPHINCSPlusParameters40;
        Shake256EngineProvider shake256EngineProvider23 = r8;
        Shake256EngineProvider shake256EngineProvider24 = new Shake256EngineProvider(false, 32, 16, 8, 14, 22, 64);
        SPHINCSPlusParameters sPHINCSPlusParameters42 = new SPHINCSPlusParameters(valueOf18, "shake-256s", shake256EngineProvider23);
        shake_256s = sPHINCSPlusParameters42;
        Integer valueOf19 = Integers.valueOf(196865);
        SPHINCSPlusParameters sPHINCSPlusParameters43 = sPHINCSPlusParameters42;
        HarakaSEngineProvider harakaSEngineProvider = r8;
        HarakaSEngineProvider harakaSEngineProvider2 = new HarakaSEngineProvider(true, 16, 16, 22, 6, 33, 66);
        SPHINCSPlusParameters sPHINCSPlusParameters44 = new SPHINCSPlusParameters(valueOf19, "haraka-128f-robust", harakaSEngineProvider);
        haraka_128f = sPHINCSPlusParameters44;
        Integer valueOf20 = Integers.valueOf(196866);
        SPHINCSPlusParameters sPHINCSPlusParameters45 = sPHINCSPlusParameters44;
        HarakaSEngineProvider harakaSEngineProvider3 = r8;
        HarakaSEngineProvider harakaSEngineProvider4 = new HarakaSEngineProvider(true, 16, 16, 7, 12, 14, 63);
        SPHINCSPlusParameters sPHINCSPlusParameters46 = new SPHINCSPlusParameters(valueOf20, "haraka-128s-robust", harakaSEngineProvider3);
        haraka_128s = sPHINCSPlusParameters46;
        Integer valueOf21 = Integers.valueOf(196867);
        SPHINCSPlusParameters sPHINCSPlusParameters47 = sPHINCSPlusParameters46;
        HarakaSEngineProvider harakaSEngineProvider5 = r8;
        HarakaSEngineProvider harakaSEngineProvider6 = new HarakaSEngineProvider(true, 24, 16, 22, 8, 33, 66);
        SPHINCSPlusParameters sPHINCSPlusParameters48 = new SPHINCSPlusParameters(valueOf21, "haraka-192f-robust", harakaSEngineProvider5);
        haraka_192f = sPHINCSPlusParameters48;
        Integer valueOf22 = Integers.valueOf(196868);
        SPHINCSPlusParameters sPHINCSPlusParameters49 = sPHINCSPlusParameters48;
        HarakaSEngineProvider harakaSEngineProvider7 = r8;
        HarakaSEngineProvider harakaSEngineProvider8 = new HarakaSEngineProvider(true, 24, 16, 7, 14, 17, 63);
        SPHINCSPlusParameters sPHINCSPlusParameters50 = new SPHINCSPlusParameters(valueOf22, "haraka-192s-robust", harakaSEngineProvider7);
        haraka_192s = sPHINCSPlusParameters50;
        Integer valueOf23 = Integers.valueOf(196869);
        SPHINCSPlusParameters sPHINCSPlusParameters51 = sPHINCSPlusParameters50;
        HarakaSEngineProvider harakaSEngineProvider9 = r8;
        HarakaSEngineProvider harakaSEngineProvider10 = new HarakaSEngineProvider(true, 32, 16, 17, 9, 35, 68);
        SPHINCSPlusParameters sPHINCSPlusParameters52 = new SPHINCSPlusParameters(valueOf23, "haraka-256f-robust", harakaSEngineProvider9);
        haraka_256f = sPHINCSPlusParameters52;
        Integer valueOf24 = Integers.valueOf(196870);
        SPHINCSPlusParameters sPHINCSPlusParameters53 = sPHINCSPlusParameters52;
        HarakaSEngineProvider harakaSEngineProvider11 = r8;
        HarakaSEngineProvider harakaSEngineProvider12 = new HarakaSEngineProvider(true, 32, 16, 8, 14, 22, 64);
        SPHINCSPlusParameters sPHINCSPlusParameters54 = new SPHINCSPlusParameters(valueOf24, "haraka-256s-robust", harakaSEngineProvider11);
        haraka_256s = sPHINCSPlusParameters54;
        Integer valueOf25 = Integers.valueOf(197121);
        SPHINCSPlusParameters sPHINCSPlusParameters55 = sPHINCSPlusParameters54;
        HarakaSEngineProvider harakaSEngineProvider13 = r8;
        HarakaSEngineProvider harakaSEngineProvider14 = new HarakaSEngineProvider(false, 16, 16, 22, 6, 33, 66);
        SPHINCSPlusParameters sPHINCSPlusParameters56 = new SPHINCSPlusParameters(valueOf25, "haraka-128f-simple", harakaSEngineProvider13);
        haraka_128f_simple = sPHINCSPlusParameters56;
        Integer valueOf26 = Integers.valueOf(197122);
        SPHINCSPlusParameters sPHINCSPlusParameters57 = sPHINCSPlusParameters56;
        HarakaSEngineProvider harakaSEngineProvider15 = r8;
        HarakaSEngineProvider harakaSEngineProvider16 = new HarakaSEngineProvider(false, 16, 16, 7, 12, 14, 63);
        SPHINCSPlusParameters sPHINCSPlusParameters58 = new SPHINCSPlusParameters(valueOf26, "haraka-128s-simple", harakaSEngineProvider15);
        haraka_128s_simple = sPHINCSPlusParameters58;
        Integer valueOf27 = Integers.valueOf(197123);
        SPHINCSPlusParameters sPHINCSPlusParameters59 = sPHINCSPlusParameters58;
        HarakaSEngineProvider harakaSEngineProvider17 = r8;
        HarakaSEngineProvider harakaSEngineProvider18 = new HarakaSEngineProvider(false, 24, 16, 22, 8, 33, 66);
        SPHINCSPlusParameters sPHINCSPlusParameters60 = new SPHINCSPlusParameters(valueOf27, "haraka-192f-simple", harakaSEngineProvider17);
        haraka_192f_simple = sPHINCSPlusParameters60;
        Integer valueOf28 = Integers.valueOf(197124);
        SPHINCSPlusParameters sPHINCSPlusParameters61 = sPHINCSPlusParameters60;
        HarakaSEngineProvider harakaSEngineProvider19 = r8;
        HarakaSEngineProvider harakaSEngineProvider20 = new HarakaSEngineProvider(false, 24, 16, 7, 14, 17, 63);
        SPHINCSPlusParameters sPHINCSPlusParameters62 = new SPHINCSPlusParameters(valueOf28, "haraka-192s-simple", harakaSEngineProvider19);
        haraka_192s_simple = sPHINCSPlusParameters62;
        Integer valueOf29 = Integers.valueOf(197125);
        SPHINCSPlusParameters sPHINCSPlusParameters63 = sPHINCSPlusParameters62;
        HarakaSEngineProvider harakaSEngineProvider21 = r8;
        HarakaSEngineProvider harakaSEngineProvider22 = new HarakaSEngineProvider(false, 32, 16, 17, 9, 35, 68);
        SPHINCSPlusParameters sPHINCSPlusParameters64 = new SPHINCSPlusParameters(valueOf29, "haraka-256f-simple", harakaSEngineProvider21);
        haraka_256f_simple = sPHINCSPlusParameters64;
        Integer valueOf30 = Integers.valueOf(197126);
        SPHINCSPlusParameters sPHINCSPlusParameters65 = sPHINCSPlusParameters64;
        HarakaSEngineProvider harakaSEngineProvider23 = r8;
        HarakaSEngineProvider harakaSEngineProvider24 = new HarakaSEngineProvider(false, 32, 16, 8, 14, 22, 64);
        SPHINCSPlusParameters sPHINCSPlusParameters66 = new SPHINCSPlusParameters(valueOf30, "haraka-256s-simple", harakaSEngineProvider23);
        haraka_256s_simple = sPHINCSPlusParameters66;
        SPHINCSPlusParameters[] sPHINCSPlusParametersArr = {sPHINCSPlusParameters, sPHINCSPlusParameters2, sPHINCSPlusParameters3, sPHINCSPlusParameters4, sPHINCSPlusParameters5, sPHINCSPlusParameters7, sPHINCSPlusParameters9, sPHINCSPlusParameters11, sPHINCSPlusParameters13, sPHINCSPlusParameters15, sPHINCSPlusParameters17, sPHINCSPlusParameters19, sPHINCSPlusParameters21, sPHINCSPlusParameters23, sPHINCSPlusParameters25, sPHINCSPlusParameters27, sPHINCSPlusParameters29, sPHINCSPlusParameters31, sPHINCSPlusParameters33, sPHINCSPlusParameters35, sPHINCSPlusParameters37, sPHINCSPlusParameters39, sPHINCSPlusParameters41, sPHINCSPlusParameters43, sPHINCSPlusParameters45, sPHINCSPlusParameters47, sPHINCSPlusParameters49, sPHINCSPlusParameters51, sPHINCSPlusParameters53, sPHINCSPlusParameters55, sPHINCSPlusParameters57, sPHINCSPlusParameters59, sPHINCSPlusParameters61, sPHINCSPlusParameters63, sPHINCSPlusParameters65, sPHINCSPlusParameters66};
        for (int i = 0; i < 36; i++) {
            SPHINCSPlusParameters sPHINCSPlusParameters67 = sPHINCSPlusParametersArr[i];
            ID_TO_PARAMS.put(sPHINCSPlusParameters67.getID(), sPHINCSPlusParameters67);
        }
    }

    private SPHINCSPlusParameters(Integer num, String str, SPHINCSPlusEngineProvider sPHINCSPlusEngineProvider) {
        this.id = num;
        this.name = str;
        this.engineProvider = sPHINCSPlusEngineProvider;
    }

    public static Integer getID(SPHINCSPlusParameters sPHINCSPlusParameters) {
        return sPHINCSPlusParameters.getID();
    }

    public static SPHINCSPlusParameters getParams(Integer num) {
        return ID_TO_PARAMS.get(num);
    }

    public byte[] getEncoded() {
        return Pack.intToBigEndian(getID().intValue());
    }

    /* access modifiers changed from: package-private */
    public SPHINCSPlusEngine getEngine() {
        return this.engineProvider.get();
    }

    public Integer getID() {
        return this.id;
    }

    /* access modifiers changed from: package-private */
    public int getN() {
        return this.engineProvider.getN();
    }

    public String getName() {
        return this.name;
    }
}
