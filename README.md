Examples of abstract algebra structures used for real-world problems


Cyclic Group of codes for financial contract expiry dates
=========================================================

Several times I noticed that maths structures (established formalisms as Eric Evans would say) are hidden everywhere in common business concepts. 

Dates and how we take liberties with them for trading of financial instruments offer a good example: traders of futures often use a notation like 'U8' to describe an expiry date like September 2018; 'U' means September, and the '8' digit refers to 2018, but also to 2028, and 2038 etc. We notice this notation only works for 10 years, and it recycles each codes every decade. 

In the case of IMM contract codes, we only care about quarterly dates on March (H), June (M), September (U) and December (Z): only 4 possibilities, combined with 10 possible year digits, hence 40 different codes in total.

As a software developer we are asked all the time to manage such IMM expiry codes, sort them, find the next leading month contract, enumerate the next 24 codes etc. This is often done ad hoc with a gazillion of functions for each use-case, leading to thousands of lines of code, and hard to maintain because they involve parsing of the 'U8' format everytime we want to calculate something.

Now we have TDD, so we can tackle this topic with more rigour, starting with tests to define what we want to achieve. The funny thing is that in the process, the cyclic logic of the IMM codes strongly reminded me of the cyclic group Z/nZ. I had met this strange maths creature at school, where I had hard time to see the point. But now on a real example it was looking definitely more interesting!

Now we also have Wikipedia, so it was easy to find out more on the established formalism of [Cyclic Groups](http://en.wikipedia.org/wiki/Cyclic_group "Cyclic Groups"). In particular:

> Every finite cyclic group is isomorphic to the group { [0], [1], [2], ..., [n − 1] } of integers modulo n under addition

The Wikipedia page also mentions some product concept of cyclic groups in relation with their order (here the numer of elements). Looks like it is the math-ish way to say that 4 possibilities for month combined with 10 possible year digits give 40 different codes in total.

So what? Sounds like we could identity the set of the 4 months to a cyclic group, the set of the 10 year digits to another, and that even the combination (product) of both is also a cyclic group of order 40. So what?

Because we've just seen that there is an isomorphism between ANY finite cyclic group and the cyclic group of integer of the same order, maybe we can just integer logic to simplify the implementation.

I must insist that I did not came to this conclusion as easily. The process of TDD was indeed very helpful not to get lost in every possible direction along the way. Even when you have found a formal structure that could solve your problem in one go, even in a "formal proof-ish fashion", perhaps you need less tests to verify the correctness, but you sure still need tests to think on the specification part of the problem. 







