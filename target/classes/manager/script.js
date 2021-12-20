console.log("redirected 01");

//import {ll} from '../global.js';
//console.log(ll)
//const {data} = require('../global.js');
//console.log(num);
let userID = 0;
let filter=0;
let filtering = false;


window.onload= async function(){

    let btn = document.getElementById("btn-getReimb");
    let logoutBtn = document.getElementById("logout");

    //console.log(btn);

    let uname = await checkSession();
    let userInfo = await getUserInfo(uname);
    userID = userInfo[0].user_id;
    console.log(userInfo);
    if(userInfo[0].role_id == 2)
    {
        window.location.href = "../employee"
    }

        //console.log(menu);
    let pending = document.getElementById("pending");
    let approved = document.getElementById("approved");
    let denied = document.getElementById("denied");
        //console.log(denied);
    pending.addEventListener("click", async function(){
           let txtp = document.getElementById("dropdownMenuButton1");
           txtp.innerHTML = "Pending";
           filter = 1;
           filtering = true;
                    });
    approved.addEventListener("click", async function(){
           let txta = document.getElementById("dropdownMenuButton1");
           txta.innerHTML = "Approved";
           filter = 2;
           filtering = true;
                    });
     denied.addEventListener("click", async function(){
           let txtd = document.getElementById("dropdownMenuButton1");
           txtd.innerHTML = "Denied";
           filter = 3;
           filtering = true;
                    });

    btn.addEventListener("click", async function(){
        let data = await getAllReimbursments();


       //console.log(data);
        populateReimbursment(data);
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
async function checkSession()
{
    let response = await fetch("http://localhost:9000/check-session");
    let data = await response.text();

    return data;
    //console.log(data)

}
async function getUserInfo(usrname)
{
    let response = await fetch(`http://localhost:9000/user/${usrname}`);
    let data = await response.json();
    //console.log(data[0].email);
    return data;

}

async function getAllReimbursments()
{
    let response = await fetch("http://localhost:9000/reimbursments");
    let data = await response.json();

    return data;
}

//let reimbContainerElem = document.getElementById("reimb-container");



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


    Reimbursment.forEach(async ReimbItem =>
    {
    //console.log(ReimbItem);

            if(ReimbItem.reimb_stat_id == filter)
            {

                        let reimbElem = document.createElement("div");
                        reimbElem.className = "reimb-container";
                        reimbElem.id = "reimb-container";
                        reimbElem.innerHTML  = `Reimbursment ID: ${ReimbItem.reimb_id}`;
                        //console.log(getUsernameById(ReimbItem.author));
                        //reimbElem.parentNode.removeChild(reimbElem);

                        let ReimbIssuerElem = document.createElement("div");

                        let x = await getUsernameById(ReimbItem.author);

                        ReimbIssuerElem.innerHTML  =  `Issued By: ${x}`;

                        let ReimbDescrElem = document.createElement("div");
                        ReimbDescrElem.innerHTML  = `Description: ${ReimbItem.description}`;

                        let ReimbAmountElem = document.createElement("div");
                        ReimbAmountElem.innerHTML  = `Amount: ${ReimbItem.amount}`;

                        let ReimbStatElem = document.createElement("div");
                        let stat = await getStat(ReimbItem.reimb_stat_id);
                        ReimbStatElem.innerHTML  = `Status: ${stat}`;

                        let approveBtn = document.createElement("button");
                        approveBtn.className = "btn btn-primary";
                        approveBtn.id = `approve${ReimbItem.reimb_id}`
                        approveBtn.innerText = "Approve";

                        let denyBtn = document.createElement("button");
                        denyBtn.className = "btn btn-primary";
                        approveBtn.id = `deny${ReimbItem.reimb_id}`
                        denyBtn.innerText = "Deny";

                        document.body.appendChild(reimbElem);
                        reimbElem.appendChild(ReimbIssuerElem);
                        reimbElem.appendChild(ReimbDescrElem);
                        reimbElem.appendChild(ReimbAmountElem);
                        reimbElem.appendChild(ReimbStatElem);
                        if(ReimbItem.reimb_stat_id==1)
                        {
                            reimbElem.appendChild(approveBtn);
                            reimbElem.appendChild(denyBtn);
                        }

                          approveBtn.addEventListener("click", async function(){
                                let data = await approveReimb(ReimbItem.reimb_id,userID);
                                approveBtn.disabled=true;
                                denyBtn.disabled=true;
                            });
                          denyBtn.addEventListener("click", async function(){
                                let data = await denyReimb(ReimbItem.reimb_id,userID);
                                approveBtn.disabled=true;
                                denyBtn.disabled=true;
                            });
            }
            if(filtering==false)
            {



                let reimbElem = document.createElement("div");
                reimbElem.className = "reimb-container";
                reimbElem.id = "reimb-container";
                reimbElem.innerHTML  = `Reimbursment ID: ${ReimbItem.reimb_id}`;
                                        //console.log(getUsernameById(ReimbItem.author));

                let ReimbIssuerElem = document.createElement("div");

                let x = await getUsernameById(ReimbItem.author);

                ReimbIssuerElem.innerHTML  =  `Issued By: ${x}`;

                let ReimbDescrElem = document.createElement("div");
                ReimbDescrElem.innerHTML  = `Description: ${ReimbItem.description}`;

                let ReimbAmountElem = document.createElement("div");
                ReimbAmountElem.innerHTML  = `Amount: ${ReimbItem.amount}`;

                let ReimbStatElem = document.createElement("div");
                let stat = await getStat(ReimbItem.reimb_stat_id);
                ReimbStatElem.innerHTML  = `Status: ${stat}`;

                let approveBtn = document.createElement("button");
                approveBtn.className = "btn btn-primary";
                approveBtn.id = `approve${ReimbItem.reimb_id}`
                approveBtn.innerText = "Approve";

                let denyBtn = document.createElement("button");
                denyBtn.className = "btn btn-primary";
                approveBtn.id = `deny${ReimbItem.reimb_id}`
                denyBtn.innerText = "Deny";

                document.body.appendChild(reimbElem);
                reimbElem.appendChild(ReimbIssuerElem);
                reimbElem.appendChild(ReimbDescrElem);
                reimbElem.appendChild(ReimbAmountElem);
                reimbElem.appendChild(ReimbStatElem);
                                        if(ReimbItem.reimb_stat_id==1)
                                        {
                                            reimbElem.appendChild(approveBtn);
                                            reimbElem.appendChild(denyBtn);
                                        }

                                          approveBtn.addEventListener("click", async function(){
                                                let data = await approveReimb(ReimbItem.reimb_id,userID);
                                                approveBtn.disabled=true;
                                                denyBtn.disabled=true;

                                            });
                                          denyBtn.addEventListener("click", async function(){
                                                let data = await denyReimb(ReimbItem.reimb_id,userID);
                                                approveBtn.disabled=true;
                                                denyBtn.disabled=true;
                                            });
            }





    });

async function getUsernameById(id)
{
    let response = await fetch(`http://localhost:9000/users/${id}`);
    let data = await response.text();
    console.log(data);
    return data;
}

async function approveReimb(reimb_id,reslv)
{

    //let bdeny = document.getElementById("");
    let response = await fetch(`http://localhost:9000/reimbursments/approve/${reimb_id}/${reslv}`,{
    method:'PATCH'
    });
}

async function denyReimb(reimb_id,reslv)
{
    let response = await fetch(`http://localhost:9000/reimbursments/deny/${reimb_id}/${reslv}`,{
    method:'PATCH'
    });
}

async function getStat(id)
{
    let response = await fetch(`http://localhost:9000/reimbstat/${id}`);

    let data = await response.text();

    return data;

}


}


