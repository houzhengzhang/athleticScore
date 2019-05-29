package athletic.utils;


import org.apache.commons.codec.digest.DigestUtils;


/**
 * @ Date: 2018/12/1 21:26
 * @ Description:
 */
public class MD5Util {
    public static final String SOLT="06DF75F523A84D7AB482A513ADAA13DA";

    public static String convertToMd5(String str){
        // DigestUtils.md5Hex()此方法为加密方法
        String password = DigestUtils.md5Hex(str);
        //此处加密后加盐再进行加密
        return DigestUtils.md5Hex(password+MD5Util.SOLT);
    }

    public static void main(String[] args) {
        String password = "123456";
        System.out.println(convertToMd5(password));
    }
}
