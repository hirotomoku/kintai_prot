package basic;

import java.util.Calendar;

public class CalendarLogic {

	//カレンダーインスタンスを生成するメソッド（int...は可変長）
	public Calendar1 createCalendar(int...args) {
		//カレンダーインスタンス生成
		Calendar1 c1 = new Calendar1();
		//現在日時でカレンダーインスタンス生成
		Calendar c2 = Calendar.getInstance();
		//2つの引数がきていたら
		if (args.length == 2) {
			//最初の引数で年を設定
			c2.set(Calendar.YEAR, args[0]);
			//次の引数で月を設定
			c2.set(Calendar.MONTH, args[1] - 1);
		}
		
//マイカレンダーに年を設定
		c1.setYear(c2.get(Calendar.YEAR));
		
		//マイカレンダーに元号を設定
		if(c1.getYear() > 2018) {
			c1.setGengou("令和" + (c1.getYear() - 2018));
		} else if (c1.getYear() > 1988) {
			c1.setGengou("平成" + (c1.getYear() - 1988));
		} else if (c1.getYear() > 1925) {
			c1.setGengou("昭和" + (c1.getYear() - 1925));
		} else if (c1.getYear() > 1911) {
			c1.setGengou("大正" + (c1.getYear() - 1911));
		} else {
			c1.setGengou("" + c1.getYear());
		}
		
//マイカレンダーに月の設定
		c1.setMonth(c2.get(Calendar.MONTH) + 1);
		
		//その月の1日が何曜日か調べる為に日付を1日にする
		c2.set(Calendar.DATE, 1);
		
		//カレンダーの最初の空白の数
		int before = c2.get(Calendar.DAY_OF_WEEK) - 1;
		
		//カレンダーの日付の数
		int daysCount = c2.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		//その月の最後の月が何曜日かを調べるために日付を最終日にする
		c2.set(Calendar.DATE, daysCount);
		
		//最後の日後の空白の数
		int after = 7 - c2.get(Calendar.DAY_OF_WEEK);
		
		//すべての要素数
		int total = before + daysCount + after;
		
		//その要素数を幅7個の配列に入れた場合何行になるか
		int rows = total/7;
		
		//その行数で2次元配列を生成
		String[][] data = new String[rows][7];
		
		//今見ているカレンダーが今月かどうかを調べるために
		//この瞬間の日付情報を持つもう一つのインスタンスを作成しておく
		Calendar now = Calendar.getInstance();
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < 7; j++) {
				if(i == 0 && j < before || i == rows - 1 && j > (7 - after)) {
					//カレンダーの前後に入る空白の部分は空文字
					data[i][j] = "";
				} else {
					//カウンター変数と実際の日付変換
					int date = i * 7 + j + 1 - before;
					//配列に日付を入れる
					data[i][j] = String.valueOf(date);
					//今作業しているマイカレンダーが今月のカレンダーだったら今日の日付の
					//先頭に*を付与する
					if(now.get(Calendar.DATE) == date && now.get(Calendar.MONTH) == c1.getMonth()
							- 1 && now.get(Calendar.YEAR) == c1.getYear()) {
						data[i][j] = "*" + data[i][j];
					}
				}
			}
		}
//作成した2次元配列をマイカレンダーにセットする
		c1.setData(data);
		
		return c1;
	}

}
