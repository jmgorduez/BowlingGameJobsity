# Bowling Game. 
### Run app.
#### Run app without arguments.
```
$ make run
```
It runs the app using the file under: *src/main/resources/inputFile/input.txt*.

#### Run app with arguments.
```
$ make run file=src/main/resources/inputFile/input.txt
```
It runs the app using the file specified as argument.

#### Generate .jar file.
```
$ make jar
```
### Testing.

#### Run unit test.
```
$ make test
```
The result of unit tests will be available under: *build/reports/tests/test/*

#### Run integration test.
```
$ make it
```
The result of integration tests will be available under: *build/reports/tests/integrationTest/*

#### Run test coverage.
```
$ make coverage
```
The result of tests coverage will be available under: *build/reports/jacoco/test/html/*

### About desing.

#### Files directory.
```
├── domain
|   ├── abstractions
|   ├── enums
|   ├── frames
|   ├── parsers
|   └── readers
├── infrastructure
|   └── abstractions
└── utils   
```
* Under domain package can find the solution domain classes: 
    * Under *abstractions* package can find the interfaces.
    * Under *enums* package can find enums used.
    * Under *frames* package can find all of kind of frame.
    * Under *parsers* package can find concrete classes that parse a Frame object.
    * Under *readers* package can find concrete classes that read several scores.
* Under *infrastructure* package can find a class to present the result to the user. This package follows the same structure of *domain* package.
* Under *utils* package can find a utilitarian class with all of constants used in the app.



### Exercise description.
This coding exercise is an opportunity for you to demonstrate your ability to build a greenfield project, specifically a command-line application to
score a game of ten-pin bowling (https://en.wikipedia.org/wiki/Ten-pin_bowling#Rules_of_play)
The program should run from the command-line and take a text file as input: ‘bowling-game.txt’
The content of the input text file (e.g., ‘game.txt’) for several players bowling 10 frames each. This would be like:
Jeff 10
John 3
John 7
Jeff 7
Jeff 3
John 6
John 3
Jeff 9
Jeff 0
John 10
Jeff 10
John 8
John 1
Jeff 0
Jeff 8
John 10
Jeff 8
Jeff 2
John 10
Jeff F
Jeff 6
John 9
John 0
Jeff 10
John 7
John 3
Jeff 10
John 4
John 4
Jeff 10
Jeff 8
Jeff 1
John 10
John 9
John 0
Each line represents a player and a chance with the subsequent number of pins knocked down.
An 'F' indicates a foul on that chance and no pins knocked down (identical for scoring to a roll of 0).
The input shall be valid (i.e., no chance will produce a negative number of knocked down pins or more than 10, etc).
The rows are tab-separated.
The program should then output the scoring for the associated game. So for the above game for Jeff, the classic scoring would be
written:
Frame 1 2 3 4 5 6 7 8 9 10
Pinfalls X 7 / 9 0 X 0 8 8 / F 6 X X X 8 1
Score 20 39 48 66 74 84 90 120 148 167
a.
b.
c.
4.
a.
b.
5.
a.
b.
Your program should print out a similar score to standard out, in the format:
Here is the same output with hidden whitespace revealed:
For each player, print their name on a separate line before printing that player's pinfalls and score.
All values are tab-separated.
As seen into the above output, the output should calculate if a player scores a strike ('X'), a spare ('/') and allow for extra chances
in the tenth frame.
What you should deliver to Jobsity, a zip file containing:
The source code for a project that satisfies the above bowling problem written in Java (1.6 or up).
A text file containing instructions on how to compile and run the project (Gradle, Maven, shell script).
Your code will be evaluated on:
Clarity, design, extensibility and maintainability.
Testing and code coverage (e.g., for Java programs, using JUnit or other unit testing frameworks).
Further help:
Your program should be able to handle all possible cases of a game both including a game where all rolls are 0, all rolls are fouls (F) and
a perfect game, where all rolls are strikes:
Carl 10
Carl 10
Carl 10
Carl 10
Carl 10
Carl 10
Carl 10
Carl 10
Carl 10
Carl 10
Carl 10
Carl 10
Frame 1 2 3 4 5 6 7 8 9 10
Pinfalls X X X X X X X X X X X X
Score 30 60 90 120 150 180 210 240 270 300
