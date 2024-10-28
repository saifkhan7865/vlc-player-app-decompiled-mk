package com.typesafe.config.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

abstract class ConfigNodeComplexValue extends AbstractConfigNodeValue {
    protected final ArrayList<AbstractConfigNode> children;

    /* access modifiers changed from: package-private */
    public abstract ConfigNodeComplexValue newNode(Collection<AbstractConfigNode> collection);

    ConfigNodeComplexValue(Collection<AbstractConfigNode> collection) {
        this.children = new ArrayList<>(collection);
    }

    public final Collection<AbstractConfigNode> children() {
        return this.children;
    }

    /* access modifiers changed from: protected */
    public Collection<Token> tokens() {
        ArrayList arrayList = new ArrayList();
        Iterator<AbstractConfigNode> it = this.children.iterator();
        while (it.hasNext()) {
            arrayList.addAll(it.next().tokens());
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public ConfigNodeComplexValue indentText(AbstractConfigNode abstractConfigNode) {
        ArrayList arrayList = new ArrayList(this.children);
        int i = 0;
        while (i < arrayList.size()) {
            AbstractConfigNode abstractConfigNode2 = (AbstractConfigNode) arrayList.get(i);
            if ((abstractConfigNode2 instanceof ConfigNodeSingleToken) && Tokens.isNewline(((ConfigNodeSingleToken) abstractConfigNode2).token())) {
                i++;
                arrayList.add(i, abstractConfigNode);
            } else if (abstractConfigNode2 instanceof ConfigNodeField) {
                ConfigNodeField configNodeField = (ConfigNodeField) abstractConfigNode2;
                AbstractConfigNodeValue value = configNodeField.value();
                if (value instanceof ConfigNodeComplexValue) {
                    arrayList.set(i, configNodeField.replaceValue(((ConfigNodeComplexValue) value).indentText(abstractConfigNode)));
                }
            } else if (abstractConfigNode2 instanceof ConfigNodeComplexValue) {
                arrayList.set(i, ((ConfigNodeComplexValue) abstractConfigNode2).indentText(abstractConfigNode));
            }
            i++;
        }
        return newNode(arrayList);
    }
}
