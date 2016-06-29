package com.bms.flearndata.dao.holder;
import com.bms.system.bean.BaseHolder;
import com.bms.system.inteface.Constant;
import com.bms.system.util.DateUtil;
public class FLearndata extends BaseHolder
{
 private static final long serialVersionUID = 6635445758655267917L;
private String id;
private String title;
private String summary;
private String filepath;
private String createtime;

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

public void setCreatetime( String createtime )
{
this.createtime = createtime;
}

public String getCreatetime()
{
return this.createtime;
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
return buf.toString();
 }

}
