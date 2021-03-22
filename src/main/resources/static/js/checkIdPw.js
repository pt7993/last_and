function IdCheck() {
    var frm = document.checkForm;
    console.log(frm.user_name.value)
    console.log(frm.user_email.value)
    if (frm.user_name.value.length == 0) {
        alert("이름을 입력하세요.");
        frm.user_name.focus();
        return false;
    }else if (frm.user_email.value.length == 0) {
        alert("이메일을 입력하세요");
        frm.user_name.focus();
        return false;
    }

    $.ajax({
        url: "/IdCheck",
        type : "POST",
        data: {
            user_name: $("#user_name").val(),
            user_email: $("#user_email").val()
        },
        dataType: "json",
        error: function () {
        alert("일치하는 정보가 없습니다");
        },
        success : function(data){
            if($.trim(data) == "YES") {
                alert("일치하는 정보가 없습니다");
            } else{
                alert("아이디는" + data.user_id + "입니다");
            }
        }
    })
}

function PwCheck() {
    let frm = document.checkPwForm;
    let user_id = frm.user_id.value;
    let user_name = frm.user_name.value;
    let user_email = frm.user_email.value;
    $.ajax({
        type: "GET",
        url: "/findPw",
        data: {
            "user_name": user_name,
            "user_email": user_email
        },
        success: function (res) {
            if (res['check']) {
                swal("발송 완료!", "입력하신 이메일로 임시비밀번호가 발송되었습니다.", "success").then((OK) => {
                    if(OK) {
                        $.ajax({
                            type: "POST",
                            url: "/findPw/sendEmail",
                            data: {
                                "user_name": user_name,
                                "user_email": user_email
                            }
                        })
                        window.location = "/login";
                    }
                });
                $('#checkMsg').html('<p style="color:#00008b"></p>');
            } else {
                $('#checkMsg').html('<p style="color:#ff0000">일치하는 정보가 없습니다.</p>');
            }
        }
    })


}