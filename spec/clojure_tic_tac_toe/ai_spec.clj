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

  (it "generates an end state board with a winner"
    (should= ["O" 2 3
              "O" "X" "O"
              "O" "X" 9]
      (generate-board   [1   2   3
                        "O" "X" "O"
                        "O" "X"  9] 0 "O")))

  (it "reaches depth of 5 before generating an end state for that board"
      (should= true
       (generate-board [1   2   3
                        4   5  "X"
                        7   8   9] 0 "O")))

  (it "generates an end state board with no winner"
      (should= ["X" "O" "O"
                "O" "X" "X"
                "X" "X" "O"]
        (generate-board ["X"  2  "O"
                         "O" "X" "X"
                         "X"  8  "O"] 0 "O")))
)
