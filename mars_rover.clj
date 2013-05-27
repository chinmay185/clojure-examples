; a clojure solution to mars rover problem (for problem statement refer https://github.com/vijendra/mars-rovers#problem-statement-)

(defn create-direction[direction left right x y]
	{:value direction :left left :right right :x x :y y}
)

(def directions {	"N" (create-direction "N" "W" "E" 0 1 )
					"S" (create-direction "S" "E" "W" 0 -1 )
					"E" (create-direction "E" "N" "S" 1 0 )
					"W" (create-direction "W" "S" "N" -1 0 )}
)

(defn create-rover [x y direction]
	{:x x :y y :direction direction}
)

(defn- get-direction[directionType]
	(directions directionType)
)

(defn- new-direction[rover turnDirection]
	(get-direction ((rover :direction) turnDirection))
)

(defn- turn [rover turnDirection]
	(assoc rover :direction (new-direction rover turnDirection))
)

(defn left [rover]
	 (turn rover :left)
)

(defn right [rover]
	(turn rover :right)
)

(defn- new-position[rover position]
	(+ (rover position) ((rover :direction) position))
)

(defn- change [rover position]
	(assoc rover position (new-position rover position))
)

(defn- change-x-position [rover]
	(change rover :x)
)

(defn- change-y-position [rover]
	(change rover :y)
)

(defn move [rover]
	(-> rover change-x-position change-y-position)
)

(defn- list-of [commands]
	(rest(map (fn [x] x) (.split commands "")))
)

(def all-commands {"L" left "R" right "M" move})

(defn- execute-command[rover command]
	((all-commands command) rover)
)

(defn execute [rover commands]
	(reduce execute-command rover (list-of commands))
)

(defn display [rover]
	(str (rover :x) " " (rover :y) " " ((rover :direction) :value))
)

(defn- print-rover [rover]
	(-> rover display println)
)

(defn- give-output-for [x y direction commands]
	(print-rover (execute (create-rover x y (get-direction direction)) commands))
)

(give-output-for 1 2 "N" "LMLMLMLMM")
(give-output-for 3 3 "E" "MMRMMRMRRM")