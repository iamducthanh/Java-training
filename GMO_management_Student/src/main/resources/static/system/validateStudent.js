/*
Ngày viết : 08/11/2021
Tác giả : GMO_ThanhND
*/


const indexControls = [5,7,9,13,15,11];

// Duyệt bảng và kiểm tra dữ liệu đầu vào trên từng dòng đã có ít nhất 1 trường dữ liệu
function checkInputList(){
    let rowTable = $(".rowTable");
    for(let i=0;i<rowTable.length;i++){
        let classVal = rowTable[i].childNodes[5].childNodes[1].value;
        let fullname = rowTable[i].childNodes[7].childNodes[1].value;
        let dateOfBirth = rowTable[i].childNodes[9].childNodes[1].value;
        console.log(dateOfBirth);
        let phone = rowTable[i].childNodes[13].childNodes[1].value;
        let note = rowTable[i].childNodes[15].childNodes[1].value;
        let sex = '';
        if(rowTable[i].childNodes[11].childNodes[1].checked){
            sex = '1';
        } else if(rowTable[i].childNodes[11].childNodes[3].checked){
            sex = '0';
        }
        
        if(classVal == '' && fullname == '' && dateOfBirth == '' && phone == '' && note == '' && sex == ''){
            hiddenMessageInRow(rowTable[i], 1);
        } else {
            if(fullname.trim().length == 0){
                showErrorMessage(rowTable[i], 7, 1, C_00_002, 'Full name');
            } else {
                hiddenMessage(rowTable[i], 7, 1);
            }
            if(phone.trim().length == 0){
                showErrorMessage(rowTable[i], 13, 1, C_00_002, 'Phone');
            } else {
                hiddenMessage(rowTable[i], 13, 1);
            }
            if(dateOfBirth.trim().length == 0){
                showErrorMessage(rowTable[i], 9, 1, C_00_002, 'Date of birth');
            } else {
                hiddenMessage(rowTable[i], 9, 1);
            }
            if(sex.trim().length == 0){
                showErrorMessage(rowTable[i], 11, 1, C_00_002, 'Sex');
                rowTable[i].childNodes[11].style.color = 'red';
            } else {
                hiddenMessage(rowTable[i], 11, 1);
                rowTable[i].childNodes[11].style.color = 'black';
            }
            if(classVal.trim().length == 0){
                showErrorMessage(rowTable[i], 5, 1, C_00_002, 'Class');
            } else {
                hiddenMessage(rowTable[i], 5, 1);
            }
        }

    }
}

// @param rowTable: 1 dòng trên bảng
// @param j: Vị trí của 1 cột của 1 dòng
// @param k: Vị trí của một phần tử con trên 1 cột của 1 dòng
// @param messageCode: mã message
// @param messageParam: tham số cho message
function showErrorMessage(rowTable, j, k, messageCode, messageParam){
    rowTable.childNodes[j].className = 'tags';
    rowTable.childNodes[j].setAttribute("gloss", messageCode.replace('{1}', messageParam));
    rowTable.childNodes[j].childNodes[k].style.border = '1px solid red';
}

// @param rowTable: 1 dòng trên bảng
// @param j: Vị trí của 1 cột của 1 dòng
// @param k: Vị trí của một phần tử con trên 1 cột của 1 dòng
// Ẩn thông báo tại trường dữ liệu
function hiddenMessage(rowTable, j, k){
    rowTable.childNodes[j].className = '';
    rowTable.childNodes[j].removeAttribute("gloss");
    rowTable.childNodes[j].childNodes[k].style.border = '1px solid #d1d3e2';
}

// @param rowTable: 1 dòng trên bảng
// @param k: Vị trí của một phần tử con trên 1 cột của 1 dòng
// Ẩn thông báo tại dòng không có dữ liệu
function hiddenMessageInRow(rowTable, k){
    for(let i=0;i<indexControls.length;i++){
        rowTable.childNodes[indexControls[i]].className = '';
        rowTable.childNodes[indexControls[i]].removeAttribute("gloss");
        rowTable.childNodes[indexControls[i]].childNodes[k].style.border = '1px solid #d1d3e2';
        rowTable.childNodes[indexControls[i]].style.color = 'black';

    }
}

