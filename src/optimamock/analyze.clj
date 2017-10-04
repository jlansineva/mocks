(ns optimamock.analyze
    (:require [clojure.string :as string]))

(def keywords ["Robotic Process Automation"])

(def unwanted-keywords ["Matti Meikäläinen" "Rise of the Planet of the Apes"])

(defn- has-keyword? [essay keyword]
    (string/includes? essay keyword))

(defn has-keywords? [essay keywords]
    (if (nil? keywords) 
        true
        (if (has-keyword? essay (first keywords))
            (has-keywords? essay (next keywords))
            false)))