$('.nav_load').load('/view/navbar');
//현재 스크롤 위치 저장
let lastScroll = 0;
let page = 0;
let nowPageLimit = 0;
let nextPageLimit = 0;
let currentScroll = 0
let documentHeight = 0
let nowHeight = 0
let isLoading =false;
//데이터 가져오는 함수
function getData(limit){
    if (isLoading) {
        return;
    }
	//다음페이지
	nextPageLimit = (page + 1) * limit;

    isLoading = true

	$.ajax({
		type: "GET",
		url: '/test/load-more-data',
		data: {
			 "nextPageLimit" : nextPageLimit,
			 "limit" : limit
		},
		success: function(data){
		    if (data.length > 0 ) {
		        $("#home_feed_box2 > div").append(data);
		    }
		},
		error: function (data, status, err) {
			page = page;
		}, complete: function(){
		    isLoading = false;
			page = page + 1;
		}
	});
}

$(document).scroll(function(e){
    //현재 높이 저장
    currentScroll = $(this).scrollTop();
    //전체 문서의 높이
    documentHeight = $(document).height();

    //(현재 화면상단 + 현재 화면 높이)
    nowHeight = $(this).scrollTop() + $(window).height();


    //스크롤이 아래로 내려갔을때만 해당 이벤트 진행.
    if(currentScroll > lastScroll){

        //nowHeight을 통해 현재 화면의 끝이 어디까지 내려왔는지 파악가능
        //즉 전체 문서의 높이에 일정량 근접했을때 글 더 불러오기)
        if(documentHeight < (nowHeight + (documentHeight*0.1))){
            getData(8);
        }
    }

    //현재위치 최신화
    lastScroll = currentScroll;
});

$(document).on("click", ".recommend_follow", function() {
        var $this = $(this);

        var followId = $(this).data("follow-id");
        var buttonText = $(this).text();

        if ($.trim(buttonText) === "팔로우") {
           $.ajax({
           		type: "GET",
           		url: '/follow/followUser',
           		data: {
           			 "followId" : followId,
           		},
           		success: function(data){
           		    if (data.success) {
           		        $this.text("팔로잉");
           		        $this.css("color", "black");
           		    }
           		    alert(data.message)
           		},
           		error: function (data) {
           		    console.error("에러 발생:", data);
           		}
           	});
        } else {
            // "팔로우" 버튼이 아닌 경우
            $.ajax({
                    type: "GET",
                    url: '/follow/followCancel',
                    data: {
                         "followId" : followId,
                    },
                    success: function(data){
                        if (data.success) {
                            $this.text("팔로우");
                            $this.css("color", "deepskyblue");
                        }
                        alert(data.message)
                    },
                    error: function (data) {
                        console.error("에러 발생:", data);
                    }
                });
        }
});

$(document).on('keypress', '.feed_footer_postComment', function(event) {
    if (event.which === 13) {
        event.preventDefault();

        // 폼 요소들의 값을 가져오기
        var comment = $(this).val();
        var postId = $(this).closest('form').find('input[type="hidden"][id="postId"]').val();

        // AJAX를 사용하여 데이터 전송
        $.ajax({
            type: 'GET',
            url: '/comment/registerComment',
            data: {
                comment: comment,
                postId: postId
            },
            success: function(response) {

              if (response.success) {
                 var newCommentSize = response.count;
                 var articleId = 'Post' + postId;
                 // 해당 article의 ID를 사용하여 댓글 개수 업데이트
                 $('#' + articleId + ' .feed_footer_commentNum').text(newCommentSize);
              }
            },
            error: function(error) {
                // 오류가 발생한 경우에 대한 처리
                console.log(error);
            }
        });

        // 폼 초기화
        $(this).val('');
    }
});

