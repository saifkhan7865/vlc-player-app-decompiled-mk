package com.typesafe.config;

import com.google.common.net.HttpHeaders;
import com.typesafe.config.impl.ConfigImplUtil;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import org.fusesource.jansi.AnsiRenderer;

public abstract class ConfigException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 1;
    private final transient ConfigOrigin origin;

    protected ConfigException(ConfigOrigin configOrigin, String str, Throwable th) {
        super(configOrigin.description() + ": " + str, th);
        this.origin = configOrigin;
    }

    protected ConfigException(ConfigOrigin configOrigin, String str) {
        this(configOrigin.description() + ": " + str, (Throwable) null);
    }

    protected ConfigException(String str, Throwable th) {
        super(str, th);
        this.origin = null;
    }

    protected ConfigException(String str) {
        this(str, (Throwable) null);
    }

    public ConfigOrigin origin() {
        return this.origin;
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        ConfigImplUtil.writeOrigin(objectOutputStream, this.origin);
    }

    /* access modifiers changed from: private */
    public static <T> void setOriginField(T t, Class<T> cls, ConfigOrigin configOrigin) throws IOException {
        try {
            Field declaredField = cls.getDeclaredField(HttpHeaders.ReferrerPolicyValues.ORIGIN);
            declaredField.setAccessible(true);
            try {
                declaredField.set(t, configOrigin);
            } catch (IllegalArgumentException e) {
                throw new IOException("unable to set origin field", e);
            } catch (IllegalAccessException e2) {
                throw new IOException("unable to set origin field", e2);
            }
        } catch (NoSuchFieldException e3) {
            throw new IOException(cls.getSimpleName() + " has no origin field?", e3);
        } catch (SecurityException e4) {
            throw new IOException("unable to fill out origin field in " + cls.getSimpleName(), e4);
        }
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        setOriginField(this, ConfigException.class, ConfigImplUtil.readOrigin(objectInputStream));
    }

    public static class WrongType extends ConfigException {
        private static final long serialVersionUID = 1;

        public WrongType(ConfigOrigin configOrigin, String str, String str2, String str3, Throwable th) {
            super(configOrigin, str + " has type " + str3 + " rather than " + str2, th);
        }

        public WrongType(ConfigOrigin configOrigin, String str, String str2, String str3) {
            this(configOrigin, str, str2, str3, (Throwable) null);
        }

        public WrongType(ConfigOrigin configOrigin, String str, Throwable th) {
            super(configOrigin, str, th);
        }

        public WrongType(ConfigOrigin configOrigin, String str) {
            super(configOrigin, str, (Throwable) null);
        }
    }

    public static class Missing extends ConfigException {
        private static final long serialVersionUID = 1;

        public Missing(String str, Throwable th) {
            super("No configuration setting found for key '" + str + "'", th);
        }

        public Missing(ConfigOrigin configOrigin, String str) {
            this(configOrigin, "No configuration setting found for key '" + str + "'", (Throwable) null);
        }

        public Missing(String str) {
            this(str, (Throwable) null);
        }

        protected Missing(ConfigOrigin configOrigin, String str, Throwable th) {
            super(configOrigin, str, th);
        }
    }

    public static class Null extends Missing {
        private static final long serialVersionUID = 1;

        private static String makeMessage(String str, String str2) {
            if (str2 != null) {
                return "Configuration key '" + str + "' is set to null but expected " + str2;
            }
            return "Configuration key '" + str + "' is null";
        }

        public Null(ConfigOrigin configOrigin, String str, String str2, Throwable th) {
            super(configOrigin, makeMessage(str, str2), th);
        }

        public Null(ConfigOrigin configOrigin, String str, String str2) {
            this(configOrigin, str, str2, (Throwable) null);
        }
    }

    public static class BadValue extends ConfigException {
        private static final long serialVersionUID = 1;

        public BadValue(ConfigOrigin configOrigin, String str, String str2, Throwable th) {
            super(configOrigin, "Invalid value at '" + str + "': " + str2, th);
        }

        public BadValue(ConfigOrigin configOrigin, String str, String str2) {
            this(configOrigin, str, str2, (Throwable) null);
        }

        public BadValue(String str, String str2, Throwable th) {
            super("Invalid value at '" + str + "': " + str2, th);
        }

        public BadValue(String str, String str2) {
            this(str, str2, (Throwable) null);
        }
    }

    public static class BadPath extends ConfigException {
        private static final long serialVersionUID = 1;

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public BadPath(com.typesafe.config.ConfigOrigin r3, java.lang.String r4, java.lang.String r5, java.lang.Throwable r6) {
            /*
                r2 = this;
                if (r4 == 0) goto L_0x0018
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                java.lang.String r1 = "Invalid path '"
                r0.<init>(r1)
                r0.append(r4)
                java.lang.String r4 = "': "
                r0.append(r4)
                r0.append(r5)
                java.lang.String r5 = r0.toString()
            L_0x0018:
                r2.<init>(r3, r5, r6)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.typesafe.config.ConfigException.BadPath.<init>(com.typesafe.config.ConfigOrigin, java.lang.String, java.lang.String, java.lang.Throwable):void");
        }

        public BadPath(ConfigOrigin configOrigin, String str, String str2) {
            this(configOrigin, str, str2, (Throwable) null);
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public BadPath(java.lang.String r3, java.lang.String r4, java.lang.Throwable r5) {
            /*
                r2 = this;
                if (r3 == 0) goto L_0x0018
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                java.lang.String r1 = "Invalid path '"
                r0.<init>(r1)
                r0.append(r3)
                java.lang.String r3 = "': "
                r0.append(r3)
                r0.append(r4)
                java.lang.String r4 = r0.toString()
            L_0x0018:
                r2.<init>((java.lang.String) r4, (java.lang.Throwable) r5)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.typesafe.config.ConfigException.BadPath.<init>(java.lang.String, java.lang.String, java.lang.Throwable):void");
        }

        public BadPath(String str, String str2) {
            this(str, str2, (Throwable) null);
        }

        public BadPath(ConfigOrigin configOrigin, String str) {
            this(configOrigin, (String) null, str);
        }
    }

    public static class BugOrBroken extends ConfigException {
        private static final long serialVersionUID = 1;

        public BugOrBroken(String str, Throwable th) {
            super(str, th);
        }

        public BugOrBroken(String str) {
            this(str, (Throwable) null);
        }
    }

    public static class IO extends ConfigException {
        private static final long serialVersionUID = 1;

        public IO(ConfigOrigin configOrigin, String str, Throwable th) {
            super(configOrigin, str, th);
        }

        public IO(ConfigOrigin configOrigin, String str) {
            this(configOrigin, str, (Throwable) null);
        }
    }

    public static class Parse extends ConfigException {
        private static final long serialVersionUID = 1;

        public Parse(ConfigOrigin configOrigin, String str, Throwable th) {
            super(configOrigin, str, th);
        }

        public Parse(ConfigOrigin configOrigin, String str) {
            this(configOrigin, str, (Throwable) null);
        }
    }

    public static class UnresolvedSubstitution extends Parse {
        private static final long serialVersionUID = 1;
        private final String detail;

        public UnresolvedSubstitution(ConfigOrigin configOrigin, String str, Throwable th) {
            super(configOrigin, "Could not resolve substitution to a value: " + str, th);
            this.detail = str;
        }

        public UnresolvedSubstitution(ConfigOrigin configOrigin, String str) {
            this(configOrigin, str, (Throwable) null);
        }

        private UnresolvedSubstitution(UnresolvedSubstitution unresolvedSubstitution, ConfigOrigin configOrigin, String str) {
            super(configOrigin, str, unresolvedSubstitution);
            this.detail = unresolvedSubstitution.detail;
        }

        public UnresolvedSubstitution addExtraDetail(String str) {
            return new UnresolvedSubstitution(this, origin(), String.format(str, new Object[]{this.detail}));
        }
    }

    public static class NotResolved extends BugOrBroken {
        private static final long serialVersionUID = 1;

        public NotResolved(String str, Throwable th) {
            super(str, th);
        }

        public NotResolved(String str) {
            this(str, (Throwable) null);
        }
    }

    public static class ValidationProblem implements Serializable {
        private final transient ConfigOrigin origin;
        private final String path;
        private final String problem;

        public ValidationProblem(String str, ConfigOrigin configOrigin, String str2) {
            this.path = str;
            this.origin = configOrigin;
            this.problem = str2;
        }

        public String path() {
            return this.path;
        }

        public ConfigOrigin origin() {
            return this.origin;
        }

        public String problem() {
            return this.problem;
        }

        private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
            objectOutputStream.defaultWriteObject();
            ConfigImplUtil.writeOrigin(objectOutputStream, this.origin);
        }

        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            objectInputStream.defaultReadObject();
            ConfigException.setOriginField(this, ValidationProblem.class, ConfigImplUtil.readOrigin(objectInputStream));
        }

        public String toString() {
            return "ValidationProblem(" + this.path + AnsiRenderer.CODE_LIST_SEPARATOR + this.origin + AnsiRenderer.CODE_LIST_SEPARATOR + this.problem + ")";
        }
    }

    public static class ValidationFailed extends ConfigException {
        private static final long serialVersionUID = 1;
        private final Iterable<ValidationProblem> problems;

        public ValidationFailed(Iterable<ValidationProblem> iterable) {
            super(makeMessage(iterable), (Throwable) null);
            this.problems = iterable;
        }

        public Iterable<ValidationProblem> problems() {
            return this.problems;
        }

        private static String makeMessage(Iterable<ValidationProblem> iterable) {
            StringBuilder sb = new StringBuilder();
            for (ValidationProblem next : iterable) {
                sb.append(next.origin().description());
                sb.append(": ");
                sb.append(next.path());
                sb.append(": ");
                sb.append(next.problem());
                sb.append(", ");
            }
            if (sb.length() != 0) {
                sb.setLength(sb.length() - 2);
                return sb.toString();
            }
            throw new BugOrBroken("ValidationFailed must have a non-empty list of problems");
        }
    }

    public static class BadBean extends BugOrBroken {
        private static final long serialVersionUID = 1;

        public BadBean(String str, Throwable th) {
            super(str, th);
        }

        public BadBean(String str) {
            this(str, (Throwable) null);
        }
    }

    public static class Generic extends ConfigException {
        private static final long serialVersionUID = 1;

        public Generic(String str, Throwable th) {
            super(str, th);
        }

        public Generic(String str) {
            this(str, (Throwable) null);
        }
    }
}
