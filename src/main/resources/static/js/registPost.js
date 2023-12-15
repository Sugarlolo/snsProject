var user_src;
var user_id;

$('#create_cancle_img_box').on('click', function() {
    window.location.href = "/view/home";
});

$('#edit_img_add_box #edit_img_add_button').on('click', function () {
   $('#edit_img_input').click();
});

var newWidth = 0;
var newHeight = 0;
var aspectRatio = 0;
var mode = 'UPLOAD';
var image = new Image();
var original_img;
var currentScale = 1;
$('#edit_img_add_box #edit_img_input').on('change', function () {
   const selectedFile = this.files[0];

   const reader = new FileReader();

   reader.onload = function (e) {
      const fileData = e.target.result;

      $('#edit_img_title_container').css('z-index', '1');
      $('#edit_img_title_leftWrap').css('display', 'flex');
      $('#edit_img_title_leftWrap').css('z-index', '2');
      $('#edit_img_title_rightWrap').css('display', 'flex');
      $('#edit_img_title_rightWrap').css('z-index', '2');
      $('#edit_img_title').text('자르기');

      $('#edit_img_add_padding').css('display', 'none');

      var editField = $("<div>").addClass("edit_field_container");
      var interactiveBox = $("<div>").addClass("edit_field_box");
      var editImg = $("<div>").addClass("edit_field_img");
      var editDiv = $("<div>").addClass("edit_field_div");
      var editScale = $("<div>").addClass("edit_field_scale_box");
      var zoomBox = $("<div>").addClass("edit_zoom_box");
      var zoomInput = $("<input>").addClass("edit_zoom_input");
      $(zoomInput).attr({
         'type': 'range',
         'min': '0',
         'max': '100',
         'readonly': true,
         'value': '0'
      });

      $('#edit_img_add_box').append(editField);
      $('.edit_field_container').append(interactiveBox);
      $('.edit_field_box').append(editImg);
      $('.edit_field_box').append(editDiv);
      $('.edit_field_box').append(editScale);
      $('.edit_field_scale_box').append('<i class="fa-solid fa-magnifying-glass-plus"></i>');
      $('.edit_field_box').append(zoomBox);
      $('.edit_zoom_box').append(zoomInput);

      $('.edit_field_container').css('overflow', 'hidden');
      $('.edit_field_img').css('background-image', 'url(' + fileData + ')');
      $('.edit_field_img').css('background-position', 'center center');
      $('.edit_field_img').css('background-repeat', 'no-repeat');
      $('.edit_field_img').css('background-size', 'cover');
      $('.edit_field_img').css('overflow', 'hidden');
      image.onload = function () {
         aspectRatio = image.width / image.height;

         newWidth = Math.max(730, aspectRatio * 730);
         newHeight = 730;

         $('.edit_field_img').css('width', `${newWidth}px`);
         $('.edit_field_img').css('height', `${newHeight}px`);
      };

      $('.edit_field_scale_box').on('click', function () {
         if ($('.edit_zoom_box').css('display') == 'none') {
            $('.edit_zoom_box').fadeIn(300, function () {
               $('.edit_zoom_box').css('display', 'flex');
            });
         } else if ($('.edit_zoom_box').css('display') == 'flex') {
            $('.edit_zoom_box').fadeOut(300, function () {
               $('.edit_zoom_box').css('display', 'none');
            })
         }
      });

      $('.edit_zoom_input').on('input', function () {
         currentScale = $(this).val() * 0.01 + 1;
         var currentTransform = $('.edit_field_img').css('transform');
         var tempX = ((newWidth * currentScale) - minWidth) / 2;
         var tempY = ((newHeight * currentScale) - minHeight) / 2;
         if (newX > tempX) {
            newX = tempX;
         } else if (newX < -tempX) {
            newX = -tempX;
         }
         if (newY > tempY) {
            newY = tempY;
         } else if (newY < -tempY) {
            newY = -tempY;
         }
         var newTransform = currentTransform === 'none' ? 'scale(' + currentScale + ')' : 'translate3d(' + newX + 'px, ' + newY + 'px, 0)' + ' scale(' + currentScale + ')';


         $('.edit_field_img').css('transform', newTransform);
      });

      image.src = fileData;
   };

   reader.readAsDataURL(selectedFile);
   mode = 'EDIT';
});

