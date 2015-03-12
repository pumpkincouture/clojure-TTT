(ns clojure-tic-tac-toe.ai
  (:require [clojure-tic-tac-toe.board :as board]))

(declare update-each-space)

(def ^:const win 10)
(def ^:const loss -10)
(def ^:const tie 0)

(defn switch-players [current-player cells]
  (board/find-opponent-piece current-player cells))

(def max-player (atom "O"))
(def min-player (atom "X"))

(defn set-max-player [player-piece]
  (reset! max-player player-piece))

(defn score-board [cells current-player depth]
  (let [opponent (switch-players current-player cells)]
    (cond
      (= (board/winner? current-player opponent cells) "O") (- win depth)
      (= (board/winner? current-player opponent cells) "X") (- depth win)
      :else tie)))

(defn minimax [cells current-player depth]
   (if (board/game-over? current-player (switch-players current-player cells) cells)
      (score-board cells current-player depth)
      (let [scores-list (vec (update-each-space cells current-player depth))]
       (= current-player "O") (apply max scores-list)
       (= current-player "X") (apply min scores-list))))

(defn update-each-space [cells current-player depth]
  (let [open-spaces (board/find-open-spaces cells)]
    (for [space open-spaces]
      (minimax (board/place-move (dec space) current-player cells) (switch-players current-player cells) (inc depth)))))

(defn place-scored-move [cells current-player space depth]
  (let [updated-board (board/place-move (dec space) current-player cells)]
    (minimax updated-board current-player (inc depth))))

(defn get-spaces [cells current-player depth]
  (let [open-spaces (board/find-open-spaces cells)]
    (for [space open-spaces]
      (place-scored-move cells current-player space depth))))

(defn minimax-move [cells depth current-player]
  (println (get-spaces cells current-player depth) (board/find-open-spaces cells))
  (let [values (map vector (vec (board/find-open-spaces cells)) (vec (get-spaces cells current-player depth)))
        ]
    (first (first (sort-by second > values)))))

(defn ai-move [cells depth current-player]
  (cond
    (board/board-empty? cells) 1
    (and (= (count (board/find-open-spaces cells)) (- (board/size? cells) 1)) (some #{5} (board/find-open-spaces cells))) 5
   :else (minimax-move cells depth current-player)))

