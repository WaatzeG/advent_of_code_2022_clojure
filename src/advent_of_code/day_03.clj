(ns advent-of-code.day-03
  (:require [clojure.string :as str])
  (:require clojure.set))


(defn compartments
  "Takes contents of a rucksack and split into compartments"
  [rucksack]
  (split-at (/ (count rucksack) 2) rucksack))


(defn get_faulty_item
  "Takes two compartments and returns the faulty item as a set"
  ([args]
   (apply clojure.set/intersection (map set args))))

(defn get_value_for_item
  "Gets the sum of values of the chars string seq"
  [chars]
  (->>
    (map int chars)
    (map #(if (> % 96)                                      ;upper-case ASCII value or lower-case ASCII value
            (- % 96)
            (- % 38)))
    (reduce +)))

(defn part-1
  "Day 03 Part 1"
  [input]
  (->>
    (str/split-lines input)
    (map compartments)
    (map get_faulty_item)
    (map get_value_for_item)
    (reduce +)))

(defn part-2
  "Day 03 Part 2"
  [input]
  (->>
    (str/split-lines input)
    (partition 3)
    (map get_faulty_item)
    (map get_value_for_item)
    (reduce +)))
