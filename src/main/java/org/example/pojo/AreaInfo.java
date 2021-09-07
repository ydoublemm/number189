package org.example.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.example.annotation.BeanFieldAnnotation;

/**
 * Created by caojidasabi on 2021/9/6.
 */
@Data
public class AreaInfo {
    @JSONField(name = "freight_area_name")
    @BeanFieldAnnotation(order = 1)
    private String provinceName;

    @JSONField(name = "freight_area_code")
    @BeanFieldAnnotation(order = 2)
    private String provinceCode;

    @JSONField(name = "freight_area_name")
    @BeanFieldAnnotation(order = 3)
    private String cityName;

    @JSONField(name = "freight_area_code")
    @BeanFieldAnnotation(order = 4)
    private String  cityCode;


}
