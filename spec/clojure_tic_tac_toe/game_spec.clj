(ns clojure-tic-tac-toe.game-spec
  (:use speclj.core)
  (:use clojure-tic-tac-toe.game))

(describe "Game"

  (it "does not go into continuous if input is wrong"
    (let [human-piece "y"]
;      (with-redefs [read-line "1"]
      (should= nil
        (run-game))))
)
