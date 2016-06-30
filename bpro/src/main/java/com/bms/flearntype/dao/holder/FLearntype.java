package com.bms.flearntype.dao.holder;
import com.bms.system.bean.BaseHolder;
import com.bms.system.inteface.Constant;
import com.bms.system.util.DateUtil;
public class FLearntype extends BaseHolder
{
 private static final long serialVersionUID = 3710568506617808583L;
private String id;
private String typename;

public void setId( String id )
{
this.id = id;
}

public String getId()
{
return this.id;
}

public void setTypename( String typename )
{
this.typename = typename;
}

public String getTypename()
{
return this.typename;
}


@Override
public int hashCode()
{
 return this.getId().hashCode();
}
@Override
public boolean equals(Object obj)
{
if(obj instanceof FLearntype)
{
 FLearntype bean = (FLearntype) obj;
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
buf.append(" typename: [ " + this.getTypename() + " ] "); 
return buf.toString();
 }

}
