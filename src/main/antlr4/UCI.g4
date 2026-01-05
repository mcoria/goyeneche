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

fen: STRING STRING STRING STRING INTEGER INTEGER;

go: 'go' goparam?;

goparam
    : go_infinite
    | go_depth
    | go_movetime
    | go_time
    ;

go_infinite: 'infinite';

go_depth: 'depth' INTEGER;

go_movetime: 'movetime' INTEGER;

go_time: 'wtime' INTEGER 'btime' INTEGER 'winc' INTEGER 'binc' INTEGER ('movestogo' INTEGER)?;

// wtime: INTEGER;
// btime: INTEGER;
// winc: INTEGER;
// binc: INTEGER;
// movestogo: INTEGER;

response
    : uciok
    | readyok
    | 'id' id
    | 'bestmove' move ('ponder' move)?
    | 'option' option
    ;

uciok: 'uciok';

readyok: 'readyok';

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

INTEGER: [0-9]+;

STRING: ~[ \t\r\n]+;

WS: [ \t\r\n]+ -> skip;