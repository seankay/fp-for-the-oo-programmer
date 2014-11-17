(use 'clojure.test)
;; exercise 1
(def second (fn [list] (nth list 1)))

(is (= 2 (second '(1 2 3))))

;; exercise 2
(def third (fn [list] (nth list 2)))

(is (= 3 (third '(1 2 3))))

(def third (fn [list] (first (rest (rest list)))))

(is (= 3 (third '(1 2 3))))

;; exercise 3
(defn add-squares [& n]
  (apply + (map #(+ (* % %)) n)))

(is (= 30 (add-squares 1 2 5)))

;; exercise 4
(defn bizarre-factorial [x]
  (apply * (range 1 (+ x 1))))

(is (= 120 (bizarre-factorial 5)))

;; exercise 5
(is (= '(1 2 3) (take 3 (range 1 10))))
(is (= '(1 2 3 4 5 6) (distinct '(1 1 2 3 4 5 6 6))))
(is (= [1 1 2 3 4 5 6] (concat [1] [1 2 3] [4 5 6])))
(is (= [[1 2] [1 2] [1 2] [1 2] [1 2]](repeat 5 [1 2])))
(is (= [1 3 5 2 4 6] (interleave [1 2] [3 4] [5 6])))
(is (= [3] (drop 2 [1 2 3])))
(is (= [1] (drop-last 2 [1 2 3])))
(is (= [1 2 3 4 5 6 7] (flatten [[1] [2 3] [[[4 5]] 6 7]])))
(is (= [[1 2] [3 4]](partition 2 [1 2 3 4])))
(is (= true (every? #(> % 0) [1 2 3])))

;; exercise 6
(defn prefix-of? [candidate sequence]
  (loop [c candidate
         s sequence
         flag true]
    (if-not (seq c)
      flag
      (recur (rest c)
             (rest s)
             (= (first s) (first c))))))

(is (= true (prefix-of? [1 2] [1 2 3 4])))
(is (= false (prefix-of? '(2 3) [1 2 3 4])))
(is (= true (prefix-of? '(1 2) [1 2 3 4])))

;; exercise 7
(defn tails [list]
  (map #(drop % list) (range (+ 1 (count list)))))

(is (= '((1 2 3 4) (2 3 4)  (3 4) (4) ()) (tails '(1 2 3 4))))

;; exercise 8
(def puzzle #(list %))

(puzzle '(1 2 3))
