This repo contains a class for running timing and size comparisons for Collections. Feel free to have a play
around. 

The project uses Maven, so you should be able to run it in all Java IDEs.

To run the scenarios the main method is located in the com.calebstride.analysis.Main class.

This is overengineered for all that it does. I wrote this to try and make it as generic as possible for expansion.  

Some example results:
```text
Integer Collections with 5000 elements running each process 1000 times

           |   add (ns) |     remove (ns) |   contains (ns) |  size (ns) | bytes (KB) |
 ArrayList |         89 |            2561 |            2347 |         86 |        105 |
   HashSet |        121 |             198 |             108 |         88 |        272 |
   TreeSet |        336 |             657 |             265 |         96 |        280 |
LinkedList |        122 |           29454 |           12620 |         93 |        200 |
ArrayDeque |        107 |           43148 |           30166 |         96 |        105 |
```