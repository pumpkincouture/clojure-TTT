(ns clojure-tic-tac-toe.board-spec
  (:use speclj.core)
  (:use clojure-tic-tac-toe.board))

(describe "Board"
  (it "creates a new board"
    (should= ["*" "*" "*" "*" "*" "*" "*" "*" "*"]
      (make-new 9)))

  (it "checks if certain index of the board is an asterisk"
      (should= false
      (open-space? 4, ["*", "*", "*", "*", "X"])))

  (it "checks if board is full"
      (should= true
      (full? ["X" "O" "X" "O" "X" "O" "O" "X" "O"])))

  (it "checks if board has any empty spaces"
      (should= false
      (full? ["X" "O" "X" "*" "X" "O" "O" "X" "O"])))
 )
