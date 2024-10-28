package com.typesafe.config.impl;

import com.typesafe.config.ConfigOrigin;
import com.typesafe.config.ConfigSyntax;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.fusesource.jansi.AnsiRenderer;

final class ConfigNodeObject extends ConfigNodeComplexValue {
    ConfigNodeObject(Collection<AbstractConfigNode> collection) {
        super(collection);
    }

    /* access modifiers changed from: protected */
    public ConfigNodeObject newNode(Collection<AbstractConfigNode> collection) {
        return new ConfigNodeObject(collection);
    }

    public boolean hasValue(Path path) {
        Iterator it = this.children.iterator();
        while (it.hasNext()) {
            AbstractConfigNode abstractConfigNode = (AbstractConfigNode) it.next();
            if (abstractConfigNode instanceof ConfigNodeField) {
                ConfigNodeField configNodeField = (ConfigNodeField) abstractConfigNode;
                Path value = configNodeField.path().value();
                if (value.equals(path) || value.startsWith(path) || (path.startsWith(value) && (configNodeField.value() instanceof ConfigNodeObject) && ((ConfigNodeObject) configNodeField.value()).hasValue(path.subPath(value.length())))) {
                    return true;
                }
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00f4, code lost:
        if (r5.equals(r8.children.get(r1)) == false) goto L_0x00bf;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.typesafe.config.impl.ConfigNodeObject changeValueOnPath(com.typesafe.config.impl.Path r9, com.typesafe.config.impl.AbstractConfigNodeValue r10, com.typesafe.config.ConfigSyntax r11) {
        /*
            r8 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            java.util.ArrayList<com.typesafe.config.impl.AbstractConfigNode> r1 = r8.children
            r0.<init>(r1)
            int r1 = r0.size()
            r2 = 1
            int r1 = r1 - r2
            r3 = 0
            r4 = r10
        L_0x000f:
            if (r1 < 0) goto L_0x00fc
            java.lang.Object r5 = r0.get(r1)
            boolean r5 = r5 instanceof com.typesafe.config.impl.ConfigNodeSingleToken
            if (r5 == 0) goto L_0x0032
            java.lang.Object r5 = r0.get(r1)
            com.typesafe.config.impl.ConfigNodeSingleToken r5 = (com.typesafe.config.impl.ConfigNodeSingleToken) r5
            com.typesafe.config.impl.Token r5 = r5.token()
            com.typesafe.config.ConfigSyntax r6 = com.typesafe.config.ConfigSyntax.JSON
            if (r11 != r6) goto L_0x00f8
            if (r3 != 0) goto L_0x00f8
            com.typesafe.config.impl.Token r6 = com.typesafe.config.impl.Tokens.COMMA
            if (r5 != r6) goto L_0x00f8
            r0.remove(r1)
            goto L_0x00f8
        L_0x0032:
            java.lang.Object r5 = r0.get(r1)
            boolean r5 = r5 instanceof com.typesafe.config.impl.ConfigNodeField
            if (r5 != 0) goto L_0x003c
            goto L_0x00f8
        L_0x003c:
            java.lang.Object r5 = r0.get(r1)
            com.typesafe.config.impl.ConfigNodeField r5 = (com.typesafe.config.impl.ConfigNodeField) r5
            com.typesafe.config.impl.ConfigNodePath r6 = r5.path()
            com.typesafe.config.impl.Path r6 = r6.value()
            if (r4 != 0) goto L_0x0052
            boolean r7 = r6.equals(r9)
            if (r7 != 0) goto L_0x005e
        L_0x0052:
            boolean r7 = r6.startsWith(r9)
            if (r7 == 0) goto L_0x0087
            boolean r7 = r6.equals(r9)
            if (r7 != 0) goto L_0x0087
        L_0x005e:
            r0.remove(r1)
        L_0x0061:
            int r5 = r0.size()
            if (r1 >= r5) goto L_0x00f8
            java.lang.Object r5 = r0.get(r1)
            boolean r5 = r5 instanceof com.typesafe.config.impl.ConfigNodeSingleToken
            if (r5 == 0) goto L_0x00f8
            java.lang.Object r5 = r0.get(r1)
            com.typesafe.config.impl.ConfigNodeSingleToken r5 = (com.typesafe.config.impl.ConfigNodeSingleToken) r5
            com.typesafe.config.impl.Token r5 = r5.token()
            boolean r6 = com.typesafe.config.impl.Tokens.isIgnoredWhitespace(r5)
            if (r6 != 0) goto L_0x0083
            com.typesafe.config.impl.Token r6 = com.typesafe.config.impl.Tokens.COMMA
            if (r5 != r6) goto L_0x00f8
        L_0x0083:
            r0.remove(r1)
            goto L_0x0061
        L_0x0087:
            boolean r3 = r6.equals(r9)
            r7 = 0
            if (r3 == 0) goto L_0x00c1
            int r3 = r1 + -1
            if (r3 <= 0) goto L_0x0099
            java.lang.Object r3 = r0.get(r3)
            com.typesafe.config.impl.AbstractConfigNode r3 = (com.typesafe.config.impl.AbstractConfigNode) r3
            goto L_0x009a
        L_0x0099:
            r3 = r7
        L_0x009a:
            boolean r4 = r10 instanceof com.typesafe.config.impl.ConfigNodeComplexValue
            if (r4 == 0) goto L_0x00b7
            boolean r4 = r3 instanceof com.typesafe.config.impl.ConfigNodeSingleToken
            if (r4 == 0) goto L_0x00b7
            r4 = r3
            com.typesafe.config.impl.ConfigNodeSingleToken r4 = (com.typesafe.config.impl.ConfigNodeSingleToken) r4
            com.typesafe.config.impl.Token r4 = r4.token()
            boolean r4 = com.typesafe.config.impl.Tokens.isIgnoredWhitespace(r4)
            if (r4 == 0) goto L_0x00b7
            r4 = r10
            com.typesafe.config.impl.ConfigNodeComplexValue r4 = (com.typesafe.config.impl.ConfigNodeComplexValue) r4
            com.typesafe.config.impl.ConfigNodeComplexValue r3 = r4.indentText(r3)
            goto L_0x00b8
        L_0x00b7:
            r3 = r10
        L_0x00b8:
            com.typesafe.config.impl.ConfigNodeField r3 = r5.replaceValue(r3)
            r0.set(r1, r3)
        L_0x00bf:
            r4 = r7
            goto L_0x00f7
        L_0x00c1:
            boolean r3 = r9.startsWith(r6)
            if (r3 == 0) goto L_0x00f7
            com.typesafe.config.impl.AbstractConfigNodeValue r3 = r5.value()
            boolean r3 = r3 instanceof com.typesafe.config.impl.ConfigNodeObject
            if (r3 == 0) goto L_0x00f7
            int r3 = r6.length()
            com.typesafe.config.impl.Path r3 = r9.subPath(r3)
            com.typesafe.config.impl.AbstractConfigNodeValue r6 = r5.value()
            com.typesafe.config.impl.ConfigNodeObject r6 = (com.typesafe.config.impl.ConfigNodeObject) r6
            com.typesafe.config.impl.ConfigNodeObject r3 = r6.changeValueOnPath(r3, r4, r11)
            com.typesafe.config.impl.ConfigNodeField r3 = r5.replaceValue(r3)
            r0.set(r1, r3)
            if (r4 == 0) goto L_0x00f7
            java.util.ArrayList<com.typesafe.config.impl.AbstractConfigNode> r3 = r8.children
            java.lang.Object r3 = r3.get(r1)
            boolean r3 = r5.equals(r3)
            if (r3 != 0) goto L_0x00f7
            goto L_0x00bf
        L_0x00f7:
            r3 = 1
        L_0x00f8:
            int r1 = r1 + -1
            goto L_0x000f
        L_0x00fc:
            com.typesafe.config.impl.ConfigNodeObject r9 = new com.typesafe.config.impl.ConfigNodeObject
            r9.<init>(r0)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.typesafe.config.impl.ConfigNodeObject.changeValueOnPath(com.typesafe.config.impl.Path, com.typesafe.config.impl.AbstractConfigNodeValue, com.typesafe.config.ConfigSyntax):com.typesafe.config.impl.ConfigNodeObject");
    }

    public ConfigNodeObject setValueOnPath(String str, AbstractConfigNodeValue abstractConfigNodeValue) {
        return setValueOnPath(str, abstractConfigNodeValue, ConfigSyntax.CONF);
    }

    public ConfigNodeObject setValueOnPath(String str, AbstractConfigNodeValue abstractConfigNodeValue, ConfigSyntax configSyntax) {
        return setValueOnPath(PathParser.parsePathNode(str, configSyntax), abstractConfigNodeValue, configSyntax);
    }

    private ConfigNodeObject setValueOnPath(ConfigNodePath configNodePath, AbstractConfigNodeValue abstractConfigNodeValue, ConfigSyntax configSyntax) {
        ConfigNodeObject changeValueOnPath = changeValueOnPath(configNodePath.value(), abstractConfigNodeValue, configSyntax);
        return !changeValueOnPath.hasValue(configNodePath.value()) ? changeValueOnPath.addValueOnPath(configNodePath, abstractConfigNodeValue, configSyntax) : changeValueOnPath;
    }

    private Collection<AbstractConfigNode> indentation() {
        String str;
        int i;
        ArrayList arrayList = new ArrayList();
        if (this.children.isEmpty()) {
            return arrayList;
        }
        boolean z = false;
        for (int i2 = 0; i2 < this.children.size(); i2++) {
            if (!z) {
                if ((this.children.get(i2) instanceof ConfigNodeSingleToken) && Tokens.isNewline(((ConfigNodeSingleToken) this.children.get(i2)).token())) {
                    arrayList.add(new ConfigNodeSingleToken(Tokens.newLine((ConfigOrigin) null)));
                    z = true;
                }
            } else if ((this.children.get(i2) instanceof ConfigNodeSingleToken) && Tokens.isIgnoredWhitespace(((ConfigNodeSingleToken) this.children.get(i2)).token()) && (i = i2 + 1) < this.children.size() && ((this.children.get(i) instanceof ConfigNodeField) || (this.children.get(i) instanceof ConfigNodeInclude))) {
                arrayList.add(this.children.get(i2));
                return arrayList;
            }
        }
        if (arrayList.isEmpty()) {
            arrayList.add(new ConfigNodeSingleToken(Tokens.newIgnoredWhitespace((ConfigOrigin) null, AnsiRenderer.CODE_TEXT_SEPARATOR)));
        } else {
            AbstractConfigNode abstractConfigNode = (AbstractConfigNode) this.children.get(this.children.size() - 1);
            if ((abstractConfigNode instanceof ConfigNodeSingleToken) && ((ConfigNodeSingleToken) abstractConfigNode).token() == Tokens.CLOSE_CURLY) {
                AbstractConfigNode abstractConfigNode2 = (AbstractConfigNode) this.children.get(this.children.size() - 2);
                if (abstractConfigNode2 instanceof ConfigNodeSingleToken) {
                    ConfigNodeSingleToken configNodeSingleToken = (ConfigNodeSingleToken) abstractConfigNode2;
                    if (Tokens.isIgnoredWhitespace(configNodeSingleToken.token())) {
                        str = configNodeSingleToken.token().tokenText();
                        arrayList.add(new ConfigNodeSingleToken(Tokens.newIgnoredWhitespace((ConfigOrigin) null, str + "  ")));
                    }
                }
                str = "";
                arrayList.add(new ConfigNodeSingleToken(Tokens.newIgnoredWhitespace((ConfigOrigin) null, str + "  ")));
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public ConfigNodeObject addValueOnPath(ConfigNodePath configNodePath, AbstractConfigNodeValue abstractConfigNodeValue, ConfigSyntax configSyntax) {
        Path value = configNodePath.value();
        ArrayList arrayList = new ArrayList(this.children);
        ArrayList arrayList2 = new ArrayList(indentation());
        AbstractConfigNodeValue indentText = (!(abstractConfigNodeValue instanceof ConfigNodeComplexValue) || arrayList2.isEmpty()) ? abstractConfigNodeValue : ((ConfigNodeComplexValue) abstractConfigNodeValue).indentText((AbstractConfigNode) arrayList2.get(arrayList2.size() - 1));
        boolean z = false;
        boolean z2 = arrayList2.size() <= 0 || !(arrayList2.get(0) instanceof ConfigNodeSingleToken) || !Tokens.isNewline(((ConfigNodeSingleToken) arrayList2.get(0)).token());
        if (value.length() > 1) {
            for (int size = this.children.size() - 1; size >= 0; size--) {
                if (this.children.get(size) instanceof ConfigNodeField) {
                    ConfigNodeField configNodeField = (ConfigNodeField) this.children.get(size);
                    Path value2 = configNodeField.path().value();
                    if (value.startsWith(value2) && (configNodeField.value() instanceof ConfigNodeObject)) {
                        arrayList.set(size, configNodeField.replaceValue(((ConfigNodeObject) configNodeField.value()).addValueOnPath(configNodePath.subPath(value2.length()), abstractConfigNodeValue, configSyntax)));
                        return new ConfigNodeObject(arrayList);
                    }
                }
            }
        }
        if (!this.children.isEmpty() && (this.children.get(0) instanceof ConfigNodeSingleToken) && ((ConfigNodeSingleToken) this.children.get(0)).token() == Tokens.OPEN_CURLY) {
            z = true;
        }
        ArrayList arrayList3 = new ArrayList();
        arrayList3.addAll(arrayList2);
        arrayList3.add(configNodePath.first());
        arrayList3.add(new ConfigNodeSingleToken(Tokens.newIgnoredWhitespace((ConfigOrigin) null, AnsiRenderer.CODE_TEXT_SEPARATOR)));
        arrayList3.add(new ConfigNodeSingleToken(Tokens.COLON));
        arrayList3.add(new ConfigNodeSingleToken(Tokens.newIgnoredWhitespace((ConfigOrigin) null, AnsiRenderer.CODE_TEXT_SEPARATOR)));
        if (value.length() == 1) {
            arrayList3.add(indentText);
        } else {
            ArrayList arrayList4 = new ArrayList();
            arrayList4.add(new ConfigNodeSingleToken(Tokens.OPEN_CURLY));
            if (arrayList2.isEmpty()) {
                arrayList4.add(new ConfigNodeSingleToken(Tokens.newLine((ConfigOrigin) null)));
            }
            arrayList4.addAll(arrayList2);
            arrayList4.add(new ConfigNodeSingleToken(Tokens.CLOSE_CURLY));
            arrayList3.add(new ConfigNodeObject(arrayList4).addValueOnPath(configNodePath.subPath(1), indentText, configSyntax));
        }
        if (configSyntax == ConfigSyntax.JSON || z || z2) {
            int size2 = arrayList.size() - 1;
            while (true) {
                if (size2 < 0) {
                    break;
                } else if ((configSyntax == ConfigSyntax.JSON || z2) && (arrayList.get(size2) instanceof ConfigNodeField)) {
                    int i = size2 + 1;
                    if (i >= arrayList.size() || !(arrayList.get(i) instanceof ConfigNodeSingleToken) || ((ConfigNodeSingleToken) arrayList.get(i)).token() != Tokens.COMMA) {
                        arrayList.add(i, new ConfigNodeSingleToken(Tokens.COMMA));
                    }
                } else {
                    if (z && (arrayList.get(size2) instanceof ConfigNodeSingleToken) && ((ConfigNodeSingleToken) arrayList.get(size2)).token == Tokens.CLOSE_CURLY) {
                        int i2 = size2 - 1;
                        AbstractConfigNode abstractConfigNode = (AbstractConfigNode) arrayList.get(i2);
                        boolean z3 = abstractConfigNode instanceof ConfigNodeSingleToken;
                        if (z3 && Tokens.isNewline(((ConfigNodeSingleToken) abstractConfigNode).token())) {
                            arrayList.add(i2, new ConfigNodeField(arrayList3));
                        } else if (!z3 || !Tokens.isIgnoredWhitespace(((ConfigNodeSingleToken) abstractConfigNode).token())) {
                            arrayList.add(size2, new ConfigNodeField(arrayList3));
                        } else {
                            int i3 = size2 - 2;
                            AbstractConfigNode abstractConfigNode2 = (AbstractConfigNode) arrayList.get(i3);
                            if (z2) {
                                arrayList.add(i2, new ConfigNodeField(arrayList3));
                            } else if (!(abstractConfigNode2 instanceof ConfigNodeSingleToken) || !Tokens.isNewline(((ConfigNodeSingleToken) abstractConfigNode2).token())) {
                                arrayList.add(size2, new ConfigNodeField(arrayList3));
                            } else {
                                arrayList.add(i3, new ConfigNodeField(arrayList3));
                                size2 -= 2;
                            }
                        }
                        size2--;
                    }
                    size2--;
                }
            }
        }
        if (!z) {
            if (arrayList.isEmpty() || !(arrayList.get(arrayList.size() - 1) instanceof ConfigNodeSingleToken) || !Tokens.isNewline(((ConfigNodeSingleToken) arrayList.get(arrayList.size() - 1)).token())) {
                arrayList.add(new ConfigNodeField(arrayList3));
            } else {
                arrayList.add(arrayList.size() - 1, new ConfigNodeField(arrayList3));
            }
        }
        return new ConfigNodeObject(arrayList);
    }

    public ConfigNodeObject removeValueOnPath(String str, ConfigSyntax configSyntax) {
        return changeValueOnPath(PathParser.parsePathNode(str, configSyntax).value(), (AbstractConfigNodeValue) null, configSyntax);
    }
}
