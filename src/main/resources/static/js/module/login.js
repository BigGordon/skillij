/**
 * 用户登录
 */
login = function () {
    var me = this;

    /**
     * login页面初始化
     */
    me.init = function () {
        //登录按钮
        me._initLoginBtn();
    }

    /**
     * 初始化登录按钮
     * @private
     */
    me._initLoginBtn = function () {
        $("#btn-login").on("click", function () {
            var user = $("#inputEmail").val();
            var passwd = $("#inputPassword").val();

            if (user == null || user == "") {
                alert("请输入账号");
                return;
            }

            if (passwd == null || passwd == "") {
                alert("请输入密码");
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
                    alert(resJson.data.loginResult);
                    $(location).attr('href', 'admin.html');
                },
                error: function (e) {
                    alert("请求出错！");
                }
                
            });
        });
    }

    return me;
}