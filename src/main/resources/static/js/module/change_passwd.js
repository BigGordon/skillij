/**
 * 修改密码
 */
change_passwd = function () {
    var me = this;

    /**
     * 修改密码页面初始化
     */
    me.init = function () {
        //确认修改按钮
        me._initConfirmBtn();
    };

    /**
     * 确认修改按钮
     * @private
     */
    me._initConfirmBtn = function () {
        $("#confirmBtn").on("click", function () {
            //输入校验
            var oldPasswd = $("#inputOldPassword").val();
            if (oldPasswd == null || $.trim(oldPasswd) === "") {
                me._warn("请输入旧密码");
                return false;
            }
            var newPasswd = $("#inputNewPassword").val();
            if (newPasswd == null || $.trim(newPasswd) === "") {
                me._warn("请输入新密码");
                return false;
            }
            if (newPasswd === oldPasswd) {
                me._warn("新旧密码相同");
                return false;
            }

            //密码格式校验
            var reg = /^.{6,20}$/;
            var passwdOK = reg.test(newPasswd);
            if(!passwdOK) {
                me._warn( "您输入的密码字符长度有误，合法长度为6-20个字符" );
                return false;
            }

            var newPasswd_again = $("#reInputNewPassword").val();
            if (newPasswd_again == null || $.trim(newPasswd_again) === "") {
                me._warn("请再次输入新密码");
                return false;
            }
            if (newPasswd !== newPasswd_again) {
                me._warn("新密码输入不一致");
                return false;
            }

            var username = localStorage.getItem("currentUser_name");
            if (username === null) {
                layer.open({
                    title: '温馨提示',
                    content: "登录已过期，请重新登录",
                    end: function () {
                        window.parent.location.href = "login.html";//父页面跳转至登录界面
                    }
                });
                return false;
            }

            $.ajax({
                type: "POST",
                url: "/account/change-passwd",
                async: true,
                data: {
                    username: username,
                    oldPasswd: oldPasswd,
                    newPasswd: newPasswd
                },

                success: function (res) {
                    var resJson = JSON.parse(res);
                    var result = resJson.data.changeResult;
                    me._warn(result);
                },
                error: function (e) {
                    alert("请求出错！");
                }
            });
        });
    };

    /**
     * 弹出提示框
     * @param words
     * @private
     */
    me._warn = function (words) {
        layer.open({title: '温馨提示', content: words});
    };
};