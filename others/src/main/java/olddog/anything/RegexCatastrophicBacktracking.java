package olddog.anything;/**
 * Created by hanyong on 2017/8/25.
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author hanyong
 * @Date 2017/8/25
 */
public class RegexCatastrophicBacktracking {
    public static void main(String[] args) {


        System.out.println(matches("ab{1,3}?+c", "abc"));

    }

    private static boolean matches(String regex, CharSequence input) {

        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(input);
        return m.matches();
    }



}
