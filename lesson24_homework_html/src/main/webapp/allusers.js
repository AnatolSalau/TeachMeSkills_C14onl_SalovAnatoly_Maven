let url = 'http://127.0.0.1:8080/lesson24homework/allusers';

fetch(url)
    .then(res => res.json())
    .then((out) => {
        console.log('Checkout this JSON! ', out);
    })
    .catch(err => { throw err });