(ns clojure-tic-tac-toe.ai
  (:require [clojure-tic-tac-toe.board :as board]))

(def ^:const win 10)
(def ^:const loss -10)
(def ^:const tie 0)

(defn score-board [cells player-piece opponent-piece depth]
  (cond
    (= (board/winner? player-piece opponent-piece cells) player-piece) win
    (= (board/winner? player-piece opponent-piece cells) opponent-piece) loss
    :else tie))

(defn generate-boards [cells player-piece opponent-piece depth]
  (let [indexed-spaces (map dec (board/find-open-spaces cells))]
   (map (fn [space] (board/place-move space player-piece cells)) indexed-spaces)))

(defn get-scores [cells player-piece opponent-piece depth]
 (map (fn [cells] (score-board cells player-piece opponent-piece depth)) (generate-boards cells player-piece opponent-piece (inc depth))))

(defn update-scores-list [cells depth player-piece opponent-piece]
  (zipmap (board/find-open-spaces cells) (get-scores cells player-piece opponent-piece depth)))

(defn ai-move [cells depth player-piece opponent-piece]
  (let [indexed-spaces (map dec (board/find-open-spaces cells))
        min-scores (update-scores-list cells (inc depth) opponent-piece player-piece)]
    (first (first (sort-by second > min-scores)))))

(defn get-move [cells depth player-piece opponent-piece]
  (ai-move cells depth player-piece opponent-piece))
