package kotlinx.serialization.json.internal;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import kotlin.DeepRecursiveFunction;
import kotlin.DeepRecursiveKt;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.json.JsonArray;
import kotlinx.serialization.json.JsonConfiguration;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonLiteral;
import kotlinx.serialization.json.JsonNull;
import kotlinx.serialization.json.JsonObject;
import kotlinx.serialization.json.JsonPrimitive;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\u000b\u001a\u00020\fJ\b\u0010\r\u001a\u00020\fH\u0002J\b\u0010\u000e\u001a\u00020\fH\u0002J\b\u0010\u000f\u001a\u00020\fH\u0002J\u0017\u0010\u0010\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\f0\u0013H\bJ\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\bH\u0002J!\u0010\u000f\u001a\u00020\f*\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\f0\u0017H@ø\u0001\u0000¢\u0006\u0002\u0010\u0019R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u001a"}, d2 = {"Lkotlinx/serialization/json/internal/JsonTreeReader;", "", "configuration", "Lkotlinx/serialization/json/JsonConfiguration;", "lexer", "Lkotlinx/serialization/json/internal/AbstractJsonLexer;", "(Lkotlinx/serialization/json/JsonConfiguration;Lkotlinx/serialization/json/internal/AbstractJsonLexer;)V", "isLenient", "", "stackDepth", "", "read", "Lkotlinx/serialization/json/JsonElement;", "readArray", "readDeepRecursive", "readObject", "readObjectImpl", "Lkotlinx/serialization/json/JsonObject;", "reader", "Lkotlin/Function0;", "readValue", "Lkotlinx/serialization/json/JsonPrimitive;", "isString", "Lkotlin/DeepRecursiveScope;", "", "(Lkotlin/DeepRecursiveScope;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-serialization-json"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: JsonTreeReader.kt */
public final class JsonTreeReader {
    private final boolean isLenient;
    /* access modifiers changed from: private */
    public final AbstractJsonLexer lexer;
    private int stackDepth;

    public JsonTreeReader(JsonConfiguration jsonConfiguration, AbstractJsonLexer abstractJsonLexer) {
        Intrinsics.checkNotNullParameter(jsonConfiguration, "configuration");
        Intrinsics.checkNotNullParameter(abstractJsonLexer, "lexer");
        this.lexer = abstractJsonLexer;
        this.isLenient = jsonConfiguration.isLenient();
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0049  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x006d  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00b3  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00b5  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00c8  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00ce  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00d4  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object readObject(kotlin.DeepRecursiveScope<kotlin.Unit, kotlinx.serialization.json.JsonElement> r21, kotlin.coroutines.Continuation<? super kotlinx.serialization.json.JsonElement> r22) {
        /*
            r20 = this;
            r0 = r20
            r1 = r22
            boolean r2 = r1 instanceof kotlinx.serialization.json.internal.JsonTreeReader$readObject$2
            if (r2 == 0) goto L_0x0018
            r2 = r1
            kotlinx.serialization.json.internal.JsonTreeReader$readObject$2 r2 = (kotlinx.serialization.json.internal.JsonTreeReader$readObject$2) r2
            int r3 = r2.label
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r3 & r4
            if (r3 == 0) goto L_0x0018
            int r1 = r2.label
            int r1 = r1 - r4
            r2.label = r1
            goto L_0x001d
        L_0x0018:
            kotlinx.serialization.json.internal.JsonTreeReader$readObject$2 r2 = new kotlinx.serialization.json.internal.JsonTreeReader$readObject$2
            r2.<init>(r0, r1)
        L_0x001d:
            java.lang.Object r1 = r2.result
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r2.label
            r5 = 7
            r6 = 6
            r7 = 1
            r8 = 4
            if (r4 == 0) goto L_0x0049
            if (r4 != r7) goto L_0x0041
            java.lang.Object r4 = r2.L$3
            java.lang.String r4 = (java.lang.String) r4
            java.lang.Object r9 = r2.L$2
            java.util.LinkedHashMap r9 = (java.util.LinkedHashMap) r9
            java.lang.Object r10 = r2.L$1
            kotlinx.serialization.json.internal.JsonTreeReader r10 = (kotlinx.serialization.json.internal.JsonTreeReader) r10
            java.lang.Object r11 = r2.L$0
            kotlin.DeepRecursiveScope r11 = (kotlin.DeepRecursiveScope) r11
            kotlin.ResultKt.throwOnFailure(r1)
            goto L_0x00a1
        L_0x0041:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L_0x0049:
            kotlin.ResultKt.throwOnFailure(r1)
            kotlinx.serialization.json.internal.AbstractJsonLexer r1 = r0.lexer
            byte r1 = r1.consumeNextToken((byte) r6)
            kotlinx.serialization.json.internal.AbstractJsonLexer r4 = r0.lexer
            byte r4 = r4.peekNextToken()
            if (r4 == r8) goto L_0x00ef
            java.util.LinkedHashMap r4 = new java.util.LinkedHashMap
            r4.<init>()
            r10 = r0
            r9 = r4
            r4 = r2
            r2 = r1
            r1 = r21
        L_0x0065:
            kotlinx.serialization.json.internal.AbstractJsonLexer r11 = r10.lexer
            boolean r11 = r11.canConsumeValue()
            if (r11 == 0) goto L_0x00cc
            boolean r2 = r10.isLenient
            if (r2 == 0) goto L_0x0078
            kotlinx.serialization.json.internal.AbstractJsonLexer r2 = r10.lexer
            java.lang.String r2 = r2.consumeStringLenient()
            goto L_0x007e
        L_0x0078:
            kotlinx.serialization.json.internal.AbstractJsonLexer r2 = r10.lexer
            java.lang.String r2 = r2.consumeString()
        L_0x007e:
            kotlinx.serialization.json.internal.AbstractJsonLexer r11 = r10.lexer
            r12 = 5
            r11.consumeNextToken((byte) r12)
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            r4.L$0 = r1
            r4.L$1 = r10
            r4.L$2 = r9
            r4.L$3 = r2
            r4.label = r7
            java.lang.Object r11 = r1.callRecursive(r11, r4)
            if (r11 != r3) goto L_0x0097
            return r3
        L_0x0097:
            r18 = r11
            r11 = r1
            r1 = r18
            r19 = r4
            r4 = r2
            r2 = r19
        L_0x00a1:
            kotlinx.serialization.json.JsonElement r1 = (kotlinx.serialization.json.JsonElement) r1
            r12 = r9
            java.util.Map r12 = (java.util.Map) r12
            r12.put(r4, r1)
            kotlinx.serialization.json.internal.AbstractJsonLexer r1 = r10.lexer
            byte r1 = r1.consumeNextToken()
            if (r1 == r8) goto L_0x00c8
            if (r1 != r5) goto L_0x00b5
            r2 = r1
            goto L_0x00cc
        L_0x00b5:
            kotlinx.serialization.json.internal.AbstractJsonLexer r12 = r10.lexer
            r16 = 6
            r17 = 0
            java.lang.String r13 = "Expected end of the object or comma"
            r14 = 0
            r15 = 0
            kotlinx.serialization.json.internal.AbstractJsonLexer.fail$default(r12, r13, r14, r15, r16, r17)
            kotlin.KotlinNothingValueException r1 = new kotlin.KotlinNothingValueException
            r1.<init>()
            throw r1
        L_0x00c8:
            r4 = r2
            r2 = r1
            r1 = r11
            goto L_0x0065
        L_0x00cc:
            if (r2 != r6) goto L_0x00d4
            kotlinx.serialization.json.internal.AbstractJsonLexer r1 = r10.lexer
            r1.consumeNextToken((byte) r5)
            goto L_0x00d6
        L_0x00d4:
            if (r2 == r8) goto L_0x00de
        L_0x00d6:
            kotlinx.serialization.json.JsonObject r1 = new kotlinx.serialization.json.JsonObject
            java.util.Map r9 = (java.util.Map) r9
            r1.<init>(r9)
            return r1
        L_0x00de:
            kotlinx.serialization.json.internal.AbstractJsonLexer r2 = r10.lexer
            r6 = 6
            r7 = 0
            java.lang.String r3 = "Unexpected trailing comma"
            r4 = 0
            r5 = 0
            kotlinx.serialization.json.internal.AbstractJsonLexer.fail$default(r2, r3, r4, r5, r6, r7)
            kotlin.KotlinNothingValueException r1 = new kotlin.KotlinNothingValueException
            r1.<init>()
            throw r1
        L_0x00ef:
            kotlinx.serialization.json.internal.AbstractJsonLexer r2 = r0.lexer
            r6 = 6
            r7 = 0
            java.lang.String r3 = "Unexpected leading comma"
            r4 = 0
            r5 = 0
            kotlinx.serialization.json.internal.AbstractJsonLexer.fail$default(r2, r3, r4, r5, r6, r7)
            kotlin.KotlinNothingValueException r1 = new kotlin.KotlinNothingValueException
            r1.<init>()
            goto L_0x0101
        L_0x0100:
            throw r1
        L_0x0101:
            goto L_0x0100
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.serialization.json.internal.JsonTreeReader.readObject(kotlin.DeepRecursiveScope, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final JsonObject readObjectImpl(Function0<? extends JsonElement> function0) {
        byte consumeNextToken = this.lexer.consumeNextToken((byte) 6);
        if (this.lexer.peekNextToken() != 4) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            while (true) {
                if (!this.lexer.canConsumeValue()) {
                    break;
                }
                String consumeStringLenient = this.isLenient ? this.lexer.consumeStringLenient() : this.lexer.consumeString();
                this.lexer.consumeNextToken((byte) 5);
                linkedHashMap.put(consumeStringLenient, (JsonElement) function0.invoke());
                consumeNextToken = this.lexer.consumeNextToken();
                if (consumeNextToken != 4) {
                    if (consumeNextToken != 7) {
                        AbstractJsonLexer.fail$default(this.lexer, "Expected end of the object or comma", 0, (String) null, 6, (Object) null);
                        throw new KotlinNothingValueException();
                    }
                }
            }
            if (consumeNextToken == 6) {
                this.lexer.consumeNextToken((byte) 7);
            } else if (consumeNextToken == 4) {
                AbstractJsonLexer.fail$default(this.lexer, "Unexpected trailing comma", 0, (String) null, 6, (Object) null);
                throw new KotlinNothingValueException();
            }
            return new JsonObject(linkedHashMap);
        }
        AbstractJsonLexer.fail$default(this.lexer, "Unexpected leading comma", 0, (String) null, 6, (Object) null);
        throw new KotlinNothingValueException();
    }

    /* access modifiers changed from: private */
    public final JsonElement readArray() {
        byte consumeNextToken = this.lexer.consumeNextToken();
        if (this.lexer.peekNextToken() != 4) {
            ArrayList arrayList = new ArrayList();
            while (this.lexer.canConsumeValue()) {
                arrayList.add(read());
                consumeNextToken = this.lexer.consumeNextToken();
                if (consumeNextToken != 4) {
                    AbstractJsonLexer abstractJsonLexer = this.lexer;
                    boolean z = consumeNextToken == 9;
                    int access$getCurrentPosition$p = abstractJsonLexer.currentPosition;
                    if (!z) {
                        AbstractJsonLexer.fail$default(abstractJsonLexer, "Expected end of the array or comma", access$getCurrentPosition$p, (String) null, 4, (Object) null);
                        throw new KotlinNothingValueException();
                    }
                }
            }
            if (consumeNextToken == 8) {
                this.lexer.consumeNextToken((byte) 9);
            } else if (consumeNextToken == 4) {
                AbstractJsonLexer.fail$default(this.lexer, "Unexpected trailing comma", 0, (String) null, 6, (Object) null);
                throw new KotlinNothingValueException();
            }
            return new JsonArray(arrayList);
        }
        AbstractJsonLexer.fail$default(this.lexer, "Unexpected leading comma", 0, (String) null, 6, (Object) null);
        throw new KotlinNothingValueException();
    }

    /* access modifiers changed from: private */
    public final JsonPrimitive readValue(boolean z) {
        String str;
        if (this.isLenient || !z) {
            str = this.lexer.consumeStringLenient();
        } else {
            str = this.lexer.consumeString();
        }
        String str2 = str;
        if (z || !Intrinsics.areEqual((Object) str2, (Object) AbstractJsonLexerKt.NULL)) {
            return new JsonLiteral(str2, z, (SerialDescriptor) null, 4, (DefaultConstructorMarker) null);
        }
        return JsonNull.INSTANCE;
    }

    public final JsonElement read() {
        JsonElement jsonElement;
        byte peekNextToken = this.lexer.peekNextToken();
        if (peekNextToken == 1) {
            return readValue(true);
        }
        if (peekNextToken == 0) {
            return readValue(false);
        }
        if (peekNextToken == 6) {
            int i = this.stackDepth + 1;
            this.stackDepth = i;
            if (i == 200) {
                jsonElement = readDeepRecursive();
            } else {
                jsonElement = readObject();
            }
            this.stackDepth--;
            return jsonElement;
        } else if (peekNextToken == 8) {
            return readArray();
        } else {
            AbstractJsonLexer.fail$default(this.lexer, "Cannot begin reading element, unexpected token: " + peekNextToken, 0, (String) null, 6, (Object) null);
            throw new KotlinNothingValueException();
        }
    }

    private final JsonElement readDeepRecursive() {
        return (JsonElement) DeepRecursiveKt.invoke(new DeepRecursiveFunction(new JsonTreeReader$readDeepRecursive$1(this, (Continuation<? super JsonTreeReader$readDeepRecursive$1>) null)), Unit.INSTANCE);
    }

    private final JsonElement readObject() {
        byte consumeNextToken = this.lexer.consumeNextToken((byte) 6);
        if (this.lexer.peekNextToken() != 4) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            while (true) {
                if (!this.lexer.canConsumeValue()) {
                    break;
                }
                String consumeStringLenient = this.isLenient ? this.lexer.consumeStringLenient() : this.lexer.consumeString();
                this.lexer.consumeNextToken((byte) 5);
                linkedHashMap.put(consumeStringLenient, read());
                consumeNextToken = this.lexer.consumeNextToken();
                if (consumeNextToken != 4) {
                    if (consumeNextToken != 7) {
                        AbstractJsonLexer.fail$default(this.lexer, "Expected end of the object or comma", 0, (String) null, 6, (Object) null);
                        throw new KotlinNothingValueException();
                    }
                }
            }
            if (consumeNextToken == 6) {
                this.lexer.consumeNextToken((byte) 7);
            } else if (consumeNextToken == 4) {
                AbstractJsonLexer.fail$default(this.lexer, "Unexpected trailing comma", 0, (String) null, 6, (Object) null);
                throw new KotlinNothingValueException();
            }
            return new JsonObject(linkedHashMap);
        }
        AbstractJsonLexer.fail$default(this.lexer, "Unexpected leading comma", 0, (String) null, 6, (Object) null);
        throw new KotlinNothingValueException();
    }
}
