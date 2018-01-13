package com.lottery.user.service.imlp;

import cn.jiguang.common.resp.APIRequestException;
import cn.jsms.api.ValidSMSResult;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lottery.user.dao.UserMapper;
import com.lottery.user.dao.UserVerifycodeMapper;
import com.lottery.user.domain.Model.ResultStatus;
import com.lottery.user.domain.User;
import com.lottery.user.domain.UserVerifycode;
import com.lottery.user.service.interfaces.IUser.IUserLogin;
import com.lottery.util.exception.ResponeseCodes;
import com.lottery.util.exception.UserRuntimeException;
import com.lottery.util.verifycode.SendMS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by gaojunc on 2017/12/24 17:06.
 * Created Reason:用户登录默认实现类
 */
@Service("userLogin")
public class UserLoginImpl implements IUserLogin {

    private UserMapper userMapper;
    private UserVerifycodeMapper userVerifycodeMapper;
    /*
     * 用户登录
     */
    public String loginWithVerifyCode(String requestBody) {
        ResultStatus resultStatus = new ResultStatus();
        if (requestBody != null && requestBody.length() > 0) {
            List<UserVerifycode> userVerifycode = null;
            try {
                JSONObject jsonObject = JSON.parseObject(requestBody);
                String telephoneNum = jsonObject.getString("phone_num");
                String verifyCode = jsonObject.getString("verify_code");

                //验证验证码
                userVerifycode = userVerifycodeMapper.selectByPhone(telephoneNum);
                if (userVerifycode == null || userVerifycode.size() ==0)
                    throw new UserRuntimeException();
                String messageid = userVerifycode.get(userVerifycode.size() == 1 ? 0 : userVerifycode.size() - 1).getMessageid();
                ValidSMSResult smsCodeValid = SendMS.isSMSCodeValid(messageid, verifyCode);
                if (smsCodeValid == null || !smsCodeValid.getIsValid()) {
                    resultStatus.setStatusCode(ResponeseCodes.CODE_E000006);
                    resultStatus.setStatusInfo("验证码输入错误");
                    return JSON.toJSONString(resultStatus);
                }
                //查询用户是否存在
                User user = new User();
                user.setPhoneNum(telephoneNum);
                if (findUser(user) == null) {
                    resultStatus.setStatusCode(ResponeseCodes.CODE_E000008);
                    resultStatus.setStatusInfo("用户不存在");
                } else {
                    resultStatus.setStatusCode(ResponeseCodes.CODE_E000001);
                    resultStatus.setStatusInfo("登录成功");
                }
                return JSON.toJSONString(resultStatus);
            } catch (APIRequestException e) {
                //验证码非法
                if (e.getErrorCode() == 50010) {
                    resultStatus.setStatusInfo("验证码已经过期或者输入错误,请重试");
                    resultStatus.setStatusCode(ResponeseCodes.CODE_E000006);
                }
            } catch (UserRuntimeException e) {
                resultStatus.setStatusInfo("验证码已经过期或者输入错误,请重试");
                resultStatus.setStatusCode(ResponeseCodes.CODE_E000006);
            } finally {
                //删除该用户最新的message信息
                if (userVerifycode != null){
                    for (UserVerifycode verifycode: userVerifycode
                            ) {
                        userVerifycodeMapper.deleteByPrimaryKey(verifycode.getId());
                    }
                }
            }
        }else{
            resultStatus.setStatusInfo("请求报文为空或不合法");
            resultStatus.setStatusCode(ResponeseCodes.CODE_E000007);
        }
        return JSON.toJSONString(resultStatus);
    }

    public String loginWithPassword(String requestBody) {
        return null;
    }


    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    public void setUserVerifycodeMapper(UserVerifycodeMapper userVerifycodeMapper) {
        this.userVerifycodeMapper = userVerifycodeMapper;
    }

    public User findUser(User user) {
        return userMapper.selectByPhone(user.getPhoneNum());
    }
}
