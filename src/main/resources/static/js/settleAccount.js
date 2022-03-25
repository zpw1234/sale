$(document).ready(function () {
    var loading = new Loading();
    var layer = new Layer();

    var isCheckAll = false;
    $('#selectAll').click(function () {
        if (isCheckAll) {
            $(":checkbox").each(function() {
                this.checked = false;
            });
            isCheckAll = false;
        } else {
            $(":checkbox").each(function() {
                this.checked = true;
            });
            isCheckAll = true;
        }
    });

    $('#newTable').click(function (e) {
        var e = arguments[0] || window.event;
        target = e.srcElement ? e.srcElement : e.target;
        // var quantity;
        if (target.nodeName == "A" && target.parentElement.className == "moreNum") {
            var num = target.parentElement.parentElement.children[1].children[0].value;
            num++;
            target.parentElement.parentElement.children[1].children[0].value = num;
        } else if(target.nodeName == "A" && target.parentElement.className == "lessNum") {
            var num = target.parentElement.parentElement.children[1].children[0].value;
            if (num > 1) {
                num--;
                target.parentElement.parentElement.children[1].children[0].value = num;
            }
        }else if (target.nodeName =="A" && target.parentElement.className =="delete") {
            var ele = target;
            var id = ele && ele.dataset.id;
            var userid = ele && ele.dataset.userid;
            layer.reset({
                content:'确认从购物车删除该内容吗？',
                onconfirm:function () {
                    $.ajax({
                        url:'/deleteCartDetail',
                        type:'post',
                        data:{'userId':userid,'contentId':id},
                        success:function (data) {
                            layer.hide();
                            var code = data.code;
                            var msg = data.msg;
                            if (code == 200) {
                                loading.result("删除成功");
                                location.reload();
                            }
                        }
                    });
                }.bind(this)
            }).show();
        }
    });

    $('#buy').click(function () {
        var contents = []
        var userId = $('#user').val();
        $('input[name="cartVo"]').each(function () {
            if (this.checked) {
                var ele = this;
                var id = ele && ele.dataset.id;
                var num = ele.parentElement.parentElement.children[2].children[1].children[0].value;
                var detail = {'contentId':id, 'num':num};
                contents.push(detail);
            }
        });

        var accountList = {'userId': userId, 'items': JSON.stringify(contents)};
        console.log(accountList)
        layer.reset({
            content: "确认购买吗？",
            onconfirm: function () {
                layer.hide();
                loading.show();
                $.ajax({
                    url: '/buy',
                    type: 'post',
                    data: JSON.stringify(accountList),
                    contentType: "application/json",
                    success: function (data) {
                        var code = data.code;
                        var msg = data.msg;
                        if (code == 200) {
                            loading.result(msg);
                            setTimeout('location.href="/account"', 3000);
                        } else {
                            loading.result(msg);
                        }
                    }
                })
            }.bind(this)
        }).show()

    });

});