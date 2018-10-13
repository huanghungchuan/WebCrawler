(function() {
	//默认配制,不能通过传参修改
	var defaults = {
		importAction:'/excel/excelAction!importExcel.dhtml',
		downloadAction:'/excel/excelAction!downloadExcel.dhtml',
		form:'excel_form_auto',
		iframe:'excel_iframe_auto',
		file:'excel_file_auto'
	};
	//合并对象
	function extend() {
		var args = arguments
		if(arguments.length == 0) {
			return {};
		} else if(arguments.length == 1) {
			return arguments[0];
		} else {
			var base = arguments[0];
			for(var i=1;i<arguments.length;i++) {
				for(var n in arguments[i]) {
					base[n] = arguments[i][n];
				}
			}
			return base;
		}
	}
	//获取html内容
	function generateHTML() {
		var o = arguments[0];
		var html = '';
		html += '<form id="'+o.form+'" name="'+o.form+'" action="" method="post" target="'+o.iframe+'" enctype="multipart/form-data" style="display:inline">';
		html += ' <span>';
		html += '  <a style="z-index:11;cursor:pointer;" href="#" onclick="Excel.downloadExcel()">下载模版</a>';
		html += '  <a style="z-index:11;cursor:pointer;" href="#">导入Excel</a>';
		html += '  <span style="overflow:hidden;width:56px;height:18px;border:0px solid red;margin-left:-56px;z-index:12" >';
		html += '   <input type="file" name="model.file" id="'+o.file+'" style="opacity: 0; filter: alpha(opacity = 0);cursor:pointer;width:0px;border:none;z-index:12" onchange="Excel.importExcel()" />';
		html += '  </span>';
		html += ' </span>';
		//if(o.showTip) {
		//	html += ' <span style="color:red">提示：导入Excel会覆盖原数据2。</span>';
		//}
		html += ' <input type="hidden" name="model.sqid" value="'+o.sqid+'" />';
		html += ' <input type="hidden" name="model.ksjls" value="'+o.ksjls+'" />';
		html += ' <input type="hidden" name="model.xmlFile" value="'+o.xmlFile+'" />';
		html += ' <input type="hidden" name="model.templateName" value="'+o.templateName+'" />';
		html += '</form>';
		html += '<iframe id="'+o.iframe+'" name="'+o.iframe+'" frameborder="0" width="0px" height="0px" style="display:inline;width:0px;height:0px"></iframe>';
		return html;
	}
	var Excel = {
		'errorMess' : function(mess) {
			alert(mess);
		},
		'load' : function(o) {
			var ops = extend(Excel.defaults,o,defaults);
			var ed = document.getElementById(ops.id);
			ed.innerHTML = generateHTML(ops);
		},
		'downloadExcel' : function() {
			var fr = document.getElementById(Excel.defaults.form);
			fr.action = Excel.defaults.webapp + Excel.defaults.downloadAction;
			fr.submit();
		},
		'importExcel' : function() {
			var legal = ",xls,xlsx,";
			var file = document.getElementById(Excel.defaults.file);
			var fileName = file.value;
			var hz = fileName.substring(fileName.indexOf(".")+1);
			if(legal.indexOf(","+hz+",") == -1) {
				alert("请上传Excel文件。");
				return;
			}
			var fr = document.getElementById(Excel.defaults.form);
			fr.action = Excel.defaults.webapp + Excel.defaults.importAction + '?r=' + Math.random();
			fr.submit();
			fr.reset();
		},
		'defaults' : {
			id:'excelDiv',
			xmlFile:'spscxkzImp.xml',
			templateName:'spscxkzImp_HF',
			webapp:'/gjjspsc',
			sqid:'spsc',
			ksjls:'3',
			showTip:true
		}
	};
	
	window.Excel = Excel;
})();