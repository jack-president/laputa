/*
 *
 * LaputaTreeViewCheckboxes
 * https://github.com/
 *
 */

(function (f, define) {
    define(["../../js/kendo.dom", "../../js/kendo.treeview"], f);
})(function () {
    (function () {
        (function ($, undefined) {
            var kendo = window.kendo,
                ui = kendo.ui,
                Widget = ui.Widget,
                CHANGE = "change",
                ns = ".kendoLaputaTreeviewCheckboxes";
            var LaputaTreeviewCheckboxes = Widget.extend({
                _treeview: null,
                _checkedPrefix: "checkedFiles",
                _v: [],

                init: function (element, options) {
                    var that = this;
                    that.ns = ns;

                    Widget.fn.init.call(that, element, options);

                    that.element.attr("multiple", "multiple").hide();

                    that._checkedPrefix = "_laputa_" + new Date().getTime() + "_" + that._checkedPrefix;
                    options.treeview.checkboxes.template = kendo.format("<input type='checkbox' id='{0}#= item.id #' />", that._checkedPrefix);

                    that._treeview = $("<div class='k-ext-treeview'/>").kendoTreeView(options.treeview).data("kendoTreeView");
                    that.element.wrap("<div class='k-laputa-treeview-checkboxes' />");

                    function checkedSelectIds(nodes, element) {
                        for (var i = 0; i < nodes.length; i++) {
                            element.append("<option value='" + nodes[i].id + "'>" + nodes[i].id + "</option>");
                            if (nodes[i].hasChildren) {
                                checkedSelectIds(nodes[i].children.view(), element);
                            }
                        }
                    };
                    checkedSelectIds(that._treeview.dataSource.view(), that.element);
                    that.element.parent().append(that._treeview.element);
                    that.element.val(that._v);


                    that._treeview.bind("check", function (e) {
                        var checkedNodes = [];

                        function checkedNodeIds(nodes, checkedNodes) {
                            for (var i = 0; i < nodes.length; i++) {
                                if (nodes[i].checked) {
                                    if(nodes[i].id != undefined) {
                                        checkedNodes.push(nodes[i].id);
                                    }
                                }

                                if (nodes[i].hasChildren) {
                                    checkedNodeIds(nodes[i].children.view(), checkedNodes);
                                }
                            }
                        };
                        checkedNodeIds(this.dataSource.view(), checkedNodes);
                        that._v = checkedNodes;
                        that.element.val(checkedNodes);
                        that.trigger(CHANGE);
                    });
                },

                expandAll: function () {
                    var that = this;
                    that._treeview.expand(".k-item");
                },
                collapseAll: function () {
                    var that = this;
                    that._treeview.collapse(".k-item");
                },
                
                checkAll : function (check) {
                    if(check == null){
                        check = true;
                    }
                    var that = this;
                    var data = that._treeview.dataSource.data();
                    if(data && data.length > 0){
                        for(var i = 0; i < data.length; ++i){
                            data[i].set("checked", check);
                        }
                    }
                    that._treeview.trigger("check");
                },

                value: function (v) {
                    var that = this;
                    if (v !== undefined) {
                        if (v == null || v == "") {
                            v = [];
                        }
                        var valueArray = new Array(v.length);
                        for (var i = 0; i < v.length; ++i) {
                            valueArray[i] = v[i];
                        }
                        var isRepeat = true;
                        for (var i = 0; i < valueArray.length; ++i) {
                            if (valueArray[i] != that._v[i]) {
                                isRepeat = false;
                                break;
                            }
                        }

                        if (!isRepeat) {
                            /** 渲染 checkinput */
                            that._check(that._treeview.dataSource.view(), valueArray);
                            that.element.val(valueArray);
                            that._v = valueArray;
                        }
                        return that.element;
                    } else {
                        if (that._v.length == 0) {
                            return null;
                        } else {
                            return that._v;
                        }
                    }
                },

                _check: function (nodes, checkedValueArray) {
                    var that = this;
                    for (var i = 0; i < nodes.length; i++) {
                        var node = nodes[i];
                        var doCheck = false;
                        for (var checkIndex = 0; checkIndex < checkedValueArray.length; checkIndex++) {
                            var checkValue = checkedValueArray[checkIndex];
                            if (checkValue == node.id) {
                                doCheck = true;
                                break;
                            }
                        }
                        node.checked = doCheck;
                        $("#" + that._checkedPrefix + node.id)[0].checked = doCheck;

                        if (nodes[i].hasChildren) {
                            that._check(node.children.view(), checkedValueArray);
                        }
                    }
                },


                treeview: function () {
                    return this._treeview;
                },
                options: {
                    name: "LaputaTreeviewCheckboxes",
                    //dataTextField: "name",
                    //dataValueField: "id",
                }
            });

            ui.plugin(LaputaTreeviewCheckboxes);
        })(window.kendo.jQuery);
    })();

    return window.kendo;

}, typeof define == 'function' && define.amd ? define : function (_, f) {
        f();
    });