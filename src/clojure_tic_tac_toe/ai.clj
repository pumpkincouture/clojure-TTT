(ns clojure-tic-tac-toe.ai
  (:require [clojure-tic-tac-toe.board :as board]))

(def ^:const win 10)
(def ^:const loss -10)

(defn- switch-players [player-piece cells]
  (board/find-opponent-piece player-piece cells))

(defn score-board [cells player-piece opponent-piece depth]
  (cond
    (= (board/winner? player-piece opponent-piece cells) player-piece) win
    (= (board/winner? player-piece opponent-piece cells) opponent-piece) loss
    :else 0))

(defn generate-boards [cells player-piece opponent-piece depth]
  (let [indexed-spaces (map dec (board/find-open-spaces cells))]
   (map (fn [space] (board/place-move space player-piece cells)) indexed-spaces)))

(defn get-scores [cells player-piece opponent-piece depth]
 (map (fn [cells] (score-board cells player-piece opponent-piece depth)) (generate-boards cells player-piece opponent-piece (inc depth))))

(defn update-scores-list [cells depth player-piece opponent-piece]
  (zipmap (board/find-open-spaces cells) (get-scores cells player-piece opponent-piece depth)))

(defn ai-move [cells depth player-piece opponent-piece]
  (let [indexed-spaces (map dec (board/find-open-spaces cells))
        max-scores (update-scores-list cells (inc depth) player-piece opponent-piece)
        min-scores (update-scores-list cells (inc depth) opponent-piece player-piece)]
    (first (first (sort-by second > min-scores)))))

(defn get-move [cells depth player-piece opponent-piece]
  (ai-move cells depth player-piece opponent-piece))

;(first (first (sort-by second < (ai-move cells depth player-piece opponent-piece)))))
