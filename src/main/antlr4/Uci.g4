grammar Uci;

command: (request | response) EOF;

request
    : 'uci'
    | 'isready'
    | 'ucinewgame'
    | 'position' position
    | 'go' (go)?
    | 'stop'
    | 'quit'
    ;

position: ('startpos' | 'fen' fen) ('moves' move+)?;

fen: STRING STRING STRING STRING STRING STRING;

go: 'infinite';

response
    : 'id' id
    | 'uciok'
    | 'readyok'
    | 'bestmove' move ('ponder' move)?
    ;

id: ('name' name | 'author' author);

name: STRING*;

author: STRING*;

move: STRING;

STRING : ~[ \t\r\n]+;

WS: [ \t\r\n]+ -> skip;

