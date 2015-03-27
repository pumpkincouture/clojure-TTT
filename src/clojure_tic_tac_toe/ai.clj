(ns clojure-tic-tac-toe.ai
  (:require [clojure-tic-tac-toe.board :as board]))

(declare rank-each-space)

(def ^:const win 10)
(def ^:const loss -10)
(def ^:const tie 0)

(def max-player (atom "O"))
(def min-player (atom "X"))

(defn switch-players [current-player cells]
  (if (= current-player @max-player)
    @min-player
    @max-player))

(defn set-max-player [player-piece]
  (reset! max-player player-piece))

(defn set-min-player [player-piece cells]
  (reset! min-player (board/find-opponent-piece player-piece cells)))

(defn score-board [cells current-player depth]
  (let [opponent (switch-players current-player cells)]
    (cond
      (= (board/winner? current-player opponent cells) @max-player) (- win depth)
      (= (board/winner? current-player opponent cells) @min-player) (+ loss depth)
      :else tie)))

(defn apply-minimax [cells current-player depth]
   (if (board/game-over? current-player (switch-players current-player cells) cells)
      (score-board cells current-player depth)
      (let [scores-list (vec (rank-each-space cells (switch-players current-player cells) depth))]
       (if (= current-player @min-player)
         (apply max scores-list)
         (apply min scores-list)))))

(defn rank-each-space [cells current-player depth]
  (let [open-spaces (board/find-open-spaces cells)]
    (for [space open-spaces]
      (let [updated-board (board/place-move (dec space) current-player cells)]
        (apply-minimax updated-board current-player (inc depth))))))

(defn calculate-best-move [cells depth current-player]
  (set-min-player current-player cells)
  (let [values (zipmap (board/find-open-spaces cells) (rank-each-space cells current-player depth))]
    (first (first (sort-by second > values)))))

(defn ai-move [cells depth current-player]
  (if (board/board-empty? cells)
     (board/get-random-move cells)
       (let [max-player (set-max-player current-player)]
         (calculate-best-move cells depth max-player))))

