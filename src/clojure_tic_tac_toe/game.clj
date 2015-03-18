(ns clojure-tic-tac-toe.game
    (:require [clojure-tic-tac-toe.board :as board])
      (:require [clojure-tic-tac-toe.ui :as ui])
        (:require [clojure-tic-tac-toe.ai :as ai]))

(def player-types ["human" "ai"])
(declare get-human-move)

(def first-player (atom {}))
(def second-player (atom {}))

;(defmulti get-move (fn [current-player cells] (:player-type current-player)))
;
;(defmethod get-move "human" [current-player cells]
;  (get-human-move cells current-player))
;
;(defmethod get-move "ai" [current-player cells]
;  (ai/ai-move cells 0 current-player))

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

(defn get-move [current-player board]
  (if (= (current-player :player-type) (second player-types))
      (dec (ai/ai-move board 0 (current-player :player-piece)))
      (get-human-move board (current-player :player-piece))))

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
        (recur second-piece first-piece next-type current-type updated-board))))))))

(defn game-loop [first-player second-player board]
   (loop [first-player  first-player
          second-player  second-player
          board board]
    (if (board/game-over? (first-player :player-piece) (second-player :player-piece) board)
       (ui/print-result (first-player :player-piece) (second-player :player-piece) board)
          (do
            (let [move (get-move (first-player :player-type) board)]
            (let [updated-board (board/place-move move (first-player :player-piece) board)]
            (ui/print-choice ((board/current-player board first-player second-player) :player-piece) move)
            (ui/print-board updated-board)
            (recur second-player first-player updated-board)))))))

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
        second-piece (get options :second-marker) board (get options :board)
        first-player-type (get options :first-type)
        second-player-type (get options :second-type)
       ]
  (swap! first-player assoc :player-type first-player-type :player-piece first-piece)
  (swap! second-player assoc :player-type second-player-type :player-piece second-piece)
  (ui/print-board board)
  (game-loop @first-player @second-player board)))
