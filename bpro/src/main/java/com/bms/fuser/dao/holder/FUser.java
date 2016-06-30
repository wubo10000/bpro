package com.bms.fuser.dao.holder;
import java.util.Date;
import com.bms.system.bean.BaseHolder;
import com.bms.system.inteface.Constant;
import com.bms.system.util.DateUtil;
public class FUser extends BaseHolder
{
 private static final long serialVersionUID = 9018705241629904094L;
private String id;
private String usercode;
private String nickname;
private String pwd;
private String realname;
private String cardcode;
private String shcool;
private String age;
private String workage;
private Date createtime;
private String createtimeStr;
private Date startCreatetime;
private Date endCreatetime;
private String startCreatetimeStr;
private String endCreatetimeStr;
private String phone;
private String email;

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

public void setPhone( String phone )
{
this.phone = phone;
}

public String getPhone()
{
return this.phone;
}

public void setEmail( String email )
{
this.email = email;
}

public String getEmail()
{
return this.email;
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
buf.append(" createtimeStr: [ " + this.getCreatetimeStr() + " ] "); 
buf.append(" startCreatetimeStr: [ " + this.getStartCreatetimeStr() + " ] "); 
buf.append(" endCreatetimeStr: [ " + this.getEndCreatetimeStr() + " ] "); 
buf.append(" phone: [ " + this.getPhone() + " ] "); 
buf.append(" email: [ " + this.getEmail() + " ] "); 
return buf.toString();
 }

}
