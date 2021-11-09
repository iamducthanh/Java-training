/*
Ngày viết : 08/11/2021
Tác giả : GMO_ThanhND
*/

function checkInputList(){
    let rowTable = $(".rowTable");
    for(let i=0;i<rowTable.length;i++){
        // console.log(rowTable[0].childNodes);
        let classVal = rowTable[i].childNodes[5].childNodes[1].value;
        let fullname = rowTable[i].childNodes[7].childNodes[1].value;
        let dateOfBirth = rowTable[i].childNodes[9].childNodes[1].value;
        let phone = rowTable[i].childNodes[13].childNodes[1].value;
        let note = rowTable[i].childNodes[15].childNodes[1].value;
        let sex = '';
        if(rowTable[i].childNodes[11].childNodes[1].checked){
            sex = '1';
        } else if(rowTable[i].childNodes[11].childNodes[3].checked){
            sex = '0';
        }

        if(classVal == '' && fullname == '' && dateOfBirth == '' && phone == '' && note == '' && sex == ''){
            
        } else {
            if(fullname.trim().length == 0){
                rowTable[i].childNodes[7].className = 'tags';
                rowTable[i].childNodes[7].setAttribute("gloss", C_00_002.replace('{1}', 'Full name'));
                rowTable[i].childNodes[7].childNodes[1].style.border = '1px solid red';
            }
            if(phone.trim().length == 0){
                rowTable[i].childNodes[13].className = 'tags';
                rowTable[i].childNodes[13].setAttribute("gloss", C_00_002.replace('{1}', 'Phone'));
                rowTable[i].childNodes[13].childNodes[1].style.border = '1px solid red';
            }
            if(phone.trim().length == 0){
                rowTable[i].childNodes[9].className = 'tags';
                rowTable[i].childNodes[9].setAttribute("gloss", C_00_002.replace('{1}', 'Date of birth'));
                rowTable[i].childNodes[9].childNodes[1].style.border = '1px solid red';
            }
            if(sex.trim().length == 0){
                rowTable[i].childNodes[11].className = 'tags';
                rowTable[i].childNodes[11].setAttribute("gloss", C_00_002.replace('{1}', 'Sex'));
                rowTable[i].childNodes[11].childNodes[1].childNodes[1].style.color = 'red';
                // rowTable[i].childNodes[11].childNodes[1].childNodes[1].style.border = '1px solid red';
            }
        }

        // console.log(rowTable[i].childNodes[11].childNodes[1].checked);
        // console.log(rowTable[i].childNodes[11].childNodes[3].checked);

        // console.log(classVal);
        // console.log(fullname);
        // console.log(dateOfBirth);
        // console.log(phone);
        // console.log(note);
        // console.log(sex);

    }
}

