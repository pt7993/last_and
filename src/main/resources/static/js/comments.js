function CommentSave() {
    var main = {
        init: function () {
            var _this = this;
            _this.save();
        },
        save: function () {
            var data = {
                comments: $('#comments').val(),
                parentNum: $('#parentNum').val(),
                user_id: $('#user_id').val()
            };
            var parentNum = $('#parentNum').val();
            console.log("유저아이디" + data.user_id)
            console.log("보드번호" + data.parentNum)
            console.log("댓글" + data.comments)

            $.ajax({
                type: 'post',
                url: '/comments/save/' + parentNum,
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function () {
                alert('댓글이 등록되었습니다');
                // window.location.href = '/board/trainerBoard/detail'+'hb_num'=hb_num;
                location.reload();
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
        }
    }
    main.init();
}

function CDelete() {
    var main3 = {
        init: function () {
            var _this = this;
            _this.delete();
        },
        delete: function () {
            var id = $('#commentId').val();
            console.log("id=" + id);
            $.ajax({
                type: 'post',
                url: '/comments/delete/' + id,
                dataType: 'json',
                contentType: 'application/json; charset=utf-8'
            }).done(function () {
                alert('댓글이 삭제되었습니다.')
                location.reload();
                // window.location.href = "/";
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
        }
    }
    main3.init();
}

function ReCommentSave() {
    let form = $(this).parent();
    let re_user_id = form.children("#re_user_id").val();
    let re_parentCoNum = form.children("#re_parentCoNum").val();
    let re_comments = form.children(".comment_write_area").children().val();

    // $('re_comments').click(function () {
    //     if ($('#re_user_id') == null) {
    //         alert("로그인을 해주세요");
    //         location.href = "/member/login";
    //     }
    // });
    var main = {

        init: function () {
            var _this = this;
            _this.save();
        },
        save: function () {
            var data = {
                re_user_id: re_user_id,
                re_parentCoNum: re_parentCoNum,
                re_comments: re_comments,
            };
            console.log("유저아이디" + data.re_user_id);
            console.log("댓글부모" + data.re_parentCoNum);
            console.log("리댓" + data.re_comments);

            $.ajax({
                type: 'post',
                url: '/recomments/save/' + re_parentCoNum,
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function () {
                alert('답글이 등록되었습니다');
                // window.location.href = '/board/trainerBoard/detail'+'hb_num'=hb_num;
                location.reload();
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
        }
    };
    main.init();
}

function recommend() {

    var id = $('#recommendId').val().length;
    var boardId = $('#boards_id').val();
    var loginId = $('#recommendId').val();
    console.log(loginId);
    console.log(boardId);

    if (id == 0) {
        confirm("로그인을 이용해주세요");
        location.href = "/member/login";
    } else {
        $.ajax({
            type: 'post',
            url: '/board/recommend/' + boardId,
            data: {
                id: $('#recommendId').val(),
                boards_id: $('#boards_id').val(),
            },
            dataType: "json",
            success: function (data) {
                console.log(data);
                if (data === true) {
                    alert("게시글을 추천 하셨습니다");
                } else {
                    alert("한개의 글에 한번만 클릭이 가능합니다");
                }
            },
            error: function () {
                alert("한개의 글에 한번만 클릭이 가능합니다");
            }
        })
    }
}

function RCDelete() {
    var Rcdel = {
        init: function () {
            var _this = this;
            _this.delete();
        },
        delete: function () {
            var id = $('#recommentId').val();
            console.log("id=" + id);
            $.ajax({
                type: 'post',
                url: '/recomments/delete/' + id,
                dataType: 'json',
                contentType: 'application/json; charset=utf-8'
            }).done(function () {
                alert('댓글이 삭제되었습니다.')
                location.reload();
                // window.location.href = "/";
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
        }
    }
    Rcdel.init();
}


function like() {
    var likeId = $('#likeId').val().length;
    var boardLike = $('#boardLike').val();
    var com = $('#comId').val();
    var id = $('#likeId').val();

    if (likeId == 0) {
        confirm("로그인을 이용해주세요");
        location.href = "/member/login";
    }
    console.log("로그인 아이디" + id);
    console.log("게시글번호" + boardLike);
    console.log("댓글부모" + com);

    $.ajax({
        type: 'post',
        url: '/board/like/' + boardLike,
        data: {
            likeId: $('#likeId').val(),
            boardLike: $('#boardLike').val(),
            comId: $('#comId').val()
        },

        dataType: "json",
        success: function (data) {
            console.log(data);
            if (data === 1) {
                alert("댓글을 추천 하셨습니다");
                location.reload();
            } else if (data === 2) {
                alert("이미 추천하신 글입니다");
                location.reload();
            } else if (data === 3) {
                alert("3번임")
                location.reload();
            } else if (data === 0) {
                alert("댓글을 추천 하셨습니다")
                location.reload();
            }
        },
        error: function () {
            alert("잘못된 접근방식입니다");
        }
    })
}

function unlike() {
    var likeId = $('#likeId').val().length;
    var boardLike = $('#boardLike').val();
    var com = $('#comId').val();
    var id = $('#likeId').val();

    if (likeId == 0) {
        confirm("로그인을 이용해주세요");
        location.href = "/member/login";
    }
    console.log("로그인 아이디" + id);
    console.log("게시글번호" + boardLike);
    console.log("댓글부모" + com);
    $.ajax({
        type: 'post',
        url: '/board/dislike/' + boardLike,
        data: {
            likeId: $('#likeId').val(),
            boardLike: $('#boardLike').val(),
            comId: $('#comId').val()
        },
        dataType: "json",
        success: function (data) {
            console.log(data);
            if (data === 1) {
                alert("댓글을 추천 하셨습니다");
                location.reload();
            } else if (data === 2) {
                alert("이미 추천하신 글입니다");
            } else if (data === 3) {
                alert("댓글을 추천 하셨습니다");
                location.reload();
            } else if (data === 0) {
                alert("댓글을 추천 하셨습니다")
                location.reload();
            }
        },
        error: function () {
            alert("한개의 글에 한번만 클릭이 가능합니다");
        }
    })
}

$(function () {
    $('#comment_save').on("click", CommentSave);
    $('#delete').on("click", CDelete);
    $('#RCDelete').on("click", RCDelete);

    $('.recomment_save_btn').on("click", ReCommentSave);
    $('.recmt_button').on("click", buttonToggle);

});


function buttonToggle() {
    $(this).next().toggle();

}