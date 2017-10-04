(ns optimamock.authorize)

(defn authorized? [username password]
    (and 
        (= username "keios")
        (= password "chaos")))