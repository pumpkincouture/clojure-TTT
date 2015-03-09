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

(defn update-scores [move score current-player depth cells]
  (println cells)
  (println move "this is move")
  (println score "this is score")
  (println current-player "current player"))

(defn apply-minimax [cells depth current-player]
  (for [open-space (board/find-open-spaces cells)]
    (let [space-index (- open-space 1)
          updated-board (board/place-move space-index current-player cells)
         score (apply-minimax updated-board (inc depth) (switch-players current-player updated-board))]
      (update-scores open-space score current-player depth updated-board))))

;(defn ai-move [cells depth max-player min-player]

;  (for [open-space (board/find-open-spaces cells)]
;    (let [space-index (- open-space 1)
;          updated-board (board/place-move space-index current-player cells)]

;  (if (= max-player max-player)
;     (update-scores cells (inc depth) max-player min-player)
;     (update-scores cells (inc depth) min-player max-player)
;  ))

(defn get-move [cells depth current-player]
  (set-max-player max-player)
  (apply-minimax cells depth current-player))
