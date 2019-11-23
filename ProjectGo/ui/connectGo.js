

// Reference - https://stackoverflow.com/questions/36921947/read-a-server-side-file-using-javascript
function loadFile(filePath) {
    let result;
    let request = new XMLHttpRequest();
    request.open("GET", filePath, false);
    request.send();
    if (request.status==200) {
        result = request.responseText;
    }
    return result;
}
// References: https://developer.mozilla.org/en-US/docs/Web/API/WebSocket
const socket = new WebSocket('ws://' + loadFile('serverAddress'));

socket.addEventListener('message', (event) => {
    let data = JSON.parse(event.data);
    switch (data.event_type) {
        case "add_piece":
            goBoard.addPiece(data.point.x, data.point.y, data.color);
            break;
        case "remove_piece":
            goBoard.removePiece(data.point.x, data.point.y);
            break;
        case "game_end":
            goBoard.endGame(data.winner);
            break;
        case "alert":
            let message = data.alert_message;
            alert(message);
            break;
        case "usage_stats":
            let users = data.live_users;
            let multiplayerUsers = data.users_in_multiplayer;
            document.getElementById('liveUsers').innerHTML = users;
            document.getElementById('multiplayerUsers').innerHTML = multiplayerUsers;
            
            break;
    }
});

let submitMoveForm = (event) => {
    let x = document.getElementById('x').value;
    let y = document.getElementById('y').value;
    document.getElementById('x').value = null;
    document.getElementById('y').value = null;    
    makeMove(x, y);
    document.getElementById('x').focus();
    return false;
};
let submitNewSinglePlayerGame = (event) => {
    startNewSinglePlayerGame();
    goBoard = new GoBoard(GAME_SIZE);
    return false;
};
let submitNewMultiPlayerGame = (event) => {
    startNewMultiPlayerGame();
    goBoard = new GoBoard(GAME_SIZE);
    return false;
};
let submitPassForm = (event) => {
    pass();
    return false;
};

let submitUndoForm = (event) => {
    undo();
    return false;
};



let startNewSinglePlayerGame = () => {
    sendEventMessage('start_single_player');
};
let startNewMultiPlayerGame = () => {
    sendEventMessage('start_multi_player');
}
let makeMove = (x, y) => {
    let move = {x, y};
    sendEventMessage('move', move);
};
let pass = () => {
    sendEventMessage('pass');
};
let undo = () => {
    sendEventMessage('undo');
};
let sendEventMessage = (eventType, extraMessageParams = {}) => {
    let event = {
        "event_type" : eventType,
        ...extraMessageParams
    };
    socket.send(JSON.stringify(event));
};