var car = false;

$(document).ready(function () {
    let memberJoinForm = document.getElementById('memberJoinForm');
    let user_id = document.getElementById('user_id');
    let user_repw = document.getElementById('user_repw');
    let zip = document.getElementById('zip');
    let joinbtn = document.getElementById('joinbtn');

    memberJoinForm.addEventListener("submit",function (e) {if(!joinChk()) e.preventDefault();});
    user_id.addEventListener("keyup",idChk);
    user_repw.addEventListener("keyup",pwdCheck);
    zip.addEventListener("click",execPostCode);
    joinbtn.addEventListener("click", email_concat);

    emailChg();
});

// 회원가입 유효성 검사
function joinChk() {
    var frm = document.memberJoinForm;
    //조건1. 6~20 영문 대소문자 , 최소 1개의 숫자 혹은 특수 문자를 포함해야 함
    var pwRule = /^(?=.*[a-zA-Z])((?=.*\d)|(?=.*\W)).{6,16}$/;

    if (frm.user_id.value.length == 0) {
        alert("아이디를 입력하세요.");
        frm.user_id.focus();
        return false;
    } else if ($("#chkIdmsg").text() == "사용중인 아이디입니다.") {
        alert("아이디를 다시 입력해 주세요.");
        frm.user_id.focus();
        return false;
    } else if (frm.user_pw.value.length == 0) {
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
    } else if (frm.user_name.value.length == 0) {
        alert("이름을 입력하세요");
        frm.user_name.focus();
        return false;
    }  else if (frm.user_pn.value.length == 0) {
        alert("휴대폰 번호를 입력하세요");
        frm.user_pn.focus();
        return false;
    }
    else if (frm.inputCertifiedNumber.value.length == 0) {
        alert("핸드폰 인증번호를 해주세요");
        frm.inputCertifiedNumber.focus();
        return false;
    } else if (car == false) {
        alert("인증번호가 틀렸습니다");
        return false;
    }
    // 이메일
    else if (frm.user_email.value.length == 1) {
        alert("이메일을 입력하세요");
        frm.user_email.focus();
        return false;
    }
    //주민번호
    else if (frm.user_rrn.value.length == 0) {
        alert("주민번호를 입력하세요");
        frm.user_rrn.focus();
        return false;
    }
    //젠더
    else if (frm.user_gender.value.length == 0) {
        alert("주민번호 뒷자리를 입력하세요");
        frm.user_gender.focus();
        return false;
    }
    // 주소
    else if (frm.address_normal.value.length == 0) {
        alert("기본주소를 입력하세요");
        frm.address_normal.focus();
        return false;
    }
    // 상세 주소
    else if (frm.address_detail.value.length == 0) {
        alert("상세주소를 입력하세요");
        frm.address_detail.focus();
        return false;
    } else {
        alert("회원가입이 완료 되었습니다.");
        return true;
    }
    return false;
}


// 주소 API
function execPostCode() {
    alert('누름');
    new daum.Postcode({
        oncomplete: function (data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 도로명 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var fullRoadAddr = data.roadAddress; // 도로명 주소 변수
            var extraRoadAddr = ""; // 도로명 조합형 주소 변수

            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if (data.bname !== "" && /[동|로|가]$/g.test(data.bname)) {
                extraRoadAddr += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if (data.buildingName !== "" && data.apartment === "Y") {
                extraRoadAddr +=
                    extraRoadAddr !== "" ? ", " + data.buildingName : data.buildingName;
            }
            // 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if (extraRoadAddr !== "") {
                extraRoadAddr = " (" + extraRoadAddr + ")";
            }
            // 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
            if (fullRoadAddr !== "") {
                fullRoadAddr += extraRoadAddr;
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            console.log(data.zonecode);
            console.log(fullRoadAddr);

            $("[name=user_addr]").val(data.zonecode);
            $("[name=address_normal]").val(fullRoadAddr);

            /* document.getElementById('signUpUserPostNo').value = data.zonecode; //5자리 새우편번호 사용
                        document.getElementById('signUpUserCompanyAddress').value = fullRoadAddr;
                        document.getElementById('signUpUserCompanyAddressDetail').value = data.jibunAddress; */
        },
    }).open();
}

// 인풋 히든 값에 합친 값 넣기
const str_concat = () => {
    // 네임 값
    let name1 = "pn";
    let name2 = "user_pn";

    let arr = document.getElementsByName(name1);
    //let arr = $(`[name="${name1}"]`).attr("value");
    let str = "";

    for (let i = 0; i < 3; i++) {
        str += arr[i].value;
    }
    // 인풋 히든 의 값
    $(`[name="${name2}"]`).attr("value", str);
};

//이메일 합치기
const email_concat = () => {
    // 네임 값
    let name1 = "user_email1";
    let name2 = "user_email";

    let arr = document.getElementsByName(name1);
    let str = "";

    for (let i = 0; i < 2; i++) {
        str += arr[i].value;
        if (i == 0) {
            str += "@";
        }
    }
    // 인풋 히든 의 값
    $(`[name="${name2}"]`).attr("value", str);
};
