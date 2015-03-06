(ns clojure-tic-tac-toe.ai
  (:require [clojure-tic-tac-toe.board :as board]))

(defn- switch-players [player-piece cells]
  (board/find-opponent-piece player-piece cells))

(defn score-board [cells player-piece opponent-piece depth]
  (cond
    (= (board/winner? player-piece opponent-piece cells) player-piece) (- 10 depth)
    (= (board/winner? player-piece opponent-piece cells) opponent-piece) (- depth 10)
    :else 0))

(defn generate-boards [cells player-piece opponent-piece depth]
  (let [indexed-spaces (map dec (board/find-open-spaces cells))]
   (map (fn [space] (board/place-move space player-piece cells)) indexed-spaces)))

(defn get-scores [cells player-piece opponent-piece depth]
 (map (fn [cells] (score-board cells player-piece opponent-piece depth)) (generate-boards cells player-piece opponent-piece depth)))

(defn update-min-scores-list [cells depth player-piece opponent-piece]
  (zipmap (board/find-open-spaces cells) (get-scores cells player-piece opponent-piece depth)))

(defn ai-move [cells depth player-piece opponent-piece]

  (let [indexed-spaces (map dec (board/find-open-spaces cells))

           ; updated-board-with-comp-piece (board/place-move space-index player-piece cells)
           min-scores
          ; updated-board-with-opponent-piece (board/place-move space-index opponent-piece cells)]
       ; (update-scores-list open-space (generate-board updated-board-with-comp-piece (inc depth) player-piece))
        (update-min-scores-list cells depth opponent-piece player-piece)]
   (first (first (sort-by val > min-scores)))))

(defn get-move [cells depth player-piece opponent-piece]
  (ai-move cells depth player-piece opponent-piece))
;(first (first (sort-by second < (ai-move cells depth player-piece opponent-piece)))))
