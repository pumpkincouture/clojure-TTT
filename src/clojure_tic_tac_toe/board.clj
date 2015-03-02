(ns clojure-tic-tac-toe.board)

(defn make-new [size]
  (vec (range 1 (inc size))))

(defn full? [size cells]
  (= size (count (filter string? cells))))

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

(defn delete-move [index cells]
  (assoc cells index (+ 1 index)))

(defn find-opponent-piece [player-piece cells]
  (nth (filter (fn [cell]
    (and (not (= player-piece cell)) (not (number? cell))))
      cells) 0))

(defn winner-row-one? [player-piece cells]
  (every? #{player-piece} (subvec cells 0 2)))

(defn winner-row-two? [player-piece cells]
  (every? #{player-piece} (subvec cells 3 5)))

(defn winner-row-three? [player-piece cells]
  (every? #{player-piece} (subvec cells 6 8)))

(defn winner-column-one? [player-piece cells]
  (cond
    (and (= player-piece (get cells 0)) (= player-piece (get cells 3)) (= player-piece (get cells 6))) true
    :else false))

(defn winner-column-two? [player-piece cells]
  (cond
    (and (= player-piece (get cells 1)) (= player-piece (get cells 4)) (= player-piece (get cells 7))) true
    :else false))

(defn winner-column-three? [player-piece cells]
  (cond
    (and (= player-piece (get cells 2)) (= player-piece (get cells 5)) (= player-piece (get cells 8))) true
    :else false))

(defn winner-diagonal-one? [player-piece cells]
  (cond
    (and (= player-piece (get cells 0)) (= player-piece (get cells 4)) (= player-piece (get cells 8))) true
    :else false))

(defn winner-diagonal-two? [player-piece cells]
  (cond
    (and (= player-piece (get cells 2)) (= player-piece (get cells 4)) (= player-piece (get cells 6))) true
    :else false))

(defn any-row-winner? [player-piece cells]
  (some #(% player-piece cells) [winner-row-one? winner-row-two? winner-row-three?]))

(defn any-column-winner? [player-piece cells]
  (some #(% player-piece cells) [winner-column-one? winner-column-two? winner-column-three?]))

(defn any-diag-winner? [player-piece cells]
  (some #(% player-piece cells) [winner-diagonal-one? winner-diagonal-two?]))

(defn winner? [piece-one piece-two cells]
  (cond
    (or (some #(% piece-one cells) [any-row-winner? any-column-winner? any-diag-winner?]) (some #(% piece-two cells) [any-row-winner? any-column-winner? any-diag-winner?])) true
  :else false))

(defn game-over? [piece-one piece-two cells]
  boolean (or (winner? piece-one piece-two cells) (full? 9 cells)))
