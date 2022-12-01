package model;

import java.util.ArrayList;

import beans.RecordBeans;
import dao.KintaiDao;

/**
 * ビジネスロジック層、つまりModelの処理の中心となるクラスです。<br>
 * このサンプルでは、ShohinJavabeansの内容をDBのテーブルに書くだけで処理はありませんが<br>
 * 本来は、このビジネスロジック層、Ｍが処理を担当します。<br>
 * このＭを作成しないとサーブレットから、ＤＢにアクセスすることになりＭＶＣモデルとは<br>
 * 言えなくなります<br>
 * @author M.Hosoi
 *
 */
public class KintaiMain {
	
	public ArrayList<RecordBeans> selectAllRecord(RecordBeans beans){
		KintaiDao kdao = new KintaiDao();
		ArrayList<RecordBeans> list = kdao.selectAllRecord(beans);
		return list;
	}
	
	public int checkData2(RecordBeans jbeans) {
		KintaiDao kdao = new KintaiDao();
		int judge = kdao.checkRecord2(jbeans);
		if(judge == 0)
			return 0;
		else 
			return 1;
	}
	
	public void updateRecord(RecordBeans jbeans) {
		KintaiDao kdao = new KintaiDao();
		kdao.updateRecord(jbeans);
	}
	
	public String getShukinZikan(RecordBeans jbeans) {
		KintaiDao kdao = new KintaiDao();
		String shukinzikan = kdao.getShukinZikan(jbeans);
		return shukinzikan;
	}
	
	public String getTaikinZikan(RecordBeans jbeans) {
		KintaiDao kdao = new KintaiDao();
		String taikinzikan = kdao.getTaikinZikan(jbeans);
		return taikinzikan;
	}
	
	public void insertRecord(RecordBeans jbeans) {
		KintaiDao kdao = new KintaiDao();
		kdao.insertRecord(jbeans);
	}

	public int checkData(RecordBeans jbeans) {
		KintaiDao kdao = new KintaiDao();
		int judge = kdao.checkRecord(jbeans);
		if(judge == 1)
			return 1;
		else 
			return 0;
	}
	
	public int checkTaikinData(RecordBeans jbeans) {
		KintaiDao kdao = new KintaiDao();
		int judge = kdao.checkRecordTaikin(jbeans);
		if(judge == 1)
			return 1;
		else 
			return 0;
	}
	
	public int checkError(RecordBeans jbeans) {
		KintaiDao kdao = new KintaiDao();
		int judge = kdao.checkErrorPattern(jbeans);
		if(judge == 1)
			return 1;
		else 
			return 0;
	}

//	public ArrayList<ShohinJavabeans> selectAll() {
//		dao.ShohinDao sdao = new ShohinDao();
//		ArrayList<ShohinJavabeans> alist = sdao.selectAllRecord();
//
//		return alist;
//	}

	}