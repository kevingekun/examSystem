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
							
							String pSql="select t.st_id as names from e_questions t where trunc(t.st_lrsj)=trunc(sysdate)";
							
							
							
							//out.println(pSql);

							rs=stmt.executeQuery(pSql);
							//conn.commit();
							int i=1;
							while(rs.next()){
								names=rs.getString("names");
								
							
									Statement stmt1 =	conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
									stmt1.setFetchSize(1000);
									//timu=names+"("+person[i]+")";
									System.out.println("--------------");
									String sql1=" insert into e_paperquestions values(pape_ques_sequ.nextval,'1182','"+names+"','0.00','"+i+"','0.00') ";
									System.out.println(sql1);
									i++;
									stmt1.executeQuery(sql1);
									stmt1.close();
                                    
								
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

