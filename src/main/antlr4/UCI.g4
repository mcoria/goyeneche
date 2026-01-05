grammar UCI;

command: (request | response ) EOF;

request
    : uci
    | ucinewgame
    | isready
    | stop
    | quit
    | setoption
    | position
    | go
    ;

uci
    : 'uci'
    ;

isready
    : 'isready'
    ;

ucinewgame
    : 'ucinewgame'
    ;

stop
    : 'stop'
    ;

quit
    : 'quit'
    ;

setoption: 'setoption' 'name' optionname ('value' optionvalue)?;

position: 'position' ('startpos' | 'fen' fen) ('moves' move+)?;

fen: STRING STRING STRING STRING STRING STRING;

go: 'go' goparam?;

goparam
    : go_infinite
    | 'depth' depthparam
    | 'movetime' movetime
    | 'wtime' wtime 'btime' btime 'winc' winc 'binc' binc ('movestogo' movestogo)?
    ;

go_infinite
    : 'infinite'
    ;

depthparam: STRING;
movetime: STRING;
wtime: STRING;
btime: STRING;
winc: STRING;
binc: STRING;
movestogo: STRING;

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

optionvalue: STRING;

optiontype
    : 'button'
    | 'string' 'default' STRING;

STRING: ~[ \t\r\n]+;

WS: [ \t\r\n]+ -> skip;

