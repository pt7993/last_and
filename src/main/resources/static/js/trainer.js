function apply() {
    var main = {
        init: function () {
            var _this = this;
            _this.apply();
        },
        apply: function () {
            var data = {
                user_name: $('#user_name').val(),
                user_id: $('#user_id').val(),
                user_email: $('#user_email').val(),
                user_pn: $('#user_pn').val(),
                start_date: $('#start_date').val(),
                end_date: $('#end_date').val()
            }
            var member_id = $('#member_id').val();
            var trainer_id = $('#trainer_id').val();


            $.ajax({
                type: 'post',
                url: '/ptUser/apply/success/' + member_id + "/" + trainer_id,
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function () {
                alert('등록되었습니다');
                window.location.href = '/ptUser/view';
            }).fail(function (error) {
                alert(JSON.stringify(error));
            })
        }
    }
    main.init();
}