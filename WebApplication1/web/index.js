document.addEventListener("DOMContentLoaded", function(event) {

  var data = {};

  document.getElementById('form').addEventListener('input',function(){
    data.username=document.getElementById('username').value;
    data.password=document.getElementById('password').value;
  })

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


  var submit = function(){
    let  url = "http://10.114.32.77:8080/WebApplication1/ws/users?username="+data.username+"&password=" +data.password;
    fetch(url, {
      method: "POST",
      cache: 'no-cache'
    })
    .then(response => response.text())
    .then(str => (new window.DOMParser()).parseFromString(str, "text/xml"))
    .then(data => xmlToJson(data))
    // .then (response => console.log(response))
    .then(
      function(result){
        console.log(result)
        if (Object.keys(result.userss).length ===0){
          alert('Wrong username or password');
        }{
          localStorage.setItem("user", JSON.stringify(result.userss.users))
            if(JSON.parse(localStorage.user).departmentId.name==='Admin'){
              window.location.replace("http://10.114.32.77:8080/WebApplication1/MainPage.html")
            } else {
              window.location.replace("http://10.114.32.77:8080/WebApplication1/UserPage.html")
            }
        }
      }
    )
  }

  document.getElementById('submit').addEventListener('click',function(){
    submit();
  });


  document.addEventListener("keypress", function(e) {
    var key = e.which || e.keyCode;
    if (key === 13) {
      submit();
    }
  });

})
