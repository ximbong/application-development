 document.addEventListener("DOMContentLoaded", function(event) {
0



var data = {};

document.getElementById('form').addEventListener('input',function(){
  data.username=document.getElementById('username').value;
  data.password=document.getElementById('password').value;
})

document.getElementById('submit').addEventListener('click',function(){
    console.log(data);
  var url = "http://localhost:8080/WebApplication1/ws/users?username="+data.username+"&password=" +data.password;
  fetch(url, {
    method: "POST"
  }).then(response => response.json())
     .then(result => (result.length===0)?alert('Wrong username or password.'): window.location.replace("http://localhost:8080/WebApplication1/MainPage.html"))
      .then(result => localStorage.setItem("user", result[0]))
})


  });
