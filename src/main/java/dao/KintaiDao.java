package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import beans.RecordBeans;

public class KintaiDao extends SuperDao {

	private static String TABLENAME = "kintai";
	
	public ArrayList<RecordBeans> selectAllRecord(RecordBeans ibeans) {
		loadJDBCDriver();
		System.out.println("ロードできました。");

		// メンバ一覧を保持するリスト
		ArrayList<RecordBeans> list = new ArrayList<>();
		RecordBeans btest = null;

		Connection con = null;
		try {
			/* データベースに接続 */
			con = getConnection();
			/* データベースにSQL文を送信 */
			String sql = "select * from " + TABLENAME+ " WHERE shain_id = '" + ibeans.getShain_id() +
					"' AND nengetu = '" + ibeans.getNengetu() + "';";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			/* メンバ一覧情報の作成 */
			while (rs.next()) {
				RecordBeans beans = new RecordBeans();

				// 検索結果をSample_Javabeansに格納
				beans.setShukinbi(rs.getDate("shukinbi"));
				beans.setShukin_zikan(rs.getString("shukin_zikan"));
				beans.setTaikin_zikan(rs.getString("taikin_zikan"));
				beans.setZangyou_zikan(rs.getInt("zangyou_zikan"));
				beans.setShinya_zangyou(rs.getInt("shinya_zangyou"));
				beans.setKyuzitu_syukin(rs.getInt("kyuzitu_syukin"));
				beans.setHusoku_zikan(rs.getInt("husoku_zikan"));
				beans.setYear(rs.getInt("nen"));
				beans.setMonth(rs.getInt("tuki"));
				beans.setDay(rs.getInt("hi"));
				beans.setDayofCount(rs.getInt("hi_count"));
				beans.setNengetu(rs.getString("nengetu"));
				

				// Sample_Javabeansを、 一覧を保持するリストに追加
				list.add(beans);
			}

			/* メンバ一覧を返す */

		} catch (SQLException e) {
			/* エラー時の処理 */
			e.printStackTrace();
			// メンバ一覧獲得に失敗した場合はnullを返す
			list = null;

		} finally {
			/* データベースからの切断 */
			closeConnection(con);
		}
		btest = list.get(0);
		System.out.println(btest.getDay());
		return list;
	}
	
	public int checkRecord2(RecordBeans beans) {
		int a = 0;
		//データベースに接続
		loadJDBCDriver();
		System.out.println("ロードできました。");
		Connection con = null;
		try {
			con = getConnection();//継承されてるメソッドとフィールドの接続情報を使ってアクセス
			//当日レコードがあるかチェック。あったら１なかったら０
			String sql = "select count(*) AS count from " + TABLENAME+" where shain_id = '" + beans.getShain_id() + 
					"' AND shukinbi = '" + beans.getShukinbi() + "' AND taikin_zikan IS NOT NULL;";
			Statement stmt = con.createStatement();//SQL文をデータベースへ送るためのオブジェクトを生成
			ResultSet rs = stmt.executeQuery(sql);//SQL文を実行し、結果をrsへ格納している。
			rs.next();//レコードを取り出す。
			a = rs.getInt("count");//そのレコードのcountカラムを取り出す。
			
		} catch (SQLException e) {
			/* エラー時の処理 */
			e.printStackTrace();
			// メンバ一覧獲得に失敗した場合はnullを返す

		} finally {
			/* データベースからの切断 */
			closeConnection(con);
		}
		return a; 
	}
	
	public String getTaikinZikan(RecordBeans beans) {
		loadJDBCDriver();
		String a;
		Connection con = null;
		
		try {
			/* データベースに接続 */
			con = getConnection();
			/* データベースにSQL文を送信 */
			String sql = "select * from " + TABLENAME+" where shain_id = '" + beans.getShain_id() + 
					"' AND shukinbi = '" + beans.getShukinbi() + "';";
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			/* メンバ一覧情報の作成 */
			rs.next();//レコードを取り出す。
			a = rs.getString("taikin_zikan");
			System.out.println(a);
			/* メンバ一覧を返す */

		} catch (SQLException e) {
			/* エラー時の処理 */
			e.printStackTrace();
			// メンバ一覧獲得に失敗した場合はnullを返す
			a = null;

		} finally {
			/* データベースからの切断 */
			closeConnection(con);
		}
		return a;
	}
	
