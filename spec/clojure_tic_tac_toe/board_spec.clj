(ns clojure-tic-tac-toe.board-spec
  (:use speclj.core)
  (:use clojure-tic-tac-toe.board))

(describe "Board"
  (it "creates a new board"
    (should= [1 2 3 4 5 6 7 8 9]
      (make-new 9)))

  (it "checks if space is open"
    (should= true
      (open-space? 4, [1 2 3 4 5 6 7 8 9])))

  (it "checks if board is full"
    (should= true
      (full? 9 ["X" "O" "X" "O" "X" "O" "O" "X" "O"])))

  (it "checks if board is not full"
    (should= false
      (full? 9 ["X" "O" "X" 4 "X" "O" "O" "X" "O"])))

  (it "checks if the chosen space is not within range"
    (should= false
      (valid-space? 10 )))

  (it "checks if the chosen space is not within range"
    (should= true
      (valid-space? 8)))

  (it "checks if space is both of a valid index and is open"
    (should= true
      (open-and-valid? 3 ["X" "O" "X" 4 "X" "O" "O" "X" "O"])))

  (it "returns false if space is either not valid or taken"
    (should= false
      (open-and-valid? 5 ["X" "O" "X" 4 "X" "O" "O" "X" "O"])))

  (it "returns open spaces"
    (should= '(2 8)
      (find-open-spaces ["X" 2 "X" "O" "X" "O" "O" "X" 8])))

  (it "places move on board"
    (should= [1 2 3 4 5 6 7 8 "X"]
      (place-move 8 "X" [1 2 3 4 5 6 7 8 9])))

  (it "deletes a move that was placed on the board"
    (should= [1 2 3 4 5 6 7 8 9]
      (delete-move 8 [1 2 3 4 5 6 7 8 "X"])))

  (it "finds opponent's piece given O"
    (should= "X"
      (find-opponent-piece "O" ["X" "O" "X"
                                4 "X" "O"
                                "O" "X" "O"])))

  (it "finds opponent's piece given X"
    (should= "O"
      (find-opponent-piece "X" ["X" "O" "X"
                                4 "X" "O"
                                "O" "X" "O"])))

  (it "returns true if winner in first row"
    (should= true
      (winner-row-one? "X" ["X" "X" "X"
                            4 "X" "O"
                            "O" "X" "O"])))

  (it "returns false if no winner in first row"
    (should= false
      (winner-row-one? "O" ["O" "X" "X"
                            4 "X" "O"
                            "O" "X" "O"])))

  (it "returns true if winner in second row"
    (should= true
      (winner-row-two? "O" ["O" "X" "X"
                            "O" "O" "O"
                            "O" "X" "O"])))

  (it "returns false if no winner in second row"
    (should= false
      (winner-row-two? "O" ["O" "X" "X"
                            "X" "O" "O"
                            "O" "X" "O"])))

  (it "returns true if winner in third row"
    (should= true
      (winner-row-three? "X" ["O" "X" "X"
                              "X" "O" "O"
                              "X" "X" "X"])))

  (it "returns false if no winner in third row"
    (should= false
      (winner-row-three? "O" ["O" "X" "X"
                              "X" "O" "O"
                              "X" "X" "X"])))

  (it "returns true if winner in first diagonal"
    (should= true
      (winner-diagonal-one? "O" ["O" "X" "X"
                                 "X" "O" "O"
                                 "X" "X" "O"])))

  (it "returns false if no winner in first diagonal"
    (should= false
      (winner-diagonal-one? "X" ["O" "X" "X"
                                 "X" "O" "O"
                                 "X" "X" "O"])))

  (it "returns true if winner in second diagonal"
    (should= true
      (winner-diagonal-two? "X" ["O" "O" "X"
                                 "O" "X" "O"
                                 "X" "O" "X"])))

  (it "returns false if no winner in second diagonal"
    (should= false
      (winner-diagonal-two? "O" ["O" "O" "X"
                                 "O" "X" "O"
                                 "X" "O" "X"])))

  (it "returns true if winner in first column"
    (should= true
      (winner-column-one? "X" ["X" "X" "X"
                               "X" "O" "O"
                               "X" "X" "O"])))

   (it "returns false if no winner in first column"
    (should= false
      (winner-column-one? "O" ["X" "X" "X"
                               "X" "O" "O"
                               "X" "X" "O"])))

   (it "returns true if winner in second column"
    (should= true
      (winner-column-two? "O" ["X" "O" "X"
                               "O" "O" "X"
                               "X" "O" "O"])))

   (it "returns false if no winner in second column"
    (should= false
      (winner-column-two? "X" ["X" "O" "X"
                               "O" "O" "X"
                               "X" "O" "O"])))

   (it "returns true if winner in third column"
    (should= true
      (winner-column-three? "X" ["X" "O" "X"
                                 "O" "O" "X"
                                 "X" "O" "X"])))

   (it "returns false if no winner in third column"
    (should= false
      (winner-column-three? "O" ["X" "O" "X"
                                 "O" "O" "X"
                                 "X" "O" "X"])))
 )
