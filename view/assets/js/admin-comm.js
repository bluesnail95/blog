new Vue({
	el: "#admin-menu",
	data: function() {
		  return {
          blogs: []
      };
	},
	mounted: function() {
      
  },
  methods: {
    	write() {
  			  sessionStorage.removeItem("blogId");
  			  window.location.href = "../admin/admin-edit.html";
  	  }
  }
})