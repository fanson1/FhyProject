/**
 * Created by Administrator on 2017/2/20 0015.
 */

function tabChangeEvent(obj){
	var title = obj.find(".sp-ly-title li");
	var con = obj.find(".sp-help-cont .sp-help-col");
  change(document.location.search.split("=")[2])
  $(title).click(function(){
    i=$(this).index();
    change(i);
  });

	function change(i){
    title.removeClass("default");
    con.removeClass("default");
    title.eq(i).addClass("default");
    con.eq(i).addClass("default");
	};

};


$(function(){
	tabChangeEvent($(".sp-ly-center"));
  var navArr=["help_shop","pay_ways","send_ways","afterSalesService","aboutUs"];
  $(".sp-ly-list a").each(function(i){
    $(this).attr("href","helpCenter?p="+navArr[i]+"&v=0")
  })
});
