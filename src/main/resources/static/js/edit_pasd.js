$(function () {
    $.get("user/getUser",function (data) {
        if (data.code==200){
            $("#userNikename").text(data.data.nickname);
        }
    },"json");

    $("#editPasdForm").bootstrapValidator({

        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },

        fields: {
            password: {
                message: '原密码验证失败',
                validators: {
                    notEmpty: {
                        message: '原密码不能为空'
                    },
                    threshold :  0 , //设置4字符以上开始请求服务器
                    //有待验证，备注以备下次使用。
                    //bootstrap的remote验证器需要的返回结果一定是json格式的数据 :
                    //{"valid":false} //表示不合法，验证不通过
                    //{"valid":true} //表示合法，验证通过
                    remote: { //发起Ajax请求。
                        url: 'user/checkPasd',//验证地址
                        data:{password:$('input[name="password"]').val() },
                        message: '原密码错误',//提示消息
                        headers:{"Api-Style":"NONE"},
                        delay :  1000,//设置2秒发起密码验证
                        type: 'POST' //请求方式
                    }
                }
            },
            newpassword: {
                validators: {
                    notEmpty: {
                        message: '新密码不能为空'
                    }
                }
            },
            renewpassword: {
                validators: {
                    notEmpty: {
                        message: '新密码不能为空'
                    },
                    identical:{
                        field: 'newpassword',
                        message:'两次输入密码不一致'
                    }
                }
            }
        }
    })
        .on('success.form.bv',function (e) {
            e.preventDefault();
            $.post("user/editPasd",{password:$('input[name="newpassword"]').val()},function (data) {
                alert(data.msg);
                if (data.code==500){
                    $('#LoginForm')[0].reset();
                    return;
                }
                window.location.href="logout";
            },"json")
        });

});
function editPasd() {
    $("#modal").modal();
}

