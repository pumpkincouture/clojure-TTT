(ns clojure-tic-tac-toe.board)

(defn make-new [size]
  (vec (range size)))

(defn full? [cells]
  (= 9 (count (filter string? cells))))

(defn open-space? [index cells]
  (if (number? (get cells index)) true false))

(defn valid-space? [index]
  (<= index 9))

(defn open-and-valid? [index cells]
  (cond
    (and (open-space? index cells) (valid-space? index)) true
    :else false))

(defn find-open-spaces [cells]
  (filter (fn [cell] (number? cell)) cells))

(defn place-move [index player-piece cells]
  (assoc cells index player-piece))
