(ns clojure-tic-tac-toe.ai
  (:require [clojure-tic-tac-toe.board :as board]))

(defn pick-move [cells]
  (rand-nth (board/find-open-spaces cells)))
