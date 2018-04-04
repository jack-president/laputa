/*
* Kendo UI v2015.3.930 (http://www.telerik.com/kendo-ui)
* Copyright 2015 Telerik AD. All rights reserved.
*
* Kendo UI commercial licenses may be obtained at
* http://www.telerik.com/purchase/license-agreement/kendo-ui-complete
* If you do not own a commercial license, this file shall be governed by the trial license terms.
*/
!function(e,define){define(["./kendo.core.min"],e)}(function(){return function(e){function t(){this.node=null}function n(){}function i(e,t,n){this.nodeName=e,this.attr=t||{},this.children=n||[]}function o(e){this.nodeValue=e}function r(e){this.html=e}function s(e,t){for(d.innerHTML=t;d.firstChild;)e.appendChild(d.firstChild)}function a(e){return new r(e)}function l(e,t,n){return new i(e,t,n)}function c(e){return new o(e)}function h(e){this.root=e,this.children=[]}var u,d;t.prototype={remove:function(){this.node.parentNode.removeChild(this.node),this.attr={}},attr:{},text:function(){return""}},n.prototype={nodeName:"#null",attr:{style:{}},children:[],remove:function(){}},u=new n,i.prototype=new t,i.prototype.appendTo=function(e){var t,n=document.createElement(this.nodeName),i=this.children;for(t=0;i.length>t;t++)i[t].render(n,u);return e.appendChild(n),n},i.prototype.render=function(e,t){var n,i,o,r,s,a;if(t.nodeName!==this.nodeName)t.remove(),n=this.appendTo(e);else{if(n=t.node,o=this.children,r=o.length,s=t.children,a=s.length,Math.abs(a-r)>2)return void this.render({appendChild:function(n){e.replaceChild(n,t.node)}},u);for(i=0;r>i;i++)o[i].render(n,s[i]||u);for(i=r;a>i;i++)s[i].remove()}this.node=n,this.syncAttributes(t.attr),this.removeAttributes(t.attr)},i.prototype.syncAttributes=function(e){var t,n,i,o=this.attr;for(t in o)n=o[t],i=e[t],"style"===t?this.setStyle(n,i):n!==i&&this.setAttribute(t,n,i)},i.prototype.setStyle=function(e,t){var n,i=this.node;if(t)for(n in e)e[n]!==t[n]&&(i.style[n]=e[n]);else for(n in e)i.style[n]=e[n]},i.prototype.removeStyle=function(e){var t,n=this.attr.style||{},i=this.node;for(t in e)void 0===n[t]&&(i.style[t]="")},i.prototype.removeAttributes=function(e){var t,n=this.attr;for(t in e)"style"===t?this.removeStyle(e.style):void 0===n[t]&&this.removeAttribute(t)},i.prototype.removeAttribute=function(e){var t=this.node;"style"===e?t.style.cssText="":"className"===e?t.className="":t.removeAttribute(e)},i.prototype.setAttribute=function(e,t){var n=this.node;void 0!==n[e]?n[e]=t:n.setAttribute(e,t)},i.prototype.text=function(){var e,t="";for(e=0;this.children.length>e;++e)t+=this.children[e].text();return t},o.prototype=new t,o.prototype.nodeName="#text",o.prototype.render=function(e,t){var n;t.nodeName!==this.nodeName?(t.remove(),n=document.createTextNode(this.nodeValue),e.appendChild(n)):(n=t.node,this.nodeValue!==t.nodeValue&&(n.nodeValue=this.nodeValue)),this.node=n},o.prototype.text=function(){return this.nodeValue},r.prototype={nodeName:"#html",attr:{},remove:function(){for(var e=0;this.nodes.length>e;e++)this.nodes[e].parentNode.removeChild(this.nodes[e])},render:function(e,t){var n,i;if(t.nodeName!==this.nodeName||t.html!==this.html)for(t.remove(),n=e.lastChild,s(e,this.html),this.nodes=[],i=n?n.nextSibling:e.firstChild;i;i=i.nextSibling)this.nodes.push(i);else this.nodes=t.nodes.slice(0)}},d=document.createElement("div"),h.prototype={html:a,element:l,text:c,render:function(e){var t,n,i=this.children;for(t=0,n=e.length;n>t;t++)e[t].render(this.root,i[t]||u);for(t=n;i.length>t;t++)i[t].remove();this.children=e}},e.dom={html:a,text:c,element:l,Tree:h,Node:t}}(window.kendo),window.kendo},"function"==typeof define&&define.amd?define:function(e,t){t()});