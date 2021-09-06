package org.example;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.text.csv.CsvData;
import cn.hutool.core.text.csv.CsvReader;
import cn.hutool.core.text.csv.CsvUtil;
import cn.hutool.core.text.csv.CsvWriter;
import cn.hutool.core.util.CharsetUtil;
import org.example.annotation.AnnotationUtils;
import org.example.pojo.AreaInfo;
import org.example.pojo.ProvinceInfo;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by caojidasabi on 2021/9/6.
 */
public class CsvUtils {

    private static final String BASE_PATH = "D:\\workspace\\JavaCode\\number189\\file\\";


    public static <T extends ProvinceInfo> void write(String fileName, List<T> list , Class<T> tClass){

        CsvWriter writer = CsvUtil.getWriter(BASE_PATH + fileName, CharsetUtil.CHARSET_UTF_8);


        writer.write(AnnotationUtils.getOrderedField(tClass));

        for(T t : list){
            writer.write(new String[]{t.getName(),t.getCode()});
        }

    }

    public static <T extends AreaInfo> void writeArea(String fileName, List<T> list , Class<T> tClass){

        CsvWriter writer = CsvUtil.getWriter(BASE_PATH + fileName, CharsetUtil.CHARSET_UTF_8);


        writer.write(AnnotationUtils.getOrderedField(tClass));

        for(T t : list){
            writer.write(new String[]{t.getProvinceName(),t.getProvinceCode(),t.getCityName(),t.getCityCode()});
        }

    }

    public static  CsvWriter getWrite(String fileName){

        return CsvUtil.getWriter(BASE_PATH + fileName, CharsetUtil.CHARSET_UTF_8);


    }


    public static <T extends ProvinceInfo> List<T>  read(String fileName, Class<T> tClass){

        final CsvReader reader = CsvUtil.getReader();
//假设csv文件在classpath目录下
        return  reader.read(ResourceUtil.getUtf8Reader(BASE_PATH+fileName), tClass);



    }

    public static <T extends AreaInfo> List<T>  readArea(String fileName, Class<T> tClass){

        final CsvReader reader = CsvUtil.getReader();
//假设csv文件在classpath目录下
        return  reader.read(ResourceUtil.getUtf8Reader(BASE_PATH+fileName), tClass);



    }
}
