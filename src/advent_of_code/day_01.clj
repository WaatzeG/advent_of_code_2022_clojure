(ns advent-of-code.day-01
  (:require [clojure.string :as str]))

(defn part-1
  "Day 01 Part 1"
  [input]
  (->> (str/split input #"\n\n")
       (map str/split-lines)
       (map (partial map read-string))
       (map (partial reduce +))
       (reduce max)))


(defn part-2
  "Day 01 Part 2"
  [input]
  (->> (str/split input #"\n\n")
       (map str/split-lines)
       (map (partial map read-string))
       (map (partial reduce +))
       (sort >)
       (take 3)
       (reduce +)))

