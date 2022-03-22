$(document).ready(function () {

$(".addBtn").on("click", function(){
    console.log("clickyyyyy");

    let mno = $(".mno").val();
    let info = $("#text").val();
    let check = $(".check");

    if(info == "") {
        check.html("내용을 입력해주세요");
        check.css("color", "#dc3545");
        return null;
    }

    $.ajax({
        url: "/bday2/addTmi/" + mno,
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


});