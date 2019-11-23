// Reference: https://developer.mozilla.org/en-US/docs/Web/API/WebSocket
const socket = new WebSocket('ws://gabrielhart.webfaction.com:21324');

socket.addEventListener('open', (event) => {
    console.log('socket opened');
    console.log(event);
});

socket.addEventListener('message', (event) => {
    console.log(event);
    console.log('Message from server: ');
    let data = JSON.parse(event.data);
    console.log(data);
    switch (data.event_type) {
        case "add_piece":
            goBoard.addPiece(data.point.x, data.point.y, data.color);
            break;
        case "remove_piece":
            goBoard.removePiece(data.point.x, data.point.y);
            break;
        case "game_end":
            goBoard.endGame(data.winner);
            console.log(goBoard);
            break;
    }
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