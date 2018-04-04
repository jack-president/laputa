/*
* Kendo UI v2015.3.930 (http://www.telerik.com/kendo-ui)
* Copyright 2015 Telerik AD. All rights reserved.
*
* Kendo UI commercial licenses may be obtained at
* http://www.telerik.com/purchase/license-agreement/kendo-ui-complete
* If you do not own a commercial license, this file shall be governed by the trial license terms.
*/
!function(e,define){define(["./kendo.core.min"],e)}(function(){return function(e,t){function n(i,o){var l,c,u,d,h,f,p,g,m=[],v=i.logic||"and",_=i.filters;for(l=0,c=_.length;c>l;l++)i=_[l],u=i.field,p=i.value,f=i.operator,i.filters?i=n(i,o):(g=i.ignoreCase,u=u.replace(/\./g,"/"),i=a[f],o&&(i=s[f]),i&&p!==t&&(d=e.type(p),"string"===d?(h="'{1}'",p=p.replace(/'/g,"''"),g===!0&&(u="tolower("+u+")")):h="date"===d?o?"{1:yyyy-MM-ddTHH:mm:ss+00:00}":"datetime'{1:yyyy-MM-ddTHH:mm:ss}'":"{1}",i.length>3?"substringof"!==i?h="{0}({2},"+h+")":(h="{0}("+h+",{2})","doesnotcontain"===f&&(o?(h="{0}({2},'{1}') eq -1",i="indexof"):h+=" eq false")):h="{2} {0} "+h,i=r.format(h,i,p,u))),m.push(i);return i=m.join(" "+v+" "),m.length>1&&(i="("+i+")"),i}function i(e){for(var t in e)0===t.indexOf("@odata")&&delete e[t]}var r=window.kendo,o=e.extend,a={eq:"eq",neq:"ne",gt:"gt",gte:"ge",lt:"lt",lte:"le",contains:"substringof",doesnotcontain:"substringof",endswith:"endswith",startswith:"startswith"},s=o({},a,{contains:"contains"}),l={pageSize:e.noop,page:e.noop,filter:function(e,t,i){t&&(t=n(t,i),t&&(e.$filter=t))},sort:function(t,n){var i=e.map(n,function(e){var t=e.field.replace(/\./g,"/");return"desc"===e.dir&&(t+=" desc"),t}).join(",");i&&(t.$orderby=i)},skip:function(e,t){t&&(e.$skip=t)},take:function(e,t){t&&(e.$top=t)}},c={read:{dataType:"jsonp"}};o(!0,r.data,{schemas:{odata:{type:"json",data:function(e){return e.d.results||[e.d]},total:"d.__count"}},transports:{odata:{read:{cache:!0,dataType:"jsonp",jsonp:"$callback"},update:{cache:!0,dataType:"json",contentType:"application/json",type:"PUT"},create:{cache:!0,dataType:"json",contentType:"application/json",type:"POST"},destroy:{cache:!0,dataType:"json",type:"DELETE"},parameterMap:function(e,t,n){var i,o,a,s;if(e=e||{},t=t||"read",s=(this.options||c)[t],s=s?s.dataType:"json","read"===t){i={$inlinecount:"allpages"},"json"!=s&&(i.$format="json");for(a in e)l[a]?l[a](i,e[a],n):i[a]=e[a]}else{if("json"!==s)throw Error("Only json dataType can be used for "+t+" operation.");if("destroy"!==t){for(a in e)o=e[a],"number"==typeof o&&(e[a]=o+"");i=r.stringify(e)}}return i}}}}),o(!0,r.data,{schemas:{"odata-v4":{type:"json",data:function(t){return t=e.extend({},t),i(t),t.value?t.value:[t]},total:function(e){return e["@odata.count"]}}},transports:{"odata-v4":{read:{cache:!0,dataType:"json"},update:{cache:!0,dataType:"json",contentType:"application/json;IEEE754Compatible=true",type:"PUT"},create:{cache:!0,dataType:"json",contentType:"application/json;IEEE754Compatible=true",type:"POST"},destroy:{cache:!0,dataType:"json",type:"DELETE"},parameterMap:function(e,t){var n=r.data.transports.odata.parameterMap(e,t,!0);return"read"==t&&(n.$count=!0,delete n.$inlinecount),n}}}})}(window.kendo.jQuery),window.kendo},"function"==typeof define&&define.amd?define:function(e,t){t()});