(def send-to
  (fn [object message & args]
    (apply (message (:__methods__ object)) object args)))

(def make
  (fn [klass & args] (apply klass args)))

(def Point
  (fn [x y]
    {:x x
     :y y
     :__class__symbol__ 'Point
     :methods {
               :class :__class_symbol
               :shift (fn [this xinc yinc]
                        (make Point
                              (+ (:x this) xinc)
                              (+ (:y this) yinc)))}}))
