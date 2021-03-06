(ns clojure-tic-tac-toe.ai-spec
  (:use speclj.core)
  (:use clojure-tic-tac-toe.ai))

(describe "AI"
  (it "generates a score of 10 for computer win"
      (should= 10
        (score-board ["O"  2  "O"
                      "O" "X" "X"
                      "O"  8  "X"] (set-max-player "O") 0)))

  (it "generates a score of -10 for opponent win"
      (set-min-player "O" ["X"  2  "O"
                           "X" "O" "X"
                           "X"  8  "O"])
      (should= -10
        (score-board ["X"  2  "O"
                      "X" "O" "X"
                      "X"  8  "O"] (set-max-player "O") 0)))

  (it "generates a score of -10 for computer loss"
      (set-min-player "O" ["X"  2  "X"
                           "O" "X" "X"
                           "X"  8  "O"])
      (should= -10
        (score-board ["X"  2  "X"
                      "O" "X" "X"
                      "X"  8  "O"] (set-max-player "O") 0)))

  (it "returns a score of -10 for computer loss"
      (set-min-player "O" ["X"  2  "X"
                           "O" "O" "X"
                           "X"  8  "X"])
      (should= -10
        (score-board ["X"  2  "X"
                      "O" "O" "X"
                      "X"  8  "X"] (set-max-player "O") 0)))

  (it "returns a score of -10 for T win"
      (set-min-player "$" [ 1   2  "$"
                           "T" "$" "T"
                           "T" "T" "T"])
      (should= -10
        (score-board [ 1   2  "$"
                      "T" "$" "T"
                      "T" "T" "T"] (set-max-player "$") 0)))

  (it "generates a score of 0 for tie board"
      (should= 0
        (score-board ["O" "X" "O"
                      "X" "O" "X"
                      "X" "O" "X"] "O" 0)))

  (it "returns 6 as best move"
    (should= 6
       (ai-move ["X" "X" "O"
                 "O" "X"  6
                  7   8  "O"] 0 (set-max-player "O"))))

(it "returns 7 as best move"
  (should= 7
    (ai-move ["X"  2   3
              "X" "O"  6
               7   8   9] 0 (set-max-player "O"))))

 (it "returns 7 as best move"
   (should= 7
     (ai-move ["X" "X" "O"
               "O" "O" "X"
                7   8   9] 0 (set-max-player "O"))))

(it "returns 5 as best move"
    (should= 5
      (ai-move ["X"  2  "O"
                "O"  5   6
                "O" "X" "X"] 0 (set-max-player "O"))))

(it "returns 3 as best move"
    (should= 3
      (ai-move [ 1  "X"  3
                 4   5  "X"
                "O" "O" "X"] 0 (set-max-player "O"))))

(it "returns 2 as best move"
    (should= 2
      (ai-move ["O"  2  "O"
                 4  "X" "X"
                 7   8   9] 0 (set-max-player "O"))))

(it "returns a block to opponent"
    (should= 9
      (ai-move ["X"  2   3
                 4  "X"  6
                "O"  8   9] 0 "O")))

 (it "blocks opponent in the bottom left corner"
    (should= 7
      (ai-move [ 1  "O" "X"
                 4  "X"  6
                 7   8   9] 0 "O")))

(it "blocks opponent in upper right hand corner"
    (should= 3
      (ai-move ["O"  2   3
                 4  "X"  6
                "X"  8   9] 0 "O")))

   (it "returns 2 as computer's winning move"
      (should= 2
        (ai-move ["X"  2   3
                   4  "O"  6
                   7  "O"  "X"] 0 "O")))

   (it "returns 4 as computer's second move and a block"
      (should= 4
        (ai-move ["X"  2   3
                   4  "O"  6
                  "X"  8   9] 0 "O")))

   (it "returns 4 as computer's second move and a win for computer"
      (should= 4
        (ai-move ["X"  2  "X"
                   4  "O" "O"
                   7   8  "X"] 0 "O")))

   (it "returns 5 as computer's first move"
     (should= 5
       (ai-move ["T"  2   3
                  4   5   6
                  7   8   9] 0 "P")))
)
