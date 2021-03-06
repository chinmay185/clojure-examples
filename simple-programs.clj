; clojure buzzwords
; metaprogramming with macros, functions as first class citizens, keywords, immutability, higher-order functions, s-expression, forms, abstract syntax tree (AST), homoiconicity, code as data and data as code, 

; A valid s-expression is one that follows all the rules of Clojure and is called a form.
; Macros allow you to programmatically control the abstract syntax tree.
; Lisp stands for LISt Processing.

; find gcd of given numbers
; search for an element in the array and return its position
; sorting of array or list of numbers using quick sort, merge sort etc.
; finding all prime numbers less than a given number
; mars rover
; tree traversal
; matrix multiplication
; maze path finder
; math works kids game
; data matching ee problem
; simulating a shell ee problem
; syntax highlighter
; convert centigrate temperature to farenheight scale


; add all elements in the array or list
(def l '(1 2 3 4 5)) ; list needs to be quoted to avoid evaluation
(println (apply + l))
(def v [1 2 3 4 5])
(println (apply + v))

; calculate simple interest and total amount
(defn simple-interest "calculate simple interest and total amount on a principle for a given rate and time"
	[principle rate period]
	(let [si (/ (* principle rate period) 100.0) 
		amount (+ principle si)] ; calculate and store value of interest and amount 
		{:interest si :amount amount})) ; return a map of interest and total amount
(simple-interest 1000 10 2)

; trying to write an assertion for testing simple-interest function
(def actual (simple-interest 1000 10 2))
(def expected {:interest 200.0 :amount 1200.00})
(and (== (:interest expected) (:interest actual)) (== (:amount expected) (:amount actual)))
(= actual expected)

; calculate compound interest and total amount
(defn compound-interest "calculate compound interest and total amount on a principle for a given rate and time"
	[principle rate period]
	(let [amount (* principle (Math/pow (+ 1 (/ rate 100.0)) period))
		ci (- amount principle)] ; calculate and store value of interest and amount 
	(println (str "principle : " principle ", rate : " rate ", period : " period ", interest : " ci ", amount : " amount))
		{:interest ci :amount amount})) ; return a map of interest and total amount
(compound-interest 1000 10 2)

; Syntax highlighter
(defn highlight-syntax [keywords input]
	"When given a set of keywords we need to identify the occurrences of keywords in given input text and mark them with [blue] color attribute"
	input)

(def input "If we write a program and compile it, then we can run the program to get output.")
(def keywords (list "as" "if" "and" "then" "when"))
(def expectedOutput "[blue]If[blue] we write a program [blue]and[blue] compile it, [blue]then[blue] we can run the program to get output.")

(def actualOutput (highlight-syntax keywords input))
(= expectedOutput actualOutput)

; a function to find the last element of any data structure (problem 19 on 4clojure.com)
(defn last-element [c] (first (reverse c)))
((comp first reverse) '(1 2 3 4 5))

(assert (= 5 (last-element '(1 2 3 4 5))))
(assert (= 5 (last-element [1 2 3 4 5])))
(assert (= 2 (last-element [nil 2])))
(assert (= nil (last-element [nil])))
(assert (= nil (last-element ())))
(assert (= \g (last-element "a simple string")))
(assert (= 999999 (last-element (range 1000000))))


; alternate solution by using nth
(defn last-element [c]
	(if (empty? c) 
		nil
		(nth c (dec (count c)))))

; a function to find second last (penultimate) element in a collection (problem 20 on 4clojure.com)
(defn second-last [c] (first (rest (reverse c))))
(second-last '(1 2 3 4 5 6))

; alternate solution using threading macros
(defn second-last [c]
	(->
		c
		reverse
		rest
		first))

; yet another solution using comp
((comp first rest reverse) '(1 2 3 4))
((comp last butlast) '(1 2 3 4))		
((comp second reverse) '(1 2 3 4))
(#(last (butlast %)) '(1 2 3 4)) 

; solution using nth
(defn second-last [c] 
	(nth c (- (count c) 2)))

; a function which returns the Nth element from a sequence (problem 21 from 4clojure.com)
(defn nth-element [c n]
	(if (zero? n)
		(first c)
		(recur (rest c) (dec n))))

(nth-element '(4 5 6 7 8 9) 2)

; alternate solution using drop
(defn nth-element [c n]
	(->
		(drop n c)
		first))

; a function which returns the total number of elements in a sequence, solution using reduce
(#(reduce (fn [c _] (inc c)) 0 [1 2 3 4 5 6]))

; use of higher order function demonstrated below
(defn square-all [numbers] 
	(if (empty? numbers)
		()
		(cons (square (first numbers)) 
			(square-all (rest numbers)))))

(defn square [n] (* n n))

(defn cube-all [numbers] 
	(if (empty? numbers)
		()
		(cons (cube (first numbers)) 
			(cube-all (rest numbers)))))

(defn cube [n] (* n n n))

(defn do-to-all [f numbers]
	(if empty? numbers)
	()
	(cons (f numbers) (do-to-all f (rest numbers))))

(do-to-all square [1 2 3 4 5 6 7 8])
(do-to-all cube [1 2 3 4 5 6 7 8])

; index-of-any
(map-indexed vector [1 2 3 4])

(for [x [1 2 3 4] :when (< x 2)] x)

(defn index-any [coll pred]
	(for [[idx item] (map-indexed vector coll) :when (pred item)]
		idx))

(#{\a \e \i \o \u} \o)

(index-any "this is my string" #{\a \e \i \o \u})

; find word frequencies in a text file.
(require '[clojure.java.io :as io])

(defn get-lines [f]
	(line-seq (io/reader f)))

; find max number from given numbers, solution using reduce
(reduce #(if (> %1 %2) %1 %2) [1 2 3 4])

; program to remove consecutive duplicates from numbers
(def input [1 2 2 3 2 2 2 3 3 1 1 3])
(def output [1 2 3 2 3 1 3])
(defn remove-duplicates [x y]
	(if (number? x)
		(if (= x y)
			[x]
			[x y])
		(if (= (last x) y)
			x
			(conj x y))))
(assert (= output (reduce remove-duplicates input)))

; tic tac toe
(defn tic-tac-toe [matrix] 
	())

(flatten [[:x :o :e]
          [:e :o :e]
          [:x :o :e]])

; convert a number to its string representation. ex. convert 23 to "twenty three"
(def m {0 "zero", 1 "one", 2 "two", 3 "three", 4 "four", 5 "five", 6 "six", 7 "seven", 8 "eight", 9 "nine", 10 "ten", 11, "eleven", 12 "twelve", 13 "thirteen", 14 "fourteen", 15 "fifteen", 16 "sixteen", 17 "seventeen", 18 "eighteen", 19 "nineteen", 20 "twenty", 30 "thirty", 40 "forty", 50 "fifty", 60 "sixty", 70 "seventy", 80 "eighty", 90 "ninety", 100 "hundered", 1000 "thousand"})

(defn tostring [n]
	(if-not (nil? (m n)) 
		(m n)
	(let [unit-digit (rem n 10) tens-value (- n unit-digit)]
	(str (m tens-value) " " (m unit-digit)))))

(defn toDigits 
	([n] (toDigits n []))
	([n digits]
		(if (zero? (int (rem n 10)))
			(reverse digits)
		(recur (int (/ n 10)) (conj digits (rem n 10))))))

(defn test-to-String []
	(and
		(nil? (assert (= (tostring 20) "twenty")))
		(nil? (assert (= (tostring 10) "ten")))))
(test-to-String)

; Alien Chefs Code chef problem
(def movie-timings [[1 4] [3 10] [2 6] [5 8]])
(def visiting-times [1 10 9])

; this works
(filter (defn pred [timing] 
	(nil? ((set (flatten (#(for [visiting-time visiting-times] 
		(cons (found? (set (range (first %1) (inc (last %1)))) visiting-time) [])) timing))) true))) movie-timings)

(defn found? [s e] (if (nil? (s e)) false true))

(filter matches? movie-timings)

; Week 1 Assignments - Coursera Course on FP
; pascal's triangle
(defn pascal[c, r]
	"returns number at position c and r in pascal's triangle"
	(if (or (= c 0) (= r 0) (= c r)) 
		1
		(+ (pascal (dec c) (dec r)) (pascal c (dec r)))))

; check balance of parantheses
(defn balance 
	([chars] (balance chars 0))
	([chars c]
	(if (neg? c)
		false
	(if (empty? chars)
		(zero? c)
	(if (= (first chars) \( )
		(recur (rest chars) (inc c))
	(if (= (first chars) \) )
		(recur (rest chars) (dec c))
	(recur (rest chars) c)))))))

; counting change or number partition
(defn count-change [amount coin-values]
	(cond (= amount 0) 1
		(or (< amount 0) (empty? coin-values)) 0
	:else
		(+ (count-change amount (rest coin-values))
			(count-change (- amount (first coin-values)) coin-values))))