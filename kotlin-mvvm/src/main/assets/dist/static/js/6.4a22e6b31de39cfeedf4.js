webpackJsonp([6],{"6nYc":function(t,e,s){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var o=s("wY8d"),a=s("C80W"),i={name:"Pages",props:["pageNumC","pageSizeC","totalC"],data:function(){return{pageNum:this.pageNumC,pageSize:this.pageSizeC,total:this.totalC,pageNums:[],pageSteps:5}},computed:{maxPage:function(){return Math.ceil(this.total/this.pageSize)}},watch:{totalC:function(t){this.total=t,this.setNum(this.pageNum)},pageNumC:function(t){this.pageNum=t}},mounted:function(){this.setNum(this.pageNum)},methods:{prev:function(){if(1==this.pageNum)return!1;this.pageNum>1&&this.pageNum--,this.setNum(this.pageNum),this.pageDone()},next:function(){if(this.pageNum==this.maxPage)return!1;this.pageNum<this.maxPage&&this.pageNum++,this.setNum(this.pageNum),this.pageDone()},pageGo:function(t){this.pageNum=t,this.setNum(this.pageNum),this.pageDone()},setNum:function(t){var e=[],s=Math.floor(this.pageSteps/2),o=this.maxPage,a=t-s,i=t+s;a<1&&(i+=1-a,a=1),i>o&&(a-=i-o,i=o),a<1&&(a=1);for(var r=a;r<=i;r++)e.push(r);this.pageNums=e},pageDone:function(){this.$emit("pageDone",{pageNum:this.pageNum})}}},r={render:function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("div",{staticClass:"pages"},[s("i",{staticClass:"prev",class:{disabled:1==t.pageNum},on:{click:function(e){t.pageGo(1)}}},[t._v("首页")]),t._v(" "),s("i",{staticClass:"prev",class:{disabled:1==t.pageNum},on:{click:function(e){t.prev()}}},[t._v("上一页")]),t._v(" "),t._l(t.pageNums,function(e,o){return s("i",{class:{hover:t.pageNum==e},on:{click:function(s){t.pageGo(e)}}},[t._v(t._s(e))])}),t._v(" "),s("i",{staticClass:"next",class:{disabled:t.pageNum==t.maxPage},on:{click:function(e){t.next()}}},[t._v("下一页")]),t._v(" "),s("i",{staticClass:"prev",class:{disabled:t.pageNum==t.maxPage},on:{click:function(e){t.pageGo(t.maxPage)}}},[t._v("末页")]),t._v(" "),s("em",[t._v("共"+t._s(t.total)+"条")])],2)},staticRenderFns:[]};var c=s("Z0/y")(i,r,!1,function(t){s("x4hD")},"data-v-15335759",null).exports,n=s("vYnV"),l={name:"List",components:{noFile:a.a,Pages:c,Flyball:n.a,Bar:o.a},data:function(){return{classShow:!1,category:"",commodityList:void 0,thirdCategoryList:null,pagesCode:{},classHeight:0,prvData:"",sessionCache:"",uriParams:null}},computed:{crumb:function(){return decodeURIComponent(decodeURIComponent(this.$route.query.crumb))},crumbHover:function(){return this.crumb.split(">")},loginStatus:function(){return this.$store.state.loginStatus}},created:function(){},mounted:function(){!sessionStorage.getItem("sessionCache")&&sessionStorage.setItem("sessionCache",this.$route.fullPath),this.getCommodityList(),this.getThirdCategoryList(),this.setShowClass()},watch:{$route:function(t){this.getCommodityList(),this.getThirdCategoryList(),this.setShowClass();var e=t.query.prev;this.prvData!=e&&(this.classShow=!1,this.prvData=e)}},methods:{collect:function(t,e){!0===t.focus?this.axios.get(this.api.removeCollection+"?commodityCode="+e).then(function(e){200===e.status&&e.data.code<300&&(t.focus=!1)}):this.axios.get(this.api.collect+"?commodityCode="+e).then(function(e){200===e.status&&e.data.code<300&&(t.focus=!0)})},listSort:function(){this.getCommodityList()},addCart:function(t,e){var s=this;if(this.loginStatus){var o={commodityCode:e.commodityCode,num:1,secKill:!1};this.axios.post(this.api.save,o).then(function(e){200===e.status&&200===e.data.code?(s.$refs.flyball.drop(t),setTimeout(function(){s.$store.dispatch("getCartNum")},600)):s.$message({message:e.data.remark,type:"warning"})})}else this.$router.push({name:"Login"})},setShowClass:function(){var t=this;this.$nextTick(function(){var e=document.querySelector(".class-es");if(!e)return!1;t.classHeight=e.scrollHeight})},goList:function(t){var e=this.$pub.toParams(this.$route.fullPath)||{},s=this.crumb.split(">");t?(e.trdCategory=t.categoryCode,s.splice(2,1,t.categoryName)):(s.splice(2,1),delete e.trdCategory);var o=encodeURIComponent(encodeURIComponent(s.join(">")));e.crumb=o;var a=this.$pub.toQuery(e);this.$router.push("/list?"+a)},getThirdCategoryList:function(){var t=this.$route.query.parent,e=this.$route.query.prev,s=this;JSON.parse(localStorage.localStorageMainCategory||"[]").forEach(function(o){o.categoryCode==t&&o.secondCategoryList.forEach(function(t){t.categoryCode==e&&(s.thirdCategoryList=t.thirdCategoryList||[])})})},isShow:function(t){this.classShow=!t},getCommodityList:function(t){var e=this;this.$route.query.keyword?this.pagesCode.keyword=decodeURIComponent(this.$route.query.keyword):this.pagesCode.keyword="",this.pagesCode.category=this.$route.query.trdCategory||this.$route.query.category,this.$route.query.keyword?this.pagesCode.sort=this.pagesCode.sort||"C":this.pagesCode.orderby=this.pagesCode.orderby||"C",this.pagesCode.pageNum=t&&t.pageNum||1,this.pagesCode.pageSize=this.pagesCode.pageSize||20,this.pagesCode.width=200,this.pagesCode.height=200,(this.$route.query.keyword?this.axios.get(this.api.searchlist+"?"+this.$pub.toQuery(this.pagesCode)):this.axios.post(this.api.getCommodityList,this.pagesCode)).then(function(t){200===t.status&&200===t.data.code&&(window.scrollTo(0,0),e.commodityList=t.data.result&&t.data.result.pageeCommodity||null,e.pagesCode.total=e.commodityList&&e.commodityList.total)})},getPageDone:function(t){this.getCommodityList(t)}}},u={render:function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("article",{staticClass:"main-article"},[s("div",{staticClass:"navigation"},[t.$route.query.keyword?s("a",{staticClass:"f12 lh300"},[t._v("查询结果")]):s("p",t._l(t.crumbHover,function(e,o){return s("i",{staticClass:"f12 lh300"},[t._v(" "+t._s(o>0?">":"")+" "),s("a",{class:{hover:t.crumbHover.length>2},on:{click:function(e){1==o&&t.goList()}}},[t._v(t._s(e))])])}))]),t._v(" "),t.$route.query.keyword?t._e():s("div",{staticClass:"class-box pr"},[s("ul",{class:{show:t.classShow}},[t._m(0),t._v(" "),s("li",{staticClass:"class-es"},t._l(t.thirdCategoryList,function(e,o){return s("a",{class:{"c-green":e.categoryCode==t.$route.query.trdCategory},attrs:{href:"javascript:"},on:{click:function(s){t.goList(e)}}},[t._v(t._s(e.categoryName))])}))]),t._v(" "),t.classHeight>47?s("i",{staticClass:"show-icon pa f12 cursor ",on:{click:function(e){t.isShow(t.classShow),t.prvData=t.$route.query.prev}}},[t._v(t._s(t.classShow?"收起":"展开"))]):t._e()]),t._v(" "),s("div",{staticClass:"select-box mt20"},[t.$route.query.keyword?s("div",{staticClass:"select-icon"},[s("span",{class:{hover:"C"==t.pagesCode.sort},on:{click:function(e){t.listSort(t.pagesCode.sort="C")}}},[t._v("综合")]),t._v(" "),s("span",{class:{hover:"a"==t.pagesCode.sort||"A"==t.pagesCode.sort},on:{click:function(e){t.listSort("a"==t.pagesCode.sort?t.pagesCode.sort="A":t.pagesCode.sort="a")}}},[t._v("销量"+t._s("a"==t.pagesCode.sort?"↑":"↓"))]),t._v(" "),s("span",{class:{hover:"b"==t.pagesCode.sort||"B"==t.pagesCode.sort},on:{click:function(e){t.listSort("b"==t.pagesCode.sort?t.pagesCode.sort="B":t.pagesCode.sort="b")}}},[t._v("价格"+t._s("b"==t.pagesCode.sort?"↑":"↓"))])]):s("div",{staticClass:"select-icon"},[s("span",{class:{hover:"C"==t.pagesCode.orderby},on:{click:function(e){t.listSort(t.pagesCode.orderby="C")}}},[t._v("综合")]),t._v(" "),s("span",{class:{hover:"a"==t.pagesCode.orderby||"A"==t.pagesCode.orderby},on:{click:function(e){t.listSort("a"==t.pagesCode.orderby?t.pagesCode.orderby="A":t.pagesCode.orderby="a")}}},[t._v("销量"+t._s("a"==t.pagesCode.orderby?"↑":"↓"))]),t._v(" "),s("span",{class:{hover:"b"==t.pagesCode.orderby||"B"==t.pagesCode.orderby},on:{click:function(e){t.listSort("b"==t.pagesCode.orderby?t.pagesCode.orderby="B":t.pagesCode.orderby="b")}}},[t._v("价格"+t._s("b"==t.pagesCode.orderby?"↑":"↓"))])])]),t._v(" "),s("div",{staticClass:"list-content-box"},[t.commodityList?s("ul",[t._l(t.commodityList.results,function(e,o){return s("li",{staticClass:"pr",staticStyle:{position:"relative"},on:{click:function(s){s.stopPropagation(),t.$router.push({name:"Detail",params:{commodityCode:e.commodityCode}})}}},[e.presell?[s("i",{staticClass:"iconfont icon-yushou"}),t._v(" "),s("div",{staticClass:"trangle"})]:t._e(),t._v(" "),s("img",{attrs:{src:e.imageUrl,alt:""}}),t._v(" "),3===e.commodityStatus&&0===e.commodityStock?s("span",{staticClass:"icon-no-stock"},[t._v("已售罄"),s("br"),t._v("拼命补货中")]):t._e(),t._v(" "),s("div",{staticClass:"list-content-text"},[s("p",[s("span",[s("i",{staticClass:"price b c-red f16"},[t._v(t._s(t._f("toF")(e.commoditySalesPrice)))]),t._v(" "),s("i",{staticClass:"del-tag price f12"},[t._v(t._s(t._f("toF")(e.commodityOriginalPrice)))])]),t._v(" "),s("span",[s("i",{staticClass:"f999 f12 fr"},[t._v(t._s(e.praise)+"%")])])]),t._v(" "),s("span",{staticClass:"block f12 lh220 textcut",staticStyle:{height:"60px",width:"100%"}},[t._v("\n            "+t._s(e.commodityName)+"\n          ")])]),t._v(" "),s("div",{staticClass:"btn-box"},[s("i",{class:{hover:e.focus},on:{click:function(s){s.stopPropagation(),t.collect(e,e.commodityCode)}}},[s("em",{staticClass:" iconfont  icon-guanzhu",staticStyle:{"vertical-align":"text-top"}}),t._v(" "+t._s(e.focus?"已关注":"关注"))]),t._v(" "),s("i",{on:{click:function(s){s.stopPropagation(),t.addCart(s,e)}}},[s("em",{staticClass:" iconfont  icon-gouwuche",staticStyle:{"vertical-align":"text-top"}}),t._v("  加入购物车")])])],2)}),t._v(" "),s("li"),s("li"),s("li"),s("li"),s("li")],2):t._e(),t._v(" "),null===t.commodityList?s("noFile"):t._e()],1),t._v(" "),s("div",{staticClass:"fr"},[t.commodityList?s("Pages",{attrs:{pageNumC:t.pagesCode.pageNum,pageSizeC:t.pagesCode.pageSize,totalC:t.pagesCode.total},on:{pageDone:t.getPageDone}}):t._e()],1),t._v(" "),s("Flyball",{ref:"flyball"}),t._v(" "),s("Bar",{attrs:{type:1}})],1)},staticRenderFns:[function(){var t=this.$createElement,e=this._self._c||t;return e("li",[e("span",[this._v("品类")])])}]};var d=s("Z0/y")(l,u,!1,function(t){s("veUE")},"data-v-05d74278",null);e.default=d.exports},veUE:function(t,e){},x4hD:function(t,e){}});
//# sourceMappingURL=6.4a22e6b31de39cfeedf4.js.map