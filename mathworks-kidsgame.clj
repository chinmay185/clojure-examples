(require '(clojure  [set :as s]))

(defn prime? [x] 
	(let [divisors (range 2 (inc (int (Math/sqrt x))))
		remainders (map #(rem x %) divisors)]
	(not (some zero? remainders))))

(def numbers (range 2 100))

(def all-primes (filter prime? numbers))

(def even-numbers (filter even? numbers))

(def even-primes (map (fn [x y] (if-not (identical? x y) x)) even-numbers all-primes))

(s/intersection (set all-primes) (set even-numbers))

;(defn even-primes [] (filter matches? numbers))
;(def (filter even? (filter prime? numbers)))

;(defn matches? [collection & conditions] 
;(println collection) 

;forech(number : collection){
; result = true;
 ;foreach( func : conditions){
;result = result && func(number);
;if(!result)

 ;}
;}

;)

even-primes