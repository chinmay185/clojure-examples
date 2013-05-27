; TODO:
; Example of closure with clojure
; TDD in clojure?
; difference between macros and normal functions

; hello world in clojure
(def message "Hello World!") ; clojure strings are java strings
(println message)

; understanding data types
; all clojure variables are immutable.
(def x 4) ; defaults to java.lang.Long
(def y 3.6e4) ; defaults to java.lang.Double
(def z true) ; defaults to java.lang.Boolean
(def c \n) ; define a single character, defaults to java.lang.Character (verify again)
(type y) ; returns java.lang.Double
(class y) ; returns java.lang.Double
(var x) ; prints #'user/x
'x

; operators as first class functions
(def p 10) ; define some numbers
(def q 2)
(def r 3)
(= 2 2.0) ; returns false
(== 2 2.0) ; returns true
(println (+ p q r 1)) ; prints 16
(println (- p q r 1)) ; prints 4
(println (* p q r 0.5)) ; prints 30
(println (/ p q r 0.5)) ; prints 3.3333
(def s 3/4) ; s = 3/4, a rational number
(and (== 5 5) (< 1 2)) ; returns true, behaves like && in java
(or (== 5 5) (== 5 4)) ; return true, behaves like || in java
(not (= 4 4)) ; returns false, behaves like ! in java
(println (rem 5 2)) ; prints the remainder when 5 is divided by 2 i.e. prints 1
(println (quot 5 2)) ; prints the quotient when 5 is divided by 2 i.e. prints 2
(println (inc 5)) ; prints the value when 5 is incremented by one i.e. prints 6
(println (dec 5)) ; prints the value when 5 is decremented by one i.e. prints 4
(println (pos? 5)) ; prints whehter the number is positive or not. i.e. prints true
(println (neg? 5)) ; prints whehter the number is negative or not. i.e. prints false
(println (zero? 5)) ; prints whehter the number is zero or not. i.e. prints false
(println (number? "40")) ; takes a single argument and returns true if it is a number, in this case false
(println (max 9 2 4.4 18 3 -9 8.9)) ; prints the max value among the list i.e. prints 18 
(println (min 9 2 4.4 18 3 -9.3 -8.9)) ; prints the min value among the list i.e. prints -9.3 

; keywords as a primitive data type in clojure
(keyword "something") ; returns :something, a keyword with the same name as the string that is passed to it. 
:something ; the usual way to refer to keywords
::something ; refers to keywords in the current namespace
:user/something ; refers to keyword in the user namespace
(keyword "foo" "bar") ; returns :foo/bar, a namespaced keyword
(keyword? :foo/bar) ; tests whether the argument passed to it is a keyword or not

(doc +) ; prints the documentation for + function
(find-doc "immutable") ; finds the references of word "immutable" in all the docs.
(source +) ; prints the source code of the function

; explicit casting
(int 1.8) ; returns 1
(double 1) ; returns 1.0
(double 3/4) ; returns 0.75
(boolean nil) ; retuns false

; conditionals
(if (= 2 2) "Math works" "Math falied.") ; equality check
(if (== 2 2) "Math works" "Math falied.") ; another type of equality check (need to find the differences)
(if (not= 2 3) "Math works" "Math falied.") ; not equal to operator
(if true "true" "this is not printed") ; prints "true"
(if false "this is not printed" "in else part") ; prints "in else part"

; nested if
(if false "this is not printed" 
	(if true "nested if"
		"this is not printed")) ; prints "nested if"

; if-not construct
(if-not (= 2 3)
	"indeed 2 is not equal to 3"
	"this is not printed") ; prints "indeed 2 is not equal to 3"

; if and if-not in combination
(if false "this is not printed"
	(if-not true "this is not printed"
		"nested if and if-not in action")) ; prints "nested if and if-not in action"

; switch case statement
(cond (> 1 2) ; evaluates to false
	"this is not printed"
	(< 15 10) ; evaluates to false
	"this is not printed as well"
	:default (println "this is default output"))

(cond (> 3 2) ; evaluates to true
	"3 is greater than 2"
	(< 5 10) ; evaluates to true
	"5 is less than 10"
	:default (println "default case")) ; prints the first true condition i.e. prints "3 is greater than 2"

