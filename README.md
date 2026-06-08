# WordSquare

## Overview

`WordSquare` is a small Java command-line application that builds a word square from a set of input characters.

The program accepts:

- `rowSize`: the length of each word and the number of rows/columns in the square
- `inputString`: a string containing exactly `rowSize * rowSize` characters

It attempts to form an N×N grid of words where each row and each column contains the same sequence of words.

## What the application does

The application reads a dictionary of candidate words and uses a backtracking algorithm to build the square. It ensures that each word:

- has the correct length,
- can be formed from the available characters, and
- matches the current prefix constraints imposed by previously chosen rows.

## How the application solves the problem

The solver works by:

1. loading dictionary words of the correct length,
2. filtering candidates that can be formed from the remaining character counts,
3. adding words one row at a time,
4. checking that each chosen word matches the vertical prefix created by earlier rows,
5. backtracking when a partial square cannot be completed.

This approach ensures the final square is valid both row-wise and column-wise.

## Dependencies

- Java Development Kit (JDK) 11 or newer
- No external libraries are required
- `dictionary.txt` must be available in the working directory when running the program

## Project layout

- `src/main/java/` contains production source code
- `src/test/java/` contains unit tests
- `src/main/resources/` can hold the dictionary resource to copy into the working directory

## Build and run

Compile production sources into an output directory:

```bash
javac -d out src/main/java/*.java
```

Copy the dictionary into the output directory:

```bash
cp src/main/resources/dictionary.txt out.
```

Run the program from the project root:

```bash
java -cp out Main <rowSize> <inputString>
```

Working Examples include:

```bash 
java -cp out Main 4 aaccdeeeemmnnnoo
java -cp out Main 5 aaaeeeefhhmoonssrrrrttttw
java -cp out Main 5 aabbeeeeeeeehmosrrrruttvv
java -cp out Main 7 aaaaaaaaabbeeeeeeedddddggmmlloooonnssssrrrruvvyyy
```


## Running tests

Compile test sources against the production output:

```bash
javac -d out -cp out src/test/java/WordSquareBuilderTest.java
```

Run the tests:

```bash
java -ea -cp out WordSquareBuilderTest
```
