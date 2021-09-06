package org.example.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * Created by caojidasabi on 2021/9/5.
 */
@Data
public class CityInfo {

    @JSONField(name = "freight_area_code")
    private String code;


    @JSONField(name = "freight_area_name")
    private String name;

}
