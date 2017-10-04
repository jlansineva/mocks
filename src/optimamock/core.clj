(ns optimamock.core
  (:require [compojure.core :as c-core]
            [compojure.route :as route]
            [compojure.handler :as handler]
            [org.httpkit.server :as server]
            [ring.util.response :as resp]
            [optimamock.static.optima :as optima]
            [optimamock.authorize :as auth]
            [optimamock.analyze :as analyze]
            ))

(defn homepage [req]
   (optima/optima optima/main))

(defn work-handler [req]
  (if 
    (and 
      (analyze/has-keywords? (-> req :params :essee) analyze/keywords)
      (not (analyze/has-keywords? (-> req :params :essee) analyze/unwanted-keywords)))
    (resp/resource-response "layout-good.html" {:root "public"})
    (resp/resource-response "layout-error.html" {:root "public"})
  ))

(defn login-handler [req]
  (println "got post req...." req)
  (let [username (-> req :params :username)
        password (-> req :params :password)]
        (if (auth/authorized? username password) 
          (resp/resource-response "layout.html" {:root "public"})
          (optima/optima optima/error))))

(c-core/defroutes app
  (c-core/GET "/" [] (resp/resource-response "index.html" {:root "public"}))
  (c-core/POST "/login" [] login-handler)
  (c-core/POST "/submit-work" [] work-handler)
  (route/resources "/")
  (route/not-found "Not found"))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (server/run-server (handler/site #'app) {:port 8099}))
