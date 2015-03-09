(ns clojure-tic-tac-toe.board
  (:require [clojure.math.numeric-tower :as math]))

(defn make-new [size]
  (vec (range 1 (inc size))))

(defn size? [cells]
  (count cells))

(defn square-root? [cells]
  (math/sqrt (size? cells)))

(defn full? [cells]
  (= (size? cells) (count (filter string? cells))))

(defn open-space? [index cells]
  (number? (get cells index)))

(defn valid-space? [index cells]
  (<= index (size? cells)))

(defn open-and-valid? [index cells]
  (cond
    (and (open-space? index cells) (valid-space? index cells)) true
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

(defn row-winner? [player-piece cells]
  (loop [start (- (size? cells) (size? cells)) end (+ 2 (- (size? cells) (size? cells)))]
    (cond
      (every? #{player-piece} (subvec cells start (+ end 1))) true
      (= end (- (size? cells) 1)) false
      :else (recur (+ start 3) (+ end 3)))))

(defn diagonal-winner? [player-piece cells]
  (loop [start (- (size? cells) (size? cells)) middle (+ (square-root? cells) 1) end (- (size? cells) 1)]
    (cond
      (and (= player-piece (get cells start)) (= player-piece (get cells middle)) (= player-piece (get cells end))) true
      (= end (- (size? cells) 3)) false
      :else (recur (+ start 2) middle (- end 2)))))

(defn column-winner? [player-piece cells]
  (loop [start (- (size? cells) (size? cells)) middle (square-root? cells) end (* (square-root? cells) 2) ]
    (cond
      (and (= player-piece (get cells start)) (= player-piece (get cells middle)) (= player-piece (get cells end))) true
      (= end (- (size? cells) 1)) false
      :else (recur (inc start) (inc middle) (inc end)))))

(defn winner? [piece-one piece-two cells]
  (cond
    (some #(% piece-one cells) [row-winner? column-winner? diagonal-winner?]) piece-one
    (some #(% piece-two cells) [row-winner? column-winner? diagonal-winner?]) piece-two
     :else false))

(defn tie? [cells piece-one piece-two]
  (let [open-spaces (find-open-spaces cells) cells cells]
     (cond
       (and (empty? open-spaces) (not (winner? piece-one piece-two cells)) (not (winner? piece-one piece-two cells))) true
       :else false)))

(defn game-over? [piece-one piece-two cells]
  (cond
    (or (string? (winner? piece-one piece-two cells)) (= true (tie? cells piece-one piece-two))) true
    :else false))
