(ns clojure-tic-tac-toe.ai
  (:require [clojure-tic-tac-toe.board :as board]))

(defn- switch-players [player-piece cells]
  (board/find-opponent-piece player-piece cells))

(defn ai-move [cells depth player-piece]
  (loop [index 0 open-cell (- (nth (board/find-open-spaces cells) index) 1) game-piece player-piece depth depth]
    (let [updated-board (board/place-move open-cell game-piece cells)]
      (println updated-board))
      (println open-cell)
    (cond
      (= depth 4) true
      :else (recur (inc index) (- (nth (board/find-open-spaces cells) index) 1) (switch-players game-piece cells) (inc depth)))))

(defn pick-move [cells]
  (- (rand-nth (board/find-open-spaces cells)) 1))
