/*
* Kendo UI v2015.3.930 (http://www.telerik.com/kendo-ui)
* Copyright 2015 Telerik AD. All rights reserved.
*
* Kendo UI commercial licenses may be obtained at
* http://www.telerik.com/purchase/license-agreement/kendo-ui-complete
* If you do not own a commercial license, this file shall be governed by the trial license terms.
*/
!function(e,define){define(["./kendo.core.min"],e)}(function(){return function(e,t){var n=window.kendo,i=e.isArray,r=e.isPlainObject,o=e.map,a=e.each,s=e.extend,l=n.getter,c=n.Class,u=c.extend({init:function(t){var l,c,u,d,h=this,f=t.total,p=t.model,g=t.parse,m=t.errors,v=t.serialize,_=t.data;p&&(r(p)&&(l=t.modelBase||n.data.Model,p.fields&&a(p.fields,function(t,n){r(n)&&n.field?e.isFunction(n.field)||(n=s(n,{field:h.getter(n.field)})):n={field:h.getter(n)},p.fields[t]=n}),c=p.id,c&&(u={},u[h.xpathToMember(c,!0)]={field:h.getter(c)},p.fields=s(u,p.fields),p.id=h.xpathToMember(c)),p=l.define(p)),h.model=p),f&&("string"==typeof f?(f=h.getter(f),h.total=function(e){return parseInt(f(e),10)}):"function"==typeof f&&(h.total=f)),m&&("string"==typeof m?(m=h.getter(m),h.errors=function(e){return m(e)||null}):"function"==typeof m&&(h.errors=m)),_&&("string"==typeof _?(_=h.xpathToMember(_),h.data=function(e){var t,n=h.evaluate(e,_);return n=i(n)?n:[n],h.model&&p.fields?(t=new h.model,o(n,function(e){if(e){var n,i={};for(n in p.fields)i[n]=t._parse(n,p.fields[n].field(e));return i}})):n}):"function"==typeof _&&(h.data=_)),"function"==typeof g&&(d=h.parse,h.parse=function(e){var t=g.call(h,e);return d.call(h,t)}),"function"==typeof v&&(h.serialize=v)},total:function(e){return this.data(e).length},errors:function(e){return e?e.errors:null},serialize:function(e){return e},parseDOM:function(e){var n,r,o,a,s,l,c,u={},d=e.attributes,h=d.length;for(c=0;h>c;c++)l=d[c],u["@"+l.nodeName]=l.nodeValue;for(r=e.firstChild;r;r=r.nextSibling)o=r.nodeType,3===o||4===o?u["#text"]=r.nodeValue:1===o&&(n=this.parseDOM(r),a=r.nodeName,s=u[a],i(s)?s.push(n):s=s!==t?[s,n]:n,u[a]=s);return u},evaluate:function(e,t){for(var n,r,o,a,s,l=t.split(".");n=l.shift();)if(e=e[n],i(e)){for(r=[],t=l.join("."),s=0,o=e.length;o>s;s++)a=this.evaluate(e[s],t),a=i(a)?a:[a],r.push.apply(r,a);return r}return e},parse:function(t){var n,i,r={};return n=t.documentElement||e.parseXML(t).documentElement,i=this.parseDOM(n),r[n.nodeName]=i,r},xpathToMember:function(e,t){return e?(e=e.replace(/^\//,"").replace(/\//g,"."),e.indexOf("@")>=0?e.replace(/\.?(@.*)/,t?"$1":'["$1"]'):e.indexOf("text()")>=0?e.replace(/(\.?text\(\))/,t?"#text":'["#text"]'):e):""},getter:function(e){return l(this.xpathToMember(e),!0)}});e.extend(!0,n.data,{XmlDataReader:u,readers:{xml:u}})}(window.kendo.jQuery),window.kendo},"function"==typeof define&&define.amd?define:function(e,t){t()});