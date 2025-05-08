[![Java CI with Maven](https://github.com/mcoria/goyeneche/actions/workflows/maven.yml/badge.svg)](https://github.com/mcoria/goyeneche/actions/workflows/maven.yml)

# goyeneche

**goyeneche** is a Java library implementing the **UCI (Universal Chess Interface) Protocol**, enabling easy integration with chess engines or GUI controllers supporting this protocol.

## Features

- Provides a streamlined way to communicate with UCI-compatible chess engines.
- Send and receive UCI commands with ease.
- Built with Java, making it simple to integrate into existing Java projects.

## Prerequisites

- **Java 21** or higher
- **Maven** or **Gradle** for dependency management (optional)

## Installation

To use **goyeneche** in your project, add the dependency to your `pom.xml` (for Maven) or `build.gradle` (for Gradle).

### Maven

```xml
<dependency>
    <groupId>net.chesstango</groupId>
    <artifactId>goyeneche</artifactId>
    <version>1.0.3</version>
</dependency>
```

### Gradle

```groovy
implementation 'net.chesstango:goyeneche:1.0.3'
```

## Usage

Follow the steps below to integrate **goyeneche** into your project:

1. **Initialize and Configure the UCI Client**

   Set up the UCI client to connect with a UCI-compatible chess engine (e.g., Stockfish).

   ```java
   public class EngineSkeleton extends AbstractUCIEngine {

    public static void main(String[] args) {

        // Create an instance of the EngineSkeleton to handle UCI commands.
        EngineSkeleton engineSkeleton = new EngineSkeleton();

        // Configure the engine's output stream to forward responses to the GUI via standard output,
        // using a StringConsumer for protocol-compliant communication.
        engineSkeleton.setOutputStream(new UCIOutputStreamToStringAdapter(new StringConsumer(new OutputStreamWriter(System.out))));

        // Open the engine to prepare it for communication with the GUI.
        engineSkeleton.open();

        // Initialize and set up the UCIActiveStreamReader to read UCI commands from standard input.
        UCIActiveStreamReader uciActiveStreamReader = createUciActiveStreamReader(engineSkeleton);

        // Start processing UCI commands in the active reader loop.
        uciActiveStreamReader.run();

        // Close the engine after completing the communication.
        engineSkeleton.close();
    }



    private static UCIActiveStreamReader createUciActiveStreamReader(Consumer<UCICommand> uciCommandConsumer) {
        UCIActiveStreamReader uciActiveStreamReader = new UCIActiveStreamReader();

        // Configures the active stream reader to read input from the standard input (System.in)
        // by adapting it to the UCIInputStream interface through a StringSupplier.
        uciActiveStreamReader.setInputStream(new UCIInputStreamFromStringAdapter(new StringSupplier(new InputStreamReader(System.in))));

        // Directs the output of the active stream reader to be handled by the engineSkeleton's accept method,
        // enabling the engine to process UCI commands received from the input.
        uciActiveStreamReader.setOutputStream(uciCommandConsumer::accept);

        // Return the configured UCIActiveStreamReader instance to handle communication with the GUI.
        return uciActiveStreamReader;
    }


    @Override
    public void do_uci(ReqUci reqUci) {
        replyResponse(new RspId(RspId.RspIdType.NAME, "Skeleton 1.0"));
        replyResponse(new RspId(RspId.RspIdType.AUTHOR, "John Doe"));
        replyResponse(new RspUciOk());
    }


    @Override
    public void do_isReady(ReqIsReady cmdIsReady) {
        replyResponse(new RspReadyOk());
    }
    

    @Override
    public void do_go(ReqGo reqGo) {
        replyResponse(new RspBestMove("c2c4"));
    }
   }
   ```

   Execute EngineSkeleton and start exchanging commands as follows:
   ```
   >> uci
   << id name Skeleton 1.0
   << id author John Doe
   << uciok
   >> isready
   << readyok
   >> ucinewgame
   >> position startpos moves e2e4 e7e5
   >> go infinite
   << bestmove c2c4
   >> quit
   ```

2. **Commands for UCI Protocol**

   Here are some commonly used UCI commands you can send through the client:

    - `uci`: Initialize the UCI mode.
    - `isready`: Check if the engine is ready.
    - `usinewgame`: Initialize a new game.
    - `position`: Set up the board position.
    - `go`: Start calculating the best move.
    - `stop`: Stop the current calculation.

## Contributing

Contributions are welcome! Feel free to open issues and submit pull requests.

## License

This project is licensed under the **BSD 3-Clause License**. See the `LICENSE` file for details.