; calling java code from clojure
(System/getProperties) ; calls static method
(Thread/sleep 1000) ; sleeps for 1 second
(Math/PI) ; prints 3.141592653589793
(.. "hello" getClass getProtectionDomain)
(.toUpperCase "hello") ; The dot before toUpperCase tells Clojure to treat it as the name of a Java method instead of a Clojure function
(. "something" toString) ; prints "something"
(. "something" (substring 2 4)) ; prints "me"
(.. "something" (substring 2 4) (charAt 1)) ; -> "something".substring(2, 4).charAt(1) -> prints 'e'
(. System/out (println "something")) ; prints "something" and returns nil


(str 1 2 nil 3) ; prints "123" skipping nils

;;;----------------------------------------------------------------------------
;;; defining functions
; general form of functional definition is as follows
(defn function-name doc-string? attr-map? [parameter-list]
	conditions-map?
	(expressions))
; some examples are as follows
(defn add "adds two integers." [x y] (+ x y)) ; this form is usually preferred
(def add (fn [x y] (+ x y))) ; another function definition form
(add 4 6) ; this is how you call the above functions

; functions with conditions-map
(defn total-price [price-per-item quantity]
	{
	:pre [(pos? price-per-item) (pos? quantity)] ; preconditions to check before function execution
	:post [pos? %]} ; postconditions to check after function execution, % refers to function's return value
	(* price-per-item quantity))
(total-price 12 3) ; returns 36
(total-price -12 3) ; throws AssertionError Assert Failed: (pos? price-per-item)

; functions with multiple arities
(defn square-or-multiply 
	"squares or multiplies integers"
	([] 0) ; return 0 when called without any arguments
	([x] (square-or-multiply x x)) ; call the same function with two parameters
	([x y] (* x y))) ; return x multiplied by y
(square-or-multiply) ; returns 0
(square-or-multiply 3) ; returns 9
(square-or-multiply 12 4) ; returns 48

; functions with variable arguments (varargs)
(defn add [x y & others] (apply + x y others))
(add 3 4 8 8 4) ; returns 27

; using defn to define functions
(defn make-a-set
	"returns a set of the arguments passed"
	([x] #{x})
	([x y] #{x y}))

; in-place or anonymous functions with #()
(def add #(+ %1 %2)) ; here %1 and %2 refer to the arguments of the functions
(def add #(apply + %1 %2 %&)) ; %& refers to variable number of arguments

;;;----------------------------------------------------------------------------
; managing side effects with do
(defn sum-with-logging [x & others]
	(do
		(def sum (apply + x others)) ; calculate sum
		(println (str "sum is " sum)) ; this is where side effect (logging) occurs
		sum)) ; return sum, the last statement of do is returned.

; managing side effects with do
(defn sum-with-logging [x & others]
	(do
		(let [sum (apply + x others)]
			(println (str "sum is " sum))
			sum)))

; program to print a greeting message to user
(defn greet [name] (println (str "Hello " name)) name) ; str concatenates the strings.
(greet "chinmay")

; program to add two or more numbers
(defn add [x y & more] (apply + x y more)) ; in this case, apply applies addition operation to all the arguments
(add 4 5 6 7 1 2 3)

; program to add all elements of a list of integers
(def lst '(1 2 3 4 5 6))
(println (apply + lst))
(/ (apply + lst) (* (count lst) 1.0)) ; calculate the average of the elements in the list

(defn greet [t] "greets appropriate message based on tht time t of the day."
	(if (> t 0)
		if (< t 12)
			(println "Good morning!")
		(if (< t 17)
			(println "Good afternoon")
		(if (< t 23)
			(println "Good everning")
		(if (= t 24)
			(println "Good night!")
			(println "invalid time."))))))
(greet -2)
(greet 0)
(greet 2)
(greet 12)
(greet 16)
(greet 22)
(greet 23)
(greet 24)

;;; understand these
(cond (> 1 2)
	"this is not printed"
	(< 15 10)
	"this is not printed as well"
	:default ((println "first line.") (println "this is default output"))) ; this throws NullPointerException.
;	:default (do (println "first line.") (println "this is default output"))) ; this fixes the above exception
