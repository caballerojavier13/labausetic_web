//
//  echolalia.js
//
//  Created by Christian Swinehart on 2010-12-15.
//  Copyright (c) 2011 Samizdat Drafting Co. All rights reserved.
//

(function($){

  DeadSimpleRenderer = function(canvas){
    var canvas = $(canvas).get(0)
    var ctx = canvas.getContext("2d");
    var particleSystem = null

    var that = {
      //
      // the particle system will call the init function once, right before the
      // first frame is to be drawn. it's a good place to set up the canvas and
      // to pass the canvas size to the particle system
      //
      init:function(system){
        // save a reference to the particle system for use in the .redraw() loop
        particleSystem = system

        // inform the system of the screen dimensions so it can map coords for us.
        // if the canvas is ever resized, screenSize should be called again with
        // the new dimensions
        particleSystem.screenSize(canvas.width, canvas.height)
        particleSystem.screenPadding(80) // leave an extra 80px of whitespace per side
      },

      //
      // redraw will be called repeatedly during the run whenever the node positions
      // change. the new positions for the nodes can be accessed by looking at the
      // .p attribute of a given node. however the p.x & p.y values are in the coordinates
      // of the particle system rather than the screen. you can either map them to
      // the screen yourself, or use the convenience iterators .eachNode (and .eachEdge)
      // which allow you to step through the actual node objects but also pass an
      // x,y point in the screen's coordinate system
      //

      redraw:function(){
        var nodeBoxes = {}
        ctx.clearRect(0,0, canvas.width, canvas.height)

        particleSystem.eachNode(function(node, pt){
          // node: {mass:#, p:{x,y}, name:"", data:{}}
          // pt:   {x:#, y:#}  node position in screen coords

          // draw a rectangle centered at pt

          var label = node.data.label||""
          var w = ctx.measureText(""+label).width + 20
          if (!(""+label).match(/^[ \t]*$/)){
            pt.x = Math.floor(pt.x)
            pt.y = Math.floor(pt.y)
          }else{
            label = null
          }
          ctx.fillStyle = "transparent"
          ctx.fillStyle = "white"
          ctx.strokeStyle="black";
          ctx.lineWidth=3;
          ctx.beginPath();
          ctx.arc(pt.x, pt.y,w,0,Math.PI*2,true);
          nodeBoxes[node.name] = [pt.x - w - 2 , pt.y - w - 2, w*2,w*2]
          ctx.closePath();
          ctx.fill();
          ctx.stroke();



          ctx.font = "12px Helvetica"
          ctx.lineWidth = 2;
          ctx.strokeStyle = 'black';
          ctx.stroke()
          ctx.textAlign = "center"
          ctx.fillStyle = "black"
          if (node.data.color=='none') ctx.fillStyle = '#eeeee'
            ctx.fillText(node.name||"X", pt.x, pt.y+4)
            ctx.fillText(node.name||"X", pt.x, pt.y+4)
          })

        particleSystem.eachEdge(function(edge, pt1, pt2){
          // edge: {source:Node, target:Node, length:#, data:{}}
          // pt1:  {x:#, y:#}  source position in screen coords
          // pt2:  {x:#, y:#}  target position in screen coords

          // draw a line from pt1 to pt2
          var weight = edge.data.weight
          var color = edge.data.color

          if (!color || (""+color).match(/^[ \t]*$/)) color = null

            // find the start point
          var tail = intersect_line_box(pt1, pt2, nodeBoxes[edge.source.name])
          var head = intersect_line_box(tail, pt2, nodeBoxes[edge.target.name])

          ctx.save()
          ctx.beginPath()
          ctx.lineWidth = 2
          ctx.strokeStyle = 'rgba(150,150,150,1)'


          ctx.moveTo(tail.x, tail.y)
          ctx.lineTo(head.x, head.y)
          ctx.stroke()
          ctx.restore()

          ctx.fillStyle =  'rgba(50,50,50,1)'

          ctx.save()
          // move to the head position of the edge we just drew
          var wt = !isNaN(weight) ? parseFloat(weight) : 1
          var arrowLength = 12 + wt
          var arrowWidth = 4 + wt
          ctx.fillStyle = (color) ? color : "#ccccc"
          ctx.translate(head.x, head.y);
          ctx.rotate(Math.atan2(head.y - tail.y, head.x - tail.x));

          // delete some of the edge that's already there (so the point isn't hidden)
          ctx.clearRect(-arrowLength/2,-wt/2, arrowLength/2,wt)

          // draw the chevron
          ctx.beginPath();
          ctx.moveTo(-arrowLength, arrowWidth );
          ctx.lineTo(0, 0);
          ctx.lineTo(-arrowLength, -arrowWidth);
          ctx.lineTo(-arrowLength * 0.6, -0);
          ctx.closePath();
          ctx.fill();
          ctx.restore()

        })


      }
    }

    var intersect_line_line = function(p1, p2, p3, p4)
  {
    var denom = ((p4.y - p3.y)*(p2.x - p1.x) - (p4.x - p3.x)*(p2.y - p1.y));
    if (denom === 0) return false // lines are parallel
      var ua = ((p4.x - p3.x)*(p1.y - p3.y) - (p4.y - p3.y)*(p1.x - p3.x)) / denom;
      var ub = ((p2.x - p1.x)*(p1.y - p3.y) - (p2.y - p1.y)*(p1.x - p3.x)) / denom;

      if (ua < 0 || ua > 1 || ub < 0 || ub > 1)  return false
        return arbor.Point(p1.x + ua * (p2.x - p1.x), p1.y + ua * (p2.y - p1.y));
      }

      var intersect_line_box = function(p1, p2, boxTuple)
    {
      var p3 = {x:boxTuple[0], y:boxTuple[1]},
      w = boxTuple[2],
      h = boxTuple[3]

      var tl = {x: p3.x, y: p3.y};
      var tr = {x: p3.x + w, y: p3.y};
      var bl = {x: p3.x, y: p3.y + h};
      var br = {x: p3.x + w, y: p3.y + h};

      return intersect_line_line(p1, p2, tl, tr) ||
      intersect_line_line(p1, p2, tr, br) ||
      intersect_line_line(p1, p2, br, bl) ||
      intersect_line_line(p1, p2, bl, tl) ||
      false
    }

    return that
  }

  $(document).ready(function(){
    $("#viewport").width($("#canvas").width())
    var sys = arbor.ParticleSystem(2000, 600, 0.3) // create the system with sensible repulsion/stiffness/friction
    sys.renderer = DeadSimpleRenderer("#viewport") // our newly created renderer will have its .init() method called shortly by sys...

    // pick a random datafile and load it
    var alltrans = "frm"

    // load the data into the particle system as is (since it's already formatted correctly for .grafting)
    var data = $.parseJSON($("#code").val())
    sys.graft({nodes:data.nodes, edges:data.edges})

  })



})(this.jQuery)
