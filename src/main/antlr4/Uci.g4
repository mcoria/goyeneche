grammar Uci;

command: (request | response) EOF;

request
    : 'uci'
    | 'isready'
    | 'setoption' setoption
    | 'ucinewgame'
    | 'position' position
    | 'go' (go)?
    | 'stop'
    | 'quit'
    ;

setoption: 'name' optionname ('value' STRING)?;

position: ('startpos' | 'fen' fen) ('moves' move+)?;

fen: STRING STRING STRING STRING STRING STRING;

go
    : 'infinite'
    | 'depth' depthparam
    | 'movetime' movetime
    | 'wtime' wtime 'btime' btime 'winc' winc 'binc' binc
    ;

depthparam: STRING;
movetime: STRING;
wtime: STRING;
btime: STRING;
winc: STRING;
binc: STRING;

response
    : 'id' id
    | 'uciok'
    | 'readyok'
    | 'bestmove' move ('ponder' move)?
    | 'option' option
    ;

id: ('name' name | 'author' author);

name: STRING*;

author: STRING*;

move: STRING;

option: 'name' optionname 'type' optiontype;

optionname: STRING;

optiontype
    : 'button'
    | 'string' 'default' STRING;


STRING : ~[ \t\r\n]+;

WS: [ \t\r\n]+ -> skip;

