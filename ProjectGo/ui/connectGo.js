// Reference: https://developer.mozilla.org/en-US/docs/Web/API/WebSocket
const socket = new WebSocket('ws://localhost:8887');

socket.addEventListener('open', (event) => {
    console.log('socket opened');
    console.log(event);
});

socket.addEventListener('message', (event) => {
    console.log(event);
    console.log('Message from server: ', event.data);
});

function submitForm(event) {
    let textInput = document.getElementById("msg").value;
    console.log(' Sending to server: ', textInput);
    document.getElementById("msg").value = "";
    socket.send(textInput);
    return false;
}