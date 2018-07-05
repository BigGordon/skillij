/**
 * 用户登录
 */
login = function () {
    var me = this;

    /**
     * login页面初始化
     */
    me.init = function () {
        //检查登录状态，已登录则跳转
        me._redirectIfLogin();
        //登录按钮
        me._initLoginBtn();
    };

    /**
     * 检查登录状态，已登录则跳转
     * @private
     */
    me._redirectIfLogin = function () {
        //若token已失效则不用跳转
        SkillijUtil.loginCheck(function () {
            var token = localStorage.getItem("currentUser_token");
            if (token != null) {
                window.location.href = "admin.html";
            }
        }, null);
    };

    /**
     * 初始化登录按钮
     * @private
     */
    me._initLoginBtn = function () {
        $("#btn-login").on("click", function () {
            var user = $("#inputEmail").val();
            var passwd = $("#inputPassword").val();

            if (user == null || user == "") {
                layer.open({title: '温馨提示', content: '请输入账号'});
                return;
            }

            if (passwd == null || passwd == "") {
                layer.open({title: '温馨提示', content: '请输入密码'});
                return;
            }

            $.ajax({
                type: "POST",
                url: "/account/login",
                async: false,
                data: {
                    user: user,
                    passwd: passwd
                },
                success: function (res) {
                    var resJson = JSON.parse(res);
                    var result = resJson.data.loginResult;
                    var token = resJson.data.token;
                    if (result === "登录成功") {
                        //将用户名和jwt token存入localStorage
                        localStorage.setItem("currentUser_name",user);
                        localStorage.setItem("currentUser_token",token);
                        window.location.href = "admin.html";
                    } else {
                        layer.open({
                            title: '温馨提示',
                            content: result
                        });
                        // alert(result);
                    }
                },
                error: function (e) {
                    alert("请求出错！");
                }
                
            });
        });
    };


    return me;
};