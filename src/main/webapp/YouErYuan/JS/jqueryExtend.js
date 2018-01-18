/**
 * 基于jQuery的扩展方法定义
 */

/**
 * 扩展jQuery类方法：stringifyJSON
 * 用于把JavaScript对象序列化为JSON字符串
 */
jQuery.extend({
	special4JSON: {'\b': '\\b', '\t': '\\t', '\n': '\\n', '\f': '\\f', '\r': '\\r', '"' : '\\"', '\\': '\\\\'},
	escape4JSON: function(chr){
		return jQuery.special4JSON[chr] || '\\u' + ('0000' + chr.charCodeAt(0).toString(16)).slice(-4);
	},
	stringifyJSON: function(data){
		// use native if exists
		if (window.JSON && window.JSON.stringify){
			return window.JSON.stringify(data);
		}
			
		switch (jQuery.type(data)){
			case 'string':
				return '"' + data.replace(/[\x00-\x1f\\"]/g, jQuery.escape4JSON) + '"';
			case 'array':
				return '[' + jQuery.map(data, jQuery.stringifyJSON) + ']';
			case 'object':
				var string = [];
				jQuery.each(data, function(key, val){
					var json = jQuery.stringifyJSON(val);
					if (json) 
						string.push(jQuery.stringifyJSON(key) + ':' + json);
				});
				return '{' + string + '}';
			case 'number': 
			case 'boolean': 
				return '' + data;
			case 'undefined':
			case 'null': 
				return 'null';
		}
		
		return data;
	}

});

/**
 * 扩展jQuery对象方法：smartFloat
 * 用于展示页面右侧悬浮div层，页面上需要定义一个div，调用该jquery对象的smartFloat()方法
 * 例子：
 * <div class="float" id="float" onclick="toEditService()">浮动显示</div>
 * 
 * $("#float").smartFloat();
 */
jQuery.fn.smartFloat = function() {
    var position = function(element) {
        var top = element.position().top, pos = element.css("position");
        jQuery(window).scroll(function() {
            var scrolls = jQuery(this).scrollTop();
            if (scrolls > top) {
                if (window.XMLHttpRequest) {
                    element.css({
                        position: "fixed",
                        top: 0
                    });    
                } else {
                    element.css({
                        top: scrolls
                    });    
                }
            }else {
                element.css({
                    position: "absolute",
                    top: top
                });    
            }
        });
    };
    return jQuery(this).each(function() {
        position(jQuery(this));                         
    });
}; 

/**
 * 扩展jQuery的zTree命名空间，方便使用zTree组件
 */
jQuery.zTree={
	/**
	 * 获取js对象的真实类型
	 * @param {Object} o 任意js对象
	 * @return {String} 表示对象类型的字符串
	 */
	getRealType: function(o){
		if(typeof(o) == "object"){
			if(o === null){
				return "null";
			}
			if(o.constructor == (new Array).constructor){
				return "array";
			}
			if(o.constructor == (new Date).constructor){
				return "date";
			}
			if(o.constructor == (new RegExp).constructor){
				return "regex";
			}
			return "object";
		}
		return typeof(o);
	},
	/**
	 * 用于将json对象转换成ztree需要的json数据格式
	 * @param {JSON} obj 任意json对象
	 * @param {Array} parentArray 父数组，如果不传值，则默认方法内部自己构建一个数组返回，如果有值则取该值添加转换后的json对象
	 * @return {JSON}
	 */
	convert4ZTree: function(obj, parentArray){
		var retValue = null;
	  	if(parentArray){
	  		retValue = parentArray;
	  	}else{
	  		retValue = new Array();
	  	}
	  	switch($.zTree.getRealType(obj)){
	      	case "object"://如果是对象，则遍历属性名和属性值，构造json返回
	      		$.each(obj, function(name, value){
	      			var json = {};
				  		json["name"] = name;//属性名
				  		json["open"] = true;
	  					json["children"] = $.zTree.convert4ZTree(value);
	  					retValue.push(json);
	      		});
	      		break;
	      	case "array"://如果是数组，则遍历数组元素，构造json返回    
	      		$.each(obj, function(i, data){
	      			if($.zTree.getRealType(data) == "object"){
	      				$.zTree.convert4ZTree(data, retValue);
	      			}else{
	      				var json = {};
					  	json["name"] = data;//属性名
	  					retValue.push(json);
	      			}
	      		});
	      		break;
	  	}
	  	return retValue;
	}
};
jQuery.fn.hoverDelay = function(options){
    var defaults = {
        hoverDuring: 300,
        outDuring: 200,
        hoverEvent: function(){
            $.noop();
        },
        outEvent: function(){
            $.noop();    
        }
    };
    var sets = $.extend(defaults,options || {});
    var hoverTimer, outTimer;
    return $(this).each(function(){
        $(this).hover(function(){
            clearTimeout(outTimer);
            hoverTimer = setTimeout(sets.hoverEvent, sets.hoverDuring);
        },function(){
            clearTimeout(hoverTimer);
            outTimer = setTimeout(sets.outEvent, sets.outDuring);
        });    
    });
}
jQuery.fn.defaultChars = function() {
   return $(this).each(function() {
        var $this = $(this);
        if ($this.val())
            return;
        var $defaultchars = $this.attr('defaultchars');
        var oldColor = $this.css('color');
        $this.val($defaultchars).css('color', '#999');
        $this.focusin(function () {
            if ($this.val() == $defaultchars)
                $this.val('').css('color', oldColor);
        }).focusout(function () {
            if ($this.val() == '')
                $this.val($defaultchars).css('color', '#999');
            ;
        });
    });
}

jQuery.fn.changeDefaultChars = function() {
	   return $(this).each(function() {
	        var $this = $(this);
	        
	        var $defaultchars = $this.attr('defaultchars');
	        var oldColor = $this.css('color');
	        $this.val($defaultchars).css('color', '#999');
	        $this.focusin(function () {
	        	if($this.val() == '请输入渠道名称' || $this.val() == '请输入渠道编号'){
	                $this.val('').css('color', oldColor);
	        	}
	        }).focusout(function () {
	        	if($this.val() == '请输入渠道名称' || $this.val() == '请输入渠道编号'){
	        		$this.val($defaultchars);
	        		$this.val($defaultchars).css('color', '#999');
	        	}
	        });
	    });
	}

$.fn.textMagnifier = function(options) {
	var options = $.extend({
		splitType:[3,4,4],
		explainText:"请输入13位手机号码",
		delimiter:" ",
		align:"bottom",
		width:253
	},options);
	
	var self = this;

	self.bind("focusin keyup", function(){
		if($(this).val().indexOf("1")==0){
			creat()
		}else{
			hide();
		};	
	});

	self.bind("focusout", function(){
		hide();
	});


	var _temp = '<div class="Rose-zoomTips" >'+
       '<div class="Rose-zoomTips-cnt" rel="con"></div>'+
       '<div class="Rose-zoomTips-msg" rel="text"></div>'+
    '</div>',_dom;


	var creat = function(){
		if (!_dom ) {
			_dom = $(_temp);
			$(document.body).append($(_dom));
			setPosition();
		}else{
			setContent();
		}
	}

	var setContent = function(){
		var _val = getSplitText(self.val()),_text = options.explainText;
		_dom.find("[rel='con']").html(_val).end().find("[rel='text']").html(_text);	

		_val.length > 0 ? _dom.show() : null; 
		if(options.explainText==""){_dom.find("[rel='text']").remove()}
	}

	var getSplitText=function(str){
        var output=[],start=0,st=options.splitType;
        for(var i=0 ,len = st.length; i<len ;i++){
            var s=str.substr(start,st[i]);
            if(s.length > 0){
                output.push(s);
            }
            start+=st[i];
        }
        return output.join(options.delimiter);
    };

	var setPosition = function(){
		var y = self.outerHeight();
        var t = self.offset().top;
        var l = self.offset().left;
        $(_dom).css({
            "top": (t+y) + "px",
            "left":l + "px"
        }); 
	}

	var hide = function(){
		 if(_dom){
            _dom.hide();
        }	
	}
}



$.extend({
    getUrlVars: function () {
        var vars = [], hash;
        var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
        for (var i = 0; i < hashes.length; i++) {
            hash = hashes[i].split('=');
            vars.push(hash[0]);
            vars[hash[0]] = hash[1];
        }
        return vars;
    },
    getUrlVar: function (name) {
        return $.getUrlVars()[name];
    },

    fillTpl:  function(showId,tplId,json){
    	var tplList = $("#"+tplId).val();
    	var tplTemplate = Handlebars.compile(tplList); // 应用模板
    	var tplHtml = tplTemplate(json); // 填充数据
    	var np=$("#"+showId).html(tplHtml);
    },
    getCurrentDate: function(format){
    	if(format==''||format==undefined){
    		format = "yyyy-MM-dd";
    	}
    	Date.prototype.format = function(fmt) 
    	{ //author: meizz 
    	  var o = { 
    	    "M+" : this.getMonth()+1,                 //月份 
    	    "d+" : this.getDate(),                    //日 
    	    "h+" : this.getHours(),                   //小时 
    	    "m+" : this.getMinutes(),                 //分 
    	    "s+" : this.getSeconds(),                 //秒 
    	    "q+" : Math.floor((this.getMonth()+3)/3), //季度 
    	    "S"  : this.getMilliseconds()             //毫秒 
    	  }; 
    	  if(/(y+)/.test(fmt)) 
    	    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
    	  for(var k in o) 
    	    if(new RegExp("("+ k +")").test(fmt)) 
    	  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length))); 
    	  return fmt; 
    	}
    	return new Date().format(format);
    },
    getCurrentMonthFirst:function(format){
    	if(format==''||format==undefined){
    		format = "yyyy-MM-dd";
    	}
    	Date.prototype.format = function(fmt) 
    	{ //author: meizz 
    	  var o = { 
    	    "M+" : this.getMonth()+1,                 //月份 
    	    "d+" : this.getDate(),                    //日 
    	    "h+" : this.getHours(),                   //小时 
    	    "m+" : this.getMinutes(),                 //分 
    	    "s+" : this.getSeconds(),                 //秒 
    	    "q+" : Math.floor((this.getMonth()+3)/3), //季度 
    	    "S"  : this.getMilliseconds()             //毫秒 
    	  }; 
    	  if(/(y+)/.test(fmt)) 
    	    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
    	  for(var k in o) 
    	    if(new RegExp("("+ k +")").test(fmt)) 
    	  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length))); 
    	  return fmt; 
    	}
    	var date=new Date();
    	date.setDate(1);

    	return date.format(format);
    },
    getCurrentMonthEnd:function(format){
    	if(format==''||format==undefined){
    		format = "yyyy-MM-dd";
    	}
    	Date.prototype.format = function(fmt) 
    	{ //author: meizz 
    		var o = { 
    				"M+" : this.getMonth()+1,                 //月份 
    				"d+" : this.getDate(),                    //日 
    				"h+" : this.getHours(),                   //小时 
    				"m+" : this.getMinutes(),                 //分 
    				"s+" : this.getSeconds(),                 //秒 
    				"q+" : Math.floor((this.getMonth()+3)/3), //季度 
    				"S"  : this.getMilliseconds()             //毫秒 
    		}; 
    		if(/(y+)/.test(fmt)) 
    			fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
    		for(var k in o) 
    			if(new RegExp("("+ k +")").test(fmt)) 
    				fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length))); 
    		return fmt; 
    	}
    	
    	var endDate = new Date();

    	endDate.setMonth(endDate.getMonth()+1);

    	endDate.setDate(0);


    	
    	return endDate.format(format);
    },
    
   getMonthBetween: function(startDate,endDate){
	// 拆分年月日
	   startDate = startDate.split('-');
	   // 得到月数
	   startDate = parseInt(startDate[0]) * 12 + parseInt(startDate[1]);
	   // 拆分年月日
	   endDate = endDate.split('-');
	   // 得到月数
	   endDate = parseInt(endDate[0]) * 12 + parseInt(endDate[1]);
	   var m = Math.abs(startDate - endDate);
	   
       return m; 
    },
    
    //判断浏览器
    getExplorer:function() {
    	var explorer = window.navigator.userAgent;
    	//ie 
    	if (explorer.indexOf("MSIE") >= 0) {
    	    return "IE";
    	}
    	//firefox 
    	else if (explorer.indexOf("Firefox") >= 0) {
    		return "FIREFOX";
    	}
    	//Chrome
    	else if(explorer.indexOf("Chrome") >= 0){
    		return "CHROME";
    	}
    	//Opera
    	else if(explorer.indexOf("Opera") >= 0){
    		return "OPERA";
    	}
    	//Safari
    	else if(explorer.indexOf("Safari") >= 0){
    		return "SAFARI";
    	}else {
    		return "IE";
    	}
    
    }

});

