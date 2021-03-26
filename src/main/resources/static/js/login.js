$(document).ready(function(){

    let userInputId = getCookie("userInputId");//저장된 쿠기값 가져오기
    $("input[name='user_id']").val(userInputId);

    if($("input[name='user_id']").val() != ""){ // 그 전에 ID를 저장해서 처음 페이지 로딩
        // 아이디 저장하기 체크되어있을 시,
        $("#save_id").attr("checked", true); // ID 저장하기를 체크 상태로 두기.
    }

    $("#save_id").change(function(){ // 체크박스에 변화가 발생시
        if($("#save_id").is(":checked")){ // ID 저장하기 체크했을 때,
            let userInputId = $("input[name='user_id']").val();
            setCookie("userInputId", userInputId, 7); // 7일 동안 쿠키 보관
        }else{ // ID 저장하기 체크 해제 시,
            deleteCookie("userInputId");
        }
    });

    // ID 저장하기를 체크한 상태에서 ID를 입력하는 경우, 이럴 때도 쿠키 저장.
    $("input[name='user_id']").keyup(function(){ // ID 입력 칸에 ID를 입력할 때,
        if($("#save_id").is(":checked")){ // ID 저장하기를 체크한 상태라면,
            let userInputId = $("input[name='user_id']").val();
            setCookie("userInputId", userInputId, 7); // 7일 동안 쿠키 보관
        }
    });

});

function setCookie(cookieName, value, exdays){
    let exdate = new Date();
    exdate.setDate(exdate.getDate() + exdays);
    let cookieValue = escape(value) + ((exdays==null) ? "" : "; expires=" + exdate.toGMTString());
    document.cookie = cookieName + "=" + cookieValue;
}

function deleteCookie(cookieName){
    let expireDate = new Date();
    expireDate.setDate(expireDate.getDate() - 1);
    document.cookie = cookieName + "= " + "; expires=" + expireDate.toGMTString();
}

function getCookie(cookieName) {
    cookieName = cookieName + '=';
    let cookieData = document.cookie;
    let start = cookieData.indexOf(cookieName);
    let cookieValue = '';
    if(start != -1){
        start += cookieName.length;
        let end = cookieData.indexOf(';', start);
        if(end == -1)end = cookieData.length;
        cookieValue = cookieData.substring(start, end);
    }
    return unescape(cookieValue);
}

function checkLoginStatus() {
    var auth2 = gapi.auth2.getAuthInstance();
    if (gauth.isSignedIn.get()) {
        var token = gauth.currentUser.get().getAuthResponse().id_token; // 토큰값 받음.
        var xhr = new XMLHttpRequest();
        xhr.open('POST', '/login/google'); // controller 주소로 연결
        xhr.setRequestHeader('Content-Type', 'application/json'); // 헤더 이름, 헤더 본문 값
        xhr.onload = function () {
            console.log('Signed in as: ' + xhr.responseText);
        };
        xhr.send('token=' + token);
        auth2.signOut().then(function () {
            console.log("logout");
        });
        auth2.disconnect();
        setTimeout(function () {
            location.href = '/';
        }, 1500);
    } else {
        console.log('dont login');
    }
}

function googleLogin(googleUser) {
    gauth.signIn().then(function () {
        console.log('gauth.signIn()');
        checkLoginStatus();
    });
}

function onSignIn(googleUser) {
    console.log('sign');
    var auth2 = gapi.auth2.getAuthInstance();
    if (auth2.isSignedIn.get()) {
        alert("auth2 googleUser");
        var id_token = googleUser.getAuthResponse().id_token; // 구글 id토큰값

        var xhr = new XMLHttpRequest();
        xhr.open('POST', '/login/google'); // controller 주소로 연결
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded'); // 헤더 이름, 헤더 본문 값
        xhr.onload = function () {
            console.log('Signed in as: ' + xhr.responseText);
        };
        xhr.send('idtoken=' + id_token);

    }
}

function init() {
    console.log('init');
    gapi.load('auth2', function () {        //google API auth2관련된 부분을 실행
        console.log('auth2');

        window.gauth = gapi.auth2.init({          // 초기화 하는 부분이라함.
            client_id: '831600705653-q3poakj6j0ehm7ihhe09l4k77n1tnbme.apps.googleusercontent.com'
        })
        gauth.then(function () {          // gapi.auth2.init. then(A,B) A = 성공, B = 실패 시 실행됨.
            console.log('googleAuth success');
            checkLoginStatus();
        }, function () {
            console.log('googleAuth fail');
        });

    });
}

////// 카카오톡 //////
Kakao.init('07a8782c4835a3627204ba9cdfc84f4c');

//로그인 시
function kakaoLogin() {
    try {
        return new Promise((resolve => {
            if (!Kakao) {
                reject('Kakao 인스턴스 존재 X');
            }
            Kakao.Auth.login({

                success: (auth) => {
                    console.log('정상적으로 로그인 됐습니다.', auth);

                    Kakao.API.request({
                        url: '/v2/user/me',
                        success: function (res) {
                            var userId = res.id;
                            var userEmail = res.kakao_account.email; // 이메일값 못받아오게 바뀜.
                            var userNickName = res.properties.nickname;
                            var userRrn = res.kakao_account.birthday;
                            var userGender = res.kakao_account.gender;
                            console.log(userId);
                            console.log(userEmail);
                            console.log(userNickName);

                            $.ajax({
                                data: {
                                    userId: userId, // data 옵션
                                    userEmail: userEmail,
                                    userNickName: userNickName,
                                    userRrn: userRrn,
                                    userGender: userGender
                                },	// 끝에 컴마(,)를 주의해야됨
                                type: "post",
                                url: "/login/kakao", // url 필수
                                success: function (data) { // success option
                                    console.log(data.length);
                                }
                            })
                            setTimeout(function () {
                                location.href = '/';
                            }, 500);
                        },
                        fail: function (error) {
                            console.error(error)
                        }
                    })
                },
                fail: (err) => {
                    console.error(err)
                }
            });
        }))
    } catch (err) {
        console.error(err);
    }
}