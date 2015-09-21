INTRODUCTION
------------
This program is a project for my CS 440 - Artificial Intelligence class. It will take a graphviz description
of a map and color all the "countries" in that map without any adjacent countries being the same color. This program
uses a heuristic to determine the best sequence of countries to color in order to color the map using the fewest number
of colors, no more than four.

REQUIREMENTS
------------
This program is written in Java 8.

INSTALLATION
------------
This code is built using gradle. Simply download the code, enter the root directory, and run "./gradlew build" or
"gradlew.bat build". If you want to prevent the unit tests from running, add -x test to the end of that command.
After running the command, a jar file should appear in build/libs. You can run the jar file with the command 
"java -jar [filename.jar]".
