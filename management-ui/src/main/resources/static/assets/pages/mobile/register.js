$(document).ready(function () {
    var vcode = null;
    var userId = GetQueryString('userId');
    var userName = GetQueryString('userName');
    var infoFrom = GetQueryString('infoFrom');

    if (!(userId != null && userName != null && infoFrom != null)) {
        showWarning('页面参数有误!');
    }

    $('#fatherName').val(userName);

    function showWarning(message) {
        var $tooltips = $('.js_tooltips');

        if ($tooltips.css('display') != 'none') return;

        // toptips的fixed, 如果有`animation`, `position: fixed`不生效
        $('.page.cell').removeClass('slideIn');
        $tooltips.html(message);
        $tooltips.css('display', 'block');
        setTimeout(function () {
            $tooltips.css('display', 'none');
        }, 2000);
    }

    function showToast(message) {
        var $toast = $('#toast');
        $toast.find('.weui-toast__content').html(message);
        if ($toast.css('display') != 'none') return;
        $toast.fadeIn(100);
        setTimeout(function () {
            $toast.fadeOut(100);
        }, 2000);
    }

    function showLoadingToast(message) {
        var $loadingToast = $('#loadingToast');
        $loadingToast.find('.weui-toast__content').html(message);
        if ($loadingToast.css('display') != 'none') return;

        $loadingToast.fadeIn(100);
    }

    function hideLoadingToast() {
        var $loadingToast = $('#loadingToast');
        $loadingToast.fadeOut(100);
    }

    function checkPwd() {
        var check = $('#password').val() == $('#password2').val();
        if (!check) {
            showWarning('密码不一致!');
        }
        return check;
    }

    function checkForm() {
        var shortCode = $.trim($('#shortCode').val());
        var userName = $.trim($('#userName').val());
        var password = $.trim($('#password').val());
        var password2 = $.trim($('#password2').val());
        var mobileNo = $.trim($('#mobileNo').val());
        var check = shortCode != '' && userName != '' && password != '' && password2 != '' && mobileNo != '' && userId != '' && infoFrom != '';
        if (!check) {
            showWarning('表单有未完成项!');
        }
        return check;
    }

    function checkShortCode() {
        var shortCode = $.trim($('#shortCode').val());
        var check = shortCode == vcode;

        if (!check) {
            showWarning('手机验证码有误!');
        }
        return check;
    }

    $('#btnSendCode').click(function () {
        var param = {
            'phoneNo': $('#mobileNo').val()
        };

        $.ajax({
            url: '/mobile/api/smss/send',
            type: 'GET',
            cache: false,
            data: param,
            success: function (r) {
                if (r.hasOwnProperty('errcode')) {
                    if ('0' == r.errcode) {
                        setTimeout(function () {
                            vcode = r.data;
                            $('#shortCode').val(vcode);
                        }, 1000);
                    } else {
                        showWarning(r.errmsg);
                    }
                } else {
                    showWarning('请求失败!');
                }
            },
            beforeSend: function (XMLHttpRequest) {
                // MaskUtil.mask();
                vcode = null;
                showLoadingToast('正在发送中');
            },
            error: function (request) {
                showWarning('请求失败!');
            },
            complete: function (XMLHttpRequest, textStatus) {
                hideLoadingToast();
            }

        });
    });

    $('#submit').click(function () {
        if (!checkForm()) {
            return;
        }

        if (!checkPwd()) {
            return;
        }

        if (!checkShortCode()) {
            return;
        }

        var param = {
            'shortCode': $('#shortCode').val(),
            'userName': $('#userName').val(),
            'password': $('#password').val(),
            'mobileNo': $('#mobileNo').val(),
            'infoFrom': infoFrom,
            'fatherId': userId
        };

        $.ajax({
            url: '/mobile/api/register',
            type: 'POST',
            cache: false,
            contentType: 'application/json;charset=UTF-8',
            data: JSON.stringify(param),
            success: function (r) {
                if (r.hasOwnProperty('errcode')) {
                    if ('0' == r.errcode) {
                        setTimeout(function () {
                            showToast('注册成功!');
                        }, 1000);
                    } else {
                        showWarning(r.errmsg);
                    }
                } else {
                    showWarning('请求失败!');
                }
            },
            beforeSend: function (XMLHttpRequest) {
                // MaskUtil.mask();
                showLoadingToast('数据加载中');
            },
            error: function (request) {
                showWarning('请求失败!');
            },
            complete: function (XMLHttpRequest, textStatus) {
                hideLoadingToast();
            }

        });
    });


});
