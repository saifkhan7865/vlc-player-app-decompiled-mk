package com.typesafe.config.impl;

final class DefaultTransformer {
    DefaultTransformer() {
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:30|31|32) */
    /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
        r1 = java.lang.Double.parseDouble(r0);
        r6 = java.lang.Double.valueOf(r1);
        r4 = r5.origin();
        r6.getClass();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00a2, code lost:
        return new com.typesafe.config.impl.ConfigDouble(r4, r1, r0);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:30:0x008e */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static com.typesafe.config.impl.AbstractConfigValue transform(com.typesafe.config.impl.AbstractConfigValue r5, com.typesafe.config.ConfigValueType r6) {
        /*
            com.typesafe.config.ConfigValueType r0 = r5.valueType()
            com.typesafe.config.ConfigValueType r1 = com.typesafe.config.ConfigValueType.STRING
            r2 = 3
            r3 = 1
            if (r0 != r1) goto L_0x00a3
            java.lang.Object r0 = r5.unwrapped()
            java.lang.String r0 = (java.lang.String) r0
            int[] r1 = com.typesafe.config.impl.DefaultTransformer.AnonymousClass2.$SwitchMap$com$typesafe$config$ConfigValueType
            int r6 = r6.ordinal()
            r6 = r1[r6]
            if (r6 == r3) goto L_0x0079
            r1 = 2
            if (r6 == r1) goto L_0x0067
            if (r6 == r2) goto L_0x0021
            goto L_0x0144
        L_0x0021:
            java.lang.String r6 = "true"
            boolean r6 = r0.equals(r6)
            if (r6 != 0) goto L_0x005d
            java.lang.String r6 = "yes"
            boolean r6 = r0.equals(r6)
            if (r6 != 0) goto L_0x005d
            java.lang.String r6 = "on"
            boolean r6 = r0.equals(r6)
            if (r6 == 0) goto L_0x003a
            goto L_0x005d
        L_0x003a:
            java.lang.String r6 = "false"
            boolean r6 = r0.equals(r6)
            if (r6 != 0) goto L_0x0052
            java.lang.String r6 = "no"
            boolean r6 = r0.equals(r6)
            if (r6 != 0) goto L_0x0052
            java.lang.String r6 = "off"
            boolean r6 = r0.equals(r6)
            if (r6 == 0) goto L_0x0144
        L_0x0052:
            com.typesafe.config.impl.ConfigBoolean r6 = new com.typesafe.config.impl.ConfigBoolean
            com.typesafe.config.impl.SimpleConfigOrigin r5 = r5.origin()
            r0 = 0
            r6.<init>(r5, r0)
            return r6
        L_0x005d:
            com.typesafe.config.impl.ConfigBoolean r6 = new com.typesafe.config.impl.ConfigBoolean
            com.typesafe.config.impl.SimpleConfigOrigin r5 = r5.origin()
            r6.<init>(r5, r3)
            return r6
        L_0x0067:
            java.lang.String r6 = "null"
            boolean r6 = r0.equals(r6)
            if (r6 == 0) goto L_0x0144
            com.typesafe.config.impl.ConfigNull r6 = new com.typesafe.config.impl.ConfigNull
            com.typesafe.config.impl.SimpleConfigOrigin r5 = r5.origin()
            r6.<init>(r5)
            return r6
        L_0x0079:
            long r1 = java.lang.Long.parseLong(r0)     // Catch:{ NumberFormatException -> 0x008e }
            java.lang.Long r6 = java.lang.Long.valueOf(r1)     // Catch:{ NumberFormatException -> 0x008e }
            com.typesafe.config.impl.ConfigLong r3 = new com.typesafe.config.impl.ConfigLong     // Catch:{ NumberFormatException -> 0x008e }
            com.typesafe.config.impl.SimpleConfigOrigin r4 = r5.origin()     // Catch:{ NumberFormatException -> 0x008e }
            r6.getClass()     // Catch:{ NumberFormatException -> 0x008e }
            r3.<init>(r4, r1, r0)     // Catch:{ NumberFormatException -> 0x008e }
            return r3
        L_0x008e:
            double r1 = java.lang.Double.parseDouble(r0)     // Catch:{ NumberFormatException -> 0x0144 }
            java.lang.Double r6 = java.lang.Double.valueOf(r1)     // Catch:{ NumberFormatException -> 0x0144 }
            com.typesafe.config.impl.ConfigDouble r3 = new com.typesafe.config.impl.ConfigDouble     // Catch:{ NumberFormatException -> 0x0144 }
            com.typesafe.config.impl.SimpleConfigOrigin r4 = r5.origin()     // Catch:{ NumberFormatException -> 0x0144 }
            r6.getClass()     // Catch:{ NumberFormatException -> 0x0144 }
            r3.<init>(r4, r1, r0)     // Catch:{ NumberFormatException -> 0x0144 }
            return r3
        L_0x00a3:
            com.typesafe.config.ConfigValueType r0 = com.typesafe.config.ConfigValueType.STRING
            if (r6 != r0) goto L_0x00c7
            int[] r6 = com.typesafe.config.impl.DefaultTransformer.AnonymousClass2.$SwitchMap$com$typesafe$config$ConfigValueType
            com.typesafe.config.ConfigValueType r0 = r5.valueType()
            int r0 = r0.ordinal()
            r6 = r6[r0]
            if (r6 == r3) goto L_0x00b9
            if (r6 == r2) goto L_0x00b9
            goto L_0x0144
        L_0x00b9:
            com.typesafe.config.impl.ConfigString$Quoted r6 = new com.typesafe.config.impl.ConfigString$Quoted
            com.typesafe.config.impl.SimpleConfigOrigin r0 = r5.origin()
            java.lang.String r5 = r5.transformToString()
            r6.<init>(r0, r5)
            return r6
        L_0x00c7:
            com.typesafe.config.ConfigValueType r0 = com.typesafe.config.ConfigValueType.LIST
            if (r6 != r0) goto L_0x0144
            com.typesafe.config.ConfigValueType r6 = r5.valueType()
            com.typesafe.config.ConfigValueType r0 = com.typesafe.config.ConfigValueType.OBJECT
            if (r6 != r0) goto L_0x0144
            r6 = r5
            com.typesafe.config.impl.AbstractConfigObject r6 = (com.typesafe.config.impl.AbstractConfigObject) r6
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            java.util.Set r1 = r6.keySet()
            java.util.Iterator r1 = r1.iterator()
        L_0x00e3:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0106
            java.lang.Object r2 = r1.next()
            java.lang.String r2 = (java.lang.String) r2
            r3 = 10
            int r3 = java.lang.Integer.parseInt(r2, r3)     // Catch:{ NumberFormatException -> 0x0104 }
            if (r3 >= 0) goto L_0x00f8
            goto L_0x00e3
        L_0x00f8:
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ NumberFormatException -> 0x0104 }
            com.typesafe.config.impl.AbstractConfigValue r2 = r6.get((java.lang.Object) r2)     // Catch:{ NumberFormatException -> 0x0104 }
            r0.put(r3, r2)     // Catch:{ NumberFormatException -> 0x0104 }
            goto L_0x00e3
        L_0x0104:
            goto L_0x00e3
        L_0x0106:
            boolean r6 = r0.isEmpty()
            if (r6 != 0) goto L_0x0144
            java.util.ArrayList r6 = new java.util.ArrayList
            java.util.Set r0 = r0.entrySet()
            r6.<init>(r0)
            com.typesafe.config.impl.DefaultTransformer$1 r0 = new com.typesafe.config.impl.DefaultTransformer$1
            r0.<init>()
            java.util.Collections.sort(r6, r0)
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.Iterator r6 = r6.iterator()
        L_0x0126:
            boolean r1 = r6.hasNext()
            if (r1 == 0) goto L_0x013a
            java.lang.Object r1 = r6.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            java.lang.Object r1 = r1.getValue()
            r0.add(r1)
            goto L_0x0126
        L_0x013a:
            com.typesafe.config.impl.SimpleConfigList r6 = new com.typesafe.config.impl.SimpleConfigList
            com.typesafe.config.impl.SimpleConfigOrigin r5 = r5.origin()
            r6.<init>(r5, r0)
            return r6
        L_0x0144:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.typesafe.config.impl.DefaultTransformer.transform(com.typesafe.config.impl.AbstractConfigValue, com.typesafe.config.ConfigValueType):com.typesafe.config.impl.AbstractConfigValue");
    }

    /* renamed from: com.typesafe.config.impl.DefaultTransformer$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$typesafe$config$ConfigValueType;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|14) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.typesafe.config.ConfigValueType[] r0 = com.typesafe.config.ConfigValueType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$typesafe$config$ConfigValueType = r0
                com.typesafe.config.ConfigValueType r1 = com.typesafe.config.ConfigValueType.NUMBER     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$typesafe$config$ConfigValueType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.typesafe.config.ConfigValueType r1 = com.typesafe.config.ConfigValueType.NULL     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$typesafe$config$ConfigValueType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.typesafe.config.ConfigValueType r1 = com.typesafe.config.ConfigValueType.BOOLEAN     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$typesafe$config$ConfigValueType     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.typesafe.config.ConfigValueType r1 = com.typesafe.config.ConfigValueType.LIST     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$typesafe$config$ConfigValueType     // Catch:{ NoSuchFieldError -> 0x003e }
                com.typesafe.config.ConfigValueType r1 = com.typesafe.config.ConfigValueType.OBJECT     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$typesafe$config$ConfigValueType     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.typesafe.config.ConfigValueType r1 = com.typesafe.config.ConfigValueType.STRING     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.typesafe.config.impl.DefaultTransformer.AnonymousClass2.<clinit>():void");
        }
    }
}
