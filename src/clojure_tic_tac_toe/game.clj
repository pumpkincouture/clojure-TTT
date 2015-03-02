(ns clojure-tic-tac-toe.game
  (:require [clojure-tic-tac-toe.board :as board])
  (:require [clojure-tic-tac-toe.ui :as ui])
  (:require [clojure-tic-tac-toe.ai :as ai]))

(defn capture-valid-move []
  (try Integer/parseInt (read-line))
    (catch NumberFormatExeption e nil))

(defn get-move [player-piece board]
  (let [input (capture-move)]
     (if (board/open-and-valid? input board)
       (board/place-move input human-piece board)
       (recur board human-piece))))

(defn get-human-move [human-piece board]
  (ui/prompt-for-move human-piece)
  (let [ input (capture-valid-move)]
    (if (and input (include? (range 1 10) input))
      (input - 1)
      (recur board human-piece))))

(defn play-game-loop)
(defn start-game []
  (let [ _(ui/prompt-for-piece)
           human-piece (read-line)
         _(ui/prompt-for-ai-piece)
           ai-piece (read-line)
           size 9
           board (vec (range 1 (inc size)))
         ]
    (ui/print-board board)
    (ui/print-board (get-human-move human-piece board))
    ))
