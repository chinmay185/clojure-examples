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

; Fibonacci sequence
(defn f [n] 
	(if (zero? n) 0 
		(if (= n 1) 1
			(+ (f (dec n)) (f (- n 2))))))

(defn next-terms [term-1 term-2]
	(let [term-3 (+ term-1 term-2)]
		(lazy-seq
			(cons term-3
				(next-terms term-2 term-3)))))

(defn fibonacci [t1 t2]
	(concat [t1 t2]
		(next-terms t1 t2)))

(take 15 (fibonacci 0 1))

; Syntax highlighter
(defn highlight-syntax [keywords input]
	"When given a set of keywords we need to identify the occurrences of keywords in given input text and mark them with [blue] color attribute"
	input)

(def input "If we write a program and compile it, then we can run the program to get output.")
(def keywords (list "as" "if" "and" "then" "when"))
(def expectedOutput "[blue]If[blue] we write a program [blue]and[blue] compile it, [blue]then[blue] we can run the program to get output.")

(def actualOutput (highlight-syntax keywords input))
(= expectedOutput actualOutput)

; program to add up to a given number, for ex, 1 + 2 + 3 + 4, where x = 4
(defn add-up
	"adds all the numbers up to a limit"
	([limit] (add-up limit 0 0 ))
	([limit current sum]
		(if (< limit current)
			sum
		(recur limit (+ 1 current) (+ current sum)))))

; factorial of a number
(defn factorial [n]
	(let [numbers (range 1 (+ n 1))]
		(reduce * numbers)))

(defn fact-loop [n]
	(loop [current n fact 1]
		(if (= current 1)
			fact
		(recur (dec current) (* fact current)))))


; FIXME
(defn factorial 
	"returns a factorial of a number"
	([n] (factorial n (dec n))) 
	([n m]
		(if (zero? n)
			1
		(* n (factorial (dec n))))))

; looping with recursion
(defn looping [i]
	(if (= i 10)
		i
	(recur (inc i))))

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

; alternate solution with recursion to find last element
(defn last-element [c]
	(if (empty? (rest c))
		(first c)
		(recur (rest c))))

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

; a function which returns the total number of elements in a sequence
(defn count-elements 
	([c] (count-elements c 0))
	([c size]
	(if (empty? c)
		size
		(recur (rest c) (inc size)))))

; alternate solution using reduce
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

; find max number from given numbers
(defn m 
	([nums] (m (first nums) (rest nums)))
	([current-max others]
		(if (first others)
			(if (> current-max (first others))
				(recur current-max (rest others))
				(recur (first others) (rest others)))
			current-max)))
