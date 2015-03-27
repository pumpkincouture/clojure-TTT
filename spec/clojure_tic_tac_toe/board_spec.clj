(ns clojure-tic-tac-toe.board-spec
  (:use speclj.core)
  (:use clojure-tic-tac-toe.board))

(describe "Board"
  (it "creates a new board"
    (should= [1 2 3 4 5 6 7 8 9]
      (make-new 9)))

  (it "checks size of the board"
    (should= 9
      (size? [1 2 3 4 5 6 7 8 9])))

  (it "gets the square root of the board"
    (should= 3
      (square-root? [1 2 3 4 5 6 7 8 9])))

  (it "checks if space is open"
    (should= true
      (open-space? 4, [1 2 3 4 5 6 7 8 9])))

  (it "checks if a false input is valid"
    (should= false
      (open-space? false, [1 2 3 4 5 6 7 8 9])))

  (it "returns true if board is full"
    (should= true
      (full? ["X" "O" "X" "O" "X" "O" "O" "X" "O"])))

  (it "returns false if board is not full"
    (should= false
      (full? ["X" "O" "X" 4 "X" "O" "O" "X" "O"])))

  (it "returns the correct player"
    (should= {:player-type "human" :player-piece "X"}
      (current-player ["X" "O" "X" 4 "X" "O" "O" "X" "O"] {:player-type "human" :player-piece "X"} {:player-type "computer" :player-piece "O"})))

  (it "returns true if board is full"
    (should= true
      (full? ["X" "O" "X" "O" "X" "O" "O" "X" "O"])))

  (it "returns true if board is empty"
    (should= true
      (board-empty? [1 2 3 4 5 6 7 8 9])))

  (it "checks if the chosen space is not within range"
    (should= false
      (valid-space? 10 [1 2 "X" "X" 5 "O" "O" "X" "O"])))

  (it "checks if the chosen space is within range"
    (should= true
      (valid-space? 8 [1 2 "X" "X" 5 "O" "O" "X" "O"])))

  (it "checks if space is both of a valid index and is open"
    (should= true
      (open-and-valid? 3 ["X" "O" "X" 4 "X" "O" "O" "X" "O"])))

  (it "returns false if invalid index"
    (should= false
      (open-and-valid? 9 [1 2 3 4 5 6 7 8 9])))

  (it "returns false if space is taken"
    (should= false
      (open-and-valid? 6 ["X" "O" "X" 4 "X" "O" "O" "X" "O"])))

  (it "returns open spaces"
    (should= '(2 8)
      (find-open-spaces ["X" 2 "X" "O" "X" "O" "O" "X" 8])))

  (it "returns open spaces for board"
    (should= '(1 2 5 8)
      (find-open-spaces [1 2 "X" "O" 5 "O" "O" "X" 8])))

  (it "places move on board"
    (should= [1 2 3 4 5 6 7 8 "X"]
      (place-move 8 "X" [1 2 3 4 5 6 7 8 9])))

  (it "deletes a move that was placed on the board"
    (should= [1 2 3 4 5 6 7 8 9]
      (delete-move 8 [1 2 3 4 5 6 7 8 "X"])))

  (it "finds opponent's piece given O"
    (should= "X"
      (find-opponent-piece "O" ["X" "O" "X"
                                 4  "X" "O"
                                "O" "X" "O"])))

  (it "finds opponent's piece given X"
    (should= "O"
      (find-opponent-piece "X" ["X" "O" "X"
                                 4  "X" "O"
                                "O" "X" "O"])))

  (it "returns false if no row winner on the board"
     (should= false
       (row-winner? "X" ["X" "O" "O"
                         "O" "X" "X"
                         "X" "X" "O"])))

  (it "returns false if no winners in any rows"
     (should= false
      (row-winner? "X" ["X" "O" "X"
                         4  "X" "O"
                        "O" "X" "O"])))

  (it "returns true if there is a winner in the first row"
     (should= false
      (row-winner? "X" ["O" "O" "O"
                        "X" "X" "O"
                        "O" "X" "O"])))

  (it "returns true if there is a winner in the second row"
     (should= true
      (row-winner? "X" ["X" "O" "X"
                        "X" "X" "X"
                        "O" "X" "O"])))

  (it "returns true if there is a winner in the third row"
    (should= true
      (row-winner? "O"     [ 1  "X" "X"
                             4  "X" "O"
                            "O" "O" "O"])))

  (it "returns false if no diagonal winner on the board"
     (should= false
       (diagonal-winner? "X" ["X" "O" "O"
                              "O" "X" "X"
                              "X" "X" "O"])))

  (it "returns true if winner in first diagonal"
    (should= true
      (diagonal-winner? "O" ["O" "X" "X"
                             "X" "O" "O"
                             "X" "X" "O"])))

  (it "returns false if no winner in first diagonal"
    (should= false
      (diagonal-winner? "X" ["O" "X" "X"
                             "X" "O" "O"
                             "X" "X" "O"])))

  (it "returns true if winner in second diagonal"
    (should= true
      (diagonal-winner? "X" ["O" "O" "X"
                             "O" "X" "O"
                             "X" "O" "X"])))

  (it "returns false if no winner in second diagonal"
    (should= false
      (diagonal-winner? "O" ["O" "O" "X"
                             "O" "X" "O"
                             "X" "O" "X"])))

  (it "returns true if winner in first column"
    (should= true
      (column-winner? "X" ["X" "X" "X"
                           "X" "O" "O"
                           "X" "X" "O"])))

  (it "returns false if no winner in any column"
    (should= false
      (column-winner? "X" ["O" "X" "X"
                           "X" "O" "O"
                           "X" "X" "O"])))

   (it "returns true if winner in second column"
    (should= true
      (column-winner? "O" ["X" "O" "X"
                           "O" "O" "X"
                           "X" "O" "O"])))

   (it "returns true if winner in third column"
    (should= true
      (column-winner? "X" ["X" "O" "X"
                           "O" "O" "X"
                           "X" "O" "X"])))

  (it "returns true if there is a winner somewhere on the board"
     (should= "X"
       (winner? "O" "X" ["O" "X" "X"
                         "X" "O" "O"
                         "X" "X" "X"])))

  (it "returns false if there is no winner on the board"
     (should= false
       (winner? "O" "X" ["O" "X" "X"
                         "X" "O" "O"
                         "X" "O" "X"])))

  (it "returns false if no winner on the board"
     (should= false
       (winner? "O" "X" ["X" "O" "O"
                         "O" "X" "X"
                         "X" "X" "O"])))

  (it "returns false if there is a winner on the board"
      (should= false
       (tie? ["X"  2  "X"
              "X" "O" "O"
              "X"  8  "X"]"O" "X")))

  (it "returns true if there is a tie on the board when the boad is full"
      (should= true
       (tie? ["O" "X" "X"
              "X" "O" "O"
              "X" "O" "X"]"O" "X")))

  (it "returns false if there is a winner on the board when the boad is full"
      (should= false
       (tie? ["O" "X" "X"
              "O" "O" "O"
              "X" "O" "X"]"O" "X")))

  (it "returns true if board is full and there is no winner"
     (should= true
       (game-over? "O" "X" ["O" "X" "X"
                            "X" "O" "O"
                            "X" "O" "X"])))

  (it "returns false if no winner and board is not full"
     (should= false
       (game-over? "O" "X" [1  "X" "X"
                           "X" "O" "O"
                           "X" "O" "X"])))

  (it "returns true if there is a winner"
     (should= true
       (game-over? "O" "X" ["X" "X" "X"
                            "X" "O" "O"
                            "X"  8   9])))
 )
