/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webcrawl;

//import com.mysql.jdbc.Connection;
import java.sql.Statement;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import static java.nio.file.Files.lines;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import static jdk.nashorn.internal.objects.NativeString.indexOf;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
 
/**
 *
 * @author bnand
 */
public class webcrawl{

    /**
     * @param args the command line arguments
     */
   public static  Connection connection=null;
    public static String [] topics={"placi_video","laptopuri","tablete","telefoane-mobile"};
    public static  String domain="https://www.emag.ro";
    
    
    public static String ip="85.121.123.169";
     public static String port="3306";
    public static String dbname="Projekt";
     public static String tablename="Products_"+GettingCurrentDate.getdate();
    
   public static  String username = "acer";
   public static  String password = "isacergettingbetter";
    public static Statement stmt = null;
   public static Credentials get_info=null;
    public static boolean got_info=false;
     public static  BufferedReader br =null;
  public static   String graphical_module="n";
  
  private static final int sleeptime=1000;
    public static void main(String[] args) throws IOException, SQLException {
        
System.out.println("Loading driver...");
//Loading Driver 
        try {
          Class.forName("com.mysql.jdbc.Driver");
           System.out.println("Driver loaded!");
            } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Cannot find the driver in the classpath!", e);
        }
///Loading Driver
//Read Line
br=new BufferedReader(new InputStreamReader(System.in));
System.out.println("Initialise graphical module ?(y,n)");
String graphical_module=br.readLine();

if(graphical_module.equals("y")){
//Graphical module , if needed
         get_info=new Credentials();
        get_info.setVisible(true);
        while(!got_info){System.out.println("waiting for input");}
//End of Graphical module
}



         String mysqlurl = "jdbc:mysql://"+ip+":"+port+"/"+dbname;
        String newtable ="CREATE TABLE `"+tablename+"` (ID int PRIMARY KEY AUTO_INCREMENT, Name text, Price int(11), Extra text, Currency text,Link text);";

       

        try {
             System.out.println("Connecting database...");
    if(graphical_module.equals("y"))    get_info.append_text("Connecting to~"+dbname);
        
             connection = (Connection) DriverManager.getConnection(mysqlurl, username, password);
            } catch (SQLException e) {
        throw new IllegalStateException("Cannot connect the database!", e);
        } 
        
        System.out.println("Database connected!");
      if(graphical_module.equals("y"))  get_info.append_text("Succesfully connected  to~"+dbname);
        
        
        try{
        System.out.println("Creating  Table~"+tablename);
    if(graphical_module.equals("y"))    get_info.append_text("Creating  Table~"+tablename);
        
        stmt = connection.createStatement();
        stmt.executeUpdate(newtable);
        }catch(SQLException e){
             throw new IllegalStateException("Could not create new table~"+tablename, e);
    }
        ArrayList<String>  log_name=new ArrayList();
        String url;
       
               
        
       for(int k=0;k<topics.length;k++) {
           
        url=domain+"/"+topics[k]+"/c";   
       
         write(url,log_name,connection);
        int i=2;
     while(write(domain+"/"+topics[k]+"/p"+String.valueOf(i)+"/c",log_name,connection)==false){      
        i++;}
             try {
                   Thread.sleep(2*sleeptime);
             } catch (InterruptedException ex) {
                  Logger.getLogger(webcrawl.class.getName()).log(Level.SEVERE, null, ex);
             }
       }
       connection.close();
      
      
       
       System.out.println(" DATABASE MOCKED SUCCESFULLY");
if(graphical_module.equals("y"))get_info.append_text("DATABASE MOCKED SUCCESFULLY");
    }
static  boolean write(String url_string,ArrayList<String>  log_name,Connection con) throws IOException, SQLException{
       
       
       
       
       
     
    System.out.println(url_string);
if(graphical_module.equals("y"))    get_info.append_text("processing ~"+url_string);
   
URL url;
    InputStream is = null;
   
    BufferedReader br=null;
    
    String line;
    ArrayList<String>  lines= new ArrayList<String>( );
      
   boolean end=false;
      
   
    
    try {
       
        url = new URL(url_string);
         URLConnection hc = url.openConnection();
          System.out.println("opened url connection");
          hc.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");

        is = url.openStream();  // throws an IOException
          System.out.println("created input stream");
        br = new BufferedReader(new InputStreamReader(hc.getInputStream()));
        System.out.println("created buffered reader");
       
        
        while ((line = br.readLine()) != null){
         
            lines.add(line);
            if(line.contains("javascript:void(0)"+'"'+">Pagina urmatoare")){
                System.out.println(line);
                
            end=true;
            }
           // System.out.println(line);
            
        }
    } catch (MalformedURLException mue) {
         mue.printStackTrace();
         System.out.println("could not connect dto domain");
         
    } catch (IOException ioe) {
         ioe.printStackTrace();
         System.out.println("could not read anything");
    } finally {
        try {
          
            if (is != null) is.close();
            else {end =true;}
        } catch (IOException ioe) {
            end =true;
        }
    }
 
    try {
           Thread.sleep(sleeptime);
       } catch (InterruptedException ex) {
           Logger.getLogger(webcrawl.class.getName()).log(Level.SEVERE, null, ex);
       }
    
    parse(lines,log_name,con);
    return end;
    
    
   // Files.write(file, lines, Charset.forName("UTF-8"), StandardOpenOption.APPEND);
    
    }
