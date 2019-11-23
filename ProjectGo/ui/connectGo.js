// Reference: https://developer.mozilla.org/en-US/docs/Web/API/WebSocket
const socket = new WebSocket('ws://localhost:8887');

socket.addEventListener('open', (event) => {
    console.log('socket opened');
    console.log(event);
});

socket.addEventListener('message', (event) => {
    console.log(event);
    console.log('Message from server: ', event.data);
    let data = JSON.parse(event.data);
    console.log(data);
    goBoard.addPiece(3, 8, STONE_COLOR.BLACK);
});

let submitMoveForm = (event) => {
    let x = document.getElementById('x').value;
    let y = document.getElementById('y').value;
    document.getElementById('x').value = null;
    document.getElementById('y').value = null;    
    makeMove(x, y);
    return false;
}
let submitPassForm = (event) => {
    pass();
    return false;
}

let submitUndoForm = (event) => {
    undo();
    return false;
}

let makeMove = (x, y) => {
    let move = {x, y};
    sendEventMessage('move', move);
};
let pass = () => {
    sendEventMessage('pass');
}
let undo = () => {
    sendEventMessage('undo');
}
let sendEventMessage = (eventType, extraMessageParams = {}) => {
    let event = {
        "event_type" : eventType,
        ...extraMessageParams
    };
    console.log('sending:');
    console.log(event);
    socket.send(JSON.stringify(event));
}