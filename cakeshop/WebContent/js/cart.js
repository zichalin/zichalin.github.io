
/**
 * 加入购物车
 */
function buy(goodid){
	$.post("goods_buy", {goodsid:goodid}, function(data){
		if(data=="ok"){
			layer.msg("添加到购物车成功!", {time:800}, function(){
				location.reload();
			});
		}else if(data=="fail"){
			layer.msg("库存不足，请购买其他商品!", {time:800}, function(){
				
			});
		}
//		if(data=="ok"){
//			layer.msg("添加到购物车成功!", {time:800}, function(){
//				location.reload();
//			});
////		}else if(data=="login"){
////			alert("请登录后购买!");
////			location.href="login.jsp";
//		}else if(data=="fail"){
//			alert("库存不足,请购买其他商品!");
//		}else{
//			alert("请求失败!");
//		}
	});
}
/**
 * 购物车减去
 */
function lessen(goodid){
	$.post("goods_lessen", {goodsid:goodid}, function(data){
		if(data=="ok"){
			layer.msg("从购物车移除商品操作成功!", {time:800}, function(){
				location.reload();
			});
		}
//		else if(data=="login"){
//			alert("请登录后操作!");
//			location.href="login.jsp";
//		}else{
//			alert("请求失败!");
//		}
	});
}
/**
 * 购物车删除
 */
function deletes(goodid){
	$.post("delete.action", {goodid:goodid}, function(data){
		if(data=="ok"){
			layer.msg("删除成功!", {time:800}, function(){
				location.href="cart.action";
			});
		}else if(data=="login"){
			alert("请登录后操作!");
			location.href="login.jsp";
		}else{
			alert("请求失败!");
		}
	});
}