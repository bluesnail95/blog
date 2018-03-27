url : http://193.112.47.238:8088

update date : 2018-03-27 

Author : liuffei

Project : blog

一：这个项目将会使用哪些技术？

(1)后台:maven+springboot+mybatis

(2)前端:vue+axios+js

(3)数据库:mysql


二：这个网站目前已经实现的功能有：

(1)利用开源框架gecco获取csdn,cnblogs,osc的首页数据。要点：遍历元素，jquery选择器

(2)用vue的组件封装了标题栏，减少了标题代码的重复率。

(3)登录注册功能。

(4)仿segmengfault的上传头像功能。

三：部署

特点：前后端分开部署

(1)前端：nginx作为反向代理服务器。

(2)后台：springboot打的jar,内置的tomcat。

(3)运行在腾讯云centos7.3操作系统。
