Use this file to record your reflection on this assignment.

What are your initial impressions of how `Unit Testing` affects your programming?
To be honest, my first impression was that unit testing felt like extra work. It definitely takes more time to set up individual test cases than it does to just throw everything into a main method and look at the print statements. However, once I started running the tests, I realized how helpful it actually is.
Instead of just hoping the code works, the tests forced me to look at the "state" of my objects. It was a relief to see a test fail and know exactly which line was the problem, rather than staring at a long console output trying to figure out where things went wrong. It makes the programming process feel much more organized.

What worked, what didn't, what advice would you give someone taking this course in the future?
I found that using really specific numbers (like 32 for memory or 1200 for price) worked well because it made the hardcoded bugs (like the 16 and 0) stand out immediately.
I ran into some frustrating compiler errors early on because of JUnit versions. I realized I was trying to put the "failure message" at the end of the assertEquals line, but the version we are using requires the message to be the very first thing in the parentheses. Once I fixed that and added the static import, everything smoothed out.
If you're taking this course in the future, my advice is to not look at testing as a separate chore you do at the end. Try to write a test as soon as you finish a single method. It feels like it’s slowing you down, but it actually saves you hours of debugging later.