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

  (it "checks if the chosen space is not within range"
    (should= false
      (valid-space? 11 )))

  (it "checks if the chosen space is not within range"
    (should= true
      (valid-space? 8)))

  (it "checks if space is both of a valid index and is open"
    (should= true
      (open-and-valid? 3 ["X" "O" "X" "*" "X" "O" "O" "X" "O"])))

  (it "returns false if space is either not valid or taken"
    (should= false
      (open-and-valid? 4 ["X" "O" "X" "*" "X" "O" "O" "X" "O"])))
 )
