(ns clojure-tic-tac-toe.ai
  (:require [clojure-tic-tac-toe.board :as board]))

(defn- switch-players [player-piece cells]
  (board/find-opponent-piece player-piece cells))

(defn generate-board
  ([cells depth player-piece]
   (let [depth depth
         open-space (- (nth (board/find-open-spaces cells) 0) 1)
         depth depth
         player-piece player-piece
         cells cells
         ]
     (generate-board open-space depth player-piece cells)))

  ([open-space depth player-piece cells]
  (let [
        open-space open-space
        depth depth
        player-piece player-piece
        opponent-piece (switch-players player-piece cells)
        updated-board (board/place-move open-space player-piece cells)
        ]
    (cond
      (= depth 5) true
      (board/game-over? player-piece opponent-piece updated-board)
         updated-board
      :else (recur (- (nth (board/find-open-spaces updated-board) 0) 1) (inc depth) (switch-players player-piece cells) updated-board)))))

(defn ai-move [cells depth player-piece]
  (loop [index 0 open-cell (- (nth (board/find-open-spaces cells) index) 1) game-piece player-piece depth depth]
    (let [updated-board (board/place-move open-cell game-piece cells)
          other-piece (switch-players player-piece updated-board)
          ]
      (println player-piece)
      (println updated-board)
      (println open-cell))
    (cond
      (= depth 4) true
      :else (recur (inc index) (- (nth (board/find-open-spaces cells) index) 1) (switch-players player-piece cells) (inc depth)))))

(defn pick-move [cells]
  (- (rand-nth (board/find-open-spaces cells)) 1))
