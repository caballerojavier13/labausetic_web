var show_referencias = false;

$(function () {
    heigth_canvas();

    $(".menu-lateral > .pestaña").click(function () {
        if (show_referencias) {
            show_referencias = false;
            $(this).html("&laquo;");
            $(".menu-lateral").animate({right: "-" + $(".menu-lateral").width()}, 500);
            $(".menu-lateral > .pestaña").animate({right: 0}, 500);
        } else {
            show_referencias = true;
            $(this).html("&raquo;");
            $(".menu-lateral").animate({right: 0}, 500);
            $(".menu-lateral > .pestaña").animate({right: $(".menu-lateral").width()}, 500);
        }
    });

});

function heigth_canvas() {
    $("#viewport").width($("#canvas").width());
    $("#canvas").height(parseInt($(window).height()) - 70 - 60);
}

$(window).resize(function () {
    heigth_canvas();
    if (show_referencias) {
        $(".menu-lateral > .pestaña").css({right: $(".menu-lateral").width()});
    } else {
        $(".menu-lateral").css({right: "-" + $(".menu-lateral").width()});
    }
});

