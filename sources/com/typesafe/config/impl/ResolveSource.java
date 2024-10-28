package com.typesafe.config.impl;

import com.typesafe.config.ConfigException;
import com.typesafe.config.impl.AbstractConfigValue;

final class ResolveSource {
    final Node<Container> pathFromRoot;
    final AbstractConfigObject root;

    ResolveSource(AbstractConfigObject abstractConfigObject, Node<Container> node) {
        this.root = abstractConfigObject;
        this.pathFromRoot = node;
    }

    ResolveSource(AbstractConfigObject abstractConfigObject) {
        this.root = abstractConfigObject;
        this.pathFromRoot = null;
    }

    private AbstractConfigObject rootMustBeObj(Container container) {
        if (container instanceof AbstractConfigObject) {
            return (AbstractConfigObject) container;
        }
        return SimpleConfigObject.empty();
    }

    private static ResultWithPath findInObject(AbstractConfigObject abstractConfigObject, ResolveContext resolveContext, Path path) throws AbstractConfigValue.NotPossibleToResolve {
        if (ConfigImpl.traceSubstitutionsEnabled()) {
            ConfigImpl.trace("*** finding '" + path + "' in " + abstractConfigObject);
        }
        Path restrictToChild = resolveContext.restrictToChild();
        ResolveResult<? extends AbstractConfigValue> resolve = resolveContext.restrict(path).resolve(abstractConfigObject, new ResolveSource(abstractConfigObject));
        ResolveContext restrict = resolve.context.restrict(restrictToChild);
        if (resolve.value instanceof AbstractConfigObject) {
            ValueWithPath findInObject = findInObject((AbstractConfigObject) resolve.value, path);
            return new ResultWithPath(ResolveResult.make(restrict, findInObject.value), findInObject.pathFromRoot);
        }
        throw new ConfigException.BugOrBroken("resolved object to non-object " + abstractConfigObject + " to " + resolve);
    }

    private static ValueWithPath findInObject(AbstractConfigObject abstractConfigObject, Path path) {
        try {
            return findInObject(abstractConfigObject, path, (Node<Container>) null);
        } catch (ConfigException.NotResolved e) {
            throw ConfigImpl.improveNotResolved(path, e);
        }
    }

    private static ValueWithPath findInObject(AbstractConfigObject abstractConfigObject, Path path, Node<Container> node) {
        String first = path.first();
        Path remainder = path.remainder();
        if (ConfigImpl.traceSubstitutionsEnabled()) {
            ConfigImpl.trace("*** looking up '" + first + "' in " + abstractConfigObject);
        }
        AbstractConfigValue attemptPeekWithPartialResolve = abstractConfigObject.attemptPeekWithPartialResolve(first);
        Node<Container> node2 = node == null ? new Node<>(abstractConfigObject) : node.prepend(abstractConfigObject);
        if (remainder == null) {
            return new ValueWithPath(attemptPeekWithPartialResolve, node2);
        }
        if (attemptPeekWithPartialResolve instanceof AbstractConfigObject) {
            return findInObject((AbstractConfigObject) attemptPeekWithPartialResolve, remainder, node2);
        }
        return new ValueWithPath((AbstractConfigValue) null, node2);
    }

    /* access modifiers changed from: package-private */
    public ResultWithPath lookupSubst(ResolveContext resolveContext, SubstitutionExpression substitutionExpression, int i) throws AbstractConfigValue.NotPossibleToResolve {
        if (ConfigImpl.traceSubstitutionsEnabled()) {
            int depth = resolveContext.depth();
            ConfigImpl.trace(depth, "searching for " + substitutionExpression);
        }
        if (ConfigImpl.traceSubstitutionsEnabled()) {
            int depth2 = resolveContext.depth();
            ConfigImpl.trace(depth2, substitutionExpression + " - looking up relative to file it occurred in");
        }
        ResultWithPath findInObject = findInObject(this.root, resolveContext, substitutionExpression.path());
        if (findInObject.result.value == null) {
            Path subPath = substitutionExpression.path().subPath(i);
            if (i > 0) {
                if (ConfigImpl.traceSubstitutionsEnabled()) {
                    int depth3 = findInObject.result.context.depth();
                    ConfigImpl.trace(depth3, subPath + " - looking up relative to parent file");
                }
                findInObject = findInObject(this.root, findInObject.result.context, subPath);
            }
            if (findInObject.result.value == null && findInObject.result.context.options().getUseSystemEnvironment()) {
                if (ConfigImpl.traceSubstitutionsEnabled()) {
                    int depth4 = findInObject.result.context.depth();
                    ConfigImpl.trace(depth4, subPath + " - looking up in system environment");
                }
                findInObject = findInObject(ConfigImpl.envVariablesAsConfigObject(), resolveContext, subPath);
            }
        }
        if (ConfigImpl.traceSubstitutionsEnabled()) {
            int depth5 = findInObject.result.context.depth();
            ConfigImpl.trace(depth5, "resolved to " + findInObject);
        }
        return findInObject;
    }

