package com.lottery.account.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Account {
    private String userid;

    private BigDecimal balance;

    private String bankNum;

    private String identifyCard;

    private String bankName;

    private String bindingPhone;

    private String bankLocation;

    private Date createTime;

    private Date modifyTime;

    private String validTag;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getBankNum() {
        return bankNum;
    }

    public void setBankNum(String bankNum) {
        this.bankNum = bankNum == null ? null : bankNum.trim();
    }

    public String getIdentifyCard() {
        return identifyCard;
    }

    public void setIdentifyCard(String identifyCard) {
        this.identifyCard = identifyCard == null ? null : identifyCard.trim();
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    public String getBindingPhone() {
        return bindingPhone;
    }

    public void setBindingPhone(String bindingPhone) {
        this.bindingPhone = bindingPhone == null ? null : bindingPhone.trim();
    }

    public String getBankLocation() {
        return bankLocation;
    }

    public void setBankLocation(String bankLocation) {
        this.bankLocation = bankLocation == null ? null : bankLocation.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getValidTag() {
        return validTag;
    }

    public void setValidTag(String validTag) {
        this.validTag = validTag == null ? null : validTag.trim();
    }
}