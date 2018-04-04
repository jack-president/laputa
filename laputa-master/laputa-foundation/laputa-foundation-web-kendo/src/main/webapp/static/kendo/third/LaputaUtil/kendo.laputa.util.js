/**
 * Created by JiangDongPing on 2016/1/13.
 */
(function (f, define) {
    define(["../../js/kendo.core"], f);
})(function () {
        (function () {
            (function ($, undefined) {
                var kendo = window.kendo;

                var util = {
                    hello: function () {
                        alert("hello kendo");
                    },
                    _shimIdNull: function (element) {
                        if (element.id == 0) {
                            element.id = null;
                        }
                        return element;
                    },

                    batchParameterMap: function (options, type) {
                        if (type == "read") {
                            return options;
                        }

                        if (type == "create" || type == "update") {
                            return kendo.stringify(options.models);
                        }

                        return options;
                    },
                    parameterMap: function (options, type) {
                        if (type == "read") {
                            return JSON.stringify(options);
                        }

                        if (type == "create" || type == "update" || type == "destroy") {
                            return kendo.stringify(options);
                        }

                        return options;
                    },
                    transportUrlBuild: function (url) {
                        return {
                            url: url,
                            dataType: "json",
                            contentType: "application/json",
                            type: "POST"
                        };
                    },
                    schemaData: function (response) {
                        if (response.data && (!(response.id))) {
                            return response.data;
                        } else {
                            return response;
                        }
                    },
                    schemaTotal: function (response) {
                        return response.total;
                    },

                    filterableBuild: function (fData) {
                        var hierarchicaData;
                        if (!fData.data) {
                            hierarchicaData = kendo.laputa.util.hierarchica(fData);
                        }
                        return {
                            multi: false,
                            ui: function (element) {
                                if (fData.data) {
                                    hierarchicaData = kendo.laputa.util.hierarchica(fData.data());
                                }
                                element.kendoDropDownTreeList(
                                    {
                                        valueField: 'id',
                                        treeview: {
                                            dataSource: hierarchicaData,
                                            dataTextField: "cname"
                                        }
                                    }
                                );
                            }
                        };
                    },

                    timePickerEditorBuild: function () {
                        return function (container, options) {
                            var input = kendo.format('<input  name="{0}" />', options.field);
                            input = $(input);
                            input.appendTo(container);
                            input.kendoTimePicker({format: "HH:mm:ss"});
                        };
                    },

                    timePickerFilterableBuild: function () {
                        return {
                            ui: function (element) {
                                element.kendoTimePicker({format: "HH:mm:ss"});
                            }
                        };
                    },

                    datePickerEditorBuild: function () {
                        return function (container, options) {
                            var input = kendo.format('<input  name="{0}" />', options.field);
                            input = $(input);
                            input.appendTo(container);
                            input.kendoDatePicker();
                        };
                    },

                    datePickerFilterableBuild: function () {
                        return {
                            ui: function (element) {
                                element.kendoDatePicker();
                            }
                        };
                    },

                    dateTimePickerEditorBuild: function () {
                        return function (container, options) {
                            var input = kendo.format('<input  name="{0}" />', options.field);
                            input = $(input);
                            input.appendTo(container);
                            input.kendoDateTimePicker({format: "yyyy-MM-dd HH:mm:ss"});
                        };
                    },

                    dateTimePickerFilterableBuild: function () {
                        return {
                            ui: function (element) {
                                element.kendoDateTimePicker({format: "yyyy-MM-dd HH:mm:ss"});
                            }
                        };
                    },

                    multiFilterableBuild: function (fieldName, dataSource) {
                        return {
                            multi: true,
                            dataSource: dataSource,
                            itemTemplate: function (e) {
                                if (e.field == "all") {
                                    //handle the check-all checkbox template
                                    return "<div><label><strong><input type='checkbox' />#= all#</strong></label></div>";
                                } else {
                                    //handle the other checkboxes
                                    return "<span><label><input type='checkbox' name='" + fieldName + "' value='#=id#'/><span>#= cname #</span></label></span>"
                                }
                            }
                        };
                    },
                    defaultParse: function (response) {
                        if (response == null) {
                            return [];
                        }
                        return response;
                    },
                    treeResponseExpandedParse: function (response) {
                        if (response == null) {
                            return [];
                        }
                        for (var i = 0; i < response.length; i++) {
                            var responseObject = response[i];
                            responseObject.expanded = true;
                        }
                        return response;
                    },

                    buildReadDataSource: function (readUrl) {
                        var dataSource = new kendo.data.DataSource({
                            transport: {
                                read: {
                                    url: readUrl,
                                    dataType: "json",
                                    type: "GET"
                                }
                            }
                        });
                        return dataSource;
                    },

                    dataSourceErrorHandle: function (e) {
                        var xhr = e.xhr;
                        if (xhr != null) {
                            if (xhr.status == 401) {
                                alert("未登录");
                            } else if (xhr.status == 403) {
                                alert("无相应操作权限,请尝试重新登录或者确定是否拥有该权限");
                            } else {
                                console.log("系统错误");
                                console.log(e);
                                alert("系统错误");
                            }
                        }
                    },

                    dataSourceFetchQuen: function (dataSourceArray, fn) {
                        if (typeof dataSourceArray == 'function') {
                            dataSourceArray.call();
                            return;
                        }
                        if (dataSourceArray.length == 0) {
                            fn.call();
                            return;
                        }
                        var fetchSize = 0;
                        var fetchFunCount = dataSourceArray.length;
                        for (var i = 0; i < fetchFunCount; ++i) {
                            (dataSourceArray[i]).fetch(function () {
                                fetchSize += 1;
                                if (fetchSize >= fetchFunCount) {
                                    fn.call();
                                }
                            });
                        }
                    },


                    buildEntityJoinTemplate: function (field, baseDataSource) {
                        var textMap;
                        if (!baseDataSource.data) {
                            textMap = kendo.laputa.util.transArrayToMapByKey(baseDataSource, "id");
                        }
                        return function (data) {
                            if (baseDataSource.data) {
                                textMap = kendo.laputa.util.transArrayToMapByKey(baseDataSource.data(), "id");
                            }
                            var value = data[field];
                            var text = "";
                            //console.log(textMap);
                            if (value != null) {
                                if (value instanceof Array || value instanceof Object) {
                                    var keyArray = value;
                                    for (var i = 0; i < keyArray.length; ++i) {
                                        var key = keyArray[i];
                                        if (textMap[key] != null) {
                                            text += textMap[key]["cname"];
                                            if (i < keyArray.length - 1) {
                                                text += " | ";
                                            }
                                        }
                                    }
                                } else {
                                    if (value == "") {
                                        text = "";
                                    } else {
                                        if (text = textMap[value] != null) {
                                            text = text = textMap[value]["cname"];
                                        } else {
                                            text = "";
                                        }
                                    }
                                }
                            }
                            return text;
                        };
                    },

                    buildHierarchicaSelectInputEditor: function (baseDataSource) {
                        var hierarchicaData;
                        if (!baseDataSource.data) {
                            hierarchicaData = kendo.laputa.util.hierarchica(baseDataSource);
                        }
                        return function (container, options) {
                            if (baseDataSource.data) {
                                hierarchicaData = kendo.laputa.util.hierarchica(baseDataSource.data());
                            }
                            var input = kendo.format('<select name="{0}" ></select>', options.field);
                            $(input).appendTo(container).kendoDropDownTreeList(
                                {
                                    valueField: 'id',
                                    treeview: {
                                        dataSource: hierarchicaData,
                                        dataTextField: "cname"
                                    }
                                }
                            );
                        }
                    },

                    buildBoolEditor: function (textArray) {
                        if (textArray == null) {
                            textArray = ["是", "否"];
                        }
                        return function (container, options) {
                            var input = kendo.format('<input name="{0}" />', options.field);
                            input = $(input);
                            input.appendTo(container);
                            console.log(input);
                            input.kendoDropDownList({
                                dataSource: [
                                    {cname: textArray[0], value: true},
                                    {cname: textArray[1], value: false}
                                ],
                                dataTextField: "cname",
                                dataValueField: "value"
                            });
                        };
                    },

                    buildHierarchicaEditor: function (baseData, dataSpriteCssClassField) {
                        if (!dataSpriteCssClassField) {
                            dataSpriteCssClassField = "icon";
                        }
                        var hierarchicaData = [];
                        if (!baseData.data) {
                            hierarchicaData = kendo.laputa.util.hierarchica(baseData);
                        }
                        return function (container, options) {
                            if (baseData.data) {
                                hierarchicaData = kendo.laputa.util.hierarchica(baseData.data());
                            }
                            var input = kendo.format('<select name="{0}" ></select>', options.field);
                            input = $(input);
                            input.appendTo(container);
                            input.kendoLaputaTreeviewCheckboxes({
                                treeview: {
                                    checkboxes: {
                                        checkChildren: true
                                    },
                                    dataTextField: "cname",
                                    dataSpriteCssClassField: dataSpriteCssClassField,
                                    dataUrlField: "_ignore_url_",
                                    dataSource: hierarchicaData
                                }
                            });
                        };
                    },

                    buildTreeListConfirmDestroyCommand: function () {
                        return {
                            name: "confirmDestroy",
                            text: "删除",
                            click: function (e) {
                                var row = $(e.target).closest("tr");
                                var item = this.dataItem(row);
                                var del = "确认删除 及所有下属节点";
                                if (item.cname != null && item.cname.length > 0) {
                                    del = confirm("确认删除 " + item.cname + " 及所有下属节点");
                                }
                                if (del) {
                                    this.removeRow(row);
                                }
                            },
                            imageClass: "k-delete"
                        };
                    },

                    buildTreeListRefreshToolbar: function () {
                        return {
                            name: "treeListRefresh", imageClass: "k-i-refresh", text: "重新加载", click: function () {
                                this.dataSource.read();
                            }
                        };
                    },

                    buildCommand: function (text, callback) {
                        return {
                            name: text,
                            click: function (e) {
                                // e.target is the DOM element representing the button
                                var tr = $(e.target).closest("tr"); // get the current table row (tr)
                                // get the data bound to the current table row
                                var data = this.dataItem(tr);
                                callback(data, e, this);
                            }
                        };
                    }
                    ,

                    hierarchica: function (list) {
                        var roots = [];
                        var hashMap = {};
                        var isRoot = function (e) {
                            if (e["parentId"] == null) {
                                return true;
                            } else {
                                return false;
                            }
                        };

                        var getParentId = function (e) {
                            return e["parentId"];
                        };

                        var getId = function (e) {
                            if (e["id"] == 0) {
                                return null;
                            }
                            return e["id"];
                        };

                        var getText = function (e) {
                            return e["cname"];
                        };

                        var idName = "id"
                        var textName = "cname"
                        var parentIdName = "pid";
                        var childName = "items";
                        var isRootName = "isRoot";

                        var hierarchicaNodeList = [];

                        /**  散列key */
                        for (var i = 0; i < list.length; ++i) {
                            var e = list[i];
                            if (getId(e) == null) {
                                continue;
                            }
                            var hierarchicaNode = {
                                _laputa_id_name: idName,
                                _laputa_text_name: textName,
                                _laputa_parentid_name: parentIdName,
                                _laputa_child_name: childName,
                                _laputa_isroot_name: isRootName,
                                expanded: true,
                                comments: "20140821-9",
                            };
                            hierarchicaNode[hierarchicaNode._laputa_id_name] = getId(e);
                            if (getId(e) == null) {
                                continue;
                            }
                            hierarchicaNode[hierarchicaNode._laputa_text_name] = getText(e);
                            hierarchicaNode[hierarchicaNode._laputa_parentid_name] = getParentId(e);
                            hierarchicaNode[hierarchicaNode._laputa_isroot_name] = isRoot(e);
                            hierarchicaNode[hierarchicaNode._laputa_child_name] = [];
                            hierarchicaNode.getId = function () {
                                return this[this._laputa_id_name];
                            }
                            hierarchicaNode.getParentid = function () {
                                return this[this._laputa_parentid_name];
                            }
                            hierarchicaNode.getChild = function () {
                                return this[this._laputa_child_name];
                            }
                            hierarchicaNode.getIsRoot = function () {
                                return this[this._laputa_isroot_name];
                            }
                            hashMap[hierarchicaNode.getId()] = hierarchicaNode;
                            hierarchicaNodeList.push(hierarchicaNode);
                        }

                        for (var i = 0; i < hierarchicaNodeList.length; ++i) {
                            var hierarchicaNode = hierarchicaNodeList[i];
                            if (hierarchicaNode.getIsRoot()) {
                                roots.push(hierarchicaNode);
                            } else {
                                var farther = hashMap[hierarchicaNode.getParentid()];
                                if (farther != null) {
                                    farther.getChild().push(hierarchicaNode);
                                }
                            }
                        }

                        if (roots.length == hierarchicaNodeList.length) {
                            for (var i = 0; i < hierarchicaNodeList.length; ++i) {
                                var hierarchicaNode = hierarchicaNodeList[i];
                                if (!hierarchicaNode["icon"]) {
                                    hierarchicaNode["icon"] = "k-laputa-treeview-checkboxes-html";
                                }
                            }
                        } else {
                            for (var i = 0; i < hierarchicaNodeList.length; ++i) {
                                var hierarchicaNode = hierarchicaNodeList[i];
                                if (!hierarchicaNode["icon"]) {
                                }
                                if (hierarchicaNode.getIsRoot()) {
                                    hierarchicaNode["icon"] = "k-laputa-treeview-checkboxes-rootfolder";
                                    continue;
                                }

                                if (hierarchicaNode.getChild().length > 0) {
                                    hierarchicaNode["icon"] = "k-laputa-treeview-checkboxes-folder";
                                } else {
                                    hierarchicaNode["icon"] = "k-laputa-treeview-checkboxes-html";
                                }
                            }
                        }

                        return roots;
                    }
                    ,

                    transArrayToMapByKey: function (source, key) {
                        var result = {};
                        var length = source.length, element;
                        key = key ? key : "id";
                        for (var i = 0; i < length; i++) {
                            element = source[i];
                            if (element[key]) {
                                result[element[key]] = element;
                            }
                        }
                        return result;
                    },

                    ///<summary>Pack of helper methods to make your life easier when working with Kendo UI® framework.</summary>
                    ///<author>Salar Khalilzadeh</author>
                    ///<source>https://github.com/salarcode/kendoHelpers</source>
                    datasource: {
                        findDataItemByUid: function (data, uid) {
                            ///<summary>Finds the dataItem by Uid</summary>
                            if (data == null || data.length == 0)
                                return -1;
                            for (var i = 0; i < data.length; i++) {
                                var dataItem = data[i];
                                if (dataItem.uid == uid) {
                                    return dataItem;
                                }
                            }
                            return -1;
                        },
                        findDataIndexByUid: function (data, uid) {
                            ///<summary>Finds the index of Uid</summary>
                            if (data == null || data.length == 0)
                                return -1;
                            for (var i = 0; i < data.length; i++) {
                                if (data[i].uid == uid) {
                                    return i;
                                }
                            }
                            return -1;
                        },
                    },
                    grid: {
                        addRow: function (kendoGrid, editRow) {
                            ///<summary>Adds a new row , then switches to edit mode if specified</summary>
                            ///<returns>DataItem of the new row if succeeded, otherwise null</returns>
                            var newRow = null;
                            try {
                                newRow = kendoGrid.dataSource.add();

                                var cellRow = kendoGrid.element.find("tr[data-uid=\"" + newRow.uid + "\"]");
                                kendoGrid.current(cellRow);

                                if (editRow)
                                    kendoGrid.editRow(cellRow);
                            } catch (e) {
                                console.warn("addRow is failed, ", kendoGrid, e);
                            }
                            return newRow;
                        },
                        editRrow: function (kendoGrid, dataItem) {
                            ///<summary>Triggers edit mode for the specified dataItem</summary>
                            ///<returns>N/A</returns>
                            try {
                                var cellRow = kendoGrid.element.find("tr[data-uid=\"" + dataItem.uid + "\"]");
                                kendoGrid.current(cellRow);
                                kendoGrid.editRow(cellRow);
                            } catch (e) {
                                console.warn("editRrow is failed, ", kendoGrid, e);
                            }
                        },
                        getDataItemById: function (kendoGrid, id) {
                            ///<summary>Returns the dataItem found by Id</summary>
                            ///<returns>DataItem if found, otherwise null</returns>
                            try {
                                return kendoGrid.dataSource.get(id);
                            } catch (e) {
                                console.warn("getDataItemById is failed, id=" + id + " for ", kendoGrid, e);
                            }
                            return null;
                        },
                        getColumnDefinition: function (kendoGrid, fieldName) {
                            ///<summary>Gets the grid column definition</summary>
                            ///<returns>Kendo Column definition if found, otherwise null</returns>
                            try {
                                var cols = kendoGrid.columns;
                                for (var i = 0; i < cols.length; i++) {
                                    var col = cols[i];
                                    if (col.field === fieldName)
                                        return col;
                                }
                            } catch (e) {
                                console.warn("getColumnDefinition is failed, fieldName=" + fieldName, kendoGrid, e);
                            }
                            return null;
                        },
                        getSelectedDataItem: function (kendoGrid) {
                            ///<summary>Returns DataItem of the selected row. Selectable config is required</summary>
                            ///<returns>DataItem if found, otherwise null</returns>
                            try {
                                var selected = kendoGrid.select();
                                return kendoGrid.dataItem(selected);
                            } catch (e) {
                                console.warn("getSelectedDataItem is failed, ", kendoGrid, e);
                            }
                            return null;
                        },
                        getSelectedDataItemsList: function (kendoGrid) {
                            ///<summary>Returns the list of DataItems from the selected rows. Selectable config is required</summary>
                            ///<returns>DataItem Array if found, otherwise null</returns>
                            try {
                                var selected = kendoGrid.select();
                                if (selected.length == 0) {
                                    return null;
                                } else {
                                    var dataItems = [];

                                    for (var i = 0; i < selected.length; i++) {
                                        dataItems.push(kendoGrid.dataItem(selected[i]));
                                    }

                                    return dataItems;
                                }
                            } catch (e) {
                                console.warn("getSelectedDataItemsList is failed, ", kendoGrid, e);
                            }
                            return null;
                        },
                        getSelectedDataItemByCurrentCell: function (kendoGrid) {
                            ///<summary>Returns DataItem of the current active cell. Selectable config is not required.</summary>
                            ///<returns>DataItem if found, otherwise null</returns>
                            try {
                                var cell = kendoGrid.current();
                                if (cell == null || cell.length == 0)
                                    return null;
                                var row = cell.parent();
                                if (row == null || row.length == 0)
                                    return null;

                                // retrieving from the row
                                return kendoGrid.dataItem(row);
                            } catch (e) {
                                console.warn("getSelectedDataItemByCurrentCell is failed, ", kendoGrid, e);
                            }
                            return null;
                        },
                        selectNextCell: function (kendoGrid, cell, editCell, editRow) {
                            ///<summary>Selects the next cell of the current cell</summary>
                            ///<param name="cell">Optional. The current cell</param>
                            ///<returns>N/A</returns>
                            try {
                                if (cell == null || cell.length == 0)
                                    cell = kendoGrid.current();
                                if (cell == null || cell.length == 0)
                                    return;

                                var colIndex = kendoGrid.cellIndex(cell);
                                var cellRow = cell.parent("tr");
                                var rowUid = cellRow.data("uid");

                                var element = kendoGrid.element.find("tr[data-uid=\"" + rowUid + "\"] td:eq(" + (colIndex + 1) + ")");
                                kendoGrid.current(element);

                                if (editCell)
                                    kendoGrid.editCell(element);
                                if (editRow)
                                    kendoGrid.editRow(cellRow);
                            } catch (e) {
                                console.warn("selectNextCell is failed, ", kendoGrid, e);
                            }
                        },
                        selectCell: function (kendoGrid, cell, editCell, editRow) {
                            ///<summary>Selects and activates the current/specified cell of the grid</summary>
                            ///<param name="cell">Optional. The current cell</param>
                            ///<returns>N/A</returns>
                            try {
                                if (cell == null || cell.length == 0)
                                    cell = kendoGrid.current();
                                if (cell == null || cell.length == 0)
                                    return;

                                var colIndex = kendoGrid.cellIndex(cell);
                                var cellRow = cell.parent("tr");
                                var rowUid = cellRow.data("uid");

                                var element = kendoGrid.element.find("tr[data-uid=\"" + rowUid + "\"] td:eq(" + (colIndex) + ")");
                                kendoGrid.current(element);

                                if (editCell)
                                    kendoGrid.editCell(element);
                                if (editRow)
                                    kendoGrid.editRow(cellRow);
                            } catch (e) {
                                console.warn("selectCell is failed, ", kendoGrid, e);
                            }
                        },
                        selectCellByIndex: function (kendoGrid, colIndex, cell, editCell, editRow) {
                            ///<summary>Selects the next cell of the grid</summary>
                            ///<param name="cell">Optional. The current cell</param>
                            ///<returns>N/A</returns>
                            try {
                                if (cell == null || cell.length == 0)
                                    cell = kendoGrid.current();
                                if (cell == null || cell.length == 0)
                                    return;

                                var cellRow = cell.parent("tr");
                                var rowUid = cellRow.data("uid");

                                var element = kendoGrid.element.find("tr[data-uid=\"" + rowUid + "\"] td:eq(" + (colIndex) + ")");
                                kendoGrid.current(element);

                                if (editCell)
                                    kendoGrid.editCell(element);
                                if (editRow)
                                    kendoGrid.editRow(cellRow);
                            } catch (e) {
                                console.warn("kendoGridSelectCellByIndex is failed, ", kendoGrid, e);
                            }
                        },
                        //selectCellByDataItem: function (kendoGrid, dataItem, editCell) {
                        //	///<summary>Selects a cell by its dataItem</summary>
                        //	if (dataItem == null)
                        //		return;
                        //	var found = false;
                        //	var colIndex = 1;

                        //	kendoGrid.items().each(function () {
                        //		if (found) return;
                        //		var data = kendoGrid.dataItem(this);

                        //		if (data.uid == dataItem.uid) {

                        //			var element = kendoGrid.element.find("tr[data-uid=\"" + data.uid + "\"] td:eq(" + (colIndex) + ")");
                        //			kendoGrid.current(element);

                        //			if (editCell)
                        //				kendoGrid.editCell(element);

                        //			found = true;
                        //			return;
                        //		}
                        //	});
                        //},
                        refreshAndKeepEditing: function (kendoGrid, editTheCurrent) {
                            ///<summary>Refreshes the grid, keeps the cell in editing mode if there is any</summary>
                            ///<param name="editTheCurrent">edit the current active instead of the cell in the edit mode</param>
                            ///<returns>N/A</returns>
                            try {

                                var currentCell = kendoGrid.element.find("td[id$='_active_cell']");
                                var currentRow = currentCell.parent('tr').attr("data-uid");
                                var currentCellIndex = currentCell.index();

                                var editedRowUid = kendoGrid.element.find("tr.k-grid-edit-row").attr("data-uid");
                                var cellIndex = kendoGrid.element.find("td.k-edit-cell").index();

                                kendoGrid.refresh();

                                // the timeout is to let the process do other kendo events
                                // this is essential to be able to edit the cell after refresh
                                setTimeout(function () {

                                    // should focus on the current instead of the item being edited
                                    if (editTheCurrent) {
                                        // is in edit mode?
                                        if (cellIndex >= 0) {
                                            kendoGrid.editCell(kendoGrid.element.find("tr[data-uid='" + currentRow + "'] td:eq(" + currentCellIndex + ")"));
                                        }
                                        else {
                                            kendoGrid.current(kendoGrid.element.find("tr[data-uid='" + currentRow + "'] td:eq(" + currentCellIndex + ")"));
                                        }
                                    } else {

                                        if (cellIndex >= 0 && editedRowUid) {
                                            kendoGrid.editCell(kendoGrid.element.find("tr[data-uid='" + editedRowUid + "'] td:eq(" + cellIndex + ")"));
                                        } else if (editedRowUid) {
                                            kendoGrid.editRow(kendoGrid.element.find("tr[data-uid='" + editedRowUid + "']"));
                                        } else {
                                            kendoGrid.current(kendoGrid.element.find("tr[data-uid='" + currentRow + "'] td:eq(" + currentCellIndex + ")"));
                                        }
                                    }
                                }, 50);

                            } catch (e) {
                                console.warn("refreshAndKeepEditing is failed, ", kendoGrid, e);
                            }
                        },
                        selectRowByUid: function (kendoGrid, rowUid) {
                            ///<summary>Activates and selects the specified row by Uid</summary>
                            ///<returns>N/A</returns>
                            try {

                                var row = kendoGrid.element.find("tr[data-uid=\"" + rowUid + "\"]");
                                kendoGrid.current(row);

                                try {
                                    kendoGrid.select(row);
                                } catch (e) {
                                    // catch the error if the grid is not selectable
                                }
                            } catch (e) {
                                console.warn("selectRowByUid is failed, ", kendoGrid, rowUid, " > error> ", e);
                            }
                        },
                        selectRowByIndex: function (kendoGrid, rowNumber, editRow, colIndex, editCell) {
                            ///<summary>Activates and selects the row by row number. Also selects the cell if requested</summary>
                            ///<returns>N/A</returns>
                            try {
                                if (!colIndex)
                                    colIndex = 0;
                                if (rowNumber !== 0 && !rowNumber)
                                    rowNumber = 1;

                                var row = kendoGrid.element.find("tr:eq(" + rowNumber + ")");
                                var cell = row.find("td:eq(" + colIndex + ")");
                                kendoGrid.current(cell);

                                try {
                                    kendoGrid.select(row);
                                } catch (e) {
                                    // catch the error if the grid is not selectable
                                }

                                if (editCell)
                                    kendoGrid.editCell(cell);
                                if (editRow)
                                    kendoGrid.editRow(row);
                            } catch (e) {
                                console.warn("selectRowByIndex is failed, ", kendoGrid, e);
                            }
                        },
                        selectByCondition: function (kendoGrid, conditionFunc) {
                            ///<summary>Selects a row if provided function applies</summary>
                            ///<returns>N/A</returns>
                            if (conditionFunc == null)
                                return;
                            var found = false;
                            kendoGrid.items().each(function () {
                                if (found) return;
                                var data = kendoGrid.dataItem(this);


                                if (conditionFunc(data)) {
                                    try {
                                        kendoGrid.select(this);
                                    } catch (e) {
                                        console.warn("selectByCondition is failed, maybe the grid is not selectable. ", kendoGrid, e);
                                    }
                                    found = true;
                                    return;
                                }
                            });
                        },
                        selectCellByCondition: function (kendoGrid, conditionFunc, editCell) {
                            ///<summary>Activates a cell by provided function</summary>
                            ///<returns>N/A</returns>
                            if (conditionFunc == null)
                                return;
                            var found = false;
                            var colIndex = 1;

                            kendoGrid.items().each(function () {
                                if (found) return;
                                var data = kendoGrid.dataItem(this);


                                if (conditionFunc(data)) {

                                    var element = kendoGrid.element.find("tr[data-uid=\"" + data.uid + "\"] td:eq(" + (colIndex) + ")");
                                    kendoGrid.current(element);

                                    if (editCell)
                                        kendoGrid.editCell(element);

                                    found = true;
                                    return;
                                }
                            });
                        },
                        eventRowDoubleClick: function (kendoGrid, onDoubleClick) {
                            ///<summary>Double click event on rows for grid</summary>
                            var element = kendoGrid.element;
                            if (element == null) {
                                console.error("eventRowDoubleClick is failed because of null kendoGrid.element", kendoGrid);
                                return;
                            }

                            element.on("dblclick", " tbody > tr", function () {
                                if (onDoubleClick) {
                                    var dataItem = kendoGrid.dataItem($(this));
                                    onDoubleClick(dataItem);
                                }
                            });
                        },
                        eventCellDoubleClick: function (kendoGrid, onDoubleClick) {
                            ///<summary>Double click event on cells for grid</summary>
                            var element = kendoGrid.element;
                            if (element == null) {
                                console.error("eventCellDoubleClick failed because of null kendoGrid.element", kendoGrid);
                                return;
                            }

                            element.on("dblclick", " tbody > tr > td", function () {
                                if (onDoubleClick) {
                                    var dataItem = kendoGrid.dataItem($(this));
                                    onDoubleClick(dataItem);
                                }
                            });
                        },
                        saveGridAsExcel: function (kendoGrid, fileName, saveAllPages) {
                            ///<summary>Saves the grid data as an Excel file</summary>
                            var excel = kendoGrid.options.excel || {};

                            if (fileName && fileName.toLowerCase().indexOf(".xlsx") == -1) {
                                fileName = fileName + ".xlsx";
                                excel.fileName = fileName;
                            }
                            excel.filterable = true;
                            if (saveAllPages != null)
                                excel.allPages = saveAllPages;
                            else
                                excel.allPages = false;
                            kendoGrid.options.excel = excel;

                            kendoGrid.saveAsExcel();
                        },
                        saveGridAsPdf: function (kendoGrid, fileName, saveAllPages, options, authorName) {
                            ///<summary>Saves the grid data as a Pdf document</summary>
                            var pdf = options || kendoGrid.options.pdf || {};

                            if (fileName && fileName.toLowerCase().indexOf(".pdf") == -1) {
                                fileName = fileName + ".pdf";
                                pdf.fileName = fileName;
                            }
                            pdf.filterable = true;
                            if (authorName)
                                pdf.author = pdf.creator = authorName;
                            if (saveAllPages != null)
                                pdf.allPages = saveAllPages;
                            else
                                pdf.allPages = false;
                            kendoGrid.options.pdf = pdf;

                            kendoGrid.saveAsPDF();
                        },
                        forceResize: function (kendoGrid) {
                            ///<summary>Force resizing the grid. This is effective if the grids' height is 100%.</summary>
                            try {
                                if (kendoGrid._resize) {
                                    kendoGrid.element.find('.k-grid-content').css('height', '10px');
                                    kendoGrid._resize(kendoGrid.getSize(), true);
                                } else {
                                    kendoGrid.resize();
                                    console.warn("kendoGrid internal function '_resize' was not found");
                                }
                            } catch (e) {
                                console.warn("forceResize is failed, ", kendoGrid, e);
                            }
                        }
                    },
                    tabstrip: {
                        displayLoading: function (tabstrip) {
                            ///<summary>Displays loading process on the tabStrip</summary>
                            ///<returns>N/A</returns>
                            if (tabstrip == null)
                                return;

                            function showLoading() {
                                window.setTimeout(function () {
                                    kendo.ui.progress(tabstrip.element, true);
                                });
                            }

                            function hideLoading() {
                                window.setTimeout(function () {
                                    kendo.ui.progress(tabstrip.element, false);
                                });
                            }

                            tabstrip.bind("select", showLoading);
                            tabstrip.bind("activate", hideLoading);
                            tabstrip.bind("contentLoad", hideLoading);
                        },
                        showHideTab: function (tabstrip, tabIndex, hide) {
                            ///<summary>Show/hide the specified tab</summary>
                            ///<returns>N/A</returns>
                            try {
                                if (hide) {
                                    $(tabstrip.items()[tabIndex]).css("display", "none");
                                } else {
                                    $(tabstrip.items()[tabIndex]).css("display", "inline-block");
                                }
                            } catch (e) {
                                console.warn("tabStripShowHide is failed, tabIndex=" + tabIndex, tabstrip, e);
                            }
                        }
                    },
                    listview: {
                        selectRowByUid: function (kendoList, rowUid) {
                            ///<summary>Selects the specified row by Uid</summary>
                            ///<returns>N/A</returns>
                            try {
                                var element = kendoList.element.find("div[data-uid=\"" + rowUid + "\"]");
                                kendoList.select(element);

                            } catch (e) {
                                console.warn("selectRowByUid is failed ", kendoList, rowUid, " > error> ", e);
                            }
                        },
                    },
                    treelist: {
                        actionForSubItems: function (treeList, dataItem, action, onlyFirstLevel) {
                            ///<summary>Executes a callback for each sub item</summary>
                            ///<param name="onlyFirstLevel">If true, only the direct children of the item will be applied</param>
                            ///<returns>N/A</returns>
                            if (dataItem == null || action == null)
                                return;

                            var children = treeList.dataSource.childNodes(dataItem);
                            for (var i = 0; i < children.length; i++) {
                                var child = children[i];

                                action(child);

                                if (!onlyFirstLevel)
                                    kendoHelpers.treelist.actionForSubItems(treeList, child, action, onlyFirstLevel);
                            }

                            //// the dev should update the tree to replicate the changes to the UI
                            // treeList.refresh();
                        },
                        getColumnDefinition: function (treeList, fieldName) {
                            ///<summary>[Alias] Gets the treeList column definition</summary>
                            ///<returns>Kendo Column definition if found, otherwise null</returns>
                            return kendoHelpers.grid.getColumnDefinition(treeList, fieldName);
                        },

                    },
                    treeview: {
                        applyRightClickSelection: function (treeview, onRightClick) {
                            ///<summary>Makes treeview select the item on the right click</summary>
                            ///<returns>N/A</returns>
                            var element = treeview.element;
                            if (element == null) {
                                console.error("applyRightClickSelection failed because of null treeview.element", treeview);
                                return;
                            }
                            element.on("mousedown", ".k-item", function (event) {
                                if (event.which === 3) {
                                    event.stopPropagation(); // to avoid propagation of this event to the root of the treeview

                                    treeview.select(this);

                                    if (onRightClick) {
                                        onRightClick();
                                    }
                                }
                            });
                        },
                        checkSubItems: function (node, check, checkSubItems) {
                            ///<summary>Checks the node and its sub items</summary>
                            ///<returns>N/A</returns>
                            var nodeChildren = node.children.view();
                            for (var i = 0; i < nodeChildren.length; i++) {
                                var n = nodeChildren[i];
                                n.checked = check;

                                // make the tree update itself
                                n.trigger("change", { field: "checked" });

                                if (checkSubItems && n.hasChildren) {
                                    kendoHelpers.treeview.checkSubItems(n, check, checkSubItems);
                                }
                            }
                        },
                        getCheckedItems: function (treeview, uncheck) {
                            ///<summary>Returns the checked items if there is any</summary>
                            ///<returns>DataItem Array if found, otherwise empty array</returns>
                            ///<param name="uncheck">Should uncheck the nodes</param>
                            // Source: http://blogs.telerik.com/kendoui/posts/13-10-17/how-to-get-the-checked-items-from-a-treeview-with-checkboxes

                            var nodes = treeview.dataSource.view();

                            function getCheckedNodes(nodes) {
                                var node, childCheckedNodes;
                                var checkedNodes = [];

                                for (var i = 0; i < nodes.length; i++) {
                                    node = nodes[i];
                                    if (node.checked) {
                                        checkedNodes.push(node);
                                        if (uncheck) {
                                            node.checked = false;
                                        }
                                    }

                                    // to understand recursion, first
                                    // you must understand recursion
                                    if (node.hasChildren) {
                                        childCheckedNodes = getCheckedNodes(node.children.view());
                                        if (childCheckedNodes.length > 0) {
                                            checkedNodes = checkedNodes.concat(childCheckedNodes);
                                        }
                                    }
                                }
                                return checkedNodes;
                            }

                            return getCheckedNodes(nodes);
                        },
                        reloadSelectedNode: function (treeview, childrenOnly) {
                            ///<summary>Reloads the selected node's parent in order to refresh the node itself</summary>
                            ///<param name="childrenOnly">If true, only the children of the selected node will update</param>
                            try {
                                var item = treeview.select();
                                if (item != null) {
                                    var dataItem = treeview.dataItem(item);

                                    if (childrenOnly && dataItem && dataItem.hasChildren) {

                                        dataItem.loaded(false);
                                        dataItem.load();

                                    } else {
                                        var parent = treeview.parent(item);
                                        var parentDataItem = treeview.dataItem(parent);

                                        if (parentDataItem != null) {
                                            parentDataItem.loaded(false);
                                            parentDataItem.load();
                                        } else {
                                            treeview.dataSource.read();
                                        }
                                    }
                                }
                            } catch (e) {
                                console.warn("treeViewReloadSelectedNode is failed. No node is selected. Realoding the whole tree", treeview);
                                treeview.dataSource.read();
                            }
                        }
                    },
                    upload: {
                        hasAnyFileSelected: function (kendoUpload) {
                            ///<summary>Check if any file is selected</summary>
                            ///<returns type="Boolean">Boolean</returns>
                            if (!kendoUpload.wrapper) {
                                return false;
                            }
                            return kendoUpload.wrapper.find(".k-file").length > 0;
                        },

                        getUploadElements: function (kendoUpload) {
                            ///<summary>Retrieving the upload elements from the upload control</summary>
                            ///<returns>Array of elements if found, otherwise null</returns>
                            if (!kendoUpload.wrapper) {
                                return null;
                            }
                            return kendoUpload.wrapper.find("[data-role=upload]");
                        },
                    },
                    dropdown: {
                        //makeItemsAutoWith: function (kendoDropDown) {
                        //	///<summary>Makes drop-down items auto width</summary>
                        //	var name = "#" + kendoDropDown.element.attr('id') + "-list";
                        //	$(name).addClass('k-dropdown-items-auto-width');
                        //	//.k-dropdown-items-auto-width.k-list-container {
                        //	//	-moz-min-width: 130px !important;
                        //	//	-ms-min-width: 130px !important;
                        //	//	-o-min-width: 130px !important;
                        //	//	-webkit-min-width: 130px !important;
                        //	//	min-width: 130px !important;
                        //	//	width: auto !important;
                        //	//}

                        //	//.k-dropdown-items-auto-width.k-list-container .k-list {
                        //	//	width: auto !important;
                        //	//}
                        //},
                    },
                    validator: {
                        isValid: function (form) {
                            ///<summary>Triggers kendo validation for form and returns the status of form</summary>
                            ///<returns type="Boolean">Boolean</returns>
                            try {
                                form = $(form);
                                if (form == null || form.length == 0)
                                    return false;

                                var validator = form.kendoValidator().data("kendoValidator");
                                if (!validator.validate())
                                    return false;
                                return true;
                            } catch (e) {
                                console.warn("isValid is failed, ", form, e);
                                return false;
                            }
                        },
                        hideMessages: function (form) {
                            ///<summary>Enables kendo validation for form then hides any visible message</summary>
                            ///<returns>N/A</returns>
                            try {
                                form = $(form);
                                if (form == null || form.length == 0)
                                    return;

                                var validator = form.kendoValidator().data("kendoValidator");
                                validator.hideMessages();
                            } catch (e) {
                                console.warn("hideMessages is failed, ", form, e);
                            }
                        },
                    },
                    getDataItemById: function (kendoWidget, id) {
                        ///<summary>Returns the dataItem found by Id</summary>
                        ///<returns>DataItem if found, otherwise null</returns>
                        try {
                            return kendoWidget.dataSource.get(id);
                        } catch (e) {
                            console.warn("getDataItemById is failed, id=" + id + " for ", kendoWidget, e);
                        }
                        return null;
                    },
                    enableControls: function (container, enable) {
                        ///<summary>Enables/Disables all the kendo controls in the container.</summary>
                        try {
                            $(container).find('[data-' + kendo.ns + 'role]').addBack().each(function () {
                                var data = $(this).data();
                                for (var key in data) {
                                    if (key.indexOf('kendo') === 0 && typeof data[key].enable === "function") {
                                        data[key].enable(enable);
                                    }
                                }
                            });
                        } catch (e) {
                            console.warn("enableControls is failed to set enable=" + enable + " in " + container, e);
                        }
                    }

                };


                if (kendo["laputa"] == null) {
                    kendo["laputa"] = {util: util};
                } else {
                    kendo["laputa"]["util"] = util;
                }

            })(window.kendo.jQuery);
        })();

        return window.kendo;

    },
    typeof define == 'function' && define.amd ? define : function (_, f) {
        f();
    }
)
;
