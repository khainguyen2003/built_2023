let number = "123456789";
let capLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
let lowLetters = "abcdefghijklmnopqrstuvwxyz";
let specChar = "@#$&*_";

export const removeVietnameseTones = (str) => {
  str = str.replace(/à|á|ạ|ả|ã|â|ầ|ấ|ậ|ẩ|ẫ|ă|ằ|ắ|ặ|ẳ|ẵ/g, "a");
  str = str.replace(/è|é|ẹ|ẻ|ẽ|ê|ề|ế|ệ|ể|ễ/g, "e");
  str = str.replace(/ì|í|ị|ỉ|ĩ/g, "i");
  str = str.replace(/ò|ó|ọ|ỏ|õ|ô|ồ|ố|ộ|ổ|ỗ|ơ|ờ|ớ|ợ|ở|ỡ/g, "o");
  str = str.replace(/ù|ú|ụ|ủ|ũ|ư|ừ|ứ|ự|ử|ữ/g, "u");
  str = str.replace(/ỳ|ý|ỵ|ỷ|ỹ/g, "y");
  str = str.replace(/đ/g, "d");
  str = str.replace(/À|Á|Ạ|Ả|Ã|Â|Ầ|Ấ|Ậ|Ẩ|Ẫ|Ă|Ằ|Ắ|Ặ|Ẳ|Ẵ/g, "A");
  str = str.replace(/È|É|Ẹ|Ẻ|Ẽ|Ê|Ề|Ế|Ệ|Ể|Ễ/g, "E");
  str = str.replace(/Ì|Í|Ị|Ỉ|Ĩ/g, "I");
  str = str.replace(/Ò|Ó|Ọ|Ỏ|Õ|Ô|Ồ|Ố|Ộ|Ổ|Ỗ|Ơ|Ờ|Ớ|Ợ|Ở|Ỡ/g, "O");
  str = str.replace(/Ù|Ú|Ụ|Ủ|Ũ|Ư|Ừ|Ứ|Ự|Ử|Ữ/g, "U");
  str = str.replace(/Ỳ|Ý|Ỵ|Ỷ|Ỹ/g, "Y");
  str = str.replace(/Đ/g, "D");
  // Some system encode vietnamese combining accent as individual utf-8 characters
  // Một vài bộ encode coi các dấu mũ, dấu chữ như một kí tự riêng biệt nên thêm hai dòng này
  str = str.replace(/\u0300|\u0301|\u0303|\u0309|\u0323/g, ""); // ̀ ́ ̃ ̉ ̣  huyền, sắc, ngã, hỏi, nặng
  str = str.replace(/\u02C6|\u0306|\u031B/g, ""); // ˆ ̆ ̛  Â, Ê, Ă, Ơ, Ư
  // Remove extra spaces
  // Bỏ các khoảng trắng liền nhau
  str = str.replace(/ + /g, " ");
  str = str.trim();
  // Remove punctuations
  // Bỏ dấu câu, kí tự đặc biệt
  str = str.replace(
    /!|@|%|\^|\*|\(|\)|\+|\=|\<|\>|\?|\/|,|\.|\:|\;|\'|\"|\&|\#|\[|\]|~|\$|_|`|-|{|}|\||\\/g,
    " "
  );
  return str;
};

export const createAlias = (name) => {
	name = name.toLowerCase();
  removeVietnameseTones(name);
  let words = name.split(" ");
  let alias = words[words.length - 1];
  for (let i = 0; i < words.length - 1; i++) {
    alias += words[i].charAt(0);
  }

  return alias;
};

export const generatePassword = (length) => {
	var pass = "";
	pass += number.charAt(Math.floor(Math.random() * number.length));
	pass += capLetters.charAt(Math.floor(Math.random() * capLetters.length));
	pass += lowLetters.charAt(Math.floor(Math.random() * lowLetters.length));
	pass += specChar.charAt(Math.floor(Math.random() * specChar.length));
	var remaintLength = length - 4;
	for(var i = 0; i < remaintLength; i++) {
		pass += determineChar();
	}
	return shuffleString(pass);
}

function determineChar() {
	var charSetIndex = Math.floor(Math.random() * 4);
	switch(charSetIndex) {
		case 0:
			return number.charAt(Math.floor(Math.random() * number.length));
		case 1:
			return capLetters.charAt(Math.floor(Math.random() * capLetters.length));
		case 2: 
			return lowLetters.charAt(Math.floor(Math.random() * lowLetters.length));
		default:
			return specChar.charAt(Math.floor(Math.random() * specChar.length));
	}
}

function shuffleString(str) {
	var result = str.split('');
	for(var i = 0; i < str.length; i++) {
		var index = Math.floor(Math.random() * str.length);
		var tmp = result[index];
		result[index] = result[i];
		result[i] = tmp;
	}
	return result.join('');
}
