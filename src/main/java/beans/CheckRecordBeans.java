package beans;

import java.io.Serializable;
import java.sql.Date;

public class CheckRecordBeans implements Serializable {
	private static final long serialVersionID = 1L;

	private String shain_id;
	private Date access_day;
	private int flag;
	
	
	public String getShain_id() {
		return shain_id;
	}
	public void setShain_id(String shain_id) {
		this.shain_id = shain_id;
	}
	public Date getAccess_day() {
		return access_day;
	}
	public void setAccess_day(Date access_day) {
		this.access_day = access_day;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}

}
