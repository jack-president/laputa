/*
* Kendo UI v2015.3.930 (http://www.telerik.com/kendo-ui)
* Copyright 2015 Telerik AD. All rights reserved.
*
* Kendo UI commercial licenses may be obtained at
* http://www.telerik.com/purchase/license-agreement/kendo-ui-complete
* If you do not own a commercial license, this file shall be governed by the trial license terms.
*/
!function(e,define){define(["./kendo.core.min"],e)}(function(){return function(e,t){function n(t){return e.map(i(t),function(e){return e.name}).join(", ")}function i(e){var t=e[0];return t.files?o(t.files):[{name:s(t.value),extension:a(t.value),size:null}]}function o(t){return e.map(t,function(e){return r(e)})}function r(e){var t=e.name||e.fileName;return{name:y.htmlEncode(t),extension:a(t),size:e.size||e.fileSize,rawFile:e}}function a(e){var t=e.match(k);return t?t[0]:""}function s(e){var t=e.lastIndexOf("\\");return-1!=t?e.substr(t+1):e}function l(t,n){var i=y.guid();return e.map(t,function(e){return e.uid=n?y.guid():i,e})}function c(t){return!t.multiple&&e(".k-file",t.wrapper).length>1}function u(n,i,o){var r,a;return i._supportsRemove()?(r=n.data("fileNames"),a=e.map(r,function(e){return e.name}),i._submitRemove(a,o,function(e,t,o){i._removeFileEntry(n),i.trigger(A,{operation:"remove",files:r,response:e,XMLHttpRequest:o})},function(e){c(i)&&i._removeFileEntry(n),i.trigger(D,{operation:"remove",files:r,XMLHttpRequest:e}),x("Server response: "+e.responseText)}),t):(c(i)&&i._removeFileEntry(n),t)}function d(t,n,i){var o=!1,r="";try{r=e.parseJSON(h(t)),o=!0}catch(a){i()}o&&n(r)}function h(e){return(t===e||""===e)&&(e="{}"),e}function f(e){e.stopPropagation(),e.preventDefault()}function p(e,t,n,i){var o,r;e.on("dragenter"+t,function(){n(),r=new Date,o||(o=setInterval(function(){var e=new Date-r;e>100&&(i(),clearInterval(o),o=null)},100))}).on("dragover"+t,function(){r=new Date})}function g(e){return e.is(".k-file-progress, .k-file-success, .k-file-error")}function m(t){return e(t.target).closest(".k-file")}var v,_,y=window.kendo,b=y.ui.Widget,w=y.antiForgeryTokens,x=y.logToConsole,k=/\.([^\.]+)$/,C=".kendoUpload",S="select",T="upload",A="success",D="error",E="complete",M="cancel",P="progress",I="remove",z=b.extend({init:function(t,n){var i,o,r,a=this;b.fn.init.call(a,t,n),a.name=t.name,a.multiple=a.options.multiple,a.localization=a.options.localization,i=a.element,a.wrapper=i.closest(".k-upload"),0===a.wrapper.length&&(a.wrapper=a._wrapInput(i)),a._activeInput(i),a.toggle(a.options.enabled),o=a._ns=C+"-"+y.guid(),i.closest("form").on("submit"+o,e.proxy(a._onParentFormSubmit,a)).on("reset"+o,e.proxy(a._onParentFormReset,a)),a.options.async.saveUrl?(a._module=a._supportsFormData()?new _(a):new v(a),a._async=!0,r=a.options.files,r.length>0&&a._renderInitialFiles(r)):a._module=new R(a),a._supportsDrop()&&a._setupDropZone(),a.wrapper.on("click",".k-upload-action",e.proxy(a._onFileAction,a)).on("click",".k-upload-selected",e.proxy(a._onUploadSelected,a)),a.element.val()&&a._onInputChange({target:a.element})},events:[S,T,A,D,E,M,P,I],options:{name:"Upload",enabled:!0,multiple:!0,showFileList:!0,template:"",files:[],async:{removeVerb:"POST",autoUpload:!0,withCredentials:!0},localization:{select:"Select files...",cancel:"Cancel",retry:"Retry",remove:"Remove",uploadSelectedFiles:"Upload files",dropFilesHere:"drop files here to upload",statusUploading:"uploading",statusUploaded:"uploaded",statusWarning:"warning",statusFailed:"failed",headerStatusUploading:"Uploading...",headerStatusUploaded:"Done"}},setOptions:function(e){var t=this,n=t.element;b.fn.setOptions.call(t,e),t.multiple=t.options.multiple,n.attr("multiple",t._supportsMultiple()?t.multiple:!1),t.toggle(t.options.enabled)},enable:function(e){e=t===e?!0:e,this.toggle(e)},disable:function(){this.toggle(!1)},toggle:function(e){e=t===e?e:!e,this.wrapper.toggleClass("k-state-disabled",e),this.element.prop("disabled",e)},destroy:function(){var t=this;e(document).add(e(".k-dropzone",t.wrapper)).add(t.wrapper.closest("form")).off(t._ns),e(t.element).off(C),b.fn.destroy.call(t)},_addInput:function(t){if(t[0].nodeType){var n=this,i=t.clone().val("");i.insertAfter(n.element).data("kendoUpload",n),e(n.element).hide().attr("tabindex","-1").removeAttr("id").off(C),n._activeInput(i),n.element.focus()}},_activeInput:function(t){var n=this,i=n.wrapper;n.element=t,t.attr("multiple",n._supportsMultiple()?n.multiple:!1).attr("autocomplete","off").on("click"+C,function(e){i.hasClass("k-state-disabled")&&e.preventDefault()}).on("focus"+C,function(){e(this).parent().addClass("k-state-focused")}).on("blur"+C,function(){e(this).parent().removeClass("k-state-focused")}).on("change"+C,e.proxy(n._onInputChange,n)).on("keydown"+C,e.proxy(n._onInputKeyDown,n))},_onInputKeyDown:function(e){var t=this,n=t.wrapper.find(".k-upload-action:first");e.keyCode===y.keys.TAB&&n.length>0&&(e.preventDefault(),n.focus())},_onInputChange:function(t){var n=this,i=e(t.target),o=l(n._inputFiles(i),n._isAsyncNonBatch()),r=n.trigger(S,{files:o});r?(n._addInput(i),i.remove()):n._module.onSelect({target:i},o)},_onDrop:function(t){var n,i=t.originalEvent.dataTransfer,r=this,a=i.files,s=l(o(a),r._isAsyncNonBatch());f(t),a.length>0&&!r.wrapper.hasClass("k-state-disabled")&&(!r.multiple&&s.length>1&&s.splice(1,s.length-1),n=r.trigger(S,{files:s}),n||r._module.onSelect({target:e(".k-dropzone",r.wrapper)},s))},_isAsyncNonBatch:function(){return this._async&&!this.options.async.batch||!1},_renderInitialFiles:function(t){var n,i,o=this,r=0;for(t=l(t,!0),r=0;t.length>r;r++)n=t[r],i=o._enqueueFile(n.name,{fileNames:[n]}),i.addClass("k-file-success").data("files",[t[r]]),e(".k-progress",i).width("100%"),o.options.template||e(".k-upload-status",i).prepend("<span class='k-upload-pct'>100%</span>"),o._supportsRemove()&&o._fileAction(i,I)},_prepareTemplateData:function(e,t){var n=t.fileNames,i={},o=0,r=0;for(r=0;n.length>r;r++)o+=n[r].size;return i.name=e,i.size=o,i.files=t.fileNames,i},_prepareDefaultFileEntryTemplate:function(t,n){var i="",o=e("<li class='k-file'><span class='k-progress'></span><span class='k-icon'></span><span class='k-filename' title='"+t+"'>"+t+"</span><strong class='k-upload-status'></strong></li>");return 1==n.fileNames.length&&n.fileNames[0].extension&&(i=n.fileNames[0].extension.substring(1),e(".k-icon",o).addClass("k-i-"+i)),o},_enqueueFile:function(t,n){var i,o,r,a,s=this,l=n.fileNames[0].uid,c=e(".k-upload-files",s.wrapper),u=s.options,d=u.template;return 0===c.length&&(c=e("<ul class='k-upload-files k-reset'></ul>").appendTo(s.wrapper),s.options.showFileList||c.hide(),s.wrapper.removeClass("k-upload-empty")),i=e(".k-file",c),d?(r=s._prepareTemplateData(t,n),d=y.template(d),o=e("<li class='k-file'>"+d(r)+"</li>"),o.find(".k-upload-action").addClass("k-button k-button-bare"),s.angular("compile",function(){return{elements:o,data:[r]}})):o=s._prepareDefaultFileEntryTemplate(t,n),o.attr(y.attr("uid"),l).appendTo(c).data(n),s._async||e(".k-progress",o).width("100%"),!s.multiple&&i.length>0&&(a={files:i.data("fileNames")},s.trigger(I,a)||s._module.onRemove({target:e(i,s.wrapper)},a.data)),o},_removeFileEntry:function(t){var n,i,o=this,r=t.closest(".k-upload-files");t.remove(),n=e(".k-file",r),i=e(".k-file-success, .k-file-error",r),i.length===n.length&&this._hideUploadButton(),0===n.length&&(r.remove(),o.wrapper.addClass("k-upload-empty"),o._hideHeaderUploadstatus())},_fileAction:function(e,t){var n={remove:"k-delete",cancel:"k-cancel",retry:"k-retry"},i={remove:"k-i-close",cancel:"k-i-close",retry:"k-i-refresh"};n.hasOwnProperty(t)&&(this._clearFileAction(e),this.options.template?e.find(".k-upload-action").addClass("k-button k-button-bare").append("<span class='k-icon "+i[t]+" "+n[t]+"' title='"+this.localization[t]+"'></span>").show():(e.find(".k-upload-status .k-upload-action").remove(),e.find(".k-upload-status").append(this._renderAction(n[t],this.localization[t],i[t]))))},_fileState:function(t,n){var i=this.localization,o={uploading:{text:i.statusUploading},uploaded:{text:i.statusUploaded},failed:{text:i.statusFailed}},r=o[n];r&&e(".k-icon:not(.k-delete, .k-cancel, .k-retry)",t).text(r.text)},_renderAction:function(t,n,i){return e(""!==t?"<button type='button' class='k-button k-button-bare k-upload-action'><span class='k-icon "+i+" "+t+"' title='"+n+"'></span></button>":"<button type='button' class='k-button'>"+n+"</button>")},_clearFileAction:function(t){e(".k-upload-action",t).empty().hide()},_onFileAction:function(t){var n,i,o,r,a=this;return a.wrapper.hasClass("k-state-disabled")||(n=e(t.target).closest(".k-upload-action"),i=n.find(".k-icon"),o=n.closest(".k-file"),r={files:o.data("fileNames")},i.hasClass("k-delete")?a.trigger(I,r)||a._module.onRemove({target:e(o,a.wrapper)},r.data):i.hasClass("k-cancel")?(a.trigger(M,r),a._module.onCancel({target:e(o,a.wrapper)}),this._checkAllComplete(),a._updateHeaderUploadStatus()):i.hasClass("k-retry")&&(e(".k-warning",o).remove(),a._module.onRetry({target:e(o,a.wrapper)}))),!1},_onUploadSelected:function(){var e=this,t=e.wrapper;return t.hasClass("k-state-disabled")||this._module.onSaveSelected(),!1},_onFileProgress:function(t,n){var i;n>100&&(n=100),this.options.template?e(".k-progress",t.target).width(n+"%"):(i=e(".k-upload-pct",t.target),0===i.length&&e(".k-upload-status",t.target).prepend("<span class='k-upload-pct'></span>"),e(".k-upload-pct",t.target).text(n+"%"),e(".k-progress",t.target).width(n+"%")),this.trigger(P,{files:m(t).data("fileNames"),percentComplete:n})},_onUploadSuccess:function(e,t,n){var i=m(e);this._fileState(i,"uploaded"),i.removeClass("k-file-progress").addClass("k-file-success"),this._updateHeaderUploadStatus(),this.trigger(A,{files:i.data("fileNames"),response:t,operation:"upload",XMLHttpRequest:n}),this._supportsRemove()?this._fileAction(i,I):this._clearFileAction(i),this._checkAllComplete()},_onUploadError:function(t,n){var i=m(t),o=e(".k-upload-pct",i);this._fileState(i,"failed"),i.removeClass("k-file-progress").addClass("k-file-error"),e(".k-progress",i).width("100%"),o.length>0?o.empty().removeClass("k-upload-pct").addClass("k-icon k-warning"):e(".k-upload-status",i).prepend("<span class='k-icon k-warning'></span>"),this._updateHeaderUploadStatus(),this._fileAction(i,"retry"),this.trigger(D,{operation:"upload",files:i.data("fileNames"),XMLHttpRequest:n}),x("Server response: "+n.responseText),this._checkAllComplete()},_showUploadButton:function(){var t=e(".k-upload-selected",this.wrapper);0===t.length&&(t=this._renderAction("",this.localization.uploadSelectedFiles).addClass("k-upload-selected")),this.wrapper.append(t)},_hideUploadButton:function(){e(".k-upload-selected",this.wrapper).remove()},_showHeaderUploadStatus:function(){var t=this.localization,n=e(".k-dropzone",this.wrapper),i=e(".k-upload-status-total",this.wrapper);0!==i.length&&i.remove(),i='<strong class="k-upload-status k-upload-status-total">'+t.headerStatusUploading+'<span class="k-icon k-loading">'+t.statusUploading+"</span></strong>",n.length>0?n.append(i):e(".k-upload-button",this.wrapper).after(i)},_updateHeaderUploadStatus:function(){var t,n,i,o=this,r=o.localization,a=e(".k-file",o.wrapper).not(".k-file-success, .k-file-error");0===a.length&&(t=e(".k-file.k-file-error",o.wrapper),n=e(".k-upload-status-total",o.wrapper),i=e(".k-icon",n).removeClass("k-loading").addClass(0!==t.length?"k-warning":"k-i-tick").text(0!==t.length?r.statusWarning:r.statusUploaded),n.text(o.localization.headerStatusUploaded).append(i))},_hideHeaderUploadstatus:function(){e(".k-upload-status-total",this.wrapper).remove()},_onParentFormSubmit:function(){var n,i=this,o=i.element;t!==this._module.onAbort&&this._module.onAbort(),o.value||(n=e(o),n.attr("disabled","disabled"),window.setTimeout(function(){n.removeAttr("disabled")},0))},_onParentFormReset:function(){e(".k-upload-files",this.wrapper).remove()},_supportsFormData:function(){return"undefined"!=typeof FormData},_supportsMultiple:function(){var e=this._userAgent().indexOf("Windows")>-1;return!(y.support.browser.opera||y.support.browser.safari&&e)},_supportsDrop:function(){var e=this._userAgent().toLowerCase(),t=/chrome/.test(e),n=!t&&/safari/.test(e),i=n&&/windows/.test(e);return!i&&this._supportsFormData()&&this.options.async.saveUrl},_userAgent:function(){return navigator.userAgent},_setupDropZone:function(){var t,n,i=this;e(".k-upload-button",this.wrapper).wrap("<div class='k-dropzone'></div>"),t=i._ns,n=e(".k-dropzone",i.wrapper).append(e("<em>"+i.localization.dropFilesHere+"</em>")).on("dragenter"+t,f).on("dragover"+t,function(e){e.preventDefault()}).on("drop"+t,e.proxy(this._onDrop,this)),p(n,t,function(){n.closest(".k-upload").hasClass("k-state-disabled")||n.addClass("k-dropzone-hovered")},function(){n.removeClass("k-dropzone-hovered")}),p(e(document),t,function(){n.closest(".k-upload").hasClass("k-state-disabled")||(n.addClass("k-dropzone-active"),n.closest(".k-upload").removeClass("k-upload-empty"))},function(){n.removeClass("k-dropzone-active"),0===e("li.k-file",n.closest(".k-upload")).length&&n.closest(".k-upload").addClass("k-upload-empty")})},_supportsRemove:function(){return!!this.options.async.removeUrl},_submitRemove:function(t,n,i,o){var r=this,a=r.options.async.removeField||"fileNames",s=e.extend(n,w());s[a]=t,jQuery.ajax({type:this.options.async.removeVerb,dataType:"json",dataFilter:h,url:this.options.async.removeUrl,traditional:!0,data:s,success:i,error:o,xhrFields:{withCredentials:this.options.async.withCredentials}})},_wrapInput:function(e){var t=this,n=t.options;return e.wrap("<div class='k-widget k-upload k-header'><div class='k-button k-upload-button'></div></div>"),n.async.saveUrl||e.closest(".k-upload").addClass("k-upload-sync"),e.closest(".k-upload").addClass("k-upload-empty"),e.closest(".k-button").append("<span>"+this.localization.select+"</span>"),e.closest(".k-upload")},_checkAllComplete:function(){0===e(".k-file.k-file-progress",this.wrapper).length&&this.trigger(E)},_inputFiles:function(e){return i(e)}}),R=function(e){this.name="syncUploadModule",this.element=e.wrapper,this.upload=e,this.element.closest("form").attr("enctype","multipart/form-data").attr("encoding","multipart/form-data")};R.prototype={onSelect:function(t,i){var o,r=this.upload,a=e(t.target);r._addInput(a),o=r._enqueueFile(n(a),{relatedInput:a,fileNames:i}),r._fileAction(o,I)},onRemove:function(e){var t=m(e);t.data("relatedInput").remove(),this.upload._removeFileEntry(t)}},v=function(e){this.name="iframeUploadModule",this.element=e.wrapper,this.upload=e,this.iframes=[]},z._frameId=0,v.prototype={onSelect:function(t,n){var i=this.upload,o=e(t.target),r=this.prepareUpload(o,n);i.options.async.autoUpload?this.performUpload(r):(i._supportsRemove()&&this.upload._fileAction(r,I),i._showUploadButton())},prepareUpload:function(t,i){var o,r,a,s=this.upload,l=e(s.element),c=s.options.async.saveField||t.attr("name");return s._addInput(t),t.attr("name",c),o=this.createFrame(s.name+"_"+z._frameId++),this.registerFrame(o),r=this.createForm(s.options.async.saveUrl,o.attr("name")).append(l),a=s._enqueueFile(n(t),{frame:o,relatedInput:l,fileNames:i}),o.data({form:r,file:a}),a},performUpload:function(t){var n,i,o,r={files:t.data("fileNames")},a=t.data("frame"),s=this.upload;if(s.trigger(T,r))s._removeFileEntry(a.data("file")),this.cleanupFrame(a),this.unregisterFrame(a);else{s._hideUploadButton(),s._showHeaderUploadStatus(),a.appendTo(document.body),n=a.data("form").attr("action",s.options.async.saveUrl).appendTo(document.body),r.data=e.extend({},r.data,w());for(i in r.data)o=n.find("input[name='"+i+"']"),0===o.length&&(o=e("<input>",{type:"hidden",name:i}).prependTo(n)),o.val(r.data[i]);s._fileAction(t,M),s._fileState(t,"uploading"),e(t).removeClass("k-file-error").addClass("k-file-progress"),a.one("load",e.proxy(this.onIframeLoad,this)),n[0].submit()}},onSaveSelected:function(){var t=this;e(".k-file",this.element).each(function(){var n=e(this),i=g(n);i||t.performUpload(n)})},onIframeLoad:function(t){var n,i=e(t.target);try{n=i.contents().text()}catch(o){n="Error trying to get server response: "+o}this.processResponse(i,n)},processResponse:function(t,n){var i=t.data("file"),o=this,r={responseText:n};d(n,function(n){e.extend(r,{statusText:"OK",status:"200"}),o.upload._onFileProgress({target:e(i,o.upload.wrapper)},100),o.upload._onUploadSuccess({target:e(i,o.upload.wrapper)},n,r),o.cleanupFrame(t),o.unregisterFrame(t)},function(){e.extend(r,{statusText:"error",status:"500"}),o.upload._onUploadError({target:e(i,o.upload.wrapper)},r)})},onCancel:function(t){var n=e(t.target).data("frame");this.stopFrameSubmit(n),this.cleanupFrame(n),this.unregisterFrame(n),this.upload._removeFileEntry(n.data("file"))},onRetry:function(e){var t=m(e);this.performUpload(t)},onRemove:function(e,t){var n=m(e),i=n.data("frame");i?(this.unregisterFrame(i),this.upload._removeFileEntry(n),this.cleanupFrame(i)):u(n,this.upload,t)},onAbort:function(){var t=this.element,n=this;e.each(this.iframes,function(){e("input",this.data("form")).appendTo(t),n.stopFrameSubmit(this[0]),this.data("form").remove(),this.remove()}),this.iframes=[]},createFrame:function(t){return e("<iframe name='"+t+"' id='"+t+"' style='display:none;' />")},createForm:function(t,n){return e("<form enctype='multipart/form-data' method='POST' action='"+t+"' target='"+n+"'/>")},stopFrameSubmit:function(e){t!==e.stop?e.stop():e.document&&e.document.execCommand("Stop")},registerFrame:function(e){this.iframes.push(e)},unregisterFrame:function(t){this.iframes=e.grep(this.iframes,function(e){return e.attr("name")!=t.attr("name")})},cleanupFrame:function(e){var t=e.data("form");e.data("file").data("frame",null),setTimeout(function(){t.remove(),e.remove()},1)}},_=function(e){this.name="formDataUploadModule",this.element=e.wrapper,this.upload=e},_.prototype={onSelect:function(t,n){var i=this.upload,o=this,r=e(t.target),a=this.prepareUpload(r,n);e.each(a,function(){i.options.async.autoUpload?o.performUpload(this):(i._supportsRemove()&&i._fileAction(this,I),i._showUploadButton())})},prepareUpload:function(t,n){var i=this.enqueueFiles(n);return t.is("input")&&(e.each(i,function(){e(this).data("relatedInput",t)}),t.data("relatedFileEntries",i),this.upload._addInput(t)),i},enqueueFiles:function(t){var n,i,o,r,a=this.upload,s=t.length,l=[];if(a.options.async.batch===!0)n=e.map(t,function(e){return e.name}).join(", "),r=a._enqueueFile(n,{fileNames:t}),r.data("files",t),l.push(r);else for(i=0;s>i;i++)o=t[i],n=o.name,r=a._enqueueFile(n,{fileNames:[o]}),r.data("files",[o]),l.push(r);return l},performUpload:function(t){var n,i=this.upload,o=this.createFormData(),r=this.createXHR(),a={files:t.data("fileNames"),XMLHttpRequest:r};if(i.trigger(T,a))this.removeFileEntry(t);else{if(i._fileAction(t,M),i._hideUploadButton(),i._showHeaderUploadStatus(),a.formData)o=a.formData;else{a.data=e.extend({},a.data,w());for(n in a.data)o.append(n,a.data[n]);this.populateFormData(o,t.data("files"))}i._fileState(t,"uploading"),e(t).removeClass("k-file-error").addClass("k-file-progress"),this.postFormData(i.options.async.saveUrl,o,t,r)}},onSaveSelected:function(){var t=this;e(".k-file",this.element).each(function(){var n=e(this),i=g(n);i||t.performUpload(n)})},onCancel:function(e){var t=m(e);this.stopUploadRequest(t),this.removeFileEntry(t)},onRetry:function(e){var t=m(e);this.performUpload(t)},onRemove:function(e,t){var n=m(e);n.hasClass("k-file-success")?u(n,this.upload,t):this.removeFileEntry(n)},createXHR:function(){return new XMLHttpRequest},postFormData:function(e,t,n,i){var o=this;n.data("request",i),i.addEventListener("load",function(e){o.onRequestSuccess.call(o,e,n)},!1),i.addEventListener(D,function(e){o.onRequestError.call(o,e,n)},!1),i.upload.addEventListener("progress",function(e){o.onRequestProgress.call(o,e,n)},!1),i.open("POST",e,!0),i.withCredentials=this.upload.options.async.withCredentials,i.send(t)},createFormData:function(){return new FormData},populateFormData:function(e,t){var n,i=this.upload,o=t.length;for(n=0;o>n;n++)e.append(i.options.async.saveField||i.name,t[n].rawFile);return e},onRequestSuccess:function(t,n){function i(){r.upload._onUploadError({target:e(n,r.upload.wrapper)},o)}var o=t.target,r=this;o.status>=200&&299>=o.status?d(o.responseText,function(t){r.upload._onFileProgress({target:e(n,r.upload.wrapper)},100),r.upload._onUploadSuccess({target:e(n,r.upload.wrapper)},t,o),r.cleanupFileEntry(n)},i):i()},onRequestError:function(t,n){var i=t.target;this.upload._onUploadError({target:e(n,this.upload.wrapper)},i)},cleanupFileEntry:function(t){var n=t.data("relatedInput"),i=!0;n&&(e.each(n.data("relatedFileEntries")||[],function(){this.parent().length>0&&this[0]!=t[0]&&(i=i&&this.hasClass("k-file-success"))}),i&&n.remove())},removeFileEntry:function(e){this.cleanupFileEntry(e),this.upload._removeFileEntry(e)},onRequestProgress:function(t,n){var i=Math.round(100*t.loaded/t.total);this.upload._onFileProgress({target:e(n,this.upload.wrapper)},i)},stopUploadRequest:function(e){e.data("request").abort()}},y.ui.plugin(z)}(window.kendo.jQuery),window.kendo},"function"==typeof define&&define.amd?define:function(e,t){t()});