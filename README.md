Date:2018-01-27   

Author:liuffei

Project:blog

一：这个项目计划用来实现哪些功能？

(1)基本功能：从各大网站（包括CSDN，博客园，掘金，开源中国等等）爬取主页数据，用户查看各个网站的博客。

(2)扩展功能：根据用户的浏览习惯或是自己的选择标签，有选择地抓取符合用户习惯的其他博客网站的数据。

综上，目前的想法就是做一个帮助用户筛选自己感兴趣的知识的网站。

二：这个项目将会使用哪些技术？

(1)后台将会使用Maven管理包依赖，SpringBoot集成Spring,SpringMvc,Mybatis。这里选择Mybatis而不是Spring Data JPA的原因是，自己比较熟悉Mybatis，而且如果表与表之间的关联比较多，直接写原生的sql比较简便，虽然Spring Data JPA也可以写原生的Sql,但需要硬编码在代码中，而且MyBatis更有利于优化Sql，美中不足的是不能像Spring Data JPA一样自动建表。可能会选择[开源框架Gecco](http://www.geccocrawler.com/)做网站内容抽取，目前还在观望中。

(2)前端会采用axios+vue+bootstrap的组合做视图层。

(3)数据库选择的是mysql。

三:项目文件夹说明
(1)blog:项目源代码。
(2)perspective：效果图存放的位置。
(3)doc：开发日志，记录自己开发过程遇到的问题。
