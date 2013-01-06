Examples of abstract algebra structures used for real-world problems


Cyclic Group of codes for financial contract expiry dates
=========================================================

I often noticed that maths structures (established formalisms as Eric Evans would say) are hidden everywhere in common business concepts.

Dates and how we take liberties with them for trading of financial instruments offer a good example: traders of futures often use a notation like 'U8' to describe an expiry date like September 2018; 'U' means September, and the '8' digit refers to 2018, but also to 2028, and 2038 etc. We notice this notation only works for 10 years, and it recycles each code every decade. 

In the case of IMM contract codes, we only care about quarterly dates on March (H), June (M), September (U) and December (Z): only 4 possibilities, combined with 10 possible year digits, hence 40 different codes in total.

As a software developer we are asked all the time to manage such IMM expiry codes, sort them, find the next leading month contract, enumerate the next 24 codes etc. 

This is often done ad hoc with a gazillion of functions for each use-case, leading to thousands of lines of code hard to maintain because they involve parsing of the 'U8' format everytime we want to calculate something.

We now have TDD, so we can tackle this topic with more rigor, starting with tests to define what we want to achieve. The funny thing is that in the process, the cyclic logic of the IMM codes strongly reminded me of the cyclic group Z/nZ. I had met this strange maths creature at school many many years ago, and I had a hard time with it by the way. But now on a real example it was definitely more interesting!

Draw on established formalisms
------------------------------

Now we also have Wikipedia, so it was easy to find out more on the established formalism of [Cyclic Groups](http://en.wikipedia.org/wiki/Cyclic_group "Cyclic Groups"). In particular:

> Every finite cyclic group is isomorphic to the group { [0], [1], [2], ..., [n − 1] } of integers modulo n under addition

The Wikipedia page also mentions a concept of the product of cyclic groups in relation with their order (here the number of elements). Looks like this is the math-ish way to say that 4 possibilities for quarterly months combined with 10 possible year digits give 40 different codes in total.

So what? Sounds like we could identity the set of the 4 months to a cyclic group, the set of the 10 year digits to another, and that even the combination (product) of both also looks like a cyclic group of order 10 * 4 = 40 (even though the addition operation will not be called like that). So what?

Because we've just seen that there is an isomorphism between ANY finite cyclic group and the cyclic group of integer of the same order, we can just switch to the integer cyclic group logic (plain integers and the modulo operator) to simplify the implementation big time.

Basically the idea is to convert from the code to an integer in the range 0..39, then do every operation on this 'ordinal' integer instead of the actual code. Then we can format back to a code whenever we really need it.

Do I still need TDD when I have a complete formal solution?
-----------------------------------------------------------

I must insist that I did not came to this conclusion as easily. The process of TDD was indeed very helpful not to get lost in every possible direction along the way. Even when you have found a formal structure that could solve your problem in one go, even in a "formal proof-ish fashion", perhaps you need less tests to verify the correctness, but you sure still need tests to think on the specification part of the problem. 

Partial order in a cyclic group
-------------------------------

Given a list of IMM codes we often need to sort them for display. The problem is that a cyclic group has no total order, the ordering depends on where you are in time. Let's take the example of the days of the week that also form a cycle: MONDAY, TUESDAY, WEDNESDAY...SUNDAY, MONDAY etc. Is Monday before WEDNESDAY? Not if we focus on the future and we're on TUESDAY. If we're on TUESDAY, MONDAY is next MONDAY and comes after WEDNESDAY.

This is why we cannot unfortunately just implement Comparable to take care of the ordering. Because we need to consider a date-aware partial order, we need to resort to a Comparator that takes the reference IMM code in its constructor. 

Once we identify that situation to the cyclic group of integers, it becomes easy to shift both operands of the comparison to 0 before comparing them in a safe (total order-ish) way. Again, this trick is made possible by the freedom to experiment given by the TDD tests. As long as we're still green, we can go ahead and try any approach.

Try it as a kata
----------------
This example is also a good coding kata. Given a simple presentation of the format of an IMM contract code, you can choose to code the sort, find the next and previous code, and perhaps even optimize for memory (cache the instances, e.g. lazily) and speed (cache the toString() value, e.g. in the constructor).

In closing
----------
Maths structures are hidden behind many common business concepts. And I look for them whenever I can!







