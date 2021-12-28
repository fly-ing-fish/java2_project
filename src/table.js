function onClickAddHotel() {
    let HotelName = document.getElementById("Hotel_Name").value;
    if(validateInput(HotelName)&&checkUnique()&&checkTime()){
        addRow();
        document.getElementById("b").setAttribute("style","display: none")
    }
}
function clickme(){
    document.getElementById("b").setAttribute("style","display: block")
}
function checkTime(){
    let Time=document.querySelector('form input[name="date"]').value;
    let date=new Date()
    let year = date.getFullYear();
    let month = date.getMonth()+1 < 10 ? "0"+(date.getMonth()+1) : (date.getMonth()+1);
    let date1 = date.getDate()+1 < 10 ? "0"+date.getDate(): date.getDate();
    let a=Time.split("-")
    if(a[0]<=year&&(parseInt(a[1]))<=date.getMonth()+1&&parseInt(a[2])<=date.getDate()+1) {
        alert("wrong date")
        return false;
    }
    return true;
}
function checkUnique(){
    let HotelName = document.getElementById("Hotel_Name").value;
    let City = document.querySelector('form input[name="City"]:checked').value;
    let District= document.getElementById("District").value;
    let RoomType = document.getElementById("Room Type").value;
    let table=document.getElementById("table")
    let price=document.getElementById("Price").value;
    for(let i=0,rows=table.rows.length;i<rows;i++) {
        for (let j = 0, cells = table.rows[i].cells.length; j < cells; j++) {
            if (HotelName === table.rows[i].cells[0].innerHTML && City === table.rows[i].cells[1].innerHTML && District === table.rows[i].cells[2].innerHTML && RoomType === table.rows[i].cells[6].innerHTML) {
                alert("same Hotel Name, City, District and Room Type can not be\n" +
                    "appeared for many times")
                return false
            }
            if (HotelName === table.rows[i].cells[0].innerHTML && City === table.rows[i].cells[1].innerHTML && District === table.rows[i].cells[2].innerHTML && RoomType !== table.rows[i].cells[6].innerHTML && price === table.rows[i].cells[5].innerHTML) {
                alert("Different Room Type should have different price")
                return false
            }
        }
    }return true
}
function validateInput(HotelName) {
    let HotelNameRegex = new RegExp(/^([A-Z]|[a-z])*$/);
    if (!HotelNameRegex.test(HotelName)) {
        alert("Invalid Hotel Name,Hotel Name must only be English letters");
        return false;
    }
    return true
}
const response = new XMLHttpRequest();
function initial(type) {
    response.onload= function () {
        if (response.status === 200) {
            console.log(response)
            let jsonObj = JSON.parse(response.responseText)
            if(jsonObj!=null) {
                document.getElementById("length").innerHTML = jsonObj['length']
                for (let num = 0; num < jsonObj['table'].length; num++) {
                    console.log(jsonObj['table'].length)
                    addRow(jsonObj['table'][num], num + 1);
                }
                for (let num = 10; num >= jsonObj['table'].length+1; num--) {
                    let bodyObj = document.getElementById("tbody");
                    console.log(num)
                    removeRow(bodyObj.rows[num])

                }
            }
        }
    }
    console.log(type)
    response.open("GET","http://localhost:8082/api/getInformation?type="+type+"&&page=1",true);
    console.log(response)
    response.send();
}
function go(type) {
    let page = document.getElementById("page").value;
    response.open("GET","http://localhost:8082/api/getInformation?type="+type+"&&page="+page,true);
    console.log(response)
    response.send();
}
function sort(type){
    let attribute=document.getElementById("attribute").value;
    let sort = new XMLHttpRequest();
    sort.onload= function () {
        if (sort.status === 200) {
            response.open("GET","http://localhost:8082/api/getInformation?type="+type+"&&page=1",true);
            response.send();
        }
    }
    if (type===1) {
        sort.open("GET", "http://localhost:8082/api/sortInformation?sort=" + attribute, true);
        sort.send();
    }else if (type===2) {
        sort.open("GET", "http://localhost:8082/api/sortLatestInformation?sort=" + attribute, true);
        sort.send();
    }else if (type===3) {
        sort.open("GET", "http://localhost:8082/api/sortVaccinationInformation?sort=" + attribute, true);
        sort.send();
    }else if (type===4) {
        sort.open("GET", "http://localhost:8082/api/sortVaccinationMetaInformation?sort=" + attribute, true);
        sort.send();
    }else if(type===0){
        sort.open("GET", "http://localhost:8082/api/sortAllInformation?sort=" + attribute, true);
        sort.send();
    }

}
function search(type){
    let attribute=document.getElementById("attribute").value;
    let search=document.getElementById("search").value;
    let searchRequest = new XMLHttpRequest();
    searchRequest.onload= function () {
        if (searchRequest.status === 200) {
            response.open("GET","http://localhost:8082/api/getInformation?type="+type+"&&page=1",true);
            response.send();
        }
    }
    if(type===1) {
        searchRequest.open("GET", "http://localhost:8082/api/searchInformation?sort=" + attribute + "&&search=" + search, true);
        searchRequest.send();
    }else if(type===2){
        searchRequest.open("GET", "http://localhost:8082/api/searchLatestInformation?sort=" + attribute + "&&search=" + search, true);
        searchRequest.send();
    }else if(type===3) {
        searchRequest.open("GET", "http://localhost:8082/api/searchVaccinationInformation?sort=" + attribute + "&&search=" + search, true);
        searchRequest.send();
    }else if(type===4){
        searchRequest.open("GET", "http://localhost:8082/api/searchVaccinationMetaInformation?sort=" + attribute + "&&search=" + search, true);
        searchRequest.send();
    }else if(type===0){
        searchRequest.open("GET", "http://localhost:8082/api/searchAllInformation?sort=" + attribute + "&&search=" + search, true);
        searchRequest.send();
    }
}
function setDistrict() {
    let City = document.querySelector('form input[name="City"]:checked').value;
    let District = document.getElementById("District");
    let Shenzhen=["FU TIAN", "NAN SHAN", "LUO HU", "LONG GANG", "PING SHAN", "LONG HUA", "GUANG MING",
        "YAN TIAN"]
    let Guangzhou=["ZHONG SHAN","TIAN HE","NAN SHA"]
    District.innerHTML = "";
    District.options.add(new Option("--", null));
    if(City==="ShenZhen") {
        for (let i = 0; i < 8; i++) {
            District.options.add(new Option(Shenzhen[i],Shenzhen[i]));
        }
    }else if(City==="GuangZhou"){
        for (let i = 0; i <3; i++) {
            District.options.add(new Option(Guangzhou[i], Guangzhou[i]));
        }
    }
}
function addRow(series,j) {
    let bodyObj = document.getElementById("tbody");
    if (!bodyObj) {
        alert("Body of Table not Exist!");
        return;
    }
    let rowCount = bodyObj.rows.length;
    let cellCount = bodyObj.rows[0].cells.length;
    if (rowCount<=10){
        bodyObj.style.display = ""; // display the tbody
        let newRow = bodyObj.insertRow(rowCount++);
        for(let i=0;i<series.data.length;i++){
            newRow.insertCell(i).innerHTML =series.data[i];
        }
        bodyObj.rows[0].style.display = "none"; // hide first row
    }else {
        for(let i=0;i<series.data.length;i++) {
                bodyObj.rows[j].cells[i].innerHTML = series.data[i]
        }
    }
}
function download(type){
    let searchRequest = new XMLHttpRequest();
    searchRequest.onload= function () {
        if (searchRequest.status === 200) {
            const data = JSON.parse(searchRequest.responseText)['data'] // 这里填CSV内容的字符串
            const blob = new Blob([data], {type: "text/plain"})
            const link = document.createElement("a")
            link.href = URL.createObjectURL(blob)
            link.download = "file.txt" // 这里填保存成的文件名
            link.click()
            URL.revokeObjectURL(link.href)
        }
    };
    searchRequest.open("GET","http://localhost:8082/api/download1?what="+type,true);
    searchRequest.send();
}

function removeRow(inputobj) {
    if (!inputobj) return;
    let parentTD = inputobj.parentNode;
    parentTD.removeChild(inputobj);
}
