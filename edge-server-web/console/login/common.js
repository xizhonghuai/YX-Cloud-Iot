/**
 * 
 */

// 日期格式化
Date.prototype.format = function(format) {
	var o = {
		"M+" : this.getMonth() + 1, // month
		"d+" : this.getDate(), // day
		"h+" : this.getHours(), // hour
		"m+" : this.getMinutes(), // minute
		"s+" : this.getSeconds(), // second
		"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
		"S" : this.getMilliseconds()
	// millisecond
	}
	if (/(y+)/.test(format))
		format = format.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(format))
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
					: ("00" + o[k]).substr(("" + o[k]).length));
	return format;
}

function DateFormat(tm) {
	return new Date(tm).format('yyyy-MM-dd hh:mm:ss');
}

function formatDate(tm, form) {
	return new Date(tm).format(form);
}

function checkTM(TM) {
	var subM = Math.abs((new Date().getTime() - new Date(TM).getTime())
			/ (60 * 60 * 1000));// 得到两个时间相差的小时数

	if (subM < 2) {
		return true;
	}
	return false;
}

// 数据请求
function GetData(Url, request) {
	var ret;
	$.ajax({
		url : Url,
		type : 'post',
		async : false,
		dataType : "json",
		data : request,
		success : function(data) {

			ret = data;
		},
		error : function() {
			ret = {
				"statusCode":500
			}
		}
	});

	return ret;
}

// 数据请求
function GetData(Url, request, retType, enType) {
	var ret;
	$.ajax({
		url : Url,
		type : 'post',
		timeout : 5000,
		async : false,
		contentType : enType, // 默认值: "application/x-www-form-urlencoded;
								// charset=UTF-8"
		dataType : retType,
		data : request,
		success : function(data) {
			ret = data;

		},
		error : function(XMLHttpRequest, textStatus) {
			ret = "错误：" + XMLHttpRequest.status + "              "
					+ XMLHttpRequest.statusText

		}
	});

	return ret;
}

// 获取url中的参数
function getUrlParam(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); // 构造一个含有目标参数的正则表达式对象
	var r = window.location.search.substr(1).match(reg); // 匹配目标参数
	if (r != null)
		return unescape(r[2]);
	return null; // 返回参数值
}

function getUserType() {

	var userType = 404;
	userType = GetData("../getUserType");
	
	if (userType == 404){
		 window.location.href = "sessionTimeout.html";
	} else return userType;
}

function urlEncode (str) {
	str = (str + '').toString();

	return encodeURIComponent(str).replace(/!/g, '%21').replace(/'/g, '%27').replace(/\(/g, '%28').
	replace(/\)/g, '%29').replace(/\*/g, '%2A').replace(/%20/g, '+');
}


function successMsg(msg) {

	toastr.options = {
		"closeButton": true,
		"debug": false,
		"progressBar": false,
		"positionClass": "toast-top-center",
		"onclick": null,
		"showDuration": "400",
		"hideDuration": "1000",
		"timeOut": "7000",
		"extendedTimeOut": "1000",
		"showEasing": "swing",
		"hideEasing": "linear",
		"showMethod": "fadeIn",
		"hideMethod": "fadeOut"
	};
	toastr.success(msg);

}


function warningMsg(msg) {

	toastr.options = {
		/*   "closeButton": true, */
		"debug": false,
		"progressBar": false,
		"positionClass": "toast-top-center",
		"onclick": null,
		"showDuration": "200",
		"hideDuration": "1000",
		"timeOut": "7000",
		"extendedTimeOut": "1000",
		"showEasing": "swing",
		"hideEasing": "linear",
		"showMethod": "fadeIn",
		"hideMethod": "fadeOut"
	};
	toastr.warning(msg);

}

function errorMsg(msg) {

	toastr.options = {
		"closeButton": true,
		"debug": false,
		"progressBar": false,
		"positionClass": "toast-top-center",
		"onclick": null,
		"showDuration": "400",
		"hideDuration": "1000",
		"timeOut": "7000",
		"extendedTimeOut": "1000",
		"showEasing": "swing",
		"hideEasing": "linear",
		"showMethod": "fadeIn",
		"hideMethod": "fadeOut"
	};
	toastr.error(msg);
}

function tips(msg,obj) {

	layer.tips(msg, obj,{
		tips: [1, '#3595CC'],
		time: 4000
	});

}