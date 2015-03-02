(ns clojure-tic-tac-toe.ui-spec
  (:use speclj.core)
  (:use clojure-tic-tac-toe.ui))

(describe "UI"
  (around [it]
    (with-out-str (it)))

  (it "prints welcome to the game"
    (should= "Welcome to Tic Tac Toe! Board is index based, when choosing a piece press 0 for 1, 1 for 2, etc\n"
      (with-out-str (print-welcome))))

  (it "should print out a 3x3 board"
    (should="\"                 \"\n\"  1  |  2  |  3  \"\n\"-----------------\"\n\"  4  |  5  |  6  \"\n\"-----------------\"\n\"  7  |  8  |  9  \"\n\"                 \"\n"
      (with-out-str (print-board [1 2 3 4 5 6 7 8 9]))))

  (it "should prompt the X player for a move"
    (should= "Player X , please choose a number :\n"
      (with-out-str (prompt-for-move "X"))))

  (it "should print out what the player has chosen after they made a move"
    (should= "Player Y chose space 6\n"
      (with-out-str (print-choice "Y" "6"))))

  (it "should print out game winner"
    (should= "O wins!\n"
      (with-out-str (print-winner "O"))))

  (it "should print out cat's game if game is a tie"
    (should= "Cat's game!\n"
      (with-out-str (print-tie-game))))

  (it "should print an error message if input invalid"
    (should= "That is not a valid choice, please try again!\n"
      (with-out-str (print-error))))
)
