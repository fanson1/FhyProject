webpackJsonp([3],{"81nG":function(t,i){},Irh6:function(t,i,e){t.exports=e.p+"static/img/default.9682ad0.png"},JbHv:function(t,i){},jzrW:function(t,i,e){"use strict";Object.defineProperty(i,"__esModule",{value:!0});var o=e("3cXf"),s=e.n(o),a=e("wY8d");function c(t){for(var i=t.offsetTop,e=t.offsetLeft;t.offsetParent;)i+=(t=t.offsetParent).offsetTop,e+=t.offsetLeft;return{left:e,top:i}}var r={name:"magnify",props:{previewImg:{type:String,default:""},zoomImg:{type:String,default:""}},data:function(){return{zoomVisiable:!1,hoverVisiable:!1}},methods:{out:function(){this.zoomVisiable=!1},move:function(t){this.init();var i=t.clientX,e=t.clientY,o=c(this.oPreviewBox).left,s=c(this.oPreviewBox).top,a=i-o-this.houverWidth/2,r=void 0;r=this.scroll>0?e-s+this.scroll-this.houverHeight/2:e-s-this.houverHeight/2;var n=this.pWidth-this.houverWidth,d=this.pWidth-this.houverHeight,l=(a=a<0?0:a>n?n:a)/n,m=(r=r<0?0:r>d?d:r)/d;this.oHoverBox.style.left=a+"px",this.oHoverBox.style.top=r+"px",this.oBigImg.style.left=l*(this.bWidth-this.imgWidth)+"px",this.oBigImg.style.top=m*(this.bHeight-this.imgHeight)+"px",this.$emit("move",t),this.zoomVisiable=!0},init:function(){this.oHoverBox=this.$refs.hoverBox,this.oPreviewBox=this.$refs.previewBox,this.oBigImg=this.$refs.bigImg,this.imgBox=this.$refs.zoomBox,this.houverWidth=this.oHoverBox.offsetWidth,this.houverHeight=this.oHoverBox.offsetHeight,this.pWidth=this.oPreviewBox.offsetWidth,this.pHeight=this.oPreviewBox.offsetHeight,this.imgWidth=this.oBigImg.offsetWidth,this.imgHeight=this.oBigImg.offsetHeight,this.bWidth=this.imgBox.offsetWidth,this.bHeight=this.imgBox.offsetHeight,this.scroll=document.documentElement.scrollTop||document.body.scrollTop}}},n={render:function(){var t=this,i=t.$createElement,e=t._self._c||i;return e("div",{staticClass:"magnify"},[e("div",{ref:"previewBox",staticClass:"preview-box",on:{mousemove:function(i){t.move(i)},mouseout:t.out}},[e("img",{attrs:{width:"100%",src:t.previewImg,alt:""}}),t._v(" "),e("div",{ref:"hoverBox",staticClass:"hover-box"})]),t._v(" "),e("div",{directives:[{name:"show",rawName:"v-show",value:t.zoomVisiable,expression:"zoomVisiable"}],ref:"zoomBox",staticClass:"zoom-box"},[e("img",{ref:"bigImg",staticStyle:{width:"1200px"},attrs:{src:t.zoomImg,alt:""}})])])},staticRenderFns:[]};var d={name:"Detail",components:{Magnify:e("Z0/y")(r,n,!1,function(t){e("81nG")},null,null).exports,Flyball:e("vYnV").a,Bar:a.a},data:function(){return{imageUrl:null,detail:null,listIndex:0,num:1,currentTabIndex:0,currentImage:null,commodityCode:null,tabs:[{name:"商品介绍"},{name:"售后保障"}]}},computed:{currentImagesList:function(){return this.detail&&this.detail.commodityGoodsSpecList?this.currentProduct.headerImageUrlList.slice(this.listIndex,this.listIndex+4):this.detail.headerImageUrlList.slice(this.listIndex,this.listIndex+4)},currentProduct:function(){var t=this;if(this.detail&&this.detail.commodityGoodsSpecList){var i=this.detail.commodityGoodsSpecList.find(function(i){return i.commodityCode===t.commodityCode});return i||(this.commodityCode=this.detail.commodityGoodsSpecList[0].commodityCode,this.detail.commodityGoodsSpecList[0])}if(this.detail&&!this.detail.commodityGoodsSpecList)return this.detail},loginStatus:function(){return this.$store.state.loginStatus}},watch:{$route:function(){this.getCommodityDetail()}},created:function(){this.getCommodityDetail(),window.scrollTo(0,0)},methods:{collect:function(){var t=this;this.currentProduct.focus?this.axios.get(this.api.removeCollection+"?commodityCode="+this.currentProduct.commodityCode).then(function(i){200===i.status&&i.data.code<300&&(t.currentProduct.focus=!1)}):this.axios.get(this.api.collect+"?commodityCode="+this.currentProduct.commodityCode).then(function(i){200===i.status&&i.data.code<300&&(t.currentProduct.focus=!0)})},preCreateOrder:function(t){var i=this.currentProduct,e={commodityCode:i.commodityCode,num:this.num,categoryCode:i.categoryCode,secKill:i.secKill,presell:i.presell};sessionStorage.setItem("toPayCommodityList",s()([e])),this.loginStatus?this.$router.push({name:"prePay"}):this.$router.push({name:"Login",query:{redirect:"prePay"}})},getCommodityDetail:function(){var t=this;this.imageUrl=null;var i={commodityCode:this.$route.params.commodityCode,introduceImageWidth:880};this.axios.post(this.api.getCommodityDetail,i).then(function(i){200===i.status&&200===i.data.code&&(i.data.result.commodityStock=Math.floor(i.data.result.commodityStock),i.data.result.commodityGoodsSpecList&&i.data.result.commodityGoodsSpecList.forEach(function(t){t.commodityStock=Math.floor(t.commodityStock)}),t.detail=i.data.result,t.commodityCode=t.$route.params.commodityCode,t.selectCurrentImage(t.detail.imageUrl))})},navigationTo:function(t){this.$router.push("/list?category="+t.categoryCode),console.log(t)},selectCurrentImage:function(t){this.imageUrl=t},setDisplayImages:function(){this.listIndex+4>=this.detail.headerImageUrlList.length?this.listIndex=0:this.listIndex+=4},selectProductType:function(t){this.commodityCode=t.commodityCode,this.num=1,this.selectCurrentImage(t.imageUrl)},setProductNum:function(){var t=this.num=parseInt(this.num.replace(/\D/g,""))||1;t>200&&this.currentProduct.commodityStock>200?this.num=200:t>200&&this.currentProduct.commodityStock<=200?this.num=this.currentProduct.commodityStock||1:t>this.currentProduct.commodityStock&&(this.num=this.currentProduct.commodityStock||1)},selectTab:function(t){this.currentTabIndex=t},add:function(){this.num++},reduce:function(){this.num--},addCart:function(t,i){var e=this;if(this.loginStatus){var o={commodityCode:i.commodityCode,num:this.num,secKill:!1};this.axios.post(this.api.save,o).then(function(i){200===i.status&&200===i.data.code?(e.$refs.flyball.drop(t),setTimeout(function(){e.$store.dispatch("getCartNum")},600)):e.$message({message:i.data.remark,type:"warning"})})}else this.$router.push({name:"Login",query:{redirect:"Detail",commodityCode:i.commodityCode}})},getProductDetail:function(t){this.$router.push({name:"Detail",params:{commodityCode:t.commodityCode}}),this.getCommodityDetail(),this.num=1,window.scrollTo(0,0)}}},l={render:function(){var t=this,i=t.$createElement,o=t._self._c||i;return o("div",{attrs:{id:"detail"}},[t.detail?o("div",{staticClass:"navigation f12"},[o("a",{attrs:{href:"/index"}},[t._v("首页")]),t._v(" > 商品详情\n  ")]):t._e(),t._v(" "),o("div",{staticClass:"product-wrapper"},[t.detail?o("div",{staticClass:"preview pr"},[o("Magnify",{attrs:{previewImg:t.imageUrl,zoomImg:t.imageUrl}}),t._v(" "),o("div",{staticClass:"images-wrapper"},[t.currentImagesList.length>0?o("ul",{staticClass:"image-list"},t._l(t.currentImagesList,function(i,e){return o("li",{key:e,staticClass:"image-wrapper",style:i===t.imageUrl||i!==t.imageUrl&&i.indexOf(t.imageUrl)>-1?"border: 2px solid #7BBE2B;":"border: 2px solid #FFF",on:{mouseenter:function(e){t.selectCurrentImage(i)}}},[o("img",{attrs:{src:i}})])})):t._e(),t._v(" "),t.detail.headerImageUrlList.length>4?o("i",{staticClass:"iconfont icon-few",on:{click:t.setDisplayImages}}):t._e(),t._v(" "),3===t.currentProduct.commodityStatus&&0===t.currentProduct.commodityStock?o("div",{staticClass:"icon-no-stock"},[t._v("已售罄"),o("br"),t._v("拼命补货中")]):t._e()])],1):o("img",{staticStyle:{width:"430px"},attrs:{src:e("Irh6")}}),t._v(" "),o("div",{staticClass:"product-detail"},[t.detail?o("h1",[t.currentProduct.presell?o("span",[t._v("【预售】")]):t._e(),t._v("\n        "+t._s(t.currentProduct.commodityName)+"\n      ")]):t._e(),t._v(" "),t.detail?o("div",{staticClass:"product-comment"},[t._v(t._s(t.currentProduct.commodityComment))]):t._e(),t._v(" "),t.detail&&t.detail.tags?o("div",{staticClass:"tags-wrapper"},t._l(t.currentProduct.tags,function(i,e){return o("span",{staticClass:"tag"},[t._v(t._s(i))])})):t._e(),t._v(" "),o("div",{staticClass:"old-price"},[o("span",{staticClass:"label"},[t._v("价格：")]),t._v(" "),t.detail?o("span",{staticClass:"value"},[t._v("¥ "+t._s(t.currentProduct.commodityOriginalPrice.toFixed(2)))]):t._e()]),t._v(" "),o("div",{staticClass:"sale-price"},[o("span",{staticClass:"label"},[t._v("折扣价：")]),t._v(" "),t.detail?o("span",{staticClass:"value"},[t._v("¥ "+t._s(t.currentProduct.commoditySalesPrice.toFixed(2)))]):t._e()]),t._v(" "),o("div",{staticClass:"number-wrapper"},[o("span",{staticClass:"label"},[t._v("数量：")]),t._v(" "),t.detail?o("span",[t.num>1?o("button",{staticClass:"reduce-button",on:{click:t.reduce}},[t._v("-")]):o("button",{staticClass:"reduce-button"},[t._v("-")]),t._v(" "),o("input",{directives:[{name:"model",rawName:"v-model.trim",value:t.num,expression:"num",modifiers:{trim:!0}}],staticClass:"number",attrs:{type:"text"},domProps:{value:t.num},on:{change:t.setProductNum,input:function(i){i.target.composing||(t.num=i.target.value.trim())},blur:function(i){t.$forceUpdate()}}}),t._v(" "),t.num<200&&t.num<t.currentProduct.commodityStock?o("button",{staticClass:"add-button",on:{click:t.add}},[t._v("+")]):o("button",{staticClass:"add-button"},[t._v("+")]),t._v(" "),o("span",{staticClass:"commodity-stock"},[t._v("库存"+t._s(t.currentProduct.commodityStock)+"件")]),t._v(" "),200===t.num?o("span",{staticClass:"commodity-stock"},[t._v("最多只能购买200件")]):t._e()]):t._e()]),t._v(" "),o("div",{staticClass:"type-wrapper"},[o("span",{staticClass:"label"},[t._v("选择种类：")]),t._v(" "),t.detail?o("span",{staticStyle:{display:"inline-block",width:"600px","vertical-align":"top"}},t._l(t.detail.commodityGoodsSpecList,function(i,e){return o("span",{staticClass:"type",class:t.commodityCode===i.commodityCode?"selected":"",staticStyle:{"margin-bottom":"10px"},on:{click:function(e){t.selectProductType(i)}}},[t._v("\n            "+t._s(i.commoditySpecification)+"\n            "),o("i",{directives:[{name:"show",rawName:"v-show",value:t.commodityCode===i.commodityCode,expression:"commodityCode === item.commodityCode"}],staticClass:"iconfont icon-chenggong"})])})):t._e()]),t._v(" "),t.currentProduct&&3===t.currentProduct.commodityStatus&&t.currentProduct.commodityStock>0?o("div",{staticClass:"button-wrapper"},[o("button",{staticClass:"buy-now",on:{click:function(i){t.preCreateOrder()}}},[t._v("立即购买")]),t._v(" "),o("button",{staticClass:"add-cart",on:{click:function(i){t.addCart(i,t.currentProduct)}}},[t._v("加入购物车")])]):t._e(),t._v(" "),o("div",{staticClass:"collection",class:t.currentProduct&&t.currentProduct.focus?"collected":"",on:{click:t.collect}},[o("i",{staticClass:"iconfont icon-guanzhu-tianchong"}),t._v(" "),t.currentProduct&&t.currentProduct.focus?o("span",[t._v("取消关注")]):o("span",[t._v("关注商品")])])])]),t._v(" "),o("h1",{staticClass:"like-header"},[t._v("猜你喜欢")]),t._v(" "),t.detail?o("div",{staticClass:"like-wrapper"},t._l(t.detail.guessYouLikeCommodityList,function(i,e){return e<5?o("div",{key:e,staticClass:"recommend-commodity",staticStyle:{position:"relative"},on:{click:function(e){t.getProductDetail(i)}}},[i.presell?[o("i",{staticClass:"iconfont icon-yushou"}),t._v(" "),o("div",{staticClass:"trangle"})]:t._e(),t._v(" "),o("img",{attrs:{src:i.imageUrl}}),t._v(" "),o("div",{staticClass:"commodity-name"},[t._v(t._s(i.commodityName))]),t._v(" "),o("div",{staticClass:"commodity-price"},[t._v("¥ "+t._s(i.commoditySalesPrice.toFixed(2)))]),t._v(" "),o("div",{staticClass:"add-cart",on:{click:function(e){e.stopPropagation(),t.addCart(e,i)}}},[t._v("加入购物车")])],2):t._e()})):t._e(),t._v(" "),o("div",{staticClass:"product-introduction"},[o("div",{staticClass:"tabs-wrapper"},t._l(t.tabs,function(i,e){return o("span",{staticClass:"tab",class:t.currentTabIndex===e?"active":"",on:{click:function(i){t.selectTab(e)}}},[t._v("\n        "+t._s(i.name)+"\n        "),t.detail&&2===e?o("span",[t._v("("+t._s(t.detail.countComment)+")")]):t._e()])})),t._v(" "),o("div",{directives:[{name:"show",rawName:"v-show",value:0===t.currentTabIndex,expression:"currentTabIndex === 0"}]},[t.detail?o("div",{staticClass:"props-wrapper"},t._l(t.currentProduct.commodityProperties,function(i,e){return o("span",{staticClass:"prop"},[t._v("\n          "+t._s(i.commodityPropertyName)+"："+t._s(i.commodityPropertyValue)+"\n        ")])})):t._e(),t._v(" "),o("div",{staticStyle:{overflow:"hidden"}},[t.detail?o("div",{staticStyle:{float:"left",width:"880px"}},t._l(t.currentProduct.introduceImageList,function(t,i){return o("img",{key:i,staticStyle:{width:"880px"},attrs:{src:t}})})):t._e(),t._v(" "),o("div",{staticStyle:{float:"right"}},[o("h1",{staticClass:"recommend-header",staticStyle:{"text-indent":"30px"}},[t._v("推荐商品")]),t._v(" "),t.detail?o("div",{staticClass:"recommend-wrapper"},t._l(t.currentProduct.recommendCommodityList,function(i,e){return e<5?o("div",{key:e,staticClass:"recommend-commodity",staticStyle:{position:"relative"},on:{click:function(e){t.getProductDetail(i)}}},[i.presell?[o("i",{staticClass:"iconfont icon-yushou"}),t._v(" "),o("div",{staticClass:"trangle"})]:t._e(),t._v(" "),o("img",{attrs:{src:i.imageUrl}}),t._v(" "),o("div",{staticClass:"commodity-name"},[t._v(t._s(i.commodityName))]),t._v(" "),o("div",{staticClass:"commodity-price"},[t._v("¥ "+t._s(i.commoditySalesPrice.toFixed(2)))]),t._v(" "),o("div",{staticClass:"add-cart",on:{click:function(e){e.stopPropagation(),t.addCart(e,i)}}},[t._v("加入购物车")])],2):t._e()})):t._e()])])]),t._v(" "),t.detail&&1===t.currentTabIndex?o("dl",{staticClass:"custom-service"},[o("dt",[t._v("生鲜换货渠道")]),t._v(" "),o("dd",[t._v("生鲜类商品（包括蔬菜水果、鲜蛋肉禽、海鲜水产等）和部分特殊商品（商品说明中会提示）时效性较强，为保证商品品质，请在配送完成后及时取货，并在现场取货时第一时间验收商品，如出现品质或数量缺失问题，可立马向工作人员反映，我们将及时为您安排换货或补发商品，一旦签收，我们将默认商品质量和数量均无问题，此后不再接受换货要求。")]),t._v(" "),o("dt",[t._v("专业生鲜客服团队—让您售后无忧")]),t._v(" "),o("dd",[t._v("银犁食品客服电话：400-1111-028")]),t._v(" "),o("dd",[t._v("服务时间：09:00-18:00")]),t._v(" "),o("dt",[t._v("银犁食品售后政策")]),t._v(" "),o("dd",[t._v("注：图片及信息仅供参照，商品将以实物为准。因拍摄灯光及不同显示器色差等问题可能造成商品图片与实物有一定色差，不属质量问题。如因客户个人原因造成商品出现质量问题的，恕不换货。")]),t._v(" "),o("dt",[t._v("权责声明：")]),t._v(" "),o("dd",[t._v("银犁食品上的所有商品信息、客户评价等内容，是银犁食品重要的经营资源，未经许可，禁止非法转载使用。")]),t._v(" "),o("dt",[t._v("价格说明：")]),t._v(" "),o("dd",[t._v("价格：商品的银犁食品销售价，是您最终购买商品的价格依据。")]),t._v(" "),o("dd",[t._v("市场价：商品在各大超市和菜市场的平均参考价，该价格可能是品牌专柜标价、商品吊牌价或由品牌供应商提供的正品零售价（如厂商指导价、建议零售价等）；由于地区、时间的差异性和市场行情波动，超市价或菜市场的价格等可能会与您购物时展示的不一致，该价格仅供您参考。")])]):t._e()]),t._v(" "),o("Flyball",{ref:"flyball"}),t._v(" "),o("Bar")],1)},staticRenderFns:[]};var m=e("Z0/y")(d,l,!1,function(t){e("JbHv")},null,null);i.default=m.exports}});
//# sourceMappingURL=3.549247e8df83e586a4d0.js.map