class ChangeColor {
    #elementOfDocument;

    constructor(elementId) {
        this.#elementOfDocument = document.getElementById(elementId);
    }
    changeBackgroundColor(color) {
        this.#elementOfDocument.style.backgroundColor = color;
    }
}

export default ChangeColor;