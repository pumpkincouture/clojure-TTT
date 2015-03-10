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
      (= (board/winner? current-player opponent cells) "X") (- loss depth)
      :else tie)))

(defn get-scores [cells current-player depth]
  ;(println current-player depth cells "current player, depth and cells from get-scores, ")
   (if (board/game-over? current-player (switch-players current-player cells) cells)
      (score-board cells current-player depth)
      (let [scores-list (vec (update-each-space cells current-player depth))]
     (if (= current-player "O") (apply max scores-list)
      (apply min scores-list)))))

(defn update-each-space [cells current-player depth]
  ;(println cells "cells from update each space")
  ;(println (board/find-open-spaces cells) "remaining cells")
  (let [open-spaces (board/find-open-spaces cells)]
    (for [space open-spaces]
      (get-scores (board/place-move (dec space) (switch-players current-player cells) cells) (switch-players current-player cells) (inc depth)))))

(defn place-scored-move [cells current-player space depth]
  (let [updated-board (board/place-move (dec space) current-player cells)]
    (get-scores updated-board current-player (inc depth))))

(defn get-spaces [cells current-player depth]
  (let [open-spaces (board/find-open-spaces cells)]
    ;(println open-spaces "open spaces from get-spaces")
    (for [space open-spaces]
      (place-scored-move cells current-player space depth))))

(defn update-scores-list [cells depth current-player]
  (zipmap (board/find-open-spaces cells) (get-spaces cells current-player depth)))

(defn get-move [cells depth current-player]
  (set-max-player max-player)
  ;(println (get-spaces cells current-player depth) "this is from get-move")
  (update-scores-list cells depth current-player))

(defn ai-move [cells depth current-player]
  (println (get-move cells depth current-player) "scores from ai move")
  (first (first (sort-by second > (get-move cells depth current-player)))))
