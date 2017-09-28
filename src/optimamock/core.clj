(ns optimamock.core
  (:require [compojure.core :as c-core]
            [compojure.route :as route]
            [compojure.handler :as handler]
            [org.httpkit.server :as server]
            [hiccup.core :as hiccup]
            [optimamock.static.optima :as optima]))

(defn homepage [req]
  (hiccup/html (optima/optima {:site "success"})))

(c-core/defroutes app
  (c-core/GET "/" [] homepage))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (server/run-server (handler/site #'app) {:port 8080}))
