const STONE_COLOR = {
    BLACK: "BLACK",
    WHITE: "WHITE"
};
const GAME_SIZE = 9;
class GoBoard {
    constructor(size) {
        this.board = [];
        this.size = size;
        for (let i = 0; i < size; ++i) {
            let row = [];
            for (let j = 0; j < size; ++j) {
                row.push(undefined);
            }
            this.board.push(row);
        } 
    }
    addPiece(x, y, color) {
        this.board[x][y] = color;
    }
    removePiece(x, y) {
        this.board[x][y] = undefined;
    }
    endGame(winner) {
        this.winner = winner;
    }
    draw(length) {
        this.drawGrid(length);
        this.drawStones(length);    
        if (this.winner !== undefined) {
            this.drawWinner(length);
        }
    }
    drawWinner(length) {
        textSize(36);
        fill('#a9a9a9');
        let textBufferLeft = 75;
        text(this.winner + " wins!", length/2 - textBufferLeft, length/2);
    }
    drawGrid(length) {
        let interval = length/this.size;
        let padding = interval/2;
        for (let i = 0; i < this.size; ++i) {
            line(padding + i * interval, padding, padding + i * interval, length-interval + padding);
            line(padding, padding + i * interval, length-interval + padding, padding + i * interval);
        }
    }
    drawStones(length) {
        let interval = length / this.size;
        let padding = interval / 2;
        for (let i = 0; i < this.size; ++i) {
            for (let j = 0; j < this.size; ++j) {
                if (this.board[i][j] !== undefined) {
                    if (this.board[i][j] === STONE_COLOR.BLACK) {
                        fill(51);
                    } else {
                        noFill();
                    }
                    circle(padding + interval * i, padding + interval * j, 40);
                }
            }
        }
    }
}
let goBoard = new GoBoard(GAME_SIZE);
let length;

function setup () {
    length = windowHeight/2;
    let cnv = createCanvas(length, length);
    cnv.parent('sketch-holder');
}

function draw() {
    background(255);
    goBoard.draw(length);
}

function windowResized() {
    length = windowHeight/2;
    resizeCanvas(length, length);
}