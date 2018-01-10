package UserMouleTest;

import HttpUtil.HttpConnector;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by gaojunc on 2017/12/30 18:29.
 * Created Reason:
 */
public class UserloginTest extends AbstractTest {

    @Test
    public void loginTest(){
        String code = "915667";
        String body = "{\n" +
                "   \"verify_code\" : \"" + code + "\",\n" +
                "   \"phone_num\" : \"13289396632\"\n" +
                "}";
        HttpConnector httpConnector = new HttpConnector(TOMCAT_URL + "/login/dologin", body);
        String send = httpConnector.send();
        System.out.println("收到报文:" + send);
        Assert.assertNotNull(send);
    }
}
