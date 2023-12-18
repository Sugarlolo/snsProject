$('.nav_load').load('/view/navbar');
function reloadContent() {
    let loading = false;
    $(document).ready(function(){
        // 스크롤 이벤트 감지
        $(window).scroll(function(){
            loading = true;
            // 스크롤 페이지가 바닥에 닿을 때
            if($(window).scrollTop()+$(window).height() >= $(document).height()){
                $.ajax({
                    url: '/explore',
                    type: 'GET',
                    success: function (response) {
                        $('#loadedContent')
                    }
                });
            }
        })
    })
}

function loadContent() {
    $.ajax({
        url: '/explore/load',
        type: 'POST',
        dataType: "html",
        success: function (data) {
            $(".content").append(data);
        }
    });
}

function loadPost() {
    $.ajax({
        url: '/explore/',
        type: 'POST',
        dataType: 'html',
        success: function (data) {
            $(".body").html(data);
        }
    });
}

function getPostHtml() {
    let id = $(".postId").val();
    console.log("추출된 ID: "+ id);

    $.ajax({
        url: '/post/'+id,
        type: 'POST',
        dataType: 'html',
        success: function(data) {
            $(".modal-body").html(data);
        },
        error: function(xhr, status, error) {
            console.log(xhr.status + " " + status + " " + error);
        }
    });
}

$(document).ready(function() {
    $('.content').on('click', '.exampleModal', function() {
        var postId = $(this).find('.postId').val();
        $('#exampleModal .modal-body').html('이미지 번호: ' + postId);
    });
});

$(document).ready(function (){
    $.ajax({
        url: "/view/carousel",
        type: "POST",
        dataType: "html",
        success: function (data){
            $(".carousel").html(data)
        }
    })
})

// $(document).ready(function () {
//     $.ajax( {
//         url: "",
//         type: "",
//         dataType: "",
//
//         success: function(data) {
//
//         }
//     }
// })