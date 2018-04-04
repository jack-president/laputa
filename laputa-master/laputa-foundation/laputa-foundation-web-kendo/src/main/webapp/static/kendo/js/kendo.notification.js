/*
* Kendo UI v2015.3.930 (http://www.telerik.com/kendo-ui)
* Copyright 2015 Telerik AD. All rights reserved.
*
* Kendo UI commercial licenses may be obtained at
* http://www.telerik.com/purchase/license-agreement/kendo-ui-complete
* If you do not own a commercial license, this file shall be governed by the trial license terms.
*/
!function(e,define){define(["./kendo.core.min","./kendo.popup.min"],e)}(function(){return function(e,t){var n=window.kendo,i=n.ui.Widget,o=e.proxy,r=e.extend,s=window.setTimeout,a="click",l="show",c="hide",u="k-notification",d=".k-notification-wrap .k-i-close",h="info",f="success",p="warning",g="error",m="top",v="left",_="bottom",w="right",b="up",y=".kendoNotification",x='<div class="k-widget k-notification"></div>',k='<div class="k-notification-wrap"><span class="k-icon k-i-note">#=typeIcon#</span>#=content#<span class="k-icon k-i-close">Hide</span></div>',C=i.extend({init:function(t,o){var r=this;i.fn.init.call(r,t,o),o=r.options,o.appendTo&&e(o.appendTo).is(t)||r.element.hide(),r._compileTemplates(o.templates),r._guid="_"+n.guid(),r._isRtl=n.support.isRtl(t),r._compileStacking(o.stacking,o.position.top,o.position.left),n.notify(r)},events:[l,c],options:{name:"Notification",position:{pinned:!0,top:null,left:null,bottom:20,right:20},stacking:"default",hideOnClick:!0,button:!1,allowHideAfter:0,autoHideAfter:5e3,appendTo:null,width:null,height:null,templates:[],animation:{open:{effects:"fade:in",duration:300},close:{effects:"fade:out",duration:600,hide:!0}}},_compileTemplates:function(t){var i=this,o=n.template;i._compiled={},e.each(t,function(t,n){i._compiled[n.type]=o(n.template||e("#"+n.templateId).html())}),i._defaultCompiled=o(k)},_getCompiled:function(e){var t=this,n=t._defaultCompiled;return e?t._compiled[e]||n:n},_compileStacking:function(e,t,n){var i,o,r=this,s={paddingTop:0,paddingRight:0,paddingBottom:0,paddingLeft:0},a=null!==n?v:w;switch(e){case"down":i=_+" "+a,o=m+" "+a,delete s.paddingBottom;break;case w:i=m+" "+w,o=m+" "+v,delete s.paddingRight;break;case v:i=m+" "+v,o=m+" "+w,delete s.paddingLeft;break;case b:i=m+" "+a,o=_+" "+a,delete s.paddingTop;break;default:null!==t?(i=_+" "+a,o=m+" "+a,delete s.paddingBottom):(i=m+" "+a,o=_+" "+a,delete s.paddingTop)}r._popupOrigin=i,r._popupPosition=o,r._popupPaddings=s},_attachPopupEvents:function(e,t){function i(e){e.on(a+y,function(){r._hidePopup(t)})}var o,r=this,l=e.allowHideAfter,c=!isNaN(l)&&l>0;t.options.anchor!==document.body&&t.options.origin.indexOf(w)>0&&t.bind("open",function(){var e=n.getShadows(t.element);s(function(){t.wrapper.css("left",parseFloat(t.wrapper.css("left"))+e.left+e.right)})}),e.hideOnClick?t.bind("activate",function(){c?s(function(){i(t.element)},l):i(t.element)}):e.button&&(o=t.element.find(d),c?s(function(){i(o)},l):i(o))},_showPopup:function(t,i){var o,a,l=this,c=i.autoHideAfter,u=i.position.left,h=i.position.top;a=e("."+l._guid+":not(.k-hiding)").last(),o=new n.ui.Popup(t,{anchor:a[0]?a:document.body,origin:l._popupOrigin,position:l._popupPosition,animation:i.animation,modal:!0,collision:"",isRtl:l._isRtl,close:function(){l._triggerHide(this.element)},deactivate:function(e){e.sender.element.off(y),e.sender.element.find(d).off(y),e.sender.destroy()}}),l._attachPopupEvents(i,o),a[0]?o.open():(null===u&&(u=e(window).width()-t.width()-i.position.right),null===h&&(h=e(window).height()-t.height()-i.position.bottom),o.open(u,h)),o.wrapper.addClass(l._guid).css(r({margin:0},l._popupPaddings)),i.position.pinned?(o.wrapper.css("position","fixed"),a[0]&&l._togglePin(o.wrapper,!0)):a[0]||l._togglePin(o.wrapper,!1),c>0&&s(function(){l._hidePopup(o)},c)},_hidePopup:function(e){e.wrapper.addClass("k-hiding"),e.close()},_togglePin:function(t,n){var i=e(window),o=n?-1:1;t.css({top:parseInt(t.css(m),10)+o*i.scrollTop(),left:parseInt(t.css(v),10)+o*i.scrollLeft()})},_attachStaticEvents:function(e,t){function n(e){e.on(a+y,o(i._hideStatic,i,t))}var i=this,r=e.allowHideAfter,l=!isNaN(r)&&r>0;e.hideOnClick?l?s(function(){n(t)},r):n(t):e.button&&(l?s(function(){n(t.find(d))},r):n(t.find(d)))},_showStatic:function(e,t){var n=this,i=t.autoHideAfter,o=t.animation,r=t.stacking==b||t.stacking==v?"prependTo":"appendTo";e.addClass(n._guid)[r](t.appendTo).hide().kendoAnimate(o.open||!1),n._attachStaticEvents(t,e),i>0&&s(function(){n._hideStatic(e)},i)},_hideStatic:function(e){e.kendoAnimate(r(this.options.animation.close||!1,{complete:function(){e.off(y).find(d).off(y),e.remove()}})),this._triggerHide(e)},_triggerHide:function(e){this.trigger(c,{element:e}),this.angular("cleanup",function(){return{elements:e}})},show:function(i,o){var s,a,c=this,d=c.options,f=e(x);return o||(o=h),null!==i&&i!==t&&""!==i&&(n.isFunction(i)&&(i=i()),a={typeIcon:o,content:""},s=e.isPlainObject(i)?r(a,i):r(a,{content:i}),f.addClass(u+"-"+o).toggleClass(u+"-button",d.button).attr("data-role","alert").css({width:d.width,height:d.height}).append(c._getCompiled(o)(s)),c.angular("compile",function(){return{elements:f,data:[{dataItem:s}]}}),e(d.appendTo)[0]?c._showStatic(f,d):c._showPopup(f,d),c.trigger(l,{element:f})),c},info:function(e){return this.show(e,h)},success:function(e){return this.show(e,f)},warning:function(e){return this.show(e,p)},error:function(e){return this.show(e,g)},hide:function(){var t=this,n=t.getNotifications();return n.each(t.options.appendTo?function(n,i){t._hideStatic(e(i))}:function(n,i){var o=e(i).data("kendoPopup");o&&t._hidePopup(o)}),t},getNotifications:function(){var t=this,n=e("."+t._guid);return t.options.appendTo?n:n.children("."+u)},setOptions:function(e){var n,o=this;i.fn.setOptions.call(o,e),n=o.options,e.templates!==t&&o._compileTemplates(n.templates),(e.stacking!==t||e.position!==t)&&o._compileStacking(n.stacking,n.position.top,n.position.left)},destroy:function(){i.fn.destroy.call(this),this.getNotifications().off(y).find(d).off(y)}});n.ui.plugin(C)}(window.kendo.jQuery),window.kendo},"function"==typeof define&&define.amd?define:function(e,t){t()});