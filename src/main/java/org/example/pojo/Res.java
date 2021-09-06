package org.example.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.example.pojo.CityInfo;
import org.example.pojo.ProvinceInfo;

import java.util.List;

/**
 * Created by caojidasabi on 2021/8/31.
 */
@Data
public class Res {




    @JSONField(name = "provinceCode")
    private String provinceCode;

    @JSONField(name = "contractid")
    private String contractId;


    @JSONField(name = "errorMsg")
    private String errorMsg;


    @JSONField(name = "contnumber")
    private String contNumber;

    @JSONField(name = "minpay")
    private String minPay;

    @JSONField(name = "maxpay")
    private String maxPay;

    @JSONField(name = "maxPage")
    private String maxPage;

    @JSONField(name = "salesProdId")
    private String salesProdId;

    @JSONField(name = "shopId")
    private String shopId;

    @JSONField(name = "subid")
    private String subId;

    @JSONField(name = "istymh")
    private String istymh;

    @JSONField(name = "pageend")
    private String pageEnd;

    @JSONField(name = "isSelectArea")
    private String isSelectArea;

    @JSONField(name = "sortby")
    private String sortBy;

    @JSONField(name = "querystatus")
    private String queryStatus;

    @JSONField(name = "status")
    private String status;

    @JSONField(name = "areaCode")
    private String areaCode;

    @JSONField(name = "json_prove_map")
    private List<ProvinceInfo> provinceInfo;

    @JSONField(name = "jsonAreaMap")
    private List<CityInfo> cityInfo;


    @JSONField(name = "code")
    private String code;

    @JSONField(name = "prettypattern")
    private String prettyPattern;

    @JSONField(name = "comboid")
    private String comboId;

    @JSONField(name = "numbertype")
    private String numberType;


    @JSONField(name = "listphones")
    private List<PhoneInfo> listPhones;

    @JSONField(name = "pageindex")
    private String pageIndex;








}
