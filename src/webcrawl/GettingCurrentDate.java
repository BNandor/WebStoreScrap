/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webcrawl;

/**
 *
 * @author bnand
 */
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class GettingCurrentDate {
   public static String getdate() {
       //getting current date and time using Date class
       DateFormat df = new SimpleDateFormat("ddMMyyHHmmss");
       Date dateobj = new Date();
       System.out.println(df.format(dateobj));

       /*getting current date time using calendar class 
        * An Alternative of above*/
       Calendar calobj = Calendar.getInstance();
      return df.format(calobj.getTime());
    }
}