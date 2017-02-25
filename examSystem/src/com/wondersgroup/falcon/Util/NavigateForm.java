package com.wondersgroup.falcon.Util;



/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class NavigateForm
    {
  private int pagesize;
  private int currpage;
  private int total;
  private int pagenum;
  public int getPagesize() {
    return pagesize;
  }

  public int getCurrpage() {
    return currpage;
  }

  public int getTotal() {
    return total;
  }

  public int getPagenum() {
    return pagenum;
  }

  public void setPagesize(int pagesize) {
    this.pagesize = pagesize;
  }

  public void setCurrpage(int currpage) {
    this.currpage = currpage;
  }

  public void setTotal(int total) {
    this.total = total;
  }

  public void setPagenum(int pagenum) {
    this.pagenum = pagenum;
  }
}
