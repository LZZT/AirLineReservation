import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by QQZhao on 3/4/17.
 */
public class test {

    public static void main(String[] args) {
        List<String> x = new ArrayList<>();
        x.add("x1");
        x.add("x2");
        Object a = (Object)x;
        List b = (List<String>)a;
        System.out.println(b);
    }

}
