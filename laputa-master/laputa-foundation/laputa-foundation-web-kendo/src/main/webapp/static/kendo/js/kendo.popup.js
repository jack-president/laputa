/*
* Kendo UI v2015.3.930 (http://www.telerik.com/kendo-ui)
* Copyright 2015 Telerik AD. All rights reserved.
*
* Kendo UI commercial licenses may be obtained at
* http://www.telerik.com/purchase/license-agreement/kendo-ui-complete
* If you do not own a commercial license, this file shall be governed by the trial license terms.
*/
!function(e,define){define(["./kendo.core.min"],e)}(function(){return function(e,t){function n(t,n){return t===n||e.contains(t,n)}var i=window.kendo,o=i.ui,r=o.Widget,s=i.support,a=i.getOffset,l="open",c="close",u="deactivate",d="activate",h="center",f="left",p="right",g="top",m="bottom",v="absolute",_="hidden",w="body",b="location",y="position",x="visible",k="effects",C="k-state-active",S="k-state-border",T=/k-state-border-(\w+)/,D=".k-picker-wrap, .k-dropdown-wrap, .k-link",A="down",E=e(document.documentElement),P=e(window),M="scroll",I="resize scroll",R=s.transitions.css,B=R+"transform",z=e.extend,L=".kendoPopup",F=["font-size","font-family","font-stretch","font-style","font-weight","line-height"],O=r.extend({init:function(t,n){var o,s=this;n=n||{},n.isRtl&&(n.origin=n.origin||m+" "+p,n.position=n.position||g+" "+p),r.fn.init.call(s,t,n),t=s.element,n=s.options,s.collisions=n.collision?n.collision.split(" "):[],s.downEvent=i.applyEventMap(A,i.guid()),1===s.collisions.length&&s.collisions.push(s.collisions[0]),o=e(s.options.anchor).closest(".k-popup,.k-group").filter(":not([class^=km-])"),n.appendTo=e(e(n.appendTo)[0]||o[0]||w),s.element.hide().addClass("k-popup k-group k-reset").toggleClass("k-rtl",!!n.isRtl).css({position:v}).appendTo(n.appendTo).on("mouseenter"+L,function(){s._hovered=!0}).on("mouseleave"+L,function(){s._hovered=!1}),s.wrapper=e(),n.animation===!1&&(n.animation={open:{effects:{}},close:{hide:!0,effects:{}}}),z(n.animation.open,{complete:function(){s.wrapper.css({overflow:x}),s._activated=!0,s._trigger(d)}}),z(n.animation.close,{complete:function(){s._animationClose()}}),s._mousedownProxy=function(e){s._mousedown(e)},s._resizeProxy=function(e){s._resize(e)},n.toggleTarget&&e(n.toggleTarget).on(n.toggleEvent+L,e.proxy(s.toggle,s))},events:[l,d,c,u],options:{name:"Popup",toggleEvent:"click",origin:m+" "+f,position:g+" "+f,anchor:w,appendTo:null,collision:"flip fit",viewport:window,copyAnchorStyles:!0,autosize:!1,modal:!1,adjustSize:{width:0,height:0},animation:{open:{effects:"slideIn:down",transition:!0,duration:200},close:{duration:100,hide:!0}}},_animationClose:function(){var t,n,o,r,s=this,a=s.options;s.wrapper.hide(),t=s.wrapper.data(b),n=e(a.anchor),t&&s.wrapper.css(t),a.anchor!=w&&(o=((n.attr("class")||"").match(T)||["","down"])[1],r=S+"-"+o,n.removeClass(r).children(D).removeClass(C).removeClass(r),s.element.removeClass(S+"-"+i.directions[o].reverse)),s._closing=!1,s._trigger(u)},destroy:function(){var t,n=this,o=n.options,s=n.element.off(L);r.fn.destroy.call(n),o.toggleTarget&&e(o.toggleTarget).off(L),o.modal||(E.unbind(n.downEvent,n._mousedownProxy),n._toggleResize(!1)),i.destroy(n.element.children()),s.removeData(),o.appendTo[0]===document.body&&(t=s.parent(".k-animation-container"),t[0]?t.remove():s.remove())},open:function(t,n){var o,r,a,c=this,u={isFixed:!isNaN(parseInt(n,10)),x:t,y:n},d=c.element,h=c.options,f="down",p=e(h.anchor),m=d[0]&&d.hasClass("km-widget");if(!c.visible()){if(h.copyAnchorStyles&&(m&&"font-size"==F[0]&&F.shift(),d.css(i.getComputedStyles(p[0],F))),d.data("animating")||c._trigger(l))return;c._activated=!1,h.modal||(E.unbind(c.downEvent,c._mousedownProxy).bind(c.downEvent,c._mousedownProxy),s.mobileOS.ios||s.mobileOS.android||(c._toggleResize(!1),c._toggleResize(!0))),c.wrapper=r=i.wrap(d,h.autosize).css({overflow:_,display:"block",position:v}),s.mobileOS.android&&r.css(B,"translatez(0)"),r.css(y),e(h.appendTo)[0]==document.body&&r.css(g,"-10000px"),o=z(!0,{},h.animation.open),c.flipped=c._position(u),o.effects=i.parseEffects(o.effects,c.flipped),f=o.effects.slideIn?o.effects.slideIn.direction:f,h.anchor!=w&&(a=S+"-"+f,d.addClass(S+"-"+i.directions[f].reverse),p.addClass(a).children(D).addClass(C).addClass(a)),d.data(k,o.effects).kendoStop(!0).kendoAnimate(o)}},position:function(){this.visible()&&this._position()},toggle:function(){var e=this;e[e.visible()?c:l]()},visible:function(){return this.element.is(":"+x)},close:function(n){var o,r,s,a,l=this,u=l.options;if(l.visible()){if(o=l.wrapper[0]?l.wrapper:i.wrap(l.element).hide(),l._toggleResize(!1),l._closing||l._trigger(c))return l._toggleResize(!0),t;l.element.find(".k-popup").each(function(){var t=e(this),i=t.data("kendoPopup");i&&i.close(n)}),E.unbind(l.downEvent,l._mousedownProxy),n?r={hide:!0,effects:{}}:(r=z(!0,{},u.animation.close),s=l.element.data(k),a=r.effects,!a&&!i.size(a)&&s&&i.size(s)&&(r.effects=s,r.reverse=!0),l._closing=!0),l.element.kendoStop(!0),o.css({overflow:_}),l.element.kendoAnimate(r)}},_trigger:function(e){return this.trigger(e,{type:e})},_resize:function(e){var t=this;"resize"===e.type?(clearTimeout(t._resizeTimeout),t._resizeTimeout=setTimeout(function(){t._position(),t._resizeTimeout=null},50)):(!t._hovered||t._activated&&t.element.hasClass("k-list-container"))&&t.close()},_toggleResize:function(e){var t=e?"on":"off";this._scrollableParents()[t](M,this._resizeProxy),P[t](I,this._resizeProxy)},_mousedown:function(t){var o=this,r=o.element[0],s=o.options,a=e(s.anchor)[0],l=s.toggleTarget,c=i.eventTarget(t),u=e(c).closest(".k-popup"),d=u.parent().parent(".km-shim").length;u=u[0],(d||!u||u===o.element[0])&&"popover"!==e(t.target).closest("a").data("rel")&&(n(r,c)||n(a,c)||l&&n(e(l)[0],c)||o.close())},_fit:function(e,t,n){var i=0;return e+t>n&&(i=n-(e+t)),0>e&&(i=-e),i},_flip:function(e,t,n,i,o,r,s){var a=0;return s=s||t,r!==o&&r!==h&&o!==h&&(e+s>i&&(a+=-(n+t)),0>e+a&&(a+=n+t)),a},_scrollableParents:function(){return e(this.options.anchor).parentsUntil("body").filter(function(e,t){return i.isScrollable(t)})},_position:function(t){var n,o,r,l,c,u,d,h,f,p,g,m=this,_=m.element,w=m.wrapper,x=m.options,k=e(x.viewport),C=k.offset(),S=e(x.anchor),T=x.origin.toLowerCase().split(" "),D=x.position.toLowerCase().split(" "),A=m.collisions,E=s.zoomLevel(),P=10002,M=!!(k[0]==window&&window.innerWidth&&1.02>=E),I=0,R=document.documentElement,B=M?window.innerWidth:k.width(),L=M?window.innerHeight:k.height();if(M&&R.scrollHeight-R.clientHeight>0&&(B-=i.support.scrollbar()),n=S.parents().filter(w.siblings()),n[0])if(r=Math.max(+n.css("zIndex"),0))P=r+10;else for(o=S.parentsUntil(n),l=o.length;l>I;I++)r=+e(o[I]).css("zIndex"),r&&r>P&&(P=r+10);return w.css("zIndex",P),w.css(t&&t.isFixed?{left:t.x,top:t.y}:m._align(T,D)),c=a(w,y,S[0]===w.offsetParent()[0]),u=a(w),d=S.offsetParent().parent(".k-animation-container,.k-popup,.k-group"),d.length&&(c=a(w,y,!0),u=a(w)),k[0]===window?(u.top-=window.pageYOffset||document.documentElement.scrollTop||0,u.left-=window.pageXOffset||document.documentElement.scrollLeft||0):(u.top-=C.top,u.left-=C.left),m.wrapper.data(b)||w.data(b,z({},c)),h=z({},u),f=z({},c),p=x.adjustSize,"fit"===A[0]&&(f.top+=m._fit(h.top,w.outerHeight()+p.height,L/E)),"fit"===A[1]&&(f.left+=m._fit(h.left,w.outerWidth()+p.width,B/E)),g=z({},f),"flip"===A[0]&&(f.top+=m._flip(h.top,_.outerHeight(),S.outerHeight(),L/E,T[0],D[0],w.outerHeight())),"flip"===A[1]&&(f.left+=m._flip(h.left,_.outerWidth(),S.outerWidth(),B/E,T[1],D[1],w.outerWidth())),_.css(y,v),w.css(f),f.left!=g.left||f.top!=g.top},_align:function(t,n){var i,o=this,r=o.wrapper,s=e(o.options.anchor),l=t[0],c=t[1],u=n[0],d=n[1],f=a(s),g=e(o.options.appendTo),v=r.outerWidth(),_=r.outerHeight(),w=s.outerWidth(),b=s.outerHeight(),y=f.top,x=f.left,k=Math.round;return g[0]!=document.body&&(i=a(g),y-=i.top,x-=i.left),l===m&&(y+=b),l===h&&(y+=k(b/2)),u===m&&(y-=_),u===h&&(y-=k(_/2)),c===p&&(x+=w),c===h&&(x+=k(w/2)),d===p&&(x-=v),d===h&&(x-=k(v/2)),{top:y,left:x}}});o.plugin(O)}(window.kendo.jQuery),window.kendo},"function"==typeof define&&define.amd?define:function(e,t){t()});