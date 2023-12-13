$('.profile_nav_load').load('navbar.html');


function toggleContent(category) {
    if (category === 'post') {
        $('.profile-board').show();
        $('.saved-board').hide();
    } else if (category === 'saved') {
        $('.profile-board').hide();
        $('.saved-board').show();
    }
}

$('#postLink').click(function() {
    toggleContent('post');
    $('#postLink').attr('aria-selected', 'true');
    $('#savedLink').attr('aria-selected', 'false');
});

$('#savedLink').click(function() {
    toggleContent('saved');
    $('#postLink').attr('aria-selected', 'false');
    $('#savedLink').attr('aria-selected', 'true');
});

