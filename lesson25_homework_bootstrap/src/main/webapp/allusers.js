let url = 'http://127.0.0.1:8080/lesson25homework/allusers';
//Словарь с пользователями
let arrayOfUsers = [];

//Функция получения json c url
const getData = async (url) => {
    const data = await fetch(url);
    return data.json();
};

//Функция с логикой нашего приложения
//Приходится писать логику в функции так как чтобы получить из json нормальный объект приходится использовать await
const logic = async () => {
    //Используем await
    const ourData = await getData(url);
    //logic
    //Перебираем объект и кладем данные в массив
    for(var i in ourData.users)
        arrayOfUsers.push([i, ourData.users[i]]);
    //Как можно перебрать массив по ключам!
    /*
    arrayOfUsers.forEach(
        function(item) {
            Object.keys(item).forEach(function(key) {
                console.log("key:" + key + "value:" + item[key]);
            });
        });
    */
    //Индексы нужно писать как строки
    console.log(arrayOfUsers["0"]["1"]["login"]);
    // function that create table with users
    function generate_table() {
        //Создаем текс с название таблицы
        var br = document.createElement("br");
        document.body.appendChild(br);
        const topTable = document.createElement("text");
        topTable.textContent = "List of user"

        // creates a <table> element and a <tbody> element
        const tbl = document.createElement("table");
        const tblBody = document.createElement("tbody");
        console.log(arrayOfUsers);

        // creating all cells
        for (let i = 0; i < arrayOfUsers.length; i++) {

            // creates a table row
            const row = document.createElement("tr");
            for (let j = 0; j <=0 ; j++) {
                // Create a <td> element and a text node, make the text
                // node the contents of the <td>, and put the <td> at
                // the end of the table row
                console.log(arrayOfUsers);
                const cell = document.createElement("td");
                const cellText = document.createTextNode(
                    arrayOfUsers[i]["1"]["login"]);
                //Добавляем созданные элементы в HTML
                cell.appendChild(cellText);
                row.appendChild(cell);
            }
            // add the row to the end of the table body
            tblBody.appendChild(row);
        }
        // appends <text> into <body>
        document.body.appendChild(topTable)
        // put the <tbody> in the <table>
        tbl.appendChild(tblBody);
        // appends <table> into <body>
        document.body.appendChild(tbl);
        // sets the border attribute of tbl to '2'
        tbl.setAttribute("border", "2");
    };
    generate_table();
};
logic();