$.fn.extend({
    "NiceSelect":function(options){
        var _self = this;
        options = $.extend({
            url:"business?service=ajax&page=Common&listener=getStaticData",
            datas:"codeType=test",
            id:"testId",
            name:"testId",
            key :"CODE_VALUE",
            value:"CODE_NAME",
            defaultValue:"",
            all:false, //是否显示"所有",值是"" 。 默认显示"请选择"，值是"" 
            allVal:"",
            handler:function(){ //onchange事件
                //do...
            }, 
            callback:function(){ //回调事件
                //do...
            }
        },options);
        sendAjax();
        function sendAjax(){
            $.PostJson(options.url, options.datas , ajaxCallback);
        }
        function errorAjax(){
            var $a = $('<a href="javascript:;">重新加载数据</a>')
            .bind("click",function(){
                sendAjax();
            });
            _self.html($a);
        }
        function ajaxCallback(state,json){
            //判断状态,是否成功
            if(state=="success"){
                //判断状态,是否成功
                if(json.returnCode=="0"){
                    var ops = '<select class="element text" id="'+options.id+'" name="'+options.name+'" >';
                    if(json.beans.length!=1){
                        if( (typeof options.all=="boolean")&&(options.all.constructor==Boolean) ){
                            if(options.all){
                                ops += '<option value="">所有</option>'; 
                            }else{
                                ops += '<option value="">所有</option>';
//                                   ops += '';
                            }
                        }else{
                            if(options.all!=""){
                                ops += '<option value="'+options.allVal+'">'+options.all+'</option>';
                            }else{
                                ops += '';
                            }
                        }
                    }
                    for(var i=0;i<json.beans.length;i++){
                    	//添加设置默认值
                    	var sel = "";
                    	if(options.defaultValue){
                    		sel = (json.beans[i][options.key]==options.defaultValue) ? "selected='selected'" :"" ;
                    	}
                    	ops += '<option value="'+json.beans[i][options.key]+'" '+sel+' >'+json.beans[i][options.value]+'</option>';
                    }
                    ops += '</select>';
                    _self.html( $(ops).bind("change",options.handler) );
                    
                    //触发回调函数
                    if (typeof options.callback == 'function') {
                    	options.callback.call(_self.find("select")[0]);
                    }
                    
                	if(options.muti){
                		
                	}else{
                        //把下拉框变成可以输入的下拉框
                        _self.find("select").combobox();
                	}
                   
                    
                }else{
                    errorAjax();
                }
            }else{
                errorAjax();
            }
        }
        return this;
    }
});