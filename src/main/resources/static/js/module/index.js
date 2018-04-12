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
                    $("#tree").show();
                    eChartsTree.showLoading();
                    $.ajax({
                        type: "GET",
                        url: "/account/skills",
                        async: true,
                        data: {
                            user: "java"//names[i-1]
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
    
    me._loadTree = function (data) {
        eChartsTree.hideLoading();

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
                    animationDurationUpdate: 750
                }
            ]
        });
    };




    return me;


};