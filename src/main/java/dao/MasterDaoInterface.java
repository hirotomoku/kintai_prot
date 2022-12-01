package dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;


/**
 * システムで使う共通DAOのメソッドを規定する。<br/>
 * <共通メソッドとは、
 * <ul>
 * <li>コネクションの確立</li>
 * <li>コネクションの解除</li>
 * </ul>
 * 実行SQLとしては、<br/>
 * <ul>
 * <li>テーブルの全データ読み出し</li>
 * <li>１レコードの読み出し</li>
 * <li>１レコードの挿入</li>
 * <li>１レコードの更新</li>
 * <li>１レコードの削除</li>
 * </ul>
 * を実装すること規定します。
 *
 * @author m_hosoi
 *
 */
public interface MasterDaoInterface {

	/**
	 * JDBCロードメソッド
	 *
	 * @return ナシ
	 */
	void loadJDBCDriver();

	/**
	 * 接続メソッド （DriverManagerを利用した接続）
	 *
	 * @return Connection オブジェクトを返す
	 */
	public Connection getConnection();

	/**
	 * 切断メソッド
	 *
	 * @param Connection
	 */
	public void closeConnection(Connection con);

	/**
	 * テーブル内全レコードの読み出しメソッド
	 * @return ArrayList
	 **/
	public ArrayList<?> selectAllRecord();

	/**
	 * テーブル内1レコードの読み出しメソッド
	 *
	 * @param オブジェクト
	 *            検索キー
	 * @return ArrayList
	 **/
	public List<?> selectRecordById();

	/**
	 * １レコード登録メソッド
	 *
	 * @param オブジェクト
	 *            （プライマリ・キー）
	 * @return ナシ
	 **/
	public void insertRecord(Object obj);

	/**
	 * １レコード情報更新用メソッド
	 *
	 * @param オブジェクト
	 *            （プライマリ・キー）
	 * @return ナシ
	 *
	 **/
	public void updateRecordById(Object obj);

	/**
	 * プライマリキーで１レコードを取得する
	 *
	 * @param オブジェクト
	 *            （プライマリ・キー）
	 * @return Object
	 */
	public Object selectRecordById(Object obj);

	/**
	 * 1レコードの削除
	 *
	 * @param オブジェクト
	 *            （プライマリ・キー）
	 * @return ナシ
	 */
	public void deleteRecordById(Object obj);

}
