package org.example.annotation;

import org.example.pojo.ProvinceInfo;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by caojidasabi on 2021/9/6.
 */
public class AnnotationUtils {

    public static  <T> String[] getOrderedField(Class<T> tClass){
        Field[] fields = tClass.getDeclaredFields();

        // 用来存放所有的属性域
        List<Field> fieldList = new ArrayList<>();
        // 过滤带有注解的Field
        for(Field f:fields){
            if(f.getAnnotation(BeanFieldAnnotation.class)!=null){
                fieldList.add(f);
            }
        }
        // 这个比较排序的语法依赖于java 1.8
        List<String> stringList = fieldList.stream().sorted(Comparator.comparingInt(
                m -> m.getAnnotation(BeanFieldAnnotation.class).order()))
                .map(Field::getName)
                .collect(Collectors.toList());

        return stringList.toArray(new String[stringList.size()]);
    }

    public static void main(String[] args) {

        getOrderedField(ProvinceInfo.class);


    }
}
