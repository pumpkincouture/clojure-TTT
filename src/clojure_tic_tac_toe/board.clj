(ns clojure-tic-tac-toe.board)

(defn make-new [size]
  (vec (repeat size "*")))

(defn open-space? [index cells]
  (if (= "*" (get cells index)) true false))

(defn full? [cells]
  (= 9 (count (filter (fn [cell] (not (= "*" cell)))cells))))

(defn valid-space? [index]
  (if (<= index 9) true false))

(defn open-and-valid? [index cells]
  (cond
    (and (open-space? index cells) (valid-space? index)) true
    :else false))
