Examples of abstract algebra structures used for real-world problems


Monoid of a fixing observation with its complete traceability
=============================================================

Observation of of the value of an index on the market
----------------------------------------------------- 

In finance many financial instruments give payments that are calculated along the life of the instrument based on what happens on the markets. This means that at some predefined date we observe a market reference, e.g. a market index like LIBOR 3M (3 months) to snapshot its value.


For instance, Floater bonds pay interests, or "coupons", not according to a fixed rate (e.g. 5%), but according to market interests rates indexes like LIBOR, with an additional additive margin on top, or even a multiplicative coefficient, that may even be negative sometime.

Let's take some examples:
* A Floater bond on LIBOR 3M: if LIBOR 3M = 3.25%, then fixing to use to pay interests = 3.25%
* A Floater bond on LIBOR 3M + 20bp (0.20 point) margin: if LIBOR 3M = 3.25%, then the fixing used to pay interests = 3.25% + 20bp = 3.45%
* A "Reverse Floater" bond on 4.10% - LIBOR 3M: if LIBOR 3M = 3.25%, then the fixing used to pay interests = 4.10% - 3.25% = 0.85%
* A Funky levered "x2" bond on LIBOR 3M * 2: if LIBOR 3M = 3.25%, then the fixing used to pay interests = 3.25% * 2 = 6.50%

Keeping track of the calculation, step by step
----------------------------------------------
There are norms that require that we keep track of every step of the calculations, so that to explain in details why the result is as it is in case of dispute. You can do that with logging frameworks, but there's a better way: keep track of any relevant calculations within the result itself!

The idea is that each operation performs its calculation as usual, but also appends a log of what happened to the internal description field. The internal description field keeps on growing along each operation applied.

We're of course working on Value Objects, where each remembers how it was calculated from other. This is similar to the typical persistent data structures in functional programming languages.

Draw on established formalisms
------------------------------

As usual Wikipedia tells us what we need to know on the established formalism of [Monoid](http://en.wikipedia.org/wiki/Monoid "Monoid"). In particular:

> ...











