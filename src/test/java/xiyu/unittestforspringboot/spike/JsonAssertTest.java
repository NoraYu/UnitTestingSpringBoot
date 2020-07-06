package xiyu.unittestforspringboot.spike;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

public class JsonAssertTest {
    String actual="{\"id\":0,\"name\":\"Apple Watch\",\"price\":399.0,\"quantity\":1}";
    @Test
    public void jsonAssert_StrictFalse() throws JSONException {
        String expected="{\"id\":0,\"name\":\"Apple Watch\"}";
        JSONAssert.assertEquals(expected,actual,false);
    }
    @Test
    public void jsonAssert_StrickTrue() throws JSONException {
        String expected="{\"id\":0,\"name\":\"Apple Watch\",\"price\":399.0,\"quantity\":1}";
        JSONAssert.assertEquals(expected,actual,true);
    }
    @Test
    public void jsonAssert_withoutEscape() throws JSONException {
        String expected="{id:  0, name:\"Apple Watch\", price:399.0,quantity:1}";
        JSONAssert.assertEquals(expected,actual,false);
    }
}
