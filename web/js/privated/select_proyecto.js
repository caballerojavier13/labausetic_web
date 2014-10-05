
var show_archivo = false;
var show_opearaciones = false;

$(function() {
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

$(function() {
  $(".columna1").height($(window).height() - $("body .container nav[1]").height());

  if ($(window).width() > 768) {
    $("#metodo_tree").css("top", $(".columna1 div:first-child").height() + $("body .container nav[1]").height() + 70);
    $("#metodo_tree").height($(window).height() - parseInt($("#metodo_tree").css("top")));
  } else {
    $("#metodo_tree").css("top", $(".columna1 div:first-child").height() + $("body .container nav[1]").height() + 20);
    $("#metodo_tree").height($(".columna1").height() - parseInt($("#metodo_tree").css("top")) - 100);
  }

});
$(window).resize(function() {
  $(".columna1").height($(window).height() - $("body .container nav[1]").height());
  if ($(window).width() > 768) {
    $("#metodo_tree").css("top", $(".columna1 div:first-child").height() + $("body .container nav[1]").height() + 70);
    $("#metodo_tree").height($(window).height() - parseInt($("#metodo_tree").css("top")));
  } else {
    $("#metodo_tree").css("top", $(".columna1 div:first-child").height() + $("body .container nav[1]").height() + 20);
    $("#metodo_tree").height($(".columna1").height() - parseInt($("#metodo_tree").css("top")) - 100);
  }

});




$("#archivo").click(function() {
  hideOperaciones();
  show_opearaciones = false;
  if ($(window).width() > parseInt($(".columna1").css("right"))) {
    hideArchivo();
    show_archivo = false;
  } else {
    $(".columna1").css("box-shadow", "0 0 20px gray");
    var hasta = $(window).width() - $(".columna1").width();
    $(".columna1").animate({right: hasta}, 500);
    show_archivo = true;
  }
});
$(window).resize(function() {
  if (show_archivo) {
    var hasta = $(window).width() - $(".columna1").width();
    $(".columna1").css("right", hasta);
  }else{
    $(".columna1").css("right", $(window).width());
  }

});
$("#operaciones").click(function() {
  hideArchivo();
  show_archivo = false;
  if (parseInt($(".columna3").css("right"))) {
    $(".columna3").css("box-shadow", "0 0 20px gray");
    $(".columna3").animate({right: "0"}, 500);
    show_opearaciones = true;
  } else {
    hideOperaciones();
    show_opearaciones = false;
  }
});
$(".columna2").click(function() {
  show_opearaciones = false;
  show_archivo = false;
  hideArchivo();
  hideOperaciones();
});
$(".navbar-fixed-top").click(function() {
  show_opearaciones = false;
  show_archivo = false;
  hideArchivo();
  hideOperaciones();
});
function hideArchivo() {
  $(".columna1").animate({right: "100%"}, 500);
  setTimeout(function() {
    $(".columna1").css("box-shadow", "none");
  }, 500);
}
function hideOperaciones() {
  var hasta = ($(".columna3").width()) * (-1);
  $(".columna3").animate({right: hasta}, 500);
  setTimeout(function() {
    $(".columna3").css("box-shadow", "none");
  }, 500);
}

