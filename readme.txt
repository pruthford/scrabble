Requirements:
  Requires Maven 3 or newer and Java JDK 8 or newer.

To build:
  mvn clean package

To run tests and coverage report:
  mvn clean verify

To run:
  java -jar ./target/artifacts/scrabble_jar/scrabble.jar

URL format:
    http://localhost:8080/words/<letters>

Example usage:
    > curl http://localhost:8080/words/hat
    ["hat","ah","ha","th","at","a"]

    > curl http://localhost:8080/words/zzz
    []

Memory Usage
The memory usage is relatively high to be able to facitilate fast run speed. The words are in a series of hashmaps and
hashsets acting as caches

Runtime analysis
The O(n) runtime of this program is N. A number of caches are used, but overall the it is a linear runtime.

github link
https://github.com/pruthford/scrabble
