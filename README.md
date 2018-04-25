url : http://193.112.47.238:8088

update date : 2018-04-25

Author : liuffei

Project : blog

一：这个项目将会使用哪些技术？

(1)后台:maven+springboot+mybatis+redis

(2)前端:vue+axios+js+amazeui

(3)数据库:mysql


二：这个网站目前已经实现的功能有：

(1)利用开源框架gecco获取csdn,cnblogs,osc的首页数据。要点：遍历元素，jquery选择器

(2)用vue的组件封装了标题栏，减少了标题代码的重复率。

(3)登录注册功能。

(4)仿segmengfault的上传头像功能。

(5)使用amazeui重构页面，完成markdown编辑器的增删改查,存草稿待完成。(2018-04-03)

(6)完成后台登录验证(2018-04-09)

(7)引入spring-data-redis,修改接口的返回值是JSONObject(原来的ObjectMapper不支持Redis的序列化)(2018-04-12)

(8)自定义登录异常，添加页面处理登录异常的操作，整理代码，对外的博客网站和对内管理员使用的后台管理系统环境初步搭建完成(2018-04-15)。

(9)完成通过指定的UTRL下载文件，上传文件到指定的服务器还没有完成。(2018-04-23)

(10)用环绕通知和异常通知替代每个方法的try{}catch(){}。(2018-04-25)

三：部署

特点：前后端分开部署

(1)前端：nginx作为反向代理服务器。

(2)后台：springboot打的jar,内置的tomcat。

(3)运行在腾讯云centos7.3操作系统。
