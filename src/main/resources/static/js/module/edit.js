/**
 * 技能树编辑
 */
edit = function () {
    var me = this;

    var checkBoxHtml ="<div><label><input class=\"checkbox\" type=\"checkbox\" value=\"\"></label></div>";

    var editingIds = new Array();

    /**
     * edit页面初始化
     */
    me.init = function () {
        //模板初始化
        me._initTemplet();
        //加载技能树节点表
        me._initNodeTable();
        //按钮初始化
        me._initBtns();
    };

    /**
     * 按钮初始化
     * @private
     */
    me._initBtns = function() {
        //全选复选框初始化
        me._initCheckBoxAll();
        //修改按钮初始化
        me._initEditBtn();
        //完成按钮初始化
        me._initEditDoneBtn();
        //删除按钮初始化
        me._initDeleteBtn();
    };

    /**
     * 删除按钮初始化
     * @private
     */
    me._initDeleteBtn = function() {
        $("#delete-btn").on("click", function () {
            //检查技能是否勾选
            if (!me._isCheckBoxSelected()) {
                layer.open({title: '温馨提示', content: '请勾选需要操作的技能'});
                return;
            }
            editingIds.splice(0, editingIds.length);//清空数组
            //统计要删除的id
            $(".checkbox").each(function () {
                if ($(this).prop("checked") && $(this).attr("id") !== "main-checkbox") {
                    var tr = $(this).parents("tr");
                    editingIds.push(tr.attr("skillId"));
                }
            });
            //弹出删除提示框
            layer.open({title: '温馨提示', content: '确定要删除吗', yes: function () {
                    //向后台发送删除信息
                    me._sendDeleteMsg();
                }})
        });
    };

    /**
     * 修改完成按钮初始化
     * @private
     */
    me._initEditDoneBtn = function() {
        $("#confirm-btn").on("click", function () {
            //采集用户输入的修改值
            var json = [];
            for (var i = 0; i < editingIds.length; i++) {
                var id = editingIds[i];
                $("#dataTable-skill tr").each(function () {
                    if ($(this).attr("skillId") === id) {
                        //单条tr转化为json
                        var aJson =
                            {skillId: "", skillName: "", parentSkillName: "", proficiency: "", description: ""};
                        var td = $(this).children();
                        aJson.skillId = id;
                        aJson.skillName = td.eq(1).children("input").val();
                        aJson.proficiency = td.eq(2).children("input").val();
                        var parentSkillName = td.eq(3).children("input").val();
                        if (parentSkillName == null || parentSkillName.trim() ==="") {
                            aJson.parentSkillName = "root";
                        } else {
                            aJson.parentSkillName = parentSkillName;
                        }
                        aJson.description = td.eq(5).children("input").val();
                        json.push(aJson);
                    }
                });
            }
            //将修改的结果发给后台
            $.ajax({
                type: "POST",
                url: "/edit/revise",
                async: true,
                data: {
                    nodes: JSON.stringify(json)
                },
                success: function (res) {
                    var resJson = JSON.parse(res);
                    var result = resJson.data.result;
                    layer.open({title: '温馨提示', content: result, end: function () {
                            //重新加载表格
                            window.location.reload();
                        }})
                },
                error: function (e) {
                    alert("请求出错！");
                }

            });
            //将按钮状态复原
            me._doneOperation();
        });
    };

    /**
     * 修改按钮初始化
     * @private
     */
    me._initEditBtn = function() {
        $("#edit-btn").on("click", function () {
            //检查技能是否勾选
            if (!me._isCheckBoxSelected()) {
                layer.open({title: '温馨提示', content: '请勾选需要操作的技能'});
                return;
            }
            editingIds.splice(0, editingIds.length);//清空数组
            $(".checkbox").each(function () {
                if ($(this).prop("checked") && $(this).attr("id") !== "main-checkbox") {
                    var tr = $(this).parents("tr");
                    editingIds.push(tr.attr("skillId"));
                    tr.children().each(function () {
                        if ($(this).attr("editable") == 'true') {
                            var val = $(this).html();
                            $(this).empty();
                            $(this).append(me._getInputTextHtml(val));
                        }
                    });
                }
                $(this).attr("disabled", true);
            });
            me._onOperation();
        });
    };

    /**
     * 向后台发出删除技能请求
     * @private
     */
    me._sendDeleteMsg = function() {
        $.ajax({
            type: "POST",
            url: "/edit/delete",
            async: true,
            data: {
                ids: JSON.stringify(editingIds)
            },
            success: function (res) {
                layer.open({title: '温馨提示', content: '删除成功', end: function () {
                        //重新加载表格
                        window.location.reload();
                    }})
            },
            error: function (e) {
                alert("请求出错！");
            }

        });
    };

    /**
     * 全选复选框初始化
     * @private
     */
    me._initCheckBoxAll = function() {
        var mainCheckBox = $("#main-checkbox");
        mainCheckBox.attr("selected", false);
        //选中则全部选中
        mainCheckBox.on("click", function () {
            if (mainCheckBox.attr("selected")) {
                $(".checkbox").prop("checked",false);
                mainCheckBox.attr("selected", false);
            } else {
                $(".checkbox").prop("checked",true);
                mainCheckBox.attr("selected", true);
            }
        });
    };

    /**
     * 加载技能树节点表
     * @private
     */
    me._initNodeTable = function () {

        $.ajax({
            type: "GET",
            url: "/edit/nodes",
            async: false,//为了支持表格功能不能异步
            data: {
                user: "gordon"//TODO: 需要根据不同用户进行设置
            },
            success: function (res) {
                var resJson = JSON.parse(res);
                var nodes = resJson.data.skillNodes;
                me._loadNodeTable(nodes);
            },
            error: function (e) {
                alert("请求出错！");
            }

        });
    };

    /**
     * 将服务器返回的数据填入表格
     * @param nodes
     * @private
     */
    me._loadNodeTable = function(nodes) {
        for (var i = 0; i < nodes.length; i++) {
            var node = nodes[i];

            var $row = $("<tr></tr>");
            $row.attr("skillId", node.skillId);

            //复选框
            $row.append("<td editable = false >" + checkBoxHtml + "</td>");

            $row.append("<td editable = true >" + node.skillName.toString() + "</td>");
            $row.append("<td editable = true >" + node.proficiency.toString() + "</td>");
            $row.append("<td editable = true >" + node.parentSkillName + "</td>");
            $row.append("<td editable = false >" + node.childrenName.toString() + "</td>");
            var descrip = node.descip;
            descrip = descrip != null? descrip.toString(): "";
            $row.append("<td editable = true >" + descrip + "</td>");

            $("#tbody").append($row);
        }
    };

    /**
     * 检查技能是否勾选
     * @private
     */
    me._isCheckBoxSelected = function() {
        var flag = false;
        $(".checkbox").each(function () {
            if ($(this).prop("checked")) {
                flag = true;
            }
        });
        return flag;
    };

    /**
     * 获取可修改输入文本
     * @param value
     * @returns {string}
     * @private
     */
    me._getInputTextHtml = function(value) {
        return "<input type='text' value='" + value.toString() + "'/>";
    };

    /**
     * 操作环境，激活确定按钮，使其他按钮失效
     * @private
     */
    me._onOperation = function() {
        $("#new-btn").attr("disabled", true);
        $("#delete-btn").attr("disabled", true);
        $("#edit-btn").attr("disabled", true);
        $("#confirm-btn").attr("disabled", false);


        $("#dataTable-skill_length").hide();
        $("#dataTable-skill_filter").hide();
        $("#dataTable-skill_paginate").hide();

    };

    /**
     * 操作结束，使完成按钮失效，激活其他按钮、复选框
     * @private
     */
    me._doneOperation = function() {
        $("#new-btn").attr("disabled", false);
        $("#delete-btn").attr("disabled", false);
        $("#edit-btn").attr("disabled", false);
        $("#confirm-btn").attr("disabled", true);
        $(".checkbox").each(function () {
            $(this).attr("disabled", false);
        });

        $("#dataTable-skill_length").show();
        $("#dataTable-skill_filter").show();
        $("#dataTable-skill_paginate").show();
    };

    /**
     * 模板自带js
     */
    me._initTemplet = function () {
//Loads the correct sidebar on window load,
//collapses the sidebar on window resize.
// Sets the min-height of #page-wrapper to window size
        $(function() {
            $(window).bind("load resize", function() {
                var topOffset = 50;
                var width = (this.window.innerWidth > 0) ? this.window.innerWidth : this.screen.width;
                if (width < 768) {
                    $('div.navbar-collapse').addClass('collapse');
                    topOffset = 100; // 2-row-menu
                } else {
                    $('div.navbar-collapse').removeClass('collapse');
                }

                var height = ((this.window.innerHeight > 0) ? this.window.innerHeight : this.screen.height) - 1;
                height = height - topOffset;
                if (height < 1) height = 1;
                if (height > topOffset) {
                    $("#page-wrapper").css("min-height", (height) + "px");
                }
            });

            var url = window.location;
            // var element = $('ul.nav a').filter(function() {
            //     return this.href == url;
            // }).addClass('active').parent().parent().addClass('in').parent();
            var element = $('ul.nav a').filter(function() {
                return this.href == url;
            }).addClass('active').parent();

            while (true) {
                if (element.is('li')) {
                    element = element.parent().addClass('in').parent();
                } else {
                    break;
                }
            }
        });

    };
};