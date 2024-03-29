/*
Ngày viết : 08/11/2021
Tác giả : GMO_ThanhND
*/

document
    .querySelector("#formInput")
    .addEventListener("submit", batchInput, true); // thêm sự kiện submit cho form
let MAX_LENGHT_FORMDATA = 5; // số lượng trường dữ liệu cần lấy trên form batch input
let checkboxSingles = document.getElementsByClassName("checkboxSingle"); // lấy ra các checkbox trên các hàng

let checkedList = new Array();

//Xóa trắng ô nhập khi người dùng click vào
function resetInput(e) {
    e.value = "";
}

//Lấy dữ liệu trên dòng template
function batchInput(event) {
    const MALE = "1";
    const FEMALE = "0";
    event.preventDefault();
    let formData = $("#formInput").serializeArray(); // lấy toàn bộ dữ liệu trong form
    let rowTable = document.getElementsByClassName("rowTable");
    for (let i = 0; i < rowTable.length; i++) {
        for (let j = 0; j <= MAX_LENGHT_FORMDATA; j++) {
            if (formData[j].name == "sex") {
                if (formData[j].value == MALE) {
                    document.getElementsByClassName("male")[i].checked = true;
                } else if (formData[j].value == FEMALE) {
                    document.getElementsByClassName("female")[i].checked = true;
                }
            } else {
                document.getElementsByClassName(formData[j].name)[i].value =
                    formData[j].value;
            }
        }
        rowTable[i].setAttribute("status", "on");
    }
}

let rowTable = document.getElementsByClassName("rowTable")[0];
let bodytable = document.getElementById("bodytable");

//Tạo các dòng trong bảng
for (let i = 1; i < 10; i++) {
    let tr = document.createElement("tr");
    tr.innerHTML = rowTable.innerHTML;
    tr.getElementsByClassName("noRow")[0].innerHTML = i + 1;
    tr.getElementsByClassName("sexRdo")[2].name = "sex" + i;
    tr.getElementsByClassName("sexRdo")[0].name = "sex" + i;
    tr.getElementsByClassName("sexRdo")[1].name = "sex" + i;
    tr.id = "row" + i;
    tr.className = "rowTable";
    tr.setAttribute("status", "off");
    bodytable.appendChild(tr);
}

//Thêm hành động xóa trắng vào các ô nhập
let inputTable = document.getElementsByClassName("inputTable");
for (let i = 0; i < inputTable.length; i++) {
    inputTable[i].onclick = resetInput.bind(this, inputTable[i]);
}

// sự kiện bật tắt nút checkbox all
function checkBoxAll() {
    let checkboxAll = document.getElementsByClassName("checkboxAll")[0];
    if (checkboxAll.checked == false) {
        for (let i = 0; i < checkboxSingles.length; i++) {
            checkboxSingles[i].checked = false;
            checkedList.pop();
        }
        document.getElementById("btnDelete").style.display = "none";
        document.getElementById("btnFake").style.display = "unset";
    } else {
        for (let i = 0; i < checkboxSingles.length; i++) {
            checkboxSingles[i].checked = true;
            checkedList.push('checkbox')
        }
        document.getElementById("btnDelete").style.display = "unset";
        document.getElementById("btnFake").style.display = "none";
    }
}

// sự kiện bật tắt checkbox trên các dòng
// @param e: thẻ cần thao tác
function onCheckboxChecked(e) {
    if (e.checked) {
        checkedList.push('checkbox')
    } else {
        checkedList.pop();
    }
    if (checkedList.length == 10) {
        document.getElementsByClassName("checkboxAll")[0].checked = true;
    } else if (checkedList.length == 0) {
        document.getElementById("btnDelete").style.display = "none";
        document.getElementById("btnFake").style.display = "unset";
    } else {
        document.getElementById("btnDelete").style.display = "unset";
        document.getElementById("btnFake").style.display = "none";
        document.getElementsByClassName("checkboxAll")[0].checked = false;
    }
}

//Xóa dữ liệu về mặc định trên các dòng được chọn
function deleteRowSelected() {
    for (let i = 0; i < checkboxSingles.length; i++) {
        if (checkboxSingles[i].checked == true) {
            checkboxSingles[i].checked = false;
            checkedList.pop();
            $(".btnDeleteInRow")[i].click();
            document.getElementsByName('sex' + i)[2].checked = true;
            hiddenMessageInRow($(".rowTable")[i], 1);
        }
        if (checkedList.length == 0) {
            document.getElementsByClassName("checkboxAll")[0].checked = false;
            document.getElementById("btnDelete").style.display = "none";
            document.getElementById("btnFake").style.display = "unset";
        }
    }
    checkInputList();
}

// Lưu giá trị mặc định của các input trong bảng
$('#myForm :input:not([type="button"])').each(function (idx, ele) {
    ele.dataset.initvalue = ele.value;
});

$('#myForm [type="button"]').on("click", function (e) {
    // reset current row............
    $(this)
        .closest("tr")
        .find(':input:not([type="button"])')
        .each(function (idx, ele) {
            // Khôi phục giá trị mặc định trên các ô
            ele.value = ele.dataset.initvalue;
        });
});

// mô tả: lấy dữ liệu kiểm tra và gửi về server
async function resgister() {
    checkInputList();
    let students = [];

    // nếu kiểm tra không có lỗi
    if (errorMessage.length == 0) {
        // lấy dữ liệu tại những dòng được nhập
        for (let i = 0; i < studentsRegister.length; i++) {
            if (!(studentsRegister[i].classId == ''
                && studentsRegister[i].fullname == ''
                && studentsRegister[i].dateOfBirth == ''
                && studentsRegister[i].phone == ''
                && studentsRegister[i].note == ''
                && studentsRegister[i].sex == '')) {
                students.push({
                    rowId: i,
                    classId: studentsRegister[i].classId,
                    fullname: studentsRegister[i].fullname,
                    dateOfBirth: studentsRegister[i].dateOfBirth,
                    phone: studentsRegister[i].phone,
                    note: studentsRegister[i].note,
                    sex: studentsRegister[i].sex
                })
            }
        }

        // Nhận về thông báo lỗi bên server
        let dataOut = await callAjax('add-student', JSON.stringify(students), 'POST');
        let rowTable = $(".rowTable");

        // ẩn các thông báo lỗi hiện tại
        for (let i = 0; i < rowTable.length; i++) {
            hiddenMessageInRow(rowTable[i], 1);
        }

        // đưa lên những thông báo mới
        for (let i = 0; i < dataOut.length; i++) {
            addMessage(rowTable[dataOut[i].row], dataOut[i].column, 1, dataOut[i].messageCode, dataOut[i].param);
        }
        showMessage();

        // nếu không có lỗi thì xóa trắng bảng và thông báo thêm thành công
        if (dataOut.length == 0) {
           $(".checkboxAll")[0].click();
           deleteRowSelected();
            let messageDiv = $("#message");
            messageDiv.innerHTML = '';
            messageDiv.innerHTML +=
                "<div class=\"alert alert-success\" role=\"alert\">" +
                "Add student successfully!" +
                "</div>";
        }
    }
}

