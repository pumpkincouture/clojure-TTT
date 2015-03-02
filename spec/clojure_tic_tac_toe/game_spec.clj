(ns clojure-tic-tac-toe.game-spec
   (:require [speclj.core :refer :all]
             [clojure-tic-tac-toe.game        :refer :all]
             [clojure-tic-tac-toe.board         :as board]
             [clojure-tic-tac-toe.ui            :as ui]
             [clojure-tic-tac-toe.ai            :as ai]))

(describe "Game"
  (it "gets a move"
    (let [board (vec (range 9))
          current-type  "human"
          next-type     "ai"
          current-mark  "X"
          next-mark     "O"]
    (with-redefs [read-line (constantly "1")]
       (should= 1
         (get-move board current-type current-mark next-mark)))))

  (it "hits the game over message in the game loop"
    (let [board (vec (range 9))
     game-over? "game over message"]
     (with-redefs [println (constantly "game over message")
     prn  (constantly true)]
       (should= game-over?
         (game-loop "X" "O" "ai" "ai" board))))))
