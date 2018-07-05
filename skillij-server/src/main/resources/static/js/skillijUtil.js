var SkillijUtil = SkillijUtil || {
};

(function ($) {
    SkillijUtil = $.extend(SkillijUtil, {
        /**
         * 检查token有效性，确定登录状态
         * @param onLoginAlready
         * @param onTokenInvalid
         */
        loginCheck: function (onLoginAlready, onTokenInvalid) {
            $.ajax({
                type: "GET",
                url: "/account/token",
                async: true,
                data: {
                    token: localStorage.getItem("currentUser_token")
                },
                success: function (res) {
                    var resJson = JSON.parse(res);
                    var token = resJson.data.token;
                    if (token != null && token !== "invalid") {
                        if (typeof onLoginAlready === "function") {
                            onLoginAlready();
                        }
                    } else {
                        if (typeof onTokenInvalid === "function") {
                            onTokenInvalid();
                        }
                    }
                },
                error: function (e) {
                    alert("请求出错！");
                }

            });
        },
    });
})(jQuery);