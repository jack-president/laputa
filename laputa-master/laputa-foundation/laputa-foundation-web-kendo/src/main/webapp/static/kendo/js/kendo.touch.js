/*
* Kendo UI v2015.3.930 (http://www.telerik.com/kendo-ui)
* Copyright 2015 Telerik AD. All rights reserved.
*
* Kendo UI commercial licenses may be obtained at
* http://www.telerik.com/purchase/license-agreement/kendo-ui-complete
* If you do not own a commercial license, this file shall be governed by the trial license terms.
*/
!function(e,define){define(["./kendo.core.min","./kendo.userevents.min"],e)}(function(){return function(e){var t=window.kendo,n=t.ui.Widget,i=e.proxy,o=Math.abs,r=20,a=n.extend({init:function(e,o){function r(e){return function(t){s._triggerTouch(e,t)}}function a(e){return function(t){s.trigger(e,{touches:t.touches,distance:t.distance,center:t.center,event:t.event})}}var s=this;n.fn.init.call(s,e,o),o=s.options,e=s.element,s.wrapper=e,s.events=new t.UserEvents(e,{filter:o.filter,surface:o.surface,minHold:o.minHold,multiTouch:o.multiTouch,allowSelection:!0,press:r("touchstart"),hold:r("hold"),tap:i(s,"_tap"),gesturestart:a("gesturestart"),gesturechange:a("gesturechange"),gestureend:a("gestureend")}),o.enableSwipe?(s.events.bind("start",i(s,"_swipestart")),s.events.bind("move",i(s,"_swipemove"))):(s.events.bind("start",i(s,"_dragstart")),s.events.bind("move",r("drag")),s.events.bind("end",r("dragend"))),t.notify(s)},events:["touchstart","dragstart","drag","dragend","tap","doubletap","hold","swipe","gesturestart","gesturechange","gestureend"],options:{name:"Touch",surface:null,global:!1,multiTouch:!1,enableSwipe:!1,minXDelta:30,maxYDelta:20,maxDuration:1e3,minHold:800,doubleTapTimeout:800},cancel:function(){this.events.cancel()},_triggerTouch:function(e,t){this.trigger(e,{touch:t.touch,event:t.event})&&t.preventDefault()},_tap:function(e){var n=this,i=n.lastTap,o=e.touch;i&&n.options.doubleTapTimeout>o.endTime-i.endTime&&t.touchDelta(o,i).distance<r?(n._triggerTouch("doubletap",e),n.lastTap=null):(n._triggerTouch("tap",e),n.lastTap=o)},_dragstart:function(e){this._triggerTouch("dragstart",e)},_swipestart:function(e){2*o(e.x.velocity)>=o(e.y.velocity)&&e.sender.capture()},_swipemove:function(e){var t=this,n=t.options,i=e.touch,r=e.event.timeStamp-i.startTime,a=i.x.initialDelta>0?"right":"left";o(i.x.initialDelta)>=n.minXDelta&&o(i.y.initialDelta)<n.maxYDelta&&n.maxDuration>r&&(t.trigger("swipe",{direction:a,touch:e.touch}),i.cancel())}});t.ui.plugin(a)}(window.kendo.jQuery),window.kendo},"function"==typeof define&&define.amd?define:function(e,t){t()});