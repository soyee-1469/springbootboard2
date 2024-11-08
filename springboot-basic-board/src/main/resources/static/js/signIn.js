$(document).ready(() => {
    $('#signin').click(() => {
        let userId = $('#user_id').val();
        let password = $('#password').val();

        let formData = {
            username: userId,
            password: password
        };

        $.ajax({
            type: 'POST',
            url: '/login', // 서버의 로그인 엔드포인트
            data: $.param(formData), // URL 인코딩된 형식으로 변환
            contentType: 'application/x-www-form-urlencoded; charset=utf-8',
            headers: {
                'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content') // CSRF 토큰이 필요할 경우
            },
            success: (response) => {
                console.log('res :: ', response);
                if (response.loggedIn) {
                    window.location.href = response.url;
                } else {
                    alert(response.message || "로그인에 성공했습니다.");
                }
            },
            error: (xhr) => {
                console.error('오류 발생:', xhr);
                alert(xhr.responseJSON?.message || "로그인에 실패했습니다.");
                if (xhr.responseJSON?.url) {
                    window.location.href = xhr.responseJSON.url;
                }
            }
        });
    });
});
