document.querySelector("#formInput").addEventListener('submit', signup, true)

function signup(event){
    event.preventDefault();
    console.log('done');
    let formData = $("#formInput").serializeArray();
    console.log(formData)
}

let rowTable = document.getElementsByClassName('rowTable')[0];
let bodytable = document.getElementById('bodytable');

for(let i=2;i<11;i++){
    let tr = document.createElement('tr');
    tr.innerHTML = rowTable.innerHTML;
    tr.getElementsByClassName('noRow')[0].innerHTML = i;
    tr.id = "row" + i;
    tr.setAttribute('status','off');
    bodytable.appendChild(tr);
}