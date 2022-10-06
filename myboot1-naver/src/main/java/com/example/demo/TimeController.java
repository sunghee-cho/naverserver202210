package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

class TimeThread extends Thread{
	int second; String msg;
	TimeThread(int second, String msg){
		this.second = second;
		this.msg = msg;
	}
	public void run()  {
		System.out.println(second + " 경매시간 남음");
		try {
			Thread.sleep(second);
		}catch(InterruptedException e){}
		System.out.println(msg);// 출력문 대신에 sql 실행
	}
}

@Controller
public class TimeController {
	@RequestMapping("/time") //http://localhost:8081/
	public void main() {//mvc실행메소드
		int seconds[] = {1000, 500, 2000, 100, 200, 300, 400};
		String msgs[] = new String[seconds.length];
		for(int i = 0; i < msgs.length; i++) {
			msgs[i] = seconds[i] + " 초 후에 경매 종료하고 sql 실행";
			new TimeThread(seconds[i], msgs[i]).start();
		}
	}


	public static void main(String args[]) {//java application
		int seconds[] = {1000, 500, 2000, 100, 200, 300, 400};
		String msgs[] = new String[seconds.length];
		for(int i = 0; i < msgs.length; i++) {
			msgs[i] = seconds[i] + " 초 후에 경매 종료하고 sql 실행";
			new TimeThread(seconds[i], msgs[i]).start();
		}
	}
}



