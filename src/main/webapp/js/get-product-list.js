// var example1 = new Vue({
//     el: '#example-1',
//     data: {
//         items: [
//             { message: 'Foo' },
//             { message: 'Bar' }
//         ]
//     }
// })
// var json;
// var request = new XMLHttpRequest();
// request.open("GET", "/viewCategory", true);
// request.send();
// //该属性每次变化时会触发
// request.onreadystatechange = function(){
//     //若响应完成且请求成功
//     if(request.readyState === 4 && request.status === 200){
//         json = JSON.parse(request.responseText);
//     }
// }
// var product = JSON.parse('['+json.join(',')+']');
new Vue({
    el:'#table',
    data:{
        products:[],
    },
    methods:{
        created: function () {
            //为了在内部函数能使用外部函数的this对象，要给它赋值了一个名叫self的变量。
            var self = this;
                $.ajax({
                    url: '/viewCategory',
                    type: 'get',
                    data: {},
                    dataType: 'String'
                }).then(function (res) {
                    console.log(res);
                    //把从json获取的数据赋值给数组
                    self.products = JSON.parse(res);
                }).fail(function () {
                    console.log('失败');
            })
        },
    }
})