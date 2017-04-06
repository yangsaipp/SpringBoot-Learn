angular.module('RouteService', [ 'ctp.base']).factory('Routes', [ 'Resource', '$http', function($resource, $http) {
	var Routes = $resource('console/routes/:id', {id:'@id'}, {
		query: {
		    method: 'GET', 
		    transformResponse: function(data, headers){
		        var pager = JSON.parse(data);
		        for(item in pager.data) {
			        pager.data[item] = new Routes(pager.data[item]);
		        }
		        return pager;
		    }
		},
	});
	
	Routes.prototype.fmtText = function(prop) {
		var value = this[prop];
		var items = enumItems[prop];
		return items[value];
	};

	var enumItems = {
			retryTimes : {
			    0 : "不重试",
			    1 : "1次",
			    2 : "2次",
			    3 : "3次",
			    4 : "4次",
			    5 : "5次"
			},
			loadBalanceStrategy : {
				NO : "无负载",
				POLLING : "轮询",
				RANDOM : "随机"
			},
			stripPrefix: {
				'true' : '忽略',
				'false': '不忽略' 
			},
			registerAddress: {
				Eureka: 'Eureka',
				Zookeeper: 'Zookeeper'
			},
			registType: {
				Auto: '自动注册',
				Manual: '手动注册'
			},
			manualType: {
				Host: 'Host',
				URL: 'URL'
			}
	}

	Routes.enumItems = function() {
		var returnItems = {}
		for(pro in enumItems) {
			var items = []
			var index = 0
			if(enumItems[pro] == null || angular.isArray(enumItems[pro])) {
				continue
			}
			for(p in enumItems[pro]) {
				items[index] = {};
				if('true' == p || 'false' == p) {
					items[index].value = 'true' == p;
				}else{
					items[index].value = p;
				}
				items[index].text = enumItems[pro][p];
				index++;
			}
			returnItems[pro] = items;
		};
		
		return returnItems;
	}
	
	angular.extend(Routes, {
		batchDel: function(ids, success, error) {
			var promise = $http.post('/console/routes/batch', ids).then(function(response){
				angular.extend(promise, response.data);
				success(promise);
			}, error);
			return promise;
		}
	})

	return Routes;
}]);