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

uci: 'uci';

isready: 'isready';

ucinewgame: 'ucinewgame';

stop: 'stop';

quit: 'quit';

setoption: 'setoption' 'name' setoption_name ('value' setoption_value)?;

position: 'position' ('startpos' | 'fen' fen) ('moves' move+)?;

setoption_name: STRING;

setoption_value
    : INTEGER
    | STRING
    | STRING_EMPTY;

fen: STRING STRING STRING STRING INTEGER INTEGER;

go: 'go' goparam*;

goparam
    : go_infinite
    | go_depth
    | go_movetime
    | go_wtime
    | go_btime
    | go_winc
    | go_binc
    | go_movestogo
    ;

go_infinite: 'infinite';

go_depth: 'depth' INTEGER;

go_movetime: 'movetime' INTEGER;

go_wtime: 'wtime' INTEGER;

go_btime: 'btime' INTEGER;

go_winc: 'winc' INTEGER;

go_binc: 'binc' INTEGER;

go_movestogo: 'movestogo' INTEGER;

response
    : uciok
    | readyok
    | id
    | option
    | bestmove
    ;

uciok: 'uciok';

readyok: 'readyok';

id: 'id' ('name' name | 'author' author);

bestmove: 'bestmove' best_move ('ponder' poder_move)?;

name: STRING*;

author: STRING*;

move: STRING;

best_move: STRING;

poder_move: STRING;

option: 'option' 'name' optionname 'type' optiontype;

optionname: STRING;

optiontype
    : optiontype_button
    | optiontype_string
    | optiontype_spin;

optiontype_button: 'button';

optiontype_string: 'string' 'default' STRING;

optiontype_spin: 'spin' 'default' INTEGER 'min' INTEGER 'max' INTEGER;

INTEGER: [0-9]+;

STRING_EMPTY: '<empty>';

STRING: ~[ \t\r\n]+;

WS: [ \t\r\n]+ -> skip;