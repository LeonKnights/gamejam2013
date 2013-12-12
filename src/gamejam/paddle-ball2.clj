    ;Nikita Beloglazov's version using a state map
;;gist is here https://gist.github.com/nbeloglazov/7920049

(ns paddle-ball2
  (:require [quil.core :refer :all]))


(def initial-state
  {:ball-x 200
   :ball-y 100
   :speed-x 10
   :speed-y 2
   :hit 0
   :miss 0})

(defn setup []
  (set-state! :state (atom initial-state)))

(defn update-hit-miss [state]
  (if (> (:ball-y state) (height))
    (let [paddle (/ 1000.0 (+ (:hit state) 10))
          ball-inside-paddle (< (abs (- (mouse-x) (:ball-x state))) paddle)
          hit-or-miss (if ball-inside-paddle :hit :miss)]
      (update-in state [hit-or-miss] inc))
    state))

(defn update-speed [state]
  (println state)
  (-> state
      (update-in [:speed-x] (if (<= 0 (:ball-x state) (width))
                              + -))
      (update-in [:speed-y] (if (> (:ball-y state) (height))
                              - inc))))

(defn update-position [state]
  (-> state
      (update-in [:ball-x] + (:speed-x state))
      (update-in [:ball-y] + (:speed-y state))))

(defn update-state [state]
  (-> state
      update-speed
      update-position
      update-hit-miss))

(defn draw-state [state]
  (background 100 200 50)
  (fill 200 100 50)
  (ellipse (:ball-x state) (:ball-y state) 50, 50)
  (fill 50 100 200)
  (let [paddle (/ 1000.0 (+ (:hit state) 10))]
    (rect (- (mouse-x) paddle) (- (height) 10) (* 2 paddle), 10))
  (fill 0)
  (text (str "Hit: " (:hit state)) 10 10)
  (text (str "Miss: " (:miss state)) 10 20)
  (text (str (int (current-frame-rate))) 10 30))

(defn iteration []
  (swap! (state :state) update-state)
  (draw-state @(state :state)))

(defsketch bouncey-paddle-balley
  :title "OMG OMG OMG Bouncey Ball!!!"
  :setup setup
  :draw iteration
  :size [600 400])
