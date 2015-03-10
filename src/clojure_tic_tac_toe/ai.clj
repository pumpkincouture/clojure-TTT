(ns clojure-tic-tac-toe.ai
  (:require [clojure-tic-tac-toe.board :as board]))

(declare update-each-space)

(def ^:const win 10)
(def ^:const loss -10)
(def ^:const tie 0)

(defn switch-players [current-player cells]
  (board/find-opponent-piece current-player cells))

(def max-player (atom ""))
(def min-player (atom "X"))

(defn set-max-player [player-piece]
  (reset! max-player player-piece))

(defn score-board [cells current-player depth]
  (let [opponent (switch-players current-player cells)]
    (cond
      (= (board/winner? current-player opponent cells) current-player) (- win depth)
      (= (board/winner? current-player opponent cells) opponent) (- loss depth)
      :else tie)))

(defn get-scores [cells current-player depth]
  (board/game-over? current-player (switch-players current-player cells) cells)
    (println (score-board cells current-player depth))
  (let [scores-list (update-each-space cells current-player depth)]
    (cond
      (= current-player @max-player) (apply max scores-list)
      :else (apply min scores-list))))

(defn add-to-scores-list [space score]
  [space score])

(defn update-each-space [cells current-player depth]
  (let [open-spaces (board/find-open-spaces cells)]
    (for [space open-spaces]
      (add-to-scores-list space (get-scores cells current-player depth)))))

(defn place-scored-move [cells current-player space]
  (let [updated-board (board/place-move (dec space) current-player cells)
        depth 1]
    (get-scores updated-board current-player depth)))

(defn get-spaces [cells current-player]
  (let [open-spaces (board/find-open-spaces cells)]
    (for [space open-spaces]
      (place-scored-move cells current-player space))))

(defn get-move [cells depth current-player]
  (set-max-player max-player)
  (get-spaces cells current-player))
