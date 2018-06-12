<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath }/js/ztree/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/ztree/jquery.ztree.all-3.5.js"></script>
	
<title>Insert title here</title>

</head>
<body>
		
<!-- 	<hr>
//使用ztree 标准json数据格式  关键点：通过children指定子节点
		<ul id="ztree1" class="ztree"></ul>
		<script type="text/javascript">
			//配置ztree属性
			var setting1 = {}; //使用默认值
			
			var nodes1 = [
			             	{"id":1,"name":"节点一",children:[{"id":101,"name":"节点二",children:[{"id":201,"name":"节点三"}]}]}
			              ];
			//p1:用于展现 zTree 的 DOM 容器  p2:zTree 的配置数据 p3:zTree 的节点数据'
			$.fn.zTree.init($("#ztree1"), setting1, nodes1);
		</script>
		<hr>
		
		//使用ztree 简单json数据格式  关键点：通过pId指定上级节点
		<ul class="ztree" id="ztree2"></ul>
		<script type="text/javascript">
			var setting2 = {
					data: {
						simpleData: {
							enable: true,   //开启简单数据模式
							idKey: "id",    //通过id属性指定节点标识
							pIdKey: "pId",  //通过pId属性指定上级节点
							rootPId: 0,     //根节点（顶级节点）上级ID为0
						}
					}
			};
			var nodes2 = [{"id":1,"name":"节点一","pId":101},{"id":101,"name":"节点二","pId":103},{"id":103,"name":"节点三","pId":0}]
			$.fn.zTree.init($("#ztree2"), setting2, nodes2);
			
		</script> -->
		
		
		
		
		
		
		
		
		
		
		
		<ul id="treeDemo" class="ztree"></ul>
		
		<SCRIPT type="text/javascript">
		
			var setting = {	};
	
			var zNodes =[
				{ name:"父节点1 - 展开", open:true,
					children: [
						{ name:"父节点11 - 折叠",
							children: [
								{ name:"叶子节点111"},
								{ name:"叶子节点112"},
								{ name:"叶子节点113"},
								{ name:"叶子节点114"}
							]},
						{ name:"父节点12 - 折叠",
							children: [
								{ name:"叶子节点121"},
								{ name:"叶子节点122"},
								{ name:"叶子节点123"},
								{ name:"叶子节点124"}
							]},
						{ name:"父节点13 - 没有子节点", isParent:true}
					]},
				{ name:"父节点2 - 折叠",
					children: [
						{ name:"父节点21 - 展开", open:true,
							children: [
								{ name:"叶子节点211"},
								{ name:"叶子节点212"},
								{ name:"叶子节点213"},
								{ name:"叶子节点214"}
							]},
						{ name:"父节点22 - 折叠",
							children: [
								{ name:"叶子节点221"},
								{ name:"叶子节点222"},
								{ name:"叶子节点223"},
								{ name:"叶子节点224"}
							]},
						{ name:"父节点23 - 折叠",
							children: [
								{ name:"叶子节点231"},
								{ name:"叶子节点232"},
								{ name:"叶子节点233"},
								{ name:"叶子节点234"}
							]}
					]},
				{ name:"父节点3 - 没有子节点", isParent:true}
	
			];
	
			$(document).ready(function(){
				$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			});
		
	</SCRIPT>
		
	
	<ul id="treeDemo1" class="ztree"></ul>
		
	<SCRIPT type="text/javascript">
		
		var setting = {
			data: {
				simpleData: {
					enable: true
				}
			}
		};

		var zNodes =[
			{ id:1, pId:0, name:"父节点1 - 展开", open:true},
			{ id:11, pId:1, name:"父节点11 - 折叠"},
			{ id:111, pId:11, name:"叶子节点111"},
			{ id:112, pId:11, name:"叶子节点112"},
			{ id:113, pId:11, name:"叶子节点113"},
			{ id:114, pId:11, name:"叶子节点114"},
			{ id:12, pId:1, name:"父节点12 - 折叠"},
			{ id:121, pId:12, name:"叶子节点121"},
			{ id:122, pId:12, name:"叶子节点122"},
			{ id:123, pId:12, name:"叶子节点123"},
			{ id:124, pId:12, name:"叶子节点124"},
			{ id:13, pId:1, name:"父节点13 - 没有子节点", isParent:true},
			{ id:2, pId:0, name:"父节点2 - 折叠"},
			{ id:21, pId:2, name:"父节点21 - 展开", open:false},
			{ id:211, pId:21, name:"叶子节点211"},
			{ id:212, pId:21, name:"叶子节点212"},
			{ id:213, pId:21, name:"叶子节点213"},
			{ id:214, pId:21, name:"叶子节点214"},
			{ id:22, pId:2, name:"父节点22 - 折叠"},
			{ id:221, pId:22, name:"叶子节点221"},
			{ id:222, pId:22, name:"叶子节点222"},
			{ id:223, pId:22, name:"叶子节点223"},
			{ id:224, pId:22, name:"叶子节点224"},
			{ id:23, pId:2, name:"父节点23 - 折叠"},
			{ id:231, pId:23, name:"叶子节点231"},
			{ id:232, pId:23, name:"叶子节点232"},
			{ id:233, pId:23, name:"叶子节点233"},
			{ id:234, pId:23, name:"叶子节点234"},
			{ id:3, pId:0, name:"父节点3 - 没有子节点", isParent:true}
		];

		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo1"), setting, zNodes);
		});
		
	</SCRIPT>	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
</body>
</html>