	public String getShukinZikan(RecordBeans beans) {
		loadJDBCDriver();
		String a;
		Connection con = null;
		
		try {
			/* データベースに接続 */
			con = getConnection();
			/* データベースにSQL文を送信 */
			String sql = "select * from " + TABLENAME+" where shain_id = '" + beans.getShain_id() + 
					"' AND shukinbi = '" + beans.getShukinbi() + "';";
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			/* メンバ一覧情報の作成 */
			rs.next();//レコードを取り出す。
			a = rs.getString("shukin_zikan");
			System.out.println(a);
			System.out.println("おおおおおおおお");
			/* メンバ一覧を返す */

		} catch (SQLException e) {
			/* エラー時の処理 */
			e.printStackTrace();
			// メンバ一覧獲得に失敗した場合はnullを返す
			a = null;

		} finally {
			/* データベースからの切断 */
			closeConnection(con);
		}
		return a;
	}
	
	public void updateRecord(RecordBeans beans) {
	loadJDBCDriver();
	Connection con = getConnection();

	String sql = "UPDATE " + TABLENAME + " SET taikin_zikan = ?, zangyou_zikan = ? ,husoku_zikan = ? ,shinya_zangyou = ? WHERE shain_id = '"
			+ beans.getShain_id() + "' AND shukinbi = '" + beans.getShukinbi() + "';";

	try {
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setString(1, beans.getTaikin_zikan());
		statement.setInt(2, beans.getZangyou_zikan());
		statement.setInt(3, beans.getHusoku_zikan());
		statement.setInt(4, beans.getShinya_zangyou());

		System.out.println(sql + "を発行しました"); // OK
		// Statement stmt = con.createStatement();

		statement.executeUpdate();
		//commitする。自動コミットの場合は、使わない。
		//con.commit();
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		/* データベースからの切断 */
		closeConnection(con);
		System.out.println("DAO抜ける！");
	}
	return;
}
	
	
	public void insertRecord(RecordBeans beans) {
	loadJDBCDriver();
	Connection con = getConnection();

	String sql = "insert into " + TABLENAME + " values(?,?,?,?,?,?,?,?,?,?,?,?,?);";

	try {
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setString(1, beans.getShain_id());
		statement.setDate(2, beans.getShukinbi());
		statement.setString(3, beans.getShukin_zikan());
		statement.setString(4, null);
		statement.setInt(5, 0);
		statement.setInt(6, 0);
		statement.setInt(7, 0);
		statement.setInt(8, 0);
		statement.setInt(9, beans.getYear());
		statement.setInt(10, beans.getMonth());
		statement.setInt(11, beans.getDay());
		statement.setInt(12, beans.getDayofCount());
		statement.setString(13, beans.getNengetu());

		System.out.println(sql + "を発行しました"); // OK
		// Statement stmt = con.createStatement();

		statement.executeUpdate();
		//commitする。自動コミットの場合は、使わない。
		//con.commit();
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		/* データベースからの切断 */
		closeConnection(con);
		System.out.println("DAO抜ける！");
	}
	return;
}
	
	public int checkRecord(RecordBeans beans) {
		int a = 0;
		//データベースに接続
		loadJDBCDriver();
		System.out.println("ロードできました。");
		Connection con = null;
		try {
			con = getConnection();//継承されてるメソッドとフィールドの接続情報を使ってアクセス
			//当日レコードがあるかチェック。あったら１なかったら０
			String sql = "select count(*) AS count from " + TABLENAME+" where shain_id = '" + beans.getShain_id() + 
					"' AND shukinbi = '" + beans.getShukinbi() + "';";
			Statement stmt = con.createStatement();//SQL文をデータベースへ送るためのオブジェクトを生成
			ResultSet rs = stmt.executeQuery(sql);//SQL文を実行し、結果をrsへ格納している。
			rs.next();//カーソルを移動させる。(最初はカラム行のところにある。nextで次の行が取り出せる)
			a = rs.getInt("count");//そのレコードのcountカラムを取り出す。引数に数字を入れることで何番目のカラムのデータを取り出すかを指定することができる。（最初は１）
			
		} catch (SQLException e) {
			/* エラー時の処理 */
			e.printStackTrace();
			// メンバ一覧獲得に失敗した場合はnullを返す

		} finally {
			/* データベースからの切断 */
			closeConnection(con);
		}
		return a; 
	}
	
	public int checkRecordTaikin(RecordBeans beans) {
		int a = 0;
		//データベースに接続
		loadJDBCDriver();
		System.out.println("ロードできました。");
		Connection con = null;
		try {
			con = getConnection();//継承されてるメソッドとフィールドの接続情報を使ってアクセス
			//mailaddressとパスワードで一致するかチェック
			String sql = "select count(*) AS count from " + TABLENAME+" where shain_id = '" + beans.getShain_id() + 
					"' AND taikin_zikan IS NOT NULL AND shukinbi = '" + beans.getShukinbi() + "';";
			Statement stmt = con.createStatement();//SQL文をデータベースへ送るためのオブジェクトを生成
			ResultSet rs = stmt.executeQuery(sql);//SQL文を実行し、結果をrsへ格納している。
			rs.next();//レコードを取り出す。
			a = rs.getInt("count");//そのレコードのcountカラムを取り出す。
			
		} catch (SQLException e) {
			/* エラー時の処理 */
			e.printStackTrace();
			// メンバ一覧獲得に失敗した場合はnullを返す

		} finally {
			/* データベースからの切断 */
			closeConnection(con);
		}
		return a; 
	}
	
