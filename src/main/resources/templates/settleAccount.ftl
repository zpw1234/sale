<html>
<head>
    <meta charset="utf-8">
    <title>购物车</title>
    <link rel="stylesheet" href="/css/style.css">
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
<#if (cartVoList?size == 0)>
    <div class="g-doc">
        <div class="n-result">
            <h3>购物车空空如也~</h3>
            <p><a href="/">[前往购物]</a><a href="/">[返回首页]</a></p>
        </div>
    </div>
<#else>
    <div class="g-doc" id="settleAccount">
        <div class="m-tab m-tab-fw m-tab-simple f-cb">
            <input style="display: none" id="user" name="user" value="${user.id}">
            <h2>已添加到购物车的内容</h2>
        </div>
        <table id="newTable" class="m-table m-table-row n-table g-b3">
            <tbody>
            <tr>
                <th><input type="checkbox" id="selectAll" class="cart-checkbox" name="allCarts"></th>
                <th>内容名称</th>
                <th>数量</th>
                <th>价格</th>
                <th>操作</th>
            <tr>
                <#list cartVoList as cartVo>
            <tr id="${cartVo.contentId}">
                <td><input type="checkbox" class="cart-checkbox" name="cartVo" data-id="${cartVo.contentId}"></td>
                <td><a href="/show/${cartVo.contentId}">${cartVo.title!}</a></td>
                <td>
                    <span class="lessNum"><a>-</a></span>
                    <span class="totalNum"><input style="width: 39px" type="text" value="${cartVo.num!}"
                                                  onkeyup="this.value=this.value.replace(/\D|^0/g,'')"></span>
                    <span class="moreNum"><a>+</a></span>
                </td>
                <td>
                    ${cartVo.price?c}
                </td>
                <td>
                    <span class="delete"><button style="background-color: red" name="deleteCartDetail" data-userid="${cartVo.userId}" data-id="${cartVo.contentId}">删除</button></span>
                </td>
            </tr>
            </#list>
            </tbody>
        </table>
        <div id="act-btn">
            <button class="u-btn u-btn-primary" id="back" onclick="javascript:history.back(-1);">返回</button>
            <button class="u-btn u-btn-primary" id="buy">购买</button>
        </div>

    </div>
</#if>

<div class="n-foot">
    <p>版权所有：<a href="http://www.163.com/">网易</a>Java开发工程师大作业</p>
</div>
<script type="text/javascript" src="/layui/layui.js"></script>
<script type="text/javascript" src="/js/global.js"></script>
<script type="text/javascript" src="/js/settleAccount.js"></script>
</body>
</html>