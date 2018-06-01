/**
 * Created by 123 on 2016/4/14.
 */
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

/**
 * 复制JSON
 * @param json
 * @returns {null}
 */
function clone(json) {
    return json == null ? null : $.parseJSON(JSON.stringify(json));
}

/**
 * 输出固定位数数字
 * @param num
 * @param length
 * @returns {string}
 */
function fixNum(num, length) {
    return ('' + num).length < length ? ((new Array(length + 1)).join('0') + num).slice(-length) : '' + num;
}

/**
 * 表单转JSON
 * @param form
 * @returns {{}}
 */
function formToJson(form) {
    var json = {};
    var array = $(form).serializeArray()
    for (var i = 0; i < array.length; i++) {
        json[array[i].name] = array[i].value == "" ? null : array[i].value;
    }
    return json;
}

/**
 * 从路径获取文件名
 * @param o
 * @returns {string|*}
 */
function getFileName(o) {
    var pos = o.lastIndexOf("\\");
    return o.substring(pos + 1);
}

/**
 * 获取字符串长度，英文1个，中文2个
 * @param str
 * @returns {*}
 */
function getStringLength(str) {
    var t = str.replace(/[^\x00-\xff]/g, '');                   //替换中文
    return (str.length - t.length) * 2 + t.length;              //判断长度
}

var getStringLength2 = function (str) {
    ///<summary>获得字符串实际长度，中文2，英文1</summary>
    ///<param name="str">要获得长度的字符串</param>
    var realLength = 0, len = str.length, charCode = -1;
    for (var i = 0; i < len; i++) {
        charCode = str.charCodeAt(i);
        if (charCode >= 0 && charCode <= 128) realLength += 1;
        else realLength += 2;
    }
    return realLength;
};

//js截取字符串，中英文都能用
//如果给定的字符串大于指定长度，截取指定长度返回，否者返回源字符串。
//字符串，长度

/**
 * js截取字符串，中英文都能用
 * @param str：需要截取的字符串
 * @param len: 需要截取的长度
 */
function cutstr(str, len) {
    var str_length = 0;
    var str_len = 0;
    var str_cut = new String();
    str_len = str.length;
    for (var i = 0; i < str_len; i++) {
        a = str.charAt(i);
        str_length++;
        if (escape(a).length > 4) {
            //中文字符的长度经编码之后大于4
            str_length++;
        }
        str_cut = str_cut.concat(a);
        if (str_length >= len) {
            str_cut = str_cut.concat("...");
            return str_cut;
        }
    }
    //如果给定字符串小于指定长度，则返回源字符串；
    if (str_length < len) {
        return str;
    }
}

/**
 * 是否为数组
 * @param value
 * @returns {boolean}
 */
function isArray(value) {
    if (value && typeof value === "object" && Object.prototype.toString.call(value) === "[object Array]") {
        return true;
    } else {
        return false;
    }
}

/**
 * 下拉框的所选值是否在源数据列表中（下拉框值可自行输入）
 * @param target
 * @param t
 * @param v
 * @returns {boolean}
 */
function isComboBoxValueInList(target, t, v) {
    var rows = target.combobox("getData");
    var text = target.combobox("getText");
    var value = target.combobox("getValue");

    if ("" == text && "" == value) {
        return true;
    }
    for (var i = 0; i < rows.length; i++) {
        if (rows[i][v] == value && rows[i][t] == text) {
            return true
        }
    }
    return false;
}

/**
 * 指定列是否有重复
 * @param rows
 * @param column
 * @returns {boolean}
 */
function isColumnDuplicate(rows, column) {
    for (var i = 0; i < rows.length; i++) {
        for (var j = i + 1; j < rows.length; j++) {
            if (rows[i][column] == rows[j][column]) {
                return true;
            }
        }
    }
    return false;
}

/**
 * 指定列是否有空值
 * @param rows
 * @param column
 * @returns {boolean}
 */
function isColumnEmpty(rows, column) {
    for (var i = 0; i < rows.length; i++) {
        if (null == rows[i][column] || "" == $.trim(rows[i][column])) {
            return true;
        }
    }
    return false;
}

/**
 * 指定列是否有相同行
 * @param rows
 * @param col1
 * @param col2
 * @returns {boolean}
 */
function isColumnSame(rows, col1, col2) {
    for (var i = 0; i < rows.length; i++) {
        if (null != rows[i][col1] && null != rows[i][col2] && rows[i][col1] == rows[i][col2]) {
            return true;
        }
    }
    return false;
}

/**
 * 是否为UUID
 * @param value
 * @returns {boolean}
 */
function isUUID(value) {
    var z = /^[a-zA-Z0-9]{8}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{12}$/;
    return z.test(value);
}

function formatDbTimestamp(time) {
    return time.substr(0, 4) + "-" + time.substr(4, 2) + "-" + time.substr(6, 2) + " " + time.substr(8, 2) + ":" + time.substr(10, 2) + ":" + time.substr(12, 2);
}

/**
 * 是否以指定字符串结尾
 * @param s
 * @returns {boolean}
 */
String.prototype.endWith = function (s) {
    if (s == null || s == "" || this.length == 0 || s.length > this.length)
        return false;
    if (this.substring(this.length - s.length) == s)
        return true;
    else
        return false;
    return true;
}

/**
 * 是否以指定字符串开始
 * @param s
 * @returns {boolean}
 */
String.prototype.startWith = function (s) {
    if (s == null || s == "" || this.length == 0 || s.length > this.length)
        return false;
    if (this.substr(0, s.length) == s)
        return true;
    else
        return false;
    return true;
}

/**
 * 获取字符串宽度
 * @param fontSize
 * @returns {number}
 */
String.prototype.getWidth = function (fontSize) {
    var span = document.getElementById("__getwidth");
    if (span == null) {
        span = document.createElement("span");
        span.id = "__getwidth";
        document.body.appendChild(span);
        span.style.visibility = "hidden";
        span.style.whiteSpace = "nowrap";
    }
    span.innerText = this;
    span.style.fontSize = fontSize + "px";

    return span.offsetWidth;
}

/**
 * 清空数组
 */
Array.prototype.clear = function () {
    this.length = 0;
}

/**
 * 在指定位置插入
 * @param index
 * @param obj
 */
Array.prototype.insertAt = function (index, obj) {
    this.splice(index, 0, obj);
}

/**
 * 从指定位置删除
 * @param index
 */
Array.prototype.removeAt = function (index) {
    this.splice(index, 1);
}

/**
 * 删除指定元素
 * @param obj
 */
Array.prototype.remove = function (obj) {
    var index = this.indexOf(obj);
    if (index >= 0) {
        this.removeAt(index);
    }
}

/**
 * 格式化日期
 * @param format
 * @returns {*}
 */
Date.prototype.format = function (format) {
    var date = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "h+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3),
        "S+": this.getMilliseconds()
    };
    if (/(y+)/i.test(format)) {
        format = (format + "").replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
    }
    for (var k in date) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? date[k] : ("00" + date[k]).substr(("" + date[k]).length));
        }
    }
    return format;
}
// console.log(newDate.format('yyyy-MM-dd h:m:s'));

/**
 * DataGrid 默认分页
 * @type {number}
 */
var DEFAULT_PAGE_SIZE = 15;
var DEFAULT_PAGE_LIST = [15, 25, 35];

var DEFAULT_AJAX_TIMEOUT = 20000;