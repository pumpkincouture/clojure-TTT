(ns clojure-tic-tac-toe.game-spec
  (:use speclj.core)
  (:use clojure-tic-tac-toe.game))

(describe "Game"
  (around [it]
      (with-out-str (it)))

  (it "returns the index if choice is an integer"
     (should= 4
       (valid-input? "5")))

  (it "returns false if choice is not integer"
      (should= false
      (valid-input? "h")))

  (it "returns false if numerical input is not integer"
      (should= false
      (valid-input? "1.0")))

  (it "returns the index if choice is an integer"
     (should= 7
       (valid-input? "8")))

  (it "should return game options"
      (with-redefs [read-line (constantly "X")]
      (should= {:second-type "ai"
                :first-type "human"
                :first-marker "X"
                :second-marker "X"
                :board [1 2 3 4 5 6 7 8 9]}
        (get-options))))

  (it "should get the ai move if current player-type is ai"
       (should= 2
         (get-move ["X" "X"  3
                    "O"  5   6
                    7   8   9] "ai" "O" "X")))

  (it "should get the human move if current-piece is not ai"
     (with-redefs [read-line (constantly "3")]
       (should= 2
         (get-move ["X" "X"  3
                    "O"  5   6
                     7   8   9] "human" "X" "O"))))

  (it "gets the human's move"
     (with-redefs [read-line (constantly "4")]
      (should= 3
        (get-human-move ["X" "X" "O"
                          4   5   6
                          7   8   9] "X"))))

  (it "returns the human player type"
      (should= "human"
        (get-human-option)))

  (it "returns the ai player type"
      (should= "ai"
        (get-ai-option)))
)
