function getzz() {
	var a = $("ul#all li");
	var zz = new Array(a.length);
	for(var i = 0; i < a.length; i++) {
		zz[i] = a[i].innerHTML;
	} //div的字符串数组付给zz
	return zz;
}

function change(e) {
	pageno = e;
	if(e < 1) {
		e = 1;
		pageno = 1; //就等于第1页 ， 当前页为1
	}
	if(e > pageall) { //如果输入页大于最大页
		e = pageall;
		pageno = pageall; //输入页和当前页都=最大页
	}
	$("#all").html(""); //全部清空
	var html = "";
	for(var i = 0; i < pagesize; i++) {
		html += '<li>' + zz[(e - 1) * pagesize + i] + '</li>'; //创建一页的li列表
		if(zz[(e - 1) * pagesize + i + 1] == null) break; //超出最后的范围跳出
	}
	$("ul#all").html(html); //给ul列表写入html
	var ye = "";
	for(var j = 1; j <= pageall; j++) {
		if(e == j) {
			ye = ye + "<span><a href='#' class='active' onClick='change(" + j + ")'>" + j + "</a></span> "
		} else {
			ye = ye + "<a href='#' onClick='change(" + j + ")'>" + j + "</a> "
		}
	}
	var pageContent = "";
	pageContent += '<a href="#" onClick="change(--pageno)">上一页</a>';
	pageContent += '<span id="a1">' + ye + '</span>';
	pageContent += '<span id="a2">' + '第' + pageno + '/' + pageall + '页' + '</span>';
	pageContent += '<a href="#" onClick="change(++pageno)">下一页</a>';
	$("#page").html(pageContent);
}