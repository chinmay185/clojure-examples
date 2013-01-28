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

; factorial using recursion
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
