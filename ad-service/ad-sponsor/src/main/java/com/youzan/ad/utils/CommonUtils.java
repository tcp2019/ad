package com.youzan.ad.utils;

import com.youzan.ad.constant.Constants;
import com.youzan.ad.exception.AdException;
import org.apache.commons.codec.digest.DigestUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author TCP
 * @create 2019/3/27 11:04
 */
public class CommonUtils {

    public static String md5(String str) {
        return DigestUtils.md5Hex(str).toUpperCase();
    }

    public static Date parseStringDate(String str) throws AdException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(str);
        } catch (Exception e) {
            throw new AdException(e.getMessage());
        }
    }
}
