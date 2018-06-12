package zhangbin.cumt.fore.action.base;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class OrderActionTest {

	public static void main(String[] args) {
		Date date = new Date();
		
		System.out.println(date);
		String format = "HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		
		String string = simpleDateFormat.format(date);
		System.out.println("11111111" +string);
		
		
		Calendar instance = Calendar.getInstance();
		Date time = instance.getTime();
		System.out.println(time);
		
		
		
	}
	
	
	
	
	
	
	
	
}
