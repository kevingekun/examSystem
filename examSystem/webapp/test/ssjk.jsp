<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page 	import ="java.sql.*"%>
<%@ page 	import ="com.wondersgroup.falcon.persistence.*"%>


<% 

//java.util.Date d = new java.util.Date();


//long begintime = System.currentTimeMillis();

//System.out.println("begintime:"+begintime);

String id = request.getParameter("id");


String n = request.getParameter("n");
    //id="3003";
            String log="";
			
            String[] person = {"德","能","勤","绩","综合"};
            String names="";
            String timu="";
	         
          
            //HibernateUtil.closeSession();
			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
						String url="jdbc:oracle:thin:@172.16.31.22:1521:orcl";	
						String user="falcon";
    				    String password="falcon";  
						Connection conn = DriverManager.getConnection(url,user,password);						
						Statement stmt =	conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
						stmt.setFetchSize(1000);
						ResultSet rs = null; 
						
						try{
							
							String pSql="select um.name as names from user_minzhuceping um where um.name in ('陈婷','陈琴','胡慧英','黄洋','郎陈','王伟宏','王琪','张慧芬') order by um.id asc ";
							
							
							//out.println(pSql);

							rs=stmt.executeQuery(pSql);
							//conn.commit();												
							while(rs.next()){
								names=rs.getString("names");
								
								for(int i=0;i<5;i++){
									Statement stmt1 =	conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
									stmt1.setFetchSize(1000);
									timu=names+"("+person[i]+")";
									System.out.println("--------------");
									String sql1=" insert into e_questions values(question_sequence.nextval,'"+timu+"','好||较好||一般||差','A','','','1','1','1','1',sysdate,'admin',sysdate,'"+names+"','','','0','','','','1','admin','',sysdate) ";
									System.out.println(sql1);
									stmt1.executeQuery(sql1);
									stmt1.close();
								}
								
								
							}
										
							}catch (Exception e){out.print(e);}  
					
					finally{
							if(rs!=null){
							rs.close();	}	
							stmt.close();
							conn.close();  				
						}	
			
			//System.out.print(person[2]+"-------------")
%> 

