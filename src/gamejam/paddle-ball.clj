(ns gamejam.paddle-ball
 (:use quil.core))
 
(defn setup []
  (def ball-x (atom 200))
  (def ball-y (atom 100))
  (def speed-x (atom 10))
  (def speed-y (atom 2))
  (def hit (atom 0))
  (def miss (atom 0)))
 
 
(defn draw []
  (def paddle (/ 1000.0 (+ @hit 10)))
  
(when  (or (neg? @ball-x) (> @ball-x (width))) (swap! speed-x -))
 
  (if (> @ball-y (height))
      (do (swap! speed-y -)
          (if (< (abs (- (mouse-x) @ball-x)) paddle)
            (swap! hit inc) (swap! miss inc)))
      (swap! speed-y #(+ 1 %)))
 
  (swap! ball-x #(+ @speed-x %))
  (swap! ball-y #(+ @speed-y %))
  (background 100 200 50)
  (fill 200 100 50)
  (ellipse @ball-x, @ball-y, 50, 50)
  (fill 50 100 200)
  (rect (- (mouse-x) paddle) (- (height) 10) (* 2 paddle), 10)
  (fill 0)
  (text (str "Hit: " @hit) 10 10)
  (text (str "Miss: " @miss) 10 20))
 
 
(defsketch bouncey-paddle-balley
  :title "OMG OMG OMG Bouncey Ball!!!"
  :setup setup
  :draw draw
  :size [600 400])
