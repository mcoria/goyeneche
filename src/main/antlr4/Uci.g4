// $antlr-format alignTrailingComments true, columnLimit 150, minEmptyLines 1, maxEmptyLinesToKeep 1, reflowComments false, useTab false
// $antlr-format allowShortRulesOnASingleLine false, allowShortBlocksOnASingleLine true, alignSemicolons hanging, alignColons hanging

grammar Uci;

command: (request | response) EOF;

request
    : uci
    | isready
    | ucinewgame
    ;

uci: 'uci';

isready: 'isready' ;

ucinewgame: 'ucinewgame';

response
    : id
    | uciok
    | readyok
    ;

id: 'id' (name | author);

author: 'name' ' ' STRING;

name: 'author';

uciok: 'uciok';

readyok: 'readyok';

STRING : ~('\r' | '\n')+ ;

WS
    : [\r\n\t]+ -> skip
    ;
