(ns clojure-tic-tac-toe.game-spec
  (:use speclj.core)
  (:use clojure-tic-tac-toe.game))

(describe "Game"
  (it "returns true if input is valid"
    (let [input "easy"]
      (should= true
        (validate-type input))))

  (it "returns nil if input is invalid"
    (let [input "hhh"]
      (should= nil
        (validate-type input))))

  (it "does not go into continuous if input is wrong"
    (let [input "y"
          input "easy"]
      (should= "easy"
        (get-player-choice input))))
)
