/*
Ngày viết : 08/11/2021
Tác giả : GMO_ThanhND
*/

// các ô có chưa dữ liệu
const indexControls = [5,7,9,13,15,11];
let formatFullname = null;
let formatDate = /^\d{4}[\/\-](0?[1-9]|1[012])[\/\-](0?[1-9]|[12][0-9]|3[01])$/;
let formatPhone = null;
let errorMessage = new Array();
let studentsRegister = [];

// Duyệt bảng và kiểm tra dữ liệu đầu vào trên từng dòng đã có ít nhất 1 trường dữ liệu
function checkInputList(){
    studentsRegister.length = 0;
    // getAllStudentRegister();
    errorMessage.length = 0;
    let rowTable = $(".rowTable");
    for(let i=0;i<rowTable.length;i++){
        formatPhone = /((09|03|07|08|05)+([0-9]{8})\b)/g;
        formatFullname = /[ `!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?~]/;
        // lấy giá trị trên từng dòng
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

        studentsRegister.push({
            classId: classVal,
            fullname: fullname,
            dateOfBirth: dateOfBirth,
            phone: phone,
            note: note,
            sex: sex
        })
        
        // kiểm tra các giá trị trên 1 dòng phải có 1 trường được nhập
        if(classVal == '' && fullname == '' && dateOfBirth == '' && phone == '' && note == '' && sex == ''){
            hiddenMessageInRow(rowTable[i], 1); // tắt thông báo nếu dòng đó chưa được sử dụng
        } else {
            // check full name
            if(fullname.trim().length == 0){
                addMessage(rowTable[i], 7, 1, C_00_002, 'Full name');
            } else if(fullname.trim().length > 25) {
                addMessage(rowTable[i], 7, 1, C_00_004, '');
            } else if(formatFullname.test(fullname)){
                addMessage(rowTable[i], 7, 1, C_00_003, 'Full name');
            } else {
                hiddenMessage(rowTable[i], 7, 1);
            }

            // check phone
            if(phone.trim().length == 0){
                addMessage(rowTable[i], 13, 1, C_00_002, 'Phone');
            } else if(!formatPhone.test(phone)){
                addMessage(rowTable[i], 13, 1, C_00_007, '');
            } else {
                hiddenMessage(rowTable[i], 13, 1);
            }

            // check date of birth
            if(dateOfBirth.trim().length == 0){
                addMessage(rowTable[i], 9, 1, C_00_002, 'Date of birth');
            } else if(!formatDate.test(dateOfBirth)){
                addMessage(rowTable[i], 9, 1, C_00_005, 'Date of birth');
            } else if(fullname.trim().length <= 25 && fullname.trim().length > 0){
                checkStudentRegisterConstant(rowTable[i], i, fullname, dateOfBirth);
            } else {
                hiddenMessage(rowTable[i], 9, 1);
            }

            // check sex
            if(sex.trim().length == 0){
                addMessage(rowTable[i], 11, 1, C_00_002, 'Sex');
                rowTable[i].childNodes[11].style.color = 'red';
            } else if (sex != '0' && sex != '1'){
                addMessage(rowTable[i], 11, 1, C_00_006, '');
                rowTable[i].childNodes[11].style.color = 'red';
            } else {
                hiddenMessage(rowTable[i], 11, 1);
                rowTable[i].childNodes[11].style.color = 'black';
            }

            // check class
            if(classVal.trim().length == 0){
                addMessage(rowTable[i], 5, 1, C_00_002, 'Class');
            } else {
                hiddenMessage(rowTable[i], 5, 1);
            }
        }
    }
    showMessage();
}

function showMessage(){
    let messageDiv = document.getElementById('message');

    messageDiv.innerHTML = '';
    for(let i=0;i<errorMessage.length;i++){
        messageDiv.innerHTML +=
            "<div class=\"alert alert-danger\" role=\"alert\">"+
            errorMessage[i] +
            "</div>";
    }
}

// Mô tả: Thêm thông báo tương ứng với lỗi
// @param rowTable: 1 dòng trên bảng
// @param j: Vị trí của 1 cột của 1 dòng
// @param k: Vị trí của một phần tử con trên 1 cột của 1 dòng
// @param messageCode: mã message
// @param messageParam: tham số cho message
function addMessage(rowTable, j, k, messageCode, param){
    let message = messageCode.replace('{1}', param);
    if(!errorMessage.includes(message)){
        errorMessage.push(message)
    }
    if(j == 11){
        rowTable.childNodes[11].style.color = 'red';
    }
    rowTable.childNodes[j].childNodes[k].style.border = '1px solid red';
}

// Mô tả: Ẩn thông báo tại dòng không có dữ liệu
// @param rowTable: 1 dòng trên bảng
// @param k: Vị trí của một phần tử con trên 1 cột của 1 dòng
function hiddenMessageInRow(rowTable, k){
    for(let i=0;i<indexControls.length;i++){
        rowTable.childNodes[indexControls[i]].childNodes[k].style.border = '1px solid #d1d3e2';
        rowTable.childNodes[indexControls[i]].style.color = 'black';

    }
}

// Mô tả: Hiển thị thông báo tại dòng có lỗi
// @param rowTable: 1 dòng trên bảng
// @param k: Vị trí của một phần tử con trên 1 cột của 1 dòng
function onMessageInRow(rowTable, k){
    rowTable.childNodes[indexControls[i]].childNodes[k].style.border = '1px solid red';
    rowTable.childNodes[indexControls[i]].style.color = 'red';

}

// @param rowTable: 1 dòng trên bảng
// @param j: Vị trí của 1 cột của 1 dòng
// @param k: Vị trí của một phần tử con trên 1 cột của 1 dòng
// Ẩn thông báo tại trường dữ liệu
function hiddenMessage(rowTable, j, k){
    rowTable.childNodes[j].childNodes[k].style.border = '1px solid #d1d3e2';
}

// Mô tả: Kiểm tra sinh viên có trùng lặp trên bảng không
// @param rowTable: dòng chứa sinh viên cần kiểm tra
// @param row: vị trị dòng chứa sinh viên cần kiểm tra
// @param fullname: tên cần kiểm tra
// @param dateOfBirth: ngày sinh cần kiểm tra
function checkStudentRegisterConstant(rowTable ,row, fullname, dateOfBirth){
    for(let i=0;i<studentsRegister.length;i++){
        if(i == row){
            continue;
        } else {
            if(studentsRegister[i].fullname == fullname && studentsRegister[i].dateOfBirth == dateOfBirth){
                // thêm thông báo vào danh sách để hiển thị
                addMessage(rowTable, 7, 1, C_00_008, '');
                addMessage(rowTable, 9, 1, C_00_008, '');
                // đổi viền đỏ cho ô bị lỗi
                rowTable.childNodes[7].childNodes[1].style.border = '1px solid red';
                rowTable.childNodes[9].childNodes[1].style.border = '1px solid red';
                break;
            } else {
                // ẩn thông báo lỗi tại ô
                rowTable.childNodes[7].childNodes[1].style.border = '1px solid #d1d3e2';
                rowTable.childNodes[9].childNodes[1].style.border = '1px solid #d1d3e2';
            }
        }
    }
}

