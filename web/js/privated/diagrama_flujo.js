var currentZoom  = 1;
$(function() {
  var btn = document.getElementById("run"),
          cd = document.getElementById("code"),
          chart;
  var code = cd.value;

  if (chart) {
    chart.clean();
  }

  chart = flowchart.parse(code);
  chart.drawSVG('canvas', {
    'x': 0,
    'y': 0,
    'line-width': 2,
    'text-margin': 5,
    'font-size': 12,
    'font': 'normal',
    'font-family': 'Helvetica',
    'font-weight': 'normal',
    'font-color': 'black',
    'line-color': 'darkgray',
    'element-color': 'black',
    'fill': 'white',
    'yes-text': 'Si',
    'no-text': 'No',
    'arrow-end': 'block',
    'symbols': {
      'start': {
        'font-color': 'black',
        'element-color': 'black',
        'fill': 'white'
      },
      'end': {
        'class': 'end-element'
      }
    }
  });
  $("#canvas svg").attr("id", "viewport");
  
  
  heightCanvas();
  
});

$(window).resize(function() {
  heightCanvas();
});

function heightCanvas(){
  if($(window).height() > 320){
    $("#canvas").height($(window).height() - 90);
  }else{
    $("#canvas").height($(window).height() - 80);
    $("#canvas").css("top","50px");
    
  }
  
}

$("#zoom_out").click(function(){
  $('#canvas svg').css("zoom", currentZoom -= .1 );
});
$("#zoom_in").click(function(){
  $('#canvas svg').css("zoom", currentZoom += .1);
});