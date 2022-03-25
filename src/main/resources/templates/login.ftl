<html>
<head>
    <meta charset="utf-8"/>
    <title>登录</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css">
    <link rel="stylesheet" href="/layui/css/layui.css">
    <script type="text/javascript" src="/jquery/jquery.min.js"></script>
</head>
<body>
<div class="n-support">请使用Chrome、Safari等webkit内核的浏览器！</div>
<div class="n-head">
    <div class="g-doc f-cb">
        <div class="user">
            请<a href="/login">[登录]</a>
        </div>
        <ul class="nav">
            <li><a href="/">首页</a></li>
        </ul>
    </div>
</div>
<form class="m-form m-form-ht n-login" id="loginForm" onsubmit="return false;" autocomplete="off">
    <div class="fmitem">
        <label class="fmlab"><i class="layui-icon layui-icon-username"></i>用户名：</label>
        <div class="fmipt">
            <input class="u-ipt" id="username" name="username" autofocus="" placeholder="用户名(buyer/seller)">
        </div>
    </div>
    <div class="fmitem">
        <label class="fmlab"><i class="glyphicon glyphicon-star" aria-hidden="true"></i> 密码：</label>
        <div class="fmipt">
            <input class="u-ipt" type="password" id="password" name="password" placeholder="密码(reyub/relles)">
        </div>
    </div>
    <div class="fmitem fmitem-nolab fmitem-btn">
        <div class="fmipt">
            <button type="submit" class="u-btn u-btn-primary u-btn-lg u-btn-block" id="login-btn">登 录</button>
        </div>
    </div>

</form>
<div class="n-foot">
    <p>版权所有：网易<a href="http://www.163.com"></a>Java开发工程师大作业</p>
</div>
<script type="text/javascript" src="/js/md5.js"></script>
<script type="text/javascript" src="/layui/layui.js"></script>
<script type="text/javascript" src="/js/pageLogin.js"></script>
</body>
</html>