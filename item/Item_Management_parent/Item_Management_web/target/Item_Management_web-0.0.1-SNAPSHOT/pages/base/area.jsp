<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<title>区域设置</title>
		<!-- 导入jquery核心类库 -->
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
		<!--导入jquery的上传文件类库  -->
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/upload/jquery.ocupload-1.1.2.js"></script>
		<!-- 导入easyui类库 -->
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui/themes/icon.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui/ext/portal.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/default.css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/ext/jquery.portal.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/ext/jquery.cookie.js"></script>
		<script src="${pageContext.request.contextPath}/js/easyui/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
		<script type="text/javascript">
			function doAdd(){
				$('#addWindow').window("open");
			}
			
			function doView(){
				//先要回显数据
				var rows = $('#grid').datagrid('getSelections');  
				if(rows.length != 1){
					$.messager.alert('系统消息','同时只能修改一条选中的数据','info');
				}
				else{
					$('#addWindow').form('load',rows[0]);
					$('#addWindow').window("open");
					
				}
				
				
				
			}
			
			function doDelete(){
				alert("删除...");
			}
			
			function doImport(){
				 $("#button-import").upload({
				        action:'${pageContext.request.contextPath}/area_importXls.action',
				        name:'upload',
				        onComplete: function (data, self, element) { 
					        if(data=="0"){
					                $.messager.alert("提示信息","数据导入成功!","info");
					            }
					        else if (data == "1"){
					                $.messager.alert("提示信息","数据导入失败!","info");
					        }
					        $('#grid').datagrid("reload"); 
				        }
			})}
					
			
			//工具栏
			var toolbar = [ {
				id : 'button-edit',	
				text : '修改',
				iconCls : 'icon-edit',
				handler : doView
			}, {
				id : 'button-add',
				text : '增加',
				iconCls : 'icon-add',
				handler : doAdd
			}, {
				id : 'button-delete',
				text : '删除',
				iconCls : 'icon-cancel',
				handler : doDelete
			}, {
				id : 'button-import',
				text : '导入',
				iconCls : 'icon-redo',
				handler: doImport
			}];
			// 定义列
			var columns = [ [ {
				field : 'id',
				checkbox : true,
			},{
				field : 'province',
				title : '省',
				width : 120,
				align : 'center'
			}, {
				field : 'city',
				title : '市',
				width : 120,
				align : 'center'
			}, {
				field : 'district',
				title : '区',
				width : 120,
				align : 'center'
			}, {
				field : 'postcode',
				title : '邮编',
				width : 120,
				align : 'center'
			}, {
				field : 'shortcode',
				title : '简码',
				width : 120,
				align : 'center'
			}, {
				field : 'citycode',
				title : '城市编码',
				width : 200,
				align : 'center'
			} ] ];
			
			$(function(){
				// 先将body隐藏，再显示，不会出现页面刷新效果
				$("body").css({visibility:"visible"});
				
				// 区域管理数据表格
				$('#grid').datagrid( {
					iconCls : 'icon-forward',
					fit : true,
					border : false,
					rownumbers : true,
					striped : true,
					pageList: [15,30,50,100],
					pagination : true,
					toolbar : toolbar,
					url : "${pageContext.request.contextPath}/area_pageQuery.action",
					idField : 'id',
					columns : columns,
					onDblClickRow : doDblClickRow
				});
				

				
				
				
				// 添加、修改区域窗口
				$('#addWindow').window({
			        title: '添加修改区域',
			        width: 400,
			        modal: true,
			        shadow: true,
			        closed: true,
			        height: 400,
			        resizable:false
			    });
				
			});
		
			function doDblClickRow(){
				alert("双击表格数据...");
			}
		</script>
	</head>

	<body class="easyui-layout" style="visibility:hidden;">
		<div region="center" border="false">
			<table id="grid"></table>
		</div>
		<div class="easyui-window" title="区域添加修改" id="addWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
			<div region="north" style="height:31px;overflow:hidden;" split="false" border="false">
				<div class="datagrid-toolbar">
					<a id="save" icon="icon-save" href="#" class="easyui-linkbutton" plain="true">保存</a>
					<script type="text/javascript">
						$("#save").click(function(){
							var r = $('#newArea').form('validate');
							if(r){
								$("#newArea").submit();
							}
						})
					</script>
				</div>
			</div>

			<div region="center" style="overflow:auto;padding:5px;" border="false">
				<form id="newArea" method="post" action="${pageContext.request.contextPath}/area_save.action">
					<table class="table-edit" width="80%" align="center">
						<tr class="title">
							<td colspan="2">区域信息
								<!--提供隐藏域 装载id -->
								<input type="hidden" name="id" />
							</td>
						</tr>
						<tr>
							<td>省</td>
							<td>
								<input type="text" name="province" class="easyui-validatebox" required="true" />
							</td>
						</tr>
						<tr>
							<td>市</td>
							<td>
								<input type="text" name="city" class="easyui-validatebox" required="true" />
							</td>
						</tr>
						<tr>
							<td>区</td>
							<td>
								<input type="text" name="district" class="easyui-validatebox" required="true" />
							</td>
						</tr>
						<tr>
							<td>邮编</td>
							<td>
								<input type="text" name="postcode" class="easyui-validatebox" required="true" />
							</td>
						</tr>
						<tr>
							<td>简码</td>
							<td>
								<input type="text" name="shortcode" class="easyui-validatebox" required="true" />
							</td>
						</tr>
						<tr>
							<td>城市编码</td>
							<td>
								<input type="text" name="citycode" class="easyui-validatebox" required="true" />
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		
		<!-- 查询区域-->
		<div class="easyui-window" title="查询区域窗口" closed="true" id="searchWindow" collapsible="false" minimizable="false" maximizable="false" style="width: 400px; top:40px;left:200px">
			<div style="overflow:auto;padding:5px;" border="false">
				<form id="searchForm">
					<table class="table-edit" width="80%" align="center">
						<tr class="title">
							<td colspan="2">查询条件</td>
						</tr>
						<tr>
							<td>省份</td>
							<td>
								<input type="text" name="province" />
							</td>
						</tr>
						<tr>
							<td>城市</td>
							<td>
								<input type="text" name="city" />
							</td>
						</tr>
						<tr>
							<td>区（县）</td>
							<td>
								<input type="text" name="district" />
							</td>
						</tr>
						<tr>
							<td colspan="2"><a id="searchBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a> </td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</body>

</html>