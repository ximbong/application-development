document.addEventListener("DOMContentLoaded", function(event) {
  var key =
    "http://sandbox.api.simsimi.com/request.p?key=ebf0dfaf-719b-440c-9b30-4d7ead297e2e&lc=en&ft=1.0&text=";

  var objDiv = document.getElementById("chat-box");
  objDiv.scrollTop = objDiv.scrollHeight;
  // responsive nav

  document.getElementById("menu-icon").onclick = function() {
    let displayValue = document.getElementById("tabs").style.display;
    if (displayValue !== "flex") {
      console.log("display tabs");
      document.getElementById("backdrop").style.display = "block";
      document.getElementById("tabs").style.display = "flex";
    }
  };

  // $(window).resize(function() {
  //   if (window.innerWidth > 1000) {
  //     $(".navButton").css("display", "inline-block");
  //   } else {
  //     $(".navButton").css("display", "none");
  //   }
  // });

  var navUsername = document.getElementById("nav-username");

  window.onclick = function(event) {
    // console.log(event.target);
    if (document.getElementById("dropdown").contains(event.target))
      document.getElementById("dropdown-content").style.display = "block";
    else document.getElementById("dropdown-content").style.display = "none";

    if (document.getElementById("status-dropdown").contains(event.target))
      document.getElementById("status-dropdown-content").style.display =
        "block";
    else
      document.getElementById("status-dropdown-content").style.display = "none";

    if (document.getElementById("tabs").style.display === "flex") {
      if (document.getElementById("backdrop").contains(event.target)) {
        document.getElementById("tabs").style.display = "none";
        document.getElementById("backdrop").style.display = "none";
      }
    }
  };
  //button push

  var lastButtonPushed = document.getElementById("section1");
  var section = document.getElementsByClassName("section");

  for (var i = 0; i < section.length; i++) {
    // Here we have the same onclick

    section[i].addEventListener("click", function(event) {
      var thisButtonPushed = event.target;
      console.log(lastButtonPushed);
      if (lastButtonPushed !== thisButtonPushed) {
        lastButtonPushed.classList.toggle("pushed");
        thisButtonPushed.classList.toggle("pushed");
        lastButtonPushed.classList.toggle("unpushed");
        thisButtonPushed.classList.toggle("unpushed");
        lastButtonPushed = thisButtonPushed;
      }
    });
  }

  //section display

  document.getElementById("section1").addEventListener("click", function() {
    document.querySelector(".user-chat-column").style.display = "block";
    document.querySelector(".group-chat-column").style.display = "none";
    document.querySelector(".ann-column").style.display = "none";
    document.querySelector(".input-box").style.display = "flex";
    if (window.innerWidth < 1000) {
      document
        .querySelector(".id-displayer")
        .classList.remove("id-displayer-group");
    }
  });

  document.getElementById("section2").addEventListener("click", function() {
    document.querySelector(".user-chat-column").style.display = "none";
    document.querySelector(".group-chat-column").style.display = "block";
    document.querySelector(".ann-column").style.display = "none";
    document.querySelector(".input-box").style.display = "flex";
    if (window.innerWidth < 1000) {
      document
        .querySelector(".id-displayer")
        .classList.add("id-displayer-group");
    }
  });

  document.getElementById("section3").addEventListener("click", function() {
    document.querySelector(".user-chat-column").style.display = "none";
    document.querySelector(".group-chat-column").style.display = "none";
    document.querySelector(".ann-column").style.display = "block";
    document.querySelector(".input-box").style.display = "none";
    if (window.innerWidth < 1000) {
      document
        .querySelector(".id-displayer")
        .classList.remove("id-displayer-group");
    }
  });

  document.querySelector(".back-button").addEventListener("click", function() {
    document.querySelector(".main").style.transform = "translateX(0)";
    document.querySelector(".section-div").style.display = "block";
  });

  for (let val of document.getElementsByClassName("user-box")) {
    val.addEventListener("click", function() {
      let element = val.getElementsByTagName("span");
      document.querySelector(".id-displayer").textContent =
        element[0].textContent;

      document.querySelector(".status-displayer").style.display = "block";
      document.querySelector(".ann-info").style.display = "none";
      document.querySelector(".message-box").style.display = "block";

      if (window.innerWidth < 1000) {
        document.querySelector(".main").style.transform = " translateX(-100%)";
        document.querySelector(".section-div").style.display = "none";
      }
    });
  }

  for (let val of document.getElementsByClassName("group-box")) {
    val.addEventListener("click", function() {
      let element = val.getElementsByTagName("span");
      document.querySelector(".id-displayer").textContent =
        element[0].textContent;

      document.querySelector(".status-displayer").style.display = "none";
      document.querySelector(".ann-info").style.display = "none";
      document.querySelector(".message-box").style.display = "block";

      if (window.innerWidth < 1000) {
        document.querySelector(".main").style.transform = " translateX(-100%)";
        document.querySelector(".section-div").style.display = "none";
      }
    });
  }

  function openAnnContainer() {
    document.getElementById("backdrop").style.display = "block";
    document.querySelector(".wrap-div").style.display = "flex";
    document.querySelector(".wrap-div").style.opacity = "1";
    document.querySelector(".new-ann-container").style.display = "block";
  }

  function closeAnnContainer() {
    document.getElementById("backdrop").style.display = "none";
    document.querySelector(".wrap-div").style.display = "none";
    document.querySelector(".wrap-div").style.opacity = "0";
    document.querySelector(".new-ann-container").style.display = "none";
  }

  function sendMsg() {
    let val = document.getElementById("inputbox").value;
    console.log("val =" + val);
    let textnode = document.createTextNode(val);

    let innerDiv = document.createElement("div");
    let outerDiv = document.createElement("div");
    let classlist = [
      "message-div",
      "animated",
      "slideInRight",
      "new-msg",
      "msg-send"
    ];
    innerDiv.classList.add("chat-message");
    outerDiv.classList.add(...classlist);
    innerDiv.appendChild(textnode);
    outerDiv.appendChild(innerDiv);
    document.querySelector(".chat-box").appendChild(outerDiv);

    document.getElementById("inputbox").value = "";

    var objDiv = document.getElementById("chat-box");
    objDiv.scrollTop = objDiv.scrollHeight;
  }
  // var api = key + val;
  // $.getJSON(api, function(data) {
  //   var answer = data.response;
  //   $(".chat-box").append(
  //     '<div class="message-div animated new-msg slideInLeft msg-receive"> <div class="chat-message ">' +
  //     answer +
  //     "</div></div>"
  //   );
  // });

  document.querySelector(".ann-button").addEventListener("click", function() {
    openAnnContainer();
  });

  document
    .querySelector(".new-ann-container")
    .addEventListener("click", function() {
      if (
        event.target === document.getElementById("close") ||
        event.target === document.getElementById("close").childNodes[0]
      )
        closeAnnContainer();

      if (event.target === document.querySelector(".ann-submit")) {
        let annTitle = document.querySelector(".ann-input-title").value;
        let annTextarea = document.getElementById("ann-textarea").value;
        console.log(annTitle);
        console.log(annTextarea);
        if (annTitle !== "" && annTextarea !== "") {
          let boxDiv = document.createElement("div");
          let titleDiv = document.createElement("div");
          let introDiv = document.createElement("div");
          boxDiv.classList.add("ann-box");
          titleDiv.classList.add("ann-title");
          introDiv.classList.add("ann-intro");
          let textnode1 = document.createTextNode(annTitle);
          let textnode2 = document.createTextNode(annTextarea);
          titleDiv.appendChild(textnode1);
          introDiv.appendChild(textnode2);
          boxDiv.appendChild(titleDiv);
          boxDiv.appendChild(introDiv);

          let list = document.querySelector(".ann-list");
          list.insertBefore(boxDiv, list.childNodes[0]);
          closeAnnContainer();
          // document.querySelector(".ann-input-title").value = "";
          // document.getElementById("ann-textarea").value = "";
        }
      }
    });

  document.querySelector("#inputbox").addEventListener("keypress", function(e) {
    var key = e.which || e.keyCode;
    if (key === 13) {
      sendMsg();
    }
  });

  document.querySelector("#send").addEventListener("click", function(e) {
    sendMsg();
  });

  document.querySelector(".ann-list").addEventListener("click", function(event) {
    if (window.innerWidth < 1000) {
      document.querySelector(".main").style.transform = " translateX(-100%)";
      document
        .querySelector(".id-displayer")
        .classList.add("id-displayer-group");
      document.querySelector(".section-div").style.display = "none";
    }
    let el;
    console.log(event.target);
    if (event.target.classList.contains("ann-box")) el = event.target;
    if (
      event.target.classList.contains("ann-title") ||
      event.target.classList.contains("ann-intro")
    )
      el = event.target.parentElement;
    let title, intro;
    var children = el.childNodes;
    title = children[0].textContent;
    intro = children[1].textContent;
    console.log("title = " + title);
    console.log("intro = " + intro);
    document.querySelector(".id-displayer").textContent = title;
    document.querySelector(".status-displayer").style.display = "none";
    document.querySelector(".message-box").style.display = "none";

    document.querySelector(".ann-info").style.display = "block";

    document.querySelector(".ann-info").textContent = intro;
  });

  document
    .querySelector("#status-dropdown-content a")
    .addEventListener("click", function() {
      let color;
      console.log(event.target.tagName);
      if (event.target.tagName === "A") {
        let child = event.target.childNodes;
        let child2 = child[1].childNodes;
        color = child2[0].style.color;
        console.log(child[1]);
      } else {
        color = event.target.style.color;
        console.log(event.target);
      }
      document.querySelector(".avatar .status").style.color = color;
    });
});
