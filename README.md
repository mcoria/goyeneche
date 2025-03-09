[![Java CI with Maven](https://github.com/mcoria/uci-protocol/actions/workflows/maven.yml/badge.svg)](https://github.com/mcoria/uci-protocol/actions/workflows/maven.yml)

# chesstango-uci

**chesstango-uci** is a Java library implementing the **UCI (Universal Chess Interface) Protocol**, enabling easy integration with chess engines supporting this protocol.

## Features

- Provides a streamlined way to communicate with UCI-compatible chess engines.
- Send and receive UCI commands with ease.
- Built with Java, making it simple to integrate into existing Java projects.

## Prerequisites

- **Java 21** or higher
- **Maven** or **Gradle** for dependency management (optional)

## Installation

To use **chesstango-uci** in your project, add the dependency to your `pom.xml` (for Maven) or `build.gradle` (for Gradle).

### Maven

```xml
<dependency>
    <groupId>net.chesstango</groupId>
    <artifactId>uci-protocol</artifactId>
    <version>1.0.2</version>
</dependency>
```

### Gradle

```groovy
implementation 'net.chesstango:uci-protocol:1.0.2'
```

## Usage

Follow the steps below to integrate **chesstango-uci** into your project:

1. **Initialize and Configure the UCI Client**

   Set up the UCI client to connect with a UCI-compatible chess engine (e.g., Stockfish).

   ```java
   ```

2. **Commands for UCI Protocol**

   Here are some commonly used UCI commands you can send through the client:

    - `uci`: Initialize the UCI mode.
    - `isready`: Check if the engine is ready.
    - `usinewgame`: Initialize a new game.
    - `position`: Set up the board position.
    - `go`: Start calculating the best move.
    - `stop`: Stop the current calculation.

3. **Example Interaction**

   Suppose you want to analyze a game position. You can send commands like this:

   ```java

   ```

## Contributing

Contributions are welcome! Feel free to open issues and submit pull requests.

## License

This project is licensed under the **MIT License**. See the `LICENSE` file for details.

