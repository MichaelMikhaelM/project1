const loginForm = document.getElementById("form_id");
const loginButton = document.getElementById("submit");


loginButton.addEventListener("click", (e) => {
    e.preventDefault();
    getresponse();

})



async function getresponse(){
     const username = loginForm.username.value;
     const password = loginForm.password.value;

    let response = await fetch(`http://localhost:9000/login?username=${username}&user_password=${password}`,{
    method:'GET'
    });
    let data = await response;
    console.log(response);

     //export ={data};



    window.open(response.url,"_self");
}
let ll=23;
export {ll};