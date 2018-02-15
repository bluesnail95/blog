 /*
 <ul class="header">
		<li><a href="index.html" class="active">主页</a></li>
		<div class="dropdown">
			<a href="#" class="dropbtn">博客网站</a>
			<div class="dropdown-content">
				<a href="blog/csdn.html">CSDN</a>
				<a href="blog/cnblogs.html">博客园</a>
				<a href="blog/osc.html">开源中国</a>
			</div>
		</div>
		<li><a href="contact.html">联系我</a></li>
		<li><a href="about.html">关于</a></li>
		<li style="float: right;"><a href="user/login.html">登录</a></li>
	</ul>
 */

/**
 *path 从后台获取项目路径
 */
var path,router;
axios.get('http://127.0.0.1:8080/comm/path')
    .then(function(response){
        path = response.data.root;

        if(initRouter(path)){
        	/**
        	 *定义标题导航组件
        	 */
        	Vue.component('my-nav',{
			    //props:['index_href','contact_href','about_href','csdn_href','cnblogs_href','osc_href','login_href'],
			    template:'<ul class="header">'
					      +   '<li><a v-bind:href="index_href" class="active">主页</a></li>'
					      +   '<div class="dropdown">'
						  +       '<a href="#" class="dropbtn">博客网站</a>'
						  +       '<div class="dropdown-content">'
						  +	          '<a v-bind:href="csdn_href">CSDN</a>'
						  +	          '<a v-bind:href="cnblogs_href">博客园</a>'
						  +	          '<a v-bind:href="osc_href">开源中国</a>'
						  +        '</div>'
					      +   '</div>'
					      +   '<li><a v-bind:href="contact_href">联系我</a></li>'
					      +   '<li><a v-bind:href="about_href">关于</a></li>'
					      +   '<li style="float: right;"><a v-bind:href="login_href">登录</a></li>'
				          +'</ul>',
				data:function(){
					return router;
				}
			})

            /**
             *定义组件的作用范围
             */
			new Vue({
				el:"#top"
			})
        }      

    })
    .catch(function(error){

    });

function initRouter(path){
    router = {
	    'index_href':"/"+path+"templates/html/index.html",
	    'contact_href':"/"+path+"templates/html/contact.html",
	    'about_href':"/"+path+"templates/html/about.html",
	    'csdn_href':"/"+path+"templates/html/blog/csdn.html",
	    'cnblogs_href':"/"+path+"templates/html/blog/cnblogs.html",
	    'osc_href':"/"+path+"templates/html/blog/osc.html",
	    'login_href':"/"+path+"templates/html/user/login.html"
	};
    return true;
}










