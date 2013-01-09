Examples of abstract algebra structures used for real-world problems
====================================================================

I often noticed that maths structures (established formalisms as Eric Evans would say) are hidden everywhere in common business concepts.


As a software developer we are asked all the time to manage domain concepts like IMM expiry codes, yield curves, observation of market indexes with additional calculations on them what we need to trace, manipulate sequences of cashflows in many ways...

This is often done ad hoc with a gazillion of functions for each use-case, leading to thousands of lines of code hard to maintain because they hardly try to go to the essence of the concept, i.e. its underlying mathematical structure. 

We now have TDD, so we can tackle with more rigor these challenges, starting with tests to define what we want to achieve. The funny thing is that in the process, you can recognize strange maths creature you had seen at school many years ago. Even if you were not friend with that kind of maths at school, now on a real example all this math-ish stuff definitely becomes more interesting!

Draw on established formalisms
------------------------------

Wikipedia is your friend!

Do I still need TDD when I have a complete formal solution?
-----------------------------------------------------------

The process of TDD remains indeed very helpful not to get lost in every possible direction along the way. Even when you have found a formal structure that could solve your problem in one go, even in a "formal proof-ish fashion", perhaps you need less tests to verify the correctness, but you sure still need tests to think on the specification part of the problem. 


Try it yourself as a kata
-------------------------
This examples suggested here are also coding katas you could try.

In closing
----------
Maths structures are hidden behind many common business concepts. And I look for them whenever I can!

See the [first blog post](http://cyrille.martraire.com/2013/01/tdd-vs-math-formalism-friend-or-foe/) on my my blog