    /* access modifiers changed from: package-private */
    public ResolveSource pushParent(Container container) {
        if (container != null) {
            if (ConfigImpl.traceSubstitutionsEnabled()) {
                StringBuilder sb = new StringBuilder("pushing parent ");
                sb.append(container);
                sb.append(" ==root ");
                sb.append(container == this.root);
                sb.append(" onto ");
                sb.append(this);
                ConfigImpl.trace(sb.toString());
            }
            Node<Container> node = this.pathFromRoot;
            if (node == null) {
                AbstractConfigObject abstractConfigObject = this.root;
                if (container == abstractConfigObject) {
                    return new ResolveSource(abstractConfigObject, new Node(container));
                }
                if (ConfigImpl.traceSubstitutionsEnabled() && this.root.hasDescendant((AbstractConfigValue) container)) {
                    ConfigImpl.trace("***** BUG ***** tried to push parent " + container + " without having a path to it in " + this);
                }
                return this;
            }
            Container head = node.head();
            if (ConfigImpl.traceSubstitutionsEnabled() && head != null && !head.hasDescendant((AbstractConfigValue) container)) {
                ConfigImpl.trace("***** BUG ***** trying to push non-child of " + head + ", non-child was " + container);
            }
            return new ResolveSource(this.root, this.pathFromRoot.prepend(container));
        }
        throw new ConfigException.BugOrBroken("can't push null parent");
    }

    /* access modifiers changed from: package-private */
    public ResolveSource resetParents() {
        if (this.pathFromRoot == null) {
            return this;
        }
        return new ResolveSource(this.root);
    }

    private static Node<Container> replace(Node<Container> node, Container container, AbstractConfigValue abstractConfigValue) {
        Container head = node.head();
        if (head == container) {
            Container head2 = node.tail() == null ? null : node.tail().head();
            if (abstractConfigValue == null || !(abstractConfigValue instanceof Container)) {
                if (head2 == null) {
                    return null;
                }
                return replace(node.tail(), head2, head2.replaceChild((AbstractConfigValue) container, (AbstractConfigValue) null));
            } else if (head2 == null) {
                return new Node<>((Container) abstractConfigValue);
            } else {
                Node<Container> replace = replace(node.tail(), head2, head2.replaceChild((AbstractConfigValue) container, abstractConfigValue));
                if (replace != null) {
                    return replace.prepend((Container) abstractConfigValue);
                }
                return new Node<>((Container) abstractConfigValue);
            }
        } else {
            throw new ConfigException.BugOrBroken("Can only replace() the top node we're resolving; had " + head + " on top and tried to replace " + container + " overall list was " + node);
        }
    }

    /* access modifiers changed from: package-private */
    public ResolveSource replaceCurrentParent(Container container, Container container2) {
        if (ConfigImpl.traceSubstitutionsEnabled()) {
            ConfigImpl.trace("replaceCurrentParent old " + container + "@" + System.identityHashCode(container) + " replacement " + container2 + "@" + System.identityHashCode(container) + " in " + this);
        }
        if (container == container2) {
            return this;
        }
        Node<Container> node = this.pathFromRoot;
        if (node != null) {
            Node<Container> replace = replace(node, container, (AbstractConfigValue) container2);
            if (ConfigImpl.traceSubstitutionsEnabled()) {
                ConfigImpl.trace("replaced " + container + " with " + container2 + " in " + this);
                StringBuilder sb = new StringBuilder("path was: ");
                sb.append(this.pathFromRoot);
                sb.append(" is now ");
                sb.append(replace);
                ConfigImpl.trace(sb.toString());
            }
            if (replace != null) {
                return new ResolveSource((AbstractConfigObject) replace.last(), replace);
            }
            return new ResolveSource(SimpleConfigObject.empty());
        } else if (container == this.root) {
            return new ResolveSource(rootMustBeObj(container2));
        } else {
            throw new ConfigException.BugOrBroken("attempt to replace root " + this.root + " with " + container2);
        }
    }

