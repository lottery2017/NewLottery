package com.lottery.app.util.exception;

/**
 * 返回信息码
 */
public class ResponeseCodes {

	@CodeDesc(code = "ESB-E-000000", description = "后台调用出现未知异常")
	public static final String CODE_E000000 = "Lottery-E-000000";

	@CodeDesc(code = "ESB-E-000001", description = "调用成功")
	public static final String CODE_E000001 = "Lottery-I-000001";

	@CodeDesc(code = "ESB-E-000002", description = "用户已经存在")
	public static final String CODE_E000002 = "Lottery-E-000002";

	@CodeDesc(code = "ESB-E-000003", description = "数据库异常")
	public static final String CODE_E000003 = "Lottery-E-000003";

	@CodeDesc(code = "ESB-E-000004", description = "用户名、密码输入错误")
	public static final String CODE_E000004 = "Lottery-E-000004";

	@CodeDesc(code = "ESB-E-000005", description = "操作失败")
	public static final String CODE_E000005 = "Lottery-E-000005";

}
