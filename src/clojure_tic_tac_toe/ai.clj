(ns clojure-tic-tac-toe.ai
  (:require [clojure-tic-tac-toe.board :as board]))

(def ^:const win 10)
(def ^:const loss -10)
(def ^:const tie 0)

(defn switch-players [current-player cells]
  (board/find-opponent-piece current-player cells))

(def max-player (atom ""))

(defn set-max-player [player-piece]
  (reset! max-player player-piece))

(defn score-board [cells current-player depth]
  (let [opponent (switch-players current-player cells)]
    (cond
      (= (board/winner? current-player opponent cells) @max-player) (- win depth)
      (= (board/winner? current-player opponent cells) opponent) (- loss depth)
      :else tie)))

(defn get-scores [cells current-player depth]
  (board/game-over? current-player (switch-players current-player cells) cells) (score-board cells current-player depth)
  (println "hello from get-scores, game isnt over"))

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
