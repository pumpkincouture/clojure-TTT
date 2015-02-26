(ns clojure-tic-tac-toe.ai-spec
  (:use speclj.core)
  (:use clojure-tic-tac-toe.ai))

(describe "AI"
  (it "picks a random move given a 3x3 board"
    (should= 2
      (pick-move ["X" 2 "X" "O" "X" "O" "O" "X" 8])))
  )
