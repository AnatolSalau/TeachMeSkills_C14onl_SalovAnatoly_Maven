let url = 'http://127.0.0.1:8080/lesson25homework/errorpage';
//Словарь с пользователями
let userArr = [];

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
    console.log(ourData);
    //Переводим объект в массив
    for (const ourDataKey in ourData.user) {
        userArr.push([ourDataKey, ourData.user[ourDataKey]])
    }
    console.log(userArr);
    console.log(userArr[0][1]);
    //Создаем текс с название таблицы
    var br = document.createElement("br");
    document.body.appendChild(br);
    const topTable = document.createElement("text");
    topTable.textContent = "User with login " + userArr[0][1] + " already exist";
    // appends <text> into <body>
    document.body.appendChild(topTable)
};
logic();