$(document).on('click', ".fa-heart", function() {
    var postId = $(this).data("post-id");
    var className = $(this).attr('class');
    var $this = $(this);

    // 좋아요 수를 나타내는 요소를 찾음
    var likeNumElement = $('#likeCount_' + postId);

    if (className === "fa-solid fa-heart") {
          $.ajax({
                    type: 'GET',
                    url: '/post/cancelLike',
                    data: {
                        postId: postId,
                    },
                    success: function(response) {
                      if (response.success) {
                         alert(response.message);
                         likeNumElement.text(response.count);
                         $this.prop('class', 'fa-regular fa-heart');
                         $this.css("color", "black");
                      }
                    },
                    error: function(error) {
                        // 오류가 발생한 경우에 대한 처리
                        console.log(error);
                    }
                });
    } else if (className === "fa-regular fa-heart") {
           $.ajax({
                 type: 'GET',
                 url: '/post/registerLike',
                 data: {
                     postId: postId,
                 },
                 success: function(response) {
                   if (response.success) {
                      alert(response.message)
                      likeNumElement.text(response.count);
                      $this.prop('class', 'fa-solid fa-heart');
                      $this.css("color", "red");
                   }
                 },
                 error: function(error) {
                     // 오류가 발생한 경우에 대한 처리
                     console.log(error);
                 }
             });
    }
});

$(document).on('click', ".fa-bookmark", function() {
    var postId = $(this).data("post-id");
    var className = $(this).attr('class');
    var $this = $(this);

    if (className === "fa-solid fa-bookmark") {
    console.log("클래스명은 구분함");
          $.ajax({
                    type: 'GET',
                    url: '/bookmark/registerBookmark',
                    data: {
                        postId: postId,
                    },
                    success: function(response) {
                      if (response.success) {
                         alert(response.message);
                         $this.prop('class', 'fa-regular fa-bookmark');
                      }
                    },
                    error: function(error) {
                        // 오류가 발생한 경우에 대한 처리
                        console.log(error);
                    }
                });
    } else if (className === "fa-regular fa-bookmark") {
           $.ajax({
                 type: 'GET',
                 url: '/bookmark/registerBookmark',
                 data: {
                     postId: postId,
                 },
                 success: function(response) {
                   if (response.success) {
                      alert(response.message)
                      $this.prop('class', 'fa-solid fa-bookmark');
                   }
                 },
                 error: function(error) {
                     // 오류가 발생한 경우에 대한 처리
                     console.log(error);
                 }
             });
    }
});
/*
$(document).on('click', ".feed_footer_emoteButton", function () {
         // 현재 클릭된 .feed_footer_emoteButton 내부의 .emoticon 요소에 대한 작업 수행
        $(this).find('.emoticonbox').each(function () {
        var emoticonBox = $(this);
        emoticonBox.toggle();

         // 클릭 이벤트 전파 방지
            event.stopPropagation();
    });
});*/

$(document).on('click', ".feed_footer_emoteButton .fa-face-smile", function (event) {
    // 현재 클릭된 .fa-face-smile이 포함된 .feed_footer_emoteButton 내부의 .emoticonbox 요소에 대한 작업 수행
    var emoticonBox = $(this).closest('.feed_footer_emoteButton').find('.emoticonbox');

    // 클릭 이벤트 전파 방지
    event.stopPropagation();

    // .emoticonbox를 토글
    emoticonBox.toggle();
});

// 문서의 다른 부분을 클릭했을 때 이벤트 전파 방지
$(document).on('click', function (event) {
    // 현재 클릭된 요소가 .fa-face-smile 또는 .emoticonbox 내부에 속한 요소가 아니라면 모든 .emoticonbox를 닫음
    if (!$(event.target).hasClass('fa-face-smile') && $(event.target).closest('.emoticonbox').length === 0) {
        $(".feed_footer_emoteButton .emoticonbox").hide();
    }
});


$(document).on('click', ".emoticon", function () {
    // 클릭된 .emoticon의 텍스트를 가져와서 댓글 입력란에 추가
    var emoticonText = $(this).text();
    var postCommentInput = $(this).closest(".home_feed_contents").find(".feed_footer_postComment")
    var currentText = postCommentInput.val();
     postCommentInput.val(currentText + emoticonText);

    // 댓글 입력란에 포커스 설정
    postCommentInput.focus();
});