/**
 * Created by hz15071510 on 2015/12/17.
 */
;
(function (window) {

    var SUFFIX = getSUFFIX();
    /**
     * Used by Sidebar, and can be overwrite.
     * @property SUFFIX      : hostname SUFFIX, eg. .test or .com
     * @property defaultData : used for SidebarInfo request failed
     * @property productId   : 产品ID,eg,ecs,rds.
     */
    var SIDEBAR_CONFIG = {
        SUFFIX: SUFFIX,
        defaultData: getDefaultData(),
        isfolded: false,
        forcedFolding: false,
        productId: null,
        appendNode: null,
        requestUrl: {
            sidebarInfo: "",
            setUserPreference: ""
        }
    };
    var SIDEBAR_FOLD_COOKIENAME = "sidebar-type";
    var SIDEBAR_FOLD_COOKIEDOMAIN = ".aliyun.com";

    var sidebarNode, productMap, serviceMap, sidebarType;

    if (!window.jQuery) {
        throw("jQuery is NEED for aliyun console Sidebar. Recommand 1.10.2");
        return;
    }

    var $ = window.jQuery;

    if (window['SIDEBAR_CONFIG'] != null) {
        $.extend(SIDEBAR_CONFIG, window['SIDEBAR_CONFIG']);
    }

    shim();
    getSidebarInfo();

    function getSUFFIX() {
        var domain = window.location.hostname;
        if (!domain.match(/^.+\.aliyun\.|^aliyun\./))return '.com';
        var output = domain.replace(/^.*\.aliyun|^aliyun/i, '');
        if (!output)output = '.com';
        return output;
    };


    function getSidebarInfo() {
        $.ajax({
                url: SIDEBAR_CONFIG.requestUrl.sidebarInfo,
                dataType: "json"
            })
            .done(function (result) {
                if (result != null) {
                    SIDEBAR_CONFIG.defaultData = result;
                    for (var i = 0; i < result.length; ++i) {
                        var e = SIDEBAR_CONFIG.defaultData[i];
                        if(SIDEBAR_CONFIG.menuUrlPrefex != "/") {
                            e.resources = SIDEBAR_CONFIG.menuUrlPrefex + e.resources;
                        }
                    }
                    getSidebarTemplate(SIDEBAR_CONFIG.defaultData);
                } else {
                    getSidebarTemplate(SIDEBAR_CONFIG.defaultData);
                }

            })
            .fail(function (result) {
                console.log("Get Sidebar info error.show default data");
                console.log(result);
                getSidebarTemplate(SIDEBAR_CONFIG.defaultData);
            });
    }


    function getNavHead() {
        var headHtml =
            '<li class="nav-header">' +
            '<div class="dropdown profile-element">' +
            '<span><img alt="image" class="img-circle" height="64" src="' + SIDEBAR_CONFIG.userInfo.imageUrl + '" /></span>' +
            '<a data-toggle="dropdown" class="dropdown-toggle" href="#">' +
            '<span class="clear">' +
            '<span class="block m-t-xs"><strong class="font-bold">' + SIDEBAR_CONFIG.userInfo.name + '</strong></span>' +
            '<span class="text-muted text-xs block">超级管理员<b class="caret"></b></span>' +
            '</span>' +
            '</a>' +
            '<ul class="dropdown-menu animated fadeInRight m-t-xs">' +
            '<li><a class="J_menuItem" href="form_avatar.html">修改头像</a>' +
            '</li>' +
            '<li><a class="J_menuItem" href="profile.html">个人资料</a>' +
            '</li>' +
            '<li><a class="J_menuItem" href="contacts.html">联系我们</a>' +
            '</li>' +
            '<li><a class="J_menuItem" href="mailbox.html">信箱</a>' +
            '</li>' +
            '<li class="divider"></li>' +
            '<li><a href="logout">安全退出</a>' +
            '</li>' +
            '</ul>' +
            '</div>' +
            '<div class="logo-element">H+' +
            '</div>' +
            '</li>';
        return headHtml;
    }

    function findChildMenuList(menuList, id) {
        var childMenuList = [];
        if (menuList != null) {
            for (var i = 0; i < menuList.length; ++i) {
                if (menuList[i].parentId == id) {
                    childMenuList.push(menuList[i]);
                }
            }
        }
        childMenuList.sort(function (a, b) {
            return a.orderValue - b.orderValue
        });

        return childMenuList;
    }

    function getNavBody() {
        var menuList = SIDEBAR_CONFIG.defaultData;
        var resultHTML = "";
        var rootMenuList = findChildMenuList(menuList, null);
        if (rootMenuList != null && rootMenuList.length > 0) {
            for (var rootIndex = 0; rootIndex < rootMenuList.length; rootIndex++) {
                var rootMenu = rootMenuList[rootIndex];
                var midleMenuList = findChildMenuList(menuList, rootMenu.id);
                if (midleMenuList != null && midleMenuList.length > 0) {
                    resultHTML += '<li>';
                    resultHTML += '<a href="#">';
                    resultHTML += '<i class="' + toFaIconClass(rootMenu.iconClass) + '"></i>';
                    resultHTML += '<span class="nav-label">' + rootMenu.cname + '</span>';
                    resultHTML += '<span class="fa arrow"></span>';
                    resultHTML += '</a>';

                    resultHTML += '<ul class="nav nav-second-level">';
                    for (var midleIndex = 0; midleIndex < midleMenuList.length; midleIndex++) {
                        var midleMenu = midleMenuList[midleIndex];
                        var leafMenuList = findChildMenuList(menuList, midleMenu.id);
                        if (leafMenuList != null && leafMenuList.length > 0) {
                            resultHTML += '<li>';
                            resultHTML += '<a href="#">' + midleMenu.cname + ' <span class="fa arrow"></span></a>';
                            resultHTML += '<ul class="nav nav-third-level">';
                            for (var leafIndex = 0; leafIndex < leafMenuList.length; ++leafIndex) {
                                var leafMenu = leafMenuList[leafIndex];
                                resultHTML += '<li>';
                                resultHTML += '<a class="J_menuItem" href="' + leafMenu.resources + '">' + leafMenu.cname + '</a>';
                                resultHTML += '</li>';
                            }
                            resultHTML += '</ul>';
                            resultHTML += '</li>';
                        } else {
                            resultHTML += '<li>';
                            resultHTML += '<a class="J_menuItem" href="' + midleMenu.resources + '">' + midleMenu.cname + '</a>';
                            resultHTML += '</li>';
                        }
                    }
                    resultHTML += '</ul>';

                } else {
                    resultHTML += '<li>';
                    resultHTML += '<a href="' + rootMenu.resources + '">';
                    resultHTML += '<i class="' + toFaIconClass(rootMenu.iconClass) + '"></i>';
                    resultHTML += '<span class="nav-label">' + rootMenu.cname + '</span>';
                    resultHTML += '</a>';
                }


                resultHTML += '</li>';
            }
        }
        return resultHTML;
    }

    function toFaIconClass(iconClass){
        if(iconClass != null && iconClass.length > 0){
            return "fa fa-" + iconClass;
        }
        return "";
    }

    function getSidebarTemplate() {
        getSidebarFoldType();

        var sidebarHtml =
            '<nav class="navbar-default navbar-static-side" role="navigation">' +
            '   <div class="nav-close"><i class="fa fa-times-circle"></i>' +
            '   </div>' +
            '   <div class="sidebar-collapse">' +
            '       <ul class="nav" id="side-menu">' +
            getNavHead() + getNavBody() +
            '       </ul>' +
            '   </div>' +
            '</nav>';

        sidebarNode = $(sidebarHtml);
        $(document).ready(function () {
            $('#' + SIDEBAR_CONFIG.appendNode).append(sidebarNode);
            bindEnvent();
            bindContabs();
        });
    }

    function bindContabs() {
        $(function () {
            function t(t) {
                var e = 0;
                return $(t).each(function () {
                    e += $(this).outerWidth(!0)
                }),
                    e
            }

            function e(e) {
                var a = t($(e).prevAll()),
                    i = t($(e).nextAll()),
                    n = t($('.content-tabs').children().not('.J_menuTabs')),
                    s = $('.content-tabs').outerWidth(!0) - n,
                    r = 0;
                if ($('.page-tabs-content').outerWidth() < s) r = 0;
                else if (i <= s - $(e).outerWidth(!0) - $(e).next().outerWidth(!0)) {
                    if (s - $(e).next().outerWidth(!0) > i) {
                        r = a;
                        for (var o = e; r - $(o).outerWidth() > $('.page-tabs-content').outerWidth() - s;) r -= $(o).prev().outerWidth(),
                            o = $(o).prev()
                    }
                } else a > s - $(e).outerWidth(!0) - $(e).prev().outerWidth(!0) && (r = a - $(e).prev().outerWidth(!0));
                $('.page-tabs-content').animate({
                    marginLeft: 0 - r + 'px'
                }, 'fast')
            }

            function a() {
                var e = Math.abs(parseInt($('.page-tabs-content').css('margin-left'))),
                    a = t($('.content-tabs').children().not('.J_menuTabs')),
                    i = $('.content-tabs').outerWidth(!0) - a,
                    n = 0;
                if ($('.page-tabs-content').width() < i) return !1;
                for (var s = $('.J_menuTab:first'), r = 0; r + $(s).outerWidth(!0) <= e;) r += $(s).outerWidth(!0),
                    s = $(s).next();
                if (r = 0, t($(s).prevAll()) > i) {
                    for (; r + $(s).outerWidth(!0) < i && s.length > 0;) r += $(s).outerWidth(!0),
                        s = $(s).prev();
                    n = t($(s).prevAll())
                }
                $('.page-tabs-content').animate({
                    marginLeft: 0 - n + 'px'
                }, 'fast')
            }

            function i() {
                var e = Math.abs(parseInt($('.page-tabs-content').css('margin-left'))),
                    a = t($('.content-tabs').children().not('.J_menuTabs')),
                    i = $('.content-tabs').outerWidth(!0) - a,
                    n = 0;
                if ($('.page-tabs-content').width() < i) return !1;
                for (var s = $('.J_menuTab:first'), r = 0; r + $(s).outerWidth(!0) <= e;) r += $(s).outerWidth(!0),
                    s = $(s).next();
                for (r = 0; r + $(s).outerWidth(!0) < i && s.length > 0;) r += $(s).outerWidth(!0),
                    s = $(s).next();
                n = t($(s).prevAll()),
                n > 0 && $('.page-tabs-content').animate({
                    marginLeft: 0 - n + 'px'
                }, 'fast')
            }

            function n() {
                var t = $(this).attr('href'),
                    a = $(this).data('index'),
                    i = $.trim($(this).text()),
                    n = !0;
                if (void 0 == t || 0 == $.trim(t).length) return !1;
                if ($('.J_menuTab').each(function () {
                        return $(this).data('id') == t ? ($(this).hasClass('active') || ($(this).addClass('active').siblings('.J_menuTab').removeClass('active'), e(this), $('.J_mainContent .J_iframe').each(function () {
                            return $(this).data('id') == t ? ($(this).show().siblings('.J_iframe').hide(), !1) : void 0
                        })), n = !1, !1) : void 0
                    }), n) {
                    var s = '<a href="javascript:;" class="active J_menuTab" data-id="' + t + '">' + i + ' <i class="fa fa-times-circle"></i></a>';
                    $('.J_menuTab').removeClass('active');
                    var r = '<iframe scrolling="no" class="J_iframe" name="iframe' + a + '" width="100%" height="100%" src="' + t + '" frameborder="0" data-id="' + t + '" seamless></iframe>';
                    $('.J_mainContent').find('iframe.J_iframe').hide().parents('.J_mainContent').append(r);
                    var o = layer.load();
                    $('.J_mainContent iframe:visible').load(function () {
                        layer.close(o)
                    }),
                        $('.J_menuTabs .page-tabs-content').append(s),
                        e($('.J_menuTab.active'))
                }
                return !1
            }

            function s() {
                var t = $(this).parents('.J_menuTab').data('id'),
                    a = $(this).parents('.J_menuTab').width();
                if ($(this).parents('.J_menuTab').hasClass('active')) {
                    if ($(this).parents('.J_menuTab').next('.J_menuTab').size()) {
                        var i = $(this).parents('.J_menuTab').next('.J_menuTab:eq(0)').data('id');
                        $(this).parents('.J_menuTab').next('.J_menuTab:eq(0)').addClass('active'),
                            $('.J_mainContent .J_iframe').each(function () {
                                return $(this).data('id') == i ? ($(this).show().siblings('.J_iframe').hide(), !1) : void 0
                            });
                        var n = parseInt($('.page-tabs-content').css('margin-left'));
                        0 > n && $('.page-tabs-content').animate({
                            marginLeft: n + a + 'px'
                        }, 'fast'),
                            $(this).parents('.J_menuTab').remove(),
                            $('.J_mainContent .J_iframe').each(function () {
                                return $(this).data('id') == t ? ($(this).remove(), !1) : void 0
                            })
                    }
                    if ($(this).parents('.J_menuTab').prev('.J_menuTab').size()) {
                        var i = $(this).parents('.J_menuTab').prev('.J_menuTab:last').data('id');
                        $(this).parents('.J_menuTab').prev('.J_menuTab:last').addClass('active'),
                            $('.J_mainContent .J_iframe').each(function () {
                                return $(this).data('id') == i ? ($(this).show().siblings('.J_iframe').hide(), !1) : void 0
                            }),
                            $(this).parents('.J_menuTab').remove(),
                            $('.J_mainContent .J_iframe').each(function () {
                                return $(this).data('id') == t ? ($(this).remove(), !1) : void 0
                            })
                    }
                } else $(this).parents('.J_menuTab').remove(),
                    $('.J_mainContent .J_iframe').each(function () {
                        return $(this).data('id') == t ? ($(this).remove(), !1) : void 0
                    }),
                    e($('.J_menuTab.active'));
                return !1
            }

            function r() {
                $('.page-tabs-content').children('[data-id]').not(':first').not('.active').each(function () {
                    $('.J_iframe[data-id="' + $(this).data('id') + '"]').remove(),
                        $(this).remove()
                }),
                    $('.page-tabs-content').css('margin-left', '0')
            }

            function o() {
                e($('.J_menuTab.active'))
            }

            function d() {
                if (!$(this).hasClass('active')) {
                    var t = $(this).data('id');
                    $('.J_mainContent .J_iframe').each(function () {
                        return $(this).data('id') == t ? ($(this).show().siblings('.J_iframe').hide(), !1) : void 0
                    }),
                        $(this).addClass('active').siblings('.J_menuTab').removeClass('active'),
                        e(this)
                }
            }

            function c() {
                var t = $('.J_iframe[data-id="' + $(this).data('id') + '"]'),
                    e = t.attr('src'),
                    a = layer.load();
                t.attr('src', e).load(function () {
                    layer.close(a)
                })
            }

            $('.J_menuItem').each(function (t) {
                $(this).attr('data-index') || $(this).attr('data-index', t)
            }),
                $('.J_menuItem').on('click', n),
                $('.J_menuTabs').on('click', '.J_menuTab i', s),
                $('.J_tabCloseOther').on('click', r),
                $('.J_tabShowActive').on('click', o),
                $('.J_menuTabs').on('click', '.J_menuTab', d),
                $('.J_menuTabs').on('dblclick', '.J_menuTab', c),
                $('.J_tabLeft').on('click', a),
                $('.J_tabRight').on('click', i),
                $('.J_tabCloseAll').on('click', function () {
                    $('.page-tabs-content').children('[data-id]').not(':first').each(function () {
                        $('.J_iframe[data-id="' + $(this).data('id') + '"]').remove(),
                            $(this).remove()
                    }),
                        $('.page-tabs-content').children('[data-id]:first').each(function () {
                            $('.J_iframe[data-id="' + $(this).data('id') + '"]').show(),
                                $(this).addClass('active')
                        }),
                        $('.page-tabs-content').css('margin-left', '0')
                })
        });
    }

    function NavToggle() {
        $('.navbar-minimalize').trigger('click')
    }

    function bindEnvent() {
        $('#side-menu').metisMenu();

        $('#side-menu>li').click(function () {
            $('body').hasClass('mini-navbar') && NavToggle()
        });

        $('#side-menu>li li a').click(function () {
            $(window).width() < 769 && NavToggle()
        });

        $('.nav-close').click(NavToggle);
    }

    function getSidebarFoldType() {
        if (!SIDEBAR_CONFIG.forcedFolding) {
            sidebarType = getCookie(SIDEBAR_FOLD_COOKIENAME);
            if (!isValidSidebarType(sidebarType)) {
                sidebarType = SIDEBAR_CONFIG.isfolded ? 'mini' : 'full';
            }
        } else {
            sidebarType = 'mini';
        }
        return sidebarType;
    }

    function getDefaultData() {
        return [{
            "id": "1",
            "text": "111111",
            "url": "2321321",
            "iconCls": null,
            "seq": 11,
            "pid": null,
            "pname": null,
            "attributes": {"url": "2321321"}
        }, {
            "id": "2",
            "text": "2222222",
            "url": "213123",
            "iconCls": null,
            "seq": 232,
            "pid": null,
            "pname": null,
            "attributes": {"url": "213123"}
        }, {
            "id": "3",
            "text": "3333333",
            "url": "333333",
            "iconCls": null,
            "seq": 23,
            "pid": null,
            "pname": null,
            "attributes": {"url": "333333"}
        }];
    }

    function isValidSidebarType(type) {
        return type == 'mini' || type == 'full';
    }

    function setCookie(name, value, domain) {
        if (!name) {
            return;
        }
        var formatDomain = "";
        if (domain) {
            formatDomain = ";domain=" + domain;
        }
        document.cookie = name + "=" + escape(value) + formatDomain + "; path=/";
    }

    function getCookie(name) {
        if (!name) {
            return;
        }
        var cookieList = document.cookie.split(";");
        var cookievalue;
        for (var i = 0; i < cookieList.length; i++) {
            var temp = cookieList[i].split("=");
            if (trim(temp[0]) == name) {
                cookievalue = unescape(temp[1]);
            }
        }
        return cookievalue;
    }

    function shim() {
        if (!Array.prototype.indexOf) {
            Array.prototype.indexOf = function (obj) {
                for (var i = 0; i < this.length; i++) {
                    if (this[i] == obj) {
                        return i;
                    }
                }
                return -1;
            }
        }
    }

    function trim(str) {
        return str.replace(/^\s+|\s+$/g, '');
    };


    /**
     * 根据某个key,将数组数据转换为map
     * @param source
     * @param key
     * @returns {{}}
     */
    function transArrayToMapByKey(source, key) {
        var result = {};
        var length = source.length, element;
        key = key ? key : "id";
        for (var i = 0; i < length; i++) {
            element = source[i];
            if (element[key]) {
                result[element[key]] = element;
            }
        }
        return result;
    }

})(window);