static boolean   parse( ArrayList<String>  list,ArrayList<String> log_name,Connection con ) throws IOException, SQLException{
   

      
      String Name="";
      String color="";
      String currency="";
      String Link="";
      System.out.println("Starting parseing process");
for(int i=0;i<list.size();i++){

        String Price="";
          
        String  line=list.get(i);
       
    if(line.contains("card-body product-title-zone")==true){
        while(line.contains("data-zone="+'"'+"title"+'"')==false&&i+1<list.size()){
            i++;line=list.get(i);
       } 
       
        String t_name="";
        for(int j=line.indexOf("href")+6;j<line.indexOf('"'+" data-ref");j++){
              if(line.charAt(j)!='='&&line.charAt(j)!='"'&&line.charAt(j)!=','&& line.charAt(j)!='!'&& line.charAt(j)!='>'&& line.charAt(j)!=39 && line.charAt(j)!=96)
        Link+=line.charAt(j);
        }
        
        for(int j=line.indexOf("data-zone="+'"'+"title"+'"')+18;j<line.length()-4;j++){
              if(line.charAt(j)!='='&&line.charAt(j)!='"'&&line.charAt(j)!=','&& line.charAt(j)!='!'&& line.charAt(j)!='>'&& line.charAt(j)!=39 && line.charAt(j)!=96)
        t_name+=line.charAt(j);
                
        }
        
        Scanner iss =new Scanner(t_name);String token;
        while(iss.hasNext()){
            token=iss.next();
            
        if(contains(token)){
            color=color+"_"+token;
        }
        }


        Name=t_name;
         
       
    }

 if(line.contains("product-new-price")==true){
        

       for(int j=line.indexOf("product-new-price")+19;j<line.indexOf("<sup");j++){
           if(line.charAt(j)!=46 && line.charAt(j)<='9'&& line.charAt(j)>='0')
       Price+=line.charAt(j);}
       
        for(int j=line.indexOf("<span",line.indexOf(Price))+6;j<line.indexOf("</span");j++){
      currency+=line.charAt(j);}

       if(Price.equals(" ")||Price.equals(""))Price="0";
         System.out.println(line);System.out.println("Name"+Name);System.out.println(Link); System.out.println(color);System.out.println(Price);System.out.println(currency);
      try{  
       String sql="INSERT INTO `"+dbname+"`.`"+tablename+"` (`ID`, `Name`, `Price`, `Extra`, `Currency`,`Link`) VALUES (NULL, '"+Name+"', '"+Price+"', '"+color+"','"+currency+"','"+Link+"')\n";
        stmt = con.createStatement();
       System.out.println(sql+"\n");
        
       stmt.executeUpdate(sql);
        } catch (SQLException e) {
        throw new IllegalStateException("Could not insert record~ ", e);
        } 
      // log_name.add(Name);
      
       //ArrayList write_string =new ArrayList();
       //write_string.add(sql);
       //Files.write(file,write_string, Charset.forName("UTF-8"),StandardOpenOption.APPEND);
        Name="";color="";currency="";Link="";
        
    }




}

return true;
}   
   
 
   
   
static String[] colors= new String []{"pink", "Red", "orange","yellow", "brown", "green","cyan", "blue", "indigo", "purple" ,"violet" ,"magenta", "white" ,"gray", "black","silver","dark","bright","gb","ddr","bit","ghz","gt","geforce","nvidia","radeon","0","gaming","ti","mb","pny","apple","lenovo"
,"asus","thinkpad","macbook","ipad","iphone","hdmi","usb","rezolutie","resolution","touch","matt","metal","inch","hdd","ssd","haswell","xenon","intel","celeron","i7","i5","i3","windows","linux","dos","wifi","bluetooth","dvd","cd","tb","mini","kingston","samsung","hama","sandisk"};
static boolean contains(String s ){   
for(int i=0;i<colors.length;i++)if((s.toLowerCase()).contains(colors[i]))return true;
return false;
}
}
