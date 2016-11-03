function b() {
	h = $(window).height();
	t = $(document).scrollTop();
	if (t > h) {
		$('#gotop').show();
	} else {
		$('#gotop').hide();
	}
}
$(document).ready(function(e) {
	b();
	$('#gotop').click(function() {
		$(document).scrollTop(0);
	})
});

$(window).scroll(function(e) {
	b();
})

function submitContent() {
	var info = $("#info").val();
	$.post("XiaoLB/sendMessage.do", {
		info : info
	}, function(result) {
		var result = eval("(" + result + ")");
		var str = '';
		if (result.text) {
			$("#reply").html(str += result.text);
		}
		if (result.url) {
			$("#reply").html(
					str + "<a style='text-decoration:underline;color:blue' href='" + result.url
							+ "' target='_blank'>点击查看</a>");
		}
		if (result.list) {
			for (var i = 0; i < result.list.length; i++) {
				if(result.list[i].article==''){
					$("#reply").html(
							str += "<br><a style='text-decoration:underline;color:blue' href='" + result.list[i].detailurl
							+ "' target='_blank'>" + result.list[i].source
							+ "</a>");
				}else{
					$("#reply").html(
							str += "<br><a style='text-decoration:underline;color:blue' href='" + result.list[i].detailurl
							+ "' target='_blank'>" + result.list[i].article
							+ "</a>");
				}
			}
		}
	});
}

/*绑定事件*/
function addEvent(obj, sType, fn) {
	if (obj.addEventListener) {
		obj.addEventListener(sType, fn, false);
	} else {
		obj.attachEvent('on' + sType, fn);
	}
};
function removeEvent(obj, sType, fn) {
	if (obj.removeEventListener) {
		obj.removeEventListener(sType, fn, false);
	} else {
		obj.detachEvent('on' + sType, fn);
	}
};
function prEvent(ev) {
	var oEvent = ev || window.event;
	if (oEvent.preventDefault) {
		oEvent.preventDefault();
	}
	return oEvent;
}

/*页面载入后*/
window.onload = function() {
	var oImg = document.getElementById('oImg');
	var dialog = document.getElementById('dialog');
	/*拖拽功能*/
	(function() {
		addEvent(
				oImg,
				'mousedown',
				function(ev) {
					var dialog = document.getElementById('dialog');
					var oEvent = prEvent(ev), oParent = oImg.parentNode, disX = oEvent.clientX
							- oImg.offsetLeft, disY = oEvent.clientY
							- oImg.offsetTop, startMove = function(ev) {
						if (oParent.setCapture) {
							oParent.setCapture();
						}
						var oEvent = ev || window.event, l = oEvent.clientX
								- disX, t = oEvent.clientY - disY;
						oImg.style.left = l + 'px';
						oImg.style.top = t + 'px';
						dialog.style.left = -190 + l + 'px';
						dialog.style.top = t + 'px';
						oParent.onselectstart = function() {
							return false;
						}
					}, endMove = function(ev) {
						if (oParent.releaseCapture) {
							oParent.releaseCapture();
						}
						oParent.onselectstart = null;
						removeEvent(oParent, 'mousemove', startMove);
						removeEvent(oParent, 'mouseup', endMove);
					};
					addEvent(oParent, 'mousemove', startMove);
					addEvent(oParent, 'mouseup', endMove);
					return false;
				});

	})();
};