    /* access modifiers changed from: package-private */
    public ResolveSource replaceWithinCurrentParent(AbstractConfigValue abstractConfigValue, AbstractConfigValue abstractConfigValue2) {
        if (ConfigImpl.traceSubstitutionsEnabled()) {
            ConfigImpl.trace("replaceWithinCurrentParent old " + abstractConfigValue + "@" + System.identityHashCode(abstractConfigValue) + " replacement " + abstractConfigValue2 + "@" + System.identityHashCode(abstractConfigValue) + " in " + this);
        }
        if (abstractConfigValue == abstractConfigValue2) {
            return this;
        }
        Node<Container> node = this.pathFromRoot;
        if (node != null) {
            Container head = node.head();
            AbstractConfigValue replaceChild = head.replaceChild(abstractConfigValue, abstractConfigValue2);
            return replaceCurrentParent(head, replaceChild instanceof Container ? (Container) replaceChild : null);
        } else if (abstractConfigValue == this.root && (abstractConfigValue2 instanceof Container)) {
            return new ResolveSource(rootMustBeObj((Container) abstractConfigValue2));
        } else {
            throw new ConfigException.BugOrBroken("replace in parent not possible " + abstractConfigValue + " with " + abstractConfigValue2 + " in " + this);
        }
    }

    public String toString() {
        return "ResolveSource(root=" + this.root + ", pathFromRoot=" + this.pathFromRoot + ")";
    }

    static final class Node<T> {
        final Node<T> next;
        final T value;

        Node(T t, Node<T> node) {
            this.value = t;
            this.next = node;
        }

        Node(T t) {
            this(t, (Node) null);
        }

        /* access modifiers changed from: package-private */
        public Node<T> prepend(T t) {
            return new Node<>(t, this);
        }

        /* access modifiers changed from: package-private */
        public T head() {
            return this.value;
        }

        /* access modifiers changed from: package-private */
        public Node<T> tail() {
            return this.next;
        }

        /* access modifiers changed from: package-private */
        public T last() {
            Node<T> node = this;
            while (true) {
                Node<T> node2 = node.next;
                if (node2 == null) {
                    return node.value;
                }
                node = node2;
            }
        }

        /* access modifiers changed from: package-private */
        public Node<T> reverse() {
            if (this.next == null) {
                return this;
            }
            Node<T> node = new Node<>(this.value);
            for (Node<T> node2 = this.next; node2 != null; node2 = node2.next) {
                node = node.prepend(node2.value);
            }
            return node;
        }

        public String toString() {
            StringBuffer stringBuffer = new StringBuffer("[");
            for (Node<T> reverse = reverse(); reverse != null; reverse = reverse.next) {
                stringBuffer.append(reverse.value.toString());
                if (reverse.next != null) {
                    stringBuffer.append(" <= ");
                }
            }
            stringBuffer.append("]");
            return stringBuffer.toString();
        }
    }

    static final class ValueWithPath {
        final Node<Container> pathFromRoot;
        final AbstractConfigValue value;

        ValueWithPath(AbstractConfigValue abstractConfigValue, Node<Container> node) {
            this.value = abstractConfigValue;
            this.pathFromRoot = node;
        }

        public String toString() {
            return "ValueWithPath(value=" + this.value + ", pathFromRoot=" + this.pathFromRoot + ")";
        }
    }

    static final class ResultWithPath {
        final Node<Container> pathFromRoot;
        final ResolveResult<? extends AbstractConfigValue> result;

        ResultWithPath(ResolveResult<? extends AbstractConfigValue> resolveResult, Node<Container> node) {
            this.result = resolveResult;
            this.pathFromRoot = node;
        }

        public String toString() {
            return "ResultWithPath(result=" + this.result + ", pathFromRoot=" + this.pathFromRoot + ")";
        }
    }
}
