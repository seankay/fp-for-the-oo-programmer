(use 'clojure.test)
(def Point
  (fn [x y]
    {:x x
     :y y
     :__class__symbol__ 'Point}))

(:x (Point 1 2))
(:y (Point 1 2))
(:__class__symbol__ (Point 1 2))

;; getters
(def x :x)
(def y :y)
(def class-of :__class__symbol__)
(x (Point 1 2))
(y (Point 1 2))
(class-of (Point 1 2))

(def shift
  (fn [this xinc yinc]
    (Point (+ (x this) xinc)
           (+ (y this) yinc))))

(shift (Point 1 200) -1 -200)

(def Triangle
     (fn [point1 point2 point3]
       {:point1 point1, :point2 point2, :point3 point3
        :__class_symbol__ 'Triangle}))


(def right-triangle (Triangle (Point 0 0)
                              (Point 0 1)
                              (Point 1 0)))

(def equal-right-triangle (Triangle (Point 0 0)
                                    (Point 0 1)
                                    (Point 1 0)))

(def different-triangle (Triangle (Point 0 0)
                                  (Point 0 10)
                                  (Point 10 0)))

;; exercise 1 - implement add
(def add
  "Without Shift"
  (fn [p1 p2]
  (Point (+ (x p1) (y p2))
         (+ (x p2) (y p2)))))

(def add-shift
  "With Shift"
  (fn [p1 p2]
    (shift p1 (x p2) (y p2))))

(is (= (Point 2 2) (add (Point 1 1) (Point 1 1))))
(is (= (Point 2 2) (add-shift (Point 1 1) (Point 1 1))))

;; exercise 2 - a 'new' a.k.a 'make' operator
(def make
  (fn [klass & args] (apply klass args)))

(is (= (Point 1 1) (make Point 1 1)))
(is (= (Triangle (Point 1 1) (Point 2 2) (Point 3 3))
       (make Triangle
             (make Point 1 1)
             (make Point 2 2)
             (make Point 3 3))))

;; exercise 3
(def equal-triangles?
  (fn [triangle1 triangle2]
    (= triangle1 triangle2)))

(is (= true (equal-triangles? right-triangle right-triangle)))
(is (= true (equal-triangles? right-triangle equal-right-triangle)))
(is (= false (equal-triangles? right-triangle different-triangle)))

;; exercise 4
(def equal-triangles? =)

(is (= false (equal-triangles? right-triangle
                               equal-right-triangle
                               different-triangle)))
;; exercise 5
(def valid-triangle?
  (fn [& points]
    (and (= 3 (count points))
         (= (distinct points) points))))

(is (= true (valid-triangle? (Point 1 1) (Point 2 2) (Point 3 3))))
(is (= false (valid-triangle? (Point 2 2) (Point 2 2) (Point 3 3))))
(is (= false (valid-triangle? (Point 1 1) (Point 2 2) (Point 3 3) (Point 4 4))))
