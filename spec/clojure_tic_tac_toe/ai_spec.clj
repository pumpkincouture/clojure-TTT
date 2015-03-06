(ns clojure-tic-tac-toe.ai-spec
  (:use speclj.core)
  (:use clojure-tic-tac-toe.ai))

(describe "AI"
  (it "picks a random move given a 3x3 board"
    (should= 7
      (pick-move ["X" "X" "X"
                  "O" "X" "O"
                  "O" "X" 8])))

  (it "picks a random move given a 3x3 board"
    (should= 1
      (pick-move ["X" "X" 2
                  "O" "X" "O"
                  "O" "X" "O"])))

  (it "generates a score of 10 for computer win"
      (should= 10
        (score-board ["T"  2  "T"
                      "T" "H" "H"
                      "T"  8  "T"] "T" "H" 0)))

  (it "generates a score of -10 for opponent win"
      (should= -10
        (score-board ["H"  2  "T"
                      "H" "T" "H"
                      "H"  8  "T"] "T" "H" 0)))

  (it "generates a score of -10 for computer loss"
      (should= -10
        (score-board ["H"  2  "H"
                      "T" "H" "H"
                      "H"  8  "T"] "T" "H" 0)))

  (it "returns a score of -10 for computer loss"
      (should= -10
        (score-board ["H"  2  "H"
                      "T" "T" "H"
                      "H"  8  "H"] "T" "H" 0)))

  (it "returns a score of -10 for opponent win"
      (should= -10
        (score-board [ 1   2  "T"
                      "H" "T" "H"
                      "H" "H" "H"] "T" "H" 0)))

  (it "generates a score of 0 for tie board"
      (should= 0
        (score-board ["T" "H" "T"
                      "H" "T" "H"
                      "H" "T" "H"] "T" "H" 0)))

  (it "returns highest rated move"
      (should= 6
        (get-move ["X"  2  "O"
                   "O" "X"  6
                   "X"  8  "O"] 0 "O")))

  (it "returns move to block opponent"
      (should= 9
        (get-move ["X"  2   3
                    4  "X"  6
                   "O"  8   9] 0 "O")))
)
