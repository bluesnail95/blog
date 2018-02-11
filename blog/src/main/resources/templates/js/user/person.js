function logout() {
    
}

/**
 *初始化
 */
function init(){
    document.getElementById('content').children[0].style.display = "block";
}

/**
 * @param name 模块的id
 */
function module(name){
    var childs = document.getElementById('content').children;
    for(var i = 0;i < childs.length;i++){
    	childs[i].style.display = "none";
    }
    document.getElementById(name).style.display = "block";
}

window.onload = function(){
	init();
}
