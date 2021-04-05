function CommentSave() {
    var main = {
        init: function () {
            var _this = this;
            _this.save();
        },
        save: function () {
            var data = {
                comments: $('#comments').val(),
                parentNum : $('#parentNum').val(),
                user_id : $('#user_id').val()
            };
            var parentNum = $('#parentNum').val();
            console.log("session login id=" + data.id)
            console.log("session login id=" + data.parentNum)

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
                url: '/comments/delete/'+id ,
                dataType: 'json',
                contentType: 'application/json; charset=utf-8'
            }).done(function () {
                alert('글이 삭제되었습니다.')
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
                re_user_id: $('#re_user_id').val(),
                re_parentNum: $('#re_parentNum').val(),
                re_parentCoNum: $('#re_parentCoNum').val(),
                re_comments: $('#re_comments').val()
            };
            console.log("유저아이디"+data.re_user_id);
            console.log("댓글부모"+data.re_parentCoNum)
            console.log("보드번호"+data.re_parentNum)
            console.log("리댓"+data.re_comments)
            var re_parentCoNum = $('#re_parentCoNum').val();
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