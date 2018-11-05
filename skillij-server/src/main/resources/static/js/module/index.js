/**
 * 首页
 */
index = function () {
    var me = this;

    var sideNameDom ="<a href='#' class='list-group-item' id='side-name'></a>";

    var eChartsTree = echarts.init(document.getElementById('tree'));

    /**
     * index页面初始化
     */
    me.init = function () {
        //登录状态判断
        me._initLoginStatus();
        //demo账号按钮
        me._initSideBarBtn();
        //初始化java演示
        me._initDemo();
    };

    /**
     * 根据登录状态更改导航栏
     * @private
     */
    me._initLoginStatus = function () {
        SkillijUtil.loginCheck(function () {
            $("#loginBtn").hide();
            $("#registerBtn").hide();
            var username = localStorage.getItem("currentUser_name");
            var adminBtn = $("#adminBtn")
            adminBtn.html(username + " 的管理平台");
            adminBtn.show();
        }, null);
    };

    /**
     * 初始化demo中的按钮
     * @private
     */
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

    /**
     * 切换技能树按钮
     * @param names
     * @private
     */
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
                    eChartsTree.showLoading();
                    $.ajax({
                        type: "GET",
                        url: "/account/skills",
                        async: true,
                        data: {
                            user: names[i-1]
                        },
                        success: function (res) {
                            var resJson = JSON.parse(res);
                            me._loadTree(resJson.data.skills);
                        },
                        error: function (e) {
                            alert("请求出错！");
                        }

                    });
                });
            })(i);
        }

    };

    /**
     * 将数据装载到eChartsTree上
     * @param data
     * @private
     */
    me._loadTree = function (data) {
        eChartsTree.hideLoading();
        eChartsTree.clear();

        echarts.util.each(data.children, function (datum, index) {
            index % 2 === 0 && (datum.collapsed = true);
        });

        eChartsTree.setOption(option = {
            tooltip: {
                trigger: 'item',
                triggerOn: 'mousemove'
            },
            series: [
                {
                    type: 'tree',

                    data: [data],

                    top: '1%',
                    left: '7%',
                    bottom: '1%',
                    right: '20%',

                    symbolSize: 7,

                    label: {
                        normal: {
                            position: 'left',
                            verticalAlign: 'middle',
                            align: 'right',
                            fontSize: 9
                        }
                    },

                    leaves: {
                        label: {
                            normal: {
                                position: 'right',
                                verticalAlign: 'middle',
                                align: 'left'
                            }
                        }
                    },

                    expandAndCollapse: true,
                    animationDuration: 550,
                    animationDurationUpdate: 750,
                    initialTreeDepth: -1
                }
            ]
        });
        window.location.href = "#demo";
    };

    /**
     * 初始化demo，默认数据为整棵java技能树
     * @private
     */
    me._initDemo = function () {
        eChartsTree.showLoading();
        $.ajax({
            type: "GET",
            url: "/account/skills",
            async: true,
            data: {
                user: "java",
                treeId: -1
            },
            success: function (res) {
                var resJson = JSON.parse(res);
                me._loadTree(resJson.data.skills);
            },
            error: function (e) {
                alert("请求出错！");
            }

        });
    };




    return me;


};