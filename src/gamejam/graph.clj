(ns graph
  (:use quil.core)
  (:import [java.awt GraphicsEnvironment
            Robot Rectangle image.BufferedImage]
           [processing.core PImage]))

(defn grab-screen []
  (let [ge (GraphicsEnvironment/getLocalGraphicsEnvironment)
        gs (.getScreenDevices ge)
        bounds (Rectangle. 320 240  640 480)]
    (.createScreenCapture (Robot. (aget gs 0)) bounds)))

(defn setup []
  (frame-rate 30))

(defn draw []
  (let [img (PImage. (grab-screen))]
    (image img 0 0)))

(defsketch live-coding
  :setup setup
  :draw draw
  :size [640 480])
