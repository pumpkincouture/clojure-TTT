(ns clojure-tic-tac-toe.setup
  (:require [clojure-tic-tac-toe.ui :as ui]))

(def options-list (atom {:first-marker "first-marker" :second-marker "second-marker" :board "board" :first-type "first-type" :second-type "second-type"}))

(def add-to-options (fn [symbol option] (swap! options-list assoc-in [symbol] option)))

(defn get-first-player []
  (ui/prompt-for-piece)
  (read-line))

(defn get-second-player []
  (ui/prompt-for-ai-piece)
  (read-line))

(defn get-board []
  (vec (range 1 (inc 9))))

(defn get-setup-options []
  (add-to-options :first-marker (get-first-player))
  (add-to-options :second-marker (get-second-player))
  (add-to-options :board (get-board))
  (add-to-options :first-type "human")
  (add-to-options :second-type "ai"))
