(ns clojure-tic-tac-toe.game
    (:require [clojure-tic-tac-toe.board :as board])
      (:require [clojure-tic-tac-toe.ui :as ui])
        (:require [clojure-tic-tac-toe.ai :as ai]))

(def player-types ["human" "ai"])

(defn valid-input? [input]
  (cond
    (integer? (read-string input)) (dec (Integer/parseInt input))
    :else false))

(defn get-human-move [board player-piece]
   (ui/prompt-for-move player-piece)
   (let [choice (read-line)
         input (valid-input? choice)]
   (if (board/open-and-valid? input board)
      input
       (do
         (ui/print-error)
         [input (get-human-move board player-piece)]
          input))))

(defn get-move [board current-type current-mark next-mark]
  (if (= current-type (second player-types))
      (dec (ai/get-move board 0 current-mark next-mark))
      (get-human-move board current-mark)))

(defn game-loop [first-piece second-piece current-type next-type board]
   (loop [first-piece  first-piece
          second-piece  second-piece
          current-type current-type
          next-type next-type
          board board]
    (if (board/game-over? first-piece second-piece board)
       (ui/print-result first-piece second-piece board)
          (do
            (let [move (get-move board current-type first-piece second-piece)]
            (let [updated-board (board/place-move move first-piece board)]
            (ui/print-choice first-piece move)
            (ui/print-board updated-board)
            (recur second-piece first-piece next-type current-type updated-board)))))))

(defn get-human-option []
  (first player-types))

(defn get-ai-option []
  (second player-types))

(defn get-options []
   (let [ _(ui/prompt-for-piece)
          first-piece (read-line)
          _(ui/prompt-for-ai-piece)
          second-piece (read-line)
          size 9
          board (vec (range 1 (inc size)))
          first-player (get-human-option)
          second-player (get-ai-option)
         ]
   (assoc {} :first-marker first-piece :second-marker second-piece :board board :first-type first-player :second-type second-player)))

(defn start-game [options]
  (let [first-piece (get options :first-marker)
        second-piece (get options :second-marker)
        board (get options :board)
        first-player-type (get options :first-type)
        second-player-type (get options :second-type)
       ]
  (ui/print-board board)
  (game-loop first-piece second-piece first-player-type second-player-type board)))
