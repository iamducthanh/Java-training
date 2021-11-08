document.querySelector("#formInput").addEventListener('submit', batchInput, true)
let MAX_LENGHT_FORMDATA = 5;
let checkboxSingles = document.getElementsByClassName('checkboxSingle');

function resetInput(e){
    e.value = '';
    console.log(
        'on reset'
    )
}

function batchInput(event){
    event.preventDefault();
    let formData = $("#formInput").serializeArray();
    console.log(formData)
    let checkEmpty = false;
    for(let i=0;i<formData.length;i++){
        if(formData[i].value.length != 0){
            checkEmpty = true;
            break;
        }
    }
    if(checkEmpty){
        let rowTable = document.getElementsByClassName('rowTable');
        for(let i=0;i<rowTable.length;i++){
                for(let j=0;j<=MAX_LENGHT_FORMDATA;j++){
                    if(formData[j].name == 'sex'){
                        if(formData[j].value == '1'){
                            document.getElementsByClassName('male')[i].checked = true;
                        } else if(formData[j].value == '0'){
                            document.getElementsByClassName('female')[i].checked = true;
                        }
                    }
                    else {
                        document.getElementsByClassName(formData[j].name)[i].value = formData[j].value
                    }
                }
                rowTable[i].setAttribute('status','on');
        }
    }
}

let rowTable = document.getElementsByClassName('rowTable')[0];
let bodytable = document.getElementById('bodytable');

for(let i=1;i<10;i++){
    let tr = document.createElement('tr');
    tr.innerHTML = rowTable.innerHTML;
    tr.getElementsByClassName('noRow')[0].innerHTML = i + 1;
    tr.getElementsByClassName('sexRdo')[0].name = 'sex' + i;
    tr.getElementsByClassName('sexRdo')[1].name = 'sex' + i;
    tr.id = "row" + i;
    tr.className = 'rowTable';
    tr.setAttribute('status','off');
    bodytable.appendChild(tr);
}

let inputTable = document.getElementsByClassName('inputTable');
for(let i=0;i<inputTable.length;i++){
    inputTable[i].onclick = resetInput.bind(this, inputTable[i]);
    console.log('add reset')
}

function checkBoxAll(){
    let checkboxAll = document.getElementsByClassName('checkboxAll')[0];
    if(checkboxAll.checked == false){
        for(let i=0;i<checkboxSingles.length;i++){
            checkboxSingles[i].checked = false;
        }
        document.getElementById('btnDelete').style.display = 'none'
    } else {
        for(let i=0;i<checkboxSingles.length;i++){
            checkboxSingles[i].checked = true;
        }
        document.getElementById('btnDelete').style.display = 'unset'
    }
}

function onCheckboxChecked(){
    if(!checkCheckboxChecked()){
        document.getElementById('btnDelete').style.display = 'none';
        document.getElementsByClassName('checkboxAll')[0].checked = false;
    } else {
        document.getElementById('btnDelete').style.display = 'unset';
        
    }
}

function checkCheckboxChecked(){
    for(let i=0;i<checkboxSingles.length;i++){
        if(checkboxSingles[i].checked == true){
            return true;
        }
    }
    return false;
}

function deleteRowSelected(){
    for(let i=0;i<checkboxSingles.length;i++){
        if(checkboxSingles[i].checked == true){
            console.log(i);
        }
    }
}




