package beans;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;

import common.ConvertLocalDateTime;

public class RecordBeans implements Serializable {
	private static final long serialVersionID = 1L;

	private String shain_id;
	private Date shukinbi;
	private String shukin_zikan;
	private String taikin_zikan;
	private int zangyou_zikan;
	private int shinya_zangyou;
	private int kyuzitu_syukin;
	private int husoku_zikan;
	private String nengetu;//YYYY-MM
	private String shukin_zikan_2;//HH:MM
	private int year;
	private int month;
	private int day;
	private int dayofCount;
	
	public void setYearMonthDayDayofCount(LocalDateTime a) {
		ArrayList<Integer> list = new ArrayList<>();
		list = ConvertLocalDateTime.convertLTDtoList(a);
		setYear((int)list.get(0)); 
		setMonth((int)list.get(1));
		setDay((int)list.get(2));
		setDayofCount((int)list.get(2) - 1);
	}
	
	public int getYear() {
		
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getDayofCount() {
		return dayofCount;
	}
	public void setDayofCount(int dayofCount) {
		this.dayofCount = dayofCount;
	}
	public String getShain_id() {
		return shain_id;
	}
	public void setShain_id(String shain_id) {
		this.shain_id = shain_id;
	}
	public Date getShukinbi() {
		return shukinbi;
	}
	public void setShukinbi(Date shukinbi) {
		this.shukinbi = shukinbi;
	}
	public String getShukin_zikan() {
		return shukin_zikan;
	}
	public void setShukin_zikan(String shukin_zikan) {
		this.shukin_zikan = shukin_zikan;
	}
	public String getTaikin_zikan() {
		return taikin_zikan;
	}
	public void setTaikin_zikan(String taikinz_zikan) {
		this.taikin_zikan = taikinz_zikan;
	}
	public int getZangyou_zikan() {
		return zangyou_zikan;
	}
	public void setZangyou_zikan(int zangyou_zikan) {
		this.zangyou_zikan = zangyou_zikan;
	}
	public int getShinya_zangyou() {
		return shinya_zangyou;
	}
	public void setShinya_zangyou(int shinya_zangyou) {
		this.shinya_zangyou = shinya_zangyou;
	}
	public int getKyuzitu_syukin() {
		return kyuzitu_syukin;
	}
	public void setKyuzitu_syukin(int kyuzitu_syukin) {
		this.kyuzitu_syukin = kyuzitu_syukin;
	}
	public int getHusoku_zikan() {
		return husoku_zikan;
	}
	public void setHusoku_zikan(int husoku_zikan) {
		this.husoku_zikan = husoku_zikan;
	}
	public String getNengetu() {
		return nengetu;
	}
	public void setNengetu(String nengetu) {
		this.nengetu = nengetu;
	}
	public String getShukin_zikan_2() {
		return shukin_zikan_2;
	}
	public void setShukin_zikan_2(String shukin_zikan_2) {
		this.shukin_zikan_2 = shukin_zikan_2;
	}
	
	
	
	
}
