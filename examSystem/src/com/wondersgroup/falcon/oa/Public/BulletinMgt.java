package com.wondersgroup.falcon.oa.Public;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.wondersgroup.falcon.oa.Pubdata.AttachFile;
import com.wondersgroup.falcon.oa.database.DBOperation;
import com.wondersgroup.falcon.oa.util.DateUtil;
import com.wondersgroup.falcon.oa.util.StringUtil;
import com.wondersgroup.falcon.oa.util.SysLog;
import com.wondersgroup.falcon.persistence.HibernateSessionCallback;
import com.wondersgroup.falcon.persistence.HibernateUtil;

/**
 * <p>Title: �������</p>
 * <p>Description:  </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: wonders</p>
 * @author peter
 * @version 1.0
 */

public class BulletinMgt {
  private static Logger log = Logger.getLogger(BulletinMgt.class.getName());

  public BulletinMgt() {
  }
  /**
   * 
   *
   * <p>Description:���ҽ����ּ�������</p>
   * 
   * Created by [www] [Oct 24, 2009]
   * Midified by [�޸���] [�޸�ʱ��]
   *
   * @return
   */
 
  public int findNumbToday()throws Exception{
		return (Integer) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				 int i=0;
				  try{
//					  DBOperation db = new DBOperation();
					  String sql="select count(*) from T_GG gg where to_char(gg.ggrq, 'yyyymmdd')=to_char(sysdate,'yyyymmdd')";
					  Connection conn=session.connection();
					  PreparedStatement pst = conn.prepareStatement(sql);
					  ResultSet rs =pst.executeQuery();
					  if(rs.next()){
						  i=rs.getInt(1);
					  }
					  conn.close();
					  conn=null;
				  }catch(Exception e){
				      log.error(e);
				   }
				  return i; 

			}
		});


	}

  //��ѯ��¼
  //id,lmid,lmmc,ggbt,ggrq,bmid,bmmc,ryid,rymc
  public String[][] listBulletin(String lmid,String ggrq1,String ggrq2,String ggbt,String bmid){
    return listBulletin(lmid,ggrq1,ggrq2,ggbt,bmid,0);
  }

  public String[][] listBulletin(String lmid,String ggrq1,String ggrq2,String ggbt,String bmid,int Start){
    String ret[][]=null;
    String sql="";
    try{
        DBOperation db = new DBOperation();

        if(!lmid.equals(""))
          sql+=" AND GG.LMID = '" + lmid + "' ";

        if(!ggrq1.equals(""))
          sql+=" AND GGRQ>=TO_DATE('" + ggrq1 + " 00:00:00" + "','yyyy-MM-dd HH24:mi:ss') ";
        if(!ggrq2.equals(""))
          sql+=" AND GGRQ<=TO_DATE('" + ggrq2 + " 23:59:59" + "','yyyy-MM-dd HH24:mi:ss') ";

        if(!ggbt.equals(""))
          sql+=" AND GG.GGBT LIKE '%" + ggbt + "%' ";

        if(!bmid.equals(""))
          sql+=" AND GG.BMID = '" + bmid + "' ";

        sql="SELECT GG.GGID,GG.LMID,LM.LXMC,GG.GGBT,to_char(GG.GGRQ,'yyyy-MM-dd HH24:mi:ss') GGRQ,GG.BMID,BM.name,GG.RYID,RY.REALNAME,RY.USERNAME" +
                   " FROM T_GG GG,T_GGLM LM,GROUPS BM,USERS RY WHERE GG.LMID=LM.LXID(+) AND GG.BMID=BM.group_id(+) " +
                   " AND GG.RYID=RY.USERNAME(+) " + sql + " ORDER BY GGRQ DESC";

        List ls = db.Query_new(sql,Start);
        //System.out.println("sql���==="+sql);
        //id,lmid,lmmc,ggbt,ggrq,bmid,bmmc,ryid,rymc,ggnr
        if(ls!=null){
          if (ls.size()!= 0) {
            ret = new String[ls.size()][10];
            for (int i = 0; i < ls.size(); i++) {
              Object[] gg = (Object[]) ls.get(i);
              ret[i][0] = StringUtil.trim(gg[0]);
              ret[i][1] = StringUtil.trim(gg[1]);
              ret[i][2] = StringUtil.trim(gg[2]);
              ret[i][3] = StringUtil.trim(gg[3]);
              ret[i][4] = StringUtil.trim(gg[4]);
             // System.out.println("ʱ��==="+gg[4]);
              ret[i][5] = StringUtil.trim(gg[5]);
              ret[i][6] = StringUtil.trim(gg[6]);
              ret[i][7] = StringUtil.trim(gg[7]);
              ret[i][8] = StringUtil.trim(gg[8]);
              ret[i][9] = StringUtil.trim(gg[9]);
            }
          }
          ls.clear();
        }

    }
    catch(Exception e){
      log.error(e);
    }
    return ret;

  }

  //���
  public String addBulletin(String lmid,String ggbt,String bmid,String ryid,String ggnr){
    String ret="0";
    try{
      DBOperation db = new DBOperation();

       //����Ϣ
      String id=db.getSequenceID("SEQ_GGID");

      String sql = "INSERT INTO T_GG (GGID,LMID,GGBT,BMID,RYID,GGRQ,GGNR) VALUES " +
                   " ('" + id + "','" + lmid + "','" + ggbt + "','" + bmid + "','" + ryid + "'," +
                   " TO_DATE('" + DateUtil.getCurrentDateTime("yyyy-MM-dd HH:mm:ss") + "','yyyy-MM-dd HH24:mi:ss')," +
                   "'" + ggnr + "')";

      db.executeUpdate(sql);

      //��Ӹ���
      //����
      AttachFile af=new AttachFile();
      af.attachList(id,ryid,"T_GG");


      ret=id;
      SysLog.Write(ryid,"��ӹ���","1");
     }catch(Exception e){
       log.error(e);
       SysLog.Write(ryid,"��ӹ���","0");
     }
     return ret;
  }

  //�޸�
  public String updateBulletin(String ryid,String ggid,String lmid,String ggbt,String ggnr){
    String ret="0";
    try{
      DBOperation db = new DBOperation();

       //����Ϣ
       String sql = "UPDATE T_GG SET LMID='" + lmid + "',GGBT='" + ggbt + "'," +
           " GGRQ=TO_DATE('" + DateUtil.getCurrentDateTime("yyyy-MM-dd HH:mm:ss") + "','yyyy-MM-dd HH24:mi:ss')," +
           " GGNR='" + ggnr + "' WHERE GGID='" + ggid + "'";
      db.executeUpdate(sql);

      //����


      ret=ggid;
      SysLog.Write(ryid,"�޸Ĺ���","1");
     }catch(Exception e){
       log.error(e);
       SysLog.Write(ryid,"�޸Ĺ���","0");
     }
     return ret;
  }

  //ɾ��
  public String delBulletin(String ryid,String id,String realpath){
    String ret="0";
    try{
      DBOperation db = new DBOperation();

      String sql="DELETE FROM T_GG WHERE GGID='" + id + "'";
      db.executeUpdate(sql);

      //����
      AttachFile af=new AttachFile();
      af.DelAttachedFile(realpath,id,"T_GG");

      ret = id;
      SysLog.Write(ryid,"ɾ���","1");
    }
    catch(Exception e){
      log.error(e);
      SysLog.Write(ryid,"ɾ���","0");
    }
    return ret;
  }


  //lmid,lmmc,ggbt,bmid,bmmc,ryid,rymc,ggrq,ggnr
  public String[] getBulletin(String id) {
    String[] ret=new String[]{"","","","","","","","",""};
    try {
      DBOperation db=new DBOperation();
      String sql ="SELECT GG.LMID,LM.LXMC,GG.GGBT,GG.BMID,BM.BMMC,GG.RYID,RY.REALNAME,to_char(GG.GGRQ,'yyyy-MM-dd HH24:mi:ss') GGRQ,GG.GGNR FROM T_GG GG,T_GGLM LM,USERS RY,T_BM BM WHERE " +
                 " GG.LMID=LM.LXID(+) AND GG.RYID=RY.USERNAME(+) AND GG.BMID=BM.BMID(+) AND GG.GGID='" + id + "'";
        List ls = db.Query(sql);

        if(ls!=null){
          if (ls.size()!= 0) {
            ret = new String[9];
            Object[] l = (Object[]) ls.get(0);
            ret[0] = StringUtil.trim(l[0]);
            ret[1] = StringUtil.trim(l[1]);
            ret[2] = StringUtil.trim(l[2]);
            ret[3] = StringUtil.trim(l[3]);
            ret[4] = StringUtil.trim(l[4]);
            ret[5] = StringUtil.trim(l[5]);
            ret[6] = StringUtil.trim(l[6]);
            ret[7] = StringUtil.trim(l[7],"yyyy-MM-dd HH:mm");
            ret[8] = StringUtil.trim(l[8],"\r\n","");
          }
          ls.clear();
        }


    }catch (Exception e) {
      log.error(e);
    }
    return ret;
  }

  //fjxh,fjmc,fjdz
  public String[][] getBulletinAttach(String id) {
    String[][] ret=null;
    try{
      AttachFile af=new AttachFile();
      List fjlst=af.FJList(id,"T_GG");

      if(fjlst!=null){
        ret = new String[fjlst.size()][4];
        for (int i = 0; i < fjlst.size(); i++) {
          Object[] ls = (Object[]) fjlst.get(i);
          ret[i][0] = StringUtil.trim(ls[0]);
          ret[i][1] = StringUtil.trim(ls[1]);
          ret[i][2] = StringUtil.trim(ls[2]);
          ret[i][3] = StringUtil.trim(ls[3]);
        }
        fjlst.clear();
      }

    }catch(Exception e){
      log.error(e);
    }

    return ret;
  }

}
