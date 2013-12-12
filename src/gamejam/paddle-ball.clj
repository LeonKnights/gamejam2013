(ns gamejam.paddle-ball
 (:use quil.core))

(defn setup []
  (def ball-x (atom 200))
  (def ball-y (atom 100))
  (def speed-x (atom 10))
  (def speed-y (atom 2))
  (def hit (atom 0))
  (def miss (atom 0)))

(def simple-red [255 0 0])
(def simple-green [0 255 0])
(def simple-blue [0 0 255])


(defn make-a-ball [& {:keys [x y size color] :or {x @ball-x y @ball-y size 100 color simple-red}}]
  (let [[r g b] color h (/ size 2) w h]
   (do (fill r g b)
      (ellipse x y h w))))

;;more composability for the win
(defn make-a-crazy-ball []
  (let [rand-rgb [(rand-int 255) (rand-int 255) (rand-int 255)]]
  (make-a-ball :size (rand-int 400) :color rand-rgb)
  (make-a-ball :size 100) (make-a-ball :size 50 :color [0 0 0])))

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
  ;;(fill 200 100 50)
  ;;(ellipse @ball-x, @ball-y, 50, 50)
  ;;replacing with function call

;;  (make-a-ball :size (rand-int 400) :color simple-blue)
;;  (make-a-ball :size 200 )
;;replacing with a function call
(make-a-crazy-ball)
  (fill 50 100 200)
  (rect (- (mouse-x) paddle) (- (height) 10) (* 2 paddle), 10)
  (fill 0)
  (text (str "Hit: " @hit) 10 10)
(text (str "Miss: " @miss) 10 20)
(text (str (int (current-frame-rate))) 10 30))


(defsketch bouncey-paddle-balley
  :title "OMG OMG OMG Bouncey Ball!!!"
  :setup setup
  :draw draw
  :size [600 400])
