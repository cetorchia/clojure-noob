(ns clojure-noob.fibonacci
  (:gen-class))

(defn sum-of-even-fibonacci-numbers
  [max-value]
  (loop [sum-of-evens 0
         even-num 0
         odd-num 1]
    (let [next-odd-num (+ even-num odd-num)
          next-even-num (+ odd-num next-odd-num)
          next-next-odd-num (+ next-odd-num next-even-num)]
      (if (> even-num max-value)
        sum-of-evens
        (recur (+ sum-of-evens even-num) next-even-num next-next-odd-num)))))
