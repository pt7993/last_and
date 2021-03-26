function save() {
    var main = {
        init: function () {
            var _this = this;
            _this.save();
        },
        save: function () {
            var data = {
                title: $('#title').val(),
                author: $('#author').val(),
                content: $('#content').val()
            };
            console.log("session login id="+data.id)
            var id = $('#id').val();

            $.ajax({
                type: 'post',
                url: '/board/trainerBoard/save/'+id,
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function () {
                alert('등록되었습니다');
                window.location.href = '/board';
            }).fail(function (error) {
                alert(JSON.stringify(error));
            })
        }
    }
    main.init();
}

function update() {
    var main2 = {
        init: function () {
            var _this = this;
            _this.update();
        },
        update: function () {
            var data = {
                title: $('#title').val(),
                content: $('#content').val()
            };
            var id = $('#id').val();
            $.ajax({
                type: 'POST',
                url: '/board/trainerBoard/update',
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function () {
                alert('글이 수정되었습니다.');
                window.location.href = '/';
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
        }
    }
    main2.init()
}


function Bdelete() {
    var main3 = {
        init: function () {
            var _this = this;
            _this.delete();
        },
        delete: function () {
            var id = $('#id').val();

            $.ajax({
                type: 'post',
                url: '/board/trainerBoard/delete' ,
                dataType: 'json',
                contentType: 'application/json; charset=utf-8'
            }).done(function () {
                alert('글이 삭제되었습니다.')
                window.location.href = "/";
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
        }
    }
    main3.init();
}