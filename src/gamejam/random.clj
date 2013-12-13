(ns gamejam.random (:use quil.core))

(defn fract [v]
  (- v (int v)))

(defn random-int [a b]
  (int (random a b)))

(defn random-bool []
  (= 1 (int (random 0 2))))

(defn setup []
  (smooth)
  (frame-rate 30)
  (stroke 0 0)
  (stroke-weight 0))

(defn random-fill []
  (case (random-int 0 4)
    0 (fill  (rand-int 255) (rand-int 255)  (rand-int 255))
    1 (fill 10 127  94)
    2 (fill  69  69  69)
    3 (fill (rand-int 200))))

(defn random-xy [d]
  (let [rf (if (random-bool) + -)]
    (rf (* 10 (random-int 0 90)) d)))

(defn draw []
  (let [ms (/ (millis) 10000)
        t (- 1.0 (fract ms))
        t (* t t)
        d (+ 90 (* 25 t))
        c (rand-int 220)]
    (background c)
    (random-seed (+ (day) ms))
    (dotimes [i (rand-int c)]
      (random-fill)
      (ellipse (random-xy d) (random-xy d) d d))
    (when (and (< c 100) (even? c)) (fill c))
    (rect   1   0 100 900)
    (rect   0   0 900 100)
    (rect 800   0 100 900)
    (rect   0 800 900 100)))

(defsketch doodle :title "random" :setup setup :draw draw :size [900 900])
