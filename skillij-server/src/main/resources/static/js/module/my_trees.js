/**
 * 我的技能树
 */
my_trees = function() {
    var me = this;

    var currentTreeId;
    var currentTreeName;

    var reloadFlag = 0;

    var eChartsTree = echarts.init(document.getElementById('tree'));

    /**
     * 管理页面初始化
     */
    me.init = function () {
        //技能树标题栏树名列表初始化
        me._initTreeTitleName();
        //技能树标题栏树名按钮初始化
        me._initTreeTitleBtns();

        if(localStorage.getItem("reloadFlag") === "1") //判断是否为1来确定是否局部刷新页面中的表
            currentTreeId = localStorage.getItem("currentTreeId");

        //页面刷新reloadFlag初始化
        me._initReloadFlag();
        //将数据加载到echart tree
        me._initTreeData(currentTreeId);
    };

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
     * 页面刷新reloadFlag初始化
     * @private
     */
    me._initReloadFlag = function() {
        reloadFlag = 0;
        localStorage.setItem("reloadFlag",reloadFlag.toString());
    }

    /**
     * 装载树名
     * @param titles
     * @private
     */
    me._loadTitles = function(titles) {
        for (var i = 0;i <titles.length; i++) {
            var title = titles[i];

            var $skillTreeName = $('#skillTreeName');
            $skillTreeName.append("<a treeName=\"" + title.treeName.toString() + "\" treeId=\"" +
                title.treeId.toString()+ "\" class=\"treeName\" href=\"#\">" + title.treeName.toString() + "</a>")
        }
    };

    /**
     * 向后台请求树节点数据
     * @param currentTreeId
     * @private
     */
    me._initTreeData = function (currentTreeId) {
        eChartsTree.showLoading();
        $.ajax({
            type: "GET",
            url: "/account/skills",
            async: true,
            data: {
                user: localStorage.getItem("currentUser_name"),
                treeId: currentTreeId
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

}