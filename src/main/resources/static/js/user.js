var currentPage;
var pageSize;
//切换全部 , 个人留言按钮
var msgStatus;
if (isEmpty(currentPage)){
    currentPage=1;
}
if (isEmpty(msgStatus)){
    msgStatus=1;
}

function getTianqi(i) {
    $.get("forecast/getData?city="+i,function (data) {
        if (data.code="200") {
            var btn = $(".tianqinbtn");
            console.log(btn)
            for (var j=0;j<btn.length;j++){
                $(btn[j]).removeClass("btn-primary");
            }
            data=data.data;
            if (data[0].city =="铜山区"){
                $(btn[1]).addClass("btn-primary");
            }else{
                $(btn[0]).addClass("btn-primary");
            }
            $(".city h1").text(data[0].city)
            $(".lasttime").text(data[0].reporttime);
            $(".miaoshu").text(data[0].dayweather);
            $(".sheshidu").text(data[0].nighttemp+"-"+data[0].daytemp);
            $(".weilai").empty();
            for (var j in data){
                var div="<div class='col-xs-3 weilaitianqi'>" +
                    "<div>"+data[j].date+"</div>" +
                    "<div>"+data[j].dayweather+"</div>" +
                    "<div>"+data[j].nighttemp+"-"+data[j].daytemp+"℃</div>" +
                    "<div>"+data[j].daywind+"风"+data[j].daypower+"级</div>" +
                    "</div>";
                $(".weilai").append(div)
            }
        }
    },"json");
}

//留言切换按钮
function msgChange(i) {
    var click;
    var text;
    $(".msg-change").css({"background":"#393e46","color":"#ffffff"})
    if (i==1){
        //只看我
        click="msgChange(0)";
        text="所有人";
    }else {
        //所有人
        click="msgChange(1)";
        text="只看我";
    }
    $(".msg-change").attr("onClick",click)
    $(".msg-change").text(text)
    getData(currentPage,pageSize,i)
}


function msgSumbit(){
    if (isEmpty($(".text-area").val().trim())){
        alert("提交内容不能为空哦")
        return false;
    }
        $.get("http://quan.suning.com/getSysTime.do",function (data) {
            $(".time").empty();
            var time=data.sysTime2;
            $(".time").val(time);
            var msg = $("#msgform").serialize();
            $.post("msg/uploadMsg?"+msg,function (data) {
                alert(data.msg)
                $(".time").val("");
                if(data.code=200){
                    $(".text-area").val("");
                    window.location.reload();
                    getData(currentPage,pageSize,msgStatus);
                }
            },"json");
        },"json");
}


function searchFormSumbit() {
    if ($(".searchFormInput").val().trim()==""){
        alert("请输入用户名或者昵称")
        return false;
    }
    $.post("user/search",{key:$(".searchFormInput").val()},function (data) {
        console.log(data)
        if (data.code==500){
            alert(data.msg);
        }
        var result="";
        for (var i = 0; i <data.data.length ; i++) {
            result += data.data[i];
        }
        alert(result);
    },"json")
}

function getdate(){
    $.get("http://quan.suning.com/getSysTime.do",function (data) {
        $("#time").empty();
        var time=data.sysTime2.substr(0,10);
        $("#time").text(time);
    },"json");
}

var msgTimeOut;//用于存储定时器的变量
function mouseDown(i){
    //当鼠标指针移动到元素上方，并按下鼠标按键时，会发生 mousedown 事件
    msgTimeOut= setTimeout(function() {
        console.log($(i))
        $("#msgModal").modal();
        $("#msgEditForm").find("input[name=time]").val( $(i).find(".time").text());
        $("#msgEditForm").find("input[name=id]").val( $(i).find(".id").text());
        $("#msgEditForm").find("textarea[name=msg]").val( $(i).find(".msgtext").text());
    }, 1000);//鼠标按下1秒后发生事件
}

function mouseUp() {
    clearTimeout(msgTimeOut);
}


//获取留言
function getData(cPage,pSize,i) {
    msgStatus=i;
    $.get("msg/selectAll",{currentPage:cPage, pageSize:pSize,status:i},function (data) {
        if (data.code==200) {
            $(".msgTable").empty();
            data=data.data;
            currentPage = data.msg.pageNum;
            pageSize = data.msg.pageSize;
            for (var i = 0; i < data.msg.list.length; i++) {
                var div = "<div class='msgth' onmousedown='mouseDown(this)' onmouseup='mouseUp()' " +
                    " onmouseout='mouseUp()' ontouchstart='mouseDown(this)'  " +
                    "ontouchmove='mouseUp()' ontouchend='mouseUp()'>  " +
                    "<p class='author'>" +
                    "<span class='auth'>" + data.msg.list[i].user.nickname + ":</span>" +
                    "<span class='time'>" + data.msg.list[i].time + " </span> " +
                    "<span style='float: right'>#<span class='id'> " + data.msg.list[i].id + "</span></span>" +
                    " </p>" +
                    "<hr class='center'>" +
                    "<p class='msgtext'>" + data.msg.list[i].msg + "</p>" +
                    "</div>";
                $(".msgTable").append(div);
            }
            $(".msgTable").append("<p class='page-tips'>当前第" + data.msg.pageNum + "页,共" + data.msg.pages + "页</p>");
            page(data.msg.pages);
        }else{
            alert(data.msg);
        }
        },"json");

}


function page(maxpage) {
    var page2="";
    if (currentPage==1){
        page2+="<span >首页</span>";
        page2+="<span >上一页</span>";
    }else{
        page2+="<button onclick='firstPage()' >首页</button>";
        page2+="<button onclick='prevPage()'>上一页</button>";
    }
    if (currentPage==maxpage){
        page2+="<span>下一页</span>";
        page2+="<span>尾页</span>";
    }else{
        page2+="<button onclick='nextPage()'>下一页</button>";
        page2+="<button onclick='lastPage("+maxpage+")'>尾页</button>";
    }

    $(".msgTable").append("<div class='row'><div class='page'>"+page2+"</div></div>");
}
function firstPage() {
    getData(1,pageSize,msgStatus);
}
function prevPage() {
    getData(currentPage-1,pageSize,msgStatus);
}
function nextPage() {
    getData(currentPage+1,pageSize,msgStatus);
}
function lastPage(maxpage) {
    getData(maxpage,pageSize,msgStatus);
}

function Reload() {
    /*window.location.reload();*/
    getData(currentPage,pageSize,msgStatus);
    $(".reload").blur()
    $(".reload").text("已刷新");
    $('.reload').attr('onclick',"");
    setTimeout(function () {
        $(".reload").text("刷新");
        $('.reload').attr('onclick',"Reload()");
    },2000)
}


/**
 * 判断是否是空
 * @param value
 */
function isEmpty(value){
    if(value == null || value == "" || value == "undefined" || value == undefined || value == "null"){
        return true;
    }
    else{
        value = value.replace(/\s/g,"");
        if(value == ""){
            return true;
        }
        return false;
    }
}