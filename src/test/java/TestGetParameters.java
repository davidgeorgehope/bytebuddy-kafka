
import com.github.shehanperera.parameters.GetParameters;
import org.junit.Test;

public class TestGetParameters {

    @Test
    public void test(){
        try {
            GetParameters.getParametrs(null, new String[]{"test", "test","test","test"});
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
