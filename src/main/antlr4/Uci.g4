grammar Uci;

command: (request | response) EOF;

request
    : uci
    | isready
    | ucinewgame
    | position
    | stop
    | quit
    ;

uci: 'uci';

isready: 'isready' ;

ucinewgame: 'ucinewgame';

position: 'position' (startpos | fen) ('moves' move*)?;

startpos: 'startpos';

fen: 'fen' STRING;

move: STRING;

stop: 'stop';

quit: 'quit';

response
    : id
    | uciok
    | readyok
    ;

id: 'id' (name | author);

author: 'name' STRING;

name: 'author';

uciok: 'uciok';

readyok: 'readyok';

STRING : ~[ \t\r\n]+;

WS: [ \t\r\n]+ -> skip;

