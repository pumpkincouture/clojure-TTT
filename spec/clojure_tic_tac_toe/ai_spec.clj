(ns clojure-tic-tac-toe.ai-spec
  (:use speclj.core)
  (:use clojure-tic-tac-toe.ai))

(describe "AI"
  (it "generates a score of 10 for computer win"
      (should= 10
        (score-board ["T"  2  "T"
                      "T" "H" "H"
                      "T"  8  "T"] (set-max-player "T") 0)))

  (it "generates a score of -10 for opponent win"
      (should= -10
        (score-board ["H"  2  "T"
                      "H" "T" "H"
                      "H"  8  "T"] (set-max-player "T") 0)))

  (it "generates a score of -10 for computer loss"
      (should= -10
        (score-board ["H"  2  "H"
                      "T" "H" "H"
                      "H"  8  "T"] (set-max-player "T") 0)))

  (it "returns a score of -10 for computer loss"
      (should= -10
        (score-board ["H"  2  "H"
                      "T" "T" "H"
                      "H"  8  "H"] (set-max-player "T") 0)))

  (it "returns a score of -10 for opponent win"
      (should= -10
        (score-board [ 1   2  "T"
                      "H" "T" "H"
                      "H" "H" "H"] (set-max-player "T") 0)))

  (it "generates a score of 0 for tie board"
      (should= 0
        (score-board ["T" "H" "T"
                      "H" "T" "H"
                      "H" "T" "H"] (set-max-player "T") 0)))

  (it "returns X if O is the current player"
      (should= "X"
          (switch-players "O" ["X"  2  3
                                4   5  6
                                7   8  9])))

  (it "returns O if X is the current player"
      (should= "O"
          (switch-players "X" ["O" "X" 3
                                4   5  6
                                7   8  9])))
  (it "returns 8 as best move"
      (should= 8
        (get-move ["X" "O" "O"
                   "O" "O" "X"
                   "X"  8  "X"] 0 "O")))
)
