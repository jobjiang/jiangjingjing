<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
	<title>Freemarker入门СDEMO </title>
</head>
<body>
<#assign linkman="八维">
联系人：${linkman}
<#assign info={"mobile":"13333333333",'address':'北京市海淀区'} >

<#assign text="{'bank':'工商银行','account':'10101920201920212'}" />
<#assign data=text?eval />


<#--qwertyui  -->
${name},趣味,${message},${linkman}
电话：${info.mobile}  地址：${info.address}
<hr/>
<#include "head.ftl">
<hr/>

<#if success==true>
  你已通过实名认证
<#else>  
  你未通过实名认证
</#if>

<hr/>
<#list goodsList as goods>
  ${goods_index+1} 商品名称： ${goods.name} 价格：${goods.price}<br>
</#list>
共  ${goodsList?size}  条记录
<hr/>
开户行：${data.bank}  账号：${data.account}

<hr/><hr/>
当前日期：${today?date} <br>
当前时间：${today?time} <br>   
当前日期+时间：${today?datetime} <br>        
日期格式化：  ${today?string("yyyy年MM月")}
<hr/>

${point}可以转换积分
${point?c}不想转换积分

<hr/><hr/>
<#if aaa??>
  aaa变量存在
<#else>
  aaa变量不存在
</#if>
<hr/><hr/><hr/>
${aaa!'-'}
${name!'-'}
</body>
</html>
