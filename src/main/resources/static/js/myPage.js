function pnNum() {
    let user_pn = $('#user_pn').val();
    let pn = [user_pn.substring(0,3), user_pn.substring(3,7), user_pn.substring(7,11) ];

    for(let i = 0; i < 3; i++){
        $("input[name=pn]").eq(i).val(pn[i]);
    }
}
$(function (){
    pnNum();
});

$(document).ready(function () {
    let memberJoinForm = document.getElementById('memberJoinForm');
    let user_id = document.getElementById('user_id');
    let user_repw = document.getElementById('user_repw');
    let joinbtn = document.getElementById('joinbtn');

    joinbtn.addEventListener("submit",function (e) {if(!joinChk()) e.preventDefault();});
    user_id.addEventListener("keyup",idChk);
    user_repw.addEventListener("keyup",pwdCheck);
});

// 회원 정보 수정 유효성 검사
function joinChk() {
    var frm = document.memberJoinForm;
    //조건1. 6~20 영문 대소문자 , 최소 1개의 숫자 혹은 특수 문자를 포함해야 함
    var pwRule = /^(?=.*[a-zA-Z])((?=.*\d)|(?=.*\W)).{6,16}$/;

    if (frm.user_pw.value.length == 0) {
        alert("비밀번호를 입력하세요.");
        frm.user_pw.focus();
        return false;
    } else if (!pwRule.test(frm.user_pw.value)) {
        alert("패스워드는 숫자, 문자포함 6~16자리로 지정해주세요");
        return false;
    } else if (frm.user_pw.value != frm.user_repw.value || frm.user_repw.value.length == 0 ) {
        alert("비밀번호를 다시 한번 확인해주세요");
        frm.user_repw.focus();
        return false;
    }
    // 이메일
    else if (frm.user_email.value.length == 0) {
        alert("이메일을 입력하세요");
        frm.user_email.focus();
        return false;
    }
    else {
        alert("회원 정보 수정이 완료 되었습니다.");
        return true;
    }
    return false;
}

// 비밀번호 일치 여부
function pwdCheck() {
    var frm = document.memberJoinForm;
    var msg = document.getElementById("chkmsg");

    if (frm.user_pw.value != frm.user_repw.value) {
        msg.innerHTML = "비밀번호가 일치하지 않습니다.";
        msg.style.color = "red";
    } else {
        msg.innerHTML = "비밀번호가 일치합니다.";
        msg.style.color = "blue";
    }
}

// 아이디 중복확인
function idChk() {
    $.ajax({
        url: "/member/idChk",
        type: "POST",
        data: {
            user_id: $("#user_id").val(),
        },
        success: function (data) {
            if ($.trim(data) == "YES") {
                $("#chkIdmsg").text("사용중인 아이디입니다.");
                $("#chkIdmsg").css("color", "red");
            } else {
                $("#chkIdmsg").text("사용가능한 아이디입니다");
                $("#chkIdmsg").css("color", "blue");
            }
        },
    });
}

