document.addEventListener("DOMContentLoaded", function(event) {
  var key =
  "http://sandbox.api.simsimi.com/request.p?key=ebf0dfaf-719b-440c-9b30-4d7ead297e2e&lc=en&ft=1.0&text=";

  var objDiv = document.getElementById("chat-box");
  objDiv.scrollTop = objDiv.scrollHeight;
  // responsive nav

  function xmlToJson(xml) {

    // Create the return object
    var obj = {};

    if (xml.nodeType == 1) { // element
      // do attributes
      if (xml.attributes.length > 0) {
        obj["@attributes"] = {};
        for (var j = 0; j < xml.attributes.length; j++) {
          var attribute = xml.attributes.item(j);
          obj["@attributes"][attribute.nodeName] = attribute.nodeValue;
        }
      }
    } else if (xml.nodeType == 3) { // text
      obj = xml.nodeValue;
    }

    // do children
    // If just one text node inside
    if (xml.hasChildNodes() && xml.childNodes.length === 1 && xml.childNodes[0].nodeType === 3) {
      obj = xml.childNodes[0].nodeValue;
    }
    else if (xml.hasChildNodes()) {
      for(var i = 0; i < xml.childNodes.length; i++) {
        var item = xml.childNodes.item(i);
        var nodeName = item.nodeName;
        if (typeof(obj[nodeName]) == "undefined") {
          obj[nodeName] = xmlToJson(item);
        } else {
          if (typeof(obj[nodeName].push) == "undefined") {
            var old = obj[nodeName];
            obj[nodeName] = [];
            obj[nodeName].push(old);
          }
          obj[nodeName].push(xmlToJson(item));
        }
      }
    }
    return obj;
  }


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

    let url =   `http://localhost:8080/WebApplication1/ws/users`;
    let list = document.querySelector(".user-chat-column");
    list.innerHTML="";
    fetch(url, {
      method: "GET"
    })
    .then(response => response.text())
    .then(str => (new window.DOMParser()).parseFromString(str, "text/xml"))
    .then(data => xmlToJson(data))
    .then(function(response){
      for (let element of response.userss.users) {
        console.log(element);
        let { id, password, role, statusCode, username } = element;
        let boxDiv = document.createElement("div");
        let avatarDiv = document.createElement("div");
        let chatInfoDiv = document.createElement("div");
        let idDiv = document.createElement("div");
        let timestampDiv= document.createElement("div");
        let lastmsgDiv = document.createElement("div");
        let usernameSpan = document.createElement("span");
        let icon = document.createElement("i");

        let classlist = ['fa','fa-circle','status'];
        icon.classList.add(...classlist);
        boxDiv.classList.add("user-box");
        avatarDiv.classList.add("avatar");
        chatInfoDiv.classList.add("chat-info");
        idDiv.classList.add("id");
        usernameSpan.classList.add("username");

        switch (parseInt(statusCode)) {
          case 1:
          icon.classList.add('color-online')
          break;
          case 2:
          icon.classList.add('color-idle')
          break;
          case 3:
          icon.classList.add('color-busy');
          break;
          default:
          icon.classList.add('color-invisible');
        }

        let textnode2 = document.createTextNode(username);
        usernameSpan.appendChild(textnode2);
        idDiv.appendChild(usernameSpan);
        idDiv.appendChild(icon);
        chatInfoDiv.appendChild(idDiv);
        chatInfoDiv.appendChild(timestampDiv);
        chatInfoDiv.appendChild(lastmsgDiv);
        boxDiv.appendChild(avatarDiv);
        boxDiv.appendChild(chatInfoDiv);


        list.insertBefore(boxDiv, list.childNodes[0]);

      }
      for (let val of document.getElementsByClassName("user-box")) {
        val.addEventListener("click", function() {
          let element = val.getElementsByTagName("span");
          let usrname =  element[0].textContent;
          let id2;
          document.querySelector(".id-displayer").textContent = usrname;
          console.log(usrname)
          document.querySelector(".status-displayer").style.display = "block";
          document.querySelector(".ann-info").style.display = "none";
          document.querySelector(".message-box").style.display = "block";

          if (window.innerWidth < 1000) {
            document.querySelector(".main").style.transform = " translateX(-100%)";
            document.querySelector(".section-div").style.display = "none";
          }

          for (let element of  response.userss.users){
            if (element.username===usrname) id2 = element.id;
          }
          let url = `http://localhost:8080/WebApplication1/ws/cv?id1=${JSON.parse(localStorage.user).id}&id2=${id2}`;
          fetch(url, {
            method: "GET",
          })
        });
      }
    })
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

    let url =   `http://localhost:8080/WebApplication1/ws/dpm`;
    let list = document.querySelector(".group-chat-column");
    list.innerHTML="";
    fetch(url, {
      method: "GET"
    })
    .then(response => response.text())
    .then(str => (new window.DOMParser()).parseFromString(str, "text/xml"))
    .then(data => xmlToJson(data))
    .then(function(response){
      for (let element of response.departments.department) {
        console.log(element);

        if (JSON.parse(localStorage.user).departmentId.name==='Admin'){
          let { id, name } = element;
          let boxDiv = document.createElement("div");
          let chatInfoDiv = document.createElement("div");
          let idDiv = document.createElement("div");
          let timestampDiv= document.createElement("div");
          let lastmsgDiv = document.createElement("div");
          let usernameSpan = document.createElement("span");


          boxDiv.classList.add("group-box");
          chatInfoDiv.classList.add("chat-info");
          idDiv.classList.add("id");
          usernameSpan.classList.add("groupname");

          let textnode2 = document.createTextNode(name);
          usernameSpan.appendChild(textnode2);
          idDiv.appendChild(usernameSpan);
          chatInfoDiv.appendChild(idDiv);
          chatInfoDiv.appendChild(timestampDiv);
          chatInfoDiv.appendChild(lastmsgDiv);
          boxDiv.appendChild(chatInfoDiv);


          list.insertBefore(boxDiv, list.childNodes[0]);
        } else {
          let { id, name } = element;
          if (JSON.parse(localStorage.user).departmentId.name===name){
            let boxDiv = document.createElement("div");
            let chatInfoDiv = document.createElement("div");
            let idDiv = document.createElement("div");
            let timestampDiv= document.createElement("div");
            let lastmsgDiv = document.createElement("div");
            let usernameSpan = document.createElement("span");


            boxDiv.classList.add("group-box");
            chatInfoDiv.classList.add("chat-info");
            idDiv.classList.add("id");
            usernameSpan.classList.add("groupname");

            let textnode2 = document.createTextNode(name);
            usernameSpan.appendChild(textnode2);
            idDiv.appendChild(usernameSpan);
            chatInfoDiv.appendChild(idDiv);
            chatInfoDiv.appendChild(timestampDiv);
            chatInfoDiv.appendChild(lastmsgDiv);
            boxDiv.appendChild(chatInfoDiv);


            list.insertBefore(boxDiv, list.childNodes[0]);
          }
        }

      }

      for (let val of document.getElementsByClassName("group-box")) {
        val.addEventListener("click", function() {
          document.querySelector(".message-box").innerHTML="";
          let element = val.getElementsByTagName("span");
          let groupname=element[0].textContent;
          document.querySelector(".id-displayer").textContent = groupname;
          let id;
          document.querySelector(".status-displayer").style.display = "none";
          document.querySelector(".ann-info").style.display = "none";
          document.querySelector(".message-box").style.display = "block";

          if (window.innerWidth < 1000) {
            document.querySelector(".main").style.transform = " translateX(-100%)";
            document.querySelector(".section-div").style.display = "none";
          }
          for (let element of  response.departments.department){
            if (element.name===groupname) id = element.id;
          }

          let url = `http://localhost:8080/WebApplication1/ws/msg?id=${id}`;
          fetch(url, {
            method: "GET",
          }).then(response => response.text())
          .then(str => (new window.DOMParser()).parseFromString(str, "text/xml"))
          .then(data => xmlToJson(data))
          .then(function(response) {
            console.log(response)
            for (let element of response.messages.message) {
              console.log(element)
              let {content,isTask,sendtime } = element;
              let senderId= element.senderId.id;
              let val = content;
              let textnode = document.createTextNode(val);
              let classlist;
              let innerDiv = document.createElement("div");
              let outerDiv = document.createElement("div");
              if (senderId===JSON.parse(localStorage.user).id){
                classlist = [
                  "message-div",
                  "new-msg",
                  "msg-send"
                ];
              } else {
                classlist = [
                  "message-div",
                  "new-msg",
                  "msg-receive"
                ];
              }


              innerDiv.classList.add("chat-message");
              outerDiv.classList.add(...classlist);
              innerDiv.appendChild(textnode);
              outerDiv.appendChild(innerDiv);
              document.querySelector(".message-box").appendChild(outerDiv);

              var objDiv = document.getElementById("chat-box");
              objDiv.scrollTop = objDiv.scrollHeight;
            }
          })




          document.querySelector("#inputbox").addEventListener("keypress", function(e) {
            let value = document.getElementById("inputbox").value;
            let url2 = `http://localhost:8080/WebApplication1/ws/msg?dpm_id=${id}&sender_id=${JSON.parse(localStorage.user).id}&content=${value}`;

            var key = e.which || e.keyCode;
            if (key === 13 && value!=='') {
              fetch(url2, {
                method: "POST",
              })
              sendMsg();
            }
          });

          document.querySelector("#send").addEventListener("click", function(e) {
            let value = document.getElementById("inputbox").value;
            let url2 = `http://localhost:8080/WebApplication1/ws/msg?dpm_id=${id}&sender_id=${JSON.parse(localStorage.user).id}&content=${value}`;

            if (value!==''){
              fetch(url2, {
                method: "POST",
              })
              sendMsg();
            }
          });
        });
      }
    })



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

    let list = document.querySelector(".ann-list");
    list.innerHTML="";
    let url = `http://localhost:8080/WebApplication1/ws/announcement`;

    fetch(url, {
      method: "GET",
    })
    .then(response => response.text())
    .then(str => (new window.DOMParser()).parseFromString(str, "text/xml"))
    .then(data => xmlToJson(data))
    .then(function(response) {
      for (let element of response.announcements.announcement) {
        let { creatorId, description, id , title } = element;
        console.log(description);
        console.log(title);

        let boxDiv = document.createElement("div");
        let titleDiv = document.createElement("div");
        let introDiv = document.createElement("div");
        boxDiv.classList.add("ann-box");
        titleDiv.classList.add("ann-title");
        introDiv.classList.add("ann-intro");
        let textnode1 = document.createTextNode(title);
        let textnode2 = document.createTextNode(description);
        titleDiv.appendChild(textnode1);
        introDiv.appendChild(textnode2);
        boxDiv.appendChild(titleDiv);
        boxDiv.appendChild(introDiv);


        list.insertBefore(boxDiv, list.childNodes[0]);

      }})
    });

    document.querySelector(".back-button").addEventListener("click", function() {
      document.querySelector(".main").style.transform = "translateX(0)";
      document.querySelector(".section-div").style.display = "block";
    });




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

    function openTaskContainer() {
      document.getElementById("backdrop").style.display = "block";
      document.querySelector(".wrap-div").style.display = "flex";
      document.querySelector(".wrap-div").style.opacity = "1";
      document.querySelector(".new-task-container").style.display = "block";
    }

    function closeTaskContainer() {
      document.getElementById("backdrop").style.display = "none";
      document.querySelector(".wrap-div").style.display = "none";
      document.querySelector(".wrap-div").style.opacity = "0";
      document.querySelector(".new-task-container").style.display = "none";
    }

    function sendMsg() {
      let val = document.getElementById("inputbox").value;
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
      document.querySelector(".message-box").appendChild(outerDiv);

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

    document.querySelector(".new-task-container").addEventListener("click", function(event) {
      if (
        event.target === document.getElementById("close2") ||
        event.target === document.getElementById("close2").childNodes[0]
      )
      closeTaskContainer();
    });

    document
    .querySelector(".new-ann-container")
    .addEventListener("click", function(event) {
      console.log(event.target);
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
          document.querySelector(".ann-input-title").value = "";
          document.getElementById("ann-textarea").value = "";

          let url =
          `http://localhost:8080/WebApplication1/ws/announcement?title=${annTitle}&description=${annTextarea}&creator_id=${JSON.parse(localStorage.user).id}`;


          fetch(url, {
            method: "POST"
          })
        }
      }
    });

    document.querySelector('.input-box').addEventListener("click", function(event) {
      console.log(event.target);
      if (
        event.target === document.getElementById("task") ||
        event.target === document.getElementById("task").childNodes[0] ||
        event.target === document.getElementById("task").parentElement
      )
      openTaskContainer();
    });

    // document.querySelector("#inputbox").addEventListener("keypress", function(e) {
    //   var key = e.which || e.keyCode;
    //   if (key === 13) {
    //     sendMsg();
    //   }
    // });
    //
    // document.querySelector("#send").addEventListener("click", function(e) {
    //   sendMsg();
    // });

    document
    .querySelector(".ann-list")
    .addEventListener("click", function(event) {
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

    var statusArray =  document
    .querySelectorAll("#status-dropdown-content a");
    var status_code;
    for (let element of statusArray){
      element.addEventListener("click", function(event) {
        let text;
        if (event.target.tagName === "A") {
          text=event.target.childNodes[3].textContent;
        } else {
          text=event.target.parentElement.childNodes[3].textContent;
        }

        switch (text) {
          case "Online":
          document.querySelector('#status-dropdown .avatar svg').style.color = '#1bd139';
          status_code=1;
          break;
          case "Idle":
          document.querySelector('#status-dropdown .avatar svg').style.color = '#ccb80c';
          status_code=2;
          break;
          case "Busy":
          document.querySelector('#status-dropdown .avatar svg').style.color = '#cc0c0c';
          status_code=3;
          break;
          default:
          document.querySelector('#status-dropdown .avatar svg').style.color = '#adbabc';
          status_code=4;

        }
        let url =   `http://localhost:8080/WebApplication1/ws/users/${JSON.parse(localStorage.user).id}/${status_code}`;

        fetch(url, {
          method: "PUT"
        })
      })
    }

  });
