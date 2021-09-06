package org.example.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.example.annotation.BeanFieldAnnotation;

/**
 * Created by caojidasabi on 2021/8/31.
 */
@Data
public class PhoneInfo {

    /**
     * "specialOffers": "",
*       "status": "2",
*       "provinceCode": "600201",
*       "cityCode": "8510800",
*       "tipText": null,
*       "city": "广元市",
*       "minAmount": 0,
*       "level": 0,
*       "salesProdId": "000000008C5A3720E99F148FE053AC1410AC2E76",
*       "phoneNumber": "17761173297",
*       "hlEnd": 0,
*       "province": "四川省",
*       "prepayMent": 0,
*       "typeId": null,
*       "hlStart": 0
     */
    @JSONField(name = "phoneNumber")
    @BeanFieldAnnotation(order = 1)
    private String phoneNumber;

    @JSONField(name = "tipText")
    @BeanFieldAnnotation(order = 2)
    private String tipText;

    @JSONField(name = "province")
    @BeanFieldAnnotation(order = 3)
    private String province;

    @JSONField(name = "city")
    @BeanFieldAnnotation(order = 4)
    private String city;

    @JSONField(name = "prepayMent")
    @BeanFieldAnnotation(order = 5)
    private String prepayMent;

    @JSONField(name = "minAmount")
    @BeanFieldAnnotation(order = 6)
    private String minAmount;


    @JSONField(name = "specialOffers")
    private String specialOffers;

    @JSONField(name = "status")
    private String status;

    @JSONField(name = "provinceCode")
    private String provinceCode;

    @JSONField(name = "cityCode")
    private String cityCode;







    @JSONField(name = "level")
    private String level;

    @JSONField(name = "salesProdId")
    private String salesProdId;



    @JSONField(name = "hlEnd")
    private String hlEnd;





    @JSONField(name = "typeId")
    private String typeId;

    @JSONField(name = "hlStart")
    private String hlStart;











}
