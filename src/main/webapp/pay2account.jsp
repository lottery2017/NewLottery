<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@page import="com.lottery.account.domain.BatchPayWS.BatchPayServiceLocator"%>
<%@page import="com.lottery.account.domain.complatible.SimpleRequestBean"%>

<%@page import="com.lottery.account.domain.complatible.SimpleResponseBean"%>
<%@page import="com.lottery.util.md5.MD5Util"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" >
</head>
<br/>
<body>
<%
    //客户编号所对应的密钥。。在账户邮箱中获取
    String key = "YKB4WI4GS6UDNZXN";  
    //收款方姓名   
    String creditName = "UserName";
    //收款方快钱用户名(Email或手机号)
    String creditContact ="496333210@qq.com";
    //货币类型
    String  currencyCode ="1";
    //交易金额，以元为单位，
    String amount ="1.5";
    //交易备注   
    String description ="pay2account";
    //验证收款方姓名标志
    String correctName ="n";
    //收款方还不是快钱用户时是否付款标志
    String tempAccount ="y";
    //订单号
    String orderId = new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
    //组合字符串。。必须按照此顺序组串
    String macVal= creditContact + currencyCode + amount +orderId + key;
    String mac = MD5Util.md5Hex(macVal.getBytes("gb2312")).toUpperCase();
    SimpleRequestBean requestBean = new SimpleRequestBean();

    requestBean.setCreditName(creditName);
    requestBean.setCreditContact(creditContact);
    requestBean.setCurrencyCode(currencyCode);
    requestBean.setAmount(Double.parseDouble(amount)); 
    requestBean.setDescription(description);
    requestBean.setCorrectName(correctName);
    requestBean.setTempAccount(tempAccount);
    requestBean.setOrderId(orderId);
    requestBean.setMac(mac);
    SimpleRequestBean[] queryArray = new SimpleRequestBean[1];
    queryArray[0] = requestBean;
    String merchant_id = "10012138842" ;
    String merchant_ip = "222.190.107.178";

    BatchPayServiceLocator locator = new BatchPayServiceLocator();
    SimpleResponseBean[] responseBean = new SimpleResponseBean[1];
    responseBean = locator.getBatchPayWS().simplePay(queryArray,merchant_id,merchant_ip);
 
%>
<table border="2">
<tr>
	<td>收款方姓名: </td>
	<td><%= responseBean[0].getCreditName() %></td>
</tr>
<tr>
	<td>收款方快钱用户名(Email或手机号):</td>
	<td><%= responseBean[0].getCreditContact() %></td>
</tr>
<tr>
	<td>货币类型: </td>
	<td><%=  responseBean[0].getCurrencyCode() %></td>
</tr>
<tr>
	<td>交易金额: </td>
	<td><%=  responseBean[0].getAmount() %></td>
</tr>
<tr>
	<td>交易备注: </td>
	<td><%=  responseBean[0].getDescription() %></td>
</tr>
<tr>
	<td>验证收款方姓名标志: </td>
	<td><%=  responseBean[0].getCorrectName() %></td>
</tr>
<tr>
	<td>收款方还不是快钱用户时是否付款标志 </td>
	<td><%=  responseBean[0].getTempAccount() %></td>
</tr>
<tr>
	<td>订单号</td>
	<td><%=  responseBean[0].getOrderId() %></td>
</tr>
<tr>
	<td>快钱交易号</td>
	<td><%=  responseBean[0].getDealId() %></td>
</tr>

<tr>
	<td>快钱手续费</td>
	<td><%=  responseBean[0].getDealCharge() %></td>
</tr>
<tr>
	<td>付款方费用</td>
	<td><%=  responseBean[0].getDebitCharge() %></td>
</tr>
<tr>
	<td>收款方费用</td>
	<td><%=  responseBean[0].getCreditCharge() %></td>
</tr>
<tr>
	<td>命令执行结果</td>
	<td><%=  responseBean[0].isResultFlag() %></td>
</tr>
<tr>
	<td>失败原因代码</td>
	<td><%=  responseBean[0].getFailureCause() %></td>
</tr>
</table>
<br/>
<br/>
</body>
</html>