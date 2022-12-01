package beans;

import java.io.Serializable;
import java.sql.Date;

public class RegisterClockInRecordBeans implements Serializable {
	private static final long serialVersionID = 1L;

	private String shain_id;
	private Date shukinbi;
	private String shukin_zikan;
	private String taikinz_zikan;
	
	
	
	public String getTaikinz_zikan() {
		return taikinz_zikan;
	}
	public void setTaikinz_zikan(String taikinz_zikan) {
		this.taikinz_zikan = taikinz_zikan;
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
	
	
	
}
