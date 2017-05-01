angular.module('appService').factory('ConfigFormatter', [ function() {
	
	////////////////////////////////////////////

	function exec(strExpr, data) {
		var exprArray = strExpr.split('.');	// 1.id -> [1,id]
		if(!isNaN(exprArray[0])) {
			exprArray[0] = exprArray[0] - 1;	// [1,id] -> [0,id]
		}
		var d = data;
		for(var i in exprArray) {
			d = d[exprArray[i]];
		}
		return d;
	}

	var paramExprFlag = "arg";
	var resultExprFlag = 'result';

	function wipeExprFlag(strExpr, isParamExpr) {
		if(isParamExpr) {
			return strExpr.slice(paramExprFlag.length);
		}else {
			return strExpr.slice(resultExprFlag.length + 1);
		}
	}

	function initExprType(expr, isSimpleExpr) {
		expr.strExpr = expr.strExpr.slice(2, -1);	//　去掉‘={’
		
		if(expr.strExpr.indexOf(paramExprFlag) === 0) {
			expr.isParamExpr = true;
		}

		if(isSimpleExpr) {
			expr.strExpr = wipeExprFlag(expr.strExpr, expr.isParamExpr);
		}
	}

	function SimpleExpr(strExpr) {
		this.orgiExpr = strExpr;
		this.strExpr = strExpr;
		this.init();
	}

	SimpleExpr.prototype = {
		init: function () {
			this.isParamExpr = false;
			initExprType(this, true);
		},
		exec: function (data) {
			try {
				var value = exec(this.strExpr, data);
				console.debug("SimpleExpr: expr=%s,value=%o", this.strExpr, value);
				if(typeof value != "string") {
					value = JSON.stringify(value);
				}
				return value;
			} catch (e) {
				console.debug("SimpleExpr error: expr=%s, data=%o, error=%o", this.strExpr, data, e);
				return null;
			}
			
		}
	};

	function lookupParent(exprFragment, exprFragmentArray) {
		var strExpr = exprFragment.strExpr;
		for(var i in exprFragmentArray) {
			if(exprFragment == exprFragmentArray[i]) {
				continue;
			}

			if(strExpr.indexOf(exprFragmentArray[i].strExpr) != -1) {
				var startIndex = exprFragmentArray[i].strExpr.length + 1;
				var endIndex = strExpr.lastIndexOf('.');
				var strParentExpr = strExpr.substring(startIndex, endIndex);
				var newStrExpr = strExpr.substring(endIndex + 1);
				exprFragment.strExpr = newStrExpr;
				if(strParentExpr == ".") {
					return exprFragmentArray[i];
				}else {
					return exprFragmentArray[i]._lookupParent(strParentExpr);	
				}
			}
		}
	}
	
	function initExprFragment(complexExpr) {

		var strExpr = complexExpr.strExpr;
		var exprArray = strExpr.match(/[^,=]+?\[.+?\]/g);
		console.debug('initExprFragment:%o',exprArray);
		complexExpr.exprFragment = new ExprFragment(wipeExprFlag(exprArray.shift(), complexExpr.isParamExpr));
		var exprFragmentArray = [];
		exprFragmentArray.push(complexExpr.exprFragment);
		for(var i in exprArray) {
			var exprFragment = new ExprFragment(wipeExprFlag(exprArray[i], complexExpr.isParamExpr));
			exprFragmentArray.push(exprFragment);
		}

		exprFragmentArray.sort(function (expr1, expr2) {
			return expr2.strExpr.length - expr1.strExpr.length;
		});

		for(var i in exprFragmentArray) {
			var exprFragment = exprFragmentArray[i];
			if(exprFragment == complexExpr.exprFragment) {
				continue;
			}

			var parent = lookupParent(exprFragment, exprFragmentArray);
			exprFragment.parent = parent;
			parent.addExprFragment(exprFragment);
		}
	}

	function ExprFragment(strExpr, parent, mapExprFragment) {
		this.parent = parent;
		this.orgiExpr = strExpr;
		this.strExpr = strExpr;
		this.mapExprFragment = mapExprFragment || {};
		this.init();
	}

	ExprFragment.prototype = {
		init: function () {
			if(this.strExpr.indexOf('[') != -1) {
				var exprArray = this.strExpr.match(/(.+?)\[(.+)\]/);
				this.strExpr = exprArray[1];
				var expF = exprArray[2].split(',');
				this.text = expF.shift();
				if(this.text.indexOf("=") != -1) {
					var arrText = this.text.split("=");
					this.text = arrText[0];
					this.isMultiArray = true;
				}

				this.cExprFragments = [];
				this.isArrayValue = true;	// Array、List、Map
				for(var i in expF) {
					var exprFragment = new ExprFragment(expF[i], this, this.mapExprFragment);
					if(exprFragment.parent == this) {
						this.cExprFragments.push(exprFragment);
					}
				}
			}else {
				var exprArray = this.strExpr.split("=");
				this.text = exprArray[1];
				this.strExpr = exprArray[0];
				this.mapExprFragment[this.strExpr] = this;
				if(this.strExpr.indexOf('.') != -1) {
					var exprArr = this.strExpr.split('.');

					this.strExpr = exprArr.pop();
					var parentExprFragment = this._lookupParent(exprArr.join('.'));
					this.parent = parentExprFragment;
					parentExprFragment.addExprFragment(this);
				}
				
			}
		},

		_lookupParent: function (parentStrExpr) {
			return this.mapExprFragment[parentStrExpr];
		},

		addExprFragment: function (exprFragment) {
			if(this.cExprFragments == null) {
				this.cExprFragments = [];
			}

			this.cExprFragments.push(exprFragment);
		},
		exec: function (data, p, textBuilder) {
			try {
				if(this.cExprFragments) {	// 原始数据为集合
					// 执行 判断返回值 1.对象 2.数组 3.MAP
					var d = exec(this.strExpr, data);
					if(d == null) {
						textBuilder[p][this.text] = null;
					}else {
						if(this.isArrayValue) {	// 数组 List Map
							var arr;
							// 创建与原始数据类型一致的集合对象用于存放格式化数据
							if(textBuilder == null) {	// 第一次进入时创建textBuilder用于存放整个格式化数据
								arr = textBuilder = getIntanceByType(d);
							}else {
								arr = textBuilder[p][this.text] = getIntanceByType(d);
							}
							// 遍历原始数据集合
							for(var i in d) {
								// 创建与原始数据集合里元素类型一致的对象用于对应的格式化数据
								arr[i] = getIntanceByType(d[i]);
								if(this.isMultiArray) {	// 处理List<List<Object>>以及List<Map<String,Object>>等场景
									for(var k in d[i]) {
										arr[i][k] = getIntanceByType(d[i][k]);

										for(var j in this.cExprFragments) {
											this.cExprFragments[j].exec(d[i][k], k, arr[i]);
										}
									}
									
								}else {
									for(var j in this.cExprFragments) {
										this.cExprFragments[j].exec(d[i], i, arr);
									}
								}
								
							}
						}else {					// 对象
							textBuilder[p][this.text] = {};
							for(var j in this.cExprFragments) {
								this.cExprFragments[j].exec(d, this.text, textBuilder[p]);
							}
						}
					}
					
				}else {
					var value = exec(this.strExpr, data);
					textBuilder[p][this.text] = value;
				}

				// console.log("ComplexExpr:\n expr:%o, \n strBuilder:%o",this.orgiExpr, textBuilder);
				return JSON.stringify(textBuilder);
			} catch (e) {
				console.debug("ComplexExpr error: expr=%s, data=%o, error=%o", this.strExpr, data, e);
				return null;
			}
		}

	};

	function getIntanceByType(obj) {
		return angular.isArray(obj) ? [] : {};
	}

	function ComplexExpr(strExpr) {
		this.orgiExpr = strExpr;
		this.strExpr = strExpr;
		this.init();
	}

	ComplexExpr.prototype = {
		init: function () {
			this.isParamExpr = false;
			initExprType(this);
			this.exprFragment = null;
			this.cExprFragments = [];
			initExprFragment(this);
		},
		exec: function (data) {
			return this.exprFragment.exec(data);
		}
	};

	function ConfigExpr(strExpr, location) {
		this.location = location;
		this.init(strExpr);
	}

	ConfigExpr.prototype.init = function(strExpr) {
		
		// 复杂表达式带有[]
		if(strExpr.indexOf('[') != -1) {
			this.expr = new ComplexExpr(strExpr);
		}else {
			this.expr = new SimpleExpr(strExpr);
		}
	};
	
	ConfigExpr.prototype.exec = function(data) {
		if(this.expr.isParamExpr) {
			return this.expr.exec(data.lstParamData);
		} else {
			return this.expr.exec(data.resultData);
		}
	};
	
	function ConfigFormatter(formatConfig) {
		this.formatConfig = formatConfig;
		this.init();
	}

	ConfigFormatter.prototype.init = function() {
		this.mapConfigExpr = {};
		this.formatTemplate = '';
		var me = this;
		var index = 0;
		this.formatTemplate = this.formatConfig.replace(/={.+?}/g, function(matchStr) {
			var placeholder = '={' + index + '}';
			me.mapConfigExpr[placeholder] = new ConfigExpr(matchStr, index);
			index ++;
			return placeholder;
		});
		
//		console.debug(this.formatTemplate);
	};
	
	

	ConfigFormatter.prototype.format = function(data) {
		var mapValue = {};
		for(var placeholder in this.mapConfigExpr) {
			var configExpr = this.mapConfigExpr[placeholder];
			mapValue[placeholder] = configExpr.exec(data);
		}
		var formatText = this.formatTemplate.replace(/={.+?}/g, function(placeholder) {
//			console.debug("placeholder:%s", placeholder);
			return "=" + mapValue[placeholder];
		});
		console.log("========================");
		console.log("formatText:%s",formatText);
		return formatText;
	};
	
	return {
		createFormatConfig: function(mapFieldConfigVO) {
			return buildFormatConfig(mapFieldConfigVO);
		},
		format: function(formatConfig, lstParamData, resultData) {
			// 解析
			console.log("formatConfig:%o",formatConfig);
			console.log("========================");
			var configFormatter = new ConfigFormatter(formatConfig);
			// 获取值
			var data = {
				'lstParamData': lstParamData,
				'resultData': resultData
			};
			return configFormatter.format(data);
		}
	};
}]);