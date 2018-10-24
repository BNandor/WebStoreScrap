import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class GetCurrentDate {
	
		   public static String getdate() {
		       //getting current date and time using Date class
		       DateFormat df = new SimpleDateFormat("yyMMddHHmmss");
		       
		       /*getting current date time using calendar class 
		        * An Alternative of above*/
		       Calendar calobj = Calendar.getInstance();
		      return df.format(calobj.getTime());
		    }

}
