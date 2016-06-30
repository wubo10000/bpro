package com.bms.flearndata.dao.holder;
import java.util.Date;
import com.bms.system.bean.BaseHolder;
import com.bms.system.inteface.Constant;
import com.bms.system.util.DateUtil;
public class FLearndata extends BaseHolder
{
 private static final long serialVersionUID = -4082976131010762709L;
private String id;
private String title;
private String summary;
private String filepath;
private Date createtime;
private String createtimeStr;
private Date startCreatetime;
private Date endCreatetime;
private String startCreatetimeStr;
private String endCreatetimeStr;
private String ltype;

public void setId( String id )
{
this.id = id;
}

public String getId()
{
return this.id;
}

public void setTitle( String title )
{
this.title = title;
}

public String getTitle()
{
return this.title;
}

public void setSummary( String summary )
{
this.summary = summary;
}

public String getSummary()
{
return this.summary;
}

public void setFilepath( String filepath )
{
this.filepath = filepath;
}

public String getFilepath()
{
return this.filepath;
}

public void setCreatetime( Date createtime )
{
this.createtime = createtime;
this.createtimeStr = DateUtil.dateToStr(createtime,Constant.DATE_FORMAT);
}

public Date getCreatetime()
{
return this.createtime;
}

public void setCreatetimeStr( String createtimeStr )
{
this.createtimeStr = createtimeStr;
}

public String getCreatetimeStr()
{
return this.createtimeStr;
}

public void setStartCreatetime( Date startCreatetime )
{
this.startCreatetime = startCreatetime;
this.startCreatetimeStr = DateUtil.dateToStr(startCreatetime,Constant.DATE_FORMAT);
}

public Date getStartCreatetime()
{
return this.startCreatetime;
}

public void setEndCreatetime( Date endCreatetime )
{
this.endCreatetime = endCreatetime;
this.endCreatetimeStr = DateUtil.dateToStr(endCreatetime,Constant.DATE_FORMAT);
}

public Date getEndCreatetime()
{
return this.endCreatetime;
}

public void setStartCreatetimeStr( String startCreatetimeStr )
{
this.startCreatetimeStr = startCreatetimeStr;
}

public String getStartCreatetimeStr()
{
return this.startCreatetimeStr;
}

public void setEndCreatetimeStr( String endCreatetimeStr )
{
this.endCreatetimeStr = endCreatetimeStr;
}

public String getEndCreatetimeStr()
{
return this.endCreatetimeStr;
}

public void setLtype( String ltype )
{
this.ltype = ltype;
}

public String getLtype()
{
return this.ltype;
}


@Override
public int hashCode()
{
 return this.getId().hashCode();
}
@Override
public boolean equals(Object obj)
{
if(obj instanceof FLearndata)
{
 FLearndata bean = (FLearndata) obj;
if(bean.getId().equals(this.getId()))
{
   return true;
}
 else
 {
     return false;
 }
}
else
 {
  return false;
  }
 }
@Override
public String toString()
{
StringBuffer buf =new StringBuffer();
buf.append(" id: [ " + this.getId() + " ] "); 
buf.append(" title: [ " + this.getTitle() + " ] "); 
buf.append(" summary: [ " + this.getSummary() + " ] "); 
buf.append(" filepath: [ " + this.getFilepath() + " ] "); 
buf.append(" createtime: [ " + this.getCreatetime() + " ] "); 
buf.append(" createtimeStr: [ " + this.getCreatetimeStr() + " ] "); 
buf.append(" startCreatetimeStr: [ " + this.getStartCreatetimeStr() + " ] "); 
buf.append(" endCreatetimeStr: [ " + this.getEndCreatetimeStr() + " ] "); 
buf.append(" ltype: [ " + this.getLtype() + " ] "); 
return buf.toString();
 }

}
