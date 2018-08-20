/**
 * 用户管理
 */
admin = function() {
    var me = this;

    /**
     * 管理页面初始化
     */
    me.init = function () {
        //登录状态检查
        me._loginCheck();
        //模板初始化
        me._initTemplet();
        //左侧控制按钮初始化
        me._initLeftBtns();
        //欢迎栏初始化
        me._initWelcomeWords();
    };

    /**
     * 检查token有效性
     * @private
     */
    me._loginCheck = function () {
        SkillijUtil.loginCheck(null, function () {
            window.location.href = "login.html";
        });
    };


    /**
     * admin页面模板自带js
     */
    me._initTemplet = function () {
        //模板js
        $(function() {
            $('#side-menu').metisMenu();
        });

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

    /**
     * 左侧控制按钮初始化
     * @private
     */
    me._initLeftBtns = function () {
        //“编辑”按钮
        me._initEditBtn();
        //“消息”按钮
        me._initMessageBtn();
        //“查找用户”按钮
        me._initNewChatBtn();
        //“更改邮箱”按钮
        me._initChangeMailBtn();
        //“更改密码”按钮
        me._initChangePasswdBtn();
        //“退出登录”按钮
        me._initLogoutBtn();
    };

    /**
     * 编辑按钮初始化
     * @private
     */
    me._initEditBtn = function () {
        $("#editBtn").on("click", function () {
            var pageWrapper = $("#page-wrapper");
            pageWrapper.empty();
            pageWrapper.attr("src", "edit.html");
        })
    };

    /**
     * 消息按钮初始化
     * @private
     */
    me._initMessageBtn = function () {
        $("#messageBtn").on("click", function () {
            var pageWrapper = $("#page-wrapper");
            pageWrapper.empty();
            pageWrapper.attr("src", "message.html");
        })
    };

    /**
     * 查找用户按钮初始化
     * @private
     */
    me._initNewChatBtn = function () {
        $("#newChatBtn").on("click", function () {
            layer.prompt({
                    title: '输入用户名',
                    formType: 0
                },
                function(pass, index){
                    $.ajax({
                        type: "POST",
                        url: "/chat/new-chat",
                        async: true,
                        data: {
                            fromUser:localStorage.getItem("currentUser_name"),
                            toUser: pass
                        },

                        success: function (res) {
                            var resJson = JSON.parse(res);
                            var result = resJson.data.addChatResult;
                            if (result === "新建对话成功") {
                                layer.close(index);
                            }
                            //刷新消息页面
                            var pageWrapper = $("#page-wrapper");
                            pageWrapper.empty();
                            pageWrapper.attr("src", "message.html");
                            layer.msg(result);
                        },
                        error: function (e) {
                            alert("请求出错！");
                        }
                    });
            });
        })
    };

    /**
     * 更改邮箱按钮
     * @private
     */
    me._initChangeMailBtn = function () {
        $("#changeMailBtn").on("click", function () {
            var pageWrapper = $("#page-wrapper");
            pageWrapper.empty();
            pageWrapper.attr("src", "change_mail.html");
        })
    };

    /**
     * 更改密码按钮
     * @private
     */
    me._initChangePasswdBtn = function () {
        $("#changePasswdBtn").on("click", function () {
            var pageWrapper = $("#page-wrapper");
            pageWrapper.empty();
            pageWrapper.attr("src", "change_passwd.html");
        })
    };

    /**
     * 退出登录按钮
     * @private
     */
    me._initLogoutBtn = function () {
        $("#logoutBtn").on("click", function () {
            //弹出确认登录提示框
            layer.open({
                title: '温馨提示',
                content: '确定要退出登录吗',
                btn: ['确定', '取消'],
                yes: function () {
                    localStorage.removeItem("currentUser_token");
                    window.location.href = "index.html";
                }});
        })
    };

    /**
     * 欢迎文字初始化
     * @private
     */
    me._initWelcomeWords = function () {
        var username = localStorage.getItem("currentUser_name");
        $("#welcome").html("Skillij 欢迎您，" + username);
    };


    return me;
};

