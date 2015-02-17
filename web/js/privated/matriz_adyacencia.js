$(function () {
    init();
});

$(window).resize(function () {
    init();
});

function init() {
    $(".table-responsive").height(parseInt($(window).height()) - 110 - $(".titulo2").height());
    var alto_canvas = $(".table-responsive").height();
    var alto_tabla = $("table").height();

    if (parseInt(alto_tabla) > parseInt(alto_canvas)) {
        $("table").css({top: "0px"});
    } else {
        $("table").css({top: ((parseInt(alto_canvas) - parseInt(alto_tabla)) / 2)});
    }
    $("table").css({width: ($("table tbody tr").first().find("th").length * 42)});
    $.each($("table tbody tr td"), function (index, item) {
        if ($(item).text() === "1") {
            $(item).css({"font-weight": "bolder"});
            $(item).css({color: "blue"});
        }
    });



}
$(function () {
    $("table tbody tr td").on("mouseover", function () {
        $(".td_col_hover").removeClass("td_col_hover");
        $("." + $("table tbody td:hover").attr("class")).addClass("td_col_hover");
    });
    $("table tbody tr td").on("mouseout", function () {
        $(".td_col_hover").removeClass("td_col_hover");
    });
});