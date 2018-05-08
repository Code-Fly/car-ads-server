$(document).ready(function () {
    var error = GetQueryString("error");
    if (error == "1") {
        $.messager.alert("信息提示", "无效用户或用户名密码错误！", "warning");
    }
    else if (error == "2") {
        $.messager.alert("信息提示", "Session 已过期，请重新登陆！", "warning");
    } else {
        $.messager.alert("信息提示", "Session 无效，请重新登陆！", "warning");
    }


    $("#userName").textbox({
        required: true,
        validateOnCreate: false
    });

    $("#password").passwordbox({
        required: true,
        validateOnCreate: false
    });

    /**
     * 获取URL Query String
     * @param name
     */
    function GetQueryString(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null)
            return unescape(r[2]);
        return null;
    }
});