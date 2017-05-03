package com.salk.crawler.web.pipeline;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.StringUtils;

import com.mysql.jdbc.PreparedStatement;

public class DesinProductsPipeline {

	
	private void add(){
		String url = "jdbc:mysql://121.41.112.25:3306/biyebao?characterEncoding=UTF-8&characterSetResults=UTF-8" ;

		//String url = "jdbc:mysql://localhost:3306/biyebao?characterEncoding=UTF-8&characterSetResults=UTF-8" ;    
	     String username = "root" ;   
	     String password = "rose4j_db" ;   		
	     Connection con=null;
	     java.sql.PreparedStatement stmt=null;
		try {
			 Class.forName("com.mysql.jdbc.Driver") ;
			  con =    DriverManager.getConnection(url , username , password ) ;   		
			  con.setAutoCommit(false);
			  String SQL="insert into goods (version,description,name,price,db_type,summary,weights,add_time,adder_name,lang_type,post_time,sale_num,source_type,update_time,updater_name,view_num,recommend) "
						+ "values(?,?,?,?,?,?,?,now(),'admin','PHP',?,?,?,now(),'admin',20,'0')";
		      stmt = con.prepareStatement(SQL);
			List<String> datas=readFileLine();
			int num=0;
			for(String data:datas){
				num++;
				String[] fields=data.split("@##@");
				String title=fields[0];
				String date =fields[1];
				String summary=fields[2];
				String content=fields[3];
				String dbType=fields[4];
				if(StringUtils.isEmpty(date) ||data.equals("null")){
					date="2014-05-16";
			}
				if(StringUtils.isEmpty(dbType)|| "null".equals(dbType)){
					dbType="MYSQL";
				}
				if(StringUtils.isEmpty(summary)|| "null".equals(summary)){
					summary="";
				}
				if(StringUtils.isEmpty(title)||"null".equals(title) || StringUtils.isEmpty(content)||"null".equals(content)){
					continue;
				}
				Pattern p=Pattern.compile("\\d+[：|】]([\\s\\S]*)");
				Matcher m=p.matcher(summary);
				if(m.find()){
					summary=m.group(1);
				}
				System.out.println(summary);
				BigDecimal price = new BigDecimal(150);
				byte[] t_utf8 = content.getBytes("UTF-8");
				 content= new String(t_utf8,"UTF-8");
				 byte[] s_utf8 = summary.getBytes("UTF-8");
				 summary= new String(s_utf8,"UTF-8");
				 String source = new String("企鹅".getBytes(),"UTF-8");
				 dbType=StringUtils.upperCase(dbType);
				//String sql="insert into goods (version,description,name,price,db_type,summary,weights,add_time,adder_name,lang_type,post_time,sale_num,source_type,update_time,updater_name,view_num,recommend) "
					//	+ "values("+1+",'"+content+"','"+title+"',"+price+",'"+dbType+"','"+summary+"',"+100+",now(),'admin','JSP','"+date+"',1,'"+source+"',now(),'admin',20,'0')";
				stmt.setInt(1, 1);
				stmt.setString(2, content);
				stmt.setString(3, title);
				stmt.setDouble(4, price.doubleValue());
				stmt.setString(5, dbType);
				stmt.setString(6, summary);
				stmt.setInt(7, 100);
				stmt.setString(8, date);
				stmt.setInt(9, 1);
				stmt.setString(10, source);
				stmt.addBatch();
				if(num%5==0 ||num==datas.size()){
					int[] nums=stmt.executeBatch();
					System.out.println("影响的行数====================="+nums.length);
					con.commit();
					stmt.clearBatch();
				}
			}
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}finally{
			if(stmt!=null){
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(con!=null){
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	private List<String> readFileLine(){
		List<String> datas=new ArrayList<String>();
		try {
			datas=FileUtils.readLines(new File("E:\\data\\biyebao\\php\\data_20150120.txt"),"UTF-8");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datas;
	}
	public static void main(String[] args) {
		DesinProductsPipeline desinProductsPipeline=new DesinProductsPipeline();
		desinProductsPipeline.add();
	}
}
