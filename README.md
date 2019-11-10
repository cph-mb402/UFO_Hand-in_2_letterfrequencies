# Letter frequencies
Simple program used to illustrate performance problems. You should be able to optimize this program to run about twice as fast.


## Documentation of the current performance of the program, including why you believe your performance measurements are free of errors.
The program has been timed by running it in a console without any background processes (aside from Windows itself). The program gave out the time it took our method to run in milliseconds. Saved the milisecond results gotten from running the program 5 times in a for loop as a comment.

## An explanation of the bottlenecks in the program.
The bottleneck is caused by accessing to get from the HashMap as much as we add to it.

## A hypothesis of what is causing the problem, and a changed program which is improved to solve the problem.
Found out that the use of an Integer, Long HashMap is more inneficient than using a Character, Long HashMap and using a character buffer in our tally method.

## Documentation of the new performance, again including documentation that the measurements are free of errors.
Used the same timing method as before.
