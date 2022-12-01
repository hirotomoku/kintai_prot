package basic;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateFormat {
	public LocalDateFormat(LocalDateTime ldt) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM月dd日（E）");
		DateTimeFormatter week = DateTimeFormatter.ofPattern("E");
		String str2 = ldt.format(dtf);
		String str3 = ldt.format(week);
		System.out.println(str2);
		System.out.println(str3);
		this.change(str3);
	}

	public void change(String str3) {
		if (str3 == "水") {
			System.out.println("水曜日です");
		} else {
			System.out.println("水曜日ではありません");
		}
	}
}
