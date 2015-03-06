(ns clojure-tic-tac-toe.ai-spec
  (:use speclj.core)
  (:use clojure-tic-tac-toe.ai))

(describe "AI"
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
                   "X"  8  "O"] 0 "O" "X")))

  (it "returns move to block opponent"
      (should= 9
        (get-move ["X"  2   3
                    4  "X"  6
                   "O"  8   9] 0 "O" "X")))

  (it "blocks opponent in the bottom right corner"
      (should= 9
        (get-move ["X" "O"  3
                    4  "X"  6
                   "O"  8   9] 0 "O" "X")))

  (it "blocks opponent in top right corner"
      (should= 3
        (get-move ["X" "X"  3
                    4   5   6
                   "O"  8   9] 0 "O" "X")))

  (it "blocks opponent in the bottom left corner"
      (should= 7
        (get-move [ 1  "O" "X"
                    4  "X"  6
                    7   8   9] 0 "O" "X")))

  (it "blocks opponent in the top left corner"
      (should= 1
        (get-move [ 1   "O" "X"
                   "X"   5   6
                   "X"   8   9] 0 "O" "X")))

  (it "returns piece to win in next move"
      (should= 9
        (get-move ["X"  "X"  3
                   "O"  "O" "X"
                   "O"  "O"  9] 0 "O" "X")))

  (it "returns winning move if five spaces left"
      (should= 8
        (get-move ["X"  "O"  3
                   "X"  "O"  6
                    7    8   9] 0 "O" "X")))
)