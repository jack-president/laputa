package ${basepackage}.converter;

import ${basepackage}.entity.${className};
import ${basepackage}.model.${modelName};

import com.laputa.foundation.spring.beancopy.BeanCopyUtil;

import java.util.List;

import java.lang.Boolean;

/**
 * <p>
 * ${sysEntity.cname}  BeanCopier<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on ${createTime} .
*/
public class ${className}BeanCopier {

    private static final org.springframework.cglib.beans.BeanCopier ${modelName?uncap_first}To${className}BeanCopier = org.springframework.cglib.beans.BeanCopier.create(${modelName}.class, ${className}.class, true);

    private static final org.springframework.cglib.beans.BeanCopier ${className?uncap_first}To${modelName}BeanCopier = org.springframework.cglib.beans.BeanCopier.create(${className}.class, ${modelName}.class, true);


    private static final ${className}EntityTo${modelName}Converter ${className?uncap_first}EntityTo${modelName}Converter = new ${className}EntityTo${modelName}Converter(Boolean.FALSE);
    private static final ${className}EntityTo${modelName}Converter ${className?uncap_first}EntityTo${modelName}EagerConverter = new ${className}EntityTo${modelName}Converter(Boolean.TRUE);

    private static final ${modelName}To${className}EntityConverter ${modelName?uncap_first}To${className}EntityConverter = new ${modelName}To${className}EntityConverter();

    public static List<${modelName}> ${className?uncap_first}EntityTo${modelName}(List<${className}> ${className?uncap_first}EntityList){
        List<${modelName}> ${modelName?uncap_first}List = BeanCopyUtil.listCopy(${className?uncap_first}EntityList,${modelName}.class,${className?uncap_first}To${modelName}BeanCopier,${className?uncap_first}EntityTo${modelName}Converter);
        return ${modelName?uncap_first}List;
    }

    public static ${modelName} ${className?uncap_first}EntityTo${modelName}(${className} ${className?uncap_first}){
        ${modelName} ${modelName?uncap_first} = new ${modelName}();
        ${className?uncap_first}To${modelName}BeanCopier.copy(${className?uncap_first}, ${modelName?uncap_first}, ${className?uncap_first}EntityTo${modelName}Converter);
        return ${modelName?uncap_first};
    }

    public static ${modelName} ${className?uncap_first}EntityTo${modelName}Eager(${className} ${className?uncap_first}){
        ${modelName} ${modelName?uncap_first} = new ${modelName}();
        ${className?uncap_first}To${modelName}BeanCopier.copy(${className?uncap_first}, ${modelName?uncap_first}, ${className?uncap_first}EntityTo${modelName}EagerConverter);
        return ${modelName?uncap_first};
    }

    public static List<${modelName}> ${className?uncap_first}EntityTo${modelName}Eager(List<${className}> ${className?uncap_first}EntityList){
        List<${modelName}> ${modelName?uncap_first}List = BeanCopyUtil.listCopy(${className?uncap_first}EntityList,${modelName}.class,${className?uncap_first}To${modelName}BeanCopier,${className?uncap_first}EntityTo${modelName}EagerConverter);
        return ${modelName?uncap_first}List;
    }

    public static List<${className}> ${modelName?uncap_first}To${className}Entity(List<${modelName}> ${modelName?uncap_first}List){
        List<${className}> ${className?uncap_first}EntityList = BeanCopyUtil.listCopy(${modelName?uncap_first}List,${className}.class,${modelName?uncap_first}To${className}BeanCopier,${modelName?uncap_first}To${className}EntityConverter);
        return ${className?uncap_first}EntityList;
    }

    public static ${className} ${modelName?uncap_first}To${className}Entity(${modelName} ${modelName?uncap_first}){
        ${className} ${className?uncap_first}Entity = new ${className}();
        ${modelName?uncap_first}To${className}BeanCopier.copy(${modelName?uncap_first},${className?uncap_first}Entity,${modelName?uncap_first}To${className}EntityConverter);
        return ${className?uncap_first}Entity;
    }
}