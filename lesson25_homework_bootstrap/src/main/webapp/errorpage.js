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
    console.log(ourData);
};
logic();
