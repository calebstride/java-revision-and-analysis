This repo contains several analysis and revision resources for different Java concepts. Most of these are referenced in
my blog or just used to practice / learn about Java features I'm not that familiar with.

The project uses Maven, so you should be able to run it in all Java IDEs. It contains multiple main classes to quickly
run different scenarios.

# Collections Method Analysis
The com.calebstride.analysis.collection package contains resources for running timing and size comparisons for different 
Collections and their methods. Feel free to have a play around.

To run the scenarios the main method is located in the com.calebstride.analysis.collection.Main class.

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

# Multithreading
The com.calebstride.analysis.multithread package contains multithreading examples and different ways to run 
multiple threads.