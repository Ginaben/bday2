$(document).ready(function () {

//tmi 입력
$(".addBtn").on("click", function(){
    let mno = $(".mno").val();
    let info = $("#text").val();
    let check = $(".check");

    if(info == "") {
        check.html("내용을 입력해주세요");
        check.css("color", "#dc3545");
        return null;
    }

    $.ajax({
        url: "/addTmi/" + mno,
        method: "post",
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        data: {
            text: info,
            id: mno
        }
    })
        .done(function (fragment) {
//            $("#list-table").replaceWith(fragment);
            $("#text").val("");
            check.html("");
            self.location.reload();

        });
}); //addInfo end

//tmi 삭제
$(".deleteInfo").on("click", function(){
    let tno = $(this).parent().children(".tno").text();
    let id = $(this).parent().children(".id").text();

    $.ajax({
        url: "/deleteTmi/" + tno,
        method: "delete",
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        data: {
            tno: tno,
            id: id
        }
    })
        .done(function (fragment) {
            $("#list-table").replaceWith(fragment);
//            self.location.reload();

        });
}); //deleteInfo end

//전부 삭제
$(".deleteAll").on("click", function(){
    let id = $("#id").text();

    $.ajax({
        url: "/delete/" + id,
        method: "delete",
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        data: {
            id: id
        }
    })
        .done(function (data) {
        alert("삭제되었습니다");
        location="/";
        });
}); //deleteAll end


});