package UserMouleTest;

import HttpUtil.HttpConnector;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by gaojunc on 2017/12/24 17:40.
 * Created Reason:用户注册单元测试
 */
public class UserRegisterTest {

    public static final String TOMCAT_URL = "http://127.0.0.1:8080/";

    @Test
    public void testRegister(){
        String body = "{\n" +
                "   \"telephoneNum\" : 1888888844\n" +
                "}";
        HttpConnector httpConnector = new HttpConnector(TOMCAT_URL + "register/action", body);
        String send = httpConnector.send();
        System.out.println("收到报文:" + send);
        Assert.assertNotNull(send);
    }

}
