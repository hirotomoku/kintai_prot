package servlet;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import basic.Calendar1;
import basic.CalendarLogic;
import beans.RecordBeans;
import common.ConvertLocalDateTime;
import common.TimeInfomation;
import common.WorkingState;
import model.KintaiMain;

/**
 * Servlet implementation class KintaiServlet
 */
@WebServlet("/KintaiServlet")
public class KintaiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public int j;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KintaiServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	private void forwardJSP(String url, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(true);
		
//		String id = request.getParameter("employee_id");//JSPからidを取得（手順１）
		String id = "S0001";
		
		CalendarLogic logic = new CalendarLogic();
		Calendar1 c1 = null;
		c1 = logic.createCalendar();
		j = c1.getMonth();
		session.setAttribute("c1", j);
		
		
//		
		String button = request.getParameter("button");
		if(button == null) {
			button = "";
		}
		//String id = request.getParameter("id");
//		id = request.getParameter("id");
//		button = request.getParameter("button");
		switch(button) {
		//出勤ボタンがおされたら
		case "出勤":
			TimeInfomation nowIn = new TimeInfomation();//現在時刻のクラス作成
			if(nowIn.setClockInTime() == 0) {//現在時刻をセット＆エラーがなかったら
				LocalDateTime atime = nowIn.getClockInTime();//現在時刻を取得
				RecordBeans beans = new RecordBeans();
				beans.setShain_id(id);
				beans.setShukinbi(ConvertLocalDateTime.convertLTDtoDate(atime));
				System.out.println(beans.getShukinbi());
				beans.setShukin_zikan(ConvertLocalDateTime.convertLTDtoStr(atime));
				beans.setNengetu(ConvertLocalDateTime.convertLTDtoStr_nengetu(atime));
				beans.setYearMonthDayDayofCount(atime);
				KintaiMain main = new KintaiMain();
				main.insertRecord(beans);
			}else {
				System.out.println("エラー");
			}
			break;
		case "退勤":	
			//退勤ボタンがおされたら
			TimeInfomation nowOut = new TimeInfomation();
			if(nowOut.setClockOutTime() == 0) {
				//idと現在日にて、レコードがあるか確認
				RecordBeans beans2 = new RecordBeans();
				beans2.setShain_id(id);
				beans2.setShukinbi(ConvertLocalDateTime.convertLTDtoDate(nowOut.getTimeNow()));//現在時刻から月日を算出
				System.out.println(beans2.getShukinbi());
				//beans2.setTaikin_zikan(ConvertLocalDateTime.convertLTDtoStr(atime));
				KintaiMain main = new KintaiMain();//上でつくったビーンズで当日レコードの有無を確認。
				String shukinzikan = main.getShukinZikan(beans2);
				//出勤時間が取り出せたら（正常）
				if(shukinzikan != null) {
					LocalDateTime skz = ConvertLocalDateTime.convertLTDfromStr(shukinzikan);//出勤時間のLDT型をつくる
					nowOut.setProperOverTimeAndLackTime(skz,nowOut.getTimeNow());//出勤時間と退勤時間から残業時間と不足時間を算出
					System.out.println("ここまできました" + beans2.getShain_id());
					System.out.println("残業時間と不足時間" + nowOut.getProperOverTime() + "と" + nowOut.getLackTime());
					beans2.setTaikin_zikan(ConvertLocalDateTime.convertLTDtoStr(nowOut.getClockOutTime()));//退勤時間をビーンズに登録
					beans2.setZangyou_zikan(nowOut.getProperOverTime());//残業時間をビーンズに登録
					beans2.setHusoku_zikan(nowOut.getLackTime());//不足時間をビーンズに登録
					beans2.setShinya_zangyou(nowOut.getMidnightTime());//深夜時間をビーンズに登録
					beans2.setNengetu(ConvertLocalDateTime.convertLTDtoStr_nengetu(nowOut.getTimeNow()));
					//ビーンズ登録完了
					//レコードアップデート
					KintaiMain upd = new KintaiMain();
					if(upd.checkData2(beans2)==0) {//退勤時間がすでに登録されていたエラー
						upd.updateRecord(beans2);//問題なかった場合はアップデート
					}else {
						System.out.println("エラー：本日の登録はできません。");
						System.exit(0);
					}
				}else {
					System.out.println("エラー：出勤時間はみつかりませんでした。");
					System.exit(0);
				}
			}else {
				System.out.println("エラー：18時を超えました。本日の登録はできません。");
				System.exit(0);
			}
			break;
		case "先月":
			int h = j - 1;
			j = h;
			session.setAttribute("c1", h);
			break;
		case "来月":
			int p = j + 1;
			j = p;
			session.setAttribute("c1", p);
			break;
		default :
			break;
		}
		//共通・レコード状況確認。
		
		TimeInfomation nowTI = new TimeInfomation();
		nowTI.setTimeNow();//アクセス時間をセット
		LocalDateTime now = nowTI.getTimeNow();//アクセス時間を取得（手順２）
		Date today = ConvertLocalDateTime. convertLTDtoDate(now);//
		RecordBeans check = new RecordBeans();//ビーンズにidと今日の日付をセット(手順３)
		check.setShain_id(id);
		check.setShukinbi(today);
		check.setNengetu(ConvertLocalDateTime.convertLTDtoStr_nengetu(now));
		KintaiMain test = new KintaiMain();
		if(test.checkError(check)== 1)
			System.out.println("エラー登録内容に誤りがあるようです。");
		WorkingState state = new WorkingState(test.checkData(check),test.checkTaikinData(check));
		String zihun_s = null;//ボタンの上に表示される出勤時間
		String zihun_t = null;//ボタンの上に表示される退勤時間
		if(test.checkData(check) == 0) {
			zihun_s = "00:00";
			zihun_t = "00:00";
		}else if(test.checkData(check) == 1 && test.checkTaikinData(check) == 0) {
			String shukinzikan = test.getShukinZikan(check);
			LocalDateTime ai = ConvertLocalDateTime.convertLTDfromStr(shukinzikan);
			zihun_s = ConvertLocalDateTime.convertLTDtoStr_zihun(ai);
			zihun_t = "00:00";
		}else if(test.checkData(check) == 1 && test.checkTaikinData(check) == 1){
			String shukinzikan = test.getShukinZikan(check);
			LocalDateTime ai = ConvertLocalDateTime.convertLTDfromStr(shukinzikan);
			zihun_s = ConvertLocalDateTime.convertLTDtoStr_zihun(ai);
			String taikinzikan = test.getTaikinZikan(check);
			LocalDateTime ue = ConvertLocalDateTime.convertLTDfromStr(taikinzikan);
			zihun_t = ConvertLocalDateTime.convertLTDtoStr_zihun(ue);
		}
		session.setAttribute("zihun_s",zihun_s);
		session.setAttribute("zihun_t",zihun_t);
		System.out.println(zihun_s + "と"+ zihun_t);
		String inButton = state.InButtonState;//ボタンの状態
		String OutButton = state.OutButtonState;//ボタンの状態
		System.out.println(inButton + "と"+ OutButton);
		session.setAttribute("state1",inButton);
		session.setAttribute("state2",OutButton);

	
		//共通全レコード抽出
		//kintaimainからリストを作成　kintaimain dao 当該メソッド作成済み
		//check.setYearMonthDayDayofCount(now);
		ArrayList<RecordBeans> list = test.selectAllRecord(check);
		session.setAttribute("list", list);
		
//		forwardJSP("TopMenu.jsp", request, response);
		
		
		
		

		
		forwardJSP("TopMenu.jsp", request, response);
		//合計時間抽出
	}
	

}
