package br.com.jhonatan.personalcontrolmobile.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Jhonatan on 22/02/2016.
 */
public final class CriptografiaUtil {

    private CriptografiaUtil() {}

    public static String criptografarMD5(String valor) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(valor.getBytes());
        byte byteData[] = md.digest();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

}
