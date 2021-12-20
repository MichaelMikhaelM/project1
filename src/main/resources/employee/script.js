console.log("redirected 01");

//import {ll} from '../global.js';
//console.log(ll)
//const {data} = require('../global.js');
//console.log(num);

window.onload= async function(){

    let btnGetReimb = document.getElementById("btn-getReimb");
    let btnCreateReimb = document.getElementById("btn-createReimb");
    let logoutBtn = document.getElementById("logout");

    //console.log(btn);
    let uname = await checkSession();
    console.log(uname);
    let userInfo = await getUserInfo(uname);
    //console.log(userInfo);
    let uID = userInfo[0].user_id;
    console.log(userInfo[0].user_id);
     if(userInfo[0].role_id == 1)
        {
            window.location.href = "../manager"
        }



    btnGetReimb.addEventListener("click", async function(){
        let data = await getAllReimbursments(userInfo[0].user_id);

        console.log(data);
        populateReimbursment(data);
    });

    btnCreateReimb.addEventListener("click", async function(){
            //userInfo[0].user_id;
            inputReimbData(uID);

        });
     logoutBtn.addEventListener("click", async function(){
                let data = await logout();

                window.location.replace("http://localhost:9000");
            });

}
async function logout()
{
    let response = await fetch("http://localhost:9000/logout");
    let data = await response;

    return data;
}

async function inputReimbData(uID)
{
console.log(uID);
 let createReimbElem = document.createElement("div");
 createReimbElem.className = "options-container";

 let dropdownElem = document.createElement("div");
 dropdownElem.className = "dropdown";

 let dropdownBtnElem = document.createElement("button");

 dropdownBtnElem.className = "btn btn-secondary dropdown-toggle";
 dropdownBtnElem.id="dropdownMenuButton1";
 dropdownBtnElem.setAttribute('data-bs-toggle','dropdown');
 dropdownBtnElem.setAttribute('aria-expanded','false');
 dropdownBtnElem.innerHTML = "Reimbursment Type";

 let dropDownMenuElem = document.createElement("ul");
 dropDownMenuElem.className = "dropdown-menu";
 dropDownMenuElem.setAttribute('aria-labelledby','dropdownMenuButton1');

 let dropDnMenuItemsElem = document.createElement("li");

 let item1Elem = document.createElement("a");
 item1Elem.className = "dropdown-item";
 item1Elem.id = "lodging";
 item1Elem.innerHTML = "LODGING";

 let item2Elem = document.createElement("a");
 item2Elem.className = "dropdown-item";
 item2Elem.id = "travel";
 item2Elem.innerHTML = "TRAVEL";

 let item3Elem = document.createElement("a");
 item3Elem.className = "dropdown-item";
 item3Elem.id = "food";
 item3Elem.innerHTML = "FOOD";

 let item4Elem = document.createElement("a");
 item4Elem.className = "dropdown-item";
 item4Elem.id = "other";
 item4Elem.innerHTML = "OTHER";

 let inAmountElem = document.createElement("div");
 inAmountElem.className = "options-container";
 inAmountElem.innerHTML = "Amount";

 let inputAmountElem = document.createElement("input");
 inputAmountElem.setAttribute('type','text');
 inputAmountElem.id = "amount_id";

 let inDescrElem = document.createElement("div");
 inDescrElem.className = "options-container";
 inDescrElem.innerHTML = "Description";

 let inputDescriptionElem = document.createElement("input");
 inputDescriptionElem.setAttribute('type','text');
 inputDescriptionElem.id = "descr_id";

 let submitBtnElem = document.createElement("button");
 submitBtnElem.className = "btn btn-dark";
 submitBtnElem.innerHTML = "Submit";
 submitBtnElem.id = "submitBtn";


 dropdownElem.appendChild(dropdownBtnElem);
 dropdownElem.appendChild(dropDownMenuElem);
 //--------------------------------
 dropDownMenuElem.appendChild(dropDnMenuItemsElem);
 dropDnMenuItemsElem.appendChild(item1Elem);
 dropDnMenuItemsElem.appendChild(item2Elem);
 dropDnMenuItemsElem.appendChild(item3Elem);
 dropDnMenuItemsElem.appendChild(item4Elem);

 inAmountElem.appendChild(inputAmountElem);
 inDescrElem.appendChild(inputDescriptionElem);

 createReimbElem.appendChild(dropdownElem);
 document.body.appendChild(createReimbElem);
 document.body.appendChild(inAmountElem);
 document.body.appendChild(inDescrElem);
 document.body.appendChild(submitBtnElem);


let lodging = document.getElementById("lodging");
let travel = document.getElementById("travel");
let food = document.getElementById("food");
let other = document.getElementById("other");

let reimbType = 0;

lodging.addEventListener("click", async function(){
           let txtl = document.getElementById("dropdownMenuButton1");
           txtl.innerHTML = "LODGING";
           reimbType = 1;
                    });
travel.addEventListener("click", async function(){
           let txtt = document.getElementById("dropdownMenuButton1");
           txtt.innerHTML = "TRAVEL";
           reimbType = 2;
                    });
food.addEventListener("click", async function(){
           let txtf = document.getElementById("dropdownMenuButton1");
           txtf.innerHTML = "FOOD";
           reimbType = 3;
                    });
other.addEventListener("click", async function(){
           let txto = document.getElementById("dropdownMenuButton1");
           txto.innerHTML = "OTHER";
           reimbType = 4;
                    });
 //console.log(lodging);

const amount = document.getElementById("amount_id");
const description = document.getElementById("descr_id");
//console.log(amount);

let sBtn = document.getElementById("submitBtn");

sBtn.addEventListener("click", async function()
{
               await fetch(`http://localhost:9000/reimbursments/${uID}`,
                {

                       method: "POST",
                       body: JSON.stringify
                       ({
                            "amount": amount.value,
                            "description": description.value,
                            "reimb_stat_type": reimbType
                       })

                  })
location.reload();
});

}


