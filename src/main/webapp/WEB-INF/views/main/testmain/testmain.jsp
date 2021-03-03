<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>testmain</title>
	<script src="https://code.jquery.com/jquery-3.4.1.min.js" 
			integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
			crossorigin="anonymous"></script>
</head>
<body>
	This is testmain page.
	<br>
	<a href="/jsontest1">jsontest1</a>
	<br>
	<a href="/jsonviewtest2">jsonviewtest2</a>
	<br>
	<a href="/gsontest3">gsontest3</a>
	<br>
	<a href="javascript:sendJsonUsingJackson();">json 을 Jackson 으로 받기</a>
	<br>
	<a href="#" onclick="sendJsonUsingGson();">json 을 Gson 으로 받기</a>
	<br>
	<a href="javascript:testExcelDown1();">TestExcelDownload1</a>
	<br>
	<form id="testExcelFileUploadForm1" name="testExcelFileUploadForm1"
			enctype="multipart/form-data" method="post" action= "/testExcelUpload1.ajax">
		<h4>엑셀파일 업로드</h4>
		<input type="file" id="excelFile" name="excelFile" accept=".xlsx"/>
		<br>
		<a href="javascript:void(0);" onclick="checkAndUpload();"><span aria-hidden="true">업로드저장</span></a>
	</form>

<!-- for file Download -->
<script src="/js/jquery/jquery.fileDownload.js"></script>
<!-- for excel upload -->
<script src="/js/jquery/jquery.form.js"></script>

<script type="text/javascript">
function sendJsonUsingJackson() {
	var obj = {"id": "202003041642", "title":"sendJsonUsingJackson", "content": "success jackson"};
	
	$.ajax({
		url: "/sendJsonUsingJackson",
		type: "POST",
		data: JSON.stringify(obj),
		contentType: "application/json",
		success: function(data) {
			console.log("success");
			console.log(data);
		},
		error: function(errorThrown) {
			alert(errorThrown.statusText);
		}
	})
}

function sendJsonUsingGson() {
	var obj = {"id": "202003041646", "title":"sendJsonUsingGson", "content": "success Gson"};
	var jsonData = JSON.stringify(obj);
	var formData = {jsonstr: jsonData};
	
	$.ajax({
		url: "/sendJsonUsingGson",
		type: "POST",
		data: formData,
		//contentType: "application/json",
		success: function(data) {
			console.log("success");
			console.log(data);
		},
		error: function(errorThrown) {
			alert(errorThrown.statusText);
		}
	})
}

function testExcelDown1() {
	/* $.ajax({
		url: "/testExcelDown1.ajax",
		type: "GET",
		success: function(data) {
			console.log("success");
		},
		error: function(errorThrown) {
			alert(errorThrown.statusText);
		}
	}) */
	$.fileDownload("/testExcelDown1.ajax", {
    	httpMethod: "GET",
        successCallback: function (url) {
        	console.log("callback success!");
        },
        failCallback: function (responseHtml, url) {
        	console.log("callback fail!");
        }
    });
}

function checkFileType(filePath) {
    var fileFormat = filePath.split(".");
    if (fileFormat.indexOf("xlsx") > -1) {
        return true;
    } else {
        return false;
    }
}
function checkAndUpload() {
	var file = $("#excelFile").val();
	if (file == "" || file == null) {
		alert("파일을 선택해주세요.");
		return false;
	} else if (!checkFileType(file)) {
		alert("엑셀 파일(xlsx)만 업로드 가능합니다.");
		return false;
	}
	
	if (confirm("업로드 하시겠습니까?")) {
		var options = {
			beforeSend : function(data) {
				alert("beforeSend");
			},
			complete : function(data) {
				alert("complete");
			},
			success : function(data) {
				alert("모든 데이터가 업로드 되었습니다.");
				//location.reload();
			},
			error : function(data) {
				alert("upload error");
			},
			type : "POST"
		};
		$("#testExcelFileUploadForm1").ajaxSubmit(options);
    }
}
</script>
</body>
</html>