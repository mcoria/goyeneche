package net.chesstango.uci.protocol.internal;

import net.chesstango.uci.protocol.UCICommand;
import net.chesstango.uci.protocol.UCICommandUnknown;
import net.chesstango.uci.protocol.requests.*;
import net.chesstango.uci.protocol.requests.go.ReqGoDepth;
import net.chesstango.uci.protocol.requests.go.ReqGoFast;
import net.chesstango.uci.protocol.requests.go.ReqGoInfinite;
import net.chesstango.uci.protocol.requests.go.ReqGoTime;
import net.chesstango.uci.protocol.responses.RspBestMove;
import net.chesstango.uci.protocol.responses.RspId;
import net.chesstango.uci.protocol.responses.RspReadyOk;
import net.chesstango.uci.protocol.responses.RspUciOk;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mauricio Coria
 */
public class UCIDecoder {

    /**
     * @param input
     * @return
     */
    public UCICommand parseMessage(String input) {
        UCICommand result = null;

        String[] words = input.split(" ");

        if (words.length > 0) {
            String command = words[0].toUpperCase();
            result = switch (command) {

                // ====================== REQUESTS
                case "UCI" -> new ReqUci();
                case "SETOPTION" -> parseSetOption(words);
                case "UCINEWGAME" -> new ReqUciNewGame();
                case "POSITION" -> parsePosition(words);
                case "QUIT" -> new ReqQuit();
                case "ISREADY" -> new ReqIsReady();
                case "GO" -> parseGo(words);
                case "STOP" -> new ReqStop();

                // ====================== RESPONSES
                case "READYOK" -> new RspReadyOk();
                case "UCIOK" -> new RspUciOk();
                case "BESTMOVE" -> parseBestMove(words);
                case "ID" -> parseId(words);

                // ====================== UNKNOWN
                default -> new UCICommandUnknown(input);
            };
        }
        return result;
    }

    private UCICommand parseId(String[] words) {
        UCICommand result = null;
        if (words.length > 2) {
            String typeStr = words[1].toUpperCase();
            RspId.RspIdType type = null;
            if (RspId.RspIdType.AUTHOR.toString().equalsIgnoreCase(typeStr)) {
                type = RspId.RspIdType.AUTHOR;
            } else if (RspId.RspIdType.NAME.toString().equalsIgnoreCase(typeStr)) {
                type = RspId.RspIdType.NAME;
            }

            if (type != null) {
                StringBuilder sb = new StringBuilder();
                for (int i = 2; i < words.length; i++) {
                    sb.append(words[i]);
                    if (i < words.length - 1) {
                        sb.append(" ");
                    }
                }

                result = new RspId(type, sb.toString());
            }
        }
        return result == null ? new UCICommandUnknown(words.toString()) : result;
    }

    private UCICommand parseBestMove(String[] words) {
        String bestMove = words[1];
        String ponderMove = null;
        if (words.length == 4) {
            if ("ponder".equalsIgnoreCase(words[2])) {
                ponderMove = words[3];
            }
        }
        return new RspBestMove(bestMove, ponderMove);
    }

    private UCICommand parseSetOption(String[] words) {
        return new ReqSetOption(words[2], words[4]);
    }


    private UCICommand parseGo(String[] words) {
        ReqGo result = null;
        if (words.length > 1) {
            String goType = words[1].toUpperCase();
            switch (goType) {
                case "INFINITE":
                    result = new ReqGoInfinite();
                    break;
                case "DEPTH":
                    result = parseGoDepth(words);
                    break;
                case "MOVETIME":
                    result = parseGoMoveTime(words);
                    break;
                case "WTIME":
                case "BTIME":
                case "WINC":
                case "BINC":
                    result = parseGoMoveByClock(words);
                    break;
                default:
                    break;
            }
        } else {
            result = new ReqGoInfinite();
        }
        return result == null ? new UCICommandUnknown(words.toString()) : result;
    }

    private ReqGo parseGoDepth(String[] words) {
        ReqGoDepth result = new ReqGoDepth();

        String depth = words[2].toUpperCase();
        int depthInt = Integer.parseInt(depth);

        result.setDepth(depthInt);

        return result;
    }

    private ReqGo parseGoMoveTime(String[] words) {
        ReqGoTime result = new ReqGoTime();

        String timeOut = words[2].toUpperCase();
        int timeOutInt = Integer.parseInt(timeOut);

        result.setTimeOut(timeOutInt);

        return result;
    }

    private ReqGo parseGoMoveByClock(String[] words) {
        ReqGoFast result = new ReqGoFast();

        for (int i = 1; i < words.length; i += 2) {
            switch (words[i]) {
                case "wtime":
                    result.setWTime(Integer.parseInt(words[i + 1]));
                    break;
                case "winc":
                    result.setWInc(Integer.parseInt(words[i + 1]));
                    break;
                case "btime":
                    result.setBTime(Integer.parseInt(words[i + 1]));
                    break;
                case "binc":
                    result.setBInc(Integer.parseInt(words[i + 1]));
                    break;
            }
        }


        return result;
    }


    private UCICommand parsePosition(String[] words) {
        UCICommand result = null;
        if (words.length > 1) {
            String positionType = words[1].toUpperCase();
            switch (positionType) {
                case "FEN":
                    result = parsePositionFEN(words);
                    break;
                case "STARTPOS":
                    result = parsePositionSTARTPOS(words);
                    break;
                default:
                    break;
            }
        }
        return result == null ? new UCICommandUnknown(words.toString()) : result;
    }

    private UCICommand parsePositionSTARTPOS(String[] words) {
        List<String> moves = new ArrayList<String>();
        if (words.length > 2) {
            String movesword = words[2].toUpperCase();
            if ("MOVES".equals(movesword) && words.length > 3) {
                for (int i = 3; i < words.length; i++) {
                    moves.add(words[i]);
                }
            }

        }
        return new ReqPosition(moves);
    }


    private UCICommand parsePositionFEN(String[] words) {
        boolean readingFen = true;
        String fenString = "";
        List<String> moves = new ArrayList<>();
        for (int i = 2; i < words.length; i++) {
            if (readingFen) {
                if ("MOVES".equalsIgnoreCase(words[i])) {
                    readingFen = false;
                } else {
                    if (fenString.isEmpty()) {
                        fenString = fenString.concat(words[i]);
                    } else {
                        fenString = fenString.concat(" " + words[i]);
                    }
                }
            } else {
                moves.add(words[i]);
            }
        }

        return new ReqPosition(fenString, moves);
    }

}
