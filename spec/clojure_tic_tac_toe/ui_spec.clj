(ns clojure-tic-tac-toe.ui-spec
  (:use speclj.core)
  (:use clojure-tic-tac-toe.ui))

(describe "UI"
  (around [it]
    (with-out-str (it)))

  (it "prints welcome to the game"
    (should= "Welcome to Tic Tac Toe! Please indicate your choice with a number. \n"
      (with-out-str (print-welcome))))

  (it "prompts the human to choose a gamepiece"
    (with-redefs [read-line (constantly "%")]
    (should= "Human, please choose your game piece :\n"
      (with-out-str (prompt-for-piece)))))

  (it "prompts the human to choose a gamepiece for the opponent"
    (with-redefs [read-line (constantly "&")]
    (should= "Human, please pick your opponent's game piece :\n"
      (with-out-str (prompt-for-ai-piece)))))

  (it "should print out a 3x3 board"
    (should="\n\"-----------\"\n\" 1 | 2 | 3 \"\n\"-----------\"\n\" 4 | 5 | 6 \"\n\"-----------\"\n\" 7 | 8 | 9 \"\n\"-----------\"\n\n"
      (with-out-str (print-board [1 2 3 4 5 6 7 8 9]))))

  (it "should prompt the X player for a move"
    (with-redefs [read-line (constantly "X")]
    (should= "Player X , please choose a number :\n"
      (with-out-str (prompt-for-move "X")))))

  (it "should print out the board representation of choice"
    (should= "Player Y chose space 7\n"
      (with-out-str (print-choice "Y" 6))))

  (it "should print out game winner"
    (should= "O wins!\n"
      (with-out-str (print-winner "O"))))

  (it "should print out cat's game if game is a tie"
    (should= "Cat's game!\n"
      (with-out-str (print-tie-game))))

  (it "should print the winner of the game"
    (should= "X wins!\n"
      (with-out-str (print-result "X" "O"
                             ["X" "X" "X"
                              "O" "X" "O"
                               7   8   9]))))

  (it "should print a tie game message"
    (should= "Cat's game!\n"
      (with-out-str (print-result "X" "O"
                             ["O" "X" "O"
                              "O" "X" "X"
                              "X" "O" "O"]))))

  (it "should print an error message if input invalid"
    (should= "That is not a valid choice, please try again!\n"
      (with-out-str (print-error))))
)
