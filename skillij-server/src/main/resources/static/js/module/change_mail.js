/**
 * 修改密码
 */
change_mail = function () {
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
        $("#btn-confirm").on("click", function () {
            var mail = $("#inputEmail").val();
            var password = $("#inputPassword").val();
            //声明邮箱正则
            var myreg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/ ;
            if(!myreg.test(mail))//对输入的值进行判断
            {
                me._warn('请输入有效的E_mail！');
                return false;
            }

            //密码格式校验
            var reg = /^.{6,20}$/;
            var passwdOK = reg.test(password);
            if(!passwdOK) {
                me._warn( "您输入的密码字符长度有误，合法长度为6-20个字符" );
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
                url: "/account/change-email",
                async: true,
                data: {
                    username: username,
                    password: password,
                    email: mail
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