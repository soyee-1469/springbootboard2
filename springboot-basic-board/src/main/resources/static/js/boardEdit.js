let selectedFile = null; // 파일은 1개만 선택 가능

$(document).ready(() => {
    checkSession();
    saved();
    fileChanged(); // 오타 수정
    loadBoardDetail();

});
let loadBoardDetail = () => {
    // 게시글 상세 정보를 불러오는 AJAX 요청
    $.ajax({
        type: 'GET',
        url: '/api/board/' + $('#hiddenId').val(), // ID를 포함한 URL
        success: function (response) {
            // 응답을 처리하는 로직
            $('#title').val(response.title);
            $('#content').val(response.content);
            $('#fileInput').val(response.filePath);
            // 기타 필요한 필드 업데이트
        },
        error: function (error) {
            console.error('게시글 상세 정보를 불러오는 중 오류 발생:', error);
        }
    });
};
let saved = () => {
    $('#updateBtn').on('click', (event) => {
        event.preventDefault();

        let formData = new FormData();

        // 필드 추가
        formData.append('title', $('#title').val());
        formData.append('content', $('#content').val());

        // 선택된 파일이 있는 경우 추가
        if (selectedFile) {
            formData.append('file', selectedFile);
        }

        // FormData 내용 출력
        console.log('Form data before sending:', Array.from(formData.entries()));

        $.ajax({
            type: 'PUT',
            url: '/api/board/' + $('#hiddenId').val(), // 수정할 게시글의 ID를 포함
            data: formData,
            processData: false,
            contentType: false,
            success: function (response) {
                // 성공 시 실행될 콜백 함수
                alert('게시글이 성공적으로 수정되었습니다!');
                window.location.href = '/'; // 성공 후 다른 페이지로 이동
            },
            error: function (error) {
                // 실패 시 실행될 콜백 함수
                console.error('오류 발생:', error);
                alert('게시글 수정 중 오류가 발생하였습니다.');
            }
        });
    });
};


// 파일 선택 시 이벤트
let fileChanged = () => {
    $('#file').on('change', function (e) {
        const file = e.target.files[0]; // 첫 번째 파일만 선택

        selectedFile = file; // 선택된 파일을 변수에 저장
        updateFileList(); // 파일 목록 업데이트
    });
};
// 파일 목록 업데이트 함수 (파일 하나만)
let updateFileList = () => {
    $('#fileList').empty(); // 기존 목록 비우기

    if (selectedFile) {
        $('#fileList').append(`
            <li>
                ${selectedFile.name} <button type="button" class="remove-btn">X</button>
            </li>
        `);

        // X 버튼 클릭 시 파일 제거
        $('.remove-btn').on('click', function () {
            selectedFile = null; // 선택된 파일 제거
            $('#file').val(''); // 파일 input 초기화
            updateFileList(); // 파일 목록 갱신
        });
    }
};


let checkSession = () => {
    let hUserId = $('#hiddenUserId').val();

    if (hUserId == null || hUserId === '') {
        window.location.href = "/member/login";
    }
};

