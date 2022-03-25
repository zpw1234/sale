

util.get('plusNum').onclick = function (e){
    e = window.event || e;
    o = e.srcElement || e.target;
    var num = util.get('allNum').textContent;
    if(num > 1){
        num --;
        util.get('allNum').innerHTML = num;
    }else{
        alert("您没有购买任何商品");
    }
};

util.get('addNum').onclick = function(e){
    e = window.event || e;
    o = e.srcElement || e.target;
    var num = util.get('allNum').textContent;
    num ++;
    util.get('allNum').innerHTML = num;
};

var loading = new Loading();
var layer = new Layer();


util.get('add').onclick = function (e) {
    var ele = e.target;
    var contentId = ele && ele.dataset.id;
    var userId = ele && ele.dataset.user;
    var num = util.get('allNum').innerHTML;
    var CartJSON = {'userId':userId,'contentId':contentId,'num':num};
    console.log(ele.dataset)
    layer.reset({
        content:'确认加入购物车吗？',
        onconfirm: function(){
            layer.hide();
            loading.show();
            $.ajax({
                url:'/addCart',
                type:'post',
                data:JSON.stringify(CartJSON),
                contentType: "application/json",
                success:function (data){
                    var code = data.code;
                    var msg = data.msg;
                    loading.result(msg);
                }
            });
        }.bind(this)
    }).show();

};