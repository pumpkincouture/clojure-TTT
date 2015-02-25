(ns clojure-tic-tac-toe.board)

(defn make-new [size]
  (vec (repeat size "*")))

(defn open-space? [index cells]
  (if (= "*" (get cells index)) true false))

(defn full? [cells]
  (= 9 (count (filter (fn [cell] (not (= "*" cell)))cells))))
