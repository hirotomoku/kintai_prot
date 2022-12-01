package common;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


/**
 * @author takase
 * 各int引数またはarraylist引数によりLocalTimeDate型のオブジェクトをつくるConvertLTDAndIntと
 * LocalTimeDate型のオブジェクトをつくるConvertLTDAndIntからingeterのArraylistをつくるConvertLTDtoIntメソッドがあるクラス
 * 秒数はないものとして算出しており、秒数が引数として入ることを想定していない。
 * ConvertLTDfromStrは年月日時分が”年-月-日-時-分”のstring型が入ってくることを想定している。
 *
 */
public class ConvertLocalDateTime {
	
	static public LocalDateTime convertLTDfromStr(String str) {//”年-月-日-時-分”からLocalDateTime形に変換
		String[] strs = str.split("[-:]");
		ArrayList<Integer>test = new ArrayList<>();
		for(String in: strs) {
			test.add(Integer.parseInt(in));
		}
		LocalDateTime ltd = convertLTDfromInt(test);
		return ltd;
	}
	
	static public String convertLTDtoStr(LocalDateTime time) {//LocalDateTime形から”年-月-日-時:分”に変換
		ArrayList<Integer> temp = convertLTDtoList(time);
		ArrayList<String> temp2 = new ArrayList<>();
		for(Integer i: temp) {
			temp2.add(i.toString());
		}
		String str = temp2.get(0) + "-" + temp2.get(1) + "-" + temp2.get(2) + "-" + temp2.get(3) + ":" + temp2.get(4); 
		return str;
	}
	
	static public String convertLTDtoStr_nengetu(LocalDateTime time) {//LocalDateTime形から”年-月-日-時-分”に変換
		ArrayList<Integer> temp = convertLTDtoList(time);
		ArrayList<String> temp2 = new ArrayList<>();
		for(Integer i: temp) {
			temp2.add(i.toString());
		}
		String str = temp2.get(0) + "-" + temp2.get(1); 
		return str;
	}
	
	static public String convertLTDtoStr_zihun(LocalDateTime time) {//LocalDateTime形から”年-月-日-時-分”に変換
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String str = time.format(formatter);
		return str;
	}
	
	static public LocalDateTime convertLTDfromInt(int year, int month, int day, int hour, int minute) {
		LocalDateTime convTime = LocalDateTime.of(year, month,day,hour,minute);
		return convTime;
		
	}
	
	
	static public LocalDateTime convertLTDfromInt(ArrayList<Integer> list) {
		LocalDateTime convTime = LocalDateTime.of(
				list.get(0),
				list.get(1),
				list.get(2),
				list.get(3),
				list.get(4)
				);
		return convTime;	
	}
	
	
	static public ArrayList<Integer> convertLTDtoList(LocalDateTime ltd) {
		var convTime = new ArrayList<Integer>();
		convTime.add(0,ltd.getYear());
		convTime.add(1,ltd.getMonthValue());
		convTime.add(2,ltd.getDayOfMonth());
		convTime.add(3,ltd.getHour());
		convTime.add(4,ltd.getMinute());
		return convTime;
		
	}
	
	static public Date convertLTDtoDate(LocalDateTime time) {
		ArrayList<Integer> temp = convertLTDtoList(time);
		ArrayList<String> temp2 = new ArrayList<>();
		for(Integer i: temp) {
			temp2.add(i.toString());
		}
		String str = temp2.get(0) + "-" + temp2.get(1) + "-" + temp2.get(2);
		Date sqlDate= Date.valueOf(str);
		return sqlDate;
	}

//以下テスト時に使用したmainメソッド
	
//	public static void main(String[] args) {
//		ConvertLocalTimeDateAndInt test = new ConvertLocalTimeDateAndInt();
//		LocalDateTime a = test.ConvertLTDfromInt(2022, 10, 24, 18, 30);
//		System.out.println(a);
//		ArrayList<Integer> b =test.ConvertLTDtoInt(a);
//		System.out.println(b);
//		LocalDateTime c = test.ConvertLTDfromInt(b);
//		System.out.println(c);
//
//	}
	public static void main(String[] args) {
//		String str = "2022-10-24-18:50";
//		String[] strs = str.split("[-:]");
//		for(String i: strs) {
//			System.out.println(i);
//		}
//		ArrayList<Integer>test = new ArrayList<>();
//		for(String in: strs) {
//			test.add(Integer.parseInt(in));
//		}
//		System.out.println(test);
		String str = "2022-01-04-18:03";
		LocalDateTime test = convertLTDfromStr(str);
		System.out.println(test);
		
		String str2 = convertLTDtoStr(LocalDateTime.now());
		System.out.println(str2);
		}

}
