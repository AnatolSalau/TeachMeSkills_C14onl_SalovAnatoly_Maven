"use strict"
import OnClickRedirect from "../js/OnClickRedirect.js";

window.addEventListener("load", main);

function main() {
    console.log("Hello from console log")
    let onClickRedirect = new OnClickRedirect("logoutButton");
    onClickRedirect.consolePrint("Hello from onClickRedirect");

}