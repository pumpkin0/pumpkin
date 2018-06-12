<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<title>角色添加</title>
		<!-- 导入jquery核心类库 -->
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
		<!-- 导入easyui类库 -->
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui/themes/icon.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui/ext/portal.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/default.css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/ext/jquery.portal.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/ext/jquery.cookie.js"></script>
		<script src="${pageContext.request.contextPath}/js/easyui/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
		<!-- 导入ztree类库 -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/js/ztree/zTreeStyle.css" type="text/css" />
		<script src="${pageContext.request.contextPath}/js/ztree/jquery.ztree.all-3.5.js" type="text/javascript"></script>
		<script type="text/javascript">
			$(function(){
				//查询所有的权限数据
				$.post("${pageContext.request.contextPath}/permissionAction_findAll.action",null,function(data){
					console.info(data);
					//将数组中权限数据遍历展示在页面中
					for(var i=0;i<data.length;i++){
						var id = data[i].id;
						var name = data[i].name;
						$("#permissionTD").append('<input type="checkbox" id="'+id+'" name="permissionIds" value="'+id+'" /><label for="'+id+'">' + name+' </label>');
					}
				})
				
				// 授权树初始化
				//ztree：两种数据模式 1、标准 通过children展示子节点  2、简单 通过pID指定上级菜单
				var setting = {
					data : {
						key : {
							title : "t"
						},
						simpleData : {
							enable : true  //开启简单数据模式
						}
					},
					check : {
						enable : true
					}
				};
				
				//如果返回数据格式是标准数据格式，直接调用昨天使用树形表格访问请求即可。
				$.ajax({
					url : '${pageContext.request.contextPath}/menuAction_findAllBySimple.action',
					type : 'POST',
					dataType : 'json',  //返回数据类型
					success : function(data) {
						$.fn.zTree.init($("#menuTree"), setting, data);
					},
					error : function(msg) {
						alert('树加载异常!');
					}
				});
				
				// 点击保存
				$('#save').click(function(){
					var r = $("#roleForm").form('validate');
					if(r){
						//问题：ztree勾选中数据不能提交（span标签） 解决：调用ztree方法获取到勾选中记录
						var treeObj = $.fn.zTree.getZTreeObj("menuTree");
						//注意：调用ztree方法，必须先获取到ztree对象
						var nodes = treeObj.getCheckedNodes(true);
						var array = new Array();
						for(var i=0;i<nodes.length;i++){
							var id = nodes[i].id;
							array.push(id);
						}
						var ids = array.join(",");
						//给表单中隐藏域(菜单id)赋值
						$("#menuIds").val(ids);
						$("#roleForm").submit();
					}
				});
			});
		</script>
	</head>

	<body class="easyui-layout">
		<div region="north" style="height:31px;overflow:hidden;" split="false" border="false">
			<div class="datagrid-toolbar">
				<a id="save" icon="icon-save" href="#" class="easyui-linkbutton" plain="true">保存</a>
			</div>
		</div>
		<div region="center" style="overflow:auto;padding:5px;" border="false">
			<form id="roleForm" method="post"  action="${pageContext.request.contextPath}/roleAction_save.action">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">角色信息<input type="hidden" name="menuIds" id="menuIds"></td>
					</tr>
					<tr>
						<td>名称</td>
						<td>
							<input type="text" name="name" class="easyui-validatebox" data-options="required:true" />
						</td>
					</tr>
					<tr>
						<td>关键字</td>
						<td>
							<input type="text" name="keyword" class="easyui-validatebox" data-options="required:true" />
						</td>
					</tr>
					<tr>
						<td>描述</td>
						<td>
							<textarea name="description" rows="4" cols="60"></textarea>
						</td>
					</tr>
					<tr>
						<td>权限选择</td>
						<td id="permissionTD">
							<!-- <input type="checkbox" name="permissionIds" value="1" /> 添加快递员 
							<input type="checkbox" name="permissionIds" value="2" /> 快递员列表查询
							<input type="checkbox" name="permissionIds" value="3" /> 添加区域  -->
						</td>
					</tr>
					<tr>
						<td>菜单授权</td>
						<td>
							<ul id="menuTree" class="ztree"></ul>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>

</html>