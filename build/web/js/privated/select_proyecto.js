
var show_archivo = false;
var show_opearaciones = false;
var mobile_view = false;

    //$("#form input[tipe='submit']").on()
    $("#form input[type='submit']").click(function(){
        document.location.reload();
    });

$(function () {

    if ($(window).width() > 991) {
        mobile_view = false;
    } else {
        mobile_view = true;
    }

    $('#metodo_tree').jstree({
        "types": {
            "default": {
                "valid_children": ["default", "file"]
            },
            "file": {
                "icon": "glyphicon glyphicon-file",
                "valid_children": []
            }
        }
    });
});

$(function () {
    $(".columna1").height($(window).height() - $("body .container nav[1]").height());

    if ($(window).width() > 991) {
        $("#metodo_tree").height($(window).height() - parseInt($("#metodo_tree").css("top")));
    } else {
        $("#metodo_tree").height($(".columna1").height() - parseInt($("#metodo_tree").css("top")) - 100);
    }

});
$(window).resize(function () {
    $(".columna1").height($(window).height() - $("body .container nav[1]").height());
    if ($(window).width() > 991) {
        $("#metodo_tree").height($(window).height() - parseInt($("#metodo_tree").css("top")));
    } else {
        $("#metodo_tree").height($(".columna1").height() - parseInt($("#metodo_tree").css("top")) - 100);
    }

});


//ajuste arbol de métodos
$(window).resize(function () {
    if (show_archivo) {
        var hasta = $(window).width() - $(".columna1").width();
        $(".columna1").css("right", hasta);
    } else {
        $(".columna1").css("right", $(window).width());
    }

});

$("#archivo").click(function () {
    hideOperaciones();
    show_opearaciones = false;
    if (show_archivo) {
        hideArchivo();
        show_archivo = false;
    } else {
        $(".columna1").show();
        show_archivo = true;
    }
});


$(window).resize(function () {
    if ($(window).width() > 991) {
        mobile_view = false;
        $(".columna1").show();
        $(".columna3").show();
        show_archivo = true;
        show_opearaciones = true;
    } else {
        mobile_view = true;
        $(".columna1").hide();
        $(".columna3").hide();
        show_archivo = false;
        show_opearaciones = false;
    }
});

$("#operaciones").click(function () {
    hideArchivo();
    show_archivo = false;
    if (show_opearaciones) {
        hideOperaciones();
        show_opearaciones = false;
    } else {
        $(".columna3").show();
        show_opearaciones = true;
    }
});
$(".columna2").click(function () {
    if (mobile_view) {
        show_opearaciones = false;
        show_archivo = false;
        hideArchivo();
        hideOperaciones();
    }
});
$(".navbar-fixed-top").click(function () {
    if (mobile_view) {
        show_opearaciones = false;
        show_archivo = false;
        hideArchivo();
        hideOperaciones();
    }
});
function hideArchivo() {
    $(".columna1").hide();
}
function hideOperaciones() {
    $(".columna3").hide();
}



//Ajuste de Alto de Operaciones

$(function () {
    alto_operaciones();
});

$(window).resize(function () {
    alto_operaciones();
});

function alto_operaciones() {
    var alto_min = ($(".columna3> div").length * 50) + 70;
    if (($(window).height() - 20) < alto_min) {
        $(".columna3").height(alto_min);
    } else {
        if ($(".navbar-fixed-bottom").css("display") === "none") {
            $(".columna3").height(parseInt($(window).height()) - 121);
        } else {
            $(".columna3").height(parseInt($(window).height()) - 71);
        }
    }
}


//Ajuste de Alto de Archivo

$(function () {
    alto_archivo();
});

$(window).resize(function () {
    alto_archivo();
});

function alto_archivo() {
    var alto_min = ($(".columna1> div").length * 50) + 140;
    if ($(window).height() < alto_min) {
        $(".columna1").height(alto_min);
    } else {
        $(".columna1").height(parseInt($(window).height()) - 70);
    }
}