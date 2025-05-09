TrigCalcV3

A Java console program that helps you:
- Calculate exact trig values for special angles
- Evaluate decimal trig values for any radian input
- Practice with flashcards from the unit circle

Features:
- Accepts input like pi/3, 2pi, -7π/6, or plain decimals
- Identifies special angles and gives exact answers (like √3/2)
- Normalizes co-terminal and negative angles
- Finds reference angles and applies the correct sign based on quadrant
- Flashcard mode to drill trig values without memorizing a chart
- Parses trig expressions without any external libraries

How to Run:
Compile: javac *.java
Run: java TrigCalcV3

Files:
- TrigCalcV3.java — main menu and launcher
- TrigCalculator.java — evaluates trig values, exact or decimal
- FlashcardFactory.java — runs the flashcard quiz loop
- Flashcard.java — holds one trig flashcard (function + angle + value)
- PiCleaner.java — converts strings like 3pi/4 into radians, finds reference angle, applies correct sign

License:
MIT — do what you want, just don’t pretend you wrote it.
