/* =========================================
                Preloader
============================================ */
$(window).on('load', function() {
  // makes sure that whole site is loaded
  $('#status').fadeOut();
  $('#preloader')
    .delay(350)
    .fadeOut('slow');
});


$('#year').text(new Date().getFullYear());

