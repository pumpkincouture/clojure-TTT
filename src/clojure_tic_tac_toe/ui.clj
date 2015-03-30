(ns clojure-tic-tac-toe.ui
  (:require
    [clojure-tic-tac-toe.board :as board]))

(defn print-welcome []
 (println "Welcome to Tic Tac Toe! Please indicate your choice with a number. "))

(defn prompt-for-piece []
  (println "Human, please choose your game piece :")
  (read-line))

(defn prompt-for-ai-piece []
  (println "Human, please pick your opponent's game piece :")
  (read-line))

(defn print-board [board]
  (println "")
  (prn "-----------")
  (loop [start (- (board/size? board) (board/size? board)) middle (+ 1 start) end (+ 2 (- (board/size? board) (board/size? board)))]
    (prn (str " " (board start) " | " (board middle) " | " (board end) " "))
    (prn "-----------")
    (if (= end (- (board/size? board) 1))
      (println "")
      (recur (+ start 3) (+ middle 3) (+ end 3)))))

(defn prompt-for-move [player-piece]
  (println "Player" player-piece ", please choose a number :")
  (read-line))


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
