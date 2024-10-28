package kotlin.reflect.jvm.internal.impl.types;

import io.ktor.server.auth.OAuth2RequestParameters;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.types.TypeCheckerState;
import kotlin.reflect.jvm.internal.impl.types.model.ArgumentList;
import kotlin.reflect.jvm.internal.impl.types.model.CaptureStatus;
import kotlin.reflect.jvm.internal.impl.types.model.CapturedTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.DefinitelyNotNullTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.IntersectionTypeConstructorMarker;
import kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.SimpleTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeArgumentListMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeArgumentMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeConstructorMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeParameterMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext;
import kotlin.reflect.jvm.internal.impl.types.model.TypeVariableTypeConstructorMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeVariance;
import kotlin.reflect.jvm.internal.impl.utils.SmartList;

/* compiled from: AbstractTypeChecker.kt */
public final class AbstractTypeChecker {
    public static final AbstractTypeChecker INSTANCE = new AbstractTypeChecker();
    public static boolean RUN_SLOW_ASSERTIONS;

    /* compiled from: AbstractTypeChecker.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|13|14|15|16|(2:17|18)|19|21) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0033 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x003b */
        static {
            /*
                kotlin.reflect.jvm.internal.impl.types.model.TypeVariance[] r0 = kotlin.reflect.jvm.internal.impl.types.model.TypeVariance.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                r1 = 1
                kotlin.reflect.jvm.internal.impl.types.model.TypeVariance r2 = kotlin.reflect.jvm.internal.impl.types.model.TypeVariance.INV     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                r2 = 2
                kotlin.reflect.jvm.internal.impl.types.model.TypeVariance r3 = kotlin.reflect.jvm.internal.impl.types.model.TypeVariance.OUT     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r0[r3] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                r3 = 3
                kotlin.reflect.jvm.internal.impl.types.model.TypeVariance r4 = kotlin.reflect.jvm.internal.impl.types.model.TypeVariance.IN     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r0[r4] = r3     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                $EnumSwitchMapping$0 = r0
                kotlin.reflect.jvm.internal.impl.types.TypeCheckerState$LowerCapturedTypePolicy[] r0 = kotlin.reflect.jvm.internal.impl.types.TypeCheckerState.LowerCapturedTypePolicy.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                kotlin.reflect.jvm.internal.impl.types.TypeCheckerState$LowerCapturedTypePolicy r4 = kotlin.reflect.jvm.internal.impl.types.TypeCheckerState.LowerCapturedTypePolicy.CHECK_ONLY_LOWER     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r0[r4] = r1     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                kotlin.reflect.jvm.internal.impl.types.TypeCheckerState$LowerCapturedTypePolicy r1 = kotlin.reflect.jvm.internal.impl.types.TypeCheckerState.LowerCapturedTypePolicy.CHECK_SUBTYPE_AND_LOWER     // Catch:{ NoSuchFieldError -> 0x003b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003b }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003b }
            L_0x003b:
                kotlin.reflect.jvm.internal.impl.types.TypeCheckerState$LowerCapturedTypePolicy r1 = kotlin.reflect.jvm.internal.impl.types.TypeCheckerState.LowerCapturedTypePolicy.SKIP_LOWER     // Catch:{ NoSuchFieldError -> 0x0043 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0043 }
                r0[r1] = r3     // Catch:{ NoSuchFieldError -> 0x0043 }
            L_0x0043:
                $EnumSwitchMapping$1 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.types.AbstractTypeChecker.WhenMappings.<clinit>():void");
        }
    }

    public final boolean isSubtypeOf(TypeCheckerState typeCheckerState, KotlinTypeMarker kotlinTypeMarker, KotlinTypeMarker kotlinTypeMarker2) {
        Intrinsics.checkNotNullParameter(typeCheckerState, OAuth2RequestParameters.State);
        Intrinsics.checkNotNullParameter(kotlinTypeMarker, "subType");
        Intrinsics.checkNotNullParameter(kotlinTypeMarker2, "superType");
        return isSubtypeOf$default(this, typeCheckerState, kotlinTypeMarker, kotlinTypeMarker2, false, 8, (Object) null);
    }

    private AbstractTypeChecker() {
    }

    public static /* synthetic */ boolean isSubtypeOf$default(AbstractTypeChecker abstractTypeChecker, TypeCheckerState typeCheckerState, KotlinTypeMarker kotlinTypeMarker, KotlinTypeMarker kotlinTypeMarker2, boolean z, int i, Object obj) {
        if ((i & 8) != 0) {
            z = false;
        }
        return abstractTypeChecker.isSubtypeOf(typeCheckerState, kotlinTypeMarker, kotlinTypeMarker2, z);
    }

    public final boolean isSubtypeOf(TypeCheckerState typeCheckerState, KotlinTypeMarker kotlinTypeMarker, KotlinTypeMarker kotlinTypeMarker2, boolean z) {
        Intrinsics.checkNotNullParameter(typeCheckerState, OAuth2RequestParameters.State);
        Intrinsics.checkNotNullParameter(kotlinTypeMarker, "subType");
        Intrinsics.checkNotNullParameter(kotlinTypeMarker2, "superType");
        if (kotlinTypeMarker == kotlinTypeMarker2) {
            return true;
        }
        if (!typeCheckerState.customIsSubtypeOf(kotlinTypeMarker, kotlinTypeMarker2)) {
            return false;
        }
        return completeIsSubTypeOf(typeCheckerState, kotlinTypeMarker, kotlinTypeMarker2, z);
    }

    public final boolean equalTypes(TypeCheckerState typeCheckerState, KotlinTypeMarker kotlinTypeMarker, KotlinTypeMarker kotlinTypeMarker2) {
        Intrinsics.checkNotNullParameter(typeCheckerState, OAuth2RequestParameters.State);
        Intrinsics.checkNotNullParameter(kotlinTypeMarker, "a");
        Intrinsics.checkNotNullParameter(kotlinTypeMarker2, "b");
        TypeSystemContext typeSystemContext = typeCheckerState.getTypeSystemContext();
        if (kotlinTypeMarker == kotlinTypeMarker2) {
            return true;
        }
        AbstractTypeChecker abstractTypeChecker = INSTANCE;
        if (abstractTypeChecker.isCommonDenotableType(typeSystemContext, kotlinTypeMarker) && abstractTypeChecker.isCommonDenotableType(typeSystemContext, kotlinTypeMarker2)) {
            KotlinTypeMarker prepareType = typeCheckerState.prepareType(typeCheckerState.refineType(kotlinTypeMarker));
            KotlinTypeMarker prepareType2 = typeCheckerState.prepareType(typeCheckerState.refineType(kotlinTypeMarker2));
            SimpleTypeMarker lowerBoundIfFlexible = typeSystemContext.lowerBoundIfFlexible(prepareType);
            if (!typeSystemContext.areEqualTypeConstructors(typeSystemContext.typeConstructor(prepareType), typeSystemContext.typeConstructor(prepareType2))) {
                return false;
            }
            if (typeSystemContext.argumentsCount(lowerBoundIfFlexible) == 0) {
                if (typeSystemContext.hasFlexibleNullability(prepareType) || typeSystemContext.hasFlexibleNullability(prepareType2) || typeSystemContext.isMarkedNullable(lowerBoundIfFlexible) == typeSystemContext.isMarkedNullable(typeSystemContext.lowerBoundIfFlexible(prepareType2))) {
                    return true;
                }
                return false;
            }
        }
        if (!isSubtypeOf$default(abstractTypeChecker, typeCheckerState, kotlinTypeMarker, kotlinTypeMarker2, false, 8, (Object) null) || !isSubtypeOf$default(abstractTypeChecker, typeCheckerState, kotlinTypeMarker2, kotlinTypeMarker, false, 8, (Object) null)) {
            return false;
        }
        return true;
    }

    private final boolean completeIsSubTypeOf(TypeCheckerState typeCheckerState, KotlinTypeMarker kotlinTypeMarker, KotlinTypeMarker kotlinTypeMarker2, boolean z) {
        TypeSystemContext typeSystemContext = typeCheckerState.getTypeSystemContext();
        KotlinTypeMarker prepareType = typeCheckerState.prepareType(typeCheckerState.refineType(kotlinTypeMarker));
        KotlinTypeMarker prepareType2 = typeCheckerState.prepareType(typeCheckerState.refineType(kotlinTypeMarker2));
        AbstractTypeChecker abstractTypeChecker = INSTANCE;
        Boolean checkSubtypeForSpecialCases = abstractTypeChecker.checkSubtypeForSpecialCases(typeCheckerState, typeSystemContext.lowerBoundIfFlexible(prepareType), typeSystemContext.upperBoundIfFlexible(prepareType2));
        if (checkSubtypeForSpecialCases != null) {
            boolean booleanValue = checkSubtypeForSpecialCases.booleanValue();
            typeCheckerState.addSubtypeConstraint(prepareType, prepareType2, z);
            return booleanValue;
        }
        Boolean addSubtypeConstraint = typeCheckerState.addSubtypeConstraint(prepareType, prepareType2, z);
        if (addSubtypeConstraint != null) {
            return addSubtypeConstraint.booleanValue();
        }
        return abstractTypeChecker.isSubtypeOfForSingleClassifierType(typeCheckerState, typeSystemContext.lowerBoundIfFlexible(prepareType), typeSystemContext.upperBoundIfFlexible(prepareType2));
    }

    private final Boolean checkSubtypeForIntegerLiteralType(TypeCheckerState typeCheckerState, SimpleTypeMarker simpleTypeMarker, SimpleTypeMarker simpleTypeMarker2) {
        TypeSystemContext typeSystemContext = typeCheckerState.getTypeSystemContext();
        if (!typeSystemContext.isIntegerLiteralType(simpleTypeMarker) && !typeSystemContext.isIntegerLiteralType(simpleTypeMarker2)) {
            return null;
        }
        if (checkSubtypeForIntegerLiteralType$lambda$7$isIntegerLiteralTypeOrCapturedOne(typeSystemContext, simpleTypeMarker) && checkSubtypeForIntegerLiteralType$lambda$7$isIntegerLiteralTypeOrCapturedOne(typeSystemContext, simpleTypeMarker2)) {
            return true;
        }
        if (typeSystemContext.isIntegerLiteralType(simpleTypeMarker)) {
            if (checkSubtypeForIntegerLiteralType$lambda$7$isTypeInIntegerLiteralType(typeSystemContext, typeCheckerState, simpleTypeMarker, simpleTypeMarker2, false)) {
                return true;
            }
        } else if (typeSystemContext.isIntegerLiteralType(simpleTypeMarker2) && (checkSubtypeForIntegerLiteralType$lambda$7$isIntegerLiteralTypeInIntersectionComponents(typeSystemContext, simpleTypeMarker) || checkSubtypeForIntegerLiteralType$lambda$7$isTypeInIntegerLiteralType(typeSystemContext, typeCheckerState, simpleTypeMarker2, simpleTypeMarker, true))) {
            return true;
        }
        return null;
    }

    private static final boolean checkSubtypeForIntegerLiteralType$lambda$7$isTypeInIntegerLiteralType(TypeSystemContext typeSystemContext, TypeCheckerState typeCheckerState, SimpleTypeMarker simpleTypeMarker, SimpleTypeMarker simpleTypeMarker2, boolean z) {
        Iterable<KotlinTypeMarker> possibleIntegerTypes = typeSystemContext.possibleIntegerTypes(simpleTypeMarker);
        if ((possibleIntegerTypes instanceof Collection) && ((Collection) possibleIntegerTypes).isEmpty()) {
            return false;
        }
        for (KotlinTypeMarker kotlinTypeMarker : possibleIntegerTypes) {
            if (!Intrinsics.areEqual((Object) typeSystemContext.typeConstructor(kotlinTypeMarker), (Object) typeSystemContext.typeConstructor(simpleTypeMarker2))) {
                if (z) {
                    if (isSubtypeOf$default(INSTANCE, typeCheckerState, simpleTypeMarker2, kotlinTypeMarker, false, 8, (Object) null)) {
                    }
                }
            }
            return true;
        }
        return false;
    }

    private static final boolean checkSubtypeForIntegerLiteralType$lambda$7$isIntegerLiteralTypeInIntersectionComponents(TypeSystemContext typeSystemContext, SimpleTypeMarker simpleTypeMarker) {
        TypeConstructorMarker typeConstructor = typeSystemContext.typeConstructor(simpleTypeMarker);
        if (typeConstructor instanceof IntersectionTypeConstructorMarker) {
            Iterable<KotlinTypeMarker> supertypes = typeSystemContext.supertypes(typeConstructor);
            if (!(supertypes instanceof Collection) || !((Collection) supertypes).isEmpty()) {
                for (KotlinTypeMarker asSimpleType : supertypes) {
                    SimpleTypeMarker asSimpleType2 = typeSystemContext.asSimpleType(asSimpleType);
                    if (asSimpleType2 != null && typeSystemContext.isIntegerLiteralType(asSimpleType2)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static final boolean checkSubtypeForIntegerLiteralType$lambda$7$isCapturedIntegerLiteralType(TypeSystemContext typeSystemContext, SimpleTypeMarker simpleTypeMarker) {
        if (!(simpleTypeMarker instanceof CapturedTypeMarker)) {
            return false;
        }
        TypeArgumentMarker projection = typeSystemContext.projection(typeSystemContext.typeConstructor((CapturedTypeMarker) simpleTypeMarker));
        if (typeSystemContext.isStarProjection(projection) || !typeSystemContext.isIntegerLiteralType(typeSystemContext.upperBoundIfFlexible(typeSystemContext.getType(projection)))) {
            return false;
        }
        return true;
    }

    private static final boolean checkSubtypeForIntegerLiteralType$lambda$7$isIntegerLiteralTypeOrCapturedOne(TypeSystemContext typeSystemContext, SimpleTypeMarker simpleTypeMarker) {
        return typeSystemContext.isIntegerLiteralType(simpleTypeMarker) || checkSubtypeForIntegerLiteralType$lambda$7$isCapturedIntegerLiteralType(typeSystemContext, simpleTypeMarker);
    }

    private final boolean hasNothingSupertype(TypeCheckerState typeCheckerState, SimpleTypeMarker simpleTypeMarker) {
        TypeCheckerState.SupertypesPolicy supertypesPolicy;
        TypeSystemContext typeSystemContext = typeCheckerState.getTypeSystemContext();
        TypeConstructorMarker typeConstructor = typeSystemContext.typeConstructor(simpleTypeMarker);
        if (typeSystemContext.isClassTypeConstructor(typeConstructor)) {
            return typeSystemContext.isNothingConstructor(typeConstructor);
        }
        if (typeSystemContext.isNothingConstructor(typeSystemContext.typeConstructor(simpleTypeMarker))) {
            return true;
        }
        typeCheckerState.initialize();
        ArrayDeque<SimpleTypeMarker> supertypesDeque = typeCheckerState.getSupertypesDeque();
        Intrinsics.checkNotNull(supertypesDeque);
        Set<SimpleTypeMarker> supertypesSet = typeCheckerState.getSupertypesSet();
        Intrinsics.checkNotNull(supertypesSet);
        supertypesDeque.push(simpleTypeMarker);
        while (!supertypesDeque.isEmpty()) {
            if (supertypesSet.size() <= 1000) {
                SimpleTypeMarker pop = supertypesDeque.pop();
                Intrinsics.checkNotNull(pop);
                if (supertypesSet.add(pop)) {
                    if (typeSystemContext.isClassType(pop)) {
                        supertypesPolicy = TypeCheckerState.SupertypesPolicy.None.INSTANCE;
                    } else {
                        supertypesPolicy = TypeCheckerState.SupertypesPolicy.LowerIfFlexible.INSTANCE;
                    }
                    if (!(!Intrinsics.areEqual((Object) supertypesPolicy, (Object) TypeCheckerState.SupertypesPolicy.None.INSTANCE))) {
                        supertypesPolicy = null;
                    }
                    if (supertypesPolicy == null) {
                        continue;
                    } else {
                        TypeSystemContext typeSystemContext2 = typeCheckerState.getTypeSystemContext();
                        for (KotlinTypeMarker transformType : typeSystemContext2.supertypes(typeSystemContext2.typeConstructor(pop))) {
                            SimpleTypeMarker transformType2 = supertypesPolicy.transformType(typeCheckerState, transformType);
                            if (typeSystemContext.isNothingConstructor(typeSystemContext.typeConstructor(transformType2))) {
                                typeCheckerState.clear();
                                return true;
                            }
                            supertypesDeque.add(transformType2);
                        }
                        continue;
                    }
                }
            } else {
                throw new IllegalStateException(("Too many supertypes for type: " + simpleTypeMarker + ". Supertypes = " + CollectionsKt.joinToString$default(supertypesSet, (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 63, (Object) null)).toString());
            }
        }
        typeCheckerState.clear();
        return false;
    }

    private final boolean isSubtypeOfForSingleClassifierType(TypeCheckerState typeCheckerState, SimpleTypeMarker simpleTypeMarker, SimpleTypeMarker simpleTypeMarker2) {
        KotlinTypeMarker type;
        TypeCheckerState typeCheckerState2 = typeCheckerState;
        SimpleTypeMarker simpleTypeMarker3 = simpleTypeMarker;
        SimpleTypeMarker simpleTypeMarker4 = simpleTypeMarker2;
        TypeSystemContext typeSystemContext = typeCheckerState.getTypeSystemContext();
        if (RUN_SLOW_ASSERTIONS) {
            if (!typeSystemContext.isSingleClassifierType(simpleTypeMarker3) && !typeSystemContext.isIntersection(typeSystemContext.typeConstructor(simpleTypeMarker3))) {
                boolean isAllowedTypeVariable = typeCheckerState2.isAllowedTypeVariable(simpleTypeMarker3);
            }
            if (!typeSystemContext.isSingleClassifierType(simpleTypeMarker4)) {
                boolean isAllowedTypeVariable2 = typeCheckerState2.isAllowedTypeVariable(simpleTypeMarker4);
            }
        }
        if (!AbstractNullabilityChecker.INSTANCE.isPossibleSubtype(typeCheckerState2, simpleTypeMarker3, simpleTypeMarker4)) {
            return false;
        }
        AbstractTypeChecker abstractTypeChecker = INSTANCE;
        KotlinTypeMarker kotlinTypeMarker = simpleTypeMarker3;
        KotlinTypeMarker kotlinTypeMarker2 = simpleTypeMarker4;
        Boolean checkSubtypeForIntegerLiteralType = abstractTypeChecker.checkSubtypeForIntegerLiteralType(typeCheckerState2, typeSystemContext.lowerBoundIfFlexible(kotlinTypeMarker), typeSystemContext.upperBoundIfFlexible(kotlinTypeMarker2));
        if (checkSubtypeForIntegerLiteralType != null) {
            boolean booleanValue = checkSubtypeForIntegerLiteralType.booleanValue();
            TypeCheckerState.addSubtypeConstraint$default(typeCheckerState, kotlinTypeMarker, kotlinTypeMarker2, false, 4, (Object) null);
            return booleanValue;
        }
        TypeConstructorMarker typeConstructor = typeSystemContext.typeConstructor(simpleTypeMarker4);
        if ((typeSystemContext.areEqualTypeConstructors(typeSystemContext.typeConstructor(simpleTypeMarker3), typeConstructor) && typeSystemContext.parametersCount(typeConstructor) == 0) || typeSystemContext.isAnyConstructor(typeSystemContext.typeConstructor(simpleTypeMarker4))) {
            return true;
        }
        Iterable<SimpleTypeMarker> findCorrespondingSupertypes = abstractTypeChecker.findCorrespondingSupertypes(typeCheckerState2, simpleTypeMarker3, typeConstructor);
        int i = 10;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(findCorrespondingSupertypes, 10));
        for (SimpleTypeMarker simpleTypeMarker5 : findCorrespondingSupertypes) {
            SimpleTypeMarker asSimpleType = typeSystemContext.asSimpleType(typeCheckerState2.prepareType(simpleTypeMarker5));
            if (asSimpleType != null) {
                simpleTypeMarker5 = asSimpleType;
            }
            arrayList.add(simpleTypeMarker5);
        }
        List list = (List) arrayList;
        int size = list.size();
        if (size == 0) {
            return INSTANCE.hasNothingSupertype(typeCheckerState2, simpleTypeMarker3);
        }
        if (size == 1) {
            return INSTANCE.isSubtypeForSameConstructor(typeCheckerState2, typeSystemContext.asArgumentList((SimpleTypeMarker) CollectionsKt.first(list)), simpleTypeMarker4);
        }
        ArgumentList argumentList = new ArgumentList(typeSystemContext.parametersCount(typeConstructor));
        int parametersCount = typeSystemContext.parametersCount(typeConstructor);
        int i2 = 0;
        boolean z = false;
        while (i2 < parametersCount) {
            z = z || typeSystemContext.getVariance(typeSystemContext.getParameter(typeConstructor, i2)) != TypeVariance.OUT;
            if (!z) {
                Iterable<SimpleTypeMarker> iterable = list;
                Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, i));
                for (SimpleTypeMarker simpleTypeMarker6 : iterable) {
                    TypeArgumentMarker argumentOrNull = typeSystemContext.getArgumentOrNull(simpleTypeMarker6, i2);
                    if (argumentOrNull != null) {
                        if (typeSystemContext.getVariance(argumentOrNull) != TypeVariance.INV) {
                            argumentOrNull = null;
                        }
                        if (!(argumentOrNull == null || (type = typeSystemContext.getType(argumentOrNull)) == null)) {
                            arrayList2.add(type);
                        }
                    }
                    throw new IllegalStateException(("Incorrect type: " + simpleTypeMarker6 + ", subType: " + simpleTypeMarker3 + ", superType: " + simpleTypeMarker4).toString());
                }
                argumentList.add(typeSystemContext.asTypeArgument(typeSystemContext.intersectTypes((List) arrayList2)));
            }
            i2++;
            i = 10;
        }
        if (z || !INSTANCE.isSubtypeForSameConstructor(typeCheckerState2, argumentList, simpleTypeMarker4)) {
            return typeCheckerState2.runForkingPoint(new AbstractTypeChecker$isSubtypeOfForSingleClassifierType$1$4(list, typeCheckerState2, typeSystemContext, simpleTypeMarker4));
        }
        return true;
    }

    private final boolean isTypeVariableAgainstStarProjectionForSelfType(TypeSystemContext typeSystemContext, KotlinTypeMarker kotlinTypeMarker, KotlinTypeMarker kotlinTypeMarker2, TypeConstructorMarker typeConstructorMarker) {
        TypeParameterMarker typeParameter;
        SimpleTypeMarker asSimpleType = typeSystemContext.asSimpleType(kotlinTypeMarker);
        if (!(asSimpleType instanceof CapturedTypeMarker)) {
            return false;
        }
        CapturedTypeMarker capturedTypeMarker = (CapturedTypeMarker) asSimpleType;
        if (typeSystemContext.isOldCapturedType(capturedTypeMarker) || !typeSystemContext.isStarProjection(typeSystemContext.projection(typeSystemContext.typeConstructor(capturedTypeMarker))) || typeSystemContext.captureStatus(capturedTypeMarker) != CaptureStatus.FOR_SUBTYPING) {
            return false;
        }
        TypeConstructorMarker typeConstructor = typeSystemContext.typeConstructor(kotlinTypeMarker2);
        TypeVariableTypeConstructorMarker typeVariableTypeConstructorMarker = typeConstructor instanceof TypeVariableTypeConstructorMarker ? (TypeVariableTypeConstructorMarker) typeConstructor : null;
        if (typeVariableTypeConstructorMarker == null || (typeParameter = typeSystemContext.getTypeParameter(typeVariableTypeConstructorMarker)) == null || !typeSystemContext.hasRecursiveBounds(typeParameter, typeConstructorMarker)) {
            return false;
        }
        return true;
    }

    public final boolean isSubtypeForSameConstructor(TypeCheckerState typeCheckerState, TypeArgumentListMarker typeArgumentListMarker, SimpleTypeMarker simpleTypeMarker) {
        boolean z;
        TypeCheckerState typeCheckerState2 = typeCheckerState;
        TypeArgumentListMarker typeArgumentListMarker2 = typeArgumentListMarker;
        SimpleTypeMarker simpleTypeMarker2 = simpleTypeMarker;
        Intrinsics.checkNotNullParameter(typeCheckerState2, "<this>");
        Intrinsics.checkNotNullParameter(typeArgumentListMarker2, "capturedSubArguments");
        Intrinsics.checkNotNullParameter(simpleTypeMarker2, "superType");
        TypeSystemContext typeSystemContext = typeCheckerState.getTypeSystemContext();
        TypeConstructorMarker typeConstructor = typeSystemContext.typeConstructor(simpleTypeMarker2);
        int size = typeSystemContext.size(typeArgumentListMarker2);
        int parametersCount = typeSystemContext.parametersCount(typeConstructor);
        if (size == parametersCount) {
            KotlinTypeMarker kotlinTypeMarker = simpleTypeMarker2;
            if (size == typeSystemContext.argumentsCount(kotlinTypeMarker)) {
                for (int i = 0; i < parametersCount; i++) {
                    TypeArgumentMarker argument = typeSystemContext.getArgument(kotlinTypeMarker, i);
                    if (!typeSystemContext.isStarProjection(argument)) {
                        KotlinTypeMarker type = typeSystemContext.getType(argument);
                        TypeArgumentMarker typeArgumentMarker = typeSystemContext.get(typeArgumentListMarker2, i);
                        typeSystemContext.getVariance(typeArgumentMarker);
                        TypeVariance typeVariance = TypeVariance.INV;
                        KotlinTypeMarker type2 = typeSystemContext.getType(typeArgumentMarker);
                        AbstractTypeChecker abstractTypeChecker = INSTANCE;
                        TypeVariance effectiveVariance = abstractTypeChecker.effectiveVariance(typeSystemContext.getVariance(typeSystemContext.getParameter(typeConstructor, i)), typeSystemContext.getVariance(argument));
                        if (effectiveVariance == null) {
                            return typeCheckerState.isErrorTypeEqualsToAnything();
                        }
                        if (effectiveVariance != TypeVariance.INV || (!abstractTypeChecker.isTypeVariableAgainstStarProjectionForSelfType(typeSystemContext, type2, type, typeConstructor) && !abstractTypeChecker.isTypeVariableAgainstStarProjectionForSelfType(typeSystemContext, type, type2, typeConstructor))) {
                            if (typeCheckerState.argumentsDepth <= 100) {
                                typeCheckerState2.argumentsDepth = typeCheckerState.argumentsDepth + 1;
                                int i2 = WhenMappings.$EnumSwitchMapping$0[effectiveVariance.ordinal()];
                                if (i2 == 1) {
                                    z = abstractTypeChecker.equalTypes(typeCheckerState2, type2, type);
                                } else if (i2 == 2) {
                                    z = isSubtypeOf$default(abstractTypeChecker, typeCheckerState, type2, type, false, 8, (Object) null);
                                } else if (i2 == 3) {
                                    z = isSubtypeOf$default(abstractTypeChecker, typeCheckerState, type, type2, false, 8, (Object) null);
                                } else {
                                    throw new NoWhenBranchMatchedException();
                                }
                                typeCheckerState2.argumentsDepth = typeCheckerState.argumentsDepth - 1;
                                if (!z) {
                                    return false;
                                }
                            } else {
                                throw new IllegalStateException(("Arguments depth is too high. Some related argument: " + type2).toString());
                            }
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }

    private final boolean isCommonDenotableType(TypeSystemContext typeSystemContext, KotlinTypeMarker kotlinTypeMarker) {
        return typeSystemContext.isDenotable(typeSystemContext.typeConstructor(kotlinTypeMarker)) && !typeSystemContext.isDynamic(kotlinTypeMarker) && !typeSystemContext.isDefinitelyNotNullType(kotlinTypeMarker) && !typeSystemContext.isNotNullTypeParameter(kotlinTypeMarker) && Intrinsics.areEqual((Object) typeSystemContext.typeConstructor(typeSystemContext.lowerBoundIfFlexible(kotlinTypeMarker)), (Object) typeSystemContext.typeConstructor(typeSystemContext.upperBoundIfFlexible(kotlinTypeMarker)));
    }

    public final TypeVariance effectiveVariance(TypeVariance typeVariance, TypeVariance typeVariance2) {
        Intrinsics.checkNotNullParameter(typeVariance, "declared");
        Intrinsics.checkNotNullParameter(typeVariance2, "useSite");
        if (typeVariance == TypeVariance.INV) {
            return typeVariance2;
        }
        if (typeVariance2 == TypeVariance.INV || typeVariance == typeVariance2) {
            return typeVariance;
        }
        return null;
    }

    private final boolean isStubTypeSubtypeOfAnother(TypeSystemContext typeSystemContext, SimpleTypeMarker simpleTypeMarker, SimpleTypeMarker simpleTypeMarker2) {
        SimpleTypeMarker simpleTypeMarker3;
        SimpleTypeMarker simpleTypeMarker4;
        DefinitelyNotNullTypeMarker asDefinitelyNotNullType = typeSystemContext.asDefinitelyNotNullType(simpleTypeMarker);
        if (asDefinitelyNotNullType == null || (simpleTypeMarker3 = typeSystemContext.original(asDefinitelyNotNullType)) == null) {
            simpleTypeMarker3 = simpleTypeMarker;
        }
        DefinitelyNotNullTypeMarker asDefinitelyNotNullType2 = typeSystemContext.asDefinitelyNotNullType(simpleTypeMarker2);
        if (asDefinitelyNotNullType2 == null || (simpleTypeMarker4 = typeSystemContext.original(asDefinitelyNotNullType2)) == null) {
            simpleTypeMarker4 = simpleTypeMarker2;
        }
        if (typeSystemContext.typeConstructor(simpleTypeMarker3) != typeSystemContext.typeConstructor(simpleTypeMarker4)) {
            return false;
        }
        if (!typeSystemContext.isDefinitelyNotNullType(simpleTypeMarker) && typeSystemContext.isDefinitelyNotNullType(simpleTypeMarker2)) {
            return false;
        }
        if (!typeSystemContext.isMarkedNullable(simpleTypeMarker) || typeSystemContext.isMarkedNullable(simpleTypeMarker2)) {
            return true;
        }
        return false;
    }

    private final Boolean checkSubtypeForSpecialCases(TypeCheckerState typeCheckerState, SimpleTypeMarker simpleTypeMarker, SimpleTypeMarker simpleTypeMarker2) {
        SimpleTypeMarker simpleTypeMarker3;
        SimpleTypeMarker simpleTypeMarker4 = simpleTypeMarker;
        SimpleTypeMarker simpleTypeMarker5 = simpleTypeMarker2;
        TypeSystemContext typeSystemContext = typeCheckerState.getTypeSystemContext();
        KotlinTypeMarker kotlinTypeMarker = simpleTypeMarker4;
        boolean z = false;
        if (!typeSystemContext.isError(kotlinTypeMarker)) {
            KotlinTypeMarker kotlinTypeMarker2 = simpleTypeMarker5;
            if (!typeSystemContext.isError(kotlinTypeMarker2)) {
                if (typeSystemContext.isStubTypeForBuilderInference(simpleTypeMarker4) && typeSystemContext.isStubTypeForBuilderInference(simpleTypeMarker5)) {
                    if (INSTANCE.isStubTypeSubtypeOfAnother(typeSystemContext, simpleTypeMarker4, simpleTypeMarker5) || typeCheckerState.isStubTypeEqualsToAnything()) {
                        z = true;
                    }
                    return Boolean.valueOf(z);
                } else if (typeSystemContext.isStubType(simpleTypeMarker4) || typeSystemContext.isStubType(simpleTypeMarker5)) {
                    return Boolean.valueOf(typeCheckerState.isStubTypeEqualsToAnything());
                } else {
                    DefinitelyNotNullTypeMarker asDefinitelyNotNullType = typeSystemContext.asDefinitelyNotNullType(simpleTypeMarker5);
                    if (asDefinitelyNotNullType == null || (simpleTypeMarker3 = typeSystemContext.original(asDefinitelyNotNullType)) == null) {
                        simpleTypeMarker3 = simpleTypeMarker5;
                    }
                    CapturedTypeMarker asCapturedType = typeSystemContext.asCapturedType(simpleTypeMarker3);
                    KotlinTypeMarker lowerType = asCapturedType != null ? typeSystemContext.lowerType(asCapturedType) : null;
                    if (!(asCapturedType == null || lowerType == null)) {
                        if (typeSystemContext.isMarkedNullable(simpleTypeMarker5)) {
                            lowerType = typeSystemContext.withNullability(lowerType, true);
                        } else if (typeSystemContext.isDefinitelyNotNullType(kotlinTypeMarker2)) {
                            lowerType = typeSystemContext.makeDefinitelyNotNullOrNotNull(lowerType);
                        }
                        KotlinTypeMarker kotlinTypeMarker3 = lowerType;
                        int i = WhenMappings.$EnumSwitchMapping$1[typeCheckerState.getLowerCapturedTypePolicy(simpleTypeMarker4, asCapturedType).ordinal()];
                        if (i == 1) {
                            return Boolean.valueOf(isSubtypeOf$default(INSTANCE, typeCheckerState, kotlinTypeMarker, kotlinTypeMarker3, false, 8, (Object) null));
                        }
                        if (i == 2 && isSubtypeOf$default(INSTANCE, typeCheckerState, kotlinTypeMarker, kotlinTypeMarker3, false, 8, (Object) null)) {
                            return true;
                        }
                    }
                    TypeConstructorMarker typeConstructor = typeSystemContext.typeConstructor(simpleTypeMarker5);
                    if (typeSystemContext.isIntersection(typeConstructor)) {
                        typeSystemContext.isMarkedNullable(simpleTypeMarker5);
                        Iterable supertypes = typeSystemContext.supertypes(typeConstructor);
                        if (!(supertypes instanceof Collection) || !((Collection) supertypes).isEmpty()) {
                            Iterator it = supertypes.iterator();
                            while (true) {
                                if (!it.hasNext()) {
                                    break;
                                }
                                if (!isSubtypeOf$default(INSTANCE, typeCheckerState, kotlinTypeMarker, (KotlinTypeMarker) it.next(), false, 8, (Object) null)) {
                                    break;
                                }
                            }
                            return Boolean.valueOf(z);
                        }
                        z = true;
                        return Boolean.valueOf(z);
                    }
                    TypeConstructorMarker typeConstructor2 = typeSystemContext.typeConstructor(simpleTypeMarker4);
                    if (!(simpleTypeMarker4 instanceof CapturedTypeMarker)) {
                        if (typeSystemContext.isIntersection(typeConstructor2)) {
                            Iterable supertypes2 = typeSystemContext.supertypes(typeConstructor2);
                            if (!(supertypes2 instanceof Collection) || !((Collection) supertypes2).isEmpty()) {
                                Iterator it2 = supertypes2.iterator();
                                while (true) {
                                    if (it2.hasNext()) {
                                        if (!(((KotlinTypeMarker) it2.next()) instanceof CapturedTypeMarker)) {
                                            break;
                                        }
                                    } else {
                                        break;
                                    }
                                }
                            }
                        }
                        return null;
                    }
                    TypeParameterMarker typeParameterForArgumentInBaseIfItEqualToTarget = INSTANCE.getTypeParameterForArgumentInBaseIfItEqualToTarget(typeCheckerState.getTypeSystemContext(), kotlinTypeMarker2, kotlinTypeMarker);
                    if (typeParameterForArgumentInBaseIfItEqualToTarget == null || !typeSystemContext.hasRecursiveBounds(typeParameterForArgumentInBaseIfItEqualToTarget, typeSystemContext.typeConstructor(simpleTypeMarker5))) {
                        return null;
                    }
                    return true;
                }
            }
        }
        if (typeCheckerState.isErrorTypeEqualsToAnything()) {
            return true;
        }
        if (!typeSystemContext.isMarkedNullable(simpleTypeMarker4) || typeSystemContext.isMarkedNullable(simpleTypeMarker5)) {
            return Boolean.valueOf(AbstractStrictEqualityTypeChecker.INSTANCE.strictEqualTypes(typeSystemContext, typeSystemContext.withNullability(simpleTypeMarker4, false), typeSystemContext.withNullability(simpleTypeMarker5, false)));
        }
        return false;
    }

    private final TypeParameterMarker getTypeParameterForArgumentInBaseIfItEqualToTarget(TypeSystemContext typeSystemContext, KotlinTypeMarker kotlinTypeMarker, KotlinTypeMarker kotlinTypeMarker2) {
        KotlinTypeMarker type;
        int argumentsCount = typeSystemContext.argumentsCount(kotlinTypeMarker);
        int i = 0;
        while (true) {
            TypeArgumentMarker typeArgumentMarker = null;
            if (i >= argumentsCount) {
                return null;
            }
            TypeArgumentMarker argument = typeSystemContext.getArgument(kotlinTypeMarker, i);
            boolean z = true;
            if (!typeSystemContext.isStarProjection(argument)) {
                typeArgumentMarker = argument;
            }
            if (!(typeArgumentMarker == null || (type = typeSystemContext.getType(typeArgumentMarker)) == null)) {
                if (!typeSystemContext.isCapturedType(typeSystemContext.originalIfDefinitelyNotNullable(typeSystemContext.lowerBoundIfFlexible(type))) || !typeSystemContext.isCapturedType(typeSystemContext.originalIfDefinitelyNotNullable(typeSystemContext.lowerBoundIfFlexible(kotlinTypeMarker2)))) {
                    z = false;
                }
                if (!Intrinsics.areEqual((Object) type, (Object) kotlinTypeMarker2) && (!z || !Intrinsics.areEqual((Object) typeSystemContext.typeConstructor(type), (Object) typeSystemContext.typeConstructor(kotlinTypeMarker2)))) {
                    TypeParameterMarker typeParameterForArgumentInBaseIfItEqualToTarget = getTypeParameterForArgumentInBaseIfItEqualToTarget(typeSystemContext, type, kotlinTypeMarker2);
                    if (typeParameterForArgumentInBaseIfItEqualToTarget != null) {
                        return typeParameterForArgumentInBaseIfItEqualToTarget;
                    }
                }
            }
            i++;
        }
        return typeSystemContext.getParameter(typeSystemContext.typeConstructor(kotlinTypeMarker), i);
    }

    private final List<SimpleTypeMarker> collectAllSupertypesWithGivenTypeConstructor(TypeCheckerState typeCheckerState, SimpleTypeMarker simpleTypeMarker, TypeConstructorMarker typeConstructorMarker) {
        TypeCheckerState.SupertypesPolicy supertypesPolicy;
        SimpleTypeMarker simpleTypeMarker2 = simpleTypeMarker;
        TypeConstructorMarker typeConstructorMarker2 = typeConstructorMarker;
        TypeSystemContext typeSystemContext = typeCheckerState.getTypeSystemContext();
        List<SimpleTypeMarker> fastCorrespondingSupertypes = typeSystemContext.fastCorrespondingSupertypes(simpleTypeMarker2, typeConstructorMarker2);
        if (fastCorrespondingSupertypes != null) {
            return fastCorrespondingSupertypes;
        }
        if (!typeSystemContext.isClassTypeConstructor(typeConstructorMarker2) && typeSystemContext.isClassType(simpleTypeMarker2)) {
            return CollectionsKt.emptyList();
        }
        if (!typeSystemContext.isCommonFinalClassConstructor(typeConstructorMarker2)) {
            List<SimpleTypeMarker> smartList = new SmartList<>();
            typeCheckerState.initialize();
            ArrayDeque<SimpleTypeMarker> supertypesDeque = typeCheckerState.getSupertypesDeque();
            Intrinsics.checkNotNull(supertypesDeque);
            Set<SimpleTypeMarker> supertypesSet = typeCheckerState.getSupertypesSet();
            Intrinsics.checkNotNull(supertypesSet);
            supertypesDeque.push(simpleTypeMarker2);
            while (!supertypesDeque.isEmpty()) {
                if (supertypesSet.size() <= 1000) {
                    SimpleTypeMarker pop = supertypesDeque.pop();
                    Intrinsics.checkNotNull(pop);
                    if (supertypesSet.add(pop)) {
                        SimpleTypeMarker captureFromArguments = typeSystemContext.captureFromArguments(pop, CaptureStatus.FOR_SUBTYPING);
                        if (captureFromArguments == null) {
                            captureFromArguments = pop;
                        }
                        if (typeSystemContext.areEqualTypeConstructors(typeSystemContext.typeConstructor(captureFromArguments), typeConstructorMarker2)) {
                            smartList.add(captureFromArguments);
                            supertypesPolicy = TypeCheckerState.SupertypesPolicy.None.INSTANCE;
                        } else if (typeSystemContext.argumentsCount(captureFromArguments) == 0) {
                            supertypesPolicy = TypeCheckerState.SupertypesPolicy.LowerIfFlexible.INSTANCE;
                        } else {
                            supertypesPolicy = typeCheckerState.getTypeSystemContext().substitutionSupertypePolicy(captureFromArguments);
                        }
                        if (!(!Intrinsics.areEqual((Object) supertypesPolicy, (Object) TypeCheckerState.SupertypesPolicy.None.INSTANCE))) {
                            supertypesPolicy = null;
                        }
                        if (supertypesPolicy != null) {
                            TypeSystemContext typeSystemContext2 = typeCheckerState.getTypeSystemContext();
                            for (KotlinTypeMarker transformType : typeSystemContext2.supertypes(typeSystemContext2.typeConstructor(pop))) {
                                supertypesDeque.add(supertypesPolicy.transformType(typeCheckerState, transformType));
                            }
                        }
                    }
                    TypeCheckerState typeCheckerState2 = typeCheckerState;
                } else {
                    throw new IllegalStateException(("Too many supertypes for type: " + simpleTypeMarker2 + ". Supertypes = " + CollectionsKt.joinToString$default(supertypesSet, (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 63, (Object) null)).toString());
                }
            }
            TypeCheckerState typeCheckerState3 = typeCheckerState;
            typeCheckerState.clear();
            return smartList;
        } else if (!typeSystemContext.areEqualTypeConstructors(typeSystemContext.typeConstructor(simpleTypeMarker2), typeConstructorMarker2)) {
            return CollectionsKt.emptyList();
        } else {
            SimpleTypeMarker captureFromArguments2 = typeSystemContext.captureFromArguments(simpleTypeMarker2, CaptureStatus.FOR_SUBTYPING);
            if (captureFromArguments2 != null) {
                simpleTypeMarker2 = captureFromArguments2;
            }
            return CollectionsKt.listOf(simpleTypeMarker2);
        }
    }

    private final List<SimpleTypeMarker> collectAndFilter(TypeCheckerState typeCheckerState, SimpleTypeMarker simpleTypeMarker, TypeConstructorMarker typeConstructorMarker) {
        return selectOnlyPureKotlinSupertypes(typeCheckerState, collectAllSupertypesWithGivenTypeConstructor(typeCheckerState, simpleTypeMarker, typeConstructorMarker));
    }

    private final List<SimpleTypeMarker> selectOnlyPureKotlinSupertypes(TypeCheckerState typeCheckerState, List<? extends SimpleTypeMarker> list) {
        TypeSystemContext typeSystemContext = typeCheckerState.getTypeSystemContext();
        if (list.size() < 2) {
            return list;
        }
        Collection arrayList = new ArrayList();
        for (Object next : list) {
            TypeArgumentListMarker asArgumentList = typeSystemContext.asArgumentList((SimpleTypeMarker) next);
            int size = typeSystemContext.size(asArgumentList);
            int i = 0;
            while (true) {
                if (i < size) {
                    if (typeSystemContext.asFlexibleType(typeSystemContext.getType(typeSystemContext.get(asArgumentList, i))) != null) {
                        break;
                    }
                    i++;
                } else {
                    arrayList.add(next);
                    break;
                }
            }
        }
        List<SimpleTypeMarker> list2 = (List) arrayList;
        return list2.isEmpty() ^ true ? list2 : list;
    }

    public final List<SimpleTypeMarker> findCorrespondingSupertypes(TypeCheckerState typeCheckerState, SimpleTypeMarker simpleTypeMarker, TypeConstructorMarker typeConstructorMarker) {
        TypeCheckerState.SupertypesPolicy supertypesPolicy;
        TypeCheckerState typeCheckerState2 = typeCheckerState;
        SimpleTypeMarker simpleTypeMarker2 = simpleTypeMarker;
        TypeConstructorMarker typeConstructorMarker2 = typeConstructorMarker;
        Intrinsics.checkNotNullParameter(typeCheckerState2, OAuth2RequestParameters.State);
        Intrinsics.checkNotNullParameter(simpleTypeMarker2, "subType");
        Intrinsics.checkNotNullParameter(typeConstructorMarker2, "superConstructor");
        TypeSystemContext typeSystemContext = typeCheckerState.getTypeSystemContext();
        if (typeSystemContext.isClassType(simpleTypeMarker2)) {
            return INSTANCE.collectAndFilter(typeCheckerState2, simpleTypeMarker2, typeConstructorMarker2);
        }
        if (!typeSystemContext.isClassTypeConstructor(typeConstructorMarker2) && !typeSystemContext.isIntegerLiteralTypeConstructor(typeConstructorMarker2)) {
            return INSTANCE.collectAllSupertypesWithGivenTypeConstructor(typeCheckerState2, simpleTypeMarker2, typeConstructorMarker2);
        }
        SmartList<SimpleTypeMarker> smartList = new SmartList<>();
        typeCheckerState.initialize();
        ArrayDeque<SimpleTypeMarker> supertypesDeque = typeCheckerState.getSupertypesDeque();
        Intrinsics.checkNotNull(supertypesDeque);
        Set<SimpleTypeMarker> supertypesSet = typeCheckerState.getSupertypesSet();
        Intrinsics.checkNotNull(supertypesSet);
        supertypesDeque.push(simpleTypeMarker2);
        while (!supertypesDeque.isEmpty()) {
            if (supertypesSet.size() <= 1000) {
                SimpleTypeMarker pop = supertypesDeque.pop();
                Intrinsics.checkNotNull(pop);
                if (supertypesSet.add(pop)) {
                    if (typeSystemContext.isClassType(pop)) {
                        smartList.add(pop);
                        supertypesPolicy = TypeCheckerState.SupertypesPolicy.None.INSTANCE;
                    } else {
                        supertypesPolicy = TypeCheckerState.SupertypesPolicy.LowerIfFlexible.INSTANCE;
                    }
                    if (!(!Intrinsics.areEqual((Object) supertypesPolicy, (Object) TypeCheckerState.SupertypesPolicy.None.INSTANCE))) {
                        supertypesPolicy = null;
                    }
                    if (supertypesPolicy != null) {
                        TypeSystemContext typeSystemContext2 = typeCheckerState.getTypeSystemContext();
                        for (KotlinTypeMarker transformType : typeSystemContext2.supertypes(typeSystemContext2.typeConstructor(pop))) {
                            supertypesDeque.add(supertypesPolicy.transformType(typeCheckerState2, transformType));
                        }
                    }
                }
            } else {
                throw new IllegalStateException(("Too many supertypes for type: " + simpleTypeMarker2 + ". Supertypes = " + CollectionsKt.joinToString$default(supertypesSet, (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 63, (Object) null)).toString());
            }
        }
        typeCheckerState.clear();
        Collection arrayList = new ArrayList();
        for (SimpleTypeMarker simpleTypeMarker3 : smartList) {
            AbstractTypeChecker abstractTypeChecker = INSTANCE;
            Intrinsics.checkNotNull(simpleTypeMarker3);
            CollectionsKt.addAll(arrayList, abstractTypeChecker.collectAndFilter(typeCheckerState2, simpleTypeMarker3, typeConstructorMarker2));
        }
        return (List) arrayList;
    }
}
