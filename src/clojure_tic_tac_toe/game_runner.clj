(ns clojure-tic-tac-toe.game_runner
  (:require [clojure-tic-tac-toe.ui :as ui])
  (:require [clojure-tic-tac-toe.game :as game])
  (:gen-class :main true))

(defn -main []
  (ui/print-welcome)
  (game/start-game (game/get-options)))

