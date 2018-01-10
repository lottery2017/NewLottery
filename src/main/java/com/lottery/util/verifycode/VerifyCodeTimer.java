package com.lottery.util.verifycode;

import javax.servlet.http.HttpSession;
import java.util.Timer;
import java.util.TimerTask;

public class VerifyCodeTimer extends TimerTask {
	
	/*//放在需要调用的方法中
	//VerifyCodeTimer timerCode = new VerifyCodeTimer (request.getSession(),"yzm");
	//timerCode.timer.schedule(timerCode, 60 * 1000);//30秒钟
*/	
	public Timer timer;
	private HttpSession session;
	private String sessionKey;

	public VerifyCodeTimer(HttpSession session,String sessionKey){
		timer = new Timer();
		this.session = session;
		this.sessionKey = sessionKey;
	}
	
	@Override
	public void run(){
	session.removeAttribute(sessionKey);
	timer.cancel();
	}
}
