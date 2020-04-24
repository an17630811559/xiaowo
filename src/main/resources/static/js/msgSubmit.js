$(function () {
    $('#msgEditForm').bootstrapValidator({

        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },

        fields: {
            id: {
                message: 'ID验证失败',
                validators: {
                    notEmpty: {
                        message: '请刷新页面重试'
                    }
                }
            },
            time: {
                validators: {
                    notEmpty: {
                        message: '日期不能为空'
                    }
                }
            },
            msg: {
                validators: {
                    notEmpty: {
                        message: '留言不能为空'
                    }
                }
            },
        }
    })
        .on('success.form.bv',function (e) {
            e.preventDefault();
            var $form = $(e.target);
            var bv = $form.data('bootstrapValidator');
            $.post("msg/editMsg",$form.serialize()+"&sta=1",function (data) {
                alert(data.msg);
                if (data.code==500){
                    return;
                }
                $("#msgModal").modal('hide');
                getData(currentPage,pageSize,msgStatus);
            },"json")
        });

});

function msgDelete(i) {
    if (!confirm("你确定要删除这条留言么?")){
        return;
    }

    $.post("msg/editMsg",{id:$(i).parent().parent().find("input[name=id]").val(),sta:0},function (data) {
        alert(data.msg);
        if (data.code==500){
            return;
        }
        $("#msgModal").modal('hide');
        getData(currentPage,pageSize,msgStatus);
    },"json")
}