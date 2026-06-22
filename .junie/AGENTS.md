# Project Development Documentation

## Build/Configuration Instructions

This project is a Java-based UCI protocol implementation. It uses Maven as its build automation tool and requires Java 25.

- **Requirements**: Java 25 (or higher).
- **Build**: Run the following command from the project root:
  ```bash
  mvn clean install
  ```
  This will compile the code, generate ANTLR4 sources, and run the tests.

## Testing Information

- **Framework**: The project uses **JUnit 5** for testing.
- **Location**: Test files are located in `src/test/java`.
- **Running Tests**:
  - Run all tests:
    ```bash
    mvn test
    ```
  - Run a specific test class:
    ```bash
    mvn -Dtest=TestClassName test
    ```
- **Adding New Tests**:
  - Create a new test class under `src/test/java/net/chesstango/goyeneche/...`.
  - Use JUnit 5 annotations (e.g., `@Test`) and assertions.
  - See `src/test/java/net/chesstango/goyeneche/UCIDecoderReqTest.java` for examples.

## Additional Development Information

- **ANTLR4**: The project uses ANTLR4 to parse UCI commands. The grammar file is located at `src/main/antlr4/UCI.g4`. The generated parser classes are located in `target/generated-sources/antlr4`.
- **Code Style**: Follow standard Java conventions.
