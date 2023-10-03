/**
 * 
 */

export const previewBeforeUpload = (e) => {
	if (e.target.files.length == 0) {
		return;
	}
	let file = e.target.files[0];
	let url = URL.createObjectURL(file);
	document.querySelector("#eAvatar").src = url;
}

//takes an array of JavaScript File objects
export const getFiles = (files) => {
	return Promise.all(files.map(getFile));
}

/** Lấy 1 file dạng mảng byte và dạng base64</br>
 * 
 */
export const getFile = (file) => {
	if (file != null) {
		const reader = new FileReader();
		return new Promise((resolve, reject) => {
			reader.onerror = () => { reader.abort(); reject(new Error("Error parsing file")); }
			reader.onload = function() {

				//This will result in an array that will be recognized by C#.NET WebApi as a byte[]
				let bytes = Array.from(new Uint8Array(this.result));

				/*
				 btoa: binary String -> encode
				 - Ngoại lệ: InvalidCharacterError: Chuỗi chứa một ký tự không vừa với một byte
				 - Hàm decode: atob(encodeString)
				*/
				let base64StringFile = reader.readAsDataURL(file).result;
				//Resolve the promise with your custom file structure
				resolve({ 
					bytes,
					base64StringFile,
					fileName: file.name,
					fileType: file.type
				});
			}
			reader.readAsArrayBuffer(file);
		});
	}
}