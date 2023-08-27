// Kiểm tra sự hợp lệ của dữ liệu nhâp
function checkValidLogin() {
  let name = document.getElementById("user").value;

  // Tham chiêu đối tượng báo lỗi
  let viewErrName = document.getElementById("errName");

  // Khai bao biến xác nhận hợp lệ
  var validName = true;

  // Khai báo biến ghi nhận thông báo kiểm tra
  var message = "";

  // Kiểm tra name
  name = name.trim();
  if (name == "") {
    validName = false;
    message = "Thiếu tên đăng nhập | hộp thư";
  } else {
    if (name.length < 5 || name.length > 50) {
      validName = false;
      message = "Tên đăng nhập | hộp thư quá ngắn hoặc quá dài";
    } else {
      if (name.indexOf(" ") != -1) {
        validName = false;
        message = "Tên đăng nhập hoăc hộp thư không được chứa dấu cách";
      } else {
        if (name.indexOf("@") != -1) {
          var pattern = /\w+@\w+[.]+\w/;
          if (!name.match(pattern)) {
            validName = false;
            message = "Không đúng cấu trúc hộp thư";
          }
        }
      }
    }
  }

  //   Thông báo name
  viewErrName.style.paddingTop = "5px"; // padding-top
  viewErrName.style.paddingBottom = "5px";
  viewErrName.style.fontSize = "12px";
  if (!validName) {
    viewErrName.innerHTML =
      message + '   <i class="fa-regular fa-circle-xmark"></i>';
    viewErrName.style.display = "block";
  } else {
    viewErrName.style.display = "none";
  }
}

function checkValidPassword(data) {
  let checkLower = document.getElementById("chkLower");
  let checkUpper = document.getElementById("chkUpper");
  let checkNumber = document.getElementById("chkNumber");
  let checkSpec = document.getElementById("chkSpec");
  let checkLength = document.getElementById("chklength");

  var lower = /[a-z]/;
  var upper = /[A-Z]/;
  var number = /[0-9]/;
  var spec = /[!@#\$%\^&\*]/;
  if (lower.test(data)) {
    checkLower.classList.add("valid");
  } else {
    checkLower.classList.remove("valid");
  }
  if (upper.test(data)) {
    checkUpper.classList.add("valid");
  } else {
    checkUpper.classList.remove("valid");
  }
  if (number.test(data)) {
    checkNumber.classList.add("valid");
  } else {
    checkNumber.classList.remove("valid");
  }
  if (spec.test(data)) {
    checkSpec.classList.add("valid");
  } else {
    checkSpec.classList.remove("valid");
  }
  if (data.length >= 8) {
    checkLength.classList.add("valid");
  } else {
    checkLength.classList.remove("valid");
  }
}
var toggleBtn = document.getElementById("show-btn");
var chkPassword = document.getElementById("Password");
function showPass() {
  if (chkPassword.type == "password") {
    chkPassword.setAttribute("type", "text");
    toggleBtn.classList.add("show");
  } else {
    chkPassword.setAttribute("type", "password");
    toggleBtn.classList.remove("show");
  }
}
