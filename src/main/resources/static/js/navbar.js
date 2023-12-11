// -------------------------for more option in navbar-------------------------

var MenuItem = $('#nav_submenu');
var searchBox = $('#search_box');
var mainLogo = $('.logo_img');
var miniLogo = $('#mini_insta_icon');

$('#submenu_btn').click(function () {
    if (MenuItem.css('display') === "none") {
        MenuItem.css('display', 'block');
        var sub_btn_posY =
            $("#submenu_btn").offset().top - $("#submenu_ul").outerHeight() + 50;
        $("#nav_submenu").css('top', sub_btn_posY);
    } else {
        MenuItem.css('display', 'none');
    }
});

function animateDisplay(selectedElement) {
    if (selectedElement.css('display') == 'none') {
        setTimeout(function () {
            selectedElement.fadeIn(300).css('display', 'block');

        }, 300);
    } else {
        setTimeout(function () {
            selectedElement.fadeIn(300).css('display', 'none');
        }, 300);
    }
}

function aniSearchMove(selectedElement) {
    if (selectedElement.css('display') == 'none') {
        setTimeout(function () {
            selectedElement.css('display', 'block');
            selectedElement.animate({
                left: '70px',
            }, 400);
        }, 200);
    } else {
        setTimeout(function () {
            selectedElement.animate({
                left: '-400px',
            }, 400);
        }, 200);
        setTimeout(function() {
            selectedElement.css('display', 'none');
        }, 500);
    }
}

function searchToggle() {

    if (searchBox.css('display') == 'none') {
        MenuItem.css('display', 'none');
        aniSearchMove(searchBox);
        animateDisplay(mainLogo);
        animateDisplay(miniLogo);

    } else {
        MenuItem.css('display', 'none');
        aniSearchMove(searchBox);
        animateDisplay(mainLogo);
        animateDisplay(miniLogo);
    }
    if (searchBox.css('display') == 'block') {
        $('#navbar_box').animate({
            width: '250px',
        }, 500);
        setTimeout(function () {
            $('.box_for_span').fadeIn(300).css('display', 'block');
        }, 300);
    } else {
        $('.box_for_span').css('display', 'none');
        $('#navbar_box').animate({
            width: '70px',
        }, 500);
    }
}

function dmToggle() {
    if (dmBox.style.display === 'none') {
        dmBox.style.display = 'block';
        searchBox.style.display = 'none'; // 검색창 숨김
        moreoption.style.display = 'none'; // moreopt 숨김
    } else {
        dmBox.style.display = 'none';
    }
}

$(document).ready(function () {
    $(window).resize(function () {
        if ($("#nav_submenu").css('display') === 'block')
            var sub_btn_posY =
                $("#submenu_btn").offset().top - $("#submenu_ul").outerHeight() + 50;
        $("#nav_submenu").css('top', sub_btn_posY);

    })
});
