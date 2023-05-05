(ns advent-of-code.day-02
  (:require [clojure.string :as str]))

(def move-values {:rock 1 :paper 2 :scissors 3})
(def moves {"A" :rock "B" :paper "C" :scissors "X" :rock "Y" :paper "Z" :scissors})
(def outcome_bonus {:win 6 :draw 3 :loss 0})
(def outcomes {"X" :loss "Y" :draw "Z" :win})
(def wins {:paper :rock :scissors :paper :rock :scissors})
(def loses {:rock :paper :paper :scissors :scissors :rock})

(defn score
  "Calculates outcome score of Rock, Paper, Scissors."
  [game-outcome player-move]
  (+ (game-outcome outcome_bonus) (player-move move-values)))

(defn character-to-move
  "Translate a character into a move"
  [character]
  (get moves character))

(defn game-outcome
  "Determines the outcome of a game of Rock, Paper, Scissors from the perspective of the player"
  [opponent-move player-move]
  (if (= opponent-move player-move)
    :draw
    (if (= (opponent-move wins) player-move)
      :loss
      :win
      )))

(defn part-1
  "Day 02 Part 1"
  [input]
  (->> (str/split-lines input)
       (map #(str/split % #" "))
       (map #(let [[first-char second-char] %
                   opponent-move (character-to-move first-char)
                   player-move (character-to-move second-char)]
               (score (game-outcome opponent-move player-move) player-move)))
       (reduce +)))

(defn move-for-outcome
  [outcome opponent-move]
  (condp = outcome
    :win (opponent-move loses)
    :loss (opponent-move wins)
    :draw opponent-move))

(defn part-2
  "Day 02 Part 2"
  [input]
  (->> (str/split-lines input)
       (map #(str/split % #" "))
       (map #(let [[first-char second-char] %
                   opponent-move (get moves first-char)
                   desired-outcome (get outcomes second-char)]
               (score desired-outcome (move-for-outcome desired-outcome opponent-move))))
       (reduce +)))