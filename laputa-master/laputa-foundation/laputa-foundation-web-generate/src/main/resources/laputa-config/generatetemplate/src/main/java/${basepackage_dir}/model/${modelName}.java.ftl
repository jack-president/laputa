package ${basepackage}.model;

/**
 * <p>
 * ${sysEntity.cname} Model<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on ${createTime} .
 */
public class ${modelName} {
    <#list sysEntity.sysFieldCollection as sysField>

    /**
     * ${sysField.cname}
     */
    <#if sysField.columnTypeCode == "COLUMN">
    private ${sysField.clazzName} ${sysField.fieldName};

    <#elseIf sysField.columnTypeCode == "MANY_TO_ONE">
    <#if sysField.tree>
    private java.lang.Long parentId;
    <#else >
    private java.lang.Long ${sysField.fieldName}Id;
    </#if>
    <#elseIf sysField.columnTypeCode == "ONE_TO_MANY">
    private java.util.List<java.lang.Long> ${sysField.fieldName};

    </#if>
    </#list>

    <#list sysEntity.sysFieldCollection as sysField>
    <#if sysField.columnTypeCode == "COLUMN">

    public ${sysField.clazzName} get${sysField.fieldName?capFirst}(){
        return this.${sysField.fieldName};
    }

    public void set${sysField.fieldName?capFirst}(${sysField.clazzName} ${sysField.fieldName}) {
        this.${sysField.fieldName}= ${sysField.fieldName};
    }
    <#elseIf sysField.columnTypeCode == "MANY_TO_ONE">
    <#if sysField.tree>
    public java.lang.Long getParentId(){
        return this.parentId;
    }

    public void setParentId(java.lang.Long parentId) {
        this.parentId= parentId;
    }
    <#else >
    public java.lang.Long get${sysField.fieldName?capFirst}Id(){
        return this.${sysField.fieldName}Id;
    }

    public void set${sysField.fieldName?capFirst}Id(java.lang.Long ${sysField.fieldName}Id) {
        this.${sysField.fieldName}Id= ${sysField.fieldName}Id;
    }
    </#if>
    <#elseIf sysField.columnTypeCode == "ONE_TO_MANY">

    public java.util.List<java.lang.Long> get${sysField.fieldName?capFirst}(){
        return this.${sysField.fieldName};
    }

    public void set${sysField.fieldName?capFirst}(java.util.List<java.lang.Long> ${sysField.fieldName}) {
        this.${sysField.fieldName}= ${sysField.fieldName};
    }
    </#if>
    </#list>
}