async function checkSession()
{
    let response = await fetch("http://localhost:9000/check-session");
    let data = await response.text();

    return data;
    //console.log(data)

}

async function getAllReimbursments(usrid)
{
    let response = await fetch(`http://localhost:9000/reimbursments/${usrid}`);
    let data = await response.json();

    return data;
}

//let reimbContainerElem = document.getElementById("reimb-container");

async function getUserInfo(usrname)
{
    let response = await fetch(`http://localhost:9000/user/${usrname}`);
    let data = await response.json();
    //console.log(data[0].email);
    return data;

}

async function populateReimbursment(Reimbursment)
{

    /*

        <div class="reimb-container" id="reimb-container">

                 <div>Reimbursment ID:</div>
                 <br>
                 <div>Issued By:</div>
                 <br>
                 <div>Type:</div>
                 <br>
                 <div>Status:</div>
                 <br>
                 <button class="btn btn-primary">Approve</button>
                 <br>
                 <button class="btn btn-primary">Deny</button>

             </div>

        */


    Reimbursment.forEach(async ReimbItem => {
    console.log(ReimbItem);

    let reimbElem = document.createElement("div");
    reimbElem.className = "reimb-container";
    reimbElem.id = "reimb-container";
    reimbElem.innerHTML  = `Reimbursment ID: ${ReimbItem.reimb_id}`;
    //console.log(getUsernameById(ReimbItem.author));

    let ReimbIssuerElem = document.createElement("div");

    /*
    let x = await getUsernameById(ReimbItem.author);

    ReimbIssuerElem.innerHTML  =  `Issued By: ${x}`;

    */

    let ReimbDescrElem = document.createElement("div");
    ReimbDescrElem.innerHTML  = `Description: ${ReimbItem.description}`;

    let ReimbAmountElem = document.createElement("div");
    ReimbAmountElem.innerHTML  = `Amount: ${ReimbItem.amount}`;

    let ReimbStatusElem = document.createElement("div");
    let stat = await getStat(ReimbItem.reimb_stat_id);
    ReimbStatusElem.innerHTML  = `Status: ${stat}`;

    let ResolverElem = document.createElement("div");
    let resolver = await getUsernameById(ReimbItem.resolver);
    ResolverElem.innerHTML  = `Resolved By: ${resolver}`;



    document.body.appendChild(reimbElem);
    //reimbElem.appendChild(ReimbIssuerElem);
    reimbElem.appendChild(ReimbDescrElem);
    reimbElem.appendChild(ReimbAmountElem);
    reimbElem.appendChild(ReimbStatusElem);
    reimbElem.appendChild(ResolverElem);




    });

async function getUsernameById(id)
{
    let response = await fetch(`http://localhost:9000/users/${id}`);
    let data = await response.text();
    //console.log(data);
    return data;
}
async function getStat(id)
{
    let response = await fetch(`http://localhost:9000/reimbstat/${id}`);

    let data = await response.text();

    return data;

}





}


