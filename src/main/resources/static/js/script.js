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

$("#authorInput").prop("disabled", true);

$("#authorBox").on("change", function () {
    $(this).next().prop("disabled", !$(this).prop("checked"));
});

$('#year').text(new Date().getFullYear());

