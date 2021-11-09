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
  console.log("on reset");
}

//Lấy dữ liệu trên dòng template
function batchInput(event) {
  event.preventDefault();
  let formData = $("#formInput").serializeArray(); // lấy toàn bộ dữ liệu trong form
  let rowTable = document.getElementsByClassName("rowTable");
  for (let i = 0; i < rowTable.length; i++) {
    for (let j = 0; j <= MAX_LENGHT_FORMDATA; j++) {
      if (formData[j].name == "sex") {
        if (formData[j].value == "1") {
          document.getElementsByClassName("male")[i].checked = true;
        } else if (formData[j].value == "0") {
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

    console.log(checkedList.length)
  } else {
    for (let i = 0; i < checkboxSingles.length; i++) {
      checkboxSingles[i].checked = true;
      checkedList.push('checkbox')
    }
    document.getElementById("btnDelete").style.display = "unset";
    document.getElementById("btnFake").style.display = "none";

    console.log(checkedList.length)
  }
}

// sự kiện bật tắt checkbox trên các dòng
function onCheckboxChecked(e) {
  if(e.checked){
    checkedList.push('checkbox')
  } else {
    checkedList.pop();
  }
  console.log(checkedList.length);
  if (checkedList.length == 10) {
    document.getElementsByClassName("checkboxAll")[0].checked = true;
  } else if(checkedList.length == 0) {
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
      document.getElementsByClassName("btnDeleteInRow")[i].click();
      document.getElementsByName('sex' + i)[2].checked = true;
    }
    if(checkedList.length == 0){
      document.getElementById("btnDelete").style.display = "none";
      document.getElementById("btnFake").style.display = "unset";
      document.getElementsByClassName("checkboxAll")[0].checked = false;
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

function resgister(){
  checkInputList();
}
