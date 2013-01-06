Examples of abstract algebra structures used for real-world problems


Cyclic Group of codes for financial contract expiry dates
=========================================================

I often noticed that maths structures (established formalisms as Eric Evans would say) are hidden everywhere in common business concepts.

Dates and how we take liberties with them for trading of financial instruments offer a good example: traders of futures often use a notation like 'U8' to describe an expiry date like September 2018; 'U' means September, and the '8' digit refers to 2018, but also to 2028, and 2038 etc. We notice this notation only works for 10 years, and it recycles each codes every decade. 

In the case of IMM contract codes, we only care about quarterly dates on March (H), June (M), September (U) and December (Z): only 4 possibilities, combined with 10 possible year digits, hence 40 different codes in total.

As a software developer we are asked all the time to manage such IMM expiry codes, sort them, find the next leading month contract, enumerate the next 24 codes etc. This is often done ad hoc with a gazillion of functions for each use-case, leading to thousands of lines of code, and hard to maintain because they involve parsing of the 'U8' format everytime we want to calculate something.

Now we have TDD, so we can tackle this topic with more rigor, starting with tests to define what we want to achieve. The funny thing is that in the process, the cyclic logic of the IMM codes strongly reminded me of the cyclic group Z/nZ. I had met this strange maths creature at school, where I had hard time to see the point. But now on a real example it was looking definitely more interesting!

Draw on established formalisms
------------------------------

Now we also have Wikipedia, so it was easy to find out more on the established formalism of [Cyclic Groups](http://en.wikipedia.org/wiki/Cyclic_group "Cyclic Groups"). In particular:

> Every finite cyclic group is isomorphic to the group { [0], [1], [2], ..., [n − 1] } of integers modulo n under addition

The Wikipedia page also mentions some product concept of cyclic groups in relation with their order (here the numer of elements). Looks like it is the math-ish way to say that 4 possibilities for month combined with 10 possible year digits give 40 different codes in total.

So what? Sounds like we could identity the set of the 4 months to a cyclic group, the set of the 10 year digits to another, and that even the combination (product) of both is also a cyclic group of order 40. So what?

Because we've just seen that there is an isomorphism between ANY finite cyclic group and the cyclic group of integer of the same order, we can just integer logic (plain integers and the modulo operator) to simplify the implementation big time!

Do I still need TDD when I have a complete formal solution?
-----------------------------------------------------------

I must insist that I did not came to this conclusion as easily. The process of TDD was indeed very helpful not to get lost in every possible direction along the way. Even when you have found a formal structure that could solve your problem in one go, even in a "formal proof-ish fashion", perhaps you need less tests to verify the correctness, but you sure still need tests to think on the specification part of the problem. 

Partial order in a cyclic group
-------------------------------

Given a list of IMM codes we often need to sort them for display. The problem is that a cyclic group has no total order, the ordering depends on where you are in time. Let's take the example of the days of the week that also form a cycle: MONDAY, TUESDAY, WEDNESDAY...SUNDAY, MONDAY etc. Is Monday before WEDNESDAY? Not if we focus on the future and we're on TUESDAY. If we're on TUESDAY, MONDAY is next MONDAY and comes after WEDNESDAY.

Once we map that problem to the cyclic group of integers, it becomes easy to shift both operands of the comparison toward 0 before comparing them in a safe way. Again, this trick is made possible by the freedom to experiment given by the TDD tests. As long as we're still green, we can go ahead and try any approach.

Try it as a kata
----------------
This example is also a good coding kata. Given a simple presentation of the format of an IMM contract code, you can choose to code the sort, find the next and previous code, and perhaps even optimize for memory (cache the instances, e.g. lazily) and speed (cache the toString() value, e.g. in the constructor).

In closing
----------
Maths structures are hidden behind many common business concepts. And I look for them whenever I can!







