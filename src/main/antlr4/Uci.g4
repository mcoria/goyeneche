// $antlr-format alignTrailingComments true, columnLimit 150, minEmptyLines 1, maxEmptyLinesToKeep 1, reflowComments false, useTab false
// $antlr-format allowShortRulesOnASingleLine false, allowShortBlocksOnASingleLine true, alignSemicolons hanging, alignColons hanging

grammar Uci;

uci: (request | response) EOF;

request
    : uciRequest
    | isReadyRequest
    ;

uciRequest: 'uci';

isReadyRequest: 'isready' ;

response
    : idResponse
    | uciOkResponse
    | readyOkResponse
    ;

idResponse: 'id' (name | author);

author: 'name' ' ' STRING;

name: 'author';

uciOkResponse: 'uciok';

readyOkResponse: 'readyok';

STRING : ~('\r' | '\n')+ ;

WS
    : [\r\n\t]+ -> skip
    ;
