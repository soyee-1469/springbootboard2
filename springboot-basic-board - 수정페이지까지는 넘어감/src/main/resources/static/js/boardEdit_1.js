$(document).ready(() => {
    checkSession(); // 세션 확인
    loadBoardEdit(); // 게시글 데이터 불러오기

    // 수정 완료 버튼 클릭 이벤트
    $('#updateBtn').click(() => {
        let userId = $('#user_id').val();
        let title = $('#title').val();
        let content = $('#content').val();
        let filePath = $('#fileInput').val();

        let formData = {
            userId : userId,
            userName : userName,
            title : title,
            content : content,
            filePath : filePath
        }




        $.ajax({
            type: 'PUT',
            url: `/api/board/${boardId}`,
            contentType: 'application/json',
            data: JSON.stringify({
                id: boardId, // id 추가
                title: title,
                content: content,
                file_path: filePath
            }),
            success: function (response) {
                alert('게시글이 수정되었습니다.');
                location.href = `/`; // 수정 후 게시판 리스트 페이지로 이동
            },
            error: function (error) {
                console.error('수정 오류:', error);
                alert('게시글 수정에 실패했습니다.');
            }
        });
    });
});

// 세션 체크 함수
let checkSession = () => {
    let hUserId = $('#hiddenUserId').val();

    if (hUserId == null || hUserId === '') {
        window.location.href = "/member/login"; // 로그인 페이지로 이동
    }
}

// 게시글 데이터 로드 함수
let loadBoardDetail = () => {

    let hId = $('#hiddenId').val();
    let hUserId = $('#hiddenUserId').val();
    $.ajax({
        type: 'GET',
        url: '/api/board/' + hId,
        success: (response) => {
            $('#title').text(response.title);
            $('#content').text(response.content);
            $('#userId').text(response.userId);
            $('#created').text(response.created);

            if (hUserId != response.userId) {
                $('#editBtn').prop('disabled', true);
                $('#deleteBtn').prop('disabled', true);
            }

            // 파일 목록이 있는 경우, 파일 다운로드 링크 추가
            if (response.filePath && response.filePath.length > 0) {
                let filePath = response.filePath;
                $('#hiddenFilePath').val(filePath)
                let fileName = filePath.substring(filePath.lastIndexOf('\\') + 1); // 파일명 추출
                let fileElement = `
                            <li>
                                <a href="/api/board/file/download/${fileName}">${fileName}</a> <!-- 다운로드 링크 -->
                            </li>`;
                $('#fileList').append(fileElement);
            } else {
                $('#fileList').append('<li>첨부된 파일이 없습니다.</li>');
            }

        },
        error: function (error) {
            console.error('오류 발생:', error);
            alert('상세 데이터를 불러오는데 오류가 발생했습니다.');
        }
    });
}
