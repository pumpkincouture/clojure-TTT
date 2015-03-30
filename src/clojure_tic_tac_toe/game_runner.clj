(ns clojure-tic-tac-toe.game_runner
  (:require [clojure-tic-tac-toe.game :as game])
  (:require [clojure-tic-tac-toe.setup :as setup])
  (:gen-class :main true))

(defn -main []
  (game/start-game (setup/get-options)))