	public int checkErrorPattern(RecordBeans beans) {
		int a = 0;
		//データベースに接続
		loadJDBCDriver();
		System.out.println("ロードできました。");
		Connection con = null;
		try {
			con = getConnection();//継承されてるメソッドとフィールドの接続情報を使ってアクセス
			//mailaddressとパスワードで一致するかチェック
			String sql = "select count(*) AS count from " + TABLENAME+" where shain_id = '" + beans.getShain_id() + 
					"' AND taikin_zikan IS NOT NULL AND shukin_zikan IS NULL AND shukinbi = '" + beans.getShukinbi() + "';";
			Statement stmt = con.createStatement();//SQL文をデータベースへ送るためのオブジェクトを生成
			ResultSet rs = stmt.executeQuery(sql);//SQL文を実行し、結果をrsへ格納している。
			rs.next();//レコードを取り出す。
			a = rs.getInt("count");//そのレコードのcountカラムを取り出す。
			
		} catch (SQLException e) {
			/* エラー時の処理 */
			e.printStackTrace();
			// メンバ一覧獲得に失敗した場合はnullを返す

		} finally {
			/* データベースからの切断 */
			closeConnection(con);
		}
		return a; 
	}

	// ******** tableの全データ読み出し *******************
//	@Override
//	public ArrayList<ShohinJavabeans> selectAllRecord() {
//		loadJDBCDriver();
//		System.out.println("ロードできました。");
//
//		// メンバ一覧を保持するリスト
//		ArrayList<ShohinJavabeans> list = new ArrayList<>();
//
//		Connection con = null;
//		try {
//			/* データベースに接続 */
//			con = getConnection();
//
//			/* データベースにSQL文を送信 */
//			String sql = "select * from " + TABLENAME+" order by shohin_id";
//			Statement stmt = con.createStatement();
//			ResultSet rs = stmt.executeQuery(sql);
//
//			/* メンバ一覧情報の作成 */
//			while (rs.next()) {
//				ShohinJavabeans shohinbeans = new ShohinJavabeans();
//
//				// 検索結果をSample_Javabeansに格納
//				shohinbeans.setShohin_id(rs.getString("shohin_id"));
//				shohinbeans.setShohin_mei(rs.getString("shohin_mei"));
//				shohinbeans.setShohin_bunrui(rs.getString("shohin_bunrui"));
//				shohinbeans.setHanbai_tanka(rs.getInt("hanbai_tanka"));
//				shohinbeans.setShiire_tanka(rs.getInt("shiire_tanka"));
//				shohinbeans.setTorokubi(rs.getDate("torokubi"));
//
//				// Sample_Javabeansを、 一覧を保持するリストに追加
//				list.add(shohinbeans);
//			}
//
//			/* メンバ一覧を返す */
//
//		} catch (SQLException e) {
//			/* エラー時の処理 */
//			e.printStackTrace();
//			// メンバ一覧獲得に失敗した場合はnullを返す
//			list = null;
//
//		} finally {
//			/* データベースからの切断 */
//			closeConnection(con);
//		}
//		return list;
//	}
//
//	// Tableに1レコード挿入
//	// @Override
//	public void insertRecord(ShohinJavabeans shohinjbeans) {
//		loadJDBCDriver();
//		Connection con = getConnection();
//
//		String sql = "insert into " + TABLENAME + " values(?,?,?,?,?,?);";
//
//		try {
//			PreparedStatement statement = con.prepareStatement(sql);
//			statement.setString(1, shohinjbeans.getShohin_id());
//			statement.setString(2, shohinjbeans.getShohin_mei());
//			statement.setString(3, shohinjbeans.getShohin_bunrui());
//			statement.setInt(4, shohinjbeans.getHanbai_tanka());
//			statement.setInt(5, shohinjbeans.getShiire_tanka());
//			statement.setDate(6, shohinjbeans.getTorokubi());
//
//
//			System.out.println(sql + "を発行しました"); // OK
//			// Statement stmt = con.createStatement();
//
//			statement.executeUpdate();
//			//commitする。自動コミットの場合は、使わない。
//			//con.commit();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			/* データベースからの切断 */
//			closeConnection(con);
//			System.out.println("DAO抜ける！");
//		}
//		return;
//	}
}
