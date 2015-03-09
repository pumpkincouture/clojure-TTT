(ns clojure-tic-tac-toe.game-spec
  (:use speclj.core)
  (:use clojure-tic-tac-toe.game))

(describe "Game"
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

  (it "should return a hash with game options"
      (with-redefs [read-line (constantly "X")]
      (should= {:second-type "ai"
                :first-type "human"
                :human-marker "X"
                :ai-marker "X"
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

  (it "should play ai vs ai game"
    (let [board [1 2 3 4 5 6 7 8 9]
          game-over-message "game over"]
      (with-redefs [println (constantly "game over")
                    prn (constantly true)]
      (should= game-over-message
        (start-game {:second-type "ai"
                     :first-type "ai"
                     :human-marker "X"
                     :ai-marker "O"
                     :board [1 2 3 4 5 6 7 8 9]})))))
)
