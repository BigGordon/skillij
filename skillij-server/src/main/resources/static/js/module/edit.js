/**
 * 技能树编辑
 */
edit = function () {
    var me = this;

    var checkBoxHtml ="<div><label><input class=\"checkbox\" type=\"checkbox\" value=\"\"></label></div>";

    var selectHtml = "<select class='selectpicker' title='请选择'>" +
        "<option>0</option>" +
        "<option>1</option>" +
        "<option>2</option>" +
        "<option>3</option>" +
        "<option>4</option>" +
        "<option>5</option>" +
        "<option>6</option>" +
        "</select>";

    var editingIds = new Array();

    var statusOptions = {
        ON_DONE: 0,
        ON_EDIT: 1,
        ON_NEW: 2
    };

    var status;

    var currentTreeId;
    var currentTreeName;

    var reloadFlag = 0;

    /**
     * edit页面初始化
     */
    me.init = function () {

        //模板初始化
        me._initTemplet();
        //技能树标题栏初始化
        me._initTreeTitle();

        if(localStorage.getItem("reloadFlag") == "1") //判断是否为1来确定是否局部刷新页面中的表
            currentTreeId = localStorage.getItem("currentTreeId");

        //页面刷新reloadFlag初始化
        me._initReloadFlag();
        //加载节点
        me._initNodeTable(currentTreeId);//使用第一个id加载节点
        //按钮初始化
        me._initBtns();
    };

    /**
     * 页面刷新reloadFlag初始化
     * @private
     */
    me._initReloadFlag = function() {
        reloadFlag = 0;
        localStorage.setItem("reloadFlag",reloadFlag.toString());
    }

    /**
     * 技能树标题栏初始化
     * @private
     */
    me._initTreeTitle = function () {
        //技能树标题栏树名列表初始化
        me._initTreeTitleName();
        //技能树标题栏树名按钮初始化
        me._initTreeTitleBtns();
    }

    /**
     * 技能树标题栏树名列表初始化
     * @private
     */
    me._initTreeTitleName = function() {
        $.ajax({
            type:"GET",
            url:"edit/titles",
            async:false,
            data: {
                user: localStorage.getItem("currentUser_name")
            },
            success: function (res) {
                var resJson = JSON.parse(res);
                var titles = resJson.data.treeTitles;
                me._loadTitles(titles);
                currentTreeId = titles[0].treeId;
                currentTreeName = titles[0].treeName;
                //window.location.reload();
            },
            error:function (e) {
                alert("请求出错！");
            }
        })
    }

    me._loadTitles = function(titles) {
        for (var i = 0;i <titles.length; i++) {
            var title = titles[i];

            var $newTreeBtn = $('#new-tree-btn');
            $newTreeBtn.before("<a treeName=\"" + title.treeName.toString() + "\" treeId=\"" +
                title.treeId.toString()+ "\" class=\"treeName\" href=\"#\">" + title.treeName.toString() + "</a>")
        }
    }


    /**
     * 技能树标题栏树名按钮初始化
     * @private
     */
    me._initTreeTitleBtns = function() {
        $("#skillTreeName").on('click','.treeName',function(){
            currentTreeId = $(this).attr("treeId");
            currentTreeName = $(this).attr("treeName");
            reloadFlag = 1;
            localStorage.setItem("currentTreeId",currentTreeId);
            localStorage.setItem("currentTreeName",currentTreeName);
            localStorage.setItem("reloadFlag",reloadFlag.toString());
            //me._initNodeTable(currentTreeId);
            window.location.reload();
        });
    }


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
        me._initDoneBtn();
        //删除按钮初始化
        me._initDeleteBtn();
        //新建按钮初始化
        me._initNewBtn();
        //新增技能树按钮初始化
        me._initNewTreeBtn();
        //删除技能树按钮初始化
        me._initDelTreeBtn();
        //修改技能树名按钮初始化
        me._initEditTreeBtn();
    };

    /**
     * 修改技能树名按钮初始化
     * @private
     */
    me._initEditTreeBtn = function() {
        $("#edit-tree-btn").on("click",function () {
            layer.prompt({
                formType:0,
                value:"",
                title:"请输入" + localStorage.getItem("currentTreeName") + "技能树新名称",
                area:['800px','350px'],
                yes: function (index, layero) {

                    var newTreeName = layero.find(".layui-layer-input").val();
                    if(newTreeName == null || newTreeName=="") {
                        alert("请输入该技能树新名称！");
                    }
                    else {
                        layer.close(index);
                        $.ajax({
                            type:"POST",
                            url:"edit/editTree",
                            async:true,
                            data:{
                                treeId:localStorage.getItem("currentTreeId"),
                                userName:localStorage.getItem("currentUser_name"),
                                newTreeName:newTreeName
                            },
                            success:function (res) {
                                var resJson = JSON.parse(res);
                                var result =resJson.data.editTreeResult;
                                layer.open({title:'温馨提示',content:result,end:function(){
                                        window.location.reload();
                                    }});
                            },
                            error:function(e) {
                                alert("请求出错！");
                            }

                        });
                    }
                }
            });
        });
    }

    /**
     * 删除技能树按钮初始化
     * @private
     */
    me._initDelTreeBtn = function() {
        $("#del-tree-btn").on("click",function() {
            layer.open({
                title: '温馨提示',
                content: '确定要删除'+localStorage.getItem("currentTreeName")+'吗？',
                btn: ['确定', '取消'],
                yes: function () {
                    $.ajax({
                        type: "POST",
                        url: "edit/delTree",
                        async: true,
                        data: {
                            treeId: localStorage.getItem("currentTreeId")
                        },
                        success: function (res) {
                            layer.open({
                                title: '温馨提示', content: '删除技能树成功', end: function () {
                                    //重新加载表格
                                    window.location.reload();
                                }
                            })
                        },
                        error: function (e) {
                            alert("请求出错！");
                        }
                    });
                }
            });
        });
    }

    /**
     * 新增技能树按钮初始化
     * @private
     */
    me._initNewTreeBtn = function() {
        $("#new-tree-btn").on("click",function () {
            layer.prompt({
                formType: 0,
                value: "",
                title: '请输入新技能树名称',
                area: ['800px', '350px'],//自定义文本域宽高
                yes: function (index, layero) {

                    var newTreeName = layero.find(".layui-layer-input").val();
                    if (newTreeName == null || newTreeName == "") {
                        alert("请输入技能树名称！");
                    }
                    else {
                        layer.close(index);
                        $.ajax({
                            type: "POST",
                            url: "edit/newTree",
                            async: true,
                            data: {
                                newTreeName: newTreeName,
                                user: localStorage.getItem("currentUser_name")
                            },
                            success: function (res) {
                                var resJson = JSON.parse(res);
                                var result = resJson.data.newTreeResult;
                                layer.open({title: '温馨提示', content: result, end: function () {
                                        //重新加载表格
                                        window.location.reload();
                                    }})
                            },
                            error:function (e) {
                                alert("请求出错！");
                            }
                        });
                    }
                }
            });
        })
    }

    /**
     * 新建按钮初始化
     * @private
     */
    me._initNewBtn = function() {
        $("#new-btn").on("click", function () {
            //增加一行用于新建技能
            var $row = $("<tr id='new-tr'></tr>");

            //复选框
            $row.append("<td editable = false >" + checkBoxHtml + "</td>");

            $row.append("<td editable = true ></td>");
            $row.append("<td editable = false >" + selectHtml + "</td>");
            $row.append("<td editable = true ></td>");
            $row.append("<td editable = false ></td>");
            $row.append("<td editable = true ></td>");

            $("#tbody").prepend($row);

            //添加输入文本框
            $("#new-tr").children().each(function () {
                if ($(this).attr("editable") == 'true') {
                    var val = $(this).html();
                    $(this).empty();
                    $(this).append(me._getInputTextHtml(val));
                }
            });

            //按钮配置
            me._onOperation();
            //更新状态
            status = statusOptions.ON_NEW;
        });
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
            layer.open({
                title: '温馨提示',
                content: '确定要删除吗',
                btn: ['确定', '取消'],
                yes: function () {
                    //向后台发送删除信息
                    me._sendDeleteMsg();
                }})
        });
    };

    /**
     * 修改完成按钮初始化
     * @private
     */
    me._initDoneBtn = function() {
        $("#confirm-btn").on("click", function () {
            var doneFlag = true;
            switch (status) {
                case statusOptions.ON_EDIT:
                    me._storeEditResults();
                    break;
                case statusOptions.ON_NEW:
                    doneFlag = me._storeNewResults();
                    break;
            }
            if (doneFlag) {
                //将按钮状态复原
                me._doneOperation();
                //更新状态
                status = statusOptions.ON_DONE;
            }
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
                        if ($(this).attr("editable") === 'true') {
                            var val = $(this).html();
                            $(this).empty();
                            $(this).append(me._getInputTextHtml(val));
                        }
                        if ($(this).attr("selectable") === 'true') {
                            var oldProficiency = $(this).html();
                            $(this).empty();
                            $(this).append(selectHtml);
                            $(this).find('select').val(oldProficiency);
                        }
                    });

                }
                $(this).attr("disabled", true);
            });
            //限制其他的按钮
            me._onOperation();
            //更新状态
            status = statusOptions.ON_EDIT;
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
     * 储存技能修改结果
     * @private
     */
    me._storeEditResults = function() {
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
                    aJson.proficiency = td.eq(2).children("select").val();
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
                nodes: JSON.stringify(json),
                user: localStorage.getItem("currentUser_name"),
                treeId: currentTreeId
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
    };

    /**
     * 储存新建技能结果
     * @private
     */
    me._storeNewResults = function() {
        //采集用户输入的新值
        var newTr = $("#new-tr");
        var aJson =
            {skillName: "", parentSkillName: "", proficiency: "", description: ""};
        var td = newTr.children();
        aJson.skillName = td.eq(1).children("input").val();
        aJson.proficiency = td.eq(2).children("select").val();
        aJson.parentSkillName = td.eq(3).children("input").val();
        aJson.description = td.eq(5).children("input").val();
        //对新建的结果前台进行校验
        if (aJson.skillName === null || aJson.skillName.trim() === "") {
            layer.open({title: '温馨提示', content: '请输入节点名称'});
            return false;
        }
        if (aJson.parentSkillName === null || aJson.parentSkillName.trim() === "") {
            layer.open({title: '温馨提示', content: '请输入父节点名称'});
            return false;
        }
        if (aJson.proficiency === null) {
            layer.open({title: '温馨提示', content: '请输入熟练度'});
            return false;
        }
        //将新建的结果发给后台
        $.ajax({
            type: "POST",
            url: "/edit/new",
            async: true,
            data: {
                nodes: JSON.stringify(aJson),
                user: localStorage.getItem("currentUser_name"),
                treeId: currentTreeId
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
                window.location.reload();
            }
        });
        return true;
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
    me._initNodeTable = function (treeId) {

        $.ajax({
            type: "GET",
            url: "/edit/nodes",
            async: false,//为了支持表格功能不能异步
            data: {
                user: localStorage.getItem("currentUser_name"),
                treeId: treeId
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
            $row.append("<td editable = false selectable = true>" + node.proficiency.toString() + "</td>");
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
        $(".checkbox").each(function () {
            $(this).attr("disabled", true);
        });


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