$('#edit_img_title_cover').on('click', function () {
   if (mode == 'EDIT') {
      mode = 'UPLOAD';
      $('#edit_img_title_leftWrap').css('display', 'none');
      $('#edit_img_title_rightWrap').css('display', 'none');
      $('.edit_field_container').remove();
      $('#edit_img_title').text('새 게시물 만들기');
      $('#edit_img_add_padding').css('display', 'flex');
      $('#edit_img_input').val('');
   } else if (mode == 'PHOTOSHOP') {
      mode = 'EDIT';
      $('#edit_img_title').text('자르기');
      $('.edit_field_container').css('display', 'block');
      $('#edit_img_add_container').css('flex-direction', 'column');
      $('#edit_img_add_container').css('flex-grow', '0');
      $('.edit_photo_canvas').remove();
      $('.edit_photo_filter_wrap').remove();
      $('#edit_img_align5').animate({
         width: '730px'
      }, 400);
   } else if (mode == 'POSTING') {
      mode = 'PHOTOSHOP';
      $('.edit_photo_filter_wrap').remove();
      $('#edit_img_title').text('편집');
      filterArea();
   }
});

$('#edit_img_title_rightBox').on('click', function () {
   if (mode == 'EDIT') {
      mode = 'PHOTOSHOP';
      $('#edit_img_align5').animate({
         width: '1070px'
      }, 400);
      $('.edit_field_container').css('display', 'none');
      $('#edit_img_add_container').css('flex-direction', 'row');
      $('#edit_img_add_container').css('flex-grow', '1');
      $('#edit_img_title').text('편집');
      var canvas = $('<canvas>').addClass("edit_photo_canvas");
      $('#edit_img_add_box').append(canvas);
      drawImage(canvas);
      filterArea();
   } else if (mode == 'PHOTOSHOP') {
      mode = 'POSTING';
      $('#edit_img_title').text('새 게시물 만들기');
      $('.edit_photo_filter_box').empty();
      textArea();
   }
});

var isDragging = false;
var initialMouseX, initialMouseY, initialElementX, initialElementY;
var minWidth = 730;
var minHeight = 730;
var newX = 0;
var newY = 0;
$(document).on('mousedown', '.edit_field_div', function (e) {
   isDragging = true;
   initialMouseX = e.clientX;
   initialMouseY = e.clientY;

   $('.edit_field_div').css('background-image', 'linear-gradient(#ddd 2px, transparent 3px), linear-gradient(to right, #ccc 2px, transparent 3px)');

   var transformMatrix = $('.edit_field_img').css('transform').replace(/[^0-9\-.,]/g, '').split(',');
   initialElementX = parseInt(transformMatrix[4]) || 0;
   initialElementY = parseInt(transformMatrix[5]) || 0;

});

$(document).on('mousemove', function (e) {
   if (isDragging) {
      var offsetX = (e.clientX - initialMouseX) / 3;
      var offsetY = (e.clientY - initialMouseY) / 3;

      newX = initialElementX + offsetX;
      newY = initialElementY + offsetY;

      $('.edit_field_img').css({
         'transform': 'translate3d(' + newX + 'px, ' + newY + 'px, 0)' + ' scale(' + currentScale + ')'
      });
   }
});

$(document).on('mouseup', function () {
   if (isDragging) {
      isDragging = false;
      $('.edit_field_div').css('background-image', 'none');
      var tempX = ((newWidth * currentScale) - minWidth) / 2;
      var tempY = ((newHeight * currentScale) - minHeight) / 2;
      if (newX > tempX) {
         newX = tempX;
      } else if (newX < -tempX) {
         newX = -tempX;
      }
      if (newY > tempY) {
         newY = tempY;
      } else if (newY < -tempY) {
         newY = -tempY;
      }

      $('.edit_field_img').css({
         'transform': 'translate3d(' + newX + 'px, ' + newY + 'px, 0)' + ' scale(' + currentScale + ')'
      });
   }
});

function drawImage(canvas) {
   const cvs = canvas[0];
   const ctx = cvs.getContext('2d');
   cvs.width = 730;
   cvs.height = 730;

   var tempVar = image.width < image.height ? image.width : image.height;
   tempVar = tempVar / currentScale;
   var tempRatio = tempVar / 730;
   imageX = ((image.width - tempVar) / 2) - (newX * tempRatio);
   imageY = ((image.height - tempVar) / 2) - (newY * tempRatio);

   ctx.drawImage(image,
      imageX, imageY, tempVar, tempVar,
      0, 0, cvs.width, cvs.height);
   original_img = ctx.getImageData(0, 0, cvs.width, cvs.height);
}

