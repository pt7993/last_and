function memberOut() {
    var frm = document.frm;
    console.log(frm.user_pw.value);
    console.log(frm.info.value)
    if (frm.info.value.length == 0) {
        alert("탈퇴사유를 입력하세요");
        frm.info.focus();
        return false;
    }
    $.ajax({
        url: "/member/memberOut",
        type: "POST",
        data: {
            user_pw: $("#user_pw").val(),
            id: $("#id").val()
        },
        dataType: "json",
        error: function () {
            alert("일치하는 정보가 없습니다");
        },
        success: function (data) {
            console.log(data)
            if (confirm("탈퇴 하시겠습니까")) {
                if ($.trim(data) == "1") {
                    alert("비밀번호가 틀렸습니다");
                } else {
                    alert("회원 탈퇴에 성공하셨습니다")
                    window.location.href = "/";
                }
            }
        }
    });
}