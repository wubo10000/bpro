package com.bms.fuser.dao.holder;
import com.bms.system.bean.BaseHolder;
import com.bms.system.inteface.Constant;
import com.bms.system.util.DateUtil;
public class FUser extends BaseHolder
{
 private static final long serialVersionUID = 7480449047060914639L;
private String id;
private String usercode;
private String nickname;
private String pwd;
private String realname;
private String cardcode;
private String shcool;
private String age;
private String workage;
private String createtime;

public void setId( String id )
{
this.id = id;
}

public String getId()
{
return this.id;
}

public void setUsercode( String usercode )
{
this.usercode = usercode;
}

public String getUsercode()
{
return this.usercode;
}

public void setNickname( String nickname )
{
this.nickname = nickname;
}

public String getNickname()
{
return this.nickname;
}

public void setPwd( String pwd )
{
this.pwd = pwd;
}

public String getPwd()
{
return this.pwd;
}

public void setRealname( String realname )
{
this.realname = realname;
}

public String getRealname()
{
return this.realname;
}

public void setCardcode( String cardcode )
{
this.cardcode = cardcode;
}

public String getCardcode()
{
return this.cardcode;
}

public void setShcool( String shcool )
{
this.shcool = shcool;
}

public String getShcool()
{
return this.shcool;
}

public void setAge( String age )
{
this.age = age;
}

public String getAge()
{
return this.age;
}

public void setWorkage( String workage )
{
this.workage = workage;
}

public String getWorkage()
{
return this.workage;
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
if(obj instanceof FUser)
{
 FUser bean = (FUser) obj;
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
buf.append(" usercode: [ " + this.getUsercode() + " ] "); 
buf.append(" nickname: [ " + this.getNickname() + " ] "); 
buf.append(" pwd: [ " + this.getPwd() + " ] "); 
buf.append(" realname: [ " + this.getRealname() + " ] "); 
buf.append(" cardcode: [ " + this.getCardcode() + " ] "); 
buf.append(" shcool: [ " + this.getShcool() + " ] "); 
buf.append(" age: [ " + this.getAge() + " ] "); 
buf.append(" workage: [ " + this.getWorkage() + " ] "); 
buf.append(" createtime: [ " + this.getCreatetime() + " ] "); 
return buf.toString();
 }

}
