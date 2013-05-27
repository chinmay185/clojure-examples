; looping with recursion
(defn looping [i]
	(if (= i 10)
		i
	(recur (inc i))))

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

; factorial without Tail call optimization
(defn factorial 
	"returns a factorial of a number"
	([n] (factorial n (dec n))) 
	([n m]
		(if (zero? n)
			1
		(* n (factorial (dec n))))))

;solution with recursion to find last element of a given collection
(defn last-element [c]
	(if (empty? (rest c))
		(first c)
		(recur (rest c))))

; a function which returns the total number of elements in a sequence
(defn count-elements 
	([c] (count-elements c 0))
	([c size]
	(if (empty? c)
		size
		(recur (rest c) (inc size)))))

; find max number from given numbers
(defn m 
	([nums] (m (first nums) (rest nums)))
	([current-max others]
		(if (first others)
			(if (> current-max (first others))
				(recur current-max (rest others))
				(recur (first others) (rest others)))
			current-max)))