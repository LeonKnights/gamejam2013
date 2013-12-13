(ns gamejam.circles (:use quil.core))

(defn setup []
  (frame-rate 24)
  (smooth)
  (background 180)
  (stroke 0)
  (stroke-weight 1)
  (no-fill)
  (set-state! :diam (atom 10)
              :cent-x (atom (rand-int (width)))
              :cent-y (atom (rand-int (height)))))

(defn draw []
  (let [cent-x (state :cent-x)
        cent-y (state :cent-y)
        diam   (state :diam)]
    (if (<= @diam (rand-int 400))(do
              (stroke (rand-int 255) (rand-int 255) (rand-int 255))
              (ellipse @cent-x @cent-y @diam @diam)
              (swap! diam + 10))
          (do (background (rand-int 255) (rand-int 255) (rand-int 255))
              (reset! diam 10)
              (reset! cent-x (rand-int (width)))
              (reset! cent-y (rand-int (height)))))))

(defsketch gen-art-3
  :title "Concentric Circles"
  :setup setup
  :draw draw
  :size [500 300])
