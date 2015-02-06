$(function(){
    heigth_canvas();
});

function heigth_canvas(){
    //$("#viewport").width($(window).width());
    $("#viewport").width($("#canvas").width());
    $("#canvas").height(parseInt($(window).height()) - 70 - 60);
}

$( window ).resize(function() {
    heigth_canvas();
});