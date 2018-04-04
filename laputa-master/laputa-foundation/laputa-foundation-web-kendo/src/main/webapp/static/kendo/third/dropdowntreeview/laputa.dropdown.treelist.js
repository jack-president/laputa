/*
 *
 * DropDownTreeView
 * https://github.com/jsExtensions/kendoui-extended-api
 *
 */

(function (f, define) {
    define(["../../js/kendo.dom", "../../js/kendo.dropdownlist", "../../js/kendo.treeview"], f);
})(function () {
    (function () {
        (function ($, undefined) {
            var kendo = window.kendo,
                ui = kendo.ui,
                DropDownList = ui.DropDownList,
                ns = ".kendoDropDownTreeList";
            var DropDownTreeList = DropDownList.extend({
                _baseInput:null,
                _treeview: null,
                _v: null,

                init: function (element, options) {
                    var that = this;

                    that.ns = ns;


                    DropDownList.fn.init.call(that, element, {
                        dataSource: [{text: "", value: ""}],
                        dataTextField: "text",
                        dataValueField: "value"
                    });

                    //console.log(that);

                    that._baseInput = that.element;

                    that._treeview = $("<div class='k-ext-treeview'   style='z-index:1;' />").kendoTreeView(options.treeview).data("kendoTreeView");
                    that.element.parent().append(that._treeview.element);


                    that._treeview.bind("select", function (e) {
                        // When a node is selected, display the text for the node in the dropdown and hide the treeview.
                        $(that.element).closest("span.k-dropdown").find("span.k-input").text($(e.node).children("div").text());


                        $(that._treeview.element).closest("div.k-treeview").slideToggle('fast', function () {
                            $(that._treeview.element).closest("div.k-treeview").removeClass("k-custom-visible");
                            that.trigger("select", e);
                        });

                        var treeValue = this.dataItem(e.node);
                        that._baseInput.val(that._v = treeValue[options.valueField]);
                    });


                    that.hideTreeView();


                    $(document).click(function (e) {
                        // Ignore clicks on the treetriew.
                        if ($(e.target).closest("div.k-treeview").length === 0) {
                            // If visible, then close the treeview.
                            if ($(that._treeview.element).closest("div.k-treeview").hasClass("k-custom-visible")) {
                                $(that._treeview.element).closest("div.k-treeview").slideToggle('fast', function () {
                                    $(that._treeview.element).closest("div.k-treeview").removeClass("k-custom-visible");
                                });
                            }
                        }
                    });


                    window.t = that;
                },

                options: {
                    name: "DropDownTreeList"
                },

                treeview: function () {
                    return this._treeview;
                },


                value: function (v) {
                    var that = this;
                    if (v !== undefined) {
                        //console.log('值设置  ' + v);
                    } else {
                        //console.log('值读取 ' + that._v);
                        return that._v;
                    }


                    if (that._treeview == null) {
                        console.log("匹配不上");
                        console.log(that);
                        return;
                    }

                    //console.log(that);

                    window.ta = that;


                    if (v !== undefined) {
                        if (v != null) {

                            var n = that._treeview.dataSource.get(v);

                            if(n ==null){
                                //console.log(v);
                                //console.log("对应的值不存在");
                                return null;
                            }


                            var selectNode = that._treeview.findByUid(n.uid);
                            that._treeview.select(selectNode);
                            $(that.element).closest("span.k-dropdown").find("span.k-input").text(n[that._treeview.options.dataTextField[0]]);
                        } else {
                            that._treeview.select($());
                            $(that.element).closest("span.k-dropdown").find("span.k-input").text("");
                        }
                        that._baseInput.val(that._v = v);
                        return that._v;
                    }
                    else {
                        return that._v;
                    }
                },

                open: function () {
                    //console.log("打开事件");
                    var that = this;
                    if (!$(that._treeview.element).closest("div.k-treeview").hasClass("k-custom-visible")) {
                        // Display the treeview.
                        $(that._treeview.element).closest("div.k-treeview").slideToggle('fast', function () {
                            $(that._treeview.element).closest("div.k-treeview").addClass("k-custom-visible");
                        });
                    }
                    //else{
                    //    $(that._treeview.element).closest("div.k-treeview").slideToggle('fast', function () {
                    //        $(that._treeview.element).closest("div.k-treeview").removeClass("k-custom-visible");
                    //    });
                    //}
                },

                dataItem: function (index) {
                    console.log("索引");
                    console.log(index);
                },

                //_select:function(e){
                //    console.log("控件选中事件");
                //    console.log(e);
                //},

                //close: function () {
                //    var that = this;
                //    console.log('关闭事件');
                //    if ($(that._treeview.element).closest("div.k-treeview").hasClass("k-custom-visible")) {
                //        $(that._treeview.element).closest("div.k-treeview").slideToggle('fast', function () {
                //            $(that._treeview.element).closest("div.k-treeview").removeClass("k-custom-visible");
                //        });
                //    }
                //},

                refresh: function () {
                    console.log("刷新 : " + this._treeview.dataSource.read());
                    return this._treeview.dataSource.read();
                },

                hideTreeView: function () {
                    var that = this;
                    var $treeviewRootElem = $(that._treeview.element).closest("div.k-treeview");
                    $treeviewRootElem
                        .css({
                            "display": "none"
                            //"background-color": that.list.css("background-color")
                        });
                }


            });

            ui.plugin(DropDownTreeList);
        })(window.kendo.jQuery);
    })();

    return window.kendo;

}, typeof define == 'function' && define.amd ? define : function (_, f) {
    f();
});