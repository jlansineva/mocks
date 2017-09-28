(ns optimamock.static.optima)

(defn- defaults []
 [:html 
    [:head]
    [:body
        [:div "Moi"]]])

(defn- error-login []
    [:div "Error"])

(defn- success-login []
    [:div "Success"])

(defn- urkund-page [])

(defn- main-page []
    (defaults))

(defn optima [options]
    (let [site (:site options)]
        (cond 
            (= site "main")
                (main-page)
            (= site "success")
                (success-login)
            (= site "error")
                (error-login))))