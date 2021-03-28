function CommentSave() {
    var main = {
        init: function () {
            var _this = this;
            _this.save();
        },
        save: function () {
            var data = {
                nickname: $('#nickname').val(),
                comments: $('#comments').val(),
                parentNum : $('#parentNum').val()
            };
            var id = $('#member_id').val();
            var hb_num = $('#hb_num').val();
            console.log("session login id=" + data.id)

            $.ajax({
                type: 'post',
                url: '/comments/save/' + id,
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