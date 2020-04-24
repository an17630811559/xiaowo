
$(function () {
    $('#LoginForm').bootstrapValidator({

        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },

        fields: {
            username: {
                message: '用户名验证失败',
                validators: {
                    notEmpty: {
                        message: '用户名不能为空'
                    }
                }
            },
            password: {
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    }
                }
            }
        }
    })
        .on('success.form.bv',function (e) {
            e.preventDefault();
            var $form = $(e.target);
            var bv = $form.data('bootstrapValidator');
            $.post("user/login",$form.serialize(),function (data) {
                if (data.data.code==200){
                    alert(data.data.msg);
                    window.location.href="toIndex";
                    return;
                }
                $('#LoginForm')[0].reset();
                alert("账户或密码错误.请重新输入")
            },"json")
        });

});