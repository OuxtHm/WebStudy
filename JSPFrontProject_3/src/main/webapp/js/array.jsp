<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>음식 목록</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
  margin-top: 50px;
}
.row {
  width: 960px;
  margin: 0px auto;
}

p {
 overflow: hidden;
 white-space: nowrap;
 text-overflow: ellipsis;
}
a.link:hover{
	cursor: pointer;
}
</style>
<script type="text/javascript" src="http://unpkg.com/axios/dist/axios.min.js"></script>
<script type="text/javascript">
let food_list = [];

window.onload = () => {
	dataRecv(1);
}

async function dataRecv(page)
{
    try {
        const response = await axios.get('../food/list.do', {
            params: {
                page: page
            }
        });
        
        console.log(response.data);
        food_list = response.data;
        
    } catch (error) {
        console.error("데이터 로딩 중 오류:", error);
        return; 
    }
    
    // 2. 데이터 출력 (await 완료 후 실행)
	let html = '';
	food_list.map((food) => {
		html += '<div class="col-md-3">'
	    		+ '<div class="thumbnail">'
	      		+ '<a href="#">'
	        	+ '<img src="' + food.poster + '" style="width:230px; height:150px">'
	        	+ '<div class="caption">'
	            + '<p>'+food.name+'</p>'
	        	+ '</div>'
	      		+ '</a>'
	   			+ '</div>'
	 			+ '</div>' 
	});
	document.querySelector('.list').innerHTML = html;
	

	let pages = '<ul class="pagination">';
	
	let totalPage = food_list[0].totalPage; 
    let startPage = food_list[0].startPage;
    let endPage = food_list[0].endPage;
    let curPage = food_list[0].curPage;
    
	if(startPage > 1)
	{
		pages += '<li><a class="link" onclick="dataRecv(' + (startPage - 1) + ')">&lt;</a></li>'
	}
	for(let i = startPage; i <= endPage; i++)
	{
	    pages += '<li ' + (i == curPage ? 'class="active"' : "") + '><a class="link" onclick="dataRecv(' + i + ')">' + i + '</a></li>'	
	}
	if(endPage < totalPage) 
	{
		pages += '<li><a class="link" onclick="dataRecv(' + (endPage + 1) + ')">&gt;</a></li>'
	}
    pages += '</ul>'; 
	document.querySelector('.pages').innerHTML = pages;
}
</script>
</head>
<body>
	<div class="container">
		<div class="row list">
		
		</div>
		<div style="Height:20px"></div>
		<div class="row text-center pages">
		
		</div>
	</div>
</body>
</html>