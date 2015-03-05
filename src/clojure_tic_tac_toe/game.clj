(ns clojure-tic-tac-toe.game
    (:require [clojure-tic-tac-toe.board :as board])
      (:require [clojure-tic-tac-toe.ui :as ui])
        (:require [clojure-tic-tac-toe.ai :as ai])
        (:gen-class :main true))

(defn capture-valid-move []
      (try (Integer/parseInt (read-line))
                 (catch NumberFormatException e nil)))

(defn get-human-move [board player-piece]
   (ui/prompt-for-move player-piece)
   (let [input (capture-valid-move)]
   (if (board/open-and-valid? input board)
      input
       (do
         (ui/print-error)
         [input (get-human-move board player-piece)]
          input))))

(defn get-move [board current-type current-mark next-mark]
  (if (= current-type "ai")
      (ai/pick-move board)
      (get-human-move board current-mark)))

(defn game-loop [current-mark next-mark current-type next-type board]
   (loop [current-mark current-mark
          next-mark     next-mark
          current-type current-type
          next-type next-type
          board       board]
    (if (board/game-over? next-mark current-mark board)
       (ui/print-result current-mark next-mark board)
          (do
            (let [move (get-move board current-type current-mark next-mark)]
            (let [updated-board (board/place-move move current-mark board)]
            (ui/print-board updated-board)
            (recur next-mark current-mark next-type current-type updated-board)))))))

(defn start-game
  ([]
   (let [ _(ui/prompt-for-piece)
          human-piece (read-line)
          _(ui/prompt-for-ai-piece)
          ai-piece (read-line)
          size 9
          board (vec (range 1 (inc size)))
         ]
  (ui/print-board board)
  (game-loop human-piece ai-piece "human" "ai" board))))

(defn -main []
  (ui/print-welcome)
  (start-game))
