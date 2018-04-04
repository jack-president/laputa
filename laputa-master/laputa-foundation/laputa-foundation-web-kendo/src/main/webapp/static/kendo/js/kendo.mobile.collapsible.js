/*
* Kendo UI v2015.3.930 (http://www.telerik.com/kendo-ui)
* Copyright 2015 Telerik AD. All rights reserved.
*
* Kendo UI commercial licenses may be obtained at
* http://www.telerik.com/purchase/license-agreement/kendo-ui-complete
* If you do not own a commercial license, this file shall be governed by the trial license terms.
*/
!function(e,define){define(["./kendo.core.min"],e)}(function(){return function(e){var t=window.kendo,n=t.mobile.ui,i=n.Widget,o="km-collapsible",r="km-collapsible-header",a="km-collapsible-content",s="km-collapsibleinset",l="<div data-role='collapsible-header' class='"+r+"'></div>",c="<div data-role='collapsible-content' class='"+a+"'></div>",d="km-collapsed",u="km-expanded",h="km-animated",f="left",p="expand",g="collapse",m=i.extend({init:function(n,r){var a=this,l=e(n);i.fn.init.call(a,l,r),l.addClass(o),a._buildHeader(),a.content=l.children().not(a.header).wrapAll(c).parent(),a._userEvents=new t.UserEvents(a.header,{tap:function(){a.toggle()}}),l.addClass(a.options.collapsed?d:u),a.options.inset&&l.addClass(s),a.options.animation?(a.content.addClass(h),a.content.height(0),a.options.collapsed&&a.content.hide()):a.options.collapsed&&a.content.hide()},events:[p,g],options:{name:"Collapsible",collapsed:!0,collapseIcon:"arrow-n",expandIcon:"arrow-s",iconPosition:f,animation:!0,inset:!1},destroy:function(){i.fn.destroy.call(this),this._userEvents.destroy()},expand:function(e){var n=this.options.collapseIcon,i=this.content,o=t.support.mobileOS.ios;this.trigger(p)||(n&&this.header.find(".km-icon").removeClass().addClass("km-icon km-"+n),this.element.removeClass(d).addClass(u),this.options.animation&&!e?(i.off("transitionend"),i.show(),o&&i.removeClass(h),i.height(this._getContentHeight()),o&&i.addClass(h),t.resize(i)):i.show())},collapse:function(e){var t=this.options.expandIcon,n=this.content;this.trigger(g)||(t&&this.header.find(".km-icon").removeClass().addClass("km-icon km-"+t),this.element.removeClass(u).addClass(d),this.options.animation&&!e?(n.one("transitionend",function(){n.hide()}),n.height(0)):n.hide())},toggle:function(e){this.isCollapsed()?this.expand(e):this.collapse(e)},isCollapsed:function(){return this.element.hasClass(d)},resize:function(){!this.isCollapsed()&&this.options.animation&&this.content.height(this._getContentHeight())},_buildHeader:function(){var t=this.element.children(":header").wrapAll(l),n=e('<span class="km-icon"/>'),i=this.options.collapsed?this.options.expandIcon:this.options.collapseIcon,o=this.options.iconPosition;i&&(t.prepend(n),n.addClass("km-"+i)),this.header=t.parent(),this.header.addClass("km-icon-"+o)},_getContentHeight:function(){var e,t=this.content.attr("style");return this.content.css({position:"absolute",visibility:"hidden",height:"auto"}),e=this.content.height(),this.content.attr("style",t?t:""),e}});n.plugin(m)}(window.kendo.jQuery),window.kendo},"function"==typeof define&&define.amd?define:function(e,t){t()});