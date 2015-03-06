(ns clojure-tic-tac-toe.game
    (:require [clojure-tic-tac-toe.board :as board])
      (:require [clojure-tic-tac-toe.ui :as ui])
        (:require [clojure-tic-tac-toe.ai :as ai])
        (:gen-class :main true))

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
  (if (= current-type "ai")
      (ai/get-move board 0 current-mark next-mark)
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
            (ui/print-board updated-board)
            (recur second-piece first-piece next-type current-type updated-board)))))))

(defn start-game []
   (let [ _(ui/prompt-for-piece)
          human-piece (read-line)
          _(ui/prompt-for-ai-piece)
          ai-piece (read-line)
          size 9
          board (vec (range 1 (inc size)))
         ]
  (ui/print-board board)
  (game-loop human-piece ai-piece "human" "ai" board)))

(defn -main []
  (ui/print-welcome)
  (start-game))
