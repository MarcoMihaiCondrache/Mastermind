# Mastermind Game

- [Execution and compilation](#execution-and-compilation)
- [Rules](#rules)
- [Documentation](#code-documentation)
- [License](#license)

## Execution and compilation

### Execution
The compiled jar of the project can be found at the release section of this repository.

### Compilation
The project can be found and compiled in src directory with every java compiler. (Java 8+)

## Rules

The game is usually played in two players (in this case human and computer). One player becomes the codemaker (computer), the other the codebreaker. The codemaker chooses a pattern of four/six/eight (based on level) code pegs. Duplicates and blanks are not allowed, so the player could not choose four/six/eight (based on level) code pegs of the same color or blanks. The chosen pattern is placed in the four/six/eight (based on level) holes covered by the black color, visible to the codemaker but not to the codebreaker.

The codebreaker tries to guess the pattern, in both order and color, within eight turns. Each guess is made by placing a row of code pegs on the decoding board. Once placed, the codemaker provides feedback by placing from zero to four/six/eight (based on level) key pegs in the small holes of the row with the guess. A red key peg is placed for each code peg from the guess which is correct in both color and position. A white key peg indicates the existence of a correct color code peg placed in the wrong position.

Once feedback is provided, another guess is made; guesses and feedback continue to alternate until either the codebreaker guesses correctly (codebreaker wins), or all rows of the decoding boards are full (codemaker wins).

## Code documentation

The documentation can be found in the [docs](/docs) folder

## License

This game is developed under MIT [license](/LICENSE)