package common;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TimeInfomation {
	
	public LocalDateTime timeNow;
	public LocalDateTime defaultClockInTime;//規定出社時間
	public LocalDateTime defaultClockOutTime;//規定退社時間
	public LocalDateTime defaultMidNightTime;//規定深夜時間
	public LocalDateTime clockInTime;
	public LocalDateTime clockOutTime;
	
	public int lateTime = 0;//遅刻時間
	public int tentativeOverTime = 0;//仮残業時間
	public int properOverTime = 0;//実残業時間
	public int lackTime = 0;//不足時間
	public int midnightTime = 0;//深夜時間
	public int holidayWorkedTime = 0; //休日出勤時間
	
	final public int workedTime = 540;//実働時間+休憩1時間
	
	
	public TimeInfomation(){
		
	}
	
	
	public LocalDateTime getTimeNow() {
		return timeNow;
	}


	public LocalDateTime getDefaultClockInTime() {
		return defaultClockInTime;
	}


	public LocalDateTime getDefaultClockOutTime() {
		return defaultClockOutTime;
	}


	public LocalDateTime getDefaultMidNightTime() {
		return defaultMidNightTime;
	}


	public LocalDateTime getClockInTime() {
		return clockInTime;
	}


	public LocalDateTime getClockOutTime() {
		return clockOutTime;
	}


	public int getLateTime() {
		return lateTime;
	}


	public int getTentativeOverTime() {
		return tentativeOverTime;
	}


	public int getProperOverTime() {
		return properOverTime;
	}


	public int getLackTime() {
		return lackTime;
	}


	public int getMidnightTime() {
		return midnightTime;
	}


	public int getWorkedTime() {
		return workedTime;
	}


	public void setTimeNow() {
		timeNow = LocalDateTime.now().withSecond(0).withNano(0);//現在時刻。秒以下は0とする。
		//参考：LocalDateTime ldt = LocalDateTime.now().withHour(12).withMinute(0).withSecond(0).withNano(0);
	}
	
	public int setClockInTime() {//実出社時間から出社時間を計算 18時以降5時までの出社の場合は警告として１を返す。//遅刻時間も算出
		
		this.setTimeNow();//現在時間をセット
		defaultClockInTime = timeNow.with(LocalTime.of(9,0));//規定出社時間を算出
		
		if(timeNow.getHour() >= 5 && timeNow.getHour() <= 8) {//5~9時までの出社は9:00で計算
			clockInTime = timeNow.with(LocalTime.of(9,0));
			return 0;
		}else if(timeNow.getHour() >= 9 && timeNow.getHour() <= 17){//遅刻したときの処理。遅刻時間を算出
			clockInTime = timeNow;
			lateTime = (int)Duration.between(defaultClockInTime,timeNow).toMinutes();//遅刻時間取得
			return 0;
		}else {
			return 1;//このメソッドで1が返ってきたらエラー報告をする処理がされる。（後でつくる）
		}
	}
	
	public int setClockOutTime() {//引数に出社日。これで日またぎ退社をフィルターする。残業時間も出る。
		this.setTimeNow();//現在時間をセット
		defaultClockOutTime = timeNow.with(LocalTime.of(18,0));//規定出社時間を算出
		clockOutTime = timeNow;
		tentativeOverTime = (int)Duration.between(defaultClockOutTime,timeNow).toMinutes();//仮残業時間取得(分で)
		if(timeNow.getHour() >= 22) {//退社時間が22時を過ぎていたら深夜時間計測
			defaultMidNightTime = timeNow.with(LocalTime.of(22,0));//規定深夜時間算出
			midnightTime =  (int)Duration.between(defaultMidNightTime,timeNow).toMinutes();//深夜時間算出(分で)
		}
			
			//以下elseifのところは日またぎ残業をした際の処理。適当なレコード処理がわからず
//		}else if (clockInDay == timeNow.getDayOfMonth() + 1){//もし日またぎ残業をしていたら
//			defaultClockOutTime = defaultClockOutTime.minus(1,ChronoUnit.DAYS);//規定退社時間を前日に設定。
//			tentativeOverTime = (int)Duration.between(defaultClockOutTime,timeNow).toMinutes();
//			if(timeNow.getHour() >= 5)
//		}else {
//			return 1;//エラー処理。日またぎ出社をしていた場合
//			
//		}
		return 0;//正常終了
	}
	//実残業時間と不足時間算出
	public void setProperOverTimeAndLackTime(LocalDateTime aclockInTime, LocalDateTime aclockOutTime) {
		int calcTime =  (int)Duration.between(aclockInTime,aclockOutTime).toMinutes();
		if(calcTime > workedTime) {
			properOverTime = calcTime - workedTime;
		}else if(calcTime < workedTime) {
			lackTime = workedTime - calcTime;
		}
	} 
	
	
	public static void main(String[] args) {
//		var timeNow = LocalDateTime.now();//現在時刻取得
//
//		System.out.println(timeNow);
//		LocalDateTime defaultLeavingTime = LocalDateTime.now().with(LocalTime.of(18,0));//既定の退勤時間を
//		System.out.println(timeNow.getHour());
//		if(timeNow.getHour() >= 0 && timeNow.getHour() <= 5) {//0時から５時の間はって処理。いらんかも。
//			defaultLeavingTime = defaultLeavingTime.minus(1,ChronoUnit.DAYS);
//		}
//		System.out.println(defaultLeavingTime);
//		var duration = Duration.between(defaultLeavingTime,timeNow).toMinutes();//残業時間取得(分で)
//		System.out.println(duration);
		TimeInfomation a = new TimeInfomation();
		a.setClockInTime();
		System.out.println(a.timeNow);
		System.out.println(a.clockInTime);
		System.out.println(a.lateTime);
		
		String str = "2022-01-04-18:01";
		LocalDateTime test = ConvertLocalDateTime.convertLTDfromStr(str);
		String str2 = "2022-01-04-9:02";
		LocalDateTime test2 = ConvertLocalDateTime.convertLTDfromStr(str2);
		TimeInfomation b = new TimeInfomation();
		b.setProperOverTimeAndLackTime(test2,test);
		System.out.println(b.getProperOverTime());
		System.out.println(b.getLackTime());
		
	}

}
