(ns clojure-tic-tac-toe.ai
  (:require [clojure-tic-tac-toe.board :as board]))

(defn- switch-players [player-piece cells]
  (board/find-opponent-piece player-piece cells))

(defn score-board [cells player-piece opponent-piece depth]
  (cond
    (= (board/winner? player-piece opponent-piece cells) player-piece) (- 10 depth)
    (= (board/winner? player-piece opponent-piece cells) opponent-piece) (- depth 10)
    :else 0))

(defn generate-board
  ([cells depth player-piece]
   (let [computer-piece player-piece
         depth depth
         open-space (- (nth (board/find-open-spaces cells) 0) 1)
         depth depth
         player-piece (switch-players player-piece cells)
         cells cells
         ]
     (generate-board computer-piece open-space depth player-piece cells)))

  ([computer-piece open-space depth player-piece cells]
  (let [computer-piece computer-piece
        open-space open-space
        depth depth
        player-piece player-piece
        opponent-piece (switch-players computer-piece cells)
        updated-board (board/place-move open-space player-piece cells)
        ]
    (cond
      (= depth 5) true
      (board/game-over? player-piece opponent-piece updated-board)
         (score-board updated-board computer-piece opponent-piece depth)
      :else (recur computer-piece (- (nth (board/find-open-spaces updated-board) 0) 1) (inc depth) (switch-players player-piece cells) updated-board)))))

(defn update-scores-list [move score]
  ;{move score}
  ;(zipmap [move] [score])
  [move score]
  )

(defn ai-move [cells depth player-piece]
    (for [open-space (board/find-open-spaces cells)]
      (let [space-index  (- open-space 1)
            updated-board (board/place-move space-index player-piece cells)]
        (update-scores-list open-space (generate-board updated-board depth player-piece)))))

(defn get-move [cells depth player-piece]
  (first (first (sort-by second > (ai-move cells depth player-piece)))))

(defn pick-move [cells]
  (- (rand-nth (board/find-open-spaces cells)) 1))
