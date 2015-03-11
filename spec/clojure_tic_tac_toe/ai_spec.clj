(ns clojure-tic-tac-toe.ai-spec
  (:use speclj.core)
  (:use clojure-tic-tac-toe.ai))

(describe "AI"
;  (it "generates a score of 10 for computer win"
;      (should= 10
;        (score-board ["O"  2  "O"
;                      "O" "X" "X"
;                      "O"  8  "X"] "O" 0)))
;
;  (it "generates a score of -10 for opponent win"
;      (should= -10
;        (score-board ["X"  2  "O"
;                      "X" "O" "X"
;                      "X"  8  "O"] "O" 0)))
;
;  (it "generates a score of -10 for computer loss"
;      (should= -10
;        (score-board ["X"  2  "X"
;                      "O" "X" "X"
;                      "X"  8  "O"] "O" 0)))
;
;  (it "returns a score of -10 for computer loss"
;      (should= -10
;        (score-board ["X"  2  "X"
;                      "O" "O" "X"
;                      "X"  8  "X"] "O" 0)))
;
;  (it "returns a score of -10 for opponent win"
;      (should= -10
;        (score-board [ 1   2  "O"
;                      "X" "O" "X"
;                      "X" "X" "X"] "O" 0)))
;
;  (it "generates a score of 0 for tie board"
;      (should= 0
;        (score-board ["O" "X" "O"
;                      "X" "O" "X"
;                      "X" "O" "X"] "O" 0)))
;
;  (it "returns X if O is the current player"
;      (should= "X"
;          (switch-players "O" ["X"  2  3
;                                4   5  6
;                                7   8  9])))
;
;  (it "returns O if X is the current player"
;      (should= "O"
;          (switch-players "X" ["O" "X" 3
;                                4   5  6
;                                7   8  9])))
(it "returns 6 as best move"
    (should= 6
      (ai-move ["X" "X" "O"
                "O" "X"  6
                 7   8  "O"] 0 (set-max-player "O"))))

  ;(it "returns 7 as best move"
  ;    (should= 7
  ;      (ai-move ["X" "X" "O"
  ;                "O" "O" "X"
  ;                 7   8   9] 0 (set-max-player "O"))))

  ;(it "returns 5 as best move"
  ;    (should= 5
  ;      (ai-move ["X"  2  "O"
  ;                "O"  5   6
  ;                "O" "X" "X"] 0 (set-max-player "O"))))

  ;(it "returns 3 as best move"
  ;    (should= 3
  ;      (ai-move [ 1  "X"  3
  ;                 4   5  "X"
  ;                "O" "O" "X"] 0 (set-max-player "O"))))

  ;(it "returns 2 as best move"
  ;    (should= 2
  ;      (ai-move ["O"  2  "O"
  ;                 4  "X" "X"
  ;                 7   8   9] 0 (set-max-player "O"))))

  ;(it "returns a block to opponent"
  ;    (should= 9
  ;      (ai-move ["X"  2   3
  ;                 4  "X"  6
  ;                "O"  8   9] 0 "O")))

  ; (it "blocks opponent in the bottom left corner"
  ;    (should= 7
  ;      (ai-move [ 1  "O" "X"
  ;                 4  "X"  6
  ;                 7   8   9] 0 "O")))

  ;(it "blocks opponent in upper right hand corner"
  ;    (should= 3
  ;      (ai-move ["O"  2   3
  ;                 4  "X"  6
  ;                "X"  8   9] 0 "O")))

; (it "blocks opponent in upper right hand corner"
;     (should= 3
;       (ai-move ["O"  2   3
;                  4  "X"  6
;                 "X"  8   9] 0 "O")))
;
   (it "returns 5 as computer's first move"
      (should= 5
        (ai-move ["X"  2   3
                   4   5   6
                   7   8   9] 0 "O")))
;
;   (it "returns 2 as computer's first move"
;      (should= 2
;        (ai-move ["X"  2   3
;                   4  "O"  6
;                   7  "O"  "X"] 0 "O")))
;
   (it "returns 4 as computer's second move"
      (should= 4
        (ai-move ["X"  2   3
                   4  "O"  6
                  "X"  8   9] 0 "O")))

   (it "returns 4 as computer's second move"
      (should= 4
        (ai-move ["X"  2  "X"
                   4  "O" "O"
                   7   8  "X"] 0 "O")))

;   (it "returns best move if computer makes first move"
;      (should= 1
;        (ai-move [ 1   2   3
;                   4   5   6
;                   7   8   9] 0 "O")))
)
