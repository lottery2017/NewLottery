package UserMouleTest;

import HttpUtil.HttpConnector;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by gaojunc on 2017/12/24 17:40.
 * Created Reason:用户注册单元测试
 */
public class UserRegisterTest extends AbstractTest{

    //注册用户 获取验证码
    @Test
    public void testGetVerifyCode(){
        //获取验证码
        String body = "{\n" +
                "   \"phone_num\" : 13289396632\n" +
                "}";
        HttpConnector httpConnector = new HttpConnector(TOMCAT_URL + "verifycode/register/getverifycode", body);

        String send = httpConnector.send();
        System.out.println("收到报文:" + send);
        Assert.assertNotNull(send);
    }

    //使用验证码注册
    @Test
    public void testRegisterwithCode(){
        String code = "915667";
        String body = "{\n" +
                "   \"veryfy_code\" : \"" + code + "\",\n" +
                "   \"phone_num\" : \"13289396632\"\n" +
                "}";
        HttpConnector httpConnector = new HttpConnector(TOMCAT_URL + "users/register/verify", body);

        String send = httpConnector.send();
        System.out.println("收到报文:" + send);
        Assert.assertNotNull(send);
    }

}
