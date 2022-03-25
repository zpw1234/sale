<html>
<head>
    <meta charset="utf-8"/>
    <title>内容销售系统</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css">
    <script type="text/javascript" src="/jquery/jquery.min.js"></script>
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
    <div class="m-tab m-tab-fw m-tab-simple f-cb">
        <div class="tab">
            <ul>
                <li <#if type == 0>class="z-sel"</#if>><a href="/">所有内容</a></li>
                <#if user?? && user.isBuyer == 1>
                    <li <#if type == 1>class="z-sel"</#if>><a href="?type=1">未购买的内容</a></li>
                </#if>
            </ul>
        </div>
    </div>
    <div class="n-plist">
        <ul class="f-cb" id="plist">
            <#list contentList as content>
                <li id="${content.id}">
                    <a href="/show/${content.id}" class="link">
                        <div class="img"><img src="${content.image!}" alt="${content.title!}"></div>
                        <h3>${content.title!}</h3>
                        <div class="price"><span class="v-unit">¥</span><span class="v-value">${content.price}</span></div>
                        <#if user?? && user.isBuyer == 1>
                            <#if content.valid != 0>
                                <span class="had"><b>已购买</b></span>
                            </#if>
                        </#if>
                        <#if user?? && user.isBuyer == 0>
                            <#if type==0 && content.valid != 0>
                                <span class="had"><b>已售出</b></span>
                            </#if>
                        </#if>
                    </a>
                    <#if user?? && user.isBuyer == 0>
                        <#if sellMap[content.id] == 0>
                            <span class="u-btn u-btn-normal u-btn-xs del" data-del="${content.id!}">删除</span>
                        <#else>
                            <span class="del"><b>售出${sellMap[content.id]}</b></span>
                        </#if>
                    </#if>
                </li>
            </#list>
        </ul>
    </div>
</div>
<div class="n-foot">
    <p>版权所有：网易<a href="http://www.163.com"></a>Java开发工程师大作业</p>
</div>
<script type="text/javascript" src="/js/global.js"></script>
<script type="text/javascript" src="/js/pageIndex.js"></script>
</body>
</html>