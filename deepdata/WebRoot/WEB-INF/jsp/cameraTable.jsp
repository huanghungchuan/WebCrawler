<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
%>
<html>
<head>
	<meta charset="UTF-8">
	<title>Basic DataGrid - jQuery EasyUI Demo</title>
	<link rel="stylesheet" type="text/css" href="<%=path%>/scripts/jquery-easyui-1.5/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=path%>/scripts/jquery-easyui-1.5/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=path%>/scripts/jquery-easyui-1.5/demo/demo.css">
	<script type="text/javascript" src="<%=path%>/scripts/jquery-easyui-1.5/jquery.min.js"></script>
	<script type="text/javascript" src="<%=path%>/scripts/jquery-easyui-1.5/jquery.easyui.min.js"></script>
</head>
<body>
	<h2>CheckBox Selection on DataGrid</h2>
	<p>Click the checkbox on header to select or unselect all selections.</p>
	<div style="margin:20px 0;"></div>
	
	<table id="dg" class="easyui-datagrid" title="CheckBox Selection on DataGrid" style="width:700px;height:250px"
	        items="${cs}"
			data-options="rownumbers:true,singleSelect:true,url:'listCategory',method:'get'">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'id',width:80">id</th>
				<th data-options="field:'name',width:100">name</th>
				<th data-options="field:'SBCS',width:80,align:'right'">SBCS</th>
				<th data-options="field:'XZQY',width:80,align:'right'">XZQY</th>
				<th data-options="field:'JKDWLX',width:220">JKDWLX</th>
				<th data-options="field:'SBXH',width:60,align:'center'">SBXH</th>
			</tr>
		</thead>
	</table>
	<div style="margin:10px 0;">
		<span>Selection Mode: </span>
		<select onchange="$('#dg').datagrid({singleSelect:(this.value==0)})">
			<option value="0">Single Row</option>
			<option value="1">Multiple Rows</option>
		</select><br/>
		SelectOnCheck: <input type="checkbox" checked onchange="$('#dg').datagrid({selectOnCheck:$(this).is(':checked')})"><br/>
		CheckOnSelect: <input type="checkbox" checked onchange="$('#dg').datagrid({checkOnSelect:$(this).is(':checked')})">
	</div>
 
</body>
</html>