/*
* Kendo UI v2015.3.930 (http://www.telerik.com/kendo-ui)
* Copyright 2015 Telerik AD. All rights reserved.
*
* Kendo UI commercial licenses may be obtained at
* http://www.telerik.com/purchase/license-agreement/kendo-ui-complete
* If you do not own a commercial license, this file shall be governed by the trial license terms.
*/
!function(e,define){define(["./kendo.dataviz.chart.min"],e)}(function(){return function(e){function t(e){return"number"==typeof e?[e]:e}var n=window.kendo,i=n.dataviz,o=i.ui.Chart,r=n.data.ObservableArray,s=i.SharedTooltip,a=n.deepExtend,l=e.isArray,c=e.proxy,h=i.inArray,u=Math,d="k-",f=150,p=150,g="bar",m="bullet",v="pie",_="leave",w=[g,m],y=o.extend({init:function(n,i){var s=this,c=s.stage=e("<span />"),f=i||{};n=e(n).addClass(d+"sparkline").empty().append(c),s._initialWidth=u.floor(n.width()),f=t(f),(l(f)||f instanceof r)&&(f={seriesDefaults:{data:f}}),f.series||(f.series=[{data:t(f.data)}]),a(f,{seriesDefaults:{type:f.type}}),(h(f.series[0].type,w)||h(f.seriesDefaults.type,w))&&(f=a({},{categoryAxis:{crosshair:{visible:!1}}},f)),o.fn.init.call(s,n,f)},options:{name:"Sparkline",chartArea:{margin:2},axisDefaults:{visible:!1,majorGridLines:{visible:!1},valueAxis:{narrowRange:!0}},seriesDefaults:{type:"line",area:{line:{width:.5}},bar:{stack:!0},padding:2,width:.5,overlay:{gradient:null},highlight:{visible:!1},border:{width:0},markers:{size:2,visible:!1}},tooltip:{visible:!0,shared:!0},categoryAxis:{crosshair:{visible:!0,tooltip:{visible:!1}}},legend:{visible:!1},transitions:!1,pointWidth:5,panes:[{clip:!1}]},_modelOptions:function(){var t,n,i=this,o=i.options,r=i._initialWidth,s=i.stage;return i.stage.children().hide(),n=e("<span>&nbsp;</span>"),i.stage.append(n),t=a({width:r?r:i._autoWidth(),height:s.height(),transitions:o.transitions},o.chartArea,{inline:!0,align:!1}),s.css({width:t.width,height:t.height}),n.remove(),i.stage.children().show(),i.surface.resize(),t},_createTooltip:function(){var e,t=this,n=t.options,i=t.element;return e=t._sharedTooltip()?new b(i,t._plotArea,n.tooltip):o.fn._createTooltip.call(t),e.bind(_,c(t._tooltipleave,t)),e},_surfaceWrap:function(){return this.stage},_autoWidth:function(){var e,t,n,o=this,r=o.options,s=i.getSpacing(r.chartArea.margin),a=r.series,l=o.dataSource.total(),c=0;for(t=0;a.length>t;t++){if(n=a[t],n.type===g)return f;if(n.type===m)return p;if(n.type===v)return o.stage.height();n.data&&(c=u.max(c,n.data.length))}return e=u.max(l,c)*r.pointWidth,e>0&&(e+=s.left+s.right),e}}),b=s.extend({options:{animation:{duration:0}},_anchor:function(e,t){var n=s.fn._anchor.call(this,e,t),i=this._measure();return n.y=-i.height-this.options.offset,n},_hideElement:function(){this.element&&this.element.hide().remove()}});i.ui.plugin(y),a(i,{SparklineSharedTooltip:b})}(window.kendo.jQuery),window.kendo},"function"==typeof define&&define.amd?define:function(e,t){t()});