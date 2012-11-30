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
	(println (str "principle : " principle ", rate : " rate ", period : " period ", interest : " si ", amount : " amount))
		{:interest si :amount amount})) ; return a map of interest and total amount
(simple-interest 1000 10 2)

; calculate compound interest and total amount
(defn compound-interest "calculate compound interest and total amount on a principle for a given rate and time"
	[principle rate period]
	(let [amount (* principle (Math/pow (+ 1 (/ rate 100.0)) period))
		ci (- amount principle)] ; calculate and store value of interest and amount 
	(println (str "principle : " principle ", rate : " rate ", period : " period ", interest : " ci ", amount : " amount))
		{:interest ci :amount amount})) ; return a map of interest and total amount
(compound-interest 1000 10 2)

; convert centigrate temperature to farenheight scale

(defn f [n] 
	(if (zero? n) 0 
		(if (= n 1) 1
			(+ (f (dec n)) (f (- n 2))))))




; Fibonacci sequence
(defn next-terms [term-1 term-2]
	(let [term-3 (+ term-1 term-2)]
		(lazy-seq
			(cons term-3
				(next-terms term-2 term-3)))))

(defn fibonacci [t1 t2]
	(concat [t1 t2]
		(next-terms t1 t2)))

(take 15 (fibonacci 0 1))
