(ns optimamock.core
  (:require [compojure.core :as c-core]
            [compojure.route :as route]
            [compojure.handler :as handler]
            [org.httpkit.server :as server]
            [optimamock.static.optima :as optima]
            [optimamock.authorize :as auth]))

(defn homepage [req]
   (optima/optima optima/main))

(defn login-handler [req]
  (println "got post req...." req)
  (let [username (-> req :params :username)
        password (-> req :params :password)]
        (if (auth/authorized? username password) 
          (optima/optima optima/success)
          (optima/optima optima/error))))

(c-core/defroutes app
  (c-core/GET "/" [] homepage)
  (c-core/POST "/login" [] login-handler))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (server/run-server (handler/site #'app) {:port 8080}))
