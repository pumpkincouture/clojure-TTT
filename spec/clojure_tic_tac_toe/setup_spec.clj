(ns clojure-tic-tac-toe.setup-spec
  (:use speclj.core)
  (:use clojure-tic-tac-toe.setup))

(describe "Setup"
  (around [it]
    (with-out-str (it)))

  (it "should return the first player's piece choice"
      (with-redefs [read-line (constantly "X")]
      (should= "X"
         (get-first-player))))

  (it "should return the second players piece choice"
      (with-redefs [read-line (constantly "O")]
      (should= "O"
         (get-second-player))))

  (it "should return the board cells"
      (should= [1 2 3 4 5 6 7 8 9]
         (get-board)))

  (it "should return list of all options"
      (with-redefs [read-line (constantly "X")]
      (should= {:second-type "ai"
                :first-type "human"
                :first-marker "X"
                :second-marker "X"
                :board [1 2 3 4 5 6 7 8 9]}
         (get-options))))
  )
