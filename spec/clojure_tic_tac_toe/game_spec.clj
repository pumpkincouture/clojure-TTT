(ns clojure-tic-tac-toe.game-spec
  (:use speclj.core)
  (:use clojure-tic-tac-toe.game))

(describe "Game"

  (it "does not go into continuous if input is wrong"
      (should= nil
        (play-game)))
)
