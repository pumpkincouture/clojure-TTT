(ns clojure-tic-tac-toe.ai-game-integration-spec
  (:use speclj.core)
  (:use clojure-tic-tac-toe.game))

  (it "should play ai vs ai game"
    (let [board [1 2 3 4 5 6 7 8 9]
          game-over-message "game over"]
      (with-redefs [println (constantly "game over")
                    prn (constantly true)]
      (should= game-over-message
        (start-game {:second-type "ai"
                     :first-type "ai"
                     :first-marker "X"
                     :second-marker "O"
                     :board [1 2 3 4 5 6 7 8 9]})))))
