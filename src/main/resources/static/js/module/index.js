/**
 * 首页
 */
index = function () {
    var me = this;

    var sideNameDom ="<a href='#' class='list-group-item' id='side-name'></a>";

    /**
     * index页面初始化
     */
    me.init = function () {
        //登录按钮
        me._initSideBarBtn();
    };
    
    me._initSideBarBtn = function () {
        $.ajax({
            type: "GET",
            url: "/account/get-side",
            async: true,

            success: function (res) {
                var resJson = JSON.parse(res);
                var names = resJson.data.sideAccounts;
                me._loadSideBar(names);
            },
            error: function (e) {
                alert("请求出错！");
            }

        });


    };

    me._loadSideBar = function (names) {
        var size = names.length;
        for (var i = 1; i <= size; i++) {
            $("#sidebar").children().append(sideNameDom);
            var sideNameId = 'side-name' + i.toString();
            $("#side-name").attr('id', sideNameId);
            var sideName = $("#" + sideNameId);
            sideName.html(names[i-1]);

            (function (i) {
                sideName.on("click", function () {
                    $.ajax({
                        type: "GET",
                        url: "/account/skills",
                        async: true,
                        data: {
                            user: names[i-1]
                        },
                        success: function (res) {
                            var resJson = JSON.parse(res);
                            me._loadZtree(resJson.data.skills);
                        },
                        error: function (e) {
                            alert("请求出错！");
                        }

                    });
                });
            })(i);
        }

    };
    
    me._loadZtree = function (zNodes) {
        var zTreeObj;
        // zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
        var setting = {};
        // zTree 的数据属性，深入使用请参考 API 文档（zTreeNode 节点数据详解）
        zTreeObj = $.fn.zTree.init($("#skill-tree"), setting, zNodes);
    };




    return me;


};