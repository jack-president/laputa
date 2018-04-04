/*
* Kendo UI v2015.3.930 (http://www.telerik.com/kendo-ui)
* Copyright 2015 Telerik AD. All rights reserved.
*
* Kendo UI commercial licenses may be obtained at
* http://www.telerik.com/purchase/license-agreement/kendo-ui-complete
* If you do not own a commercial license, this file shall be governed by the trial license terms.
*/
!function(e,define){define(["./kendo.draganddrop.min"],e)}(function(){return function(e,t){function n(e,t,n){var i=n?" k-slider-horizontal":" k-slider-vertical",o=e.style?e.style:t.attr("style"),r=t.attr("class")?" "+t.attr("class"):"",a="";return"bottomRight"==e.tickPlacement?a=" k-slider-bottomright":"topLeft"==e.tickPlacement&&(a=" k-slider-topleft"),o=o?" style='"+o+"'":"","<div class='k-widget k-slider"+i+r+"'"+o+"><div class='k-slider-wrap"+(e.showButtons?" k-slider-buttons":"")+a+"'></div></div>"}function i(e,t,n){var i="";return i="increase"==t?n?"k-i-arrow-e":"k-i-arrow-n":n?"k-i-arrow-w":"k-i-arrow-s","<a class='k-button k-button-"+t+"'><span class='k-icon "+i+"' title='"+e[t+"ButtonTitle"]+"'>"+e[t+"ButtonTitle"]+"</span></a>"}function o(e,t){var n,i="<ul class='k-reset k-slider-items'>",o=x.floor(c(t/e.smallStep))+1;for(n=0;o>n;n++)i+="<li class='k-tick' role='presentation'>&nbsp;</li>";return i+="</ul>"}function r(e,t){var n=t.is("input")?1:2,i=2==n?e.leftDragHandleTitle:e.dragHandleTitle;return"<div class='k-slider-track'><div class='k-slider-selection'><!-- --></div><a href='#' class='k-draghandle' title='"+i+"' role='slider' aria-valuemin='"+e.min+"' aria-valuemax='"+e.max+"' aria-valuenow='"+(n>1?e.selectionStart||e.min:e.value||e.min)+"'>Drag</a>"+(n>1?"<a href='#' class='k-draghandle' title='"+e.rightDragHandleTitle+"'role='slider' aria-valuemin='"+e.min+"' aria-valuemax='"+e.max+"' aria-valuenow='"+(e.selectionEnd||e.max)+"'>Drag</a>":"")+"</div>"}function a(e){return function(t){return t+e}}function s(e){return function(){return e}}function l(e){return(e+"").replace(".",p.cultures.current.numberFormat["."])}function c(e){e=parseFloat(e,10);var t=x.pow(10,q||0);return x.round(e*t)/t}function u(e,n){var i=w(e.getAttribute(n));return null===i&&(i=t),i}function d(e){return typeof e!==Y}function h(e){return 1e4*e}var f,p=window.kendo,g=p.ui.Widget,m=p.ui.Draggable,v=e.extend,_=p.format,w=p.parseFloat,y=e.proxy,b=e.isArray,x=Math,k=p.support,C=k.pointers,S=k.msPointers,T="change",D="slide",A=".slider",E="touchstart"+A+" mousedown"+A,M=C?"pointerdown"+A:S?"MSPointerDown"+A:E,P="touchend"+A+" mouseup"+A,I=C?"pointerup":S?"MSPointerUp"+A:P,R="moveSelection",z="keydown"+A,B="click"+A,L="mouseover"+A,F="focus"+A,O="blur"+A,H=".k-draghandle",N=".k-slider-track",V=".k-tick",U="k-state-selected",W="k-state-focused",j="k-state-default",G="k-state-disabled",q=3,$="disabled",Y="undefined",Q="tabindex",K=p.getTouches,X=g.extend({init:function(e,t){var n,i=this;if(g.fn.init.call(i,e,t),t=i.options,i._distance=c(t.max-t.min),i._isHorizontal="horizontal"==t.orientation,i._isRtl=i._isHorizontal&&p.support.isRtl(e),i._position=i._isHorizontal?"left":"bottom",i._sizeFn=i._isHorizontal?"width":"height",i._outerSize=i._isHorizontal?"outerWidth":"outerHeight",t.tooltip.format=t.tooltip.enabled?t.tooltip.format||"{0}":"{0}",0>=t.smallStep)throw Error("Kendo UI Slider smallStep must be a positive number.");i._createHtml(),i.wrapper=i.element.closest(".k-slider"),i._trackDiv=i.wrapper.find(N),i._setTrackDivWidth(),i._maxSelection=i._trackDiv[i._sizeFn](),i._sliderItemsInit(),i._reset(),i._tabindex(i.wrapper.find(H)),i[t.enabled?"enable":"disable"](),n=p.support.isRtl(i.wrapper)?-1:1,i._keyMap={37:a(-1*n*t.smallStep),40:a(-t.smallStep),39:a(1*n*t.smallStep),38:a(+t.smallStep),35:s(t.max),36:s(t.min),33:a(+t.largeStep),34:a(-t.largeStep)},p.notify(i)},events:[T,D],options:{enabled:!0,min:0,max:10,smallStep:1,largeStep:5,orientation:"horizontal",tickPlacement:"both",tooltip:{enabled:!0,format:"{0}"}},_resize:function(){this._setTrackDivWidth(),this.wrapper.find(".k-slider-items").remove(),this._maxSelection=this._trackDiv[this._sizeFn](),this._sliderItemsInit(),this._refresh(),this.options.enabled&&this.enable(!0)},_sliderItemsInit:function(){var e=this,t=e.options,n=e._maxSelection/((t.max-t.min)/t.smallStep),i=e._calculateItemsWidth(x.floor(e._distance/t.smallStep));"none"!=t.tickPlacement&&n>=2&&(e._trackDiv.before(o(t,e._distance)),e._setItemsWidth(i),e._setItemsTitle()),e._calculateSteps(i),"none"!=t.tickPlacement&&n>=2&&t.largeStep>=t.smallStep&&e._setItemsLargeTick()},getSize:function(){return p.dimensions(this.wrapper)},_setTrackDivWidth:function(){var e=this,t=2*parseFloat(e._trackDiv.css(e._isRtl?"right":e._position),10);e._trackDiv[e._sizeFn](e.wrapper[e._sizeFn]()-2-t)},_setItemsWidth:function(t){var n,i=this,o=i.options,r=0,a=t.length-1,s=i.wrapper.find(V),l=0,c=2,u=s.length,d=0;for(n=0;u-2>n;n++)e(s[n+1])[i._sizeFn](t[n]);if(i._isHorizontal?(e(s[r]).addClass("k-first")[i._sizeFn](t[a-1]),e(s[a]).addClass("k-last")[i._sizeFn](t[a])):(e(s[a]).addClass("k-first")[i._sizeFn](t[a]),e(s[r]).addClass("k-last")[i._sizeFn](t[a-1])),i._distance%o.smallStep!==0&&!i._isHorizontal){for(n=0;t.length>n;n++)d+=t[n];l=i._maxSelection-d,l+=parseFloat(i._trackDiv.css(i._position),10)+c,i.wrapper.find(".k-slider-items").css("padding-top",l)}},_setItemsTitle:function(){for(var t=this,n=t.options,i=t.wrapper.find(V),o=n.min,r=i.length,a=t._isHorizontal&&!t._isRtl?0:r-1,s=t._isHorizontal&&!t._isRtl?r:-1,l=t._isHorizontal&&!t._isRtl?1:-1;a-s!==0;a+=l)e(i[a]).attr("title",_(n.tooltip.format,c(o))),o+=n.smallStep},_setItemsLargeTick:function(){var t,n,i,o=this,r=o.options,a=o.wrapper.find(V),s=0;if(h(r.largeStep)%h(r.smallStep)===0||o._distance/r.largeStep>=3)for(o._isHorizontal||o._isRtl||(a=e.makeArray(a).reverse()),s=0;a.length>s;s++)t=e(a[s]),n=o._values[s],i=c(h(n-this.options.min)),i%h(r.smallStep)===0&&i%h(r.largeStep)===0&&(t.addClass("k-tick-large").html("<span class='k-label'>"+t.attr("title")+"</span>"),0!==s&&s!==a.length-1&&t.css("line-height",t[o._sizeFn]()+"px"))},_calculateItemsWidth:function(e){var t,n,i,o=this,r=o.options,a=parseFloat(o._trackDiv.css(o._sizeFn))+1,s=a/o._distance;for(o._distance/r.smallStep-x.floor(o._distance/r.smallStep)>0&&(a-=o._distance%r.smallStep*s),t=a/e,n=[],i=0;e-1>i;i++)n[i]=t;return n[e-1]=n[e]=t/2,o._roundWidths(n)},_roundWidths:function(e){var t,n=0,i=e.length;for(t=0;i>t;t++)n+=e[t]-x.floor(e[t]),e[t]=x.floor(e[t]);return n=x.round(n),this._addAdditionalSize(n,e)},_addAdditionalSize:function(e,t){if(0===e)return t;var n,i=parseFloat(t.length-1)/parseFloat(1==e?e:e-1);for(n=0;e>n;n++)t[parseInt(x.round(i*n),10)]+=1;return t},_calculateSteps:function(e){var t,n=this,i=n.options,o=i.min,r=0,a=x.ceil(n._distance/i.smallStep),s=1;if(a+=n._distance/i.smallStep%1===0?1:0,e.splice(0,0,2*e[a-2]),e.splice(a-1,1,2*e.pop()),n._pixelSteps=[r],n._values=[o],0!==a){for(;a>s;)r+=(e[s-1]+e[s])/2,n._pixelSteps[s]=r,o+=i.smallStep,n._values[s]=c(o),s++;t=n._distance%i.smallStep===0?a-1:a,n._pixelSteps[t]=n._maxSelection,n._values[t]=i.max,n._isRtl&&(n._pixelSteps.reverse(),n._values.reverse())}},_getValueFromPosition:function(e,t){var n,i=this,o=i.options,r=x.max(o.smallStep*(i._maxSelection/i._distance),0),a=0,s=r/2;if(i._isHorizontal?(a=e-t.startPoint,i._isRtl&&(a=i._maxSelection-a)):a=t.startPoint-e,i._maxSelection-(parseInt(i._maxSelection%r,10)-3)/2<a)return o.max;for(n=0;i._pixelSteps.length>n;n++)if(x.abs(i._pixelSteps[n]-a)-1<=s)return c(i._values[n])},_getFormattedValue:function(e,t){var n,i,o,r=this,a="",s=r.options.tooltip;return b(e)?(i=e[0],o=e[1]):t&&t.type&&(i=t.selectionStart,o=t.selectionEnd),t&&(n=t.tooltipTemplate),!n&&s.template&&(n=p.template(s.template)),b(e)||t&&t.type?n?a=n({selectionStart:i,selectionEnd:o}):(i=_(s.format,i),o=_(s.format,o),a=i+" - "+o):(t&&(t.val=e),a=n?n({value:e}):_(s.format,e)),a},_getDraggableArea:function(){var e=this,t=p.getOffset(e._trackDiv);return{startPoint:e._isHorizontal?t.left:t.top+e._maxSelection,endPoint:e._isHorizontal?t.left+e._maxSelection:t.top}},_createHtml:function(){var e=this,t=e.element,o=e.options,a=t.find("input");2==a.length?(a.eq(0).prop("value",l(o.selectionStart)),a.eq(1).prop("value",l(o.selectionEnd))):t.prop("value",l(o.value)),t.wrap(n(o,t,e._isHorizontal)).hide(),o.showButtons&&t.before(i(o,"increase",e._isHorizontal)).before(i(o,"decrease",e._isHorizontal)),t.before(r(o,t))},_focus:function(t){var n=this,i=t.target,o=n.value(),r=n._drag;r||(i==n.wrapper.find(H).eq(0)[0]?(r=n._firstHandleDrag,n._activeHandle=0):(r=n._lastHandleDrag,n._activeHandle=1),o=o[n._activeHandle]),e(i).addClass(W+" "+U),r&&(n._activeHandleDrag=r,r.selectionStart=n.options.selectionStart,r.selectionEnd=n.options.selectionEnd,r._updateTooltip(o))},_focusWithMouse:function(t){t=e(t);var n=this,i=t.is(H)?t.index():0;window.setTimeout(function(){n.wrapper.find(H)[2==i?1:0].focus()},1),n._setTooltipTimeout()},_blur:function(t){var n=this,i=n._activeHandleDrag;e(t.target).removeClass(W+" "+U),i&&(i._removeTooltip(),delete n._activeHandleDrag,delete n._activeHandle)},_setTooltipTimeout:function(){var e=this;e._tooltipTimeout=window.setTimeout(function(){var t=e._drag||e._activeHandleDrag;t&&t._removeTooltip()},300)},_clearTooltipTimeout:function(){var e,t=this;window.clearTimeout(this._tooltipTimeout),e=t._drag||t._activeHandleDrag,e&&e.tooltipDiv&&e.tooltipDiv.stop(!0,!1).css("opacity",1)},_reset:function(){var t=this,n=t.element,i=n.attr("form"),o=i?e("#"+i):n.closest("form");o[0]&&(t._form=o.on("reset",y(t._formResetHandler,t)))},destroy:function(){this._form&&this._form.off("reset",this._formResetHandler),g.fn.destroy.call(this)}}),Z=X.extend({init:function(n,i){var o,r=this;n.type="text",i=v({},{value:u(n,"value"),min:u(n,"min"),max:u(n,"max"),smallStep:u(n,"step")},i),n=e(n),i&&i.enabled===t&&(i.enabled=!n.is("[disabled]")),X.fn.init.call(r,n,i),i=r.options,d(i.value)&&null!==i.value||(i.value=i.min,n.prop("value",l(i.min))),i.value=x.max(x.min(i.value,i.max),i.min),o=r.wrapper.find(H),this._selection=new Z.Selection(o,r,i),r._drag=new Z.Drag(o,"",r,i)},options:{name:"Slider",showButtons:!0,increaseButtonTitle:"Increase",decreaseButtonTitle:"Decrease",dragHandleTitle:"drag",tooltip:{format:"{0:#,#.##}"},value:null},enable:function(n){var i,o,r,a=this,s=a.options;a.disable(),n!==!1&&(a.wrapper.removeClass(G).addClass(j),a.wrapper.find("input").removeAttr($),i=function(n){var i,o,r,s=K(n)[0];if(s){if(i=a._isHorizontal?s.location.pageX:s.location.pageY,o=a._getDraggableArea(),r=e(n.target),r.hasClass("k-draghandle"))return r.addClass(W+" "+U),t;a._update(a._getValueFromPosition(i,o)),a._focusWithMouse(n.target),a._drag.dragstart(n),n.preventDefault()}},a.wrapper.find(V+", "+N).on(M,i).end().on(M,function(){e(document.documentElement).one("selectstart",p.preventDefault)}).on(I,function(){a._drag._end()}),a.wrapper.find(H).attr(Q,0).on(P,function(){a._setTooltipTimeout()}).on(B,function(e){a._focusWithMouse(e.target),e.preventDefault()}).on(F,y(a._focus,a)).on(O,y(a._blur,a)),o=y(function(e){var t=a._nextValueByIndex(a._valueIndex+1*e);a._setValueInRange(t),a._drag._updateTooltip(t)},a),s.showButtons&&(r=y(function(e,t){this._clearTooltipTimeout(),(1===e.which||k.touch&&0===e.which)&&(o(t),this.timeout=setTimeout(y(function(){this.timer=setInterval(function(){o(t)},60)},this),200))},a),a.wrapper.find(".k-button").on(P,y(function(e){this._clearTimer(),a._focusWithMouse(e.target)},a)).on(L,function(t){e(t.currentTarget).addClass("k-state-hover")}).on("mouseout"+A,y(function(t){e(t.currentTarget).removeClass("k-state-hover"),this._clearTimer()},a)).eq(0).on(E,y(function(e){r(e,1)},a)).click(!1).end().eq(1).on(E,y(function(e){r(e,-1)},a)).click(p.preventDefault)),a.wrapper.find(H).off(z,!1).on(z,y(this._keydown,a)),s.enabled=!0)},disable:function(){var t=this;t.wrapper.removeClass(j).addClass(G),e(t.element).prop($,$),t.wrapper.find(".k-button").off(E).on(E,p.preventDefault).off(P).on(P,p.preventDefault).off("mouseleave"+A).on("mouseleave"+A,p.preventDefault).off(L).on(L,p.preventDefault),t.wrapper.find(V+", "+N).off(M).off(I),t.wrapper.find(H).attr(Q,-1).off(P).off(z).off(B).off(F).off(O),t.options.enabled=!1},_update:function(e){var t=this,n=t.value()!=e;t.value(e),n&&t.trigger(T,{value:t.options.value})},value:function(e){var n=this,i=n.options;return e=c(e),isNaN(e)?i.value:(e>=i.min&&i.max>=e&&i.value!=e&&(n.element.prop("value",l(e)),i.value=e,n._refreshAriaAttr(e),n._refresh()),t)},_refresh:function(){this.trigger(R,{value:this.options.value})},_refreshAriaAttr:function(e){var t,n=this,i=n._drag;t=i&&i._tooltipDiv?i._tooltipDiv.text():n._getFormattedValue(e,null),this.wrapper.find(H).attr("aria-valuenow",e).attr("aria-valuetext",t)},_clearTimer:function(){clearTimeout(this.timeout),clearInterval(this.timer)},_keydown:function(e){var t=this;e.keyCode in t._keyMap&&(t._clearTooltipTimeout(),t._setValueInRange(t._keyMap[e.keyCode](t.options.value)),t._drag._updateTooltip(t.value()),e.preventDefault())},_setValueInRange:function(e){var n=this,i=n.options;return e=c(e),isNaN(e)?(n._update(i.min),t):(e=x.max(x.min(e,i.max),i.min),n._update(e),t)},_nextValueByIndex:function(e){var t=this._values.length;return this._isRtl&&(e=t-1-e),this._values[x.max(0,x.min(e,t-1))]},_formResetHandler:function(){var e=this,t=e.options.min;setTimeout(function(){var n=e.element[0].value;e.value(""===n||isNaN(n)?t:n)})},destroy:function(){var e=this;X.fn.destroy.call(e),e.wrapper.off(A).find(".k-button").off(A).end().find(H).off(A).end().find(V+", "+N).off(A).end(),e._drag.draggable.destroy(),e._drag._removeTooltip(!0)}});Z.Selection=function(e,t,n){function i(i){var o=i-n.min,r=t._valueIndex=x.ceil(c(o/n.smallStep)),a=parseInt(t._pixelSteps[r],10),s=t._trackDiv.find(".k-slider-selection"),l=parseInt(e[t._outerSize]()/2,10),u=t._isRtl?2:0;s[t._sizeFn](t._isRtl?t._maxSelection-a:a),e.css(t._position,a-l-u)}i(n.value),t.bind([T,D,R],function(e){i(parseFloat(e.value,10))})},Z.Drag=function(e,t,n,i){var o=this;o.owner=n,o.options=i,o.element=e,o.type=t,o.draggable=new m(e,{distance:0,dragstart:y(o._dragstart,o),drag:y(o.drag,o),dragend:y(o.dragend,o),dragcancel:y(o.dragcancel,o)}),e.click(!1)},Z.Drag.prototype={dragstart:function(e){this.owner._activeDragHandle=this,this.draggable.userEvents.cancel(),this.draggable.userEvents._start(e)},_dragstart:function(n){var i=this,o=i.owner,r=i.options;return r.enabled?(this.owner._activeDragHandle=this,o.element.off(L),o.wrapper.find("."+W).removeClass(W+" "+U),i.element.addClass(W+" "+U),e(document.documentElement).css("cursor","pointer"),i.dragableArea=o._getDraggableArea(),i.step=x.max(r.smallStep*(o._maxSelection/o._distance),0),i.type?(i.selectionStart=r.selectionStart,i.selectionEnd=r.selectionEnd,o._setZIndex(i.type)):i.oldVal=i.val=r.value,i._removeTooltip(!0),i._createTooltip(),t):(n.preventDefault(),t)},_createTooltip:function(){var t,n,i=this,o=i.owner,r=i.options.tooltip,a="",s=e(window);r.enabled&&(r.template&&(t=i.tooltipTemplate=p.template(r.template)),e(".k-slider-tooltip").remove(),i.tooltipDiv=e("<div class='k-widget k-tooltip k-slider-tooltip'><!-- --></div>").appendTo(document.body),a=o._getFormattedValue(i.val||o.value(),i),i.type||(n="k-callout-"+(o._isHorizontal?"s":"e"),i.tooltipInnerDiv="<div class='k-callout "+n+"'><!-- --></div>",a+=i.tooltipInnerDiv),i.tooltipDiv.html(a),i._scrollOffset={top:s.scrollTop(),left:s.scrollLeft()},i.moveTooltip())},drag:function(e){var t,n=this,i=n.owner,o=e.x.location,r=e.y.location,a=n.dragableArea.startPoint,s=n.dragableArea.endPoint;e.preventDefault(),n.val=i._isHorizontal?i._isRtl?n.constrainValue(o,a,s,s>o):n.constrainValue(o,a,s,o>=s):n.constrainValue(r,s,a,s>=r),n.oldVal!=n.val&&(n.oldVal=n.val,n.type?("firstHandle"==n.type?n.selectionStart=n.selectionEnd>n.val?n.val:n.selectionEnd=n.val:n.val>n.selectionStart?n.selectionEnd=n.val:n.selectionStart=n.selectionEnd=n.val,t={values:[n.selectionStart,n.selectionEnd],value:[n.selectionStart,n.selectionEnd]}):t={value:n.val},i.trigger(D,t)),n._updateTooltip(n.val)},_updateTooltip:function(e){var t=this,n=t.options,i=n.tooltip,o="";i.enabled&&(t.tooltipDiv||t._createTooltip(),o=t.owner._getFormattedValue(c(e),t),t.type||(o+=t.tooltipInnerDiv),t.tooltipDiv.html(o),t.moveTooltip())},dragcancel:function(){return this.owner._refresh(),e(document.documentElement).css("cursor",""),this._end()},dragend:function(){var t=this,n=t.owner;return e(document.documentElement).css("cursor",""),t.type?n._update(t.selectionStart,t.selectionEnd):(n._update(t.val),t.draggable.userEvents._disposeAll()),t._end()},_end:function(){var e=this,t=e.owner;return t._focusWithMouse(e.element),t.element.on(L),!1},_removeTooltip:function(t){var n=this,i=n.owner;n.tooltipDiv&&i.options.tooltip.enabled&&i.options.enabled&&(t?(n.tooltipDiv.remove(),n.tooltipDiv=null):n.tooltipDiv.fadeOut("slow",function(){e(this).remove(),n.tooltipDiv=null}))},moveTooltip:function(){var t,n,i,o,r=this,a=r.owner,s=0,l=0,c=r.element,u=p.getOffset(c),d=8,h=e(window),f=r.tooltipDiv.find(".k-callout"),g=r.tooltipDiv.outerWidth(),m=r.tooltipDiv.outerHeight();r.type?(t=a.wrapper.find(H),u=p.getOffset(t.eq(0)),n=p.getOffset(t.eq(1)),a._isHorizontal?(s=n.top,l=u.left+(n.left-u.left)/2):(s=u.top+(n.top-u.top)/2,l=n.left),o=t.eq(0).outerWidth()+2*d):(s=u.top,l=u.left,o=c.outerWidth()+2*d),a._isHorizontal?(l-=parseInt((g-c[a._outerSize]())/2,10),s-=m+f.height()+d):(s-=parseInt((m-c[a._outerSize]())/2,10),l-=g+f.width()+d),a._isHorizontal?(i=r._flip(s,m,o,h.outerHeight()+r._scrollOffset.top),s+=i,l+=r._fit(l,g,h.outerWidth()+r._scrollOffset.left)):(i=r._flip(l,g,o,h.outerWidth()+r._scrollOffset.left),s+=r._fit(s,m,h.outerHeight()+r._scrollOffset.top),l+=i),i>0&&f&&(f.removeClass(),f.addClass("k-callout k-callout-"+(a._isHorizontal?"n":"w"))),r.tooltipDiv.css({top:s,left:l})},_fit:function(e,t,n){var i=0;return e+t>n&&(i=n-(e+t)),0>e&&(i=-e),i},_flip:function(e,t,n,i){var o=0;return e+t>i&&(o+=-(n+t)),0>e+o&&(o+=n+t),o},constrainValue:function(e,t,n,i){var o=this,r=0;return r=e>t&&n>e?o.owner._getValueFromPosition(e,o.dragableArea):i?o.options.max:o.options.min}},p.ui.plugin(Z),f=X.extend({init:function(n,i){var o,r=this,a=e(n).find("input"),s=a.eq(0)[0],c=a.eq(1)[0];s.type="text",c.type="text",i&&i.showButtons&&(window.console&&window.console.warn("showbuttons option is not supported for the range slider, ignoring"),i.showButtons=!1),i=v({},{selectionStart:u(s,"value"),min:u(s,"min"),max:u(s,"max"),smallStep:u(s,"step")},{selectionEnd:u(c,"value"),min:u(c,"min"),max:u(c,"max"),smallStep:u(c,"step")},i),i&&i.enabled===t&&(i.enabled=!a.is("[disabled]")),X.fn.init.call(r,n,i),i=r.options,d(i.selectionStart)&&null!==i.selectionStart||(i.selectionStart=i.min,a.eq(0).prop("value",l(i.min))),d(i.selectionEnd)&&null!==i.selectionEnd||(i.selectionEnd=i.max,a.eq(1).prop("value",l(i.max))),o=r.wrapper.find(H),this._selection=new f.Selection(o,r,i),r._firstHandleDrag=new Z.Drag(o.eq(0),"firstHandle",r,i),r._lastHandleDrag=new Z.Drag(o.eq(1),"lastHandle",r,i)},options:{name:"RangeSlider",leftDragHandleTitle:"drag",rightDragHandleTitle:"drag",tooltip:{format:"{0:#,#.##}"},selectionStart:null,selectionEnd:null},enable:function(n){var i,o=this,r=o.options;o.disable(),n!==!1&&(o.wrapper.removeClass(G).addClass(j),o.wrapper.find("input").removeAttr($),i=function(n){var i,a,s,l,c,u,d,h=K(n)[0];if(h){if(i=o._isHorizontal?h.location.pageX:h.location.pageY,a=o._getDraggableArea(),s=o._getValueFromPosition(i,a),l=e(n.target),l.hasClass("k-draghandle"))return o.wrapper.find("."+W).removeClass(W+" "+U),l.addClass(W+" "+U),t;r.selectionStart>s?(c=s,u=r.selectionEnd,d=o._firstHandleDrag):s>o.selectionEnd?(c=r.selectionStart,u=s,d=o._lastHandleDrag):r.selectionEnd-s>=s-r.selectionStart?(c=s,u=r.selectionEnd,d=o._firstHandleDrag):(c=r.selectionStart,u=s,d=o._lastHandleDrag),d.dragstart(n),o._setValueInRange(c,u),o._focusWithMouse(d.element)}},o.wrapper.find(V+", "+N).on(M,i).end().on(M,function(){e(document.documentElement).one("selectstart",p.preventDefault)}).on(I,function(){o._activeDragHandle&&o._activeDragHandle._end()}),o.wrapper.find(H).attr(Q,0).on(P,function(){o._setTooltipTimeout()}).on(B,function(e){o._focusWithMouse(e.target),e.preventDefault()}).on(F,y(o._focus,o)).on(O,y(o._blur,o)),o.wrapper.find(H).off(z,p.preventDefault).eq(0).on(z,y(function(e){this._keydown(e,"firstHandle")},o)).end().eq(1).on(z,y(function(e){this._keydown(e,"lastHandle")},o)),o.options.enabled=!0)},disable:function(){var e=this;e.wrapper.removeClass(j).addClass(G),e.wrapper.find("input").prop($,$),e.wrapper.find(V+", "+N).off(M).off(I),e.wrapper.find(H).attr(Q,-1).off(P).off(z).off(B).off(F).off(O),e.options.enabled=!1},_keydown:function(e,t){var n,i,o,r=this,a=r.options.selectionStart,s=r.options.selectionEnd;e.keyCode in r._keyMap&&(r._clearTooltipTimeout(),"firstHandle"==t?(o=r._activeHandleDrag=r._firstHandleDrag,a=r._keyMap[e.keyCode](a),a>s&&(s=a)):(o=r._activeHandleDrag=r._lastHandleDrag,s=r._keyMap[e.keyCode](s),a>s&&(a=s)),r._setValueInRange(c(a),c(s)),n=Math.max(a,r.options.selectionStart),i=Math.min(s,r.options.selectionEnd),o.selectionEnd=Math.max(i,r.options.selectionStart),o.selectionStart=Math.min(n,r.options.selectionEnd),o._updateTooltip(r.value()[r._activeHandle]),e.preventDefault())},_update:function(e,t){var n=this,i=n.value(),o=i[0]!=e||i[1]!=t;n.value([e,t]),o&&n.trigger(T,{values:[e,t],value:[e,t]})},value:function(e){return e&&e.length?this._value(e[0],e[1]):this._value()},_value:function(e,n){var i=this,o=i.options,r=o.selectionStart,a=o.selectionEnd;return isNaN(e)&&isNaN(n)?[r,a]:(e=c(e),n=c(n),e>=o.min&&o.max>=e&&n>=o.min&&o.max>=n&&n>=e&&(r!=e||a!=n)&&(i.element.find("input").eq(0).prop("value",l(e)).end().eq(1).prop("value",l(n)),o.selectionStart=e,o.selectionEnd=n,i._refresh(),i._refreshAriaAttr(e,n)),t)},values:function(e,t){return b(e)?this._value(e[0],e[1]):this._value(e,t)},_refresh:function(){var e=this,t=e.options;e.trigger(R,{values:[t.selectionStart,t.selectionEnd],value:[t.selectionStart,t.selectionEnd]}),t.selectionStart==t.max&&t.selectionEnd==t.max&&e._setZIndex("firstHandle")},_refreshAriaAttr:function(e,t){var n,i=this,o=i.wrapper.find(H),r=i._activeHandleDrag;n=i._getFormattedValue([e,t],r),o.eq(0).attr("aria-valuenow",e),o.eq(1).attr("aria-valuenow",t),o.attr("aria-valuetext",n)},_setValueInRange:function(e,t){var n=this.options;e=x.max(x.min(e,n.max),n.min),t=x.max(x.min(t,n.max),n.min),e==n.max&&t==n.max&&this._setZIndex("firstHandle"),this._update(x.min(e,t),x.max(e,t))},_setZIndex:function(t){this.wrapper.find(H).each(function(n){e(this).css("z-index","firstHandle"==t?1-n:n)})},_formResetHandler:function(){var e=this,t=e.options;setTimeout(function(){var n=e.element.find("input"),i=n[0].value,o=n[1].value;e.values(""===i||isNaN(i)?t.min:i,""===o||isNaN(o)?t.max:o)})},destroy:function(){var e=this;X.fn.destroy.call(e),e.wrapper.off(A).find(V+", "+N).off(A).end().find(H).off(A),e._firstHandleDrag.draggable.destroy(),e._lastHandleDrag.draggable.destroy()}}),f.Selection=function(e,t,n){function i(i){i=i||[];var r=i[0]-n.min,a=i[1]-n.min,s=x.ceil(c(r/n.smallStep)),l=x.ceil(c(a/n.smallStep)),u=t._pixelSteps[s],d=t._pixelSteps[l],h=parseInt(e.eq(0)[t._outerSize]()/2,10),f=t._isRtl?2:0;e.eq(0).css(t._position,u-h-f).end().eq(1).css(t._position,d-h-f),o(u,d)}function o(e,n){var i,o,r=t._trackDiv.find(".k-slider-selection");i=x.abs(e-n),r[t._sizeFn](i),t._isRtl?(o=x.max(e,n),r.css("right",t._maxSelection-o-1)):(o=x.min(e,n),r.css(t._position,o-1))}i(t.value()),t.bind([T,D,R],function(e){i(e.values)})},p.ui.plugin(f)}(window.kendo.jQuery),window.kendo},"function"==typeof define&&define.amd?define:function(e,t){t()});