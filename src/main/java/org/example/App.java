package org.example;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.text.csv.CsvWriter;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import okhttp3.*;
import org.example.annotation.AnnotationUtils;
import org.example.pojo.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws Exception {

        //getProvinceInfo();

       // getAreaInfo();

        getPhoneInfo();
    }

    /**
     * 获取电话信息
     *
     * @throws IOException
     */
    private static void getPhoneInfo() throws Exception {


        List<AreaInfo> areaInfoList = CsvUtils.readArea("areaInfo.csv", AreaInfo.class);

        CsvWriter writer = CsvUtils.getWrite("phone.csv");
        writer.write(AnnotationUtils.getOrderedField(PhoneInfo.class));

        for(int i=0;i<areaInfoList.size();i++){

            try {
                AreaInfo areaInfo = areaInfoList.get(0);

                String resStr = fun(areaInfo.getProvinceCode(), areaInfo.getCityCode(), 100000);

                List<Res> resList = JSON.parseArray(resStr, Res.class);

                Res res = new Res();
                if (resList != null) {
                    res = resList.get(0);
                }

                //没有报错
                if (ObjectUtil.isEmpty(res.getErrorMsg())) {

                    //不是最后一页，重新来过
//                    if(!res.getMaxPage().equals("1")){
//
//                        i--;
//                        continue;
//
//                    }

                    List<PhoneInfo> listPhones = res.getListPhones();

                    for(PhoneInfo phoneInfo : listPhones){
                        writer.write(new String[]{
                                phoneInfo.getPhoneNumber(),
                                phoneInfo.getTipText(),
                                phoneInfo.getProvince(),
                                phoneInfo.getCity(),
                                phoneInfo.getPrepayMent(),
                                phoneInfo.getMinAmount()
                        });
                    }

                }


            }catch (Exception e){

                System.out.println("获取电话信息报错 :"+e.getMessage());

                i--;


            }

            Thread.sleep(5000);


        }

    }

    /**
     * 获取地区信息
     *
     * @throws IOException
     */
    private static void getAreaInfo() throws Exception {

        List<ProvinceInfo> provinceInfoList = CsvUtils.read("provinceInfo.csv", ProvinceInfo.class);

        List<AreaInfo> sumAreaInfoList = new ArrayList<>();

        for (int i=0;i<provinceInfoList.size();i++) {

            ProvinceInfo provinceInfo = provinceInfoList.get(i);
            try {
                 List<AreaInfo> areaInfoList = new ArrayList<>();

                String resStr = fun(provinceInfo.getCode(), "", 10);

                List<Res> resList = JSON.parseArray(resStr, Res.class);

                Res res = new Res();
                if (resList != null) {
                    res = resList.get(0);
                }

                //没有报错
                if (ObjectUtil.isEmpty(res.getErrorMsg())) {

                    List<CityInfo> cityInfo = res.getCityInfo();

                    if(!CollectionUtil.isEmpty(cityInfo)){
                        areaInfoList = cityInfo.stream()
                                .map(e->{
                                    AreaInfo areaInfo = new AreaInfo();
                                    areaInfo.setProvinceCode(provinceInfo.getCode());
                                    areaInfo.setProvinceName(provinceInfo.getName());
                                    areaInfo.setCityCode(e.getCode());
                                    areaInfo.setCityName(e.getName());
                                    return areaInfo;

                                }).collect(Collectors.toList());
                    }

                    sumAreaInfoList.addAll(areaInfoList);
                }
            } catch (Exception e) {
                System.out.println("获取地区信息报错:" + e);
                i--;
            }
            Thread.sleep(1000);
        }

        CsvUtils.writeArea("areaInfo.csv", sumAreaInfoList, AreaInfo.class);

    }


    /**
     * 获取省份信息
     *
     * @throws IOException
     */
    private static void getProvinceInfo() throws IOException {

        try {

        String resStr = fun("600101", "", 10);

        List<Res> resList = JSON.parseArray(resStr, Res.class);

        Res res = new Res();
        if (resList != null) {
            res = resList.get(0);
        }

        //没有报错
        if (ObjectUtil.isEmpty(res.getErrorMsg())) {

            List<ProvinceInfo> provinceInfo = res.getProvinceInfo();

            CsvUtils.write("provinceInfo.csv", provinceInfo, ProvinceInfo.class);

        }
        }catch (Exception e){
            System.out.println("获取省份信息报错:"+e);
        }


    }

    public static String fun(String provincecode, String areacode, Integer pagesize) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("salesProdId", "000000008C5A3720E99F148FE053AC1410AC2E76")
                .addFormDataPart("shopId", "10036")
                .addFormDataPart("pageindex", "1")
                .addFormDataPart("pagesize", String.valueOf(pagesize))
                .addFormDataPart("provincecode", provincecode)
                .addFormDataPart("areacode", areacode)
                .addFormDataPart("maxPage", "375")
                .addFormDataPart("fourFlag", "1")
                .addFormDataPart("inflag", "0")
                .addFormDataPart("lastFlag", "0")
                .addFormDataPart("sortby", "1")
                .addFormDataPart("comboDetailsId", "")
                .addFormDataPart("channelId", "")
                .addFormDataPart("minpay", "")
                .addFormDataPart("maxpay", "")
                .addFormDataPart("prettypattern", "")
                .addFormDataPart("contnumber", "")
                .addFormDataPart("cacheId", "")
                .addFormDataPart("phoneNumMinExpense", "")
                .addFormDataPart("subPhoneNumMinExpense", "")
                .addFormDataPart("phoneNumPrestoreExpense", "")
                .addFormDataPart("urlPhoneNumber", "")
                .addFormDataPart("urlMinAmount", "")
                .addFormDataPart("selectedPhoneNumber", "")
                .addFormDataPart("headNumber", "")
                .addFormDataPart("type", "")
                .addFormDataPart("numberLevel", "")
                .addFormDataPart("innumber", "")
                .addFormDataPart("isFirst", "")
                .build();
        Request request = new Request.Builder()
                .url("https://www.189.cn/dqmh/lenjoyFourG/buylenjoyFourG.do?method=getLenjoyFourGPhoneInfo&systemType=1")
                .method("POST", body)
                .addHeader("Cookie", "dqmhIpCityInfos=%E6%B5%99%E6%B1%9F%E7%9C%81%E6%B9%96%E5%B7%9E%E5%B8%82+%E7%A7%BB%E5%8A%A8; JSESSIONID-PROD=f9da3db6-adbd-4ce8-8306-d4804d770491; svid=B2225A33633406ECBD0A8D26CEB31840; SHOPID_COOKIEID=10012; code_v=20170913; s_fid=211FFBE7E967765B-18A059B0911326F4; lvid=cd2da1e49eeb78675fcb621268618c64; nvid=1; s_cc=true; loginStatus=non-logined; trkId=589E1AD6-CD4C-4BB5-A9FB-061DEF68CE88; trkHmPageName=%2Fzj%2F; trkintaid=1; trkHmCoords=0; trkHmCity=0; trkHmLinks=0; cityCode=zj; s_sq=eship-189-wap%3D%2526pid%253D%25252Fproducts%25252F90361245532.html%2526pidt%253D1%2526oid%253Donselecting_searchthis%2526oidt%253D2%2526ot%253DA%26eshipeship-189-all%3D%2526pid%253D%25252Fproducts%25252F90361245532.html%2526pidt%253D1%2526oid%253Donselecting_searchthis%2526oidt%253D2%2526ot%253DA; trkHmClickCoords=717-474-4175; JSESSIONID-PROD=52546b83-f7bc-406f-8626-ef44f7741c2d")
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("sec-ch-ua", "\"Chromium\";v=\"92\", \" Not A;Brand\";v=\"99\", \"Google Chrome\";v=\"92\"")
                .addHeader("sec-ch-ua-mobile", "?0")
                .addHeader("Sec-Fetch-Dest", "empty")
                .addHeader("Sec-Fetch-Mode", "cors")
                .addHeader("Sec-Fetch-Site", "same-origin")
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36")
                .addHeader("Host", "www.189.cn")
                .addHeader("Origin", "https://www.189.cn")
                .addHeader("Referer", "https://www.189.cn/products/90361245532.html?city=zj")
                .build();
        Response response = client.newCall(request).execute();

        return response.body().string();
    }
}
