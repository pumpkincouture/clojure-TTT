(ns clojure-tic-tac-toe.ui)

(defn print-welcome []
 (println "Welcome to Tic Tac Toe!"))

(defn prompt-for-piece []
  (println "Human, please choose your game piece :"))

(defn prompt-for-ai-piece []
  (println "Human, please pick your opponent's game piece :"))

(defn print-board [board]
  (println (get board 0) (get board 1) (get board 2))
  (println (get board 3) (get board 4) (get board 5))
  (println (get board 6) (get board 7) (get board 8))
)

(defn prompt-for-move [player-piece]
  (println "Player" player-piece ", please choose a number :"))

(defn print-choice [player-piece choice]
  (println "Player" player-piece "chose space" choice))

(defn print-winner [player-piece]
  (println player-piece "wins!"))

(defn print-tie-game []
 (println "Cat's game!"))

(defn print-error []
  (println "That is not a valid choice, please try again!"))
