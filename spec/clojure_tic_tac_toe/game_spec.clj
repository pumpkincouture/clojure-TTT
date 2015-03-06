(ns clojure-tic-tac-toe.game-spec
  (:use speclj.core)
  (:use clojure-tic-tac-toe.game))

(describe "Game"
  (it "returns the index if choice is an integer"
     (should= 4
       (valid-input? "5")))

  (it "returns false if choice is not integer"
      (should= false
      (valid-input? "h")))

  (it "returns false if numerical input is not integer"
      (should= false
      (valid-input? "1.0")))

  (it "returns the index if choice is an integer"
     (should= 7
       (valid-input? "8")))
)
