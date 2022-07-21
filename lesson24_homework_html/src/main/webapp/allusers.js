//Вывод JSON в консоль
let url = 'http://127.0.0.1:8080/lesson24homework/allusers';
let dataFromServer;


const getData = async (url) => {
    const data = await fetch(url);
    return data.json();
};

const logic = async () => {
    const ourData = await getData(url);
    console.log(ourData);
    console.log(ourData.users);
    //logic
    let arrayOfUsers = [];
    for(var i in ourData.users)
        arrayOfUsers.push([i, ourData.users[i]]);

    console.log(arrayOfUsers);
};

logic();