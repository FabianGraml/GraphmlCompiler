package net.htlgrieskirchen.aud4.election;

import java.util.ArrayList;
import java.util.List;

public class ClazzBuilder {
    private String stereotype;
    private String nameSpace;
    private String name;
    private List<String> metohds;
    private List<String> variables;
    private List<String> implementations;
    private List<String> imports;

    private ClazzBuilder() {
    }

    public static ClazzBuilder aClazz() {
        return new ClazzBuilder();
    }

    public ClazzBuilder withStereotype(String stereotype) {
        this.stereotype = stereotype;
        return this;
    }

    public ClazzBuilder withNameSpace(String nameSpace) {
        this.nameSpace = nameSpace;
        return this;
    }

    public ClazzBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public String getName(){
        return name;
    }

    public ClazzBuilder withMetohds(List<String> metohds) {
        this.metohds = metohds;
        return this;
    }

    public ClazzBuilder withVariables(List<String> variables) {
        this.variables = variables;
        return this;
    }

    public ClazzBuilder withImplementations(List<String> implementations) {
        this.implementations = implementations;
        return this;
    }

    public ClazzBuilder withImports(List<String> imports) {
        this.imports = imports;
        return this;
    }

    public ClazzBuilder addImport(String imp){
        if(imports == null) imports = new ArrayList<>();
        imports.add(imp);
        return this;
    }

    public UMLClazz build() {
        UMLClazz clazz = new UMLClazz();
        clazz.setStereotype(stereotype);
        clazz.setNameSpace(nameSpace);
        clazz.setName(name);
        clazz.setMetohds(metohds);
        clazz.setVariables(variables);
        clazz.setImplementations(implementations);
        clazz.setImports(imports);
        return clazz;
    }
}

