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
    var user = localStorage.getItem("user");
    var img;
    if(user){
        user = JSON.parse(user);
        if(user.img){
            img = user.img;
        }else{
            img = '../../image/user/profile.gif';
        }
    }
    /**
     *上传头像
     */
	new Vue({
        //el: "form",
        el: "#profile",
        data: {
            file: '',
            //TODO 这里应该修改成用户的头像，需要加载路径
            url: img
        },
        methods: {
            uploadImg(event){
                //this.url = localStorage.getItem("profile");
                this.file = event.target.files[0];
                //event.preventDefault();
                let formData = new FormData();
                formData.append("file",this.file);
                
                let token = localStorage.getItem("token");

                let config = {
                    headers: {
                        'Content-Type': 'multipart/form-data',
                        'token':token
                    }
                };

                axios.post("http://127.0.0.1:8080/upload/user", formData, config)
                    .then(function(response){
                        if(response.status == 200){
                            if(response.data.status == 2){
                                localStorage.removeItem("user");
                                localStorage.removeItem("token");
                                location.href = "../../html/user/login.html";
                                return;
                            }else if(response.data.status == 1){
                                //修改当前显示的图像
                                var uploadUrl = response.data.url;
                                localStorage.setItem("profile",uploadUrl);
                                this.url = uploadUrl;
                                alert("上传成功");
                            }
                        }
                    })
                this.url = localStorage.getItem("profile");
            },
            uploaderClick(){
                document.getElementById('avatarUploader').addEventListener("click",function(e){
                    if(document.getElementById("avatarFile")){
                        document.getElementById("avatarFile").click();          
                    }
                    //e.preventDefault();
                },false);
            }
        }
    })

    new Vue({
    	el: "#password",
    	data: {
    	    password:"",
    	    newPassword:"",
    	    confirmPassword:""	
    	},
    	methods:{
    		savePassword(){
                var token = localStorage.getItem("token");
                axios.put("http://127.0.0.1:8080/user/password",{
                   "password":md5(this.password),
                   "newPassword":md5(this.newPassword),
                   "comfirmPassword":md5(this.confirmPassword)
                },{
                   headers:{
                       "token":token
                  }
                })
                .then(function(response){
                    if(response.data.status == 1){
                        localStorage.setItem("token",response.data.token);
                        localStorage.setItem("user",JSON.stringify(response.data.user));
                        alert("密码修改成功");
                    }else if(response.data.status == 2){
                        alert(response.data.msg);
                    }
                })
                .catch(function(error){

                })            
    		},
    		resetPassword(){
                this.password = "";
                this.newPassword = "";
                this.comfirmPassword = "";
    		}
    	}
    })
}
