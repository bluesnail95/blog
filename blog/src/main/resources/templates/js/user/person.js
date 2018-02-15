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

    /**
     *上传头像
     */
	new Vue({
        el: "form",
        data: {
            file: '',
            url: '../../image/user/profile.gif'
        },
        methods: {
            uploadImg(event){
                this.file = event.target.files[0];
                console.log(this.file);
            },
            submitForm(event){
                event.preventDefault();
                let formData = new FormData();
                formData.append("file",this.file);
                
                let token = localStorage.getItem("token");

                let config = {
                    headers: {
                        'Content-Type': 'multipart/form-data',
                        'token':token
                    },
                    auth: {
                    	
                    }

                };

                axios.post("http://127.0.0.1:8080/upload/user", formData, config)
                    .then(function(response){
                        if(response.status == 200){
                        	if(response.data.status == 2){
                                localStorage.removeItem("user");
                                localStorage.removeItem("token");
                                location.href = "../../html/user/login.html";
                        	}else if(response.data.status == 1){
                        		alert("上传成功");
                        		//TODO 修改当前显示的图像
                        	}
                        }
                    })
            }
        }
    })
}
