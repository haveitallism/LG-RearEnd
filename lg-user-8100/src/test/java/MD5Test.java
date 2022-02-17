import com.group8.utils.MD5Utils;

/**
 * @author laiyong
 * @date 2022/2/17 14:22 星期四
 * @apiNote
 */
public class MD5Test {
    public static void main(String[] args) {
        String s = MD5Utils.encrypt("123456", "tom");
        System.out.println(s);
    }
}
