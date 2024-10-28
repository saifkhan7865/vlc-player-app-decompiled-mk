package com.typesafe.config.impl;

import com.typesafe.config.ConfigException;
import com.typesafe.config.ConfigParseOptions;
import com.typesafe.config.ConfigRenderOptions;
import com.typesafe.config.ConfigValue;
import com.typesafe.config.parser.ConfigDocument;
import java.io.StringReader;

final class SimpleConfigDocument implements ConfigDocument {
    private ConfigNodeRoot configNodeTree;
    private ConfigParseOptions parseOptions;

    SimpleConfigDocument(ConfigNodeRoot configNodeRoot, ConfigParseOptions configParseOptions) {
        this.configNodeTree = configNodeRoot;
        this.parseOptions = configParseOptions;
    }

    public ConfigDocument withValueText(String str, String str2) {
        if (str2 != null) {
            SimpleConfigOrigin newSimple = SimpleConfigOrigin.newSimple("single value parsing");
            StringReader stringReader = new StringReader(str2);
            AbstractConfigNodeValue parseValue = ConfigDocumentParser.parseValue(Tokenizer.tokenize(newSimple, stringReader, this.parseOptions.getSyntax()), newSimple, this.parseOptions);
            stringReader.close();
            return new SimpleConfigDocument(this.configNodeTree.setValue(str, parseValue, this.parseOptions.getSyntax()), this.parseOptions);
        }
        throw new ConfigException.BugOrBroken("null value for " + str + " passed to withValueText");
    }

    public ConfigDocument withValue(String str, ConfigValue configValue) {
        if (configValue != null) {
            return withValueText(str, configValue.render(ConfigRenderOptions.defaults().setOriginComments(false)).trim());
        }
        throw new ConfigException.BugOrBroken("null value for " + str + " passed to withValue");
    }

    public ConfigDocument withoutPath(String str) {
        return new SimpleConfigDocument(this.configNodeTree.setValue(str, (AbstractConfigNodeValue) null, this.parseOptions.getSyntax()), this.parseOptions);
    }

    public boolean hasPath(String str) {
        return this.configNodeTree.hasValue(str);
    }

    public String render() {
        return this.configNodeTree.render();
    }

    public boolean equals(Object obj) {
        return (obj instanceof ConfigDocument) && render().equals(((ConfigDocument) obj).render());
    }

    public int hashCode() {
        return render().hashCode();
    }
}
