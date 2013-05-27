; a clojure solution to Mathworks kids game (for problem statement refer http://www.slideshare.net/DhavalDalal/math-works-kidsgame)

(defn prime? [x] 
	(let [divisors (range 2 (inc (int (Math/sqrt x))))
		remainders (map #(rem x %) divisors)]
	(not (some zero? remainders))))

(def numbers (range 2 100))

(defn all-primes [numbers] 
	(filter prime? numbers))

(defn even-numbers [numbers] 
	(filter even? numbers))

(defn odd-numbers [numbers] 
	(filter even? numbers))

(defn mul-of-5? [n]
	(if (zero? (rem n 5))
	true
	false))

(defn mul-of-3? [n]
	(if (zero? (rem n 3))
	true
	false))

(defn multiples-of-5 [numbers]
	(filter mul-of-5? numbers))

(defn multiples-of-3 [numbers]
	(filter mul-of-3? numbers))

; multiples of 5 and 3. i.e. multiplese of 15
(->
	numbers
	multiples-of-5
	multiples-of-3)

; (defn multiples-of-5 [numbers]
; 	(filter (not (nil? (map mul-of-5 numbers)))) [] )

; #(zero? (rem % 5))

(multiples-of-5 [1 2 3 4 5 6 7 8 9 10 12 15 29])

(defn even-primes [numbers]
	(set (all-primes (even-numbers numbers))))

; all even prime numbers
(->
	numbers
	even-numbers
	all-primes)

(defn odd-primes [numbers]
	(set (all-primes (odd-numbers numbers))))

(even-primes numbers)

(odd-primes numbers)