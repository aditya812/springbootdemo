var demo = angular.module("demo",['ngCookies']);

demo.controller('loginController',['$scope','$cookies','$window','login',function($scope,$cookies,window,login){
	var baseUrl =  window.location.protocol+"//"+window.location.host+"/";
	alert("ss");
	$scope.login = function(){
		var data={
				"userName":$scope.userName,
				"password":$scope.password
				}
		login.add(data).then(function(response){
			console.log("fjghbj");
			$cookies.remove("demo",{path : '/'});
			$cookies.put("demo",response.data.token,{path:'/'});
			window.location.href = baseUrl+'admin/index.html';
		});
	}
}]);

demo.service('serviceFactory', ['$http','$q', function($http,$q) {
	var baseUrl =  window.location.protocol+"//"+window.location.host+"/";
	var deferred = $q.defer();
	this.sendToServer = function(url, data, methodType){
		deferred = $q.defer();
		var data = data;
		if(methodType == 'GET')
			data = null;
		
		 return $http({
 			 method: methodType,
 			 url:baseUrl+'api/v1/'+url,
 			 headers: {
 			   'Content-Type': 'application/json'
 			 },
 			data: data,
 			cache:false,
 			timeout : 5000
 	});
	}
	
}]);

demo.service('login',["serviceFactory", function(serviceFactory) {
	
	this.add = function(data){
		return serviceFactory.sendToServer("user/login", data ,'POST');
	}
	
}]);

