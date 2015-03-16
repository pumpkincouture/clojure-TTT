(ns clojure-tic-tac-toe.ai
  (:require [clojure-tic-tac-toe.board :as board]))

(declare calculate-minimax)

(def ^:const win 10)
(def ^:const loss -10)
(def ^:const tie 0)

(defn switch-players [current-player cells]
  (board/find-opponent-piece current-player cells))

(def max-player (atom "O"))
(def min-player (atom "X"))

(defn set-max-player [player-piece]
  (reset! max-player player-piece))

(defn set-min-player [player-piece cells]
  (reset! min-player (switch-players player-piece cells)))

(defn score-board [cells current-player depth]
  (let [opponent (switch-players current-player cells)]
    (cond
      (= (board/winner? current-player opponent cells) @max-player) (- win depth)
      (= (board/winner? current-player opponent cells) @min-player) (+ loss depth)
      :else tie)))

(defn minimax [cells current-player depth]
   (if (board/game-over? current-player (switch-players current-player cells) cells)
      (score-board cells current-player depth)
      (let [scores-list (vec (calculate-minimax cells current-player depth))]
       (if (= current-player @min-player)
         (apply max scores-list)
         (apply min scores-list)))))

(defn calculate-minimax [cells current-player depth]
  (let [open-spaces (board/find-open-spaces cells)]
    (for [space open-spaces]
      (minimax (board/place-move (dec space) (switch-players current-player cells) cells) (switch-players current-player cells) (inc depth)))))

(defn get-spaces [cells current-player depth]
  (let [open-spaces (board/find-open-spaces cells)]
    (for [space open-spaces]
      (let [updated-board (board/place-move (dec space) current-player cells)]
        (minimax updated-board current-player (inc depth))))))

(defn minimax-move [cells depth current-player]
  (let [values (zipmap (board/find-open-spaces cells) (get-spaces cells current-player depth))]
    (first (first (sort-by second > values)))))

(defn ai-move [cells depth current-player]
   (if (board/board-empty? cells)
     1
      (let [max-player (set-max-player current-player)
            min-player (set-min-player current-player cells)
            ]
      (minimax-move cells depth max-player))))

