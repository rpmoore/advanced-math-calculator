# Overview #

Currently there are 3 branches of work occurring.  One is in relationship to changing the Way that the equations are represented by the System.  One is changing the lexer to use an automatic code generation tool to create (currently in evaluation).  The last one is looking at adding some linear algebra tools to the calculator.  Details follow.


# Details #

RPN Branch:
  * ~~Changing the equation representation from a tree to a stack~~
  * ~~Will speed up parsing the equation, and should be more stack friendly.~~
  * ~~This will be affected by the new lexer.~~


Lexer Branch:
  * ~~Carry over from getting lexer to work originally~~
  * ~~Propose to use JavaCC to generate a new lexer~~

Linear Branch:
  * Needs to be integrated with the current architecture
  * Need to figure out a way to represent the matrices in the ui
  * Planning out how to do the expressions while making the library extendable

Graphing Branch:
  * Adding graphing features to graph how an integral will appear.
  * Add graphing features to zoom in on an area with mouse scroll.
  * Add graphing feature to click and drag the area to move the graph around.
  * Add graph on a f(x) with x and y intercept finding.  Possibly add features to graph more than 1 equation and find where they intercept.

Trunk:
  * Add some usability features for right click copy and paste.