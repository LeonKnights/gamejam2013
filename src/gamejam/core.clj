(ns gamejam.core
  (:gen-class)
  (:use quil.core))

(defn setup []
  (smooth)
  (frame-rate 1)
  (background 200))

(defn draw []
  (stroke (random 255))
  (stroke-weight (random 10))
  (fill (random 255))

  (let [diam (random 100)
        x    (random (width))
        y    (random (height))]
    (ellipse x y diam diam)))


(defn draw2 []
  (stroke (random 255))
  (stroke-weight (random 10))
  (fill (random 255) (random 255) (random 255))

  (let [diam (random 100)
        x    (random (width))
        y    (random (height))]
    (ellipse x y diam diam)))

(defn -main
  "This is the main entry point for the game when it is uber-jarred"
  [& args]
  (sketch
    :title "!!!MAIN GAME!!!!"
    :setup setup
    :draw draw2
    :size [323 200]))
