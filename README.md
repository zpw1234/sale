# 内容销售系统

## 相关介绍

系统有两类用户，买家和卖家，本系统目前只有一个买家和卖家，不提供注册功能，增加用户需在后台进行添加。卖家可以发布内容、查看购买状况等，买家可以浏览已发布的内容、购物车、查看已购买商品等。

主要技术栈：SpringBoot、MybatisPlus、Freemarker、LayUI、Redis、MySQL、七牛云等

## 查看系统需知

其中resources下的static存放界面所需的css、js数据，用户上传图片会自动上传到云端，templates中为前端页面实现。sql数据存放在comment_sale.sql中

默认的普通用户：账号：buyer，密码：reyub

默认的管理员：账号：seller，密码：relles	

