(ns clojure-tic-tac-toe.ui
  (:require
    [clojure-tic-tac-toe.board :as board]))

(defn print-welcome []
 (println "Welcome to Tic Tac Toe! Please indicate your choice with a number. "))

(defn prompt-for-piece []
  (println "Human, please choose your game piece :"))

(defn prompt-for-ai-piece []
  (println "Human, please pick your opponent's game piece :"))

(defn print-board [board]
  (prn "                 ")
  (prn (str "  " (board 0) "  |  " (board 1) "  |  " (board 2) "  "))
  (prn "-----------------")
  (prn (str "  " (board 3) "  |  " (board 4) "  |  " (board 5) "  "))
  (prn "-----------------")
  (prn (str "  " (board 6) "  |  " (board 7) "  |  " (board 8) "  "))
  (prn "                 ")
)

(defn prompt-for-move [player-piece]
  (println "Player" player-piece ", please choose a number :"))

(defn print-choice [player-piece choice]
  (println "Player" player-piece "chose space" (inc choice)))

(defn print-winner [player-piece]
  (println player-piece "wins!"))

(defn print-tie-game []
 (println "Cat's game!"))

(defn print-result [piece-one piece-two board]
  (let [winner-string (board/winner? piece-one piece-two board)]
  (cond
    (string? winner-string) (print-winner winner-string)
    :else (print-tie-game))))

(defn print-error []
  (println "That is not a valid choice, please try again!"))
