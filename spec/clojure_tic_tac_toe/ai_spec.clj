(ns clojure-tic-tac-toe.ai-spec
  (:use speclj.core)
  (:use clojure-tic-tac-toe.ai))

(describe "AI"
  (it "picks a random move given a 3x3 board"
    (should= 7
      (pick-move ["X" "X" "X" "O" "X" "O" "O" "X" 8])))

  (it "picks a random move given a 3x3 board"
    (should= 1
      (pick-move ["X" "X" 2 "O" "X" "O" "O" "X" "O"])))
)
