/**
 * Created by barrie on 17/1/15.
 */
//新添加tab
function addTab(cname, curl, ciconCls) {
    if ($('#centerTabs').tabs('exists', cname)) {
        $('#centerTabs').tabs('select', cname);
    } else {
        if (curl && curl.length > 0) {
            $('#centerTabs').tabs('add', {
                title: cname,
                closable: true,
                iconCls: ciconCls,
                bodyCls: 'tab-body',
                fit: true,
                content: '<iframe src="' + curl + '" style="width: 100%;height: 100%;" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="no" allowtransparency="yes"></iframe>',
            });
        }
    }
}

$(document).ready(function () {
    $('#logout').click(function () {
        $.messager.confirm('注销', '您确定要退出?', function (r) {
            if (r) {
                window.location.href = _logoutUri + '?token=' + _token + '&redirect=' + _localLogoutUri;
            } else {
                return;
            }
        });
    });
});