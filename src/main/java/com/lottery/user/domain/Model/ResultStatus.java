package com.lottery.user.domain.Model;

/**
 * Created by gaojunc on 2017/12/28 20:17.
 * Created Reason:
 */
public class ResultStatus {
    private String statusCode;
    private String statusInfo;
    private String invite_code;

    public String getInvite_code() {
        return invite_code;
    }

    public void setInvite_code(String invite_code) {
        this.invite_code = invite_code;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusInfo() {
        return statusInfo;
    }

    public void setStatusInfo(String statusInfo) {
        this.statusInfo = statusInfo;
    }
}
