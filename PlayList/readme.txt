I have made a rest controller which acts as the entry point of the program.
The port number is default 8080

The load Songs method , loads the number of sings required or which is set by the user.
All this can also be done using the @PathVariable , if hard coding is not allowed

The start method is the Rest Api we hit at first 
It automatically calls the next method internally if no external reuest is received

The next method shuffles and changes the songs.

the previous method plays the immediate song which was played before.
for now it only stores one value , if required it can turned into a map as well which stores more than one value.

the stop method terminates after 100 songs have been played and resets all the variables.



For now I have written everything in the controller class , if required I can delegate them to service and helper classes.

I have chosen maps instead of arrays and lists to reduce complexity. As lists and array would require checking every song if it has been played before or not 