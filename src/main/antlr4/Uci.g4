grammar Uci;

command: (request | response) EOF;

request
    : 'uci'
    | 'isready'
    | 'ucinewgame'
    | 'position' positionparams
    | 'stop'
    | 'quit'
    ;

positionparams: ('startpos' | 'fen' fen) ('moves' moves)?;

fen: STRING STRING STRING STRING STRING STRING;

moves: STRING*;

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

