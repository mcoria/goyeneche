grammar Uci;

command: (request | response) EOF;

request
    : 'uci'
    | 'isready'
    | 'ucinewgame'
    | 'position' position
    | 'stop'
    | 'quit'
    ;

position: ('startpos' | 'fen' fen) ('moves' moves)?;

fen: STRING STRING STRING STRING STRING STRING;

moves: STRING*;

response
    : 'id' id
    | 'uciok'
    | 'readyok'
    ;


id: ('name' name | 'author' author);

name: STRING*;

author: STRING*;

STRING : ~[ \t\r\n]+;

WS: [ \t\r\n]+ -> skip;

