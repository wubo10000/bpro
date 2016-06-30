<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<style type="text/css">
table {
	border-collapse: collapse;
	border: none;
}

td {
	border: solid #000 1px;
}

span {
	font-weight: bold;
}
</style>
</head>
<body>
	<span>1.报名</span>
	<table>
		<tr>
			<td>地址：</td>
			<td>http://localhost:8080/bpro/fUser/tsRegister.do</td>
		</tr>
		<tr>
			<td>参数：</td>
			<td>昵称 nickname;密码 pwd;真实姓名 realname;身份证 cardcode;年龄age;性别
				sex;手机 phone 邮箱 email;毕业学校 shcool;工作年限 workage</td>
		</tr>
		<tr>
			<td>test:</td>
			<td><br />
				<form action="../fUser/tsRegister.do">
					code：<input name="usercode" /><br /> nickname：<input
						name="nickname" /><br /> pwd：<input name="pwd" /><br />
					realname：<input name="realname" /><br /> <input type="submit" /><br />
				</form>
			</td>
		</tr>
	</table>
	<br />
	<span>2.文章类型</span>
	<table>
		<tr>
			<td>地址：</td>
			<td>http://localhost:8080/bpro/fLearntype/getGrid.do</td>
		</tr>
		<tr>
			<td>参数：</td>
			<td>起始 page;偏移 limit</td>
		</tr>
		<tr>
			<td>test:</td>
			<td><br />
				<form action="../fLearntype/getGrid.do" method="post">
					page：<input name="page" /><br /> limit：<input name="limit" /><br />
					<input type="submit" /><br />
				</form></td>
		</tr>
	</table>
	<br/>
	<span>3.文章列表</span>
	<table>
		<tr>
			<td>地址：</td>
			<td>http://localhost:8080/bpro/fLearndata/getGrid.do</td>
		</tr>
		<tr>
			<td>参数：</td>
			<td>起始 page;偏移 limit;类型 ltype</td>
		</tr>
		<tr>
			<td>test:</td>
			<td><br />
				<form action="../fLearndata/getGrid.do" method="post">
					page：<input name="page" /><br /> limit：<input name="limit" /><br />
					ltype：<input name="ltype" /><br /> <input type="submit" /><br />
				</form></td>
		</tr>
	</table>
	<br/>
	<span>3.文章详情</span>
	<table>
		<tr>
			<td>地址：</td>
			<td>http://localhost:8080/bpro/fLearndata/getGrid.do</td>
		</tr>
		<tr>
			<td>参数：</td>
			<td>文章id:id</td>
		</tr>
		<tr>
			<td>test:</td>
			<td><br />
				<form action="../fLearndata/getGrid.do" method="post">
					id：<input name="id" /><br /> 
					<input type="submit" /><br />
				</form></td>
		</tr>
	</table>
	<br/>
	<span>5.文章发布</span>
	<table>
		<tr>
			<td>地址：</td>
			<td>http://localhost:8080/bpro/fLearndata/tsAddLearn.do</td>
		</tr>
		<tr>
			<td>参数：</td>
			<td>标题title;简介summary;类型 ltype;文件路径filepath;</td>
		</tr>
		<tr>
			<td>test:</td>
			<td><br />
				<form action="../fLearndata/tsAddLearn.do" method="post" enctype="multipart/form-data">
					title：<input name="title" /><br />
					ltype：<input name="ltype" /><br />
					summary：<input name="summary" /><br />
					file：<input name="file" type="file" /><br />
					<input type="submit" /><br />
				</form></td>
		</tr>
	</table>

</body>
</html>