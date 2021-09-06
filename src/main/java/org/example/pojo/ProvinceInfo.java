package org.example.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.example.annotation.BeanFieldAnnotation;

/**
 * Created by caojidasabi on 2021/9/5.
 */
@Data
public class ProvinceInfo {

    @JSONField(name = "freight_area_name")
    @BeanFieldAnnotation(order = 1)
    private String name;

    @JSONField(name = "freight_area_code")
    @BeanFieldAnnotation(order = 2)
    private String code;



    
}
