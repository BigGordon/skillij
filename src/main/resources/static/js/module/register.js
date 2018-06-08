/**
 * 用户注册
 */
var register = function () {
    var me = this;

    /**
     * login页面初始化
     */
    me.init = function () {
        //登录按钮
        me._initRegisterBtn();
    }

    /**
     * 初始化注册按钮
     * @private
     */
    me._initRegisterBtn = function () {
        $("#btn-register").on("click", function () {
            var mail = $("#inputEmail").val();
            var userName = $("#inputUsersName").val();
            var passwd = $("#inputPassword").val();
            var ensurePasswd = $("#ensurePassword").val();

            if (mail == null || mail == "") {
                alert("请输入邮箱地址");
                document.getElementById("inputEmail").focus();
                return;
            }
            else {
                var reg = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
                var emailOK= reg.test(mail);
                if(!emailOK) {
                    alert("邮箱格式不正确，请重新输入！");
                    document.getElementById("inputEmail").focus();
                    return;
                }
            }


            if (userName == null || userName == "") {
                alert("请输入用户名");
                document.getElementById("inputUsersName").focus();
                return;
            }

            else {
                var userNameOK = me._userInputCheck(userName);
                switch(userNameOK) {
                    case 0: break;
                    case 1: {
                        alert( "您选择的用户名‘"+userName+"‘格式不正确，用户名只能包含字母、数字、下划线" );
                        document.getElementById("inputUsersName").focus();
                        return false;
                    }
                    case 2: {
                        alert( "您选择的用户名‘"+userName+"‘格式不正确，用户名只能以字母开头" );
                        document.getElementById("inputUsersName").focus();
                        return false;
                    }
                    case 3: {
                        alert( "您选择的用户名‘"+userName+"‘字符长度有误，合法长度为6-20个字符" );
                        document.getElementById("inputUsersName").focus();
                        return false;
                    }
                    default: break;
                }
            }


            if (passwd == null || passwd == "") {
                alert("请输入密码");
                document.getElementById("inputPassword").focus();
                return;
            }
            else {
                var reg = /^.{6,20}$/;
                var passwdOK = reg.test(passwd);
                if(!passwdOK) {
                    alert( "您输入的密码字符长度有误，合法长度为6-20个字符" );
                    document.getElementById("inputPassword").focus();
                    return false;
                }
            }


            if (ensurePasswd == null || ensurePasswd == "") {
                alert("请再次输入密码");
                document.getElementById("ensurePassword").focus();
                return;
            }

            if (ensurePasswd !== passwd){
                alert("密码不一致");
                document.getElementById("inputPassword").focus();
                return;
            }

            $.ajax({
                type: "POST",
                url: "/account/register",
                async: false,
                data: {
                    mail: mail,
                    userName: userName,
                    passwd: passwd
                },
                success: function (res) {
                    var resJson = JSON.parse(res);
                    alert(resJson.data.registerResult);
                },
                error: function (e) {
                    alert("请求出错！");
                }
                
            });
        });
    }

    /**
     * 判断用户输入字符有效性
     * @param userName
     * @returns {number}
     * @private
     */
    me._userInputCheck = function(userIput) {
        if(! /^[a-zA-Z0-9_]+$/.test(userIput)  ) {
            return 1;
        }
        if(! /^[a-z]/i.test(userIput)  ) {
            return 2;
        }
        if(! /^.{6,20}$/.test(userIput) ){
            return 3;
        }
        return 0;
    }
}