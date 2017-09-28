(ns optimamock.static.optima
    (:require 
            [hiccup.form :as form]
            [hiccup.core :as hiccup]))

(def success {:site "success"})

(def main {:site "main"})

(def error {:site "error"})

(defn- defaults []
 [:html 
    [:head]
    [:body
        [:div (form/form-to [:post "/login"]
            [:div
                (form/text-field {:placeholder "Anna käyttäjätunnus"} "username")]
            [:div    
                (form/password-field {:placeholder "Anna salsasana"} "password")]
            [:div
                (form/submit-button "Kirjaudu sisään")])]]])

(defn- error-login []
    [:div "Error"])

(defn- success-login []
    [:div "Success"])

(defn- urkund-page [])

(defn- main-page []
    (defaults))

(defn optima [options]
    (let [site (:site options)]
       (hiccup/html (cond 
            (= site "main")
                (main-page)
            (= site "success")
                (success-login)
            (= site "error")
                (error-login)))))