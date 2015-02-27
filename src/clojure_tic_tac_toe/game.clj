(ns clojure-tic-tac-toe.game
  (:require [clojure-tic-tac-toe.board :as board])
  (:require [clojure-tic-tac-toe.ui :as ui])
  (:require [clojure-tic-tac-toe.ai :as ai]))

(defn print-stuff [board human-piece ai-piece]
  (ui/print-board board)
  (println human-piece)
  (println ai-piece))

(defn capture-move []
  (- (Integer. (read-line)) 1))

(defn get-human-move [human-piece board]
  (ui/prompt-for-move human-piece)
  (let [input (capture-move)]
     (if (board/open-and-valid? input board)
       (board/place-move input human-piece board)
       (do
         (println "nope")
         (println "it's not a number")))))

(defn run-game []
  (ui/print-welcome)
  (let [ _(ui/prompt-for-piece)
           human-piece (read-line)
         _(ui/prompt-for-ai-piece)
           ai-piece (read-line)
           size 9
           board (vec (range 1 (inc size)))
         ]
    (ui/print-board board)
    (get-human-move human-piece board)
    ))
