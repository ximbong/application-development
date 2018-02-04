$(document).ready(function() {
  var key =
      "http://sandbox.api.simsimi.com/request.p?key=ebf0dfaf-719b-440c-9b30-4d7ead297e2e&lc=en&ft=1.0&text=";

    var objDiv = document.getElementById("chat-box");
    objDiv.scrollTop = objDiv.scrollHeight;
  // responsive nav
  $("#menu-icon").on("click", function() {
    var displayValue = $(".navButton").css("display");
    if (displayValue === "none") {
      $(".navButton").css("display", "block");
      $(".navButton").css("opacity", "1");
      $("#mobileNav").css("background", "#dfe3ee");
      $("#backdrop").css("display", "block");
    } else {
      $(".navButton").css("display", "none");
      $(".navButton").css("opacity", "0");
      $("#mobileNav").css("background", "#fff");
      $("#backdrop").css("display", "none");
    }
  });
  $(window).resize(function() {
    if (window.innerWidth > 1000) {
      $(".navButton").css("display", "inline-block");
    } else {
      $(".navButton").css("display", "none");
    }
  });

  //button push
  var lastButtonPushed = $("#section1");
  $("#section1,#section2,#section3").click(function() {
    var thisButtonPushed = $(this);

    if (lastButtonPushed !== thisButtonPushed) {
      lastButtonPushed.toggleClass("pushed").toggleClass("unpushed");
      thisButtonPushed.toggleClass("pushed").toggleClass("unpushed");

      lastButtonPushed = thisButtonPushed;
    }
  });

  //section display
  $("#section1").click(function() {
    $(".main").css("transform", " translateX(0)");
    $(".user-chat-column").css("display", "block");
    $(".group-chat-column").css("display", "none");
    $(".ann-column").css("display", "none");
    $(".input-box").css("display", "flex");
    if (window.innerWidth < 1000) {
      $(".id-displayer").removeClass("id-displayer-group");
    }
  });

  $("#section2").click(function() {
    $(".main").css("transform", " translateX(0)");
    $(".user-chat-column").css("display", "none");
    $(".group-chat-column").css("display", "block");
    $(".ann-column").css("display", "none");
    $(".input-box").css("display", "flex");
    if (window.innerWidth < 1000) {
      $(".id-displayer").addClass("id-displayer-group");
    }
  });

  $("#section3").click(function() {
    $(".main").css("transform", " translateX(0)");
    $(".user-chat-column").css("display", "none");
    $(".group-chat-column").css("display", "none");
    $(".ann-column").css("display", "block");
    $(".input-box").css("display", "none");
    if (window.innerWidth < 1000) {
      $(".id-displayer").removeClass("id-displayer-group");
    }
  });

  $(".back-button").click(function() {
    $(".main").css("transform", " translateX(0)");
  });

  $(".user-box").click(function() {
    var text = $(this)
    .find(".username")
    .text();
    $(".id-displayer").text(text);
    $(".status-displayer").css("display", "block");
    $(".ann-info").css("display", "none");
    $(".message-box").css("display", "block");
    if (window.innerWidth < 1000) {
      $(".main").css("transform", " translateX(-100%)");
    }
  });

  $(".group-box").click(function() {
    var text = $(this)
    .find(".groupname")
    .text();
    $(".ann-info").css("display", "none");
    $(".message-box").css("display", "block");
    if (window.innerWidth < 1000) {
      console.log("a");
      $(".main").css("transform", " translateX(-100%)");
    }
    $(".id-displayer").text(text);
    $(".status-displayer").css("display", "none");
  });

  function openAnnContainer() {
    $("#backdrop").css("display", "block");
    $(".ann-wrap").css("display", "flex");
    $(".ann-wrap").css("opacity", "1");
    if (window.innerWidth < 1000) {
      $(".mobileNav").css("opacity", "0");
    }
  }
  function closeAnnContainer() {
    $("#backdrop").css("display", "none");
    $(".ann-wrap").css("display", "none");
    $(".ann-wrap").css("opacity", "0");
    if (window.innerWidth < 1000) {
      $(".mobileNav").css("opacity", "1");
    }
  }
  function sendMsg() {
    var val = $(".inputbox").val();
    console.log("val =" + val);
    $(".chat-box").append(
      '<div class="message-div animated new-msg slideInRight msg-send"> <div class="chat-message ">' +
      val +
      "</div></div>"
    );
    $(".inputbox").val("");

    var objDiv = document.getElementById("chat-box");
    objDiv.scrollTop = objDiv.scrollHeight;
    var api = key + val;
    // $.getJSON(api, function(data) {
    //   var answer = data.response;
    //   $(".chat-box").append(
    //     '<div class="message-div animated new-msg slideInLeft msg-receive"> <div class="chat-message ">' +
    //     answer +
    //     "</div></div>"
    //   );
    // });
  }
  $(".ann-button").click(function() {
    openAnnContainer();
  });
  $(".new-ann-container .close").click(function() {
    closeAnnContainer();
  });

  $(".ann-submit").click(function() {
    var annTitle = $(".effect-1").val();
    var annTextarea = $("#ann-textarea").val();

    $(".ann-list").prepend(
      '<div class="ann-box"><div class="ann-title">' +
      annTitle +
      '</div><div class="ann-intro">' +
      annTextarea +
      "</div></div>"
    );
    closeAnnContainer();
  });

  $("input").keypress(function(e) {

    if (e.which == 13) {
      sendMsg();
    }
  });
  $("#send").click(function() {
    sendMsg();
  });

  $(".ann-list").on("click", ".ann-box", function() {
    console.log("ann box click");
    if (window.innerWidth < 1000) {
      $(".main").css("transform", " translateX(-100%)");
      $(".id-displayer").addClass("id-displayer-group");
    }
    var title = $(this)
    .find(".ann-title")
    .text();
    var intro = $(this)
    .find(".ann-intro")
    .text();
    console.log("title = " + title + "intro =" + intro);
    $(".id-displayer").text(title);
    $(".status-displayer").css("display", "none");
    $(".message-box").css("display", "none");
    $(".ann-info").css("display", "block");
    $(".ann-info").text(intro);
  });


  $("#status-dropdown-content a").click(function(){

    var color = $(this).find('i').css('color');

    $(".avatar i").css('color',color);

  });




});
