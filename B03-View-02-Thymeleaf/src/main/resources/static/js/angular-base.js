(function (angular) {
	var baseModule = angular.module( 'ctp.base', [ 'ngResource' ] );

	baseModule.factory( 'Resource', [ '$resource', function( $resource ) {
	   return function( url, params, methods ) {
	    var defaults = {
	      update: { method: 'put', isArray: false },
	      create: { method: 'post' }
	    };
	    
	    methods = angular.extend( defaults, methods );

	    var resource = $resource( url, params, methods );

	    resource.prototype.$save = function(params, success, error) {
	      if ( !this.id ) {
	        return this.$create(params, success, error);
	      }
	      else {
	        return this.$update(params, success, error);
	      }
	    };

	    return resource;
	  };
	}]);

	// 全局Http请求异常处理
	baseModule.factory('HttpExpectionInterceptor', ['$q', '$window', function ($q, $window) {
		return {
		    request: function(config){
		    	console.log("request:%o", config);
//		    	config.url = '/web' + config.url;
		    	return config;
		    },
		    requestError: function(err){
		    	console.log("requestError:%o", err);
		    	return $q.reject(err);
		    },
		    response: function(res){
		   		console.log("response:%o", res);
		      	return res;
		    },
		    responseError: function(err){
		    	if($window.parent && $window.parent.alertShow) {
		    		if(err.config.method == 'GET') {
		    			$window.parent.alertShow('error', '页面加载数据失败，请刷新页面或者联系维护人员。');
		    		} else if(err.config.method == 'PUT' || err.config.method == 'POST') {
		    			$window.parent.alertShow('error', '数据保存失败，请重新保存或者联系维护人员。');
		    		} else if(err.config.method == 'DELETE') {
		    			$window.parent.alertShow('error', '删除失败，请重新保存或者联系维护人员。');
		    		} else {
		    			$window.parent.alertShow('error', '操作失败，请重试或者联系维护人员。');
		    		}
		    	}

			    if(-1 === err.status) {

			    } else if(500 === err.status) {

			    } else if(501 === err.status) {
			        // ...
			    }
		      return $q.reject(err);
		    }
	    };
	}]);


	// 添加对应的 Interceptors
	baseModule.config(['$httpProvider', function($httpProvider){
		$httpProvider.interceptors.push('HttpExpectionInterceptor');
	}]);

	baseModule.factory( 'utils', [ '$window', '$rootScope', function( $window) {
	   	return {
		   	param: function (key)
		   	{
		        var reg = new RegExp("(^|&)"+ key +"=([^&]*)(&|$)");
		        var r = $window.location.search.substr(1).match(reg);
		        if(r!=null) {
		        	return  unescape(r[2]); 
		        }
		        return null;
		   	},
		   	safeApply : function($scope, fn) {
		        var phase = $scope.$root.$$phase;
		        if(phase == '$apply' || phase == '$digest') {
		            if (fn) {
		                $scope.$eval(fn);
		            }
		        } else {
		            if (fn) {
		                $scope.$apply(fn);
		            } else {
		                $scope.$apply();
		            }
		        }
		    },
		    uuid: function () {
				function s4() {
			    	return Math.floor((1 + Math.random()) * 0x10000)
			    		.toString(16)
			      		.substring(1);
			  }
			  return s4() + s4() + '-' + s4() + '-' + s4();
			}
	   }
	}]);
})(angular);

