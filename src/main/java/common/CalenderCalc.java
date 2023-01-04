package common;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CalenderCalc {
	private int thisMonth;
	private int numberOfDays;
	private int[] thisMonthOfDays;
	private Map<Integer,String> mapOfThisMonth;
	private int aMonth;
	private int aMonthOfDays;
	private Map<Integer,String> mapOfAMonth;
	
	


	public CalenderCalc() {
		setThisMonth(LocalDate.now().getMonthValue());
		this.numberOfDays = getLastDay();
		Map<Integer,String>temp = new HashMap<>();
		for(int i = 1; i <= getNumberOfDays();i++) {
			var ld = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), i).getDayOfWeek();
			String str = ld.getDisplayName(TextStyle.SHORT, Locale.getDefault());
			temp.put(i, str);
		}
		setMapOfThisMonth(temp);
	}
	
	public CalenderCalc(int count) {
		var lod = LocalDate.now().plusMonths(count);
		setaMonth(lod.getMonthValue());
		setaMonthofDays(getLastDayOfAmonth(lod));
		Map<Integer,String>temp = new HashMap<>();
		for(int i = 1; i <= getaMonthOfDays();i++) {
			var ld = LocalDate.of(lod.getYear(), lod.getMonthValue(), i).getDayOfWeek();
			String str = ld.getDisplayName(TextStyle.SHORT, Locale.getDefault());
			temp.put(i, str);
		}
		setMapOfAMonth(temp);
		
	}

	public Map<Integer, String> getMapOfAMonth() {
		return mapOfAMonth;
	}

	public void setMapOfAMonth(Map<Integer, String> mapOfAMonth) {
		this.mapOfAMonth = mapOfAMonth;
	}

	public void setaMonthofDays(int aMonthofDays) {
		this.aMonthOfDays = aMonthofDays;
	}

	
	public int[] getThisMonthOfDays() {
		return thisMonthOfDays;
	}

	public void setThisMonthOfDays(int[] thisMonthOfDays) {
		this.thisMonthOfDays = thisMonthOfDays;
	}

	public Map<Integer, String> getMapOfThisMonth() {
		return mapOfThisMonth;
	}

	public void setMapOfThisMonth(Map<Integer, String> map) {
		this.mapOfThisMonth = map;
	}
	
	public int getThisMonth() {
		return thisMonth;
	}


	public void setThisMonth(int thisMonth) {
		this.thisMonth = thisMonth;
	}


	public int getNumberOfDays() {
		return numberOfDays;
	}


	public void setNumberOfDays(int numberOfDays) {
		this.numberOfDays = numberOfDays;
	}


	public int getaMonth() {
		return aMonth;
	}


	public void setaMonth(int aMonth) {
		this.aMonth = aMonth;
	}


	public int getaMonthOfDays() {
		return aMonthOfDays;
	}


	public void setaMonthOfDays(int aMonthofDays) {
		this.aMonthOfDays = aMonthofDays;
	}


	public  int getLastDay() {
		int lastDay = LocalDate.now().lengthOfMonth();
		
		return lastDay;		
	}
	
	public  int getLastDayOfAmonth(LocalDate ld) {
		int lastDay = ld.lengthOfMonth();
		
		return lastDay;		
	}
	
	
	
	
	public static void main(String[] args) {
		CalenderCalc cl = new CalenderCalc(0);
//		System.out.println(LocalDate.now().getDayOfWeek());
//		System.out.println(cl.getLastDay());
//		System.out.println(cl.getThisMonth());
		var temp = cl.getMapOfAMonth();
		for(Map.Entry<Integer,String> entry : temp.entrySet()) {
			System.out.println(cl.getaMonth() + "月" + entry.getKey() + "日" + "(" + entry.getValue() + ")");
		}
	}

}
