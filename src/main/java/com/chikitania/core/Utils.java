package com.chikitania.core;

import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.constants.VerticalAlign;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Ruth on 23/09/2019.
 */
 
public interface Utils {

    static String getCreatedUrl(String path, long id) {
        return String.format("./%s/%d", path, id);
    }

    static String encryptPassword(String passwordToHash) {
        StringBuffer hexString = new StringBuffer();
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(passwordToHash.getBytes());
            byte byteData[] = md.digest();


            for (int i = 0; i < byteData.length; i++) {
                String hex = Integer.toHexString(0xff & byteData[i]);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hexString.toString();
    }

    static Style createHeaderStyle() {
        Style headerStyle = new Style();
        Font font = new Font(11, "DejaVu Sans", true);
        headerStyle.setFont(font);
        headerStyle.setBorder(Border.PEN_1_POINT());
        headerStyle.setHorizontalAlign(HorizontalAlign.CENTER);
        headerStyle.setVerticalAlign(VerticalAlign.MIDDLE);
        return headerStyle;
    }

    static Style createColumnStyle() {
        Style detailStyle = new Style();
        Font font = new Font(9, "DejaVu Sans", false);
        detailStyle.setFont(font);
        detailStyle.setBorder(Border.PEN_1_POINT());
        detailStyle.setHorizontalAlign(HorizontalAlign.CENTER);
        detailStyle.setVerticalAlign(VerticalAlign.MIDDLE);
        return detailStyle;
    }

    static Double doublePrecision(Double value){
        return Double.parseDouble(String.format("%.2f", value));
    }
}