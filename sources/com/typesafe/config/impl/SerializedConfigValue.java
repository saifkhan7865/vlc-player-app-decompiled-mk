package com.typesafe.config.impl;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigException;
import com.typesafe.config.ConfigList;
import com.typesafe.config.ConfigObject;
import com.typesafe.config.ConfigOrigin;
import com.typesafe.config.ConfigValue;
import com.typesafe.config.ConfigValueType;
import com.typesafe.config.impl.ConfigString;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.Externalizable;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class SerializedConfigValue extends AbstractConfigValue implements Externalizable {
    private static final long serialVersionUID = 1;
    private ConfigValue value;
    private boolean wasConfig;

    enum SerializedField {
        UNKNOWN,
        END_MARKER,
        ROOT_VALUE,
        ROOT_WAS_CONFIG,
        VALUE_DATA,
        VALUE_ORIGIN,
        ORIGIN_DESCRIPTION,
        ORIGIN_LINE_NUMBER,
        ORIGIN_END_LINE_NUMBER,
        ORIGIN_TYPE,
        ORIGIN_URL,
        ORIGIN_COMMENTS,
        ORIGIN_NULL_URL,
        ORIGIN_NULL_COMMENTS,
        ORIGIN_RESOURCE,
        ORIGIN_NULL_RESOURCE;

        static SerializedField forInt(int i) {
            if (i < values().length) {
                return values()[i];
            }
            return UNKNOWN;
        }
    }

    private enum SerializedValueType {
        NULL(ConfigValueType.NULL),
        BOOLEAN(ConfigValueType.BOOLEAN),
        INT(ConfigValueType.NUMBER),
        LONG(ConfigValueType.NUMBER),
        DOUBLE(ConfigValueType.NUMBER),
        STRING(ConfigValueType.STRING),
        LIST(ConfigValueType.LIST),
        OBJECT(ConfigValueType.OBJECT);
        
        ConfigValueType configType;

        private SerializedValueType(ConfigValueType configValueType) {
            this.configType = configValueType;
        }

        static SerializedValueType forInt(int i) {
            if (i < values().length) {
                return values()[i];
            }
            return null;
        }

        static SerializedValueType forValue(ConfigValue configValue) {
            ConfigValueType valueType = configValue.valueType();
            if (valueType != ConfigValueType.NUMBER) {
                for (SerializedValueType serializedValueType : values()) {
                    if (serializedValueType.configType == valueType) {
                        return serializedValueType;
                    }
                }
            } else if (configValue instanceof ConfigInt) {
                return INT;
            } else {
                if (configValue instanceof ConfigLong) {
                    return LONG;
                }
                if (configValue instanceof ConfigDouble) {
                    return DOUBLE;
                }
            }
            throw new ConfigException.BugOrBroken("don't know how to serialize " + configValue);
        }
    }

    public SerializedConfigValue() {
        super((ConfigOrigin) null);
    }

    SerializedConfigValue(ConfigValue configValue) {
        this();
        this.value = configValue;
        this.wasConfig = false;
    }

    SerializedConfigValue(Config config) {
        this((ConfigValue) config.root());
        this.wasConfig = true;
    }

    private Object readResolve() throws ObjectStreamException {
        if (this.wasConfig) {
            return ((ConfigObject) this.value).toConfig();
        }
        return this.value;
    }

    private static class FieldOut {
        final ByteArrayOutputStream bytes;
        final SerializedField code;
        final DataOutput data;

        FieldOut(SerializedField serializedField) {
            this.code = serializedField;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            this.bytes = byteArrayOutputStream;
            this.data = new DataOutputStream(byteArrayOutputStream);
        }
    }

    private static void writeOriginField(DataOutput dataOutput, SerializedField serializedField, Object obj) throws IOException {
        switch (AnonymousClass1.$SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedField[serializedField.ordinal()]) {
            case 1:
                dataOutput.writeUTF((String) obj);
                return;
            case 2:
                dataOutput.writeInt(((Integer) obj).intValue());
                return;
            case 3:
                dataOutput.writeInt(((Integer) obj).intValue());
                return;
            case 4:
                dataOutput.writeByte(((Integer) obj).intValue());
                return;
            case 5:
                dataOutput.writeUTF((String) obj);
                return;
            case 6:
                dataOutput.writeUTF((String) obj);
                return;
            case 7:
                List<String> list = (List) obj;
                dataOutput.writeInt(list.size());
                for (String writeUTF : list) {
                    dataOutput.writeUTF(writeUTF);
                }
                return;
            case 8:
            case 9:
            case 10:
                return;
            default:
                throw new IOException("Unhandled field from origin: " + serializedField);
        }
    }

    static void writeOrigin(DataOutput dataOutput, SimpleConfigOrigin simpleConfigOrigin, SimpleConfigOrigin simpleConfigOrigin2) throws IOException {
        Map<SerializedField, Object> map;
        if (simpleConfigOrigin != null) {
            map = simpleConfigOrigin.toFieldsDelta(simpleConfigOrigin2);
        } else {
            map = Collections.emptyMap();
        }
        for (Map.Entry next : map.entrySet()) {
            FieldOut fieldOut = new FieldOut((SerializedField) next.getKey());
            writeOriginField(fieldOut.data, fieldOut.code, next.getValue());
            writeField(dataOutput, fieldOut);
        }
        writeEndMarker(dataOutput);
    }

    static SimpleConfigOrigin readOrigin(DataInput dataInput, SimpleConfigOrigin simpleConfigOrigin) throws IOException {
        Object obj;
        EnumMap enumMap = new EnumMap(SerializedField.class);
        while (true) {
            SerializedField readCode = readCode(dataInput);
            switch (AnonymousClass1.$SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedField[readCode.ordinal()]) {
                case 1:
                    dataInput.readInt();
                    obj = dataInput.readUTF();
                    break;
                case 2:
                    dataInput.readInt();
                    obj = Integer.valueOf(dataInput.readInt());
                    break;
                case 3:
                    dataInput.readInt();
                    obj = Integer.valueOf(dataInput.readInt());
                    break;
                case 4:
                    dataInput.readInt();
                    obj = Integer.valueOf(dataInput.readUnsignedByte());
                    break;
                case 5:
                    dataInput.readInt();
                    obj = dataInput.readUTF();
                    break;
                case 6:
                    dataInput.readInt();
                    obj = dataInput.readUTF();
                    break;
                case 7:
                    dataInput.readInt();
                    int readInt = dataInput.readInt();
                    ArrayList arrayList = new ArrayList(readInt);
                    for (int i = 0; i < readInt; i++) {
                        arrayList.add(dataInput.readUTF());
                    }
                    obj = arrayList;
                    break;
                case 8:
                case 9:
                case 10:
                    dataInput.readInt();
                    obj = "";
                    break;
                case 11:
                    return SimpleConfigOrigin.fromBase(simpleConfigOrigin, enumMap);
                case 12:
                case 13:
                case 14:
                case 15:
                    throw new IOException("Not expecting this field here: " + readCode);
                case 16:
                    skipField(dataInput);
                    break;
            }
            obj = null;
            if (obj != null) {
                enumMap.put(readCode, obj);
            }
        }
    }

    private static void writeValueData(DataOutput dataOutput, ConfigValue configValue) throws IOException {
        SerializedValueType forValue = SerializedValueType.forValue(configValue);
        dataOutput.writeByte(forValue.ordinal());
        switch (AnonymousClass1.$SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedValueType[forValue.ordinal()]) {
            case 1:
                dataOutput.writeBoolean(((ConfigBoolean) configValue).unwrapped().booleanValue());
                return;
            case 3:
                dataOutput.writeInt(((ConfigInt) configValue).unwrapped().intValue());
                dataOutput.writeUTF(((ConfigNumber) configValue).transformToString());
                return;
            case 4:
                dataOutput.writeLong(((ConfigLong) configValue).unwrapped().longValue());
                dataOutput.writeUTF(((ConfigNumber) configValue).transformToString());
                return;
            case 5:
                dataOutput.writeDouble(((ConfigDouble) configValue).unwrapped().doubleValue());
                dataOutput.writeUTF(((ConfigNumber) configValue).transformToString());
                return;
            case 6:
                dataOutput.writeUTF(((ConfigString) configValue).unwrapped());
                return;
            case 7:
                ConfigList<ConfigValue> configList = (ConfigList) configValue;
                dataOutput.writeInt(configList.size());
                for (ConfigValue writeValue : configList) {
                    writeValue(dataOutput, writeValue, (SimpleConfigOrigin) configList.origin());
                }
                return;
            case 8:
                ConfigObject configObject = (ConfigObject) configValue;
                dataOutput.writeInt(configObject.size());
                for (Map.Entry entry : configObject.entrySet()) {
                    dataOutput.writeUTF((String) entry.getKey());
                    writeValue(dataOutput, (ConfigValue) entry.getValue(), (SimpleConfigOrigin) configObject.origin());
                }
                return;
            default:
                return;
        }
    }

    /* renamed from: com.typesafe.config.impl.SerializedConfigValue$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedField;
        static final /* synthetic */ int[] $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedValueType;

        /* JADX WARNING: Can't wrap try/catch for region: R(49:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|(2:17|18)|19|21|22|23|(2:25|26)|27|(2:29|30)|31|33|34|35|36|37|38|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|(3:63|64|66)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(50:0|(2:1|2)|3|(2:5|6)|7|9|10|11|(2:13|14)|15|(2:17|18)|19|21|22|23|(2:25|26)|27|(2:29|30)|31|33|34|35|36|37|38|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|(3:63|64|66)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(53:0|(2:1|2)|3|5|6|7|9|10|11|(2:13|14)|15|17|18|19|21|22|23|(2:25|26)|27|29|30|31|33|34|35|36|37|38|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|(3:63|64|66)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(58:0|1|2|3|5|6|7|9|10|11|13|14|15|17|18|19|21|22|23|25|26|27|29|30|31|33|34|35|36|37|38|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|66) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x0071 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:37:0x007b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:39:0x0085 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:41:0x008f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:43:0x0099 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:45:0x00a3 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:47:0x00ad */
        /* JADX WARNING: Missing exception handler attribute for start block: B:49:0x00b7 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:51:0x00c3 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:53:0x00cf */
        /* JADX WARNING: Missing exception handler attribute for start block: B:55:0x00db */
        /* JADX WARNING: Missing exception handler attribute for start block: B:57:0x00e7 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:59:0x00f3 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:61:0x00ff */
        /* JADX WARNING: Missing exception handler attribute for start block: B:63:0x010b */
        static {
            /*
                com.typesafe.config.impl.SerializedConfigValue$SerializedValueType[] r0 = com.typesafe.config.impl.SerializedConfigValue.SerializedValueType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedValueType = r0
                r1 = 1
                com.typesafe.config.impl.SerializedConfigValue$SerializedValueType r2 = com.typesafe.config.impl.SerializedConfigValue.SerializedValueType.BOOLEAN     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedValueType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.typesafe.config.impl.SerializedConfigValue$SerializedValueType r3 = com.typesafe.config.impl.SerializedConfigValue.SerializedValueType.NULL     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                r2 = 3
                int[] r3 = $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedValueType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.typesafe.config.impl.SerializedConfigValue$SerializedValueType r4 = com.typesafe.config.impl.SerializedConfigValue.SerializedValueType.INT     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                r3 = 4
                int[] r4 = $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedValueType     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.typesafe.config.impl.SerializedConfigValue$SerializedValueType r5 = com.typesafe.config.impl.SerializedConfigValue.SerializedValueType.LONG     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r4[r5] = r3     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                r4 = 5
                int[] r5 = $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedValueType     // Catch:{ NoSuchFieldError -> 0x003e }
                com.typesafe.config.impl.SerializedConfigValue$SerializedValueType r6 = com.typesafe.config.impl.SerializedConfigValue.SerializedValueType.DOUBLE     // Catch:{ NoSuchFieldError -> 0x003e }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r5[r6] = r4     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                r5 = 6
                int[] r6 = $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedValueType     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.typesafe.config.impl.SerializedConfigValue$SerializedValueType r7 = com.typesafe.config.impl.SerializedConfigValue.SerializedValueType.STRING     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r6[r7] = r5     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                r6 = 7
                int[] r7 = $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedValueType     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.typesafe.config.impl.SerializedConfigValue$SerializedValueType r8 = com.typesafe.config.impl.SerializedConfigValue.SerializedValueType.LIST     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r8 = r8.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r7[r8] = r6     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                r7 = 8
                int[] r8 = $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedValueType     // Catch:{ NoSuchFieldError -> 0x0060 }
                com.typesafe.config.impl.SerializedConfigValue$SerializedValueType r9 = com.typesafe.config.impl.SerializedConfigValue.SerializedValueType.OBJECT     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r9 = r9.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r8[r9] = r7     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                com.typesafe.config.impl.SerializedConfigValue$SerializedField[] r8 = com.typesafe.config.impl.SerializedConfigValue.SerializedField.values()
                int r8 = r8.length
                int[] r8 = new int[r8]
                $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedField = r8
                com.typesafe.config.impl.SerializedConfigValue$SerializedField r9 = com.typesafe.config.impl.SerializedConfigValue.SerializedField.ORIGIN_DESCRIPTION     // Catch:{ NoSuchFieldError -> 0x0071 }
                int r9 = r9.ordinal()     // Catch:{ NoSuchFieldError -> 0x0071 }
                r8[r9] = r1     // Catch:{ NoSuchFieldError -> 0x0071 }
            L_0x0071:
                int[] r1 = $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedField     // Catch:{ NoSuchFieldError -> 0x007b }
                com.typesafe.config.impl.SerializedConfigValue$SerializedField r8 = com.typesafe.config.impl.SerializedConfigValue.SerializedField.ORIGIN_LINE_NUMBER     // Catch:{ NoSuchFieldError -> 0x007b }
                int r8 = r8.ordinal()     // Catch:{ NoSuchFieldError -> 0x007b }
                r1[r8] = r0     // Catch:{ NoSuchFieldError -> 0x007b }
            L_0x007b:
                int[] r0 = $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedField     // Catch:{ NoSuchFieldError -> 0x0085 }
                com.typesafe.config.impl.SerializedConfigValue$SerializedField r1 = com.typesafe.config.impl.SerializedConfigValue.SerializedField.ORIGIN_END_LINE_NUMBER     // Catch:{ NoSuchFieldError -> 0x0085 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0085 }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0085 }
            L_0x0085:
                int[] r0 = $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedField     // Catch:{ NoSuchFieldError -> 0x008f }
                com.typesafe.config.impl.SerializedConfigValue$SerializedField r1 = com.typesafe.config.impl.SerializedConfigValue.SerializedField.ORIGIN_TYPE     // Catch:{ NoSuchFieldError -> 0x008f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x008f }
                r0[r1] = r3     // Catch:{ NoSuchFieldError -> 0x008f }
            L_0x008f:
                int[] r0 = $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedField     // Catch:{ NoSuchFieldError -> 0x0099 }
                com.typesafe.config.impl.SerializedConfigValue$SerializedField r1 = com.typesafe.config.impl.SerializedConfigValue.SerializedField.ORIGIN_URL     // Catch:{ NoSuchFieldError -> 0x0099 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0099 }
                r0[r1] = r4     // Catch:{ NoSuchFieldError -> 0x0099 }
            L_0x0099:
                int[] r0 = $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedField     // Catch:{ NoSuchFieldError -> 0x00a3 }
                com.typesafe.config.impl.SerializedConfigValue$SerializedField r1 = com.typesafe.config.impl.SerializedConfigValue.SerializedField.ORIGIN_RESOURCE     // Catch:{ NoSuchFieldError -> 0x00a3 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00a3 }
                r0[r1] = r5     // Catch:{ NoSuchFieldError -> 0x00a3 }
            L_0x00a3:
                int[] r0 = $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedField     // Catch:{ NoSuchFieldError -> 0x00ad }
                com.typesafe.config.impl.SerializedConfigValue$SerializedField r1 = com.typesafe.config.impl.SerializedConfigValue.SerializedField.ORIGIN_COMMENTS     // Catch:{ NoSuchFieldError -> 0x00ad }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00ad }
                r0[r1] = r6     // Catch:{ NoSuchFieldError -> 0x00ad }
            L_0x00ad:
                int[] r0 = $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedField     // Catch:{ NoSuchFieldError -> 0x00b7 }
                com.typesafe.config.impl.SerializedConfigValue$SerializedField r1 = com.typesafe.config.impl.SerializedConfigValue.SerializedField.ORIGIN_NULL_URL     // Catch:{ NoSuchFieldError -> 0x00b7 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00b7 }
                r0[r1] = r7     // Catch:{ NoSuchFieldError -> 0x00b7 }
            L_0x00b7:
                int[] r0 = $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedField     // Catch:{ NoSuchFieldError -> 0x00c3 }
                com.typesafe.config.impl.SerializedConfigValue$SerializedField r1 = com.typesafe.config.impl.SerializedConfigValue.SerializedField.ORIGIN_NULL_RESOURCE     // Catch:{ NoSuchFieldError -> 0x00c3 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00c3 }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00c3 }
            L_0x00c3:
                int[] r0 = $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedField     // Catch:{ NoSuchFieldError -> 0x00cf }
                com.typesafe.config.impl.SerializedConfigValue$SerializedField r1 = com.typesafe.config.impl.SerializedConfigValue.SerializedField.ORIGIN_NULL_COMMENTS     // Catch:{ NoSuchFieldError -> 0x00cf }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00cf }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00cf }
            L_0x00cf:
                int[] r0 = $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedField     // Catch:{ NoSuchFieldError -> 0x00db }
                com.typesafe.config.impl.SerializedConfigValue$SerializedField r1 = com.typesafe.config.impl.SerializedConfigValue.SerializedField.END_MARKER     // Catch:{ NoSuchFieldError -> 0x00db }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00db }
                r2 = 11
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00db }
            L_0x00db:
                int[] r0 = $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedField     // Catch:{ NoSuchFieldError -> 0x00e7 }
                com.typesafe.config.impl.SerializedConfigValue$SerializedField r1 = com.typesafe.config.impl.SerializedConfigValue.SerializedField.ROOT_VALUE     // Catch:{ NoSuchFieldError -> 0x00e7 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00e7 }
                r2 = 12
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00e7 }
            L_0x00e7:
                int[] r0 = $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedField     // Catch:{ NoSuchFieldError -> 0x00f3 }
                com.typesafe.config.impl.SerializedConfigValue$SerializedField r1 = com.typesafe.config.impl.SerializedConfigValue.SerializedField.ROOT_WAS_CONFIG     // Catch:{ NoSuchFieldError -> 0x00f3 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00f3 }
                r2 = 13
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00f3 }
            L_0x00f3:
                int[] r0 = $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedField     // Catch:{ NoSuchFieldError -> 0x00ff }
                com.typesafe.config.impl.SerializedConfigValue$SerializedField r1 = com.typesafe.config.impl.SerializedConfigValue.SerializedField.VALUE_DATA     // Catch:{ NoSuchFieldError -> 0x00ff }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00ff }
                r2 = 14
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00ff }
            L_0x00ff:
                int[] r0 = $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedField     // Catch:{ NoSuchFieldError -> 0x010b }
                com.typesafe.config.impl.SerializedConfigValue$SerializedField r1 = com.typesafe.config.impl.SerializedConfigValue.SerializedField.VALUE_ORIGIN     // Catch:{ NoSuchFieldError -> 0x010b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x010b }
                r2 = 15
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x010b }
            L_0x010b:
                int[] r0 = $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedField     // Catch:{ NoSuchFieldError -> 0x0117 }
                com.typesafe.config.impl.SerializedConfigValue$SerializedField r1 = com.typesafe.config.impl.SerializedConfigValue.SerializedField.UNKNOWN     // Catch:{ NoSuchFieldError -> 0x0117 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0117 }
                r2 = 16
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0117 }
            L_0x0117:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.typesafe.config.impl.SerializedConfigValue.AnonymousClass1.<clinit>():void");
        }
    }

    private static AbstractConfigValue readValueData(DataInput dataInput, SimpleConfigOrigin simpleConfigOrigin) throws IOException {
        int readUnsignedByte = dataInput.readUnsignedByte();
        SerializedValueType forInt = SerializedValueType.forInt(readUnsignedByte);
        if (forInt != null) {
            int i = 0;
            switch (AnonymousClass1.$SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedValueType[forInt.ordinal()]) {
                case 1:
                    return new ConfigBoolean(simpleConfigOrigin, dataInput.readBoolean());
                case 2:
                    return new ConfigNull(simpleConfigOrigin);
                case 3:
                    return new ConfigInt(simpleConfigOrigin, dataInput.readInt(), dataInput.readUTF());
                case 4:
                    return new ConfigLong(simpleConfigOrigin, dataInput.readLong(), dataInput.readUTF());
                case 5:
                    return new ConfigDouble(simpleConfigOrigin, dataInput.readDouble(), dataInput.readUTF());
                case 6:
                    return new ConfigString.Quoted(simpleConfigOrigin, dataInput.readUTF());
                case 7:
                    int readInt = dataInput.readInt();
                    ArrayList arrayList = new ArrayList(readInt);
                    while (i < readInt) {
                        arrayList.add(readValue(dataInput, simpleConfigOrigin));
                        i++;
                    }
                    return new SimpleConfigList(simpleConfigOrigin, arrayList);
                case 8:
                    int readInt2 = dataInput.readInt();
                    HashMap hashMap = new HashMap(readInt2);
                    while (i < readInt2) {
                        hashMap.put(dataInput.readUTF(), readValue(dataInput, simpleConfigOrigin));
                        i++;
                    }
                    return new SimpleConfigObject(simpleConfigOrigin, hashMap);
                default:
                    throw new IOException("Unhandled serialized value type: " + forInt);
            }
        } else {
            throw new IOException("Unknown serialized value type: " + readUnsignedByte);
        }
    }

    private static void writeValue(DataOutput dataOutput, ConfigValue configValue, SimpleConfigOrigin simpleConfigOrigin) throws IOException {
        FieldOut fieldOut = new FieldOut(SerializedField.VALUE_ORIGIN);
        writeOrigin(fieldOut.data, (SimpleConfigOrigin) configValue.origin(), simpleConfigOrigin);
        writeField(dataOutput, fieldOut);
        FieldOut fieldOut2 = new FieldOut(SerializedField.VALUE_DATA);
        writeValueData(fieldOut2.data, configValue);
        writeField(dataOutput, fieldOut2);
        writeEndMarker(dataOutput);
    }

    private static AbstractConfigValue readValue(DataInput dataInput, SimpleConfigOrigin simpleConfigOrigin) throws IOException {
        AbstractConfigValue abstractConfigValue = null;
        SimpleConfigOrigin simpleConfigOrigin2 = null;
        while (true) {
            SerializedField readCode = readCode(dataInput);
            if (readCode == SerializedField.END_MARKER) {
                if (abstractConfigValue != null) {
                    return abstractConfigValue;
                }
                throw new IOException("No value data found in serialization of value");
            } else if (readCode == SerializedField.VALUE_DATA) {
                if (simpleConfigOrigin2 != null) {
                    dataInput.readInt();
                    abstractConfigValue = readValueData(dataInput, simpleConfigOrigin2);
                } else {
                    throw new IOException("Origin must be stored before value data");
                }
            } else if (readCode == SerializedField.VALUE_ORIGIN) {
                dataInput.readInt();
                simpleConfigOrigin2 = readOrigin(dataInput, simpleConfigOrigin);
            } else {
                skipField(dataInput);
            }
        }
    }

    private static void writeField(DataOutput dataOutput, FieldOut fieldOut) throws IOException {
        byte[] byteArray = fieldOut.bytes.toByteArray();
        dataOutput.writeByte(fieldOut.code.ordinal());
        dataOutput.writeInt(byteArray.length);
        dataOutput.write(byteArray);
    }

    private static void writeEndMarker(DataOutput dataOutput) throws IOException {
        dataOutput.writeByte(SerializedField.END_MARKER.ordinal());
    }

    private static SerializedField readCode(DataInput dataInput) throws IOException {
        int readUnsignedByte = dataInput.readUnsignedByte();
        if (readUnsignedByte != SerializedField.UNKNOWN.ordinal()) {
            return SerializedField.forInt(readUnsignedByte);
        }
        throw new IOException("field code " + readUnsignedByte + " is not supposed to be on the wire");
    }

    private static void skipField(DataInput dataInput) throws IOException {
        int readInt = dataInput.readInt();
        int skipBytes = dataInput.skipBytes(readInt);
        if (skipBytes < readInt) {
            dataInput.readFully(new byte[(readInt - skipBytes)]);
        }
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        if (((AbstractConfigValue) this.value).resolveStatus() == ResolveStatus.RESOLVED) {
            FieldOut fieldOut = new FieldOut(SerializedField.ROOT_VALUE);
            writeValue(fieldOut.data, this.value, (SimpleConfigOrigin) null);
            writeField(objectOutput, fieldOut);
            FieldOut fieldOut2 = new FieldOut(SerializedField.ROOT_WAS_CONFIG);
            fieldOut2.data.writeBoolean(this.wasConfig);
            writeField(objectOutput, fieldOut2);
            writeEndMarker(objectOutput);
            return;
        }
        throw new NotSerializableException("tried to serialize a value with unresolved substitutions, need to Config#resolve() first, see API docs");
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        while (true) {
            SerializedField readCode = readCode(objectInput);
            if (readCode != SerializedField.END_MARKER) {
                DataInput fieldIn = fieldIn(objectInput);
                if (readCode == SerializedField.ROOT_VALUE) {
                    this.value = readValue(fieldIn, (SimpleConfigOrigin) null);
                } else if (readCode == SerializedField.ROOT_WAS_CONFIG) {
                    this.wasConfig = fieldIn.readBoolean();
                }
            } else {
                return;
            }
        }
    }

    private DataInput fieldIn(ObjectInput objectInput) throws IOException {
        byte[] bArr = new byte[objectInput.readInt()];
        objectInput.readFully(bArr);
        return new DataInputStream(new ByteArrayInputStream(bArr));
    }

    private static ConfigException shouldNotBeUsed() {
        return new ConfigException.BugOrBroken(SerializedConfigValue.class.getName() + " should not exist outside of serialization");
    }

    public ConfigValueType valueType() {
        throw shouldNotBeUsed();
    }

    public Object unwrapped() {
        throw shouldNotBeUsed();
    }

    /* access modifiers changed from: protected */
    public SerializedConfigValue newCopy(ConfigOrigin configOrigin) {
        throw shouldNotBeUsed();
    }

    public final String toString() {
        return getClass().getSimpleName() + "(value=" + this.value + ",wasConfig=" + this.wasConfig + ")";
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SerializedConfigValue) || !canEqual(obj)) {
            return false;
        }
        SerializedConfigValue serializedConfigValue = (SerializedConfigValue) obj;
        if (this.wasConfig != serializedConfigValue.wasConfig || !this.value.equals(serializedConfigValue.value)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((this.value.hashCode() + 41) * 41) + (this.wasConfig ? 1 : 0)) * 41;
    }
}
