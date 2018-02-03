<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@page import="com.lottery.account.domain.BatchPayWS.BatchPayServiceLocator"%>
<%@page import="com.lottery.account.domain.complatible.BankRequestBean"%>

<%@page import="com.lottery.account.domain.complatible.BankResponseBean"%>
<%@ page import="com.lottery.util.md5.MD5Util" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>
<br />
<body>
	<%
		//客户编号所对应的密钥。。在账户邮箱中获取
		String key = "YKB4WI4GS6UDNZXN";
		//城市,中文字符 主要只需要城市名，不需要省份名。也不要带"市""自治区（县）"等
		String province_city = "nj";
		//银行名称 请填写银行的标准名,详见接口文档
		String bankName = "招商银行";
		//银行卡开户行的名称
		String kaihuhang = "招商银行南京分行鼓楼支行";
		//收款人姓名,收款人的姓名，必须与银行卡账户名相同
		String creditName = "张三";
		//银行卡号  
		String bankCardNumber = "6226584150965236";
		//交易金额  整数或小数  小数为两位以内  但小数点的最后一位不能为0 如：0.10不行。单位为人民币元  
		String amount = "10.1";
		//交易备注
		String description = "fkdyh";
		//订单号
		String orderId = new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
		//组合字符串。。必须按照此顺序组串
		String macVal = bankCardNumber + amount + orderId + key;
		String mac = MD5Util.md5Hex(macVal.getBytes("gb2312")).toUpperCase();

		BankRequestBean requestBean = new BankRequestBean();
		requestBean.setProvince_city(province_city);
		requestBean.setBankName(bankName);
		requestBean.setKaihuhang(kaihuhang);
		requestBean.setCreditName(creditName);
		requestBean.setBankCardNumber(bankCardNumber);
		requestBean.setAmount(Double.parseDouble(amount));
		requestBean.setDescription(description);
		requestBean.setOrderId(orderId);
		requestBean.setMac(mac);
		BankRequestBean[] queryArray = new BankRequestBean[1];
		queryArray[0] = requestBean;

		String merchant_id = "10012138842";
		String merchant_ip = "192.168.8.1";
		BatchPayServiceLocator locator = new BatchPayServiceLocator();
		BankResponseBean[] responseBean = new BankResponseBean[1];
		responseBean = locator.getBatchPayWS().bankPay(queryArray, merchant_id, merchant_ip);
	%>
	<table border="2">
		<tr>
			<td>城市:</td>
			<td><%=responseBean[0].getProvince_city()%></td>
		</tr>
		<tr>
			<td>银行名称:</td>
			<td><%=responseBean[0].getBankName()%></td>
		</tr>
		<tr>
			<td>开户行:</td>
			<td><%=responseBean[0].getKaihuhang()%></td>
		</tr>
		<tr>
			<td>收款人姓名:</td>
			<td><%=responseBean[0].getCreditName()%></td>
		</tr>
		<tr>
			<td>银行卡号:</td>
			<td><%=responseBean[0].getBankCardNumber()%></td>
		</tr>
		<tr>
			<td>交易金额:</td>
			<td><%=responseBean[0].getAmount()%></td>
		</tr>
		<tr>
			<td>交易备注</td>
			<td><%=responseBean[0].getDescription()%></td>
		</tr>
		<tr>
			<td>订单号</td>
			<td><%=responseBean[0].getOrderId()%></td>
		</tr>
		<tr>
			<td>快钱交易号</td>
			<td><%=responseBean[0].getDealId()%></td>
		</tr>

		<tr>
			<td>快钱手续费</td>
			<td><%=responseBean[0].getDealCharge()%></td>
		</tr>
		<tr>
			<td>付款方费用</td>
			<td><%=responseBean[0].getDebitCharge()%></td>
		</tr>
		<tr>
			<td>收款方费用</td>
			<td><%=responseBean[0].getCreditCharge()%></td>
		</tr>
		<tr>
			<td>命令执行结果</td>
			<td><%=responseBean[0].isResultFlag()%></td>
		</tr>
		<tr>
			<td>失败原因代码</td>
			<td><%=responseBean[0].getFailureCause()%></td>
		</tr>
	</table>
	<br />
	<br />
</body>
</html>