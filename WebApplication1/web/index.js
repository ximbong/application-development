document.addEventListener("DOMContentLoaded", function(event) {

  var data = {};

  document.getElementById('form').addEventListener('input',function(){
    data.username=document.getElementById('username').value;
    data.password=document.getElementById('password').value;
  })


  var submit = function(){
    let  url = "http://localhost:8080/WebApplication1/ws/users?username="+data.username+"&password=" +data.password;
    fetch(url, {
      method: "POST",
      cache: 'no-cache'
    }).then(response => response.json())
    .then(result => (result.length===0)? alert('Wrong username or password.'):(
      window.location.replace("http://localhost:8080/WebApplication1/MainPage.html"),
      localStorage.setItem("user", JSON.stringify(result[0]))
    ))
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
