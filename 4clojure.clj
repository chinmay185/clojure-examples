; 19. Write a function which returns the last element in a sequence.
(fn last-element [c]
	(if (empty? (rest c))
		(first c)
		(last-element (rest c))))
(comp first reverse)
; (= (__ [1 2 3 4 5]) 5)

; 20. Write a function which returns the second to last element from a sequence.
(comp second reverse)
(comp last butlast)
; (= (__ ["a" "b" "c"]) "b")

; 21. Write a function which returns the Nth element from a sequence.
(fn [c n] 	
(if (zero? n)
	(first c)
	(recur (rest c) (dec n))))
#(first (drop %2 %))
; (= (__ '(4 5 6 7) 2) 6)

; 22. Write a function which returns the total number of elements in a sequence.
(fn	count-elements ([c] (count-elements c 0))
	([c size]
	(if (empty? c)
		size
		(recur (rest c) (inc size)))))
reduce (fn [c _] (inc c)) 0
; (= (__ '(:a :b :c)) 3)

; 23. Write a function which reverses a sequence.
reduce conj ()
into ()
(fn	rev-list ([lst] 
    (rev-list lst []))
	([lst result]
	(if (empty? lst)
		result
		(recur (butlast lst) (conj result (last lst))))))
; (= (__ (sorted-set 5 7 2 7)) '(7 5 2))

; 24. Write a function which returns the sum of a sequence of numbers.
(fn sum-it-up ([lst] (sum-it-up lst 0))
  ([lst acc]
   (if (empty? lst)
     acc
     (recur (rest lst) (+ acc (first lst))))))
; (= (__ [1 2 3]) 6)

; 26. Write a function which returns the first X fibonacci numbers.
()

; (= (__ 8) '(1 1 2 3 5 8 13 21))

; 27. Write a function which returns true if the given sequence is a palindrome.
(fn pal [seq]
	(if (<= (count seq) 1) true
	(if (not= (first seq) (last seq)) false
	(recur (butlast (rest seq))))))
#(= (seq %) (reverse %))
; (true? (__ [:foo :bar :foo]))
