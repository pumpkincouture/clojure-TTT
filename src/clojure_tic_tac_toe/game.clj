(ns clojure-tic-tac-toe.game
  (:require [clojure-tic-tac-toe.board :as board])
  (:require [clojure-tic-tac-toe.ui :as ui])
  (:require [clojure-tic-tac-toe.ai :as ai]))

(def ai-opponents ["easy" "hard"])
(def human-piece)
(def ai-piece)

(defn validate-type [opponent-choice]
  (cond
    (or (= (first ai-opponents) opponent-choice) (= (last ai-opponents) opponent-choice)) true
    :else
    (ui/print-error)))


(defn get-player-choice [input]
  (cond
     (validate-type input)
      (println "its valid")
      :else
      (recur input)))

(defn set-up-game []
  (let [_ (ui/prompt-for-ai-opponent)
          get-player-choice (read-line)
         _ (board/make-new 9)
         ])
        (ui/print-error))
