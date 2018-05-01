# Minimum number of vechicles puzzle

## Problem Statement

N stations - S1, S2, S3, ..., SN  
For each station some given minimum number of trucks must leave the station each morning.  
(e.g. m1, m2, m3, ..., mN, with each m_i >= 0)  
Trucks leaving S_i arrive at S_(i+1) in T_i days ∀ 1 <= i < N; for i = N, i.e trucks leaving SN reach S1 in TN days (with each T_i >= 1).  
Find the minimum number of trucks that are required (assmuing all m_i and T_i s are known). 

## Solution

In the general case any number of vehicles (greater than minimum required) can leave each station (i.e at S1 on day1 a1 vehicles leave and on day2 a2 vehicles leave. Both a1 & a2 need to be >= m1, but it is not necessary that a1 = a2).  
But to make the solution a bit simpler I have imposed an additional constraint that the same number of vehicles should leave any station on all days, i.e, a1 must = a2 (or in other words the number of vehicles leaving any given station cannot should remain constant on each day).  
This strategy may not give the optimal solution (or it may but I couldn't prove / disprove it yet), but it does gives us a decent lower bound and it makes it much easier to reason about the problem.

Lets consider carefully a part of the problem -  
 - Consider two adjacent stations Sb & Sa. Trucks leave from Sb and arrive to Sa in N days. The minimum number of trucks that must leave Sb & Sa are n and m respectively (ma = n, mb = m).  

I'll make some claims about this situation (and also prove them) which make the actual solution much simpler

### Claim 1 - Minimum of Trucks at Sa at Start

Minimum number of trucks required at Sa at the start (0th day) is N*m.

#### Proof
Assume that on the 0th day A trucks are there at Sa.  
We can make the following table of # of days vs number of trucks at Sa.

Day -> Number of trucks  
0 -> A  
1 -> A - m  
2 -> A - 2m  
3 -> A - 3m  
...  
N-1 -> A - (N-1)m

Since untill day N-1 no cars from Sb has reached Sa yet.  
Also since on day N-1 atleast m cars must leave Sa we have -

A - (N-1)m >= m  
=> A >= N*m

### Claim 2 - Minimum is maximum of n & m

Initially we have been given that minimum number of trucks that should leave Sb is n and Sb is m.  
But actually the minimum number of trucks that should leave both Sa & Sb = max(n, m).

#### Proof

##### Case 1 : n > m

Let's say that the number of trucks that leave Sa each day is a (with a >= m) and it is less than n, i.e, a < n.  
Let's assume on day N-1 we have A + a trucks at Sa, with A >= 0.  
So on day N the number of trucks at Sa >= A + n (since a trucks left Sa and atleast n trucks arrive).  
So we have the following table -

N   : >= A + n  
N+1 : >= A + n + (n - a)  
N+2 : >= A + n + 2(n - a)  
N+3 : >= A + n + 3(n - a)  
...

we see that the number of cars at Sa is monotonically increasing with each day (since a > n). Thus any finite number of trucks will not suffice in this senario.  
This can be avoided only when a is atleast n. i.e a >= n.   
Hence the minimum number of trucks leaving both Sa and Sb = n = max (n, m)

##### Case 2 : m > n 

Assume that number of trucks leaving Sb each day is b (with b >= n) and it is less than m, i.e. b < m.  
Assume that we start with (A + N*m) trucks at Sa at day 0, with A>=0.  
Then on day N-1 the number of trucks at Sa is <= A + m.
So we have the following table -

N-1 : <= A + m   
N   : <=  A + b (since atleast m left and b arrived)  
N+1 : <= A + b - (m - b)  
N+2 : <= A + b - 2(m - b)  
N+3 : <= A + b - 3(m - b)  
...

We see that the negative term is monotically is increasing and hence the upper bound is decreasing and will become negative after some days.  
But it is not possible and thus a contradiction.  
It can be avoided only when b >= m.
Hence the minimum number of trucks leaving both Sa and Sb = m = min(n, m)

### Actual Solution

Using the result from claim 2 we see that for any two pairs of adjacent stations (Sa and Sb), the minimum number of trucks leaving both of them is max (ma, mb).  
We can apply this result successivily and arrive at the result - 

if M = max(m1, m2, m3, ..., mN)  
then alteast M trucks must leave each station.

Now we shall use the result from claim1 - since we know the minimum number of trucks M leaving each station and time taken to reach each station from previous station is given by T_i the minimum number of trucks initially required at each station is given by - 

at S1 -> TN * M = A1  
at S2 -> T1 * M = A2   
at S3 -> T2 * M = A3  
...  
at SN -> T_(N-1) * M = AN    

and it is self evident that we use the minimum possible number A_i at each station at the start, and on each day send M trucks from each station we arrive at a correct solution, which thus is the optimal solution, given the constraints.  

Thus the minimum number of trucks required is  

M * (T1 + T2 + T3 + ... + TN)

we have hence arrived at a close form solution for the problem (after imposing the simplying condition mentioned before). This is actually trivial to implement in any programming language.

## Code

Code for the solution described above can be found in `MinTrucks.java` file.

### Usage

```
$ javac MinTrucks.java
$ java MinTrucks
```