function filterArea() {
   var wrap = $('<div>').addClass('edit_photo_filter_wrap');
   var cont = $('<div>').addClass('edit_photo_filter_container');
   var box = $('<div>').addClass('edit_photo_filter_box');
   var head = $('<div>').addClass('edit_photo_filter_head');
   var head_p = $('<div>').addClass('edit_photo_filter_head_pack');
   var head_c = $('<div>').addClass('edit_photo_filter_head_custom');
   var body_cont = $('<div>').addClass('edit_photo_filter_body_container');
   var body_box = $('<div>').addClass('edit_photo_filter_body_box');

   $('#edit_img_add_container').append(wrap);
   $(wrap).append(cont);
   $(cont).append(box);
   $(box).append(head);
   $(box).append(body_cont);
   $(head).append(head_p);
   $(head).append(head_c);
   $(head_p).append($('<span>'));
   $(head_c).append($('<span>'));
   $(body_cont).append(body_box);

   $('.edit_photo_filter_head_pack span').text('필터');
   $('.edit_photo_filter_head_custom span').text('조정');

   var list = ['SnixJ', 'ZylxP', 'Yuzio', 'Grey', 'Quixz', 'Original'];
   var img_list = ['/img/first_filter.png', '/img/second_filter.png', '/img/third_filter.png'
                  , '/img/fourth_filter.png', '/img/fifth_filter.png', '/img/original.jpg']

   for (var i = 0; i < 6; i++) {
      var filter_list = $('<div>').addClass('edit_photo_filter_list');
      var filter_button = $('<button>').addClass('edit_photo_filter_btn');
      var filter_img_box = $('<div>').addClass('edit_photo_filter_imgBox');
      var filter_title = $('<div>').addClass('edit_photo_fiter_title');
      var filter_img = $('<img>').addClass('edit_photo_filter_img');
      $(filter_title).text(list[i]);
      $(filter_img).attr('src', img_list[i]);
      $(body_box).append(filter_list);
      $(filter_list).append(filter_button);
      $(filter_button).append(filter_img_box);
      $(filter_button).append(filter_title);
      $(filter_img_box).append(filter_img);
   }
}

function textArea() {
   var wrap = $('<div>').addClass('edit_text_name_wrap');
   var cont = $('<div>').addClass('edit_text_name_cont');
   var box = $('<div>').addClass('edit_text_name_box');
   var sep = $('<div>').addClass('edit_text_name_separate');
   var imgBox = $('<div>').addClass('edit_text_name_img_box');
   var imgSpan = $('<span>').addClass('edit_text_name_img_span');
   var img = $('<img>').addClass('edit_text_name_img');
   var textBox = $('<div>').addClass('edit_text_name_value_box');
   var text = $('<span>').addClass('edit_text_name_value');

   var commentCont = $('<div>').addClass('edit_text_comment_container');
   var commentBox = $('<div>').addClass('edit_text_comment_box');
   var comment = $('<textarea>').addClass('edit_text_comment');

   $('.edit_photo_filter_box').append(wrap);
   $(wrap).append(cont);
   $(cont).append(box);
   $(box).append(sep);
   $(sep).append(imgBox);
   $(imgBox).append(imgSpan);
   $(imgSpan).append(img);
   $(sep).append(textBox);
   $(textBox).append(text);
   $('.edit_photo_filter_box').append(commentCont);
   $(commentCont).append(commentBox);
   $(commentBox).append(comment);
   $(comment).attr('spellcheck', 'false');
   $(comment).attr('placeholder', '문구를 입력해주세요..');
   $(text).text('123123123');
   $(img).attr('src', 'img/first_filter.png');
}


var camanInstance;
$(document).on('click', '.edit_photo_filter_btn', function () {
   var index = $('.edit_photo_filter_btn').index(this);
   if (camanInstance) {
      camanInstance.revert();
   }
   switch (index) {
      case 0:
         camanInstance = Caman('.edit_photo_canvas', function () {
            this.saturation(6);
            this.brightness(3);
            this.channels({
               red: 3,
               blue: 2,
               green: 4
            });
            this.stackBlur(2);
            this.render();
         });
         break;
      case 1:
         camanInstance = Caman('.edit_photo_canvas', function () {
            this.hue(5);
            this.gamma(1);
            this.saturation(-5);
            this.brightness(10);
            this.clip(11);
            this.contrast(-5);
            this.sepia(5);
            this.noise(1);
            this.sharpen(10);
            this.render();
         });
         break;
      case 2:
         camanInstance = Caman('.edit_photo_canvas', function () {
            this.saturation(-30);
            this.sepia(50);
            this.noise(8);
            this.render();
         });
         break;
      case 3:
         camanInstance = Caman('.edit_photo_canvas', function () {
            this.greyscale();
            this.render();
         });
         break;
      case 4:
         camanInstance = Caman('.edit_photo_canvas', function () {
            this.brightness(10);
            this.vibrance(-5);
            this.gamma(0.8);
            this.contrast(4);
            this.saturation(14);
            this.exposure(8);
            this.render();
         });
         break;
      case 5:
         camanInstance = Caman('.edit_photo_canvas', function () {
            this.render();
         });
         break;
   };
});
