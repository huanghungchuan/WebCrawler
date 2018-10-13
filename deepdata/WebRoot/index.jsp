<%@ page language="java" import="java.util.*" pageEncoding="Utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>功能列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="<%=path%>/scripts/jquery-1.9.1.js" ></script>
  </head>
  <script type="text/javascript"> 
    	
  </script>
  <body>
<!-- 
	<form method="post"  name="form1" id="form1" action="gitCheckInfo/bornResultWord"  >
		级别：<input type="text" name="level" id="dlevel" value="1"   size='10' />
		工程数：<input type="text" name="pronum" id="pronum" value="0"   size='10' />
		日期：<input type="text" name="datetimeStart" id="datetimeStart" value="2018-07-10"  size='10' />
			-<input type="text" name="datetimeEnd" id="datetimeEnd"   value="2018-08-06"  size='10' />
		<input type="submit" name="button1" id="button1" value="生成结果数据报告" />
	</form>
	<form method="post"  name="form2" id="form2" action="gitCheckInfo/getGithub"  >
		<input type="submit" name="button2" id="button2" value="获取昨天改动的github项目1" />
	</form>
	
	<form method="post"  name="form3" id="form3" action="gitCheckInfo/getDeepUrlByGit"  >
		<input type="submit" name="button3" id="button3" value="获取github项目的检测链接2" />
	</form>
	
	<form method="post"  name="form4" id="form4" action="gitCheckInfo/getDefLink"  >
		<input type="submit" name="button4" id="button4" value="获取检测缺陷链接3" />
	</form>
	
	<form method="post"  name="form5" id="form5" action="gitCheckInfo/getDefInfoByLink"  >
		<input type="submit" name="button5" id="button5" value="获取缺陷信息4" />
	</form>
	
	 <form method="post"  name="form6" id="form6" action="cdataTest/getGithub"  >
		<input type="submit" name="button6" id="button6" value="获取C项目" />
	</form>
	
	
	
	<form method="post"  name="form7" id="form7" action="siva/siva"  >
		<input type="submit" name="button7" id="button7" value="siva" />
	</form>
	
	<form method="post"  name="form8" id="form8" action="siva/sivaList"  >
		<input type="submit" name="button8" id="button8" value="sivaList" />
	</form>
		
	
		 -->
	<!-- <form method="post"   action="mvnController/getModUrl"  >
		<input type="submit" value="1.获取左菜单模块链接" />
	</form>
	
	<form method="post"  action="mvnController/getModChildUrl"  >
		<input type="submit" value="2.获取模块中子资源链接" />
	</form>	
	
	<form method="post"   action="mvnController/getModChildVersionUrl"  >
		<input type="submit"  value="3.获取子资源的各个版本链接" />
	</form>	
	
	<form method="post"   action="mvnController/getModChildText"  >
		<input type="submit"  value="4.获取子资源的各个版本的下载文本" />
	</form>	
	
	<form method="post"   action="mvnController/excutePomXml"  >
		<input type="submit"  value="5.拼接pom并执行" />
	</form>	 -->
	
	<!-- <form method="post"   action="screenResumes/getResumesInfo"  >
		<input type="submit"  value="智联招聘" />
	</form> -->
	<!-- <form method="post"   action="searchCode/getGithub"  >
		<input type="submit"  value="github链接" />
	</form> -->
	<form method="post"   action="gitCheckInfo/getGithub"  >
		<input type="submit"  value="抓取github链接" />
	</form>
	<form method="post"   action="searchCode/getGithub"  >
		<input type="submit"  value="抓取分支数据" />
	</form>
	<form method="post"   action="searchCode/exportExecl"  >
		<input type="submit"  value="导出数据" />
	</form>
	
	<form method="post"   action="screenResumes/getResumesInfo"  >
		<input type="submit"  value="简历遍历" />
	</form>
	
  </body>
</html>
