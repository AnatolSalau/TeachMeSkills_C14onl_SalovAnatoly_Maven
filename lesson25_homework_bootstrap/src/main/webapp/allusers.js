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
    //Индексы нужно писать как строки
    console.log(arrayOfUsers);
    //[индекс юзера]["1"][имя параметра]
    console.log(arrayOfUsers[0]["1"]["login"]);
    // function that create table with users
    generate_table();
};
logic();

function generate_table() {
    // get table body
    const tableBody = document.getElementById("tableBody");
    console.log(tableBody);
    //создаем строки
    //[индекс юзера]["1"][имя параметра]
    //${} - вставка переменных в строку
    for (let i = 0; i < arrayOfUsers.length; i++) {
        tableBody.insertAdjacentHTML('beforeend',
            `<tr>
        <th scope="row">${i+1}</th>
        <td>${arrayOfUsers[i]["1"]["login"]}</td>
        <td>${arrayOfUsers[i]["1"]["password"]}</td>
        <td>${arrayOfUsers[i]["1"]["gender"]}</td>
        <td>${arrayOfUsers[i]["1"]["description"]}</td>
        <td>${arrayOfUsers[i]["1"]["role"]}</td>
    </tr>`
        );
    }
};
