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
function initial() {
    response.onload= function () {
        if (response.status === 200) {
            console.log(response)
            let jsonObj = JSON.parse(response.responseText)
            if(jsonObj!=null) {
                console.log(jsonObj['length'])
                console.log(jsonObj)
                document.getElementById("length").innerHTML = jsonObj['length']
                for (let num = 0; num < 10; num++) {
                    addRow(jsonObj['table'][num], num + 1);
                }
            }
        }
    }
    response.open("GET","http://localhost:8083/api/getInformation?page=1",true);
    console.log(response)
    response.send();
}
function onClickSort() {

    response.open("GET","http://localhost:8083/api/sortInformation",true);
    console.log(response)
    response.send();
}
function onClick() {
    response.open("GET","http://localhost:8083/api/searchInformation",true);
    console.log(response)
    response.send();
}
function go() {
    let page = document.getElementById("page").value;
    response.open("GET","http://localhost:8083/api/getInformation?page="+page,true);
    console.log(response)
    response.send();
}
function sort(){
    let attribute=document.getElementById("attribute").value;
    response.open("GET","http://localhost:8083/api/sortInformation?sort="+attribute,true);
    response.send();
    response.open("GET","http://localhost:8083/api/getInformation?page=1",true);
    console.log(response)
    response.send();
}
function search(){
    let attribute=document.getElementById("attribute").value;
    let search=document.getElementById("search").value;
    response.open("GET","http://localhost:8083/api/searchInformation?sort="+attribute+"&&search="+search,true);
    response.send();
    response.open("GET","http://localhost:8083/api/getInformation?page=1",true);
    console.log(response)
    response.send();
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

function removeRow(inputobj) {
    if (!inputobj) return;
    let parentTD = inputobj.parentNode;
    let parentTR = parentTD.parentNode;
    let parentTBODY = parentTR.parentNode;
    parentTBODY.removeChild(parentTR);
}