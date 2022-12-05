class OnClickRedirect {
    #element;

    constructor(idElement) {
        this.#element = document.getElementById(idElement);
    }
    onClickRedirectToAnotherPageWithAlert(message, url) {
        this.#element.onclick = ()=> {
            console.log("onClickRedirectToAnotherPageWithAlert")
/*            window.alert(message);*/
            document.location.href = url,true;
        };
    }
    consolePrint(string) {
        console.log(string);
    }
}
export default OnClickRedirect;