<html>
<head>
    <meta charset="utf-8">
    <title>内容编辑</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<div class="n-support">请使用Chrome、Safari等webkit内核的浏览器！</div>
<div class="n-head">
    <div class="g-doc f-cb">
        <div class="user">
            <#if user??>
                <#if user.isBuyer == 1>
                    买家您好，<span class="name">${user.username!}</span>!<a href="/logout">[退出]</a>
                <#elseif user.isBuyer == 0>
                    卖家您好，<span class="name">${user.username!}</span>!<a href="/logout">[退出]</a>
                </#if>
            <#else>
                请<a href="/login">[登录]</a>
            </#if>
        </div>
        <ul class="nav">
            <li><a href="/">首页</a></li>
            <#if user??>
                <#if user.isBuyer == 1>
                    <li><a href="/account">财务</a></li>
                    <li><a href="/settleAccount">购物车</a></li>
                <#elseif user.isBuyer == 0>
                    <li><a href="/publish">发布</a></li>
                </#if>
            </#if>
        </ul>
    </div>
</div>

<div class="g-doc">
    <div class="n-result">
        <h3>编辑成功！</h3>
        <p><a href="/show/${content.id}">[查看内容]</a><a href="/">[返回首页]</a></p>
    </div>
</div>
<div class="n-foot">
    <p>版权所有：网易云课堂<a href="http://mooc.study.163.com/smartSpec/detail/85002.htm">Java开发工程师(Web方向)</a>微专业团队</p>
</div>
</body>
</html>