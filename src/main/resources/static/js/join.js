// 회원가입 유효성 검사
function joinChk() {
    var frm = document.memberJoinForm;
    //조건1. 6~20 영문 대소문자 , 최소 1개의 숫자 혹은 특수 문자를 포함해야 함
    var pwRule = /^(?=.*[a-zA-Z])((?=.*\d)|(?=.*\W)).{6,16}$/;

    if (frm.user_id.value.length == 0) {
        alert("아이디를 입력하세요.");
        frm.user_id.focus();
        return false;
    } else if (frm.user_password.value.length == 0) {
        alert("비밀번호를 입력하세요.");
        frm.user_password.focus();
        return false;
    } else if (!pwRule.test(frm.user_password.value)) {
        alert("패스워드는 숫자, 문자포함 6~16자리로 지정해주세요");
        return false;
    } else if (frm.user_name.value.length == 0) {
        alert("이름을 입력하세요");
        frm.user_name.focus();
        return false;
    } else if (frm.user_pn.value.length == 0) {
        alert("폰번호를 입력하세요");
        frm.user_pn.focus();
        return false;
    } else if (frm.user_email.value.length == 0) {
        alert("이메일을 입력하세요");
        frm.user_email.focus();
        return false;
    } else if (frm.user_password.value != frm.user_repassword.value) {
        alert("비밀번호를 다시 한번 확인해주세요");
        frm.user_repassword.focus();
        return false;
    } else if ($('#chkIdmsg').text() == "사용중인 아이디입니다.") {
        alert("아이디를 다시 입력해 주세요.");
        frm.user_id.focus();
        return false;
    }
}

// 비밀번호 일치 여부
function pwdCheck() {
    var frm = document.memberJoinForm;
    var msg = document.getElementById("chkmsg");

    if (frm.user_password.value != frm.user_repassword.value) {
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
        url: '/api/idChk',
        type: 'POST',
        data: {
            'user_id': $('#user_id').val()
        },
        success: function (data) {
            if ($.trim(data) == "YES") {
                $('#chkIdmsg').text("사용중인 아이디입니다.");
                $('#chkIdmsg').css("color", "red");
            } else {
                $('#chkIdmsg').text("사용가능한 아이디입니다");
                $('#chkIdmsg').css("color", "blue");
            }
        }
    })
}

// 주소 API
function execPostCode() {
    new daum.Postcode({
        oncomplete: function (data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 도로명 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var fullRoadAddr = data.roadAddress; // 도로명 주소 변수
            var extraRoadAddr = ''; // 도로명 조합형 주소 변수

            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
                extraRoadAddr += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if (data.buildingName !== '' && data.apartment === 'Y') {
                extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
            }
            // 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if (extraRoadAddr !== '') {
                extraRoadAddr = ' (' + extraRoadAddr + ')';
            }
            // 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
            if (fullRoadAddr !== '') {
                fullRoadAddr += extraRoadAddr;
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            console.log(data.zonecode);
            console.log(fullRoadAddr);


            $("[name=user_addr]").val(data.zonecode);
            $("[name=user_addr2]").val(fullRoadAddr);

            /* document.getElementById('signUpUserPostNo').value = data.zonecode; //5자리 새우편번호 사용
            document.getElementById('signUpUserCompanyAddress').value = fullRoadAddr;
            document.getElementById('signUpUserCompanyAddressDetail').value = data.jibunAddress; */
        }
    }).open();
}

