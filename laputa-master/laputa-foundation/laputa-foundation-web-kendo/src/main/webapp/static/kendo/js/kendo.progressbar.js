/*
* Kendo UI v2015.3.930 (http://www.telerik.com/kendo-ui)
* Copyright 2015 Telerik AD. All rights reserved.
*
* Kendo UI commercial licenses may be obtained at
* http://www.telerik.com/purchase/license-agreement/kendo-ui-complete
* If you do not own a commercial license, this file shall be governed by the trial license terms.
*/
!function(e,define){define(["./kendo.core.min"],e)}(function(){return function(e,t){var n=window.kendo,i=n.ui,o=i.Widget,r="horizontal",s="vertical",a=0,l=100,c=0,u=5,d="k-progressbar",h="k-progressbar-reverse",f="k-progressbar-indeterminate",p="k-complete",g="k-state-selected",m="k-progress-status",v="k-state-selected",_="k-state-default",w="k-state-disabled",b={VALUE:"value",PERCENT:"percent",CHUNK:"chunk"},y="change",x="complete",k="boolean",C=Math,S=e.extend,T=e.proxy,D=100,A=400,E=3,P={progressStatus:"<span class='k-progress-status-wrap'><span class='k-progress-status'></span></span>"},M=o.extend({init:function(e,t){var n=this;o.fn.init.call(this,e,t),t=n.options,n._progressProperty=t.orientation===r?"width":"height",n._fields(),t.value=n._validateValue(t.value),n._validateType(t.type),n._wrapper(),n._progressAnimation(),t.value!==t.min&&t.value!==!1&&n._updateProgress()},setOptions:function(e){var t=this;o.fn.setOptions.call(t,e),e.hasOwnProperty("reverse")&&t.wrapper.toggleClass("k-progressbar-reverse",e.reverse),e.hasOwnProperty("enable")&&t.enable(e.enable),t._progressAnimation(),t._validateValue(),t._updateProgress()},events:[y,x],options:{name:"ProgressBar",orientation:r,reverse:!1,min:a,max:l,value:c,enable:!0,type:b.VALUE,chunkCount:u,showStatus:!0,animation:{}},_fields:function(){var t=this;t._isStarted=!1,t.progressWrapper=t.progressStatus=e()},_validateType:function(i){var o=!1;if(e.each(b,function(e,n){return n===i?(o=!0,!1):t}),!o)throw Error(n.format("Invalid ProgressBar type '{0}'",i))},_wrapper:function(){var e,t=this,n=t.wrapper=t.element,i=t.options,o=i.orientation;n.addClass("k-widget "+d),n.addClass(d+"-"+(o===r?r:s)),i.enable===!1&&n.addClass(w),i.reverse&&n.addClass(h),i.value===!1&&n.addClass(f),i.type===b.CHUNK?t._addChunkProgressWrapper():i.showStatus&&(t.progressStatus=t.wrapper.prepend(P.progressStatus).find("."+m),e=i.value!==!1?i.value:i.min,t.progressStatus.text(i.type===b.VALUE?e:t._calculatePercentage(e).toFixed()+"%"))},value:function(e){return this._value(e)},_value:function(e){var n,i=this,o=i.options;return e===t?o.value:(typeof e!==k?(e=i._roundValue(e),isNaN(e)||(n=i._validateValue(e),n!==o.value&&(i.wrapper.removeClass(f),o.value=n,i._isStarted=!0,i._updateProgress()))):e||(i.wrapper.addClass(f),o.value=!1),t)},_roundValue:function(e){e=parseFloat(e);var t=C.pow(10,E);return C.floor(e*t)/t},_validateValue:function(e){var t=this,n=t.options;if(e!==!1){if(n.min>=e||e===!0)return n.min;if(e>=n.max)return n.max}else if(e===!1)return!1;return isNaN(t._roundValue(e))?n.min:e},_updateProgress:function(){var e=this,t=e.options,n=e._calculatePercentage();t.type===b.CHUNK?(e._updateChunks(n),e._onProgressUpdateAlways(t.value)):e._updateProgressWrapper(n)},_updateChunks:function(e){var t,n=this,i=n.options,o=i.chunkCount,a=parseInt(D/o*100,10)/100,l=parseInt(100*e,10)/100,c=C.floor(l/a);t=n.wrapper.find(i.orientation===r&&!i.reverse||i.orientation===s&&i.reverse?"li.k-item:lt("+c+")":"li.k-item:gt(-"+(c+1)+")"),n.wrapper.find("."+v).removeClass(v).addClass(_),t.removeClass(_).addClass(v)},_updateProgressWrapper:function(e){var t=this,n=t.options,i=t.wrapper.find("."+g),o=t._isStarted?t._animation.duration:0,r={};0===i.length&&t._addRegularProgressWrapper(),r[t._progressProperty]=e+"%",t.progressWrapper.animate(r,{duration:o,start:T(t._onProgressAnimateStart,t),progress:T(t._onProgressAnimate,t),complete:T(t._onProgressAnimateComplete,t,n.value),always:T(t._onProgressUpdateAlways,t,n.value)})},_onProgressAnimateStart:function(){this.progressWrapper.show()},_onProgressAnimate:function(e){var t,n=this,i=n.options,o=parseFloat(e.elem.style[n._progressProperty],10);i.showStatus&&(t=1e4/parseFloat(n.progressWrapper[0].style[n._progressProperty]),n.progressWrapper.find(".k-progress-status-wrap").css(n._progressProperty,t+"%")),i.type!==b.CHUNK&&98>=o&&n.progressWrapper.removeClass(p)},_onProgressAnimateComplete:function(e){var t,n=this,i=n.options,o=parseFloat(n.progressWrapper[0].style[n._progressProperty]);i.type!==b.CHUNK&&o>98&&n.progressWrapper.addClass(p),i.showStatus&&(t=i.type===b.VALUE?e:i.type==b.PERCENT?n._calculatePercentage(e).toFixed()+"%":C.floor(n._calculatePercentage(e))+"%",n.progressStatus.text(t)),e===i.min&&n.progressWrapper.hide()},_onProgressUpdateAlways:function(e){var t=this,n=t.options;t._isStarted&&t.trigger(y,{value:e}),e===n.max&&t._isStarted&&t.trigger(x,{value:n.max})},enable:function(e){var n=this,i=n.options;i.enable=t===e?!0:e,n.wrapper.toggleClass(w,!i.enable)},destroy:function(){var e=this;o.fn.destroy.call(e)},_addChunkProgressWrapper:function(){var e,t=this,n=t.options,i=t.wrapper,o=D/n.chunkCount,r="";for(1>=n.chunkCount&&(n.chunkCount=1),r+="<ul class='k-reset'>",e=n.chunkCount-1;e>=0;e--)r+="<li class='k-item k-state-default'></li>";r+="</ul>",i.append(r).find(".k-item").css(t._progressProperty,o+"%").first().addClass("k-first").end().last().addClass("k-last"),t._normalizeChunkSize()},_normalizeChunkSize:function(){var e=this,t=e.options,n=e.wrapper.find(".k-item:last"),i=parseFloat(n[0].style[e._progressProperty]),o=D-t.chunkCount*i;o>0&&n.css(e._progressProperty,i+o+"%")},_addRegularProgressWrapper:function(){var t=this;t.progressWrapper=e("<div class='"+g+"'></div>").appendTo(t.wrapper),t.options.showStatus&&(t.progressWrapper.append(P.progressStatus),t.progressStatus=t.wrapper.find("."+m))},_calculateChunkSize:function(){var e=this,t=e.options.chunkCount,n=e.wrapper.find("ul.k-reset");return(parseInt(n.css(e._progressProperty),10)-(t-1))/t},_calculatePercentage:function(e){var n=this,i=n.options,o=e!==t?e:i.value,r=i.min,s=i.max;return n._onePercent=C.abs((s-r)/100),C.abs((o-r)/n._onePercent)},_progressAnimation:function(){var e=this,t=e.options,n=t.animation;e._animation=n===!1?{duration:0}:S({duration:A},t.animation)}});n.ui.plugin(M)}(window.kendo.jQuery),window.kendo},"function"==typeof define&&define.amd?define:function(e,t){t()});